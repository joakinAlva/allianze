package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCREGIONAL;
import com.allianze.apicotizador.repository.TCREGIONALRepository;
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
 * Integration tests for the {@link TCREGIONALResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCREGIONALResourceIT {

    private static final String DEFAULT_REGIONAL = "AAAAAAAAAA";
    private static final String UPDATED_REGIONAL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tcregionals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idRegional}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCREGIONALRepository tCREGIONALRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCREGIONALMockMvc;

    private TCREGIONAL tCREGIONAL;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCREGIONAL createEntity(EntityManager em) {
        TCREGIONAL tCREGIONAL = new TCREGIONAL().regional(DEFAULT_REGIONAL);
        return tCREGIONAL;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCREGIONAL createUpdatedEntity(EntityManager em) {
        TCREGIONAL tCREGIONAL = new TCREGIONAL().regional(UPDATED_REGIONAL);
        return tCREGIONAL;
    }

    @BeforeEach
    public void initTest() {
        tCREGIONAL = createEntity(em);
    }

    @Test
    @Transactional
    void createTCREGIONAL() throws Exception {
        int databaseSizeBeforeCreate = tCREGIONALRepository.findAll().size();
        // Create the TCREGIONAL
        restTCREGIONALMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCREGIONAL)))
            .andExpect(status().isCreated());

        // Validate the TCREGIONAL in the database
        List<TCREGIONAL> tCREGIONALList = tCREGIONALRepository.findAll();
        assertThat(tCREGIONALList).hasSize(databaseSizeBeforeCreate + 1);
        TCREGIONAL testTCREGIONAL = tCREGIONALList.get(tCREGIONALList.size() - 1);
        assertThat(testTCREGIONAL.getRegional()).isEqualTo(DEFAULT_REGIONAL);
    }

    @Test
    @Transactional
    void createTCREGIONALWithExistingId() throws Exception {
        // Create the TCREGIONAL with an existing ID
        tCREGIONAL.setIdRegional(1L);

        int databaseSizeBeforeCreate = tCREGIONALRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCREGIONALMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCREGIONAL)))
            .andExpect(status().isBadRequest());

        // Validate the TCREGIONAL in the database
        List<TCREGIONAL> tCREGIONALList = tCREGIONALRepository.findAll();
        assertThat(tCREGIONALList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkRegionalIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCREGIONALRepository.findAll().size();
        // set the field null
        tCREGIONAL.setRegional(null);

        // Create the TCREGIONAL, which fails.

        restTCREGIONALMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCREGIONAL)))
            .andExpect(status().isBadRequest());

        List<TCREGIONAL> tCREGIONALList = tCREGIONALRepository.findAll();
        assertThat(tCREGIONALList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCREGIONALS() throws Exception {
        // Initialize the database
        tCREGIONALRepository.saveAndFlush(tCREGIONAL);

        // Get all the tCREGIONALList
        restTCREGIONALMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idRegional,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idRegional").value(hasItem(tCREGIONAL.getIdRegional().intValue())))
            .andExpect(jsonPath("$.[*].regional").value(hasItem(DEFAULT_REGIONAL)));
    }

    @Test
    @Transactional
    void getTCREGIONAL() throws Exception {
        // Initialize the database
        tCREGIONALRepository.saveAndFlush(tCREGIONAL);

        // Get the tCREGIONAL
        restTCREGIONALMockMvc
            .perform(get(ENTITY_API_URL_ID, tCREGIONAL.getIdRegional()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idRegional").value(tCREGIONAL.getIdRegional().intValue()))
            .andExpect(jsonPath("$.regional").value(DEFAULT_REGIONAL));
    }

    @Test
    @Transactional
    void getNonExistingTCREGIONAL() throws Exception {
        // Get the tCREGIONAL
        restTCREGIONALMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCREGIONAL() throws Exception {
        // Initialize the database
        tCREGIONALRepository.saveAndFlush(tCREGIONAL);

        int databaseSizeBeforeUpdate = tCREGIONALRepository.findAll().size();

        // Update the tCREGIONAL
        TCREGIONAL updatedTCREGIONAL = tCREGIONALRepository.findById(tCREGIONAL.getIdRegional()).get();
        // Disconnect from session so that the updates on updatedTCREGIONAL are not directly saved in db
        em.detach(updatedTCREGIONAL);
        updatedTCREGIONAL.regional(UPDATED_REGIONAL);

        restTCREGIONALMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCREGIONAL.getIdRegional())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCREGIONAL))
            )
            .andExpect(status().isOk());

        // Validate the TCREGIONAL in the database
        List<TCREGIONAL> tCREGIONALList = tCREGIONALRepository.findAll();
        assertThat(tCREGIONALList).hasSize(databaseSizeBeforeUpdate);
        TCREGIONAL testTCREGIONAL = tCREGIONALList.get(tCREGIONALList.size() - 1);
        assertThat(testTCREGIONAL.getRegional()).isEqualTo(UPDATED_REGIONAL);
    }

    @Test
    @Transactional
    void putNonExistingTCREGIONAL() throws Exception {
        int databaseSizeBeforeUpdate = tCREGIONALRepository.findAll().size();
        tCREGIONAL.setIdRegional(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCREGIONALMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCREGIONAL.getIdRegional())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCREGIONAL))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCREGIONAL in the database
        List<TCREGIONAL> tCREGIONALList = tCREGIONALRepository.findAll();
        assertThat(tCREGIONALList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCREGIONAL() throws Exception {
        int databaseSizeBeforeUpdate = tCREGIONALRepository.findAll().size();
        tCREGIONAL.setIdRegional(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCREGIONALMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCREGIONAL))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCREGIONAL in the database
        List<TCREGIONAL> tCREGIONALList = tCREGIONALRepository.findAll();
        assertThat(tCREGIONALList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCREGIONAL() throws Exception {
        int databaseSizeBeforeUpdate = tCREGIONALRepository.findAll().size();
        tCREGIONAL.setIdRegional(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCREGIONALMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCREGIONAL)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCREGIONAL in the database
        List<TCREGIONAL> tCREGIONALList = tCREGIONALRepository.findAll();
        assertThat(tCREGIONALList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCREGIONALWithPatch() throws Exception {
        // Initialize the database
        tCREGIONALRepository.saveAndFlush(tCREGIONAL);

        int databaseSizeBeforeUpdate = tCREGIONALRepository.findAll().size();

        // Update the tCREGIONAL using partial update
        TCREGIONAL partialUpdatedTCREGIONAL = new TCREGIONAL();
        partialUpdatedTCREGIONAL.setIdRegional(tCREGIONAL.getIdRegional());

        restTCREGIONALMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCREGIONAL.getIdRegional())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCREGIONAL))
            )
            .andExpect(status().isOk());

        // Validate the TCREGIONAL in the database
        List<TCREGIONAL> tCREGIONALList = tCREGIONALRepository.findAll();
        assertThat(tCREGIONALList).hasSize(databaseSizeBeforeUpdate);
        TCREGIONAL testTCREGIONAL = tCREGIONALList.get(tCREGIONALList.size() - 1);
        assertThat(testTCREGIONAL.getRegional()).isEqualTo(DEFAULT_REGIONAL);
    }

    @Test
    @Transactional
    void fullUpdateTCREGIONALWithPatch() throws Exception {
        // Initialize the database
        tCREGIONALRepository.saveAndFlush(tCREGIONAL);

        int databaseSizeBeforeUpdate = tCREGIONALRepository.findAll().size();

        // Update the tCREGIONAL using partial update
        TCREGIONAL partialUpdatedTCREGIONAL = new TCREGIONAL();
        partialUpdatedTCREGIONAL.setIdRegional(tCREGIONAL.getIdRegional());

        partialUpdatedTCREGIONAL.regional(UPDATED_REGIONAL);

        restTCREGIONALMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCREGIONAL.getIdRegional())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCREGIONAL))
            )
            .andExpect(status().isOk());

        // Validate the TCREGIONAL in the database
        List<TCREGIONAL> tCREGIONALList = tCREGIONALRepository.findAll();
        assertThat(tCREGIONALList).hasSize(databaseSizeBeforeUpdate);
        TCREGIONAL testTCREGIONAL = tCREGIONALList.get(tCREGIONALList.size() - 1);
        assertThat(testTCREGIONAL.getRegional()).isEqualTo(UPDATED_REGIONAL);
    }

    @Test
    @Transactional
    void patchNonExistingTCREGIONAL() throws Exception {
        int databaseSizeBeforeUpdate = tCREGIONALRepository.findAll().size();
        tCREGIONAL.setIdRegional(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCREGIONALMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCREGIONAL.getIdRegional())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCREGIONAL))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCREGIONAL in the database
        List<TCREGIONAL> tCREGIONALList = tCREGIONALRepository.findAll();
        assertThat(tCREGIONALList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCREGIONAL() throws Exception {
        int databaseSizeBeforeUpdate = tCREGIONALRepository.findAll().size();
        tCREGIONAL.setIdRegional(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCREGIONALMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCREGIONAL))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCREGIONAL in the database
        List<TCREGIONAL> tCREGIONALList = tCREGIONALRepository.findAll();
        assertThat(tCREGIONALList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCREGIONAL() throws Exception {
        int databaseSizeBeforeUpdate = tCREGIONALRepository.findAll().size();
        tCREGIONAL.setIdRegional(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCREGIONALMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCREGIONAL))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCREGIONAL in the database
        List<TCREGIONAL> tCREGIONALList = tCREGIONALRepository.findAll();
        assertThat(tCREGIONALList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCREGIONAL() throws Exception {
        // Initialize the database
        tCREGIONALRepository.saveAndFlush(tCREGIONAL);

        int databaseSizeBeforeDelete = tCREGIONALRepository.findAll().size();

        // Delete the tCREGIONAL
        restTCREGIONALMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCREGIONAL.getIdRegional()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCREGIONAL> tCREGIONALList = tCREGIONALRepository.findAll();
        assertThat(tCREGIONALList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
