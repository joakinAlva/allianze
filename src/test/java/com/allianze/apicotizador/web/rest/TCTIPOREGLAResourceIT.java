package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCTIPOREGLA;
import com.allianze.apicotizador.repository.TCTIPOREGLARepository;
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
 * Integration tests for the {@link TCTIPOREGLAResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCTIPOREGLAResourceIT {

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    private static final Long DEFAULT_SEGMENTO = 1L;
    private static final Long UPDATED_SEGMENTO = 2L;

    private static final String ENTITY_API_URL = "/api/tctiporeglas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idTipoRegla}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCTIPOREGLARepository tCTIPOREGLARepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCTIPOREGLAMockMvc;

    private TCTIPOREGLA tCTIPOREGLA;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCTIPOREGLA createEntity(EntityManager em) {
        TCTIPOREGLA tCTIPOREGLA = new TCTIPOREGLA().tipo(DEFAULT_TIPO).segmento(DEFAULT_SEGMENTO);
        return tCTIPOREGLA;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCTIPOREGLA createUpdatedEntity(EntityManager em) {
        TCTIPOREGLA tCTIPOREGLA = new TCTIPOREGLA().tipo(UPDATED_TIPO).segmento(UPDATED_SEGMENTO);
        return tCTIPOREGLA;
    }

    @BeforeEach
    public void initTest() {
        tCTIPOREGLA = createEntity(em);
    }

    @Test
    @Transactional
    void createTCTIPOREGLA() throws Exception {
        int databaseSizeBeforeCreate = tCTIPOREGLARepository.findAll().size();
        // Create the TCTIPOREGLA
        restTCTIPOREGLAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCTIPOREGLA)))
            .andExpect(status().isCreated());

        // Validate the TCTIPOREGLA in the database
        List<TCTIPOREGLA> tCTIPOREGLAList = tCTIPOREGLARepository.findAll();
        assertThat(tCTIPOREGLAList).hasSize(databaseSizeBeforeCreate + 1);
        TCTIPOREGLA testTCTIPOREGLA = tCTIPOREGLAList.get(tCTIPOREGLAList.size() - 1);
        assertThat(testTCTIPOREGLA.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testTCTIPOREGLA.getSegmento()).isEqualTo(DEFAULT_SEGMENTO);
    }

    @Test
    @Transactional
    void createTCTIPOREGLAWithExistingId() throws Exception {
        // Create the TCTIPOREGLA with an existing ID
        tCTIPOREGLA.setIdTipoRegla(1L);

        int databaseSizeBeforeCreate = tCTIPOREGLARepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCTIPOREGLAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCTIPOREGLA)))
            .andExpect(status().isBadRequest());

        // Validate the TCTIPOREGLA in the database
        List<TCTIPOREGLA> tCTIPOREGLAList = tCTIPOREGLARepository.findAll();
        assertThat(tCTIPOREGLAList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTipoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCTIPOREGLARepository.findAll().size();
        // set the field null
        tCTIPOREGLA.setTipo(null);

        // Create the TCTIPOREGLA, which fails.

        restTCTIPOREGLAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCTIPOREGLA)))
            .andExpect(status().isBadRequest());

        List<TCTIPOREGLA> tCTIPOREGLAList = tCTIPOREGLARepository.findAll();
        assertThat(tCTIPOREGLAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSegmentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCTIPOREGLARepository.findAll().size();
        // set the field null
        tCTIPOREGLA.setSegmento(null);

        // Create the TCTIPOREGLA, which fails.

        restTCTIPOREGLAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCTIPOREGLA)))
            .andExpect(status().isBadRequest());

        List<TCTIPOREGLA> tCTIPOREGLAList = tCTIPOREGLARepository.findAll();
        assertThat(tCTIPOREGLAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCTIPOREGLAS() throws Exception {
        // Initialize the database
        tCTIPOREGLARepository.saveAndFlush(tCTIPOREGLA);

        // Get all the tCTIPOREGLAList
        restTCTIPOREGLAMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idTipoRegla,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idTipoRegla").value(hasItem(tCTIPOREGLA.getIdTipoRegla().intValue())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)))
            .andExpect(jsonPath("$.[*].segmento").value(hasItem(DEFAULT_SEGMENTO.intValue())));
    }

    @Test
    @Transactional
    void getTCTIPOREGLA() throws Exception {
        // Initialize the database
        tCTIPOREGLARepository.saveAndFlush(tCTIPOREGLA);

        // Get the tCTIPOREGLA
        restTCTIPOREGLAMockMvc
            .perform(get(ENTITY_API_URL_ID, tCTIPOREGLA.getIdTipoRegla()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idTipoRegla").value(tCTIPOREGLA.getIdTipoRegla().intValue()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO))
            .andExpect(jsonPath("$.segmento").value(DEFAULT_SEGMENTO.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCTIPOREGLA() throws Exception {
        // Get the tCTIPOREGLA
        restTCTIPOREGLAMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCTIPOREGLA() throws Exception {
        // Initialize the database
        tCTIPOREGLARepository.saveAndFlush(tCTIPOREGLA);

        int databaseSizeBeforeUpdate = tCTIPOREGLARepository.findAll().size();

        // Update the tCTIPOREGLA
        TCTIPOREGLA updatedTCTIPOREGLA = tCTIPOREGLARepository.findById(tCTIPOREGLA.getIdTipoRegla()).get();
        // Disconnect from session so that the updates on updatedTCTIPOREGLA are not directly saved in db
        em.detach(updatedTCTIPOREGLA);
        updatedTCTIPOREGLA.tipo(UPDATED_TIPO).segmento(UPDATED_SEGMENTO);

        restTCTIPOREGLAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCTIPOREGLA.getIdTipoRegla())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCTIPOREGLA))
            )
            .andExpect(status().isOk());

        // Validate the TCTIPOREGLA in the database
        List<TCTIPOREGLA> tCTIPOREGLAList = tCTIPOREGLARepository.findAll();
        assertThat(tCTIPOREGLAList).hasSize(databaseSizeBeforeUpdate);
        TCTIPOREGLA testTCTIPOREGLA = tCTIPOREGLAList.get(tCTIPOREGLAList.size() - 1);
        assertThat(testTCTIPOREGLA.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testTCTIPOREGLA.getSegmento()).isEqualTo(UPDATED_SEGMENTO);
    }

    @Test
    @Transactional
    void putNonExistingTCTIPOREGLA() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPOREGLARepository.findAll().size();
        tCTIPOREGLA.setIdTipoRegla(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCTIPOREGLAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCTIPOREGLA.getIdTipoRegla())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCTIPOREGLA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCTIPOREGLA in the database
        List<TCTIPOREGLA> tCTIPOREGLAList = tCTIPOREGLARepository.findAll();
        assertThat(tCTIPOREGLAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCTIPOREGLA() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPOREGLARepository.findAll().size();
        tCTIPOREGLA.setIdTipoRegla(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCTIPOREGLAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCTIPOREGLA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCTIPOREGLA in the database
        List<TCTIPOREGLA> tCTIPOREGLAList = tCTIPOREGLARepository.findAll();
        assertThat(tCTIPOREGLAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCTIPOREGLA() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPOREGLARepository.findAll().size();
        tCTIPOREGLA.setIdTipoRegla(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCTIPOREGLAMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCTIPOREGLA)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCTIPOREGLA in the database
        List<TCTIPOREGLA> tCTIPOREGLAList = tCTIPOREGLARepository.findAll();
        assertThat(tCTIPOREGLAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCTIPOREGLAWithPatch() throws Exception {
        // Initialize the database
        tCTIPOREGLARepository.saveAndFlush(tCTIPOREGLA);

        int databaseSizeBeforeUpdate = tCTIPOREGLARepository.findAll().size();

        // Update the tCTIPOREGLA using partial update
        TCTIPOREGLA partialUpdatedTCTIPOREGLA = new TCTIPOREGLA();
        partialUpdatedTCTIPOREGLA.setIdTipoRegla(tCTIPOREGLA.getIdTipoRegla());

        partialUpdatedTCTIPOREGLA.segmento(UPDATED_SEGMENTO);

        restTCTIPOREGLAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCTIPOREGLA.getIdTipoRegla())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCTIPOREGLA))
            )
            .andExpect(status().isOk());

        // Validate the TCTIPOREGLA in the database
        List<TCTIPOREGLA> tCTIPOREGLAList = tCTIPOREGLARepository.findAll();
        assertThat(tCTIPOREGLAList).hasSize(databaseSizeBeforeUpdate);
        TCTIPOREGLA testTCTIPOREGLA = tCTIPOREGLAList.get(tCTIPOREGLAList.size() - 1);
        assertThat(testTCTIPOREGLA.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testTCTIPOREGLA.getSegmento()).isEqualTo(UPDATED_SEGMENTO);
    }

    @Test
    @Transactional
    void fullUpdateTCTIPOREGLAWithPatch() throws Exception {
        // Initialize the database
        tCTIPOREGLARepository.saveAndFlush(tCTIPOREGLA);

        int databaseSizeBeforeUpdate = tCTIPOREGLARepository.findAll().size();

        // Update the tCTIPOREGLA using partial update
        TCTIPOREGLA partialUpdatedTCTIPOREGLA = new TCTIPOREGLA();
        partialUpdatedTCTIPOREGLA.setIdTipoRegla(tCTIPOREGLA.getIdTipoRegla());

        partialUpdatedTCTIPOREGLA.tipo(UPDATED_TIPO).segmento(UPDATED_SEGMENTO);

        restTCTIPOREGLAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCTIPOREGLA.getIdTipoRegla())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCTIPOREGLA))
            )
            .andExpect(status().isOk());

        // Validate the TCTIPOREGLA in the database
        List<TCTIPOREGLA> tCTIPOREGLAList = tCTIPOREGLARepository.findAll();
        assertThat(tCTIPOREGLAList).hasSize(databaseSizeBeforeUpdate);
        TCTIPOREGLA testTCTIPOREGLA = tCTIPOREGLAList.get(tCTIPOREGLAList.size() - 1);
        assertThat(testTCTIPOREGLA.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testTCTIPOREGLA.getSegmento()).isEqualTo(UPDATED_SEGMENTO);
    }

    @Test
    @Transactional
    void patchNonExistingTCTIPOREGLA() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPOREGLARepository.findAll().size();
        tCTIPOREGLA.setIdTipoRegla(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCTIPOREGLAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCTIPOREGLA.getIdTipoRegla())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCTIPOREGLA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCTIPOREGLA in the database
        List<TCTIPOREGLA> tCTIPOREGLAList = tCTIPOREGLARepository.findAll();
        assertThat(tCTIPOREGLAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCTIPOREGLA() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPOREGLARepository.findAll().size();
        tCTIPOREGLA.setIdTipoRegla(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCTIPOREGLAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCTIPOREGLA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCTIPOREGLA in the database
        List<TCTIPOREGLA> tCTIPOREGLAList = tCTIPOREGLARepository.findAll();
        assertThat(tCTIPOREGLAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCTIPOREGLA() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPOREGLARepository.findAll().size();
        tCTIPOREGLA.setIdTipoRegla(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCTIPOREGLAMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCTIPOREGLA))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCTIPOREGLA in the database
        List<TCTIPOREGLA> tCTIPOREGLAList = tCTIPOREGLARepository.findAll();
        assertThat(tCTIPOREGLAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCTIPOREGLA() throws Exception {
        // Initialize the database
        tCTIPOREGLARepository.saveAndFlush(tCTIPOREGLA);

        int databaseSizeBeforeDelete = tCTIPOREGLARepository.findAll().size();

        // Delete the tCTIPOREGLA
        restTCTIPOREGLAMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCTIPOREGLA.getIdTipoRegla()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCTIPOREGLA> tCTIPOREGLAList = tCTIPOREGLARepository.findAll();
        assertThat(tCTIPOREGLAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
