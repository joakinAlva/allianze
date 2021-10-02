package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCHIPOTESIS;
import com.allianze.apicotizador.dto.TCHIPOTESISDTO;
import com.allianze.apicotizador.repository.TCHIPOTESISRepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCHIPOTESIS}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCHIPOTESISResource {

    private final Logger log = LoggerFactory.getLogger(TCHIPOTESISResource.class);

    private static final String ENTITY_NAME = "tCHIPOTESIS";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCHIPOTESISRepository tCHIPOTESISRepository;

    public TCHIPOTESISResource(TCHIPOTESISRepository tCHIPOTESISRepository) {
        this.tCHIPOTESISRepository = tCHIPOTESISRepository;
    }

    /**
     * {@code POST  /tchipoteses} : Create a new tCHIPOTESIS.
     *
     * @param tCHIPOTESIS the tCHIPOTESIS to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCHIPOTESIS, or with status {@code 400 (Bad Request)} if the tCHIPOTESIS has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tchipoteses")
    public ResponseEntity<TCHIPOTESIS> createTCHIPOTESIS(@Valid @RequestBody TCHIPOTESIS tCHIPOTESIS) throws URISyntaxException {
        log.debug("REST request to save TCHIPOTESIS : {}", tCHIPOTESIS);
        if (tCHIPOTESIS.getIdHipotesis() != null) {
            throw new BadRequestAlertException("A new tCHIPOTESIS cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCHIPOTESIS result = tCHIPOTESISRepository.save(tCHIPOTESIS);
        return ResponseEntity
            .created(new URI("/api/tchipoteses/" + result.getIdHipotesis()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdHipotesis().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tchipoteses/:idHipotesis} : Updates an existing tCHIPOTESIS.
     *
     * @param idHipotesis the id of the tCHIPOTESIS to save.
     * @param tCHIPOTESIS the tCHIPOTESIS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCHIPOTESIS,
     * or with status {@code 400 (Bad Request)} if the tCHIPOTESIS is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCHIPOTESIS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tchipoteses/{idHipotesis}")
    public ResponseEntity<TCHIPOTESIS> updateTCHIPOTESIS(
        @PathVariable(value = "idHipotesis", required = false) final Long idHipotesis,
        @Valid @RequestBody TCHIPOTESIS tCHIPOTESIS
    ) throws URISyntaxException {
        log.debug("REST request to update TCHIPOTESIS : {}, {}", idHipotesis, tCHIPOTESIS);
        if (tCHIPOTESIS.getIdHipotesis() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idHipotesis, tCHIPOTESIS.getIdHipotesis())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCHIPOTESISRepository.existsById(idHipotesis)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCHIPOTESIS result = tCHIPOTESISRepository.save(tCHIPOTESIS);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCHIPOTESIS.getIdHipotesis().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tchipoteses/:idHipotesis} : Partial updates given fields of an existing tCHIPOTESIS, field will ignore if it is null
     *
     * @param idHipotesis the id of the tCHIPOTESIS to save.
     * @param tCHIPOTESIS the tCHIPOTESIS to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCHIPOTESIS,
     * or with status {@code 400 (Bad Request)} if the tCHIPOTESIS is not valid,
     * or with status {@code 404 (Not Found)} if the tCHIPOTESIS is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCHIPOTESIS couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tchipoteses/{idHipotesis}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCHIPOTESIS> partialUpdateTCHIPOTESIS(
        @PathVariable(value = "idHipotesis", required = false) final Long idHipotesis,
        @NotNull @RequestBody TCHIPOTESIS tCHIPOTESIS
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCHIPOTESIS partially : {}, {}", idHipotesis, tCHIPOTESIS);
        if (tCHIPOTESIS.getIdHipotesis() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idHipotesis, tCHIPOTESIS.getIdHipotesis())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCHIPOTESISRepository.existsById(idHipotesis)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCHIPOTESIS> result = tCHIPOTESISRepository
            .findById(tCHIPOTESIS.getIdHipotesis())
            .map(
                existingTCHIPOTESIS -> {
                    if (tCHIPOTESIS.getHipotesis() != null) {
                        existingTCHIPOTESIS.setHipotesis(tCHIPOTESIS.getHipotesis());
                    }
                    if (tCHIPOTESIS.getValor() != null) {
                        existingTCHIPOTESIS.setValor(tCHIPOTESIS.getValor());
                    }
                    if (tCHIPOTESIS.getVariable() != null) {
                        existingTCHIPOTESIS.setVariable(tCHIPOTESIS.getVariable());
                    }

                    return existingTCHIPOTESIS;
                }
            )
            .map(tCHIPOTESISRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCHIPOTESIS.getIdHipotesis().toString())
        );
    }

    /**
     * {@code POST  /tchipoteses]/getAll} : get all the tCHIPOTESES.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCHIPOTESES in body.
     */
    @PostMapping("/tchipoteses/getAll")
    public List<TCHIPOTESIS> getAllTCHIPOTESES(@RequestBody TCHIPOTESISDTO tchipotesis) {
        log.debug("REST request to get all TCHIPOTESES");
        return tCHIPOTESISRepository.findAll();
    }

    /**
     * {@code POST  /tchipoteses/getId} : get the "id" tCHIPOTESIS.
     *
     * @param id the id of the tCHIPOTESIS to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCHIPOTESIS, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tchipoteses/getId")
    public ResponseEntity<TCHIPOTESIS> getTCHIPOTESIS(@RequestBody TCHIPOTESISDTO tchipotesis) {
        log.debug("REST request to get TCHIPOTESIS : {}", tchipotesis.getId());
        Optional<TCHIPOTESIS> tCHIPOTESIS = tCHIPOTESISRepository.findById(tchipotesis.getId());
        return ResponseUtil.wrapOrNotFound(tCHIPOTESIS);
    }

    /**
     * {@code POST  /tchipoteses/:deleteId} : delete the "id" tCHIPOTESIS.
     *
     * @param id the id of the tCHIPOTESIS to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tchipoteses/deleteId")
    public ResponseEntity<Void> deleteTCHIPOTESIS(@RequestBody TCHIPOTESISDTO tchipotesis) {
        log.debug("REST request to delete TCHIPOTESIS : {}", tchipotesis.getId());
        tCHIPOTESISRepository.deleteById(tchipotesis.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tchipotesis.getId().toString()))
            .build();
    }
}
