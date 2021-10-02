package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCOCUPACION;
import com.allianze.apicotizador.dto.TCOCUPACIONDTO;
import com.allianze.apicotizador.repository.TCOCUPACIONRepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCOCUPACION}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCOCUPACIONResource {

    private final Logger log = LoggerFactory.getLogger(TCOCUPACIONResource.class);

    private static final String ENTITY_NAME = "tCOCUPACION";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCOCUPACIONRepository tCOCUPACIONRepository;

    public TCOCUPACIONResource(TCOCUPACIONRepository tCOCUPACIONRepository) {
        this.tCOCUPACIONRepository = tCOCUPACIONRepository;
    }

    @PostMapping("/tcocupacions/insert")
    public ResponseEntity<TCOCUPACION> createTCOCUPACION(@Valid @RequestBody TCOCUPACION tCOCUPACION) throws URISyntaxException {
        log.debug("REST request to save TCOCUPACION : {}", tCOCUPACION);
        if (tCOCUPACION.getIdOcupacion() != null) {
            throw new BadRequestAlertException("A new tCOCUPACION cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCOCUPACION result = tCOCUPACIONRepository.save(tCOCUPACION);
        return ResponseEntity
            .created(new URI("/api/tcocupacions/" + result.getIdOcupacion()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdOcupacion().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcocupacions/:idOcupacion} : Updates an existing tCOCUPACION.
     *
     * @param idOcupacion the id of the tCOCUPACION to save.
     * @param tCOCUPACION the tCOCUPACION to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCOCUPACION,
     * or with status {@code 400 (Bad Request)} if the tCOCUPACION is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCOCUPACION couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcocupacions/{idOcupacion}")
    public ResponseEntity<TCOCUPACION> updateTCOCUPACION(
        @PathVariable(value = "idOcupacion", required = false) final Long idOcupacion,
        @Valid @RequestBody TCOCUPACION tCOCUPACION
    ) throws URISyntaxException {
        log.debug("REST request to update TCOCUPACION : {}, {}", idOcupacion, tCOCUPACION);
        if (tCOCUPACION.getIdOcupacion() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idOcupacion, tCOCUPACION.getIdOcupacion())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCOCUPACIONRepository.existsById(idOcupacion)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCOCUPACION result = tCOCUPACIONRepository.save(tCOCUPACION);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCOCUPACION.getIdOcupacion().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tcocupacions/:idOcupacion} : Partial updates given fields of an existing tCOCUPACION, field will ignore if it is null
     *
     * @param idOcupacion the id of the tCOCUPACION to save.
     * @param tCOCUPACION the tCOCUPACION to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCOCUPACION,
     * or with status {@code 400 (Bad Request)} if the tCOCUPACION is not valid,
     * or with status {@code 404 (Not Found)} if the tCOCUPACION is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCOCUPACION couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcocupacions/{idOcupacion}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCOCUPACION> partialUpdateTCOCUPACION(
        @PathVariable(value = "idOcupacion", required = false) final Long idOcupacion,
        @NotNull @RequestBody TCOCUPACION tCOCUPACION
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCOCUPACION partially : {}, {}", idOcupacion, tCOCUPACION);
        if (tCOCUPACION.getIdOcupacion() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idOcupacion, tCOCUPACION.getIdOcupacion())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCOCUPACIONRepository.existsById(idOcupacion)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCOCUPACION> result = tCOCUPACIONRepository
            .findById(tCOCUPACION.getIdOcupacion())
            .map(
                existingTCOCUPACION -> {
                    if (tCOCUPACION.getOcupacion() != null) {
                        existingTCOCUPACION.setOcupacion(tCOCUPACION.getOcupacion());
                    }
                    if (tCOCUPACION.getFactorGiroAnterior() != null) {
                        existingTCOCUPACION.setFactorGiroAnterior(tCOCUPACION.getFactorGiroAnterior());
                    }
                    if (tCOCUPACION.getFactorGiroActual() != null) {
                        existingTCOCUPACION.setFactorGiroActual(tCOCUPACION.getFactorGiroActual());
                    }

                    return existingTCOCUPACION;
                }
            )
            .map(tCOCUPACIONRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCOCUPACION.getIdOcupacion().toString())
        );
    }

    /**
     * {@code POST  /tcocupacions/getAll} : get all the tCOCUPACIONS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCOCUPACIONS in body.
     */
    @PostMapping("/tcocupacions/getAll")
    public List<TCOCUPACION> getAllTCOCUPACIONS( @RequestBody TCOCUPACIONDTO tcOcupacionDto) {
        log.debug("REST request to get all TCOCUPACIONS");
        return tCOCUPACIONRepository.findAll();
    }

    /**
     * {@code POST  /tcocupacions/getId} : get the "id" tCOCUPACION.
     *
     * @param id the id of the tCOCUPACION to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCOCUPACION, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tcocupacions/getId")
    public ResponseEntity<TCOCUPACION> getTCOCUPACION(@RequestBody TCOCUPACIONDTO tcOcupacionDto) {
        log.debug("REST request to get TCOCUPACION : {}", tcOcupacionDto.getId());
        Optional<TCOCUPACION> tCOCUPACION = tCOCUPACIONRepository.findById(tcOcupacionDto.getId());
        return ResponseUtil.wrapOrNotFound(tCOCUPACION);
    }

    /**
     * {@code POST  /tcocupacions/deleteId} : delete the "id" tCOCUPACION.
     *
     * @param id the id of the tCOCUPACION to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tcocupacions/deleteId")
    public ResponseEntity<Void> deleteTCOCUPACION(@RequestBody TCOCUPACIONDTO tcOcupacionDto) {
        log.debug("REST request to delete TCOCUPACION : {}", tcOcupacionDto.getId());
        tCOCUPACIONRepository.deleteById(tcOcupacionDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcOcupacionDto.getId().toString()))
            .build();
    }
}
