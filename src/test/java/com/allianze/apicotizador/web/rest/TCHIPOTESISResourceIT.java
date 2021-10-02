package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCHIPOTESIS;
import com.allianze.apicotizador.repository.TCHIPOTESISRepository;
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
 * Integration tests for the {@link TCHIPOTESISResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCHIPOTESISResourceIT {

    private static final String DEFAULT_HIPOTESIS = "AAAAAAAAAA";
    private static final String UPDATED_HIPOTESIS = "BBBBBBBBBB";

    private static final Long DEFAULT_VALOR = 1L;
    private static final Long UPDATED_VALOR = 2L;

    private static final String DEFAULT_VARIABLE = "AAAAAAAAAA";
    private static final String UPDATED_VARIABLE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tchipoteses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idHipotesis}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCHIPOTESISRepository tCHIPOTESISRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCHIPOTESISMockMvc;

    private TCHIPOTESIS tCHIPOTESIS;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCHIPOTESIS createEntity(EntityManager em) {
        TCHIPOTESIS tCHIPOTESIS = new TCHIPOTESIS().hipotesis(DEFAULT_HIPOTESIS).valor(DEFAULT_VALOR).variable(DEFAULT_VARIABLE);
        return tCHIPOTESIS;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCHIPOTESIS createUpdatedEntity(EntityManager em) {
        TCHIPOTESIS tCHIPOTESIS = new TCHIPOTESIS().hipotesis(UPDATED_HIPOTESIS).valor(UPDATED_VALOR).variable(UPDATED_VARIABLE);
        return tCHIPOTESIS;
    }

    @BeforeEach
    public void initTest() {
        tCHIPOTESIS = createEntity(em);
    }

    @Test
    @Transactional
    void createTCHIPOTESIS() throws Exception {
        int databaseSizeBeforeCreate = tCHIPOTESISRepository.findAll().size();
        // Create the TCHIPOTESIS
        restTCHIPOTESISMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCHIPOTESIS)))
            .andExpect(status().isCreated());

        // Validate the TCHIPOTESIS in the database
        List<TCHIPOTESIS> tCHIPOTESISList = tCHIPOTESISRepository.findAll();
        assertThat(tCHIPOTESISList).hasSize(databaseSizeBeforeCreate + 1);
        TCHIPOTESIS testTCHIPOTESIS = tCHIPOTESISList.get(tCHIPOTESISList.size() - 1);
        assertThat(testTCHIPOTESIS.getHipotesis()).isEqualTo(DEFAULT_HIPOTESIS);
        assertThat(testTCHIPOTESIS.getValor()).isEqualTo(DEFAULT_VALOR);
        assertThat(testTCHIPOTESIS.getVariable()).isEqualTo(DEFAULT_VARIABLE);
    }

    @Test
    @Transactional
    void createTCHIPOTESISWithExistingId() throws Exception {
        // Create the TCHIPOTESIS with an existing ID
        tCHIPOTESIS.setIdHipotesis(1L);

        int databaseSizeBeforeCreate = tCHIPOTESISRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCHIPOTESISMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCHIPOTESIS)))
            .andExpect(status().isBadRequest());

        // Validate the TCHIPOTESIS in the database
        List<TCHIPOTESIS> tCHIPOTESISList = tCHIPOTESISRepository.findAll();
        assertThat(tCHIPOTESISList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkHipotesisIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCHIPOTESISRepository.findAll().size();
        // set the field null
        tCHIPOTESIS.setHipotesis(null);

        // Create the TCHIPOTESIS, which fails.

        restTCHIPOTESISMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCHIPOTESIS)))
            .andExpect(status().isBadRequest());

        List<TCHIPOTESIS> tCHIPOTESISList = tCHIPOTESISRepository.findAll();
        assertThat(tCHIPOTESISList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCHIPOTESISRepository.findAll().size();
        // set the field null
        tCHIPOTESIS.setValor(null);

        // Create the TCHIPOTESIS, which fails.

        restTCHIPOTESISMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCHIPOTESIS)))
            .andExpect(status().isBadRequest());

        List<TCHIPOTESIS> tCHIPOTESISList = tCHIPOTESISRepository.findAll();
        assertThat(tCHIPOTESISList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkVariableIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCHIPOTESISRepository.findAll().size();
        // set the field null
        tCHIPOTESIS.setVariable(null);

        // Create the TCHIPOTESIS, which fails.

        restTCHIPOTESISMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCHIPOTESIS)))
            .andExpect(status().isBadRequest());

        List<TCHIPOTESIS> tCHIPOTESISList = tCHIPOTESISRepository.findAll();
        assertThat(tCHIPOTESISList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCHIPOTESES() throws Exception {
        // Initialize the database
        tCHIPOTESISRepository.saveAndFlush(tCHIPOTESIS);

        // Get all the tCHIPOTESISList
        restTCHIPOTESISMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idHipotesis,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idHipotesis").value(hasItem(tCHIPOTESIS.getIdHipotesis().intValue())))
            .andExpect(jsonPath("$.[*].hipotesis").value(hasItem(DEFAULT_HIPOTESIS)))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.intValue())))
            .andExpect(jsonPath("$.[*].variable").value(hasItem(DEFAULT_VARIABLE)));
    }

    @Test
    @Transactional
    void getTCHIPOTESIS() throws Exception {
        // Initialize the database
        tCHIPOTESISRepository.saveAndFlush(tCHIPOTESIS);

        // Get the tCHIPOTESIS
        restTCHIPOTESISMockMvc
            .perform(get(ENTITY_API_URL_ID, tCHIPOTESIS.getIdHipotesis()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idHipotesis").value(tCHIPOTESIS.getIdHipotesis().intValue()))
            .andExpect(jsonPath("$.hipotesis").value(DEFAULT_HIPOTESIS))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.intValue()))
            .andExpect(jsonPath("$.variable").value(DEFAULT_VARIABLE));
    }

    @Test
    @Transactional
    void getNonExistingTCHIPOTESIS() throws Exception {
        // Get the tCHIPOTESIS
        restTCHIPOTESISMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCHIPOTESIS() throws Exception {
        // Initialize the database
        tCHIPOTESISRepository.saveAndFlush(tCHIPOTESIS);

        int databaseSizeBeforeUpdate = tCHIPOTESISRepository.findAll().size();

        // Update the tCHIPOTESIS
        TCHIPOTESIS updatedTCHIPOTESIS = tCHIPOTESISRepository.findById(tCHIPOTESIS.getIdHipotesis()).get();
        // Disconnect from session so that the updates on updatedTCHIPOTESIS are not directly saved in db
        em.detach(updatedTCHIPOTESIS);
        updatedTCHIPOTESIS.hipotesis(UPDATED_HIPOTESIS).valor(UPDATED_VALOR).variable(UPDATED_VARIABLE);

        restTCHIPOTESISMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCHIPOTESIS.getIdHipotesis())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCHIPOTESIS))
            )
            .andExpect(status().isOk());

        // Validate the TCHIPOTESIS in the database
        List<TCHIPOTESIS> tCHIPOTESISList = tCHIPOTESISRepository.findAll();
        assertThat(tCHIPOTESISList).hasSize(databaseSizeBeforeUpdate);
        TCHIPOTESIS testTCHIPOTESIS = tCHIPOTESISList.get(tCHIPOTESISList.size() - 1);
        assertThat(testTCHIPOTESIS.getHipotesis()).isEqualTo(UPDATED_HIPOTESIS);
        assertThat(testTCHIPOTESIS.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testTCHIPOTESIS.getVariable()).isEqualTo(UPDATED_VARIABLE);
    }

    @Test
    @Transactional
    void putNonExistingTCHIPOTESIS() throws Exception {
        int databaseSizeBeforeUpdate = tCHIPOTESISRepository.findAll().size();
        tCHIPOTESIS.setIdHipotesis(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCHIPOTESISMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCHIPOTESIS.getIdHipotesis())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCHIPOTESIS))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCHIPOTESIS in the database
        List<TCHIPOTESIS> tCHIPOTESISList = tCHIPOTESISRepository.findAll();
        assertThat(tCHIPOTESISList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCHIPOTESIS() throws Exception {
        int databaseSizeBeforeUpdate = tCHIPOTESISRepository.findAll().size();
        tCHIPOTESIS.setIdHipotesis(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCHIPOTESISMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCHIPOTESIS))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCHIPOTESIS in the database
        List<TCHIPOTESIS> tCHIPOTESISList = tCHIPOTESISRepository.findAll();
        assertThat(tCHIPOTESISList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCHIPOTESIS() throws Exception {
        int databaseSizeBeforeUpdate = tCHIPOTESISRepository.findAll().size();
        tCHIPOTESIS.setIdHipotesis(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCHIPOTESISMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCHIPOTESIS)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCHIPOTESIS in the database
        List<TCHIPOTESIS> tCHIPOTESISList = tCHIPOTESISRepository.findAll();
        assertThat(tCHIPOTESISList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCHIPOTESISWithPatch() throws Exception {
        // Initialize the database
        tCHIPOTESISRepository.saveAndFlush(tCHIPOTESIS);

        int databaseSizeBeforeUpdate = tCHIPOTESISRepository.findAll().size();

        // Update the tCHIPOTESIS using partial update
        TCHIPOTESIS partialUpdatedTCHIPOTESIS = new TCHIPOTESIS();
        partialUpdatedTCHIPOTESIS.setIdHipotesis(tCHIPOTESIS.getIdHipotesis());

        partialUpdatedTCHIPOTESIS.hipotesis(UPDATED_HIPOTESIS).valor(UPDATED_VALOR).variable(UPDATED_VARIABLE);

        restTCHIPOTESISMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCHIPOTESIS.getIdHipotesis())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCHIPOTESIS))
            )
            .andExpect(status().isOk());

        // Validate the TCHIPOTESIS in the database
        List<TCHIPOTESIS> tCHIPOTESISList = tCHIPOTESISRepository.findAll();
        assertThat(tCHIPOTESISList).hasSize(databaseSizeBeforeUpdate);
        TCHIPOTESIS testTCHIPOTESIS = tCHIPOTESISList.get(tCHIPOTESISList.size() - 1);
        assertThat(testTCHIPOTESIS.getHipotesis()).isEqualTo(UPDATED_HIPOTESIS);
        assertThat(testTCHIPOTESIS.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testTCHIPOTESIS.getVariable()).isEqualTo(UPDATED_VARIABLE);
    }

    @Test
    @Transactional
    void fullUpdateTCHIPOTESISWithPatch() throws Exception {
        // Initialize the database
        tCHIPOTESISRepository.saveAndFlush(tCHIPOTESIS);

        int databaseSizeBeforeUpdate = tCHIPOTESISRepository.findAll().size();

        // Update the tCHIPOTESIS using partial update
        TCHIPOTESIS partialUpdatedTCHIPOTESIS = new TCHIPOTESIS();
        partialUpdatedTCHIPOTESIS.setIdHipotesis(tCHIPOTESIS.getIdHipotesis());

        partialUpdatedTCHIPOTESIS.hipotesis(UPDATED_HIPOTESIS).valor(UPDATED_VALOR).variable(UPDATED_VARIABLE);

        restTCHIPOTESISMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCHIPOTESIS.getIdHipotesis())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCHIPOTESIS))
            )
            .andExpect(status().isOk());

        // Validate the TCHIPOTESIS in the database
        List<TCHIPOTESIS> tCHIPOTESISList = tCHIPOTESISRepository.findAll();
        assertThat(tCHIPOTESISList).hasSize(databaseSizeBeforeUpdate);
        TCHIPOTESIS testTCHIPOTESIS = tCHIPOTESISList.get(tCHIPOTESISList.size() - 1);
        assertThat(testTCHIPOTESIS.getHipotesis()).isEqualTo(UPDATED_HIPOTESIS);
        assertThat(testTCHIPOTESIS.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testTCHIPOTESIS.getVariable()).isEqualTo(UPDATED_VARIABLE);
    }

    @Test
    @Transactional
    void patchNonExistingTCHIPOTESIS() throws Exception {
        int databaseSizeBeforeUpdate = tCHIPOTESISRepository.findAll().size();
        tCHIPOTESIS.setIdHipotesis(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCHIPOTESISMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCHIPOTESIS.getIdHipotesis())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCHIPOTESIS))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCHIPOTESIS in the database
        List<TCHIPOTESIS> tCHIPOTESISList = tCHIPOTESISRepository.findAll();
        assertThat(tCHIPOTESISList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCHIPOTESIS() throws Exception {
        int databaseSizeBeforeUpdate = tCHIPOTESISRepository.findAll().size();
        tCHIPOTESIS.setIdHipotesis(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCHIPOTESISMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCHIPOTESIS))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCHIPOTESIS in the database
        List<TCHIPOTESIS> tCHIPOTESISList = tCHIPOTESISRepository.findAll();
        assertThat(tCHIPOTESISList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCHIPOTESIS() throws Exception {
        int databaseSizeBeforeUpdate = tCHIPOTESISRepository.findAll().size();
        tCHIPOTESIS.setIdHipotesis(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCHIPOTESISMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCHIPOTESIS))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCHIPOTESIS in the database
        List<TCHIPOTESIS> tCHIPOTESISList = tCHIPOTESISRepository.findAll();
        assertThat(tCHIPOTESISList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCHIPOTESIS() throws Exception {
        // Initialize the database
        tCHIPOTESISRepository.saveAndFlush(tCHIPOTESIS);

        int databaseSizeBeforeDelete = tCHIPOTESISRepository.findAll().size();

        // Delete the tCHIPOTESIS
        restTCHIPOTESISMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCHIPOTESIS.getIdHipotesis()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCHIPOTESIS> tCHIPOTESISList = tCHIPOTESISRepository.findAll();
        assertThat(tCHIPOTESISList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
