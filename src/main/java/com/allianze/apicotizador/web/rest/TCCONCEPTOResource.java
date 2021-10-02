package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCCONCEPTO;
import com.allianze.apicotizador.repository.TCCONCEPTORepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCCONCEPTO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCCONCEPTOResource {

    private final Logger log = LoggerFactory.getLogger(TCCONCEPTOResource.class);

    private static final String ENTITY_NAME = "tCCONCEPTO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCCONCEPTORepository tCCONCEPTORepository;

    public TCCONCEPTOResource(TCCONCEPTORepository tCCONCEPTORepository) {
        this.tCCONCEPTORepository = tCCONCEPTORepository;
    }

    /**
     * {@code POST  /tcconceptos} : Create a new tCCONCEPTO.
     *
     * @param tCCONCEPTO the tCCONCEPTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCCONCEPTO, or with status {@code 400 (Bad Request)} if the tCCONCEPTO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcconceptos")
    public ResponseEntity<TCCONCEPTO> createTCCONCEPTO(@Valid @RequestBody TCCONCEPTO tCCONCEPTO) throws URISyntaxException {
        log.debug("REST request to save TCCONCEPTO : {}", tCCONCEPTO);
        if (tCCONCEPTO.getIdConcepto() != null) {
            throw new BadRequestAlertException("A new tCCONCEPTO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCCONCEPTO result = tCCONCEPTORepository.save(tCCONCEPTO);
        return ResponseEntity
            .created(new URI("/api/tcconceptos/" + result.getIdConcepto()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdConcepto().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcconceptos/:idConcepto} : Updates an existing tCCONCEPTO.
     *
     * @param idConcepto the id of the tCCONCEPTO to save.
     * @param tCCONCEPTO the tCCONCEPTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCONCEPTO,
     * or with status {@code 400 (Bad Request)} if the tCCONCEPTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCCONCEPTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcconceptos/{idConcepto}")
    public ResponseEntity<TCCONCEPTO> updateTCCONCEPTO(
        @PathVariable(value = "idConcepto", required = false) final Long idConcepto,
        @Valid @RequestBody TCCONCEPTO tCCONCEPTO
    ) throws URISyntaxException {
        log.debug("REST request to update TCCONCEPTO : {}, {}", idConcepto, tCCONCEPTO);
        if (tCCONCEPTO.getIdConcepto() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idConcepto, tCCONCEPTO.getIdConcepto())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCONCEPTORepository.existsById(idConcepto)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCCONCEPTO result = tCCONCEPTORepository.save(tCCONCEPTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCONCEPTO.getIdConcepto().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tcconceptos/:idConcepto} : Partial updates given fields of an existing tCCONCEPTO, field will ignore if it is null
     *
     * @param idConcepto the id of the tCCONCEPTO to save.
     * @param tCCONCEPTO the tCCONCEPTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCONCEPTO,
     * or with status {@code 400 (Bad Request)} if the tCCONCEPTO is not valid,
     * or with status {@code 404 (Not Found)} if the tCCONCEPTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCCONCEPTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcconceptos/{idConcepto}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCCONCEPTO> partialUpdateTCCONCEPTO(
        @PathVariable(value = "idConcepto", required = false) final Long idConcepto,
        @NotNull @RequestBody TCCONCEPTO tCCONCEPTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCCONCEPTO partially : {}, {}", idConcepto, tCCONCEPTO);
        if (tCCONCEPTO.getIdConcepto() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idConcepto, tCCONCEPTO.getIdConcepto())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCONCEPTORepository.existsById(idConcepto)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCCONCEPTO> result = tCCONCEPTORepository
            .findById(tCCONCEPTO.getIdConcepto())
            .map(
                existingTCCONCEPTO -> {
                    if (tCCONCEPTO.getConcepto() != null) {
                        existingTCCONCEPTO.setConcepto(tCCONCEPTO.getConcepto());
                    }
                    if (tCCONCEPTO.getDato() != null) {
                        existingTCCONCEPTO.setDato(tCCONCEPTO.getDato());
                    }

                    return existingTCCONCEPTO;
                }
            )
            .map(tCCONCEPTORepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCONCEPTO.getIdConcepto().toString())
        );
    }

    /**
     * {@code GET  /tcconceptos} : get all the tCCONCEPTOS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCCONCEPTOS in body.
     */
    @GetMapping("/tcconceptos")
    public List<TCCONCEPTO> getAllTCCONCEPTOS() {
        log.debug("REST request to get all TCCONCEPTOS");
        return tCCONCEPTORepository.findAll();
    }

    /**
     * {@code GET  /tcconceptos/:id} : get the "id" tCCONCEPTO.
     *
     * @param id the id of the tCCONCEPTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCCONCEPTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tcconceptos/{id}")
    public ResponseEntity<TCCONCEPTO> getTCCONCEPTO(@PathVariable Long id) {
        log.debug("REST request to get TCCONCEPTO : {}", id);
        Optional<TCCONCEPTO> tCCONCEPTO = tCCONCEPTORepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tCCONCEPTO);
    }

    /**
     * {@code DELETE  /tcconceptos/:id} : delete the "id" tCCONCEPTO.
     *
     * @param id the id of the tCCONCEPTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tcconceptos/{id}")
    public ResponseEntity<Void> deleteTCCONCEPTO(@PathVariable Long id) {
        log.debug("REST request to delete TCCONCEPTO : {}", id);
        tCCONCEPTORepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
