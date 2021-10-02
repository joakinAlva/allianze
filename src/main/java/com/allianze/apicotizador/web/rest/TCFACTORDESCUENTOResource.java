package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCFACTORDESCUENTO;
import com.allianze.apicotizador.dto.TCFACTORDESCUENTORDTO;
import com.allianze.apicotizador.repository.TCFACTORDESCUENTORepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCFACTORDESCUENTO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCFACTORDESCUENTOResource {

    private final Logger log = LoggerFactory.getLogger(TCFACTORDESCUENTOResource.class);

    private static final String ENTITY_NAME = "tCFACTORDESCUENTO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCFACTORDESCUENTORepository tCFACTORDESCUENTORepository;

    public TCFACTORDESCUENTOResource(TCFACTORDESCUENTORepository tCFACTORDESCUENTORepository) {
        this.tCFACTORDESCUENTORepository = tCFACTORDESCUENTORepository;
    }

    /**
     * {@code POST  /tcfactordescuentos} : Create a new tCFACTORDESCUENTO.
     *
     * @param tCFACTORDESCUENTO the tCFACTORDESCUENTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCFACTORDESCUENTO, or with status {@code 400 (Bad Request)} if the tCFACTORDESCUENTO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcfactordescuentos")
    public ResponseEntity<TCFACTORDESCUENTO> createTCFACTORDESCUENTO(@Valid @RequestBody TCFACTORDESCUENTO tCFACTORDESCUENTO)
        throws URISyntaxException {
        log.debug("REST request to save TCFACTORDESCUENTO : {}", tCFACTORDESCUENTO);
        if (tCFACTORDESCUENTO.getIdFactorDescuento() != null) {
            throw new BadRequestAlertException("A new tCFACTORDESCUENTO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCFACTORDESCUENTO result = tCFACTORDESCUENTORepository.save(tCFACTORDESCUENTO);
        return ResponseEntity
            .created(new URI("/api/tcfactordescuentos/" + result.getIdFactorDescuento()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdFactorDescuento().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcfactordescuentos/:idFactorDescuento} : Updates an existing tCFACTORDESCUENTO.
     *
     * @param idFactorDescuento the id of the tCFACTORDESCUENTO to save.
     * @param tCFACTORDESCUENTO the tCFACTORDESCUENTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCFACTORDESCUENTO,
     * or with status {@code 400 (Bad Request)} if the tCFACTORDESCUENTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCFACTORDESCUENTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcfactordescuentos/{idFactorDescuento}")
    public ResponseEntity<TCFACTORDESCUENTO> updateTCFACTORDESCUENTO(
        @PathVariable(value = "idFactorDescuento", required = false) final Long idFactorDescuento,
        @Valid @RequestBody TCFACTORDESCUENTO tCFACTORDESCUENTO
    ) throws URISyntaxException {
        log.debug("REST request to update TCFACTORDESCUENTO : {}, {}", idFactorDescuento, tCFACTORDESCUENTO);
        if (tCFACTORDESCUENTO.getIdFactorDescuento() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idFactorDescuento, tCFACTORDESCUENTO.getIdFactorDescuento())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCFACTORDESCUENTORepository.existsById(idFactorDescuento)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCFACTORDESCUENTO result = tCFACTORDESCUENTORepository.save(tCFACTORDESCUENTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCFACTORDESCUENTO.getIdFactorDescuento().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tcfactordescuentos/:idFactorDescuento} : Partial updates given fields of an existing tCFACTORDESCUENTO, field will ignore if it is null
     *
     * @param idFactorDescuento the id of the tCFACTORDESCUENTO to save.
     * @param tCFACTORDESCUENTO the tCFACTORDESCUENTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCFACTORDESCUENTO,
     * or with status {@code 400 (Bad Request)} if the tCFACTORDESCUENTO is not valid,
     * or with status {@code 404 (Not Found)} if the tCFACTORDESCUENTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCFACTORDESCUENTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcfactordescuentos/{idFactorDescuento}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCFACTORDESCUENTO> partialUpdateTCFACTORDESCUENTO(
        @PathVariable(value = "idFactorDescuento", required = false) final Long idFactorDescuento,
        @NotNull @RequestBody TCFACTORDESCUENTO tCFACTORDESCUENTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCFACTORDESCUENTO partially : {}, {}", idFactorDescuento, tCFACTORDESCUENTO);
        if (tCFACTORDESCUENTO.getIdFactorDescuento() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idFactorDescuento, tCFACTORDESCUENTO.getIdFactorDescuento())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCFACTORDESCUENTORepository.existsById(idFactorDescuento)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCFACTORDESCUENTO> result = tCFACTORDESCUENTORepository
            .findById(tCFACTORDESCUENTO.getIdFactorDescuento())
            .map(
                existingTCFACTORDESCUENTO -> {
                    if (tCFACTORDESCUENTO.getFactor() != null) {
                        existingTCFACTORDESCUENTO.setFactor(tCFACTORDESCUENTO.getFactor());
                    }
                    if (tCFACTORDESCUENTO.getPorcentaje() != null) {
                        existingTCFACTORDESCUENTO.setPorcentaje(tCFACTORDESCUENTO.getPorcentaje());
                    }

                    return existingTCFACTORDESCUENTO;
                }
            )
            .map(tCFACTORDESCUENTORepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCFACTORDESCUENTO.getIdFactorDescuento().toString())
        );
    }

    /**
     * {@code POST  /tcfactordescuentos/getAll} : get all the tCFACTORDESCUENTOS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCFACTORDESCUENTOS in body.
     */
    @PostMapping("/tcfactordescuentos/getAll")
    public List<TCFACTORDESCUENTO> getAllTCFACTORDESCUENTOS(@RequestBody TCFACTORDESCUENTORDTO tcfactorDescuentoDto) {
        log.debug("REST request to get all TCFACTORDESCUENTOS");
        return tCFACTORDESCUENTORepository.findAll();
    }

    /**
     * {@code POST  /tcfactordescuentos/getId} : get the "id" tCFACTORDESCUENTO.
     *
     * @param id the id of the tCFACTORDESCUENTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCFACTORDESCUENTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tcfactordescuentos/getId")
    public ResponseEntity<TCFACTORDESCUENTO> getTCFACTORDESCUENTO(@RequestBody TCFACTORDESCUENTORDTO tcfactorDescuentoDto) {
        log.debug("REST request to get TCFACTORDESCUENTO : {}", tcfactorDescuentoDto.getId());
        Optional<TCFACTORDESCUENTO> tCFACTORDESCUENTO = tCFACTORDESCUENTORepository.findById(tcfactorDescuentoDto.getId());
        return ResponseUtil.wrapOrNotFound(tCFACTORDESCUENTO);
    }

    /**
     * {@code POST  /tcfactordescuentos/deleteId} : delete the "id" tCFACTORDESCUENTO.
     *
     * @param id the id of the tCFACTORDESCUENTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tcfactordescuentos/deleteId")
    public ResponseEntity<Void> deleteTCFACTORDESCUENTO(@RequestBody TCFACTORDESCUENTORDTO tcfactorDescuentoDto) {
        log.debug("REST request to delete TCFACTORDESCUENTO : {}", tcfactorDescuentoDto.getId());
        tCFACTORDESCUENTORepository.deleteById(tcfactorDescuentoDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcfactorDescuentoDto.getClass().toString()))
            .build();
    }
}
