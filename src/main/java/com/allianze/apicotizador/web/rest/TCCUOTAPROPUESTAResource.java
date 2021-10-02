package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCCUOTAPROPUESTA;
import com.allianze.apicotizador.repository.TCCUOTAPROPUESTARepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCCUOTAPROPUESTA}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCCUOTAPROPUESTAResource {

    private final Logger log = LoggerFactory.getLogger(TCCUOTAPROPUESTAResource.class);

    private static final String ENTITY_NAME = "tCCUOTAPROPUESTA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCCUOTAPROPUESTARepository tCCUOTAPROPUESTARepository;

    public TCCUOTAPROPUESTAResource(TCCUOTAPROPUESTARepository tCCUOTAPROPUESTARepository) {
        this.tCCUOTAPROPUESTARepository = tCCUOTAPROPUESTARepository;
    }

    /**
     * {@code POST  /tccuotapropuestas} : Create a new tCCUOTAPROPUESTA.
     *
     * @param tCCUOTAPROPUESTA the tCCUOTAPROPUESTA to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCCUOTAPROPUESTA, or with status {@code 400 (Bad Request)} if the tCCUOTAPROPUESTA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tccuotapropuestas")
    public ResponseEntity<TCCUOTAPROPUESTA> createTCCUOTAPROPUESTA(@Valid @RequestBody TCCUOTAPROPUESTA tCCUOTAPROPUESTA)
        throws URISyntaxException {
        log.debug("REST request to save TCCUOTAPROPUESTA : {}", tCCUOTAPROPUESTA);
        if (tCCUOTAPROPUESTA.getIdCuotaPropuesta() != null) {
            throw new BadRequestAlertException("A new tCCUOTAPROPUESTA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCCUOTAPROPUESTA result = tCCUOTAPROPUESTARepository.save(tCCUOTAPROPUESTA);
        return ResponseEntity
            .created(new URI("/api/tccuotapropuestas/" + result.getIdCuotaPropuesta()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdCuotaPropuesta().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tccuotapropuestas/:idCuotaPropuesta} : Updates an existing tCCUOTAPROPUESTA.
     *
     * @param idCuotaPropuesta the id of the tCCUOTAPROPUESTA to save.
     * @param tCCUOTAPROPUESTA the tCCUOTAPROPUESTA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCUOTAPROPUESTA,
     * or with status {@code 400 (Bad Request)} if the tCCUOTAPROPUESTA is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCCUOTAPROPUESTA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tccuotapropuestas/{idCuotaPropuesta}")
    public ResponseEntity<TCCUOTAPROPUESTA> updateTCCUOTAPROPUESTA(
        @PathVariable(value = "idCuotaPropuesta", required = false) final Long idCuotaPropuesta,
        @Valid @RequestBody TCCUOTAPROPUESTA tCCUOTAPROPUESTA
    ) throws URISyntaxException {
        log.debug("REST request to update TCCUOTAPROPUESTA : {}, {}", idCuotaPropuesta, tCCUOTAPROPUESTA);
        if (tCCUOTAPROPUESTA.getIdCuotaPropuesta() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCuotaPropuesta, tCCUOTAPROPUESTA.getIdCuotaPropuesta())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCUOTAPROPUESTARepository.existsById(idCuotaPropuesta)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCCUOTAPROPUESTA result = tCCUOTAPROPUESTARepository.save(tCCUOTAPROPUESTA);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCUOTAPROPUESTA.getIdCuotaPropuesta().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tccuotapropuestas/:idCuotaPropuesta} : Partial updates given fields of an existing tCCUOTAPROPUESTA, field will ignore if it is null
     *
     * @param idCuotaPropuesta the id of the tCCUOTAPROPUESTA to save.
     * @param tCCUOTAPROPUESTA the tCCUOTAPROPUESTA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCUOTAPROPUESTA,
     * or with status {@code 400 (Bad Request)} if the tCCUOTAPROPUESTA is not valid,
     * or with status {@code 404 (Not Found)} if the tCCUOTAPROPUESTA is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCCUOTAPROPUESTA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tccuotapropuestas/{idCuotaPropuesta}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCCUOTAPROPUESTA> partialUpdateTCCUOTAPROPUESTA(
        @PathVariable(value = "idCuotaPropuesta", required = false) final Long idCuotaPropuesta,
        @NotNull @RequestBody TCCUOTAPROPUESTA tCCUOTAPROPUESTA
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCCUOTAPROPUESTA partially : {}, {}", idCuotaPropuesta, tCCUOTAPROPUESTA);
        if (tCCUOTAPROPUESTA.getIdCuotaPropuesta() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCuotaPropuesta, tCCUOTAPROPUESTA.getIdCuotaPropuesta())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCUOTAPROPUESTARepository.existsById(idCuotaPropuesta)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCCUOTAPROPUESTA> result = tCCUOTAPROPUESTARepository
            .findById(tCCUOTAPROPUESTA.getIdCuotaPropuesta())
            .map(
                existingTCCUOTAPROPUESTA -> {
                    if (tCCUOTAPROPUESTA.getEdad() != null) {
                        existingTCCUOTAPROPUESTA.setEdad(tCCUOTAPROPUESTA.getEdad());
                    }
                    if (tCCUOTAPROPUESTA.getValorVg() != null) {
                        existingTCCUOTAPROPUESTA.setValorVg(tCCUOTAPROPUESTA.getValorVg());
                    }
                    if (tCCUOTAPROPUESTA.getValorBipTres() != null) {
                        existingTCCUOTAPROPUESTA.setValorBipTres(tCCUOTAPROPUESTA.getValorBipTres());
                    }
                    if (tCCUOTAPROPUESTA.getValorBit() != null) {
                        existingTCCUOTAPROPUESTA.setValorBit(tCCUOTAPROPUESTA.getValorBit());
                    }
                    if (tCCUOTAPROPUESTA.getValorDi() != null) {
                        existingTCCUOTAPROPUESTA.setValorDi(tCCUOTAPROPUESTA.getValorDi());
                    }

                    return existingTCCUOTAPROPUESTA;
                }
            )
            .map(tCCUOTAPROPUESTARepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCUOTAPROPUESTA.getIdCuotaPropuesta().toString())
        );
    }

    /**
     * {@code GET  /tccuotapropuestas} : get all the tCCUOTAPROPUESTAS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCCUOTAPROPUESTAS in body.
     */
    @GetMapping("/tccuotapropuestas")
    public List<TCCUOTAPROPUESTA> getAllTCCUOTAPROPUESTAS() {
        log.debug("REST request to get all TCCUOTAPROPUESTAS");
        return tCCUOTAPROPUESTARepository.findAll();
    }

    /**
     * {@code GET  /tccuotapropuestas/:id} : get the "id" tCCUOTAPROPUESTA.
     *
     * @param id the id of the tCCUOTAPROPUESTA to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCCUOTAPROPUESTA, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tccuotapropuestas/{id}")
    public ResponseEntity<TCCUOTAPROPUESTA> getTCCUOTAPROPUESTA(@PathVariable Long id) {
        log.debug("REST request to get TCCUOTAPROPUESTA : {}", id);
        Optional<TCCUOTAPROPUESTA> tCCUOTAPROPUESTA = tCCUOTAPROPUESTARepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tCCUOTAPROPUESTA);
    }

    /**
     * {@code DELETE  /tccuotapropuestas/:id} : delete the "id" tCCUOTAPROPUESTA.
     *
     * @param id the id of the tCCUOTAPROPUESTA to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tccuotapropuestas/{id}")
    public ResponseEntity<Void> deleteTCCUOTAPROPUESTA(@PathVariable Long id) {
        log.debug("REST request to delete TCCUOTAPROPUESTA : {}", id);
        tCCUOTAPROPUESTARepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
