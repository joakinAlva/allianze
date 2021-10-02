package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCUNIDADNEGOCIO;
import com.allianze.apicotizador.dto.TCUNIDADNEGOCIODTO;
import com.allianze.apicotizador.repository.TCUNIDADNEGOCIORepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCUNIDADNEGOCIO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCUNIDADNEGOCIOResource {

    private final Logger log = LoggerFactory.getLogger(TCUNIDADNEGOCIOResource.class);

    private static final String ENTITY_NAME = "tCUNIDADNEGOCIO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCUNIDADNEGOCIORepository tCUNIDADNEGOCIORepository;

    public TCUNIDADNEGOCIOResource(TCUNIDADNEGOCIORepository tCUNIDADNEGOCIORepository) {
        this.tCUNIDADNEGOCIORepository = tCUNIDADNEGOCIORepository;
    }

    /**
     * {@code POST  /tcunidadnegocios/insert} : Create a new tCUNIDADNEGOCIO.
     *
     * @param tCUNIDADNEGOCIO the tCUNIDADNEGOCIO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCUNIDADNEGOCIO, or with status {@code 400 (Bad Request)} if the tCUNIDADNEGOCIO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcunidadnegocios/insert")
    public ResponseEntity<TCUNIDADNEGOCIO> createTCUNIDADNEGOCIO(@Valid @RequestBody TCUNIDADNEGOCIO tCUNIDADNEGOCIO)
        throws URISyntaxException {
        log.debug("REST request to save TCUNIDADNEGOCIO : {}", tCUNIDADNEGOCIO);
        if (tCUNIDADNEGOCIO.getIdUnidadNegocio() != null) {
            throw new BadRequestAlertException("A new tCUNIDADNEGOCIO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCUNIDADNEGOCIO result = tCUNIDADNEGOCIORepository.save(tCUNIDADNEGOCIO);
        return ResponseEntity
            .created(new URI("/api/tcunidadnegocios/" + result.getIdUnidadNegocio()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdUnidadNegocio().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcunidadnegocios/:idUnidadNegocio} : Updates an existing tCUNIDADNEGOCIO.
     *
     * @param idUnidadNegocio the id of the tCUNIDADNEGOCIO to save.
     * @param tCUNIDADNEGOCIO the tCUNIDADNEGOCIO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCUNIDADNEGOCIO,
     * or with status {@code 400 (Bad Request)} if the tCUNIDADNEGOCIO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCUNIDADNEGOCIO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcunidadnegocios/{idUnidadNegocio}")
    public ResponseEntity<TCUNIDADNEGOCIO> updateTCUNIDADNEGOCIO(
        @PathVariable(value = "idUnidadNegocio", required = false) final Long idUnidadNegocio,
        @Valid @RequestBody TCUNIDADNEGOCIO tCUNIDADNEGOCIO
    ) throws URISyntaxException {
        log.debug("REST request to update TCUNIDADNEGOCIO : {}, {}", idUnidadNegocio, tCUNIDADNEGOCIO);
        if (tCUNIDADNEGOCIO.getIdUnidadNegocio() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idUnidadNegocio, tCUNIDADNEGOCIO.getIdUnidadNegocio())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCUNIDADNEGOCIORepository.existsById(idUnidadNegocio)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCUNIDADNEGOCIO result = tCUNIDADNEGOCIORepository.save(tCUNIDADNEGOCIO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCUNIDADNEGOCIO.getIdUnidadNegocio().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tcunidadnegocios/:idUnidadNegocio} : Partial updates given fields of an existing tCUNIDADNEGOCIO, field will ignore if it is null
     *
     * @param idUnidadNegocio the id of the tCUNIDADNEGOCIO to save.
     * @param tCUNIDADNEGOCIO the tCUNIDADNEGOCIO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCUNIDADNEGOCIO,
     * or with status {@code 400 (Bad Request)} if the tCUNIDADNEGOCIO is not valid,
     * or with status {@code 404 (Not Found)} if the tCUNIDADNEGOCIO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCUNIDADNEGOCIO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcunidadnegocios/{idUnidadNegocio}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCUNIDADNEGOCIO> partialUpdateTCUNIDADNEGOCIO(
        @PathVariable(value = "idUnidadNegocio", required = false) final Long idUnidadNegocio,
        @NotNull @RequestBody TCUNIDADNEGOCIO tCUNIDADNEGOCIO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCUNIDADNEGOCIO partially : {}, {}", idUnidadNegocio, tCUNIDADNEGOCIO);
        if (tCUNIDADNEGOCIO.getIdUnidadNegocio() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idUnidadNegocio, tCUNIDADNEGOCIO.getIdUnidadNegocio())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCUNIDADNEGOCIORepository.existsById(idUnidadNegocio)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCUNIDADNEGOCIO> result = tCUNIDADNEGOCIORepository
            .findById(tCUNIDADNEGOCIO.getIdUnidadNegocio())
            .map(
                existingTCUNIDADNEGOCIO -> {
                    if (tCUNIDADNEGOCIO.getUnidadNegocio() != null) {
                        existingTCUNIDADNEGOCIO.setUnidadNegocio(tCUNIDADNEGOCIO.getUnidadNegocio());
                    }

                    return existingTCUNIDADNEGOCIO;
                }
            )
            .map(tCUNIDADNEGOCIORepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCUNIDADNEGOCIO.getIdUnidadNegocio().toString())
        );
    }

    /**
     * {@code POST  /tcunidadnegocios/getAll} : get all the tCUNIDADNEGOCIOS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCUNIDADNEGOCIOS in body.
     */
    @PostMapping("/tcunidadnegocios/getAll")
    public List<TCUNIDADNEGOCIO> getAllTCUNIDADNEGOCIOS(@RequestBody TCUNIDADNEGOCIODTO tcunidadNegocioDto) {
        log.debug("REST request to get all TCUNIDADNEGOCIOS");
        return tCUNIDADNEGOCIORepository.findAll();
    }

    /**
     * {@code POST  /tcunidadnegocios/getId} : get the "id" tCUNIDADNEGOCIO.
     *
     * @param id the id of the tCUNIDADNEGOCIO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCUNIDADNEGOCIO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tcunidadnegocios/getId")
    public ResponseEntity<TCUNIDADNEGOCIO> getTCUNIDADNEGOCIO(@RequestBody TCUNIDADNEGOCIODTO tcunidadNegocioDto) {
        log.debug("REST request to get TCUNIDADNEGOCIO : {}", tcunidadNegocioDto.getId());
        Optional<TCUNIDADNEGOCIO> tCUNIDADNEGOCIO = tCUNIDADNEGOCIORepository.findById(tcunidadNegocioDto.getId());
        return ResponseUtil.wrapOrNotFound(tCUNIDADNEGOCIO);
    }

    /**
     * {@code POST  /tcunidadnegocios/deleteId} : delete the "id" tCUNIDADNEGOCIO.
     *
     * @param id the id of the tCUNIDADNEGOCIO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tcunidadnegocios/deleteId")
    public ResponseEntity<Void> deleteTCUNIDADNEGOCIO(@RequestBody TCUNIDADNEGOCIODTO tcunidadNegocioDto) {
        log.debug("REST request to delete TCUNIDADNEGOCIO : {}", tcunidadNegocioDto.getId());
        tCUNIDADNEGOCIORepository.deleteById(tcunidadNegocioDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcunidadNegocioDto.getId().toString()))
            .build();
    }
}
