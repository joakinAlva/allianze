package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCESTATUSCOTIZACION;
import com.allianze.apicotizador.repository.TCESTATUSCOTIZACIONRepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCESTATUSCOTIZACION}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCESTATUSCOTIZACIONResource {

    private final Logger log = LoggerFactory.getLogger(TCESTATUSCOTIZACIONResource.class);

    private static final String ENTITY_NAME = "tCESTATUSCOTIZACION";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCESTATUSCOTIZACIONRepository tCESTATUSCOTIZACIONRepository;

    public TCESTATUSCOTIZACIONResource(TCESTATUSCOTIZACIONRepository tCESTATUSCOTIZACIONRepository) {
        this.tCESTATUSCOTIZACIONRepository = tCESTATUSCOTIZACIONRepository;
    }

    /**
     * {@code POST  /tcestatuscotizacions} : Create a new tCESTATUSCOTIZACION.
     *
     * @param tCESTATUSCOTIZACION the tCESTATUSCOTIZACION to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCESTATUSCOTIZACION, or with status {@code 400 (Bad Request)} if the tCESTATUSCOTIZACION has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcestatuscotizacions")
    public ResponseEntity<TCESTATUSCOTIZACION> createTCESTATUSCOTIZACION(@Valid @RequestBody TCESTATUSCOTIZACION tCESTATUSCOTIZACION)
        throws URISyntaxException {
        log.debug("REST request to save TCESTATUSCOTIZACION : {}", tCESTATUSCOTIZACION);
        if (tCESTATUSCOTIZACION.getIdEstatusCotizacion() != null) {
            throw new BadRequestAlertException("A new tCESTATUSCOTIZACION cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCESTATUSCOTIZACION result = tCESTATUSCOTIZACIONRepository.save(tCESTATUSCOTIZACION);
        return ResponseEntity
            .created(new URI("/api/tcestatuscotizacions/" + result.getIdEstatusCotizacion()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdEstatusCotizacion().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcestatuscotizacions/:idEstatusCotizacion} : Updates an existing tCESTATUSCOTIZACION.
     *
     * @param idEstatusCotizacion the id of the tCESTATUSCOTIZACION to save.
     * @param tCESTATUSCOTIZACION the tCESTATUSCOTIZACION to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCESTATUSCOTIZACION,
     * or with status {@code 400 (Bad Request)} if the tCESTATUSCOTIZACION is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCESTATUSCOTIZACION couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcestatuscotizacions/{idEstatusCotizacion}")
    public ResponseEntity<TCESTATUSCOTIZACION> updateTCESTATUSCOTIZACION(
        @PathVariable(value = "idEstatusCotizacion", required = false) final Long idEstatusCotizacion,
        @Valid @RequestBody TCESTATUSCOTIZACION tCESTATUSCOTIZACION
    ) throws URISyntaxException {
        log.debug("REST request to update TCESTATUSCOTIZACION : {}, {}", idEstatusCotizacion, tCESTATUSCOTIZACION);
        if (tCESTATUSCOTIZACION.getIdEstatusCotizacion() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idEstatusCotizacion, tCESTATUSCOTIZACION.getIdEstatusCotizacion())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCESTATUSCOTIZACIONRepository.existsById(idEstatusCotizacion)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCESTATUSCOTIZACION result = tCESTATUSCOTIZACIONRepository.save(tCESTATUSCOTIZACION);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    tCESTATUSCOTIZACION.getIdEstatusCotizacion().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tcestatuscotizacions/:idEstatusCotizacion} : Partial updates given fields of an existing tCESTATUSCOTIZACION, field will ignore if it is null
     *
     * @param idEstatusCotizacion the id of the tCESTATUSCOTIZACION to save.
     * @param tCESTATUSCOTIZACION the tCESTATUSCOTIZACION to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCESTATUSCOTIZACION,
     * or with status {@code 400 (Bad Request)} if the tCESTATUSCOTIZACION is not valid,
     * or with status {@code 404 (Not Found)} if the tCESTATUSCOTIZACION is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCESTATUSCOTIZACION couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcestatuscotizacions/{idEstatusCotizacion}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCESTATUSCOTIZACION> partialUpdateTCESTATUSCOTIZACION(
        @PathVariable(value = "idEstatusCotizacion", required = false) final Long idEstatusCotizacion,
        @NotNull @RequestBody TCESTATUSCOTIZACION tCESTATUSCOTIZACION
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCESTATUSCOTIZACION partially : {}, {}", idEstatusCotizacion, tCESTATUSCOTIZACION);
        if (tCESTATUSCOTIZACION.getIdEstatusCotizacion() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idEstatusCotizacion, tCESTATUSCOTIZACION.getIdEstatusCotizacion())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCESTATUSCOTIZACIONRepository.existsById(idEstatusCotizacion)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCESTATUSCOTIZACION> result = tCESTATUSCOTIZACIONRepository
            .findById(tCESTATUSCOTIZACION.getIdEstatusCotizacion())
            .map(
                existingTCESTATUSCOTIZACION -> {
                    if (tCESTATUSCOTIZACION.getOrden() != null) {
                        existingTCESTATUSCOTIZACION.setOrden(tCESTATUSCOTIZACION.getOrden());
                    }
                    if (tCESTATUSCOTIZACION.getEstatusCotizacion() != null) {
                        existingTCESTATUSCOTIZACION.setEstatusCotizacion(tCESTATUSCOTIZACION.getEstatusCotizacion());
                    }

                    return existingTCESTATUSCOTIZACION;
                }
            )
            .map(tCESTATUSCOTIZACIONRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCESTATUSCOTIZACION.getIdEstatusCotizacion().toString())
        );
    }

    /**
     * {@code GET  /tcestatuscotizacions} : get all the tCESTATUSCOTIZACIONS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCESTATUSCOTIZACIONS in body.
     */
    @GetMapping("/tcestatuscotizacions")
    public List<TCESTATUSCOTIZACION> getAllTCESTATUSCOTIZACIONS() {
        log.debug("REST request to get all TCESTATUSCOTIZACIONS");
        return tCESTATUSCOTIZACIONRepository.findAll();
    }

    /**
     * {@code GET  /tcestatuscotizacions/:id} : get the "id" tCESTATUSCOTIZACION.
     *
     * @param id the id of the tCESTATUSCOTIZACION to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCESTATUSCOTIZACION, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tcestatuscotizacions/{id}")
    public ResponseEntity<TCESTATUSCOTIZACION> getTCESTATUSCOTIZACION(@PathVariable Long id) {
        log.debug("REST request to get TCESTATUSCOTIZACION : {}", id);
        Optional<TCESTATUSCOTIZACION> tCESTATUSCOTIZACION = tCESTATUSCOTIZACIONRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tCESTATUSCOTIZACION);
    }

    /**
     * {@code DELETE  /tcestatuscotizacions/:id} : delete the "id" tCESTATUSCOTIZACION.
     *
     * @param id the id of the tCESTATUSCOTIZACION to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tcestatuscotizacions/{id}")
    public ResponseEntity<Void> deleteTCESTATUSCOTIZACION(@PathVariable Long id) {
        log.debug("REST request to delete TCESTATUSCOTIZACION : {}", id);
        tCESTATUSCOTIZACIONRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
