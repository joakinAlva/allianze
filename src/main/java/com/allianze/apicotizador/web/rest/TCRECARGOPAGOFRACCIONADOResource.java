package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCRECARGOPAGOFRACCIONADO;
import com.allianze.apicotizador.dto.TCRECARGOPAGOFRACCIONADORDTO;
import com.allianze.apicotizador.repository.TCRECARGOPAGOFRACCIONADORepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCRECARGOPAGOFRACCIONADO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCRECARGOPAGOFRACCIONADOResource {

    private final Logger log = LoggerFactory.getLogger(TCRECARGOPAGOFRACCIONADOResource.class);

    private static final String ENTITY_NAME = "tCRECARGOPAGOFRACCIONADO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCRECARGOPAGOFRACCIONADORepository tCRECARGOPAGOFRACCIONADORepository;

    public TCRECARGOPAGOFRACCIONADOResource(TCRECARGOPAGOFRACCIONADORepository tCRECARGOPAGOFRACCIONADORepository) {
        this.tCRECARGOPAGOFRACCIONADORepository = tCRECARGOPAGOFRACCIONADORepository;
    }

    /**
     * {@code POST  /tcrecargopagofraccionados} : Create a new tCRECARGOPAGOFRACCIONADO.
     *
     * @param tCRECARGOPAGOFRACCIONADO the tCRECARGOPAGOFRACCIONADO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCRECARGOPAGOFRACCIONADO, or with status {@code 400 (Bad Request)} if the tCRECARGOPAGOFRACCIONADO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcrecargopagofraccionados")
    public ResponseEntity<TCRECARGOPAGOFRACCIONADO> createTCRECARGOPAGOFRACCIONADO(
        @Valid @RequestBody TCRECARGOPAGOFRACCIONADO tCRECARGOPAGOFRACCIONADO
    ) throws URISyntaxException {
        log.debug("REST request to save TCRECARGOPAGOFRACCIONADO : {}", tCRECARGOPAGOFRACCIONADO);
        if (tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado() != null) {
            throw new BadRequestAlertException("A new tCRECARGOPAGOFRACCIONADO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCRECARGOPAGOFRACCIONADO result = tCRECARGOPAGOFRACCIONADORepository.save(tCRECARGOPAGOFRACCIONADO);
        return ResponseEntity
            .created(new URI("/api/tcrecargopagofraccionados/" + result.getIdRecargoPagoFraccionado()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdRecargoPagoFraccionado().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /tcrecargopagofraccionados/:idRecargoPagoFraccionado} : Updates an existing tCRECARGOPAGOFRACCIONADO.
     *
     * @param idRecargoPagoFraccionado the id of the tCRECARGOPAGOFRACCIONADO to save.
     * @param tCRECARGOPAGOFRACCIONADO the tCRECARGOPAGOFRACCIONADO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCRECARGOPAGOFRACCIONADO,
     * or with status {@code 400 (Bad Request)} if the tCRECARGOPAGOFRACCIONADO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCRECARGOPAGOFRACCIONADO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcrecargopagofraccionados/{idRecargoPagoFraccionado}")
    public ResponseEntity<TCRECARGOPAGOFRACCIONADO> updateTCRECARGOPAGOFRACCIONADO(
        @PathVariable(value = "idRecargoPagoFraccionado", required = false) final Long idRecargoPagoFraccionado,
        @Valid @RequestBody TCRECARGOPAGOFRACCIONADO tCRECARGOPAGOFRACCIONADO
    ) throws URISyntaxException {
        log.debug("REST request to update TCRECARGOPAGOFRACCIONADO : {}, {}", idRecargoPagoFraccionado, tCRECARGOPAGOFRACCIONADO);
        if (tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idRecargoPagoFraccionado, tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCRECARGOPAGOFRACCIONADORepository.existsById(idRecargoPagoFraccionado)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCRECARGOPAGOFRACCIONADO result = tCRECARGOPAGOFRACCIONADORepository.save(tCRECARGOPAGOFRACCIONADO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tcrecargopagofraccionados/:idRecargoPagoFraccionado} : Partial updates given fields of an existing tCRECARGOPAGOFRACCIONADO, field will ignore if it is null
     *
     * @param idRecargoPagoFraccionado the id of the tCRECARGOPAGOFRACCIONADO to save.
     * @param tCRECARGOPAGOFRACCIONADO the tCRECARGOPAGOFRACCIONADO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCRECARGOPAGOFRACCIONADO,
     * or with status {@code 400 (Bad Request)} if the tCRECARGOPAGOFRACCIONADO is not valid,
     * or with status {@code 404 (Not Found)} if the tCRECARGOPAGOFRACCIONADO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCRECARGOPAGOFRACCIONADO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcrecargopagofraccionados/{idRecargoPagoFraccionado}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCRECARGOPAGOFRACCIONADO> partialUpdateTCRECARGOPAGOFRACCIONADO(
        @PathVariable(value = "idRecargoPagoFraccionado", required = false) final Long idRecargoPagoFraccionado,
        @NotNull @RequestBody TCRECARGOPAGOFRACCIONADO tCRECARGOPAGOFRACCIONADO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update TCRECARGOPAGOFRACCIONADO partially : {}, {}",
            idRecargoPagoFraccionado,
            tCRECARGOPAGOFRACCIONADO
        );
        if (tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idRecargoPagoFraccionado, tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCRECARGOPAGOFRACCIONADORepository.existsById(idRecargoPagoFraccionado)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCRECARGOPAGOFRACCIONADO> result = tCRECARGOPAGOFRACCIONADORepository
            .findById(tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado())
            .map(
                existingTCRECARGOPAGOFRACCIONADO -> {
                    if (tCRECARGOPAGOFRACCIONADO.getPeriodo() != null) {
                        existingTCRECARGOPAGOFRACCIONADO.setPeriodo(tCRECARGOPAGOFRACCIONADO.getPeriodo());
                    }
                    if (tCRECARGOPAGOFRACCIONADO.getPorcentaje() != null) {
                        existingTCRECARGOPAGOFRACCIONADO.setPorcentaje(tCRECARGOPAGOFRACCIONADO.getPorcentaje());
                    }

                    return existingTCRECARGOPAGOFRACCIONADO;
                }
            )
            .map(tCRECARGOPAGOFRACCIONADORepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado().toString()
            )
        );
    }

    /**
     * {@code POST  /tcrecargopagofraccionados/getAll} : get all the tCRECARGOPAGOFRACCIONADOS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCRECARGOPAGOFRACCIONADOS in body.
     */
    @GetMapping("/tcrecargopagofraccionados")
    public List<TCRECARGOPAGOFRACCIONADO> getAllTCRECARGOPAGOFRACCIONADOS(
        @RequestBody TCRECARGOPAGOFRACCIONADORDTO tcrecargoPagoFraccionadorDto
    ) {
        log.debug("REST request to get all TCRECARGOPAGOFRACCIONADOS");
        return tCRECARGOPAGOFRACCIONADORepository.findAll();
    }

    /**
     * {@code POST  /tcrecargopagofraccionados/g} : get the "id" tCRECARGOPAGOFRACCIONADO.
     *
     * @param id the id of the tCRECARGOPAGOFRACCIONADO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCRECARGOPAGOFRACCIONADO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tcrecargopagofraccionados/getId")
    public ResponseEntity<TCRECARGOPAGOFRACCIONADO> getTCRECARGOPAGOFRACCIONADO(
        @RequestBody TCRECARGOPAGOFRACCIONADORDTO tcrecargoPagoFraccionadorDto
    ) {
        log.debug("REST request to get TCRECARGOPAGOFRACCIONADO : {}", tcrecargoPagoFraccionadorDto.getId());
        Optional<TCRECARGOPAGOFRACCIONADO> tCRECARGOPAGOFRACCIONADO = tCRECARGOPAGOFRACCIONADORepository.findById(
            tcrecargoPagoFraccionadorDto.getId()
        );
        return ResponseUtil.wrapOrNotFound(tCRECARGOPAGOFRACCIONADO);
    }

    /**
     * {@code POST  /tcrecargopagofraccionados/deleteId} : delete the "id" tCRECARGOPAGOFRACCIONADO.
     *
     * @param id the id of the tCRECARGOPAGOFRACCIONADO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tcrecargopagofraccionados/deleteId")
    public ResponseEntity<Void> deleteTCRECARGOPAGOFRACCIONADO(@RequestBody TCRECARGOPAGOFRACCIONADORDTO tcrecargoPagoFraccionadorDto) {
        log.debug("REST request to delete TCRECARGOPAGOFRACCIONADO : {}", tcrecargoPagoFraccionadorDto.getId());
        tCRECARGOPAGOFRACCIONADORepository.deleteById(tcrecargoPagoFraccionadorDto.getId());
        return ResponseEntity
            .noContent()
            .headers(
                HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcrecargoPagoFraccionadorDto.getId().toString())
            )
            .build();
    }
}
