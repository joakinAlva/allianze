package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TMCOTIZACIONINFO;
import com.allianze.apicotizador.repository.TMCOTIZACIONINFORepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TMCOTIZACIONINFOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TMCOTIZACIONINFOResourceIT {

    private static final Long DEFAULT_NUMERO = 1L;
    private static final Long UPDATED_NUMERO = 2L;

    private static final Long DEFAULT_COTIZACION = 1L;
    private static final Long UPDATED_COTIZACION = 2L;

    private static final Long DEFAULT_REGION = 1L;
    private static final Long UPDATED_REGION = 2L;

    private static final LocalDate DEFAULT_FECHA_SOLICITUD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_SOLICITUD = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_RESPONSABLE = 1L;
    private static final Long UPDATED_RESPONSABLE = 2L;

    private static final LocalDate DEFAULT_FECHA_ENTREGA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_ENTREGA = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_CONTRATANTE = 1L;
    private static final Long UPDATED_CONTRATANTE = 2L;

    private static final Long DEFAULT_TIPO_UNO = 1L;
    private static final Long UPDATED_TIPO_UNO = 2L;

    private static final Long DEFAULT_TIPO_DOS = 1L;
    private static final Long UPDATED_TIPO_DOS = 2L;

    private static final Long DEFAULT_POLIZA = 1L;
    private static final Long UPDATED_POLIZA = 2L;

    private static final LocalDate DEFAULT_INICIO_VIGENCIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INICIO_VIGENCIA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FIN_VIGENCIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FIN_VIGENCIA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_INTERMEDIARIO = "AAAAAAAAAA";
    private static final String UPDATED_INTERMEDIARIO = "BBBBBBBBBB";

    private static final String DEFAULT_RFC = "AAAAAAAAAA";
    private static final String UPDATED_RFC = "BBBBBBBBBB";

    private static final Long DEFAULT_EJECUTIVO = 1L;
    private static final Long UPDATED_EJECUTIVO = 2L;

    private static final Long DEFAULT_SUSCRIPTOR = 1L;
    private static final Long UPDATED_SUSCRIPTOR = 2L;

    private static final Long DEFAULT_PLAN = 1L;
    private static final Long UPDATED_PLAN = 2L;

    private static final Long DEFAULT_OCUPACION = 1L;
    private static final Long UPDATED_OCUPACION = 2L;

    private static final Long DEFAULT_PRIMA_COVID_VIDA = 1L;
    private static final Long UPDATED_PRIMA_COVID_VIDA = 2L;

    private static final Long DEFAULT_PRIMA_COVID_GFF = 1L;
    private static final Long UPDATED_PRIMA_COVID_GFF = 2L;

    private static final Long DEFAULT_DESCUENTO_PRIMA_COVID = 1L;
    private static final Long UPDATED_DESCUENTO_PRIMA_COVID = 2L;

    private static final Long DEFAULT_EXTRA_PRIMA_VIDA = 1L;
    private static final Long UPDATED_EXTRA_PRIMA_VIDA = 2L;

    private static final Long DEFAULT_EXTRA_PRIMA_GFF = 1L;
    private static final Long UPDATED_EXTRA_PRIMA_GFF = 2L;

    private static final Long DEFAULT_PORCENTAJE_EXTRA_PRIMA_VIDA = 1L;
    private static final Long UPDATED_PORCENTAJE_EXTRA_PRIMA_VIDA = 2L;

    private static final Long DEFAULT_PORCENTAJE_EXTRA_PRIMA_GFF = 1L;
    private static final Long UPDATED_PORCENTAJE_EXTRA_PRIMA_GFF = 2L;

    private static final Long DEFAULT_VALOR_FTR = 1L;
    private static final Long UPDATED_VALOR_FTR = 2L;

    private static final Long DEFAULT_SAMI = 1L;
    private static final Long UPDATED_SAMI = 2L;

    private static final Long DEFAULT_SAMI_MIN = 1L;
    private static final Long UPDATED_SAMI_MIN = 2L;

    private static final Long DEFAULT_SAMI_MAX = 1L;
    private static final Long UPDATED_SAMI_MAX = 2L;

    private static final Long DEFAULT_MARGEN = 1L;
    private static final Long UPDATED_MARGEN = 2L;

    private static final Long DEFAULT_MARGEN_MIN = 1L;
    private static final Long UPDATED_MARGEN_MIN = 2L;

    private static final Long DEFAULT_MARGEN_MAX = 1L;
    private static final Long UPDATED_MARGEN_MAX = 2L;

    private static final Long DEFAULT_COMISION = 1L;
    private static final Long UPDATED_COMISION = 2L;

    private static final Long DEFAULT_COMISION_MIN = 1L;
    private static final Long UPDATED_COMISION_MIN = 2L;

    private static final Long DEFAULT_COMISION_MAX = 1L;
    private static final Long UPDATED_COMISION_MAX = 2L;

    private static final Long DEFAULT_DESCUENTO = 1L;
    private static final Long UPDATED_DESCUENTO = 2L;

    private static final Long DEFAULT_DESCUENTO_MIN = 1L;
    private static final Long UPDATED_DESCUENTO_MIN = 2L;

    private static final Long DEFAULT_DESCUENTO_MAX = 1L;
    private static final Long UPDATED_DESCUENTO_MAX = 2L;

    private static final Long DEFAULT_PRIMANETA = 1L;
    private static final Long UPDATED_PRIMANETA = 2L;

    private static final Long DEFAULT_PRIMA_NETA_MIN = 1L;
    private static final Long UPDATED_PRIMA_NETA_MIN = 2L;

    private static final Long DEFAULT_PRIMA_NETA_MAX = 1L;
    private static final Long UPDATED_PRIMA_NETA_MAX = 2L;

    private static final Long DEFAULT_DERECHO_POLIZA = 1L;
    private static final Long UPDATED_DERECHO_POLIZA = 2L;

    private static final Long DEFAULT_DERECHO_POLIZA_MIN = 1L;
    private static final Long UPDATED_DERECHO_POLIZA_MIN = 2L;

    private static final Long DEFAULT_DERECHO_POLIZA_MAX = 1L;
    private static final Long UPDATED_DERECHO_POLIZA_MAX = 2L;

    private static final Long DEFAULT_MAYORES = 1L;
    private static final Long UPDATED_MAYORES = 2L;

    private static final Long DEFAULT_MAYORES_MIN = 1L;
    private static final Long UPDATED_MAYORES_MIN = 2L;

    private static final Long DEFAULT_MAYORES_MAX = 1L;
    private static final Long UPDATED_MAYORES_MAX = 2L;

    private static final Long DEFAULT_ASEGURADOS = 1L;
    private static final Long UPDATED_ASEGURADOS = 2L;

    private static final Long DEFAULT_ASEGURADOS_MIN = 1L;
    private static final Long UPDATED_ASEGURADOS_MIN = 2L;

    private static final Long DEFAULT_ASEGURADOS_MAX = 1L;
    private static final Long UPDATED_ASEGURADOS_MAX = 2L;

    private static final Long DEFAULT_SA_PROMEDIO = 1L;
    private static final Long UPDATED_SA_PROMEDIO = 2L;

    private static final Long DEFAULT_SA_PROMEDIO_MIN = 1L;
    private static final Long UPDATED_SA_PROMEDIO_MIN = 2L;

    private static final Long DEFAULT_SA_PROMEDIO_MAX = 1L;
    private static final Long UPDATED_SA_PROMEDIO_MAX = 2L;

    private static final Long DEFAULT_VAR_SA = 1L;
    private static final Long UPDATED_VAR_SA = 2L;

    private static final Long DEFAULT_VAR_SA_MIN = 1L;
    private static final Long UPDATED_VAR_SA_MIN = 2L;

    private static final Long DEFAULT_VAR_SA_MAX = 1L;
    private static final Long UPDATED_VAR_SA_MAX = 2L;

    private static final Long DEFAULT_SA_TOTAL = 1L;
    private static final Long UPDATED_SA_TOTAL = 2L;

    private static final Long DEFAULT_SA_MAXIMA = 1L;
    private static final Long UPDATED_SA_MAXIMA = 2L;

    private static final Long DEFAULT_SA_MAXIMA_MIN = 1L;
    private static final Long UPDATED_SA_MAXIMA_MIN = 2L;

    private static final Long DEFAULT_SA_MAXIMA_MAX = 1L;
    private static final Long UPDATED_SA_MAXIMA_MAX = 2L;

    private static final Long DEFAULT_SUBGRUPOS = 1L;
    private static final Long UPDATED_SUBGRUPOS = 2L;

    private static final Long DEFAULT_SUBGRUPOS_MIN = 1L;
    private static final Long UPDATED_SUBGRUPOS_MIN = 2L;

    private static final Long DEFAULT_EDAD_MEDIA_MIN = 1L;
    private static final Long UPDATED_EDAD_MEDIA_MIN = 2L;

    private static final Long DEFAULT_EDAD_ACTUARIAL = 1L;
    private static final Long UPDATED_EDAD_ACTUARIAL = 2L;

    private static final Long DEFAULT_EDAD_ACTUARIAL_MIN = 1L;
    private static final Long UPDATED_EDAD_ACTUARIAL_MIN = 2L;

    private static final Long DEFAULT_EDAD_ACT_POND = 1L;
    private static final Long UPDATED_EDAD_ACT_POND = 2L;

    private static final Long DEFAULT_EDAD_ACT_POND_MIN = 1L;
    private static final Long UPDATED_EDAD_ACT_POND_MIN = 2L;

    private static final Long DEFAULT_EDAD_MIN = 1L;
    private static final Long UPDATED_EDAD_MIN = 2L;

    private static final Long DEFAULT_EDAD_MIN_DOS = 1L;
    private static final Long UPDATED_EDAD_MIN_DOS = 2L;

    private static final Long DEFAULT_EDAD_MAX_DOS = 1L;
    private static final Long UPDATED_EDAD_MAX_DOS = 2L;

    private static final Long DEFAULT_EDAD_MAX_TRES = 1L;
    private static final Long UPDATED_EDAD_MAX_TRES = 2L;

    private static final Long DEFAULT_COEFICIENTE_VARIACION = 1L;
    private static final Long UPDATED_COEFICIENTE_VARIACION = 2L;

    private static final Long DEFAULT_FACTOR_TR_UNO_GIRO = 1L;
    private static final Long UPDATED_FACTOR_TR_UNO_GIRO = 2L;

    private static final Long DEFAULT_FACTOR_SA_PROM = 1L;
    private static final Long UPDATED_FACTOR_SA_PROM = 2L;

    private static final Long DEFAULT_P_NETA_GLOBAL_SDMC = 1L;
    private static final Long UPDATED_P_NETA_GLOBAL_SDMC = 2L;

    private static final Long DEFAULT_P_NETA_GLOBAL_CDMC_CUOTA = 1L;
    private static final Long UPDATED_P_NETA_GLOBAL_CDMC_CUOTA = 2L;

    private static final Long DEFAULT_P_NETA_GLOBAL_SMCCDESC = 1L;
    private static final Long UPDATED_P_NETA_GLOBAL_SMCCDESC = 2L;

    private static final Long DEFAULT_P_NETA_GLOBAL_SMCCDESC_CUOTA = 1L;
    private static final Long UPDATED_P_NETA_GLOBAL_SMCCDESC_CUOTA = 2L;

    private static final Long DEFAULT_P_NETA_BASICA_SDMC = 1L;
    private static final Long UPDATED_P_NETA_BASICA_SDMC = 2L;

    private static final Long DEFAULT_P_NETA_BASICA_SDMC_CUOTA = 1L;
    private static final Long UPDATED_P_NETA_BASICA_SDMC_CUOTA = 2L;

    private static final Long DEFAULT_P_NETA_BASICA_CDMC = 1L;
    private static final Long UPDATED_P_NETA_BASICA_CDMC = 2L;

    private static final Long DEFAULT_P_NETA_BASICA_CDMC_CUOTA = 1L;
    private static final Long UPDATED_P_NETA_BASICA_CDMC_CUOTA = 2L;

    private static final String ENTITY_API_URL = "/api/tmcotizacioninfos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCotizacionInfo}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TMCOTIZACIONINFORepository tMCOTIZACIONINFORepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTMCOTIZACIONINFOMockMvc;

    private TMCOTIZACIONINFO tMCOTIZACIONINFO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TMCOTIZACIONINFO createEntity(EntityManager em) {
        TMCOTIZACIONINFO tMCOTIZACIONINFO = new TMCOTIZACIONINFO()
            .numero(DEFAULT_NUMERO)
            .cotizacion(DEFAULT_COTIZACION)
            .region(DEFAULT_REGION)
            .fechaSolicitud(DEFAULT_FECHA_SOLICITUD)
            .responsable(DEFAULT_RESPONSABLE)
            .fechaEntrega(DEFAULT_FECHA_ENTREGA)
            .contratante(DEFAULT_CONTRATANTE)
            .tipoUno(DEFAULT_TIPO_UNO)
            .tipoDos(DEFAULT_TIPO_DOS)
            .poliza(DEFAULT_POLIZA)
            .inicioVigencia(DEFAULT_INICIO_VIGENCIA)
            .finVigencia(DEFAULT_FIN_VIGENCIA)
            .intermediario(DEFAULT_INTERMEDIARIO)
            .rfc(DEFAULT_RFC)
            .ejecutivo(DEFAULT_EJECUTIVO)
            .suscriptor(DEFAULT_SUSCRIPTOR)
            .plan(DEFAULT_PLAN)
            .ocupacion(DEFAULT_OCUPACION)
            .primaCovidVida(DEFAULT_PRIMA_COVID_VIDA)
            .primaCovidGff(DEFAULT_PRIMA_COVID_GFF)
            .descuentoPrimaCovid(DEFAULT_DESCUENTO_PRIMA_COVID)
            .extraPrimaVida(DEFAULT_EXTRA_PRIMA_VIDA)
            .extraPrimaGff(DEFAULT_EXTRA_PRIMA_GFF)
            .porcentajeExtraPrimaVida(DEFAULT_PORCENTAJE_EXTRA_PRIMA_VIDA)
            .porcentajeExtraPrimaGff(DEFAULT_PORCENTAJE_EXTRA_PRIMA_GFF)
            .valorFtr(DEFAULT_VALOR_FTR)
            .sami(DEFAULT_SAMI)
            .samiMin(DEFAULT_SAMI_MIN)
            .samiMax(DEFAULT_SAMI_MAX)
            .margen(DEFAULT_MARGEN)
            .margenMin(DEFAULT_MARGEN_MIN)
            .margenMax(DEFAULT_MARGEN_MAX)
            .comision(DEFAULT_COMISION)
            .comisionMin(DEFAULT_COMISION_MIN)
            .comisionMax(DEFAULT_COMISION_MAX)
            .descuento(DEFAULT_DESCUENTO)
            .descuentoMin(DEFAULT_DESCUENTO_MIN)
            .descuentoMax(DEFAULT_DESCUENTO_MAX)
            .primaneta(DEFAULT_PRIMANETA)
            .primaNetaMin(DEFAULT_PRIMA_NETA_MIN)
            .primaNetaMax(DEFAULT_PRIMA_NETA_MAX)
            .derechoPoliza(DEFAULT_DERECHO_POLIZA)
            .derechoPolizaMin(DEFAULT_DERECHO_POLIZA_MIN)
            .derechoPolizaMax(DEFAULT_DERECHO_POLIZA_MAX)
            .mayores(DEFAULT_MAYORES)
            .mayoresMin(DEFAULT_MAYORES_MIN)
            .mayoresMax(DEFAULT_MAYORES_MAX)
            .asegurados(DEFAULT_ASEGURADOS)
            .aseguradosMin(DEFAULT_ASEGURADOS_MIN)
            .aseguradosMax(DEFAULT_ASEGURADOS_MAX)
            .saPromedio(DEFAULT_SA_PROMEDIO)
            .saPromedioMin(DEFAULT_SA_PROMEDIO_MIN)
            .saPromedioMax(DEFAULT_SA_PROMEDIO_MAX)
            .varSa(DEFAULT_VAR_SA)
            .varSaMin(DEFAULT_VAR_SA_MIN)
            .varSaMax(DEFAULT_VAR_SA_MAX)
            .saTotal(DEFAULT_SA_TOTAL)
            .saMaxima(DEFAULT_SA_MAXIMA)
            .saMaximaMin(DEFAULT_SA_MAXIMA_MIN)
            .saMaximaMax(DEFAULT_SA_MAXIMA_MAX)
            .subgrupos(DEFAULT_SUBGRUPOS)
            .subgruposMin(DEFAULT_SUBGRUPOS_MIN)
            .edadMediaMin(DEFAULT_EDAD_MEDIA_MIN)
            .edadActuarial(DEFAULT_EDAD_ACTUARIAL)
            .edadActuarialMin(DEFAULT_EDAD_ACTUARIAL_MIN)
            .edadActPond(DEFAULT_EDAD_ACT_POND)
            .edadActPondMin(DEFAULT_EDAD_ACT_POND_MIN)
            .edadMin(DEFAULT_EDAD_MIN)
            .edadMinDos(DEFAULT_EDAD_MIN_DOS)
            .edadMaxDos(DEFAULT_EDAD_MAX_DOS)
            .edadMaxTres(DEFAULT_EDAD_MAX_TRES)
            .coeficienteVariacion(DEFAULT_COEFICIENTE_VARIACION)
            .factorTrUnoGiro(DEFAULT_FACTOR_TR_UNO_GIRO)
            .factorSaProm(DEFAULT_FACTOR_SA_PROM)
            .pNetaGlobalSdmc(DEFAULT_P_NETA_GLOBAL_SDMC)
            .pNetaGlobalCdmcCuota(DEFAULT_P_NETA_GLOBAL_CDMC_CUOTA)
            .pNetaGlobalSmccdesc(DEFAULT_P_NETA_GLOBAL_SMCCDESC)
            .pNetaGlobalSmccdescCuota(DEFAULT_P_NETA_GLOBAL_SMCCDESC_CUOTA)
            .pNetaBasicaSdmc(DEFAULT_P_NETA_BASICA_SDMC)
            .pNetaBasicaSdmcCuota(DEFAULT_P_NETA_BASICA_SDMC_CUOTA)
            .pNetaBasicaCdmc(DEFAULT_P_NETA_BASICA_CDMC)
            .pNetaBasicaCdmcCuota(DEFAULT_P_NETA_BASICA_CDMC_CUOTA);
        return tMCOTIZACIONINFO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TMCOTIZACIONINFO createUpdatedEntity(EntityManager em) {
        TMCOTIZACIONINFO tMCOTIZACIONINFO = new TMCOTIZACIONINFO()
            .numero(UPDATED_NUMERO)
            .cotizacion(UPDATED_COTIZACION)
            .region(UPDATED_REGION)
            .fechaSolicitud(UPDATED_FECHA_SOLICITUD)
            .responsable(UPDATED_RESPONSABLE)
            .fechaEntrega(UPDATED_FECHA_ENTREGA)
            .contratante(UPDATED_CONTRATANTE)
            .tipoUno(UPDATED_TIPO_UNO)
            .tipoDos(UPDATED_TIPO_DOS)
            .poliza(UPDATED_POLIZA)
            .inicioVigencia(UPDATED_INICIO_VIGENCIA)
            .finVigencia(UPDATED_FIN_VIGENCIA)
            .intermediario(UPDATED_INTERMEDIARIO)
            .rfc(UPDATED_RFC)
            .ejecutivo(UPDATED_EJECUTIVO)
            .suscriptor(UPDATED_SUSCRIPTOR)
            .plan(UPDATED_PLAN)
            .ocupacion(UPDATED_OCUPACION)
            .primaCovidVida(UPDATED_PRIMA_COVID_VIDA)
            .primaCovidGff(UPDATED_PRIMA_COVID_GFF)
            .descuentoPrimaCovid(UPDATED_DESCUENTO_PRIMA_COVID)
            .extraPrimaVida(UPDATED_EXTRA_PRIMA_VIDA)
            .extraPrimaGff(UPDATED_EXTRA_PRIMA_GFF)
            .porcentajeExtraPrimaVida(UPDATED_PORCENTAJE_EXTRA_PRIMA_VIDA)
            .porcentajeExtraPrimaGff(UPDATED_PORCENTAJE_EXTRA_PRIMA_GFF)
            .valorFtr(UPDATED_VALOR_FTR)
            .sami(UPDATED_SAMI)
            .samiMin(UPDATED_SAMI_MIN)
            .samiMax(UPDATED_SAMI_MAX)
            .margen(UPDATED_MARGEN)
            .margenMin(UPDATED_MARGEN_MIN)
            .margenMax(UPDATED_MARGEN_MAX)
            .comision(UPDATED_COMISION)
            .comisionMin(UPDATED_COMISION_MIN)
            .comisionMax(UPDATED_COMISION_MAX)
            .descuento(UPDATED_DESCUENTO)
            .descuentoMin(UPDATED_DESCUENTO_MIN)
            .descuentoMax(UPDATED_DESCUENTO_MAX)
            .primaneta(UPDATED_PRIMANETA)
            .primaNetaMin(UPDATED_PRIMA_NETA_MIN)
            .primaNetaMax(UPDATED_PRIMA_NETA_MAX)
            .derechoPoliza(UPDATED_DERECHO_POLIZA)
            .derechoPolizaMin(UPDATED_DERECHO_POLIZA_MIN)
            .derechoPolizaMax(UPDATED_DERECHO_POLIZA_MAX)
            .mayores(UPDATED_MAYORES)
            .mayoresMin(UPDATED_MAYORES_MIN)
            .mayoresMax(UPDATED_MAYORES_MAX)
            .asegurados(UPDATED_ASEGURADOS)
            .aseguradosMin(UPDATED_ASEGURADOS_MIN)
            .aseguradosMax(UPDATED_ASEGURADOS_MAX)
            .saPromedio(UPDATED_SA_PROMEDIO)
            .saPromedioMin(UPDATED_SA_PROMEDIO_MIN)
            .saPromedioMax(UPDATED_SA_PROMEDIO_MAX)
            .varSa(UPDATED_VAR_SA)
            .varSaMin(UPDATED_VAR_SA_MIN)
            .varSaMax(UPDATED_VAR_SA_MAX)
            .saTotal(UPDATED_SA_TOTAL)
            .saMaxima(UPDATED_SA_MAXIMA)
            .saMaximaMin(UPDATED_SA_MAXIMA_MIN)
            .saMaximaMax(UPDATED_SA_MAXIMA_MAX)
            .subgrupos(UPDATED_SUBGRUPOS)
            .subgruposMin(UPDATED_SUBGRUPOS_MIN)
            .edadMediaMin(UPDATED_EDAD_MEDIA_MIN)
            .edadActuarial(UPDATED_EDAD_ACTUARIAL)
            .edadActuarialMin(UPDATED_EDAD_ACTUARIAL_MIN)
            .edadActPond(UPDATED_EDAD_ACT_POND)
            .edadActPondMin(UPDATED_EDAD_ACT_POND_MIN)
            .edadMin(UPDATED_EDAD_MIN)
            .edadMinDos(UPDATED_EDAD_MIN_DOS)
            .edadMaxDos(UPDATED_EDAD_MAX_DOS)
            .edadMaxTres(UPDATED_EDAD_MAX_TRES)
            .coeficienteVariacion(UPDATED_COEFICIENTE_VARIACION)
            .factorTrUnoGiro(UPDATED_FACTOR_TR_UNO_GIRO)
            .factorSaProm(UPDATED_FACTOR_SA_PROM)
            .pNetaGlobalSdmc(UPDATED_P_NETA_GLOBAL_SDMC)
            .pNetaGlobalCdmcCuota(UPDATED_P_NETA_GLOBAL_CDMC_CUOTA)
            .pNetaGlobalSmccdesc(UPDATED_P_NETA_GLOBAL_SMCCDESC)
            .pNetaGlobalSmccdescCuota(UPDATED_P_NETA_GLOBAL_SMCCDESC_CUOTA)
            .pNetaBasicaSdmc(UPDATED_P_NETA_BASICA_SDMC)
            .pNetaBasicaSdmcCuota(UPDATED_P_NETA_BASICA_SDMC_CUOTA)
            .pNetaBasicaCdmc(UPDATED_P_NETA_BASICA_CDMC)
            .pNetaBasicaCdmcCuota(UPDATED_P_NETA_BASICA_CDMC_CUOTA);
        return tMCOTIZACIONINFO;
    }

    @BeforeEach
    public void initTest() {
        tMCOTIZACIONINFO = createEntity(em);
    }

    @Test
    @Transactional
    void createTMCOTIZACIONINFO() throws Exception {
        int databaseSizeBeforeCreate = tMCOTIZACIONINFORepository.findAll().size();
        // Create the TMCOTIZACIONINFO
        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isCreated());

        // Validate the TMCOTIZACIONINFO in the database
        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeCreate + 1);
        TMCOTIZACIONINFO testTMCOTIZACIONINFO = tMCOTIZACIONINFOList.get(tMCOTIZACIONINFOList.size() - 1);
        assertThat(testTMCOTIZACIONINFO.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testTMCOTIZACIONINFO.getCotizacion()).isEqualTo(DEFAULT_COTIZACION);
        assertThat(testTMCOTIZACIONINFO.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testTMCOTIZACIONINFO.getFechaSolicitud()).isEqualTo(DEFAULT_FECHA_SOLICITUD);
        assertThat(testTMCOTIZACIONINFO.getResponsable()).isEqualTo(DEFAULT_RESPONSABLE);
        assertThat(testTMCOTIZACIONINFO.getFechaEntrega()).isEqualTo(DEFAULT_FECHA_ENTREGA);
        assertThat(testTMCOTIZACIONINFO.getContratante()).isEqualTo(DEFAULT_CONTRATANTE);
        assertThat(testTMCOTIZACIONINFO.getTipoUno()).isEqualTo(DEFAULT_TIPO_UNO);
        assertThat(testTMCOTIZACIONINFO.getTipoDos()).isEqualTo(DEFAULT_TIPO_DOS);
        assertThat(testTMCOTIZACIONINFO.getPoliza()).isEqualTo(DEFAULT_POLIZA);
        assertThat(testTMCOTIZACIONINFO.getInicioVigencia()).isEqualTo(DEFAULT_INICIO_VIGENCIA);
        assertThat(testTMCOTIZACIONINFO.getFinVigencia()).isEqualTo(DEFAULT_FIN_VIGENCIA);
        assertThat(testTMCOTIZACIONINFO.getIntermediario()).isEqualTo(DEFAULT_INTERMEDIARIO);
        assertThat(testTMCOTIZACIONINFO.getRfc()).isEqualTo(DEFAULT_RFC);
        assertThat(testTMCOTIZACIONINFO.getEjecutivo()).isEqualTo(DEFAULT_EJECUTIVO);
        assertThat(testTMCOTIZACIONINFO.getSuscriptor()).isEqualTo(DEFAULT_SUSCRIPTOR);
        assertThat(testTMCOTIZACIONINFO.getPlan()).isEqualTo(DEFAULT_PLAN);
        assertThat(testTMCOTIZACIONINFO.getOcupacion()).isEqualTo(DEFAULT_OCUPACION);
        assertThat(testTMCOTIZACIONINFO.getPrimaCovidVida()).isEqualTo(DEFAULT_PRIMA_COVID_VIDA);
        assertThat(testTMCOTIZACIONINFO.getPrimaCovidGff()).isEqualTo(DEFAULT_PRIMA_COVID_GFF);
        assertThat(testTMCOTIZACIONINFO.getDescuentoPrimaCovid()).isEqualTo(DEFAULT_DESCUENTO_PRIMA_COVID);
        assertThat(testTMCOTIZACIONINFO.getExtraPrimaVida()).isEqualTo(DEFAULT_EXTRA_PRIMA_VIDA);
        assertThat(testTMCOTIZACIONINFO.getExtraPrimaGff()).isEqualTo(DEFAULT_EXTRA_PRIMA_GFF);
        assertThat(testTMCOTIZACIONINFO.getPorcentajeExtraPrimaVida()).isEqualTo(DEFAULT_PORCENTAJE_EXTRA_PRIMA_VIDA);
        assertThat(testTMCOTIZACIONINFO.getPorcentajeExtraPrimaGff()).isEqualTo(DEFAULT_PORCENTAJE_EXTRA_PRIMA_GFF);
        assertThat(testTMCOTIZACIONINFO.getValorFtr()).isEqualTo(DEFAULT_VALOR_FTR);
        assertThat(testTMCOTIZACIONINFO.getSami()).isEqualTo(DEFAULT_SAMI);
        assertThat(testTMCOTIZACIONINFO.getSamiMin()).isEqualTo(DEFAULT_SAMI_MIN);
        assertThat(testTMCOTIZACIONINFO.getSamiMax()).isEqualTo(DEFAULT_SAMI_MAX);
        assertThat(testTMCOTIZACIONINFO.getMargen()).isEqualTo(DEFAULT_MARGEN);
        assertThat(testTMCOTIZACIONINFO.getMargenMin()).isEqualTo(DEFAULT_MARGEN_MIN);
        assertThat(testTMCOTIZACIONINFO.getMargenMax()).isEqualTo(DEFAULT_MARGEN_MAX);
        assertThat(testTMCOTIZACIONINFO.getComision()).isEqualTo(DEFAULT_COMISION);
        assertThat(testTMCOTIZACIONINFO.getComisionMin()).isEqualTo(DEFAULT_COMISION_MIN);
        assertThat(testTMCOTIZACIONINFO.getComisionMax()).isEqualTo(DEFAULT_COMISION_MAX);
        assertThat(testTMCOTIZACIONINFO.getDescuento()).isEqualTo(DEFAULT_DESCUENTO);
        assertThat(testTMCOTIZACIONINFO.getDescuentoMin()).isEqualTo(DEFAULT_DESCUENTO_MIN);
        assertThat(testTMCOTIZACIONINFO.getDescuentoMax()).isEqualTo(DEFAULT_DESCUENTO_MAX);
        assertThat(testTMCOTIZACIONINFO.getPrimaneta()).isEqualTo(DEFAULT_PRIMANETA);
        assertThat(testTMCOTIZACIONINFO.getPrimaNetaMin()).isEqualTo(DEFAULT_PRIMA_NETA_MIN);
        assertThat(testTMCOTIZACIONINFO.getPrimaNetaMax()).isEqualTo(DEFAULT_PRIMA_NETA_MAX);
        assertThat(testTMCOTIZACIONINFO.getDerechoPoliza()).isEqualTo(DEFAULT_DERECHO_POLIZA);
        assertThat(testTMCOTIZACIONINFO.getDerechoPolizaMin()).isEqualTo(DEFAULT_DERECHO_POLIZA_MIN);
        assertThat(testTMCOTIZACIONINFO.getDerechoPolizaMax()).isEqualTo(DEFAULT_DERECHO_POLIZA_MAX);
        assertThat(testTMCOTIZACIONINFO.getMayores()).isEqualTo(DEFAULT_MAYORES);
        assertThat(testTMCOTIZACIONINFO.getMayoresMin()).isEqualTo(DEFAULT_MAYORES_MIN);
        assertThat(testTMCOTIZACIONINFO.getMayoresMax()).isEqualTo(DEFAULT_MAYORES_MAX);
        assertThat(testTMCOTIZACIONINFO.getAsegurados()).isEqualTo(DEFAULT_ASEGURADOS);
        assertThat(testTMCOTIZACIONINFO.getAseguradosMin()).isEqualTo(DEFAULT_ASEGURADOS_MIN);
        assertThat(testTMCOTIZACIONINFO.getAseguradosMax()).isEqualTo(DEFAULT_ASEGURADOS_MAX);
        assertThat(testTMCOTIZACIONINFO.getSaPromedio()).isEqualTo(DEFAULT_SA_PROMEDIO);
        assertThat(testTMCOTIZACIONINFO.getSaPromedioMin()).isEqualTo(DEFAULT_SA_PROMEDIO_MIN);
        assertThat(testTMCOTIZACIONINFO.getSaPromedioMax()).isEqualTo(DEFAULT_SA_PROMEDIO_MAX);
        assertThat(testTMCOTIZACIONINFO.getVarSa()).isEqualTo(DEFAULT_VAR_SA);
        assertThat(testTMCOTIZACIONINFO.getVarSaMin()).isEqualTo(DEFAULT_VAR_SA_MIN);
        assertThat(testTMCOTIZACIONINFO.getVarSaMax()).isEqualTo(DEFAULT_VAR_SA_MAX);
        assertThat(testTMCOTIZACIONINFO.getSaTotal()).isEqualTo(DEFAULT_SA_TOTAL);
        assertThat(testTMCOTIZACIONINFO.getSaMaxima()).isEqualTo(DEFAULT_SA_MAXIMA);
        assertThat(testTMCOTIZACIONINFO.getSaMaximaMin()).isEqualTo(DEFAULT_SA_MAXIMA_MIN);
        assertThat(testTMCOTIZACIONINFO.getSaMaximaMax()).isEqualTo(DEFAULT_SA_MAXIMA_MAX);
        assertThat(testTMCOTIZACIONINFO.getSubgrupos()).isEqualTo(DEFAULT_SUBGRUPOS);
        assertThat(testTMCOTIZACIONINFO.getSubgruposMin()).isEqualTo(DEFAULT_SUBGRUPOS_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadMediaMin()).isEqualTo(DEFAULT_EDAD_MEDIA_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadActuarial()).isEqualTo(DEFAULT_EDAD_ACTUARIAL);
        assertThat(testTMCOTIZACIONINFO.getEdadActuarialMin()).isEqualTo(DEFAULT_EDAD_ACTUARIAL_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadActPond()).isEqualTo(DEFAULT_EDAD_ACT_POND);
        assertThat(testTMCOTIZACIONINFO.getEdadActPondMin()).isEqualTo(DEFAULT_EDAD_ACT_POND_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadMin()).isEqualTo(DEFAULT_EDAD_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadMinDos()).isEqualTo(DEFAULT_EDAD_MIN_DOS);
        assertThat(testTMCOTIZACIONINFO.getEdadMaxDos()).isEqualTo(DEFAULT_EDAD_MAX_DOS);
        assertThat(testTMCOTIZACIONINFO.getEdadMaxTres()).isEqualTo(DEFAULT_EDAD_MAX_TRES);
        assertThat(testTMCOTIZACIONINFO.getCoeficienteVariacion()).isEqualTo(DEFAULT_COEFICIENTE_VARIACION);
        assertThat(testTMCOTIZACIONINFO.getFactorTrUnoGiro()).isEqualTo(DEFAULT_FACTOR_TR_UNO_GIRO);
        assertThat(testTMCOTIZACIONINFO.getFactorSaProm()).isEqualTo(DEFAULT_FACTOR_SA_PROM);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalSdmc()).isEqualTo(DEFAULT_P_NETA_GLOBAL_SDMC);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalCdmcCuota()).isEqualTo(DEFAULT_P_NETA_GLOBAL_CDMC_CUOTA);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalSmccdesc()).isEqualTo(DEFAULT_P_NETA_GLOBAL_SMCCDESC);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalSmccdescCuota()).isEqualTo(DEFAULT_P_NETA_GLOBAL_SMCCDESC_CUOTA);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaSdmc()).isEqualTo(DEFAULT_P_NETA_BASICA_SDMC);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaSdmcCuota()).isEqualTo(DEFAULT_P_NETA_BASICA_SDMC_CUOTA);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaCdmc()).isEqualTo(DEFAULT_P_NETA_BASICA_CDMC);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaCdmcCuota()).isEqualTo(DEFAULT_P_NETA_BASICA_CDMC_CUOTA);
    }

    @Test
    @Transactional
    void createTMCOTIZACIONINFOWithExistingId() throws Exception {
        // Create the TMCOTIZACIONINFO with an existing ID
        tMCOTIZACIONINFO.setIdCotizacionInfo(1L);

        int databaseSizeBeforeCreate = tMCOTIZACIONINFORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMCOTIZACIONINFO in the database
        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setNumero(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCotizacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setCotizacion(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRegionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setRegion(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFechaSolicitudIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setFechaSolicitud(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkResponsableIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setResponsable(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFechaEntregaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setFechaEntrega(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkContratanteIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setContratante(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTipoUnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setTipoUno(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTipoDosIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setTipoDos(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPolizaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setPoliza(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInicioVigenciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setInicioVigencia(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFinVigenciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setFinVigencia(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIntermediarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setIntermediario(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRfcIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setRfc(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEjecutivoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setEjecutivo(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSuscriptorIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setSuscriptor(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPlanIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setPlan(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkOcupacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setOcupacion(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrimaCovidVidaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setPrimaCovidVida(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrimaCovidGffIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setPrimaCovidGff(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescuentoPrimaCovidIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setDescuentoPrimaCovid(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExtraPrimaVidaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setExtraPrimaVida(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExtraPrimaGffIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setExtraPrimaGff(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPorcentajeExtraPrimaVidaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setPorcentajeExtraPrimaVida(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPorcentajeExtraPrimaGffIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setPorcentajeExtraPrimaGff(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorFtrIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setValorFtr(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSamiIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setSami(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSamiMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setSamiMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSamiMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setSamiMax(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMargenIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setMargen(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMargenMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setMargenMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMargenMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setMargenMax(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkComisionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setComision(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkComisionMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setComisionMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkComisionMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setComisionMax(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescuentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setDescuento(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescuentoMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setDescuentoMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescuentoMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setDescuentoMax(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrimanetaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setPrimaneta(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrimaNetaMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setPrimaNetaMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrimaNetaMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setPrimaNetaMax(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDerechoPolizaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setDerechoPoliza(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDerechoPolizaMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setDerechoPolizaMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDerechoPolizaMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setDerechoPolizaMax(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMayoresIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setMayores(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMayoresMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setMayoresMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMayoresMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setMayoresMax(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAseguradosIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setAsegurados(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAseguradosMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setAseguradosMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAseguradosMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setAseguradosMax(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSaPromedioIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setSaPromedio(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSaPromedioMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setSaPromedioMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSaPromedioMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setSaPromedioMax(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkVarSaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setVarSa(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkVarSaMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setVarSaMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkVarSaMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setVarSaMax(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSaTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setSaTotal(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSaMaximaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setSaMaxima(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSaMaximaMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setSaMaximaMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSaMaximaMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setSaMaximaMax(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubgruposIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setSubgrupos(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubgruposMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setSubgruposMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEdadMediaMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setEdadMediaMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEdadActuarialIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setEdadActuarial(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEdadActuarialMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setEdadActuarialMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEdadActPondIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setEdadActPond(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEdadActPondMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setEdadActPondMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEdadMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setEdadMin(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEdadMinDosIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setEdadMinDos(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEdadMaxDosIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setEdadMaxDos(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEdadMaxTresIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setEdadMaxTres(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCoeficienteVariacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setCoeficienteVariacion(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFactorTrUnoGiroIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setFactorTrUnoGiro(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFactorSaPromIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setFactorSaProm(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkpNetaGlobalSdmcIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setpNetaGlobalSdmc(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkpNetaGlobalCdmcCuotaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setpNetaGlobalCdmcCuota(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkpNetaGlobalSmccdescIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setpNetaGlobalSmccdesc(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkpNetaGlobalSmccdescCuotaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setpNetaGlobalSmccdescCuota(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkpNetaBasicaSdmcIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setpNetaBasicaSdmc(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkpNetaBasicaSdmcCuotaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setpNetaBasicaSdmcCuota(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkpNetaBasicaCdmcIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setpNetaBasicaCdmc(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkpNetaBasicaCdmcCuotaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONINFORepository.findAll().size();
        // set the field null
        tMCOTIZACIONINFO.setpNetaBasicaCdmcCuota(null);

        // Create the TMCOTIZACIONINFO, which fails.

        restTMCOTIZACIONINFOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTMCOTIZACIONINFOS() throws Exception {
        // Initialize the database
        tMCOTIZACIONINFORepository.saveAndFlush(tMCOTIZACIONINFO);

        // Get all the tMCOTIZACIONINFOList
        restTMCOTIZACIONINFOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCotizacionInfo,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCotizacionInfo").value(hasItem(tMCOTIZACIONINFO.getIdCotizacionInfo().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())))
            .andExpect(jsonPath("$.[*].cotizacion").value(hasItem(DEFAULT_COTIZACION.intValue())))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION.intValue())))
            .andExpect(jsonPath("$.[*].fechaSolicitud").value(hasItem(DEFAULT_FECHA_SOLICITUD.toString())))
            .andExpect(jsonPath("$.[*].responsable").value(hasItem(DEFAULT_RESPONSABLE.intValue())))
            .andExpect(jsonPath("$.[*].fechaEntrega").value(hasItem(DEFAULT_FECHA_ENTREGA.toString())))
            .andExpect(jsonPath("$.[*].contratante").value(hasItem(DEFAULT_CONTRATANTE.intValue())))
            .andExpect(jsonPath("$.[*].tipoUno").value(hasItem(DEFAULT_TIPO_UNO.intValue())))
            .andExpect(jsonPath("$.[*].tipoDos").value(hasItem(DEFAULT_TIPO_DOS.intValue())))
            .andExpect(jsonPath("$.[*].poliza").value(hasItem(DEFAULT_POLIZA.intValue())))
            .andExpect(jsonPath("$.[*].inicioVigencia").value(hasItem(DEFAULT_INICIO_VIGENCIA.toString())))
            .andExpect(jsonPath("$.[*].finVigencia").value(hasItem(DEFAULT_FIN_VIGENCIA.toString())))
            .andExpect(jsonPath("$.[*].intermediario").value(hasItem(DEFAULT_INTERMEDIARIO)))
            .andExpect(jsonPath("$.[*].rfc").value(hasItem(DEFAULT_RFC)))
            .andExpect(jsonPath("$.[*].ejecutivo").value(hasItem(DEFAULT_EJECUTIVO.intValue())))
            .andExpect(jsonPath("$.[*].suscriptor").value(hasItem(DEFAULT_SUSCRIPTOR.intValue())))
            .andExpect(jsonPath("$.[*].plan").value(hasItem(DEFAULT_PLAN.intValue())))
            .andExpect(jsonPath("$.[*].ocupacion").value(hasItem(DEFAULT_OCUPACION.intValue())))
            .andExpect(jsonPath("$.[*].primaCovidVida").value(hasItem(DEFAULT_PRIMA_COVID_VIDA.intValue())))
            .andExpect(jsonPath("$.[*].primaCovidGff").value(hasItem(DEFAULT_PRIMA_COVID_GFF.intValue())))
            .andExpect(jsonPath("$.[*].descuentoPrimaCovid").value(hasItem(DEFAULT_DESCUENTO_PRIMA_COVID.intValue())))
            .andExpect(jsonPath("$.[*].extraPrimaVida").value(hasItem(DEFAULT_EXTRA_PRIMA_VIDA.intValue())))
            .andExpect(jsonPath("$.[*].extraPrimaGff").value(hasItem(DEFAULT_EXTRA_PRIMA_GFF.intValue())))
            .andExpect(jsonPath("$.[*].porcentajeExtraPrimaVida").value(hasItem(DEFAULT_PORCENTAJE_EXTRA_PRIMA_VIDA.intValue())))
            .andExpect(jsonPath("$.[*].porcentajeExtraPrimaGff").value(hasItem(DEFAULT_PORCENTAJE_EXTRA_PRIMA_GFF.intValue())))
            .andExpect(jsonPath("$.[*].valorFtr").value(hasItem(DEFAULT_VALOR_FTR.intValue())))
            .andExpect(jsonPath("$.[*].sami").value(hasItem(DEFAULT_SAMI.intValue())))
            .andExpect(jsonPath("$.[*].samiMin").value(hasItem(DEFAULT_SAMI_MIN.intValue())))
            .andExpect(jsonPath("$.[*].samiMax").value(hasItem(DEFAULT_SAMI_MAX.intValue())))
            .andExpect(jsonPath("$.[*].margen").value(hasItem(DEFAULT_MARGEN.intValue())))
            .andExpect(jsonPath("$.[*].margenMin").value(hasItem(DEFAULT_MARGEN_MIN.intValue())))
            .andExpect(jsonPath("$.[*].margenMax").value(hasItem(DEFAULT_MARGEN_MAX.intValue())))
            .andExpect(jsonPath("$.[*].comision").value(hasItem(DEFAULT_COMISION.intValue())))
            .andExpect(jsonPath("$.[*].comisionMin").value(hasItem(DEFAULT_COMISION_MIN.intValue())))
            .andExpect(jsonPath("$.[*].comisionMax").value(hasItem(DEFAULT_COMISION_MAX.intValue())))
            .andExpect(jsonPath("$.[*].descuento").value(hasItem(DEFAULT_DESCUENTO.intValue())))
            .andExpect(jsonPath("$.[*].descuentoMin").value(hasItem(DEFAULT_DESCUENTO_MIN.intValue())))
            .andExpect(jsonPath("$.[*].descuentoMax").value(hasItem(DEFAULT_DESCUENTO_MAX.intValue())))
            .andExpect(jsonPath("$.[*].primaneta").value(hasItem(DEFAULT_PRIMANETA.intValue())))
            .andExpect(jsonPath("$.[*].primaNetaMin").value(hasItem(DEFAULT_PRIMA_NETA_MIN.intValue())))
            .andExpect(jsonPath("$.[*].primaNetaMax").value(hasItem(DEFAULT_PRIMA_NETA_MAX.intValue())))
            .andExpect(jsonPath("$.[*].derechoPoliza").value(hasItem(DEFAULT_DERECHO_POLIZA.intValue())))
            .andExpect(jsonPath("$.[*].derechoPolizaMin").value(hasItem(DEFAULT_DERECHO_POLIZA_MIN.intValue())))
            .andExpect(jsonPath("$.[*].derechoPolizaMax").value(hasItem(DEFAULT_DERECHO_POLIZA_MAX.intValue())))
            .andExpect(jsonPath("$.[*].mayores").value(hasItem(DEFAULT_MAYORES.intValue())))
            .andExpect(jsonPath("$.[*].mayoresMin").value(hasItem(DEFAULT_MAYORES_MIN.intValue())))
            .andExpect(jsonPath("$.[*].mayoresMax").value(hasItem(DEFAULT_MAYORES_MAX.intValue())))
            .andExpect(jsonPath("$.[*].asegurados").value(hasItem(DEFAULT_ASEGURADOS.intValue())))
            .andExpect(jsonPath("$.[*].aseguradosMin").value(hasItem(DEFAULT_ASEGURADOS_MIN.intValue())))
            .andExpect(jsonPath("$.[*].aseguradosMax").value(hasItem(DEFAULT_ASEGURADOS_MAX.intValue())))
            .andExpect(jsonPath("$.[*].saPromedio").value(hasItem(DEFAULT_SA_PROMEDIO.intValue())))
            .andExpect(jsonPath("$.[*].saPromedioMin").value(hasItem(DEFAULT_SA_PROMEDIO_MIN.intValue())))
            .andExpect(jsonPath("$.[*].saPromedioMax").value(hasItem(DEFAULT_SA_PROMEDIO_MAX.intValue())))
            .andExpect(jsonPath("$.[*].varSa").value(hasItem(DEFAULT_VAR_SA.intValue())))
            .andExpect(jsonPath("$.[*].varSaMin").value(hasItem(DEFAULT_VAR_SA_MIN.intValue())))
            .andExpect(jsonPath("$.[*].varSaMax").value(hasItem(DEFAULT_VAR_SA_MAX.intValue())))
            .andExpect(jsonPath("$.[*].saTotal").value(hasItem(DEFAULT_SA_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].saMaxima").value(hasItem(DEFAULT_SA_MAXIMA.intValue())))
            .andExpect(jsonPath("$.[*].saMaximaMin").value(hasItem(DEFAULT_SA_MAXIMA_MIN.intValue())))
            .andExpect(jsonPath("$.[*].saMaximaMax").value(hasItem(DEFAULT_SA_MAXIMA_MAX.intValue())))
            .andExpect(jsonPath("$.[*].subgrupos").value(hasItem(DEFAULT_SUBGRUPOS.intValue())))
            .andExpect(jsonPath("$.[*].subgruposMin").value(hasItem(DEFAULT_SUBGRUPOS_MIN.intValue())))
            .andExpect(jsonPath("$.[*].edadMediaMin").value(hasItem(DEFAULT_EDAD_MEDIA_MIN.intValue())))
            .andExpect(jsonPath("$.[*].edadActuarial").value(hasItem(DEFAULT_EDAD_ACTUARIAL.intValue())))
            .andExpect(jsonPath("$.[*].edadActuarialMin").value(hasItem(DEFAULT_EDAD_ACTUARIAL_MIN.intValue())))
            .andExpect(jsonPath("$.[*].edadActPond").value(hasItem(DEFAULT_EDAD_ACT_POND.intValue())))
            .andExpect(jsonPath("$.[*].edadActPondMin").value(hasItem(DEFAULT_EDAD_ACT_POND_MIN.intValue())))
            .andExpect(jsonPath("$.[*].edadMin").value(hasItem(DEFAULT_EDAD_MIN.intValue())))
            .andExpect(jsonPath("$.[*].edadMinDos").value(hasItem(DEFAULT_EDAD_MIN_DOS.intValue())))
            .andExpect(jsonPath("$.[*].edadMaxDos").value(hasItem(DEFAULT_EDAD_MAX_DOS.intValue())))
            .andExpect(jsonPath("$.[*].edadMaxTres").value(hasItem(DEFAULT_EDAD_MAX_TRES.intValue())))
            .andExpect(jsonPath("$.[*].coeficienteVariacion").value(hasItem(DEFAULT_COEFICIENTE_VARIACION.intValue())))
            .andExpect(jsonPath("$.[*].factorTrUnoGiro").value(hasItem(DEFAULT_FACTOR_TR_UNO_GIRO.intValue())))
            .andExpect(jsonPath("$.[*].factorSaProm").value(hasItem(DEFAULT_FACTOR_SA_PROM.intValue())))
            .andExpect(jsonPath("$.[*].pNetaGlobalSdmc").value(hasItem(DEFAULT_P_NETA_GLOBAL_SDMC.intValue())))
            .andExpect(jsonPath("$.[*].pNetaGlobalCdmcCuota").value(hasItem(DEFAULT_P_NETA_GLOBAL_CDMC_CUOTA.intValue())))
            .andExpect(jsonPath("$.[*].pNetaGlobalSmccdesc").value(hasItem(DEFAULT_P_NETA_GLOBAL_SMCCDESC.intValue())))
            .andExpect(jsonPath("$.[*].pNetaGlobalSmccdescCuota").value(hasItem(DEFAULT_P_NETA_GLOBAL_SMCCDESC_CUOTA.intValue())))
            .andExpect(jsonPath("$.[*].pNetaBasicaSdmc").value(hasItem(DEFAULT_P_NETA_BASICA_SDMC.intValue())))
            .andExpect(jsonPath("$.[*].pNetaBasicaSdmcCuota").value(hasItem(DEFAULT_P_NETA_BASICA_SDMC_CUOTA.intValue())))
            .andExpect(jsonPath("$.[*].pNetaBasicaCdmc").value(hasItem(DEFAULT_P_NETA_BASICA_CDMC.intValue())))
            .andExpect(jsonPath("$.[*].pNetaBasicaCdmcCuota").value(hasItem(DEFAULT_P_NETA_BASICA_CDMC_CUOTA.intValue())));
    }

    @Test
    @Transactional
    void getTMCOTIZACIONINFO() throws Exception {
        // Initialize the database
        tMCOTIZACIONINFORepository.saveAndFlush(tMCOTIZACIONINFO);

        // Get the tMCOTIZACIONINFO
        restTMCOTIZACIONINFOMockMvc
            .perform(get(ENTITY_API_URL_ID, tMCOTIZACIONINFO.getIdCotizacionInfo()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCotizacionInfo").value(tMCOTIZACIONINFO.getIdCotizacionInfo().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.intValue()))
            .andExpect(jsonPath("$.cotizacion").value(DEFAULT_COTIZACION.intValue()))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION.intValue()))
            .andExpect(jsonPath("$.fechaSolicitud").value(DEFAULT_FECHA_SOLICITUD.toString()))
            .andExpect(jsonPath("$.responsable").value(DEFAULT_RESPONSABLE.intValue()))
            .andExpect(jsonPath("$.fechaEntrega").value(DEFAULT_FECHA_ENTREGA.toString()))
            .andExpect(jsonPath("$.contratante").value(DEFAULT_CONTRATANTE.intValue()))
            .andExpect(jsonPath("$.tipoUno").value(DEFAULT_TIPO_UNO.intValue()))
            .andExpect(jsonPath("$.tipoDos").value(DEFAULT_TIPO_DOS.intValue()))
            .andExpect(jsonPath("$.poliza").value(DEFAULT_POLIZA.intValue()))
            .andExpect(jsonPath("$.inicioVigencia").value(DEFAULT_INICIO_VIGENCIA.toString()))
            .andExpect(jsonPath("$.finVigencia").value(DEFAULT_FIN_VIGENCIA.toString()))
            .andExpect(jsonPath("$.intermediario").value(DEFAULT_INTERMEDIARIO))
            .andExpect(jsonPath("$.rfc").value(DEFAULT_RFC))
            .andExpect(jsonPath("$.ejecutivo").value(DEFAULT_EJECUTIVO.intValue()))
            .andExpect(jsonPath("$.suscriptor").value(DEFAULT_SUSCRIPTOR.intValue()))
            .andExpect(jsonPath("$.plan").value(DEFAULT_PLAN.intValue()))
            .andExpect(jsonPath("$.ocupacion").value(DEFAULT_OCUPACION.intValue()))
            .andExpect(jsonPath("$.primaCovidVida").value(DEFAULT_PRIMA_COVID_VIDA.intValue()))
            .andExpect(jsonPath("$.primaCovidGff").value(DEFAULT_PRIMA_COVID_GFF.intValue()))
            .andExpect(jsonPath("$.descuentoPrimaCovid").value(DEFAULT_DESCUENTO_PRIMA_COVID.intValue()))
            .andExpect(jsonPath("$.extraPrimaVida").value(DEFAULT_EXTRA_PRIMA_VIDA.intValue()))
            .andExpect(jsonPath("$.extraPrimaGff").value(DEFAULT_EXTRA_PRIMA_GFF.intValue()))
            .andExpect(jsonPath("$.porcentajeExtraPrimaVida").value(DEFAULT_PORCENTAJE_EXTRA_PRIMA_VIDA.intValue()))
            .andExpect(jsonPath("$.porcentajeExtraPrimaGff").value(DEFAULT_PORCENTAJE_EXTRA_PRIMA_GFF.intValue()))
            .andExpect(jsonPath("$.valorFtr").value(DEFAULT_VALOR_FTR.intValue()))
            .andExpect(jsonPath("$.sami").value(DEFAULT_SAMI.intValue()))
            .andExpect(jsonPath("$.samiMin").value(DEFAULT_SAMI_MIN.intValue()))
            .andExpect(jsonPath("$.samiMax").value(DEFAULT_SAMI_MAX.intValue()))
            .andExpect(jsonPath("$.margen").value(DEFAULT_MARGEN.intValue()))
            .andExpect(jsonPath("$.margenMin").value(DEFAULT_MARGEN_MIN.intValue()))
            .andExpect(jsonPath("$.margenMax").value(DEFAULT_MARGEN_MAX.intValue()))
            .andExpect(jsonPath("$.comision").value(DEFAULT_COMISION.intValue()))
            .andExpect(jsonPath("$.comisionMin").value(DEFAULT_COMISION_MIN.intValue()))
            .andExpect(jsonPath("$.comisionMax").value(DEFAULT_COMISION_MAX.intValue()))
            .andExpect(jsonPath("$.descuento").value(DEFAULT_DESCUENTO.intValue()))
            .andExpect(jsonPath("$.descuentoMin").value(DEFAULT_DESCUENTO_MIN.intValue()))
            .andExpect(jsonPath("$.descuentoMax").value(DEFAULT_DESCUENTO_MAX.intValue()))
            .andExpect(jsonPath("$.primaneta").value(DEFAULT_PRIMANETA.intValue()))
            .andExpect(jsonPath("$.primaNetaMin").value(DEFAULT_PRIMA_NETA_MIN.intValue()))
            .andExpect(jsonPath("$.primaNetaMax").value(DEFAULT_PRIMA_NETA_MAX.intValue()))
            .andExpect(jsonPath("$.derechoPoliza").value(DEFAULT_DERECHO_POLIZA.intValue()))
            .andExpect(jsonPath("$.derechoPolizaMin").value(DEFAULT_DERECHO_POLIZA_MIN.intValue()))
            .andExpect(jsonPath("$.derechoPolizaMax").value(DEFAULT_DERECHO_POLIZA_MAX.intValue()))
            .andExpect(jsonPath("$.mayores").value(DEFAULT_MAYORES.intValue()))
            .andExpect(jsonPath("$.mayoresMin").value(DEFAULT_MAYORES_MIN.intValue()))
            .andExpect(jsonPath("$.mayoresMax").value(DEFAULT_MAYORES_MAX.intValue()))
            .andExpect(jsonPath("$.asegurados").value(DEFAULT_ASEGURADOS.intValue()))
            .andExpect(jsonPath("$.aseguradosMin").value(DEFAULT_ASEGURADOS_MIN.intValue()))
            .andExpect(jsonPath("$.aseguradosMax").value(DEFAULT_ASEGURADOS_MAX.intValue()))
            .andExpect(jsonPath("$.saPromedio").value(DEFAULT_SA_PROMEDIO.intValue()))
            .andExpect(jsonPath("$.saPromedioMin").value(DEFAULT_SA_PROMEDIO_MIN.intValue()))
            .andExpect(jsonPath("$.saPromedioMax").value(DEFAULT_SA_PROMEDIO_MAX.intValue()))
            .andExpect(jsonPath("$.varSa").value(DEFAULT_VAR_SA.intValue()))
            .andExpect(jsonPath("$.varSaMin").value(DEFAULT_VAR_SA_MIN.intValue()))
            .andExpect(jsonPath("$.varSaMax").value(DEFAULT_VAR_SA_MAX.intValue()))
            .andExpect(jsonPath("$.saTotal").value(DEFAULT_SA_TOTAL.intValue()))
            .andExpect(jsonPath("$.saMaxima").value(DEFAULT_SA_MAXIMA.intValue()))
            .andExpect(jsonPath("$.saMaximaMin").value(DEFAULT_SA_MAXIMA_MIN.intValue()))
            .andExpect(jsonPath("$.saMaximaMax").value(DEFAULT_SA_MAXIMA_MAX.intValue()))
            .andExpect(jsonPath("$.subgrupos").value(DEFAULT_SUBGRUPOS.intValue()))
            .andExpect(jsonPath("$.subgruposMin").value(DEFAULT_SUBGRUPOS_MIN.intValue()))
            .andExpect(jsonPath("$.edadMediaMin").value(DEFAULT_EDAD_MEDIA_MIN.intValue()))
            .andExpect(jsonPath("$.edadActuarial").value(DEFAULT_EDAD_ACTUARIAL.intValue()))
            .andExpect(jsonPath("$.edadActuarialMin").value(DEFAULT_EDAD_ACTUARIAL_MIN.intValue()))
            .andExpect(jsonPath("$.edadActPond").value(DEFAULT_EDAD_ACT_POND.intValue()))
            .andExpect(jsonPath("$.edadActPondMin").value(DEFAULT_EDAD_ACT_POND_MIN.intValue()))
            .andExpect(jsonPath("$.edadMin").value(DEFAULT_EDAD_MIN.intValue()))
            .andExpect(jsonPath("$.edadMinDos").value(DEFAULT_EDAD_MIN_DOS.intValue()))
            .andExpect(jsonPath("$.edadMaxDos").value(DEFAULT_EDAD_MAX_DOS.intValue()))
            .andExpect(jsonPath("$.edadMaxTres").value(DEFAULT_EDAD_MAX_TRES.intValue()))
            .andExpect(jsonPath("$.coeficienteVariacion").value(DEFAULT_COEFICIENTE_VARIACION.intValue()))
            .andExpect(jsonPath("$.factorTrUnoGiro").value(DEFAULT_FACTOR_TR_UNO_GIRO.intValue()))
            .andExpect(jsonPath("$.factorSaProm").value(DEFAULT_FACTOR_SA_PROM.intValue()))
            .andExpect(jsonPath("$.pNetaGlobalSdmc").value(DEFAULT_P_NETA_GLOBAL_SDMC.intValue()))
            .andExpect(jsonPath("$.pNetaGlobalCdmcCuota").value(DEFAULT_P_NETA_GLOBAL_CDMC_CUOTA.intValue()))
            .andExpect(jsonPath("$.pNetaGlobalSmccdesc").value(DEFAULT_P_NETA_GLOBAL_SMCCDESC.intValue()))
            .andExpect(jsonPath("$.pNetaGlobalSmccdescCuota").value(DEFAULT_P_NETA_GLOBAL_SMCCDESC_CUOTA.intValue()))
            .andExpect(jsonPath("$.pNetaBasicaSdmc").value(DEFAULT_P_NETA_BASICA_SDMC.intValue()))
            .andExpect(jsonPath("$.pNetaBasicaSdmcCuota").value(DEFAULT_P_NETA_BASICA_SDMC_CUOTA.intValue()))
            .andExpect(jsonPath("$.pNetaBasicaCdmc").value(DEFAULT_P_NETA_BASICA_CDMC.intValue()))
            .andExpect(jsonPath("$.pNetaBasicaCdmcCuota").value(DEFAULT_P_NETA_BASICA_CDMC_CUOTA.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTMCOTIZACIONINFO() throws Exception {
        // Get the tMCOTIZACIONINFO
        restTMCOTIZACIONINFOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTMCOTIZACIONINFO() throws Exception {
        // Initialize the database
        tMCOTIZACIONINFORepository.saveAndFlush(tMCOTIZACIONINFO);

        int databaseSizeBeforeUpdate = tMCOTIZACIONINFORepository.findAll().size();

        // Update the tMCOTIZACIONINFO
        TMCOTIZACIONINFO updatedTMCOTIZACIONINFO = tMCOTIZACIONINFORepository.findById(tMCOTIZACIONINFO.getIdCotizacionInfo()).get();
        // Disconnect from session so that the updates on updatedTMCOTIZACIONINFO are not directly saved in db
        em.detach(updatedTMCOTIZACIONINFO);
        updatedTMCOTIZACIONINFO
            .numero(UPDATED_NUMERO)
            .cotizacion(UPDATED_COTIZACION)
            .region(UPDATED_REGION)
            .fechaSolicitud(UPDATED_FECHA_SOLICITUD)
            .responsable(UPDATED_RESPONSABLE)
            .fechaEntrega(UPDATED_FECHA_ENTREGA)
            .contratante(UPDATED_CONTRATANTE)
            .tipoUno(UPDATED_TIPO_UNO)
            .tipoDos(UPDATED_TIPO_DOS)
            .poliza(UPDATED_POLIZA)
            .inicioVigencia(UPDATED_INICIO_VIGENCIA)
            .finVigencia(UPDATED_FIN_VIGENCIA)
            .intermediario(UPDATED_INTERMEDIARIO)
            .rfc(UPDATED_RFC)
            .ejecutivo(UPDATED_EJECUTIVO)
            .suscriptor(UPDATED_SUSCRIPTOR)
            .plan(UPDATED_PLAN)
            .ocupacion(UPDATED_OCUPACION)
            .primaCovidVida(UPDATED_PRIMA_COVID_VIDA)
            .primaCovidGff(UPDATED_PRIMA_COVID_GFF)
            .descuentoPrimaCovid(UPDATED_DESCUENTO_PRIMA_COVID)
            .extraPrimaVida(UPDATED_EXTRA_PRIMA_VIDA)
            .extraPrimaGff(UPDATED_EXTRA_PRIMA_GFF)
            .porcentajeExtraPrimaVida(UPDATED_PORCENTAJE_EXTRA_PRIMA_VIDA)
            .porcentajeExtraPrimaGff(UPDATED_PORCENTAJE_EXTRA_PRIMA_GFF)
            .valorFtr(UPDATED_VALOR_FTR)
            .sami(UPDATED_SAMI)
            .samiMin(UPDATED_SAMI_MIN)
            .samiMax(UPDATED_SAMI_MAX)
            .margen(UPDATED_MARGEN)
            .margenMin(UPDATED_MARGEN_MIN)
            .margenMax(UPDATED_MARGEN_MAX)
            .comision(UPDATED_COMISION)
            .comisionMin(UPDATED_COMISION_MIN)
            .comisionMax(UPDATED_COMISION_MAX)
            .descuento(UPDATED_DESCUENTO)
            .descuentoMin(UPDATED_DESCUENTO_MIN)
            .descuentoMax(UPDATED_DESCUENTO_MAX)
            .primaneta(UPDATED_PRIMANETA)
            .primaNetaMin(UPDATED_PRIMA_NETA_MIN)
            .primaNetaMax(UPDATED_PRIMA_NETA_MAX)
            .derechoPoliza(UPDATED_DERECHO_POLIZA)
            .derechoPolizaMin(UPDATED_DERECHO_POLIZA_MIN)
            .derechoPolizaMax(UPDATED_DERECHO_POLIZA_MAX)
            .mayores(UPDATED_MAYORES)
            .mayoresMin(UPDATED_MAYORES_MIN)
            .mayoresMax(UPDATED_MAYORES_MAX)
            .asegurados(UPDATED_ASEGURADOS)
            .aseguradosMin(UPDATED_ASEGURADOS_MIN)
            .aseguradosMax(UPDATED_ASEGURADOS_MAX)
            .saPromedio(UPDATED_SA_PROMEDIO)
            .saPromedioMin(UPDATED_SA_PROMEDIO_MIN)
            .saPromedioMax(UPDATED_SA_PROMEDIO_MAX)
            .varSa(UPDATED_VAR_SA)
            .varSaMin(UPDATED_VAR_SA_MIN)
            .varSaMax(UPDATED_VAR_SA_MAX)
            .saTotal(UPDATED_SA_TOTAL)
            .saMaxima(UPDATED_SA_MAXIMA)
            .saMaximaMin(UPDATED_SA_MAXIMA_MIN)
            .saMaximaMax(UPDATED_SA_MAXIMA_MAX)
            .subgrupos(UPDATED_SUBGRUPOS)
            .subgruposMin(UPDATED_SUBGRUPOS_MIN)
            .edadMediaMin(UPDATED_EDAD_MEDIA_MIN)
            .edadActuarial(UPDATED_EDAD_ACTUARIAL)
            .edadActuarialMin(UPDATED_EDAD_ACTUARIAL_MIN)
            .edadActPond(UPDATED_EDAD_ACT_POND)
            .edadActPondMin(UPDATED_EDAD_ACT_POND_MIN)
            .edadMin(UPDATED_EDAD_MIN)
            .edadMinDos(UPDATED_EDAD_MIN_DOS)
            .edadMaxDos(UPDATED_EDAD_MAX_DOS)
            .edadMaxTres(UPDATED_EDAD_MAX_TRES)
            .coeficienteVariacion(UPDATED_COEFICIENTE_VARIACION)
            .factorTrUnoGiro(UPDATED_FACTOR_TR_UNO_GIRO)
            .factorSaProm(UPDATED_FACTOR_SA_PROM)
            .pNetaGlobalSdmc(UPDATED_P_NETA_GLOBAL_SDMC)
            .pNetaGlobalCdmcCuota(UPDATED_P_NETA_GLOBAL_CDMC_CUOTA)
            .pNetaGlobalSmccdesc(UPDATED_P_NETA_GLOBAL_SMCCDESC)
            .pNetaGlobalSmccdescCuota(UPDATED_P_NETA_GLOBAL_SMCCDESC_CUOTA)
            .pNetaBasicaSdmc(UPDATED_P_NETA_BASICA_SDMC)
            .pNetaBasicaSdmcCuota(UPDATED_P_NETA_BASICA_SDMC_CUOTA)
            .pNetaBasicaCdmc(UPDATED_P_NETA_BASICA_CDMC)
            .pNetaBasicaCdmcCuota(UPDATED_P_NETA_BASICA_CDMC_CUOTA);

        restTMCOTIZACIONINFOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTMCOTIZACIONINFO.getIdCotizacionInfo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTMCOTIZACIONINFO))
            )
            .andExpect(status().isOk());

        // Validate the TMCOTIZACIONINFO in the database
        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeUpdate);
        TMCOTIZACIONINFO testTMCOTIZACIONINFO = tMCOTIZACIONINFOList.get(tMCOTIZACIONINFOList.size() - 1);
        assertThat(testTMCOTIZACIONINFO.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testTMCOTIZACIONINFO.getCotizacion()).isEqualTo(UPDATED_COTIZACION);
        assertThat(testTMCOTIZACIONINFO.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testTMCOTIZACIONINFO.getFechaSolicitud()).isEqualTo(UPDATED_FECHA_SOLICITUD);
        assertThat(testTMCOTIZACIONINFO.getResponsable()).isEqualTo(UPDATED_RESPONSABLE);
        assertThat(testTMCOTIZACIONINFO.getFechaEntrega()).isEqualTo(UPDATED_FECHA_ENTREGA);
        assertThat(testTMCOTIZACIONINFO.getContratante()).isEqualTo(UPDATED_CONTRATANTE);
        assertThat(testTMCOTIZACIONINFO.getTipoUno()).isEqualTo(UPDATED_TIPO_UNO);
        assertThat(testTMCOTIZACIONINFO.getTipoDos()).isEqualTo(UPDATED_TIPO_DOS);
        assertThat(testTMCOTIZACIONINFO.getPoliza()).isEqualTo(UPDATED_POLIZA);
        assertThat(testTMCOTIZACIONINFO.getInicioVigencia()).isEqualTo(UPDATED_INICIO_VIGENCIA);
        assertThat(testTMCOTIZACIONINFO.getFinVigencia()).isEqualTo(UPDATED_FIN_VIGENCIA);
        assertThat(testTMCOTIZACIONINFO.getIntermediario()).isEqualTo(UPDATED_INTERMEDIARIO);
        assertThat(testTMCOTIZACIONINFO.getRfc()).isEqualTo(UPDATED_RFC);
        assertThat(testTMCOTIZACIONINFO.getEjecutivo()).isEqualTo(UPDATED_EJECUTIVO);
        assertThat(testTMCOTIZACIONINFO.getSuscriptor()).isEqualTo(UPDATED_SUSCRIPTOR);
        assertThat(testTMCOTIZACIONINFO.getPlan()).isEqualTo(UPDATED_PLAN);
        assertThat(testTMCOTIZACIONINFO.getOcupacion()).isEqualTo(UPDATED_OCUPACION);
        assertThat(testTMCOTIZACIONINFO.getPrimaCovidVida()).isEqualTo(UPDATED_PRIMA_COVID_VIDA);
        assertThat(testTMCOTIZACIONINFO.getPrimaCovidGff()).isEqualTo(UPDATED_PRIMA_COVID_GFF);
        assertThat(testTMCOTIZACIONINFO.getDescuentoPrimaCovid()).isEqualTo(UPDATED_DESCUENTO_PRIMA_COVID);
        assertThat(testTMCOTIZACIONINFO.getExtraPrimaVida()).isEqualTo(UPDATED_EXTRA_PRIMA_VIDA);
        assertThat(testTMCOTIZACIONINFO.getExtraPrimaGff()).isEqualTo(UPDATED_EXTRA_PRIMA_GFF);
        assertThat(testTMCOTIZACIONINFO.getPorcentajeExtraPrimaVida()).isEqualTo(UPDATED_PORCENTAJE_EXTRA_PRIMA_VIDA);
        assertThat(testTMCOTIZACIONINFO.getPorcentajeExtraPrimaGff()).isEqualTo(UPDATED_PORCENTAJE_EXTRA_PRIMA_GFF);
        assertThat(testTMCOTIZACIONINFO.getValorFtr()).isEqualTo(UPDATED_VALOR_FTR);
        assertThat(testTMCOTIZACIONINFO.getSami()).isEqualTo(UPDATED_SAMI);
        assertThat(testTMCOTIZACIONINFO.getSamiMin()).isEqualTo(UPDATED_SAMI_MIN);
        assertThat(testTMCOTIZACIONINFO.getSamiMax()).isEqualTo(UPDATED_SAMI_MAX);
        assertThat(testTMCOTIZACIONINFO.getMargen()).isEqualTo(UPDATED_MARGEN);
        assertThat(testTMCOTIZACIONINFO.getMargenMin()).isEqualTo(UPDATED_MARGEN_MIN);
        assertThat(testTMCOTIZACIONINFO.getMargenMax()).isEqualTo(UPDATED_MARGEN_MAX);
        assertThat(testTMCOTIZACIONINFO.getComision()).isEqualTo(UPDATED_COMISION);
        assertThat(testTMCOTIZACIONINFO.getComisionMin()).isEqualTo(UPDATED_COMISION_MIN);
        assertThat(testTMCOTIZACIONINFO.getComisionMax()).isEqualTo(UPDATED_COMISION_MAX);
        assertThat(testTMCOTIZACIONINFO.getDescuento()).isEqualTo(UPDATED_DESCUENTO);
        assertThat(testTMCOTIZACIONINFO.getDescuentoMin()).isEqualTo(UPDATED_DESCUENTO_MIN);
        assertThat(testTMCOTIZACIONINFO.getDescuentoMax()).isEqualTo(UPDATED_DESCUENTO_MAX);
        assertThat(testTMCOTIZACIONINFO.getPrimaneta()).isEqualTo(UPDATED_PRIMANETA);
        assertThat(testTMCOTIZACIONINFO.getPrimaNetaMin()).isEqualTo(UPDATED_PRIMA_NETA_MIN);
        assertThat(testTMCOTIZACIONINFO.getPrimaNetaMax()).isEqualTo(UPDATED_PRIMA_NETA_MAX);
        assertThat(testTMCOTIZACIONINFO.getDerechoPoliza()).isEqualTo(UPDATED_DERECHO_POLIZA);
        assertThat(testTMCOTIZACIONINFO.getDerechoPolizaMin()).isEqualTo(UPDATED_DERECHO_POLIZA_MIN);
        assertThat(testTMCOTIZACIONINFO.getDerechoPolizaMax()).isEqualTo(UPDATED_DERECHO_POLIZA_MAX);
        assertThat(testTMCOTIZACIONINFO.getMayores()).isEqualTo(UPDATED_MAYORES);
        assertThat(testTMCOTIZACIONINFO.getMayoresMin()).isEqualTo(UPDATED_MAYORES_MIN);
        assertThat(testTMCOTIZACIONINFO.getMayoresMax()).isEqualTo(UPDATED_MAYORES_MAX);
        assertThat(testTMCOTIZACIONINFO.getAsegurados()).isEqualTo(UPDATED_ASEGURADOS);
        assertThat(testTMCOTIZACIONINFO.getAseguradosMin()).isEqualTo(UPDATED_ASEGURADOS_MIN);
        assertThat(testTMCOTIZACIONINFO.getAseguradosMax()).isEqualTo(UPDATED_ASEGURADOS_MAX);
        assertThat(testTMCOTIZACIONINFO.getSaPromedio()).isEqualTo(UPDATED_SA_PROMEDIO);
        assertThat(testTMCOTIZACIONINFO.getSaPromedioMin()).isEqualTo(UPDATED_SA_PROMEDIO_MIN);
        assertThat(testTMCOTIZACIONINFO.getSaPromedioMax()).isEqualTo(UPDATED_SA_PROMEDIO_MAX);
        assertThat(testTMCOTIZACIONINFO.getVarSa()).isEqualTo(UPDATED_VAR_SA);
        assertThat(testTMCOTIZACIONINFO.getVarSaMin()).isEqualTo(UPDATED_VAR_SA_MIN);
        assertThat(testTMCOTIZACIONINFO.getVarSaMax()).isEqualTo(UPDATED_VAR_SA_MAX);
        assertThat(testTMCOTIZACIONINFO.getSaTotal()).isEqualTo(UPDATED_SA_TOTAL);
        assertThat(testTMCOTIZACIONINFO.getSaMaxima()).isEqualTo(UPDATED_SA_MAXIMA);
        assertThat(testTMCOTIZACIONINFO.getSaMaximaMin()).isEqualTo(UPDATED_SA_MAXIMA_MIN);
        assertThat(testTMCOTIZACIONINFO.getSaMaximaMax()).isEqualTo(UPDATED_SA_MAXIMA_MAX);
        assertThat(testTMCOTIZACIONINFO.getSubgrupos()).isEqualTo(UPDATED_SUBGRUPOS);
        assertThat(testTMCOTIZACIONINFO.getSubgruposMin()).isEqualTo(UPDATED_SUBGRUPOS_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadMediaMin()).isEqualTo(UPDATED_EDAD_MEDIA_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadActuarial()).isEqualTo(UPDATED_EDAD_ACTUARIAL);
        assertThat(testTMCOTIZACIONINFO.getEdadActuarialMin()).isEqualTo(UPDATED_EDAD_ACTUARIAL_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadActPond()).isEqualTo(UPDATED_EDAD_ACT_POND);
        assertThat(testTMCOTIZACIONINFO.getEdadActPondMin()).isEqualTo(UPDATED_EDAD_ACT_POND_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadMin()).isEqualTo(UPDATED_EDAD_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadMinDos()).isEqualTo(UPDATED_EDAD_MIN_DOS);
        assertThat(testTMCOTIZACIONINFO.getEdadMaxDos()).isEqualTo(UPDATED_EDAD_MAX_DOS);
        assertThat(testTMCOTIZACIONINFO.getEdadMaxTres()).isEqualTo(UPDATED_EDAD_MAX_TRES);
        assertThat(testTMCOTIZACIONINFO.getCoeficienteVariacion()).isEqualTo(UPDATED_COEFICIENTE_VARIACION);
        assertThat(testTMCOTIZACIONINFO.getFactorTrUnoGiro()).isEqualTo(UPDATED_FACTOR_TR_UNO_GIRO);
        assertThat(testTMCOTIZACIONINFO.getFactorSaProm()).isEqualTo(UPDATED_FACTOR_SA_PROM);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalSdmc()).isEqualTo(UPDATED_P_NETA_GLOBAL_SDMC);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalCdmcCuota()).isEqualTo(UPDATED_P_NETA_GLOBAL_CDMC_CUOTA);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalSmccdesc()).isEqualTo(UPDATED_P_NETA_GLOBAL_SMCCDESC);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalSmccdescCuota()).isEqualTo(UPDATED_P_NETA_GLOBAL_SMCCDESC_CUOTA);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaSdmc()).isEqualTo(UPDATED_P_NETA_BASICA_SDMC);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaSdmcCuota()).isEqualTo(UPDATED_P_NETA_BASICA_SDMC_CUOTA);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaCdmc()).isEqualTo(UPDATED_P_NETA_BASICA_CDMC);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaCdmcCuota()).isEqualTo(UPDATED_P_NETA_BASICA_CDMC_CUOTA);
    }

    @Test
    @Transactional
    void putNonExistingTMCOTIZACIONINFO() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONINFORepository.findAll().size();
        tMCOTIZACIONINFO.setIdCotizacionInfo(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTMCOTIZACIONINFOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tMCOTIZACIONINFO.getIdCotizacionInfo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMCOTIZACIONINFO in the database
        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTMCOTIZACIONINFO() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONINFORepository.findAll().size();
        tMCOTIZACIONINFO.setIdCotizacionInfo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMCOTIZACIONINFOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMCOTIZACIONINFO in the database
        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTMCOTIZACIONINFO() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONINFORepository.findAll().size();
        tMCOTIZACIONINFO.setIdCotizacionInfo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMCOTIZACIONINFOMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TMCOTIZACIONINFO in the database
        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTMCOTIZACIONINFOWithPatch() throws Exception {
        // Initialize the database
        tMCOTIZACIONINFORepository.saveAndFlush(tMCOTIZACIONINFO);

        int databaseSizeBeforeUpdate = tMCOTIZACIONINFORepository.findAll().size();

        // Update the tMCOTIZACIONINFO using partial update
        TMCOTIZACIONINFO partialUpdatedTMCOTIZACIONINFO = new TMCOTIZACIONINFO();
        partialUpdatedTMCOTIZACIONINFO.setIdCotizacionInfo(tMCOTIZACIONINFO.getIdCotizacionInfo());

        partialUpdatedTMCOTIZACIONINFO
            .numero(UPDATED_NUMERO)
            .cotizacion(UPDATED_COTIZACION)
            .fechaEntrega(UPDATED_FECHA_ENTREGA)
            .tipoUno(UPDATED_TIPO_UNO)
            .tipoDos(UPDATED_TIPO_DOS)
            .inicioVigencia(UPDATED_INICIO_VIGENCIA)
            .intermediario(UPDATED_INTERMEDIARIO)
            .extraPrimaVida(UPDATED_EXTRA_PRIMA_VIDA)
            .samiMin(UPDATED_SAMI_MIN)
            .margenMin(UPDATED_MARGEN_MIN)
            .margenMax(UPDATED_MARGEN_MAX)
            .comision(UPDATED_COMISION)
            .primaNetaMin(UPDATED_PRIMA_NETA_MIN)
            .derechoPoliza(UPDATED_DERECHO_POLIZA)
            .derechoPolizaMax(UPDATED_DERECHO_POLIZA_MAX)
            .mayores(UPDATED_MAYORES)
            .mayoresMax(UPDATED_MAYORES_MAX)
            .asegurados(UPDATED_ASEGURADOS)
            .aseguradosMax(UPDATED_ASEGURADOS_MAX)
            .saPromedioMax(UPDATED_SA_PROMEDIO_MAX)
            .varSaMax(UPDATED_VAR_SA_MAX)
            .subgrupos(UPDATED_SUBGRUPOS)
            .edadActPond(UPDATED_EDAD_ACT_POND)
            .edadMin(UPDATED_EDAD_MIN)
            .edadMinDos(UPDATED_EDAD_MIN_DOS)
            .edadMaxTres(UPDATED_EDAD_MAX_TRES)
            .coeficienteVariacion(UPDATED_COEFICIENTE_VARIACION)
            .factorTrUnoGiro(UPDATED_FACTOR_TR_UNO_GIRO)
            .pNetaGlobalCdmcCuota(UPDATED_P_NETA_GLOBAL_CDMC_CUOTA)
            .pNetaGlobalSmccdesc(UPDATED_P_NETA_GLOBAL_SMCCDESC)
            .pNetaBasicaSdmc(UPDATED_P_NETA_BASICA_SDMC)
            .pNetaBasicaCdmc(UPDATED_P_NETA_BASICA_CDMC);

        restTMCOTIZACIONINFOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTMCOTIZACIONINFO.getIdCotizacionInfo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTMCOTIZACIONINFO))
            )
            .andExpect(status().isOk());

        // Validate the TMCOTIZACIONINFO in the database
        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeUpdate);
        TMCOTIZACIONINFO testTMCOTIZACIONINFO = tMCOTIZACIONINFOList.get(tMCOTIZACIONINFOList.size() - 1);
        assertThat(testTMCOTIZACIONINFO.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testTMCOTIZACIONINFO.getCotizacion()).isEqualTo(UPDATED_COTIZACION);
        assertThat(testTMCOTIZACIONINFO.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testTMCOTIZACIONINFO.getFechaSolicitud()).isEqualTo(DEFAULT_FECHA_SOLICITUD);
        assertThat(testTMCOTIZACIONINFO.getResponsable()).isEqualTo(DEFAULT_RESPONSABLE);
        assertThat(testTMCOTIZACIONINFO.getFechaEntrega()).isEqualTo(UPDATED_FECHA_ENTREGA);
        assertThat(testTMCOTIZACIONINFO.getContratante()).isEqualTo(DEFAULT_CONTRATANTE);
        assertThat(testTMCOTIZACIONINFO.getTipoUno()).isEqualTo(UPDATED_TIPO_UNO);
        assertThat(testTMCOTIZACIONINFO.getTipoDos()).isEqualTo(UPDATED_TIPO_DOS);
        assertThat(testTMCOTIZACIONINFO.getPoliza()).isEqualTo(DEFAULT_POLIZA);
        assertThat(testTMCOTIZACIONINFO.getInicioVigencia()).isEqualTo(UPDATED_INICIO_VIGENCIA);
        assertThat(testTMCOTIZACIONINFO.getFinVigencia()).isEqualTo(DEFAULT_FIN_VIGENCIA);
        assertThat(testTMCOTIZACIONINFO.getIntermediario()).isEqualTo(UPDATED_INTERMEDIARIO);
        assertThat(testTMCOTIZACIONINFO.getRfc()).isEqualTo(DEFAULT_RFC);
        assertThat(testTMCOTIZACIONINFO.getEjecutivo()).isEqualTo(DEFAULT_EJECUTIVO);
        assertThat(testTMCOTIZACIONINFO.getSuscriptor()).isEqualTo(DEFAULT_SUSCRIPTOR);
        assertThat(testTMCOTIZACIONINFO.getPlan()).isEqualTo(DEFAULT_PLAN);
        assertThat(testTMCOTIZACIONINFO.getOcupacion()).isEqualTo(DEFAULT_OCUPACION);
        assertThat(testTMCOTIZACIONINFO.getPrimaCovidVida()).isEqualTo(DEFAULT_PRIMA_COVID_VIDA);
        assertThat(testTMCOTIZACIONINFO.getPrimaCovidGff()).isEqualTo(DEFAULT_PRIMA_COVID_GFF);
        assertThat(testTMCOTIZACIONINFO.getDescuentoPrimaCovid()).isEqualTo(DEFAULT_DESCUENTO_PRIMA_COVID);
        assertThat(testTMCOTIZACIONINFO.getExtraPrimaVida()).isEqualTo(UPDATED_EXTRA_PRIMA_VIDA);
        assertThat(testTMCOTIZACIONINFO.getExtraPrimaGff()).isEqualTo(DEFAULT_EXTRA_PRIMA_GFF);
        assertThat(testTMCOTIZACIONINFO.getPorcentajeExtraPrimaVida()).isEqualTo(DEFAULT_PORCENTAJE_EXTRA_PRIMA_VIDA);
        assertThat(testTMCOTIZACIONINFO.getPorcentajeExtraPrimaGff()).isEqualTo(DEFAULT_PORCENTAJE_EXTRA_PRIMA_GFF);
        assertThat(testTMCOTIZACIONINFO.getValorFtr()).isEqualTo(DEFAULT_VALOR_FTR);
        assertThat(testTMCOTIZACIONINFO.getSami()).isEqualTo(DEFAULT_SAMI);
        assertThat(testTMCOTIZACIONINFO.getSamiMin()).isEqualTo(UPDATED_SAMI_MIN);
        assertThat(testTMCOTIZACIONINFO.getSamiMax()).isEqualTo(DEFAULT_SAMI_MAX);
        assertThat(testTMCOTIZACIONINFO.getMargen()).isEqualTo(DEFAULT_MARGEN);
        assertThat(testTMCOTIZACIONINFO.getMargenMin()).isEqualTo(UPDATED_MARGEN_MIN);
        assertThat(testTMCOTIZACIONINFO.getMargenMax()).isEqualTo(UPDATED_MARGEN_MAX);
        assertThat(testTMCOTIZACIONINFO.getComision()).isEqualTo(UPDATED_COMISION);
        assertThat(testTMCOTIZACIONINFO.getComisionMin()).isEqualTo(DEFAULT_COMISION_MIN);
        assertThat(testTMCOTIZACIONINFO.getComisionMax()).isEqualTo(DEFAULT_COMISION_MAX);
        assertThat(testTMCOTIZACIONINFO.getDescuento()).isEqualTo(DEFAULT_DESCUENTO);
        assertThat(testTMCOTIZACIONINFO.getDescuentoMin()).isEqualTo(DEFAULT_DESCUENTO_MIN);
        assertThat(testTMCOTIZACIONINFO.getDescuentoMax()).isEqualTo(DEFAULT_DESCUENTO_MAX);
        assertThat(testTMCOTIZACIONINFO.getPrimaneta()).isEqualTo(DEFAULT_PRIMANETA);
        assertThat(testTMCOTIZACIONINFO.getPrimaNetaMin()).isEqualTo(UPDATED_PRIMA_NETA_MIN);
        assertThat(testTMCOTIZACIONINFO.getPrimaNetaMax()).isEqualTo(DEFAULT_PRIMA_NETA_MAX);
        assertThat(testTMCOTIZACIONINFO.getDerechoPoliza()).isEqualTo(UPDATED_DERECHO_POLIZA);
        assertThat(testTMCOTIZACIONINFO.getDerechoPolizaMin()).isEqualTo(DEFAULT_DERECHO_POLIZA_MIN);
        assertThat(testTMCOTIZACIONINFO.getDerechoPolizaMax()).isEqualTo(UPDATED_DERECHO_POLIZA_MAX);
        assertThat(testTMCOTIZACIONINFO.getMayores()).isEqualTo(UPDATED_MAYORES);
        assertThat(testTMCOTIZACIONINFO.getMayoresMin()).isEqualTo(DEFAULT_MAYORES_MIN);
        assertThat(testTMCOTIZACIONINFO.getMayoresMax()).isEqualTo(UPDATED_MAYORES_MAX);
        assertThat(testTMCOTIZACIONINFO.getAsegurados()).isEqualTo(UPDATED_ASEGURADOS);
        assertThat(testTMCOTIZACIONINFO.getAseguradosMin()).isEqualTo(DEFAULT_ASEGURADOS_MIN);
        assertThat(testTMCOTIZACIONINFO.getAseguradosMax()).isEqualTo(UPDATED_ASEGURADOS_MAX);
        assertThat(testTMCOTIZACIONINFO.getSaPromedio()).isEqualTo(DEFAULT_SA_PROMEDIO);
        assertThat(testTMCOTIZACIONINFO.getSaPromedioMin()).isEqualTo(DEFAULT_SA_PROMEDIO_MIN);
        assertThat(testTMCOTIZACIONINFO.getSaPromedioMax()).isEqualTo(UPDATED_SA_PROMEDIO_MAX);
        assertThat(testTMCOTIZACIONINFO.getVarSa()).isEqualTo(DEFAULT_VAR_SA);
        assertThat(testTMCOTIZACIONINFO.getVarSaMin()).isEqualTo(DEFAULT_VAR_SA_MIN);
        assertThat(testTMCOTIZACIONINFO.getVarSaMax()).isEqualTo(UPDATED_VAR_SA_MAX);
        assertThat(testTMCOTIZACIONINFO.getSaTotal()).isEqualTo(DEFAULT_SA_TOTAL);
        assertThat(testTMCOTIZACIONINFO.getSaMaxima()).isEqualTo(DEFAULT_SA_MAXIMA);
        assertThat(testTMCOTIZACIONINFO.getSaMaximaMin()).isEqualTo(DEFAULT_SA_MAXIMA_MIN);
        assertThat(testTMCOTIZACIONINFO.getSaMaximaMax()).isEqualTo(DEFAULT_SA_MAXIMA_MAX);
        assertThat(testTMCOTIZACIONINFO.getSubgrupos()).isEqualTo(UPDATED_SUBGRUPOS);
        assertThat(testTMCOTIZACIONINFO.getSubgruposMin()).isEqualTo(DEFAULT_SUBGRUPOS_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadMediaMin()).isEqualTo(DEFAULT_EDAD_MEDIA_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadActuarial()).isEqualTo(DEFAULT_EDAD_ACTUARIAL);
        assertThat(testTMCOTIZACIONINFO.getEdadActuarialMin()).isEqualTo(DEFAULT_EDAD_ACTUARIAL_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadActPond()).isEqualTo(UPDATED_EDAD_ACT_POND);
        assertThat(testTMCOTIZACIONINFO.getEdadActPondMin()).isEqualTo(DEFAULT_EDAD_ACT_POND_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadMin()).isEqualTo(UPDATED_EDAD_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadMinDos()).isEqualTo(UPDATED_EDAD_MIN_DOS);
        assertThat(testTMCOTIZACIONINFO.getEdadMaxDos()).isEqualTo(DEFAULT_EDAD_MAX_DOS);
        assertThat(testTMCOTIZACIONINFO.getEdadMaxTres()).isEqualTo(UPDATED_EDAD_MAX_TRES);
        assertThat(testTMCOTIZACIONINFO.getCoeficienteVariacion()).isEqualTo(UPDATED_COEFICIENTE_VARIACION);
        assertThat(testTMCOTIZACIONINFO.getFactorTrUnoGiro()).isEqualTo(UPDATED_FACTOR_TR_UNO_GIRO);
        assertThat(testTMCOTIZACIONINFO.getFactorSaProm()).isEqualTo(DEFAULT_FACTOR_SA_PROM);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalSdmc()).isEqualTo(DEFAULT_P_NETA_GLOBAL_SDMC);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalCdmcCuota()).isEqualTo(UPDATED_P_NETA_GLOBAL_CDMC_CUOTA);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalSmccdesc()).isEqualTo(UPDATED_P_NETA_GLOBAL_SMCCDESC);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalSmccdescCuota()).isEqualTo(DEFAULT_P_NETA_GLOBAL_SMCCDESC_CUOTA);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaSdmc()).isEqualTo(UPDATED_P_NETA_BASICA_SDMC);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaSdmcCuota()).isEqualTo(DEFAULT_P_NETA_BASICA_SDMC_CUOTA);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaCdmc()).isEqualTo(UPDATED_P_NETA_BASICA_CDMC);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaCdmcCuota()).isEqualTo(DEFAULT_P_NETA_BASICA_CDMC_CUOTA);
    }

    @Test
    @Transactional
    void fullUpdateTMCOTIZACIONINFOWithPatch() throws Exception {
        // Initialize the database
        tMCOTIZACIONINFORepository.saveAndFlush(tMCOTIZACIONINFO);

        int databaseSizeBeforeUpdate = tMCOTIZACIONINFORepository.findAll().size();

        // Update the tMCOTIZACIONINFO using partial update
        TMCOTIZACIONINFO partialUpdatedTMCOTIZACIONINFO = new TMCOTIZACIONINFO();
        partialUpdatedTMCOTIZACIONINFO.setIdCotizacionInfo(tMCOTIZACIONINFO.getIdCotizacionInfo());

        partialUpdatedTMCOTIZACIONINFO
            .numero(UPDATED_NUMERO)
            .cotizacion(UPDATED_COTIZACION)
            .region(UPDATED_REGION)
            .fechaSolicitud(UPDATED_FECHA_SOLICITUD)
            .responsable(UPDATED_RESPONSABLE)
            .fechaEntrega(UPDATED_FECHA_ENTREGA)
            .contratante(UPDATED_CONTRATANTE)
            .tipoUno(UPDATED_TIPO_UNO)
            .tipoDos(UPDATED_TIPO_DOS)
            .poliza(UPDATED_POLIZA)
            .inicioVigencia(UPDATED_INICIO_VIGENCIA)
            .finVigencia(UPDATED_FIN_VIGENCIA)
            .intermediario(UPDATED_INTERMEDIARIO)
            .rfc(UPDATED_RFC)
            .ejecutivo(UPDATED_EJECUTIVO)
            .suscriptor(UPDATED_SUSCRIPTOR)
            .plan(UPDATED_PLAN)
            .ocupacion(UPDATED_OCUPACION)
            .primaCovidVida(UPDATED_PRIMA_COVID_VIDA)
            .primaCovidGff(UPDATED_PRIMA_COVID_GFF)
            .descuentoPrimaCovid(UPDATED_DESCUENTO_PRIMA_COVID)
            .extraPrimaVida(UPDATED_EXTRA_PRIMA_VIDA)
            .extraPrimaGff(UPDATED_EXTRA_PRIMA_GFF)
            .porcentajeExtraPrimaVida(UPDATED_PORCENTAJE_EXTRA_PRIMA_VIDA)
            .porcentajeExtraPrimaGff(UPDATED_PORCENTAJE_EXTRA_PRIMA_GFF)
            .valorFtr(UPDATED_VALOR_FTR)
            .sami(UPDATED_SAMI)
            .samiMin(UPDATED_SAMI_MIN)
            .samiMax(UPDATED_SAMI_MAX)
            .margen(UPDATED_MARGEN)
            .margenMin(UPDATED_MARGEN_MIN)
            .margenMax(UPDATED_MARGEN_MAX)
            .comision(UPDATED_COMISION)
            .comisionMin(UPDATED_COMISION_MIN)
            .comisionMax(UPDATED_COMISION_MAX)
            .descuento(UPDATED_DESCUENTO)
            .descuentoMin(UPDATED_DESCUENTO_MIN)
            .descuentoMax(UPDATED_DESCUENTO_MAX)
            .primaneta(UPDATED_PRIMANETA)
            .primaNetaMin(UPDATED_PRIMA_NETA_MIN)
            .primaNetaMax(UPDATED_PRIMA_NETA_MAX)
            .derechoPoliza(UPDATED_DERECHO_POLIZA)
            .derechoPolizaMin(UPDATED_DERECHO_POLIZA_MIN)
            .derechoPolizaMax(UPDATED_DERECHO_POLIZA_MAX)
            .mayores(UPDATED_MAYORES)
            .mayoresMin(UPDATED_MAYORES_MIN)
            .mayoresMax(UPDATED_MAYORES_MAX)
            .asegurados(UPDATED_ASEGURADOS)
            .aseguradosMin(UPDATED_ASEGURADOS_MIN)
            .aseguradosMax(UPDATED_ASEGURADOS_MAX)
            .saPromedio(UPDATED_SA_PROMEDIO)
            .saPromedioMin(UPDATED_SA_PROMEDIO_MIN)
            .saPromedioMax(UPDATED_SA_PROMEDIO_MAX)
            .varSa(UPDATED_VAR_SA)
            .varSaMin(UPDATED_VAR_SA_MIN)
            .varSaMax(UPDATED_VAR_SA_MAX)
            .saTotal(UPDATED_SA_TOTAL)
            .saMaxima(UPDATED_SA_MAXIMA)
            .saMaximaMin(UPDATED_SA_MAXIMA_MIN)
            .saMaximaMax(UPDATED_SA_MAXIMA_MAX)
            .subgrupos(UPDATED_SUBGRUPOS)
            .subgruposMin(UPDATED_SUBGRUPOS_MIN)
            .edadMediaMin(UPDATED_EDAD_MEDIA_MIN)
            .edadActuarial(UPDATED_EDAD_ACTUARIAL)
            .edadActuarialMin(UPDATED_EDAD_ACTUARIAL_MIN)
            .edadActPond(UPDATED_EDAD_ACT_POND)
            .edadActPondMin(UPDATED_EDAD_ACT_POND_MIN)
            .edadMin(UPDATED_EDAD_MIN)
            .edadMinDos(UPDATED_EDAD_MIN_DOS)
            .edadMaxDos(UPDATED_EDAD_MAX_DOS)
            .edadMaxTres(UPDATED_EDAD_MAX_TRES)
            .coeficienteVariacion(UPDATED_COEFICIENTE_VARIACION)
            .factorTrUnoGiro(UPDATED_FACTOR_TR_UNO_GIRO)
            .factorSaProm(UPDATED_FACTOR_SA_PROM)
            .pNetaGlobalSdmc(UPDATED_P_NETA_GLOBAL_SDMC)
            .pNetaGlobalCdmcCuota(UPDATED_P_NETA_GLOBAL_CDMC_CUOTA)
            .pNetaGlobalSmccdesc(UPDATED_P_NETA_GLOBAL_SMCCDESC)
            .pNetaGlobalSmccdescCuota(UPDATED_P_NETA_GLOBAL_SMCCDESC_CUOTA)
            .pNetaBasicaSdmc(UPDATED_P_NETA_BASICA_SDMC)
            .pNetaBasicaSdmcCuota(UPDATED_P_NETA_BASICA_SDMC_CUOTA)
            .pNetaBasicaCdmc(UPDATED_P_NETA_BASICA_CDMC)
            .pNetaBasicaCdmcCuota(UPDATED_P_NETA_BASICA_CDMC_CUOTA);

        restTMCOTIZACIONINFOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTMCOTIZACIONINFO.getIdCotizacionInfo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTMCOTIZACIONINFO))
            )
            .andExpect(status().isOk());

        // Validate the TMCOTIZACIONINFO in the database
        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeUpdate);
        TMCOTIZACIONINFO testTMCOTIZACIONINFO = tMCOTIZACIONINFOList.get(tMCOTIZACIONINFOList.size() - 1);
        assertThat(testTMCOTIZACIONINFO.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testTMCOTIZACIONINFO.getCotizacion()).isEqualTo(UPDATED_COTIZACION);
        assertThat(testTMCOTIZACIONINFO.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testTMCOTIZACIONINFO.getFechaSolicitud()).isEqualTo(UPDATED_FECHA_SOLICITUD);
        assertThat(testTMCOTIZACIONINFO.getResponsable()).isEqualTo(UPDATED_RESPONSABLE);
        assertThat(testTMCOTIZACIONINFO.getFechaEntrega()).isEqualTo(UPDATED_FECHA_ENTREGA);
        assertThat(testTMCOTIZACIONINFO.getContratante()).isEqualTo(UPDATED_CONTRATANTE);
        assertThat(testTMCOTIZACIONINFO.getTipoUno()).isEqualTo(UPDATED_TIPO_UNO);
        assertThat(testTMCOTIZACIONINFO.getTipoDos()).isEqualTo(UPDATED_TIPO_DOS);
        assertThat(testTMCOTIZACIONINFO.getPoliza()).isEqualTo(UPDATED_POLIZA);
        assertThat(testTMCOTIZACIONINFO.getInicioVigencia()).isEqualTo(UPDATED_INICIO_VIGENCIA);
        assertThat(testTMCOTIZACIONINFO.getFinVigencia()).isEqualTo(UPDATED_FIN_VIGENCIA);
        assertThat(testTMCOTIZACIONINFO.getIntermediario()).isEqualTo(UPDATED_INTERMEDIARIO);
        assertThat(testTMCOTIZACIONINFO.getRfc()).isEqualTo(UPDATED_RFC);
        assertThat(testTMCOTIZACIONINFO.getEjecutivo()).isEqualTo(UPDATED_EJECUTIVO);
        assertThat(testTMCOTIZACIONINFO.getSuscriptor()).isEqualTo(UPDATED_SUSCRIPTOR);
        assertThat(testTMCOTIZACIONINFO.getPlan()).isEqualTo(UPDATED_PLAN);
        assertThat(testTMCOTIZACIONINFO.getOcupacion()).isEqualTo(UPDATED_OCUPACION);
        assertThat(testTMCOTIZACIONINFO.getPrimaCovidVida()).isEqualTo(UPDATED_PRIMA_COVID_VIDA);
        assertThat(testTMCOTIZACIONINFO.getPrimaCovidGff()).isEqualTo(UPDATED_PRIMA_COVID_GFF);
        assertThat(testTMCOTIZACIONINFO.getDescuentoPrimaCovid()).isEqualTo(UPDATED_DESCUENTO_PRIMA_COVID);
        assertThat(testTMCOTIZACIONINFO.getExtraPrimaVida()).isEqualTo(UPDATED_EXTRA_PRIMA_VIDA);
        assertThat(testTMCOTIZACIONINFO.getExtraPrimaGff()).isEqualTo(UPDATED_EXTRA_PRIMA_GFF);
        assertThat(testTMCOTIZACIONINFO.getPorcentajeExtraPrimaVida()).isEqualTo(UPDATED_PORCENTAJE_EXTRA_PRIMA_VIDA);
        assertThat(testTMCOTIZACIONINFO.getPorcentajeExtraPrimaGff()).isEqualTo(UPDATED_PORCENTAJE_EXTRA_PRIMA_GFF);
        assertThat(testTMCOTIZACIONINFO.getValorFtr()).isEqualTo(UPDATED_VALOR_FTR);
        assertThat(testTMCOTIZACIONINFO.getSami()).isEqualTo(UPDATED_SAMI);
        assertThat(testTMCOTIZACIONINFO.getSamiMin()).isEqualTo(UPDATED_SAMI_MIN);
        assertThat(testTMCOTIZACIONINFO.getSamiMax()).isEqualTo(UPDATED_SAMI_MAX);
        assertThat(testTMCOTIZACIONINFO.getMargen()).isEqualTo(UPDATED_MARGEN);
        assertThat(testTMCOTIZACIONINFO.getMargenMin()).isEqualTo(UPDATED_MARGEN_MIN);
        assertThat(testTMCOTIZACIONINFO.getMargenMax()).isEqualTo(UPDATED_MARGEN_MAX);
        assertThat(testTMCOTIZACIONINFO.getComision()).isEqualTo(UPDATED_COMISION);
        assertThat(testTMCOTIZACIONINFO.getComisionMin()).isEqualTo(UPDATED_COMISION_MIN);
        assertThat(testTMCOTIZACIONINFO.getComisionMax()).isEqualTo(UPDATED_COMISION_MAX);
        assertThat(testTMCOTIZACIONINFO.getDescuento()).isEqualTo(UPDATED_DESCUENTO);
        assertThat(testTMCOTIZACIONINFO.getDescuentoMin()).isEqualTo(UPDATED_DESCUENTO_MIN);
        assertThat(testTMCOTIZACIONINFO.getDescuentoMax()).isEqualTo(UPDATED_DESCUENTO_MAX);
        assertThat(testTMCOTIZACIONINFO.getPrimaneta()).isEqualTo(UPDATED_PRIMANETA);
        assertThat(testTMCOTIZACIONINFO.getPrimaNetaMin()).isEqualTo(UPDATED_PRIMA_NETA_MIN);
        assertThat(testTMCOTIZACIONINFO.getPrimaNetaMax()).isEqualTo(UPDATED_PRIMA_NETA_MAX);
        assertThat(testTMCOTIZACIONINFO.getDerechoPoliza()).isEqualTo(UPDATED_DERECHO_POLIZA);
        assertThat(testTMCOTIZACIONINFO.getDerechoPolizaMin()).isEqualTo(UPDATED_DERECHO_POLIZA_MIN);
        assertThat(testTMCOTIZACIONINFO.getDerechoPolizaMax()).isEqualTo(UPDATED_DERECHO_POLIZA_MAX);
        assertThat(testTMCOTIZACIONINFO.getMayores()).isEqualTo(UPDATED_MAYORES);
        assertThat(testTMCOTIZACIONINFO.getMayoresMin()).isEqualTo(UPDATED_MAYORES_MIN);
        assertThat(testTMCOTIZACIONINFO.getMayoresMax()).isEqualTo(UPDATED_MAYORES_MAX);
        assertThat(testTMCOTIZACIONINFO.getAsegurados()).isEqualTo(UPDATED_ASEGURADOS);
        assertThat(testTMCOTIZACIONINFO.getAseguradosMin()).isEqualTo(UPDATED_ASEGURADOS_MIN);
        assertThat(testTMCOTIZACIONINFO.getAseguradosMax()).isEqualTo(UPDATED_ASEGURADOS_MAX);
        assertThat(testTMCOTIZACIONINFO.getSaPromedio()).isEqualTo(UPDATED_SA_PROMEDIO);
        assertThat(testTMCOTIZACIONINFO.getSaPromedioMin()).isEqualTo(UPDATED_SA_PROMEDIO_MIN);
        assertThat(testTMCOTIZACIONINFO.getSaPromedioMax()).isEqualTo(UPDATED_SA_PROMEDIO_MAX);
        assertThat(testTMCOTIZACIONINFO.getVarSa()).isEqualTo(UPDATED_VAR_SA);
        assertThat(testTMCOTIZACIONINFO.getVarSaMin()).isEqualTo(UPDATED_VAR_SA_MIN);
        assertThat(testTMCOTIZACIONINFO.getVarSaMax()).isEqualTo(UPDATED_VAR_SA_MAX);
        assertThat(testTMCOTIZACIONINFO.getSaTotal()).isEqualTo(UPDATED_SA_TOTAL);
        assertThat(testTMCOTIZACIONINFO.getSaMaxima()).isEqualTo(UPDATED_SA_MAXIMA);
        assertThat(testTMCOTIZACIONINFO.getSaMaximaMin()).isEqualTo(UPDATED_SA_MAXIMA_MIN);
        assertThat(testTMCOTIZACIONINFO.getSaMaximaMax()).isEqualTo(UPDATED_SA_MAXIMA_MAX);
        assertThat(testTMCOTIZACIONINFO.getSubgrupos()).isEqualTo(UPDATED_SUBGRUPOS);
        assertThat(testTMCOTIZACIONINFO.getSubgruposMin()).isEqualTo(UPDATED_SUBGRUPOS_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadMediaMin()).isEqualTo(UPDATED_EDAD_MEDIA_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadActuarial()).isEqualTo(UPDATED_EDAD_ACTUARIAL);
        assertThat(testTMCOTIZACIONINFO.getEdadActuarialMin()).isEqualTo(UPDATED_EDAD_ACTUARIAL_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadActPond()).isEqualTo(UPDATED_EDAD_ACT_POND);
        assertThat(testTMCOTIZACIONINFO.getEdadActPondMin()).isEqualTo(UPDATED_EDAD_ACT_POND_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadMin()).isEqualTo(UPDATED_EDAD_MIN);
        assertThat(testTMCOTIZACIONINFO.getEdadMinDos()).isEqualTo(UPDATED_EDAD_MIN_DOS);
        assertThat(testTMCOTIZACIONINFO.getEdadMaxDos()).isEqualTo(UPDATED_EDAD_MAX_DOS);
        assertThat(testTMCOTIZACIONINFO.getEdadMaxTres()).isEqualTo(UPDATED_EDAD_MAX_TRES);
        assertThat(testTMCOTIZACIONINFO.getCoeficienteVariacion()).isEqualTo(UPDATED_COEFICIENTE_VARIACION);
        assertThat(testTMCOTIZACIONINFO.getFactorTrUnoGiro()).isEqualTo(UPDATED_FACTOR_TR_UNO_GIRO);
        assertThat(testTMCOTIZACIONINFO.getFactorSaProm()).isEqualTo(UPDATED_FACTOR_SA_PROM);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalSdmc()).isEqualTo(UPDATED_P_NETA_GLOBAL_SDMC);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalCdmcCuota()).isEqualTo(UPDATED_P_NETA_GLOBAL_CDMC_CUOTA);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalSmccdesc()).isEqualTo(UPDATED_P_NETA_GLOBAL_SMCCDESC);
        assertThat(testTMCOTIZACIONINFO.getpNetaGlobalSmccdescCuota()).isEqualTo(UPDATED_P_NETA_GLOBAL_SMCCDESC_CUOTA);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaSdmc()).isEqualTo(UPDATED_P_NETA_BASICA_SDMC);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaSdmcCuota()).isEqualTo(UPDATED_P_NETA_BASICA_SDMC_CUOTA);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaCdmc()).isEqualTo(UPDATED_P_NETA_BASICA_CDMC);
        assertThat(testTMCOTIZACIONINFO.getpNetaBasicaCdmcCuota()).isEqualTo(UPDATED_P_NETA_BASICA_CDMC_CUOTA);
    }

    @Test
    @Transactional
    void patchNonExistingTMCOTIZACIONINFO() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONINFORepository.findAll().size();
        tMCOTIZACIONINFO.setIdCotizacionInfo(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTMCOTIZACIONINFOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tMCOTIZACIONINFO.getIdCotizacionInfo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMCOTIZACIONINFO in the database
        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTMCOTIZACIONINFO() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONINFORepository.findAll().size();
        tMCOTIZACIONINFO.setIdCotizacionInfo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMCOTIZACIONINFOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMCOTIZACIONINFO in the database
        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTMCOTIZACIONINFO() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONINFORepository.findAll().size();
        tMCOTIZACIONINFO.setIdCotizacionInfo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMCOTIZACIONINFOMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONINFO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TMCOTIZACIONINFO in the database
        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTMCOTIZACIONINFO() throws Exception {
        // Initialize the database
        tMCOTIZACIONINFORepository.saveAndFlush(tMCOTIZACIONINFO);

        int databaseSizeBeforeDelete = tMCOTIZACIONINFORepository.findAll().size();

        // Delete the tMCOTIZACIONINFO
        restTMCOTIZACIONINFOMockMvc
            .perform(delete(ENTITY_API_URL_ID, tMCOTIZACIONINFO.getIdCotizacionInfo()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TMCOTIZACIONINFO> tMCOTIZACIONINFOList = tMCOTIZACIONINFORepository.findAll();
        assertThat(tMCOTIZACIONINFOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
