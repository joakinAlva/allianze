package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCTIPO;
import com.allianze.apicotizador.dto.TCTIPODTO;
import com.allianze.apicotizador.repository.TCTIPORepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCTIPO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCTIPOResource {

    private final Logger log = LoggerFactory.getLogger(TCTIPOResource.class);

    private static final String ENTITY_NAME = "tCTIPO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCTIPORepository tCTIPORepository;

    public TCTIPOResource(TCTIPORepository tCTIPORepository) {
        this.tCTIPORepository = tCTIPORepository;
    }

    /**
     * {@code POST  /tctipos} : Create a new tCTIPO.
     *
     * @param tCTIPO the tCTIPO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCTIPO, or with status {@code 400 (Bad Request)} if the tCTIPO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    /**
     * {@code POST  /tctipos/insert} : Create a new tCTIPO.
     *
     * @param tCTIPO the tCTIPO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCTIPO, or with status {@code 400 (Bad Request)} if the tCTIPO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tctipos/insert")
    public ResponseEntity<TCTIPO> createTCTIPO(@Valid @RequestBody TCTIPO tCTIPO) throws URISyntaxException {
        log.debug("REST request to save TCTIPO : {}", tCTIPO);
        if (tCTIPO.getIdTipo() != null) {
            throw new BadRequestAlertException("A new tCTIPO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCTIPO result = tCTIPORepository.save(tCTIPO);
        return ResponseEntity
            .created(new URI("/api/tctipos/" + result.getIdTipo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdTipo().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tctipos/:idTipo} : Updates an existing tCTIPO.
     *
     * @param idTipo the id of the tCTIPO to save.
     * @param tCTIPO the tCTIPO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCTIPO,
     * or with status {@code 400 (Bad Request)} if the tCTIPO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCTIPO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tctipos/{idTipo}")
    public ResponseEntity<TCTIPO> updateTCTIPO(
        @PathVariable(value = "idTipo", required = false) final Long idTipo,
        @Valid @RequestBody TCTIPO tCTIPO
    ) throws URISyntaxException {
        log.debug("REST request to update TCTIPO : {}, {}", idTipo, tCTIPO);
        if (tCTIPO.getIdTipo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idTipo, tCTIPO.getIdTipo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCTIPORepository.existsById(idTipo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCTIPO result = tCTIPORepository.save(tCTIPO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCTIPO.getIdTipo().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tctipos/:idTipo} : Partial updates given fields of an existing tCTIPO, field will ignore if it is null
     *
     * @param idTipo the id of the tCTIPO to save.
     * @param tCTIPO the tCTIPO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCTIPO,
     * or with status {@code 400 (Bad Request)} if the tCTIPO is not valid,
     * or with status {@code 404 (Not Found)} if the tCTIPO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCTIPO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tctipos/{idTipo}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCTIPO> partialUpdateTCTIPO(
        @PathVariable(value = "idTipo", required = false) final Long idTipo,
        @NotNull @RequestBody TCTIPO tCTIPO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCTIPO partially : {}, {}", idTipo, tCTIPO);
        if (tCTIPO.getIdTipo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idTipo, tCTIPO.getIdTipo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCTIPORepository.existsById(idTipo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCTIPO> result = tCTIPORepository
            .findById(tCTIPO.getIdTipo())
            .map(
                existingTCTIPO -> {
                    if (tCTIPO.getTipoRegla() != null) {
                        existingTCTIPO.setTipoRegla(tCTIPO.getTipoRegla());
                    }

                    return existingTCTIPO;
                }
            )
            .map(tCTIPORepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCTIPO.getIdTipo().toString())
        );
    }

    /**
     * {@code POST  /tctipos/getAll} : get all the tCTIPOS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCTIPOS in body.
     */
    @PostMapping("/tctipos/getAll")
    public List<TCTIPO> getAllTCTIPOS(@RequestBody TCTIPODTO tcTipoDto) {
        log.debug("REST request to get all TCTIPOS");
        return tCTIPORepository.findAll();
    }

    /**
     * {@code POST  /tctipos/getId} : get the "id" tCTIPO.
     *
     * @param id the id of the tCTIPO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCTIPO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tctipos/getId")
    public ResponseEntity<TCTIPO> getTCTIPO( @RequestBody TCTIPODTO tcTipoDto) {
        log.debug("REST request to get TCTIPO : {}", tcTipoDto.getId());
        Optional<TCTIPO> tCTIPO = tCTIPORepository.findById(tcTipoDto.getId());
        return ResponseUtil.wrapOrNotFound(tCTIPO);
    }

    /**
     * {@code POST  /tctipos/deleteId} : delete the "id" tCTIPO.
     *
     * @param id the id of the tCTIPO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tctipos/deleteId")
    public ResponseEntity<Void> deleteTCTIPO(@RequestBody TCTIPODTO tcTipoDto) {
        log.debug("REST request to delete TCTIPO : {}", tcTipoDto.getId());
        tCTIPORepository.deleteById(tcTipoDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcTipoDto.getId().toString()))
            .build();
    }
}
