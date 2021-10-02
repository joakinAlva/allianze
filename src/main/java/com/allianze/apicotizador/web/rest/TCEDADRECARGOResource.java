package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCEDADRECARGO;
import com.allianze.apicotizador.dto.TCEDADRECARGODTO;
import com.allianze.apicotizador.repository.TCEDADRECARGORepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCEDADRECARGO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCEDADRECARGOResource {

    private final Logger log = LoggerFactory.getLogger(TCEDADRECARGOResource.class);

    private static final String ENTITY_NAME = "tCEDADRECARGO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCEDADRECARGORepository tCEDADRECARGORepository;

    public TCEDADRECARGOResource(TCEDADRECARGORepository tCEDADRECARGORepository) {
        this.tCEDADRECARGORepository = tCEDADRECARGORepository;
    }

    /**
     * {@code POST  /tcedadrecargos} : Create a new tCEDADRECARGO.
     *
     * @param tCEDADRECARGO the tCEDADRECARGO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCEDADRECARGO, or with status {@code 400 (Bad Request)} if the tCEDADRECARGO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcedadrecargos")
    public ResponseEntity<TCEDADRECARGO> createTCEDADRECARGO(@Valid @RequestBody TCEDADRECARGO tCEDADRECARGO) throws URISyntaxException {
        log.debug("REST request to save TCEDADRECARGO : {}", tCEDADRECARGO);
        if (tCEDADRECARGO.getIdEdadRecargo() != null) {
            throw new BadRequestAlertException("A new tCEDADRECARGO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCEDADRECARGO result = tCEDADRECARGORepository.save(tCEDADRECARGO);
        return ResponseEntity
            .created(new URI("/api/tcedadrecargos/" + result.getIdEdadRecargo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdEdadRecargo().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcedadrecargos/:idEdadRecargo} : Updates an existing tCEDADRECARGO.
     *
     * @param idEdadRecargo the id of the tCEDADRECARGO to save.
     * @param tCEDADRECARGO the tCEDADRECARGO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCEDADRECARGO,
     * or with status {@code 400 (Bad Request)} if the tCEDADRECARGO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCEDADRECARGO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcedadrecargos/{idEdadRecargo}")
    public ResponseEntity<TCEDADRECARGO> updateTCEDADRECARGO(
        @PathVariable(value = "idEdadRecargo", required = false) final Long idEdadRecargo,
        @Valid @RequestBody TCEDADRECARGO tCEDADRECARGO
    ) throws URISyntaxException {
        log.debug("REST request to update TCEDADRECARGO : {}, {}", idEdadRecargo, tCEDADRECARGO);
        if (tCEDADRECARGO.getIdEdadRecargo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idEdadRecargo, tCEDADRECARGO.getIdEdadRecargo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCEDADRECARGORepository.existsById(idEdadRecargo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCEDADRECARGO result = tCEDADRECARGORepository.save(tCEDADRECARGO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCEDADRECARGO.getIdEdadRecargo().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tcedadrecargos/:idEdadRecargo} : Partial updates given fields of an existing tCEDADRECARGO, field will ignore if it is null
     *
     * @param idEdadRecargo the id of the tCEDADRECARGO to save.
     * @param tCEDADRECARGO the tCEDADRECARGO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCEDADRECARGO,
     * or with status {@code 400 (Bad Request)} if the tCEDADRECARGO is not valid,
     * or with status {@code 404 (Not Found)} if the tCEDADRECARGO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCEDADRECARGO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcedadrecargos/{idEdadRecargo}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCEDADRECARGO> partialUpdateTCEDADRECARGO(
        @PathVariable(value = "idEdadRecargo", required = false) final Long idEdadRecargo,
        @NotNull @RequestBody TCEDADRECARGO tCEDADRECARGO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCEDADRECARGO partially : {}, {}", idEdadRecargo, tCEDADRECARGO);
        if (tCEDADRECARGO.getIdEdadRecargo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idEdadRecargo, tCEDADRECARGO.getIdEdadRecargo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCEDADRECARGORepository.existsById(idEdadRecargo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCEDADRECARGO> result = tCEDADRECARGORepository
            .findById(tCEDADRECARGO.getIdEdadRecargo())
            .map(
                existingTCEDADRECARGO -> {
                    if (tCEDADRECARGO.getEdadMin() != null) {
                        existingTCEDADRECARGO.setEdadMin(tCEDADRECARGO.getEdadMin());
                    }
                    if (tCEDADRECARGO.getEdadMax() != null) {
                        existingTCEDADRECARGO.setEdadMax(tCEDADRECARGO.getEdadMax());
                    }
                    if (tCEDADRECARGO.getRecargoAnterior() != null) {
                        existingTCEDADRECARGO.setRecargoAnterior(tCEDADRECARGO.getRecargoAnterior());
                    }
                    if (tCEDADRECARGO.getRecargoActual() != null) {
                        existingTCEDADRECARGO.setRecargoActual(tCEDADRECARGO.getRecargoActual());
                    }

                    return existingTCEDADRECARGO;
                }
            )
            .map(tCEDADRECARGORepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCEDADRECARGO.getIdEdadRecargo().toString())
        );
    }

    /**
     * {@code POST  /tcedadrecargos/getAll} : get all the tCEDADRECARGOS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCEDADRECARGOS in body.
     */
    @PostMapping("/tcedadrecargos/getAll")
    public List<TCEDADRECARGO> getAllTCEDADRECARGOS(@RequestBody TCEDADRECARGODTO tcedadRecargoDto) {
        log.debug("REST request to get all TCEDADRECARGOS");
        return tCEDADRECARGORepository.findAll();
    }

    /**
     * {@code POST  /tcedadrecargos/getId} : get the "id" tCEDADRECARGO.
     *
     * @param id the id of the tCEDADRECARGO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCEDADRECARGO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tcedadrecargos/getId")
    public ResponseEntity<TCEDADRECARGO> getTCEDADRECARGO(@RequestBody TCEDADRECARGODTO tcedadRecargoDto) {
        log.debug("REST request to get TCEDADRECARGO : {}", tcedadRecargoDto.getId());
        Optional<TCEDADRECARGO> tCEDADRECARGO = tCEDADRECARGORepository.findById(tcedadRecargoDto.getId());
        return ResponseUtil.wrapOrNotFound(tCEDADRECARGO);
    }

    /**
     * {@code POST  /tcedadrecargos/deleteId} : delete the "id" tCEDADRECARGO.
     *
     * @param id the id of the tCEDADRECARGO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tcedadrecargos/deleteId")
    public ResponseEntity<Void> deleteTCEDADRECARGO(@RequestBody TCEDADRECARGODTO tcedadRecargoDto) {
        log.debug("REST request to delete TCEDADRECARGO : {}", tcedadRecargoDto.getId());
        tCEDADRECARGORepository.deleteById(tcedadRecargoDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcedadRecargoDto.getId().toString()))
            .build();
    }
}
