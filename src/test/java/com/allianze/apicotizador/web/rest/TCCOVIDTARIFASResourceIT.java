package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCCOVIDTARIFAS;
import com.allianze.apicotizador.repository.TCCOVIDTARIFASRepository;
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
 * Integration tests for the {@link TCCOVIDTARIFASResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCCOVIDTARIFASResourceIT {

    private static final String DEFAULT_EDAD = "AAAAAAAAAA";
    private static final String UPDATED_EDAD = "BBBBBBBBBB";

    private static final Long DEFAULT_QXCNSFG = 1L;
    private static final Long UPDATED_QXCNSFG = 2L;

    private static final String DEFAULT_TITULAR = "AAAAAAAAAA";
    private static final String UPDATED_TITULAR = "BBBBBBBBBB";

    private static final String DEFAULT_CONYUGE = "AAAAAAAAAA";
    private static final String UPDATED_CONYUGE = "BBBBBBBBBB";

    private static final String DEFAULT_HIJO_MAYOR = "AAAAAAAAAA";
    private static final String UPDATED_HIJO_MAYOR = "BBBBBBBBBB";

    private static final String DEFAULT_HIJO_MENOR = "AAAAAAAAAA";
    private static final String UPDATED_HIJO_MENOR = "BBBBBBBBBB";

    private static final Long DEFAULT_QX_TITULAR = 1L;
    private static final Long UPDATED_QX_TITULAR = 2L;

    private static final Long DEFAULT_QX_CONYUGE = 1L;
    private static final Long UPDATED_QX_CONYUGE = 2L;

    private static final Long DEFAULT_QX_HIJO_MAYOR = 1L;
    private static final Long UPDATED_QX_HIJO_MAYOR = 2L;

    private static final Long DEFAULT_QX_HIJO_MENOR = 1L;
    private static final Long UPDATED_QX_HIJO_MENOR = 2L;

    private static final Long DEFAULT_QX_TITULAR_RECARGADA = 1L;
    private static final Long UPDATED_QX_TITULAR_RECARGADA = 2L;

    private static final Long DEFAULT_QX_CONYUGE_RECARGADA = 1L;
    private static final Long UPDATED_QX_CONYUGE_RECARGADA = 2L;

    private static final Long DEFAULT_QX_HIJO_MAYOR_RECARGADA = 1L;
    private static final Long UPDATED_QX_HIJO_MAYOR_RECARGADA = 2L;

    private static final Long DEFAULT_QX_HIJO_MENOR_RECARGADA = 1L;
    private static final Long UPDATED_QX_HIJO_MENOR_RECARGADA = 2L;

    private static final Long DEFAULT_VALOR_GFF = 1L;
    private static final Long UPDATED_VALOR_GFF = 2L;

    private static final Long DEFAULT_VALOR_GFF_COVID = 1L;
    private static final Long UPDATED_VALOR_GFF_COVID = 2L;

    private static final String ENTITY_API_URL = "/api/tccovidtarifas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCovidTarifas}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCCOVIDTARIFASRepository tCCOVIDTARIFASRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCCOVIDTARIFASMockMvc;

    private TCCOVIDTARIFAS tCCOVIDTARIFAS;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCOVIDTARIFAS createEntity(EntityManager em) {
        TCCOVIDTARIFAS tCCOVIDTARIFAS = new TCCOVIDTARIFAS()
            .edad(DEFAULT_EDAD)
            .qxcnsfg(DEFAULT_QXCNSFG)
            .titular(DEFAULT_TITULAR)
            .conyuge(DEFAULT_CONYUGE)
            .hijoMayor(DEFAULT_HIJO_MAYOR)
            .hijoMenor(DEFAULT_HIJO_MENOR)
            .qxTitular(DEFAULT_QX_TITULAR)
            .qxConyuge(DEFAULT_QX_CONYUGE)
            .qxHijoMayor(DEFAULT_QX_HIJO_MAYOR)
            .qxHijoMenor(DEFAULT_QX_HIJO_MENOR)
            .qxTitularRecargada(DEFAULT_QX_TITULAR_RECARGADA)
            .qxConyugeRecargada(DEFAULT_QX_CONYUGE_RECARGADA)
            .qxHijoMayorRecargada(DEFAULT_QX_HIJO_MAYOR_RECARGADA)
            .qxHijoMenorRecargada(DEFAULT_QX_HIJO_MENOR_RECARGADA)
            .valorGff(DEFAULT_VALOR_GFF)
            .valorGffCovid(DEFAULT_VALOR_GFF_COVID);
        return tCCOVIDTARIFAS;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCOVIDTARIFAS createUpdatedEntity(EntityManager em) {
        TCCOVIDTARIFAS tCCOVIDTARIFAS = new TCCOVIDTARIFAS()
            .edad(UPDATED_EDAD)
            .qxcnsfg(UPDATED_QXCNSFG)
            .titular(UPDATED_TITULAR)
            .conyuge(UPDATED_CONYUGE)
            .hijoMayor(UPDATED_HIJO_MAYOR)
            .hijoMenor(UPDATED_HIJO_MENOR)
            .qxTitular(UPDATED_QX_TITULAR)
            .qxConyuge(UPDATED_QX_CONYUGE)
            .qxHijoMayor(UPDATED_QX_HIJO_MAYOR)
            .qxHijoMenor(UPDATED_QX_HIJO_MENOR)
            .qxTitularRecargada(UPDATED_QX_TITULAR_RECARGADA)
            .qxConyugeRecargada(UPDATED_QX_CONYUGE_RECARGADA)
            .qxHijoMayorRecargada(UPDATED_QX_HIJO_MAYOR_RECARGADA)
            .qxHijoMenorRecargada(UPDATED_QX_HIJO_MENOR_RECARGADA)
            .valorGff(UPDATED_VALOR_GFF)
            .valorGffCovid(UPDATED_VALOR_GFF_COVID);
        return tCCOVIDTARIFAS;
    }

    @BeforeEach
    public void initTest() {
        tCCOVIDTARIFAS = createEntity(em);
    }

    @Test
    @Transactional
    void createTCCOVIDTARIFAS() throws Exception {
        int databaseSizeBeforeCreate = tCCOVIDTARIFASRepository.findAll().size();
        // Create the TCCOVIDTARIFAS
        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isCreated());

        // Validate the TCCOVIDTARIFAS in the database
        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeCreate + 1);
        TCCOVIDTARIFAS testTCCOVIDTARIFAS = tCCOVIDTARIFASList.get(tCCOVIDTARIFASList.size() - 1);
        assertThat(testTCCOVIDTARIFAS.getEdad()).isEqualTo(DEFAULT_EDAD);
        assertThat(testTCCOVIDTARIFAS.getQxcnsfg()).isEqualTo(DEFAULT_QXCNSFG);
        assertThat(testTCCOVIDTARIFAS.getTitular()).isEqualTo(DEFAULT_TITULAR);
        assertThat(testTCCOVIDTARIFAS.getConyuge()).isEqualTo(DEFAULT_CONYUGE);
        assertThat(testTCCOVIDTARIFAS.getHijoMayor()).isEqualTo(DEFAULT_HIJO_MAYOR);
        assertThat(testTCCOVIDTARIFAS.getHijoMenor()).isEqualTo(DEFAULT_HIJO_MENOR);
        assertThat(testTCCOVIDTARIFAS.getQxTitular()).isEqualTo(DEFAULT_QX_TITULAR);
        assertThat(testTCCOVIDTARIFAS.getQxConyuge()).isEqualTo(DEFAULT_QX_CONYUGE);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMayor()).isEqualTo(DEFAULT_QX_HIJO_MAYOR);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMenor()).isEqualTo(DEFAULT_QX_HIJO_MENOR);
        assertThat(testTCCOVIDTARIFAS.getQxTitularRecargada()).isEqualTo(DEFAULT_QX_TITULAR_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getQxConyugeRecargada()).isEqualTo(DEFAULT_QX_CONYUGE_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMayorRecargada()).isEqualTo(DEFAULT_QX_HIJO_MAYOR_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMenorRecargada()).isEqualTo(DEFAULT_QX_HIJO_MENOR_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getValorGff()).isEqualTo(DEFAULT_VALOR_GFF);
        assertThat(testTCCOVIDTARIFAS.getValorGffCovid()).isEqualTo(DEFAULT_VALOR_GFF_COVID);
    }

    @Test
    @Transactional
    void createTCCOVIDTARIFASWithExistingId() throws Exception {
        // Create the TCCOVIDTARIFAS with an existing ID
        tCCOVIDTARIFAS.setIdCovidTarifas(1L);

        int databaseSizeBeforeCreate = tCCOVIDTARIFASRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOVIDTARIFAS in the database
        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEdadIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setEdad(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQxcnsfgIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setQxcnsfg(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTitularIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setTitular(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkConyugeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setConyuge(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkHijoMayorIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setHijoMayor(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkHijoMenorIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setHijoMenor(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQxTitularIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setQxTitular(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQxConyugeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setQxConyuge(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQxHijoMayorIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setQxHijoMayor(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQxHijoMenorIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setQxHijoMenor(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQxTitularRecargadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setQxTitularRecargada(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQxConyugeRecargadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setQxConyugeRecargada(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQxHijoMayorRecargadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setQxHijoMayorRecargada(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkQxHijoMenorRecargadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setQxHijoMenorRecargada(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorGffIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setValorGff(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorGffCovidIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDTARIFASRepository.findAll().size();
        // set the field null
        tCCOVIDTARIFAS.setValorGffCovid(null);

        // Create the TCCOVIDTARIFAS, which fails.

        restTCCOVIDTARIFASMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCCOVIDTARIFAS() throws Exception {
        // Initialize the database
        tCCOVIDTARIFASRepository.saveAndFlush(tCCOVIDTARIFAS);

        // Get all the tCCOVIDTARIFASList
        restTCCOVIDTARIFASMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCovidTarifas,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCovidTarifas").value(hasItem(tCCOVIDTARIFAS.getIdCovidTarifas().intValue())))
            .andExpect(jsonPath("$.[*].edad").value(hasItem(DEFAULT_EDAD)))
            .andExpect(jsonPath("$.[*].qxcnsfg").value(hasItem(DEFAULT_QXCNSFG.intValue())))
            .andExpect(jsonPath("$.[*].titular").value(hasItem(DEFAULT_TITULAR)))
            .andExpect(jsonPath("$.[*].conyuge").value(hasItem(DEFAULT_CONYUGE)))
            .andExpect(jsonPath("$.[*].hijoMayor").value(hasItem(DEFAULT_HIJO_MAYOR)))
            .andExpect(jsonPath("$.[*].hijoMenor").value(hasItem(DEFAULT_HIJO_MENOR)))
            .andExpect(jsonPath("$.[*].qxTitular").value(hasItem(DEFAULT_QX_TITULAR.intValue())))
            .andExpect(jsonPath("$.[*].qxConyuge").value(hasItem(DEFAULT_QX_CONYUGE.intValue())))
            .andExpect(jsonPath("$.[*].qxHijoMayor").value(hasItem(DEFAULT_QX_HIJO_MAYOR.intValue())))
            .andExpect(jsonPath("$.[*].qxHijoMenor").value(hasItem(DEFAULT_QX_HIJO_MENOR.intValue())))
            .andExpect(jsonPath("$.[*].qxTitularRecargada").value(hasItem(DEFAULT_QX_TITULAR_RECARGADA.intValue())))
            .andExpect(jsonPath("$.[*].qxConyugeRecargada").value(hasItem(DEFAULT_QX_CONYUGE_RECARGADA.intValue())))
            .andExpect(jsonPath("$.[*].qxHijoMayorRecargada").value(hasItem(DEFAULT_QX_HIJO_MAYOR_RECARGADA.intValue())))
            .andExpect(jsonPath("$.[*].qxHijoMenorRecargada").value(hasItem(DEFAULT_QX_HIJO_MENOR_RECARGADA.intValue())))
            .andExpect(jsonPath("$.[*].valorGff").value(hasItem(DEFAULT_VALOR_GFF.intValue())))
            .andExpect(jsonPath("$.[*].valorGffCovid").value(hasItem(DEFAULT_VALOR_GFF_COVID.intValue())));
    }

    @Test
    @Transactional
    void getTCCOVIDTARIFAS() throws Exception {
        // Initialize the database
        tCCOVIDTARIFASRepository.saveAndFlush(tCCOVIDTARIFAS);

        // Get the tCCOVIDTARIFAS
        restTCCOVIDTARIFASMockMvc
            .perform(get(ENTITY_API_URL_ID, tCCOVIDTARIFAS.getIdCovidTarifas()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCovidTarifas").value(tCCOVIDTARIFAS.getIdCovidTarifas().intValue()))
            .andExpect(jsonPath("$.edad").value(DEFAULT_EDAD))
            .andExpect(jsonPath("$.qxcnsfg").value(DEFAULT_QXCNSFG.intValue()))
            .andExpect(jsonPath("$.titular").value(DEFAULT_TITULAR))
            .andExpect(jsonPath("$.conyuge").value(DEFAULT_CONYUGE))
            .andExpect(jsonPath("$.hijoMayor").value(DEFAULT_HIJO_MAYOR))
            .andExpect(jsonPath("$.hijoMenor").value(DEFAULT_HIJO_MENOR))
            .andExpect(jsonPath("$.qxTitular").value(DEFAULT_QX_TITULAR.intValue()))
            .andExpect(jsonPath("$.qxConyuge").value(DEFAULT_QX_CONYUGE.intValue()))
            .andExpect(jsonPath("$.qxHijoMayor").value(DEFAULT_QX_HIJO_MAYOR.intValue()))
            .andExpect(jsonPath("$.qxHijoMenor").value(DEFAULT_QX_HIJO_MENOR.intValue()))
            .andExpect(jsonPath("$.qxTitularRecargada").value(DEFAULT_QX_TITULAR_RECARGADA.intValue()))
            .andExpect(jsonPath("$.qxConyugeRecargada").value(DEFAULT_QX_CONYUGE_RECARGADA.intValue()))
            .andExpect(jsonPath("$.qxHijoMayorRecargada").value(DEFAULT_QX_HIJO_MAYOR_RECARGADA.intValue()))
            .andExpect(jsonPath("$.qxHijoMenorRecargada").value(DEFAULT_QX_HIJO_MENOR_RECARGADA.intValue()))
            .andExpect(jsonPath("$.valorGff").value(DEFAULT_VALOR_GFF.intValue()))
            .andExpect(jsonPath("$.valorGffCovid").value(DEFAULT_VALOR_GFF_COVID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCCOVIDTARIFAS() throws Exception {
        // Get the tCCOVIDTARIFAS
        restTCCOVIDTARIFASMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCCOVIDTARIFAS() throws Exception {
        // Initialize the database
        tCCOVIDTARIFASRepository.saveAndFlush(tCCOVIDTARIFAS);

        int databaseSizeBeforeUpdate = tCCOVIDTARIFASRepository.findAll().size();

        // Update the tCCOVIDTARIFAS
        TCCOVIDTARIFAS updatedTCCOVIDTARIFAS = tCCOVIDTARIFASRepository.findById(tCCOVIDTARIFAS.getIdCovidTarifas()).get();
        // Disconnect from session so that the updates on updatedTCCOVIDTARIFAS are not directly saved in db
        em.detach(updatedTCCOVIDTARIFAS);
        updatedTCCOVIDTARIFAS
            .edad(UPDATED_EDAD)
            .qxcnsfg(UPDATED_QXCNSFG)
            .titular(UPDATED_TITULAR)
            .conyuge(UPDATED_CONYUGE)
            .hijoMayor(UPDATED_HIJO_MAYOR)
            .hijoMenor(UPDATED_HIJO_MENOR)
            .qxTitular(UPDATED_QX_TITULAR)
            .qxConyuge(UPDATED_QX_CONYUGE)
            .qxHijoMayor(UPDATED_QX_HIJO_MAYOR)
            .qxHijoMenor(UPDATED_QX_HIJO_MENOR)
            .qxTitularRecargada(UPDATED_QX_TITULAR_RECARGADA)
            .qxConyugeRecargada(UPDATED_QX_CONYUGE_RECARGADA)
            .qxHijoMayorRecargada(UPDATED_QX_HIJO_MAYOR_RECARGADA)
            .qxHijoMenorRecargada(UPDATED_QX_HIJO_MENOR_RECARGADA)
            .valorGff(UPDATED_VALOR_GFF)
            .valorGffCovid(UPDATED_VALOR_GFF_COVID);

        restTCCOVIDTARIFASMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCCOVIDTARIFAS.getIdCovidTarifas())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCCOVIDTARIFAS))
            )
            .andExpect(status().isOk());

        // Validate the TCCOVIDTARIFAS in the database
        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeUpdate);
        TCCOVIDTARIFAS testTCCOVIDTARIFAS = tCCOVIDTARIFASList.get(tCCOVIDTARIFASList.size() - 1);
        assertThat(testTCCOVIDTARIFAS.getEdad()).isEqualTo(UPDATED_EDAD);
        assertThat(testTCCOVIDTARIFAS.getQxcnsfg()).isEqualTo(UPDATED_QXCNSFG);
        assertThat(testTCCOVIDTARIFAS.getTitular()).isEqualTo(UPDATED_TITULAR);
        assertThat(testTCCOVIDTARIFAS.getConyuge()).isEqualTo(UPDATED_CONYUGE);
        assertThat(testTCCOVIDTARIFAS.getHijoMayor()).isEqualTo(UPDATED_HIJO_MAYOR);
        assertThat(testTCCOVIDTARIFAS.getHijoMenor()).isEqualTo(UPDATED_HIJO_MENOR);
        assertThat(testTCCOVIDTARIFAS.getQxTitular()).isEqualTo(UPDATED_QX_TITULAR);
        assertThat(testTCCOVIDTARIFAS.getQxConyuge()).isEqualTo(UPDATED_QX_CONYUGE);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMayor()).isEqualTo(UPDATED_QX_HIJO_MAYOR);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMenor()).isEqualTo(UPDATED_QX_HIJO_MENOR);
        assertThat(testTCCOVIDTARIFAS.getQxTitularRecargada()).isEqualTo(UPDATED_QX_TITULAR_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getQxConyugeRecargada()).isEqualTo(UPDATED_QX_CONYUGE_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMayorRecargada()).isEqualTo(UPDATED_QX_HIJO_MAYOR_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMenorRecargada()).isEqualTo(UPDATED_QX_HIJO_MENOR_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getValorGff()).isEqualTo(UPDATED_VALOR_GFF);
        assertThat(testTCCOVIDTARIFAS.getValorGffCovid()).isEqualTo(UPDATED_VALOR_GFF_COVID);
    }

    @Test
    @Transactional
    void putNonExistingTCCOVIDTARIFAS() throws Exception {
        int databaseSizeBeforeUpdate = tCCOVIDTARIFASRepository.findAll().size();
        tCCOVIDTARIFAS.setIdCovidTarifas(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCOVIDTARIFASMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCCOVIDTARIFAS.getIdCovidTarifas())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOVIDTARIFAS in the database
        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCCOVIDTARIFAS() throws Exception {
        int databaseSizeBeforeUpdate = tCCOVIDTARIFASRepository.findAll().size();
        tCCOVIDTARIFAS.setIdCovidTarifas(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOVIDTARIFASMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOVIDTARIFAS in the database
        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCCOVIDTARIFAS() throws Exception {
        int databaseSizeBeforeUpdate = tCCOVIDTARIFASRepository.findAll().size();
        tCCOVIDTARIFAS.setIdCovidTarifas(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOVIDTARIFASMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCOVIDTARIFAS in the database
        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCCOVIDTARIFASWithPatch() throws Exception {
        // Initialize the database
        tCCOVIDTARIFASRepository.saveAndFlush(tCCOVIDTARIFAS);

        int databaseSizeBeforeUpdate = tCCOVIDTARIFASRepository.findAll().size();

        // Update the tCCOVIDTARIFAS using partial update
        TCCOVIDTARIFAS partialUpdatedTCCOVIDTARIFAS = new TCCOVIDTARIFAS();
        partialUpdatedTCCOVIDTARIFAS.setIdCovidTarifas(tCCOVIDTARIFAS.getIdCovidTarifas());

        partialUpdatedTCCOVIDTARIFAS
            .edad(UPDATED_EDAD)
            .qxcnsfg(UPDATED_QXCNSFG)
            .conyuge(UPDATED_CONYUGE)
            .hijoMayor(UPDATED_HIJO_MAYOR)
            .qxConyugeRecargada(UPDATED_QX_CONYUGE_RECARGADA)
            .qxHijoMenorRecargada(UPDATED_QX_HIJO_MENOR_RECARGADA)
            .valorGff(UPDATED_VALOR_GFF);

        restTCCOVIDTARIFASMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCOVIDTARIFAS.getIdCovidTarifas())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCOVIDTARIFAS))
            )
            .andExpect(status().isOk());

        // Validate the TCCOVIDTARIFAS in the database
        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeUpdate);
        TCCOVIDTARIFAS testTCCOVIDTARIFAS = tCCOVIDTARIFASList.get(tCCOVIDTARIFASList.size() - 1);
        assertThat(testTCCOVIDTARIFAS.getEdad()).isEqualTo(UPDATED_EDAD);
        assertThat(testTCCOVIDTARIFAS.getQxcnsfg()).isEqualTo(UPDATED_QXCNSFG);
        assertThat(testTCCOVIDTARIFAS.getTitular()).isEqualTo(DEFAULT_TITULAR);
        assertThat(testTCCOVIDTARIFAS.getConyuge()).isEqualTo(UPDATED_CONYUGE);
        assertThat(testTCCOVIDTARIFAS.getHijoMayor()).isEqualTo(UPDATED_HIJO_MAYOR);
        assertThat(testTCCOVIDTARIFAS.getHijoMenor()).isEqualTo(DEFAULT_HIJO_MENOR);
        assertThat(testTCCOVIDTARIFAS.getQxTitular()).isEqualTo(DEFAULT_QX_TITULAR);
        assertThat(testTCCOVIDTARIFAS.getQxConyuge()).isEqualTo(DEFAULT_QX_CONYUGE);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMayor()).isEqualTo(DEFAULT_QX_HIJO_MAYOR);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMenor()).isEqualTo(DEFAULT_QX_HIJO_MENOR);
        assertThat(testTCCOVIDTARIFAS.getQxTitularRecargada()).isEqualTo(DEFAULT_QX_TITULAR_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getQxConyugeRecargada()).isEqualTo(UPDATED_QX_CONYUGE_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMayorRecargada()).isEqualTo(DEFAULT_QX_HIJO_MAYOR_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMenorRecargada()).isEqualTo(UPDATED_QX_HIJO_MENOR_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getValorGff()).isEqualTo(UPDATED_VALOR_GFF);
        assertThat(testTCCOVIDTARIFAS.getValorGffCovid()).isEqualTo(DEFAULT_VALOR_GFF_COVID);
    }

    @Test
    @Transactional
    void fullUpdateTCCOVIDTARIFASWithPatch() throws Exception {
        // Initialize the database
        tCCOVIDTARIFASRepository.saveAndFlush(tCCOVIDTARIFAS);

        int databaseSizeBeforeUpdate = tCCOVIDTARIFASRepository.findAll().size();

        // Update the tCCOVIDTARIFAS using partial update
        TCCOVIDTARIFAS partialUpdatedTCCOVIDTARIFAS = new TCCOVIDTARIFAS();
        partialUpdatedTCCOVIDTARIFAS.setIdCovidTarifas(tCCOVIDTARIFAS.getIdCovidTarifas());

        partialUpdatedTCCOVIDTARIFAS
            .edad(UPDATED_EDAD)
            .qxcnsfg(UPDATED_QXCNSFG)
            .titular(UPDATED_TITULAR)
            .conyuge(UPDATED_CONYUGE)
            .hijoMayor(UPDATED_HIJO_MAYOR)
            .hijoMenor(UPDATED_HIJO_MENOR)
            .qxTitular(UPDATED_QX_TITULAR)
            .qxConyuge(UPDATED_QX_CONYUGE)
            .qxHijoMayor(UPDATED_QX_HIJO_MAYOR)
            .qxHijoMenor(UPDATED_QX_HIJO_MENOR)
            .qxTitularRecargada(UPDATED_QX_TITULAR_RECARGADA)
            .qxConyugeRecargada(UPDATED_QX_CONYUGE_RECARGADA)
            .qxHijoMayorRecargada(UPDATED_QX_HIJO_MAYOR_RECARGADA)
            .qxHijoMenorRecargada(UPDATED_QX_HIJO_MENOR_RECARGADA)
            .valorGff(UPDATED_VALOR_GFF)
            .valorGffCovid(UPDATED_VALOR_GFF_COVID);

        restTCCOVIDTARIFASMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCOVIDTARIFAS.getIdCovidTarifas())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCOVIDTARIFAS))
            )
            .andExpect(status().isOk());

        // Validate the TCCOVIDTARIFAS in the database
        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeUpdate);
        TCCOVIDTARIFAS testTCCOVIDTARIFAS = tCCOVIDTARIFASList.get(tCCOVIDTARIFASList.size() - 1);
        assertThat(testTCCOVIDTARIFAS.getEdad()).isEqualTo(UPDATED_EDAD);
        assertThat(testTCCOVIDTARIFAS.getQxcnsfg()).isEqualTo(UPDATED_QXCNSFG);
        assertThat(testTCCOVIDTARIFAS.getTitular()).isEqualTo(UPDATED_TITULAR);
        assertThat(testTCCOVIDTARIFAS.getConyuge()).isEqualTo(UPDATED_CONYUGE);
        assertThat(testTCCOVIDTARIFAS.getHijoMayor()).isEqualTo(UPDATED_HIJO_MAYOR);
        assertThat(testTCCOVIDTARIFAS.getHijoMenor()).isEqualTo(UPDATED_HIJO_MENOR);
        assertThat(testTCCOVIDTARIFAS.getQxTitular()).isEqualTo(UPDATED_QX_TITULAR);
        assertThat(testTCCOVIDTARIFAS.getQxConyuge()).isEqualTo(UPDATED_QX_CONYUGE);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMayor()).isEqualTo(UPDATED_QX_HIJO_MAYOR);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMenor()).isEqualTo(UPDATED_QX_HIJO_MENOR);
        assertThat(testTCCOVIDTARIFAS.getQxTitularRecargada()).isEqualTo(UPDATED_QX_TITULAR_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getQxConyugeRecargada()).isEqualTo(UPDATED_QX_CONYUGE_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMayorRecargada()).isEqualTo(UPDATED_QX_HIJO_MAYOR_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getQxHijoMenorRecargada()).isEqualTo(UPDATED_QX_HIJO_MENOR_RECARGADA);
        assertThat(testTCCOVIDTARIFAS.getValorGff()).isEqualTo(UPDATED_VALOR_GFF);
        assertThat(testTCCOVIDTARIFAS.getValorGffCovid()).isEqualTo(UPDATED_VALOR_GFF_COVID);
    }

    @Test
    @Transactional
    void patchNonExistingTCCOVIDTARIFAS() throws Exception {
        int databaseSizeBeforeUpdate = tCCOVIDTARIFASRepository.findAll().size();
        tCCOVIDTARIFAS.setIdCovidTarifas(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCOVIDTARIFASMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCCOVIDTARIFAS.getIdCovidTarifas())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOVIDTARIFAS in the database
        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCCOVIDTARIFAS() throws Exception {
        int databaseSizeBeforeUpdate = tCCOVIDTARIFASRepository.findAll().size();
        tCCOVIDTARIFAS.setIdCovidTarifas(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOVIDTARIFASMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOVIDTARIFAS in the database
        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCCOVIDTARIFAS() throws Exception {
        int databaseSizeBeforeUpdate = tCCOVIDTARIFASRepository.findAll().size();
        tCCOVIDTARIFAS.setIdCovidTarifas(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOVIDTARIFASMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCCOVIDTARIFAS))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCOVIDTARIFAS in the database
        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCCOVIDTARIFAS() throws Exception {
        // Initialize the database
        tCCOVIDTARIFASRepository.saveAndFlush(tCCOVIDTARIFAS);

        int databaseSizeBeforeDelete = tCCOVIDTARIFASRepository.findAll().size();

        // Delete the tCCOVIDTARIFAS
        restTCCOVIDTARIFASMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCCOVIDTARIFAS.getIdCovidTarifas()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCCOVIDTARIFAS> tCCOVIDTARIFASList = tCCOVIDTARIFASRepository.findAll();
        assertThat(tCCOVIDTARIFASList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
