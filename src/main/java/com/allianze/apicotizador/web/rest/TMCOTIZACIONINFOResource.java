package com.allianze.apicotizador.web.rest;

import com.allianze.apicotizador.domain.TMCOTIZACIONINFO;
import com.allianze.apicotizador.repository.TMCOTIZACIONINFORepository;
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
 * REST controller for managing {@link com.allianze.apicotizador.domain.TMCOTIZACIONINFO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TMCOTIZACIONINFOResource {

    private final Logger log = LoggerFactory.getLogger(TMCOTIZACIONINFOResource.class);

    private static final String ENTITY_NAME = "tMCOTIZACIONINFO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TMCOTIZACIONINFORepository tMCOTIZACIONINFORepository;

    public TMCOTIZACIONINFOResource(TMCOTIZACIONINFORepository tMCOTIZACIONINFORepository) {
        this.tMCOTIZACIONINFORepository = tMCOTIZACIONINFORepository;
    }

    /**
     * {@code POST  /tmcotizacioninfos} : Create a new tMCOTIZACIONINFO.
     *
     * @param tMCOTIZACIONINFO the tMCOTIZACIONINFO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tMCOTIZACIONINFO, or with status {@code 400 (Bad Request)} if the tMCOTIZACIONINFO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tmcotizacioninfos")
    public ResponseEntity<TMCOTIZACIONINFO> createTMCOTIZACIONINFO(@Valid @RequestBody TMCOTIZACIONINFO tMCOTIZACIONINFO)
        throws URISyntaxException {
        log.debug("REST request to save TMCOTIZACIONINFO : {}", tMCOTIZACIONINFO);
        if (tMCOTIZACIONINFO.getIdCotizacionInfo() != null) {
            throw new BadRequestAlertException("A new tMCOTIZACIONINFO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TMCOTIZACIONINFO result = tMCOTIZACIONINFORepository.save(tMCOTIZACIONINFO);
        return ResponseEntity
            .created(new URI("/api/tmcotizacioninfos/" + result.getIdCotizacionInfo()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getIdCotizacionInfo().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tmcotizacioninfos/:idCotizacionInfo} : Updates an existing tMCOTIZACIONINFO.
     *
     * @param idCotizacionInfo the id of the tMCOTIZACIONINFO to save.
     * @param tMCOTIZACIONINFO the tMCOTIZACIONINFO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tMCOTIZACIONINFO,
     * or with status {@code 400 (Bad Request)} if the tMCOTIZACIONINFO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tMCOTIZACIONINFO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tmcotizacioninfos/{idCotizacionInfo}")
    public ResponseEntity<TMCOTIZACIONINFO> updateTMCOTIZACIONINFO(
        @PathVariable(value = "idCotizacionInfo", required = false) final Long idCotizacionInfo,
        @Valid @RequestBody TMCOTIZACIONINFO tMCOTIZACIONINFO
    ) throws URISyntaxException {
        log.debug("REST request to update TMCOTIZACIONINFO : {}, {}", idCotizacionInfo, tMCOTIZACIONINFO);
        if (tMCOTIZACIONINFO.getIdCotizacionInfo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCotizacionInfo, tMCOTIZACIONINFO.getIdCotizacionInfo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tMCOTIZACIONINFORepository.existsById(idCotizacionInfo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TMCOTIZACIONINFO result = tMCOTIZACIONINFORepository.save(tMCOTIZACIONINFO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tMCOTIZACIONINFO.getIdCotizacionInfo().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /tmcotizacioninfos/:idCotizacionInfo} : Partial updates given fields of an existing tMCOTIZACIONINFO, field will ignore if it is null
     *
     * @param idCotizacionInfo the id of the tMCOTIZACIONINFO to save.
     * @param tMCOTIZACIONINFO the tMCOTIZACIONINFO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tMCOTIZACIONINFO,
     * or with status {@code 400 (Bad Request)} if the tMCOTIZACIONINFO is not valid,
     * or with status {@code 404 (Not Found)} if the tMCOTIZACIONINFO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tMCOTIZACIONINFO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tmcotizacioninfos/{idCotizacionInfo}", consumes = "application/merge-patch+json")
    public ResponseEntity<TMCOTIZACIONINFO> partialUpdateTMCOTIZACIONINFO(
        @PathVariable(value = "idCotizacionInfo", required = false) final Long idCotizacionInfo,
        @NotNull @RequestBody TMCOTIZACIONINFO tMCOTIZACIONINFO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TMCOTIZACIONINFO partially : {}, {}", idCotizacionInfo, tMCOTIZACIONINFO);
        if (tMCOTIZACIONINFO.getIdCotizacionInfo() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(idCotizacionInfo, tMCOTIZACIONINFO.getIdCotizacionInfo())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tMCOTIZACIONINFORepository.existsById(idCotizacionInfo)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TMCOTIZACIONINFO> result = tMCOTIZACIONINFORepository
            .findById(tMCOTIZACIONINFO.getIdCotizacionInfo())
            .map(
                existingTMCOTIZACIONINFO -> {
                    if (tMCOTIZACIONINFO.getNumero() != null) {
                        existingTMCOTIZACIONINFO.setNumero(tMCOTIZACIONINFO.getNumero());
                    }
                    if (tMCOTIZACIONINFO.getCotizacion() != null) {
                        existingTMCOTIZACIONINFO.setCotizacion(tMCOTIZACIONINFO.getCotizacion());
                    }
                    if (tMCOTIZACIONINFO.getRegion() != null) {
                        existingTMCOTIZACIONINFO.setRegion(tMCOTIZACIONINFO.getRegion());
                    }
                    if (tMCOTIZACIONINFO.getFechaSolicitud() != null) {
                        existingTMCOTIZACIONINFO.setFechaSolicitud(tMCOTIZACIONINFO.getFechaSolicitud());
                    }
                    if (tMCOTIZACIONINFO.getResponsable() != null) {
                        existingTMCOTIZACIONINFO.setResponsable(tMCOTIZACIONINFO.getResponsable());
                    }
                    if (tMCOTIZACIONINFO.getFechaEntrega() != null) {
                        existingTMCOTIZACIONINFO.setFechaEntrega(tMCOTIZACIONINFO.getFechaEntrega());
                    }
                    if (tMCOTIZACIONINFO.getContratante() != null) {
                        existingTMCOTIZACIONINFO.setContratante(tMCOTIZACIONINFO.getContratante());
                    }
                    if (tMCOTIZACIONINFO.getTipoUno() != null) {
                        existingTMCOTIZACIONINFO.setTipoUno(tMCOTIZACIONINFO.getTipoUno());
                    }
                    if (tMCOTIZACIONINFO.getTipoDos() != null) {
                        existingTMCOTIZACIONINFO.setTipoDos(tMCOTIZACIONINFO.getTipoDos());
                    }
                    if (tMCOTIZACIONINFO.getPoliza() != null) {
                        existingTMCOTIZACIONINFO.setPoliza(tMCOTIZACIONINFO.getPoliza());
                    }
                    if (tMCOTIZACIONINFO.getInicioVigencia() != null) {
                        existingTMCOTIZACIONINFO.setInicioVigencia(tMCOTIZACIONINFO.getInicioVigencia());
                    }
                    if (tMCOTIZACIONINFO.getFinVigencia() != null) {
                        existingTMCOTIZACIONINFO.setFinVigencia(tMCOTIZACIONINFO.getFinVigencia());
                    }
                    if (tMCOTIZACIONINFO.getIntermediario() != null) {
                        existingTMCOTIZACIONINFO.setIntermediario(tMCOTIZACIONINFO.getIntermediario());
                    }
                    if (tMCOTIZACIONINFO.getRfc() != null) {
                        existingTMCOTIZACIONINFO.setRfc(tMCOTIZACIONINFO.getRfc());
                    }
                    if (tMCOTIZACIONINFO.getEjecutivo() != null) {
                        existingTMCOTIZACIONINFO.setEjecutivo(tMCOTIZACIONINFO.getEjecutivo());
                    }
                    if (tMCOTIZACIONINFO.getSuscriptor() != null) {
                        existingTMCOTIZACIONINFO.setSuscriptor(tMCOTIZACIONINFO.getSuscriptor());
                    }
                    if (tMCOTIZACIONINFO.getPlan() != null) {
                        existingTMCOTIZACIONINFO.setPlan(tMCOTIZACIONINFO.getPlan());
                    }
                    if (tMCOTIZACIONINFO.getOcupacion() != null) {
                        existingTMCOTIZACIONINFO.setOcupacion(tMCOTIZACIONINFO.getOcupacion());
                    }
                    if (tMCOTIZACIONINFO.getPrimaCovidVida() != null) {
                        existingTMCOTIZACIONINFO.setPrimaCovidVida(tMCOTIZACIONINFO.getPrimaCovidVida());
                    }
                    if (tMCOTIZACIONINFO.getPrimaCovidGff() != null) {
                        existingTMCOTIZACIONINFO.setPrimaCovidGff(tMCOTIZACIONINFO.getPrimaCovidGff());
                    }
                    if (tMCOTIZACIONINFO.getDescuentoPrimaCovid() != null) {
                        existingTMCOTIZACIONINFO.setDescuentoPrimaCovid(tMCOTIZACIONINFO.getDescuentoPrimaCovid());
                    }
                    if (tMCOTIZACIONINFO.getExtraPrimaVida() != null) {
                        existingTMCOTIZACIONINFO.setExtraPrimaVida(tMCOTIZACIONINFO.getExtraPrimaVida());
                    }
                    if (tMCOTIZACIONINFO.getExtraPrimaGff() != null) {
                        existingTMCOTIZACIONINFO.setExtraPrimaGff(tMCOTIZACIONINFO.getExtraPrimaGff());
                    }
                    if (tMCOTIZACIONINFO.getPorcentajeExtraPrimaVida() != null) {
                        existingTMCOTIZACIONINFO.setPorcentajeExtraPrimaVida(tMCOTIZACIONINFO.getPorcentajeExtraPrimaVida());
                    }
                    if (tMCOTIZACIONINFO.getPorcentajeExtraPrimaGff() != null) {
                        existingTMCOTIZACIONINFO.setPorcentajeExtraPrimaGff(tMCOTIZACIONINFO.getPorcentajeExtraPrimaGff());
                    }
                    if (tMCOTIZACIONINFO.getValorFtr() != null) {
                        existingTMCOTIZACIONINFO.setValorFtr(tMCOTIZACIONINFO.getValorFtr());
                    }
                    if (tMCOTIZACIONINFO.getSami() != null) {
                        existingTMCOTIZACIONINFO.setSami(tMCOTIZACIONINFO.getSami());
                    }
                    if (tMCOTIZACIONINFO.getSamiMin() != null) {
                        existingTMCOTIZACIONINFO.setSamiMin(tMCOTIZACIONINFO.getSamiMin());
                    }
                    if (tMCOTIZACIONINFO.getSamiMax() != null) {
                        existingTMCOTIZACIONINFO.setSamiMax(tMCOTIZACIONINFO.getSamiMax());
                    }
                    if (tMCOTIZACIONINFO.getMargen() != null) {
                        existingTMCOTIZACIONINFO.setMargen(tMCOTIZACIONINFO.getMargen());
                    }
                    if (tMCOTIZACIONINFO.getMargenMin() != null) {
                        existingTMCOTIZACIONINFO.setMargenMin(tMCOTIZACIONINFO.getMargenMin());
                    }
                    if (tMCOTIZACIONINFO.getMargenMax() != null) {
                        existingTMCOTIZACIONINFO.setMargenMax(tMCOTIZACIONINFO.getMargenMax());
                    }
                    if (tMCOTIZACIONINFO.getComision() != null) {
                        existingTMCOTIZACIONINFO.setComision(tMCOTIZACIONINFO.getComision());
                    }
                    if (tMCOTIZACIONINFO.getComisionMin() != null) {
                        existingTMCOTIZACIONINFO.setComisionMin(tMCOTIZACIONINFO.getComisionMin());
                    }
                    if (tMCOTIZACIONINFO.getComisionMax() != null) {
                        existingTMCOTIZACIONINFO.setComisionMax(tMCOTIZACIONINFO.getComisionMax());
                    }
                    if (tMCOTIZACIONINFO.getDescuento() != null) {
                        existingTMCOTIZACIONINFO.setDescuento(tMCOTIZACIONINFO.getDescuento());
                    }
                    if (tMCOTIZACIONINFO.getDescuentoMin() != null) {
                        existingTMCOTIZACIONINFO.setDescuentoMin(tMCOTIZACIONINFO.getDescuentoMin());
                    }
                    if (tMCOTIZACIONINFO.getDescuentoMax() != null) {
                        existingTMCOTIZACIONINFO.setDescuentoMax(tMCOTIZACIONINFO.getDescuentoMax());
                    }
                    if (tMCOTIZACIONINFO.getPrimaneta() != null) {
                        existingTMCOTIZACIONINFO.setPrimaneta(tMCOTIZACIONINFO.getPrimaneta());
                    }
                    if (tMCOTIZACIONINFO.getPrimaNetaMin() != null) {
                        existingTMCOTIZACIONINFO.setPrimaNetaMin(tMCOTIZACIONINFO.getPrimaNetaMin());
                    }
                    if (tMCOTIZACIONINFO.getPrimaNetaMax() != null) {
                        existingTMCOTIZACIONINFO.setPrimaNetaMax(tMCOTIZACIONINFO.getPrimaNetaMax());
                    }
                    if (tMCOTIZACIONINFO.getDerechoPoliza() != null) {
                        existingTMCOTIZACIONINFO.setDerechoPoliza(tMCOTIZACIONINFO.getDerechoPoliza());
                    }
                    if (tMCOTIZACIONINFO.getDerechoPolizaMin() != null) {
                        existingTMCOTIZACIONINFO.setDerechoPolizaMin(tMCOTIZACIONINFO.getDerechoPolizaMin());
                    }
                    if (tMCOTIZACIONINFO.getDerechoPolizaMax() != null) {
                        existingTMCOTIZACIONINFO.setDerechoPolizaMax(tMCOTIZACIONINFO.getDerechoPolizaMax());
                    }
                    if (tMCOTIZACIONINFO.getMayores() != null) {
                        existingTMCOTIZACIONINFO.setMayores(tMCOTIZACIONINFO.getMayores());
                    }
                    if (tMCOTIZACIONINFO.getMayoresMin() != null) {
                        existingTMCOTIZACIONINFO.setMayoresMin(tMCOTIZACIONINFO.getMayoresMin());
                    }
                    if (tMCOTIZACIONINFO.getMayoresMax() != null) {
                        existingTMCOTIZACIONINFO.setMayoresMax(tMCOTIZACIONINFO.getMayoresMax());
                    }
                    if (tMCOTIZACIONINFO.getAsegurados() != null) {
                        existingTMCOTIZACIONINFO.setAsegurados(tMCOTIZACIONINFO.getAsegurados());
                    }
                    if (tMCOTIZACIONINFO.getAseguradosMin() != null) {
                        existingTMCOTIZACIONINFO.setAseguradosMin(tMCOTIZACIONINFO.getAseguradosMin());
                    }
                    if (tMCOTIZACIONINFO.getAseguradosMax() != null) {
                        existingTMCOTIZACIONINFO.setAseguradosMax(tMCOTIZACIONINFO.getAseguradosMax());
                    }
                    if (tMCOTIZACIONINFO.getSaPromedio() != null) {
                        existingTMCOTIZACIONINFO.setSaPromedio(tMCOTIZACIONINFO.getSaPromedio());
                    }
                    if (tMCOTIZACIONINFO.getSaPromedioMin() != null) {
                        existingTMCOTIZACIONINFO.setSaPromedioMin(tMCOTIZACIONINFO.getSaPromedioMin());
                    }
                    if (tMCOTIZACIONINFO.getSaPromedioMax() != null) {
                        existingTMCOTIZACIONINFO.setSaPromedioMax(tMCOTIZACIONINFO.getSaPromedioMax());
                    }
                    if (tMCOTIZACIONINFO.getVarSa() != null) {
                        existingTMCOTIZACIONINFO.setVarSa(tMCOTIZACIONINFO.getVarSa());
                    }
                    if (tMCOTIZACIONINFO.getVarSaMin() != null) {
                        existingTMCOTIZACIONINFO.setVarSaMin(tMCOTIZACIONINFO.getVarSaMin());
                    }
                    if (tMCOTIZACIONINFO.getVarSaMax() != null) {
                        existingTMCOTIZACIONINFO.setVarSaMax(tMCOTIZACIONINFO.getVarSaMax());
                    }
                    if (tMCOTIZACIONINFO.getSaTotal() != null) {
                        existingTMCOTIZACIONINFO.setSaTotal(tMCOTIZACIONINFO.getSaTotal());
                    }
                    if (tMCOTIZACIONINFO.getSaMaxima() != null) {
                        existingTMCOTIZACIONINFO.setSaMaxima(tMCOTIZACIONINFO.getSaMaxima());
                    }
                    if (tMCOTIZACIONINFO.getSaMaximaMin() != null) {
                        existingTMCOTIZACIONINFO.setSaMaximaMin(tMCOTIZACIONINFO.getSaMaximaMin());
                    }
                    if (tMCOTIZACIONINFO.getSaMaximaMax() != null) {
                        existingTMCOTIZACIONINFO.setSaMaximaMax(tMCOTIZACIONINFO.getSaMaximaMax());
                    }
                    if (tMCOTIZACIONINFO.getSubgrupos() != null) {
                        existingTMCOTIZACIONINFO.setSubgrupos(tMCOTIZACIONINFO.getSubgrupos());
                    }
                    if (tMCOTIZACIONINFO.getSubgruposMin() != null) {
                        existingTMCOTIZACIONINFO.setSubgruposMin(tMCOTIZACIONINFO.getSubgruposMin());
                    }
                    if (tMCOTIZACIONINFO.getEdadMediaMin() != null) {
                        existingTMCOTIZACIONINFO.setEdadMediaMin(tMCOTIZACIONINFO.getEdadMediaMin());
                    }
                    if (tMCOTIZACIONINFO.getEdadActuarial() != null) {
                        existingTMCOTIZACIONINFO.setEdadActuarial(tMCOTIZACIONINFO.getEdadActuarial());
                    }
                    if (tMCOTIZACIONINFO.getEdadActuarialMin() != null) {
                        existingTMCOTIZACIONINFO.setEdadActuarialMin(tMCOTIZACIONINFO.getEdadActuarialMin());
                    }
                    if (tMCOTIZACIONINFO.getEdadActPond() != null) {
                        existingTMCOTIZACIONINFO.setEdadActPond(tMCOTIZACIONINFO.getEdadActPond());
                    }
                    if (tMCOTIZACIONINFO.getEdadActPondMin() != null) {
                        existingTMCOTIZACIONINFO.setEdadActPondMin(tMCOTIZACIONINFO.getEdadActPondMin());
                    }
                    if (tMCOTIZACIONINFO.getEdadMin() != null) {
                        existingTMCOTIZACIONINFO.setEdadMin(tMCOTIZACIONINFO.getEdadMin());
                    }
                    if (tMCOTIZACIONINFO.getEdadMinDos() != null) {
                        existingTMCOTIZACIONINFO.setEdadMinDos(tMCOTIZACIONINFO.getEdadMinDos());
                    }
                    if (tMCOTIZACIONINFO.getEdadMaxDos() != null) {
                        existingTMCOTIZACIONINFO.setEdadMaxDos(tMCOTIZACIONINFO.getEdadMaxDos());
                    }
                    if (tMCOTIZACIONINFO.getEdadMaxTres() != null) {
                        existingTMCOTIZACIONINFO.setEdadMaxTres(tMCOTIZACIONINFO.getEdadMaxTres());
                    }
                    if (tMCOTIZACIONINFO.getCoeficienteVariacion() != null) {
                        existingTMCOTIZACIONINFO.setCoeficienteVariacion(tMCOTIZACIONINFO.getCoeficienteVariacion());
                    }
                    if (tMCOTIZACIONINFO.getFactorTrUnoGiro() != null) {
                        existingTMCOTIZACIONINFO.setFactorTrUnoGiro(tMCOTIZACIONINFO.getFactorTrUnoGiro());
                    }
                    if (tMCOTIZACIONINFO.getFactorSaProm() != null) {
                        existingTMCOTIZACIONINFO.setFactorSaProm(tMCOTIZACIONINFO.getFactorSaProm());
                    }
                    if (tMCOTIZACIONINFO.getpNetaGlobalSdmc() != null) {
                        existingTMCOTIZACIONINFO.setpNetaGlobalSdmc(tMCOTIZACIONINFO.getpNetaGlobalSdmc());
                    }
                    if (tMCOTIZACIONINFO.getpNetaGlobalCdmcCuota() != null) {
                        existingTMCOTIZACIONINFO.setpNetaGlobalCdmcCuota(tMCOTIZACIONINFO.getpNetaGlobalCdmcCuota());
                    }
                    if (tMCOTIZACIONINFO.getpNetaGlobalSmccdesc() != null) {
                        existingTMCOTIZACIONINFO.setpNetaGlobalSmccdesc(tMCOTIZACIONINFO.getpNetaGlobalSmccdesc());
                    }
                    if (tMCOTIZACIONINFO.getpNetaGlobalSmccdescCuota() != null) {
                        existingTMCOTIZACIONINFO.setpNetaGlobalSmccdescCuota(tMCOTIZACIONINFO.getpNetaGlobalSmccdescCuota());
                    }
                    if (tMCOTIZACIONINFO.getpNetaBasicaSdmc() != null) {
                        existingTMCOTIZACIONINFO.setpNetaBasicaSdmc(tMCOTIZACIONINFO.getpNetaBasicaSdmc());
                    }
                    if (tMCOTIZACIONINFO.getpNetaBasicaSdmcCuota() != null) {
                        existingTMCOTIZACIONINFO.setpNetaBasicaSdmcCuota(tMCOTIZACIONINFO.getpNetaBasicaSdmcCuota());
                    }
                    if (tMCOTIZACIONINFO.getpNetaBasicaCdmc() != null) {
                        existingTMCOTIZACIONINFO.setpNetaBasicaCdmc(tMCOTIZACIONINFO.getpNetaBasicaCdmc());
                    }
                    if (tMCOTIZACIONINFO.getpNetaBasicaCdmcCuota() != null) {
                        existingTMCOTIZACIONINFO.setpNetaBasicaCdmcCuota(tMCOTIZACIONINFO.getpNetaBasicaCdmcCuota());
                    }

                    return existingTMCOTIZACIONINFO;
                }
            )
            .map(tMCOTIZACIONINFORepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tMCOTIZACIONINFO.getIdCotizacionInfo().toString())
        );
    }

    /**
     * {@code GET  /tmcotizacioninfos} : get all the tMCOTIZACIONINFOS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tMCOTIZACIONINFOS in body.
     */
    @GetMapping("/tmcotizacioninfos")
    public List<TMCOTIZACIONINFO> getAllTMCOTIZACIONINFOS() {
        log.debug("REST request to get all TMCOTIZACIONINFOS");
        return tMCOTIZACIONINFORepository.findAll();
    }

    /**
     * {@code GET  /tmcotizacioninfos/:id} : get the "id" tMCOTIZACIONINFO.
     *
     * @param id the id of the tMCOTIZACIONINFO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tMCOTIZACIONINFO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tmcotizacioninfos/{id}")
    public ResponseEntity<TMCOTIZACIONINFO> getTMCOTIZACIONINFO(@PathVariable Long id) {
        log.debug("REST request to get TMCOTIZACIONINFO : {}", id);
        Optional<TMCOTIZACIONINFO> tMCOTIZACIONINFO = tMCOTIZACIONINFORepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tMCOTIZACIONINFO);
    }

    /**
     * {@code DELETE  /tmcotizacioninfos/:id} : delete the "id" tMCOTIZACIONINFO.
     *
     * @param id the id of the tMCOTIZACIONINFO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tmcotizacioninfos/{id}")
    public ResponseEntity<Void> deleteTMCOTIZACIONINFO(@PathVariable Long id) {
        log.debug("REST request to delete TMCOTIZACIONINFO : {}", id);
        tMCOTIZACIONINFORepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
