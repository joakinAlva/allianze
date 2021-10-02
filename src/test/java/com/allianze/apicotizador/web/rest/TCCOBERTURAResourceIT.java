package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCCOBERTURA;
import com.allianze.apicotizador.repository.TCCOBERTURARepository;
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
 * Integration tests for the {@link TCCOBERTURAResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCCOBERTURAResourceIT {

    private static final String DEFAULT_COBERTURA = "AAAAAAAAAA";
    private static final String UPDATED_COBERTURA = "BBBBBBBBBB";

    private static final Long DEFAULT_POSICION = 1L;
    private static final Long UPDATED_POSICION = 2L;

    private static final String ENTITY_API_URL = "/api/tccoberturas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCobertura}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCCOBERTURARepository tCCOBERTURARepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCCOBERTURAMockMvc;

    private TCCOBERTURA tCCOBERTURA;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCOBERTURA createEntity(EntityManager em) {
        TCCOBERTURA tCCOBERTURA = new TCCOBERTURA().cobertura(DEFAULT_COBERTURA).posicion(DEFAULT_POSICION);
        return tCCOBERTURA;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCOBERTURA createUpdatedEntity(EntityManager em) {
        TCCOBERTURA tCCOBERTURA = new TCCOBERTURA().cobertura(UPDATED_COBERTURA).posicion(UPDATED_POSICION);
        return tCCOBERTURA;
    }

    @BeforeEach
    public void initTest() {
        tCCOBERTURA = createEntity(em);
    }

    @Test
    @Transactional
    void createTCCOBERTURA() throws Exception {
        int databaseSizeBeforeCreate = tCCOBERTURARepository.findAll().size();
        // Create the TCCOBERTURA
        restTCCOBERTURAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOBERTURA)))
            .andExpect(status().isCreated());

        // Validate the TCCOBERTURA in the database
        List<TCCOBERTURA> tCCOBERTURAList = tCCOBERTURARepository.findAll();
        assertThat(tCCOBERTURAList).hasSize(databaseSizeBeforeCreate + 1);
        TCCOBERTURA testTCCOBERTURA = tCCOBERTURAList.get(tCCOBERTURAList.size() - 1);
        assertThat(testTCCOBERTURA.getCobertura()).isEqualTo(DEFAULT_COBERTURA);
        assertThat(testTCCOBERTURA.getPosicion()).isEqualTo(DEFAULT_POSICION);
    }

    @Test
    @Transactional
    void createTCCOBERTURAWithExistingId() throws Exception {
        // Create the TCCOBERTURA with an existing ID
        tCCOBERTURA.setIdCobertura(1L);

        int databaseSizeBeforeCreate = tCCOBERTURARepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCCOBERTURAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOBERTURA)))
            .andExpect(status().isBadRequest());

        // Validate the TCCOBERTURA in the database
        List<TCCOBERTURA> tCCOBERTURAList = tCCOBERTURARepository.findAll();
        assertThat(tCCOBERTURAList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCoberturaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOBERTURARepository.findAll().size();
        // set the field null
        tCCOBERTURA.setCobertura(null);

        // Create the TCCOBERTURA, which fails.

        restTCCOBERTURAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOBERTURA)))
            .andExpect(status().isBadRequest());

        List<TCCOBERTURA> tCCOBERTURAList = tCCOBERTURARepository.findAll();
        assertThat(tCCOBERTURAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPosicionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOBERTURARepository.findAll().size();
        // set the field null
        tCCOBERTURA.setPosicion(null);

        // Create the TCCOBERTURA, which fails.

        restTCCOBERTURAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOBERTURA)))
            .andExpect(status().isBadRequest());

        List<TCCOBERTURA> tCCOBERTURAList = tCCOBERTURARepository.findAll();
        assertThat(tCCOBERTURAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCCOBERTURAS() throws Exception {
        // Initialize the database
        tCCOBERTURARepository.saveAndFlush(tCCOBERTURA);

        // Get all the tCCOBERTURAList
        restTCCOBERTURAMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCobertura,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCobertura").value(hasItem(tCCOBERTURA.getIdCobertura().intValue())))
            .andExpect(jsonPath("$.[*].cobertura").value(hasItem(DEFAULT_COBERTURA)))
            .andExpect(jsonPath("$.[*].posicion").value(hasItem(DEFAULT_POSICION.intValue())));
    }

    @Test
    @Transactional
    void getTCCOBERTURA() throws Exception {
        // Initialize the database
        tCCOBERTURARepository.saveAndFlush(tCCOBERTURA);

        // Get the tCCOBERTURA
        restTCCOBERTURAMockMvc
            .perform(get(ENTITY_API_URL_ID, tCCOBERTURA.getIdCobertura()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCobertura").value(tCCOBERTURA.getIdCobertura().intValue()))
            .andExpect(jsonPath("$.cobertura").value(DEFAULT_COBERTURA))
            .andExpect(jsonPath("$.posicion").value(DEFAULT_POSICION.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCCOBERTURA() throws Exception {
        // Get the tCCOBERTURA
        restTCCOBERTURAMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCCOBERTURA() throws Exception {
        // Initialize the database
        tCCOBERTURARepository.saveAndFlush(tCCOBERTURA);

        int databaseSizeBeforeUpdate = tCCOBERTURARepository.findAll().size();

        // Update the tCCOBERTURA
        TCCOBERTURA updatedTCCOBERTURA = tCCOBERTURARepository.findById(tCCOBERTURA.getIdCobertura()).get();
        // Disconnect from session so that the updates on updatedTCCOBERTURA are not directly saved in db
        em.detach(updatedTCCOBERTURA);
        updatedTCCOBERTURA.cobertura(UPDATED_COBERTURA).posicion(UPDATED_POSICION);

        restTCCOBERTURAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCCOBERTURA.getIdCobertura())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCCOBERTURA))
            )
            .andExpect(status().isOk());

        // Validate the TCCOBERTURA in the database
        List<TCCOBERTURA> tCCOBERTURAList = tCCOBERTURARepository.findAll();
        assertThat(tCCOBERTURAList).hasSize(databaseSizeBeforeUpdate);
        TCCOBERTURA testTCCOBERTURA = tCCOBERTURAList.get(tCCOBERTURAList.size() - 1);
        assertThat(testTCCOBERTURA.getCobertura()).isEqualTo(UPDATED_COBERTURA);
        assertThat(testTCCOBERTURA.getPosicion()).isEqualTo(UPDATED_POSICION);
    }

    @Test
    @Transactional
    void putNonExistingTCCOBERTURA() throws Exception {
        int databaseSizeBeforeUpdate = tCCOBERTURARepository.findAll().size();
        tCCOBERTURA.setIdCobertura(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCOBERTURAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCCOBERTURA.getIdCobertura())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCOBERTURA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOBERTURA in the database
        List<TCCOBERTURA> tCCOBERTURAList = tCCOBERTURARepository.findAll();
        assertThat(tCCOBERTURAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCCOBERTURA() throws Exception {
        int databaseSizeBeforeUpdate = tCCOBERTURARepository.findAll().size();
        tCCOBERTURA.setIdCobertura(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOBERTURAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCOBERTURA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOBERTURA in the database
        List<TCCOBERTURA> tCCOBERTURAList = tCCOBERTURARepository.findAll();
        assertThat(tCCOBERTURAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCCOBERTURA() throws Exception {
        int databaseSizeBeforeUpdate = tCCOBERTURARepository.findAll().size();
        tCCOBERTURA.setIdCobertura(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOBERTURAMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOBERTURA)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCOBERTURA in the database
        List<TCCOBERTURA> tCCOBERTURAList = tCCOBERTURARepository.findAll();
        assertThat(tCCOBERTURAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCCOBERTURAWithPatch() throws Exception {
        // Initialize the database
        tCCOBERTURARepository.saveAndFlush(tCCOBERTURA);

        int databaseSizeBeforeUpdate = tCCOBERTURARepository.findAll().size();

        // Update the tCCOBERTURA using partial update
        TCCOBERTURA partialUpdatedTCCOBERTURA = new TCCOBERTURA();
        partialUpdatedTCCOBERTURA.setIdCobertura(tCCOBERTURA.getIdCobertura());

        restTCCOBERTURAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCOBERTURA.getIdCobertura())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCOBERTURA))
            )
            .andExpect(status().isOk());

        // Validate the TCCOBERTURA in the database
        List<TCCOBERTURA> tCCOBERTURAList = tCCOBERTURARepository.findAll();
        assertThat(tCCOBERTURAList).hasSize(databaseSizeBeforeUpdate);
        TCCOBERTURA testTCCOBERTURA = tCCOBERTURAList.get(tCCOBERTURAList.size() - 1);
        assertThat(testTCCOBERTURA.getCobertura()).isEqualTo(DEFAULT_COBERTURA);
        assertThat(testTCCOBERTURA.getPosicion()).isEqualTo(DEFAULT_POSICION);
    }

    @Test
    @Transactional
    void fullUpdateTCCOBERTURAWithPatch() throws Exception {
        // Initialize the database
        tCCOBERTURARepository.saveAndFlush(tCCOBERTURA);

        int databaseSizeBeforeUpdate = tCCOBERTURARepository.findAll().size();

        // Update the tCCOBERTURA using partial update
        TCCOBERTURA partialUpdatedTCCOBERTURA = new TCCOBERTURA();
        partialUpdatedTCCOBERTURA.setIdCobertura(tCCOBERTURA.getIdCobertura());

        partialUpdatedTCCOBERTURA.cobertura(UPDATED_COBERTURA).posicion(UPDATED_POSICION);

        restTCCOBERTURAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCOBERTURA.getIdCobertura())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCOBERTURA))
            )
            .andExpect(status().isOk());

        // Validate the TCCOBERTURA in the database
        List<TCCOBERTURA> tCCOBERTURAList = tCCOBERTURARepository.findAll();
        assertThat(tCCOBERTURAList).hasSize(databaseSizeBeforeUpdate);
        TCCOBERTURA testTCCOBERTURA = tCCOBERTURAList.get(tCCOBERTURAList.size() - 1);
        assertThat(testTCCOBERTURA.getCobertura()).isEqualTo(UPDATED_COBERTURA);
        assertThat(testTCCOBERTURA.getPosicion()).isEqualTo(UPDATED_POSICION);
    }

    @Test
    @Transactional
    void patchNonExistingTCCOBERTURA() throws Exception {
        int databaseSizeBeforeUpdate = tCCOBERTURARepository.findAll().size();
        tCCOBERTURA.setIdCobertura(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCOBERTURAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCCOBERTURA.getIdCobertura())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCOBERTURA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOBERTURA in the database
        List<TCCOBERTURA> tCCOBERTURAList = tCCOBERTURARepository.findAll();
        assertThat(tCCOBERTURAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCCOBERTURA() throws Exception {
        int databaseSizeBeforeUpdate = tCCOBERTURARepository.findAll().size();
        tCCOBERTURA.setIdCobertura(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOBERTURAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCOBERTURA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOBERTURA in the database
        List<TCCOBERTURA> tCCOBERTURAList = tCCOBERTURARepository.findAll();
        assertThat(tCCOBERTURAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCCOBERTURA() throws Exception {
        int databaseSizeBeforeUpdate = tCCOBERTURARepository.findAll().size();
        tCCOBERTURA.setIdCobertura(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOBERTURAMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCCOBERTURA))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCOBERTURA in the database
        List<TCCOBERTURA> tCCOBERTURAList = tCCOBERTURARepository.findAll();
        assertThat(tCCOBERTURAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCCOBERTURA() throws Exception {
        // Initialize the database
        tCCOBERTURARepository.saveAndFlush(tCCOBERTURA);

        int databaseSizeBeforeDelete = tCCOBERTURARepository.findAll().size();

        // Delete the tCCOBERTURA
        restTCCOBERTURAMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCCOBERTURA.getIdCobertura()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCCOBERTURA> tCCOBERTURAList = tCCOBERTURARepository.findAll();
        assertThat(tCCOBERTURAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
