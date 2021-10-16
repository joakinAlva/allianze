package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TMASEGURADO;
import com.allianze.apicotizador.dto.TMASEGURADODTO;
import com.allianze.apicotizador.repository.TMASEGURADORepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TMASEGURADO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TMASEGURADOResource {

    private final Logger log = LoggerFactory.getLogger(TMASEGURADOResource.class);

    private static final String ENTITY_NAME = "tMASEGURADO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TMASEGURADORepository tMASEGURADORepository;

    public TMASEGURADOResource(TMASEGURADORepository tMASEGURADORepository) {
        this.tMASEGURADORepository = tMASEGURADORepository;
    }

    /**
     * {@code POST  /tmasegurados} : Create a new tMASEGURADO.
     *
     * @param tMASEGURADO the tMASEGURADO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tMASEGURADO, or with status {@code 400 (Bad Request)} if the tMASEGURADO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tmasegurados")
    public ResponseEntity<TMASEGURADO> createTMASEGURADO(@Valid @RequestBody TMASEGURADO tMASEGURADO) throws URISyntaxException {
        log.debug("REST request to save TMASEGURADO : {}", tMASEGURADO);
        if (tMASEGURADO.getIdAsegurados() != null) {
            throw new BadRequestAlertException("A new tMASEGURADO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TMASEGURADO result = tMASEGURADORepository.save(tMASEGURADO);
        return ResponseEntity
            .created(new URI("/api/tmasegurados/" + result.getIdAsegurados()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdAsegurados().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tmasegurados/:idAsegurados} : Updates an existing tMASEGURADO.
     *
     * @param idAsegurados the id of the tMASEGURADO to save.
     * @param tMASEGURADO the tMASEGURADO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tMASEGURADO,
     * or with status {@code 400 (Bad Request)} if the tMASEGURADO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tMASEGURADO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tmasegurados/{idAsegurados}")
    public ResponseEntity<TMASEGURADO> updateTMASEGURADO(
        @PathVariable(value = "idAsegurados", required = false) final Long idAsegurados,
        @Valid @RequestBody TMASEGURADO tMASEGURADO
    ) throws URISyntaxException {
        log.debug("REST request to update TMASEGURADO : {}, {}", idAsegurados, tMASEGURADO);
        if (tMASEGURADO.getIdAsegurados() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idAsegurados, tMASEGURADO.getIdAsegurados())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tMASEGURADORepository.existsById(idAsegurados)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TMASEGURADO result = tMASEGURADORepository.save(tMASEGURADO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tMASEGURADO.getIdAsegurados().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tmasegurados/:idAsegurados} : Partial updates given fields of an existing tMASEGURADO, field will ignore if it is null
     *
     * @param idAsegurados the id of the tMASEGURADO to save.
     * @param tMASEGURADO the tMASEGURADO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tMASEGURADO,
     * or with status {@code 400 (Bad Request)} if the tMASEGURADO is not valid,
     * or with status {@code 404 (Not Found)} if the tMASEGURADO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tMASEGURADO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tmasegurados/{idAsegurados}", consumes = "application/merge-patch+json")
    public ResponseEntity<TMASEGURADO> partialUpdateTMASEGURADO(
        @PathVariable(value = "idAsegurados", required = false) final Long idAsegurados,
        @NotNull @RequestBody TMASEGURADO tMASEGURADO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TMASEGURADO partially : {}, {}", idAsegurados, tMASEGURADO);
        if (tMASEGURADO.getIdAsegurados() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idAsegurados, tMASEGURADO.getIdAsegurados())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tMASEGURADORepository.existsById(idAsegurados)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TMASEGURADO> result = tMASEGURADORepository
            .findById(tMASEGURADO.getIdAsegurados())
            .map(
                existingTMASEGURADO -> {
                    if (tMASEGURADO.getNumero() != null) {
                        existingTMASEGURADO.setNumero(tMASEGURADO.getNumero());
                    }
                    if (tMASEGURADO.getExcedente() != null) {
                        existingTMASEGURADO.setExcedente(tMASEGURADO.getExcedente());
                    }
                    if (tMASEGURADO.getSubgrupo() != null) {
                        existingTMASEGURADO.setSubgrupo(tMASEGURADO.getSubgrupo());
                    }
                    if (tMASEGURADO.getNombre() != null) {
                        existingTMASEGURADO.setNombre(tMASEGURADO.getNombre());
                    }
                    if (tMASEGURADO.getfNacimiento() != null) {
                        existingTMASEGURADO.setfNacimiento(tMASEGURADO.getfNacimiento());
                    }
                    if (tMASEGURADO.getSueldo() != null) {
                        existingTMASEGURADO.setSueldo(tMASEGURADO.getSueldo());
                    }
                    if (tMASEGURADO.getReglaMonto() != null) {
                        existingTMASEGURADO.setReglaMonto(tMASEGURADO.getReglaMonto());
                    }
                    if (tMASEGURADO.getEdad() != null) {
                        existingTMASEGURADO.setEdad(tMASEGURADO.getEdad());
                    }
                    if (tMASEGURADO.getSaTotal() != null) {
                        existingTMASEGURADO.setSaTotal(tMASEGURADO.getSaTotal());
                    }
                    if (tMASEGURADO.getSaTopado() != null) {
                        existingTMASEGURADO.setSaTopado(tMASEGURADO.getSaTopado());
                    }
                    if (tMASEGURADO.getBasica() != null) {
                        existingTMASEGURADO.setBasica(tMASEGURADO.getBasica());
                    }
                    if (tMASEGURADO.getBasicaCovid() != null) {
                        existingTMASEGURADO.setBasicaCovid(tMASEGURADO.getBasicaCovid());
                    }
                    if (tMASEGURADO.getExtrabas() != null) {
                        existingTMASEGURADO.setExtrabas(tMASEGURADO.getExtrabas());
                    }
                    if (tMASEGURADO.getPrimaBasica() != null) {
                        existingTMASEGURADO.setPrimaBasica(tMASEGURADO.getPrimaBasica());
                    }
                    if (tMASEGURADO.getInvalidez() != null) {
                        existingTMASEGURADO.setInvalidez(tMASEGURADO.getInvalidez());
                    }
                    if (tMASEGURADO.getExtraInv() != null) {
                        existingTMASEGURADO.setExtraInv(tMASEGURADO.getExtraInv());
                    }
                    if (tMASEGURADO.getExencion() != null) {
                        existingTMASEGURADO.setExencion(tMASEGURADO.getExencion());
                    }
                    if (tMASEGURADO.getExtraExe() != null) {
                        existingTMASEGURADO.setExtraExe(tMASEGURADO.getExtraExe());
                    }
                    if (tMASEGURADO.getMuerteAcc() != null) {
                        existingTMASEGURADO.setMuerteAcc(tMASEGURADO.getMuerteAcc());
                    }
                    if (tMASEGURADO.getExtraAcc() != null) {
                        existingTMASEGURADO.setExtraAcc(tMASEGURADO.getExtraAcc());
                    }
                    if (tMASEGURADO.getValorGff() != null) {
                        existingTMASEGURADO.setValorGff(tMASEGURADO.getValorGff());
                    }
                    if (tMASEGURADO.getValorGffCovid() != null) {
                        existingTMASEGURADO.setValorGffCovid(tMASEGURADO.getValorGffCovid());
                    }
                    if (tMASEGURADO.getExtraGff() != null) {
                        existingTMASEGURADO.setExtraGff(tMASEGURADO.getExtraGff());
                    }
                    if (tMASEGURADO.getPrimaGff() != null) {
                        existingTMASEGURADO.setPrimaGff(tMASEGURADO.getPrimaGff());
                    }
                    if (tMASEGURADO.getPrimaCa() != null) {
                        existingTMASEGURADO.setPrimaCa(tMASEGURADO.getPrimaCa());
                    }
                    if (tMASEGURADO.getExtraCa() != null) {
                        existingTMASEGURADO.setExtraCa(tMASEGURADO.getExtraCa());
                    }
                    if (tMASEGURADO.getPrimaGe() != null) {
                        existingTMASEGURADO.setPrimaGe(tMASEGURADO.getPrimaGe());
                    }
                    if (tMASEGURADO.getExtraGe() != null) {
                        existingTMASEGURADO.setExtraGe(tMASEGURADO.getExtraGe());
                    }
                    if (tMASEGURADO.getPrimaIqs() != null) {
                        existingTMASEGURADO.setPrimaIqs(tMASEGURADO.getPrimaIqs());
                    }
                    if (tMASEGURADO.getExtraIqa() != null) {
                        existingTMASEGURADO.setExtraIqa(tMASEGURADO.getExtraIqa());
                    }
                    if (tMASEGURADO.getPrimaIqv() != null) {
                        existingTMASEGURADO.setPrimaIqv(tMASEGURADO.getPrimaIqv());
                    }
                    if (tMASEGURADO.getExtraIqv() != null) {
                        existingTMASEGURADO.setExtraIqv(tMASEGURADO.getExtraIqv());
                    }

                    return existingTMASEGURADO;
                }
            )
            .map(tMASEGURADORepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tMASEGURADO.getIdAsegurados().toString())
        );
    }

    /**
     * {@code POST  /tmasegurados/getAll} : get all the tMASEGURADOS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tMASEGURADOS in body.
     */
    @PostMapping("/tmasegurados/getAll")
    public List<TMASEGURADO> getAllTMASEGURADOS(@RequestBody TMASEGURADODTO tmaseguradoraDto) {
        log.debug("REST request to get all TMASEGURADOS");
        return tMASEGURADORepository.findAll();
    }

    /**
     * {@code POST  /tmasegurados/getId} : get the "id" tMASEGURADO.
     *
     * @param id the id of the tMASEGURADO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tMASEGURADO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tmasegurados/{id}")
    public ResponseEntity<TMASEGURADO> getTMASEGURADO(@RequestBody TMASEGURADODTO tmaseguradoraDto) {
        log.debug("REST request to get TMASEGURADO : {}", tmaseguradoraDto.getId());
        Optional<TMASEGURADO> tMASEGURADO = tMASEGURADORepository.findById(tmaseguradoraDto.getId());
        return ResponseUtil.wrapOrNotFound(tMASEGURADO);
    }

    /**
     * {@code POST  /tmasegurados/deleteId} : delete the "id" tMASEGURADO.
     *
     * @param id the id of the tMASEGURADO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tmasegurados/deleteId")
    public ResponseEntity<Void> deleteTMASEGURADO(@RequestBody TMASEGURADODTO tmaseguradoraDto) {
        log.debug("REST request to delete TMASEGURADO : {}", tmaseguradoraDto.getId());
        tMASEGURADORepository.deleteById(tmaseguradoraDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tmaseguradoraDto.getId().toString()))
            .build();
    }
}
