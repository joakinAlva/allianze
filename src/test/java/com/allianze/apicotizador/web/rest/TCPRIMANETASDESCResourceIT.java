package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCPRIMANETASDESC;
import com.allianze.apicotizador.repository.TCPRIMANETASDESCRepository;
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
 * Integration tests for the {@link TCPRIMANETASDESCResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCPRIMANETASDESCResourceIT {

    private static final String DEFAULT_PRIMA_NETA_SDESC = "AAAAAAAAAA";
    private static final String UPDATED_PRIMA_NETA_SDESC = "BBBBBBBBBB";

    private static final Long DEFAULT_MARGEN_MIN = 1L;
    private static final Long UPDATED_MARGEN_MIN = 2L;

    private static final Long DEFAULT_MARGEN_MAX = 1L;
    private static final Long UPDATED_MARGEN_MAX = 2L;

    private static final Long DEFAULT_MAX_COM_SD = 1L;
    private static final Long UPDATED_MAX_COM_SD = 2L;

    private static final Long DEFAULT_MAX_COM_EP = 1L;
    private static final Long UPDATED_MAX_COM_EP = 2L;

    private static final String ENTITY_API_URL = "/api/tcprimanetasdescs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idPrimaNetaSdesc}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCPRIMANETASDESCRepository tCPRIMANETASDESCRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCPRIMANETASDESCMockMvc;

    private TCPRIMANETASDESC tCPRIMANETASDESC;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCPRIMANETASDESC createEntity(EntityManager em) {
        TCPRIMANETASDESC tCPRIMANETASDESC = new TCPRIMANETASDESC()
            .primaNetaSdesc(DEFAULT_PRIMA_NETA_SDESC)
            .margenMin(DEFAULT_MARGEN_MIN)
            .margenMax(DEFAULT_MARGEN_MAX)
            .maxComSd(DEFAULT_MAX_COM_SD)
            .maxComEp(DEFAULT_MAX_COM_EP);
        return tCPRIMANETASDESC;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCPRIMANETASDESC createUpdatedEntity(EntityManager em) {
        TCPRIMANETASDESC tCPRIMANETASDESC = new TCPRIMANETASDESC()
            .primaNetaSdesc(UPDATED_PRIMA_NETA_SDESC)
            .margenMin(UPDATED_MARGEN_MIN)
            .margenMax(UPDATED_MARGEN_MAX)
            .maxComSd(UPDATED_MAX_COM_SD)
            .maxComEp(UPDATED_MAX_COM_EP);
        return tCPRIMANETASDESC;
    }

    @BeforeEach
    public void initTest() {
        tCPRIMANETASDESC = createEntity(em);
    }

    @Test
    @Transactional
    void createTCPRIMANETASDESC() throws Exception {
        int databaseSizeBeforeCreate = tCPRIMANETASDESCRepository.findAll().size();
        // Create the TCPRIMANETASDESC
        restTCPRIMANETASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPRIMANETASDESC))
            )
            .andExpect(status().isCreated());

        // Validate the TCPRIMANETASDESC in the database
        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeCreate + 1);
        TCPRIMANETASDESC testTCPRIMANETASDESC = tCPRIMANETASDESCList.get(tCPRIMANETASDESCList.size() - 1);
        assertThat(testTCPRIMANETASDESC.getPrimaNetaSdesc()).isEqualTo(DEFAULT_PRIMA_NETA_SDESC);
        assertThat(testTCPRIMANETASDESC.getMargenMin()).isEqualTo(DEFAULT_MARGEN_MIN);
        assertThat(testTCPRIMANETASDESC.getMargenMax()).isEqualTo(DEFAULT_MARGEN_MAX);
        assertThat(testTCPRIMANETASDESC.getMaxComSd()).isEqualTo(DEFAULT_MAX_COM_SD);
        assertThat(testTCPRIMANETASDESC.getMaxComEp()).isEqualTo(DEFAULT_MAX_COM_EP);
    }

    @Test
    @Transactional
    void createTCPRIMANETASDESCWithExistingId() throws Exception {
        // Create the TCPRIMANETASDESC with an existing ID
        tCPRIMANETASDESC.setIdPrimaNetaSdesc(1L);

        int databaseSizeBeforeCreate = tCPRIMANETASDESCRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCPRIMANETASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPRIMANETASDESC))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCPRIMANETASDESC in the database
        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPrimaNetaSdescIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCPRIMANETASDESCRepository.findAll().size();
        // set the field null
        tCPRIMANETASDESC.setPrimaNetaSdesc(null);

        // Create the TCPRIMANETASDESC, which fails.

        restTCPRIMANETASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPRIMANETASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMargenMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCPRIMANETASDESCRepository.findAll().size();
        // set the field null
        tCPRIMANETASDESC.setMargenMin(null);

        // Create the TCPRIMANETASDESC, which fails.

        restTCPRIMANETASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPRIMANETASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMargenMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCPRIMANETASDESCRepository.findAll().size();
        // set the field null
        tCPRIMANETASDESC.setMargenMax(null);

        // Create the TCPRIMANETASDESC, which fails.

        restTCPRIMANETASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPRIMANETASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMaxComSdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCPRIMANETASDESCRepository.findAll().size();
        // set the field null
        tCPRIMANETASDESC.setMaxComSd(null);

        // Create the TCPRIMANETASDESC, which fails.

        restTCPRIMANETASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPRIMANETASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkMaxComEpIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCPRIMANETASDESCRepository.findAll().size();
        // set the field null
        tCPRIMANETASDESC.setMaxComEp(null);

        // Create the TCPRIMANETASDESC, which fails.

        restTCPRIMANETASDESCMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPRIMANETASDESC))
            )
            .andExpect(status().isBadRequest());

        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCPRIMANETASDESCS() throws Exception {
        // Initialize the database
        tCPRIMANETASDESCRepository.saveAndFlush(tCPRIMANETASDESC);

        // Get all the tCPRIMANETASDESCList
        restTCPRIMANETASDESCMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idPrimaNetaSdesc,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idPrimaNetaSdesc").value(hasItem(tCPRIMANETASDESC.getIdPrimaNetaSdesc().intValue())))
            .andExpect(jsonPath("$.[*].primaNetaSdesc").value(hasItem(DEFAULT_PRIMA_NETA_SDESC)))
            .andExpect(jsonPath("$.[*].margenMin").value(hasItem(DEFAULT_MARGEN_MIN.intValue())))
            .andExpect(jsonPath("$.[*].margenMax").value(hasItem(DEFAULT_MARGEN_MAX.intValue())))
            .andExpect(jsonPath("$.[*].maxComSd").value(hasItem(DEFAULT_MAX_COM_SD.intValue())))
            .andExpect(jsonPath("$.[*].maxComEp").value(hasItem(DEFAULT_MAX_COM_EP.intValue())));
    }

    @Test
    @Transactional
    void getTCPRIMANETASDESC() throws Exception {
        // Initialize the database
        tCPRIMANETASDESCRepository.saveAndFlush(tCPRIMANETASDESC);

        // Get the tCPRIMANETASDESC
        restTCPRIMANETASDESCMockMvc
            .perform(get(ENTITY_API_URL_ID, tCPRIMANETASDESC.getIdPrimaNetaSdesc()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idPrimaNetaSdesc").value(tCPRIMANETASDESC.getIdPrimaNetaSdesc().intValue()))
            .andExpect(jsonPath("$.primaNetaSdesc").value(DEFAULT_PRIMA_NETA_SDESC))
            .andExpect(jsonPath("$.margenMin").value(DEFAULT_MARGEN_MIN.intValue()))
            .andExpect(jsonPath("$.margenMax").value(DEFAULT_MARGEN_MAX.intValue()))
            .andExpect(jsonPath("$.maxComSd").value(DEFAULT_MAX_COM_SD.intValue()))
            .andExpect(jsonPath("$.maxComEp").value(DEFAULT_MAX_COM_EP.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCPRIMANETASDESC() throws Exception {
        // Get the tCPRIMANETASDESC
        restTCPRIMANETASDESCMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCPRIMANETASDESC() throws Exception {
        // Initialize the database
        tCPRIMANETASDESCRepository.saveAndFlush(tCPRIMANETASDESC);

        int databaseSizeBeforeUpdate = tCPRIMANETASDESCRepository.findAll().size();

        // Update the tCPRIMANETASDESC
        TCPRIMANETASDESC updatedTCPRIMANETASDESC = tCPRIMANETASDESCRepository.findById(tCPRIMANETASDESC.getIdPrimaNetaSdesc()).get();
        // Disconnect from session so that the updates on updatedTCPRIMANETASDESC are not directly saved in db
        em.detach(updatedTCPRIMANETASDESC);
        updatedTCPRIMANETASDESC
            .primaNetaSdesc(UPDATED_PRIMA_NETA_SDESC)
            .margenMin(UPDATED_MARGEN_MIN)
            .margenMax(UPDATED_MARGEN_MAX)
            .maxComSd(UPDATED_MAX_COM_SD)
            .maxComEp(UPDATED_MAX_COM_EP);

        restTCPRIMANETASDESCMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCPRIMANETASDESC.getIdPrimaNetaSdesc())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCPRIMANETASDESC))
            )
            .andExpect(status().isOk());

        // Validate the TCPRIMANETASDESC in the database
        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeUpdate);
        TCPRIMANETASDESC testTCPRIMANETASDESC = tCPRIMANETASDESCList.get(tCPRIMANETASDESCList.size() - 1);
        assertThat(testTCPRIMANETASDESC.getPrimaNetaSdesc()).isEqualTo(UPDATED_PRIMA_NETA_SDESC);
        assertThat(testTCPRIMANETASDESC.getMargenMin()).isEqualTo(UPDATED_MARGEN_MIN);
        assertThat(testTCPRIMANETASDESC.getMargenMax()).isEqualTo(UPDATED_MARGEN_MAX);
        assertThat(testTCPRIMANETASDESC.getMaxComSd()).isEqualTo(UPDATED_MAX_COM_SD);
        assertThat(testTCPRIMANETASDESC.getMaxComEp()).isEqualTo(UPDATED_MAX_COM_EP);
    }

    @Test
    @Transactional
    void putNonExistingTCPRIMANETASDESC() throws Exception {
        int databaseSizeBeforeUpdate = tCPRIMANETASDESCRepository.findAll().size();
        tCPRIMANETASDESC.setIdPrimaNetaSdesc(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCPRIMANETASDESCMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCPRIMANETASDESC.getIdPrimaNetaSdesc())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCPRIMANETASDESC))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCPRIMANETASDESC in the database
        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCPRIMANETASDESC() throws Exception {
        int databaseSizeBeforeUpdate = tCPRIMANETASDESCRepository.findAll().size();
        tCPRIMANETASDESC.setIdPrimaNetaSdesc(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCPRIMANETASDESCMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCPRIMANETASDESC))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCPRIMANETASDESC in the database
        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCPRIMANETASDESC() throws Exception {
        int databaseSizeBeforeUpdate = tCPRIMANETASDESCRepository.findAll().size();
        tCPRIMANETASDESC.setIdPrimaNetaSdesc(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCPRIMANETASDESCMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCPRIMANETASDESC))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCPRIMANETASDESC in the database
        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCPRIMANETASDESCWithPatch() throws Exception {
        // Initialize the database
        tCPRIMANETASDESCRepository.saveAndFlush(tCPRIMANETASDESC);

        int databaseSizeBeforeUpdate = tCPRIMANETASDESCRepository.findAll().size();

        // Update the tCPRIMANETASDESC using partial update
        TCPRIMANETASDESC partialUpdatedTCPRIMANETASDESC = new TCPRIMANETASDESC();
        partialUpdatedTCPRIMANETASDESC.setIdPrimaNetaSdesc(tCPRIMANETASDESC.getIdPrimaNetaSdesc());

        partialUpdatedTCPRIMANETASDESC
            .primaNetaSdesc(UPDATED_PRIMA_NETA_SDESC)
            .margenMax(UPDATED_MARGEN_MAX)
            .maxComSd(UPDATED_MAX_COM_SD)
            .maxComEp(UPDATED_MAX_COM_EP);

        restTCPRIMANETASDESCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCPRIMANETASDESC.getIdPrimaNetaSdesc())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCPRIMANETASDESC))
            )
            .andExpect(status().isOk());

        // Validate the TCPRIMANETASDESC in the database
        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeUpdate);
        TCPRIMANETASDESC testTCPRIMANETASDESC = tCPRIMANETASDESCList.get(tCPRIMANETASDESCList.size() - 1);
        assertThat(testTCPRIMANETASDESC.getPrimaNetaSdesc()).isEqualTo(UPDATED_PRIMA_NETA_SDESC);
        assertThat(testTCPRIMANETASDESC.getMargenMin()).isEqualTo(DEFAULT_MARGEN_MIN);
        assertThat(testTCPRIMANETASDESC.getMargenMax()).isEqualTo(UPDATED_MARGEN_MAX);
        assertThat(testTCPRIMANETASDESC.getMaxComSd()).isEqualTo(UPDATED_MAX_COM_SD);
        assertThat(testTCPRIMANETASDESC.getMaxComEp()).isEqualTo(UPDATED_MAX_COM_EP);
    }

    @Test
    @Transactional
    void fullUpdateTCPRIMANETASDESCWithPatch() throws Exception {
        // Initialize the database
        tCPRIMANETASDESCRepository.saveAndFlush(tCPRIMANETASDESC);

        int databaseSizeBeforeUpdate = tCPRIMANETASDESCRepository.findAll().size();

        // Update the tCPRIMANETASDESC using partial update
        TCPRIMANETASDESC partialUpdatedTCPRIMANETASDESC = new TCPRIMANETASDESC();
        partialUpdatedTCPRIMANETASDESC.setIdPrimaNetaSdesc(tCPRIMANETASDESC.getIdPrimaNetaSdesc());

        partialUpdatedTCPRIMANETASDESC
            .primaNetaSdesc(UPDATED_PRIMA_NETA_SDESC)
            .margenMin(UPDATED_MARGEN_MIN)
            .margenMax(UPDATED_MARGEN_MAX)
            .maxComSd(UPDATED_MAX_COM_SD)
            .maxComEp(UPDATED_MAX_COM_EP);

        restTCPRIMANETASDESCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCPRIMANETASDESC.getIdPrimaNetaSdesc())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCPRIMANETASDESC))
            )
            .andExpect(status().isOk());

        // Validate the TCPRIMANETASDESC in the database
        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeUpdate);
        TCPRIMANETASDESC testTCPRIMANETASDESC = tCPRIMANETASDESCList.get(tCPRIMANETASDESCList.size() - 1);
        assertThat(testTCPRIMANETASDESC.getPrimaNetaSdesc()).isEqualTo(UPDATED_PRIMA_NETA_SDESC);
        assertThat(testTCPRIMANETASDESC.getMargenMin()).isEqualTo(UPDATED_MARGEN_MIN);
        assertThat(testTCPRIMANETASDESC.getMargenMax()).isEqualTo(UPDATED_MARGEN_MAX);
        assertThat(testTCPRIMANETASDESC.getMaxComSd()).isEqualTo(UPDATED_MAX_COM_SD);
        assertThat(testTCPRIMANETASDESC.getMaxComEp()).isEqualTo(UPDATED_MAX_COM_EP);
    }

    @Test
    @Transactional
    void patchNonExistingTCPRIMANETASDESC() throws Exception {
        int databaseSizeBeforeUpdate = tCPRIMANETASDESCRepository.findAll().size();
        tCPRIMANETASDESC.setIdPrimaNetaSdesc(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCPRIMANETASDESCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCPRIMANETASDESC.getIdPrimaNetaSdesc())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCPRIMANETASDESC))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCPRIMANETASDESC in the database
        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCPRIMANETASDESC() throws Exception {
        int databaseSizeBeforeUpdate = tCPRIMANETASDESCRepository.findAll().size();
        tCPRIMANETASDESC.setIdPrimaNetaSdesc(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCPRIMANETASDESCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCPRIMANETASDESC))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCPRIMANETASDESC in the database
        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCPRIMANETASDESC() throws Exception {
        int databaseSizeBeforeUpdate = tCPRIMANETASDESCRepository.findAll().size();
        tCPRIMANETASDESC.setIdPrimaNetaSdesc(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCPRIMANETASDESCMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCPRIMANETASDESC))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCPRIMANETASDESC in the database
        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCPRIMANETASDESC() throws Exception {
        // Initialize the database
        tCPRIMANETASDESCRepository.saveAndFlush(tCPRIMANETASDESC);

        int databaseSizeBeforeDelete = tCPRIMANETASDESCRepository.findAll().size();

        // Delete the tCPRIMANETASDESC
        restTCPRIMANETASDESCMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCPRIMANETASDESC.getIdPrimaNetaSdesc()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCPRIMANETASDESC> tCPRIMANETASDESCList = tCPRIMANETASDESCRepository.findAll();
        assertThat(tCPRIMANETASDESCList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
