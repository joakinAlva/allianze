package com.allianze.apicotizador.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TMCOTIZACIONINFO.
 */
@Entity
@Table(name = "tmcotizacioninfo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TMCOTIZACIONINFO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idCotizacionInfo;

    @NotNull
    @Column(name = "numero", nullable = false)
    private Long numero;

    @NotNull
    @Column(name = "cotizacion", nullable = false)
    private Long cotizacion;

    @NotNull
    @Column(name = "region", nullable = false)
    private Long region;

    @NotNull
    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;

    @NotNull
    @Column(name = "responsable", nullable = false)
    private Long responsable;

    @NotNull
    @Column(name = "fecha_entrega", nullable = false)
    private LocalDate fechaEntrega;

    @NotNull
    @Column(name = "contratante", nullable = false)
    private Long contratante;

    @NotNull
    @Column(name = "tipo_uno", nullable = false)
    private Long tipoUno;

    @NotNull
    @Column(name = "tipo_dos", nullable = false)
    private Long tipoDos;

    @NotNull
    @Column(name = "poliza", nullable = false)
    private Long poliza;

    @NotNull
    @Column(name = "inicio_vigencia", nullable = false)
    private LocalDate inicioVigencia;

    @NotNull
    @Column(name = "fin_vigencia", nullable = false)
    private LocalDate finVigencia;

    @NotNull
    @Column(name = "intermediario", nullable = false)
    private String intermediario;

    @NotNull
    @Column(name = "rfc", nullable = false)
    private String rfc;

    @NotNull
    @Column(name = "ejecutivo", nullable = false)
    private Long ejecutivo;

    @NotNull
    @Column(name = "suscriptor", nullable = false)
    private Long suscriptor;

    @NotNull
    @Column(name = "jhi_plan", nullable = false)
    private Long plan;

    @NotNull
    @Column(name = "ocupacion", nullable = false)
    private Long ocupacion;

    @NotNull
    @Column(name = "prima_covid_vida", nullable = false)
    private Long primaCovidVida;

    @NotNull
    @Column(name = "prima_covid_gff", nullable = false)
    private Long primaCovidGff;

    @NotNull
    @Column(name = "descuento_prima_covid", nullable = false)
    private Long descuentoPrimaCovid;

    @NotNull
    @Column(name = "extra_prima_vida", nullable = false)
    private Long extraPrimaVida;

    @NotNull
    @Column(name = "extra_prima_gff", nullable = false)
    private Long extraPrimaGff;

    @NotNull
    @Column(name = "porcentaje_extra_prima_vida", nullable = false)
    private Long porcentajeExtraPrimaVida;

    @NotNull
    @Column(name = "porcentaje_extra_prima_gff", nullable = false)
    private Long porcentajeExtraPrimaGff;

    @NotNull
    @Column(name = "valor_ftr", nullable = false)
    private Long valorFtr;

    @NotNull
    @Column(name = "sami", nullable = false)
    private Long sami;

    @NotNull
    @Column(name = "sami_min", nullable = false)
    private Long samiMin;

    @NotNull
    @Column(name = "sami_max", nullable = false)
    private Long samiMax;

    @NotNull
    @Column(name = "margen", nullable = false)
    private Long margen;

    @NotNull
    @Column(name = "margen_min", nullable = false)
    private Long margenMin;

    @NotNull
    @Column(name = "margen_max", nullable = false)
    private Long margenMax;

    @NotNull
    @Column(name = "comision", nullable = false)
    private Long comision;

    @NotNull
    @Column(name = "comision_min", nullable = false)
    private Long comisionMin;

    @NotNull
    @Column(name = "comision_max", nullable = false)
    private Long comisionMax;

    @NotNull
    @Column(name = "descuento", nullable = false)
    private Long descuento;

    @NotNull
    @Column(name = "descuento_min", nullable = false)
    private Long descuentoMin;

    @NotNull
    @Column(name = "descuento_max", nullable = false)
    private Long descuentoMax;

    @NotNull
    @Column(name = "primaneta", nullable = false)
    private Long primaneta;

    @NotNull
    @Column(name = "prima_neta_min", nullable = false)
    private Long primaNetaMin;

    @NotNull
    @Column(name = "prima_neta_max", nullable = false)
    private Long primaNetaMax;

    @NotNull
    @Column(name = "derecho_poliza", nullable = false)
    private Long derechoPoliza;

    @NotNull
    @Column(name = "derecho_poliza_min", nullable = false)
    private Long derechoPolizaMin;

    @NotNull
    @Column(name = "derecho_poliza_max", nullable = false)
    private Long derechoPolizaMax;

    @NotNull
    @Column(name = "mayores", nullable = false)
    private Long mayores;

    @NotNull
    @Column(name = "mayores_min", nullable = false)
    private Long mayoresMin;

    @NotNull
    @Column(name = "mayores_max", nullable = false)
    private Long mayoresMax;

    @NotNull
    @Column(name = "asegurados", nullable = false)
    private Long asegurados;

    @NotNull
    @Column(name = "asegurados_min", nullable = false)
    private Long aseguradosMin;

    @NotNull
    @Column(name = "asegurados_max", nullable = false)
    private Long aseguradosMax;

    @NotNull
    @Column(name = "sa_promedio", nullable = false)
    private Long saPromedio;

    @NotNull
    @Column(name = "sa_promedio_min", nullable = false)
    private Long saPromedioMin;

    @NotNull
    @Column(name = "sa_promedio_max", nullable = false)
    private Long saPromedioMax;

    @NotNull
    @Column(name = "var_sa", nullable = false)
    private Long varSa;

    @NotNull
    @Column(name = "var_sa_min", nullable = false)
    private Long varSaMin;

    @NotNull
    @Column(name = "var_sa_max", nullable = false)
    private Long varSaMax;

    @NotNull
    @Column(name = "sa_total", nullable = false)
    private Long saTotal;

    @NotNull
    @Column(name = "sa_maxima", nullable = false)
    private Long saMaxima;

    @NotNull
    @Column(name = "sa_maxima_min", nullable = false)
    private Long saMaximaMin;

    @NotNull
    @Column(name = "sa_maxima_max", nullable = false)
    private Long saMaximaMax;

    @NotNull
    @Column(name = "subgrupos", nullable = false)
    private Long subgrupos;

    @NotNull
    @Column(name = "subgrupos_min", nullable = false)
    private Long subgruposMin;

    @NotNull
    @Column(name = "edad_media_min", nullable = false)
    private Long edadMediaMin;

    @NotNull
    @Column(name = "edad_actuarial", nullable = false)
    private Long edadActuarial;

    @NotNull
    @Column(name = "edad_actuarial_min", nullable = false)
    private Long edadActuarialMin;

    @NotNull
    @Column(name = "edad_act_pond", nullable = false)
    private Long edadActPond;

    @NotNull
    @Column(name = "edad_act_pond_min", nullable = false)
    private Long edadActPondMin;

    @NotNull
    @Column(name = "edad_min", nullable = false)
    private Long edadMin;

    @NotNull
    @Column(name = "edad_min_dos", nullable = false)
    private Long edadMinDos;

    @NotNull
    @Column(name = "edad_max_dos", nullable = false)
    private Long edadMaxDos;

    @NotNull
    @Column(name = "edad_max_tres", nullable = false)
    private Long edadMaxTres;

    @NotNull
    @Column(name = "coeficiente_variacion", nullable = false)
    private Long coeficienteVariacion;

    @NotNull
    @Column(name = "factor_tr_uno_giro", nullable = false)
    private Long factorTrUnoGiro;

    @NotNull
    @Column(name = "factor_sa_prom", nullable = false)
    private Long factorSaProm;

    @NotNull
    @Column(name = "p_neta_global_sdmc", nullable = false)
    private Long pNetaGlobalSdmc;

    @NotNull
    @Column(name = "p_neta_global_cdmc_cuota", nullable = false)
    private Long pNetaGlobalCdmcCuota;

    @NotNull
    @Column(name = "p_neta_global_smccdesc", nullable = false)
    private Long pNetaGlobalSmccdesc;

    @NotNull
    @Column(name = "p_neta_global_smccdesc_cuota", nullable = false)
    private Long pNetaGlobalSmccdescCuota;

    @NotNull
    @Column(name = "p_neta_basica_sdmc", nullable = false)
    private Long pNetaBasicaSdmc;

    @NotNull
    @Column(name = "p_neta_basica_sdmc_cuota", nullable = false)
    private Long pNetaBasicaSdmcCuota;

    @NotNull
    @Column(name = "p_neta_basica_cdmc", nullable = false)
    private Long pNetaBasicaCdmc;

    @NotNull
    @Column(name = "p_neta_basica_cdmc_cuota", nullable = false)
    private Long pNetaBasicaCdmcCuota;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdCotizacionInfo() {
        return idCotizacionInfo;
    }

    public void setIdCotizacionInfo(Long idCotizacionInfo) {
        this.idCotizacionInfo = idCotizacionInfo;
    }

    public TMCOTIZACIONINFO idCotizacionInfo(Long idCotizacionInfo) {
        this.idCotizacionInfo = idCotizacionInfo;
        return this;
    }

    public Long getNumero() {
        return this.numero;
    }

    public TMCOTIZACIONINFO numero(Long numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getCotizacion() {
        return this.cotizacion;
    }

    public TMCOTIZACIONINFO cotizacion(Long cotizacion) {
        this.cotizacion = cotizacion;
        return this;
    }

    public void setCotizacion(Long cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Long getRegion() {
        return this.region;
    }

    public TMCOTIZACIONINFO region(Long region) {
        this.region = region;
        return this;
    }

    public void setRegion(Long region) {
        this.region = region;
    }

    public LocalDate getFechaSolicitud() {
        return this.fechaSolicitud;
    }

    public TMCOTIZACIONINFO fechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
        return this;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Long getResponsable() {
        return this.responsable;
    }

    public TMCOTIZACIONINFO responsable(Long responsable) {
        this.responsable = responsable;
        return this;
    }

    public void setResponsable(Long responsable) {
        this.responsable = responsable;
    }

    public LocalDate getFechaEntrega() {
        return this.fechaEntrega;
    }

    public TMCOTIZACIONINFO fechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
        return this;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Long getContratante() {
        return this.contratante;
    }

    public TMCOTIZACIONINFO contratante(Long contratante) {
        this.contratante = contratante;
        return this;
    }

    public void setContratante(Long contratante) {
        this.contratante = contratante;
    }

    public Long getTipoUno() {
        return this.tipoUno;
    }

    public TMCOTIZACIONINFO tipoUno(Long tipoUno) {
        this.tipoUno = tipoUno;
        return this;
    }

    public void setTipoUno(Long tipoUno) {
        this.tipoUno = tipoUno;
    }

    public Long getTipoDos() {
        return this.tipoDos;
    }

    public TMCOTIZACIONINFO tipoDos(Long tipoDos) {
        this.tipoDos = tipoDos;
        return this;
    }

    public void setTipoDos(Long tipoDos) {
        this.tipoDos = tipoDos;
    }

    public Long getPoliza() {
        return this.poliza;
    }

    public TMCOTIZACIONINFO poliza(Long poliza) {
        this.poliza = poliza;
        return this;
    }

    public void setPoliza(Long poliza) {
        this.poliza = poliza;
    }

    public LocalDate getInicioVigencia() {
        return this.inicioVigencia;
    }

    public TMCOTIZACIONINFO inicioVigencia(LocalDate inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
        return this;
    }

    public void setInicioVigencia(LocalDate inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public LocalDate getFinVigencia() {
        return this.finVigencia;
    }

    public TMCOTIZACIONINFO finVigencia(LocalDate finVigencia) {
        this.finVigencia = finVigencia;
        return this;
    }

    public void setFinVigencia(LocalDate finVigencia) {
        this.finVigencia = finVigencia;
    }

    public String getIntermediario() {
        return this.intermediario;
    }

    public TMCOTIZACIONINFO intermediario(String intermediario) {
        this.intermediario = intermediario;
        return this;
    }

    public void setIntermediario(String intermediario) {
        this.intermediario = intermediario;
    }

    public String getRfc() {
        return this.rfc;
    }

    public TMCOTIZACIONINFO rfc(String rfc) {
        this.rfc = rfc;
        return this;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Long getEjecutivo() {
        return this.ejecutivo;
    }

    public TMCOTIZACIONINFO ejecutivo(Long ejecutivo) {
        this.ejecutivo = ejecutivo;
        return this;
    }

    public void setEjecutivo(Long ejecutivo) {
        this.ejecutivo = ejecutivo;
    }

    public Long getSuscriptor() {
        return this.suscriptor;
    }

    public TMCOTIZACIONINFO suscriptor(Long suscriptor) {
        this.suscriptor = suscriptor;
        return this;
    }

    public void setSuscriptor(Long suscriptor) {
        this.suscriptor = suscriptor;
    }

    public Long getPlan() {
        return this.plan;
    }

    public TMCOTIZACIONINFO plan(Long plan) {
        this.plan = plan;
        return this;
    }

    public void setPlan(Long plan) {
        this.plan = plan;
    }

    public Long getOcupacion() {
        return this.ocupacion;
    }

    public TMCOTIZACIONINFO ocupacion(Long ocupacion) {
        this.ocupacion = ocupacion;
        return this;
    }

    public void setOcupacion(Long ocupacion) {
        this.ocupacion = ocupacion;
    }

    public Long getPrimaCovidVida() {
        return this.primaCovidVida;
    }

    public TMCOTIZACIONINFO primaCovidVida(Long primaCovidVida) {
        this.primaCovidVida = primaCovidVida;
        return this;
    }

    public void setPrimaCovidVida(Long primaCovidVida) {
        this.primaCovidVida = primaCovidVida;
    }

    public Long getPrimaCovidGff() {
        return this.primaCovidGff;
    }

    public TMCOTIZACIONINFO primaCovidGff(Long primaCovidGff) {
        this.primaCovidGff = primaCovidGff;
        return this;
    }

    public void setPrimaCovidGff(Long primaCovidGff) {
        this.primaCovidGff = primaCovidGff;
    }

    public Long getDescuentoPrimaCovid() {
        return this.descuentoPrimaCovid;
    }

    public TMCOTIZACIONINFO descuentoPrimaCovid(Long descuentoPrimaCovid) {
        this.descuentoPrimaCovid = descuentoPrimaCovid;
        return this;
    }

    public void setDescuentoPrimaCovid(Long descuentoPrimaCovid) {
        this.descuentoPrimaCovid = descuentoPrimaCovid;
    }

    public Long getExtraPrimaVida() {
        return this.extraPrimaVida;
    }

    public TMCOTIZACIONINFO extraPrimaVida(Long extraPrimaVida) {
        this.extraPrimaVida = extraPrimaVida;
        return this;
    }

    public void setExtraPrimaVida(Long extraPrimaVida) {
        this.extraPrimaVida = extraPrimaVida;
    }

    public Long getExtraPrimaGff() {
        return this.extraPrimaGff;
    }

    public TMCOTIZACIONINFO extraPrimaGff(Long extraPrimaGff) {
        this.extraPrimaGff = extraPrimaGff;
        return this;
    }

    public void setExtraPrimaGff(Long extraPrimaGff) {
        this.extraPrimaGff = extraPrimaGff;
    }

    public Long getPorcentajeExtraPrimaVida() {
        return this.porcentajeExtraPrimaVida;
    }

    public TMCOTIZACIONINFO porcentajeExtraPrimaVida(Long porcentajeExtraPrimaVida) {
        this.porcentajeExtraPrimaVida = porcentajeExtraPrimaVida;
        return this;
    }

    public void setPorcentajeExtraPrimaVida(Long porcentajeExtraPrimaVida) {
        this.porcentajeExtraPrimaVida = porcentajeExtraPrimaVida;
    }

    public Long getPorcentajeExtraPrimaGff() {
        return this.porcentajeExtraPrimaGff;
    }

    public TMCOTIZACIONINFO porcentajeExtraPrimaGff(Long porcentajeExtraPrimaGff) {
        this.porcentajeExtraPrimaGff = porcentajeExtraPrimaGff;
        return this;
    }

    public void setPorcentajeExtraPrimaGff(Long porcentajeExtraPrimaGff) {
        this.porcentajeExtraPrimaGff = porcentajeExtraPrimaGff;
    }

    public Long getValorFtr() {
        return this.valorFtr;
    }

    public TMCOTIZACIONINFO valorFtr(Long valorFtr) {
        this.valorFtr = valorFtr;
        return this;
    }

    public void setValorFtr(Long valorFtr) {
        this.valorFtr = valorFtr;
    }

    public Long getSami() {
        return this.sami;
    }

    public TMCOTIZACIONINFO sami(Long sami) {
        this.sami = sami;
        return this;
    }

    public void setSami(Long sami) {
        this.sami = sami;
    }

    public Long getSamiMin() {
        return this.samiMin;
    }

    public TMCOTIZACIONINFO samiMin(Long samiMin) {
        this.samiMin = samiMin;
        return this;
    }

    public void setSamiMin(Long samiMin) {
        this.samiMin = samiMin;
    }

    public Long getSamiMax() {
        return this.samiMax;
    }

    public TMCOTIZACIONINFO samiMax(Long samiMax) {
        this.samiMax = samiMax;
        return this;
    }

    public void setSamiMax(Long samiMax) {
        this.samiMax = samiMax;
    }

    public Long getMargen() {
        return this.margen;
    }

    public TMCOTIZACIONINFO margen(Long margen) {
        this.margen = margen;
        return this;
    }

    public void setMargen(Long margen) {
        this.margen = margen;
    }

    public Long getMargenMin() {
        return this.margenMin;
    }

    public TMCOTIZACIONINFO margenMin(Long margenMin) {
        this.margenMin = margenMin;
        return this;
    }

    public void setMargenMin(Long margenMin) {
        this.margenMin = margenMin;
    }

    public Long getMargenMax() {
        return this.margenMax;
    }

    public TMCOTIZACIONINFO margenMax(Long margenMax) {
        this.margenMax = margenMax;
        return this;
    }

    public void setMargenMax(Long margenMax) {
        this.margenMax = margenMax;
    }

    public Long getComision() {
        return this.comision;
    }

    public TMCOTIZACIONINFO comision(Long comision) {
        this.comision = comision;
        return this;
    }

    public void setComision(Long comision) {
        this.comision = comision;
    }

    public Long getComisionMin() {
        return this.comisionMin;
    }

    public TMCOTIZACIONINFO comisionMin(Long comisionMin) {
        this.comisionMin = comisionMin;
        return this;
    }

    public void setComisionMin(Long comisionMin) {
        this.comisionMin = comisionMin;
    }

    public Long getComisionMax() {
        return this.comisionMax;
    }

    public TMCOTIZACIONINFO comisionMax(Long comisionMax) {
        this.comisionMax = comisionMax;
        return this;
    }

    public void setComisionMax(Long comisionMax) {
        this.comisionMax = comisionMax;
    }

    public Long getDescuento() {
        return this.descuento;
    }

    public TMCOTIZACIONINFO descuento(Long descuento) {
        this.descuento = descuento;
        return this;
    }

    public void setDescuento(Long descuento) {
        this.descuento = descuento;
    }

    public Long getDescuentoMin() {
        return this.descuentoMin;
    }

    public TMCOTIZACIONINFO descuentoMin(Long descuentoMin) {
        this.descuentoMin = descuentoMin;
        return this;
    }

    public void setDescuentoMin(Long descuentoMin) {
        this.descuentoMin = descuentoMin;
    }

    public Long getDescuentoMax() {
        return this.descuentoMax;
    }

    public TMCOTIZACIONINFO descuentoMax(Long descuentoMax) {
        this.descuentoMax = descuentoMax;
        return this;
    }

    public void setDescuentoMax(Long descuentoMax) {
        this.descuentoMax = descuentoMax;
    }

    public Long getPrimaneta() {
        return this.primaneta;
    }

    public TMCOTIZACIONINFO primaneta(Long primaneta) {
        this.primaneta = primaneta;
        return this;
    }

    public void setPrimaneta(Long primaneta) {
        this.primaneta = primaneta;
    }

    public Long getPrimaNetaMin() {
        return this.primaNetaMin;
    }

    public TMCOTIZACIONINFO primaNetaMin(Long primaNetaMin) {
        this.primaNetaMin = primaNetaMin;
        return this;
    }

    public void setPrimaNetaMin(Long primaNetaMin) {
        this.primaNetaMin = primaNetaMin;
    }

    public Long getPrimaNetaMax() {
        return this.primaNetaMax;
    }

    public TMCOTIZACIONINFO primaNetaMax(Long primaNetaMax) {
        this.primaNetaMax = primaNetaMax;
        return this;
    }

    public void setPrimaNetaMax(Long primaNetaMax) {
        this.primaNetaMax = primaNetaMax;
    }

    public Long getDerechoPoliza() {
        return this.derechoPoliza;
    }

    public TMCOTIZACIONINFO derechoPoliza(Long derechoPoliza) {
        this.derechoPoliza = derechoPoliza;
        return this;
    }

    public void setDerechoPoliza(Long derechoPoliza) {
        this.derechoPoliza = derechoPoliza;
    }

    public Long getDerechoPolizaMin() {
        return this.derechoPolizaMin;
    }

    public TMCOTIZACIONINFO derechoPolizaMin(Long derechoPolizaMin) {
        this.derechoPolizaMin = derechoPolizaMin;
        return this;
    }

    public void setDerechoPolizaMin(Long derechoPolizaMin) {
        this.derechoPolizaMin = derechoPolizaMin;
    }

    public Long getDerechoPolizaMax() {
        return this.derechoPolizaMax;
    }

    public TMCOTIZACIONINFO derechoPolizaMax(Long derechoPolizaMax) {
        this.derechoPolizaMax = derechoPolizaMax;
        return this;
    }

    public void setDerechoPolizaMax(Long derechoPolizaMax) {
        this.derechoPolizaMax = derechoPolizaMax;
    }

    public Long getMayores() {
        return this.mayores;
    }

    public TMCOTIZACIONINFO mayores(Long mayores) {
        this.mayores = mayores;
        return this;
    }

    public void setMayores(Long mayores) {
        this.mayores = mayores;
    }

    public Long getMayoresMin() {
        return this.mayoresMin;
    }

    public TMCOTIZACIONINFO mayoresMin(Long mayoresMin) {
        this.mayoresMin = mayoresMin;
        return this;
    }

    public void setMayoresMin(Long mayoresMin) {
        this.mayoresMin = mayoresMin;
    }

    public Long getMayoresMax() {
        return this.mayoresMax;
    }

    public TMCOTIZACIONINFO mayoresMax(Long mayoresMax) {
        this.mayoresMax = mayoresMax;
        return this;
    }

    public void setMayoresMax(Long mayoresMax) {
        this.mayoresMax = mayoresMax;
    }

    public Long getAsegurados() {
        return this.asegurados;
    }

    public TMCOTIZACIONINFO asegurados(Long asegurados) {
        this.asegurados = asegurados;
        return this;
    }

    public void setAsegurados(Long asegurados) {
        this.asegurados = asegurados;
    }

    public Long getAseguradosMin() {
        return this.aseguradosMin;
    }

    public TMCOTIZACIONINFO aseguradosMin(Long aseguradosMin) {
        this.aseguradosMin = aseguradosMin;
        return this;
    }

    public void setAseguradosMin(Long aseguradosMin) {
        this.aseguradosMin = aseguradosMin;
    }

    public Long getAseguradosMax() {
        return this.aseguradosMax;
    }

    public TMCOTIZACIONINFO aseguradosMax(Long aseguradosMax) {
        this.aseguradosMax = aseguradosMax;
        return this;
    }

    public void setAseguradosMax(Long aseguradosMax) {
        this.aseguradosMax = aseguradosMax;
    }

    public Long getSaPromedio() {
        return this.saPromedio;
    }

    public TMCOTIZACIONINFO saPromedio(Long saPromedio) {
        this.saPromedio = saPromedio;
        return this;
    }

    public void setSaPromedio(Long saPromedio) {
        this.saPromedio = saPromedio;
    }

    public Long getSaPromedioMin() {
        return this.saPromedioMin;
    }

    public TMCOTIZACIONINFO saPromedioMin(Long saPromedioMin) {
        this.saPromedioMin = saPromedioMin;
        return this;
    }

    public void setSaPromedioMin(Long saPromedioMin) {
        this.saPromedioMin = saPromedioMin;
    }

    public Long getSaPromedioMax() {
        return this.saPromedioMax;
    }

    public TMCOTIZACIONINFO saPromedioMax(Long saPromedioMax) {
        this.saPromedioMax = saPromedioMax;
        return this;
    }

    public void setSaPromedioMax(Long saPromedioMax) {
        this.saPromedioMax = saPromedioMax;
    }

    public Long getVarSa() {
        return this.varSa;
    }

    public TMCOTIZACIONINFO varSa(Long varSa) {
        this.varSa = varSa;
        return this;
    }

    public void setVarSa(Long varSa) {
        this.varSa = varSa;
    }

    public Long getVarSaMin() {
        return this.varSaMin;
    }

    public TMCOTIZACIONINFO varSaMin(Long varSaMin) {
        this.varSaMin = varSaMin;
        return this;
    }

    public void setVarSaMin(Long varSaMin) {
        this.varSaMin = varSaMin;
    }

    public Long getVarSaMax() {
        return this.varSaMax;
    }

    public TMCOTIZACIONINFO varSaMax(Long varSaMax) {
        this.varSaMax = varSaMax;
        return this;
    }

    public void setVarSaMax(Long varSaMax) {
        this.varSaMax = varSaMax;
    }

    public Long getSaTotal() {
        return this.saTotal;
    }

    public TMCOTIZACIONINFO saTotal(Long saTotal) {
        this.saTotal = saTotal;
        return this;
    }

    public void setSaTotal(Long saTotal) {
        this.saTotal = saTotal;
    }

    public Long getSaMaxima() {
        return this.saMaxima;
    }

    public TMCOTIZACIONINFO saMaxima(Long saMaxima) {
        this.saMaxima = saMaxima;
        return this;
    }

    public void setSaMaxima(Long saMaxima) {
        this.saMaxima = saMaxima;
    }

    public Long getSaMaximaMin() {
        return this.saMaximaMin;
    }

    public TMCOTIZACIONINFO saMaximaMin(Long saMaximaMin) {
        this.saMaximaMin = saMaximaMin;
        return this;
    }

    public void setSaMaximaMin(Long saMaximaMin) {
        this.saMaximaMin = saMaximaMin;
    }

    public Long getSaMaximaMax() {
        return this.saMaximaMax;
    }

    public TMCOTIZACIONINFO saMaximaMax(Long saMaximaMax) {
        this.saMaximaMax = saMaximaMax;
        return this;
    }

    public void setSaMaximaMax(Long saMaximaMax) {
        this.saMaximaMax = saMaximaMax;
    }

    public Long getSubgrupos() {
        return this.subgrupos;
    }

    public TMCOTIZACIONINFO subgrupos(Long subgrupos) {
        this.subgrupos = subgrupos;
        return this;
    }

    public void setSubgrupos(Long subgrupos) {
        this.subgrupos = subgrupos;
    }

    public Long getSubgruposMin() {
        return this.subgruposMin;
    }

    public TMCOTIZACIONINFO subgruposMin(Long subgruposMin) {
        this.subgruposMin = subgruposMin;
        return this;
    }

    public void setSubgruposMin(Long subgruposMin) {
        this.subgruposMin = subgruposMin;
    }

    public Long getEdadMediaMin() {
        return this.edadMediaMin;
    }

    public TMCOTIZACIONINFO edadMediaMin(Long edadMediaMin) {
        this.edadMediaMin = edadMediaMin;
        return this;
    }

    public void setEdadMediaMin(Long edadMediaMin) {
        this.edadMediaMin = edadMediaMin;
    }

    public Long getEdadActuarial() {
        return this.edadActuarial;
    }

    public TMCOTIZACIONINFO edadActuarial(Long edadActuarial) {
        this.edadActuarial = edadActuarial;
        return this;
    }

    public void setEdadActuarial(Long edadActuarial) {
        this.edadActuarial = edadActuarial;
    }

    public Long getEdadActuarialMin() {
        return this.edadActuarialMin;
    }

    public TMCOTIZACIONINFO edadActuarialMin(Long edadActuarialMin) {
        this.edadActuarialMin = edadActuarialMin;
        return this;
    }

    public void setEdadActuarialMin(Long edadActuarialMin) {
        this.edadActuarialMin = edadActuarialMin;
    }

    public Long getEdadActPond() {
        return this.edadActPond;
    }

    public TMCOTIZACIONINFO edadActPond(Long edadActPond) {
        this.edadActPond = edadActPond;
        return this;
    }

    public void setEdadActPond(Long edadActPond) {
        this.edadActPond = edadActPond;
    }

    public Long getEdadActPondMin() {
        return this.edadActPondMin;
    }

    public TMCOTIZACIONINFO edadActPondMin(Long edadActPondMin) {
        this.edadActPondMin = edadActPondMin;
        return this;
    }

    public void setEdadActPondMin(Long edadActPondMin) {
        this.edadActPondMin = edadActPondMin;
    }

    public Long getEdadMin() {
        return this.edadMin;
    }

    public TMCOTIZACIONINFO edadMin(Long edadMin) {
        this.edadMin = edadMin;
        return this;
    }

    public void setEdadMin(Long edadMin) {
        this.edadMin = edadMin;
    }

    public Long getEdadMinDos() {
        return this.edadMinDos;
    }

    public TMCOTIZACIONINFO edadMinDos(Long edadMinDos) {
        this.edadMinDos = edadMinDos;
        return this;
    }

    public void setEdadMinDos(Long edadMinDos) {
        this.edadMinDos = edadMinDos;
    }

    public Long getEdadMaxDos() {
        return this.edadMaxDos;
    }

    public TMCOTIZACIONINFO edadMaxDos(Long edadMaxDos) {
        this.edadMaxDos = edadMaxDos;
        return this;
    }

    public void setEdadMaxDos(Long edadMaxDos) {
        this.edadMaxDos = edadMaxDos;
    }

    public Long getEdadMaxTres() {
        return this.edadMaxTres;
    }

    public TMCOTIZACIONINFO edadMaxTres(Long edadMaxTres) {
        this.edadMaxTres = edadMaxTres;
        return this;
    }

    public void setEdadMaxTres(Long edadMaxTres) {
        this.edadMaxTres = edadMaxTres;
    }

    public Long getCoeficienteVariacion() {
        return this.coeficienteVariacion;
    }

    public TMCOTIZACIONINFO coeficienteVariacion(Long coeficienteVariacion) {
        this.coeficienteVariacion = coeficienteVariacion;
        return this;
    }

    public void setCoeficienteVariacion(Long coeficienteVariacion) {
        this.coeficienteVariacion = coeficienteVariacion;
    }

    public Long getFactorTrUnoGiro() {
        return this.factorTrUnoGiro;
    }

    public TMCOTIZACIONINFO factorTrUnoGiro(Long factorTrUnoGiro) {
        this.factorTrUnoGiro = factorTrUnoGiro;
        return this;
    }

    public void setFactorTrUnoGiro(Long factorTrUnoGiro) {
        this.factorTrUnoGiro = factorTrUnoGiro;
    }

    public Long getFactorSaProm() {
        return this.factorSaProm;
    }

    public TMCOTIZACIONINFO factorSaProm(Long factorSaProm) {
        this.factorSaProm = factorSaProm;
        return this;
    }

    public void setFactorSaProm(Long factorSaProm) {
        this.factorSaProm = factorSaProm;
    }

    public Long getpNetaGlobalSdmc() {
        return this.pNetaGlobalSdmc;
    }

    public TMCOTIZACIONINFO pNetaGlobalSdmc(Long pNetaGlobalSdmc) {
        this.pNetaGlobalSdmc = pNetaGlobalSdmc;
        return this;
    }

    public void setpNetaGlobalSdmc(Long pNetaGlobalSdmc) {
        this.pNetaGlobalSdmc = pNetaGlobalSdmc;
    }

    public Long getpNetaGlobalCdmcCuota() {
        return this.pNetaGlobalCdmcCuota;
    }

    public TMCOTIZACIONINFO pNetaGlobalCdmcCuota(Long pNetaGlobalCdmcCuota) {
        this.pNetaGlobalCdmcCuota = pNetaGlobalCdmcCuota;
        return this;
    }

    public void setpNetaGlobalCdmcCuota(Long pNetaGlobalCdmcCuota) {
        this.pNetaGlobalCdmcCuota = pNetaGlobalCdmcCuota;
    }

    public Long getpNetaGlobalSmccdesc() {
        return this.pNetaGlobalSmccdesc;
    }

    public TMCOTIZACIONINFO pNetaGlobalSmccdesc(Long pNetaGlobalSmccdesc) {
        this.pNetaGlobalSmccdesc = pNetaGlobalSmccdesc;
        return this;
    }

    public void setpNetaGlobalSmccdesc(Long pNetaGlobalSmccdesc) {
        this.pNetaGlobalSmccdesc = pNetaGlobalSmccdesc;
    }

    public Long getpNetaGlobalSmccdescCuota() {
        return this.pNetaGlobalSmccdescCuota;
    }

    public TMCOTIZACIONINFO pNetaGlobalSmccdescCuota(Long pNetaGlobalSmccdescCuota) {
        this.pNetaGlobalSmccdescCuota = pNetaGlobalSmccdescCuota;
        return this;
    }

    public void setpNetaGlobalSmccdescCuota(Long pNetaGlobalSmccdescCuota) {
        this.pNetaGlobalSmccdescCuota = pNetaGlobalSmccdescCuota;
    }

    public Long getpNetaBasicaSdmc() {
        return this.pNetaBasicaSdmc;
    }

    public TMCOTIZACIONINFO pNetaBasicaSdmc(Long pNetaBasicaSdmc) {
        this.pNetaBasicaSdmc = pNetaBasicaSdmc;
        return this;
    }

    public void setpNetaBasicaSdmc(Long pNetaBasicaSdmc) {
        this.pNetaBasicaSdmc = pNetaBasicaSdmc;
    }

    public Long getpNetaBasicaSdmcCuota() {
        return this.pNetaBasicaSdmcCuota;
    }

    public TMCOTIZACIONINFO pNetaBasicaSdmcCuota(Long pNetaBasicaSdmcCuota) {
        this.pNetaBasicaSdmcCuota = pNetaBasicaSdmcCuota;
        return this;
    }

    public void setpNetaBasicaSdmcCuota(Long pNetaBasicaSdmcCuota) {
        this.pNetaBasicaSdmcCuota = pNetaBasicaSdmcCuota;
    }

    public Long getpNetaBasicaCdmc() {
        return this.pNetaBasicaCdmc;
    }

    public TMCOTIZACIONINFO pNetaBasicaCdmc(Long pNetaBasicaCdmc) {
        this.pNetaBasicaCdmc = pNetaBasicaCdmc;
        return this;
    }

    public void setpNetaBasicaCdmc(Long pNetaBasicaCdmc) {
        this.pNetaBasicaCdmc = pNetaBasicaCdmc;
    }

    public Long getpNetaBasicaCdmcCuota() {
        return this.pNetaBasicaCdmcCuota;
    }

    public TMCOTIZACIONINFO pNetaBasicaCdmcCuota(Long pNetaBasicaCdmcCuota) {
        this.pNetaBasicaCdmcCuota = pNetaBasicaCdmcCuota;
        return this;
    }

    public void setpNetaBasicaCdmcCuota(Long pNetaBasicaCdmcCuota) {
        this.pNetaBasicaCdmcCuota = pNetaBasicaCdmcCuota;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TMCOTIZACIONINFO)) {
            return false;
        }
        return idCotizacionInfo != null && idCotizacionInfo.equals(((TMCOTIZACIONINFO) o).idCotizacionInfo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TMCOTIZACIONINFO{" +
            "idCotizacionInfo=" + getIdCotizacionInfo() +
            ", numero=" + getNumero() +
            ", cotizacion=" + getCotizacion() +
            ", region=" + getRegion() +
            ", fechaSolicitud='" + getFechaSolicitud() + "'" +
            ", responsable=" + getResponsable() +
            ", fechaEntrega='" + getFechaEntrega() + "'" +
            ", contratante=" + getContratante() +
            ", tipoUno=" + getTipoUno() +
            ", tipoDos=" + getTipoDos() +
            ", poliza=" + getPoliza() +
            ", inicioVigencia='" + getInicioVigencia() + "'" +
            ", finVigencia='" + getFinVigencia() + "'" +
            ", intermediario='" + getIntermediario() + "'" +
            ", rfc='" + getRfc() + "'" +
            ", ejecutivo=" + getEjecutivo() +
            ", suscriptor=" + getSuscriptor() +
            ", plan=" + getPlan() +
            ", ocupacion=" + getOcupacion() +
            ", primaCovidVida=" + getPrimaCovidVida() +
            ", primaCovidGff=" + getPrimaCovidGff() +
            ", descuentoPrimaCovid=" + getDescuentoPrimaCovid() +
            ", extraPrimaVida=" + getExtraPrimaVida() +
            ", extraPrimaGff=" + getExtraPrimaGff() +
            ", porcentajeExtraPrimaVida=" + getPorcentajeExtraPrimaVida() +
            ", porcentajeExtraPrimaGff=" + getPorcentajeExtraPrimaGff() +
            ", valorFtr=" + getValorFtr() +
            ", sami=" + getSami() +
            ", samiMin=" + getSamiMin() +
            ", samiMax=" + getSamiMax() +
            ", margen=" + getMargen() +
            ", margenMin=" + getMargenMin() +
            ", margenMax=" + getMargenMax() +
            ", comision=" + getComision() +
            ", comisionMin=" + getComisionMin() +
            ", comisionMax=" + getComisionMax() +
            ", descuento=" + getDescuento() +
            ", descuentoMin=" + getDescuentoMin() +
            ", descuentoMax=" + getDescuentoMax() +
            ", primaneta=" + getPrimaneta() +
            ", primaNetaMin=" + getPrimaNetaMin() +
            ", primaNetaMax=" + getPrimaNetaMax() +
            ", derechoPoliza=" + getDerechoPoliza() +
            ", derechoPolizaMin=" + getDerechoPolizaMin() +
            ", derechoPolizaMax=" + getDerechoPolizaMax() +
            ", mayores=" + getMayores() +
            ", mayoresMin=" + getMayoresMin() +
            ", mayoresMax=" + getMayoresMax() +
            ", asegurados=" + getAsegurados() +
            ", aseguradosMin=" + getAseguradosMin() +
            ", aseguradosMax=" + getAseguradosMax() +
            ", saPromedio=" + getSaPromedio() +
            ", saPromedioMin=" + getSaPromedioMin() +
            ", saPromedioMax=" + getSaPromedioMax() +
            ", varSa=" + getVarSa() +
            ", varSaMin=" + getVarSaMin() +
            ", varSaMax=" + getVarSaMax() +
            ", saTotal=" + getSaTotal() +
            ", saMaxima=" + getSaMaxima() +
            ", saMaximaMin=" + getSaMaximaMin() +
            ", saMaximaMax=" + getSaMaximaMax() +
            ", subgrupos=" + getSubgrupos() +
            ", subgruposMin=" + getSubgruposMin() +
            ", edadMediaMin=" + getEdadMediaMin() +
            ", edadActuarial=" + getEdadActuarial() +
            ", edadActuarialMin=" + getEdadActuarialMin() +
            ", edadActPond=" + getEdadActPond() +
            ", edadActPondMin=" + getEdadActPondMin() +
            ", edadMin=" + getEdadMin() +
            ", edadMinDos=" + getEdadMinDos() +
            ", edadMaxDos=" + getEdadMaxDos() +
            ", edadMaxTres=" + getEdadMaxTres() +
            ", coeficienteVariacion=" + getCoeficienteVariacion() +
            ", factorTrUnoGiro=" + getFactorTrUnoGiro() +
            ", factorSaProm=" + getFactorSaProm() +
            ", pNetaGlobalSdmc=" + getpNetaGlobalSdmc() +
            ", pNetaGlobalCdmcCuota=" + getpNetaGlobalCdmcCuota() +
            ", pNetaGlobalSmccdesc=" + getpNetaGlobalSmccdesc() +
            ", pNetaGlobalSmccdescCuota=" + getpNetaGlobalSmccdescCuota() +
            ", pNetaBasicaSdmc=" + getpNetaBasicaSdmc() +
            ", pNetaBasicaSdmcCuota=" + getpNetaBasicaSdmcCuota() +
            ", pNetaBasicaCdmc=" + getpNetaBasicaCdmc() +
            ", pNetaBasicaCdmcCuota=" + getpNetaBasicaCdmcCuota() +
            "}";
    }
}
