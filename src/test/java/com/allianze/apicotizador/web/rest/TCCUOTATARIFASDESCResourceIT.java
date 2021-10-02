package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCCUOTATARIFASDESC;
import com.allianze.apicotizador.repository.TCCUOTATARIFASDESCRepository;
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
 * Integration tests for the {@link TCCUOTATARIFASDESCResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCCUOTATARIFASDESCResourceIT {

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

    private static final String ENTITY_API_URL = "/api/tccuotatarifasdescs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCuotaTarifaSdesc}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCCUOTATARIFASDESCRepository tCCUOTATARIFASDESCRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCCUOTATARIFASDESCMockMvc;

    private TCCUOTATARIFASDESC tCCUOTATARIFASDESC;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCUOTATARIFASDESC createEntity(EntityManager em) {
        TCCUOTATARIFASDESC tCCUOTATARIFASDESC = new TCCUOTATARIFASDESC()
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
        return tCCUOTATARIFASDESC;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCUOTATARIFASDESC createUpdatedEntity(EntityManager em) {
        TCCUOTATARIFASDESC tCCUOTATARIFASDESC = new TCCUOTATARIFASDESC()
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
        return tCCUOTATARIFASDESC;
    }

    @BeforeEach
    public void initTest() {
        tCCUOTATARIFASDESC = createEntity(em);
    }

    @Test
    @Transactional
    void createTCCUOTATARIFASDESC() throws Exception {
        int databaseSizeBeforeCreate = tCCUOTATARIFASDESCRepository.findAll().size();
        // Create the TCCUOTATARIFASDESC
        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isCreated());

        // Validate the TCCUOTATARIFASDESC in the database
        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeCreate + 1);
        TCCUOTATARIFASDESC testTCCUOTATARIFASDESC = tCCUOTATARIFASDESCList.get(tCCUOTATARIFASDESCList.size() - 1);
        assertThat(testTCCUOTATARIFASDESC.getEdad()).isEqualTo(DEFAULT_EDAD);
        assertThat(testTCCUOTATARIFASDESC.getValorBa()).isEqualTo(DEFAULT_VALOR_BA);
        assertThat(testTCCUOTATARIFASDESC.getValorBaCovid()).isEqualTo(DEFAULT_VALOR_BA_COVID);
        assertThat(testTCCUOTATARIFASDESC.getValorBipTres()).isEqualTo(DEFAULT_VALOR_BIP_TRES);
        assertThat(testTCCUOTATARIFASDESC.getValorBipSeis()).isEqualTo(DEFAULT_VALOR_BIP_SEIS);
        assertThat(testTCCUOTATARIFASDESC.getValorBit()).isEqualTo(DEFAULT_VALOR_BIT);
        assertThat(testTCCUOTATARIFASDESC.getValorMa()).isEqualTo(DEFAULT_VALOR_MA);
        assertThat(testTCCUOTATARIFASDESC.getValorDi()).isEqualTo(DEFAULT_VALOR_DI);
        assertThat(testTCCUOTATARIFASDESC.getValorTi()).isEqualTo(DEFAULT_VALOR_TI);
        assertThat(testTCCUOTATARIFASDESC.getValorGff()).isEqualTo(DEFAULT_VALOR_GFF);
        assertThat(testTCCUOTATARIFASDESC.getValorGffCovid()).isEqualTo(DEFAULT_VALOR_GFF_COVID);
        assertThat(testTCCUOTATARIFASDESC.getValorCa()).isEqualTo(DEFAULT_VALOR_CA);
        assertThat(testTCCUOTATARIFASDESC.getValorGe()).isEqualTo(DEFAULT_VALOR_GE);
        assertThat(testTCCUOTATARIFASDESC.getValorIqUno()).isEqualTo(DEFAULT_VALOR_IQ_UNO);
        assertThat(testTCCUOTATARIFASDESC.getValorIqDos()).isEqualTo(DEFAULT_VALOR_IQ_DOS);
    }

    @Test
    @Transactional
    void createTCCUOTATARIFASDESCWithExistingId() throws Exception {
        // Create the TCCUOTATARIFASDESC with an existing ID
        tCCUOTATARIFASDESC.setIdCuotaTarifaSdesc(1L);

        int databaseSizeBeforeCreate = tCCUOTATARIFASDESCRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTATARIFASDESC in the database
        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEdadIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTATARIFASDESCRepository.findAll().size();
        // set the field null
        tCCUOTATARIFASDESC.setEdad(null);

        // Create the TCCUOTATARIFASDESC, which fails.

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorBaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTATARIFASDESCRepository.findAll().size();
        // set the field null
        tCCUOTATARIFASDESC.setValorBa(null);

        // Create the TCCUOTATARIFASDESC, which fails.

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorBaCovidIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTATARIFASDESCRepository.findAll().size();
        // set the field null
        tCCUOTATARIFASDESC.setValorBaCovid(null);

        // Create the TCCUOTATARIFASDESC, which fails.

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorBipTresIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTATARIFASDESCRepository.findAll().size();
        // set the field null
        tCCUOTATARIFASDESC.setValorBipTres(null);

        // Create the TCCUOTATARIFASDESC, which fails.

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorBipSeisIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTATARIFASDESCRepository.findAll().size();
        // set the field null
        tCCUOTATARIFASDESC.setValorBipSeis(null);

        // Create the TCCUOTATARIFASDESC, which fails.

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorBitIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTATARIFASDESCRepository.findAll().size();
        // set the field null
        tCCUOTATARIFASDESC.setValorBit(null);

        // Create the TCCUOTATARIFASDESC, which fails.

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorMaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTATARIFASDESCRepository.findAll().size();
        // set the field null
        tCCUOTATARIFASDESC.setValorMa(null);

        // Create the TCCUOTATARIFASDESC, which fails.

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorDiIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTATARIFASDESCRepository.findAll().size();
        // set the field null
        tCCUOTATARIFASDESC.setValorDi(null);

        // Create the TCCUOTATARIFASDESC, which fails.

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorTiIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTATARIFASDESCRepository.findAll().size();
        // set the field null
        tCCUOTATARIFASDESC.setValorTi(null);

        // Create the TCCUOTATARIFASDESC, which fails.

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorGffIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTATARIFASDESCRepository.findAll().size();
        // set the field null
        tCCUOTATARIFASDESC.setValorGff(null);

        // Create the TCCUOTATARIFASDESC, which fails.

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorGffCovidIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTATARIFASDESCRepository.findAll().size();
        // set the field null
        tCCUOTATARIFASDESC.setValorGffCovid(null);

        // Create the TCCUOTATARIFASDESC, which fails.

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorCaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTATARIFASDESCRepository.findAll().size();
        // set the field null
        tCCUOTATARIFASDESC.setValorCa(null);

        // Create the TCCUOTATARIFASDESC, which fails.

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorGeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTATARIFASDESCRepository.findAll().size();
        // set the field null
        tCCUOTATARIFASDESC.setValorGe(null);

        // Create the TCCUOTATARIFASDESC, which fails.

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorIqUnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTATARIFASDESCRepository.findAll().size();
        // set the field null
        tCCUOTATARIFASDESC.setValorIqUno(null);

        // Create the TCCUOTATARIFASDESC, which fails.

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorIqDosIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTATARIFASDESCRepository.findAll().size();
        // set the field null
        tCCUOTATARIFASDESC.setValorIqDos(null);

        // Create the TCCUOTATARIFASDESC, which fails.

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCCUOTATARIFASDESCS() throws Exception {
        // Initialize the database
        tCCUOTATARIFASDESCRepository.saveAndFlush(tCCUOTATARIFASDESC);

        // Get all the tCCUOTATARIFASDESCList
        restTCCUOTATARIFASDESCMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCuotaTarifaSdesc,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCuotaTarifaSdesc").value(hasItem(tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc().intValue())))
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
    void getTCCUOTATARIFASDESC() throws Exception {
        // Initialize the database
        tCCUOTATARIFASDESCRepository.saveAndFlush(tCCUOTATARIFASDESC);

        // Get the tCCUOTATARIFASDESC
        restTCCUOTATARIFASDESCMockMvc
            .perform(get(ENTITY_API_URL_ID, tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCuotaTarifaSdesc").value(tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc().intValue()))
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
    void getNonExistingTCCUOTATARIFASDESC() throws Exception {
        // Get the tCCUOTATARIFASDESC
        restTCCUOTATARIFASDESCMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCCUOTATARIFASDESC() throws Exception {
        // Initialize the database
        tCCUOTATARIFASDESCRepository.saveAndFlush(tCCUOTATARIFASDESC);

        int databaseSizeBeforeUpdate = tCCUOTATARIFASDESCRepository.findAll().size();

        // Update the tCCUOTATARIFASDESC
        TCCUOTATARIFASDESC updatedTCCUOTATARIFASDESC = tCCUOTATARIFASDESCRepository
            .findById(tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc())
            .get();
        // Disconnect from session so that the updates on updatedTCCUOTATARIFASDESC are not directly saved in db
        em.detach(updatedTCCUOTATARIFASDESC);
        updatedTCCUOTATARIFASDESC
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

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCCUOTATARIFASDESC.getIdCuotaTarifaSdesc())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCCUOTATARIFASDESC))
            )
            .andExpect(status().isOk());

        // Validate the TCCUOTATARIFASDESC in the database
        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeUpdate);
        TCCUOTATARIFASDESC testTCCUOTATARIFASDESC = tCCUOTATARIFASDESCList.get(tCCUOTATARIFASDESCList.size() - 1);
        assertThat(testTCCUOTATARIFASDESC.getEdad()).isEqualTo(UPDATED_EDAD);
        assertThat(testTCCUOTATARIFASDESC.getValorBa()).isEqualTo(UPDATED_VALOR_BA);
        assertThat(testTCCUOTATARIFASDESC.getValorBaCovid()).isEqualTo(UPDATED_VALOR_BA_COVID);
        assertThat(testTCCUOTATARIFASDESC.getValorBipTres()).isEqualTo(UPDATED_VALOR_BIP_TRES);
        assertThat(testTCCUOTATARIFASDESC.getValorBipSeis()).isEqualTo(UPDATED_VALOR_BIP_SEIS);
        assertThat(testTCCUOTATARIFASDESC.getValorBit()).isEqualTo(UPDATED_VALOR_BIT);
        assertThat(testTCCUOTATARIFASDESC.getValorMa()).isEqualTo(UPDATED_VALOR_MA);
        assertThat(testTCCUOTATARIFASDESC.getValorDi()).isEqualTo(UPDATED_VALOR_DI);
        assertThat(testTCCUOTATARIFASDESC.getValorTi()).isEqualTo(UPDATED_VALOR_TI);
        assertThat(testTCCUOTATARIFASDESC.getValorGff()).isEqualTo(UPDATED_VALOR_GFF);
        assertThat(testTCCUOTATARIFASDESC.getValorGffCovid()).isEqualTo(UPDATED_VALOR_GFF_COVID);
        assertThat(testTCCUOTATARIFASDESC.getValorCa()).isEqualTo(UPDATED_VALOR_CA);
        assertThat(testTCCUOTATARIFASDESC.getValorGe()).isEqualTo(UPDATED_VALOR_GE);
        assertThat(testTCCUOTATARIFASDESC.getValorIqUno()).isEqualTo(UPDATED_VALOR_IQ_UNO);
        assertThat(testTCCUOTATARIFASDESC.getValorIqDos()).isEqualTo(UPDATED_VALOR_IQ_DOS);
    }

    @Test
    @Transactional
    void putNonExistingTCCUOTATARIFASDESC() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTATARIFASDESCRepository.findAll().size();
        tCCUOTATARIFASDESC.setIdCuotaTarifaSdesc(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCUOTATARIFASDESCMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTATARIFASDESC in the database
        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCCUOTATARIFASDESC() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTATARIFASDESCRepository.findAll().size();
        tCCUOTATARIFASDESC.setIdCuotaTarifaSdesc(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTATARIFASDESCMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTATARIFASDESC in the database
        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCCUOTATARIFASDESC() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTATARIFASDESCRepository.findAll().size();
        tCCUOTATARIFASDESC.setIdCuotaTarifaSdesc(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTATARIFASDESCMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCUOTATARIFASDESC in the database
        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCCUOTATARIFASDESCWithPatch() throws Exception {
        // Initialize the database
        tCCUOTATARIFASDESCRepository.saveAndFlush(tCCUOTATARIFASDESC);

        int databaseSizeBeforeUpdate = tCCUOTATARIFASDESCRepository.findAll().size();

        // Update the tCCUOTATARIFASDESC using partial update
        TCCUOTATARIFASDESC partialUpdatedTCCUOTATARIFASDESC = new TCCUOTATARIFASDESC();
        partialUpdatedTCCUOTATARIFASDESC.setIdCuotaTarifaSdesc(tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc());

        partialUpdatedTCCUOTATARIFASDESC
            .valorBa(UPDATED_VALOR_BA)
            .valorBaCovid(UPDATED_VALOR_BA_COVID)
            .valorBipSeis(UPDATED_VALOR_BIP_SEIS)
            .valorMa(UPDATED_VALOR_MA)
            .valorDi(UPDATED_VALOR_DI)
            .valorTi(UPDATED_VALOR_TI)
            .valorGff(UPDATED_VALOR_GFF)
            .valorCa(UPDATED_VALOR_CA)
            .valorIqUno(UPDATED_VALOR_IQ_UNO);

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCUOTATARIFASDESC.getIdCuotaTarifaSdesc())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCUOTATARIFASDESC))
            )
            .andExpect(status().isOk());

        // Validate the TCCUOTATARIFASDESC in the database
        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeUpdate);
        TCCUOTATARIFASDESC testTCCUOTATARIFASDESC = tCCUOTATARIFASDESCList.get(tCCUOTATARIFASDESCList.size() - 1);
        assertThat(testTCCUOTATARIFASDESC.getEdad()).isEqualTo(DEFAULT_EDAD);
        assertThat(testTCCUOTATARIFASDESC.getValorBa()).isEqualTo(UPDATED_VALOR_BA);
        assertThat(testTCCUOTATARIFASDESC.getValorBaCovid()).isEqualTo(UPDATED_VALOR_BA_COVID);
        assertThat(testTCCUOTATARIFASDESC.getValorBipTres()).isEqualTo(DEFAULT_VALOR_BIP_TRES);
        assertThat(testTCCUOTATARIFASDESC.getValorBipSeis()).isEqualTo(UPDATED_VALOR_BIP_SEIS);
        assertThat(testTCCUOTATARIFASDESC.getValorBit()).isEqualTo(DEFAULT_VALOR_BIT);
        assertThat(testTCCUOTATARIFASDESC.getValorMa()).isEqualTo(UPDATED_VALOR_MA);
        assertThat(testTCCUOTATARIFASDESC.getValorDi()).isEqualTo(UPDATED_VALOR_DI);
        assertThat(testTCCUOTATARIFASDESC.getValorTi()).isEqualTo(UPDATED_VALOR_TI);
        assertThat(testTCCUOTATARIFASDESC.getValorGff()).isEqualTo(UPDATED_VALOR_GFF);
        assertThat(testTCCUOTATARIFASDESC.getValorGffCovid()).isEqualTo(DEFAULT_VALOR_GFF_COVID);
        assertThat(testTCCUOTATARIFASDESC.getValorCa()).isEqualTo(UPDATED_VALOR_CA);
        assertThat(testTCCUOTATARIFASDESC.getValorGe()).isEqualTo(DEFAULT_VALOR_GE);
        assertThat(testTCCUOTATARIFASDESC.getValorIqUno()).isEqualTo(UPDATED_VALOR_IQ_UNO);
        assertThat(testTCCUOTATARIFASDESC.getValorIqDos()).isEqualTo(DEFAULT_VALOR_IQ_DOS);
    }

    @Test
    @Transactional
    void fullUpdateTCCUOTATARIFASDESCWithPatch() throws Exception {
        // Initialize the database
        tCCUOTATARIFASDESCRepository.saveAndFlush(tCCUOTATARIFASDESC);

        int databaseSizeBeforeUpdate = tCCUOTATARIFASDESCRepository.findAll().size();

        // Update the tCCUOTATARIFASDESC using partial update
        TCCUOTATARIFASDESC partialUpdatedTCCUOTATARIFASDESC = new TCCUOTATARIFASDESC();
        partialUpdatedTCCUOTATARIFASDESC.setIdCuotaTarifaSdesc(tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc());

        partialUpdatedTCCUOTATARIFASDESC
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

        restTCCUOTATARIFASDESCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCUOTATARIFASDESC.getIdCuotaTarifaSdesc())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCUOTATARIFASDESC))
            )
            .andExpect(status().isOk());

        // Validate the TCCUOTATARIFASDESC in the database
        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeUpdate);
        TCCUOTATARIFASDESC testTCCUOTATARIFASDESC = tCCUOTATARIFASDESCList.get(tCCUOTATARIFASDESCList.size() - 1);
        assertThat(testTCCUOTATARIFASDESC.getEdad()).isEqualTo(UPDATED_EDAD);
        assertThat(testTCCUOTATARIFASDESC.getValorBa()).isEqualTo(UPDATED_VALOR_BA);
        assertThat(testTCCUOTATARIFASDESC.getValorBaCovid()).isEqualTo(UPDATED_VALOR_BA_COVID);
        assertThat(testTCCUOTATARIFASDESC.getValorBipTres()).isEqualTo(UPDATED_VALOR_BIP_TRES);
        assertThat(testTCCUOTATARIFASDESC.getValorBipSeis()).isEqualTo(UPDATED_VALOR_BIP_SEIS);
        assertThat(testTCCUOTATARIFASDESC.getValorBit()).isEqualTo(UPDATED_VALOR_BIT);
        assertThat(testTCCUOTATARIFASDESC.getValorMa()).isEqualTo(UPDATED_VALOR_MA);
        assertThat(testTCCUOTATARIFASDESC.getValorDi()).isEqualTo(UPDATED_VALOR_DI);
        assertThat(testTCCUOTATARIFASDESC.getValorTi()).isEqualTo(UPDATED_VALOR_TI);
        assertThat(testTCCUOTATARIFASDESC.getValorGff()).isEqualTo(UPDATED_VALOR_GFF);
        assertThat(testTCCUOTATARIFASDESC.getValorGffCovid()).isEqualTo(UPDATED_VALOR_GFF_COVID);
        assertThat(testTCCUOTATARIFASDESC.getValorCa()).isEqualTo(UPDATED_VALOR_CA);
        assertThat(testTCCUOTATARIFASDESC.getValorGe()).isEqualTo(UPDATED_VALOR_GE);
        assertThat(testTCCUOTATARIFASDESC.getValorIqUno()).isEqualTo(UPDATED_VALOR_IQ_UNO);
        assertThat(testTCCUOTATARIFASDESC.getValorIqDos()).isEqualTo(UPDATED_VALOR_IQ_DOS);
    }

    @Test
    @Transactional
    void patchNonExistingTCCUOTATARIFASDESC() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTATARIFASDESCRepository.findAll().size();
        tCCUOTATARIFASDESC.setIdCuotaTarifaSdesc(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCUOTATARIFASDESCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTATARIFASDESC in the database
        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCCUOTATARIFASDESC() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTATARIFASDESCRepository.findAll().size();
        tCCUOTATARIFASDESC.setIdCuotaTarifaSdesc(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTATARIFASDESCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTATARIFASDESC in the database
        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCCUOTATARIFASDESC() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTATARIFASDESCRepository.findAll().size();
        tCCUOTATARIFASDESC.setIdCuotaTarifaSdesc(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTATARIFASDESCMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTATARIFASDESC))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCUOTATARIFASDESC in the database
        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCCUOTATARIFASDESC() throws Exception {
        // Initialize the database
        tCCUOTATARIFASDESCRepository.saveAndFlush(tCCUOTATARIFASDESC);

        int databaseSizeBeforeDelete = tCCUOTATARIFASDESCRepository.findAll().size();

        // Delete the tCCUOTATARIFASDESC
        restTCCUOTATARIFASDESCMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCCUOTATARIFASDESC.getIdCuotaTarifaSdesc()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCCUOTATARIFASDESC> tCCUOTATARIFASDESCList = tCCUOTATARIFASDESCRepository.findAll();
        assertThat(tCCUOTATARIFASDESCList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
