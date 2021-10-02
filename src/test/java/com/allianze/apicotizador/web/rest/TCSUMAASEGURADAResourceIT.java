package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCSUMAASEGURADA;
import com.allianze.apicotizador.repository.TCSUMAASEGURADARepository;
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
 * Integration tests for the {@link TCSUMAASEGURADAResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCSUMAASEGURADAResourceIT {

    private static final Long DEFAULT_SAGFF = 1L;
    private static final Long UPDATED_SAGFF = 2L;

    private static final Long DEFAULT_SACA = 1L;
    private static final Long UPDATED_SACA = 2L;

    private static final Long DEFAULT_SAGE = 1L;
    private static final Long UPDATED_SAGE = 2L;

    private static final Long DEFAULT_SAIQA = 1L;
    private static final Long UPDATED_SAIQA = 2L;

    private static final Long DEFAULT_SAIQV = 1L;
    private static final Long UPDATED_SAIQV = 2L;

    private static final String ENTITY_API_URL = "/api/tcsumaaseguradas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idSumaAsegurada}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCSUMAASEGURADARepository tCSUMAASEGURADARepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCSUMAASEGURADAMockMvc;

    private TCSUMAASEGURADA tCSUMAASEGURADA;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCSUMAASEGURADA createEntity(EntityManager em) {
        TCSUMAASEGURADA tCSUMAASEGURADA = new TCSUMAASEGURADA()
            .sagff(DEFAULT_SAGFF)
            .saca(DEFAULT_SACA)
            .sage(DEFAULT_SAGE)
            .saiqa(DEFAULT_SAIQA)
            .saiqv(DEFAULT_SAIQV);
        return tCSUMAASEGURADA;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCSUMAASEGURADA createUpdatedEntity(EntityManager em) {
        TCSUMAASEGURADA tCSUMAASEGURADA = new TCSUMAASEGURADA()
            .sagff(UPDATED_SAGFF)
            .saca(UPDATED_SACA)
            .sage(UPDATED_SAGE)
            .saiqa(UPDATED_SAIQA)
            .saiqv(UPDATED_SAIQV);
        return tCSUMAASEGURADA;
    }

    @BeforeEach
    public void initTest() {
        tCSUMAASEGURADA = createEntity(em);
    }

    @Test
    @Transactional
    void createTCSUMAASEGURADA() throws Exception {
        int databaseSizeBeforeCreate = tCSUMAASEGURADARepository.findAll().size();
        // Create the TCSUMAASEGURADA
        restTCSUMAASEGURADAMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCSUMAASEGURADA))
            )
            .andExpect(status().isCreated());

        // Validate the TCSUMAASEGURADA in the database
        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeCreate + 1);
        TCSUMAASEGURADA testTCSUMAASEGURADA = tCSUMAASEGURADAList.get(tCSUMAASEGURADAList.size() - 1);
        assertThat(testTCSUMAASEGURADA.getSagff()).isEqualTo(DEFAULT_SAGFF);
        assertThat(testTCSUMAASEGURADA.getSaca()).isEqualTo(DEFAULT_SACA);
        assertThat(testTCSUMAASEGURADA.getSage()).isEqualTo(DEFAULT_SAGE);
        assertThat(testTCSUMAASEGURADA.getSaiqa()).isEqualTo(DEFAULT_SAIQA);
        assertThat(testTCSUMAASEGURADA.getSaiqv()).isEqualTo(DEFAULT_SAIQV);
    }

    @Test
    @Transactional
    void createTCSUMAASEGURADAWithExistingId() throws Exception {
        // Create the TCSUMAASEGURADA with an existing ID
        tCSUMAASEGURADA.setIdSumaAsegurada(1L);

        int databaseSizeBeforeCreate = tCSUMAASEGURADARepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCSUMAASEGURADAMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCSUMAASEGURADA in the database
        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSagffIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCSUMAASEGURADARepository.findAll().size();
        // set the field null
        tCSUMAASEGURADA.setSagff(null);

        // Create the TCSUMAASEGURADA, which fails.

        restTCSUMAASEGURADAMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSacaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCSUMAASEGURADARepository.findAll().size();
        // set the field null
        tCSUMAASEGURADA.setSaca(null);

        // Create the TCSUMAASEGURADA, which fails.

        restTCSUMAASEGURADAMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSageIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCSUMAASEGURADARepository.findAll().size();
        // set the field null
        tCSUMAASEGURADA.setSage(null);

        // Create the TCSUMAASEGURADA, which fails.

        restTCSUMAASEGURADAMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSaiqaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCSUMAASEGURADARepository.findAll().size();
        // set the field null
        tCSUMAASEGURADA.setSaiqa(null);

        // Create the TCSUMAASEGURADA, which fails.

        restTCSUMAASEGURADAMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSaiqvIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCSUMAASEGURADARepository.findAll().size();
        // set the field null
        tCSUMAASEGURADA.setSaiqv(null);

        // Create the TCSUMAASEGURADA, which fails.

        restTCSUMAASEGURADAMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCSUMAASEGURADAS() throws Exception {
        // Initialize the database
        tCSUMAASEGURADARepository.saveAndFlush(tCSUMAASEGURADA);

        // Get all the tCSUMAASEGURADAList
        restTCSUMAASEGURADAMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idSumaAsegurada,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idSumaAsegurada").value(hasItem(tCSUMAASEGURADA.getIdSumaAsegurada().intValue())))
            .andExpect(jsonPath("$.[*].sagff").value(hasItem(DEFAULT_SAGFF.intValue())))
            .andExpect(jsonPath("$.[*].saca").value(hasItem(DEFAULT_SACA.intValue())))
            .andExpect(jsonPath("$.[*].sage").value(hasItem(DEFAULT_SAGE.intValue())))
            .andExpect(jsonPath("$.[*].saiqa").value(hasItem(DEFAULT_SAIQA.intValue())))
            .andExpect(jsonPath("$.[*].saiqv").value(hasItem(DEFAULT_SAIQV.intValue())));
    }

    @Test
    @Transactional
    void getTCSUMAASEGURADA() throws Exception {
        // Initialize the database
        tCSUMAASEGURADARepository.saveAndFlush(tCSUMAASEGURADA);

        // Get the tCSUMAASEGURADA
        restTCSUMAASEGURADAMockMvc
            .perform(get(ENTITY_API_URL_ID, tCSUMAASEGURADA.getIdSumaAsegurada()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idSumaAsegurada").value(tCSUMAASEGURADA.getIdSumaAsegurada().intValue()))
            .andExpect(jsonPath("$.sagff").value(DEFAULT_SAGFF.intValue()))
            .andExpect(jsonPath("$.saca").value(DEFAULT_SACA.intValue()))
            .andExpect(jsonPath("$.sage").value(DEFAULT_SAGE.intValue()))
            .andExpect(jsonPath("$.saiqa").value(DEFAULT_SAIQA.intValue()))
            .andExpect(jsonPath("$.saiqv").value(DEFAULT_SAIQV.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCSUMAASEGURADA() throws Exception {
        // Get the tCSUMAASEGURADA
        restTCSUMAASEGURADAMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCSUMAASEGURADA() throws Exception {
        // Initialize the database
        tCSUMAASEGURADARepository.saveAndFlush(tCSUMAASEGURADA);

        int databaseSizeBeforeUpdate = tCSUMAASEGURADARepository.findAll().size();

        // Update the tCSUMAASEGURADA
        TCSUMAASEGURADA updatedTCSUMAASEGURADA = tCSUMAASEGURADARepository.findById(tCSUMAASEGURADA.getIdSumaAsegurada()).get();
        // Disconnect from session so that the updates on updatedTCSUMAASEGURADA are not directly saved in db
        em.detach(updatedTCSUMAASEGURADA);
        updatedTCSUMAASEGURADA.sagff(UPDATED_SAGFF).saca(UPDATED_SACA).sage(UPDATED_SAGE).saiqa(UPDATED_SAIQA).saiqv(UPDATED_SAIQV);

        restTCSUMAASEGURADAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCSUMAASEGURADA.getIdSumaAsegurada())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCSUMAASEGURADA))
            )
            .andExpect(status().isOk());

        // Validate the TCSUMAASEGURADA in the database
        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
        TCSUMAASEGURADA testTCSUMAASEGURADA = tCSUMAASEGURADAList.get(tCSUMAASEGURADAList.size() - 1);
        assertThat(testTCSUMAASEGURADA.getSagff()).isEqualTo(UPDATED_SAGFF);
        assertThat(testTCSUMAASEGURADA.getSaca()).isEqualTo(UPDATED_SACA);
        assertThat(testTCSUMAASEGURADA.getSage()).isEqualTo(UPDATED_SAGE);
        assertThat(testTCSUMAASEGURADA.getSaiqa()).isEqualTo(UPDATED_SAIQA);
        assertThat(testTCSUMAASEGURADA.getSaiqv()).isEqualTo(UPDATED_SAIQV);
    }

    @Test
    @Transactional
    void putNonExistingTCSUMAASEGURADA() throws Exception {
        int databaseSizeBeforeUpdate = tCSUMAASEGURADARepository.findAll().size();
        tCSUMAASEGURADA.setIdSumaAsegurada(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCSUMAASEGURADAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCSUMAASEGURADA.getIdSumaAsegurada())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCSUMAASEGURADA in the database
        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCSUMAASEGURADA() throws Exception {
        int databaseSizeBeforeUpdate = tCSUMAASEGURADARepository.findAll().size();
        tCSUMAASEGURADA.setIdSumaAsegurada(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCSUMAASEGURADAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCSUMAASEGURADA in the database
        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCSUMAASEGURADA() throws Exception {
        int databaseSizeBeforeUpdate = tCSUMAASEGURADARepository.findAll().size();
        tCSUMAASEGURADA.setIdSumaAsegurada(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCSUMAASEGURADAMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCSUMAASEGURADA))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCSUMAASEGURADA in the database
        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCSUMAASEGURADAWithPatch() throws Exception {
        // Initialize the database
        tCSUMAASEGURADARepository.saveAndFlush(tCSUMAASEGURADA);

        int databaseSizeBeforeUpdate = tCSUMAASEGURADARepository.findAll().size();

        // Update the tCSUMAASEGURADA using partial update
        TCSUMAASEGURADA partialUpdatedTCSUMAASEGURADA = new TCSUMAASEGURADA();
        partialUpdatedTCSUMAASEGURADA.setIdSumaAsegurada(tCSUMAASEGURADA.getIdSumaAsegurada());

        partialUpdatedTCSUMAASEGURADA.sage(UPDATED_SAGE).saiqv(UPDATED_SAIQV);

        restTCSUMAASEGURADAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCSUMAASEGURADA.getIdSumaAsegurada())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCSUMAASEGURADA))
            )
            .andExpect(status().isOk());

        // Validate the TCSUMAASEGURADA in the database
        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
        TCSUMAASEGURADA testTCSUMAASEGURADA = tCSUMAASEGURADAList.get(tCSUMAASEGURADAList.size() - 1);
        assertThat(testTCSUMAASEGURADA.getSagff()).isEqualTo(DEFAULT_SAGFF);
        assertThat(testTCSUMAASEGURADA.getSaca()).isEqualTo(DEFAULT_SACA);
        assertThat(testTCSUMAASEGURADA.getSage()).isEqualTo(UPDATED_SAGE);
        assertThat(testTCSUMAASEGURADA.getSaiqa()).isEqualTo(DEFAULT_SAIQA);
        assertThat(testTCSUMAASEGURADA.getSaiqv()).isEqualTo(UPDATED_SAIQV);
    }

    @Test
    @Transactional
    void fullUpdateTCSUMAASEGURADAWithPatch() throws Exception {
        // Initialize the database
        tCSUMAASEGURADARepository.saveAndFlush(tCSUMAASEGURADA);

        int databaseSizeBeforeUpdate = tCSUMAASEGURADARepository.findAll().size();

        // Update the tCSUMAASEGURADA using partial update
        TCSUMAASEGURADA partialUpdatedTCSUMAASEGURADA = new TCSUMAASEGURADA();
        partialUpdatedTCSUMAASEGURADA.setIdSumaAsegurada(tCSUMAASEGURADA.getIdSumaAsegurada());

        partialUpdatedTCSUMAASEGURADA.sagff(UPDATED_SAGFF).saca(UPDATED_SACA).sage(UPDATED_SAGE).saiqa(UPDATED_SAIQA).saiqv(UPDATED_SAIQV);

        restTCSUMAASEGURADAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCSUMAASEGURADA.getIdSumaAsegurada())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCSUMAASEGURADA))
            )
            .andExpect(status().isOk());

        // Validate the TCSUMAASEGURADA in the database
        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
        TCSUMAASEGURADA testTCSUMAASEGURADA = tCSUMAASEGURADAList.get(tCSUMAASEGURADAList.size() - 1);
        assertThat(testTCSUMAASEGURADA.getSagff()).isEqualTo(UPDATED_SAGFF);
        assertThat(testTCSUMAASEGURADA.getSaca()).isEqualTo(UPDATED_SACA);
        assertThat(testTCSUMAASEGURADA.getSage()).isEqualTo(UPDATED_SAGE);
        assertThat(testTCSUMAASEGURADA.getSaiqa()).isEqualTo(UPDATED_SAIQA);
        assertThat(testTCSUMAASEGURADA.getSaiqv()).isEqualTo(UPDATED_SAIQV);
    }

    @Test
    @Transactional
    void patchNonExistingTCSUMAASEGURADA() throws Exception {
        int databaseSizeBeforeUpdate = tCSUMAASEGURADARepository.findAll().size();
        tCSUMAASEGURADA.setIdSumaAsegurada(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCSUMAASEGURADAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCSUMAASEGURADA.getIdSumaAsegurada())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCSUMAASEGURADA in the database
        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCSUMAASEGURADA() throws Exception {
        int databaseSizeBeforeUpdate = tCSUMAASEGURADARepository.findAll().size();
        tCSUMAASEGURADA.setIdSumaAsegurada(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCSUMAASEGURADAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCSUMAASEGURADA in the database
        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCSUMAASEGURADA() throws Exception {
        int databaseSizeBeforeUpdate = tCSUMAASEGURADARepository.findAll().size();
        tCSUMAASEGURADA.setIdSumaAsegurada(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCSUMAASEGURADAMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCSUMAASEGURADA))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCSUMAASEGURADA in the database
        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCSUMAASEGURADA() throws Exception {
        // Initialize the database
        tCSUMAASEGURADARepository.saveAndFlush(tCSUMAASEGURADA);

        int databaseSizeBeforeDelete = tCSUMAASEGURADARepository.findAll().size();

        // Delete the tCSUMAASEGURADA
        restTCSUMAASEGURADAMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCSUMAASEGURADA.getIdSumaAsegurada()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCSUMAASEGURADA> tCSUMAASEGURADAList = tCSUMAASEGURADARepository.findAll();
        assertThat(tCSUMAASEGURADAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
