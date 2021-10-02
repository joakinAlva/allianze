package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCCUOTAVALOR;
import com.allianze.apicotizador.repository.TCCUOTAVALORRepository;
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
 * Integration tests for the {@link TCCUOTAVALORResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCCUOTAVALORResourceIT {

    private static final Long DEFAULT_PORCENTAJE = 1L;
    private static final Long UPDATED_PORCENTAJE = 2L;

    private static final String DEFAULT_VALOR = "AAAAAAAAAA";
    private static final String UPDATED_VALOR = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tccuotavalors";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCuotaValor}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCCUOTAVALORRepository tCCUOTAVALORRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCCUOTAVALORMockMvc;

    private TCCUOTAVALOR tCCUOTAVALOR;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCUOTAVALOR createEntity(EntityManager em) {
        TCCUOTAVALOR tCCUOTAVALOR = new TCCUOTAVALOR().porcentaje(DEFAULT_PORCENTAJE).valor(DEFAULT_VALOR);
        return tCCUOTAVALOR;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCUOTAVALOR createUpdatedEntity(EntityManager em) {
        TCCUOTAVALOR tCCUOTAVALOR = new TCCUOTAVALOR().porcentaje(UPDATED_PORCENTAJE).valor(UPDATED_VALOR);
        return tCCUOTAVALOR;
    }

    @BeforeEach
    public void initTest() {
        tCCUOTAVALOR = createEntity(em);
    }

    @Test
    @Transactional
    void createTCCUOTAVALOR() throws Exception {
        int databaseSizeBeforeCreate = tCCUOTAVALORRepository.findAll().size();
        // Create the TCCUOTAVALOR
        restTCCUOTAVALORMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTAVALOR)))
            .andExpect(status().isCreated());

        // Validate the TCCUOTAVALOR in the database
        List<TCCUOTAVALOR> tCCUOTAVALORList = tCCUOTAVALORRepository.findAll();
        assertThat(tCCUOTAVALORList).hasSize(databaseSizeBeforeCreate + 1);
        TCCUOTAVALOR testTCCUOTAVALOR = tCCUOTAVALORList.get(tCCUOTAVALORList.size() - 1);
        assertThat(testTCCUOTAVALOR.getPorcentaje()).isEqualTo(DEFAULT_PORCENTAJE);
        assertThat(testTCCUOTAVALOR.getValor()).isEqualTo(DEFAULT_VALOR);
    }

    @Test
    @Transactional
    void createTCCUOTAVALORWithExistingId() throws Exception {
        // Create the TCCUOTAVALOR with an existing ID
        tCCUOTAVALOR.setIdCuotaValor(1L);

        int databaseSizeBeforeCreate = tCCUOTAVALORRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCCUOTAVALORMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTAVALOR)))
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTAVALOR in the database
        List<TCCUOTAVALOR> tCCUOTAVALORList = tCCUOTAVALORRepository.findAll();
        assertThat(tCCUOTAVALORList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPorcentajeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTAVALORRepository.findAll().size();
        // set the field null
        tCCUOTAVALOR.setPorcentaje(null);

        // Create the TCCUOTAVALOR, which fails.

        restTCCUOTAVALORMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTAVALOR)))
            .andExpect(status().isBadRequest());

        List<TCCUOTAVALOR> tCCUOTAVALORList = tCCUOTAVALORRepository.findAll();
        assertThat(tCCUOTAVALORList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTAVALORRepository.findAll().size();
        // set the field null
        tCCUOTAVALOR.setValor(null);

        // Create the TCCUOTAVALOR, which fails.

        restTCCUOTAVALORMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTAVALOR)))
            .andExpect(status().isBadRequest());

        List<TCCUOTAVALOR> tCCUOTAVALORList = tCCUOTAVALORRepository.findAll();
        assertThat(tCCUOTAVALORList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCCUOTAVALORS() throws Exception {
        // Initialize the database
        tCCUOTAVALORRepository.saveAndFlush(tCCUOTAVALOR);

        // Get all the tCCUOTAVALORList
        restTCCUOTAVALORMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCuotaValor,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCuotaValor").value(hasItem(tCCUOTAVALOR.getIdCuotaValor().intValue())))
            .andExpect(jsonPath("$.[*].porcentaje").value(hasItem(DEFAULT_PORCENTAJE.intValue())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR)));
    }

    @Test
    @Transactional
    void getTCCUOTAVALOR() throws Exception {
        // Initialize the database
        tCCUOTAVALORRepository.saveAndFlush(tCCUOTAVALOR);

        // Get the tCCUOTAVALOR
        restTCCUOTAVALORMockMvc
            .perform(get(ENTITY_API_URL_ID, tCCUOTAVALOR.getIdCuotaValor()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCuotaValor").value(tCCUOTAVALOR.getIdCuotaValor().intValue()))
            .andExpect(jsonPath("$.porcentaje").value(DEFAULT_PORCENTAJE.intValue()))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR));
    }

    @Test
    @Transactional
    void getNonExistingTCCUOTAVALOR() throws Exception {
        // Get the tCCUOTAVALOR
        restTCCUOTAVALORMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCCUOTAVALOR() throws Exception {
        // Initialize the database
        tCCUOTAVALORRepository.saveAndFlush(tCCUOTAVALOR);

        int databaseSizeBeforeUpdate = tCCUOTAVALORRepository.findAll().size();

        // Update the tCCUOTAVALOR
        TCCUOTAVALOR updatedTCCUOTAVALOR = tCCUOTAVALORRepository.findById(tCCUOTAVALOR.getIdCuotaValor()).get();
        // Disconnect from session so that the updates on updatedTCCUOTAVALOR are not directly saved in db
        em.detach(updatedTCCUOTAVALOR);
        updatedTCCUOTAVALOR.porcentaje(UPDATED_PORCENTAJE).valor(UPDATED_VALOR);

        restTCCUOTAVALORMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCCUOTAVALOR.getIdCuotaValor())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCCUOTAVALOR))
            )
            .andExpect(status().isOk());

        // Validate the TCCUOTAVALOR in the database
        List<TCCUOTAVALOR> tCCUOTAVALORList = tCCUOTAVALORRepository.findAll();
        assertThat(tCCUOTAVALORList).hasSize(databaseSizeBeforeUpdate);
        TCCUOTAVALOR testTCCUOTAVALOR = tCCUOTAVALORList.get(tCCUOTAVALORList.size() - 1);
        assertThat(testTCCUOTAVALOR.getPorcentaje()).isEqualTo(UPDATED_PORCENTAJE);
        assertThat(testTCCUOTAVALOR.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    void putNonExistingTCCUOTAVALOR() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTAVALORRepository.findAll().size();
        tCCUOTAVALOR.setIdCuotaValor(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCUOTAVALORMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCCUOTAVALOR.getIdCuotaValor())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTAVALOR))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTAVALOR in the database
        List<TCCUOTAVALOR> tCCUOTAVALORList = tCCUOTAVALORRepository.findAll();
        assertThat(tCCUOTAVALORList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCCUOTAVALOR() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTAVALORRepository.findAll().size();
        tCCUOTAVALOR.setIdCuotaValor(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTAVALORMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTAVALOR))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTAVALOR in the database
        List<TCCUOTAVALOR> tCCUOTAVALORList = tCCUOTAVALORRepository.findAll();
        assertThat(tCCUOTAVALORList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCCUOTAVALOR() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTAVALORRepository.findAll().size();
        tCCUOTAVALOR.setIdCuotaValor(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTAVALORMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTAVALOR)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCUOTAVALOR in the database
        List<TCCUOTAVALOR> tCCUOTAVALORList = tCCUOTAVALORRepository.findAll();
        assertThat(tCCUOTAVALORList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCCUOTAVALORWithPatch() throws Exception {
        // Initialize the database
        tCCUOTAVALORRepository.saveAndFlush(tCCUOTAVALOR);

        int databaseSizeBeforeUpdate = tCCUOTAVALORRepository.findAll().size();

        // Update the tCCUOTAVALOR using partial update
        TCCUOTAVALOR partialUpdatedTCCUOTAVALOR = new TCCUOTAVALOR();
        partialUpdatedTCCUOTAVALOR.setIdCuotaValor(tCCUOTAVALOR.getIdCuotaValor());

        partialUpdatedTCCUOTAVALOR.porcentaje(UPDATED_PORCENTAJE).valor(UPDATED_VALOR);

        restTCCUOTAVALORMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCUOTAVALOR.getIdCuotaValor())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCUOTAVALOR))
            )
            .andExpect(status().isOk());

        // Validate the TCCUOTAVALOR in the database
        List<TCCUOTAVALOR> tCCUOTAVALORList = tCCUOTAVALORRepository.findAll();
        assertThat(tCCUOTAVALORList).hasSize(databaseSizeBeforeUpdate);
        TCCUOTAVALOR testTCCUOTAVALOR = tCCUOTAVALORList.get(tCCUOTAVALORList.size() - 1);
        assertThat(testTCCUOTAVALOR.getPorcentaje()).isEqualTo(UPDATED_PORCENTAJE);
        assertThat(testTCCUOTAVALOR.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    void fullUpdateTCCUOTAVALORWithPatch() throws Exception {
        // Initialize the database
        tCCUOTAVALORRepository.saveAndFlush(tCCUOTAVALOR);

        int databaseSizeBeforeUpdate = tCCUOTAVALORRepository.findAll().size();

        // Update the tCCUOTAVALOR using partial update
        TCCUOTAVALOR partialUpdatedTCCUOTAVALOR = new TCCUOTAVALOR();
        partialUpdatedTCCUOTAVALOR.setIdCuotaValor(tCCUOTAVALOR.getIdCuotaValor());

        partialUpdatedTCCUOTAVALOR.porcentaje(UPDATED_PORCENTAJE).valor(UPDATED_VALOR);

        restTCCUOTAVALORMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCUOTAVALOR.getIdCuotaValor())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCUOTAVALOR))
            )
            .andExpect(status().isOk());

        // Validate the TCCUOTAVALOR in the database
        List<TCCUOTAVALOR> tCCUOTAVALORList = tCCUOTAVALORRepository.findAll();
        assertThat(tCCUOTAVALORList).hasSize(databaseSizeBeforeUpdate);
        TCCUOTAVALOR testTCCUOTAVALOR = tCCUOTAVALORList.get(tCCUOTAVALORList.size() - 1);
        assertThat(testTCCUOTAVALOR.getPorcentaje()).isEqualTo(UPDATED_PORCENTAJE);
        assertThat(testTCCUOTAVALOR.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    void patchNonExistingTCCUOTAVALOR() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTAVALORRepository.findAll().size();
        tCCUOTAVALOR.setIdCuotaValor(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCUOTAVALORMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCCUOTAVALOR.getIdCuotaValor())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTAVALOR))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTAVALOR in the database
        List<TCCUOTAVALOR> tCCUOTAVALORList = tCCUOTAVALORRepository.findAll();
        assertThat(tCCUOTAVALORList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCCUOTAVALOR() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTAVALORRepository.findAll().size();
        tCCUOTAVALOR.setIdCuotaValor(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTAVALORMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTAVALOR))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTAVALOR in the database
        List<TCCUOTAVALOR> tCCUOTAVALORList = tCCUOTAVALORRepository.findAll();
        assertThat(tCCUOTAVALORList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCCUOTAVALOR() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTAVALORRepository.findAll().size();
        tCCUOTAVALOR.setIdCuotaValor(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTAVALORMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCCUOTAVALOR))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCUOTAVALOR in the database
        List<TCCUOTAVALOR> tCCUOTAVALORList = tCCUOTAVALORRepository.findAll();
        assertThat(tCCUOTAVALORList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCCUOTAVALOR() throws Exception {
        // Initialize the database
        tCCUOTAVALORRepository.saveAndFlush(tCCUOTAVALOR);

        int databaseSizeBeforeDelete = tCCUOTAVALORRepository.findAll().size();

        // Delete the tCCUOTAVALOR
        restTCCUOTAVALORMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCCUOTAVALOR.getIdCuotaValor()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCCUOTAVALOR> tCCUOTAVALORList = tCCUOTAVALORRepository.findAll();
        assertThat(tCCUOTAVALORList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
