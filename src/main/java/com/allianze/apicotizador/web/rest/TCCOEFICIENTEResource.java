package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCCOEFICIENTE;
import com.allianze.apicotizador.repository.TCCOEFICIENTERepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCCOEFICIENTE}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCCOEFICIENTEResource {

    private final Logger log = LoggerFactory.getLogger(TCCOEFICIENTEResource.class);

    private static final String ENTITY_NAME = "tCCOEFICIENTE";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCCOEFICIENTERepository tCCOEFICIENTERepository;

    public TCCOEFICIENTEResource(TCCOEFICIENTERepository tCCOEFICIENTERepository) {
        this.tCCOEFICIENTERepository = tCCOEFICIENTERepository;
    }

    /**
     * {@code POST  /tccoeficientes} : Create a new tCCOEFICIENTE.
     *
     * @param tCCOEFICIENTE the tCCOEFICIENTE to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCCOEFICIENTE, or with status {@code 400 (Bad Request)} if the tCCOEFICIENTE has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tccoeficientes")
    public ResponseEntity<TCCOEFICIENTE> createTCCOEFICIENTE(@Valid @RequestBody TCCOEFICIENTE tCCOEFICIENTE) throws URISyntaxException {
        log.debug("REST request to save TCCOEFICIENTE : {}", tCCOEFICIENTE);
        if (tCCOEFICIENTE.getIdCoeficiente() != null) {
            throw new BadRequestAlertException("A new tCCOEFICIENTE cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCCOEFICIENTE result = tCCOEFICIENTERepository.save(tCCOEFICIENTE);
        return ResponseEntity
            .created(new URI("/api/tccoeficientes/" + result.getIdCoeficiente()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdCoeficiente().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tccoeficientes/:idCoeficiente} : Updates an existing tCCOEFICIENTE.
     *
     * @param idCoeficiente the id of the tCCOEFICIENTE to save.
     * @param tCCOEFICIENTE the tCCOEFICIENTE to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCOEFICIENTE,
     * or with status {@code 400 (Bad Request)} if the tCCOEFICIENTE is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCCOEFICIENTE couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tccoeficientes/{idCoeficiente}")
    public ResponseEntity<TCCOEFICIENTE> updateTCCOEFICIENTE(
        @PathVariable(value = "idCoeficiente", required = false) final Long idCoeficiente,
        @Valid @RequestBody TCCOEFICIENTE tCCOEFICIENTE
    ) throws URISyntaxException {
        log.debug("REST request to update TCCOEFICIENTE : {}, {}", idCoeficiente, tCCOEFICIENTE);
        if (tCCOEFICIENTE.getIdCoeficiente() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCoeficiente, tCCOEFICIENTE.getIdCoeficiente())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCOEFICIENTERepository.existsById(idCoeficiente)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCCOEFICIENTE result = tCCOEFICIENTERepository.save(tCCOEFICIENTE);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCOEFICIENTE.getIdCoeficiente().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tccoeficientes/:idCoeficiente} : Partial updates given fields of an existing tCCOEFICIENTE, field will ignore if it is null
     *
     * @param idCoeficiente the id of the tCCOEFICIENTE to save.
     * @param tCCOEFICIENTE the tCCOEFICIENTE to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCOEFICIENTE,
     * or with status {@code 400 (Bad Request)} if the tCCOEFICIENTE is not valid,
     * or with status {@code 404 (Not Found)} if the tCCOEFICIENTE is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCCOEFICIENTE couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tccoeficientes/{idCoeficiente}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCCOEFICIENTE> partialUpdateTCCOEFICIENTE(
        @PathVariable(value = "idCoeficiente", required = false) final Long idCoeficiente,
        @NotNull @RequestBody TCCOEFICIENTE tCCOEFICIENTE
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCCOEFICIENTE partially : {}, {}", idCoeficiente, tCCOEFICIENTE);
        if (tCCOEFICIENTE.getIdCoeficiente() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCoeficiente, tCCOEFICIENTE.getIdCoeficiente())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCOEFICIENTERepository.existsById(idCoeficiente)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCCOEFICIENTE> result = tCCOEFICIENTERepository
            .findById(tCCOEFICIENTE.getIdCoeficiente())
            .map(
                existingTCCOEFICIENTE -> {
                    if (tCCOEFICIENTE.getCoeficiente() != null) {
                        existingTCCOEFICIENTE.setCoeficiente(tCCOEFICIENTE.getCoeficiente());
                    }
                    if (tCCOEFICIENTE.getIntervaloMin() != null) {
                        existingTCCOEFICIENTE.setIntervaloMin(tCCOEFICIENTE.getIntervaloMin());
                    }
                    if (tCCOEFICIENTE.getIntervaloMax() != null) {
                        existingTCCOEFICIENTE.setIntervaloMax(tCCOEFICIENTE.getIntervaloMax());
                    }
                    if (tCCOEFICIENTE.getDescuentoExtra() != null) {
                        existingTCCOEFICIENTE.setDescuentoExtra(tCCOEFICIENTE.getDescuentoExtra());
                    }

                    return existingTCCOEFICIENTE;
                }
            )
            .map(tCCOEFICIENTERepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCOEFICIENTE.getIdCoeficiente().toString())
        );
    }

    /**
     * {@code GET  /tccoeficientes} : get all the tCCOEFICIENTES.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCCOEFICIENTES in body.
     */
    @GetMapping("/tccoeficientes")
    public List<TCCOEFICIENTE> getAllTCCOEFICIENTES() {
        log.debug("REST request to get all TCCOEFICIENTES");
        return tCCOEFICIENTERepository.findAll();
    }

    /**
     * {@code GET  /tccoeficientes/:id} : get the "id" tCCOEFICIENTE.
     *
     * @param id the id of the tCCOEFICIENTE to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCCOEFICIENTE, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tccoeficientes/{id}")
    public ResponseEntity<TCCOEFICIENTE> getTCCOEFICIENTE(@PathVariable Long id) {
        log.debug("REST request to get TCCOEFICIENTE : {}", id);
        Optional<TCCOEFICIENTE> tCCOEFICIENTE = tCCOEFICIENTERepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tCCOEFICIENTE);
    }

    /**
     * {@code DELETE  /tccoeficientes/:id} : delete the "id" tCCOEFICIENTE.
     *
     * @param id the id of the tCCOEFICIENTE to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tccoeficientes/{id}")
    public ResponseEntity<Void> deleteTCCOEFICIENTE(@PathVariable Long id) {
        log.debug("REST request to delete TCCOEFICIENTE : {}", id);
        tCCOEFICIENTERepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
