package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TMCOTIZACION;
import com.allianze.apicotizador.dto.TMCOTIZACIONDTO;
import com.allianze.apicotizador.repository.TMCOTIZACIONRepository;
import com.allianze.apicotizador.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.allianze.apicotizador.domain.TMCOTIZACION}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TMCOTIZACIONResource {

    private final Logger log = LoggerFactory.getLogger(TMCOTIZACIONResource.class);

    private static final String ENTITY_NAME = "tMCOTIZACION";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TMCOTIZACIONRepository tMCOTIZACIONRepository;

    public TMCOTIZACIONResource(TMCOTIZACIONRepository tMCOTIZACIONRepository) {
        this.tMCOTIZACIONRepository = tMCOTIZACIONRepository;
    }

    /**
     * {@code POST  /tmcotizacions} : Create a new tMCOTIZACION.
     *
     * @param tMCOTIZACION the tMCOTIZACION to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tMCOTIZACION, or with status {@code 400 (Bad Request)} if the tMCOTIZACION has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tmcotizacions")
    public ResponseEntity<TMCOTIZACION> createTMCOTIZACION(@Valid @RequestBody TMCOTIZACION tMCOTIZACION) throws URISyntaxException {
        log.debug("REST request to save TMCOTIZACION : {}", tMCOTIZACION);
        if (tMCOTIZACION.getIdCotizacion() != null) {
            throw new BadRequestAlertException("A new tMCOTIZACION cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TMCOTIZACION result = tMCOTIZACIONRepository.save(tMCOTIZACION);
        return ResponseEntity
            .created(new URI("/api/tmcotizacions/" + result.getIdCotizacion()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdCotizacion().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tmcotizacions/:idCotizacion} : Updates an existing tMCOTIZACION.
     *
     * @param idCotizacion the id of the tMCOTIZACION to save.
     * @param tMCOTIZACION the tMCOTIZACION to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tMCOTIZACION,
     * or with status {@code 400 (Bad Request)} if the tMCOTIZACION is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tMCOTIZACION couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tmcotizacions/{idCotizacion}")
    public ResponseEntity<TMCOTIZACION> updateTMCOTIZACION(
        @PathVariable(value = "idCotizacion", required = false) final Long idCotizacion,
        @Valid @RequestBody TMCOTIZACION tMCOTIZACION
    ) throws URISyntaxException {
        log.debug("REST request to update TMCOTIZACION : {}, {}", idCotizacion, tMCOTIZACION);
        if (tMCOTIZACION.getIdCotizacion() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCotizacion, tMCOTIZACION.getIdCotizacion())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tMCOTIZACIONRepository.existsById(idCotizacion)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TMCOTIZACION result = tMCOTIZACIONRepository.save(tMCOTIZACION);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tMCOTIZACION.getIdCotizacion().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tmcotizacions/:idCotizacion} : Partial updates given fields of an existing tMCOTIZACION, field will ignore if it is null
     *
     * @param idCotizacion the id of the tMCOTIZACION to save.
     * @param tMCOTIZACION the tMCOTIZACION to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tMCOTIZACION,
     * or with status {@code 400 (Bad Request)} if the tMCOTIZACION is not valid,
     * or with status {@code 404 (Not Found)} if the tMCOTIZACION is not found,
     * or with status {@code 500 (Internal Server Error)} if the tMCOTIZACION couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tmcotizacions/{idCotizacion}", consumes = "application/merge-patch+json")
    public ResponseEntity<TMCOTIZACION> partialUpdateTMCOTIZACION(
        @PathVariable(value = "idCotizacion", required = false) final Long idCotizacion,
        @NotNull @RequestBody TMCOTIZACION tMCOTIZACION
    ) throws URISyntaxException {
        log.debug("REST request to partial update TMCOTIZACION partially : {}, {}", idCotizacion, tMCOTIZACION);
        if (tMCOTIZACION.getIdCotizacion() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCotizacion, tMCOTIZACION.getIdCotizacion())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tMCOTIZACIONRepository.existsById(idCotizacion)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TMCOTIZACION> result = tMCOTIZACIONRepository
            .findById(tMCOTIZACION.getIdCotizacion())
            .map(
                existingTMCOTIZACION -> {
                    if (tMCOTIZACION.getUsuario() != null) {
                        existingTMCOTIZACION.setUsuario(tMCOTIZACION.getUsuario());
                    }
                    if (tMCOTIZACION.getFechaRegistro() != null) {
                        existingTMCOTIZACION.setFechaRegistro(tMCOTIZACION.getFechaRegistro());
                    }
                    if (tMCOTIZACION.getEstatus() != null) {
                        existingTMCOTIZACION.setEstatus(tMCOTIZACION.getEstatus());
                    }
                    if (tMCOTIZACION.getEliminada() != null) {
                        existingTMCOTIZACION.setEliminada(tMCOTIZACION.getEliminada());
                    }

                    return existingTMCOTIZACION;
                }
            )
            .map(tMCOTIZACIONRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tMCOTIZACION.getIdCotizacion().toString())
        );
    }

    /**
     * {@code POST  /tmcotizacions/getAll} : get all the tMCOTIZACIONS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tMCOTIZACIONS in body.
     */
    @PostMapping("/tmcotizacions/getAll")
    public List<TMCOTIZACION> getAllTMCOTIZACIONS(@RequestBody TMCOTIZACIONDTO tmcotizacionDto) {
        log.debug("REST request to get all TMCOTIZACIONS");
        return tMCOTIZACIONRepository.findAll();
    }

    /**
     * {@code POST  /tmcotizacions/getId} : get the "id" tMCOTIZACION.
     *
     * @param id the id of the tMCOTIZACION to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tMCOTIZACION, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tmcotizacions/getId")
    public ResponseEntity<TMCOTIZACION> getTMCOTIZACION(@RequestBody TMCOTIZACIONDTO tmcotizacionDto) {
        log.debug("REST request to get TMCOTIZACION : {}", tmcotizacionDto.getId());
        Optional<TMCOTIZACION> tMCOTIZACION = tMCOTIZACIONRepository.findById(tmcotizacionDto.getId());
        return ResponseUtil.wrapOrNotFound(tMCOTIZACION);
    }

    /**
     * {@code POST  /tmcotizacions/:id} : delete the "id" tMCOTIZACION.
     *
     * @param id the id of the tMCOTIZACION to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tmcotizacions/deleteId")
    public ResponseEntity<Void> deleteTMCOTIZACION(@RequestBody TMCOTIZACIONDTO tmcotizacionDto) {
        log.debug("REST request to delete TMCOTIZACION : {}", tmcotizacionDto.getId());
        tMCOTIZACIONRepository.deleteById(tmcotizacionDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tmcotizacionDto.getId().toString()))
            .build();
    }
}
