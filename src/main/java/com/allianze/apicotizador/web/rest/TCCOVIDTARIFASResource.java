package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCCOVIDTARIFAS;
import com.allianze.apicotizador.dto.TCCOVIDTARIFASDTO;
import com.allianze.apicotizador.repository.TCCOVIDTARIFASRepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCCOVIDTARIFAS}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCCOVIDTARIFASResource {

    private final Logger log = LoggerFactory.getLogger(TCCOVIDTARIFASResource.class);

    private static final String ENTITY_NAME = "tCCOVIDTARIFAS";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCCOVIDTARIFASRepository tCCOVIDTARIFASRepository;

    public TCCOVIDTARIFASResource(TCCOVIDTARIFASRepository tCCOVIDTARIFASRepository) {
        this.tCCOVIDTARIFASRepository = tCCOVIDTARIFASRepository;
    }

    /**
     * {@code POST  /tccovidtarifas} : Create a new tCCOVIDTARIFAS.
     *
     * @param tCCOVIDTARIFAS the tCCOVIDTARIFAS to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCCOVIDTARIFAS, or with status {@code 400 (Bad Request)} if the tCCOVIDTARIFAS has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tccovidtarifas")
    public ResponseEntity<TCCOVIDTARIFAS> createTCCOVIDTARIFAS(@Valid @RequestBody TCCOVIDTARIFAS tCCOVIDTARIFAS)
        throws URISyntaxException {
        log.debug("REST request to save TCCOVIDTARIFAS : {}", tCCOVIDTARIFAS);
        if (tCCOVIDTARIFAS.getIdCovidTarifas() != null) {
            throw new BadRequestAlertException("A new tCCOVIDTARIFAS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCCOVIDTARIFAS result = tCCOVIDTARIFASRepository.save(tCCOVIDTARIFAS);
        return ResponseEntity
            .created(new URI("/api/tccovidtarifas/" + result.getIdCovidTarifas()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdCovidTarifas().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tccovidtarifas/:idCovidTarifas} : Updates an existing tCCOVIDTARIFAS.
     *
     * @param idCovidTarifas the id of the tCCOVIDTARIFAS to save.
     * @param tCCOVIDTARIFAS the tCCOVIDTARIFAS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCOVIDTARIFAS,
     * or with status {@code 400 (Bad Request)} if the tCCOVIDTARIFAS is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCCOVIDTARIFAS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tccovidtarifas/{idCovidTarifas}")
    public ResponseEntity<TCCOVIDTARIFAS> updateTCCOVIDTARIFAS(
        @PathVariable(value = "idCovidTarifas", required = false) final Long idCovidTarifas,
        @Valid @RequestBody TCCOVIDTARIFAS tCCOVIDTARIFAS
    ) throws URISyntaxException {
        log.debug("REST request to update TCCOVIDTARIFAS : {}, {}", idCovidTarifas, tCCOVIDTARIFAS);
        if (tCCOVIDTARIFAS.getIdCovidTarifas() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCovidTarifas, tCCOVIDTARIFAS.getIdCovidTarifas())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCOVIDTARIFASRepository.existsById(idCovidTarifas)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCCOVIDTARIFAS result = tCCOVIDTARIFASRepository.save(tCCOVIDTARIFAS);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCOVIDTARIFAS.getIdCovidTarifas().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tccovidtarifas/:idCovidTarifas} : Partial updates given fields of an existing tCCOVIDTARIFAS, field will ignore if it is null
     *
     * @param idCovidTarifas the id of the tCCOVIDTARIFAS to save.
     * @param tCCOVIDTARIFAS the tCCOVIDTARIFAS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCOVIDTARIFAS,
     * or with status {@code 400 (Bad Request)} if the tCCOVIDTARIFAS is not valid,
     * or with status {@code 404 (Not Found)} if the tCCOVIDTARIFAS is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCCOVIDTARIFAS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tccovidtarifas/{idCovidTarifas}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCCOVIDTARIFAS> partialUpdateTCCOVIDTARIFAS(
        @PathVariable(value = "idCovidTarifas", required = false) final Long idCovidTarifas,
        @NotNull @RequestBody TCCOVIDTARIFAS tCCOVIDTARIFAS
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCCOVIDTARIFAS partially : {}, {}", idCovidTarifas, tCCOVIDTARIFAS);
        if (tCCOVIDTARIFAS.getIdCovidTarifas() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCovidTarifas, tCCOVIDTARIFAS.getIdCovidTarifas())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCOVIDTARIFASRepository.existsById(idCovidTarifas)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCCOVIDTARIFAS> result = tCCOVIDTARIFASRepository
            .findById(tCCOVIDTARIFAS.getIdCovidTarifas())
            .map(
                existingTCCOVIDTARIFAS -> {
                    if (tCCOVIDTARIFAS.getEdad() != null) {
                        existingTCCOVIDTARIFAS.setEdad(tCCOVIDTARIFAS.getEdad());
                    }
                    if (tCCOVIDTARIFAS.getQxcnsfg() != null) {
                        existingTCCOVIDTARIFAS.setQxcnsfg(tCCOVIDTARIFAS.getQxcnsfg());
                    }
                    if (tCCOVIDTARIFAS.getTitular() != null) {
                        existingTCCOVIDTARIFAS.setTitular(tCCOVIDTARIFAS.getTitular());
                    }
                    if (tCCOVIDTARIFAS.getConyuge() != null) {
                        existingTCCOVIDTARIFAS.setConyuge(tCCOVIDTARIFAS.getConyuge());
                    }
                    if (tCCOVIDTARIFAS.getHijoMayor() != null) {
                        existingTCCOVIDTARIFAS.setHijoMayor(tCCOVIDTARIFAS.getHijoMayor());
                    }
                    if (tCCOVIDTARIFAS.getHijoMenor() != null) {
                        existingTCCOVIDTARIFAS.setHijoMenor(tCCOVIDTARIFAS.getHijoMenor());
                    }
                    if (tCCOVIDTARIFAS.getQxTitular() != null) {
                        existingTCCOVIDTARIFAS.setQxTitular(tCCOVIDTARIFAS.getQxTitular());
                    }
                    if (tCCOVIDTARIFAS.getQxConyuge() != null) {
                        existingTCCOVIDTARIFAS.setQxConyuge(tCCOVIDTARIFAS.getQxConyuge());
                    }
                    if (tCCOVIDTARIFAS.getQxHijoMayor() != null) {
                        existingTCCOVIDTARIFAS.setQxHijoMayor(tCCOVIDTARIFAS.getQxHijoMayor());
                    }
                    if (tCCOVIDTARIFAS.getQxHijoMenor() != null) {
                        existingTCCOVIDTARIFAS.setQxHijoMenor(tCCOVIDTARIFAS.getQxHijoMenor());
                    }
                    if (tCCOVIDTARIFAS.getQxTitularRecargada() != null) {
                        existingTCCOVIDTARIFAS.setQxTitularRecargada(tCCOVIDTARIFAS.getQxTitularRecargada());
                    }
                    if (tCCOVIDTARIFAS.getQxConyugeRecargada() != null) {
                        existingTCCOVIDTARIFAS.setQxConyugeRecargada(tCCOVIDTARIFAS.getQxConyugeRecargada());
                    }
                    if (tCCOVIDTARIFAS.getQxHijoMayorRecargada() != null) {
                        existingTCCOVIDTARIFAS.setQxHijoMayorRecargada(tCCOVIDTARIFAS.getQxHijoMayorRecargada());
                    }
                    if (tCCOVIDTARIFAS.getQxHijoMenorRecargada() != null) {
                        existingTCCOVIDTARIFAS.setQxHijoMenorRecargada(tCCOVIDTARIFAS.getQxHijoMenorRecargada());
                    }
                    if (tCCOVIDTARIFAS.getValorGff() != null) {
                        existingTCCOVIDTARIFAS.setValorGff(tCCOVIDTARIFAS.getValorGff());
                    }
                    if (tCCOVIDTARIFAS.getValorGffCovid() != null) {
                        existingTCCOVIDTARIFAS.setValorGffCovid(tCCOVIDTARIFAS.getValorGffCovid());
                    }

                    return existingTCCOVIDTARIFAS;
                }
            )
            .map(tCCOVIDTARIFASRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCOVIDTARIFAS.getIdCovidTarifas().toString())
        );
    }

    /**
     * {@code POST  /tccovidtarifas/getAll} : get all the tCCOVIDTARIFAS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCCOVIDTARIFAS in body.
     */
    @PostMapping("/tccovidtarifas/getAll")
    public List<TCCOVIDTARIFAS> getAllTCCOVIDTARIFAS(@RequestBody TCCOVIDTARIFASDTO tccovidTarifasDto) {
        log.debug("REST request to get all TCCOVIDTARIFAS");
        return tCCOVIDTARIFASRepository.findAll();
    }

    /**
     * {@code Post  /tccovidtarifas/getId} : get the "id" tCCOVIDTARIFAS.
     *
     * @param id the id of the tCCOVIDTARIFAS to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCCOVIDTARIFAS, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tccovidtarifas/getId")
    public ResponseEntity<TCCOVIDTARIFAS> getTCCOVIDTARIFAS(@RequestBody TCCOVIDTARIFASDTO tccovidTarifasDto) {
        log.debug("REST request to get TCCOVIDTARIFAS : {}", tccovidTarifasDto.getId());
        Optional<TCCOVIDTARIFAS> tCCOVIDTARIFAS = tCCOVIDTARIFASRepository.findById(tccovidTarifasDto.getId());
        return ResponseUtil.wrapOrNotFound(tCCOVIDTARIFAS);
    }

    /**
     * {@code POST  /tccovidtarifas/deleteId} : delete the "id" tCCOVIDTARIFAS.
     *
     * @param id the id of the tCCOVIDTARIFAS to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tccovidtarifas/deleteId")
    public ResponseEntity<Void> deleteTCCOVIDTARIFAS(@RequestBody TCCOVIDTARIFASDTO tccovidTarifasDto) {
        log.debug("REST request to delete TCCOVIDTARIFAS : {}", tccovidTarifasDto.getId());
        tCCOVIDTARIFASRepository.deleteById( tccovidTarifasDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME,  tccovidTarifasDto.getId().toString()))
            .build();
    }
}
