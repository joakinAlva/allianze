package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TMCOTIZACIONEXPPROPIA;
import com.allianze.apicotizador.repository.TMCOTIZACIONEXPPROPIARepository;
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
 * Integration tests for the {@link TMCOTIZACIONEXPPROPIAResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TMCOTIZACIONEXPPROPIAResourceIT {

    private static final Long DEFAULT_NUMERO = 1L;
    private static final Long UPDATED_NUMERO = 2L;

    private static final Long DEFAULT_PERIODO = 1L;
    private static final Long UPDATED_PERIODO = 2L;

    private static final Long DEFAULT_SINIESTRO = 1L;
    private static final Long UPDATED_SINIESTRO = 2L;

    private static final Long DEFAULT_ASEGURADOS = 1L;
    private static final Long UPDATED_ASEGURADOS = 2L;

    private static final Long DEFAULT_VALOR_QX = 1L;
    private static final Long UPDATED_VALOR_QX = 2L;

    private static final String ENTITY_API_URL = "/api/tmcotizacionexppropias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCotizacionExpPropia}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TMCOTIZACIONEXPPROPIARepository tMCOTIZACIONEXPPROPIARepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTMCOTIZACIONEXPPROPIAMockMvc;

    private TMCOTIZACIONEXPPROPIA tMCOTIZACIONEXPPROPIA;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TMCOTIZACIONEXPPROPIA createEntity(EntityManager em) {
        TMCOTIZACIONEXPPROPIA tMCOTIZACIONEXPPROPIA = new TMCOTIZACIONEXPPROPIA()
            .numero(DEFAULT_NUMERO)
            .periodo(DEFAULT_PERIODO)
            .siniestro(DEFAULT_SINIESTRO)
            .asegurados(DEFAULT_ASEGURADOS)
            .valorQx(DEFAULT_VALOR_QX);
        return tMCOTIZACIONEXPPROPIA;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TMCOTIZACIONEXPPROPIA createUpdatedEntity(EntityManager em) {
        TMCOTIZACIONEXPPROPIA tMCOTIZACIONEXPPROPIA = new TMCOTIZACIONEXPPROPIA()
            .numero(UPDATED_NUMERO)
            .periodo(UPDATED_PERIODO)
            .siniestro(UPDATED_SINIESTRO)
            .asegurados(UPDATED_ASEGURADOS)
            .valorQx(UPDATED_VALOR_QX);
        return tMCOTIZACIONEXPPROPIA;
    }

    @BeforeEach
    public void initTest() {
        tMCOTIZACIONEXPPROPIA = createEntity(em);
    }

    @Test
    @Transactional
    void createTMCOTIZACIONEXPPROPIA() throws Exception {
        int databaseSizeBeforeCreate = tMCOTIZACIONEXPPROPIARepository.findAll().size();
        // Create the TMCOTIZACIONEXPPROPIA
        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isCreated());

        // Validate the TMCOTIZACIONEXPPROPIA in the database
        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeCreate + 1);
        TMCOTIZACIONEXPPROPIA testTMCOTIZACIONEXPPROPIA = tMCOTIZACIONEXPPROPIAList.get(tMCOTIZACIONEXPPROPIAList.size() - 1);
        assertThat(testTMCOTIZACIONEXPPROPIA.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testTMCOTIZACIONEXPPROPIA.getPeriodo()).isEqualTo(DEFAULT_PERIODO);
        assertThat(testTMCOTIZACIONEXPPROPIA.getSiniestro()).isEqualTo(DEFAULT_SINIESTRO);
        assertThat(testTMCOTIZACIONEXPPROPIA.getAsegurados()).isEqualTo(DEFAULT_ASEGURADOS);
        assertThat(testTMCOTIZACIONEXPPROPIA.getValorQx()).isEqualTo(DEFAULT_VALOR_QX);
    }

    @Test
    @Transactional
    void createTMCOTIZACIONEXPPROPIAWithExistingId() throws Exception {
        // Create the TMCOTIZACIONEXPPROPIA with an existing ID
        tMCOTIZACIONEXPPROPIA.setIdCotizacionExpPropia(1L);

        int databaseSizeBeforeCreate = tMCOTIZACIONEXPPROPIARepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMCOTIZACIONEXPPROPIA in the database
        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONEXPPROPIARepository.findAll().size();
        // set the field null
        tMCOTIZACIONEXPPROPIA.setNumero(null);

        // Create the TMCOTIZACIONEXPPROPIA, which fails.

        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPeriodoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONEXPPROPIARepository.findAll().size();
        // set the field null
        tMCOTIZACIONEXPPROPIA.setPeriodo(null);

        // Create the TMCOTIZACIONEXPPROPIA, which fails.

        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSiniestroIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONEXPPROPIARepository.findAll().size();
        // set the field null
        tMCOTIZACIONEXPPROPIA.setSiniestro(null);

        // Create the TMCOTIZACIONEXPPROPIA, which fails.

        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAseguradosIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONEXPPROPIARepository.findAll().size();
        // set the field null
        tMCOTIZACIONEXPPROPIA.setAsegurados(null);

        // Create the TMCOTIZACIONEXPPROPIA, which fails.

        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorQxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONEXPPROPIARepository.findAll().size();
        // set the field null
        tMCOTIZACIONEXPPROPIA.setValorQx(null);

        // Create the TMCOTIZACIONEXPPROPIA, which fails.

        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isBadRequest());

        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTMCOTIZACIONEXPPROPIAS() throws Exception {
        // Initialize the database
        tMCOTIZACIONEXPPROPIARepository.saveAndFlush(tMCOTIZACIONEXPPROPIA);

        // Get all the tMCOTIZACIONEXPPROPIAList
        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCotizacionExpPropia,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCotizacionExpPropia").value(hasItem(tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())))
            .andExpect(jsonPath("$.[*].periodo").value(hasItem(DEFAULT_PERIODO.intValue())))
            .andExpect(jsonPath("$.[*].siniestro").value(hasItem(DEFAULT_SINIESTRO.intValue())))
            .andExpect(jsonPath("$.[*].asegurados").value(hasItem(DEFAULT_ASEGURADOS.intValue())))
            .andExpect(jsonPath("$.[*].valorQx").value(hasItem(DEFAULT_VALOR_QX.intValue())));
    }

    @Test
    @Transactional
    void getTMCOTIZACIONEXPPROPIA() throws Exception {
        // Initialize the database
        tMCOTIZACIONEXPPROPIARepository.saveAndFlush(tMCOTIZACIONEXPPROPIA);

        // Get the tMCOTIZACIONEXPPROPIA
        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(get(ENTITY_API_URL_ID, tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCotizacionExpPropia").value(tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.intValue()))
            .andExpect(jsonPath("$.periodo").value(DEFAULT_PERIODO.intValue()))
            .andExpect(jsonPath("$.siniestro").value(DEFAULT_SINIESTRO.intValue()))
            .andExpect(jsonPath("$.asegurados").value(DEFAULT_ASEGURADOS.intValue()))
            .andExpect(jsonPath("$.valorQx").value(DEFAULT_VALOR_QX.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTMCOTIZACIONEXPPROPIA() throws Exception {
        // Get the tMCOTIZACIONEXPPROPIA
        restTMCOTIZACIONEXPPROPIAMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTMCOTIZACIONEXPPROPIA() throws Exception {
        // Initialize the database
        tMCOTIZACIONEXPPROPIARepository.saveAndFlush(tMCOTIZACIONEXPPROPIA);

        int databaseSizeBeforeUpdate = tMCOTIZACIONEXPPROPIARepository.findAll().size();

        // Update the tMCOTIZACIONEXPPROPIA
        TMCOTIZACIONEXPPROPIA updatedTMCOTIZACIONEXPPROPIA = tMCOTIZACIONEXPPROPIARepository
            .findById(tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia())
            .get();
        // Disconnect from session so that the updates on updatedTMCOTIZACIONEXPPROPIA are not directly saved in db
        em.detach(updatedTMCOTIZACIONEXPPROPIA);
        updatedTMCOTIZACIONEXPPROPIA
            .numero(UPDATED_NUMERO)
            .periodo(UPDATED_PERIODO)
            .siniestro(UPDATED_SINIESTRO)
            .asegurados(UPDATED_ASEGURADOS)
            .valorQx(UPDATED_VALOR_QX);

        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isOk());

        // Validate the TMCOTIZACIONEXPPROPIA in the database
        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeUpdate);
        TMCOTIZACIONEXPPROPIA testTMCOTIZACIONEXPPROPIA = tMCOTIZACIONEXPPROPIAList.get(tMCOTIZACIONEXPPROPIAList.size() - 1);
        assertThat(testTMCOTIZACIONEXPPROPIA.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testTMCOTIZACIONEXPPROPIA.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testTMCOTIZACIONEXPPROPIA.getSiniestro()).isEqualTo(UPDATED_SINIESTRO);
        assertThat(testTMCOTIZACIONEXPPROPIA.getAsegurados()).isEqualTo(UPDATED_ASEGURADOS);
        assertThat(testTMCOTIZACIONEXPPROPIA.getValorQx()).isEqualTo(UPDATED_VALOR_QX);
    }

    @Test
    @Transactional
    void putNonExistingTMCOTIZACIONEXPPROPIA() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONEXPPROPIARepository.findAll().size();
        tMCOTIZACIONEXPPROPIA.setIdCotizacionExpPropia(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMCOTIZACIONEXPPROPIA in the database
        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTMCOTIZACIONEXPPROPIA() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONEXPPROPIARepository.findAll().size();
        tMCOTIZACIONEXPPROPIA.setIdCotizacionExpPropia(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMCOTIZACIONEXPPROPIA in the database
        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTMCOTIZACIONEXPPROPIA() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONEXPPROPIARepository.findAll().size();
        tMCOTIZACIONEXPPROPIA.setIdCotizacionExpPropia(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TMCOTIZACIONEXPPROPIA in the database
        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTMCOTIZACIONEXPPROPIAWithPatch() throws Exception {
        // Initialize the database
        tMCOTIZACIONEXPPROPIARepository.saveAndFlush(tMCOTIZACIONEXPPROPIA);

        int databaseSizeBeforeUpdate = tMCOTIZACIONEXPPROPIARepository.findAll().size();

        // Update the tMCOTIZACIONEXPPROPIA using partial update
        TMCOTIZACIONEXPPROPIA partialUpdatedTMCOTIZACIONEXPPROPIA = new TMCOTIZACIONEXPPROPIA();
        partialUpdatedTMCOTIZACIONEXPPROPIA.setIdCotizacionExpPropia(tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia());

        partialUpdatedTMCOTIZACIONEXPPROPIA.asegurados(UPDATED_ASEGURADOS);

        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isOk());

        // Validate the TMCOTIZACIONEXPPROPIA in the database
        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeUpdate);
        TMCOTIZACIONEXPPROPIA testTMCOTIZACIONEXPPROPIA = tMCOTIZACIONEXPPROPIAList.get(tMCOTIZACIONEXPPROPIAList.size() - 1);
        assertThat(testTMCOTIZACIONEXPPROPIA.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testTMCOTIZACIONEXPPROPIA.getPeriodo()).isEqualTo(DEFAULT_PERIODO);
        assertThat(testTMCOTIZACIONEXPPROPIA.getSiniestro()).isEqualTo(DEFAULT_SINIESTRO);
        assertThat(testTMCOTIZACIONEXPPROPIA.getAsegurados()).isEqualTo(UPDATED_ASEGURADOS);
        assertThat(testTMCOTIZACIONEXPPROPIA.getValorQx()).isEqualTo(DEFAULT_VALOR_QX);
    }

    @Test
    @Transactional
    void fullUpdateTMCOTIZACIONEXPPROPIAWithPatch() throws Exception {
        // Initialize the database
        tMCOTIZACIONEXPPROPIARepository.saveAndFlush(tMCOTIZACIONEXPPROPIA);

        int databaseSizeBeforeUpdate = tMCOTIZACIONEXPPROPIARepository.findAll().size();

        // Update the tMCOTIZACIONEXPPROPIA using partial update
        TMCOTIZACIONEXPPROPIA partialUpdatedTMCOTIZACIONEXPPROPIA = new TMCOTIZACIONEXPPROPIA();
        partialUpdatedTMCOTIZACIONEXPPROPIA.setIdCotizacionExpPropia(tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia());

        partialUpdatedTMCOTIZACIONEXPPROPIA
            .numero(UPDATED_NUMERO)
            .periodo(UPDATED_PERIODO)
            .siniestro(UPDATED_SINIESTRO)
            .asegurados(UPDATED_ASEGURADOS)
            .valorQx(UPDATED_VALOR_QX);

        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isOk());

        // Validate the TMCOTIZACIONEXPPROPIA in the database
        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeUpdate);
        TMCOTIZACIONEXPPROPIA testTMCOTIZACIONEXPPROPIA = tMCOTIZACIONEXPPROPIAList.get(tMCOTIZACIONEXPPROPIAList.size() - 1);
        assertThat(testTMCOTIZACIONEXPPROPIA.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testTMCOTIZACIONEXPPROPIA.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testTMCOTIZACIONEXPPROPIA.getSiniestro()).isEqualTo(UPDATED_SINIESTRO);
        assertThat(testTMCOTIZACIONEXPPROPIA.getAsegurados()).isEqualTo(UPDATED_ASEGURADOS);
        assertThat(testTMCOTIZACIONEXPPROPIA.getValorQx()).isEqualTo(UPDATED_VALOR_QX);
    }

    @Test
    @Transactional
    void patchNonExistingTMCOTIZACIONEXPPROPIA() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONEXPPROPIARepository.findAll().size();
        tMCOTIZACIONEXPPROPIA.setIdCotizacionExpPropia(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMCOTIZACIONEXPPROPIA in the database
        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTMCOTIZACIONEXPPROPIA() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONEXPPROPIARepository.findAll().size();
        tMCOTIZACIONEXPPROPIA.setIdCotizacionExpPropia(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMCOTIZACIONEXPPROPIA in the database
        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTMCOTIZACIONEXPPROPIA() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONEXPPROPIARepository.findAll().size();
        tMCOTIZACIONEXPPROPIA.setIdCotizacionExpPropia(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACIONEXPPROPIA))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TMCOTIZACIONEXPPROPIA in the database
        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTMCOTIZACIONEXPPROPIA() throws Exception {
        // Initialize the database
        tMCOTIZACIONEXPPROPIARepository.saveAndFlush(tMCOTIZACIONEXPPROPIA);

        int databaseSizeBeforeDelete = tMCOTIZACIONEXPPROPIARepository.findAll().size();

        // Delete the tMCOTIZACIONEXPPROPIA
        restTMCOTIZACIONEXPPROPIAMockMvc
            .perform(delete(ENTITY_API_URL_ID, tMCOTIZACIONEXPPROPIA.getIdCotizacionExpPropia()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TMCOTIZACIONEXPPROPIA> tMCOTIZACIONEXPPROPIAList = tMCOTIZACIONEXPPROPIARepository.findAll();
        assertThat(tMCOTIZACIONEXPPROPIAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
