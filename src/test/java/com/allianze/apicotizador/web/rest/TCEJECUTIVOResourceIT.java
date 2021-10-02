package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCEJECUTIVO;
import com.allianze.apicotizador.repository.TCEJECUTIVORepository;
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
 * Integration tests for the {@link TCEJECUTIVOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCEJECUTIVOResourceIT {

    private static final String DEFAULT_EJECUTIVO = "AAAAAAAAAA";
    private static final String UPDATED_EJECUTIVO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tcejecutivos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idEjecutivo}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCEJECUTIVORepository tCEJECUTIVORepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCEJECUTIVOMockMvc;

    private TCEJECUTIVO tCEJECUTIVO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCEJECUTIVO createEntity(EntityManager em) {
        TCEJECUTIVO tCEJECUTIVO = new TCEJECUTIVO().ejecutivo(DEFAULT_EJECUTIVO);
        return tCEJECUTIVO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCEJECUTIVO createUpdatedEntity(EntityManager em) {
        TCEJECUTIVO tCEJECUTIVO = new TCEJECUTIVO().ejecutivo(UPDATED_EJECUTIVO);
        return tCEJECUTIVO;
    }

    @BeforeEach
    public void initTest() {
        tCEJECUTIVO = createEntity(em);
    }

    @Test
    @Transactional
    void createTCEJECUTIVO() throws Exception {
        int databaseSizeBeforeCreate = tCEJECUTIVORepository.findAll().size();
        // Create the TCEJECUTIVO
        restTCEJECUTIVOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCEJECUTIVO)))
            .andExpect(status().isCreated());

        // Validate the TCEJECUTIVO in the database
        List<TCEJECUTIVO> tCEJECUTIVOList = tCEJECUTIVORepository.findAll();
        assertThat(tCEJECUTIVOList).hasSize(databaseSizeBeforeCreate + 1);
        TCEJECUTIVO testTCEJECUTIVO = tCEJECUTIVOList.get(tCEJECUTIVOList.size() - 1);
        assertThat(testTCEJECUTIVO.getEjecutivo()).isEqualTo(DEFAULT_EJECUTIVO);
    }

    @Test
    @Transactional
    void createTCEJECUTIVOWithExistingId() throws Exception {
        // Create the TCEJECUTIVO with an existing ID
        tCEJECUTIVO.setIdEjecutivo(1L);

        int databaseSizeBeforeCreate = tCEJECUTIVORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCEJECUTIVOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCEJECUTIVO)))
            .andExpect(status().isBadRequest());

        // Validate the TCEJECUTIVO in the database
        List<TCEJECUTIVO> tCEJECUTIVOList = tCEJECUTIVORepository.findAll();
        assertThat(tCEJECUTIVOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEjecutivoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCEJECUTIVORepository.findAll().size();
        // set the field null
        tCEJECUTIVO.setEjecutivo(null);

        // Create the TCEJECUTIVO, which fails.

        restTCEJECUTIVOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCEJECUTIVO)))
            .andExpect(status().isBadRequest());

        List<TCEJECUTIVO> tCEJECUTIVOList = tCEJECUTIVORepository.findAll();
        assertThat(tCEJECUTIVOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCEJECUTIVOS() throws Exception {
        // Initialize the database
        tCEJECUTIVORepository.saveAndFlush(tCEJECUTIVO);

        // Get all the tCEJECUTIVOList
        restTCEJECUTIVOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idEjecutivo,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idEjecutivo").value(hasItem(tCEJECUTIVO.getIdEjecutivo().intValue())))
            .andExpect(jsonPath("$.[*].ejecutivo").value(hasItem(DEFAULT_EJECUTIVO)));
    }

    @Test
    @Transactional
    void getTCEJECUTIVO() throws Exception {
        // Initialize the database
        tCEJECUTIVORepository.saveAndFlush(tCEJECUTIVO);

        // Get the tCEJECUTIVO
        restTCEJECUTIVOMockMvc
            .perform(get(ENTITY_API_URL_ID, tCEJECUTIVO.getIdEjecutivo()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idEjecutivo").value(tCEJECUTIVO.getIdEjecutivo().intValue()))
            .andExpect(jsonPath("$.ejecutivo").value(DEFAULT_EJECUTIVO));
    }

    @Test
    @Transactional
    void getNonExistingTCEJECUTIVO() throws Exception {
        // Get the tCEJECUTIVO
        restTCEJECUTIVOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCEJECUTIVO() throws Exception {
        // Initialize the database
        tCEJECUTIVORepository.saveAndFlush(tCEJECUTIVO);

        int databaseSizeBeforeUpdate = tCEJECUTIVORepository.findAll().size();

        // Update the tCEJECUTIVO
        TCEJECUTIVO updatedTCEJECUTIVO = tCEJECUTIVORepository.findById(tCEJECUTIVO.getIdEjecutivo()).get();
        // Disconnect from session so that the updates on updatedTCEJECUTIVO are not directly saved in db
        em.detach(updatedTCEJECUTIVO);
        updatedTCEJECUTIVO.ejecutivo(UPDATED_EJECUTIVO);

        restTCEJECUTIVOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCEJECUTIVO.getIdEjecutivo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCEJECUTIVO))
            )
            .andExpect(status().isOk());

        // Validate the TCEJECUTIVO in the database
        List<TCEJECUTIVO> tCEJECUTIVOList = tCEJECUTIVORepository.findAll();
        assertThat(tCEJECUTIVOList).hasSize(databaseSizeBeforeUpdate);
        TCEJECUTIVO testTCEJECUTIVO = tCEJECUTIVOList.get(tCEJECUTIVOList.size() - 1);
        assertThat(testTCEJECUTIVO.getEjecutivo()).isEqualTo(UPDATED_EJECUTIVO);
    }

    @Test
    @Transactional
    void putNonExistingTCEJECUTIVO() throws Exception {
        int databaseSizeBeforeUpdate = tCEJECUTIVORepository.findAll().size();
        tCEJECUTIVO.setIdEjecutivo(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCEJECUTIVOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCEJECUTIVO.getIdEjecutivo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCEJECUTIVO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCEJECUTIVO in the database
        List<TCEJECUTIVO> tCEJECUTIVOList = tCEJECUTIVORepository.findAll();
        assertThat(tCEJECUTIVOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCEJECUTIVO() throws Exception {
        int databaseSizeBeforeUpdate = tCEJECUTIVORepository.findAll().size();
        tCEJECUTIVO.setIdEjecutivo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCEJECUTIVOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCEJECUTIVO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCEJECUTIVO in the database
        List<TCEJECUTIVO> tCEJECUTIVOList = tCEJECUTIVORepository.findAll();
        assertThat(tCEJECUTIVOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCEJECUTIVO() throws Exception {
        int databaseSizeBeforeUpdate = tCEJECUTIVORepository.findAll().size();
        tCEJECUTIVO.setIdEjecutivo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCEJECUTIVOMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCEJECUTIVO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCEJECUTIVO in the database
        List<TCEJECUTIVO> tCEJECUTIVOList = tCEJECUTIVORepository.findAll();
        assertThat(tCEJECUTIVOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCEJECUTIVOWithPatch() throws Exception {
        // Initialize the database
        tCEJECUTIVORepository.saveAndFlush(tCEJECUTIVO);

        int databaseSizeBeforeUpdate = tCEJECUTIVORepository.findAll().size();

        // Update the tCEJECUTIVO using partial update
        TCEJECUTIVO partialUpdatedTCEJECUTIVO = new TCEJECUTIVO();
        partialUpdatedTCEJECUTIVO.setIdEjecutivo(tCEJECUTIVO.getIdEjecutivo());

        partialUpdatedTCEJECUTIVO.ejecutivo(UPDATED_EJECUTIVO);

        restTCEJECUTIVOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCEJECUTIVO.getIdEjecutivo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCEJECUTIVO))
            )
            .andExpect(status().isOk());

        // Validate the TCEJECUTIVO in the database
        List<TCEJECUTIVO> tCEJECUTIVOList = tCEJECUTIVORepository.findAll();
        assertThat(tCEJECUTIVOList).hasSize(databaseSizeBeforeUpdate);
        TCEJECUTIVO testTCEJECUTIVO = tCEJECUTIVOList.get(tCEJECUTIVOList.size() - 1);
        assertThat(testTCEJECUTIVO.getEjecutivo()).isEqualTo(UPDATED_EJECUTIVO);
    }

    @Test
    @Transactional
    void fullUpdateTCEJECUTIVOWithPatch() throws Exception {
        // Initialize the database
        tCEJECUTIVORepository.saveAndFlush(tCEJECUTIVO);

        int databaseSizeBeforeUpdate = tCEJECUTIVORepository.findAll().size();

        // Update the tCEJECUTIVO using partial update
        TCEJECUTIVO partialUpdatedTCEJECUTIVO = new TCEJECUTIVO();
        partialUpdatedTCEJECUTIVO.setIdEjecutivo(tCEJECUTIVO.getIdEjecutivo());

        partialUpdatedTCEJECUTIVO.ejecutivo(UPDATED_EJECUTIVO);

        restTCEJECUTIVOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCEJECUTIVO.getIdEjecutivo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCEJECUTIVO))
            )
            .andExpect(status().isOk());

        // Validate the TCEJECUTIVO in the database
        List<TCEJECUTIVO> tCEJECUTIVOList = tCEJECUTIVORepository.findAll();
        assertThat(tCEJECUTIVOList).hasSize(databaseSizeBeforeUpdate);
        TCEJECUTIVO testTCEJECUTIVO = tCEJECUTIVOList.get(tCEJECUTIVOList.size() - 1);
        assertThat(testTCEJECUTIVO.getEjecutivo()).isEqualTo(UPDATED_EJECUTIVO);
    }

    @Test
    @Transactional
    void patchNonExistingTCEJECUTIVO() throws Exception {
        int databaseSizeBeforeUpdate = tCEJECUTIVORepository.findAll().size();
        tCEJECUTIVO.setIdEjecutivo(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCEJECUTIVOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCEJECUTIVO.getIdEjecutivo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCEJECUTIVO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCEJECUTIVO in the database
        List<TCEJECUTIVO> tCEJECUTIVOList = tCEJECUTIVORepository.findAll();
        assertThat(tCEJECUTIVOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCEJECUTIVO() throws Exception {
        int databaseSizeBeforeUpdate = tCEJECUTIVORepository.findAll().size();
        tCEJECUTIVO.setIdEjecutivo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCEJECUTIVOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCEJECUTIVO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCEJECUTIVO in the database
        List<TCEJECUTIVO> tCEJECUTIVOList = tCEJECUTIVORepository.findAll();
        assertThat(tCEJECUTIVOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCEJECUTIVO() throws Exception {
        int databaseSizeBeforeUpdate = tCEJECUTIVORepository.findAll().size();
        tCEJECUTIVO.setIdEjecutivo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCEJECUTIVOMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCEJECUTIVO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCEJECUTIVO in the database
        List<TCEJECUTIVO> tCEJECUTIVOList = tCEJECUTIVORepository.findAll();
        assertThat(tCEJECUTIVOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCEJECUTIVO() throws Exception {
        // Initialize the database
        tCEJECUTIVORepository.saveAndFlush(tCEJECUTIVO);

        int databaseSizeBeforeDelete = tCEJECUTIVORepository.findAll().size();

        // Delete the tCEJECUTIVO
        restTCEJECUTIVOMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCEJECUTIVO.getIdEjecutivo()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCEJECUTIVO> tCEJECUTIVOList = tCEJECUTIVORepository.findAll();
        assertThat(tCEJECUTIVOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
