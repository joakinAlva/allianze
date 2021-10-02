package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCDESCUENTOSUMAASEGURADA;
import com.allianze.apicotizador.dto.TCDESCUENTOSUMAASEGURADADTO;
import com.allianze.apicotizador.repository.TCDESCUENTOSUMAASEGURADARepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCDESCUENTOSUMAASEGURADA}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCDESCUENTOSUMAASEGURADAResource {

    private final Logger log = LoggerFactory.getLogger(TCDESCUENTOSUMAASEGURADAResource.class);

    private static final String ENTITY_NAME = "tCDESCUENTOSUMAASEGURADA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCDESCUENTOSUMAASEGURADARepository tCDESCUENTOSUMAASEGURADARepository;

    public TCDESCUENTOSUMAASEGURADAResource(TCDESCUENTOSUMAASEGURADARepository tCDESCUENTOSUMAASEGURADARepository) {
        this.tCDESCUENTOSUMAASEGURADARepository = tCDESCUENTOSUMAASEGURADARepository;
    }

    /**
     * {@code POST  /tcdescuentosumaaseguradas} : Create a new tCDESCUENTOSUMAASEGURADA.
     *
     * @param tCDESCUENTOSUMAASEGURADA the tCDESCUENTOSUMAASEGURADA to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCDESCUENTOSUMAASEGURADA, or with status {@code 400 (Bad Request)} if the tCDESCUENTOSUMAASEGURADA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcdescuentosumaaseguradas")
    public ResponseEntity<TCDESCUENTOSUMAASEGURADA> createTCDESCUENTOSUMAASEGURADA(
        @Valid @RequestBody TCDESCUENTOSUMAASEGURADA tCDESCUENTOSUMAASEGURADA
    ) throws URISyntaxException {
        log.debug("REST request to save TCDESCUENTOSUMAASEGURADA : {}", tCDESCUENTOSUMAASEGURADA);
        if (tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada() != null) {
            throw new BadRequestAlertException("A new tCDESCUENTOSUMAASEGURADA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCDESCUENTOSUMAASEGURADA result = tCDESCUENTOSUMAASEGURADARepository.save(tCDESCUENTOSUMAASEGURADA);
        return ResponseEntity
            .created(new URI("/api/tcdescuentosumaaseguradas/" + result.getIdDescuentoSumaAsegurada()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdDescuentoSumaAsegurada().toString())
            )
            .body(result);
    }

    /**
     * {@code PUT  /tcdescuentosumaaseguradas/:idDescuentoSumaAsegurada} : Updates an existing tCDESCUENTOSUMAASEGURADA.
     *
     * @param idDescuentoSumaAsegurada the id of the tCDESCUENTOSUMAASEGURADA to save.
     * @param tCDESCUENTOSUMAASEGURADA the tCDESCUENTOSUMAASEGURADA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCDESCUENTOSUMAASEGURADA,
     * or with status {@code 400 (Bad Request)} if the tCDESCUENTOSUMAASEGURADA is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCDESCUENTOSUMAASEGURADA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcdescuentosumaaseguradas/{idDescuentoSumaAsegurada}")
    public ResponseEntity<TCDESCUENTOSUMAASEGURADA> updateTCDESCUENTOSUMAASEGURADA(
        @PathVariable(value = "idDescuentoSumaAsegurada", required = false) final Long idDescuentoSumaAsegurada,
        @Valid @RequestBody TCDESCUENTOSUMAASEGURADA tCDESCUENTOSUMAASEGURADA
    ) throws URISyntaxException {
        log.debug("REST request to update TCDESCUENTOSUMAASEGURADA : {}, {}", idDescuentoSumaAsegurada, tCDESCUENTOSUMAASEGURADA);
        if (tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idDescuentoSumaAsegurada, tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCDESCUENTOSUMAASEGURADARepository.existsById(idDescuentoSumaAsegurada)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCDESCUENTOSUMAASEGURADA result = tCDESCUENTOSUMAASEGURADARepository.save(tCDESCUENTOSUMAASEGURADA);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tcdescuentosumaaseguradas/:idDescuentoSumaAsegurada} : Partial updates given fields of an existing tCDESCUENTOSUMAASEGURADA, field will ignore if it is null
     *
     * @param idDescuentoSumaAsegurada the id of the tCDESCUENTOSUMAASEGURADA to save.
     * @param tCDESCUENTOSUMAASEGURADA the tCDESCUENTOSUMAASEGURADA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCDESCUENTOSUMAASEGURADA,
     * or with status {@code 400 (Bad Request)} if the tCDESCUENTOSUMAASEGURADA is not valid,
     * or with status {@code 404 (Not Found)} if the tCDESCUENTOSUMAASEGURADA is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCDESCUENTOSUMAASEGURADA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcdescuentosumaaseguradas/{idDescuentoSumaAsegurada}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCDESCUENTOSUMAASEGURADA> partialUpdateTCDESCUENTOSUMAASEGURADA(
        @PathVariable(value = "idDescuentoSumaAsegurada", required = false) final Long idDescuentoSumaAsegurada,
        @NotNull @RequestBody TCDESCUENTOSUMAASEGURADA tCDESCUENTOSUMAASEGURADA
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update TCDESCUENTOSUMAASEGURADA partially : {}, {}",
            idDescuentoSumaAsegurada,
            tCDESCUENTOSUMAASEGURADA
        );
        if (tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idDescuentoSumaAsegurada, tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCDESCUENTOSUMAASEGURADARepository.existsById(idDescuentoSumaAsegurada)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCDESCUENTOSUMAASEGURADA> result = tCDESCUENTOSUMAASEGURADARepository
            .findById(tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada())
            .map(
                existingTCDESCUENTOSUMAASEGURADA -> {
                    if (tCDESCUENTOSUMAASEGURADA.getMinSuma() != null) {
                        existingTCDESCUENTOSUMAASEGURADA.setMinSuma(tCDESCUENTOSUMAASEGURADA.getMinSuma());
                    }
                    if (tCDESCUENTOSUMAASEGURADA.getMaxSuma() != null) {
                        existingTCDESCUENTOSUMAASEGURADA.setMaxSuma(tCDESCUENTOSUMAASEGURADA.getMaxSuma());
                    }
                    if (tCDESCUENTOSUMAASEGURADA.getPorcentaje() != null) {
                        existingTCDESCUENTOSUMAASEGURADA.setPorcentaje(tCDESCUENTOSUMAASEGURADA.getPorcentaje());
                    }

                    return existingTCDESCUENTOSUMAASEGURADA;
                }
            )
            .map(tCDESCUENTOSUMAASEGURADARepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada().toString()
            )
        );
    }

    /**
     * {@code POST  /tcdescuentosumaaseguradas/getAll} : get all the tCDESCUENTOSUMAASEGURADAS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCDESCUENTOSUMAASEGURADAS in body.
     */
    @PostMapping("/tcdescuentosumaaseguradas/getAll")
    public List<TCDESCUENTOSUMAASEGURADA> getAllTCDESCUENTOSUMAASEGURADAS(@RequestBody TCDESCUENTOSUMAASEGURADADTO tcdescuentoSumaaseguradaDto) {
        log.debug("REST request to get all TCDESCUENTOSUMAASEGURADAS");
        return tCDESCUENTOSUMAASEGURADARepository.findAll();
    }

    /**
     * {@code POST  /tcdescuentosumaaseguradas/getId} : get the "id" tCDESCUENTOSUMAASEGURADA.
     *
     * @param id the id of the tCDESCUENTOSUMAASEGURADA to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCDESCUENTOSUMAASEGURADA, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tcdescuentosumaaseguradas/getId")
    public ResponseEntity<TCDESCUENTOSUMAASEGURADA> getTCDESCUENTOSUMAASEGURADA(@RequestBody TCDESCUENTOSUMAASEGURADADTO tcdescuentoSumaaseguradaDto) {
        log.debug("REST request to get TCDESCUENTOSUMAASEGURADA : {}", tcdescuentoSumaaseguradaDto.getId());
        Optional<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADA = tCDESCUENTOSUMAASEGURADARepository.findById(tcdescuentoSumaaseguradaDto.getId());
        return ResponseUtil.wrapOrNotFound(tCDESCUENTOSUMAASEGURADA);
    }

    /**
     * {@code POST  /tcdescuentosumaaseguradas/deleteId} : delete the "id" tCDESCUENTOSUMAASEGURADA.
     *
     * @param id the id of the tCDESCUENTOSUMAASEGURADA to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tcdescuentosumaaseguradas/deleteId")
    public ResponseEntity<Void> deleteTCDESCUENTOSUMAASEGURADA(@RequestBody TCDESCUENTOSUMAASEGURADADTO tcdescuentoSumaaseguradaDto) {
        log.debug("REST request to delete TCDESCUENTOSUMAASEGURADA : {}", tcdescuentoSumaaseguradaDto.getId());
        tCDESCUENTOSUMAASEGURADARepository.deleteById(tcdescuentoSumaaseguradaDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcdescuentoSumaaseguradaDto.getId().toString()))
            .build();
    }
}
