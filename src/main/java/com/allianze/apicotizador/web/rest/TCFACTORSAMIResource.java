package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCFACTORSAMI;
import com.allianze.apicotizador.dto.TCFACTORSAMIDTO;
import com.allianze.apicotizador.repository.TCFACTORSAMIRepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCFACTORSAMI}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCFACTORSAMIResource {

    private final Logger log = LoggerFactory.getLogger(TCFACTORSAMIResource.class);

    private static final String ENTITY_NAME = "tCFACTORSAMI";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCFACTORSAMIRepository tCFACTORSAMIRepository;

    public TCFACTORSAMIResource(TCFACTORSAMIRepository tCFACTORSAMIRepository) {
        this.tCFACTORSAMIRepository = tCFACTORSAMIRepository;
    }

    /**
     * {@code POST  /tcfactorsamis} : Create a new tCFACTORSAMI.
     *
     * @param tCFACTORSAMI the tCFACTORSAMI to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCFACTORSAMI, or with status {@code 400 (Bad Request)} if the tCFACTORSAMI has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcfactorsamis/insert")
    public ResponseEntity<TCFACTORSAMI> createTCFACTORSAMI(@Valid @RequestBody TCFACTORSAMI tCFACTORSAMI) throws URISyntaxException {
        log.debug("REST request to save TCFACTORSAMI : {}", tCFACTORSAMI);
        if (tCFACTORSAMI.getIdFactorSami() != null) {
            throw new BadRequestAlertException("A new tCFACTORSAMI cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCFACTORSAMI result = tCFACTORSAMIRepository.save(tCFACTORSAMI);
        return ResponseEntity
            .created(new URI("/api/tcfactorsamis/" + result.getIdFactorSami()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdFactorSami().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcfactorsamis/:idFactorSami} : Updates an existing tCFACTORSAMI.
     *
     * @param idFactorSami the id of the tCFACTORSAMI to save.
     * @param tCFACTORSAMI the tCFACTORSAMI to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCFACTORSAMI,
     * or with status {@code 400 (Bad Request)} if the tCFACTORSAMI is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCFACTORSAMI couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcfactorsamis/{idFactorSami}")
    public ResponseEntity<TCFACTORSAMI> updateTCFACTORSAMI(
        @PathVariable(value = "idFactorSami", required = false) final Long idFactorSami,
        @Valid @RequestBody TCFACTORSAMI tCFACTORSAMI
    ) throws URISyntaxException {
        log.debug("REST request to update TCFACTORSAMI : {}, {}", idFactorSami, tCFACTORSAMI);
        if (tCFACTORSAMI.getIdFactorSami() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idFactorSami, tCFACTORSAMI.getIdFactorSami())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCFACTORSAMIRepository.existsById(idFactorSami)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCFACTORSAMI result = tCFACTORSAMIRepository.save(tCFACTORSAMI);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCFACTORSAMI.getIdFactorSami().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tcfactorsamis/:idFactorSami} : Partial updates given fields of an existing tCFACTORSAMI, field will ignore if it is null
     *
     * @param idFactorSami the id of the tCFACTORSAMI to save.
     * @param tCFACTORSAMI the tCFACTORSAMI to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCFACTORSAMI,
     * or with status {@code 400 (Bad Request)} if the tCFACTORSAMI is not valid,
     * or with status {@code 404 (Not Found)} if the tCFACTORSAMI is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCFACTORSAMI couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcfactorsamis/{idFactorSami}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCFACTORSAMI> partialUpdateTCFACTORSAMI(
        @PathVariable(value = "idFactorSami", required = false) final Long idFactorSami,
        @NotNull @RequestBody TCFACTORSAMI tCFACTORSAMI
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCFACTORSAMI partially : {}, {}", idFactorSami, tCFACTORSAMI);
        if (tCFACTORSAMI.getIdFactorSami() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idFactorSami, tCFACTORSAMI.getIdFactorSami())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCFACTORSAMIRepository.existsById(idFactorSami)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCFACTORSAMI> result = tCFACTORSAMIRepository
            .findById(tCFACTORSAMI.getIdFactorSami())
            .map(
                existingTCFACTORSAMI -> {
                    if (tCFACTORSAMI.getMinAsegurados() != null) {
                        existingTCFACTORSAMI.setMinAsegurados(tCFACTORSAMI.getMinAsegurados());
                    }
                    if (tCFACTORSAMI.getMaxAsegurados() != null) {
                        existingTCFACTORSAMI.setMaxAsegurados(tCFACTORSAMI.getMaxAsegurados());
                    }
                    if (tCFACTORSAMI.getFactor() != null) {
                        existingTCFACTORSAMI.setFactor(tCFACTORSAMI.getFactor());
                    }

                    return existingTCFACTORSAMI;
                }
            )
            .map(tCFACTORSAMIRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCFACTORSAMI.getIdFactorSami().toString())
        );
    }

    /**
     * {@code POST  /tcfactorsamis/getAll} : get all the tCFACTORSAMIS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCFACTORSAMIS in body.
     */
    @PostMapping("/tcfactorsamis/getAll")
    public List<TCFACTORSAMI> getAllTCFACTORSAMIS( @RequestBody TCFACTORSAMIDTO tcFactorSamiDto) {
        log.debug("REST request to get all TCFACTORSAMIS");
        return tCFACTORSAMIRepository.findAll();
    }

    /**
     * {@code POST  /tcfactorsamis/getId} : get the "id" tCFACTORSAMI.
     *
     * @param id the id of the tCFACTORSAMI to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCFACTORSAMI, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tcfactorsamis/getId")
    public ResponseEntity<TCFACTORSAMI> getTCFACTORSAMI(@RequestBody TCFACTORSAMIDTO tcFactorSamiDto) {
        log.debug("REST request to get TCFACTORSAMI : {}", tcFactorSamiDto.getId());
        Optional<TCFACTORSAMI> tCFACTORSAMI = tCFACTORSAMIRepository.findById(tcFactorSamiDto.getId());
        return ResponseUtil.wrapOrNotFound(tCFACTORSAMI);
    }

    /**
     * {@code POST  /tcfactorsamis/deleteId} : delete the "id" tCFACTORSAMI.
     *
     * @param id the id of the tCFACTORSAMI to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tcfactorsamis/deleteId")
    public ResponseEntity<Void> deleteTCFACTORSAMI(@RequestBody TCFACTORSAMIDTO tcFactorSamiDto) {
        log.debug("REST request to delete TCFACTORSAMI : {}", tcFactorSamiDto.getId());
        tCFACTORSAMIRepository.deleteById(tcFactorSamiDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcFactorSamiDto.getId().toString()))
            .build();
    }
}
