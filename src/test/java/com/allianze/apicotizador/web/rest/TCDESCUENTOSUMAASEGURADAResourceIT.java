package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCDESCUENTOSUMAASEGURADA;
import com.allianze.apicotizador.repository.TCDESCUENTOSUMAASEGURADARepository;
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
 * Integration tests for the {@link TCDESCUENTOSUMAASEGURADAResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCDESCUENTOSUMAASEGURADAResourceIT {

    private static final String DEFAULT_MIN_SUMA = "AAAAAAAAAA";
    private static final String UPDATED_MIN_SUMA = "BBBBBBBBBB";

    private static final String DEFAULT_MAX_SUMA = "AAAAAAAAAA";
    private static final String UPDATED_MAX_SUMA = "BBBBBBBBBB";

    private static final Long DEFAULT_PORCENTAJE = 1L;
    private static final Long UPDATED_PORCENTAJE = 2L;

    private static final String ENTITY_API_URL = "/api/tcdescuentosumaaseguradas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idDescuentoSumaAsegurada}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCDESCUENTOSUMAASEGURADARepository tCDESCUENTOSUMAASEGURADARepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCDESCUENTOSUMAASEGURADAMockMvc;

    private TCDESCUENTOSUMAASEGURADA tCDESCUENTOSUMAASEGURADA;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCDESCUENTOSUMAASEGURADA createEntity(EntityManager em) {
        TCDESCUENTOSUMAASEGURADA tCDESCUENTOSUMAASEGURADA = new TCDESCUENTOSUMAASEGURADA()
            .minSuma(DEFAULT_MIN_SUMA)
            .maxSuma(DEFAULT_MAX_SUMA)
            .porcentaje(DEFAULT_PORCENTAJE);
        return tCDESCUENTOSUMAASEGURADA;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCDESCUENTOSUMAASEGURADA createUpdatedEntity(EntityManager em) {
        TCDESCUENTOSUMAASEGURADA tCDESCUENTOSUMAASEGURADA = new TCDESCUENTOSUMAASEGURADA()
            .minSuma(UPDATED_MIN_SUMA)
            .maxSuma(UPDATED_MAX_SUMA)
            .porcentaje(UPDATED_PORCENTAJE);
        return tCDESCUENTOSUMAASEGURADA;
    }

    @BeforeEach
    public void initTest() {
        tCDESCUENTOSUMAASEGURADA = createEntity(em);
    }

    @Test
    @Transactional
    void createTCDESCUENTOSUMAASEGURADA() throws Exception {
        int databaseSizeBeforeCreate = tCDESCUENTOSUMAASEGURADARepository.findAll().size();
        // Create the TCDESCUENTOSUMAASEGURADA
        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOSUMAASEGURADA))
            )
            .andExpect(status().isCreated());

        // Validate the TCDESCUENTOSUMAASEGURADA in the database
        List<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADAList = tCDESCUENTOSUMAASEGURADARepository.findAll();
        assertThat(tCDESCUENTOSUMAASEGURADAList).hasSize(databaseSizeBeforeCreate + 1);
        TCDESCUENTOSUMAASEGURADA testTCDESCUENTOSUMAASEGURADA = tCDESCUENTOSUMAASEGURADAList.get(tCDESCUENTOSUMAASEGURADAList.size() - 1);
        assertThat(testTCDESCUENTOSUMAASEGURADA.getMinSuma()).isEqualTo(DEFAULT_MIN_SUMA);
        assertThat(testTCDESCUENTOSUMAASEGURADA.getMaxSuma()).isEqualTo(DEFAULT_MAX_SUMA);
        assertThat(testTCDESCUENTOSUMAASEGURADA.getPorcentaje()).isEqualTo(DEFAULT_PORCENTAJE);
    }

    @Test
    @Transactional
    void createTCDESCUENTOSUMAASEGURADAWithExistingId() throws Exception {
        // Create the TCDESCUENTOSUMAASEGURADA with an existing ID
        tCDESCUENTOSUMAASEGURADA.setIdDescuentoSumaAsegurada(1L);

        int databaseSizeBeforeCreate = tCDESCUENTOSUMAASEGURADARepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCDESCUENTOSUMAASEGURADA in the database
        List<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADAList = tCDESCUENTOSUMAASEGURADARepository.findAll();
        assertThat(tCDESCUENTOSUMAASEGURADAList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkMinSumaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCDESCUENTOSUMAASEGURADARepository.findAll().size();
        // set the field null
        tCDESCUENTOSUMAASEGURADA.setMinSuma(null);

        // Create the TCDESCUENTOSUMAASEGURADA, which fails.

        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        List<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADAList = tCDESCUENTOSUMAASEGURADARepository.findAll();
        assertThat(tCDESCUENTOSUMAASEGURADAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMaxSumaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCDESCUENTOSUMAASEGURADARepository.findAll().size();
        // set the field null
        tCDESCUENTOSUMAASEGURADA.setMaxSuma(null);

        // Create the TCDESCUENTOSUMAASEGURADA, which fails.

        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        List<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADAList = tCDESCUENTOSUMAASEGURADARepository.findAll();
        assertThat(tCDESCUENTOSUMAASEGURADAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPorcentajeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCDESCUENTOSUMAASEGURADARepository.findAll().size();
        // set the field null
        tCDESCUENTOSUMAASEGURADA.setPorcentaje(null);

        // Create the TCDESCUENTOSUMAASEGURADA, which fails.

        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        List<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADAList = tCDESCUENTOSUMAASEGURADARepository.findAll();
        assertThat(tCDESCUENTOSUMAASEGURADAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCDESCUENTOSUMAASEGURADAS() throws Exception {
        // Initialize the database
        tCDESCUENTOSUMAASEGURADARepository.saveAndFlush(tCDESCUENTOSUMAASEGURADA);

        // Get all the tCDESCUENTOSUMAASEGURADAList
        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDescuentoSumaAsegurada,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                jsonPath("$.[*].idDescuentoSumaAsegurada").value(hasItem(tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada().intValue()))
            )
            .andExpect(jsonPath("$.[*].minSuma").value(hasItem(DEFAULT_MIN_SUMA)))
            .andExpect(jsonPath("$.[*].maxSuma").value(hasItem(DEFAULT_MAX_SUMA)))
            .andExpect(jsonPath("$.[*].porcentaje").value(hasItem(DEFAULT_PORCENTAJE.intValue())));
    }

    @Test
    @Transactional
    void getTCDESCUENTOSUMAASEGURADA() throws Exception {
        // Initialize the database
        tCDESCUENTOSUMAASEGURADARepository.saveAndFlush(tCDESCUENTOSUMAASEGURADA);

        // Get the tCDESCUENTOSUMAASEGURADA
        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(get(ENTITY_API_URL_ID, tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idDescuentoSumaAsegurada").value(tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada().intValue()))
            .andExpect(jsonPath("$.minSuma").value(DEFAULT_MIN_SUMA))
            .andExpect(jsonPath("$.maxSuma").value(DEFAULT_MAX_SUMA))
            .andExpect(jsonPath("$.porcentaje").value(DEFAULT_PORCENTAJE.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCDESCUENTOSUMAASEGURADA() throws Exception {
        // Get the tCDESCUENTOSUMAASEGURADA
        restTCDESCUENTOSUMAASEGURADAMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCDESCUENTOSUMAASEGURADA() throws Exception {
        // Initialize the database
        tCDESCUENTOSUMAASEGURADARepository.saveAndFlush(tCDESCUENTOSUMAASEGURADA);

        int databaseSizeBeforeUpdate = tCDESCUENTOSUMAASEGURADARepository.findAll().size();

        // Update the tCDESCUENTOSUMAASEGURADA
        TCDESCUENTOSUMAASEGURADA updatedTCDESCUENTOSUMAASEGURADA = tCDESCUENTOSUMAASEGURADARepository
            .findById(tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada())
            .get();
        // Disconnect from session so that the updates on updatedTCDESCUENTOSUMAASEGURADA are not directly saved in db
        em.detach(updatedTCDESCUENTOSUMAASEGURADA);
        updatedTCDESCUENTOSUMAASEGURADA.minSuma(UPDATED_MIN_SUMA).maxSuma(UPDATED_MAX_SUMA).porcentaje(UPDATED_PORCENTAJE);

        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCDESCUENTOSUMAASEGURADA))
            )
            .andExpect(status().isOk());

        // Validate the TCDESCUENTOSUMAASEGURADA in the database
        List<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADAList = tCDESCUENTOSUMAASEGURADARepository.findAll();
        assertThat(tCDESCUENTOSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
        TCDESCUENTOSUMAASEGURADA testTCDESCUENTOSUMAASEGURADA = tCDESCUENTOSUMAASEGURADAList.get(tCDESCUENTOSUMAASEGURADAList.size() - 1);
        assertThat(testTCDESCUENTOSUMAASEGURADA.getMinSuma()).isEqualTo(UPDATED_MIN_SUMA);
        assertThat(testTCDESCUENTOSUMAASEGURADA.getMaxSuma()).isEqualTo(UPDATED_MAX_SUMA);
        assertThat(testTCDESCUENTOSUMAASEGURADA.getPorcentaje()).isEqualTo(UPDATED_PORCENTAJE);
    }

    @Test
    @Transactional
    void putNonExistingTCDESCUENTOSUMAASEGURADA() throws Exception {
        int databaseSizeBeforeUpdate = tCDESCUENTOSUMAASEGURADARepository.findAll().size();
        tCDESCUENTOSUMAASEGURADA.setIdDescuentoSumaAsegurada(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCDESCUENTOSUMAASEGURADA in the database
        List<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADAList = tCDESCUENTOSUMAASEGURADARepository.findAll();
        assertThat(tCDESCUENTOSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCDESCUENTOSUMAASEGURADA() throws Exception {
        int databaseSizeBeforeUpdate = tCDESCUENTOSUMAASEGURADARepository.findAll().size();
        tCDESCUENTOSUMAASEGURADA.setIdDescuentoSumaAsegurada(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCDESCUENTOSUMAASEGURADA in the database
        List<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADAList = tCDESCUENTOSUMAASEGURADARepository.findAll();
        assertThat(tCDESCUENTOSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCDESCUENTOSUMAASEGURADA() throws Exception {
        int databaseSizeBeforeUpdate = tCDESCUENTOSUMAASEGURADARepository.findAll().size();
        tCDESCUENTOSUMAASEGURADA.setIdDescuentoSumaAsegurada(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOSUMAASEGURADA))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCDESCUENTOSUMAASEGURADA in the database
        List<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADAList = tCDESCUENTOSUMAASEGURADARepository.findAll();
        assertThat(tCDESCUENTOSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCDESCUENTOSUMAASEGURADAWithPatch() throws Exception {
        // Initialize the database
        tCDESCUENTOSUMAASEGURADARepository.saveAndFlush(tCDESCUENTOSUMAASEGURADA);

        int databaseSizeBeforeUpdate = tCDESCUENTOSUMAASEGURADARepository.findAll().size();

        // Update the tCDESCUENTOSUMAASEGURADA using partial update
        TCDESCUENTOSUMAASEGURADA partialUpdatedTCDESCUENTOSUMAASEGURADA = new TCDESCUENTOSUMAASEGURADA();
        partialUpdatedTCDESCUENTOSUMAASEGURADA.setIdDescuentoSumaAsegurada(tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada());

        partialUpdatedTCDESCUENTOSUMAASEGURADA.maxSuma(UPDATED_MAX_SUMA).porcentaje(UPDATED_PORCENTAJE);

        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCDESCUENTOSUMAASEGURADA))
            )
            .andExpect(status().isOk());

        // Validate the TCDESCUENTOSUMAASEGURADA in the database
        List<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADAList = tCDESCUENTOSUMAASEGURADARepository.findAll();
        assertThat(tCDESCUENTOSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
        TCDESCUENTOSUMAASEGURADA testTCDESCUENTOSUMAASEGURADA = tCDESCUENTOSUMAASEGURADAList.get(tCDESCUENTOSUMAASEGURADAList.size() - 1);
        assertThat(testTCDESCUENTOSUMAASEGURADA.getMinSuma()).isEqualTo(DEFAULT_MIN_SUMA);
        assertThat(testTCDESCUENTOSUMAASEGURADA.getMaxSuma()).isEqualTo(UPDATED_MAX_SUMA);
        assertThat(testTCDESCUENTOSUMAASEGURADA.getPorcentaje()).isEqualTo(UPDATED_PORCENTAJE);
    }

    @Test
    @Transactional
    void fullUpdateTCDESCUENTOSUMAASEGURADAWithPatch() throws Exception {
        // Initialize the database
        tCDESCUENTOSUMAASEGURADARepository.saveAndFlush(tCDESCUENTOSUMAASEGURADA);

        int databaseSizeBeforeUpdate = tCDESCUENTOSUMAASEGURADARepository.findAll().size();

        // Update the tCDESCUENTOSUMAASEGURADA using partial update
        TCDESCUENTOSUMAASEGURADA partialUpdatedTCDESCUENTOSUMAASEGURADA = new TCDESCUENTOSUMAASEGURADA();
        partialUpdatedTCDESCUENTOSUMAASEGURADA.setIdDescuentoSumaAsegurada(tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada());

        partialUpdatedTCDESCUENTOSUMAASEGURADA.minSuma(UPDATED_MIN_SUMA).maxSuma(UPDATED_MAX_SUMA).porcentaje(UPDATED_PORCENTAJE);

        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCDESCUENTOSUMAASEGURADA))
            )
            .andExpect(status().isOk());

        // Validate the TCDESCUENTOSUMAASEGURADA in the database
        List<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADAList = tCDESCUENTOSUMAASEGURADARepository.findAll();
        assertThat(tCDESCUENTOSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
        TCDESCUENTOSUMAASEGURADA testTCDESCUENTOSUMAASEGURADA = tCDESCUENTOSUMAASEGURADAList.get(tCDESCUENTOSUMAASEGURADAList.size() - 1);
        assertThat(testTCDESCUENTOSUMAASEGURADA.getMinSuma()).isEqualTo(UPDATED_MIN_SUMA);
        assertThat(testTCDESCUENTOSUMAASEGURADA.getMaxSuma()).isEqualTo(UPDATED_MAX_SUMA);
        assertThat(testTCDESCUENTOSUMAASEGURADA.getPorcentaje()).isEqualTo(UPDATED_PORCENTAJE);
    }

    @Test
    @Transactional
    void patchNonExistingTCDESCUENTOSUMAASEGURADA() throws Exception {
        int databaseSizeBeforeUpdate = tCDESCUENTOSUMAASEGURADARepository.findAll().size();
        tCDESCUENTOSUMAASEGURADA.setIdDescuentoSumaAsegurada(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCDESCUENTOSUMAASEGURADA in the database
        List<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADAList = tCDESCUENTOSUMAASEGURADARepository.findAll();
        assertThat(tCDESCUENTOSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCDESCUENTOSUMAASEGURADA() throws Exception {
        int databaseSizeBeforeUpdate = tCDESCUENTOSUMAASEGURADARepository.findAll().size();
        tCDESCUENTOSUMAASEGURADA.setIdDescuentoSumaAsegurada(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOSUMAASEGURADA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCDESCUENTOSUMAASEGURADA in the database
        List<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADAList = tCDESCUENTOSUMAASEGURADARepository.findAll();
        assertThat(tCDESCUENTOSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCDESCUENTOSUMAASEGURADA() throws Exception {
        int databaseSizeBeforeUpdate = tCDESCUENTOSUMAASEGURADARepository.findAll().size();
        tCDESCUENTOSUMAASEGURADA.setIdDescuentoSumaAsegurada(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOSUMAASEGURADA))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCDESCUENTOSUMAASEGURADA in the database
        List<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADAList = tCDESCUENTOSUMAASEGURADARepository.findAll();
        assertThat(tCDESCUENTOSUMAASEGURADAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCDESCUENTOSUMAASEGURADA() throws Exception {
        // Initialize the database
        tCDESCUENTOSUMAASEGURADARepository.saveAndFlush(tCDESCUENTOSUMAASEGURADA);

        int databaseSizeBeforeDelete = tCDESCUENTOSUMAASEGURADARepository.findAll().size();

        // Delete the tCDESCUENTOSUMAASEGURADA
        restTCDESCUENTOSUMAASEGURADAMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCDESCUENTOSUMAASEGURADA.getIdDescuentoSumaAsegurada()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCDESCUENTOSUMAASEGURADA> tCDESCUENTOSUMAASEGURADAList = tCDESCUENTOSUMAASEGURADARepository.findAll();
        assertThat(tCDESCUENTOSUMAASEGURADAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
