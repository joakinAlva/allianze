package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCOCUPACION;
import com.allianze.apicotizador.repository.TCOCUPACIONRepository;
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
 * Integration tests for the {@link TCOCUPACIONResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCOCUPACIONResourceIT {

    private static final String DEFAULT_OCUPACION = "AAAAAAAAAA";
    private static final String UPDATED_OCUPACION = "BBBBBBBBBB";

    private static final Long DEFAULT_FACTOR_GIRO_ANTERIOR = 1L;
    private static final Long UPDATED_FACTOR_GIRO_ANTERIOR = 2L;

    private static final Long DEFAULT_FACTOR_GIRO_ACTUAL = 1L;
    private static final Long UPDATED_FACTOR_GIRO_ACTUAL = 2L;

    private static final String ENTITY_API_URL = "/api/tcocupacions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idOcupacion}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCOCUPACIONRepository tCOCUPACIONRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCOCUPACIONMockMvc;

    private TCOCUPACION tCOCUPACION;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCOCUPACION createEntity(EntityManager em) {
        TCOCUPACION tCOCUPACION = new TCOCUPACION()
            .ocupacion(DEFAULT_OCUPACION)
            .factorGiroAnterior(DEFAULT_FACTOR_GIRO_ANTERIOR)
            .factorGiroActual(DEFAULT_FACTOR_GIRO_ACTUAL);
        return tCOCUPACION;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCOCUPACION createUpdatedEntity(EntityManager em) {
        TCOCUPACION tCOCUPACION = new TCOCUPACION()
            .ocupacion(UPDATED_OCUPACION)
            .factorGiroAnterior(UPDATED_FACTOR_GIRO_ANTERIOR)
            .factorGiroActual(UPDATED_FACTOR_GIRO_ACTUAL);
        return tCOCUPACION;
    }

    @BeforeEach
    public void initTest() {
        tCOCUPACION = createEntity(em);
    }

    @Test
    @Transactional
    void createTCOCUPACION() throws Exception {
        int databaseSizeBeforeCreate = tCOCUPACIONRepository.findAll().size();
        // Create the TCOCUPACION
        restTCOCUPACIONMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCOCUPACION)))
            .andExpect(status().isCreated());

        // Validate the TCOCUPACION in the database
        List<TCOCUPACION> tCOCUPACIONList = tCOCUPACIONRepository.findAll();
        assertThat(tCOCUPACIONList).hasSize(databaseSizeBeforeCreate + 1);
        TCOCUPACION testTCOCUPACION = tCOCUPACIONList.get(tCOCUPACIONList.size() - 1);
        assertThat(testTCOCUPACION.getOcupacion()).isEqualTo(DEFAULT_OCUPACION);
        assertThat(testTCOCUPACION.getFactorGiroAnterior()).isEqualTo(DEFAULT_FACTOR_GIRO_ANTERIOR);
        assertThat(testTCOCUPACION.getFactorGiroActual()).isEqualTo(DEFAULT_FACTOR_GIRO_ACTUAL);
    }

    @Test
    @Transactional
    void createTCOCUPACIONWithExistingId() throws Exception {
        // Create the TCOCUPACION with an existing ID
        tCOCUPACION.setIdOcupacion(1L);

        int databaseSizeBeforeCreate = tCOCUPACIONRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCOCUPACIONMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCOCUPACION)))
            .andExpect(status().isBadRequest());

        // Validate the TCOCUPACION in the database
        List<TCOCUPACION> tCOCUPACIONList = tCOCUPACIONRepository.findAll();
        assertThat(tCOCUPACIONList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkOcupacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCOCUPACIONRepository.findAll().size();
        // set the field null
        tCOCUPACION.setOcupacion(null);

        // Create the TCOCUPACION, which fails.

        restTCOCUPACIONMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCOCUPACION)))
            .andExpect(status().isBadRequest());

        List<TCOCUPACION> tCOCUPACIONList = tCOCUPACIONRepository.findAll();
        assertThat(tCOCUPACIONList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFactorGiroAnteriorIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCOCUPACIONRepository.findAll().size();
        // set the field null
        tCOCUPACION.setFactorGiroAnterior(null);

        // Create the TCOCUPACION, which fails.

        restTCOCUPACIONMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCOCUPACION)))
            .andExpect(status().isBadRequest());

        List<TCOCUPACION> tCOCUPACIONList = tCOCUPACIONRepository.findAll();
        assertThat(tCOCUPACIONList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFactorGiroActualIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCOCUPACIONRepository.findAll().size();
        // set the field null
        tCOCUPACION.setFactorGiroActual(null);

        // Create the TCOCUPACION, which fails.

        restTCOCUPACIONMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCOCUPACION)))
            .andExpect(status().isBadRequest());

        List<TCOCUPACION> tCOCUPACIONList = tCOCUPACIONRepository.findAll();
        assertThat(tCOCUPACIONList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCOCUPACIONS() throws Exception {
        // Initialize the database
        tCOCUPACIONRepository.saveAndFlush(tCOCUPACION);

        // Get all the tCOCUPACIONList
        restTCOCUPACIONMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idOcupacion,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idOcupacion").value(hasItem(tCOCUPACION.getIdOcupacion().intValue())))
            .andExpect(jsonPath("$.[*].ocupacion").value(hasItem(DEFAULT_OCUPACION)))
            .andExpect(jsonPath("$.[*].factorGiroAnterior").value(hasItem(DEFAULT_FACTOR_GIRO_ANTERIOR.intValue())))
            .andExpect(jsonPath("$.[*].factorGiroActual").value(hasItem(DEFAULT_FACTOR_GIRO_ACTUAL.intValue())));
    }

    @Test
    @Transactional
    void getTCOCUPACION() throws Exception {
        // Initialize the database
        tCOCUPACIONRepository.saveAndFlush(tCOCUPACION);

        // Get the tCOCUPACION
        restTCOCUPACIONMockMvc
            .perform(get(ENTITY_API_URL_ID, tCOCUPACION.getIdOcupacion()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idOcupacion").value(tCOCUPACION.getIdOcupacion().intValue()))
            .andExpect(jsonPath("$.ocupacion").value(DEFAULT_OCUPACION))
            .andExpect(jsonPath("$.factorGiroAnterior").value(DEFAULT_FACTOR_GIRO_ANTERIOR.intValue()))
            .andExpect(jsonPath("$.factorGiroActual").value(DEFAULT_FACTOR_GIRO_ACTUAL.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCOCUPACION() throws Exception {
        // Get the tCOCUPACION
        restTCOCUPACIONMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCOCUPACION() throws Exception {
        // Initialize the database
        tCOCUPACIONRepository.saveAndFlush(tCOCUPACION);

        int databaseSizeBeforeUpdate = tCOCUPACIONRepository.findAll().size();

        // Update the tCOCUPACION
        TCOCUPACION updatedTCOCUPACION = tCOCUPACIONRepository.findById(tCOCUPACION.getIdOcupacion()).get();
        // Disconnect from session so that the updates on updatedTCOCUPACION are not directly saved in db
        em.detach(updatedTCOCUPACION);
        updatedTCOCUPACION
            .ocupacion(UPDATED_OCUPACION)
            .factorGiroAnterior(UPDATED_FACTOR_GIRO_ANTERIOR)
            .factorGiroActual(UPDATED_FACTOR_GIRO_ACTUAL);

        restTCOCUPACIONMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCOCUPACION.getIdOcupacion())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCOCUPACION))
            )
            .andExpect(status().isOk());

        // Validate the TCOCUPACION in the database
        List<TCOCUPACION> tCOCUPACIONList = tCOCUPACIONRepository.findAll();
        assertThat(tCOCUPACIONList).hasSize(databaseSizeBeforeUpdate);
        TCOCUPACION testTCOCUPACION = tCOCUPACIONList.get(tCOCUPACIONList.size() - 1);
        assertThat(testTCOCUPACION.getOcupacion()).isEqualTo(UPDATED_OCUPACION);
        assertThat(testTCOCUPACION.getFactorGiroAnterior()).isEqualTo(UPDATED_FACTOR_GIRO_ANTERIOR);
        assertThat(testTCOCUPACION.getFactorGiroActual()).isEqualTo(UPDATED_FACTOR_GIRO_ACTUAL);
    }

    @Test
    @Transactional
    void putNonExistingTCOCUPACION() throws Exception {
        int databaseSizeBeforeUpdate = tCOCUPACIONRepository.findAll().size();
        tCOCUPACION.setIdOcupacion(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCOCUPACIONMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCOCUPACION.getIdOcupacion())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCOCUPACION))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCOCUPACION in the database
        List<TCOCUPACION> tCOCUPACIONList = tCOCUPACIONRepository.findAll();
        assertThat(tCOCUPACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCOCUPACION() throws Exception {
        int databaseSizeBeforeUpdate = tCOCUPACIONRepository.findAll().size();
        tCOCUPACION.setIdOcupacion(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCOCUPACIONMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCOCUPACION))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCOCUPACION in the database
        List<TCOCUPACION> tCOCUPACIONList = tCOCUPACIONRepository.findAll();
        assertThat(tCOCUPACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCOCUPACION() throws Exception {
        int databaseSizeBeforeUpdate = tCOCUPACIONRepository.findAll().size();
        tCOCUPACION.setIdOcupacion(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCOCUPACIONMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCOCUPACION)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCOCUPACION in the database
        List<TCOCUPACION> tCOCUPACIONList = tCOCUPACIONRepository.findAll();
        assertThat(tCOCUPACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCOCUPACIONWithPatch() throws Exception {
        // Initialize the database
        tCOCUPACIONRepository.saveAndFlush(tCOCUPACION);

        int databaseSizeBeforeUpdate = tCOCUPACIONRepository.findAll().size();

        // Update the tCOCUPACION using partial update
        TCOCUPACION partialUpdatedTCOCUPACION = new TCOCUPACION();
        partialUpdatedTCOCUPACION.setIdOcupacion(tCOCUPACION.getIdOcupacion());

        partialUpdatedTCOCUPACION.ocupacion(UPDATED_OCUPACION).factorGiroAnterior(UPDATED_FACTOR_GIRO_ANTERIOR);

        restTCOCUPACIONMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCOCUPACION.getIdOcupacion())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCOCUPACION))
            )
            .andExpect(status().isOk());

        // Validate the TCOCUPACION in the database
        List<TCOCUPACION> tCOCUPACIONList = tCOCUPACIONRepository.findAll();
        assertThat(tCOCUPACIONList).hasSize(databaseSizeBeforeUpdate);
        TCOCUPACION testTCOCUPACION = tCOCUPACIONList.get(tCOCUPACIONList.size() - 1);
        assertThat(testTCOCUPACION.getOcupacion()).isEqualTo(UPDATED_OCUPACION);
        assertThat(testTCOCUPACION.getFactorGiroAnterior()).isEqualTo(UPDATED_FACTOR_GIRO_ANTERIOR);
        assertThat(testTCOCUPACION.getFactorGiroActual()).isEqualTo(DEFAULT_FACTOR_GIRO_ACTUAL);
    }

    @Test
    @Transactional
    void fullUpdateTCOCUPACIONWithPatch() throws Exception {
        // Initialize the database
        tCOCUPACIONRepository.saveAndFlush(tCOCUPACION);

        int databaseSizeBeforeUpdate = tCOCUPACIONRepository.findAll().size();

        // Update the tCOCUPACION using partial update
        TCOCUPACION partialUpdatedTCOCUPACION = new TCOCUPACION();
        partialUpdatedTCOCUPACION.setIdOcupacion(tCOCUPACION.getIdOcupacion());

        partialUpdatedTCOCUPACION
            .ocupacion(UPDATED_OCUPACION)
            .factorGiroAnterior(UPDATED_FACTOR_GIRO_ANTERIOR)
            .factorGiroActual(UPDATED_FACTOR_GIRO_ACTUAL);

        restTCOCUPACIONMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCOCUPACION.getIdOcupacion())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCOCUPACION))
            )
            .andExpect(status().isOk());

        // Validate the TCOCUPACION in the database
        List<TCOCUPACION> tCOCUPACIONList = tCOCUPACIONRepository.findAll();
        assertThat(tCOCUPACIONList).hasSize(databaseSizeBeforeUpdate);
        TCOCUPACION testTCOCUPACION = tCOCUPACIONList.get(tCOCUPACIONList.size() - 1);
        assertThat(testTCOCUPACION.getOcupacion()).isEqualTo(UPDATED_OCUPACION);
        assertThat(testTCOCUPACION.getFactorGiroAnterior()).isEqualTo(UPDATED_FACTOR_GIRO_ANTERIOR);
        assertThat(testTCOCUPACION.getFactorGiroActual()).isEqualTo(UPDATED_FACTOR_GIRO_ACTUAL);
    }

    @Test
    @Transactional
    void patchNonExistingTCOCUPACION() throws Exception {
        int databaseSizeBeforeUpdate = tCOCUPACIONRepository.findAll().size();
        tCOCUPACION.setIdOcupacion(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCOCUPACIONMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCOCUPACION.getIdOcupacion())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCOCUPACION))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCOCUPACION in the database
        List<TCOCUPACION> tCOCUPACIONList = tCOCUPACIONRepository.findAll();
        assertThat(tCOCUPACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCOCUPACION() throws Exception {
        int databaseSizeBeforeUpdate = tCOCUPACIONRepository.findAll().size();
        tCOCUPACION.setIdOcupacion(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCOCUPACIONMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCOCUPACION))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCOCUPACION in the database
        List<TCOCUPACION> tCOCUPACIONList = tCOCUPACIONRepository.findAll();
        assertThat(tCOCUPACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCOCUPACION() throws Exception {
        int databaseSizeBeforeUpdate = tCOCUPACIONRepository.findAll().size();
        tCOCUPACION.setIdOcupacion(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCOCUPACIONMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCOCUPACION))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCOCUPACION in the database
        List<TCOCUPACION> tCOCUPACIONList = tCOCUPACIONRepository.findAll();
        assertThat(tCOCUPACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCOCUPACION() throws Exception {
        // Initialize the database
        tCOCUPACIONRepository.saveAndFlush(tCOCUPACION);

        int databaseSizeBeforeDelete = tCOCUPACIONRepository.findAll().size();

        // Delete the tCOCUPACION
        restTCOCUPACIONMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCOCUPACION.getIdOcupacion()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCOCUPACION> tCOCUPACIONList = tCOCUPACIONRepository.findAll();
        assertThat(tCOCUPACIONList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
