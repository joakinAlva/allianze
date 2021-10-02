package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCPRIMATARIFA;
import com.allianze.apicotizador.repository.TCPRIMATARIFARepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCPRIMATARIFA}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCPRIMATARIFAResource {

    private final Logger log = LoggerFactory.getLogger(TCPRIMATARIFAResource.class);

    private static final String ENTITY_NAME = "tCPRIMATARIFA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCPRIMATARIFARepository tCPRIMATARIFARepository;

    public TCPRIMATARIFAResource(TCPRIMATARIFARepository tCPRIMATARIFARepository) {
        this.tCPRIMATARIFARepository = tCPRIMATARIFARepository;
    }

    /**
     * {@code POST  /tcprimatarifas} : Create a new tCPRIMATARIFA.
     *
     * @param tCPRIMATARIFA the tCPRIMATARIFA to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCPRIMATARIFA, or with status {@code 400 (Bad Request)} if the tCPRIMATARIFA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcprimatarifas")
    public ResponseEntity<TCPRIMATARIFA> createTCPRIMATARIFA(@Valid @RequestBody TCPRIMATARIFA tCPRIMATARIFA) throws URISyntaxException {
        log.debug("REST request to save TCPRIMATARIFA : {}", tCPRIMATARIFA);
        if (tCPRIMATARIFA.getIdPrimaTarifa() != null) {
            throw new BadRequestAlertException("A new tCPRIMATARIFA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCPRIMATARIFA result = tCPRIMATARIFARepository.save(tCPRIMATARIFA);
        return ResponseEntity
            .created(new URI("/api/tcprimatarifas/" + result.getIdPrimaTarifa()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdPrimaTarifa().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcprimatarifas/:idPrimaTarifa} : Updates an existing tCPRIMATARIFA.
     *
     * @param idPrimaTarifa the id of the tCPRIMATARIFA to save.
     * @param tCPRIMATARIFA the tCPRIMATARIFA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCPRIMATARIFA,
     * or with status {@code 400 (Bad Request)} if the tCPRIMATARIFA is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCPRIMATARIFA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcprimatarifas/{idPrimaTarifa}")
    public ResponseEntity<TCPRIMATARIFA> updateTCPRIMATARIFA(
        @PathVariable(value = "idPrimaTarifa", required = false) final Long idPrimaTarifa,
        @Valid @RequestBody TCPRIMATARIFA tCPRIMATARIFA
    ) throws URISyntaxException {
        log.debug("REST request to update TCPRIMATARIFA : {}, {}", idPrimaTarifa, tCPRIMATARIFA);
        if (tCPRIMATARIFA.getIdPrimaTarifa() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idPrimaTarifa, tCPRIMATARIFA.getIdPrimaTarifa())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCPRIMATARIFARepository.existsById(idPrimaTarifa)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCPRIMATARIFA result = tCPRIMATARIFARepository.save(tCPRIMATARIFA);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCPRIMATARIFA.getIdPrimaTarifa().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tcprimatarifas/:idPrimaTarifa} : Partial updates given fields of an existing tCPRIMATARIFA, field will ignore if it is null
     *
     * @param idPrimaTarifa the id of the tCPRIMATARIFA to save.
     * @param tCPRIMATARIFA the tCPRIMATARIFA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCPRIMATARIFA,
     * or with status {@code 400 (Bad Request)} if the tCPRIMATARIFA is not valid,
     * or with status {@code 404 (Not Found)} if the tCPRIMATARIFA is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCPRIMATARIFA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcprimatarifas/{idPrimaTarifa}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCPRIMATARIFA> partialUpdateTCPRIMATARIFA(
        @PathVariable(value = "idPrimaTarifa", required = false) final Long idPrimaTarifa,
        @NotNull @RequestBody TCPRIMATARIFA tCPRIMATARIFA
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCPRIMATARIFA partially : {}, {}", idPrimaTarifa, tCPRIMATARIFA);
        if (tCPRIMATARIFA.getIdPrimaTarifa() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idPrimaTarifa, tCPRIMATARIFA.getIdPrimaTarifa())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCPRIMATARIFARepository.existsById(idPrimaTarifa)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCPRIMATARIFA> result = tCPRIMATARIFARepository
            .findById(tCPRIMATARIFA.getIdPrimaTarifa())
            .map(
                existingTCPRIMATARIFA -> {
                    if (tCPRIMATARIFA.getDivPrimaTarifa() != null) {
                        existingTCPRIMATARIFA.setDivPrimaTarifa(tCPRIMATARIFA.getDivPrimaTarifa());
                    }
                    if (tCPRIMATARIFA.getzNeta() != null) {
                        existingTCPRIMATARIFA.setzNeta(tCPRIMATARIFA.getzNeta());
                    }
                    if (tCPRIMATARIFA.getDivPrimaRiesgo() != null) {
                        existingTCPRIMATARIFA.setDivPrimaRiesgo(tCPRIMATARIFA.getDivPrimaRiesgo());
                    }
                    if (tCPRIMATARIFA.getzRiesgo() != null) {
                        existingTCPRIMATARIFA.setzRiesgo(tCPRIMATARIFA.getzRiesgo());
                    }

                    return existingTCPRIMATARIFA;
                }
            )
            .map(tCPRIMATARIFARepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCPRIMATARIFA.getIdPrimaTarifa().toString())
        );
    }

    /**
     * {@code GET  /tcprimatarifas} : get all the tCPRIMATARIFAS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCPRIMATARIFAS in body.
     */
    @GetMapping("/tcprimatarifas")
    public List<TCPRIMATARIFA> getAllTCPRIMATARIFAS() {
        log.debug("REST request to get all TCPRIMATARIFAS");
        return tCPRIMATARIFARepository.findAll();
    }

    /**
     * {@code GET  /tcprimatarifas/:id} : get the "id" tCPRIMATARIFA.
     *
     * @param id the id of the tCPRIMATARIFA to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCPRIMATARIFA, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tcprimatarifas/{id}")
    public ResponseEntity<TCPRIMATARIFA> getTCPRIMATARIFA(@PathVariable Long id) {
        log.debug("REST request to get TCPRIMATARIFA : {}", id);
        Optional<TCPRIMATARIFA> tCPRIMATARIFA = tCPRIMATARIFARepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tCPRIMATARIFA);
    }

    /**
     * {@code DELETE  /tcprimatarifas/:id} : delete the "id" tCPRIMATARIFA.
     *
     * @param id the id of the tCPRIMATARIFA to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tcprimatarifas/{id}")
    public ResponseEntity<Void> deleteTCPRIMATARIFA(@PathVariable Long id) {
        log.debug("REST request to delete TCPRIMATARIFA : {}", id);
        tCPRIMATARIFARepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
