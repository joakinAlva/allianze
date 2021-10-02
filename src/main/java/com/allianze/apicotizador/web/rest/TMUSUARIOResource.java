package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TMUSUARIO;
import com.allianze.apicotizador.dto.TMUSUARIODTO;
import com.allianze.apicotizador.repository.TMUSUARIORepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TMUSUARIO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TMUSUARIOResource {

    private final Logger log = LoggerFactory.getLogger(TMUSUARIOResource.class);

    private static final String ENTITY_NAME = "tMUSUARIO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TMUSUARIORepository tMUSUARIORepository;

    public TMUSUARIOResource(TMUSUARIORepository tMUSUARIORepository) {
        this.tMUSUARIORepository = tMUSUARIORepository;
    }

    /**
     * {@code POST  /tmusuarios} : Create a new tMUSUARIO.
     *
     * @param tMUSUARIO the tMUSUARIO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tMUSUARIO, or with status {@code 400 (Bad Request)} if the tMUSUARIO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tmusuario/insert")
    public ResponseEntity<TMUSUARIO> createTMUSUARIO(@Valid @RequestBody TMUSUARIO tMUSUARIO) throws URISyntaxException {
        log.debug("REST request to save TMUSUARIO : {}", tMUSUARIO);
        if (tMUSUARIO.getIdUsuario() != null) {
            throw new BadRequestAlertException("A new tMUSUARIO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TMUSUARIO result = tMUSUARIORepository.save(tMUSUARIO);
        return ResponseEntity
            .created(new URI("/api/tmusuarios/" + result.getIdUsuario()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdUsuario().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tmusuarios/:idUsuario} : Updates an existing tMUSUARIO.
     *
     * @param idUsuario the id of the tMUSUARIO to save.
     * @param tMUSUARIO the tMUSUARIO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tMUSUARIO,
     * or with status {@code 400 (Bad Request)} if the tMUSUARIO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tMUSUARIO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tmusuarios/{idUsuario}")
    public ResponseEntity<TMUSUARIO> updateTMUSUARIO(
        @PathVariable(value = "idUsuario", required = false) final Long idUsuario,
        @Valid @RequestBody TMUSUARIO tMUSUARIO
    ) throws URISyntaxException {
        log.debug("REST request to update TMUSUARIO : {}, {}", idUsuario, tMUSUARIO);
        if (tMUSUARIO.getIdUsuario() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idUsuario, tMUSUARIO.getIdUsuario())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tMUSUARIORepository.existsById(idUsuario)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TMUSUARIO result = tMUSUARIORepository.save(tMUSUARIO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tMUSUARIO.getIdUsuario().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tmusuarios/:idUsuario} : Partial updates given fields of an existing tMUSUARIO, field will ignore if it is null
     *
     * @param idUsuario the id of the tMUSUARIO to save.
     * @param tMUSUARIO the tMUSUARIO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tMUSUARIO,
     * or with status {@code 400 (Bad Request)} if the tMUSUARIO is not valid,
     * or with status {@code 404 (Not Found)} if the tMUSUARIO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tMUSUARIO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tmusuarios/{idUsuario}", consumes = "application/merge-patch+json")
    public ResponseEntity<TMUSUARIO> partialUpdateTMUSUARIO(
        @PathVariable(value = "idUsuario", required = false) final Long idUsuario,
           @NotNull @RequestBody TMUSUARIO tMUSUARIO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TMUSUARIO partially : {}, {}", idUsuario, tMUSUARIO);
        if (tMUSUARIO.getIdUsuario() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idUsuario, tMUSUARIO.getIdUsuario())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tMUSUARIORepository.existsById(idUsuario)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TMUSUARIO> result = tMUSUARIORepository
            .findById(tMUSUARIO.getIdUsuario())
            .map(
                existingTMUSUARIO -> {
                    if (tMUSUARIO.getNombre() != null) {
                        existingTMUSUARIO.setNombre(tMUSUARIO.getNombre());
                    }
                    if (tMUSUARIO.getApellidos() != null) {
                        existingTMUSUARIO.setApellidos(tMUSUARIO.getApellidos());
                    }
                    if (tMUSUARIO.getCorreoElectronico() != null) {
                        existingTMUSUARIO.setCorreoElectronico(tMUSUARIO.getCorreoElectronico());
                    }
                    if (tMUSUARIO.getUsuario() != null) {
                        existingTMUSUARIO.setUsuario(tMUSUARIO.getUsuario());
                    }
                    if (tMUSUARIO.getClave() != null) {
                        existingTMUSUARIO.setClave(tMUSUARIO.getClave());
                    }
                    if (tMUSUARIO.getFechaRegistro() != null) {
                        existingTMUSUARIO.setFechaRegistro(tMUSUARIO.getFechaRegistro());
                    }
                    if (tMUSUARIO.getToken() != null) {
                        existingTMUSUARIO.setToken(tMUSUARIO.getToken());
                    }
                    if (tMUSUARIO.getEstatus() != null) {
                        existingTMUSUARIO.setEstatus(tMUSUARIO.getEstatus());
                    }

                    return existingTMUSUARIO;
                }
            )
            .map(tMUSUARIORepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tMUSUARIO.getIdUsuario().toString())
        );
    }

    /**
     * {@code POST  /tmusuario/getAll} : get all the tMUSUARIOS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tMUSUARIOS in body.
     */
    @PostMapping("/tmusuario/getAll")
    public List<TMUSUARIO> getAllTMUSUARIOS( Long id) {
        log.debug("REST request to get all TMUSUARIOS");
        return tMUSUARIORepository.findAll(); 
    }

    /**
     * {@code POST  /tmusuarios/:id} : get the "id" tMUSUARIO.
     *
     * @param id the id of the tMUSUARIO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tMUSUARIO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tmusuario/getId")
    public ResponseEntity<TMUSUARIO> getTMUSUARIO( @RequestBody TMUSUARIODTO tmUsuarioDto) {
        log.debug("REST request to get TMUSUARIO : {}", tmUsuarioDto.getId());
        Optional<TMUSUARIO> tMUSUARIO = tMUSUARIORepository.findById(tmUsuarioDto.getId());
        return ResponseUtil.wrapOrNotFound(tMUSUARIO);
    }

    /**
     * {@code POST  /tmusuario/deleteId} : delete the "id" tMUSUARIO.
     *
     * @param id the id of the tMUSUARIO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tmusuario/deleteId")
    public ResponseEntity<Void> deleteTMUSUARIO(@RequestBody TMUSUARIODTO tmUsuarioDto) {
        log.debug("REST request to delete TMUSUARIO : {}", tmUsuarioDto.getId());
        tMUSUARIORepository.deleteById(tmUsuarioDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tmUsuarioDto.getId().toString()))
            .build();
    }
}
