package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCCOVID;
import com.allianze.apicotizador.repository.TCCOVIDRepository;
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
 * Integration tests for the {@link TCCOVIDResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCCOVIDResourceIT {

    private static final String DEFAULT_EDAD = "AAAAAAAAAA";
    private static final String UPDATED_EDAD = "BBBBBBBBBB";

    private static final Long DEFAULT_BASICA = 1L;
    private static final Long UPDATED_BASICA = 2L;

    private static final Long DEFAULT_RECARGO_EDAD = 1L;
    private static final Long UPDATED_RECARGO_EDAD = 2L;

    private static final Long DEFAULT_RECARGO_GIRO = 1L;
    private static final Long UPDATED_RECARGO_GIRO = 2L;

    private static final Long DEFAULT_RECARGO_TOTAL = 1L;
    private static final Long UPDATED_RECARGO_TOTAL = 2L;

    private static final Long DEFAULT_BASICA_RECARGADA = 1L;
    private static final Long UPDATED_BASICA_RECARGADA = 2L;

    private static final String ENTITY_API_URL = "/api/tccovids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCovid}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCCOVIDRepository tCCOVIDRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCCOVIDMockMvc;

    private TCCOVID tCCOVID;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCOVID createEntity(EntityManager em) {
        TCCOVID tCCOVID = new TCCOVID()
            .edad(DEFAULT_EDAD)
            .basica(DEFAULT_BASICA)
            .recargoEdad(DEFAULT_RECARGO_EDAD)
            .recargoGiro(DEFAULT_RECARGO_GIRO)
            .recargoTotal(DEFAULT_RECARGO_TOTAL)
            .basicaRecargada(DEFAULT_BASICA_RECARGADA);
        return tCCOVID;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCOVID createUpdatedEntity(EntityManager em) {
        TCCOVID tCCOVID = new TCCOVID()
            .edad(UPDATED_EDAD)
            .basica(UPDATED_BASICA)
            .recargoEdad(UPDATED_RECARGO_EDAD)
            .recargoGiro(UPDATED_RECARGO_GIRO)
            .recargoTotal(UPDATED_RECARGO_TOTAL)
            .basicaRecargada(UPDATED_BASICA_RECARGADA);
        return tCCOVID;
    }

    @BeforeEach
    public void initTest() {
        tCCOVID = createEntity(em);
    }

    @Test
    @Transactional
    void createTCCOVID() throws Exception {
        int databaseSizeBeforeCreate = tCCOVIDRepository.findAll().size();
        // Create the TCCOVID
        restTCCOVIDMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVID)))
            .andExpect(status().isCreated());

        // Validate the TCCOVID in the database
        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeCreate + 1);
        TCCOVID testTCCOVID = tCCOVIDList.get(tCCOVIDList.size() - 1);
        assertThat(testTCCOVID.getEdad()).isEqualTo(DEFAULT_EDAD);
        assertThat(testTCCOVID.getBasica()).isEqualTo(DEFAULT_BASICA);
        assertThat(testTCCOVID.getRecargoEdad()).isEqualTo(DEFAULT_RECARGO_EDAD);
        assertThat(testTCCOVID.getRecargoGiro()).isEqualTo(DEFAULT_RECARGO_GIRO);
        assertThat(testTCCOVID.getRecargoTotal()).isEqualTo(DEFAULT_RECARGO_TOTAL);
        assertThat(testTCCOVID.getBasicaRecargada()).isEqualTo(DEFAULT_BASICA_RECARGADA);
    }

    @Test
    @Transactional
    void createTCCOVIDWithExistingId() throws Exception {
        // Create the TCCOVID with an existing ID
        tCCOVID.setIdCovid(1L);

        int databaseSizeBeforeCreate = tCCOVIDRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCCOVIDMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVID)))
            .andExpect(status().isBadRequest());

        // Validate the TCCOVID in the database
        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEdadIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDRepository.findAll().size();
        // set the field null
        tCCOVID.setEdad(null);

        // Create the TCCOVID, which fails.

        restTCCOVIDMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVID)))
            .andExpect(status().isBadRequest());

        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBasicaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDRepository.findAll().size();
        // set the field null
        tCCOVID.setBasica(null);

        // Create the TCCOVID, which fails.

        restTCCOVIDMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVID)))
            .andExpect(status().isBadRequest());

        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRecargoEdadIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDRepository.findAll().size();
        // set the field null
        tCCOVID.setRecargoEdad(null);

        // Create the TCCOVID, which fails.

        restTCCOVIDMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVID)))
            .andExpect(status().isBadRequest());

        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRecargoGiroIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDRepository.findAll().size();
        // set the field null
        tCCOVID.setRecargoGiro(null);

        // Create the TCCOVID, which fails.

        restTCCOVIDMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVID)))
            .andExpect(status().isBadRequest());

        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRecargoTotalIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDRepository.findAll().size();
        // set the field null
        tCCOVID.setRecargoTotal(null);

        // Create the TCCOVID, which fails.

        restTCCOVIDMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVID)))
            .andExpect(status().isBadRequest());

        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBasicaRecargadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCOVIDRepository.findAll().size();
        // set the field null
        tCCOVID.setBasicaRecargada(null);

        // Create the TCCOVID, which fails.

        restTCCOVIDMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVID)))
            .andExpect(status().isBadRequest());

        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCCOVIDS() throws Exception {
        // Initialize the database
        tCCOVIDRepository.saveAndFlush(tCCOVID);

        // Get all the tCCOVIDList
        restTCCOVIDMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCovid,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCovid").value(hasItem(tCCOVID.getIdCovid().intValue())))
            .andExpect(jsonPath("$.[*].edad").value(hasItem(DEFAULT_EDAD)))
            .andExpect(jsonPath("$.[*].basica").value(hasItem(DEFAULT_BASICA.intValue())))
            .andExpect(jsonPath("$.[*].recargoEdad").value(hasItem(DEFAULT_RECARGO_EDAD.intValue())))
            .andExpect(jsonPath("$.[*].recargoGiro").value(hasItem(DEFAULT_RECARGO_GIRO.intValue())))
            .andExpect(jsonPath("$.[*].recargoTotal").value(hasItem(DEFAULT_RECARGO_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].basicaRecargada").value(hasItem(DEFAULT_BASICA_RECARGADA.intValue())));
    }

    @Test
    @Transactional
    void getTCCOVID() throws Exception {
        // Initialize the database
        tCCOVIDRepository.saveAndFlush(tCCOVID);

        // Get the tCCOVID
        restTCCOVIDMockMvc
            .perform(get(ENTITY_API_URL_ID, tCCOVID.getIdCovid()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCovid").value(tCCOVID.getIdCovid().intValue()))
            .andExpect(jsonPath("$.edad").value(DEFAULT_EDAD))
            .andExpect(jsonPath("$.basica").value(DEFAULT_BASICA.intValue()))
            .andExpect(jsonPath("$.recargoEdad").value(DEFAULT_RECARGO_EDAD.intValue()))
            .andExpect(jsonPath("$.recargoGiro").value(DEFAULT_RECARGO_GIRO.intValue()))
            .andExpect(jsonPath("$.recargoTotal").value(DEFAULT_RECARGO_TOTAL.intValue()))
            .andExpect(jsonPath("$.basicaRecargada").value(DEFAULT_BASICA_RECARGADA.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCCOVID() throws Exception {
        // Get the tCCOVID
        restTCCOVIDMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCCOVID() throws Exception {
        // Initialize the database
        tCCOVIDRepository.saveAndFlush(tCCOVID);

        int databaseSizeBeforeUpdate = tCCOVIDRepository.findAll().size();

        // Update the tCCOVID
        TCCOVID updatedTCCOVID = tCCOVIDRepository.findById(tCCOVID.getIdCovid()).get();
        // Disconnect from session so that the updates on updatedTCCOVID are not directly saved in db
        em.detach(updatedTCCOVID);
        updatedTCCOVID
            .edad(UPDATED_EDAD)
            .basica(UPDATED_BASICA)
            .recargoEdad(UPDATED_RECARGO_EDAD)
            .recargoGiro(UPDATED_RECARGO_GIRO)
            .recargoTotal(UPDATED_RECARGO_TOTAL)
            .basicaRecargada(UPDATED_BASICA_RECARGADA);

        restTCCOVIDMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCCOVID.getIdCovid())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCCOVID))
            )
            .andExpect(status().isOk());

        // Validate the TCCOVID in the database
        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeUpdate);
        TCCOVID testTCCOVID = tCCOVIDList.get(tCCOVIDList.size() - 1);
        assertThat(testTCCOVID.getEdad()).isEqualTo(UPDATED_EDAD);
        assertThat(testTCCOVID.getBasica()).isEqualTo(UPDATED_BASICA);
        assertThat(testTCCOVID.getRecargoEdad()).isEqualTo(UPDATED_RECARGO_EDAD);
        assertThat(testTCCOVID.getRecargoGiro()).isEqualTo(UPDATED_RECARGO_GIRO);
        assertThat(testTCCOVID.getRecargoTotal()).isEqualTo(UPDATED_RECARGO_TOTAL);
        assertThat(testTCCOVID.getBasicaRecargada()).isEqualTo(UPDATED_BASICA_RECARGADA);
    }

    @Test
    @Transactional
    void putNonExistingTCCOVID() throws Exception {
        int databaseSizeBeforeUpdate = tCCOVIDRepository.findAll().size();
        tCCOVID.setIdCovid(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCOVIDMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCCOVID.getIdCovid())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCOVID))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOVID in the database
        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCCOVID() throws Exception {
        int databaseSizeBeforeUpdate = tCCOVIDRepository.findAll().size();
        tCCOVID.setIdCovid(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOVIDMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCOVID))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOVID in the database
        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCCOVID() throws Exception {
        int databaseSizeBeforeUpdate = tCCOVIDRepository.findAll().size();
        tCCOVID.setIdCovid(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOVIDMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCOVID)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCOVID in the database
        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCCOVIDWithPatch() throws Exception {
        // Initialize the database
        tCCOVIDRepository.saveAndFlush(tCCOVID);

        int databaseSizeBeforeUpdate = tCCOVIDRepository.findAll().size();

        // Update the tCCOVID using partial update
        TCCOVID partialUpdatedTCCOVID = new TCCOVID();
        partialUpdatedTCCOVID.setIdCovid(tCCOVID.getIdCovid());

        partialUpdatedTCCOVID.recargoEdad(UPDATED_RECARGO_EDAD).recargoGiro(UPDATED_RECARGO_GIRO).recargoTotal(UPDATED_RECARGO_TOTAL);

        restTCCOVIDMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCOVID.getIdCovid())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCOVID))
            )
            .andExpect(status().isOk());

        // Validate the TCCOVID in the database
        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeUpdate);
        TCCOVID testTCCOVID = tCCOVIDList.get(tCCOVIDList.size() - 1);
        assertThat(testTCCOVID.getEdad()).isEqualTo(DEFAULT_EDAD);
        assertThat(testTCCOVID.getBasica()).isEqualTo(DEFAULT_BASICA);
        assertThat(testTCCOVID.getRecargoEdad()).isEqualTo(UPDATED_RECARGO_EDAD);
        assertThat(testTCCOVID.getRecargoGiro()).isEqualTo(UPDATED_RECARGO_GIRO);
        assertThat(testTCCOVID.getRecargoTotal()).isEqualTo(UPDATED_RECARGO_TOTAL);
        assertThat(testTCCOVID.getBasicaRecargada()).isEqualTo(DEFAULT_BASICA_RECARGADA);
    }

    @Test
    @Transactional
    void fullUpdateTCCOVIDWithPatch() throws Exception {
        // Initialize the database
        tCCOVIDRepository.saveAndFlush(tCCOVID);

        int databaseSizeBeforeUpdate = tCCOVIDRepository.findAll().size();

        // Update the tCCOVID using partial update
        TCCOVID partialUpdatedTCCOVID = new TCCOVID();
        partialUpdatedTCCOVID.setIdCovid(tCCOVID.getIdCovid());

        partialUpdatedTCCOVID
            .edad(UPDATED_EDAD)
            .basica(UPDATED_BASICA)
            .recargoEdad(UPDATED_RECARGO_EDAD)
            .recargoGiro(UPDATED_RECARGO_GIRO)
            .recargoTotal(UPDATED_RECARGO_TOTAL)
            .basicaRecargada(UPDATED_BASICA_RECARGADA);

        restTCCOVIDMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCOVID.getIdCovid())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCOVID))
            )
            .andExpect(status().isOk());

        // Validate the TCCOVID in the database
        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeUpdate);
        TCCOVID testTCCOVID = tCCOVIDList.get(tCCOVIDList.size() - 1);
        assertThat(testTCCOVID.getEdad()).isEqualTo(UPDATED_EDAD);
        assertThat(testTCCOVID.getBasica()).isEqualTo(UPDATED_BASICA);
        assertThat(testTCCOVID.getRecargoEdad()).isEqualTo(UPDATED_RECARGO_EDAD);
        assertThat(testTCCOVID.getRecargoGiro()).isEqualTo(UPDATED_RECARGO_GIRO);
        assertThat(testTCCOVID.getRecargoTotal()).isEqualTo(UPDATED_RECARGO_TOTAL);
        assertThat(testTCCOVID.getBasicaRecargada()).isEqualTo(UPDATED_BASICA_RECARGADA);
    }

    @Test
    @Transactional
    void patchNonExistingTCCOVID() throws Exception {
        int databaseSizeBeforeUpdate = tCCOVIDRepository.findAll().size();
        tCCOVID.setIdCovid(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCOVIDMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCCOVID.getIdCovid())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCOVID))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOVID in the database
        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCCOVID() throws Exception {
        int databaseSizeBeforeUpdate = tCCOVIDRepository.findAll().size();
        tCCOVID.setIdCovid(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOVIDMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCOVID))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCOVID in the database
        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCCOVID() throws Exception {
        int databaseSizeBeforeUpdate = tCCOVIDRepository.findAll().size();
        tCCOVID.setIdCovid(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCOVIDMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCCOVID)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCOVID in the database
        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCCOVID() throws Exception {
        // Initialize the database
        tCCOVIDRepository.saveAndFlush(tCCOVID);

        int databaseSizeBeforeDelete = tCCOVIDRepository.findAll().size();

        // Delete the tCCOVID
        restTCCOVIDMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCCOVID.getIdCovid()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCCOVID> tCCOVIDList = tCCOVIDRepository.findAll();
        assertThat(tCCOVIDList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
