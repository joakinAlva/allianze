package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TMASEGURADO;
import com.allianze.apicotizador.repository.TMASEGURADORepository;
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
 * Integration tests for the {@link TMASEGURADOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TMASEGURADOResourceIT {

    private static final Long DEFAULT_NUMERO = 1L;
    private static final Long UPDATED_NUMERO = 2L;

    private static final Long DEFAULT_EXCEDENTE = 1L;
    private static final Long UPDATED_EXCEDENTE = 2L;

    private static final Long DEFAULT_SUBGRUPO = 1L;
    private static final Long UPDATED_SUBGRUPO = 2L;

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_F_NACIMIENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_F_NACIMIENTO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_SUELDO = 1L;
    private static final Long UPDATED_SUELDO = 2L;

    private static final Long DEFAULT_REGLA_MONTO = 1L;
    private static final Long UPDATED_REGLA_MONTO = 2L;

    private static final Long DEFAULT_EDAD = 1L;
    private static final Long UPDATED_EDAD = 2L;

    private static final Long DEFAULT_SA_TOTAL = 1L;
    private static final Long UPDATED_SA_TOTAL = 2L;

    private static final Long DEFAULT_SA_TOPADO = 1L;
    private static final Long UPDATED_SA_TOPADO = 2L;

    private static final Long DEFAULT_BASICA = 1L;
    private static final Long UPDATED_BASICA = 2L;

    private static final Long DEFAULT_BASICA_COVID = 1L;
    private static final Long UPDATED_BASICA_COVID = 2L;

    private static final Long DEFAULT_EXTRABAS = 1L;
    private static final Long UPDATED_EXTRABAS = 2L;

    private static final Long DEFAULT_PRIMA_BASICA = 1L;
    private static final Long UPDATED_PRIMA_BASICA = 2L;

    private static final Long DEFAULT_INVALIDEZ = 1L;
    private static final Long UPDATED_INVALIDEZ = 2L;

    private static final Long DEFAULT_EXTRA_INV = 1L;
    private static final Long UPDATED_EXTRA_INV = 2L;

    private static final Long DEFAULT_EXENCION = 1L;
    private static final Long UPDATED_EXENCION = 2L;

    private static final Long DEFAULT_EXTRA_EXE = 1L;
    private static final Long UPDATED_EXTRA_EXE = 2L;

    private static final Long DEFAULT_MUERTE_ACC = 1L;
    private static final Long UPDATED_MUERTE_ACC = 2L;

    private static final Long DEFAULT_EXTRA_ACC = 1L;
    private static final Long UPDATED_EXTRA_ACC = 2L;

    private static final Long DEFAULT_VALOR_GFF = 1L;
    private static final Long UPDATED_VALOR_GFF = 2L;

    private static final Long DEFAULT_VALOR_GFF_COVID = 1L;
    private static final Long UPDATED_VALOR_GFF_COVID = 2L;

    private static final Long DEFAULT_EXTRA_GFF = 1L;
    private static final Long UPDATED_EXTRA_GFF = 2L;

    private static final Long DEFAULT_PRIMA_GFF = 1L;
    private static final Long UPDATED_PRIMA_GFF = 2L;

    private static final Long DEFAULT_PRIMA_CA = 1L;
    private static final Long UPDATED_PRIMA_CA = 2L;

    private static final Long DEFAULT_EXTRA_CA = 1L;
    private static final Long UPDATED_EXTRA_CA = 2L;

    private static final Long DEFAULT_PRIMA_GE = 1L;
    private static final Long UPDATED_PRIMA_GE = 2L;

    private static final Long DEFAULT_EXTRA_GE = 1L;
    private static final Long UPDATED_EXTRA_GE = 2L;

    private static final Long DEFAULT_PRIMA_IQS = 1L;
    private static final Long UPDATED_PRIMA_IQS = 2L;

    private static final Long DEFAULT_EXTRA_IQA = 1L;
    private static final Long UPDATED_EXTRA_IQA = 2L;

    private static final Long DEFAULT_PRIMA_IQV = 1L;
    private static final Long UPDATED_PRIMA_IQV = 2L;

    private static final Long DEFAULT_EXTRA_IQV = 1L;
    private static final Long UPDATED_EXTRA_IQV = 2L;

    private static final String ENTITY_API_URL = "/api/tmasegurados";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idAsegurados}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TMASEGURADORepository tMASEGURADORepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTMASEGURADOMockMvc;

    private TMASEGURADO tMASEGURADO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TMASEGURADO createEntity(EntityManager em) {
        TMASEGURADO tMASEGURADO = new TMASEGURADO()
            .numero(DEFAULT_NUMERO)
            .excedente(DEFAULT_EXCEDENTE)
            .subgrupo(DEFAULT_SUBGRUPO)
            .nombre(DEFAULT_NOMBRE)
            .fNacimiento(DEFAULT_F_NACIMIENTO)
            .sueldo(DEFAULT_SUELDO)
            .reglaMonto(DEFAULT_REGLA_MONTO)
            .edad(DEFAULT_EDAD)
            .saTotal(DEFAULT_SA_TOTAL)
            .saTopado(DEFAULT_SA_TOPADO)
            .basica(DEFAULT_BASICA)
            .basicaCovid(DEFAULT_BASICA_COVID)
            .extrabas(DEFAULT_EXTRABAS)
            .primaBasica(DEFAULT_PRIMA_BASICA)
            .invalidez(DEFAULT_INVALIDEZ)
            .extraInv(DEFAULT_EXTRA_INV)
            .exencion(DEFAULT_EXENCION)
            .extraExe(DEFAULT_EXTRA_EXE)
            .muerteAcc(DEFAULT_MUERTE_ACC)
            .extraAcc(DEFAULT_EXTRA_ACC)
            .valorGff(DEFAULT_VALOR_GFF)
            .valorGffCovid(DEFAULT_VALOR_GFF_COVID)
            .extraGff(DEFAULT_EXTRA_GFF)
            .primaGff(DEFAULT_PRIMA_GFF)
            .primaCa(DEFAULT_PRIMA_CA)
            .extraCa(DEFAULT_EXTRA_CA)
            .primaGe(DEFAULT_PRIMA_GE)
            .extraGe(DEFAULT_EXTRA_GE)
            .primaIqs(DEFAULT_PRIMA_IQS)
            .extraIqa(DEFAULT_EXTRA_IQA)
            .primaIqv(DEFAULT_PRIMA_IQV)
            .extraIqv(DEFAULT_EXTRA_IQV);
        return tMASEGURADO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TMASEGURADO createUpdatedEntity(EntityManager em) {
        TMASEGURADO tMASEGURADO = new TMASEGURADO()
            .numero(UPDATED_NUMERO)
            .excedente(UPDATED_EXCEDENTE)
            .subgrupo(UPDATED_SUBGRUPO)
            .nombre(UPDATED_NOMBRE)
            .fNacimiento(UPDATED_F_NACIMIENTO)
            .sueldo(UPDATED_SUELDO)
            .reglaMonto(UPDATED_REGLA_MONTO)
            .edad(UPDATED_EDAD)
            .saTotal(UPDATED_SA_TOTAL)
            .saTopado(UPDATED_SA_TOPADO)
            .basica(UPDATED_BASICA)
            .basicaCovid(UPDATED_BASICA_COVID)
            .extrabas(UPDATED_EXTRABAS)
            .primaBasica(UPDATED_PRIMA_BASICA)
            .invalidez(UPDATED_INVALIDEZ)
            .extraInv(UPDATED_EXTRA_INV)
            .exencion(UPDATED_EXENCION)
            .extraExe(UPDATED_EXTRA_EXE)
            .muerteAcc(UPDATED_MUERTE_ACC)
            .extraAcc(UPDATED_EXTRA_ACC)
            .valorGff(UPDATED_VALOR_GFF)
            .valorGffCovid(UPDATED_VALOR_GFF_COVID)
            .extraGff(UPDATED_EXTRA_GFF)
            .primaGff(UPDATED_PRIMA_GFF)
            .primaCa(UPDATED_PRIMA_CA)
            .extraCa(UPDATED_EXTRA_CA)
            .primaGe(UPDATED_PRIMA_GE)
            .extraGe(UPDATED_EXTRA_GE)
            .primaIqs(UPDATED_PRIMA_IQS)
            .extraIqa(UPDATED_EXTRA_IQA)
            .primaIqv(UPDATED_PRIMA_IQV)
            .extraIqv(UPDATED_EXTRA_IQV);
        return tMASEGURADO;
    }

    @BeforeEach
    public void initTest() {
        tMASEGURADO = createEntity(em);
    }

    @Test
    @Transactional
    void createTMASEGURADO() throws Exception {
        int databaseSizeBeforeCreate = tMASEGURADORepository.findAll().size();
        // Create the TMASEGURADO
        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isCreated());

        // Validate the TMASEGURADO in the database
        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeCreate + 1);
        TMASEGURADO testTMASEGURADO = tMASEGURADOList.get(tMASEGURADOList.size() - 1);
        assertThat(testTMASEGURADO.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testTMASEGURADO.getExcedente()).isEqualTo(DEFAULT_EXCEDENTE);
        assertThat(testTMASEGURADO.getSubgrupo()).isEqualTo(DEFAULT_SUBGRUPO);
        assertThat(testTMASEGURADO.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testTMASEGURADO.getfNacimiento()).isEqualTo(DEFAULT_F_NACIMIENTO);
        assertThat(testTMASEGURADO.getSueldo()).isEqualTo(DEFAULT_SUELDO);
        assertThat(testTMASEGURADO.getReglaMonto()).isEqualTo(DEFAULT_REGLA_MONTO);
        assertThat(testTMASEGURADO.getEdad()).isEqualTo(DEFAULT_EDAD);
        assertThat(testTMASEGURADO.getSaTotal()).isEqualTo(DEFAULT_SA_TOTAL);
        assertThat(testTMASEGURADO.getSaTopado()).isEqualTo(DEFAULT_SA_TOPADO);
        assertThat(testTMASEGURADO.getBasica()).isEqualTo(DEFAULT_BASICA);
        assertThat(testTMASEGURADO.getBasicaCovid()).isEqualTo(DEFAULT_BASICA_COVID);
        assertThat(testTMASEGURADO.getExtrabas()).isEqualTo(DEFAULT_EXTRABAS);
        assertThat(testTMASEGURADO.getPrimaBasica()).isEqualTo(DEFAULT_PRIMA_BASICA);
        assertThat(testTMASEGURADO.getInvalidez()).isEqualTo(DEFAULT_INVALIDEZ);
        assertThat(testTMASEGURADO.getExtraInv()).isEqualTo(DEFAULT_EXTRA_INV);
        assertThat(testTMASEGURADO.getExencion()).isEqualTo(DEFAULT_EXENCION);
        assertThat(testTMASEGURADO.getExtraExe()).isEqualTo(DEFAULT_EXTRA_EXE);
        assertThat(testTMASEGURADO.getMuerteAcc()).isEqualTo(DEFAULT_MUERTE_ACC);
        assertThat(testTMASEGURADO.getExtraAcc()).isEqualTo(DEFAULT_EXTRA_ACC);
        assertThat(testTMASEGURADO.getValorGff()).isEqualTo(DEFAULT_VALOR_GFF);
        assertThat(testTMASEGURADO.getValorGffCovid()).isEqualTo(DEFAULT_VALOR_GFF_COVID);
        assertThat(testTMASEGURADO.getExtraGff()).isEqualTo(DEFAULT_EXTRA_GFF);
        assertThat(testTMASEGURADO.getPrimaGff()).isEqualTo(DEFAULT_PRIMA_GFF);
        assertThat(testTMASEGURADO.getPrimaCa()).isEqualTo(DEFAULT_PRIMA_CA);
        assertThat(testTMASEGURADO.getExtraCa()).isEqualTo(DEFAULT_EXTRA_CA);
        assertThat(testTMASEGURADO.getPrimaGe()).isEqualTo(DEFAULT_PRIMA_GE);
        assertThat(testTMASEGURADO.getExtraGe()).isEqualTo(DEFAULT_EXTRA_GE);
        assertThat(testTMASEGURADO.getPrimaIqs()).isEqualTo(DEFAULT_PRIMA_IQS);
        assertThat(testTMASEGURADO.getExtraIqa()).isEqualTo(DEFAULT_EXTRA_IQA);
        assertThat(testTMASEGURADO.getPrimaIqv()).isEqualTo(DEFAULT_PRIMA_IQV);
        assertThat(testTMASEGURADO.getExtraIqv()).isEqualTo(DEFAULT_EXTRA_IQV);
    }

    @Test
    @Transactional
    void createTMASEGURADOWithExistingId() throws Exception {
        // Create the TMASEGURADO with an existing ID
        tMASEGURADO.setIdAsegurados(1L);

        int databaseSizeBeforeCreate = tMASEGURADORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        // Validate the TMASEGURADO in the database
        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setNumero(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExcedenteIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setExcedente(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubgrupoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setSubgrupo(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setNombre(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkfNacimientoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setfNacimiento(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSueldoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setSueldo(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReglaMontoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setReglaMonto(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEdadIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setEdad(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSaTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setSaTotal(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSaTopadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setSaTopado(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBasicaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setBasica(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBasicaCovidIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setBasicaCovid(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExtrabasIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setExtrabas(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrimaBasicaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setPrimaBasica(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInvalidezIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setInvalidez(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExtraInvIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setExtraInv(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExencionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setExencion(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExtraExeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setExtraExe(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMuerteAccIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setMuerteAcc(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExtraAccIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setExtraAcc(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorGffIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setValorGff(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorGffCovidIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setValorGffCovid(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExtraGffIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setExtraGff(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrimaGffIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setPrimaGff(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrimaCaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setPrimaCa(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExtraCaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setExtraCa(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrimaGeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setPrimaGe(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExtraGeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setExtraGe(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrimaIqsIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setPrimaIqs(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExtraIqaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setExtraIqa(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrimaIqvIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setPrimaIqv(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExtraIqvIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMASEGURADORepository.findAll().size();
        // set the field null
        tMASEGURADO.setExtraIqv(null);

        // Create the TMASEGURADO, which fails.

        restTMASEGURADOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isBadRequest());

        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTMASEGURADOS() throws Exception {
        // Initialize the database
        tMASEGURADORepository.saveAndFlush(tMASEGURADO);

        // Get all the tMASEGURADOList
        restTMASEGURADOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idAsegurados,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idAsegurados").value(hasItem(tMASEGURADO.getIdAsegurados().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())))
            .andExpect(jsonPath("$.[*].excedente").value(hasItem(DEFAULT_EXCEDENTE.intValue())))
            .andExpect(jsonPath("$.[*].subgrupo").value(hasItem(DEFAULT_SUBGRUPO.intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].fNacimiento").value(hasItem(DEFAULT_F_NACIMIENTO.toString())))
            .andExpect(jsonPath("$.[*].sueldo").value(hasItem(DEFAULT_SUELDO.intValue())))
            .andExpect(jsonPath("$.[*].reglaMonto").value(hasItem(DEFAULT_REGLA_MONTO.intValue())))
            .andExpect(jsonPath("$.[*].edad").value(hasItem(DEFAULT_EDAD.intValue())))
            .andExpect(jsonPath("$.[*].saTotal").value(hasItem(DEFAULT_SA_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].saTopado").value(hasItem(DEFAULT_SA_TOPADO.intValue())))
            .andExpect(jsonPath("$.[*].basica").value(hasItem(DEFAULT_BASICA.intValue())))
            .andExpect(jsonPath("$.[*].basicaCovid").value(hasItem(DEFAULT_BASICA_COVID.intValue())))
            .andExpect(jsonPath("$.[*].extrabas").value(hasItem(DEFAULT_EXTRABAS.intValue())))
            .andExpect(jsonPath("$.[*].primaBasica").value(hasItem(DEFAULT_PRIMA_BASICA.intValue())))
            .andExpect(jsonPath("$.[*].invalidez").value(hasItem(DEFAULT_INVALIDEZ.intValue())))
            .andExpect(jsonPath("$.[*].extraInv").value(hasItem(DEFAULT_EXTRA_INV.intValue())))
            .andExpect(jsonPath("$.[*].exencion").value(hasItem(DEFAULT_EXENCION.intValue())))
            .andExpect(jsonPath("$.[*].extraExe").value(hasItem(DEFAULT_EXTRA_EXE.intValue())))
            .andExpect(jsonPath("$.[*].muerteAcc").value(hasItem(DEFAULT_MUERTE_ACC.intValue())))
            .andExpect(jsonPath("$.[*].extraAcc").value(hasItem(DEFAULT_EXTRA_ACC.intValue())))
            .andExpect(jsonPath("$.[*].valorGff").value(hasItem(DEFAULT_VALOR_GFF.intValue())))
            .andExpect(jsonPath("$.[*].valorGffCovid").value(hasItem(DEFAULT_VALOR_GFF_COVID.intValue())))
            .andExpect(jsonPath("$.[*].extraGff").value(hasItem(DEFAULT_EXTRA_GFF.intValue())))
            .andExpect(jsonPath("$.[*].primaGff").value(hasItem(DEFAULT_PRIMA_GFF.intValue())))
            .andExpect(jsonPath("$.[*].primaCa").value(hasItem(DEFAULT_PRIMA_CA.intValue())))
            .andExpect(jsonPath("$.[*].extraCa").value(hasItem(DEFAULT_EXTRA_CA.intValue())))
            .andExpect(jsonPath("$.[*].primaGe").value(hasItem(DEFAULT_PRIMA_GE.intValue())))
            .andExpect(jsonPath("$.[*].extraGe").value(hasItem(DEFAULT_EXTRA_GE.intValue())))
            .andExpect(jsonPath("$.[*].primaIqs").value(hasItem(DEFAULT_PRIMA_IQS.intValue())))
            .andExpect(jsonPath("$.[*].extraIqa").value(hasItem(DEFAULT_EXTRA_IQA.intValue())))
            .andExpect(jsonPath("$.[*].primaIqv").value(hasItem(DEFAULT_PRIMA_IQV.intValue())))
            .andExpect(jsonPath("$.[*].extraIqv").value(hasItem(DEFAULT_EXTRA_IQV.intValue())));
    }

    @Test
    @Transactional
    void getTMASEGURADO() throws Exception {
        // Initialize the database
        tMASEGURADORepository.saveAndFlush(tMASEGURADO);

        // Get the tMASEGURADO
        restTMASEGURADOMockMvc
            .perform(get(ENTITY_API_URL_ID, tMASEGURADO.getIdAsegurados()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idAsegurados").value(tMASEGURADO.getIdAsegurados().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.intValue()))
            .andExpect(jsonPath("$.excedente").value(DEFAULT_EXCEDENTE.intValue()))
            .andExpect(jsonPath("$.subgrupo").value(DEFAULT_SUBGRUPO.intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.fNacimiento").value(DEFAULT_F_NACIMIENTO.toString()))
            .andExpect(jsonPath("$.sueldo").value(DEFAULT_SUELDO.intValue()))
            .andExpect(jsonPath("$.reglaMonto").value(DEFAULT_REGLA_MONTO.intValue()))
            .andExpect(jsonPath("$.edad").value(DEFAULT_EDAD.intValue()))
            .andExpect(jsonPath("$.saTotal").value(DEFAULT_SA_TOTAL.intValue()))
            .andExpect(jsonPath("$.saTopado").value(DEFAULT_SA_TOPADO.intValue()))
            .andExpect(jsonPath("$.basica").value(DEFAULT_BASICA.intValue()))
            .andExpect(jsonPath("$.basicaCovid").value(DEFAULT_BASICA_COVID.intValue()))
            .andExpect(jsonPath("$.extrabas").value(DEFAULT_EXTRABAS.intValue()))
            .andExpect(jsonPath("$.primaBasica").value(DEFAULT_PRIMA_BASICA.intValue()))
            .andExpect(jsonPath("$.invalidez").value(DEFAULT_INVALIDEZ.intValue()))
            .andExpect(jsonPath("$.extraInv").value(DEFAULT_EXTRA_INV.intValue()))
            .andExpect(jsonPath("$.exencion").value(DEFAULT_EXENCION.intValue()))
            .andExpect(jsonPath("$.extraExe").value(DEFAULT_EXTRA_EXE.intValue()))
            .andExpect(jsonPath("$.muerteAcc").value(DEFAULT_MUERTE_ACC.intValue()))
            .andExpect(jsonPath("$.extraAcc").value(DEFAULT_EXTRA_ACC.intValue()))
            .andExpect(jsonPath("$.valorGff").value(DEFAULT_VALOR_GFF.intValue()))
            .andExpect(jsonPath("$.valorGffCovid").value(DEFAULT_VALOR_GFF_COVID.intValue()))
            .andExpect(jsonPath("$.extraGff").value(DEFAULT_EXTRA_GFF.intValue()))
            .andExpect(jsonPath("$.primaGff").value(DEFAULT_PRIMA_GFF.intValue()))
            .andExpect(jsonPath("$.primaCa").value(DEFAULT_PRIMA_CA.intValue()))
            .andExpect(jsonPath("$.extraCa").value(DEFAULT_EXTRA_CA.intValue()))
            .andExpect(jsonPath("$.primaGe").value(DEFAULT_PRIMA_GE.intValue()))
            .andExpect(jsonPath("$.extraGe").value(DEFAULT_EXTRA_GE.intValue()))
            .andExpect(jsonPath("$.primaIqs").value(DEFAULT_PRIMA_IQS.intValue()))
            .andExpect(jsonPath("$.extraIqa").value(DEFAULT_EXTRA_IQA.intValue()))
            .andExpect(jsonPath("$.primaIqv").value(DEFAULT_PRIMA_IQV.intValue()))
            .andExpect(jsonPath("$.extraIqv").value(DEFAULT_EXTRA_IQV.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTMASEGURADO() throws Exception {
        // Get the tMASEGURADO
        restTMASEGURADOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTMASEGURADO() throws Exception {
        // Initialize the database
        tMASEGURADORepository.saveAndFlush(tMASEGURADO);

        int databaseSizeBeforeUpdate = tMASEGURADORepository.findAll().size();

        // Update the tMASEGURADO
        TMASEGURADO updatedTMASEGURADO = tMASEGURADORepository.findById(tMASEGURADO.getIdAsegurados()).get();
        // Disconnect from session so that the updates on updatedTMASEGURADO are not directly saved in db
        em.detach(updatedTMASEGURADO);
        updatedTMASEGURADO
            .numero(UPDATED_NUMERO)
            .excedente(UPDATED_EXCEDENTE)
            .subgrupo(UPDATED_SUBGRUPO)
            .nombre(UPDATED_NOMBRE)
            .fNacimiento(UPDATED_F_NACIMIENTO)
            .sueldo(UPDATED_SUELDO)
            .reglaMonto(UPDATED_REGLA_MONTO)
            .edad(UPDATED_EDAD)
            .saTotal(UPDATED_SA_TOTAL)
            .saTopado(UPDATED_SA_TOPADO)
            .basica(UPDATED_BASICA)
            .basicaCovid(UPDATED_BASICA_COVID)
            .extrabas(UPDATED_EXTRABAS)
            .primaBasica(UPDATED_PRIMA_BASICA)
            .invalidez(UPDATED_INVALIDEZ)
            .extraInv(UPDATED_EXTRA_INV)
            .exencion(UPDATED_EXENCION)
            .extraExe(UPDATED_EXTRA_EXE)
            .muerteAcc(UPDATED_MUERTE_ACC)
            .extraAcc(UPDATED_EXTRA_ACC)
            .valorGff(UPDATED_VALOR_GFF)
            .valorGffCovid(UPDATED_VALOR_GFF_COVID)
            .extraGff(UPDATED_EXTRA_GFF)
            .primaGff(UPDATED_PRIMA_GFF)
            .primaCa(UPDATED_PRIMA_CA)
            .extraCa(UPDATED_EXTRA_CA)
            .primaGe(UPDATED_PRIMA_GE)
            .extraGe(UPDATED_EXTRA_GE)
            .primaIqs(UPDATED_PRIMA_IQS)
            .extraIqa(UPDATED_EXTRA_IQA)
            .primaIqv(UPDATED_PRIMA_IQV)
            .extraIqv(UPDATED_EXTRA_IQV);

        restTMASEGURADOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTMASEGURADO.getIdAsegurados())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTMASEGURADO))
            )
            .andExpect(status().isOk());

        // Validate the TMASEGURADO in the database
        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeUpdate);
        TMASEGURADO testTMASEGURADO = tMASEGURADOList.get(tMASEGURADOList.size() - 1);
        assertThat(testTMASEGURADO.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testTMASEGURADO.getExcedente()).isEqualTo(UPDATED_EXCEDENTE);
        assertThat(testTMASEGURADO.getSubgrupo()).isEqualTo(UPDATED_SUBGRUPO);
        assertThat(testTMASEGURADO.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testTMASEGURADO.getfNacimiento()).isEqualTo(UPDATED_F_NACIMIENTO);
        assertThat(testTMASEGURADO.getSueldo()).isEqualTo(UPDATED_SUELDO);
        assertThat(testTMASEGURADO.getReglaMonto()).isEqualTo(UPDATED_REGLA_MONTO);
        assertThat(testTMASEGURADO.getEdad()).isEqualTo(UPDATED_EDAD);
        assertThat(testTMASEGURADO.getSaTotal()).isEqualTo(UPDATED_SA_TOTAL);
        assertThat(testTMASEGURADO.getSaTopado()).isEqualTo(UPDATED_SA_TOPADO);
        assertThat(testTMASEGURADO.getBasica()).isEqualTo(UPDATED_BASICA);
        assertThat(testTMASEGURADO.getBasicaCovid()).isEqualTo(UPDATED_BASICA_COVID);
        assertThat(testTMASEGURADO.getExtrabas()).isEqualTo(UPDATED_EXTRABAS);
        assertThat(testTMASEGURADO.getPrimaBasica()).isEqualTo(UPDATED_PRIMA_BASICA);
        assertThat(testTMASEGURADO.getInvalidez()).isEqualTo(UPDATED_INVALIDEZ);
        assertThat(testTMASEGURADO.getExtraInv()).isEqualTo(UPDATED_EXTRA_INV);
        assertThat(testTMASEGURADO.getExencion()).isEqualTo(UPDATED_EXENCION);
        assertThat(testTMASEGURADO.getExtraExe()).isEqualTo(UPDATED_EXTRA_EXE);
        assertThat(testTMASEGURADO.getMuerteAcc()).isEqualTo(UPDATED_MUERTE_ACC);
        assertThat(testTMASEGURADO.getExtraAcc()).isEqualTo(UPDATED_EXTRA_ACC);
        assertThat(testTMASEGURADO.getValorGff()).isEqualTo(UPDATED_VALOR_GFF);
        assertThat(testTMASEGURADO.getValorGffCovid()).isEqualTo(UPDATED_VALOR_GFF_COVID);
        assertThat(testTMASEGURADO.getExtraGff()).isEqualTo(UPDATED_EXTRA_GFF);
        assertThat(testTMASEGURADO.getPrimaGff()).isEqualTo(UPDATED_PRIMA_GFF);
        assertThat(testTMASEGURADO.getPrimaCa()).isEqualTo(UPDATED_PRIMA_CA);
        assertThat(testTMASEGURADO.getExtraCa()).isEqualTo(UPDATED_EXTRA_CA);
        assertThat(testTMASEGURADO.getPrimaGe()).isEqualTo(UPDATED_PRIMA_GE);
        assertThat(testTMASEGURADO.getExtraGe()).isEqualTo(UPDATED_EXTRA_GE);
        assertThat(testTMASEGURADO.getPrimaIqs()).isEqualTo(UPDATED_PRIMA_IQS);
        assertThat(testTMASEGURADO.getExtraIqa()).isEqualTo(UPDATED_EXTRA_IQA);
        assertThat(testTMASEGURADO.getPrimaIqv()).isEqualTo(UPDATED_PRIMA_IQV);
        assertThat(testTMASEGURADO.getExtraIqv()).isEqualTo(UPDATED_EXTRA_IQV);
    }

    @Test
    @Transactional
    void putNonExistingTMASEGURADO() throws Exception {
        int databaseSizeBeforeUpdate = tMASEGURADORepository.findAll().size();
        tMASEGURADO.setIdAsegurados(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTMASEGURADOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tMASEGURADO.getIdAsegurados())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMASEGURADO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMASEGURADO in the database
        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTMASEGURADO() throws Exception {
        int databaseSizeBeforeUpdate = tMASEGURADORepository.findAll().size();
        tMASEGURADO.setIdAsegurados(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMASEGURADOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMASEGURADO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMASEGURADO in the database
        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTMASEGURADO() throws Exception {
        int databaseSizeBeforeUpdate = tMASEGURADORepository.findAll().size();
        tMASEGURADO.setIdAsegurados(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMASEGURADOMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMASEGURADO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TMASEGURADO in the database
        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTMASEGURADOWithPatch() throws Exception {
        // Initialize the database
        tMASEGURADORepository.saveAndFlush(tMASEGURADO);

        int databaseSizeBeforeUpdate = tMASEGURADORepository.findAll().size();

        // Update the tMASEGURADO using partial update
        TMASEGURADO partialUpdatedTMASEGURADO = new TMASEGURADO();
        partialUpdatedTMASEGURADO.setIdAsegurados(tMASEGURADO.getIdAsegurados());

        partialUpdatedTMASEGURADO
            .numero(UPDATED_NUMERO)
            .nombre(UPDATED_NOMBRE)
            .sueldo(UPDATED_SUELDO)
            .reglaMonto(UPDATED_REGLA_MONTO)
            .saTopado(UPDATED_SA_TOPADO)
            .extrabas(UPDATED_EXTRABAS)
            .primaBasica(UPDATED_PRIMA_BASICA)
            .extraInv(UPDATED_EXTRA_INV)
            .extraExe(UPDATED_EXTRA_EXE)
            .extraAcc(UPDATED_EXTRA_ACC)
            .valorGff(UPDATED_VALOR_GFF)
            .valorGffCovid(UPDATED_VALOR_GFF_COVID)
            .primaGff(UPDATED_PRIMA_GFF)
            .extraCa(UPDATED_EXTRA_CA)
            .extraGe(UPDATED_EXTRA_GE)
            .primaIqs(UPDATED_PRIMA_IQS)
            .extraIqa(UPDATED_EXTRA_IQA)
            .extraIqv(UPDATED_EXTRA_IQV);

        restTMASEGURADOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTMASEGURADO.getIdAsegurados())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTMASEGURADO))
            )
            .andExpect(status().isOk());

        // Validate the TMASEGURADO in the database
        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeUpdate);
        TMASEGURADO testTMASEGURADO = tMASEGURADOList.get(tMASEGURADOList.size() - 1);
        assertThat(testTMASEGURADO.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testTMASEGURADO.getExcedente()).isEqualTo(DEFAULT_EXCEDENTE);
        assertThat(testTMASEGURADO.getSubgrupo()).isEqualTo(DEFAULT_SUBGRUPO);
        assertThat(testTMASEGURADO.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testTMASEGURADO.getfNacimiento()).isEqualTo(DEFAULT_F_NACIMIENTO);
        assertThat(testTMASEGURADO.getSueldo()).isEqualTo(UPDATED_SUELDO);
        assertThat(testTMASEGURADO.getReglaMonto()).isEqualTo(UPDATED_REGLA_MONTO);
        assertThat(testTMASEGURADO.getEdad()).isEqualTo(DEFAULT_EDAD);
        assertThat(testTMASEGURADO.getSaTotal()).isEqualTo(DEFAULT_SA_TOTAL);
        assertThat(testTMASEGURADO.getSaTopado()).isEqualTo(UPDATED_SA_TOPADO);
        assertThat(testTMASEGURADO.getBasica()).isEqualTo(DEFAULT_BASICA);
        assertThat(testTMASEGURADO.getBasicaCovid()).isEqualTo(DEFAULT_BASICA_COVID);
        assertThat(testTMASEGURADO.getExtrabas()).isEqualTo(UPDATED_EXTRABAS);
        assertThat(testTMASEGURADO.getPrimaBasica()).isEqualTo(UPDATED_PRIMA_BASICA);
        assertThat(testTMASEGURADO.getInvalidez()).isEqualTo(DEFAULT_INVALIDEZ);
        assertThat(testTMASEGURADO.getExtraInv()).isEqualTo(UPDATED_EXTRA_INV);
        assertThat(testTMASEGURADO.getExencion()).isEqualTo(DEFAULT_EXENCION);
        assertThat(testTMASEGURADO.getExtraExe()).isEqualTo(UPDATED_EXTRA_EXE);
        assertThat(testTMASEGURADO.getMuerteAcc()).isEqualTo(DEFAULT_MUERTE_ACC);
        assertThat(testTMASEGURADO.getExtraAcc()).isEqualTo(UPDATED_EXTRA_ACC);
        assertThat(testTMASEGURADO.getValorGff()).isEqualTo(UPDATED_VALOR_GFF);
        assertThat(testTMASEGURADO.getValorGffCovid()).isEqualTo(UPDATED_VALOR_GFF_COVID);
        assertThat(testTMASEGURADO.getExtraGff()).isEqualTo(DEFAULT_EXTRA_GFF);
        assertThat(testTMASEGURADO.getPrimaGff()).isEqualTo(UPDATED_PRIMA_GFF);
        assertThat(testTMASEGURADO.getPrimaCa()).isEqualTo(DEFAULT_PRIMA_CA);
        assertThat(testTMASEGURADO.getExtraCa()).isEqualTo(UPDATED_EXTRA_CA);
        assertThat(testTMASEGURADO.getPrimaGe()).isEqualTo(DEFAULT_PRIMA_GE);
        assertThat(testTMASEGURADO.getExtraGe()).isEqualTo(UPDATED_EXTRA_GE);
        assertThat(testTMASEGURADO.getPrimaIqs()).isEqualTo(UPDATED_PRIMA_IQS);
        assertThat(testTMASEGURADO.getExtraIqa()).isEqualTo(UPDATED_EXTRA_IQA);
        assertThat(testTMASEGURADO.getPrimaIqv()).isEqualTo(DEFAULT_PRIMA_IQV);
        assertThat(testTMASEGURADO.getExtraIqv()).isEqualTo(UPDATED_EXTRA_IQV);
    }

    @Test
    @Transactional
    void fullUpdateTMASEGURADOWithPatch() throws Exception {
        // Initialize the database
        tMASEGURADORepository.saveAndFlush(tMASEGURADO);

        int databaseSizeBeforeUpdate = tMASEGURADORepository.findAll().size();

        // Update the tMASEGURADO using partial update
        TMASEGURADO partialUpdatedTMASEGURADO = new TMASEGURADO();
        partialUpdatedTMASEGURADO.setIdAsegurados(tMASEGURADO.getIdAsegurados());

        partialUpdatedTMASEGURADO
            .numero(UPDATED_NUMERO)
            .excedente(UPDATED_EXCEDENTE)
            .subgrupo(UPDATED_SUBGRUPO)
            .nombre(UPDATED_NOMBRE)
            .fNacimiento(UPDATED_F_NACIMIENTO)
            .sueldo(UPDATED_SUELDO)
            .reglaMonto(UPDATED_REGLA_MONTO)
            .edad(UPDATED_EDAD)
            .saTotal(UPDATED_SA_TOTAL)
            .saTopado(UPDATED_SA_TOPADO)
            .basica(UPDATED_BASICA)
            .basicaCovid(UPDATED_BASICA_COVID)
            .extrabas(UPDATED_EXTRABAS)
            .primaBasica(UPDATED_PRIMA_BASICA)
            .invalidez(UPDATED_INVALIDEZ)
            .extraInv(UPDATED_EXTRA_INV)
            .exencion(UPDATED_EXENCION)
            .extraExe(UPDATED_EXTRA_EXE)
            .muerteAcc(UPDATED_MUERTE_ACC)
            .extraAcc(UPDATED_EXTRA_ACC)
            .valorGff(UPDATED_VALOR_GFF)
            .valorGffCovid(UPDATED_VALOR_GFF_COVID)
            .extraGff(UPDATED_EXTRA_GFF)
            .primaGff(UPDATED_PRIMA_GFF)
            .primaCa(UPDATED_PRIMA_CA)
            .extraCa(UPDATED_EXTRA_CA)
            .primaGe(UPDATED_PRIMA_GE)
            .extraGe(UPDATED_EXTRA_GE)
            .primaIqs(UPDATED_PRIMA_IQS)
            .extraIqa(UPDATED_EXTRA_IQA)
            .primaIqv(UPDATED_PRIMA_IQV)
            .extraIqv(UPDATED_EXTRA_IQV);

        restTMASEGURADOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTMASEGURADO.getIdAsegurados())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTMASEGURADO))
            )
            .andExpect(status().isOk());

        // Validate the TMASEGURADO in the database
        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeUpdate);
        TMASEGURADO testTMASEGURADO = tMASEGURADOList.get(tMASEGURADOList.size() - 1);
        assertThat(testTMASEGURADO.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testTMASEGURADO.getExcedente()).isEqualTo(UPDATED_EXCEDENTE);
        assertThat(testTMASEGURADO.getSubgrupo()).isEqualTo(UPDATED_SUBGRUPO);
        assertThat(testTMASEGURADO.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testTMASEGURADO.getfNacimiento()).isEqualTo(UPDATED_F_NACIMIENTO);
        assertThat(testTMASEGURADO.getSueldo()).isEqualTo(UPDATED_SUELDO);
        assertThat(testTMASEGURADO.getReglaMonto()).isEqualTo(UPDATED_REGLA_MONTO);
        assertThat(testTMASEGURADO.getEdad()).isEqualTo(UPDATED_EDAD);
        assertThat(testTMASEGURADO.getSaTotal()).isEqualTo(UPDATED_SA_TOTAL);
        assertThat(testTMASEGURADO.getSaTopado()).isEqualTo(UPDATED_SA_TOPADO);
        assertThat(testTMASEGURADO.getBasica()).isEqualTo(UPDATED_BASICA);
        assertThat(testTMASEGURADO.getBasicaCovid()).isEqualTo(UPDATED_BASICA_COVID);
        assertThat(testTMASEGURADO.getExtrabas()).isEqualTo(UPDATED_EXTRABAS);
        assertThat(testTMASEGURADO.getPrimaBasica()).isEqualTo(UPDATED_PRIMA_BASICA);
        assertThat(testTMASEGURADO.getInvalidez()).isEqualTo(UPDATED_INVALIDEZ);
        assertThat(testTMASEGURADO.getExtraInv()).isEqualTo(UPDATED_EXTRA_INV);
        assertThat(testTMASEGURADO.getExencion()).isEqualTo(UPDATED_EXENCION);
        assertThat(testTMASEGURADO.getExtraExe()).isEqualTo(UPDATED_EXTRA_EXE);
        assertThat(testTMASEGURADO.getMuerteAcc()).isEqualTo(UPDATED_MUERTE_ACC);
        assertThat(testTMASEGURADO.getExtraAcc()).isEqualTo(UPDATED_EXTRA_ACC);
        assertThat(testTMASEGURADO.getValorGff()).isEqualTo(UPDATED_VALOR_GFF);
        assertThat(testTMASEGURADO.getValorGffCovid()).isEqualTo(UPDATED_VALOR_GFF_COVID);
        assertThat(testTMASEGURADO.getExtraGff()).isEqualTo(UPDATED_EXTRA_GFF);
        assertThat(testTMASEGURADO.getPrimaGff()).isEqualTo(UPDATED_PRIMA_GFF);
        assertThat(testTMASEGURADO.getPrimaCa()).isEqualTo(UPDATED_PRIMA_CA);
        assertThat(testTMASEGURADO.getExtraCa()).isEqualTo(UPDATED_EXTRA_CA);
        assertThat(testTMASEGURADO.getPrimaGe()).isEqualTo(UPDATED_PRIMA_GE);
        assertThat(testTMASEGURADO.getExtraGe()).isEqualTo(UPDATED_EXTRA_GE);
        assertThat(testTMASEGURADO.getPrimaIqs()).isEqualTo(UPDATED_PRIMA_IQS);
        assertThat(testTMASEGURADO.getExtraIqa()).isEqualTo(UPDATED_EXTRA_IQA);
        assertThat(testTMASEGURADO.getPrimaIqv()).isEqualTo(UPDATED_PRIMA_IQV);
        assertThat(testTMASEGURADO.getExtraIqv()).isEqualTo(UPDATED_EXTRA_IQV);
    }

    @Test
    @Transactional
    void patchNonExistingTMASEGURADO() throws Exception {
        int databaseSizeBeforeUpdate = tMASEGURADORepository.findAll().size();
        tMASEGURADO.setIdAsegurados(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTMASEGURADOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tMASEGURADO.getIdAsegurados())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tMASEGURADO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMASEGURADO in the database
        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTMASEGURADO() throws Exception {
        int databaseSizeBeforeUpdate = tMASEGURADORepository.findAll().size();
        tMASEGURADO.setIdAsegurados(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMASEGURADOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tMASEGURADO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMASEGURADO in the database
        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTMASEGURADO() throws Exception {
        int databaseSizeBeforeUpdate = tMASEGURADORepository.findAll().size();
        tMASEGURADO.setIdAsegurados(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMASEGURADOMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tMASEGURADO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TMASEGURADO in the database
        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTMASEGURADO() throws Exception {
        // Initialize the database
        tMASEGURADORepository.saveAndFlush(tMASEGURADO);

        int databaseSizeBeforeDelete = tMASEGURADORepository.findAll().size();

        // Delete the tMASEGURADO
        restTMASEGURADOMockMvc
            .perform(delete(ENTITY_API_URL_ID, tMASEGURADO.getIdAsegurados()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TMASEGURADO> tMASEGURADOList = tMASEGURADORepository.findAll();
        assertThat(tMASEGURADOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
