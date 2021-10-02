package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCCOEFICIENTE;
import com.allianze.apicotizador.repository.TCCOEFICIENTERepository;
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
 * Integration tests for the {@link TCCOEFICIENTEResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCCOEFICIENTEResourceIT {

    private static final Long DEFAULT_COEFICIENTE = 1L;
    private static final Long UPDATED_COEFICIENTE = 2L;

    private static final Long DEFAULT_INTERVALO_MIN = 1L;
    private static final Long UPDATED_INTERVALO_MIN = 2L;

    private static final Long DEFAULT_INTERVALO_MAX = 1L;
    private static final Long UPDATED_INTERVALO_MAX = 2L;

    private static final Long DEFAULT_DESCUENTO_EXTRA = 1L;
    private static final Long UPDATED_DESCUENTO_EXTRA = 2L;

    private static final String ENTITY_API_URL = "/api/tccoeficientes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCoeficiente}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCCOEFICIENTERepository tCCOEFICIENTERepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCCOEFICIENTEMockMvc;

    private TCCOEFICIENTE tCCOEFICIENTE;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCOEFICIENTE createEntity(EntityManager em) {
        TCCOEFICIENTE tCCOEFICIENTE = new TCCOEFICIENTE()
            .coeficiente(DEFAULT_COEFICIENTE)
            .intervaloMin(DEFAULT_INTERVALO_MIN)
            .intervaloMax(DEFAULT_INTERVALO_MAX)
            .descuentoExtra(DEFAULT_DESCUENTO_EXTRA);
        return tCCOEFICIENTE;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCOEFICIENTE createUpdatedEntity(EntityManager em) {
        TCCOEFICIENTE tCCOEFICIENTE = new TCCOEFICIENTE()
            .coeficiente(UPDATED_COEFICIENTE)
            .intervaloMin(UPDATED_INTERVALO_MIN)
            .intervaloMax(UPDATED_INTERVALO_MAX)
            .descuentoExtra(UPDATED_DESCUENTO_EXTRA);
        return tCCOEFICIENTE;
    }

    @BeforeEach
    public void initTest() {
        tCCOEFICIENTE = createEntity(em);
    }

    @Test
    @Transactional
    void createTCCOEFICIENTE() throws Exception {
        int databaseSizeBeforeCreate = tCCOEFICIENTERepository.findAll().size();
        // Create the TCCOEFICIENTE
        restTCCOEFICIENTEMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOEFICIENTE)))
            .andExpect(status().isCreated());

        // Validate the TCCOEFICIENTE in the database
        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeCreate + 1);
        TCCOEFICIENTE testTCCOEFICIENTE = tCCOEFICIENTEList.get(tCCOEFICIENTEList.size() - 1);
        assertThat(testTCCOEFICIENTE.getCoeficiente()).isEqualTo(DEFAULT_COEFICIENTE);
        assertThat(testTCCOEFICIENTE.getIntervaloMin()).isEqualTo(DEFAULT_INTERVALO_MIN);
        assertThat(testTCCOEFICIENTE.getIntervaloMax()).isEqualTo(DEFAULT_INTERVALO_MAX);
        assertThat(testTCCOEFICIENTE.getDescuentoExtra()).isEqualTo(DEFAULT_DESCUENTO_EXTRA);
    }

    @Test
    @Transactional
    void createTCCOEFICIENTEWithExistingId() throws Exception {
        // Create the TCCOEFICIENTE with an existing ID
        tCCOEFICIENTE.setIdCoeficiente(1L);

        int databaseSizeBeforeCreate = tCCOEFICIENTERepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCCOEFICIENTEMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOEFICIENTE)))
            .andExpect(status().isBadRequest());

        // Validate the TCCOEFICIENTE in the database
        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCoeficienteIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOEFICIENTERepository.findAll().size();
        // set the field null
        tCCOEFICIENTE.setCoeficiente(null);

        // Create the TCCOEFICIENTE, which fails.

        restTCCOEFICIENTEMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOEFICIENTE)))
            .andExpect(status().isBadRequest());

        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIntervaloMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOEFICIENTERepository.findAll().size();
        // set the field null
        tCCOEFICIENTE.setIntervaloMin(null);

        // Create the TCCOEFICIENTE, which fails.

        restTCCOEFICIENTEMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOEFICIENTE)))
            .andExpect(status().isBadRequest());

        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIntervaloMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOEFICIENTERepository.findAll().size();
        // set the field null
        tCCOEFICIENTE.setIntervaloMax(null);

        // Create the TCCOEFICIENTE, which fails.

        restTCCOEFICIENTEMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOEFICIENTE)))
            .andExpect(status().isBadRequest());

        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescuentoExtraIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOEFICIENTERepository.findAll().size();
        // set the field null
        tCCOEFICIENTE.setDescuentoExtra(null);

        // Create the TCCOEFICIENTE, which fails.

        restTCCOEFICIENTEMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOEFICIENTE)))
            .andExpect(status().isBadRequest());

        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCCOEFICIENTES() throws Exception {
        // Initialize the database
        tCCOEFICIENTERepository.saveAndFlush(tCCOEFICIENTE);

        // Get all the tCCOEFICIENTEList
        restTCCOEFICIENTEMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCoeficiente,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCoeficiente").value(hasItem(tCCOEFICIENTE.getIdCoeficiente().intValue())))
            .andExpect(jsonPath("$.[*].coeficiente").value(hasItem(DEFAULT_COEFICIENTE.intValue())))
            .andExpect(jsonPath("$.[*].intervaloMin").value(hasItem(DEFAULT_INTERVALO_MIN.intValue())))
            .andExpect(jsonPath("$.[*].intervaloMax").value(hasItem(DEFAULT_INTERVALO_MAX.intValue())))
            .andExpect(jsonPath("$.[*].descuentoExtra").value(hasItem(DEFAULT_DESCUENTO_EXTRA.intValue())));
    }

    @Test
    @Transactional
    void getTCCOEFICIENTE() throws Exception {
        // Initialize the database
        tCCOEFICIENTERepository.saveAndFlush(tCCOEFICIENTE);

        // Get the tCCOEFICIENTE
        restTCCOEFICIENTEMockMvc
            .perform(get(ENTITY_API_URL_ID, tCCOEFICIENTE.getIdCoeficiente()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCoeficiente").value(tCCOEFICIENTE.getIdCoeficiente().intValue()))
            .andExpect(jsonPath("$.coeficiente").value(DEFAULT_COEFICIENTE.intValue()))
            .andExpect(jsonPath("$.intervaloMin").value(DEFAULT_INTERVALO_MIN.intValue()))
            .andExpect(jsonPath("$.intervaloMax").value(DEFAULT_INTERVALO_MAX.intValue()))
            .andExpect(jsonPath("$.descuentoExtra").value(DEFAULT_DESCUENTO_EXTRA.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCCOEFICIENTE() throws Exception {
        // Get the tCCOEFICIENTE
        restTCCOEFICIENTEMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCCOEFICIENTE() throws Exception {
        // Initialize the database
        tCCOEFICIENTERepository.saveAndFlush(tCCOEFICIENTE);

        int databaseSizeBeforeUpdate = tCCOEFICIENTERepository.findAll().size();

        // Update the tCCOEFICIENTE
        TCCOEFICIENTE updatedTCCOEFICIENTE = tCCOEFICIENTERepository.findById(tCCOEFICIENTE.getIdCoeficiente()).get();
        // Disconnect from session so that the updates on updatedTCCOEFICIENTE are not directly saved in db
        em.detach(updatedTCCOEFICIENTE);
        updatedTCCOEFICIENTE
            .coeficiente(UPDATED_COEFICIENTE)
            .intervaloMin(UPDATED_INTERVALO_MIN)
            .intervaloMax(UPDATED_INTERVALO_MAX)
            .descuentoExtra(UPDATED_DESCUENTO_EXTRA);

        restTCCOEFICIENTEMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCCOEFICIENTE.getIdCoeficiente())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCCOEFICIENTE))
            )
            .andExpect(status().isOk());

        // Validate the TCCOEFICIENTE in the database
        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeUpdate);
        TCCOEFICIENTE testTCCOEFICIENTE = tCCOEFICIENTEList.get(tCCOEFICIENTEList.size() - 1);
        assertThat(testTCCOEFICIENTE.getCoeficiente()).isEqualTo(UPDATED_COEFICIENTE);
        assertThat(testTCCOEFICIENTE.getIntervaloMin()).isEqualTo(UPDATED_INTERVALO_MIN);
        assertThat(testTCCOEFICIENTE.getIntervaloMax()).isEqualTo(UPDATED_INTERVALO_MAX);
        assertThat(testTCCOEFICIENTE.getDescuentoExtra()).isEqualTo(UPDATED_DESCUENTO_EXTRA);
    }

    @Test
    @Transactional
    void putNonExistingTCCOEFICIENTE() throws Exception {
        int databaseSizeBeforeUpdate = tCCOEFICIENTERepository.findAll().size();
        tCCOEFICIENTE.setIdCoeficiente(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCOEFICIENTEMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCCOEFICIENTE.getIdCoeficiente())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCOEFICIENTE))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOEFICIENTE in the database
        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCCOEFICIENTE() throws Exception {
        int databaseSizeBeforeUpdate = tCCOEFICIENTERepository.findAll().size();
        tCCOEFICIENTE.setIdCoeficiente(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOEFICIENTEMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCOEFICIENTE))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOEFICIENTE in the database
        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCCOEFICIENTE() throws Exception {
        int databaseSizeBeforeUpdate = tCCOEFICIENTERepository.findAll().size();
        tCCOEFICIENTE.setIdCoeficiente(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOEFICIENTEMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOEFICIENTE)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCOEFICIENTE in the database
        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCCOEFICIENTEWithPatch() throws Exception {
        // Initialize the database
        tCCOEFICIENTERepository.saveAndFlush(tCCOEFICIENTE);

        int databaseSizeBeforeUpdate = tCCOEFICIENTERepository.findAll().size();

        // Update the tCCOEFICIENTE using partial update
        TCCOEFICIENTE partialUpdatedTCCOEFICIENTE = new TCCOEFICIENTE();
        partialUpdatedTCCOEFICIENTE.setIdCoeficiente(tCCOEFICIENTE.getIdCoeficiente());

        partialUpdatedTCCOEFICIENTE.coeficiente(UPDATED_COEFICIENTE).intervaloMax(UPDATED_INTERVALO_MAX);

        restTCCOEFICIENTEMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCOEFICIENTE.getIdCoeficiente())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCOEFICIENTE))
            )
            .andExpect(status().isOk());

        // Validate the TCCOEFICIENTE in the database
        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeUpdate);
        TCCOEFICIENTE testTCCOEFICIENTE = tCCOEFICIENTEList.get(tCCOEFICIENTEList.size() - 1);
        assertThat(testTCCOEFICIENTE.getCoeficiente()).isEqualTo(UPDATED_COEFICIENTE);
        assertThat(testTCCOEFICIENTE.getIntervaloMin()).isEqualTo(DEFAULT_INTERVALO_MIN);
        assertThat(testTCCOEFICIENTE.getIntervaloMax()).isEqualTo(UPDATED_INTERVALO_MAX);
        assertThat(testTCCOEFICIENTE.getDescuentoExtra()).isEqualTo(DEFAULT_DESCUENTO_EXTRA);
    }

    @Test
    @Transactional
    void fullUpdateTCCOEFICIENTEWithPatch() throws Exception {
        // Initialize the database
        tCCOEFICIENTERepository.saveAndFlush(tCCOEFICIENTE);

        int databaseSizeBeforeUpdate = tCCOEFICIENTERepository.findAll().size();

        // Update the tCCOEFICIENTE using partial update
        TCCOEFICIENTE partialUpdatedTCCOEFICIENTE = new TCCOEFICIENTE();
        partialUpdatedTCCOEFICIENTE.setIdCoeficiente(tCCOEFICIENTE.getIdCoeficiente());

        partialUpdatedTCCOEFICIENTE
            .coeficiente(UPDATED_COEFICIENTE)
            .intervaloMin(UPDATED_INTERVALO_MIN)
            .intervaloMax(UPDATED_INTERVALO_MAX)
            .descuentoExtra(UPDATED_DESCUENTO_EXTRA);

        restTCCOEFICIENTEMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCOEFICIENTE.getIdCoeficiente())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCOEFICIENTE))
            )
            .andExpect(status().isOk());

        // Validate the TCCOEFICIENTE in the database
        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeUpdate);
        TCCOEFICIENTE testTCCOEFICIENTE = tCCOEFICIENTEList.get(tCCOEFICIENTEList.size() - 1);
        assertThat(testTCCOEFICIENTE.getCoeficiente()).isEqualTo(UPDATED_COEFICIENTE);
        assertThat(testTCCOEFICIENTE.getIntervaloMin()).isEqualTo(UPDATED_INTERVALO_MIN);
        assertThat(testTCCOEFICIENTE.getIntervaloMax()).isEqualTo(UPDATED_INTERVALO_MAX);
        assertThat(testTCCOEFICIENTE.getDescuentoExtra()).isEqualTo(UPDATED_DESCUENTO_EXTRA);
    }

    @Test
    @Transactional
    void patchNonExistingTCCOEFICIENTE() throws Exception {
        int databaseSizeBeforeUpdate = tCCOEFICIENTERepository.findAll().size();
        tCCOEFICIENTE.setIdCoeficiente(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCOEFICIENTEMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCCOEFICIENTE.getIdCoeficiente())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCOEFICIENTE))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOEFICIENTE in the database
        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCCOEFICIENTE() throws Exception {
        int databaseSizeBeforeUpdate = tCCOEFICIENTERepository.findAll().size();
        tCCOEFICIENTE.setIdCoeficiente(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOEFICIENTEMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCOEFICIENTE))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOEFICIENTE in the database
        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCCOEFICIENTE() throws Exception {
        int databaseSizeBeforeUpdate = tCCOEFICIENTERepository.findAll().size();
        tCCOEFICIENTE.setIdCoeficiente(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOEFICIENTEMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCCOEFICIENTE))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCOEFICIENTE in the database
        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCCOEFICIENTE() throws Exception {
        // Initialize the database
        tCCOEFICIENTERepository.saveAndFlush(tCCOEFICIENTE);

        int databaseSizeBeforeDelete = tCCOEFICIENTERepository.findAll().size();

        // Delete the tCCOEFICIENTE
        restTCCOEFICIENTEMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCCOEFICIENTE.getIdCoeficiente()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCCOEFICIENTE> tCCOEFICIENTEList = tCCOEFICIENTERepository.findAll();
        assertThat(tCCOEFICIENTEList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
