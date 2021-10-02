package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCPERFIL;
import com.allianze.apicotizador.repository.TCPERFILRepository;
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
 * Integration tests for the {@link TCPERFILResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCPERFILResourceIT {

    private static final String DEFAULT_PERFIL = "AAAAAAAAAA";
    private static final String UPDATED_PERFIL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tcperfils";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idPerfil}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCPERFILRepository tCPERFILRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCPERFILMockMvc;

    private TCPERFIL tCPERFIL;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCPERFIL createEntity(EntityManager em) {
        TCPERFIL tCPERFIL = new TCPERFIL().perfil(DEFAULT_PERFIL);
        return tCPERFIL;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCPERFIL createUpdatedEntity(EntityManager em) {
        TCPERFIL tCPERFIL = new TCPERFIL().perfil(UPDATED_PERFIL);
        return tCPERFIL;
    }

    @BeforeEach
    public void initTest() {
        tCPERFIL = createEntity(em);
    }

    @Test
    @Transactional
    void createTCPERFIL() throws Exception {
        int databaseSizeBeforeCreate = tCPERFILRepository.findAll().size();
        // Create the TCPERFIL
        restTCPERFILMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPERFIL)))
            .andExpect(status().isCreated());

        // Validate the TCPERFIL in the database
        List<TCPERFIL> tCPERFILList = tCPERFILRepository.findAll();
        assertThat(tCPERFILList).hasSize(databaseSizeBeforeCreate + 1);
        TCPERFIL testTCPERFIL = tCPERFILList.get(tCPERFILList.size() - 1);
        assertThat(testTCPERFIL.getPerfil()).isEqualTo(DEFAULT_PERFIL);
    }

    @Test
    @Transactional
    void createTCPERFILWithExistingId() throws Exception {
        // Create the TCPERFIL with an existing ID
        tCPERFIL.setIdPerfil(1L);

        int databaseSizeBeforeCreate = tCPERFILRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCPERFILMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPERFIL)))
            .andExpect(status().isBadRequest());

        // Validate the TCPERFIL in the database
        List<TCPERFIL> tCPERFILList = tCPERFILRepository.findAll();
        assertThat(tCPERFILList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPerfilIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCPERFILRepository.findAll().size();
        // set the field null
        tCPERFIL.setPerfil(null);

        // Create the TCPERFIL, which fails.

        restTCPERFILMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPERFIL)))
            .andExpect(status().isBadRequest());

        List<TCPERFIL> tCPERFILList = tCPERFILRepository.findAll();
        assertThat(tCPERFILList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCPERFILS() throws Exception {
        // Initialize the database
        tCPERFILRepository.saveAndFlush(tCPERFIL);

        // Get all the tCPERFILList
        restTCPERFILMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idPerfil,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idPerfil").value(hasItem(tCPERFIL.getIdPerfil().intValue())))
            .andExpect(jsonPath("$.[*].perfil").value(hasItem(DEFAULT_PERFIL)));
    }

    @Test
    @Transactional
    void getTCPERFIL() throws Exception {
        // Initialize the database
        tCPERFILRepository.saveAndFlush(tCPERFIL);

        // Get the tCPERFIL
        restTCPERFILMockMvc
            .perform(get(ENTITY_API_URL_ID, tCPERFIL.getIdPerfil()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idPerfil").value(tCPERFIL.getIdPerfil().intValue()))
            .andExpect(jsonPath("$.perfil").value(DEFAULT_PERFIL));
    }

    @Test
    @Transactional
    void getNonExistingTCPERFIL() throws Exception {
        // Get the tCPERFIL
        restTCPERFILMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCPERFIL() throws Exception {
        // Initialize the database
        tCPERFILRepository.saveAndFlush(tCPERFIL);

        int databaseSizeBeforeUpdate = tCPERFILRepository.findAll().size();

        // Update the tCPERFIL
        TCPERFIL updatedTCPERFIL = tCPERFILRepository.findById(tCPERFIL.getIdPerfil()).get();
        // Disconnect from session so that the updates on updatedTCPERFIL are not directly saved in db
        em.detach(updatedTCPERFIL);
        updatedTCPERFIL.perfil(UPDATED_PERFIL);

        restTCPERFILMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCPERFIL.getIdPerfil())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCPERFIL))
            )
            .andExpect(status().isOk());

        // Validate the TCPERFIL in the database
        List<TCPERFIL> tCPERFILList = tCPERFILRepository.findAll();
        assertThat(tCPERFILList).hasSize(databaseSizeBeforeUpdate);
        TCPERFIL testTCPERFIL = tCPERFILList.get(tCPERFILList.size() - 1);
        assertThat(testTCPERFIL.getPerfil()).isEqualTo(UPDATED_PERFIL);
    }

    @Test
    @Transactional
    void putNonExistingTCPERFIL() throws Exception {
        int databaseSizeBeforeUpdate = tCPERFILRepository.findAll().size();
        tCPERFIL.setIdPerfil(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCPERFILMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCPERFIL.getIdPerfil())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCPERFIL))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCPERFIL in the database
        List<TCPERFIL> tCPERFILList = tCPERFILRepository.findAll();
        assertThat(tCPERFILList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCPERFIL() throws Exception {
        int databaseSizeBeforeUpdate = tCPERFILRepository.findAll().size();
        tCPERFIL.setIdPerfil(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCPERFILMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCPERFIL))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCPERFIL in the database
        List<TCPERFIL> tCPERFILList = tCPERFILRepository.findAll();
        assertThat(tCPERFILList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCPERFIL() throws Exception {
        int databaseSizeBeforeUpdate = tCPERFILRepository.findAll().size();
        tCPERFIL.setIdPerfil(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCPERFILMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPERFIL)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCPERFIL in the database
        List<TCPERFIL> tCPERFILList = tCPERFILRepository.findAll();
        assertThat(tCPERFILList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCPERFILWithPatch() throws Exception {
        // Initialize the database
        tCPERFILRepository.saveAndFlush(tCPERFIL);

        int databaseSizeBeforeUpdate = tCPERFILRepository.findAll().size();

        // Update the tCPERFIL using partial update
        TCPERFIL partialUpdatedTCPERFIL = new TCPERFIL();
        partialUpdatedTCPERFIL.setIdPerfil(tCPERFIL.getIdPerfil());

        partialUpdatedTCPERFIL.perfil(UPDATED_PERFIL);

        restTCPERFILMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCPERFIL.getIdPerfil())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCPERFIL))
            )
            .andExpect(status().isOk());

        // Validate the TCPERFIL in the database
        List<TCPERFIL> tCPERFILList = tCPERFILRepository.findAll();
        assertThat(tCPERFILList).hasSize(databaseSizeBeforeUpdate);
        TCPERFIL testTCPERFIL = tCPERFILList.get(tCPERFILList.size() - 1);
        assertThat(testTCPERFIL.getPerfil()).isEqualTo(UPDATED_PERFIL);
    }

    @Test
    @Transactional
    void fullUpdateTCPERFILWithPatch() throws Exception {
        // Initialize the database
        tCPERFILRepository.saveAndFlush(tCPERFIL);

        int databaseSizeBeforeUpdate = tCPERFILRepository.findAll().size();

        // Update the tCPERFIL using partial update
        TCPERFIL partialUpdatedTCPERFIL = new TCPERFIL();
        partialUpdatedTCPERFIL.setIdPerfil(tCPERFIL.getIdPerfil());

        partialUpdatedTCPERFIL.perfil(UPDATED_PERFIL);

        restTCPERFILMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCPERFIL.getIdPerfil())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCPERFIL))
            )
            .andExpect(status().isOk());

        // Validate the TCPERFIL in the database
        List<TCPERFIL> tCPERFILList = tCPERFILRepository.findAll();
        assertThat(tCPERFILList).hasSize(databaseSizeBeforeUpdate);
        TCPERFIL testTCPERFIL = tCPERFILList.get(tCPERFILList.size() - 1);
        assertThat(testTCPERFIL.getPerfil()).isEqualTo(UPDATED_PERFIL);
    }

    @Test
    @Transactional
    void patchNonExistingTCPERFIL() throws Exception {
        int databaseSizeBeforeUpdate = tCPERFILRepository.findAll().size();
        tCPERFIL.setIdPerfil(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCPERFILMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCPERFIL.getIdPerfil())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCPERFIL))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCPERFIL in the database
        List<TCPERFIL> tCPERFILList = tCPERFILRepository.findAll();
        assertThat(tCPERFILList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCPERFIL() throws Exception {
        int databaseSizeBeforeUpdate = tCPERFILRepository.findAll().size();
        tCPERFIL.setIdPerfil(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCPERFILMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCPERFIL))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCPERFIL in the database
        List<TCPERFIL> tCPERFILList = tCPERFILRepository.findAll();
        assertThat(tCPERFILList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCPERFIL() throws Exception {
        int databaseSizeBeforeUpdate = tCPERFILRepository.findAll().size();
        tCPERFIL.setIdPerfil(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCPERFILMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCPERFIL)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCPERFIL in the database
        List<TCPERFIL> tCPERFILList = tCPERFILRepository.findAll();
        assertThat(tCPERFILList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCPERFIL() throws Exception {
        // Initialize the database
        tCPERFILRepository.saveAndFlush(tCPERFIL);

        int databaseSizeBeforeDelete = tCPERFILRepository.findAll().size();

        // Delete the tCPERFIL
        restTCPERFILMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCPERFIL.getIdPerfil()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCPERFIL> tCPERFILList = tCPERFILRepository.findAll();
        assertThat(tCPERFILList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
