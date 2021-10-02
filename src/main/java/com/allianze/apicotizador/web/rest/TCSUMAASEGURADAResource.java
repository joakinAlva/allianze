package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCSUMAASEGURADA;
import com.allianze.apicotizador.dto.TCSUMAASEGURADORADTO;
import com.allianze.apicotizador.repository.TCSUMAASEGURADARepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCSUMAASEGURADA}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCSUMAASEGURADAResource {

    private final Logger log = LoggerFactory.getLogger(TCSUMAASEGURADAResource.class);

    private static final String ENTITY_NAME = "tCSUMAASEGURADA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCSUMAASEGURADARepository tCSUMAASEGURADARepository;

    public TCSUMAASEGURADAResource(TCSUMAASEGURADARepository tCSUMAASEGURADARepository) {
        this.tCSUMAASEGURADARepository = tCSUMAASEGURADARepository;
    }

    /**
     * {@code POST  /tcsumaaseguradas} : Create a new tCSUMAASEGURADA.
     *
     * @param tCSUMAASEGURADA the tCSUMAASEGURADA to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCSUMAASEGURADA, or with status {@code 400 (Bad Request)} if the tCSUMAASEGURADA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcsumaaseguradas")
    public ResponseEntity<TCSUMAASEGURADA> createTCSUMAASEGURADA(@Valid @RequestBody TCSUMAASEGURADA tCSUMAASEGURADA)
        throws URISyntaxException {
        log.debug("REST request to save TCSUMAASEGURADA : {}", tCSUMAASEGURADA);
        if (tCSUMAASEGURADA.getIdSumaAsegurada() != null) {
            throw new BadRequestAlertException("A new tCSUMAASEGURADA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCSUMAASEGURADA result = tCSUMAASEGURADARepository.save(tCSUMAASEGURADA);
        return ResponseEntity
            .created(new URI("/api/tcsumaaseguradas/" + result.getIdSumaAsegurada()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdSumaAsegurada().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcsumaaseguradas/:idSumaAsegurada} : Updates an existing tCSUMAASEGURADA.
     *
     * @param idSumaAsegurada the id of the tCSUMAASEGURADA to save.
     * @param tCSUMAASEGURADA the tCSUMAASEGURADA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCSUMAASEGURADA,
     * or with status {@code 400 (Bad Request)} if the tCSUMAASEGURADA is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCSUMAASEGURADA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcsumaaseguradas/{idSumaAsegurada}")
    public ResponseEntity<TCSUMAASEGURADA> updateTCSUMAASEGURADA(
        @PathVariable(value = "idSumaAsegurada", required = false) final Long idSumaAsegurada,
        @Valid @RequestBody TCSUMAASEGURADA tCSUMAASEGURADA
    ) throws URISyntaxException {
        log.debug("REST request to update TCSUMAASEGURADA : {}, {}", idSumaAsegurada, tCSUMAASEGURADA);
        if (tCSUMAASEGURADA.getIdSumaAsegurada() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idSumaAsegurada, tCSUMAASEGURADA.getIdSumaAsegurada())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCSUMAASEGURADARepository.existsById(idSumaAsegurada)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCSUMAASEGURADA result = tCSUMAASEGURADARepository.save(tCSUMAASEGURADA);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCSUMAASEGURADA.getIdSumaAsegurada().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tcsumaaseguradas/:idSumaAsegurada} : Partial updates given fields of an existing tCSUMAASEGURADA, field will ignore if it is null
     *
     * @param idSumaAsegurada the id of the tCSUMAASEGURADA to save.
     * @param tCSUMAASEGURADA the tCSUMAASEGURADA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCSUMAASEGURADA,
     * or with status {@code 400 (Bad Request)} if the tCSUMAASEGURADA is not valid,
     * or with status {@code 404 (Not Found)} if the tCSUMAASEGURADA is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCSUMAASEGURADA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcsumaaseguradas/{idSumaAsegurada}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCSUMAASEGURADA> partialUpdateTCSUMAASEGURADA(
        @PathVariable(value = "idSumaAsegurada", required = false) final Long idSumaAsegurada,
        @NotNull @RequestBody TCSUMAASEGURADA tCSUMAASEGURADA
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCSUMAASEGURADA partially : {}, {}", idSumaAsegurada, tCSUMAASEGURADA);
        if (tCSUMAASEGURADA.getIdSumaAsegurada() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idSumaAsegurada, tCSUMAASEGURADA.getIdSumaAsegurada())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCSUMAASEGURADARepository.existsById(idSumaAsegurada)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCSUMAASEGURADA> result = tCSUMAASEGURADARepository
            .findById(tCSUMAASEGURADA.getIdSumaAsegurada())
            .map(
                existingTCSUMAASEGURADA -> {
                    if (tCSUMAASEGURADA.getSagff() != null) {
                        existingTCSUMAASEGURADA.setSagff(tCSUMAASEGURADA.getSagff());
                    }
                    if (tCSUMAASEGURADA.getSaca() != null) {
                        existingTCSUMAASEGURADA.setSaca(tCSUMAASEGURADA.getSaca());
                    }
                    if (tCSUMAASEGURADA.getSage() != null) {
                        existingTCSUMAASEGURADA.setSage(tCSUMAASEGURADA.getSage());
                    }
                    if (tCSUMAASEGURADA.getSaiqa() != null) {
                        existingTCSUMAASEGURADA.setSaiqa(tCSUMAASEGURADA.getSaiqa());
                    }
                    if (tCSUMAASEGURADA.getSaiqv() != null) {
                        existingTCSUMAASEGURADA.setSaiqv(tCSUMAASEGURADA.getSaiqv());
                    }

                    return existingTCSUMAASEGURADA;
                }
            )
            .map(tCSUMAASEGURADARepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCSUMAASEGURADA.getIdSumaAsegurada().toString())
        );
    }

    /**
     * {@code POST  /tcsumaaseguradas/getAll} : get all the tCSUMAASEGURADAS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCSUMAASEGURADAS in body.
     */
    @PostMapping("/tcsumaaseguradas/getAll")
    public List<TCSUMAASEGURADA> getAllTCSUMAASEGURADAS(@RequestBody TCSUMAASEGURADORADTO tcsumaAseguradoraDTO) {
        log.debug("REST request to get all TCSUMAASEGURADAS");
        return tCSUMAASEGURADARepository.findAll();
    }

    /**
     * {@code POST  /tcsumaaseguradas/getId} : get the "id" tCSUMAASEGURADA.
     *
     * @param id the id of the tCSUMAASEGURADA to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCSUMAASEGURADA, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tcsumaaseguradas/getId")
    public ResponseEntity<TCSUMAASEGURADA> getTCSUMAASEGURADA(@RequestBody TCSUMAASEGURADORADTO tcsumaAseguradoraDTO) {
        log.debug("REST request to get TCSUMAASEGURADA : {}",tcsumaAseguradoraDTO.getId());
        Optional<TCSUMAASEGURADA> tCSUMAASEGURADA = tCSUMAASEGURADARepository.findById(tcsumaAseguradoraDTO.getId());
        return ResponseUtil.wrapOrNotFound(tCSUMAASEGURADA);
    }

    /**
     * {@code POST  /tcsumaaseguradas/deleteId} : delete the "id" tCSUMAASEGURADA.
     *
     * @param id the id of the tCSUMAASEGURADA to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tcsumaaseguradas/deleteId")
    public ResponseEntity<Void> deleteTCSUMAASEGURADA(@RequestBody TCSUMAASEGURADORADTO tcsumaAseguradoraDTO) {
        log.debug("REST request to delete TCSUMAASEGURADA : {}", tcsumaAseguradoraDTO.getId());
        tCSUMAASEGURADARepository.deleteById(tcsumaAseguradoraDTO.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcsumaAseguradoraDTO.getId().toString()))
            .build();
    }
}
