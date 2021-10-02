package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCREFENCIA;
import com.allianze.apicotizador.repository.TCREFENCIARepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCREFENCIA}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCREFENCIAResource {

    private final Logger log = LoggerFactory.getLogger(TCREFENCIAResource.class);

    private static final String ENTITY_NAME = "tCREFENCIA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCREFENCIARepository tCREFENCIARepository;

    public TCREFENCIAResource(TCREFENCIARepository tCREFENCIARepository) {
        this.tCREFENCIARepository = tCREFENCIARepository;
    }

    /**
     * {@code POST  /tcrefencias} : Create a new tCREFENCIA.
     *
     * @param tCREFENCIA the tCREFENCIA to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCREFENCIA, or with status {@code 400 (Bad Request)} if the tCREFENCIA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcrefencias")
    public ResponseEntity<TCREFENCIA> createTCREFENCIA(@Valid @RequestBody TCREFENCIA tCREFENCIA) throws URISyntaxException {
        log.debug("REST request to save TCREFENCIA : {}", tCREFENCIA);
        if (tCREFENCIA.getIdReferencia() != null) {
            throw new BadRequestAlertException("A new tCREFENCIA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCREFENCIA result = tCREFENCIARepository.save(tCREFENCIA);
        return ResponseEntity
            .created(new URI("/api/tcrefencias/" + result.getIdReferencia()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdReferencia().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcrefencias/:idReferencia} : Updates an existing tCREFENCIA.
     *
     * @param idReferencia the id of the tCREFENCIA to save.
     * @param tCREFENCIA the tCREFENCIA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCREFENCIA,
     * or with status {@code 400 (Bad Request)} if the tCREFENCIA is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCREFENCIA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcrefencias/{idReferencia}")
    public ResponseEntity<TCREFENCIA> updateTCREFENCIA(
        @PathVariable(value = "idReferencia", required = false) final Long idReferencia,
        @Valid @RequestBody TCREFENCIA tCREFENCIA
    ) throws URISyntaxException {
        log.debug("REST request to update TCREFENCIA : {}, {}", idReferencia, tCREFENCIA);
        if (tCREFENCIA.getIdReferencia() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idReferencia, tCREFENCIA.getIdReferencia())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCREFENCIARepository.existsById(idReferencia)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCREFENCIA result = tCREFENCIARepository.save(tCREFENCIA);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCREFENCIA.getIdReferencia().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tcrefencias/:idReferencia} : Partial updates given fields of an existing tCREFENCIA, field will ignore if it is null
     *
     * @param idReferencia the id of the tCREFENCIA to save.
     * @param tCREFENCIA the tCREFENCIA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCREFENCIA,
     * or with status {@code 400 (Bad Request)} if the tCREFENCIA is not valid,
     * or with status {@code 404 (Not Found)} if the tCREFENCIA is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCREFENCIA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcrefencias/{idReferencia}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCREFENCIA> partialUpdateTCREFENCIA(
        @PathVariable(value = "idReferencia", required = false) final Long idReferencia,
        @NotNull @RequestBody TCREFENCIA tCREFENCIA
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCREFENCIA partially : {}, {}", idReferencia, tCREFENCIA);
        if (tCREFENCIA.getIdReferencia() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idReferencia, tCREFENCIA.getIdReferencia())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCREFENCIARepository.existsById(idReferencia)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCREFENCIA> result = tCREFENCIARepository
            .findById(tCREFENCIA.getIdReferencia())
            .map(
                existingTCREFENCIA -> {
                    if (tCREFENCIA.getPeriodo() != null) {
                        existingTCREFENCIA.setPeriodo(tCREFENCIA.getPeriodo());
                    }
                    if (tCREFENCIA.getReferencia() != null) {
                        existingTCREFENCIA.setReferencia(tCREFENCIA.getReferencia());
                    }
                    if (tCREFENCIA.getEdadpromedio() != null) {
                        existingTCREFENCIA.setEdadpromedio(tCREFENCIA.getEdadpromedio());
                    }
                    if (tCREFENCIA.getCuota() != null) {
                        existingTCREFENCIA.setCuota(tCREFENCIA.getCuota());
                    }

                    return existingTCREFENCIA;
                }
            )
            .map(tCREFENCIARepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCREFENCIA.getIdReferencia().toString())
        );
    }

    /**
     * {@code GET  /tcrefencias} : get all the tCREFENCIAS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCREFENCIAS in body.
     */
    @GetMapping("/tcrefencias")
    public List<TCREFENCIA> getAllTCREFENCIAS() {
        log.debug("REST request to get all TCREFENCIAS");
        return tCREFENCIARepository.findAll();
    }

    /**
     * {@code GET  /tcrefencias/:id} : get the "id" tCREFENCIA.
     *
     * @param id the id of the tCREFENCIA to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCREFENCIA, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tcrefencias/{id}")
    public ResponseEntity<TCREFENCIA> getTCREFENCIA(@PathVariable Long id) {
        log.debug("REST request to get TCREFENCIA : {}", id);
        Optional<TCREFENCIA> tCREFENCIA = tCREFENCIARepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tCREFENCIA);
    }

    /**
     * {@code DELETE  /tcrefencias/:id} : delete the "id" tCREFENCIA.
     *
     * @param id the id of the tCREFENCIA to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tcrefencias/{id}")
    public ResponseEntity<Void> deleteTCREFENCIA(@PathVariable Long id) {
        log.debug("REST request to delete TCREFENCIA : {}", id);
        tCREFENCIARepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
