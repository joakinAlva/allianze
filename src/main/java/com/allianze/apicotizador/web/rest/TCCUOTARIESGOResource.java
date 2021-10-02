package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCCUOTARIESGO;
import com.allianze.apicotizador.dto.TCCUOTARIESGODTO;
import com.allianze.apicotizador.repository.TCCUOTARIESGORepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCCUOTARIESGO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCCUOTARIESGOResource {

    private final Logger log = LoggerFactory.getLogger(TCCUOTARIESGOResource.class);

    private static final String ENTITY_NAME = "tCCUOTARIESGO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCCUOTARIESGORepository tCCUOTARIESGORepository;

    public TCCUOTARIESGOResource(TCCUOTARIESGORepository tCCUOTARIESGORepository) {
        this.tCCUOTARIESGORepository = tCCUOTARIESGORepository;
    }

    /**
     * {@code POST  /tccuotariesgos} : Create a new tCCUOTARIESGO.
     *
     * @param tCCUOTARIESGO the tCCUOTARIESGO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCCUOTARIESGO, or with status {@code 400 (Bad Request)} if the tCCUOTARIESGO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tccuotariesgos")
    public ResponseEntity<TCCUOTARIESGO> createTCCUOTARIESGO(@Valid @RequestBody TCCUOTARIESGO tCCUOTARIESGO) throws URISyntaxException {
        log.debug("REST request to save TCCUOTARIESGO : {}", tCCUOTARIESGO);
        if (tCCUOTARIESGO.getIdCuotaRiesgo() != null) {
            throw new BadRequestAlertException("A new tCCUOTARIESGO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCCUOTARIESGO result = tCCUOTARIESGORepository.save(tCCUOTARIESGO);
        return ResponseEntity
            .created(new URI("/api/tccuotariesgos/" + result.getIdCuotaRiesgo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdCuotaRiesgo().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tccuotariesgos/:idCuotaRiesgo} : Updates an existing tCCUOTARIESGO.
     *
     * @param idCuotaRiesgo the id of the tCCUOTARIESGO to save.
     * @param tCCUOTARIESGO the tCCUOTARIESGO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCUOTARIESGO,
     * or with status {@code 400 (Bad Request)} if the tCCUOTARIESGO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCCUOTARIESGO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tccuotariesgos/{idCuotaRiesgo}")
    public ResponseEntity<TCCUOTARIESGO> updateTCCUOTARIESGO(
        @PathVariable(value = "idCuotaRiesgo", required = false) final Long idCuotaRiesgo,
        @Valid @RequestBody TCCUOTARIESGO tCCUOTARIESGO
    ) throws URISyntaxException {
        log.debug("REST request to update TCCUOTARIESGO : {}, {}", idCuotaRiesgo, tCCUOTARIESGO);
        if (tCCUOTARIESGO.getIdCuotaRiesgo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCuotaRiesgo, tCCUOTARIESGO.getIdCuotaRiesgo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCUOTARIESGORepository.existsById(idCuotaRiesgo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCCUOTARIESGO result = tCCUOTARIESGORepository.save(tCCUOTARIESGO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCUOTARIESGO.getIdCuotaRiesgo().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tccuotariesgos/:idCuotaRiesgo} : Partial updates given fields of an existing tCCUOTARIESGO, field will ignore if it is null
     *
     * @param idCuotaRiesgo the id of the tCCUOTARIESGO to save.
     * @param tCCUOTARIESGO the tCCUOTARIESGO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCUOTARIESGO,
     * or with status {@code 400 (Bad Request)} if the tCCUOTARIESGO is not valid,
     * or with status {@code 404 (Not Found)} if the tCCUOTARIESGO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCCUOTARIESGO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tccuotariesgos/{idCuotaRiesgo}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCCUOTARIESGO> partialUpdateTCCUOTARIESGO(
        @PathVariable(value = "idCuotaRiesgo", required = false) final Long idCuotaRiesgo,
        @NotNull @RequestBody TCCUOTARIESGO tCCUOTARIESGO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCCUOTARIESGO partially : {}, {}", idCuotaRiesgo, tCCUOTARIESGO);
        if (tCCUOTARIESGO.getIdCuotaRiesgo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCuotaRiesgo, tCCUOTARIESGO.getIdCuotaRiesgo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCUOTARIESGORepository.existsById(idCuotaRiesgo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCCUOTARIESGO> result = tCCUOTARIESGORepository
            .findById(tCCUOTARIESGO.getIdCuotaRiesgo())
            .map(
                existingTCCUOTARIESGO -> {
                    if (tCCUOTARIESGO.getEdad() != null) {
                        existingTCCUOTARIESGO.setEdad(tCCUOTARIESGO.getEdad());
                    }
                    if (tCCUOTARIESGO.getValorBa() != null) {
                        existingTCCUOTARIESGO.setValorBa(tCCUOTARIESGO.getValorBa());
                    }
                    if (tCCUOTARIESGO.getValorBaCovid() != null) {
                        existingTCCUOTARIESGO.setValorBaCovid(tCCUOTARIESGO.getValorBaCovid());
                    }
                    if (tCCUOTARIESGO.getValorBipTres() != null) {
                        existingTCCUOTARIESGO.setValorBipTres(tCCUOTARIESGO.getValorBipTres());
                    }
                    if (tCCUOTARIESGO.getValorBipSeis() != null) {
                        existingTCCUOTARIESGO.setValorBipSeis(tCCUOTARIESGO.getValorBipSeis());
                    }
                    if (tCCUOTARIESGO.getValorBit() != null) {
                        existingTCCUOTARIESGO.setValorBit(tCCUOTARIESGO.getValorBit());
                    }
                    if (tCCUOTARIESGO.getValorMa() != null) {
                        existingTCCUOTARIESGO.setValorMa(tCCUOTARIESGO.getValorMa());
                    }
                    if (tCCUOTARIESGO.getValorDi() != null) {
                        existingTCCUOTARIESGO.setValorDi(tCCUOTARIESGO.getValorDi());
                    }
                    if (tCCUOTARIESGO.getValorTi() != null) {
                        existingTCCUOTARIESGO.setValorTi(tCCUOTARIESGO.getValorTi());
                    }
                    if (tCCUOTARIESGO.getValorGff() != null) {
                        existingTCCUOTARIESGO.setValorGff(tCCUOTARIESGO.getValorGff());
                    }
                    if (tCCUOTARIESGO.getValorGffCovid() != null) {
                        existingTCCUOTARIESGO.setValorGffCovid(tCCUOTARIESGO.getValorGffCovid());
                    }
                    if (tCCUOTARIESGO.getValorCa() != null) {
                        existingTCCUOTARIESGO.setValorCa(tCCUOTARIESGO.getValorCa());
                    }
                    if (tCCUOTARIESGO.getValorGe() != null) {
                        existingTCCUOTARIESGO.setValorGe(tCCUOTARIESGO.getValorGe());
                    }
                    if (tCCUOTARIESGO.getValorIqUno() != null) {
                        existingTCCUOTARIESGO.setValorIqUno(tCCUOTARIESGO.getValorIqUno());
                    }
                    if (tCCUOTARIESGO.getValorIqDos() != null) {
                        existingTCCUOTARIESGO.setValorIqDos(tCCUOTARIESGO.getValorIqDos());
                    }

                    return existingTCCUOTARIESGO;
                }
            )
            .map(tCCUOTARIESGORepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCUOTARIESGO.getIdCuotaRiesgo().toString())
        );
    }

    /**
     * {@code POST  /tccuotariesgos/getAll} : get all the tCCUOTARIESGOS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCCUOTARIESGOS in body.
     */
    @PostMapping("/tccuotariesgos/getAll")
    public List<TCCUOTARIESGO> getAllTCCUOTARIESGOS(@RequestBody TCCUOTARIESGODTO tccuotaRiesgoDto) {
        log.debug("REST request to get all TCCUOTARIESGOS");
        return tCCUOTARIESGORepository.findAll();
    }

    /**
     * {@code POST  /tccuotariesgos/getId} : get the "id" tCCUOTARIESGO.
     *
     * @param id the id of the tCCUOTARIESGO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCCUOTARIESGO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tccuotariesgos/getId")
    public ResponseEntity<TCCUOTARIESGO> getTCCUOTARIESGO(@RequestBody TCCUOTARIESGODTO tccuotaRiesgoDto) {
        log.debug("REST request to get TCCUOTARIESGO : {}", tccuotaRiesgoDto.getId());
        Optional<TCCUOTARIESGO> tCCUOTARIESGO = tCCUOTARIESGORepository.findById(tccuotaRiesgoDto.getId());
        return ResponseUtil.wrapOrNotFound(tCCUOTARIESGO);
    }

    /**
     * {@code POST  /tccuotariesgos/deleteId} : delete the "id" tCCUOTARIESGO.
     *
     * @param id the id of the tCCUOTARIESGO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tccuotariesgos/deleteId")
    public ResponseEntity<Void> deleteTCCUOTARIESGO(@RequestBody TCCUOTARIESGODTO tccuotaRiesgoDto) {
        log.debug("REST request to delete TCCUOTARIESGO : {}", tccuotaRiesgoDto.getId());
        tCCUOTARIESGORepository.deleteById(tccuotaRiesgoDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tccuotaRiesgoDto.getId().toString()))
            .build();
    }
}
