package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCCUOTAPROPUESTA;
import com.allianze.apicotizador.repository.TCCUOTAPROPUESTARepository;
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
 * Integration tests for the {@link TCCUOTAPROPUESTAResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCCUOTAPROPUESTAResourceIT {

    private static final String DEFAULT_EDAD = "AAAAAAAAAA";
    private static final String UPDATED_EDAD = "BBBBBBBBBB";

    private static final Long DEFAULT_VALOR_VG = 1L;
    private static final Long UPDATED_VALOR_VG = 2L;

    private static final Long DEFAULT_VALOR_BIP_TRES = 1L;
    private static final Long UPDATED_VALOR_BIP_TRES = 2L;

    private static final Long DEFAULT_VALOR_BIT = 1L;
    private static final Long UPDATED_VALOR_BIT = 2L;

    private static final Long DEFAULT_VALOR_DI = 1L;
    private static final Long UPDATED_VALOR_DI = 2L;

    private static final String ENTITY_API_URL = "/api/tccuotapropuestas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCuotaPropuesta}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCCUOTAPROPUESTARepository tCCUOTAPROPUESTARepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCCUOTAPROPUESTAMockMvc;

    private TCCUOTAPROPUESTA tCCUOTAPROPUESTA;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCUOTAPROPUESTA createEntity(EntityManager em) {
        TCCUOTAPROPUESTA tCCUOTAPROPUESTA = new TCCUOTAPROPUESTA()
            .edad(DEFAULT_EDAD)
            .valorVg(DEFAULT_VALOR_VG)
            .valorBipTres(DEFAULT_VALOR_BIP_TRES)
            .valorBit(DEFAULT_VALOR_BIT)
            .valorDi(DEFAULT_VALOR_DI);
        return tCCUOTAPROPUESTA;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCCUOTAPROPUESTA createUpdatedEntity(EntityManager em) {
        TCCUOTAPROPUESTA tCCUOTAPROPUESTA = new TCCUOTAPROPUESTA()
            .edad(UPDATED_EDAD)
            .valorVg(UPDATED_VALOR_VG)
            .valorBipTres(UPDATED_VALOR_BIP_TRES)
            .valorBit(UPDATED_VALOR_BIT)
            .valorDi(UPDATED_VALOR_DI);
        return tCCUOTAPROPUESTA;
    }

    @BeforeEach
    public void initTest() {
        tCCUOTAPROPUESTA = createEntity(em);
    }

    @Test
    @Transactional
    void createTCCUOTAPROPUESTA() throws Exception {
        int databaseSizeBeforeCreate = tCCUOTAPROPUESTARepository.findAll().size();
        // Create the TCCUOTAPROPUESTA
        restTCCUOTAPROPUESTAMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTAPROPUESTA))
            )
            .andExpect(status().isCreated());

        // Validate the TCCUOTAPROPUESTA in the database
        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeCreate + 1);
        TCCUOTAPROPUESTA testTCCUOTAPROPUESTA = tCCUOTAPROPUESTAList.get(tCCUOTAPROPUESTAList.size() - 1);
        assertThat(testTCCUOTAPROPUESTA.getEdad()).isEqualTo(DEFAULT_EDAD);
        assertThat(testTCCUOTAPROPUESTA.getValorVg()).isEqualTo(DEFAULT_VALOR_VG);
        assertThat(testTCCUOTAPROPUESTA.getValorBipTres()).isEqualTo(DEFAULT_VALOR_BIP_TRES);
        assertThat(testTCCUOTAPROPUESTA.getValorBit()).isEqualTo(DEFAULT_VALOR_BIT);
        assertThat(testTCCUOTAPROPUESTA.getValorDi()).isEqualTo(DEFAULT_VALOR_DI);
    }

    @Test
    @Transactional
    void createTCCUOTAPROPUESTAWithExistingId() throws Exception {
        // Create the TCCUOTAPROPUESTA with an existing ID
        tCCUOTAPROPUESTA.setIdCuotaPropuesta(1L);

        int databaseSizeBeforeCreate = tCCUOTAPROPUESTARepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCCUOTAPROPUESTAMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTAPROPUESTA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTAPROPUESTA in the database
        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEdadIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTAPROPUESTARepository.findAll().size();
        // set the field null
        tCCUOTAPROPUESTA.setEdad(null);

        // Create the TCCUOTAPROPUESTA, which fails.

        restTCCUOTAPROPUESTAMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTAPROPUESTA))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorVgIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTAPROPUESTARepository.findAll().size();
        // set the field null
        tCCUOTAPROPUESTA.setValorVg(null);

        // Create the TCCUOTAPROPUESTA, which fails.

        restTCCUOTAPROPUESTAMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTAPROPUESTA))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorBipTresIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTAPROPUESTARepository.findAll().size();
        // set the field null
        tCCUOTAPROPUESTA.setValorBipTres(null);

        // Create the TCCUOTAPROPUESTA, which fails.

        restTCCUOTAPROPUESTAMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTAPROPUESTA))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorBitIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTAPROPUESTARepository.findAll().size();
        // set the field null
        tCCUOTAPROPUESTA.setValorBit(null);

        // Create the TCCUOTAPROPUESTA, which fails.

        restTCCUOTAPROPUESTAMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTAPROPUESTA))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorDiIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCCUOTAPROPUESTARepository.findAll().size();
        // set the field null
        tCCUOTAPROPUESTA.setValorDi(null);

        // Create the TCCUOTAPROPUESTA, which fails.

        restTCCUOTAPROPUESTAMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTAPROPUESTA))
            )
            .andExpect(status().isBadRequest());

        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCCUOTAPROPUESTAS() throws Exception {
        // Initialize the database
        tCCUOTAPROPUESTARepository.saveAndFlush(tCCUOTAPROPUESTA);

        // Get all the tCCUOTAPROPUESTAList
        restTCCUOTAPROPUESTAMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCuotaPropuesta,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCuotaPropuesta").value(hasItem(tCCUOTAPROPUESTA.getIdCuotaPropuesta().intValue())))
            .andExpect(jsonPath("$.[*].edad").value(hasItem(DEFAULT_EDAD)))
            .andExpect(jsonPath("$.[*].valorVg").value(hasItem(DEFAULT_VALOR_VG.intValue())))
            .andExpect(jsonPath("$.[*].valorBipTres").value(hasItem(DEFAULT_VALOR_BIP_TRES.intValue())))
            .andExpect(jsonPath("$.[*].valorBit").value(hasItem(DEFAULT_VALOR_BIT.intValue())))
            .andExpect(jsonPath("$.[*].valorDi").value(hasItem(DEFAULT_VALOR_DI.intValue())));
    }

    @Test
    @Transactional
    void getTCCUOTAPROPUESTA() throws Exception {
        // Initialize the database
        tCCUOTAPROPUESTARepository.saveAndFlush(tCCUOTAPROPUESTA);

        // Get the tCCUOTAPROPUESTA
        restTCCUOTAPROPUESTAMockMvc
            .perform(get(ENTITY_API_URL_ID, tCCUOTAPROPUESTA.getIdCuotaPropuesta()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCuotaPropuesta").value(tCCUOTAPROPUESTA.getIdCuotaPropuesta().intValue()))
            .andExpect(jsonPath("$.edad").value(DEFAULT_EDAD))
            .andExpect(jsonPath("$.valorVg").value(DEFAULT_VALOR_VG.intValue()))
            .andExpect(jsonPath("$.valorBipTres").value(DEFAULT_VALOR_BIP_TRES.intValue()))
            .andExpect(jsonPath("$.valorBit").value(DEFAULT_VALOR_BIT.intValue()))
            .andExpect(jsonPath("$.valorDi").value(DEFAULT_VALOR_DI.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCCUOTAPROPUESTA() throws Exception {
        // Get the tCCUOTAPROPUESTA
        restTCCUOTAPROPUESTAMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCCUOTAPROPUESTA() throws Exception {
        // Initialize the database
        tCCUOTAPROPUESTARepository.saveAndFlush(tCCUOTAPROPUESTA);

        int databaseSizeBeforeUpdate = tCCUOTAPROPUESTARepository.findAll().size();

        // Update the tCCUOTAPROPUESTA
        TCCUOTAPROPUESTA updatedTCCUOTAPROPUESTA = tCCUOTAPROPUESTARepository.findById(tCCUOTAPROPUESTA.getIdCuotaPropuesta()).get();
        // Disconnect from session so that the updates on updatedTCCUOTAPROPUESTA are not directly saved in db
        em.detach(updatedTCCUOTAPROPUESTA);
        updatedTCCUOTAPROPUESTA
            .edad(UPDATED_EDAD)
            .valorVg(UPDATED_VALOR_VG)
            .valorBipTres(UPDATED_VALOR_BIP_TRES)
            .valorBit(UPDATED_VALOR_BIT)
            .valorDi(UPDATED_VALOR_DI);

        restTCCUOTAPROPUESTAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCCUOTAPROPUESTA.getIdCuotaPropuesta())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCCUOTAPROPUESTA))
            )
            .andExpect(status().isOk());

        // Validate the TCCUOTAPROPUESTA in the database
        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeUpdate);
        TCCUOTAPROPUESTA testTCCUOTAPROPUESTA = tCCUOTAPROPUESTAList.get(tCCUOTAPROPUESTAList.size() - 1);
        assertThat(testTCCUOTAPROPUESTA.getEdad()).isEqualTo(UPDATED_EDAD);
        assertThat(testTCCUOTAPROPUESTA.getValorVg()).isEqualTo(UPDATED_VALOR_VG);
        assertThat(testTCCUOTAPROPUESTA.getValorBipTres()).isEqualTo(UPDATED_VALOR_BIP_TRES);
        assertThat(testTCCUOTAPROPUESTA.getValorBit()).isEqualTo(UPDATED_VALOR_BIT);
        assertThat(testTCCUOTAPROPUESTA.getValorDi()).isEqualTo(UPDATED_VALOR_DI);
    }

    @Test
    @Transactional
    void putNonExistingTCCUOTAPROPUESTA() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTAPROPUESTARepository.findAll().size();
        tCCUOTAPROPUESTA.setIdCuotaPropuesta(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCUOTAPROPUESTAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCCUOTAPROPUESTA.getIdCuotaPropuesta())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTAPROPUESTA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTAPROPUESTA in the database
        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCCUOTAPROPUESTA() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTAPROPUESTARepository.findAll().size();
        tCCUOTAPROPUESTA.setIdCuotaPropuesta(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTAPROPUESTAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTAPROPUESTA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTAPROPUESTA in the database
        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCCUOTAPROPUESTA() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTAPROPUESTARepository.findAll().size();
        tCCUOTAPROPUESTA.setIdCuotaPropuesta(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTAPROPUESTAMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCCUOTAPROPUESTA))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCUOTAPROPUESTA in the database
        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCCUOTAPROPUESTAWithPatch() throws Exception {
        // Initialize the database
        tCCUOTAPROPUESTARepository.saveAndFlush(tCCUOTAPROPUESTA);

        int databaseSizeBeforeUpdate = tCCUOTAPROPUESTARepository.findAll().size();

        // Update the tCCUOTAPROPUESTA using partial update
        TCCUOTAPROPUESTA partialUpdatedTCCUOTAPROPUESTA = new TCCUOTAPROPUESTA();
        partialUpdatedTCCUOTAPROPUESTA.setIdCuotaPropuesta(tCCUOTAPROPUESTA.getIdCuotaPropuesta());

        partialUpdatedTCCUOTAPROPUESTA
            .valorVg(UPDATED_VALOR_VG)
            .valorBipTres(UPDATED_VALOR_BIP_TRES)
            .valorBit(UPDATED_VALOR_BIT)
            .valorDi(UPDATED_VALOR_DI);

        restTCCUOTAPROPUESTAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCUOTAPROPUESTA.getIdCuotaPropuesta())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCUOTAPROPUESTA))
            )
            .andExpect(status().isOk());

        // Validate the TCCUOTAPROPUESTA in the database
        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeUpdate);
        TCCUOTAPROPUESTA testTCCUOTAPROPUESTA = tCCUOTAPROPUESTAList.get(tCCUOTAPROPUESTAList.size() - 1);
        assertThat(testTCCUOTAPROPUESTA.getEdad()).isEqualTo(DEFAULT_EDAD);
        assertThat(testTCCUOTAPROPUESTA.getValorVg()).isEqualTo(UPDATED_VALOR_VG);
        assertThat(testTCCUOTAPROPUESTA.getValorBipTres()).isEqualTo(UPDATED_VALOR_BIP_TRES);
        assertThat(testTCCUOTAPROPUESTA.getValorBit()).isEqualTo(UPDATED_VALOR_BIT);
        assertThat(testTCCUOTAPROPUESTA.getValorDi()).isEqualTo(UPDATED_VALOR_DI);
    }

    @Test
    @Transactional
    void fullUpdateTCCUOTAPROPUESTAWithPatch() throws Exception {
        // Initialize the database
        tCCUOTAPROPUESTARepository.saveAndFlush(tCCUOTAPROPUESTA);

        int databaseSizeBeforeUpdate = tCCUOTAPROPUESTARepository.findAll().size();

        // Update the tCCUOTAPROPUESTA using partial update
        TCCUOTAPROPUESTA partialUpdatedTCCUOTAPROPUESTA = new TCCUOTAPROPUESTA();
        partialUpdatedTCCUOTAPROPUESTA.setIdCuotaPropuesta(tCCUOTAPROPUESTA.getIdCuotaPropuesta());

        partialUpdatedTCCUOTAPROPUESTA
            .edad(UPDATED_EDAD)
            .valorVg(UPDATED_VALOR_VG)
            .valorBipTres(UPDATED_VALOR_BIP_TRES)
            .valorBit(UPDATED_VALOR_BIT)
            .valorDi(UPDATED_VALOR_DI);

        restTCCUOTAPROPUESTAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCCUOTAPROPUESTA.getIdCuotaPropuesta())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCCUOTAPROPUESTA))
            )
            .andExpect(status().isOk());

        // Validate the TCCUOTAPROPUESTA in the database
        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeUpdate);
        TCCUOTAPROPUESTA testTCCUOTAPROPUESTA = tCCUOTAPROPUESTAList.get(tCCUOTAPROPUESTAList.size() - 1);
        assertThat(testTCCUOTAPROPUESTA.getEdad()).isEqualTo(UPDATED_EDAD);
        assertThat(testTCCUOTAPROPUESTA.getValorVg()).isEqualTo(UPDATED_VALOR_VG);
        assertThat(testTCCUOTAPROPUESTA.getValorBipTres()).isEqualTo(UPDATED_VALOR_BIP_TRES);
        assertThat(testTCCUOTAPROPUESTA.getValorBit()).isEqualTo(UPDATED_VALOR_BIT);
        assertThat(testTCCUOTAPROPUESTA.getValorDi()).isEqualTo(UPDATED_VALOR_DI);
    }

    @Test
    @Transactional
    void patchNonExistingTCCUOTAPROPUESTA() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTAPROPUESTARepository.findAll().size();
        tCCUOTAPROPUESTA.setIdCuotaPropuesta(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCCUOTAPROPUESTAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCCUOTAPROPUESTA.getIdCuotaPropuesta())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTAPROPUESTA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTAPROPUESTA in the database
        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCCUOTAPROPUESTA() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTAPROPUESTARepository.findAll().size();
        tCCUOTAPROPUESTA.setIdCuotaPropuesta(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTAPROPUESTAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTAPROPUESTA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCCUOTAPROPUESTA in the database
        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCCUOTAPROPUESTA() throws Exception {
        int databaseSizeBeforeUpdate = tCCUOTAPROPUESTARepository.findAll().size();
        tCCUOTAPROPUESTA.setIdCuotaPropuesta(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCCUOTAPROPUESTAMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCCUOTAPROPUESTA))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCCUOTAPROPUESTA in the database
        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCCUOTAPROPUESTA() throws Exception {
        // Initialize the database
        tCCUOTAPROPUESTARepository.saveAndFlush(tCCUOTAPROPUESTA);

        int databaseSizeBeforeDelete = tCCUOTAPROPUESTARepository.findAll().size();

        // Delete the tCCUOTAPROPUESTA
        restTCCUOTAPROPUESTAMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCCUOTAPROPUESTA.getIdCuotaPropuesta()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCCUOTAPROPUESTA> tCCUOTAPROPUESTAList = tCCUOTAPROPUESTARepository.findAll();
        assertThat(tCCUOTAPROPUESTAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
