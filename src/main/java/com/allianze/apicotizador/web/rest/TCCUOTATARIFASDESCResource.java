package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCCUOTATARIFASDESC;
import com.allianze.apicotizador.repository.TCCUOTATARIFASDESCRepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCCUOTATARIFASDESC}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCCUOTATARIFASDESCResource {

    private final Logger log = LoggerFactory.getLogger(TCCUOTATARIFASDESCResource.class);

    private static final String ENTITY_NAME = "tCCUOTATARIFASDESC";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCCUOTATARIFASDESCRepository tCCUOTATARIFASDESCRepository;

    public TCCUOTATARIFASDESCResource(TCCUOTATARIFASDESCRepository tCCUOTATARIFASDESCRepository) {
        this.tCCUOTATARIFASDESCRepository = tCCUOTATARIFASDESCRepository;
    }

    /**
     * {@code POST  /tccuotatarifasdescs} : Create a new tCCUOTATARIFASDESC.
     *
     * @param tCCUOTATARIFASDESC the tCCUOTATARIFASDESC to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCCUOTATARIFASDESC, or with status {@code 400 (Bad Request)} if the tCCUOTATARIFASDESC has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tccuotatarifasdescs")
    public ResponseEntity<TCCUOTATARIFASDESC> createTCCUOTATARIFASDESC(@Valid @RequestBody TCCUOTATARIFASDESC tCCUOTATARIFASDESC)
        throws URISyntaxException {
        log.debug("REST request to save TCCUOTATARIFASDESC : {}", tCCUOTATARIFASDESC);
        if (tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc() != null) {
            throw new BadRequestAlertException("A new tCCUOTATARIFASDESC cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCCUOTATARIFASDESC result = tCCUOTATARIFASDESCRepository.save(tCCUOTATARIFASDESC);
        return ResponseEntity
            .created(new URI("/api/tccuotatarifasdescs/" + result.getIdCuotaTarifaSdesc()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdCuotaTarifaSdesc().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tccuotatarifasdescs/:idCuotaTarifaSdesc} : Updates an existing tCCUOTATARIFASDESC.
     *
     * @param idCuotaTarifaSdesc the id of the tCCUOTATARIFASDESC to save.
     * @param tCCUOTATARIFASDESC the tCCUOTATARIFASDESC to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCUOTATARIFASDESC,
     * or with status {@code 400 (Bad Request)} if the tCCUOTATARIFASDESC is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCCUOTATARIFASDESC couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tccuotatarifasdescs/{idCuotaTarifaSdesc}")
    public ResponseEntity<TCCUOTATARIFASDESC> updateTCCUOTATARIFASDESC(
        @PathVariable(value = "idCuotaTarifaSdesc", required = false) final Long idCuotaTarifaSdesc,
        @Valid @RequestBody TCCUOTATARIFASDESC tCCUOTATARIFASDESC
    ) throws URISyntaxException {
        log.debug("REST request to update TCCUOTATARIFASDESC : {}, {}", idCuotaTarifaSdesc, tCCUOTATARIFASDESC);
        if (tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCuotaTarifaSdesc, tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCUOTATARIFASDESCRepository.existsById(idCuotaTarifaSdesc)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCCUOTATARIFASDESC result = tCCUOTATARIFASDESCRepository.save(tCCUOTATARIFASDESC);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tccuotatarifasdescs/:idCuotaTarifaSdesc} : Partial updates given fields of an existing tCCUOTATARIFASDESC, field will ignore if it is null
     *
     * @param idCuotaTarifaSdesc the id of the tCCUOTATARIFASDESC to save.
     * @param tCCUOTATARIFASDESC the tCCUOTATARIFASDESC to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCUOTATARIFASDESC,
     * or with status {@code 400 (Bad Request)} if the tCCUOTATARIFASDESC is not valid,
     * or with status {@code 404 (Not Found)} if the tCCUOTATARIFASDESC is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCCUOTATARIFASDESC couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tccuotatarifasdescs/{idCuotaTarifaSdesc}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCCUOTATARIFASDESC> partialUpdateTCCUOTATARIFASDESC(
        @PathVariable(value = "idCuotaTarifaSdesc", required = false) final Long idCuotaTarifaSdesc,
        @NotNull @RequestBody TCCUOTATARIFASDESC tCCUOTATARIFASDESC
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCCUOTATARIFASDESC partially : {}, {}", idCuotaTarifaSdesc, tCCUOTATARIFASDESC);
        if (tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCuotaTarifaSdesc, tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCUOTATARIFASDESCRepository.existsById(idCuotaTarifaSdesc)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCCUOTATARIFASDESC> result = tCCUOTATARIFASDESCRepository
            .findById(tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc())
            .map(
                existingTCCUOTATARIFASDESC -> {
                    if (tCCUOTATARIFASDESC.getEdad() != null) {
                        existingTCCUOTATARIFASDESC.setEdad(tCCUOTATARIFASDESC.getEdad());
                    }
                    if (tCCUOTATARIFASDESC.getValorBa() != null) {
                        existingTCCUOTATARIFASDESC.setValorBa(tCCUOTATARIFASDESC.getValorBa());
                    }
                    if (tCCUOTATARIFASDESC.getValorBaCovid() != null) {
                        existingTCCUOTATARIFASDESC.setValorBaCovid(tCCUOTATARIFASDESC.getValorBaCovid());
                    }
                    if (tCCUOTATARIFASDESC.getValorBipTres() != null) {
                        existingTCCUOTATARIFASDESC.setValorBipTres(tCCUOTATARIFASDESC.getValorBipTres());
                    }
                    if (tCCUOTATARIFASDESC.getValorBipSeis() != null) {
                        existingTCCUOTATARIFASDESC.setValorBipSeis(tCCUOTATARIFASDESC.getValorBipSeis());
                    }
                    if (tCCUOTATARIFASDESC.getValorBit() != null) {
                        existingTCCUOTATARIFASDESC.setValorBit(tCCUOTATARIFASDESC.getValorBit());
                    }
                    if (tCCUOTATARIFASDESC.getValorMa() != null) {
                        existingTCCUOTATARIFASDESC.setValorMa(tCCUOTATARIFASDESC.getValorMa());
                    }
                    if (tCCUOTATARIFASDESC.getValorDi() != null) {
                        existingTCCUOTATARIFASDESC.setValorDi(tCCUOTATARIFASDESC.getValorDi());
                    }
                    if (tCCUOTATARIFASDESC.getValorTi() != null) {
                        existingTCCUOTATARIFASDESC.setValorTi(tCCUOTATARIFASDESC.getValorTi());
                    }
                    if (tCCUOTATARIFASDESC.getValorGff() != null) {
                        existingTCCUOTATARIFASDESC.setValorGff(tCCUOTATARIFASDESC.getValorGff());
                    }
                    if (tCCUOTATARIFASDESC.getValorGffCovid() != null) {
                        existingTCCUOTATARIFASDESC.setValorGffCovid(tCCUOTATARIFASDESC.getValorGffCovid());
                    }
                    if (tCCUOTATARIFASDESC.getValorCa() != null) {
                        existingTCCUOTATARIFASDESC.setValorCa(tCCUOTATARIFASDESC.getValorCa());
                    }
                    if (tCCUOTATARIFASDESC.getValorGe() != null) {
                        existingTCCUOTATARIFASDESC.setValorGe(tCCUOTATARIFASDESC.getValorGe());
                    }
                    if (tCCUOTATARIFASDESC.getValorIqUno() != null) {
                        existingTCCUOTATARIFASDESC.setValorIqUno(tCCUOTATARIFASDESC.getValorIqUno());
                    }
                    if (tCCUOTATARIFASDESC.getValorIqDos() != null) {
                        existingTCCUOTATARIFASDESC.setValorIqDos(tCCUOTATARIFASDESC.getValorIqDos());
                    }

                    return existingTCCUOTATARIFASDESC;
                }
            )
            .map(tCCUOTATARIFASDESCRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc().toString())
        );
    }

    /**
     * {@code GET  /tccuotatarifasdescs} : get all the tCCUOTATARIFASDESCS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCCUOTATARIFASDESCS in body.
     */
    @GetMapping("/tccuotatarifasdescs")
    public List<TCCUOTATARIFASDESC> getAllTCCUOTATARIFASDESCS() {
        log.debug("REST request to get all TCCUOTATARIFASDESCS");
        return tCCUOTATARIFASDESCRepository.findAll();
    }

    /**
     * {@code GET  /tccuotatarifasdescs/:id} : get the "id" tCCUOTATARIFASDESC.
     *
     * @param id the id of the tCCUOTATARIFASDESC to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCCUOTATARIFASDESC, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tccuotatarifasdescs/{id}")
    public ResponseEntity<TCCUOTATARIFASDESC> getTCCUOTATARIFASDESC(@PathVariable Long id) {
        log.debug("REST request to get TCCUOTATARIFASDESC : {}", id);
        Optional<TCCUOTATARIFASDESC> tCCUOTATARIFASDESC = tCCUOTATARIFASDESCRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tCCUOTATARIFASDESC);
    }

    /**
     * {@code DELETE  /tccuotatarifasdescs/:id} : delete the "id" tCCUOTATARIFASDESC.
     *
     * @param id the id of the tCCUOTATARIFASDESC to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tccuotatarifasdescs/{id}")
    public ResponseEntity<Void> deleteTCCUOTATARIFASDESC(@PathVariable Long id) {
        log.debug("REST request to delete TCCUOTATARIFASDESC : {}", id);
        tCCUOTATARIFASDESCRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
