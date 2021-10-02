package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCUNIDADNEGOCIO;
import com.allianze.apicotizador.repository.TCUNIDADNEGOCIORepository;
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
 * Integration tests for the {@link TCUNIDADNEGOCIOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCUNIDADNEGOCIOResourceIT {

    private static final String DEFAULT_UNIDAD_NEGOCIO = "AAAAAAAAAA";
    private static final String UPDATED_UNIDAD_NEGOCIO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tcunidadnegocios";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idUnidadNegocio}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCUNIDADNEGOCIORepository tCUNIDADNEGOCIORepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCUNIDADNEGOCIOMockMvc;

    private TCUNIDADNEGOCIO tCUNIDADNEGOCIO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCUNIDADNEGOCIO createEntity(EntityManager em) {
        TCUNIDADNEGOCIO tCUNIDADNEGOCIO = new TCUNIDADNEGOCIO().unidadNegocio(DEFAULT_UNIDAD_NEGOCIO);
        return tCUNIDADNEGOCIO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCUNIDADNEGOCIO createUpdatedEntity(EntityManager em) {
        TCUNIDADNEGOCIO tCUNIDADNEGOCIO = new TCUNIDADNEGOCIO().unidadNegocio(UPDATED_UNIDAD_NEGOCIO);
        return tCUNIDADNEGOCIO;
    }

    @BeforeEach
    public void initTest() {
        tCUNIDADNEGOCIO = createEntity(em);
    }

    @Test
    @Transactional
    void createTCUNIDADNEGOCIO() throws Exception {
        int databaseSizeBeforeCreate = tCUNIDADNEGOCIORepository.findAll().size();
        // Create the TCUNIDADNEGOCIO
        restTCUNIDADNEGOCIOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCUNIDADNEGOCIO))
            )
            .andExpect(status().isCreated());

        // Validate the TCUNIDADNEGOCIO in the database
        List<TCUNIDADNEGOCIO> tCUNIDADNEGOCIOList = tCUNIDADNEGOCIORepository.findAll();
        assertThat(tCUNIDADNEGOCIOList).hasSize(databaseSizeBeforeCreate + 1);
        TCUNIDADNEGOCIO testTCUNIDADNEGOCIO = tCUNIDADNEGOCIOList.get(tCUNIDADNEGOCIOList.size() - 1);
        assertThat(testTCUNIDADNEGOCIO.getUnidadNegocio()).isEqualTo(DEFAULT_UNIDAD_NEGOCIO);
    }

    @Test
    @Transactional
    void createTCUNIDADNEGOCIOWithExistingId() throws Exception {
        // Create the TCUNIDADNEGOCIO with an existing ID
        tCUNIDADNEGOCIO.setIdUnidadNegocio(1L);

        int databaseSizeBeforeCreate = tCUNIDADNEGOCIORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCUNIDADNEGOCIOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCUNIDADNEGOCIO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCUNIDADNEGOCIO in the database
        List<TCUNIDADNEGOCIO> tCUNIDADNEGOCIOList = tCUNIDADNEGOCIORepository.findAll();
        assertThat(tCUNIDADNEGOCIOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUnidadNegocioIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCUNIDADNEGOCIORepository.findAll().size();
        // set the field null
        tCUNIDADNEGOCIO.setUnidadNegocio(null);

        // Create the TCUNIDADNEGOCIO, which fails.

        restTCUNIDADNEGOCIOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCUNIDADNEGOCIO))
            )
            .andExpect(status().isBadRequest());

        List<TCUNIDADNEGOCIO> tCUNIDADNEGOCIOList = tCUNIDADNEGOCIORepository.findAll();
        assertThat(tCUNIDADNEGOCIOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCUNIDADNEGOCIOS() throws Exception {
        // Initialize the database
        tCUNIDADNEGOCIORepository.saveAndFlush(tCUNIDADNEGOCIO);

        // Get all the tCUNIDADNEGOCIOList
        restTCUNIDADNEGOCIOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idUnidadNegocio,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idUnidadNegocio").value(hasItem(tCUNIDADNEGOCIO.getIdUnidadNegocio().intValue())))
            .andExpect(jsonPath("$.[*].unidadNegocio").value(hasItem(DEFAULT_UNIDAD_NEGOCIO)));
    }

    @Test
    @Transactional
    void getTCUNIDADNEGOCIO() throws Exception {
        // Initialize the database
        tCUNIDADNEGOCIORepository.saveAndFlush(tCUNIDADNEGOCIO);

        // Get the tCUNIDADNEGOCIO
        restTCUNIDADNEGOCIOMockMvc
            .perform(get(ENTITY_API_URL_ID, tCUNIDADNEGOCIO.getIdUnidadNegocio()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idUnidadNegocio").value(tCUNIDADNEGOCIO.getIdUnidadNegocio().intValue()))
            .andExpect(jsonPath("$.unidadNegocio").value(DEFAULT_UNIDAD_NEGOCIO));
    }

    @Test
    @Transactional
    void getNonExistingTCUNIDADNEGOCIO() throws Exception {
        // Get the tCUNIDADNEGOCIO
        restTCUNIDADNEGOCIOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCUNIDADNEGOCIO() throws Exception {
        // Initialize the database
        tCUNIDADNEGOCIORepository.saveAndFlush(tCUNIDADNEGOCIO);

        int databaseSizeBeforeUpdate = tCUNIDADNEGOCIORepository.findAll().size();

        // Update the tCUNIDADNEGOCIO
        TCUNIDADNEGOCIO updatedTCUNIDADNEGOCIO = tCUNIDADNEGOCIORepository.findById(tCUNIDADNEGOCIO.getIdUnidadNegocio()).get();
        // Disconnect from session so that the updates on updatedTCUNIDADNEGOCIO are not directly saved in db
        em.detach(updatedTCUNIDADNEGOCIO);
        updatedTCUNIDADNEGOCIO.unidadNegocio(UPDATED_UNIDAD_NEGOCIO);

        restTCUNIDADNEGOCIOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCUNIDADNEGOCIO.getIdUnidadNegocio())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCUNIDADNEGOCIO))
            )
            .andExpect(status().isOk());

        // Validate the TCUNIDADNEGOCIO in the database
        List<TCUNIDADNEGOCIO> tCUNIDADNEGOCIOList = tCUNIDADNEGOCIORepository.findAll();
        assertThat(tCUNIDADNEGOCIOList).hasSize(databaseSizeBeforeUpdate);
        TCUNIDADNEGOCIO testTCUNIDADNEGOCIO = tCUNIDADNEGOCIOList.get(tCUNIDADNEGOCIOList.size() - 1);
        assertThat(testTCUNIDADNEGOCIO.getUnidadNegocio()).isEqualTo(UPDATED_UNIDAD_NEGOCIO);
    }

    @Test
    @Transactional
    void putNonExistingTCUNIDADNEGOCIO() throws Exception {
        int databaseSizeBeforeUpdate = tCUNIDADNEGOCIORepository.findAll().size();
        tCUNIDADNEGOCIO.setIdUnidadNegocio(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCUNIDADNEGOCIOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCUNIDADNEGOCIO.getIdUnidadNegocio())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCUNIDADNEGOCIO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCUNIDADNEGOCIO in the database
        List<TCUNIDADNEGOCIO> tCUNIDADNEGOCIOList = tCUNIDADNEGOCIORepository.findAll();
        assertThat(tCUNIDADNEGOCIOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCUNIDADNEGOCIO() throws Exception {
        int databaseSizeBeforeUpdate = tCUNIDADNEGOCIORepository.findAll().size();
        tCUNIDADNEGOCIO.setIdUnidadNegocio(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCUNIDADNEGOCIOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCUNIDADNEGOCIO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCUNIDADNEGOCIO in the database
        List<TCUNIDADNEGOCIO> tCUNIDADNEGOCIOList = tCUNIDADNEGOCIORepository.findAll();
        assertThat(tCUNIDADNEGOCIOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCUNIDADNEGOCIO() throws Exception {
        int databaseSizeBeforeUpdate = tCUNIDADNEGOCIORepository.findAll().size();
        tCUNIDADNEGOCIO.setIdUnidadNegocio(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCUNIDADNEGOCIOMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCUNIDADNEGOCIO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCUNIDADNEGOCIO in the database
        List<TCUNIDADNEGOCIO> tCUNIDADNEGOCIOList = tCUNIDADNEGOCIORepository.findAll();
        assertThat(tCUNIDADNEGOCIOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCUNIDADNEGOCIOWithPatch() throws Exception {
        // Initialize the database
        tCUNIDADNEGOCIORepository.saveAndFlush(tCUNIDADNEGOCIO);

        int databaseSizeBeforeUpdate = tCUNIDADNEGOCIORepository.findAll().size();

        // Update the tCUNIDADNEGOCIO using partial update
        TCUNIDADNEGOCIO partialUpdatedTCUNIDADNEGOCIO = new TCUNIDADNEGOCIO();
        partialUpdatedTCUNIDADNEGOCIO.setIdUnidadNegocio(tCUNIDADNEGOCIO.getIdUnidadNegocio());

        restTCUNIDADNEGOCIOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCUNIDADNEGOCIO.getIdUnidadNegocio())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCUNIDADNEGOCIO))
            )
            .andExpect(status().isOk());

        // Validate the TCUNIDADNEGOCIO in the database
        List<TCUNIDADNEGOCIO> tCUNIDADNEGOCIOList = tCUNIDADNEGOCIORepository.findAll();
        assertThat(tCUNIDADNEGOCIOList).hasSize(databaseSizeBeforeUpdate);
        TCUNIDADNEGOCIO testTCUNIDADNEGOCIO = tCUNIDADNEGOCIOList.get(tCUNIDADNEGOCIOList.size() - 1);
        assertThat(testTCUNIDADNEGOCIO.getUnidadNegocio()).isEqualTo(DEFAULT_UNIDAD_NEGOCIO);
    }

    @Test
    @Transactional
    void fullUpdateTCUNIDADNEGOCIOWithPatch() throws Exception {
        // Initialize the database
        tCUNIDADNEGOCIORepository.saveAndFlush(tCUNIDADNEGOCIO);

        int databaseSizeBeforeUpdate = tCUNIDADNEGOCIORepository.findAll().size();

        // Update the tCUNIDADNEGOCIO using partial update
        TCUNIDADNEGOCIO partialUpdatedTCUNIDADNEGOCIO = new TCUNIDADNEGOCIO();
        partialUpdatedTCUNIDADNEGOCIO.setIdUnidadNegocio(tCUNIDADNEGOCIO.getIdUnidadNegocio());

        partialUpdatedTCUNIDADNEGOCIO.unidadNegocio(UPDATED_UNIDAD_NEGOCIO);

        restTCUNIDADNEGOCIOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCUNIDADNEGOCIO.getIdUnidadNegocio())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCUNIDADNEGOCIO))
            )
            .andExpect(status().isOk());

        // Validate the TCUNIDADNEGOCIO in the database
        List<TCUNIDADNEGOCIO> tCUNIDADNEGOCIOList = tCUNIDADNEGOCIORepository.findAll();
        assertThat(tCUNIDADNEGOCIOList).hasSize(databaseSizeBeforeUpdate);
        TCUNIDADNEGOCIO testTCUNIDADNEGOCIO = tCUNIDADNEGOCIOList.get(tCUNIDADNEGOCIOList.size() - 1);
        assertThat(testTCUNIDADNEGOCIO.getUnidadNegocio()).isEqualTo(UPDATED_UNIDAD_NEGOCIO);
    }

    @Test
    @Transactional
    void patchNonExistingTCUNIDADNEGOCIO() throws Exception {
        int databaseSizeBeforeUpdate = tCUNIDADNEGOCIORepository.findAll().size();
        tCUNIDADNEGOCIO.setIdUnidadNegocio(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCUNIDADNEGOCIOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCUNIDADNEGOCIO.getIdUnidadNegocio())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCUNIDADNEGOCIO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCUNIDADNEGOCIO in the database
        List<TCUNIDADNEGOCIO> tCUNIDADNEGOCIOList = tCUNIDADNEGOCIORepository.findAll();
        assertThat(tCUNIDADNEGOCIOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCUNIDADNEGOCIO() throws Exception {
        int databaseSizeBeforeUpdate = tCUNIDADNEGOCIORepository.findAll().size();
        tCUNIDADNEGOCIO.setIdUnidadNegocio(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCUNIDADNEGOCIOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCUNIDADNEGOCIO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCUNIDADNEGOCIO in the database
        List<TCUNIDADNEGOCIO> tCUNIDADNEGOCIOList = tCUNIDADNEGOCIORepository.findAll();
        assertThat(tCUNIDADNEGOCIOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCUNIDADNEGOCIO() throws Exception {
        int databaseSizeBeforeUpdate = tCUNIDADNEGOCIORepository.findAll().size();
        tCUNIDADNEGOCIO.setIdUnidadNegocio(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCUNIDADNEGOCIOMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCUNIDADNEGOCIO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCUNIDADNEGOCIO in the database
        List<TCUNIDADNEGOCIO> tCUNIDADNEGOCIOList = tCUNIDADNEGOCIORepository.findAll();
        assertThat(tCUNIDADNEGOCIOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCUNIDADNEGOCIO() throws Exception {
        // Initialize the database
        tCUNIDADNEGOCIORepository.saveAndFlush(tCUNIDADNEGOCIO);

        int databaseSizeBeforeDelete = tCUNIDADNEGOCIORepository.findAll().size();

        // Delete the tCUNIDADNEGOCIO
        restTCUNIDADNEGOCIOMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCUNIDADNEGOCIO.getIdUnidadNegocio()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCUNIDADNEGOCIO> tCUNIDADNEGOCIOList = tCUNIDADNEGOCIORepository.findAll();
        assertThat(tCUNIDADNEGOCIOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
