package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCTIPOPRODUCTO;
import com.allianze.apicotizador.repository.TCTIPOPRODUCTORepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCTIPOPRODUCTO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCTIPOPRODUCTOResource {

    private final Logger log = LoggerFactory.getLogger(TCTIPOPRODUCTOResource.class);

    private static final String ENTITY_NAME = "tCTIPOPRODUCTO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCTIPOPRODUCTORepository tCTIPOPRODUCTORepository;

    public TCTIPOPRODUCTOResource(TCTIPOPRODUCTORepository tCTIPOPRODUCTORepository) {
        this.tCTIPOPRODUCTORepository = tCTIPOPRODUCTORepository;
    }

    /**
     * {@code POST  /tctipoproductos} : Create a new tCTIPOPRODUCTO.
     *
     * @param tCTIPOPRODUCTO the tCTIPOPRODUCTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCTIPOPRODUCTO, or with status {@code 400 (Bad Request)} if the tCTIPOPRODUCTO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tctipoproductos")
    public ResponseEntity<TCTIPOPRODUCTO> createTCTIPOPRODUCTO(@Valid @RequestBody TCTIPOPRODUCTO tCTIPOPRODUCTO)
        throws URISyntaxException {
        log.debug("REST request to save TCTIPOPRODUCTO : {}", tCTIPOPRODUCTO);
        if (tCTIPOPRODUCTO.getIdTipoProducto() != null) {
            throw new BadRequestAlertException("A new tCTIPOPRODUCTO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCTIPOPRODUCTO result = tCTIPOPRODUCTORepository.save(tCTIPOPRODUCTO);
        return ResponseEntity
            .created(new URI("/api/tctipoproductos/" + result.getIdTipoProducto()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdTipoProducto().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tctipoproductos/:idTipoProducto} : Updates an existing tCTIPOPRODUCTO.
     *
     * @param idTipoProducto the id of the tCTIPOPRODUCTO to save.
     * @param tCTIPOPRODUCTO the tCTIPOPRODUCTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCTIPOPRODUCTO,
     * or with status {@code 400 (Bad Request)} if the tCTIPOPRODUCTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCTIPOPRODUCTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tctipoproductos/{idTipoProducto}")
    public ResponseEntity<TCTIPOPRODUCTO> updateTCTIPOPRODUCTO(
        @PathVariable(value = "idTipoProducto", required = false) final Long idTipoProducto,
        @Valid @RequestBody TCTIPOPRODUCTO tCTIPOPRODUCTO
    ) throws URISyntaxException {
        log.debug("REST request to update TCTIPOPRODUCTO : {}, {}", idTipoProducto, tCTIPOPRODUCTO);
        if (tCTIPOPRODUCTO.getIdTipoProducto() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idTipoProducto, tCTIPOPRODUCTO.getIdTipoProducto())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCTIPOPRODUCTORepository.existsById(idTipoProducto)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCTIPOPRODUCTO result = tCTIPOPRODUCTORepository.save(tCTIPOPRODUCTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCTIPOPRODUCTO.getIdTipoProducto().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tctipoproductos/:idTipoProducto} : Partial updates given fields of an existing tCTIPOPRODUCTO, field will ignore if it is null
     *
     * @param idTipoProducto the id of the tCTIPOPRODUCTO to save.
     * @param tCTIPOPRODUCTO the tCTIPOPRODUCTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCTIPOPRODUCTO,
     * or with status {@code 400 (Bad Request)} if the tCTIPOPRODUCTO is not valid,
     * or with status {@code 404 (Not Found)} if the tCTIPOPRODUCTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCTIPOPRODUCTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tctipoproductos/{idTipoProducto}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCTIPOPRODUCTO> partialUpdateTCTIPOPRODUCTO(
        @PathVariable(value = "idTipoProducto", required = false) final Long idTipoProducto,
        @NotNull @RequestBody TCTIPOPRODUCTO tCTIPOPRODUCTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCTIPOPRODUCTO partially : {}, {}", idTipoProducto, tCTIPOPRODUCTO);
        if (tCTIPOPRODUCTO.getIdTipoProducto() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idTipoProducto, tCTIPOPRODUCTO.getIdTipoProducto())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCTIPOPRODUCTORepository.existsById(idTipoProducto)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCTIPOPRODUCTO> result = tCTIPOPRODUCTORepository
            .findById(tCTIPOPRODUCTO.getIdTipoProducto())
            .map(
                existingTCTIPOPRODUCTO -> {
                    if (tCTIPOPRODUCTO.getTipoProducto() != null) {
                        existingTCTIPOPRODUCTO.setTipoProducto(tCTIPOPRODUCTO.getTipoProducto());
                    }
                    if (tCTIPOPRODUCTO.getRegistro() != null) {
                        existingTCTIPOPRODUCTO.setRegistro(tCTIPOPRODUCTO.getRegistro());
                    }
                    if (tCTIPOPRODUCTO.getFecha() != null) {
                        existingTCTIPOPRODUCTO.setFecha(tCTIPOPRODUCTO.getFecha());
                    }

                    return existingTCTIPOPRODUCTO;
                }
            )
            .map(tCTIPOPRODUCTORepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCTIPOPRODUCTO.getIdTipoProducto().toString())
        );
    }

    /**
     * {@code GET  /tctipoproductos} : get all the tCTIPOPRODUCTOS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCTIPOPRODUCTOS in body.
     */
    @GetMapping("/tctipoproductos")
    public List<TCTIPOPRODUCTO> getAllTCTIPOPRODUCTOS() {
        log.debug("REST request to get all TCTIPOPRODUCTOS");
        return tCTIPOPRODUCTORepository.findAll();
    }

    /**
     * {@code GET  /tctipoproductos/:id} : get the "id" tCTIPOPRODUCTO.
     *
     * @param id the id of the tCTIPOPRODUCTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCTIPOPRODUCTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tctipoproductos/{id}")
    public ResponseEntity<TCTIPOPRODUCTO> getTCTIPOPRODUCTO(@PathVariable Long id) {
        log.debug("REST request to get TCTIPOPRODUCTO : {}", id);
        Optional<TCTIPOPRODUCTO> tCTIPOPRODUCTO = tCTIPOPRODUCTORepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tCTIPOPRODUCTO);
    }

    /**
     * {@code DELETE  /tctipoproductos/:id} : delete the "id" tCTIPOPRODUCTO.
     *
     * @param id the id of the tCTIPOPRODUCTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tctipoproductos/{id}")
    public ResponseEntity<Void> deleteTCTIPOPRODUCTO(@PathVariable Long id) {
        log.debug("REST request to delete TCTIPOPRODUCTO : {}", id);
        tCTIPOPRODUCTORepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
