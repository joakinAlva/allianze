package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCESTATUSCOTIZACION;
import com.allianze.apicotizador.repository.TCESTATUSCOTIZACIONRepository;
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
 * Integration tests for the {@link TCESTATUSCOTIZACIONResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCESTATUSCOTIZACIONResourceIT {

    private static final Long DEFAULT_ORDEN = 1L;
    private static final Long UPDATED_ORDEN = 2L;

    private static final String DEFAULT_ESTATUS_COTIZACION = "AAAAAAAAAA";
    private static final String UPDATED_ESTATUS_COTIZACION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tcestatuscotizacions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idEstatusCotizacion}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCESTATUSCOTIZACIONRepository tCESTATUSCOTIZACIONRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCESTATUSCOTIZACIONMockMvc;

    private TCESTATUSCOTIZACION tCESTATUSCOTIZACION;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCESTATUSCOTIZACION createEntity(EntityManager em) {
        TCESTATUSCOTIZACION tCESTATUSCOTIZACION = new TCESTATUSCOTIZACION()
            .orden(DEFAULT_ORDEN)
            .estatusCotizacion(DEFAULT_ESTATUS_COTIZACION);
        return tCESTATUSCOTIZACION;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCESTATUSCOTIZACION createUpdatedEntity(EntityManager em) {
        TCESTATUSCOTIZACION tCESTATUSCOTIZACION = new TCESTATUSCOTIZACION()
            .orden(UPDATED_ORDEN)
            .estatusCotizacion(UPDATED_ESTATUS_COTIZACION);
        return tCESTATUSCOTIZACION;
    }

    @BeforeEach
    public void initTest() {
        tCESTATUSCOTIZACION = createEntity(em);
    }

    @Test
    @Transactional
    void createTCESTATUSCOTIZACION() throws Exception {
        int databaseSizeBeforeCreate = tCESTATUSCOTIZACIONRepository.findAll().size();
        // Create the TCESTATUSCOTIZACION
        restTCESTATUSCOTIZACIONMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCESTATUSCOTIZACION))
            )
            .andExpect(status().isCreated());

        // Validate the TCESTATUSCOTIZACION in the database
        List<TCESTATUSCOTIZACION> tCESTATUSCOTIZACIONList = tCESTATUSCOTIZACIONRepository.findAll();
        assertThat(tCESTATUSCOTIZACIONList).hasSize(databaseSizeBeforeCreate + 1);
        TCESTATUSCOTIZACION testTCESTATUSCOTIZACION = tCESTATUSCOTIZACIONList.get(tCESTATUSCOTIZACIONList.size() - 1);
        assertThat(testTCESTATUSCOTIZACION.getOrden()).isEqualTo(DEFAULT_ORDEN);
        assertThat(testTCESTATUSCOTIZACION.getEstatusCotizacion()).isEqualTo(DEFAULT_ESTATUS_COTIZACION);
    }

    @Test
    @Transactional
    void createTCESTATUSCOTIZACIONWithExistingId() throws Exception {
        // Create the TCESTATUSCOTIZACION with an existing ID
        tCESTATUSCOTIZACION.setIdEstatusCotizacion(1L);

        int databaseSizeBeforeCreate = tCESTATUSCOTIZACIONRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCESTATUSCOTIZACIONMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCESTATUSCOTIZACION))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCESTATUSCOTIZACION in the database
        List<TCESTATUSCOTIZACION> tCESTATUSCOTIZACIONList = tCESTATUSCOTIZACIONRepository.findAll();
        assertThat(tCESTATUSCOTIZACIONList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkOrdenIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCESTATUSCOTIZACIONRepository.findAll().size();
        // set the field null
        tCESTATUSCOTIZACION.setOrden(null);

        // Create the TCESTATUSCOTIZACION, which fails.

        restTCESTATUSCOTIZACIONMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCESTATUSCOTIZACION))
            )
            .andExpect(status().isBadRequest());

        List<TCESTATUSCOTIZACION> tCESTATUSCOTIZACIONList = tCESTATUSCOTIZACIONRepository.findAll();
        assertThat(tCESTATUSCOTIZACIONList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEstatusCotizacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCESTATUSCOTIZACIONRepository.findAll().size();
        // set the field null
        tCESTATUSCOTIZACION.setEstatusCotizacion(null);

        // Create the TCESTATUSCOTIZACION, which fails.

        restTCESTATUSCOTIZACIONMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCESTATUSCOTIZACION))
            )
            .andExpect(status().isBadRequest());

        List<TCESTATUSCOTIZACION> tCESTATUSCOTIZACIONList = tCESTATUSCOTIZACIONRepository.findAll();
        assertThat(tCESTATUSCOTIZACIONList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCESTATUSCOTIZACIONS() throws Exception {
        // Initialize the database
        tCESTATUSCOTIZACIONRepository.saveAndFlush(tCESTATUSCOTIZACION);

        // Get all the tCESTATUSCOTIZACIONList
        restTCESTATUSCOTIZACIONMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idEstatusCotizacion,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idEstatusCotizacion").value(hasItem(tCESTATUSCOTIZACION.getIdEstatusCotizacion().intValue())))
            .andExpect(jsonPath("$.[*].orden").value(hasItem(DEFAULT_ORDEN.intValue())))
            .andExpect(jsonPath("$.[*].estatusCotizacion").value(hasItem(DEFAULT_ESTATUS_COTIZACION)));
    }

    @Test
    @Transactional
    void getTCESTATUSCOTIZACION() throws Exception {
        // Initialize the database
        tCESTATUSCOTIZACIONRepository.saveAndFlush(tCESTATUSCOTIZACION);

        // Get the tCESTATUSCOTIZACION
        restTCESTATUSCOTIZACIONMockMvc
            .perform(get(ENTITY_API_URL_ID, tCESTATUSCOTIZACION.getIdEstatusCotizacion()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idEstatusCotizacion").value(tCESTATUSCOTIZACION.getIdEstatusCotizacion().intValue()))
            .andExpect(jsonPath("$.orden").value(DEFAULT_ORDEN.intValue()))
            .andExpect(jsonPath("$.estatusCotizacion").value(DEFAULT_ESTATUS_COTIZACION));
    }

    @Test
    @Transactional
    void getNonExistingTCESTATUSCOTIZACION() throws Exception {
        // Get the tCESTATUSCOTIZACION
        restTCESTATUSCOTIZACIONMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCESTATUSCOTIZACION() throws Exception {
        // Initialize the database
        tCESTATUSCOTIZACIONRepository.saveAndFlush(tCESTATUSCOTIZACION);

        int databaseSizeBeforeUpdate = tCESTATUSCOTIZACIONRepository.findAll().size();

        // Update the tCESTATUSCOTIZACION
        TCESTATUSCOTIZACION updatedTCESTATUSCOTIZACION = tCESTATUSCOTIZACIONRepository
            .findById(tCESTATUSCOTIZACION.getIdEstatusCotizacion())
            .get();
        // Disconnect from session so that the updates on updatedTCESTATUSCOTIZACION are not directly saved in db
        em.detach(updatedTCESTATUSCOTIZACION);
        updatedTCESTATUSCOTIZACION.orden(UPDATED_ORDEN).estatusCotizacion(UPDATED_ESTATUS_COTIZACION);

        restTCESTATUSCOTIZACIONMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCESTATUSCOTIZACION.getIdEstatusCotizacion())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCESTATUSCOTIZACION))
            )
            .andExpect(status().isOk());

        // Validate the TCESTATUSCOTIZACION in the database
        List<TCESTATUSCOTIZACION> tCESTATUSCOTIZACIONList = tCESTATUSCOTIZACIONRepository.findAll();
        assertThat(tCESTATUSCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
        TCESTATUSCOTIZACION testTCESTATUSCOTIZACION = tCESTATUSCOTIZACIONList.get(tCESTATUSCOTIZACIONList.size() - 1);
        assertThat(testTCESTATUSCOTIZACION.getOrden()).isEqualTo(UPDATED_ORDEN);
        assertThat(testTCESTATUSCOTIZACION.getEstatusCotizacion()).isEqualTo(UPDATED_ESTATUS_COTIZACION);
    }

    @Test
    @Transactional
    void putNonExistingTCESTATUSCOTIZACION() throws Exception {
        int databaseSizeBeforeUpdate = tCESTATUSCOTIZACIONRepository.findAll().size();
        tCESTATUSCOTIZACION.setIdEstatusCotizacion(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCESTATUSCOTIZACIONMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCESTATUSCOTIZACION.getIdEstatusCotizacion())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCESTATUSCOTIZACION))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCESTATUSCOTIZACION in the database
        List<TCESTATUSCOTIZACION> tCESTATUSCOTIZACIONList = tCESTATUSCOTIZACIONRepository.findAll();
        assertThat(tCESTATUSCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCESTATUSCOTIZACION() throws Exception {
        int databaseSizeBeforeUpdate = tCESTATUSCOTIZACIONRepository.findAll().size();
        tCESTATUSCOTIZACION.setIdEstatusCotizacion(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCESTATUSCOTIZACIONMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCESTATUSCOTIZACION))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCESTATUSCOTIZACION in the database
        List<TCESTATUSCOTIZACION> tCESTATUSCOTIZACIONList = tCESTATUSCOTIZACIONRepository.findAll();
        assertThat(tCESTATUSCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCESTATUSCOTIZACION() throws Exception {
        int databaseSizeBeforeUpdate = tCESTATUSCOTIZACIONRepository.findAll().size();
        tCESTATUSCOTIZACION.setIdEstatusCotizacion(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCESTATUSCOTIZACIONMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCESTATUSCOTIZACION))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCESTATUSCOTIZACION in the database
        List<TCESTATUSCOTIZACION> tCESTATUSCOTIZACIONList = tCESTATUSCOTIZACIONRepository.findAll();
        assertThat(tCESTATUSCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCESTATUSCOTIZACIONWithPatch() throws Exception {
        // Initialize the database
        tCESTATUSCOTIZACIONRepository.saveAndFlush(tCESTATUSCOTIZACION);

        int databaseSizeBeforeUpdate = tCESTATUSCOTIZACIONRepository.findAll().size();

        // Update the tCESTATUSCOTIZACION using partial update
        TCESTATUSCOTIZACION partialUpdatedTCESTATUSCOTIZACION = new TCESTATUSCOTIZACION();
        partialUpdatedTCESTATUSCOTIZACION.setIdEstatusCotizacion(tCESTATUSCOTIZACION.getIdEstatusCotizacion());

        partialUpdatedTCESTATUSCOTIZACION.orden(UPDATED_ORDEN).estatusCotizacion(UPDATED_ESTATUS_COTIZACION);

        restTCESTATUSCOTIZACIONMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCESTATUSCOTIZACION.getIdEstatusCotizacion())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCESTATUSCOTIZACION))
            )
            .andExpect(status().isOk());

        // Validate the TCESTATUSCOTIZACION in the database
        List<TCESTATUSCOTIZACION> tCESTATUSCOTIZACIONList = tCESTATUSCOTIZACIONRepository.findAll();
        assertThat(tCESTATUSCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
        TCESTATUSCOTIZACION testTCESTATUSCOTIZACION = tCESTATUSCOTIZACIONList.get(tCESTATUSCOTIZACIONList.size() - 1);
        assertThat(testTCESTATUSCOTIZACION.getOrden()).isEqualTo(UPDATED_ORDEN);
        assertThat(testTCESTATUSCOTIZACION.getEstatusCotizacion()).isEqualTo(UPDATED_ESTATUS_COTIZACION);
    }

    @Test
    @Transactional
    void fullUpdateTCESTATUSCOTIZACIONWithPatch() throws Exception {
        // Initialize the database
        tCESTATUSCOTIZACIONRepository.saveAndFlush(tCESTATUSCOTIZACION);

        int databaseSizeBeforeUpdate = tCESTATUSCOTIZACIONRepository.findAll().size();

        // Update the tCESTATUSCOTIZACION using partial update
        TCESTATUSCOTIZACION partialUpdatedTCESTATUSCOTIZACION = new TCESTATUSCOTIZACION();
        partialUpdatedTCESTATUSCOTIZACION.setIdEstatusCotizacion(tCESTATUSCOTIZACION.getIdEstatusCotizacion());

        partialUpdatedTCESTATUSCOTIZACION.orden(UPDATED_ORDEN).estatusCotizacion(UPDATED_ESTATUS_COTIZACION);

        restTCESTATUSCOTIZACIONMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCESTATUSCOTIZACION.getIdEstatusCotizacion())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCESTATUSCOTIZACION))
            )
            .andExpect(status().isOk());

        // Validate the TCESTATUSCOTIZACION in the database
        List<TCESTATUSCOTIZACION> tCESTATUSCOTIZACIONList = tCESTATUSCOTIZACIONRepository.findAll();
        assertThat(tCESTATUSCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
        TCESTATUSCOTIZACION testTCESTATUSCOTIZACION = tCESTATUSCOTIZACIONList.get(tCESTATUSCOTIZACIONList.size() - 1);
        assertThat(testTCESTATUSCOTIZACION.getOrden()).isEqualTo(UPDATED_ORDEN);
        assertThat(testTCESTATUSCOTIZACION.getEstatusCotizacion()).isEqualTo(UPDATED_ESTATUS_COTIZACION);
    }

    @Test
    @Transactional
    void patchNonExistingTCESTATUSCOTIZACION() throws Exception {
        int databaseSizeBeforeUpdate = tCESTATUSCOTIZACIONRepository.findAll().size();
        tCESTATUSCOTIZACION.setIdEstatusCotizacion(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCESTATUSCOTIZACIONMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCESTATUSCOTIZACION.getIdEstatusCotizacion())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCESTATUSCOTIZACION))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCESTATUSCOTIZACION in the database
        List<TCESTATUSCOTIZACION> tCESTATUSCOTIZACIONList = tCESTATUSCOTIZACIONRepository.findAll();
        assertThat(tCESTATUSCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCESTATUSCOTIZACION() throws Exception {
        int databaseSizeBeforeUpdate = tCESTATUSCOTIZACIONRepository.findAll().size();
        tCESTATUSCOTIZACION.setIdEstatusCotizacion(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCESTATUSCOTIZACIONMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCESTATUSCOTIZACION))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCESTATUSCOTIZACION in the database
        List<TCESTATUSCOTIZACION> tCESTATUSCOTIZACIONList = tCESTATUSCOTIZACIONRepository.findAll();
        assertThat(tCESTATUSCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCESTATUSCOTIZACION() throws Exception {
        int databaseSizeBeforeUpdate = tCESTATUSCOTIZACIONRepository.findAll().size();
        tCESTATUSCOTIZACION.setIdEstatusCotizacion(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCESTATUSCOTIZACIONMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCESTATUSCOTIZACION))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCESTATUSCOTIZACION in the database
        List<TCESTATUSCOTIZACION> tCESTATUSCOTIZACIONList = tCESTATUSCOTIZACIONRepository.findAll();
        assertThat(tCESTATUSCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCESTATUSCOTIZACION() throws Exception {
        // Initialize the database
        tCESTATUSCOTIZACIONRepository.saveAndFlush(tCESTATUSCOTIZACION);

        int databaseSizeBeforeDelete = tCESTATUSCOTIZACIONRepository.findAll().size();

        // Delete the tCESTATUSCOTIZACION
        restTCESTATUSCOTIZACIONMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCESTATUSCOTIZACION.getIdEstatusCotizacion()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCESTATUSCOTIZACION> tCESTATUSCOTIZACIONList = tCESTATUSCOTIZACIONRepository.findAll();
        assertThat(tCESTATUSCOTIZACIONList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
