package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCCUOTARIESGO;
import com.allianze.apicotizador.repository.TCCUOTARIESGORepository;
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
 * Integration tests for the {@link TCCUOTARIESGOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCCUOTARIESGOResourceIT {

    private static final String DEFAULT_EDAD = "AAAAAAAAAA";
    private static final String UPDATED_EDAD = "BBBBBBBBBB";

    private static final Long DEFAULT_VALOR_BA = 1L;
    private static final Long UPDATED_VALOR_BA = 2L;

    private static final Long DEFAULT_VALOR_BA_COVID = 1L;
    private static final Long UPDATED_VALOR_BA_COVID = 2L;

    private static final Long DEFAULT_VALOR_BIP_TRES = 1L;
    private static final Long UPDATED_VALOR_BIP_TRES = 2L;

    private static final Long DEFAULT_VALOR_BIP_SEIS = 1L;
    private static final Long UPDATED_VALOR_BIP_SEIS = 2L;

    private static final Long DEFAULT_VALOR_BIT = 1L;
    private static final Long UPDATED_VALOR_BIT = 2L;

    private static final Long DEFAULT_VALOR_MA = 1L;
    private static final Long UPDATED_VALOR_MA = 2L;

    private static final Long DEFAULT_VALOR_DI = 1L;
    private static final Long UPDATED_VALOR_DI = 2L;

    private static final Long DEFAULT_VALOR_TI = 1L;
    private static final Long UPDATED_VALOR_TI = 2L;

    private static final Long DEFAULT_VALOR_GFF = 1L;
    private static final Long UPDATED_VALOR_GFF = 2L;

    private static final Long DEFAULT_VALOR_GFF_COVID = 1L;
    private static final Long UPDATED_VALOR_GFF_COVID = 2L;

    private static final Long DEFAULT_VALOR_CA = 1L;
    private static final Long UPDATED_VALOR_CA = 2L;

    private static final Long DEFAULT_VALOR_GE = 1L;
    private static final Long UPDATED_VALOR_GE = 2L;

    private static final Long DEFAULT_VALOR_IQ_UNO = 1L;
    private static final Long UPDATED_VALOR_IQ_UNO = 2L;

    private static final Long DEFAULT_VALOR_IQ_DOS = 1L;
    private static final Long UPDATED_VALOR_IQ_DOS = 2L;

    private static final String ENTITY_API_URL = "/api/tccuotariesgos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCuotaRiesgo}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCCUOTARIESGORepository tCCUOTARIESGORepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCCUOTARIESGOMockMvc;

    private TCCUOTARIESGO tCCUOTARIESGO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCUOTARIESGO createEntity(EntityManager em) {
        TCCUOTARIESGO tCCUOTARIESGO = new TCCUOTARIESGO()
            .edad(DEFAULT_EDAD)
            .valorBa(DEFAULT_VALOR_BA)
            .valorBaCovid(DEFAULT_VALOR_BA_COVID)
            .valorBipTres(DEFAULT_VALOR_BIP_TRES)
            .valorBipSeis(DEFAULT_VALOR_BIP_SEIS)
            .valorBit(DEFAULT_VALOR_BIT)
            .valorMa(DEFAULT_VALOR_MA)
            .valorDi(DEFAULT_VALOR_DI)
            .valorTi(DEFAULT_VALOR_TI)
            .valorGff(DEFAULT_VALOR_GFF)
            .valorGffCovid(DEFAULT_VALOR_GFF_COVID)
            .valorCa(DEFAULT_VALOR_CA)
            .valorGe(DEFAULT_VALOR_GE)
            .valorIqUno(DEFAULT_VALOR_IQ_UNO)
            .valorIqDos(DEFAULT_VALOR_IQ_DOS);
        return tCCUOTARIESGO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCUOTARIESGO createUpdatedEntity(EntityManager em) {
        TCCUOTARIESGO tCCUOTARIESGO = new TCCUOTARIESGO()
            .edad(UPDATED_EDAD)
            .valorBa(UPDATED_VALOR_BA)
            .valorBaCovid(UPDATED_VALOR_BA_COVID)
            .valorBipTres(UPDATED_VALOR_BIP_TRES)
            .valorBipSeis(UPDATED_VALOR_BIP_SEIS)
            .valorBit(UPDATED_VALOR_BIT)
            .valorMa(UPDATED_VALOR_MA)
            .valorDi(UPDATED_VALOR_DI)
            .valorTi(UPDATED_VALOR_TI)
            .valorGff(UPDATED_VALOR_GFF)
            .valorGffCovid(UPDATED_VALOR_GFF_COVID)
            .valorCa(UPDATED_VALOR_CA)
            .valorGe(UPDATED_VALOR_GE)
            .valorIqUno(UPDATED_VALOR_IQ_UNO)
            .valorIqDos(UPDATED_VALOR_IQ_DOS);
        return tCCUOTARIESGO;
    }

    @BeforeEach
    public void initTest() {
        tCCUOTARIESGO = createEntity(em);
    }

    @Test
    @Transactional
    void createTCCUOTARIESGO() throws Exception {
        int databaseSizeBeforeCreate = tCCUOTARIESGORepository.findAll().size();
        // Create the TCCUOTARIESGO
        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isCreated());

        // Validate the TCCUOTARIESGO in the database
        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeCreate + 1);
        TCCUOTARIESGO testTCCUOTARIESGO = tCCUOTARIESGOList.get(tCCUOTARIESGOList.size() - 1);
        assertThat(testTCCUOTARIESGO.getEdad()).isEqualTo(DEFAULT_EDAD);
        assertThat(testTCCUOTARIESGO.getValorBa()).isEqualTo(DEFAULT_VALOR_BA);
        assertThat(testTCCUOTARIESGO.getValorBaCovid()).isEqualTo(DEFAULT_VALOR_BA_COVID);
        assertThat(testTCCUOTARIESGO.getValorBipTres()).isEqualTo(DEFAULT_VALOR_BIP_TRES);
        assertThat(testTCCUOTARIESGO.getValorBipSeis()).isEqualTo(DEFAULT_VALOR_BIP_SEIS);
        assertThat(testTCCUOTARIESGO.getValorBit()).isEqualTo(DEFAULT_VALOR_BIT);
        assertThat(testTCCUOTARIESGO.getValorMa()).isEqualTo(DEFAULT_VALOR_MA);
        assertThat(testTCCUOTARIESGO.getValorDi()).isEqualTo(DEFAULT_VALOR_DI);
        assertThat(testTCCUOTARIESGO.getValorTi()).isEqualTo(DEFAULT_VALOR_TI);
        assertThat(testTCCUOTARIESGO.getValorGff()).isEqualTo(DEFAULT_VALOR_GFF);
        assertThat(testTCCUOTARIESGO.getValorGffCovid()).isEqualTo(DEFAULT_VALOR_GFF_COVID);
        assertThat(testTCCUOTARIESGO.getValorCa()).isEqualTo(DEFAULT_VALOR_CA);
        assertThat(testTCCUOTARIESGO.getValorGe()).isEqualTo(DEFAULT_VALOR_GE);
        assertThat(testTCCUOTARIESGO.getValorIqUno()).isEqualTo(DEFAULT_VALOR_IQ_UNO);
        assertThat(testTCCUOTARIESGO.getValorIqDos()).isEqualTo(DEFAULT_VALOR_IQ_DOS);
    }

    @Test
    @Transactional
    void createTCCUOTARIESGOWithExistingId() throws Exception {
        // Create the TCCUOTARIESGO with an existing ID
        tCCUOTARIESGO.setIdCuotaRiesgo(1L);

        int databaseSizeBeforeCreate = tCCUOTARIESGORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTARIESGO in the database
        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEdadIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTARIESGORepository.findAll().size();
        // set the field null
        tCCUOTARIESGO.setEdad(null);

        // Create the TCCUOTARIESGO, which fails.

        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorBaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTARIESGORepository.findAll().size();
        // set the field null
        tCCUOTARIESGO.setValorBa(null);

        // Create the TCCUOTARIESGO, which fails.

        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorBaCovidIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTARIESGORepository.findAll().size();
        // set the field null
        tCCUOTARIESGO.setValorBaCovid(null);

        // Create the TCCUOTARIESGO, which fails.

        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorBipTresIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTARIESGORepository.findAll().size();
        // set the field null
        tCCUOTARIESGO.setValorBipTres(null);

        // Create the TCCUOTARIESGO, which fails.

        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorBipSeisIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTARIESGORepository.findAll().size();
        // set the field null
        tCCUOTARIESGO.setValorBipSeis(null);

        // Create the TCCUOTARIESGO, which fails.

        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorBitIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTARIESGORepository.findAll().size();
        // set the field null
        tCCUOTARIESGO.setValorBit(null);

        // Create the TCCUOTARIESGO, which fails.

        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorMaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTARIESGORepository.findAll().size();
        // set the field null
        tCCUOTARIESGO.setValorMa(null);

        // Create the TCCUOTARIESGO, which fails.

        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorDiIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTARIESGORepository.findAll().size();
        // set the field null
        tCCUOTARIESGO.setValorDi(null);

        // Create the TCCUOTARIESGO, which fails.

        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorTiIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTARIESGORepository.findAll().size();
        // set the field null
        tCCUOTARIESGO.setValorTi(null);

        // Create the TCCUOTARIESGO, which fails.

        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorGffIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTARIESGORepository.findAll().size();
        // set the field null
        tCCUOTARIESGO.setValorGff(null);

        // Create the TCCUOTARIESGO, which fails.

        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorGffCovidIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTARIESGORepository.findAll().size();
        // set the field null
        tCCUOTARIESGO.setValorGffCovid(null);

        // Create the TCCUOTARIESGO, which fails.

        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorCaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTARIESGORepository.findAll().size();
        // set the field null
        tCCUOTARIESGO.setValorCa(null);

        // Create the TCCUOTARIESGO, which fails.

        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorGeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTARIESGORepository.findAll().size();
        // set the field null
        tCCUOTARIESGO.setValorGe(null);

        // Create the TCCUOTARIESGO, which fails.

        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorIqUnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTARIESGORepository.findAll().size();
        // set the field null
        tCCUOTARIESGO.setValorIqUno(null);

        // Create the TCCUOTARIESGO, which fails.

        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorIqDosIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTARIESGORepository.findAll().size();
        // set the field null
        tCCUOTARIESGO.setValorIqDos(null);

        // Create the TCCUOTARIESGO, which fails.

        restTCCUOTARIESGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isBadRequest());

        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCCUOTARIESGOS() throws Exception {
        // Initialize the database
        tCCUOTARIESGORepository.saveAndFlush(tCCUOTARIESGO);

        // Get all the tCCUOTARIESGOList
        restTCCUOTARIESGOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCuotaRiesgo,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCuotaRiesgo").value(hasItem(tCCUOTARIESGO.getIdCuotaRiesgo().intValue())))
            .andExpect(jsonPath("$.[*].edad").value(hasItem(DEFAULT_EDAD)))
            .andExpect(jsonPath("$.[*].valorBa").value(hasItem(DEFAULT_VALOR_BA.intValue())))
            .andExpect(jsonPath("$.[*].valorBaCovid").value(hasItem(DEFAULT_VALOR_BA_COVID.intValue())))
            .andExpect(jsonPath("$.[*].valorBipTres").value(hasItem(DEFAULT_VALOR_BIP_TRES.intValue())))
            .andExpect(jsonPath("$.[*].valorBipSeis").value(hasItem(DEFAULT_VALOR_BIP_SEIS.intValue())))
            .andExpect(jsonPath("$.[*].valorBit").value(hasItem(DEFAULT_VALOR_BIT.intValue())))
            .andExpect(jsonPath("$.[*].valorMa").value(hasItem(DEFAULT_VALOR_MA.intValue())))
            .andExpect(jsonPath("$.[*].valorDi").value(hasItem(DEFAULT_VALOR_DI.intValue())))
            .andExpect(jsonPath("$.[*].valorTi").value(hasItem(DEFAULT_VALOR_TI.intValue())))
            .andExpect(jsonPath("$.[*].valorGff").value(hasItem(DEFAULT_VALOR_GFF.intValue())))
            .andExpect(jsonPath("$.[*].valorGffCovid").value(hasItem(DEFAULT_VALOR_GFF_COVID.intValue())))
            .andExpect(jsonPath("$.[*].valorCa").value(hasItem(DEFAULT_VALOR_CA.intValue())))
            .andExpect(jsonPath("$.[*].valorGe").value(hasItem(DEFAULT_VALOR_GE.intValue())))
            .andExpect(jsonPath("$.[*].valorIqUno").value(hasItem(DEFAULT_VALOR_IQ_UNO.intValue())))
            .andExpect(jsonPath("$.[*].valorIqDos").value(hasItem(DEFAULT_VALOR_IQ_DOS.intValue())));
    }

    @Test
    @Transactional
    void getTCCUOTARIESGO() throws Exception {
        // Initialize the database
        tCCUOTARIESGORepository.saveAndFlush(tCCUOTARIESGO);

        // Get the tCCUOTARIESGO
        restTCCUOTARIESGOMockMvc
            .perform(get(ENTITY_API_URL_ID, tCCUOTARIESGO.getIdCuotaRiesgo()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCuotaRiesgo").value(tCCUOTARIESGO.getIdCuotaRiesgo().intValue()))
            .andExpect(jsonPath("$.edad").value(DEFAULT_EDAD))
            .andExpect(jsonPath("$.valorBa").value(DEFAULT_VALOR_BA.intValue()))
            .andExpect(jsonPath("$.valorBaCovid").value(DEFAULT_VALOR_BA_COVID.intValue()))
            .andExpect(jsonPath("$.valorBipTres").value(DEFAULT_VALOR_BIP_TRES.intValue()))
            .andExpect(jsonPath("$.valorBipSeis").value(DEFAULT_VALOR_BIP_SEIS.intValue()))
            .andExpect(jsonPath("$.valorBit").value(DEFAULT_VALOR_BIT.intValue()))
            .andExpect(jsonPath("$.valorMa").value(DEFAULT_VALOR_MA.intValue()))
            .andExpect(jsonPath("$.valorDi").value(DEFAULT_VALOR_DI.intValue()))
            .andExpect(jsonPath("$.valorTi").value(DEFAULT_VALOR_TI.intValue()))
            .andExpect(jsonPath("$.valorGff").value(DEFAULT_VALOR_GFF.intValue()))
            .andExpect(jsonPath("$.valorGffCovid").value(DEFAULT_VALOR_GFF_COVID.intValue()))
            .andExpect(jsonPath("$.valorCa").value(DEFAULT_VALOR_CA.intValue()))
            .andExpect(jsonPath("$.valorGe").value(DEFAULT_VALOR_GE.intValue()))
            .andExpect(jsonPath("$.valorIqUno").value(DEFAULT_VALOR_IQ_UNO.intValue()))
            .andExpect(jsonPath("$.valorIqDos").value(DEFAULT_VALOR_IQ_DOS.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCCUOTARIESGO() throws Exception {
        // Get the tCCUOTARIESGO
        restTCCUOTARIESGOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCCUOTARIESGO() throws Exception {
        // Initialize the database
        tCCUOTARIESGORepository.saveAndFlush(tCCUOTARIESGO);

        int databaseSizeBeforeUpdate = tCCUOTARIESGORepository.findAll().size();

        // Update the tCCUOTARIESGO
        TCCUOTARIESGO updatedTCCUOTARIESGO = tCCUOTARIESGORepository.findById(tCCUOTARIESGO.getIdCuotaRiesgo()).get();
        // Disconnect from session so that the updates on updatedTCCUOTARIESGO are not directly saved in db
        em.detach(updatedTCCUOTARIESGO);
        updatedTCCUOTARIESGO
            .edad(UPDATED_EDAD)
            .valorBa(UPDATED_VALOR_BA)
            .valorBaCovid(UPDATED_VALOR_BA_COVID)
            .valorBipTres(UPDATED_VALOR_BIP_TRES)
            .valorBipSeis(UPDATED_VALOR_BIP_SEIS)
            .valorBit(UPDATED_VALOR_BIT)
            .valorMa(UPDATED_VALOR_MA)
            .valorDi(UPDATED_VALOR_DI)
            .valorTi(UPDATED_VALOR_TI)
            .valorGff(UPDATED_VALOR_GFF)
            .valorGffCovid(UPDATED_VALOR_GFF_COVID)
            .valorCa(UPDATED_VALOR_CA)
            .valorGe(UPDATED_VALOR_GE)
            .valorIqUno(UPDATED_VALOR_IQ_UNO)
            .valorIqDos(UPDATED_VALOR_IQ_DOS);

        restTCCUOTARIESGOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCCUOTARIESGO.getIdCuotaRiesgo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCCUOTARIESGO))
            )
            .andExpect(status().isOk());

        // Validate the TCCUOTARIESGO in the database
        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeUpdate);
        TCCUOTARIESGO testTCCUOTARIESGO = tCCUOTARIESGOList.get(tCCUOTARIESGOList.size() - 1);
        assertThat(testTCCUOTARIESGO.getEdad()).isEqualTo(UPDATED_EDAD);
        assertThat(testTCCUOTARIESGO.getValorBa()).isEqualTo(UPDATED_VALOR_BA);
        assertThat(testTCCUOTARIESGO.getValorBaCovid()).isEqualTo(UPDATED_VALOR_BA_COVID);
        assertThat(testTCCUOTARIESGO.getValorBipTres()).isEqualTo(UPDATED_VALOR_BIP_TRES);
        assertThat(testTCCUOTARIESGO.getValorBipSeis()).isEqualTo(UPDATED_VALOR_BIP_SEIS);
        assertThat(testTCCUOTARIESGO.getValorBit()).isEqualTo(UPDATED_VALOR_BIT);
        assertThat(testTCCUOTARIESGO.getValorMa()).isEqualTo(UPDATED_VALOR_MA);
        assertThat(testTCCUOTARIESGO.getValorDi()).isEqualTo(UPDATED_VALOR_DI);
        assertThat(testTCCUOTARIESGO.getValorTi()).isEqualTo(UPDATED_VALOR_TI);
        assertThat(testTCCUOTARIESGO.getValorGff()).isEqualTo(UPDATED_VALOR_GFF);
        assertThat(testTCCUOTARIESGO.getValorGffCovid()).isEqualTo(UPDATED_VALOR_GFF_COVID);
        assertThat(testTCCUOTARIESGO.getValorCa()).isEqualTo(UPDATED_VALOR_CA);
        assertThat(testTCCUOTARIESGO.getValorGe()).isEqualTo(UPDATED_VALOR_GE);
        assertThat(testTCCUOTARIESGO.getValorIqUno()).isEqualTo(UPDATED_VALOR_IQ_UNO);
        assertThat(testTCCUOTARIESGO.getValorIqDos()).isEqualTo(UPDATED_VALOR_IQ_DOS);
    }

    @Test
    @Transactional
    void putNonExistingTCCUOTARIESGO() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTARIESGORepository.findAll().size();
        tCCUOTARIESGO.setIdCuotaRiesgo(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCUOTARIESGOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCCUOTARIESGO.getIdCuotaRiesgo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTARIESGO in the database
        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCCUOTARIESGO() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTARIESGORepository.findAll().size();
        tCCUOTARIESGO.setIdCuotaRiesgo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTARIESGOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTARIESGO in the database
        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCCUOTARIESGO() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTARIESGORepository.findAll().size();
        tCCUOTARIESGO.setIdCuotaRiesgo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTARIESGOMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCUOTARIESGO in the database
        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCCUOTARIESGOWithPatch() throws Exception {
        // Initialize the database
        tCCUOTARIESGORepository.saveAndFlush(tCCUOTARIESGO);

        int databaseSizeBeforeUpdate = tCCUOTARIESGORepository.findAll().size();

        // Update the tCCUOTARIESGO using partial update
        TCCUOTARIESGO partialUpdatedTCCUOTARIESGO = new TCCUOTARIESGO();
        partialUpdatedTCCUOTARIESGO.setIdCuotaRiesgo(tCCUOTARIESGO.getIdCuotaRiesgo());

        partialUpdatedTCCUOTARIESGO
            .valorBa(UPDATED_VALOR_BA)
            .valorBaCovid(UPDATED_VALOR_BA_COVID)
            .valorBit(UPDATED_VALOR_BIT)
            .valorDi(UPDATED_VALOR_DI)
            .valorGe(UPDATED_VALOR_GE)
            .valorIqUno(UPDATED_VALOR_IQ_UNO)
            .valorIqDos(UPDATED_VALOR_IQ_DOS);

        restTCCUOTARIESGOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCUOTARIESGO.getIdCuotaRiesgo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCUOTARIESGO))
            )
            .andExpect(status().isOk());

        // Validate the TCCUOTARIESGO in the database
        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeUpdate);
        TCCUOTARIESGO testTCCUOTARIESGO = tCCUOTARIESGOList.get(tCCUOTARIESGOList.size() - 1);
        assertThat(testTCCUOTARIESGO.getEdad()).isEqualTo(DEFAULT_EDAD);
        assertThat(testTCCUOTARIESGO.getValorBa()).isEqualTo(UPDATED_VALOR_BA);
        assertThat(testTCCUOTARIESGO.getValorBaCovid()).isEqualTo(UPDATED_VALOR_BA_COVID);
        assertThat(testTCCUOTARIESGO.getValorBipTres()).isEqualTo(DEFAULT_VALOR_BIP_TRES);
        assertThat(testTCCUOTARIESGO.getValorBipSeis()).isEqualTo(DEFAULT_VALOR_BIP_SEIS);
        assertThat(testTCCUOTARIESGO.getValorBit()).isEqualTo(UPDATED_VALOR_BIT);
        assertThat(testTCCUOTARIESGO.getValorMa()).isEqualTo(DEFAULT_VALOR_MA);
        assertThat(testTCCUOTARIESGO.getValorDi()).isEqualTo(UPDATED_VALOR_DI);
        assertThat(testTCCUOTARIESGO.getValorTi()).isEqualTo(DEFAULT_VALOR_TI);
        assertThat(testTCCUOTARIESGO.getValorGff()).isEqualTo(DEFAULT_VALOR_GFF);
        assertThat(testTCCUOTARIESGO.getValorGffCovid()).isEqualTo(DEFAULT_VALOR_GFF_COVID);
        assertThat(testTCCUOTARIESGO.getValorCa()).isEqualTo(DEFAULT_VALOR_CA);
        assertThat(testTCCUOTARIESGO.getValorGe()).isEqualTo(UPDATED_VALOR_GE);
        assertThat(testTCCUOTARIESGO.getValorIqUno()).isEqualTo(UPDATED_VALOR_IQ_UNO);
        assertThat(testTCCUOTARIESGO.getValorIqDos()).isEqualTo(UPDATED_VALOR_IQ_DOS);
    }

    @Test
    @Transactional
    void fullUpdateTCCUOTARIESGOWithPatch() throws Exception {
        // Initialize the database
        tCCUOTARIESGORepository.saveAndFlush(tCCUOTARIESGO);

        int databaseSizeBeforeUpdate = tCCUOTARIESGORepository.findAll().size();

        // Update the tCCUOTARIESGO using partial update
        TCCUOTARIESGO partialUpdatedTCCUOTARIESGO = new TCCUOTARIESGO();
        partialUpdatedTCCUOTARIESGO.setIdCuotaRiesgo(tCCUOTARIESGO.getIdCuotaRiesgo());

        partialUpdatedTCCUOTARIESGO
            .edad(UPDATED_EDAD)
            .valorBa(UPDATED_VALOR_BA)
            .valorBaCovid(UPDATED_VALOR_BA_COVID)
            .valorBipTres(UPDATED_VALOR_BIP_TRES)
            .valorBipSeis(UPDATED_VALOR_BIP_SEIS)
            .valorBit(UPDATED_VALOR_BIT)
            .valorMa(UPDATED_VALOR_MA)
            .valorDi(UPDATED_VALOR_DI)
            .valorTi(UPDATED_VALOR_TI)
            .valorGff(UPDATED_VALOR_GFF)
            .valorGffCovid(UPDATED_VALOR_GFF_COVID)
            .valorCa(UPDATED_VALOR_CA)
            .valorGe(UPDATED_VALOR_GE)
            .valorIqUno(UPDATED_VALOR_IQ_UNO)
            .valorIqDos(UPDATED_VALOR_IQ_DOS);

        restTCCUOTARIESGOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCUOTARIESGO.getIdCuotaRiesgo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCUOTARIESGO))
            )
            .andExpect(status().isOk());

        // Validate the TCCUOTARIESGO in the database
        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeUpdate);
        TCCUOTARIESGO testTCCUOTARIESGO = tCCUOTARIESGOList.get(tCCUOTARIESGOList.size() - 1);
        assertThat(testTCCUOTARIESGO.getEdad()).isEqualTo(UPDATED_EDAD);
        assertThat(testTCCUOTARIESGO.getValorBa()).isEqualTo(UPDATED_VALOR_BA);
        assertThat(testTCCUOTARIESGO.getValorBaCovid()).isEqualTo(UPDATED_VALOR_BA_COVID);
        assertThat(testTCCUOTARIESGO.getValorBipTres()).isEqualTo(UPDATED_VALOR_BIP_TRES);
        assertThat(testTCCUOTARIESGO.getValorBipSeis()).isEqualTo(UPDATED_VALOR_BIP_SEIS);
        assertThat(testTCCUOTARIESGO.getValorBit()).isEqualTo(UPDATED_VALOR_BIT);
        assertThat(testTCCUOTARIESGO.getValorMa()).isEqualTo(UPDATED_VALOR_MA);
        assertThat(testTCCUOTARIESGO.getValorDi()).isEqualTo(UPDATED_VALOR_DI);
        assertThat(testTCCUOTARIESGO.getValorTi()).isEqualTo(UPDATED_VALOR_TI);
        assertThat(testTCCUOTARIESGO.getValorGff()).isEqualTo(UPDATED_VALOR_GFF);
        assertThat(testTCCUOTARIESGO.getValorGffCovid()).isEqualTo(UPDATED_VALOR_GFF_COVID);
        assertThat(testTCCUOTARIESGO.getValorCa()).isEqualTo(UPDATED_VALOR_CA);
        assertThat(testTCCUOTARIESGO.getValorGe()).isEqualTo(UPDATED_VALOR_GE);
        assertThat(testTCCUOTARIESGO.getValorIqUno()).isEqualTo(UPDATED_VALOR_IQ_UNO);
        assertThat(testTCCUOTARIESGO.getValorIqDos()).isEqualTo(UPDATED_VALOR_IQ_DOS);
    }

    @Test
    @Transactional
    void patchNonExistingTCCUOTARIESGO() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTARIESGORepository.findAll().size();
        tCCUOTARIESGO.setIdCuotaRiesgo(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCUOTARIESGOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCCUOTARIESGO.getIdCuotaRiesgo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTARIESGO in the database
        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCCUOTARIESGO() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTARIESGORepository.findAll().size();
        tCCUOTARIESGO.setIdCuotaRiesgo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTARIESGOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTARIESGO in the database
        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCCUOTARIESGO() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTARIESGORepository.findAll().size();
        tCCUOTARIESGO.setIdCuotaRiesgo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTARIESGOMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCCUOTARIESGO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCUOTARIESGO in the database
        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCCUOTARIESGO() throws Exception {
        // Initialize the database
        tCCUOTARIESGORepository.saveAndFlush(tCCUOTARIESGO);

        int databaseSizeBeforeDelete = tCCUOTARIESGORepository.findAll().size();

        // Delete the tCCUOTARIESGO
        restTCCUOTARIESGOMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCCUOTARIESGO.getIdCuotaRiesgo()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCCUOTARIESGO> tCCUOTARIESGOList = tCCUOTARIESGORepository.findAll();
        assertThat(tCCUOTARIESGOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
