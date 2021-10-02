package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCREGIONAL;
import com.allianze.apicotizador.dto.TCREGIONALDTO;
import com.allianze.apicotizador.repository.TCREGIONALRepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCREGIONAL}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCREGIONALResource {

    private final Logger log = LoggerFactory.getLogger(TCREGIONALResource.class);

    private static final String ENTITY_NAME = "tCREGIONAL";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCREGIONALRepository tCREGIONALRepository;

    public TCREGIONALResource(TCREGIONALRepository tCREGIONALRepository) {
        this.tCREGIONALRepository = tCREGIONALRepository;
    }

    /**
     * {@code POST  /tcregionals} : Create a new tCREGIONAL.
     *
     * @param tCREGIONAL the tCREGIONAL to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCREGIONAL, or with status {@code 400 (Bad Request)} if the tCREGIONAL has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcregionals")
    public ResponseEntity<TCREGIONAL> createTCREGIONAL(@Valid @RequestBody TCREGIONAL tCREGIONAL) throws URISyntaxException {
        log.debug("REST request to save TCREGIONAL : {}", tCREGIONAL);
        if (tCREGIONAL.getIdRegional() != null) {
            throw new BadRequestAlertException("A new tCREGIONAL cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCREGIONAL result = tCREGIONALRepository.save(tCREGIONAL);
        return ResponseEntity
            .created(new URI("/api/tcregionals/" + result.getIdRegional()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdRegional().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcregionals/:idRegional} : Updates an existing tCREGIONAL.
     *
     * @param idRegional the id of the tCREGIONAL to save.
     * @param tCREGIONAL the tCREGIONAL to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCREGIONAL,
     * or with status {@code 400 (Bad Request)} if the tCREGIONAL is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCREGIONAL couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcregionals/{idRegional}")
    public ResponseEntity<TCREGIONAL> updateTCREGIONAL(
        @PathVariable(value = "idRegional", required = false) final Long idRegional,
        @Valid @RequestBody TCREGIONAL tCREGIONAL
    ) throws URISyntaxException {
        log.debug("REST request to update TCREGIONAL : {}, {}", idRegional, tCREGIONAL);
        if (tCREGIONAL.getIdRegional() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idRegional, tCREGIONAL.getIdRegional())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCREGIONALRepository.existsById(idRegional)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCREGIONAL result = tCREGIONALRepository.save(tCREGIONAL);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCREGIONAL.getIdRegional().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tcregionals/:idRegional} : Partial updates given fields of an existing tCREGIONAL, field will ignore if it is null
     *
     * @param idRegional the id of the tCREGIONAL to save.
     * @param tCREGIONAL the tCREGIONAL to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCREGIONAL,
     * or with status {@code 400 (Bad Request)} if the tCREGIONAL is not valid,
     * or with status {@code 404 (Not Found)} if the tCREGIONAL is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCREGIONAL couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcregionals/{idRegional}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCREGIONAL> partialUpdateTCREGIONAL(
        @PathVariable(value = "idRegional", required = false) final Long idRegional,
        @NotNull @RequestBody TCREGIONAL tCREGIONAL
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCREGIONAL partially : {}, {}", idRegional, tCREGIONAL);
        if (tCREGIONAL.getIdRegional() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idRegional, tCREGIONAL.getIdRegional())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCREGIONALRepository.existsById(idRegional)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCREGIONAL> result = tCREGIONALRepository
            .findById(tCREGIONAL.getIdRegional())
            .map(
                existingTCREGIONAL -> {
                    if (tCREGIONAL.getRegional() != null) {
                        existingTCREGIONAL.setRegional(tCREGIONAL.getRegional());
                    }

                    return existingTCREGIONAL;
                }
            )
            .map(tCREGIONALRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCREGIONAL.getIdRegional().toString())
        );
    }

    /**
     * {@code POST  /tcregionals/getAll} : get all the tCREGIONALS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCREGIONALS in body.
     */
    @PostMapping("/tcregionals/getAll")
    public List<TCREGIONAL> getAllTCREGIONALS(@RequestBody TCREGIONALDTO tcregionaldto) {
        log.debug("REST request to get all TCREGIONALS");
        return tCREGIONALRepository.findAll();
    }

    /**
     * {@code POST  /tcregionals/getId} : get the "id" tCREGIONAL.
     *
     * @param id the id of the tCREGIONAL to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCREGIONAL, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tcregionals/getId")
    public ResponseEntity<TCREGIONAL> getTCREGIONAL(@RequestParam TCREGIONALDTO tcregionaldto) {
        log.debug("REST request to get TCREGIONAL : {}", tcregionaldto.getClass());
        Optional<TCREGIONAL> tCREGIONAL = tCREGIONALRepository.findById(tcregionaldto.getId());
        return ResponseUtil.wrapOrNotFound(tCREGIONAL);
    }

    /**
     * {@code POST  /tcregionals/deleteId} : delete the "id" tCREGIONAL.
     *
     * @param id the id of the tCREGIONAL to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tcregionals/deleteId")
    public ResponseEntity<Void> deleteTCREGIONAL(@RequestBody TCREGIONALDTO tcregionalDTO) {
        log.debug("REST request to delete TCREGIONAL : {}", tcregionalDTO.getId());
        tCREGIONALRepository.deleteById(tcregionalDTO.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcregionalDTO.getClass().toString()))
            .build();
    }
}
