package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCSUBGRUPO;
import com.allianze.apicotizador.repository.TCSUBGRUPORepository;
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
 * Integration tests for the {@link TCSUBGRUPOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCSUBGRUPOResourceIT {

    private static final String DEFAULT_SUB_GRUPO = "AAAAAAAAAA";
    private static final String UPDATED_SUB_GRUPO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tcsubgrupos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idSubGrupo}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCSUBGRUPORepository tCSUBGRUPORepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCSUBGRUPOMockMvc;

    private TCSUBGRUPO tCSUBGRUPO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCSUBGRUPO createEntity(EntityManager em) {
        TCSUBGRUPO tCSUBGRUPO = new TCSUBGRUPO().subGrupo(DEFAULT_SUB_GRUPO);
        return tCSUBGRUPO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCSUBGRUPO createUpdatedEntity(EntityManager em) {
        TCSUBGRUPO tCSUBGRUPO = new TCSUBGRUPO().subGrupo(UPDATED_SUB_GRUPO);
        return tCSUBGRUPO;
    }

    @BeforeEach
    public void initTest() {
        tCSUBGRUPO = createEntity(em);
    }

    @Test
    @Transactional
    void createTCSUBGRUPO() throws Exception {
        int databaseSizeBeforeCreate = tCSUBGRUPORepository.findAll().size();
        // Create the TCSUBGRUPO
        restTCSUBGRUPOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCSUBGRUPO)))
            .andExpect(status().isCreated());

        // Validate the TCSUBGRUPO in the database
        List<TCSUBGRUPO> tCSUBGRUPOList = tCSUBGRUPORepository.findAll();
        assertThat(tCSUBGRUPOList).hasSize(databaseSizeBeforeCreate + 1);
        TCSUBGRUPO testTCSUBGRUPO = tCSUBGRUPOList.get(tCSUBGRUPOList.size() - 1);
        assertThat(testTCSUBGRUPO.getSubGrupo()).isEqualTo(DEFAULT_SUB_GRUPO);
    }

    @Test
    @Transactional
    void createTCSUBGRUPOWithExistingId() throws Exception {
        // Create the TCSUBGRUPO with an existing ID
        tCSUBGRUPO.setIdSubGrupo(1L);

        int databaseSizeBeforeCreate = tCSUBGRUPORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCSUBGRUPOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCSUBGRUPO)))
            .andExpect(status().isBadRequest());

        // Validate the TCSUBGRUPO in the database
        List<TCSUBGRUPO> tCSUBGRUPOList = tCSUBGRUPORepository.findAll();
        assertThat(tCSUBGRUPOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSubGrupoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCSUBGRUPORepository.findAll().size();
        // set the field null
        tCSUBGRUPO.setSubGrupo(null);

        // Create the TCSUBGRUPO, which fails.

        restTCSUBGRUPOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCSUBGRUPO)))
            .andExpect(status().isBadRequest());

        List<TCSUBGRUPO> tCSUBGRUPOList = tCSUBGRUPORepository.findAll();
        assertThat(tCSUBGRUPOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCSUBGRUPOS() throws Exception {
        // Initialize the database
        tCSUBGRUPORepository.saveAndFlush(tCSUBGRUPO);

        // Get all the tCSUBGRUPOList
        restTCSUBGRUPOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idSubGrupo,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idSubGrupo").value(hasItem(tCSUBGRUPO.getIdSubGrupo().intValue())))
            .andExpect(jsonPath("$.[*].subGrupo").value(hasItem(DEFAULT_SUB_GRUPO)));
    }

    @Test
    @Transactional
    void getTCSUBGRUPO() throws Exception {
        // Initialize the database
        tCSUBGRUPORepository.saveAndFlush(tCSUBGRUPO);

        // Get the tCSUBGRUPO
        restTCSUBGRUPOMockMvc
            .perform(get(ENTITY_API_URL_ID, tCSUBGRUPO.getIdSubGrupo()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idSubGrupo").value(tCSUBGRUPO.getIdSubGrupo().intValue()))
            .andExpect(jsonPath("$.subGrupo").value(DEFAULT_SUB_GRUPO));
    }

    @Test
    @Transactional
    void getNonExistingTCSUBGRUPO() throws Exception {
        // Get the tCSUBGRUPO
        restTCSUBGRUPOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCSUBGRUPO() throws Exception {
        // Initialize the database
        tCSUBGRUPORepository.saveAndFlush(tCSUBGRUPO);

        int databaseSizeBeforeUpdate = tCSUBGRUPORepository.findAll().size();

        // Update the tCSUBGRUPO
        TCSUBGRUPO updatedTCSUBGRUPO = tCSUBGRUPORepository.findById(tCSUBGRUPO.getIdSubGrupo()).get();
        // Disconnect from session so that the updates on updatedTCSUBGRUPO are not directly saved in db
        em.detach(updatedTCSUBGRUPO);
        updatedTCSUBGRUPO.subGrupo(UPDATED_SUB_GRUPO);

        restTCSUBGRUPOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCSUBGRUPO.getIdSubGrupo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCSUBGRUPO))
            )
            .andExpect(status().isOk());

        // Validate the TCSUBGRUPO in the database
        List<TCSUBGRUPO> tCSUBGRUPOList = tCSUBGRUPORepository.findAll();
        assertThat(tCSUBGRUPOList).hasSize(databaseSizeBeforeUpdate);
        TCSUBGRUPO testTCSUBGRUPO = tCSUBGRUPOList.get(tCSUBGRUPOList.size() - 1);
        assertThat(testTCSUBGRUPO.getSubGrupo()).isEqualTo(UPDATED_SUB_GRUPO);
    }

    @Test
    @Transactional
    void putNonExistingTCSUBGRUPO() throws Exception {
        int databaseSizeBeforeUpdate = tCSUBGRUPORepository.findAll().size();
        tCSUBGRUPO.setIdSubGrupo(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCSUBGRUPOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCSUBGRUPO.getIdSubGrupo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCSUBGRUPO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCSUBGRUPO in the database
        List<TCSUBGRUPO> tCSUBGRUPOList = tCSUBGRUPORepository.findAll();
        assertThat(tCSUBGRUPOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCSUBGRUPO() throws Exception {
        int databaseSizeBeforeUpdate = tCSUBGRUPORepository.findAll().size();
        tCSUBGRUPO.setIdSubGrupo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCSUBGRUPOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCSUBGRUPO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCSUBGRUPO in the database
        List<TCSUBGRUPO> tCSUBGRUPOList = tCSUBGRUPORepository.findAll();
        assertThat(tCSUBGRUPOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCSUBGRUPO() throws Exception {
        int databaseSizeBeforeUpdate = tCSUBGRUPORepository.findAll().size();
        tCSUBGRUPO.setIdSubGrupo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCSUBGRUPOMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCSUBGRUPO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCSUBGRUPO in the database
        List<TCSUBGRUPO> tCSUBGRUPOList = tCSUBGRUPORepository.findAll();
        assertThat(tCSUBGRUPOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCSUBGRUPOWithPatch() throws Exception {
        // Initialize the database
        tCSUBGRUPORepository.saveAndFlush(tCSUBGRUPO);

        int databaseSizeBeforeUpdate = tCSUBGRUPORepository.findAll().size();

        // Update the tCSUBGRUPO using partial update
        TCSUBGRUPO partialUpdatedTCSUBGRUPO = new TCSUBGRUPO();
        partialUpdatedTCSUBGRUPO.setIdSubGrupo(tCSUBGRUPO.getIdSubGrupo());

        restTCSUBGRUPOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCSUBGRUPO.getIdSubGrupo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCSUBGRUPO))
            )
            .andExpect(status().isOk());

        // Validate the TCSUBGRUPO in the database
        List<TCSUBGRUPO> tCSUBGRUPOList = tCSUBGRUPORepository.findAll();
        assertThat(tCSUBGRUPOList).hasSize(databaseSizeBeforeUpdate);
        TCSUBGRUPO testTCSUBGRUPO = tCSUBGRUPOList.get(tCSUBGRUPOList.size() - 1);
        assertThat(testTCSUBGRUPO.getSubGrupo()).isEqualTo(DEFAULT_SUB_GRUPO);
    }

    @Test
    @Transactional
    void fullUpdateTCSUBGRUPOWithPatch() throws Exception {
        // Initialize the database
        tCSUBGRUPORepository.saveAndFlush(tCSUBGRUPO);

        int databaseSizeBeforeUpdate = tCSUBGRUPORepository.findAll().size();

        // Update the tCSUBGRUPO using partial update
        TCSUBGRUPO partialUpdatedTCSUBGRUPO = new TCSUBGRUPO();
        partialUpdatedTCSUBGRUPO.setIdSubGrupo(tCSUBGRUPO.getIdSubGrupo());

        partialUpdatedTCSUBGRUPO.subGrupo(UPDATED_SUB_GRUPO);

        restTCSUBGRUPOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCSUBGRUPO.getIdSubGrupo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCSUBGRUPO))
            )
            .andExpect(status().isOk());

        // Validate the TCSUBGRUPO in the database
        List<TCSUBGRUPO> tCSUBGRUPOList = tCSUBGRUPORepository.findAll();
        assertThat(tCSUBGRUPOList).hasSize(databaseSizeBeforeUpdate);
        TCSUBGRUPO testTCSUBGRUPO = tCSUBGRUPOList.get(tCSUBGRUPOList.size() - 1);
        assertThat(testTCSUBGRUPO.getSubGrupo()).isEqualTo(UPDATED_SUB_GRUPO);
    }

    @Test
    @Transactional
    void patchNonExistingTCSUBGRUPO() throws Exception {
        int databaseSizeBeforeUpdate = tCSUBGRUPORepository.findAll().size();
        tCSUBGRUPO.setIdSubGrupo(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCSUBGRUPOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCSUBGRUPO.getIdSubGrupo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCSUBGRUPO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCSUBGRUPO in the database
        List<TCSUBGRUPO> tCSUBGRUPOList = tCSUBGRUPORepository.findAll();
        assertThat(tCSUBGRUPOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCSUBGRUPO() throws Exception {
        int databaseSizeBeforeUpdate = tCSUBGRUPORepository.findAll().size();
        tCSUBGRUPO.setIdSubGrupo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCSUBGRUPOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCSUBGRUPO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCSUBGRUPO in the database
        List<TCSUBGRUPO> tCSUBGRUPOList = tCSUBGRUPORepository.findAll();
        assertThat(tCSUBGRUPOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCSUBGRUPO() throws Exception {
        int databaseSizeBeforeUpdate = tCSUBGRUPORepository.findAll().size();
        tCSUBGRUPO.setIdSubGrupo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCSUBGRUPOMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCSUBGRUPO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCSUBGRUPO in the database
        List<TCSUBGRUPO> tCSUBGRUPOList = tCSUBGRUPORepository.findAll();
        assertThat(tCSUBGRUPOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCSUBGRUPO() throws Exception {
        // Initialize the database
        tCSUBGRUPORepository.saveAndFlush(tCSUBGRUPO);

        int databaseSizeBeforeDelete = tCSUBGRUPORepository.findAll().size();

        // Delete the tCSUBGRUPO
        restTCSUBGRUPOMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCSUBGRUPO.getIdSubGrupo()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCSUBGRUPO> tCSUBGRUPOList = tCSUBGRUPORepository.findAll();
        assertThat(tCSUBGRUPOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
