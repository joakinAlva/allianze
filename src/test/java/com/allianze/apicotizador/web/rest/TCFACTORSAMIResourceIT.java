package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCFACTORSAMI;
import com.allianze.apicotizador.repository.TCFACTORSAMIRepository;
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
 * Integration tests for the {@link TCFACTORSAMIResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCFACTORSAMIResourceIT {

    private static final String DEFAULT_MIN_ASEGURADOS = "AAAAAAAAAA";
    private static final String UPDATED_MIN_ASEGURADOS = "BBBBBBBBBB";

    private static final String DEFAULT_MAX_ASEGURADOS = "AAAAAAAAAA";
    private static final String UPDATED_MAX_ASEGURADOS = "BBBBBBBBBB";

    private static final String DEFAULT_FACTOR = "AAAAAAAAAA";
    private static final String UPDATED_FACTOR = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tcfactorsamis";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idFactorSami}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCFACTORSAMIRepository tCFACTORSAMIRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCFACTORSAMIMockMvc;

    private TCFACTORSAMI tCFACTORSAMI;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCFACTORSAMI createEntity(EntityManager em) {
        TCFACTORSAMI tCFACTORSAMI = new TCFACTORSAMI()
            .minAsegurados(DEFAULT_MIN_ASEGURADOS)
            .maxAsegurados(DEFAULT_MAX_ASEGURADOS)
            .factor(DEFAULT_FACTOR);
        return tCFACTORSAMI;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCFACTORSAMI createUpdatedEntity(EntityManager em) {
        TCFACTORSAMI tCFACTORSAMI = new TCFACTORSAMI()
            .minAsegurados(UPDATED_MIN_ASEGURADOS)
            .maxAsegurados(UPDATED_MAX_ASEGURADOS)
            .factor(UPDATED_FACTOR);
        return tCFACTORSAMI;
    }

    @BeforeEach
    public void initTest() {
        tCFACTORSAMI = createEntity(em);
    }

    @Test
    @Transactional
    void createTCFACTORSAMI() throws Exception {
        int databaseSizeBeforeCreate = tCFACTORSAMIRepository.findAll().size();
        // Create the TCFACTORSAMI
        restTCFACTORSAMIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCFACTORSAMI)))
            .andExpect(status().isCreated());

        // Validate the TCFACTORSAMI in the database
        List<TCFACTORSAMI> tCFACTORSAMIList = tCFACTORSAMIRepository.findAll();
        assertThat(tCFACTORSAMIList).hasSize(databaseSizeBeforeCreate + 1);
        TCFACTORSAMI testTCFACTORSAMI = tCFACTORSAMIList.get(tCFACTORSAMIList.size() - 1);
        assertThat(testTCFACTORSAMI.getMinAsegurados()).isEqualTo(DEFAULT_MIN_ASEGURADOS);
        assertThat(testTCFACTORSAMI.getMaxAsegurados()).isEqualTo(DEFAULT_MAX_ASEGURADOS);
        assertThat(testTCFACTORSAMI.getFactor()).isEqualTo(DEFAULT_FACTOR);
    }

    @Test
    @Transactional
    void createTCFACTORSAMIWithExistingId() throws Exception {
        // Create the TCFACTORSAMI with an existing ID
        tCFACTORSAMI.setIdFactorSami(1L);

        int databaseSizeBeforeCreate = tCFACTORSAMIRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCFACTORSAMIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCFACTORSAMI)))
            .andExpect(status().isBadRequest());

        // Validate the TCFACTORSAMI in the database
        List<TCFACTORSAMI> tCFACTORSAMIList = tCFACTORSAMIRepository.findAll();
        assertThat(tCFACTORSAMIList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkMinAseguradosIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCFACTORSAMIRepository.findAll().size();
        // set the field null
        tCFACTORSAMI.setMinAsegurados(null);

        // Create the TCFACTORSAMI, which fails.

        restTCFACTORSAMIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCFACTORSAMI)))
            .andExpect(status().isBadRequest());

        List<TCFACTORSAMI> tCFACTORSAMIList = tCFACTORSAMIRepository.findAll();
        assertThat(tCFACTORSAMIList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMaxAseguradosIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCFACTORSAMIRepository.findAll().size();
        // set the field null
        tCFACTORSAMI.setMaxAsegurados(null);

        // Create the TCFACTORSAMI, which fails.

        restTCFACTORSAMIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCFACTORSAMI)))
            .andExpect(status().isBadRequest());

        List<TCFACTORSAMI> tCFACTORSAMIList = tCFACTORSAMIRepository.findAll();
        assertThat(tCFACTORSAMIList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFactorIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCFACTORSAMIRepository.findAll().size();
        // set the field null
        tCFACTORSAMI.setFactor(null);

        // Create the TCFACTORSAMI, which fails.

        restTCFACTORSAMIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCFACTORSAMI)))
            .andExpect(status().isBadRequest());

        List<TCFACTORSAMI> tCFACTORSAMIList = tCFACTORSAMIRepository.findAll();
        assertThat(tCFACTORSAMIList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCFACTORSAMIS() throws Exception {
        // Initialize the database
        tCFACTORSAMIRepository.saveAndFlush(tCFACTORSAMI);

        // Get all the tCFACTORSAMIList
        restTCFACTORSAMIMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idFactorSami,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idFactorSami").value(hasItem(tCFACTORSAMI.getIdFactorSami().intValue())))
            .andExpect(jsonPath("$.[*].minAsegurados").value(hasItem(DEFAULT_MIN_ASEGURADOS)))
            .andExpect(jsonPath("$.[*].maxAsegurados").value(hasItem(DEFAULT_MAX_ASEGURADOS)))
            .andExpect(jsonPath("$.[*].factor").value(hasItem(DEFAULT_FACTOR)));
    }

    @Test
    @Transactional
    void getTCFACTORSAMI() throws Exception {
        // Initialize the database
        tCFACTORSAMIRepository.saveAndFlush(tCFACTORSAMI);

        // Get the tCFACTORSAMI
        restTCFACTORSAMIMockMvc
            .perform(get(ENTITY_API_URL_ID, tCFACTORSAMI.getIdFactorSami()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idFactorSami").value(tCFACTORSAMI.getIdFactorSami().intValue()))
            .andExpect(jsonPath("$.minAsegurados").value(DEFAULT_MIN_ASEGURADOS))
            .andExpect(jsonPath("$.maxAsegurados").value(DEFAULT_MAX_ASEGURADOS))
            .andExpect(jsonPath("$.factor").value(DEFAULT_FACTOR));
    }

    @Test
    @Transactional
    void getNonExistingTCFACTORSAMI() throws Exception {
        // Get the tCFACTORSAMI
        restTCFACTORSAMIMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCFACTORSAMI() throws Exception {
        // Initialize the database
        tCFACTORSAMIRepository.saveAndFlush(tCFACTORSAMI);

        int databaseSizeBeforeUpdate = tCFACTORSAMIRepository.findAll().size();

        // Update the tCFACTORSAMI
        TCFACTORSAMI updatedTCFACTORSAMI = tCFACTORSAMIRepository.findById(tCFACTORSAMI.getIdFactorSami()).get();
        // Disconnect from session so that the updates on updatedTCFACTORSAMI are not directly saved in db
        em.detach(updatedTCFACTORSAMI);
        updatedTCFACTORSAMI.minAsegurados(UPDATED_MIN_ASEGURADOS).maxAsegurados(UPDATED_MAX_ASEGURADOS).factor(UPDATED_FACTOR);

        restTCFACTORSAMIMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCFACTORSAMI.getIdFactorSami())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCFACTORSAMI))
            )
            .andExpect(status().isOk());

        // Validate the TCFACTORSAMI in the database
        List<TCFACTORSAMI> tCFACTORSAMIList = tCFACTORSAMIRepository.findAll();
        assertThat(tCFACTORSAMIList).hasSize(databaseSizeBeforeUpdate);
        TCFACTORSAMI testTCFACTORSAMI = tCFACTORSAMIList.get(tCFACTORSAMIList.size() - 1);
        assertThat(testTCFACTORSAMI.getMinAsegurados()).isEqualTo(UPDATED_MIN_ASEGURADOS);
        assertThat(testTCFACTORSAMI.getMaxAsegurados()).isEqualTo(UPDATED_MAX_ASEGURADOS);
        assertThat(testTCFACTORSAMI.getFactor()).isEqualTo(UPDATED_FACTOR);
    }

    @Test
    @Transactional
    void putNonExistingTCFACTORSAMI() throws Exception {
        int databaseSizeBeforeUpdate = tCFACTORSAMIRepository.findAll().size();
        tCFACTORSAMI.setIdFactorSami(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCFACTORSAMIMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCFACTORSAMI.getIdFactorSami())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCFACTORSAMI))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCFACTORSAMI in the database
        List<TCFACTORSAMI> tCFACTORSAMIList = tCFACTORSAMIRepository.findAll();
        assertThat(tCFACTORSAMIList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCFACTORSAMI() throws Exception {
        int databaseSizeBeforeUpdate = tCFACTORSAMIRepository.findAll().size();
        tCFACTORSAMI.setIdFactorSami(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCFACTORSAMIMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCFACTORSAMI))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCFACTORSAMI in the database
        List<TCFACTORSAMI> tCFACTORSAMIList = tCFACTORSAMIRepository.findAll();
        assertThat(tCFACTORSAMIList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCFACTORSAMI() throws Exception {
        int databaseSizeBeforeUpdate = tCFACTORSAMIRepository.findAll().size();
        tCFACTORSAMI.setIdFactorSami(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCFACTORSAMIMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCFACTORSAMI)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCFACTORSAMI in the database
        List<TCFACTORSAMI> tCFACTORSAMIList = tCFACTORSAMIRepository.findAll();
        assertThat(tCFACTORSAMIList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCFACTORSAMIWithPatch() throws Exception {
        // Initialize the database
        tCFACTORSAMIRepository.saveAndFlush(tCFACTORSAMI);

        int databaseSizeBeforeUpdate = tCFACTORSAMIRepository.findAll().size();

        // Update the tCFACTORSAMI using partial update
        TCFACTORSAMI partialUpdatedTCFACTORSAMI = new TCFACTORSAMI();
        partialUpdatedTCFACTORSAMI.setIdFactorSami(tCFACTORSAMI.getIdFactorSami());

        partialUpdatedTCFACTORSAMI.maxAsegurados(UPDATED_MAX_ASEGURADOS).factor(UPDATED_FACTOR);

        restTCFACTORSAMIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCFACTORSAMI.getIdFactorSami())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCFACTORSAMI))
            )
            .andExpect(status().isOk());

        // Validate the TCFACTORSAMI in the database
        List<TCFACTORSAMI> tCFACTORSAMIList = tCFACTORSAMIRepository.findAll();
        assertThat(tCFACTORSAMIList).hasSize(databaseSizeBeforeUpdate);
        TCFACTORSAMI testTCFACTORSAMI = tCFACTORSAMIList.get(tCFACTORSAMIList.size() - 1);
        assertThat(testTCFACTORSAMI.getMinAsegurados()).isEqualTo(DEFAULT_MIN_ASEGURADOS);
        assertThat(testTCFACTORSAMI.getMaxAsegurados()).isEqualTo(UPDATED_MAX_ASEGURADOS);
        assertThat(testTCFACTORSAMI.getFactor()).isEqualTo(UPDATED_FACTOR);
    }

    @Test
    @Transactional
    void fullUpdateTCFACTORSAMIWithPatch() throws Exception {
        // Initialize the database
        tCFACTORSAMIRepository.saveAndFlush(tCFACTORSAMI);

        int databaseSizeBeforeUpdate = tCFACTORSAMIRepository.findAll().size();

        // Update the tCFACTORSAMI using partial update
        TCFACTORSAMI partialUpdatedTCFACTORSAMI = new TCFACTORSAMI();
        partialUpdatedTCFACTORSAMI.setIdFactorSami(tCFACTORSAMI.getIdFactorSami());

        partialUpdatedTCFACTORSAMI.minAsegurados(UPDATED_MIN_ASEGURADOS).maxAsegurados(UPDATED_MAX_ASEGURADOS).factor(UPDATED_FACTOR);

        restTCFACTORSAMIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCFACTORSAMI.getIdFactorSami())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCFACTORSAMI))
            )
            .andExpect(status().isOk());

        // Validate the TCFACTORSAMI in the database
        List<TCFACTORSAMI> tCFACTORSAMIList = tCFACTORSAMIRepository.findAll();
        assertThat(tCFACTORSAMIList).hasSize(databaseSizeBeforeUpdate);
        TCFACTORSAMI testTCFACTORSAMI = tCFACTORSAMIList.get(tCFACTORSAMIList.size() - 1);
        assertThat(testTCFACTORSAMI.getMinAsegurados()).isEqualTo(UPDATED_MIN_ASEGURADOS);
        assertThat(testTCFACTORSAMI.getMaxAsegurados()).isEqualTo(UPDATED_MAX_ASEGURADOS);
        assertThat(testTCFACTORSAMI.getFactor()).isEqualTo(UPDATED_FACTOR);
    }

    @Test
    @Transactional
    void patchNonExistingTCFACTORSAMI() throws Exception {
        int databaseSizeBeforeUpdate = tCFACTORSAMIRepository.findAll().size();
        tCFACTORSAMI.setIdFactorSami(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCFACTORSAMIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCFACTORSAMI.getIdFactorSami())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCFACTORSAMI))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCFACTORSAMI in the database
        List<TCFACTORSAMI> tCFACTORSAMIList = tCFACTORSAMIRepository.findAll();
        assertThat(tCFACTORSAMIList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCFACTORSAMI() throws Exception {
        int databaseSizeBeforeUpdate = tCFACTORSAMIRepository.findAll().size();
        tCFACTORSAMI.setIdFactorSami(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCFACTORSAMIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCFACTORSAMI))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCFACTORSAMI in the database
        List<TCFACTORSAMI> tCFACTORSAMIList = tCFACTORSAMIRepository.findAll();
        assertThat(tCFACTORSAMIList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCFACTORSAMI() throws Exception {
        int databaseSizeBeforeUpdate = tCFACTORSAMIRepository.findAll().size();
        tCFACTORSAMI.setIdFactorSami(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCFACTORSAMIMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCFACTORSAMI))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCFACTORSAMI in the database
        List<TCFACTORSAMI> tCFACTORSAMIList = tCFACTORSAMIRepository.findAll();
        assertThat(tCFACTORSAMIList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCFACTORSAMI() throws Exception {
        // Initialize the database
        tCFACTORSAMIRepository.saveAndFlush(tCFACTORSAMI);

        int databaseSizeBeforeDelete = tCFACTORSAMIRepository.findAll().size();

        // Delete the tCFACTORSAMI
        restTCFACTORSAMIMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCFACTORSAMI.getIdFactorSami()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCFACTORSAMI> tCFACTORSAMIList = tCFACTORSAMIRepository.findAll();
        assertThat(tCFACTORSAMIList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
