package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCCOVID;
import com.allianze.apicotizador.dto.TCCOVIDDTO;
import com.allianze.apicotizador.repository.TCCOVIDRepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCCOVID}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCCOVIDResource {

    private final Logger log = LoggerFactory.getLogger(TCCOVIDResource.class);

    private static final String ENTITY_NAME = "tCCOVID";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCCOVIDRepository tCCOVIDRepository;

    public TCCOVIDResource(TCCOVIDRepository tCCOVIDRepository) {
        this.tCCOVIDRepository = tCCOVIDRepository;
    }

    /**
     * {@code POST  /tccovids} : Create a new tCCOVID.
     *
     * @param tCCOVID the tCCOVID to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCCOVID, or with status {@code 400 (Bad Request)} if the tCCOVID has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tccovids")
    public ResponseEntity<TCCOVID> createTCCOVID(@Valid @RequestBody TCCOVID tCCOVID) throws URISyntaxException {
        log.debug("REST request to save TCCOVID : {}", tCCOVID);
        if (tCCOVID.getIdCovid() != null) {
            throw new BadRequestAlertException("A new tCCOVID cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCCOVID result = tCCOVIDRepository.save(tCCOVID);
        return ResponseEntity
            .created(new URI("/api/tccovids/" + result.getIdCovid()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdCovid().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tccovids/:idCovid} : Updates an existing tCCOVID.
     *
     * @param idCovid the id of the tCCOVID to save.
     * @param tCCOVID the tCCOVID to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCOVID,
     * or with status {@code 400 (Bad Request)} if the tCCOVID is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCCOVID couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tccovids/{idCovid}")
    public ResponseEntity<TCCOVID> updateTCCOVID(
        @PathVariable(value = "idCovid", required = false) final Long idCovid,
        @Valid @RequestBody TCCOVID tCCOVID
    ) throws URISyntaxException {
        log.debug("REST request to update TCCOVID : {}, {}", idCovid, tCCOVID);
        if (tCCOVID.getIdCovid() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCovid, tCCOVID.getIdCovid())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCOVIDRepository.existsById(idCovid)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCCOVID result = tCCOVIDRepository.save(tCCOVID);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCOVID.getIdCovid().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tccovids/:idCovid} : Partial updates given fields of an existing tCCOVID, field will ignore if it is null
     *
     * @param idCovid the id of the tCCOVID to save.
     * @param tCCOVID the tCCOVID to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCOVID,
     * or with status {@code 400 (Bad Request)} if the tCCOVID is not valid,
     * or with status {@code 404 (Not Found)} if the tCCOVID is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCCOVID couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tccovids/{idCovid}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCCOVID> partialUpdateTCCOVID(
        @PathVariable(value = "idCovid", required = false) final Long idCovid,
        @NotNull @RequestBody TCCOVID tCCOVID
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCCOVID partially : {}, {}", idCovid, tCCOVID);
        if (tCCOVID.getIdCovid() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCovid, tCCOVID.getIdCovid())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCOVIDRepository.existsById(idCovid)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCCOVID> result = tCCOVIDRepository
            .findById(tCCOVID.getIdCovid())
            .map(
                existingTCCOVID -> {
                    if (tCCOVID.getEdad() != null) {
                        existingTCCOVID.setEdad(tCCOVID.getEdad());
                    }
                    if (tCCOVID.getBasica() != null) {
                        existingTCCOVID.setBasica(tCCOVID.getBasica());
                    }
                    if (tCCOVID.getRecargoEdad() != null) {
                        existingTCCOVID.setRecargoEdad(tCCOVID.getRecargoEdad());
                    }
                    if (tCCOVID.getRecargoGiro() != null) {
                        existingTCCOVID.setRecargoGiro(tCCOVID.getRecargoGiro());
                    }
                    if (tCCOVID.getRecargoTotal() != null) {
                        existingTCCOVID.setRecargoTotal(tCCOVID.getRecargoTotal());
                    }
                    if (tCCOVID.getBasicaRecargada() != null) {
                        existingTCCOVID.setBasicaRecargada(tCCOVID.getBasicaRecargada());
                    }

                    return existingTCCOVID;
                }
            )
            .map(tCCOVIDRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCOVID.getIdCovid().toString())
        );
    }

    /**
     * {@code POST  /tccovids/getAll} : get all the tCCOVIDS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCCOVIDS in body.
     */
    @PostMapping("/tccovids/getAll")
    public List<TCCOVID> getAllTCCOVIDS(@RequestBody TCCOVIDDTO tccovidDto) {
        log.debug("REST request to get all TCCOVIDS");
        return tCCOVIDRepository.findAll();
    }

    /**
     * {@code POST  /tccovids/getId} : get the "id" tCCOVID.
     *
     * @param id the id of the tCCOVID to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCCOVID, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tccovids/getId")
    public ResponseEntity<TCCOVID> getTCCOVID(@RequestBody TCCOVIDDTO tccovidDto) {
        log.debug("REST request to get TCCOVID : {}", tccovidDto.getId());
        Optional<TCCOVID> tCCOVID = tCCOVIDRepository.findById(tccovidDto.getId());
        return ResponseUtil.wrapOrNotFound(tCCOVID);
    }

    /**
     * {@code POST  /tccovids/deleteId} : delete the "id" tCCOVID.
     *
     * @param id the id of the tCCOVID to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tccovids/deleteId")
    public ResponseEntity<Void> deleteTCCOVID(@RequestBody TCCOVIDDTO tccovidDto) {
        log.debug("REST request to delete TCCOVID : {}", tccovidDto.getId());
        tCCOVIDRepository.deleteById(tccovidDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tccovidDto.getId().toString()))
            .build();
    }
}
