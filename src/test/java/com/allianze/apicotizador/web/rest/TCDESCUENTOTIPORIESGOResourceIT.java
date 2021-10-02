package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCDESCUENTOTIPORIESGO;
import com.allianze.apicotizador.repository.TCDESCUENTOTIPORIESGORepository;
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
 * Integration tests for the {@link TCDESCUENTOTIPORIESGOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCDESCUENTOTIPORIESGOResourceIT {

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    private static final Long DEFAULT_DESCUENTO = 1L;
    private static final Long UPDATED_DESCUENTO = 2L;

    private static final String ENTITY_API_URL = "/api/tcdescuentotiporiesgos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idDescuentoTipoRiesgo}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCDESCUENTOTIPORIESGORepository tCDESCUENTOTIPORIESGORepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCDESCUENTOTIPORIESGOMockMvc;

    private TCDESCUENTOTIPORIESGO tCDESCUENTOTIPORIESGO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCDESCUENTOTIPORIESGO createEntity(EntityManager em) {
        TCDESCUENTOTIPORIESGO tCDESCUENTOTIPORIESGO = new TCDESCUENTOTIPORIESGO().tipo(DEFAULT_TIPO).descuento(DEFAULT_DESCUENTO);
        return tCDESCUENTOTIPORIESGO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCDESCUENTOTIPORIESGO createUpdatedEntity(EntityManager em) {
        TCDESCUENTOTIPORIESGO tCDESCUENTOTIPORIESGO = new TCDESCUENTOTIPORIESGO().tipo(UPDATED_TIPO).descuento(UPDATED_DESCUENTO);
        return tCDESCUENTOTIPORIESGO;
    }

    @BeforeEach
    public void initTest() {
        tCDESCUENTOTIPORIESGO = createEntity(em);
    }

    @Test
    @Transactional
    void createTCDESCUENTOTIPORIESGO() throws Exception {
        int databaseSizeBeforeCreate = tCDESCUENTOTIPORIESGORepository.findAll().size();
        // Create the TCDESCUENTOTIPORIESGO
        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOTIPORIESGO))
            )
            .andExpect(status().isCreated());

        // Validate the TCDESCUENTOTIPORIESGO in the database
        List<TCDESCUENTOTIPORIESGO> tCDESCUENTOTIPORIESGOList = tCDESCUENTOTIPORIESGORepository.findAll();
        assertThat(tCDESCUENTOTIPORIESGOList).hasSize(databaseSizeBeforeCreate + 1);
        TCDESCUENTOTIPORIESGO testTCDESCUENTOTIPORIESGO = tCDESCUENTOTIPORIESGOList.get(tCDESCUENTOTIPORIESGOList.size() - 1);
        assertThat(testTCDESCUENTOTIPORIESGO.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testTCDESCUENTOTIPORIESGO.getDescuento()).isEqualTo(DEFAULT_DESCUENTO);
    }

    @Test
    @Transactional
    void createTCDESCUENTOTIPORIESGOWithExistingId() throws Exception {
        // Create the TCDESCUENTOTIPORIESGO with an existing ID
        tCDESCUENTOTIPORIESGO.setIdDescuentoTipoRiesgo(1L);

        int databaseSizeBeforeCreate = tCDESCUENTOTIPORIESGORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOTIPORIESGO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCDESCUENTOTIPORIESGO in the database
        List<TCDESCUENTOTIPORIESGO> tCDESCUENTOTIPORIESGOList = tCDESCUENTOTIPORIESGORepository.findAll();
        assertThat(tCDESCUENTOTIPORIESGOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTipoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCDESCUENTOTIPORIESGORepository.findAll().size();
        // set the field null
        tCDESCUENTOTIPORIESGO.setTipo(null);

        // Create the TCDESCUENTOTIPORIESGO, which fails.

        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOTIPORIESGO))
            )
            .andExpect(status().isBadRequest());

        List<TCDESCUENTOTIPORIESGO> tCDESCUENTOTIPORIESGOList = tCDESCUENTOTIPORIESGORepository.findAll();
        assertThat(tCDESCUENTOTIPORIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDescuentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCDESCUENTOTIPORIESGORepository.findAll().size();
        // set the field null
        tCDESCUENTOTIPORIESGO.setDescuento(null);

        // Create the TCDESCUENTOTIPORIESGO, which fails.

        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOTIPORIESGO))
            )
            .andExpect(status().isBadRequest());

        List<TCDESCUENTOTIPORIESGO> tCDESCUENTOTIPORIESGOList = tCDESCUENTOTIPORIESGORepository.findAll();
        assertThat(tCDESCUENTOTIPORIESGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCDESCUENTOTIPORIESGOS() throws Exception {
        // Initialize the database
        tCDESCUENTOTIPORIESGORepository.saveAndFlush(tCDESCUENTOTIPORIESGO);

        // Get all the tCDESCUENTOTIPORIESGOList
        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idDescuentoTipoRiesgo,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idDescuentoTipoRiesgo").value(hasItem(tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo().intValue())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)))
            .andExpect(jsonPath("$.[*].descuento").value(hasItem(DEFAULT_DESCUENTO.intValue())));
    }

    @Test
    @Transactional
    void getTCDESCUENTOTIPORIESGO() throws Exception {
        // Initialize the database
        tCDESCUENTOTIPORIESGORepository.saveAndFlush(tCDESCUENTOTIPORIESGO);

        // Get the tCDESCUENTOTIPORIESGO
        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(get(ENTITY_API_URL_ID, tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idDescuentoTipoRiesgo").value(tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo().intValue()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO))
            .andExpect(jsonPath("$.descuento").value(DEFAULT_DESCUENTO.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCDESCUENTOTIPORIESGO() throws Exception {
        // Get the tCDESCUENTOTIPORIESGO
        restTCDESCUENTOTIPORIESGOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCDESCUENTOTIPORIESGO() throws Exception {
        // Initialize the database
        tCDESCUENTOTIPORIESGORepository.saveAndFlush(tCDESCUENTOTIPORIESGO);

        int databaseSizeBeforeUpdate = tCDESCUENTOTIPORIESGORepository.findAll().size();

        // Update the tCDESCUENTOTIPORIESGO
        TCDESCUENTOTIPORIESGO updatedTCDESCUENTOTIPORIESGO = tCDESCUENTOTIPORIESGORepository
            .findById(tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo())
            .get();
        // Disconnect from session so that the updates on updatedTCDESCUENTOTIPORIESGO are not directly saved in db
        em.detach(updatedTCDESCUENTOTIPORIESGO);
        updatedTCDESCUENTOTIPORIESGO.tipo(UPDATED_TIPO).descuento(UPDATED_DESCUENTO);

        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCDESCUENTOTIPORIESGO))
            )
            .andExpect(status().isOk());

        // Validate the TCDESCUENTOTIPORIESGO in the database
        List<TCDESCUENTOTIPORIESGO> tCDESCUENTOTIPORIESGOList = tCDESCUENTOTIPORIESGORepository.findAll();
        assertThat(tCDESCUENTOTIPORIESGOList).hasSize(databaseSizeBeforeUpdate);
        TCDESCUENTOTIPORIESGO testTCDESCUENTOTIPORIESGO = tCDESCUENTOTIPORIESGOList.get(tCDESCUENTOTIPORIESGOList.size() - 1);
        assertThat(testTCDESCUENTOTIPORIESGO.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testTCDESCUENTOTIPORIESGO.getDescuento()).isEqualTo(UPDATED_DESCUENTO);
    }

    @Test
    @Transactional
    void putNonExistingTCDESCUENTOTIPORIESGO() throws Exception {
        int databaseSizeBeforeUpdate = tCDESCUENTOTIPORIESGORepository.findAll().size();
        tCDESCUENTOTIPORIESGO.setIdDescuentoTipoRiesgo(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOTIPORIESGO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCDESCUENTOTIPORIESGO in the database
        List<TCDESCUENTOTIPORIESGO> tCDESCUENTOTIPORIESGOList = tCDESCUENTOTIPORIESGORepository.findAll();
        assertThat(tCDESCUENTOTIPORIESGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCDESCUENTOTIPORIESGO() throws Exception {
        int databaseSizeBeforeUpdate = tCDESCUENTOTIPORIESGORepository.findAll().size();
        tCDESCUENTOTIPORIESGO.setIdDescuentoTipoRiesgo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOTIPORIESGO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCDESCUENTOTIPORIESGO in the database
        List<TCDESCUENTOTIPORIESGO> tCDESCUENTOTIPORIESGOList = tCDESCUENTOTIPORIESGORepository.findAll();
        assertThat(tCDESCUENTOTIPORIESGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCDESCUENTOTIPORIESGO() throws Exception {
        int databaseSizeBeforeUpdate = tCDESCUENTOTIPORIESGORepository.findAll().size();
        tCDESCUENTOTIPORIESGO.setIdDescuentoTipoRiesgo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOTIPORIESGO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCDESCUENTOTIPORIESGO in the database
        List<TCDESCUENTOTIPORIESGO> tCDESCUENTOTIPORIESGOList = tCDESCUENTOTIPORIESGORepository.findAll();
        assertThat(tCDESCUENTOTIPORIESGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCDESCUENTOTIPORIESGOWithPatch() throws Exception {
        // Initialize the database
        tCDESCUENTOTIPORIESGORepository.saveAndFlush(tCDESCUENTOTIPORIESGO);

        int databaseSizeBeforeUpdate = tCDESCUENTOTIPORIESGORepository.findAll().size();

        // Update the tCDESCUENTOTIPORIESGO using partial update
        TCDESCUENTOTIPORIESGO partialUpdatedTCDESCUENTOTIPORIESGO = new TCDESCUENTOTIPORIESGO();
        partialUpdatedTCDESCUENTOTIPORIESGO.setIdDescuentoTipoRiesgo(tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo());

        partialUpdatedTCDESCUENTOTIPORIESGO.tipo(UPDATED_TIPO);

        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCDESCUENTOTIPORIESGO))
            )
            .andExpect(status().isOk());

        // Validate the TCDESCUENTOTIPORIESGO in the database
        List<TCDESCUENTOTIPORIESGO> tCDESCUENTOTIPORIESGOList = tCDESCUENTOTIPORIESGORepository.findAll();
        assertThat(tCDESCUENTOTIPORIESGOList).hasSize(databaseSizeBeforeUpdate);
        TCDESCUENTOTIPORIESGO testTCDESCUENTOTIPORIESGO = tCDESCUENTOTIPORIESGOList.get(tCDESCUENTOTIPORIESGOList.size() - 1);
        assertThat(testTCDESCUENTOTIPORIESGO.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testTCDESCUENTOTIPORIESGO.getDescuento()).isEqualTo(DEFAULT_DESCUENTO);
    }

    @Test
    @Transactional
    void fullUpdateTCDESCUENTOTIPORIESGOWithPatch() throws Exception {
        // Initialize the database
        tCDESCUENTOTIPORIESGORepository.saveAndFlush(tCDESCUENTOTIPORIESGO);

        int databaseSizeBeforeUpdate = tCDESCUENTOTIPORIESGORepository.findAll().size();

        // Update the tCDESCUENTOTIPORIESGO using partial update
        TCDESCUENTOTIPORIESGO partialUpdatedTCDESCUENTOTIPORIESGO = new TCDESCUENTOTIPORIESGO();
        partialUpdatedTCDESCUENTOTIPORIESGO.setIdDescuentoTipoRiesgo(tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo());

        partialUpdatedTCDESCUENTOTIPORIESGO.tipo(UPDATED_TIPO).descuento(UPDATED_DESCUENTO);

        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCDESCUENTOTIPORIESGO))
            )
            .andExpect(status().isOk());

        // Validate the TCDESCUENTOTIPORIESGO in the database
        List<TCDESCUENTOTIPORIESGO> tCDESCUENTOTIPORIESGOList = tCDESCUENTOTIPORIESGORepository.findAll();
        assertThat(tCDESCUENTOTIPORIESGOList).hasSize(databaseSizeBeforeUpdate);
        TCDESCUENTOTIPORIESGO testTCDESCUENTOTIPORIESGO = tCDESCUENTOTIPORIESGOList.get(tCDESCUENTOTIPORIESGOList.size() - 1);
        assertThat(testTCDESCUENTOTIPORIESGO.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testTCDESCUENTOTIPORIESGO.getDescuento()).isEqualTo(UPDATED_DESCUENTO);
    }

    @Test
    @Transactional
    void patchNonExistingTCDESCUENTOTIPORIESGO() throws Exception {
        int databaseSizeBeforeUpdate = tCDESCUENTOTIPORIESGORepository.findAll().size();
        tCDESCUENTOTIPORIESGO.setIdDescuentoTipoRiesgo(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOTIPORIESGO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCDESCUENTOTIPORIESGO in the database
        List<TCDESCUENTOTIPORIESGO> tCDESCUENTOTIPORIESGOList = tCDESCUENTOTIPORIESGORepository.findAll();
        assertThat(tCDESCUENTOTIPORIESGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCDESCUENTOTIPORIESGO() throws Exception {
        int databaseSizeBeforeUpdate = tCDESCUENTOTIPORIESGORepository.findAll().size();
        tCDESCUENTOTIPORIESGO.setIdDescuentoTipoRiesgo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOTIPORIESGO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCDESCUENTOTIPORIESGO in the database
        List<TCDESCUENTOTIPORIESGO> tCDESCUENTOTIPORIESGOList = tCDESCUENTOTIPORIESGORepository.findAll();
        assertThat(tCDESCUENTOTIPORIESGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCDESCUENTOTIPORIESGO() throws Exception {
        int databaseSizeBeforeUpdate = tCDESCUENTOTIPORIESGORepository.findAll().size();
        tCDESCUENTOTIPORIESGO.setIdDescuentoTipoRiesgo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCDESCUENTOTIPORIESGO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCDESCUENTOTIPORIESGO in the database
        List<TCDESCUENTOTIPORIESGO> tCDESCUENTOTIPORIESGOList = tCDESCUENTOTIPORIESGORepository.findAll();
        assertThat(tCDESCUENTOTIPORIESGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCDESCUENTOTIPORIESGO() throws Exception {
        // Initialize the database
        tCDESCUENTOTIPORIESGORepository.saveAndFlush(tCDESCUENTOTIPORIESGO);

        int databaseSizeBeforeDelete = tCDESCUENTOTIPORIESGORepository.findAll().size();

        // Delete the tCDESCUENTOTIPORIESGO
        restTCDESCUENTOTIPORIESGOMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCDESCUENTOTIPORIESGO.getIdDescuentoTipoRiesgo()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCDESCUENTOTIPORIESGO> tCDESCUENTOTIPORIESGOList = tCDESCUENTOTIPORIESGORepository.findAll();
        assertThat(tCDESCUENTOTIPORIESGOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
