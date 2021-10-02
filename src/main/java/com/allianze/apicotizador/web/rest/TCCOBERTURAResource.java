package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCCOBERTURA;
import com.allianze.apicotizador.dto.TCCOBERTURADTO;
import com.allianze.apicotizador.repository.TCCOBERTURARepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCCOBERTURA}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCCOBERTURAResource {

    private final Logger log = LoggerFactory.getLogger(TCCOBERTURAResource.class);

    private static final String ENTITY_NAME = "tCCOBERTURA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCCOBERTURARepository tCCOBERTURARepository;

    public TCCOBERTURAResource(TCCOBERTURARepository tCCOBERTURARepository) {
        this.tCCOBERTURARepository = tCCOBERTURARepository;
    }

    /**
     * {@code POST  /tccoberturas} : Create a new tCCOBERTURA.
     *
     * @param tCCOBERTURA the tCCOBERTURA to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCCOBERTURA, or with status {@code 400 (Bad Request)} if the tCCOBERTURA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tccoberturas")
    public ResponseEntity<TCCOBERTURA> createTCCOBERTURA(@Valid @RequestBody TCCOBERTURA tCCOBERTURA) throws URISyntaxException {
        log.debug("REST request to save TCCOBERTURA : {}", tCCOBERTURA);
        if (tCCOBERTURA.getIdCobertura() != null) {
            throw new BadRequestAlertException("A new tCCOBERTURA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCCOBERTURA result = tCCOBERTURARepository.save(tCCOBERTURA);
        return ResponseEntity
            .created(new URI("/api/tccoberturas/" + result.getIdCobertura()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdCobertura().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tccoberturas/:idCobertura} : Updates an existing tCCOBERTURA.
     *
     * @param idCobertura the id of the tCCOBERTURA to save.
     * @param tCCOBERTURA the tCCOBERTURA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCOBERTURA,
     * or with status {@code 400 (Bad Request)} if the tCCOBERTURA is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCCOBERTURA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tccoberturas/{idCobertura}")
    public ResponseEntity<TCCOBERTURA> updateTCCOBERTURA(
        @PathVariable(value = "idCobertura", required = false) final Long idCobertura,
        @Valid @RequestBody TCCOBERTURA tCCOBERTURA
    ) throws URISyntaxException {
        log.debug("REST request to update TCCOBERTURA : {}, {}", idCobertura, tCCOBERTURA);
        if (tCCOBERTURA.getIdCobertura() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCobertura, tCCOBERTURA.getIdCobertura())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCOBERTURARepository.existsById(idCobertura)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCCOBERTURA result = tCCOBERTURARepository.save(tCCOBERTURA);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCOBERTURA.getIdCobertura().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tccoberturas/:idCobertura} : Partial updates given fields of an existing tCCOBERTURA, field will ignore if it is null
     *
     * @param idCobertura the id of the tCCOBERTURA to save.
     * @param tCCOBERTURA the tCCOBERTURA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCCOBERTURA,
     * or with status {@code 400 (Bad Request)} if the tCCOBERTURA is not valid,
     * or with status {@code 404 (Not Found)} if the tCCOBERTURA is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCCOBERTURA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tccoberturas/{idCobertura}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCCOBERTURA> partialUpdateTCCOBERTURA(
        @PathVariable(value = "idCobertura", required = false) final Long idCobertura,
        @NotNull @RequestBody TCCOBERTURA tCCOBERTURA
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCCOBERTURA partially : {}, {}", idCobertura, tCCOBERTURA);
        if (tCCOBERTURA.getIdCobertura() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCobertura, tCCOBERTURA.getIdCobertura())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCCOBERTURARepository.existsById(idCobertura)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCCOBERTURA> result = tCCOBERTURARepository
            .findById(tCCOBERTURA.getIdCobertura())
            .map(
                existingTCCOBERTURA -> {
                    if (tCCOBERTURA.getCobertura() != null) {
                        existingTCCOBERTURA.setCobertura(tCCOBERTURA.getCobertura());
                    }
                    if (tCCOBERTURA.getPosicion() != null) {
                        existingTCCOBERTURA.setPosicion(tCCOBERTURA.getPosicion());
                    }

                    return existingTCCOBERTURA;
                }
            )
            .map(tCCOBERTURARepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCCOBERTURA.getIdCobertura().toString())
        );
    }

    /**
     * {@code POST  /tccoberturas/getAll} : get all the tCCOBERTURAS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCCOBERTURAS in body.
     */
    @PostMapping("/tccoberturas/getAll")
    public List<TCCOBERTURA> getAllTCCOBERTURAS(@RequestBody TCCOBERTURADTO tccoberturaDto) {
        log.debug("REST request to get all TCCOBERTURAS");
        return tCCOBERTURARepository.findAll();
    }

    /**
     * {@code POST  /tccoberturas/getId} : get the "id" tCCOBERTURA.
     *
     * @param id the id of the tCCOBERTURA to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCCOBERTURA, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tccoberturas/getId")
    public ResponseEntity<TCCOBERTURA> getTCCOBERTURA(@RequestBody TCCOBERTURADTO tccoberturaDto) {
        log.debug("REST request to get TCCOBERTURA : {}", tccoberturaDto.getId());
        Optional<TCCOBERTURA> tCCOBERTURA = tCCOBERTURARepository.findById(tccoberturaDto.getId());
        return ResponseUtil.wrapOrNotFound(tCCOBERTURA);
    }

    /**
     * {@code POST  /tccoberturas/deleteId} : delete the "id" tCCOBERTURA.
     *
     * @param id the id of the tCCOBERTURA to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tccoberturas/deleteId")
    public ResponseEntity<Void> deleteTCCOBERTURA(@RequestBody TCCOBERTURADTO tccoberturaDto) {
        log.debug("REST request to delete TCCOBERTURA : {}", tccoberturaDto.getId());
        tCCOBERTURARepository.deleteById(tccoberturaDto.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tccoberturaDto.getId().toString()))
            .build();
    }
}
