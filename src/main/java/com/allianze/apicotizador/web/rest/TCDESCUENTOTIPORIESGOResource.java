package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCDESCUENTOTIPORIESGO;
import com.allianze.apicotizador.dto.TCDESCUENTOTIPORIESGODTO;
import com.allianze.apicotizador.repository.TCDESCUENTOTIPORIESGORepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCDESCUENTOTIPORIESGO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCDESCUENTOTIPORIESGOResource {

    private final Logger log = LoggerFactory.getLogger(TCDESCUENTOTIPORIESGOResource.class);

    private static final String ENTITY_NAME = "tCDESCUENTOTIPORIESGO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCDESCUENTOTIPORIESGORepository tCDESCUENTOTIPORIESGORepository;

    public TCDESCUENTOTIPORIESGOResource(TCDESCUENTOTIPORIESGORepository tCDESCUENTOTIPORIESGORepository) {
        this.tCDESCUENTOTIPORIESGORepository = tCDESCUENTOTIPORIESGORepository;
    }

    /**
     * {@code POST  /tcdescuentotiporiesgos} : Create a new tCDESCUENTOTIPORIESGO.
     *
     * @param tCDESCUENTOTIPORIESGO the tCDESCUENTOTIPORIESGO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCDESCUENTOTIPORIESGO, or with status {@code 400 (Bad Request)} if the tCDESCUENTOTIPORIESGO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcdescuentotiporiesgos/insert")
    public ResponseEntity<TCDESCUENTOTIPORIESGO> createTCDESCUENTOTIPORIESGO(
        @Valid @RequestBody TCDESCUENTOTIPORIESGO tCDESCUENTOTIPORIESGO
    ) throws URISyntaxException {
        log.debug("REST request to save TCDESCUENTOTIPORIESGO : {}", tCDESCUENTOTIPORIESGO);
        if (tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo() != null) {
            throw new BadRequestAlertException("A new tCDESCUENTOTIPORIESGO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCDESCUENTOTIPORIESGO result = tCDESCUENTOTIPORIESGORepository.save(tCDESCUENTOTIPORIESGO);
        return ResponseEntity
            .created(new URI("/api/tcdescuentotiporiesgos/" + result.getIdDescuentoTipoRiesgo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdDescuentoTipoRiesgo().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcdescuentotiporiesgos/:idDescuentoTipoRiesgo} : Updates an existing tCDESCUENTOTIPORIESGO.
     *
     * @param idDescuentoTipoRiesgo the id of the tCDESCUENTOTIPORIESGO to save.
     * @param tCDESCUENTOTIPORIESGO the tCDESCUENTOTIPORIESGO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCDESCUENTOTIPORIESGO,
     * or with status {@code 400 (Bad Request)} if the tCDESCUENTOTIPORIESGO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCDESCUENTOTIPORIESGO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcdescuentotiporiesgos/{idDescuentoTipoRiesgo}")
    public ResponseEntity<TCDESCUENTOTIPORIESGO> updateTCDESCUENTOTIPORIESGO(
        @PathVariable(value = "idDescuentoTipoRiesgo", required = false) final Long idDescuentoTipoRiesgo,
        @Valid @RequestBody TCDESCUENTOTIPORIESGO tCDESCUENTOTIPORIESGO
    ) throws URISyntaxException {
        log.debug("REST request to update TCDESCUENTOTIPORIESGO : {}, {}", idDescuentoTipoRiesgo, tCDESCUENTOTIPORIESGO);
        if (tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idDescuentoTipoRiesgo, tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCDESCUENTOTIPORIESGORepository.existsById(idDescuentoTipoRiesgo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCDESCUENTOTIPORIESGO result = tCDESCUENTOTIPORIESGORepository.save(tCDESCUENTOTIPORIESGO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tcdescuentotiporiesgos/:idDescuentoTipoRiesgo} : Partial updates given fields of an existing tCDESCUENTOTIPORIESGO, field will ignore if it is null
     *
     * @param idDescuentoTipoRiesgo the id of the tCDESCUENTOTIPORIESGO to save.
     * @param tCDESCUENTOTIPORIESGO the tCDESCUENTOTIPORIESGO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCDESCUENTOTIPORIESGO,
     * or with status {@code 400 (Bad Request)} if the tCDESCUENTOTIPORIESGO is not valid,
     * or with status {@code 404 (Not Found)} if the tCDESCUENTOTIPORIESGO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCDESCUENTOTIPORIESGO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcdescuentotiporiesgos/{idDescuentoTipoRiesgo}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCDESCUENTOTIPORIESGO> partialUpdateTCDESCUENTOTIPORIESGO(
        @PathVariable(value = "idDescuentoTipoRiesgo", required = false) final Long idDescuentoTipoRiesgo,
        @NotNull @RequestBody TCDESCUENTOTIPORIESGO tCDESCUENTOTIPORIESGO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCDESCUENTOTIPORIESGO partially : {}, {}", idDescuentoTipoRiesgo, tCDESCUENTOTIPORIESGO);
        if (tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idDescuentoTipoRiesgo, tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCDESCUENTOTIPORIESGORepository.existsById(idDescuentoTipoRiesgo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCDESCUENTOTIPORIESGO> result = tCDESCUENTOTIPORIESGORepository
            .findById(tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo())
            .map(
                existingTCDESCUENTOTIPORIESGO -> {
                    if (tCDESCUENTOTIPORIESGO.getTipo() != null) {
                        existingTCDESCUENTOTIPORIESGO.setTipo(tCDESCUENTOTIPORIESGO.getTipo());
                    }
                    if (tCDESCUENTOTIPORIESGO.getDescuento() != null) {
                        existingTCDESCUENTOTIPORIESGO.setDescuento(tCDESCUENTOTIPORIESGO.getDescuento());
                    }

                    return existingTCDESCUENTOTIPORIESGO;
                }
            )
            .map(tCDESCUENTOTIPORIESGORepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo().toString()
            )
        );
    }

    /**
     * {@code POST  /tcdescuentotiporiesgos/getAll} : get all the tCDESCUENTOTIPORIESGOS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCDESCUENTOTIPORIESGOS in body.
     */
    @PostMapping("/tcdescuentotiporiesgos/getAll")
    public List<TCDESCUENTOTIPORIESGO> getAllTCDESCUENTOTIPORIESGOS() {
        log.debug("REST request to get all TCDESCUENTOTIPORIESGOS");
        return tCDESCUENTOTIPORIESGORepository.findAll();
    }

    /**
     * {@code POST  /tcdescuentotiporiesgos/getId} : get the "id" tCDESCUENTOTIPORIESGO.
     *
     * @param id the id of the tCDESCUENTOTIPORIESGO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCDESCUENTOTIPORIESGO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tcdescuentotiporiesgos/getId")
    public ResponseEntity<TCDESCUENTOTIPORIESGO> getTCDESCUENTOTIPORIESGO(@RequestBody TCDESCUENTOTIPORIESGODTO tcDescuentoTipoRiesgoDto) {
        log.debug("REST request to get TCDESCUENTOTIPORIESGO : {}", tcDescuentoTipoRiesgoDto.getId());
        Optional<TCDESCUENTOTIPORIESGO> tCDESCUENTOTIPORIESGO = tCDESCUENTOTIPORIESGORepository.findById(tcDescuentoTipoRiesgoDto.getId());
        return ResponseUtil.wrapOrNotFound(tCDESCUENTOTIPORIESGO);
    }

    /**
     * {@code POST  /tcdescuentotiporiesgos/deleteId} : delete the "id" tCDESCUENTOTIPORIESGO.
     *
     * @param id the id of the tCDESCUENTOTIPORIESGO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tcdescuentotiporiesgos/deleteId")
    public ResponseEntity<Void> deleteTCDESCUENTOTIPORIESGO(@RequestBody TCDESCUENTOTIPORIESGODTO tcDescuentoTipoRiesgoDto) {
        log.debug("REST request to delete TCDESCUENTOTIPORIESGO : {}", tcDescuentoTipoRiesgoDto.getId());
        tCDESCUENTOTIPORIESGORepository.deleteById(tcDescuentoTipoRiesgoDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcDescuentoTipoRiesgoDto.getId().toString()))
            .build();
    }
}
