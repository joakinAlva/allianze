package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCRANGOPRIMA;
import com.allianze.apicotizador.repository.TCRANGOPRIMARepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCRANGOPRIMA}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCRANGOPRIMAResource {

    private final Logger log = LoggerFactory.getLogger(TCRANGOPRIMAResource.class);

    private static final String ENTITY_NAME = "tCRANGOPRIMA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCRANGOPRIMARepository tCRANGOPRIMARepository;

    public TCRANGOPRIMAResource(TCRANGOPRIMARepository tCRANGOPRIMARepository) {
        this.tCRANGOPRIMARepository = tCRANGOPRIMARepository;
    }

    /**
     * {@code POST  /tcrangoprimas} : Create a new tCRANGOPRIMA.
     *
     * @param tCRANGOPRIMA the tCRANGOPRIMA to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCRANGOPRIMA, or with status {@code 400 (Bad Request)} if the tCRANGOPRIMA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcrangoprimas")
    public ResponseEntity<TCRANGOPRIMA> createTCRANGOPRIMA(@Valid @RequestBody TCRANGOPRIMA tCRANGOPRIMA) throws URISyntaxException {
        log.debug("REST request to save TCRANGOPRIMA : {}", tCRANGOPRIMA);
        if (tCRANGOPRIMA.getIdRangoPrima() != null) {
            throw new BadRequestAlertException("A new tCRANGOPRIMA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCRANGOPRIMA result = tCRANGOPRIMARepository.save(tCRANGOPRIMA);
        return ResponseEntity
            .created(new URI("/api/tcrangoprimas/" + result.getIdRangoPrima()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdRangoPrima().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcrangoprimas/:idRangoPrima} : Updates an existing tCRANGOPRIMA.
     *
     * @param idRangoPrima the id of the tCRANGOPRIMA to save.
     * @param tCRANGOPRIMA the tCRANGOPRIMA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCRANGOPRIMA,
     * or with status {@code 400 (Bad Request)} if the tCRANGOPRIMA is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCRANGOPRIMA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcrangoprimas/{idRangoPrima}")
    public ResponseEntity<TCRANGOPRIMA> updateTCRANGOPRIMA(
        @PathVariable(value = "idRangoPrima", required = false) final Long idRangoPrima,
        @Valid @RequestBody TCRANGOPRIMA tCRANGOPRIMA
    ) throws URISyntaxException {
        log.debug("REST request to update TCRANGOPRIMA : {}, {}", idRangoPrima, tCRANGOPRIMA);
        if (tCRANGOPRIMA.getIdRangoPrima() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idRangoPrima, tCRANGOPRIMA.getIdRangoPrima())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCRANGOPRIMARepository.existsById(idRangoPrima)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCRANGOPRIMA result = tCRANGOPRIMARepository.save(tCRANGOPRIMA);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCRANGOPRIMA.getIdRangoPrima().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tcrangoprimas/:idRangoPrima} : Partial updates given fields of an existing tCRANGOPRIMA, field will ignore if it is null
     *
     * @param idRangoPrima the id of the tCRANGOPRIMA to save.
     * @param tCRANGOPRIMA the tCRANGOPRIMA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCRANGOPRIMA,
     * or with status {@code 400 (Bad Request)} if the tCRANGOPRIMA is not valid,
     * or with status {@code 404 (Not Found)} if the tCRANGOPRIMA is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCRANGOPRIMA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcrangoprimas/{idRangoPrima}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCRANGOPRIMA> partialUpdateTCRANGOPRIMA(
        @PathVariable(value = "idRangoPrima", required = false) final Long idRangoPrima,
        @NotNull @RequestBody TCRANGOPRIMA tCRANGOPRIMA
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCRANGOPRIMA partially : {}, {}", idRangoPrima, tCRANGOPRIMA);
        if (tCRANGOPRIMA.getIdRangoPrima() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idRangoPrima, tCRANGOPRIMA.getIdRangoPrima())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCRANGOPRIMARepository.existsById(idRangoPrima)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCRANGOPRIMA> result = tCRANGOPRIMARepository
            .findById(tCRANGOPRIMA.getIdRangoPrima())
            .map(
                existingTCRANGOPRIMA -> {
                    if (tCRANGOPRIMA.getValorMin() != null) {
                        existingTCRANGOPRIMA.setValorMin(tCRANGOPRIMA.getValorMin());
                    }
                    if (tCRANGOPRIMA.getValorMax() != null) {
                        existingTCRANGOPRIMA.setValorMax(tCRANGOPRIMA.getValorMax());
                    }
                    if (tCRANGOPRIMA.getDividendos() != null) {
                        existingTCRANGOPRIMA.setDividendos(tCRANGOPRIMA.getDividendos());
                    }
                    if (tCRANGOPRIMA.getComision() != null) {
                        existingTCRANGOPRIMA.setComision(tCRANGOPRIMA.getComision());
                    }

                    return existingTCRANGOPRIMA;
                }
            )
            .map(tCRANGOPRIMARepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCRANGOPRIMA.getIdRangoPrima().toString())
        );
    }

    /**
     * {@code GET  /tcrangoprimas} : get all the tCRANGOPRIMAS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCRANGOPRIMAS in body.
     */
    @GetMapping("/tcrangoprimas")
    public List<TCRANGOPRIMA> getAllTCRANGOPRIMAS() {
        log.debug("REST request to get all TCRANGOPRIMAS");
        return tCRANGOPRIMARepository.findAll();
    }

    /**
     * {@code GET  /tcrangoprimas/:id} : get the "id" tCRANGOPRIMA.
     *
     * @param id the id of the tCRANGOPRIMA to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCRANGOPRIMA, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tcrangoprimas/{id}")
    public ResponseEntity<TCRANGOPRIMA> getTCRANGOPRIMA(@PathVariable Long id) {
        log.debug("REST request to get TCRANGOPRIMA : {}", id);
        Optional<TCRANGOPRIMA> tCRANGOPRIMA = tCRANGOPRIMARepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tCRANGOPRIMA);
    }

    /**
     * {@code DELETE  /tcrangoprimas/:id} : delete the "id" tCRANGOPRIMA.
     *
     * @param id the id of the tCRANGOPRIMA to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tcrangoprimas/{id}")
    public ResponseEntity<Void> deleteTCRANGOPRIMA(@PathVariable Long id) {
        log.debug("REST request to delete TCRANGOPRIMA : {}", id);
        tCRANGOPRIMARepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
