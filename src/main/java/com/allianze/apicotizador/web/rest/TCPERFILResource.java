package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCPERFIL;
import com.allianze.apicotizador.dto.TCPERFILDTO;
import com.allianze.apicotizador.repository.TCPERFILRepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCPERFIL}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCPERFILResource {

    private final Logger log = LoggerFactory.getLogger(TCPERFILResource.class);

    private static final String ENTITY_NAME = "tCPERFIL";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCPERFILRepository tCPERFILRepository;

    public TCPERFILResource(TCPERFILRepository tCPERFILRepository) {
        this.tCPERFILRepository = tCPERFILRepository;
    }

    /**
     * {@code POST  /tcperfils} : Create a new tCPERFIL.
     *
     * @param tCPERFIL the tCPERFIL to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCPERFIL, or with status {@code 400 (Bad Request)} if the tCPERFIL has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcperfils/insert")
    public ResponseEntity<TCPERFIL> createTCPERFIL(@Valid @RequestBody TCPERFIL tCPERFIL) throws URISyntaxException {
        log.debug("REST request to save TCPERFIL : {}", tCPERFIL);
        if (tCPERFIL.getIdPerfil() != null) {
            throw new BadRequestAlertException("A new tCPERFIL cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCPERFIL result = tCPERFILRepository.save(tCPERFIL);
        return ResponseEntity
            .created(new URI("/api/tcperfils/" + result.getIdPerfil()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdPerfil().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcperfils/:idPerfil} : Updates an existing tCPERFIL.
     *
     * @param idPerfil the id of the tCPERFIL to save.
     * @param tCPERFIL the tCPERFIL to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCPERFIL,
     * or with status {@code 400 (Bad Request)} if the tCPERFIL is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCPERFIL couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcperfils/{idPerfil}")
    public ResponseEntity<TCPERFIL> updateTCPERFIL(
        @PathVariable(value = "idPerfil", required = false) final Long idPerfil,
        @Valid @RequestBody TCPERFIL tCPERFIL
    ) throws URISyntaxException {
        log.debug("REST request to update TCPERFIL : {}, {}", idPerfil, tCPERFIL);
        if (tCPERFIL.getIdPerfil() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idPerfil, tCPERFIL.getIdPerfil())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCPERFILRepository.existsById(idPerfil)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCPERFIL result = tCPERFILRepository.save(tCPERFIL);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCPERFIL.getIdPerfil().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tcperfils/:idPerfil} : Partial updates given fields of an existing tCPERFIL, field will ignore if it is null
     *
     * @param idPerfil the id of the tCPERFIL to save.
     * @param tCPERFIL the tCPERFIL to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCPERFIL,
     * or with status {@code 400 (Bad Request)} if the tCPERFIL is not valid,
     * or with status {@code 404 (Not Found)} if the tCPERFIL is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCPERFIL couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcperfils/{idPerfil}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCPERFIL> partialUpdateTCPERFIL(
        @PathVariable(value = "idPerfil", required = false) final Long idPerfil,
        @NotNull @RequestBody TCPERFIL tCPERFIL
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCPERFIL partially : {}, {}", idPerfil, tCPERFIL);
        if (tCPERFIL.getIdPerfil() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idPerfil, tCPERFIL.getIdPerfil())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCPERFILRepository.existsById(idPerfil)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCPERFIL> result = tCPERFILRepository
            .findById(tCPERFIL.getIdPerfil())
            .map(
                existingTCPERFIL -> {
                    if (tCPERFIL.getPerfil() != null) {
                        existingTCPERFIL.setPerfil(tCPERFIL.getPerfil());
                    }

                    return existingTCPERFIL;
                }
            )
            .map(tCPERFILRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCPERFIL.getIdPerfil().toString())
        );
    }

    /**
     * {@code POST  /tcperfils/getAll} : get all the tCPERFILS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCPERFILS in body.
     */
    @PostMapping("/tcperfils/getAll")
    public List<TCPERFIL> getAllTCPERFILS( @RequestBody TCPERFILDTO tcPerfilDto) {
        log.debug("REST request to get all TCPERFILS");
        return tCPERFILRepository.findAll();
    }

    /**
     * {@code POST  /tcperfils/getId} : get the "id" tCPERFIL.
     *
     * @param id the id of the tCPERFIL to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCPERFIL, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tcperfils/getId")
    public ResponseEntity<TCPERFIL> getTCPERFIL(@RequestBody TCPERFILDTO tcPerfilDtod) {
        log.debug("REST request to get TCPERFIL : {}", tcPerfilDtod.getId());
        Optional<TCPERFIL> tCPERFIL = tCPERFILRepository.findById(tcPerfilDtod.getId());
        return ResponseUtil.wrapOrNotFound(tCPERFIL);
    }

    /**
     * {@code POST  /tcperfils/deleteId} : delete the "id" tCPERFIL.
     *
     * @param id the id of the tCPERFIL to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tcperfils/deleteId")
    public ResponseEntity<Void> deleteTCPERFIL(@RequestBody TCPERFILDTO tcPerfilDtod) {
        log.debug("REST request to delete TCPERFIL : {}", tcPerfilDtod.getId());
        tCPERFILRepository.deleteById(tcPerfilDtod.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcPerfilDtod.getId().toString()))
            .build();
    }
}
