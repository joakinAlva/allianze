package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCTIPOREGLA;
import com.allianze.apicotizador.dto.TCTIPOREGLADTO;
import com.allianze.apicotizador.repository.TCTIPOREGLARepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCTIPOREGLA}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCTIPOREGLAResource {

    private final Logger log = LoggerFactory.getLogger(TCTIPOREGLAResource.class);

    private static final String ENTITY_NAME = "tCTIPOREGLA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCTIPOREGLARepository tCTIPOREGLARepository;

    public TCTIPOREGLAResource(TCTIPOREGLARepository tCTIPOREGLARepository) {
        this.tCTIPOREGLARepository = tCTIPOREGLARepository;
    }

    /**
     * {@code POST  /tctiporeglas/insert} : Create a new tCTIPOREGLA.
     *
     * @param tCTIPOREGLA the tCTIPOREGLA to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCTIPOREGLA, or with status {@code 400 (Bad Request)} if the tCTIPOREGLA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tctiporeglas/insert")
    public ResponseEntity<TCTIPOREGLA> createTCTIPOREGLA(@Valid @RequestBody TCTIPOREGLA tCTIPOREGLA) throws URISyntaxException {
        log.debug("REST request to save TCTIPOREGLA : {}", tCTIPOREGLA);
        if (tCTIPOREGLA.getIdTipoRegla() != null) {
            throw new BadRequestAlertException("A new tCTIPOREGLA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCTIPOREGLA result = tCTIPOREGLARepository.save(tCTIPOREGLA);
        return ResponseEntity
            .created(new URI("/api/tctiporeglas/" + result.getIdTipoRegla()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdTipoRegla().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tctiporeglas/:idTipoRegla} : Updates an existing tCTIPOREGLA.
     *
     * @param idTipoRegla the id of the tCTIPOREGLA to save.
     * @param tCTIPOREGLA the tCTIPOREGLA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCTIPOREGLA,
     * or with status {@code 400 (Bad Request)} if the tCTIPOREGLA is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCTIPOREGLA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tctiporeglas/{idTipoRegla}")
    public ResponseEntity<TCTIPOREGLA> updateTCTIPOREGLA(
        @PathVariable(value = "idTipoRegla", required = false) final Long idTipoRegla,
        @Valid @RequestBody TCTIPOREGLA tCTIPOREGLA
    ) throws URISyntaxException {
        log.debug("REST request to update TCTIPOREGLA : {}, {}", idTipoRegla, tCTIPOREGLA);
        if (tCTIPOREGLA.getIdTipoRegla() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idTipoRegla, tCTIPOREGLA.getIdTipoRegla())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCTIPOREGLARepository.existsById(idTipoRegla)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCTIPOREGLA result = tCTIPOREGLARepository.save(tCTIPOREGLA);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCTIPOREGLA.getIdTipoRegla().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tctiporeglas/:idTipoRegla} : Partial updates given fields of an existing tCTIPOREGLA, field will ignore if it is null
     *
     * @param idTipoRegla the id of the tCTIPOREGLA to save.
     * @param tCTIPOREGLA the tCTIPOREGLA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCTIPOREGLA,
     * or with status {@code 400 (Bad Request)} if the tCTIPOREGLA is not valid,
     * or with status {@code 404 (Not Found)} if the tCTIPOREGLA is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCTIPOREGLA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tctiporeglas/{idTipoRegla}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCTIPOREGLA> partialUpdateTCTIPOREGLA(
        @PathVariable(value = "idTipoRegla", required = false) final Long idTipoRegla,
        @NotNull @RequestBody TCTIPOREGLA tCTIPOREGLA
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCTIPOREGLA partially : {}, {}", idTipoRegla, tCTIPOREGLA);
        if (tCTIPOREGLA.getIdTipoRegla() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idTipoRegla, tCTIPOREGLA.getIdTipoRegla())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCTIPOREGLARepository.existsById(idTipoRegla)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCTIPOREGLA> result = tCTIPOREGLARepository
            .findById(tCTIPOREGLA.getIdTipoRegla())
            .map(
                existingTCTIPOREGLA -> {
                    if (tCTIPOREGLA.getTipo() != null) {
                        existingTCTIPOREGLA.setTipo(tCTIPOREGLA.getTipo());
                    }
                    if (tCTIPOREGLA.getSegmento() != null) {
                        existingTCTIPOREGLA.setSegmento(tCTIPOREGLA.getSegmento());
                    }

                    return existingTCTIPOREGLA;
                }
            )
            .map(tCTIPOREGLARepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCTIPOREGLA.getIdTipoRegla().toString())
        );
    }

    /**
     * {@code POST  /tctiporeglas/getAll} : get all the tCTIPOREGLAS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCTIPOREGLAS in body.
     */
    @PostMapping("/tctiporeglas/getAll")
    public List<TCTIPOREGLA> getAllTCTIPOREGLAS( @RequestBody TCTIPOREGLADTO tcTipoReglaDto) {
        log.debug("REST request to get all TCTIPOREGLAS");
        return tCTIPOREGLARepository.findAll();
    }

    /**
     * {@code POST  /tctiporeglas/getId} : get the "id" tCTIPOREGLA.
     *
     * @param id the id of the tCTIPOREGLA to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCTIPOREGLA, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tctiporeglas/getId")
    public ResponseEntity<TCTIPOREGLA> getTCTIPOREGLA(@RequestBody TCTIPOREGLADTO tcTipoReglaDto) {
        log.debug("REST request to get TCTIPOREGLA : {}", tcTipoReglaDto.getId());
        Optional<TCTIPOREGLA> tCTIPOREGLA = tCTIPOREGLARepository.findById(tcTipoReglaDto.getId());
        return ResponseUtil.wrapOrNotFound(tCTIPOREGLA);
    }

    /**
     * {@code POST  /tctiporeglas/deleteId} : delete the "id" tCTIPOREGLA.
     *
     * @param id the id of the tCTIPOREGLA to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tctiporeglas/deleteId")
    public ResponseEntity<Void> deleteTCTIPOREGLA(@RequestBody TCTIPOREGLADTO tcTipoReglaDto) {
        log.debug("REST request to delete TCTIPOREGLA : {}", tcTipoReglaDto.getId());
        tCTIPOREGLARepository.deleteById(tcTipoReglaDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcTipoReglaDto.getId().toString()))
            .build();
    }
}
