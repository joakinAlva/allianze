package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TMCOTIZACIONEXPPROPIA;
import com.allianze.apicotizador.repository.TMCOTIZACIONEXPPROPIARepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TMCOTIZACIONEXPPROPIA}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TMCOTIZACIONEXPPROPIAResource {

    private final Logger log = LoggerFactory.getLogger(TMCOTIZACIONEXPPROPIAResource.class);

    private static final String ENTITY_NAME = "tMCOTIZACIONEXPPROPIA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TMCOTIZACIONEXPPROPIARepository tMCOTIZACIONEXPPROPIARepository;

    public TMCOTIZACIONEXPPROPIAResource(TMCOTIZACIONEXPPROPIARepository tMCOTIZACIONEXPPROPIARepository) {
        this.tMCOTIZACIONEXPPROPIARepository = tMCOTIZACIONEXPPROPIARepository;
    }

    /**
     * {@code POST  /tmcotizacionexppropias} : Create a new tMCOTIZACIONEXPPROPIA.
     *
     * @param tMCOTIZACIONEXPPROPIA the tMCOTIZACIONEXPPROPIA to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tMCOTIZACIONEXPPROPIA, or with status {@code 400 (Bad Request)} if the tMCOTIZACIONEXPPROPIA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tmcotizacionexppropias")
    public ResponseEntity<TMCOTIZACIONEXPPROPIA> createTMCOTIZACIONEXPPROPIA(
        @Valid @RequestBody TMCOTIZACIONEXPPROPIA tMCOTIZACIONEXPPROPIA
    ) throws URISyntaxException {
        log.debug("REST request to save TMCOTIZACIONEXPPROPIA : {}", tMCOTIZACIONEXPPROPIA);
        if (tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia() != null) {
            throw new BadRequestAlertException("A new tMCOTIZACIONEXPPROPIA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TMCOTIZACIONEXPPROPIA result = tMCOTIZACIONEXPPROPIARepository.save(tMCOTIZACIONEXPPROPIA);
        return ResponseEntity
            .created(new URI("/api/tmcotizacionexppropias/" + result.getIdCotizacionExpPropia()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdCotizacionExpPropia().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tmcotizacionexppropias/:idCotizacionExpPropia} : Updates an existing tMCOTIZACIONEXPPROPIA.
     *
     * @param idCotizacionExpPropia the id of the tMCOTIZACIONEXPPROPIA to save.
     * @param tMCOTIZACIONEXPPROPIA the tMCOTIZACIONEXPPROPIA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tMCOTIZACIONEXPPROPIA,
     * or with status {@code 400 (Bad Request)} if the tMCOTIZACIONEXPPROPIA is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tMCOTIZACIONEXPPROPIA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tmcotizacionexppropias/{idCotizacionExpPropia}")
    public ResponseEntity<TMCOTIZACIONEXPPROPIA> updateTMCOTIZACIONEXPPROPIA(
        @PathVariable(value = "idCotizacionExpPropia", required = false) final Long idCotizacionExpPropia,
        @Valid @RequestBody TMCOTIZACIONEXPPROPIA tMCOTIZACIONEXPPROPIA
    ) throws URISyntaxException {
        log.debug("REST request to update TMCOTIZACIONEXPPROPIA : {}, {}", idCotizacionExpPropia, tMCOTIZACIONEXPPROPIA);
        if (tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCotizacionExpPropia, tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tMCOTIZACIONEXPPROPIARepository.existsById(idCotizacionExpPropia)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TMCOTIZACIONEXPPROPIA result = tMCOTIZACIONEXPPROPIARepository.save(tMCOTIZACIONEXPPROPIA);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tmcotizacionexppropias/:idCotizacionExpPropia} : Partial updates given fields of an existing tMCOTIZACIONEXPPROPIA, field will ignore if it is null
     *
     * @param idCotizacionExpPropia the id of the tMCOTIZACIONEXPPROPIA to save.
     * @param tMCOTIZACIONEXPPROPIA the tMCOTIZACIONEXPPROPIA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tMCOTIZACIONEXPPROPIA,
     * or with status {@code 400 (Bad Request)} if the tMCOTIZACIONEXPPROPIA is not valid,
     * or with status {@code 404 (Not Found)} if the tMCOTIZACIONEXPPROPIA is not found,
     * or with status {@code 500 (Internal Server Error)} if the tMCOTIZACIONEXPPROPIA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tmcotizacionexppropias/{idCotizacionExpPropia}", consumes = "application/merge-patch+json")
    public ResponseEntity<TMCOTIZACIONEXPPROPIA> partialUpdateTMCOTIZACIONEXPPROPIA(
        @PathVariable(value = "idCotizacionExpPropia", required = false) final Long idCotizacionExpPropia,
        @NotNull @RequestBody TMCOTIZACIONEXPPROPIA tMCOTIZACIONEXPPROPIA
    ) throws URISyntaxException {
        log.debug("REST request to partial update TMCOTIZACIONEXPPROPIA partially : {}, {}", idCotizacionExpPropia, tMCOTIZACIONEXPPROPIA);
        if (tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCotizacionExpPropia, tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tMCOTIZACIONEXPPROPIARepository.existsById(idCotizacionExpPropia)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TMCOTIZACIONEXPPROPIA> result = tMCOTIZACIONEXPPROPIARepository
            .findById(tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia())
            .map(
                existingTMCOTIZACIONEXPPROPIA -> {
                    if (tMCOTIZACIONEXPPROPIA.getNumero() != null) {
                        existingTMCOTIZACIONEXPPROPIA.setNumero(tMCOTIZACIONEXPPROPIA.getNumero());
                    }
                    if (tMCOTIZACIONEXPPROPIA.getPeriodo() != null) {
                        existingTMCOTIZACIONEXPPROPIA.setPeriodo(tMCOTIZACIONEXPPROPIA.getPeriodo());
                    }
                    if (tMCOTIZACIONEXPPROPIA.getSiniestro() != null) {
                        existingTMCOTIZACIONEXPPROPIA.setSiniestro(tMCOTIZACIONEXPPROPIA.getSiniestro());
                    }
                    if (tMCOTIZACIONEXPPROPIA.getAsegurados() != null) {
                        existingTMCOTIZACIONEXPPROPIA.setAsegurados(tMCOTIZACIONEXPPROPIA.getAsegurados());
                    }
                    if (tMCOTIZACIONEXPPROPIA.getValorQx() != null) {
                        existingTMCOTIZACIONEXPPROPIA.setValorQx(tMCOTIZACIONEXPPROPIA.getValorQx());
                    }

                    return existingTMCOTIZACIONEXPPROPIA;
                }
            )
            .map(tMCOTIZACIONEXPPROPIARepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia().toString()
            )
        );
    }

    /**
     * {@code GET  /tmcotizacionexppropias} : get all the tMCOTIZACIONEXPPROPIAS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tMCOTIZACIONEXPPROPIAS in body.
     */
    @GetMapping("/tmcotizacionexppropias")
    public List<TMCOTIZACIONEXPPROPIA> getAllTMCOTIZACIONEXPPROPIAS() {
        log.debug("REST request to get all TMCOTIZACIONEXPPROPIAS");
        return tMCOTIZACIONEXPPROPIARepository.findAll();
    }

    /**
     * {@code GET  /tmcotizacionexppropias/:id} : get the "id" tMCOTIZACIONEXPPROPIA.
     *
     * @param id the id of the tMCOTIZACIONEXPPROPIA to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tMCOTIZACIONEXPPROPIA, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tmcotizacionexppropias/{id}")
    public ResponseEntity<TMCOTIZACIONEXPPROPIA> getTMCOTIZACIONEXPPROPIA(@PathVariable Long id) {
        log.debug("REST request to get TMCOTIZACIONEXPPROPIA : {}", id);
        Optional<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIA = tMCOTIZACIONEXPPROPIARepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tMCOTIZACIONEXPPROPIA);
    }

    /**
     * {@code DELETE  /tmcotizacionexppropias/:id} : delete the "id" tMCOTIZACIONEXPPROPIA.
     *
     * @param id the id of the tMCOTIZACIONEXPPROPIA to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tmcotizacionexppropias/{id}")
    public ResponseEntity<Void> deleteTMCOTIZACIONEXPPROPIA(@PathVariable Long id) {
        log.debug("REST request to delete TMCOTIZACIONEXPPROPIA : {}", id);
        tMCOTIZACIONEXPPROPIARepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
