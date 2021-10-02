package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TCPRIMANETASDESC;
import com.allianze.apicotizador.dto.TCPRIMANETASDESCDTO;
import com.allianze.apicotizador.repository.TCPRIMANETASDESCRepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TCPRIMANETASDESC}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TCPRIMANETASDESCResource {

    private final Logger log = LoggerFactory.getLogger(TCPRIMANETASDESCResource.class);

    private static final String ENTITY_NAME = "tCPRIMANETASDESC";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TCPRIMANETASDESCRepository tCPRIMANETASDESCRepository;

    public TCPRIMANETASDESCResource(TCPRIMANETASDESCRepository tCPRIMANETASDESCRepository) {
        this.tCPRIMANETASDESCRepository = tCPRIMANETASDESCRepository;
    }

    /**
     * {@code POST /tcprimanetasdescs/insert} : Create a new tCPRIMANETASDESC.
     *
     * @param tCPRIMANETASDESC the tCPRIMANETASDESC to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tCPRIMANETASDESC, or with status {@code 400 (Bad Request)} if the tCPRIMANETASDESC has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tcprimanetasdescs/insert")
    public ResponseEntity<TCPRIMANETASDESC> createTCPRIMANETASDESC(@Valid @RequestBody TCPRIMANETASDESC tCPRIMANETASDESC)
        throws URISyntaxException {
        log.debug("REST request to save TCPRIMANETASDESC : {}", tCPRIMANETASDESC);
        if (tCPRIMANETASDESC.getIdPrimaNetaSdesc() != null) {
            throw new BadRequestAlertException("A new tCPRIMANETASDESC cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TCPRIMANETASDESC result = tCPRIMANETASDESCRepository.save(tCPRIMANETASDESC);
        return ResponseEntity
            .created(new URI("/api/tcprimanetasdescs/" + result.getIdPrimaNetaSdesc()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdPrimaNetaSdesc().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tcprimanetasdescs/:idPrimaNetaSdesc} : Updates an existing tCPRIMANETASDESC.
     *
     * @param idPrimaNetaSdesc the id of the tCPRIMANETASDESC to save.
     * @param tCPRIMANETASDESC the tCPRIMANETASDESC to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCPRIMANETASDESC,
     * or with status {@code 400 (Bad Request)} if the tCPRIMANETASDESC is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tCPRIMANETASDESC couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tcprimanetasdescs/{idPrimaNetaSdesc}")
    public ResponseEntity<TCPRIMANETASDESC> updateTCPRIMANETASDESC(
        @PathVariable(value = "idPrimaNetaSdesc", required = false) final Long idPrimaNetaSdesc,
        @Valid @RequestBody TCPRIMANETASDESC tCPRIMANETASDESC
    ) throws URISyntaxException {
        log.debug("REST request to update TCPRIMANETASDESC : {}, {}", idPrimaNetaSdesc, tCPRIMANETASDESC);
        if (tCPRIMANETASDESC.getIdPrimaNetaSdesc() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idPrimaNetaSdesc, tCPRIMANETASDESC.getIdPrimaNetaSdesc())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCPRIMANETASDESCRepository.existsById(idPrimaNetaSdesc)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TCPRIMANETASDESC result = tCPRIMANETASDESCRepository.save(tCPRIMANETASDESC);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCPRIMANETASDESC.getIdPrimaNetaSdesc().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tcprimanetasdescs/:idPrimaNetaSdesc} : Partial updates given fields of an existing tCPRIMANETASDESC, field will ignore if it is null
     *
     * @param idPrimaNetaSdesc the id of the tCPRIMANETASDESC to save.
     * @param tCPRIMANETASDESC the tCPRIMANETASDESC to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tCPRIMANETASDESC,
     * or with status {@code 400 (Bad Request)} if the tCPRIMANETASDESC is not valid,
     * or with status {@code 404 (Not Found)} if the tCPRIMANETASDESC is not found,
     * or with status {@code 500 (Internal Server Error)} if the tCPRIMANETASDESC couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tcprimanetasdescs/{idPrimaNetaSdesc}", consumes = "application/merge-patch+json")
    public ResponseEntity<TCPRIMANETASDESC> partialUpdateTCPRIMANETASDESC(
        @PathVariable(value = "idPrimaNetaSdesc", required = false) final Long idPrimaNetaSdesc,
        @NotNull @RequestBody TCPRIMANETASDESC tCPRIMANETASDESC
    ) throws URISyntaxException {
        log.debug("REST request to partial update TCPRIMANETASDESC partially : {}, {}", idPrimaNetaSdesc, tCPRIMANETASDESC);
        if (tCPRIMANETASDESC.getIdPrimaNetaSdesc() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idPrimaNetaSdesc, tCPRIMANETASDESC.getIdPrimaNetaSdesc())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tCPRIMANETASDESCRepository.existsById(idPrimaNetaSdesc)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TCPRIMANETASDESC> result = tCPRIMANETASDESCRepository
            .findById(tCPRIMANETASDESC.getIdPrimaNetaSdesc())
            .map(
                existingTCPRIMANETASDESC -> {
                    if (tCPRIMANETASDESC.getPrimaNetaSdesc() != null) {
                        existingTCPRIMANETASDESC.setPrimaNetaSdesc(tCPRIMANETASDESC.getPrimaNetaSdesc());
                    }
                    if (tCPRIMANETASDESC.getMargenMin() != null) {
                        existingTCPRIMANETASDESC.setMargenMin(tCPRIMANETASDESC.getMargenMin());
                    }
                    if (tCPRIMANETASDESC.getMargenMax() != null) {
                        existingTCPRIMANETASDESC.setMargenMax(tCPRIMANETASDESC.getMargenMax());
                    }
                    if (tCPRIMANETASDESC.getMaxComSd() != null) {
                        existingTCPRIMANETASDESC.setMaxComSd(tCPRIMANETASDESC.getMaxComSd());
                    }
                    if (tCPRIMANETASDESC.getMaxComEp() != null) {
                        existingTCPRIMANETASDESC.setMaxComEp(tCPRIMANETASDESC.getMaxComEp());
                    }

                    return existingTCPRIMANETASDESC;
                }
            )
            .map(tCPRIMANETASDESCRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tCPRIMANETASDESC.getIdPrimaNetaSdesc().toString())
        );
    }

    /**
     * {@code POST  /tcprimanetasdescs/getAll} : get all the tCPRIMANETASDESCS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tCPRIMANETASDESCS in body.
     */
    @PostMapping("/tcprimanetasdescs/getAll")
    public List<TCPRIMANETASDESC> getAllTCPRIMANETASDESCS( @RequestBody TCPRIMANETASDESCDTO tcPrimaNetaSDesc) {
        log.debug("REST request to get all TCPRIMANETASDESCS");
        return tCPRIMANETASDESCRepository.findAll();
    }

    /**
     * {@code POST  /tcprimanetasdescs/getId} : get the "id" tCPRIMANETASDESC.
     *
     * @param id the id of the tCPRIMANETASDESC to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tCPRIMANETASDESC, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/tcprimanetasdescs/getId")
    public ResponseEntity<TCPRIMANETASDESC> getTCPRIMANETASDESC(@RequestBody TCPRIMANETASDESCDTO tcPrimaNetaSDesc) {
        log.debug("REST request to get TCPRIMANETASDESC : {}", tcPrimaNetaSDesc.getId());
        Optional<TCPRIMANETASDESC> tCPRIMANETASDESC = tCPRIMANETASDESCRepository.findById(tcPrimaNetaSDesc.getId());
        return ResponseUtil.wrapOrNotFound(tCPRIMANETASDESC);
    }

    /**
     * {@code POST  /tcprimanetasdescs/deleteId} : delete the "id" tCPRIMANETASDESC.
     *
     * @param id the id of the tCPRIMANETASDESC to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/tcprimanetasdescs/deleteId")
    public ResponseEntity<Void> deleteTCPRIMANETASDESC(@RequestBody TCPRIMANETASDESCDTO tcPrimaNetaSDesc) {
        log.debug("REST request to delete TCPRIMANETASDESC : {}", tcPrimaNetaSDesc.getId());
        tCPRIMANETASDESCRepository.deleteById(tcPrimaNetaSDesc.getId());
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, tcPrimaNetaSDesc.getId().toString()))
            .build();
    }
}
