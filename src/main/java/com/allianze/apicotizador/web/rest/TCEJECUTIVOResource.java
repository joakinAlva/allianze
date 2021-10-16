package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCEJECUTIVO;
import com.allianze.apicotizador.dto.TCEJECUTIVODTO;
import com.allianze.apicotizador.repository.TCEJECUTIVORepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCEJECUTIVO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCEJECUTIVOResource {

    private final Logger log = LoggerFactory.getLogger(TCEJECUTIVOResource.class);

    private static final String ENTITY_NAME = "tCEJECUTIVO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCEJECUTIVORepository tCEJECUTIVORepository;

    public TCEJECUTIVOResource(TCEJECUTIVORepository tCEJECUTIVORepository) {
        this.tCEJECUTIVORepository = tCEJECUTIVORepository;
    }

    /**
     * {@code POST  /tcejecutivos} : Create a new tCEJECUTIVO.
     *
     * @param tCEJECUTIVO the tCEJECUTIVO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCEJECUTIVO, or with status {@code 400 (Bad Request)} if the tCEJECUTIVO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcejecutivos/insert")
    public ResponseEntity<TCEJECUTIVO> createTCEJECUTIVO(@Valid @RequestBody TCEJECUTIVO tCEJECUTIVO) throws URISyntaxException {
        log.debug("REST request to save TCEJECUTIVO : {}", tCEJECUTIVO);
        if (tCEJECUTIVO.getIdEjecutivo() != null) {
            throw new BadRequestAlertException("A new tCEJECUTIVO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCEJECUTIVO result = tCEJECUTIVORepository.save(tCEJECUTIVO);
        return ResponseEntity
            .created(new URI("/api/tcejecutivos/" + result.getIdEjecutivo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdEjecutivo().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcejecutivos/:idEjecutivo} : Updates an existing tCEJECUTIVO.
     *
     * @param idEjecutivo the id of the tCEJECUTIVO to save.
     * @param tCEJECUTIVO the tCEJECUTIVO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCEJECUTIVO,
     * or with status {@code 400 (Bad Request)} if the tCEJECUTIVO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCEJECUTIVO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcejecutivos/{idEjecutivo}")
    public ResponseEntity<TCEJECUTIVO> updateTCEJECUTIVO(
        @PathVariable(value = "idEjecutivo", required = false) final Long idEjecutivo,
        @Valid @RequestBody TCEJECUTIVO tCEJECUTIVO
    ) throws URISyntaxException {
        log.debug("REST request to update TCEJECUTIVO : {}, {}", idEjecutivo, tCEJECUTIVO);
        if (tCEJECUTIVO.getIdEjecutivo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idEjecutivo, tCEJECUTIVO.getIdEjecutivo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCEJECUTIVORepository.existsById(idEjecutivo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCEJECUTIVO result = tCEJECUTIVORepository.save(tCEJECUTIVO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCEJECUTIVO.getIdEjecutivo().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tcejecutivos/:idEjecutivo} : Partial updates given fields of an existing tCEJECUTIVO, field will ignore if it is null
     *
     * @param idEjecutivo the id of the tCEJECUTIVO to save.
     * @param tCEJECUTIVO the tCEJECUTIVO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCEJECUTIVO,
     * or with status {@code 400 (Bad Request)} if the tCEJECUTIVO is not valid,
     * or with status {@code 404 (Not Found)} if the tCEJECUTIVO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCEJECUTIVO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcejecutivos/{idEjecutivo}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCEJECUTIVO> partialUpdateTCEJECUTIVO(
        @PathVariable(value = "idEjecutivo", required = false) final Long idEjecutivo,
        @NotNull @RequestBody TCEJECUTIVO tCEJECUTIVO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCEJECUTIVO partially : {}, {}", idEjecutivo, tCEJECUTIVO);
        if (tCEJECUTIVO.getIdEjecutivo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idEjecutivo, tCEJECUTIVO.getIdEjecutivo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCEJECUTIVORepository.existsById(idEjecutivo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCEJECUTIVO> result = tCEJECUTIVORepository
            .findById(tCEJECUTIVO.getIdEjecutivo())
            .map(
                existingTCEJECUTIVO -> {
                    if (tCEJECUTIVO.getEjecutivo() != null) {
                        existingTCEJECUTIVO.setEjecutivo(tCEJECUTIVO.getEjecutivo());
                    }

                    return existingTCEJECUTIVO;
                }
            )
            .map(tCEJECUTIVORepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCEJECUTIVO.getIdEjecutivo().toString())
        );
    }

    /**
     * {@code POST  /tcejecutivos/getAll} : get all the tCEJECUTIVOS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCEJECUTIVOS in body.
     */
    @PostMapping("/tcejecutivos/getAll")
    public List<TCEJECUTIVO> getAllTCEJECUTIVOS( @RequestBody TCEJECUTIVODTO tcEjecutivoDto) {
        log.debug("REST request to get all TCEJECUTIVOS");
        return tCEJECUTIVORepository.findAll();
    }

    /**
     * {@code POST  /tcejecutivos/getId} : get the "id" tCEJECUTIVO.
     *
     * @param id the id of the tCEJECUTIVO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCEJECUTIVO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tcejecutivos/getId")
    public ResponseEntity<TCEJECUTIVO> getTCEJECUTIVO(@RequestBody TCEJECUTIVODTO tcEjecutivoDto) {
        log.debug("REST request to get TCEJECUTIVO : {}", tcEjecutivoDto.getId());
        Optional<TCEJECUTIVO> tCEJECUTIVO = tCEJECUTIVORepository.findById(tcEjecutivoDto.getId());
        return ResponseUtil.wrapOrNotFound(tCEJECUTIVO);
    }

    /**
     * {@code POST  /tcejecutivos/deleteId} : delete the "id" tCEJECUTIVO.
     *
     * @param id the id of the tCEJECUTIVO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
     @PostMapping("/tcejecutivos/deleteId")
    public ResponseEntity<Void> deleteTCEJECUTIVO(@RequestBody TCEJECUTIVODTO tcEjecutivoDto) {
        log.debug("REST request to delete TCEJECUTIVO : {}", tcEjecutivoDto.getId());
        tCEJECUTIVORepository.deleteById(tcEjecutivoDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcEjecutivoDto.getId().toString()))
            .build();
    }
}
