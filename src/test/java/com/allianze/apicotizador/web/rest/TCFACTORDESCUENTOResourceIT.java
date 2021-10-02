package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCFACTORDESCUENTO;
import com.allianze.apicotizador.repository.TCFACTORDESCUENTORepository;
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
 * Integration tests for the {@link TCFACTORDESCUENTOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCFACTORDESCUENTOResourceIT {

    private static final Long DEFAULT_FACTOR = 1L;
    private static final Long UPDATED_FACTOR = 2L;

    private static final Long DEFAULT_PORCENTAJE = 1L;
    private static final Long UPDATED_PORCENTAJE = 2L;

    private static final String ENTITY_API_URL = "/api/tcfactordescuentos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idFactorDescuento}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCFACTORDESCUENTORepository tCFACTORDESCUENTORepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCFACTORDESCUENTOMockMvc;

    private TCFACTORDESCUENTO tCFACTORDESCUENTO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCFACTORDESCUENTO createEntity(EntityManager em) {
        TCFACTORDESCUENTO tCFACTORDESCUENTO = new TCFACTORDESCUENTO().factor(DEFAULT_FACTOR).porcentaje(DEFAULT_PORCENTAJE);
        return tCFACTORDESCUENTO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCFACTORDESCUENTO createUpdatedEntity(EntityManager em) {
        TCFACTORDESCUENTO tCFACTORDESCUENTO = new TCFACTORDESCUENTO().factor(UPDATED_FACTOR).porcentaje(UPDATED_PORCENTAJE);
        return tCFACTORDESCUENTO;
    }

    @BeforeEach
    public void initTest() {
        tCFACTORDESCUENTO = createEntity(em);
    }

    @Test
    @Transactional
    void createTCFACTORDESCUENTO() throws Exception {
        int databaseSizeBeforeCreate = tCFACTORDESCUENTORepository.findAll().size();
        // Create the TCFACTORDESCUENTO
        restTCFACTORDESCUENTOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCFACTORDESCUENTO))
            )
            .andExpect(status().isCreated());

        // Validate the TCFACTORDESCUENTO in the database
        List<TCFACTORDESCUENTO> tCFACTORDESCUENTOList = tCFACTORDESCUENTORepository.findAll();
        assertThat(tCFACTORDESCUENTOList).hasSize(databaseSizeBeforeCreate + 1);
        TCFACTORDESCUENTO testTCFACTORDESCUENTO = tCFACTORDESCUENTOList.get(tCFACTORDESCUENTOList.size() - 1);
        assertThat(testTCFACTORDESCUENTO.getFactor()).isEqualTo(DEFAULT_FACTOR);
        assertThat(testTCFACTORDESCUENTO.getPorcentaje()).isEqualTo(DEFAULT_PORCENTAJE);
    }

    @Test
    @Transactional
    void createTCFACTORDESCUENTOWithExistingId() throws Exception {
        // Create the TCFACTORDESCUENTO with an existing ID
        tCFACTORDESCUENTO.setIdFactorDescuento(1L);

        int databaseSizeBeforeCreate = tCFACTORDESCUENTORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCFACTORDESCUENTOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCFACTORDESCUENTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCFACTORDESCUENTO in the database
        List<TCFACTORDESCUENTO> tCFACTORDESCUENTOList = tCFACTORDESCUENTORepository.findAll();
        assertThat(tCFACTORDESCUENTOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkFactorIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCFACTORDESCUENTORepository.findAll().size();
        // set the field null
        tCFACTORDESCUENTO.setFactor(null);

        // Create the TCFACTORDESCUENTO, which fails.

        restTCFACTORDESCUENTOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCFACTORDESCUENTO))
            )
            .andExpect(status().isBadRequest());

        List<TCFACTORDESCUENTO> tCFACTORDESCUENTOList = tCFACTORDESCUENTORepository.findAll();
        assertThat(tCFACTORDESCUENTOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPorcentajeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCFACTORDESCUENTORepository.findAll().size();
        // set the field null
        tCFACTORDESCUENTO.setPorcentaje(null);

        // Create the TCFACTORDESCUENTO, which fails.

        restTCFACTORDESCUENTOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCFACTORDESCUENTO))
            )
            .andExpect(status().isBadRequest());

        List<TCFACTORDESCUENTO> tCFACTORDESCUENTOList = tCFACTORDESCUENTORepository.findAll();
        assertThat(tCFACTORDESCUENTOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCFACTORDESCUENTOS() throws Exception {
        // Initialize the database
        tCFACTORDESCUENTORepository.saveAndFlush(tCFACTORDESCUENTO);

        // Get all the tCFACTORDESCUENTOList
        restTCFACTORDESCUENTOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idFactorDescuento,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idFactorDescuento").value(hasItem(tCFACTORDESCUENTO.getIdFactorDescuento().intValue())))
            .andExpect(jsonPath("$.[*].factor").value(hasItem(DEFAULT_FACTOR.intValue())))
            .andExpect(jsonPath("$.[*].porcentaje").value(hasItem(DEFAULT_PORCENTAJE.intValue())));
    }

    @Test
    @Transactional
    void getTCFACTORDESCUENTO() throws Exception {
        // Initialize the database
        tCFACTORDESCUENTORepository.saveAndFlush(tCFACTORDESCUENTO);

        // Get the tCFACTORDESCUENTO
        restTCFACTORDESCUENTOMockMvc
            .perform(get(ENTITY_API_URL_ID, tCFACTORDESCUENTO.getIdFactorDescuento()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idFactorDescuento").value(tCFACTORDESCUENTO.getIdFactorDescuento().intValue()))
            .andExpect(jsonPath("$.factor").value(DEFAULT_FACTOR.intValue()))
            .andExpect(jsonPath("$.porcentaje").value(DEFAULT_PORCENTAJE.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCFACTORDESCUENTO() throws Exception {
        // Get the tCFACTORDESCUENTO
        restTCFACTORDESCUENTOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCFACTORDESCUENTO() throws Exception {
        // Initialize the database
        tCFACTORDESCUENTORepository.saveAndFlush(tCFACTORDESCUENTO);

        int databaseSizeBeforeUpdate = tCFACTORDESCUENTORepository.findAll().size();

        // Update the tCFACTORDESCUENTO
        TCFACTORDESCUENTO updatedTCFACTORDESCUENTO = tCFACTORDESCUENTORepository.findById(tCFACTORDESCUENTO.getIdFactorDescuento()).get();
        // Disconnect from session so that the updates on updatedTCFACTORDESCUENTO are not directly saved in db
        em.detach(updatedTCFACTORDESCUENTO);
        updatedTCFACTORDESCUENTO.factor(UPDATED_FACTOR).porcentaje(UPDATED_PORCENTAJE);

        restTCFACTORDESCUENTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCFACTORDESCUENTO.getIdFactorDescuento())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCFACTORDESCUENTO))
            )
            .andExpect(status().isOk());

        // Validate the TCFACTORDESCUENTO in the database
        List<TCFACTORDESCUENTO> tCFACTORDESCUENTOList = tCFACTORDESCUENTORepository.findAll();
        assertThat(tCFACTORDESCUENTOList).hasSize(databaseSizeBeforeUpdate);
        TCFACTORDESCUENTO testTCFACTORDESCUENTO = tCFACTORDESCUENTOList.get(tCFACTORDESCUENTOList.size() - 1);
        assertThat(testTCFACTORDESCUENTO.getFactor()).isEqualTo(UPDATED_FACTOR);
        assertThat(testTCFACTORDESCUENTO.getPorcentaje()).isEqualTo(UPDATED_PORCENTAJE);
    }

    @Test
    @Transactional
    void putNonExistingTCFACTORDESCUENTO() throws Exception {
        int databaseSizeBeforeUpdate = tCFACTORDESCUENTORepository.findAll().size();
        tCFACTORDESCUENTO.setIdFactorDescuento(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCFACTORDESCUENTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCFACTORDESCUENTO.getIdFactorDescuento())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCFACTORDESCUENTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCFACTORDESCUENTO in the database
        List<TCFACTORDESCUENTO> tCFACTORDESCUENTOList = tCFACTORDESCUENTORepository.findAll();
        assertThat(tCFACTORDESCUENTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCFACTORDESCUENTO() throws Exception {
        int databaseSizeBeforeUpdate = tCFACTORDESCUENTORepository.findAll().size();
        tCFACTORDESCUENTO.setIdFactorDescuento(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCFACTORDESCUENTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCFACTORDESCUENTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCFACTORDESCUENTO in the database
        List<TCFACTORDESCUENTO> tCFACTORDESCUENTOList = tCFACTORDESCUENTORepository.findAll();
        assertThat(tCFACTORDESCUENTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCFACTORDESCUENTO() throws Exception {
        int databaseSizeBeforeUpdate = tCFACTORDESCUENTORepository.findAll().size();
        tCFACTORDESCUENTO.setIdFactorDescuento(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCFACTORDESCUENTOMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCFACTORDESCUENTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCFACTORDESCUENTO in the database
        List<TCFACTORDESCUENTO> tCFACTORDESCUENTOList = tCFACTORDESCUENTORepository.findAll();
        assertThat(tCFACTORDESCUENTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCFACTORDESCUENTOWithPatch() throws Exception {
        // Initialize the database
        tCFACTORDESCUENTORepository.saveAndFlush(tCFACTORDESCUENTO);

        int databaseSizeBeforeUpdate = tCFACTORDESCUENTORepository.findAll().size();

        // Update the tCFACTORDESCUENTO using partial update
        TCFACTORDESCUENTO partialUpdatedTCFACTORDESCUENTO = new TCFACTORDESCUENTO();
        partialUpdatedTCFACTORDESCUENTO.setIdFactorDescuento(tCFACTORDESCUENTO.getIdFactorDescuento());

        partialUpdatedTCFACTORDESCUENTO.factor(UPDATED_FACTOR);

        restTCFACTORDESCUENTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCFACTORDESCUENTO.getIdFactorDescuento())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCFACTORDESCUENTO))
            )
            .andExpect(status().isOk());

        // Validate the TCFACTORDESCUENTO in the database
        List<TCFACTORDESCUENTO> tCFACTORDESCUENTOList = tCFACTORDESCUENTORepository.findAll();
        assertThat(tCFACTORDESCUENTOList).hasSize(databaseSizeBeforeUpdate);
        TCFACTORDESCUENTO testTCFACTORDESCUENTO = tCFACTORDESCUENTOList.get(tCFACTORDESCUENTOList.size() - 1);
        assertThat(testTCFACTORDESCUENTO.getFactor()).isEqualTo(UPDATED_FACTOR);
        assertThat(testTCFACTORDESCUENTO.getPorcentaje()).isEqualTo(DEFAULT_PORCENTAJE);
    }

    @Test
    @Transactional
    void fullUpdateTCFACTORDESCUENTOWithPatch() throws Exception {
        // Initialize the database
        tCFACTORDESCUENTORepository.saveAndFlush(tCFACTORDESCUENTO);

        int databaseSizeBeforeUpdate = tCFACTORDESCUENTORepository.findAll().size();

        // Update the tCFACTORDESCUENTO using partial update
        TCFACTORDESCUENTO partialUpdatedTCFACTORDESCUENTO = new TCFACTORDESCUENTO();
        partialUpdatedTCFACTORDESCUENTO.setIdFactorDescuento(tCFACTORDESCUENTO.getIdFactorDescuento());

        partialUpdatedTCFACTORDESCUENTO.factor(UPDATED_FACTOR).porcentaje(UPDATED_PORCENTAJE);

        restTCFACTORDESCUENTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCFACTORDESCUENTO.getIdFactorDescuento())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCFACTORDESCUENTO))
            )
            .andExpect(status().isOk());

        // Validate the TCFACTORDESCUENTO in the database
        List<TCFACTORDESCUENTO> tCFACTORDESCUENTOList = tCFACTORDESCUENTORepository.findAll();
        assertThat(tCFACTORDESCUENTOList).hasSize(databaseSizeBeforeUpdate);
        TCFACTORDESCUENTO testTCFACTORDESCUENTO = tCFACTORDESCUENTOList.get(tCFACTORDESCUENTOList.size() - 1);
        assertThat(testTCFACTORDESCUENTO.getFactor()).isEqualTo(UPDATED_FACTOR);
        assertThat(testTCFACTORDESCUENTO.getPorcentaje()).isEqualTo(UPDATED_PORCENTAJE);
    }

    @Test
    @Transactional
    void patchNonExistingTCFACTORDESCUENTO() throws Exception {
        int databaseSizeBeforeUpdate = tCFACTORDESCUENTORepository.findAll().size();
        tCFACTORDESCUENTO.setIdFactorDescuento(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCFACTORDESCUENTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCFACTORDESCUENTO.getIdFactorDescuento())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCFACTORDESCUENTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCFACTORDESCUENTO in the database
        List<TCFACTORDESCUENTO> tCFACTORDESCUENTOList = tCFACTORDESCUENTORepository.findAll();
        assertThat(tCFACTORDESCUENTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCFACTORDESCUENTO() throws Exception {
        int databaseSizeBeforeUpdate = tCFACTORDESCUENTORepository.findAll().size();
        tCFACTORDESCUENTO.setIdFactorDescuento(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCFACTORDESCUENTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCFACTORDESCUENTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCFACTORDESCUENTO in the database
        List<TCFACTORDESCUENTO> tCFACTORDESCUENTOList = tCFACTORDESCUENTORepository.findAll();
        assertThat(tCFACTORDESCUENTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCFACTORDESCUENTO() throws Exception {
        int databaseSizeBeforeUpdate = tCFACTORDESCUENTORepository.findAll().size();
        tCFACTORDESCUENTO.setIdFactorDescuento(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCFACTORDESCUENTOMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCFACTORDESCUENTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCFACTORDESCUENTO in the database
        List<TCFACTORDESCUENTO> tCFACTORDESCUENTOList = tCFACTORDESCUENTORepository.findAll();
        assertThat(tCFACTORDESCUENTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCFACTORDESCUENTO() throws Exception {
        // Initialize the database
        tCFACTORDESCUENTORepository.saveAndFlush(tCFACTORDESCUENTO);

        int databaseSizeBeforeDelete = tCFACTORDESCUENTORepository.findAll().size();

        // Delete the tCFACTORDESCUENTO
        restTCFACTORDESCUENTOMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCFACTORDESCUENTO.getIdFactorDescuento()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCFACTORDESCUENTO> tCFACTORDESCUENTOList = tCFACTORDESCUENTORepository.findAll();
        assertThat(tCFACTORDESCUENTOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
