package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCSUBGRUPO;
import com.allianze.apicotizador.dto.TCSUBGRUPODTO;
import com.allianze.apicotizador.repository.TCSUBGRUPORepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCSUBGRUPO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCSUBGRUPOResource {

    private final Logger log = LoggerFactory.getLogger(TCSUBGRUPOResource.class);

    private static final String ENTITY_NAME = "tCSUBGRUPO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCSUBGRUPORepository tCSUBGRUPORepository;

    public TCSUBGRUPOResource(TCSUBGRUPORepository tCSUBGRUPORepository) {
        this.tCSUBGRUPORepository = tCSUBGRUPORepository;
    }

    /**
     * {@code POST  /tcsubgrupos/insert} : Create a new tCSUBGRUPO.
     *
     * @param tCSUBGRUPO the tCSUBGRUPO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCSUBGRUPO, or with status {@code 400 (Bad Request)} if the tCSUBGRUPO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcsubgrupos/insert")
    public ResponseEntity<TCSUBGRUPO> createTCSUBGRUPO(@Valid @RequestBody TCSUBGRUPO tCSUBGRUPO) throws URISyntaxException {
        log.debug("REST request to save TCSUBGRUPO : {}", tCSUBGRUPO);
        if (tCSUBGRUPO.getIdSubGrupo() != null) {
            throw new BadRequestAlertException("A new tCSUBGRUPO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCSUBGRUPO result = tCSUBGRUPORepository.save(tCSUBGRUPO);
        return ResponseEntity
            .created(new URI("/api/tcsubgrupos/" + result.getIdSubGrupo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdSubGrupo().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcsubgrupos/:idSubGrupo} : Updates an existing tCSUBGRUPO.
     *
     * @param idSubGrupo the id of the tCSUBGRUPO to save.
     * @param tCSUBGRUPO the tCSUBGRUPO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCSUBGRUPO,
     * or with status {@code 400 (Bad Request)} if the tCSUBGRUPO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCSUBGRUPO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcsubgrupos/{idSubGrupo}")
    public ResponseEntity<TCSUBGRUPO> updateTCSUBGRUPO(
        @PathVariable(value = "idSubGrupo", required = false) final Long idSubGrupo,
        @Valid @RequestBody TCSUBGRUPO tCSUBGRUPO
    ) throws URISyntaxException {
        log.debug("REST request to update TCSUBGRUPO : {}, {}", idSubGrupo, tCSUBGRUPO);
        if (tCSUBGRUPO.getIdSubGrupo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idSubGrupo, tCSUBGRUPO.getIdSubGrupo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCSUBGRUPORepository.existsById(idSubGrupo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCSUBGRUPO result = tCSUBGRUPORepository.save(tCSUBGRUPO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCSUBGRUPO.getIdSubGrupo().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tcsubgrupos/:idSubGrupo} : Partial updates given fields of an existing tCSUBGRUPO, field will ignore if it is null
     *
     * @param idSubGrupo the id of the tCSUBGRUPO to save.
     * @param tCSUBGRUPO the tCSUBGRUPO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCSUBGRUPO,
     * or with status {@code 400 (Bad Request)} if the tCSUBGRUPO is not valid,
     * or with status {@code 404 (Not Found)} if the tCSUBGRUPO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCSUBGRUPO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcsubgrupos/{idSubGrupo}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCSUBGRUPO> partialUpdateTCSUBGRUPO(
        @PathVariable(value = "idSubGrupo", required = false) final Long idSubGrupo,
        @NotNull @RequestBody TCSUBGRUPO tCSUBGRUPO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCSUBGRUPO partially : {}, {}", idSubGrupo, tCSUBGRUPO);
        if (tCSUBGRUPO.getIdSubGrupo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idSubGrupo, tCSUBGRUPO.getIdSubGrupo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCSUBGRUPORepository.existsById(idSubGrupo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCSUBGRUPO> result = tCSUBGRUPORepository
            .findById(tCSUBGRUPO.getIdSubGrupo())
            .map(
                existingTCSUBGRUPO -> {
                    if (tCSUBGRUPO.getSubGrupo() != null) {
                        existingTCSUBGRUPO.setSubGrupo(tCSUBGRUPO.getSubGrupo());
                    }

                    return existingTCSUBGRUPO;
                }
            )
            .map(tCSUBGRUPORepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCSUBGRUPO.getIdSubGrupo().toString())
        );
    }

    /**
     * {@code POST  /tcsubgrupos/getAll} : get all the tCSUBGRUPOS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCSUBGRUPOS in body.
     */
    @PostMapping("/tcsubgrupos/getAll")
    public List<TCSUBGRUPO> getAllTCSUBGRUPOS( @RequestBody TCSUBGRUPODTO tcSubGrupoDto) {
        log.debug("REST request to get all TCSUBGRUPOS");
        return tCSUBGRUPORepository.findAll();
    }

    /**
     * {@code POST  /tcsubgrupos/getId} : get the "id" tCSUBGRUPO.
     *
     * @param id the id of the tCSUBGRUPO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCSUBGRUPO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tcsubgrupos/getId")
    public ResponseEntity<TCSUBGRUPO> getTCSUBGRUPO(@RequestBody TCSUBGRUPODTO tcSubGrupoDto) {
        log.debug("REST request to get TCSUBGRUPO : {}", tcSubGrupoDto.getId());
        Optional<TCSUBGRUPO> tCSUBGRUPO = tCSUBGRUPORepository.findById(tcSubGrupoDto.getId());
        return ResponseUtil.wrapOrNotFound(tCSUBGRUPO);
    }

    /**
     * {@code POST  /tcsubgrupos/deleteId} : delete the "id" tCSUBGRUPO.
     *
     * @param id the id of the tCSUBGRUPO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tcsubgrupos/deleteId")
    public ResponseEntity<Void> deleteTCSUBGRUPO(@RequestBody TCSUBGRUPODTO tcSubGrupoDto) {
        log.debug("REST request to delete TCSUBGRUPO : {}", tcSubGrupoDto.getId());
        tCSUBGRUPORepository.deleteById(tcSubGrupoDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcSubGrupoDto.getId().toString()))
            .build();
    }
}
