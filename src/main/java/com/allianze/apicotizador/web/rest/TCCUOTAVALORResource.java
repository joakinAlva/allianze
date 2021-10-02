package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCCUOTAVALOR;
import com.allianze.apicotizador.repository.TCCUOTAVALORRepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCCUOTAVALOR}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCCUOTAVALORResource {

    private final Logger log = LoggerFactory.getLogger(TCCUOTAVALORResource.class);

    private static final String ENTITY_NAME = "tCCUOTAVALOR";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCCUOTAVALORRepository tCCUOTAVALORRepository;

    public TCCUOTAVALORResource(TCCUOTAVALORRepository tCCUOTAVALORRepository) {
        this.tCCUOTAVALORRepository = tCCUOTAVALORRepository;
    }

    /**
     * {@code POST  /tccuotavalors} : Create a new tCCUOTAVALOR.
     *
     * @param tCCUOTAVALOR the tCCUOTAVALOR to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCCUOTAVALOR, or with status {@code 400 (Bad Request)} if the tCCUOTAVALOR has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tccuotavalors")
    public ResponseEntity<TCCUOTAVALOR> createTCCUOTAVALOR(@Valid @RequestBody TCCUOTAVALOR tCCUOTAVALOR) throws URISyntaxException {
        log.debug("REST request to save TCCUOTAVALOR : {}", tCCUOTAVALOR);
        if (tCCUOTAVALOR.getIdCuotaValor() != null) {
            throw new BadRequestAlertException("A new tCCUOTAVALOR cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCCUOTAVALOR result = tCCUOTAVALORRepository.save(tCCUOTAVALOR);
        return ResponseEntity
            .created(new URI("/api/tccuotavalors/" + result.getIdCuotaValor()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdCuotaValor().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tccuotavalors/:idCuotaValor} : Updates an existing tCCUOTAVALOR.
     *
     * @param idCuotaValor the id of the tCCUOTAVALOR to save.
     * @param tCCUOTAVALOR the tCCUOTAVALOR to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCUOTAVALOR,
     * or with status {@code 400 (Bad Request)} if the tCCUOTAVALOR is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCCUOTAVALOR couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tccuotavalors/{idCuotaValor}")
    public ResponseEntity<TCCUOTAVALOR> updateTCCUOTAVALOR(
        @PathVariable(value = "idCuotaValor", required = false) final Long idCuotaValor,
        @Valid @RequestBody TCCUOTAVALOR tCCUOTAVALOR
    ) throws URISyntaxException {
        log.debug("REST request to update TCCUOTAVALOR : {}, {}", idCuotaValor, tCCUOTAVALOR);
        if (tCCUOTAVALOR.getIdCuotaValor() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCuotaValor, tCCUOTAVALOR.getIdCuotaValor())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCUOTAVALORRepository.existsById(idCuotaValor)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCCUOTAVALOR result = tCCUOTAVALORRepository.save(tCCUOTAVALOR);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCUOTAVALOR.getIdCuotaValor().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tccuotavalors/:idCuotaValor} : Partial updates given fields of an existing tCCUOTAVALOR, field will ignore if it is null
     *
     * @param idCuotaValor the id of the tCCUOTAVALOR to save.
     * @param tCCUOTAVALOR the tCCUOTAVALOR to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCUOTAVALOR,
     * or with status {@code 400 (Bad Request)} if the tCCUOTAVALOR is not valid,
     * or with status {@code 404 (Not Found)} if the tCCUOTAVALOR is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCCUOTAVALOR couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tccuotavalors/{idCuotaValor}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCCUOTAVALOR> partialUpdateTCCUOTAVALOR(
        @PathVariable(value = "idCuotaValor", required = false) final Long idCuotaValor,
        @NotNull @RequestBody TCCUOTAVALOR tCCUOTAVALOR
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCCUOTAVALOR partially : {}, {}", idCuotaValor, tCCUOTAVALOR);
        if (tCCUOTAVALOR.getIdCuotaValor() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCuotaValor, tCCUOTAVALOR.getIdCuotaValor())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCUOTAVALORRepository.existsById(idCuotaValor)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCCUOTAVALOR> result = tCCUOTAVALORRepository
            .findById(tCCUOTAVALOR.getIdCuotaValor())
            .map(
                existingTCCUOTAVALOR -> {
                    if (tCCUOTAVALOR.getPorcentaje() != null) {
                        existingTCCUOTAVALOR.setPorcentaje(tCCUOTAVALOR.getPorcentaje());
                    }
                    if (tCCUOTAVALOR.getValor() != null) {
                        existingTCCUOTAVALOR.setValor(tCCUOTAVALOR.getValor());
                    }

                    return existingTCCUOTAVALOR;
                }
            )
            .map(tCCUOTAVALORRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCUOTAVALOR.getIdCuotaValor().toString())
        );
    }

    /**
     * {@code GET  /tccuotavalors} : get all the tCCUOTAVALORS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCCUOTAVALORS in body.
     */
    @GetMapping("/tccuotavalors")
    public List<TCCUOTAVALOR> getAllTCCUOTAVALORS() {
        log.debug("REST request to get all TCCUOTAVALORS");
        return tCCUOTAVALORRepository.findAll();
    }

    /**
     * {@code GET  /tccuotavalors/:id} : get the "id" tCCUOTAVALOR.
     *
     * @param id the id of the tCCUOTAVALOR to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCCUOTAVALOR, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tccuotavalors/{id}")
    public ResponseEntity<TCCUOTAVALOR> getTCCUOTAVALOR(@PathVariable Long id) {
        log.debug("REST request to get TCCUOTAVALOR : {}", id);
        Optional<TCCUOTAVALOR> tCCUOTAVALOR = tCCUOTAVALORRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tCCUOTAVALOR);
    }

    /**
     * {@code DELETE  /tccuotavalors/:id} : delete the "id" tCCUOTAVALOR.
     *
     * @param id the id of the tCCUOTAVALOR to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tccuotavalors/{id}")
    public ResponseEntity<Void> deleteTCCUOTAVALOR(@PathVariable Long id) {
        log.debug("REST request to delete TCCUOTAVALOR : {}", id);
        tCCUOTAVALORRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
