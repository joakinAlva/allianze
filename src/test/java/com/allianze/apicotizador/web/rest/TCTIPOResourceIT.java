package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCTIPO;
import com.allianze.apicotizador.repository.TCTIPORepository;
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
 * Integration tests for the {@link TCTIPOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCTIPOResourceIT {

    private static final String DEFAULT_TIPO_REGLA = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_REGLA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tctipos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idTipo}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCTIPORepository tCTIPORepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCTIPOMockMvc;

    private TCTIPO tCTIPO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCTIPO createEntity(EntityManager em) {
        TCTIPO tCTIPO = new TCTIPO().tipoRegla(DEFAULT_TIPO_REGLA);
        return tCTIPO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCTIPO createUpdatedEntity(EntityManager em) {
        TCTIPO tCTIPO = new TCTIPO().tipoRegla(UPDATED_TIPO_REGLA);
        return tCTIPO;
    }

    @BeforeEach
    public void initTest() {
        tCTIPO = createEntity(em);
    }

    @Test
    @Transactional
    void createTCTIPO() throws Exception {
        int databaseSizeBeforeCreate = tCTIPORepository.findAll().size();
        // Create the TCTIPO
        restTCTIPOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCTIPO)))
            .andExpect(status().isCreated());

        // Validate the TCTIPO in the database
        List<TCTIPO> tCTIPOList = tCTIPORepository.findAll();
        assertThat(tCTIPOList).hasSize(databaseSizeBeforeCreate + 1);
        TCTIPO testTCTIPO = tCTIPOList.get(tCTIPOList.size() - 1);
        assertThat(testTCTIPO.getTipoRegla()).isEqualTo(DEFAULT_TIPO_REGLA);
    }

    @Test
    @Transactional
    void createTCTIPOWithExistingId() throws Exception {
        // Create the TCTIPO with an existing ID
        tCTIPO.setIdTipo(1L);

        int databaseSizeBeforeCreate = tCTIPORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCTIPOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCTIPO)))
            .andExpect(status().isBadRequest());

        // Validate the TCTIPO in the database
        List<TCTIPO> tCTIPOList = tCTIPORepository.findAll();
        assertThat(tCTIPOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTipoReglaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCTIPORepository.findAll().size();
        // set the field null
        tCTIPO.setTipoRegla(null);

        // Create the TCTIPO, which fails.

        restTCTIPOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCTIPO)))
            .andExpect(status().isBadRequest());

        List<TCTIPO> tCTIPOList = tCTIPORepository.findAll();
        assertThat(tCTIPOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCTIPOS() throws Exception {
        // Initialize the database
        tCTIPORepository.saveAndFlush(tCTIPO);

        // Get all the tCTIPOList
        restTCTIPOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idTipo,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idTipo").value(hasItem(tCTIPO.getIdTipo().intValue())))
            .andExpect(jsonPath("$.[*].tipoRegla").value(hasItem(DEFAULT_TIPO_REGLA)));
    }

    @Test
    @Transactional
    void getTCTIPO() throws Exception {
        // Initialize the database
        tCTIPORepository.saveAndFlush(tCTIPO);

        // Get the tCTIPO
        restTCTIPOMockMvc
            .perform(get(ENTITY_API_URL_ID, tCTIPO.getIdTipo()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idTipo").value(tCTIPO.getIdTipo().intValue()))
            .andExpect(jsonPath("$.tipoRegla").value(DEFAULT_TIPO_REGLA));
    }

    @Test
    @Transactional
    void getNonExistingTCTIPO() throws Exception {
        // Get the tCTIPO
        restTCTIPOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCTIPO() throws Exception {
        // Initialize the database
        tCTIPORepository.saveAndFlush(tCTIPO);

        int databaseSizeBeforeUpdate = tCTIPORepository.findAll().size();

        // Update the tCTIPO
        TCTIPO updatedTCTIPO = tCTIPORepository.findById(tCTIPO.getIdTipo()).get();
        // Disconnect from session so that the updates on updatedTCTIPO are not directly saved in db
        em.detach(updatedTCTIPO);
        updatedTCTIPO.tipoRegla(UPDATED_TIPO_REGLA);

        restTCTIPOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCTIPO.getIdTipo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCTIPO))
            )
            .andExpect(status().isOk());

        // Validate the TCTIPO in the database
        List<TCTIPO> tCTIPOList = tCTIPORepository.findAll();
        assertThat(tCTIPOList).hasSize(databaseSizeBeforeUpdate);
        TCTIPO testTCTIPO = tCTIPOList.get(tCTIPOList.size() - 1);
        assertThat(testTCTIPO.getTipoRegla()).isEqualTo(UPDATED_TIPO_REGLA);
    }

    @Test
    @Transactional
    void putNonExistingTCTIPO() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPORepository.findAll().size();
        tCTIPO.setIdTipo(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCTIPOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCTIPO.getIdTipo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCTIPO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCTIPO in the database
        List<TCTIPO> tCTIPOList = tCTIPORepository.findAll();
        assertThat(tCTIPOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCTIPO() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPORepository.findAll().size();
        tCTIPO.setIdTipo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCTIPOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCTIPO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCTIPO in the database
        List<TCTIPO> tCTIPOList = tCTIPORepository.findAll();
        assertThat(tCTIPOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCTIPO() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPORepository.findAll().size();
        tCTIPO.setIdTipo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCTIPOMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCTIPO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCTIPO in the database
        List<TCTIPO> tCTIPOList = tCTIPORepository.findAll();
        assertThat(tCTIPOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCTIPOWithPatch() throws Exception {
        // Initialize the database
        tCTIPORepository.saveAndFlush(tCTIPO);

        int databaseSizeBeforeUpdate = tCTIPORepository.findAll().size();

        // Update the tCTIPO using partial update
        TCTIPO partialUpdatedTCTIPO = new TCTIPO();
        partialUpdatedTCTIPO.setIdTipo(tCTIPO.getIdTipo());

        restTCTIPOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCTIPO.getIdTipo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCTIPO))
            )
            .andExpect(status().isOk());

        // Validate the TCTIPO in the database
        List<TCTIPO> tCTIPOList = tCTIPORepository.findAll();
        assertThat(tCTIPOList).hasSize(databaseSizeBeforeUpdate);
        TCTIPO testTCTIPO = tCTIPOList.get(tCTIPOList.size() - 1);
        assertThat(testTCTIPO.getTipoRegla()).isEqualTo(DEFAULT_TIPO_REGLA);
    }

    @Test
    @Transactional
    void fullUpdateTCTIPOWithPatch() throws Exception {
        // Initialize the database
        tCTIPORepository.saveAndFlush(tCTIPO);

        int databaseSizeBeforeUpdate = tCTIPORepository.findAll().size();

        // Update the tCTIPO using partial update
        TCTIPO partialUpdatedTCTIPO = new TCTIPO();
        partialUpdatedTCTIPO.setIdTipo(tCTIPO.getIdTipo());

        partialUpdatedTCTIPO.tipoRegla(UPDATED_TIPO_REGLA);

        restTCTIPOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCTIPO.getIdTipo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCTIPO))
            )
            .andExpect(status().isOk());

        // Validate the TCTIPO in the database
        List<TCTIPO> tCTIPOList = tCTIPORepository.findAll();
        assertThat(tCTIPOList).hasSize(databaseSizeBeforeUpdate);
        TCTIPO testTCTIPO = tCTIPOList.get(tCTIPOList.size() - 1);
        assertThat(testTCTIPO.getTipoRegla()).isEqualTo(UPDATED_TIPO_REGLA);
    }

    @Test
    @Transactional
    void patchNonExistingTCTIPO() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPORepository.findAll().size();
        tCTIPO.setIdTipo(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCTIPOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCTIPO.getIdTipo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCTIPO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCTIPO in the database
        List<TCTIPO> tCTIPOList = tCTIPORepository.findAll();
        assertThat(tCTIPOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCTIPO() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPORepository.findAll().size();
        tCTIPO.setIdTipo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCTIPOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCTIPO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCTIPO in the database
        List<TCTIPO> tCTIPOList = tCTIPORepository.findAll();
        assertThat(tCTIPOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCTIPO() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPORepository.findAll().size();
        tCTIPO.setIdTipo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCTIPOMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCTIPO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCTIPO in the database
        List<TCTIPO> tCTIPOList = tCTIPORepository.findAll();
        assertThat(tCTIPOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCTIPO() throws Exception {
        // Initialize the database
        tCTIPORepository.saveAndFlush(tCTIPO);

        int databaseSizeBeforeDelete = tCTIPORepository.findAll().size();

        // Delete the tCTIPO
        restTCTIPOMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCTIPO.getIdTipo()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCTIPO> tCTIPOList = tCTIPORepository.findAll();
        assertThat(tCTIPOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
