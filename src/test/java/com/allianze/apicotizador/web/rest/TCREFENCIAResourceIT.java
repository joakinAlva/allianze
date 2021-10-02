package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCREFENCIA;
import com.allianze.apicotizador.repository.TCREFENCIARepository;
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
 * Integration tests for the {@link TCREFENCIAResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCREFENCIAResourceIT {

    private static final String DEFAULT_PERIODO = "AAAAAAAAAA";
    private static final String UPDATED_PERIODO = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCIA = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCIA = "BBBBBBBBBB";

    private static final String DEFAULT_EDADPROMEDIO = "AAAAAAAAAA";
    private static final String UPDATED_EDADPROMEDIO = "BBBBBBBBBB";

    private static final String DEFAULT_CUOTA = "AAAAAAAAAA";
    private static final String UPDATED_CUOTA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tcrefencias";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idReferencia}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCREFENCIARepository tCREFENCIARepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCREFENCIAMockMvc;

    private TCREFENCIA tCREFENCIA;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCREFENCIA createEntity(EntityManager em) {
        TCREFENCIA tCREFENCIA = new TCREFENCIA()
            .periodo(DEFAULT_PERIODO)
            .referencia(DEFAULT_REFERENCIA)
            .edadpromedio(DEFAULT_EDADPROMEDIO)
            .cuota(DEFAULT_CUOTA);
        return tCREFENCIA;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCREFENCIA createUpdatedEntity(EntityManager em) {
        TCREFENCIA tCREFENCIA = new TCREFENCIA()
            .periodo(UPDATED_PERIODO)
            .referencia(UPDATED_REFERENCIA)
            .edadpromedio(UPDATED_EDADPROMEDIO)
            .cuota(UPDATED_CUOTA);
        return tCREFENCIA;
    }

    @BeforeEach
    public void initTest() {
        tCREFENCIA = createEntity(em);
    }

    @Test
    @Transactional
    void createTCREFENCIA() throws Exception {
        int databaseSizeBeforeCreate = tCREFENCIARepository.findAll().size();
        // Create the TCREFENCIA
        restTCREFENCIAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCREFENCIA)))
            .andExpect(status().isCreated());

        // Validate the TCREFENCIA in the database
        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeCreate + 1);
        TCREFENCIA testTCREFENCIA = tCREFENCIAList.get(tCREFENCIAList.size() - 1);
        assertThat(testTCREFENCIA.getPeriodo()).isEqualTo(DEFAULT_PERIODO);
        assertThat(testTCREFENCIA.getReferencia()).isEqualTo(DEFAULT_REFERENCIA);
        assertThat(testTCREFENCIA.getEdadpromedio()).isEqualTo(DEFAULT_EDADPROMEDIO);
        assertThat(testTCREFENCIA.getCuota()).isEqualTo(DEFAULT_CUOTA);
    }

    @Test
    @Transactional
    void createTCREFENCIAWithExistingId() throws Exception {
        // Create the TCREFENCIA with an existing ID
        tCREFENCIA.setIdReferencia(1L);

        int databaseSizeBeforeCreate = tCREFENCIARepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCREFENCIAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCREFENCIA)))
            .andExpect(status().isBadRequest());

        // Validate the TCREFENCIA in the database
        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPeriodoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCREFENCIARepository.findAll().size();
        // set the field null
        tCREFENCIA.setPeriodo(null);

        // Create the TCREFENCIA, which fails.

        restTCREFENCIAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCREFENCIA)))
            .andExpect(status().isBadRequest());

        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReferenciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCREFENCIARepository.findAll().size();
        // set the field null
        tCREFENCIA.setReferencia(null);

        // Create the TCREFENCIA, which fails.

        restTCREFENCIAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCREFENCIA)))
            .andExpect(status().isBadRequest());

        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEdadpromedioIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCREFENCIARepository.findAll().size();
        // set the field null
        tCREFENCIA.setEdadpromedio(null);

        // Create the TCREFENCIA, which fails.

        restTCREFENCIAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCREFENCIA)))
            .andExpect(status().isBadRequest());

        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCuotaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCREFENCIARepository.findAll().size();
        // set the field null
        tCREFENCIA.setCuota(null);

        // Create the TCREFENCIA, which fails.

        restTCREFENCIAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCREFENCIA)))
            .andExpect(status().isBadRequest());

        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCREFENCIAS() throws Exception {
        // Initialize the database
        tCREFENCIARepository.saveAndFlush(tCREFENCIA);

        // Get all the tCREFENCIAList
        restTCREFENCIAMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idReferencia,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idReferencia").value(hasItem(tCREFENCIA.getIdReferencia().intValue())))
            .andExpect(jsonPath("$.[*].periodo").value(hasItem(DEFAULT_PERIODO)))
            .andExpect(jsonPath("$.[*].referencia").value(hasItem(DEFAULT_REFERENCIA)))
            .andExpect(jsonPath("$.[*].edadpromedio").value(hasItem(DEFAULT_EDADPROMEDIO)))
            .andExpect(jsonPath("$.[*].cuota").value(hasItem(DEFAULT_CUOTA)));
    }

    @Test
    @Transactional
    void getTCREFENCIA() throws Exception {
        // Initialize the database
        tCREFENCIARepository.saveAndFlush(tCREFENCIA);

        // Get the tCREFENCIA
        restTCREFENCIAMockMvc
            .perform(get(ENTITY_API_URL_ID, tCREFENCIA.getIdReferencia()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idReferencia").value(tCREFENCIA.getIdReferencia().intValue()))
            .andExpect(jsonPath("$.periodo").value(DEFAULT_PERIODO))
            .andExpect(jsonPath("$.referencia").value(DEFAULT_REFERENCIA))
            .andExpect(jsonPath("$.edadpromedio").value(DEFAULT_EDADPROMEDIO))
            .andExpect(jsonPath("$.cuota").value(DEFAULT_CUOTA));
    }

    @Test
    @Transactional
    void getNonExistingTCREFENCIA() throws Exception {
        // Get the tCREFENCIA
        restTCREFENCIAMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCREFENCIA() throws Exception {
        // Initialize the database
        tCREFENCIARepository.saveAndFlush(tCREFENCIA);

        int databaseSizeBeforeUpdate = tCREFENCIARepository.findAll().size();

        // Update the tCREFENCIA
        TCREFENCIA updatedTCREFENCIA = tCREFENCIARepository.findById(tCREFENCIA.getIdReferencia()).get();
        // Disconnect from session so that the updates on updatedTCREFENCIA are not directly saved in db
        em.detach(updatedTCREFENCIA);
        updatedTCREFENCIA.periodo(UPDATED_PERIODO).referencia(UPDATED_REFERENCIA).edadpromedio(UPDATED_EDADPROMEDIO).cuota(UPDATED_CUOTA);

        restTCREFENCIAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCREFENCIA.getIdReferencia())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCREFENCIA))
            )
            .andExpect(status().isOk());

        // Validate the TCREFENCIA in the database
        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeUpdate);
        TCREFENCIA testTCREFENCIA = tCREFENCIAList.get(tCREFENCIAList.size() - 1);
        assertThat(testTCREFENCIA.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testTCREFENCIA.getReferencia()).isEqualTo(UPDATED_REFERENCIA);
        assertThat(testTCREFENCIA.getEdadpromedio()).isEqualTo(UPDATED_EDADPROMEDIO);
        assertThat(testTCREFENCIA.getCuota()).isEqualTo(UPDATED_CUOTA);
    }

    @Test
    @Transactional
    void putNonExistingTCREFENCIA() throws Exception {
        int databaseSizeBeforeUpdate = tCREFENCIARepository.findAll().size();
        tCREFENCIA.setIdReferencia(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCREFENCIAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCREFENCIA.getIdReferencia())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCREFENCIA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCREFENCIA in the database
        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCREFENCIA() throws Exception {
        int databaseSizeBeforeUpdate = tCREFENCIARepository.findAll().size();
        tCREFENCIA.setIdReferencia(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCREFENCIAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCREFENCIA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCREFENCIA in the database
        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCREFENCIA() throws Exception {
        int databaseSizeBeforeUpdate = tCREFENCIARepository.findAll().size();
        tCREFENCIA.setIdReferencia(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCREFENCIAMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCREFENCIA)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCREFENCIA in the database
        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCREFENCIAWithPatch() throws Exception {
        // Initialize the database
        tCREFENCIARepository.saveAndFlush(tCREFENCIA);

        int databaseSizeBeforeUpdate = tCREFENCIARepository.findAll().size();

        // Update the tCREFENCIA using partial update
        TCREFENCIA partialUpdatedTCREFENCIA = new TCREFENCIA();
        partialUpdatedTCREFENCIA.setIdReferencia(tCREFENCIA.getIdReferencia());

        restTCREFENCIAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCREFENCIA.getIdReferencia())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCREFENCIA))
            )
            .andExpect(status().isOk());

        // Validate the TCREFENCIA in the database
        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeUpdate);
        TCREFENCIA testTCREFENCIA = tCREFENCIAList.get(tCREFENCIAList.size() - 1);
        assertThat(testTCREFENCIA.getPeriodo()).isEqualTo(DEFAULT_PERIODO);
        assertThat(testTCREFENCIA.getReferencia()).isEqualTo(DEFAULT_REFERENCIA);
        assertThat(testTCREFENCIA.getEdadpromedio()).isEqualTo(DEFAULT_EDADPROMEDIO);
        assertThat(testTCREFENCIA.getCuota()).isEqualTo(DEFAULT_CUOTA);
    }

    @Test
    @Transactional
    void fullUpdateTCREFENCIAWithPatch() throws Exception {
        // Initialize the database
        tCREFENCIARepository.saveAndFlush(tCREFENCIA);

        int databaseSizeBeforeUpdate = tCREFENCIARepository.findAll().size();

        // Update the tCREFENCIA using partial update
        TCREFENCIA partialUpdatedTCREFENCIA = new TCREFENCIA();
        partialUpdatedTCREFENCIA.setIdReferencia(tCREFENCIA.getIdReferencia());

        partialUpdatedTCREFENCIA
            .periodo(UPDATED_PERIODO)
            .referencia(UPDATED_REFERENCIA)
            .edadpromedio(UPDATED_EDADPROMEDIO)
            .cuota(UPDATED_CUOTA);

        restTCREFENCIAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCREFENCIA.getIdReferencia())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCREFENCIA))
            )
            .andExpect(status().isOk());

        // Validate the TCREFENCIA in the database
        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeUpdate);
        TCREFENCIA testTCREFENCIA = tCREFENCIAList.get(tCREFENCIAList.size() - 1);
        assertThat(testTCREFENCIA.getPeriodo()).isEqualTo(UPDATED_PERIODO);
        assertThat(testTCREFENCIA.getReferencia()).isEqualTo(UPDATED_REFERENCIA);
        assertThat(testTCREFENCIA.getEdadpromedio()).isEqualTo(UPDATED_EDADPROMEDIO);
        assertThat(testTCREFENCIA.getCuota()).isEqualTo(UPDATED_CUOTA);
    }

    @Test
    @Transactional
    void patchNonExistingTCREFENCIA() throws Exception {
        int databaseSizeBeforeUpdate = tCREFENCIARepository.findAll().size();
        tCREFENCIA.setIdReferencia(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCREFENCIAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCREFENCIA.getIdReferencia())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCREFENCIA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCREFENCIA in the database
        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCREFENCIA() throws Exception {
        int databaseSizeBeforeUpdate = tCREFENCIARepository.findAll().size();
        tCREFENCIA.setIdReferencia(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCREFENCIAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCREFENCIA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCREFENCIA in the database
        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCREFENCIA() throws Exception {
        int databaseSizeBeforeUpdate = tCREFENCIARepository.findAll().size();
        tCREFENCIA.setIdReferencia(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCREFENCIAMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCREFENCIA))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCREFENCIA in the database
        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCREFENCIA() throws Exception {
        // Initialize the database
        tCREFENCIARepository.saveAndFlush(tCREFENCIA);

        int databaseSizeBeforeDelete = tCREFENCIARepository.findAll().size();

        // Delete the tCREFENCIA
        restTCREFENCIAMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCREFENCIA.getIdReferencia()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCREFENCIA> tCREFENCIAList = tCREFENCIARepository.findAll();
        assertThat(tCREFENCIAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
