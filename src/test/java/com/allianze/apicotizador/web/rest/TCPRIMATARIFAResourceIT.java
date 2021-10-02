package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCPRIMATARIFA;
import com.allianze.apicotizador.repository.TCPRIMATARIFARepository;
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
 * Integration tests for the {@link TCPRIMATARIFAResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCPRIMATARIFAResourceIT {

    private static final Long DEFAULT_DIV_PRIMA_TARIFA = 1L;
    private static final Long UPDATED_DIV_PRIMA_TARIFA = 2L;

    private static final Long DEFAULT_Z_NETA = 1L;
    private static final Long UPDATED_Z_NETA = 2L;

    private static final Long DEFAULT_DIV_PRIMA_RIESGO = 1L;
    private static final Long UPDATED_DIV_PRIMA_RIESGO = 2L;

    private static final Long DEFAULT_Z_RIESGO = 1L;
    private static final Long UPDATED_Z_RIESGO = 2L;

    private static final String ENTITY_API_URL = "/api/tcprimatarifas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idPrimaTarifa}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCPRIMATARIFARepository tCPRIMATARIFARepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCPRIMATARIFAMockMvc;

    private TCPRIMATARIFA tCPRIMATARIFA;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCPRIMATARIFA createEntity(EntityManager em) {
        TCPRIMATARIFA tCPRIMATARIFA = new TCPRIMATARIFA()
            .divPrimaTarifa(DEFAULT_DIV_PRIMA_TARIFA)
            .zNeta(DEFAULT_Z_NETA)
            .divPrimaRiesgo(DEFAULT_DIV_PRIMA_RIESGO)
            .zRiesgo(DEFAULT_Z_RIESGO);
        return tCPRIMATARIFA;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCPRIMATARIFA createUpdatedEntity(EntityManager em) {
        TCPRIMATARIFA tCPRIMATARIFA = new TCPRIMATARIFA()
            .divPrimaTarifa(UPDATED_DIV_PRIMA_TARIFA)
            .zNeta(UPDATED_Z_NETA)
            .divPrimaRiesgo(UPDATED_DIV_PRIMA_RIESGO)
            .zRiesgo(UPDATED_Z_RIESGO);
        return tCPRIMATARIFA;
    }

    @BeforeEach
    public void initTest() {
        tCPRIMATARIFA = createEntity(em);
    }

    @Test
    @Transactional
    void createTCPRIMATARIFA() throws Exception {
        int databaseSizeBeforeCreate = tCPRIMATARIFARepository.findAll().size();
        // Create the TCPRIMATARIFA
        restTCPRIMATARIFAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPRIMATARIFA)))
            .andExpect(status().isCreated());

        // Validate the TCPRIMATARIFA in the database
        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeCreate + 1);
        TCPRIMATARIFA testTCPRIMATARIFA = tCPRIMATARIFAList.get(tCPRIMATARIFAList.size() - 1);
        assertThat(testTCPRIMATARIFA.getDivPrimaTarifa()).isEqualTo(DEFAULT_DIV_PRIMA_TARIFA);
        assertThat(testTCPRIMATARIFA.getzNeta()).isEqualTo(DEFAULT_Z_NETA);
        assertThat(testTCPRIMATARIFA.getDivPrimaRiesgo()).isEqualTo(DEFAULT_DIV_PRIMA_RIESGO);
        assertThat(testTCPRIMATARIFA.getzRiesgo()).isEqualTo(DEFAULT_Z_RIESGO);
    }

    @Test
    @Transactional
    void createTCPRIMATARIFAWithExistingId() throws Exception {
        // Create the TCPRIMATARIFA with an existing ID
        tCPRIMATARIFA.setIdPrimaTarifa(1L);

        int databaseSizeBeforeCreate = tCPRIMATARIFARepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCPRIMATARIFAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPRIMATARIFA)))
            .andExpect(status().isBadRequest());

        // Validate the TCPRIMATARIFA in the database
        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDivPrimaTarifaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCPRIMATARIFARepository.findAll().size();
        // set the field null
        tCPRIMATARIFA.setDivPrimaTarifa(null);

        // Create the TCPRIMATARIFA, which fails.

        restTCPRIMATARIFAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPRIMATARIFA)))
            .andExpect(status().isBadRequest());

        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkzNetaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCPRIMATARIFARepository.findAll().size();
        // set the field null
        tCPRIMATARIFA.setzNeta(null);

        // Create the TCPRIMATARIFA, which fails.

        restTCPRIMATARIFAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPRIMATARIFA)))
            .andExpect(status().isBadRequest());

        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDivPrimaRiesgoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCPRIMATARIFARepository.findAll().size();
        // set the field null
        tCPRIMATARIFA.setDivPrimaRiesgo(null);

        // Create the TCPRIMATARIFA, which fails.

        restTCPRIMATARIFAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPRIMATARIFA)))
            .andExpect(status().isBadRequest());

        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkzRiesgoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCPRIMATARIFARepository.findAll().size();
        // set the field null
        tCPRIMATARIFA.setzRiesgo(null);

        // Create the TCPRIMATARIFA, which fails.

        restTCPRIMATARIFAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPRIMATARIFA)))
            .andExpect(status().isBadRequest());

        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCPRIMATARIFAS() throws Exception {
        // Initialize the database
        tCPRIMATARIFARepository.saveAndFlush(tCPRIMATARIFA);

        // Get all the tCPRIMATARIFAList
        restTCPRIMATARIFAMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idPrimaTarifa,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idPrimaTarifa").value(hasItem(tCPRIMATARIFA.getIdPrimaTarifa().intValue())))
            .andExpect(jsonPath("$.[*].divPrimaTarifa").value(hasItem(DEFAULT_DIV_PRIMA_TARIFA.intValue())))
            .andExpect(jsonPath("$.[*].zNeta").value(hasItem(DEFAULT_Z_NETA.intValue())))
            .andExpect(jsonPath("$.[*].divPrimaRiesgo").value(hasItem(DEFAULT_DIV_PRIMA_RIESGO.intValue())))
            .andExpect(jsonPath("$.[*].zRiesgo").value(hasItem(DEFAULT_Z_RIESGO.intValue())));
    }

    @Test
    @Transactional
    void getTCPRIMATARIFA() throws Exception {
        // Initialize the database
        tCPRIMATARIFARepository.saveAndFlush(tCPRIMATARIFA);

        // Get the tCPRIMATARIFA
        restTCPRIMATARIFAMockMvc
            .perform(get(ENTITY_API_URL_ID, tCPRIMATARIFA.getIdPrimaTarifa()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idPrimaTarifa").value(tCPRIMATARIFA.getIdPrimaTarifa().intValue()))
            .andExpect(jsonPath("$.divPrimaTarifa").value(DEFAULT_DIV_PRIMA_TARIFA.intValue()))
            .andExpect(jsonPath("$.zNeta").value(DEFAULT_Z_NETA.intValue()))
            .andExpect(jsonPath("$.divPrimaRiesgo").value(DEFAULT_DIV_PRIMA_RIESGO.intValue()))
            .andExpect(jsonPath("$.zRiesgo").value(DEFAULT_Z_RIESGO.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCPRIMATARIFA() throws Exception {
        // Get the tCPRIMATARIFA
        restTCPRIMATARIFAMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCPRIMATARIFA() throws Exception {
        // Initialize the database
        tCPRIMATARIFARepository.saveAndFlush(tCPRIMATARIFA);

        int databaseSizeBeforeUpdate = tCPRIMATARIFARepository.findAll().size();

        // Update the tCPRIMATARIFA
        TCPRIMATARIFA updatedTCPRIMATARIFA = tCPRIMATARIFARepository.findById(tCPRIMATARIFA.getIdPrimaTarifa()).get();
        // Disconnect from session so that the updates on updatedTCPRIMATARIFA are not directly saved in db
        em.detach(updatedTCPRIMATARIFA);
        updatedTCPRIMATARIFA
            .divPrimaTarifa(UPDATED_DIV_PRIMA_TARIFA)
            .zNeta(UPDATED_Z_NETA)
            .divPrimaRiesgo(UPDATED_DIV_PRIMA_RIESGO)
            .zRiesgo(UPDATED_Z_RIESGO);

        restTCPRIMATARIFAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCPRIMATARIFA.getIdPrimaTarifa())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCPRIMATARIFA))
            )
            .andExpect(status().isOk());

        // Validate the TCPRIMATARIFA in the database
        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeUpdate);
        TCPRIMATARIFA testTCPRIMATARIFA = tCPRIMATARIFAList.get(tCPRIMATARIFAList.size() - 1);
        assertThat(testTCPRIMATARIFA.getDivPrimaTarifa()).isEqualTo(UPDATED_DIV_PRIMA_TARIFA);
        assertThat(testTCPRIMATARIFA.getzNeta()).isEqualTo(UPDATED_Z_NETA);
        assertThat(testTCPRIMATARIFA.getDivPrimaRiesgo()).isEqualTo(UPDATED_DIV_PRIMA_RIESGO);
        assertThat(testTCPRIMATARIFA.getzRiesgo()).isEqualTo(UPDATED_Z_RIESGO);
    }

    @Test
    @Transactional
    void putNonExistingTCPRIMATARIFA() throws Exception {
        int databaseSizeBeforeUpdate = tCPRIMATARIFARepository.findAll().size();
        tCPRIMATARIFA.setIdPrimaTarifa(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCPRIMATARIFAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCPRIMATARIFA.getIdPrimaTarifa())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCPRIMATARIFA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCPRIMATARIFA in the database
        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCPRIMATARIFA() throws Exception {
        int databaseSizeBeforeUpdate = tCPRIMATARIFARepository.findAll().size();
        tCPRIMATARIFA.setIdPrimaTarifa(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCPRIMATARIFAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCPRIMATARIFA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCPRIMATARIFA in the database
        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCPRIMATARIFA() throws Exception {
        int databaseSizeBeforeUpdate = tCPRIMATARIFARepository.findAll().size();
        tCPRIMATARIFA.setIdPrimaTarifa(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCPRIMATARIFAMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPRIMATARIFA)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCPRIMATARIFA in the database
        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCPRIMATARIFAWithPatch() throws Exception {
        // Initialize the database
        tCPRIMATARIFARepository.saveAndFlush(tCPRIMATARIFA);

        int databaseSizeBeforeUpdate = tCPRIMATARIFARepository.findAll().size();

        // Update the tCPRIMATARIFA using partial update
        TCPRIMATARIFA partialUpdatedTCPRIMATARIFA = new TCPRIMATARIFA();
        partialUpdatedTCPRIMATARIFA.setIdPrimaTarifa(tCPRIMATARIFA.getIdPrimaTarifa());

        partialUpdatedTCPRIMATARIFA.divPrimaRiesgo(UPDATED_DIV_PRIMA_RIESGO).zRiesgo(UPDATED_Z_RIESGO);

        restTCPRIMATARIFAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCPRIMATARIFA.getIdPrimaTarifa())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCPRIMATARIFA))
            )
            .andExpect(status().isOk());

        // Validate the TCPRIMATARIFA in the database
        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeUpdate);
        TCPRIMATARIFA testTCPRIMATARIFA = tCPRIMATARIFAList.get(tCPRIMATARIFAList.size() - 1);
        assertThat(testTCPRIMATARIFA.getDivPrimaTarifa()).isEqualTo(DEFAULT_DIV_PRIMA_TARIFA);
        assertThat(testTCPRIMATARIFA.getzNeta()).isEqualTo(DEFAULT_Z_NETA);
        assertThat(testTCPRIMATARIFA.getDivPrimaRiesgo()).isEqualTo(UPDATED_DIV_PRIMA_RIESGO);
        assertThat(testTCPRIMATARIFA.getzRiesgo()).isEqualTo(UPDATED_Z_RIESGO);
    }

    @Test
    @Transactional
    void fullUpdateTCPRIMATARIFAWithPatch() throws Exception {
        // Initialize the database
        tCPRIMATARIFARepository.saveAndFlush(tCPRIMATARIFA);

        int databaseSizeBeforeUpdate = tCPRIMATARIFARepository.findAll().size();

        // Update the tCPRIMATARIFA using partial update
        TCPRIMATARIFA partialUpdatedTCPRIMATARIFA = new TCPRIMATARIFA();
        partialUpdatedTCPRIMATARIFA.setIdPrimaTarifa(tCPRIMATARIFA.getIdPrimaTarifa());

        partialUpdatedTCPRIMATARIFA
            .divPrimaTarifa(UPDATED_DIV_PRIMA_TARIFA)
            .zNeta(UPDATED_Z_NETA)
            .divPrimaRiesgo(UPDATED_DIV_PRIMA_RIESGO)
            .zRiesgo(UPDATED_Z_RIESGO);

        restTCPRIMATARIFAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCPRIMATARIFA.getIdPrimaTarifa())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCPRIMATARIFA))
            )
            .andExpect(status().isOk());

        // Validate the TCPRIMATARIFA in the database
        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeUpdate);
        TCPRIMATARIFA testTCPRIMATARIFA = tCPRIMATARIFAList.get(tCPRIMATARIFAList.size() - 1);
        assertThat(testTCPRIMATARIFA.getDivPrimaTarifa()).isEqualTo(UPDATED_DIV_PRIMA_TARIFA);
        assertThat(testTCPRIMATARIFA.getzNeta()).isEqualTo(UPDATED_Z_NETA);
        assertThat(testTCPRIMATARIFA.getDivPrimaRiesgo()).isEqualTo(UPDATED_DIV_PRIMA_RIESGO);
        assertThat(testTCPRIMATARIFA.getzRiesgo()).isEqualTo(UPDATED_Z_RIESGO);
    }

    @Test
    @Transactional
    void patchNonExistingTCPRIMATARIFA() throws Exception {
        int databaseSizeBeforeUpdate = tCPRIMATARIFARepository.findAll().size();
        tCPRIMATARIFA.setIdPrimaTarifa(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCPRIMATARIFAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCPRIMATARIFA.getIdPrimaTarifa())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCPRIMATARIFA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCPRIMATARIFA in the database
        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCPRIMATARIFA() throws Exception {
        int databaseSizeBeforeUpdate = tCPRIMATARIFARepository.findAll().size();
        tCPRIMATARIFA.setIdPrimaTarifa(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCPRIMATARIFAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCPRIMATARIFA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCPRIMATARIFA in the database
        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCPRIMATARIFA() throws Exception {
        int databaseSizeBeforeUpdate = tCPRIMATARIFARepository.findAll().size();
        tCPRIMATARIFA.setIdPrimaTarifa(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCPRIMATARIFAMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCPRIMATARIFA))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCPRIMATARIFA in the database
        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCPRIMATARIFA() throws Exception {
        // Initialize the database
        tCPRIMATARIFARepository.saveAndFlush(tCPRIMATARIFA);

        int databaseSizeBeforeDelete = tCPRIMATARIFARepository.findAll().size();

        // Delete the tCPRIMATARIFA
        restTCPRIMATARIFAMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCPRIMATARIFA.getIdPrimaTarifa()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCPRIMATARIFA> tCPRIMATARIFAList = tCPRIMATARIFARepository.findAll();
        assertThat(tCPRIMATARIFAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
