package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCCONCEPTO;
import com.allianze.apicotizador.repository.TCCONCEPTORepository;
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
 * Integration tests for the {@link TCCONCEPTOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCCONCEPTOResourceIT {

    private static final String DEFAULT_CONCEPTO = "AAAAAAAAAA";
    private static final String UPDATED_CONCEPTO = "BBBBBBBBBB";

    private static final Long DEFAULT_DATO = 1L;
    private static final Long UPDATED_DATO = 2L;

    private static final String ENTITY_API_URL = "/api/tcconceptos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idConcepto}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCCONCEPTORepository tCCONCEPTORepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCCONCEPTOMockMvc;

    private TCCONCEPTO tCCONCEPTO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCONCEPTO createEntity(EntityManager em) {
        TCCONCEPTO tCCONCEPTO = new TCCONCEPTO().concepto(DEFAULT_CONCEPTO).dato(DEFAULT_DATO);
        return tCCONCEPTO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCONCEPTO createUpdatedEntity(EntityManager em) {
        TCCONCEPTO tCCONCEPTO = new TCCONCEPTO().concepto(UPDATED_CONCEPTO).dato(UPDATED_DATO);
        return tCCONCEPTO;
    }

    @BeforeEach
    public void initTest() {
        tCCONCEPTO = createEntity(em);
    }

    @Test
    @Transactional
    void createTCCONCEPTO() throws Exception {
        int databaseSizeBeforeCreate = tCCONCEPTORepository.findAll().size();
        // Create the TCCONCEPTO
        restTCCONCEPTOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCONCEPTO)))
            .andExpect(status().isCreated());

        // Validate the TCCONCEPTO in the database
        List<TCCONCEPTO> tCCONCEPTOList = tCCONCEPTORepository.findAll();
        assertThat(tCCONCEPTOList).hasSize(databaseSizeBeforeCreate + 1);
        TCCONCEPTO testTCCONCEPTO = tCCONCEPTOList.get(tCCONCEPTOList.size() - 1);
        assertThat(testTCCONCEPTO.getConcepto()).isEqualTo(DEFAULT_CONCEPTO);
        assertThat(testTCCONCEPTO.getDato()).isEqualTo(DEFAULT_DATO);
    }

    @Test
    @Transactional
    void createTCCONCEPTOWithExistingId() throws Exception {
        // Create the TCCONCEPTO with an existing ID
        tCCONCEPTO.setIdConcepto(1L);

        int databaseSizeBeforeCreate = tCCONCEPTORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCCONCEPTOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCONCEPTO)))
            .andExpect(status().isBadRequest());

        // Validate the TCCONCEPTO in the database
        List<TCCONCEPTO> tCCONCEPTOList = tCCONCEPTORepository.findAll();
        assertThat(tCCONCEPTOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkConceptoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCONCEPTORepository.findAll().size();
        // set the field null
        tCCONCEPTO.setConcepto(null);

        // Create the TCCONCEPTO, which fails.

        restTCCONCEPTOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCONCEPTO)))
            .andExpect(status().isBadRequest());

        List<TCCONCEPTO> tCCONCEPTOList = tCCONCEPTORepository.findAll();
        assertThat(tCCONCEPTOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDatoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCONCEPTORepository.findAll().size();
        // set the field null
        tCCONCEPTO.setDato(null);

        // Create the TCCONCEPTO, which fails.

        restTCCONCEPTOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCONCEPTO)))
            .andExpect(status().isBadRequest());

        List<TCCONCEPTO> tCCONCEPTOList = tCCONCEPTORepository.findAll();
        assertThat(tCCONCEPTOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCCONCEPTOS() throws Exception {
        // Initialize the database
        tCCONCEPTORepository.saveAndFlush(tCCONCEPTO);

        // Get all the tCCONCEPTOList
        restTCCONCEPTOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idConcepto,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idConcepto").value(hasItem(tCCONCEPTO.getIdConcepto().intValue())))
            .andExpect(jsonPath("$.[*].concepto").value(hasItem(DEFAULT_CONCEPTO)))
            .andExpect(jsonPath("$.[*].dato").value(hasItem(DEFAULT_DATO.intValue())));
    }

    @Test
    @Transactional
    void getTCCONCEPTO() throws Exception {
        // Initialize the database
        tCCONCEPTORepository.saveAndFlush(tCCONCEPTO);

        // Get the tCCONCEPTO
        restTCCONCEPTOMockMvc
            .perform(get(ENTITY_API_URL_ID, tCCONCEPTO.getIdConcepto()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idConcepto").value(tCCONCEPTO.getIdConcepto().intValue()))
            .andExpect(jsonPath("$.concepto").value(DEFAULT_CONCEPTO))
            .andExpect(jsonPath("$.dato").value(DEFAULT_DATO.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCCONCEPTO() throws Exception {
        // Get the tCCONCEPTO
        restTCCONCEPTOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCCONCEPTO() throws Exception {
        // Initialize the database
        tCCONCEPTORepository.saveAndFlush(tCCONCEPTO);

        int databaseSizeBeforeUpdate = tCCONCEPTORepository.findAll().size();

        // Update the tCCONCEPTO
        TCCONCEPTO updatedTCCONCEPTO = tCCONCEPTORepository.findById(tCCONCEPTO.getIdConcepto()).get();
        // Disconnect from session so that the updates on updatedTCCONCEPTO are not directly saved in db
        em.detach(updatedTCCONCEPTO);
        updatedTCCONCEPTO.concepto(UPDATED_CONCEPTO).dato(UPDATED_DATO);

        restTCCONCEPTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCCONCEPTO.getIdConcepto())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCCONCEPTO))
            )
            .andExpect(status().isOk());

        // Validate the TCCONCEPTO in the database
        List<TCCONCEPTO> tCCONCEPTOList = tCCONCEPTORepository.findAll();
        assertThat(tCCONCEPTOList).hasSize(databaseSizeBeforeUpdate);
        TCCONCEPTO testTCCONCEPTO = tCCONCEPTOList.get(tCCONCEPTOList.size() - 1);
        assertThat(testTCCONCEPTO.getConcepto()).isEqualTo(UPDATED_CONCEPTO);
        assertThat(testTCCONCEPTO.getDato()).isEqualTo(UPDATED_DATO);
    }

    @Test
    @Transactional
    void putNonExistingTCCONCEPTO() throws Exception {
        int databaseSizeBeforeUpdate = tCCONCEPTORepository.findAll().size();
        tCCONCEPTO.setIdConcepto(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCONCEPTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCCONCEPTO.getIdConcepto())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCONCEPTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCONCEPTO in the database
        List<TCCONCEPTO> tCCONCEPTOList = tCCONCEPTORepository.findAll();
        assertThat(tCCONCEPTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCCONCEPTO() throws Exception {
        int databaseSizeBeforeUpdate = tCCONCEPTORepository.findAll().size();
        tCCONCEPTO.setIdConcepto(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCONCEPTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCONCEPTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCONCEPTO in the database
        List<TCCONCEPTO> tCCONCEPTOList = tCCONCEPTORepository.findAll();
        assertThat(tCCONCEPTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCCONCEPTO() throws Exception {
        int databaseSizeBeforeUpdate = tCCONCEPTORepository.findAll().size();
        tCCONCEPTO.setIdConcepto(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCONCEPTOMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCONCEPTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCONCEPTO in the database
        List<TCCONCEPTO> tCCONCEPTOList = tCCONCEPTORepository.findAll();
        assertThat(tCCONCEPTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCCONCEPTOWithPatch() throws Exception {
        // Initialize the database
        tCCONCEPTORepository.saveAndFlush(tCCONCEPTO);

        int databaseSizeBeforeUpdate = tCCONCEPTORepository.findAll().size();

        // Update the tCCONCEPTO using partial update
        TCCONCEPTO partialUpdatedTCCONCEPTO = new TCCONCEPTO();
        partialUpdatedTCCONCEPTO.setIdConcepto(tCCONCEPTO.getIdConcepto());

        partialUpdatedTCCONCEPTO.concepto(UPDATED_CONCEPTO);

        restTCCONCEPTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCONCEPTO.getIdConcepto())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCONCEPTO))
            )
            .andExpect(status().isOk());

        // Validate the TCCONCEPTO in the database
        List<TCCONCEPTO> tCCONCEPTOList = tCCONCEPTORepository.findAll();
        assertThat(tCCONCEPTOList).hasSize(databaseSizeBeforeUpdate);
        TCCONCEPTO testTCCONCEPTO = tCCONCEPTOList.get(tCCONCEPTOList.size() - 1);
        assertThat(testTCCONCEPTO.getConcepto()).isEqualTo(UPDATED_CONCEPTO);
        assertThat(testTCCONCEPTO.getDato()).isEqualTo(DEFAULT_DATO);
    }

    @Test
    @Transactional
    void fullUpdateTCCONCEPTOWithPatch() throws Exception {
        // Initialize the database
        tCCONCEPTORepository.saveAndFlush(tCCONCEPTO);

        int databaseSizeBeforeUpdate = tCCONCEPTORepository.findAll().size();

        // Update the tCCONCEPTO using partial update
        TCCONCEPTO partialUpdatedTCCONCEPTO = new TCCONCEPTO();
        partialUpdatedTCCONCEPTO.setIdConcepto(tCCONCEPTO.getIdConcepto());

        partialUpdatedTCCONCEPTO.concepto(UPDATED_CONCEPTO).dato(UPDATED_DATO);

        restTCCONCEPTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCONCEPTO.getIdConcepto())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCONCEPTO))
            )
            .andExpect(status().isOk());

        // Validate the TCCONCEPTO in the database
        List<TCCONCEPTO> tCCONCEPTOList = tCCONCEPTORepository.findAll();
        assertThat(tCCONCEPTOList).hasSize(databaseSizeBeforeUpdate);
        TCCONCEPTO testTCCONCEPTO = tCCONCEPTOList.get(tCCONCEPTOList.size() - 1);
        assertThat(testTCCONCEPTO.getConcepto()).isEqualTo(UPDATED_CONCEPTO);
        assertThat(testTCCONCEPTO.getDato()).isEqualTo(UPDATED_DATO);
    }

    @Test
    @Transactional
    void patchNonExistingTCCONCEPTO() throws Exception {
        int databaseSizeBeforeUpdate = tCCONCEPTORepository.findAll().size();
        tCCONCEPTO.setIdConcepto(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCONCEPTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCCONCEPTO.getIdConcepto())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCONCEPTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCONCEPTO in the database
        List<TCCONCEPTO> tCCONCEPTOList = tCCONCEPTORepository.findAll();
        assertThat(tCCONCEPTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCCONCEPTO() throws Exception {
        int databaseSizeBeforeUpdate = tCCONCEPTORepository.findAll().size();
        tCCONCEPTO.setIdConcepto(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCONCEPTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCONCEPTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCONCEPTO in the database
        List<TCCONCEPTO> tCCONCEPTOList = tCCONCEPTORepository.findAll();
        assertThat(tCCONCEPTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCCONCEPTO() throws Exception {
        int databaseSizeBeforeUpdate = tCCONCEPTORepository.findAll().size();
        tCCONCEPTO.setIdConcepto(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCONCEPTOMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCCONCEPTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCONCEPTO in the database
        List<TCCONCEPTO> tCCONCEPTOList = tCCONCEPTORepository.findAll();
        assertThat(tCCONCEPTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCCONCEPTO() throws Exception {
        // Initialize the database
        tCCONCEPTORepository.saveAndFlush(tCCONCEPTO);

        int databaseSizeBeforeDelete = tCCONCEPTORepository.findAll().size();

        // Delete the tCCONCEPTO
        restTCCONCEPTOMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCCONCEPTO.getIdConcepto()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCCONCEPTO> tCCONCEPTOList = tCCONCEPTORepository.findAll();
        assertThat(tCCONCEPTOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
