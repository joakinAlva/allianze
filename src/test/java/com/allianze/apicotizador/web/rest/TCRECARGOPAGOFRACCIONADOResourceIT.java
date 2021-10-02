package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCRECARGOPAGOFRACCIONADO;
import com.allianze.apicotizador.repository.TCRECARGOPAGOFRACCIONADORepository;
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
 * Integration tests for the {@link TCRECARGOPAGOFRACCIONADOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCRECARGOPAGOFRACCIONADOResourceIT {

    private static final String DEFAULT_PERIODO = "AAAAAAAAAA";
    private static final String UPDATED_PERIODO = "BBBBBBBBBB";

    private static final Long DEFAULT_PORCENTAJE = 1L;
    private static final Long UPDATED_PORCENTAJE = 2L;

    private static final String ENTITY_API_URL = "/api/tcrecargopagofraccionados";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idRecargoPagoFraccionado}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCRECARGOPAGOFRACCIONADORepository tCRECARGOPAGOFRACCIONADORepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCRECARGOPAGOFRACCIONADOMockMvc;

    private TCRECARGOPAGOFRACCIONADO tCRECARGOPAGOFRACCIONADO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCRECARGOPAGOFRACCIONADO createEntity(EntityManager em) {
        TCRECARGOPAGOFRACCIONADO tCRECARGOPAGOFRACCIONADO = new TCRECARGOPAGOFRACCIONADO()
            .periodo(DEFAULT_PERIODO)
            .porcentaje(DEFAULT_PORCENTAJE);
        return tCRECARGOPAGOFRACCIONADO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCRECARGOPAGOFRACCIONADO createUpdatedEntity(EntityManager em) {
        TCRECARGOPAGOFRACCIONADO tCRECARGOPAGOFRACCIONADO = new TCRECARGOPAGOFRACCIONADO()
            .periodo(UPDATED_PERIODO)
            .porcentaje(UPDATED_PORCENTAJE);
        return tCRECARGOPAGOFRACCIONADO;
    }

    @BeforeEach
    public void initTest() {
        tCRECARGOPAGOFRACCIONADO = createEntity(em);
    }

    @Test
    @Transactional
    void createTCRECARGOPAGOFRACCIONADO() throws Exception {
        int databaseSizeBeforeCreate = tCRECARGOPAGOFRACCIONADORepository.findAll().size();
        // Create the TCRECARGOPAGOFRACCIONADO
        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCRECARGOPAGOFRACCIONADO))
            )
            .andExpect(status().isCreated());

        // Validate the TCRECARGOPAGOFRACCIONADO in the database
        List<TCRECARGOPAGOFRACCIONADO> tCRECARGOPAGOFRACCIONADOList = tCRECARGOPAGOFRACCIONADORepository.findAll();
        assertThat(tCRECARGOPAGOFRACCIONADOList).hasSize(databaseSizeBeforeCreate + 1);
        TCRECARGOPAGOFRACCIONADO testTCRECARGOPAGOFRACCIONADO = tCRECARGOPAGOFRACCIONADOList.get(tCRECARGOPAGOFRACCIONADOList.size() - 1);
        assertThat(testTCRECARGOPAGOFRACCIONADO.getPeriodo()).isEqualTo(DEFAULT_PERIODO);
        assertThat(testTCRECARGOPAGOFRACCIONADO.getPorcentaje()).isEqualTo(DEFAULT_PORCENTAJE);
    }

    @Test
    @Transactional
    void createTCRECARGOPAGOFRACCIONADOWithExistingId() throws Exception {
        // Create the TCRECARGOPAGOFRACCIONADO with an existing ID
        tCRECARGOPAGOFRACCIONADO.setIdRecargoPagoFraccionado(1L);

        int databaseSizeBeforeCreate = tCRECARGOPAGOFRACCIONADORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCRECARGOPAGOFRACCIONADO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCRECARGOPAGOFRACCIONADO in the database
        List<TCRECARGOPAGOFRACCIONADO> tCRECARGOPAGOFRACCIONADOList = tCRECARGOPAGOFRACCIONADORepository.findAll();
        assertThat(tCRECARGOPAGOFRACCIONADOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPeriodoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCRECARGOPAGOFRACCIONADORepository.findAll().size();
        // set the field null
        tCRECARGOPAGOFRACCIONADO.setPeriodo(null);

        // Create the TCRECARGOPAGOFRACCIONADO, which fails.

        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCRECARGOPAGOFRACCIONADO))
            )
            .andExpect(status().isBadRequest());

        List<TCRECARGOPAGOFRACCIONADO> tCRECARGOPAGOFRACCIONADOList = tCRECARGOPAGOFRACCIONADORepository.findAll();
        assertThat(tCRECARGOPAGOFRACCIONADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPorcentajeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCRECARGOPAGOFRACCIONADORepository.findAll().size();
        // set the field null
        tCRECARGOPAGOFRACCIONADO.setPorcentaje(null);

        // Create the TCRECARGOPAGOFRACCIONADO, which fails.

        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCRECARGOPAGOFRACCIONADO))
            )
            .andExpect(status().isBadRequest());

        List<TCRECARGOPAGOFRACCIONADO> tCRECARGOPAGOFRACCIONADOList = tCRECARGOPAGOFRACCIONADORepository.findAll();
        assertThat(tCRECARGOPAGOFRACCIONADOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCRECARGOPAGOFRACCIONADOS() throws Exception {
        // Initialize the database
        tCRECARGOPAGOFRACCIONADORepository.saveAndFlush(tCRECARGOPAGOFRACCIONADO);

        // Get all the tCRECARGOPAGOFRACCIONADOList
        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idRecargoPagoFraccionado,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].idRecargoPagoFraccionado").value(hasItem(tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado().intValue()))
            )
            .andExpect(jsonPath("$.[*].periodo").value(hasItem(DEFAULT_PERIODO)))
            .andExpect(jsonPath("$.[*].porcentaje").value(hasItem(DEFAULT_PORCENTAJE.intValue())));
    }

    @Test
    @Transactional
    void getTCRECARGOPAGOFRACCIONADO() throws Exception {
        // Initialize the database
        tCRECARGOPAGOFRACCIONADORepository.saveAndFlush(tCRECARGOPAGOFRACCIONADO);

        // Get the tCRECARGOPAGOFRACCIONADO
        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(get(ENTITY_API_URL_ID, tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idRecargoPagoFraccionado").value(tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado().intValue()))
            .andExpect(jsonPath("$.periodo").value(DEFAULT_PERIODO))
            .andExpect(jsonPath("$.porcentaje").value(DEFAULT_PORCENTAJE.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCRECARGOPAGOFRACCIONADO() throws Exception {
        // Get the tCRECARGOPAGOFRACCIONADO
        restTCRECARGOPAGOFRACCIONADOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCRECARGOPAGOFRACCIONADO() throws Exception {
        // Initialize the database
        tCRECARGOPAGOFRACCIONADORepository.saveAndFlush(tCRECARGOPAGOFRACCIONADO);

        int databaseSizeBeforeUpdate = tCRECARGOPAGOFRACCIONADORepository.findAll().size();

        // Update the tCRECARGOPAGOFRACCIONADO
        TCRECARGOPAGOFRACCIONADO updatedTCRECARGOPAGOFRACCIONADO = tCRECARGOPAGOFRACCIONADORepository
            .findById(tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado())
            .get();
        // Disconnect from session so that the updates on updatedTCRECARGOPAGOFRACCIONADO are not directly saved in db
        em.detach(updatedTCRECARGOPAGOFRACCIONADO);
        updatedTCRECARGOPAGOFRACCIONADO.periodo(UPDATED_PERIODO).porcentaje(UPDATED_PORCENTAJE);

        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCRECARGOPAGOFRACCIONADO))
            )
            .andExpect(status().isOk());

        // Validate the TCRECARGOPAGOFRACCIONADO in the database
        List<TCRECARGOPAGOFRACCIONADO> tCRECARGOPAGOFRACCIONADOList = tCRECARGOPAGOFRACCIONADORepository.findAll();
        assertThat(tCRECARGOPAGOFRACCIONADOList).hasSize(databaseSizeBeforeUpdate);
        TCRECARGOPAGOFRACCIONADO testTCRECARGOPAGOFRACCIONADO = tCRECARGOPAGOFRACCIONADOList.get(tCRECARGOPAGOFRACCIONADOList.size() - 1);
        assertThat(testTCRECARGOPAGOFRACCIONADO.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testTCRECARGOPAGOFRACCIONADO.getPorcentaje()).isEqualTo(UPDATED_PORCENTAJE);
    }

    @Test
    @Transactional
    void putNonExistingTCRECARGOPAGOFRACCIONADO() throws Exception {
        int databaseSizeBeforeUpdate = tCRECARGOPAGOFRACCIONADORepository.findAll().size();
        tCRECARGOPAGOFRACCIONADO.setIdRecargoPagoFraccionado(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCRECARGOPAGOFRACCIONADO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCRECARGOPAGOFRACCIONADO in the database
        List<TCRECARGOPAGOFRACCIONADO> tCRECARGOPAGOFRACCIONADOList = tCRECARGOPAGOFRACCIONADORepository.findAll();
        assertThat(tCRECARGOPAGOFRACCIONADOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCRECARGOPAGOFRACCIONADO() throws Exception {
        int databaseSizeBeforeUpdate = tCRECARGOPAGOFRACCIONADORepository.findAll().size();
        tCRECARGOPAGOFRACCIONADO.setIdRecargoPagoFraccionado(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCRECARGOPAGOFRACCIONADO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCRECARGOPAGOFRACCIONADO in the database
        List<TCRECARGOPAGOFRACCIONADO> tCRECARGOPAGOFRACCIONADOList = tCRECARGOPAGOFRACCIONADORepository.findAll();
        assertThat(tCRECARGOPAGOFRACCIONADOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCRECARGOPAGOFRACCIONADO() throws Exception {
        int databaseSizeBeforeUpdate = tCRECARGOPAGOFRACCIONADORepository.findAll().size();
        tCRECARGOPAGOFRACCIONADO.setIdRecargoPagoFraccionado(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCRECARGOPAGOFRACCIONADO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCRECARGOPAGOFRACCIONADO in the database
        List<TCRECARGOPAGOFRACCIONADO> tCRECARGOPAGOFRACCIONADOList = tCRECARGOPAGOFRACCIONADORepository.findAll();
        assertThat(tCRECARGOPAGOFRACCIONADOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCRECARGOPAGOFRACCIONADOWithPatch() throws Exception {
        // Initialize the database
        tCRECARGOPAGOFRACCIONADORepository.saveAndFlush(tCRECARGOPAGOFRACCIONADO);

        int databaseSizeBeforeUpdate = tCRECARGOPAGOFRACCIONADORepository.findAll().size();

        // Update the tCRECARGOPAGOFRACCIONADO using partial update
        TCRECARGOPAGOFRACCIONADO partialUpdatedTCRECARGOPAGOFRACCIONADO = new TCRECARGOPAGOFRACCIONADO();
        partialUpdatedTCRECARGOPAGOFRACCIONADO.setIdRecargoPagoFraccionado(tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado());

        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCRECARGOPAGOFRACCIONADO))
            )
            .andExpect(status().isOk());

        // Validate the TCRECARGOPAGOFRACCIONADO in the database
        List<TCRECARGOPAGOFRACCIONADO> tCRECARGOPAGOFRACCIONADOList = tCRECARGOPAGOFRACCIONADORepository.findAll();
        assertThat(tCRECARGOPAGOFRACCIONADOList).hasSize(databaseSizeBeforeUpdate);
        TCRECARGOPAGOFRACCIONADO testTCRECARGOPAGOFRACCIONADO = tCRECARGOPAGOFRACCIONADOList.get(tCRECARGOPAGOFRACCIONADOList.size() - 1);
        assertThat(testTCRECARGOPAGOFRACCIONADO.getPeriodo()).isEqualTo(DEFAULT_PERIODO);
        assertThat(testTCRECARGOPAGOFRACCIONADO.getPorcentaje()).isEqualTo(DEFAULT_PORCENTAJE);
    }

    @Test
    @Transactional
    void fullUpdateTCRECARGOPAGOFRACCIONADOWithPatch() throws Exception {
        // Initialize the database
        tCRECARGOPAGOFRACCIONADORepository.saveAndFlush(tCRECARGOPAGOFRACCIONADO);

        int databaseSizeBeforeUpdate = tCRECARGOPAGOFRACCIONADORepository.findAll().size();

        // Update the tCRECARGOPAGOFRACCIONADO using partial update
        TCRECARGOPAGOFRACCIONADO partialUpdatedTCRECARGOPAGOFRACCIONADO = new TCRECARGOPAGOFRACCIONADO();
        partialUpdatedTCRECARGOPAGOFRACCIONADO.setIdRecargoPagoFraccionado(tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado());

        partialUpdatedTCRECARGOPAGOFRACCIONADO.periodo(UPDATED_PERIODO).porcentaje(UPDATED_PORCENTAJE);

        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCRECARGOPAGOFRACCIONADO))
            )
            .andExpect(status().isOk());

        // Validate the TCRECARGOPAGOFRACCIONADO in the database
        List<TCRECARGOPAGOFRACCIONADO> tCRECARGOPAGOFRACCIONADOList = tCRECARGOPAGOFRACCIONADORepository.findAll();
        assertThat(tCRECARGOPAGOFRACCIONADOList).hasSize(databaseSizeBeforeUpdate);
        TCRECARGOPAGOFRACCIONADO testTCRECARGOPAGOFRACCIONADO = tCRECARGOPAGOFRACCIONADOList.get(tCRECARGOPAGOFRACCIONADOList.size() - 1);
        assertThat(testTCRECARGOPAGOFRACCIONADO.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testTCRECARGOPAGOFRACCIONADO.getPorcentaje()).isEqualTo(UPDATED_PORCENTAJE);
    }

    @Test
    @Transactional
    void patchNonExistingTCRECARGOPAGOFRACCIONADO() throws Exception {
        int databaseSizeBeforeUpdate = tCRECARGOPAGOFRACCIONADORepository.findAll().size();
        tCRECARGOPAGOFRACCIONADO.setIdRecargoPagoFraccionado(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCRECARGOPAGOFRACCIONADO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCRECARGOPAGOFRACCIONADO in the database
        List<TCRECARGOPAGOFRACCIONADO> tCRECARGOPAGOFRACCIONADOList = tCRECARGOPAGOFRACCIONADORepository.findAll();
        assertThat(tCRECARGOPAGOFRACCIONADOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCRECARGOPAGOFRACCIONADO() throws Exception {
        int databaseSizeBeforeUpdate = tCRECARGOPAGOFRACCIONADORepository.findAll().size();
        tCRECARGOPAGOFRACCIONADO.setIdRecargoPagoFraccionado(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCRECARGOPAGOFRACCIONADO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCRECARGOPAGOFRACCIONADO in the database
        List<TCRECARGOPAGOFRACCIONADO> tCRECARGOPAGOFRACCIONADOList = tCRECARGOPAGOFRACCIONADORepository.findAll();
        assertThat(tCRECARGOPAGOFRACCIONADOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCRECARGOPAGOFRACCIONADO() throws Exception {
        int databaseSizeBeforeUpdate = tCRECARGOPAGOFRACCIONADORepository.findAll().size();
        tCRECARGOPAGOFRACCIONADO.setIdRecargoPagoFraccionado(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCRECARGOPAGOFRACCIONADO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCRECARGOPAGOFRACCIONADO in the database
        List<TCRECARGOPAGOFRACCIONADO> tCRECARGOPAGOFRACCIONADOList = tCRECARGOPAGOFRACCIONADORepository.findAll();
        assertThat(tCRECARGOPAGOFRACCIONADOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCRECARGOPAGOFRACCIONADO() throws Exception {
        // Initialize the database
        tCRECARGOPAGOFRACCIONADORepository.saveAndFlush(tCRECARGOPAGOFRACCIONADO);

        int databaseSizeBeforeDelete = tCRECARGOPAGOFRACCIONADORepository.findAll().size();

        // Delete the tCRECARGOPAGOFRACCIONADO
        restTCRECARGOPAGOFRACCIONADOMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCRECARGOPAGOFRACCIONADO.getIdRecargoPagoFraccionado()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCRECARGOPAGOFRACCIONADO> tCRECARGOPAGOFRACCIONADOList = tCRECARGOPAGOFRACCIONADORepository.findAll();
        assertThat(tCRECARGOPAGOFRACCIONADOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
