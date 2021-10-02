package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCEDADRECARGO;
import com.allianze.apicotizador.repository.TCEDADRECARGORepository;
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
 * Integration tests for the {@link TCEDADRECARGOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCEDADRECARGOResourceIT {

    private static final String DEFAULT_EDAD_MIN = "AAAAAAAAAA";
    private static final String UPDATED_EDAD_MIN = "BBBBBBBBBB";

    private static final String DEFAULT_EDAD_MAX = "AAAAAAAAAA";
    private static final String UPDATED_EDAD_MAX = "BBBBBBBBBB";

    private static final Long DEFAULT_RECARGO_ANTERIOR = 1L;
    private static final Long UPDATED_RECARGO_ANTERIOR = 2L;

    private static final Long DEFAULT_RECARGO_ACTUAL = 1L;
    private static final Long UPDATED_RECARGO_ACTUAL = 2L;

    private static final String ENTITY_API_URL = "/api/tcedadrecargos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idEdadRecargo}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCEDADRECARGORepository tCEDADRECARGORepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCEDADRECARGOMockMvc;

    private TCEDADRECARGO tCEDADRECARGO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCEDADRECARGO createEntity(EntityManager em) {
        TCEDADRECARGO tCEDADRECARGO = new TCEDADRECARGO()
            .edadMin(DEFAULT_EDAD_MIN)
            .edadMax(DEFAULT_EDAD_MAX)
            .recargoAnterior(DEFAULT_RECARGO_ANTERIOR)
            .recargoActual(DEFAULT_RECARGO_ACTUAL);
        return tCEDADRECARGO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCEDADRECARGO createUpdatedEntity(EntityManager em) {
        TCEDADRECARGO tCEDADRECARGO = new TCEDADRECARGO()
            .edadMin(UPDATED_EDAD_MIN)
            .edadMax(UPDATED_EDAD_MAX)
            .recargoAnterior(UPDATED_RECARGO_ANTERIOR)
            .recargoActual(UPDATED_RECARGO_ACTUAL);
        return tCEDADRECARGO;
    }

    @BeforeEach
    public void initTest() {
        tCEDADRECARGO = createEntity(em);
    }

    @Test
    @Transactional
    void createTCEDADRECARGO() throws Exception {
        int databaseSizeBeforeCreate = tCEDADRECARGORepository.findAll().size();
        // Create the TCEDADRECARGO
        restTCEDADRECARGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCEDADRECARGO)))
            .andExpect(status().isCreated());

        // Validate the TCEDADRECARGO in the database
        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeCreate + 1);
        TCEDADRECARGO testTCEDADRECARGO = tCEDADRECARGOList.get(tCEDADRECARGOList.size() - 1);
        assertThat(testTCEDADRECARGO.getEdadMin()).isEqualTo(DEFAULT_EDAD_MIN);
        assertThat(testTCEDADRECARGO.getEdadMax()).isEqualTo(DEFAULT_EDAD_MAX);
        assertThat(testTCEDADRECARGO.getRecargoAnterior()).isEqualTo(DEFAULT_RECARGO_ANTERIOR);
        assertThat(testTCEDADRECARGO.getRecargoActual()).isEqualTo(DEFAULT_RECARGO_ACTUAL);
    }

    @Test
    @Transactional
    void createTCEDADRECARGOWithExistingId() throws Exception {
        // Create the TCEDADRECARGO with an existing ID
        tCEDADRECARGO.setIdEdadRecargo(1L);

        int databaseSizeBeforeCreate = tCEDADRECARGORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCEDADRECARGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCEDADRECARGO)))
            .andExpect(status().isBadRequest());

        // Validate the TCEDADRECARGO in the database
        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEdadMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCEDADRECARGORepository.findAll().size();
        // set the field null
        tCEDADRECARGO.setEdadMin(null);

        // Create the TCEDADRECARGO, which fails.

        restTCEDADRECARGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCEDADRECARGO)))
            .andExpect(status().isBadRequest());

        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEdadMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCEDADRECARGORepository.findAll().size();
        // set the field null
        tCEDADRECARGO.setEdadMax(null);

        // Create the TCEDADRECARGO, which fails.

        restTCEDADRECARGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCEDADRECARGO)))
            .andExpect(status().isBadRequest());

        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRecargoAnteriorIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCEDADRECARGORepository.findAll().size();
        // set the field null
        tCEDADRECARGO.setRecargoAnterior(null);

        // Create the TCEDADRECARGO, which fails.

        restTCEDADRECARGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCEDADRECARGO)))
            .andExpect(status().isBadRequest());

        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRecargoActualIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCEDADRECARGORepository.findAll().size();
        // set the field null
        tCEDADRECARGO.setRecargoActual(null);

        // Create the TCEDADRECARGO, which fails.

        restTCEDADRECARGOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCEDADRECARGO)))
            .andExpect(status().isBadRequest());

        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCEDADRECARGOS() throws Exception {
        // Initialize the database
        tCEDADRECARGORepository.saveAndFlush(tCEDADRECARGO);

        // Get all the tCEDADRECARGOList
        restTCEDADRECARGOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idEdadRecargo,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idEdadRecargo").value(hasItem(tCEDADRECARGO.getIdEdadRecargo().intValue())))
            .andExpect(jsonPath("$.[*].edadMin").value(hasItem(DEFAULT_EDAD_MIN)))
            .andExpect(jsonPath("$.[*].edadMax").value(hasItem(DEFAULT_EDAD_MAX)))
            .andExpect(jsonPath("$.[*].recargoAnterior").value(hasItem(DEFAULT_RECARGO_ANTERIOR.intValue())))
            .andExpect(jsonPath("$.[*].recargoActual").value(hasItem(DEFAULT_RECARGO_ACTUAL.intValue())));
    }

    @Test
    @Transactional
    void getTCEDADRECARGO() throws Exception {
        // Initialize the database
        tCEDADRECARGORepository.saveAndFlush(tCEDADRECARGO);

        // Get the tCEDADRECARGO
        restTCEDADRECARGOMockMvc
            .perform(get(ENTITY_API_URL_ID, tCEDADRECARGO.getIdEdadRecargo()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idEdadRecargo").value(tCEDADRECARGO.getIdEdadRecargo().intValue()))
            .andExpect(jsonPath("$.edadMin").value(DEFAULT_EDAD_MIN))
            .andExpect(jsonPath("$.edadMax").value(DEFAULT_EDAD_MAX))
            .andExpect(jsonPath("$.recargoAnterior").value(DEFAULT_RECARGO_ANTERIOR.intValue()))
            .andExpect(jsonPath("$.recargoActual").value(DEFAULT_RECARGO_ACTUAL.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCEDADRECARGO() throws Exception {
        // Get the tCEDADRECARGO
        restTCEDADRECARGOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCEDADRECARGO() throws Exception {
        // Initialize the database
        tCEDADRECARGORepository.saveAndFlush(tCEDADRECARGO);

        int databaseSizeBeforeUpdate = tCEDADRECARGORepository.findAll().size();

        // Update the tCEDADRECARGO
        TCEDADRECARGO updatedTCEDADRECARGO = tCEDADRECARGORepository.findById(tCEDADRECARGO.getIdEdadRecargo()).get();
        // Disconnect from session so that the updates on updatedTCEDADRECARGO are not directly saved in db
        em.detach(updatedTCEDADRECARGO);
        updatedTCEDADRECARGO
            .edadMin(UPDATED_EDAD_MIN)
            .edadMax(UPDATED_EDAD_MAX)
            .recargoAnterior(UPDATED_RECARGO_ANTERIOR)
            .recargoActual(UPDATED_RECARGO_ACTUAL);

        restTCEDADRECARGOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCEDADRECARGO.getIdEdadRecargo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCEDADRECARGO))
            )
            .andExpect(status().isOk());

        // Validate the TCEDADRECARGO in the database
        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeUpdate);
        TCEDADRECARGO testTCEDADRECARGO = tCEDADRECARGOList.get(tCEDADRECARGOList.size() - 1);
        assertThat(testTCEDADRECARGO.getEdadMin()).isEqualTo(UPDATED_EDAD_MIN);
        assertThat(testTCEDADRECARGO.getEdadMax()).isEqualTo(UPDATED_EDAD_MAX);
        assertThat(testTCEDADRECARGO.getRecargoAnterior()).isEqualTo(UPDATED_RECARGO_ANTERIOR);
        assertThat(testTCEDADRECARGO.getRecargoActual()).isEqualTo(UPDATED_RECARGO_ACTUAL);
    }

    @Test
    @Transactional
    void putNonExistingTCEDADRECARGO() throws Exception {
        int databaseSizeBeforeUpdate = tCEDADRECARGORepository.findAll().size();
        tCEDADRECARGO.setIdEdadRecargo(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCEDADRECARGOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCEDADRECARGO.getIdEdadRecargo())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCEDADRECARGO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCEDADRECARGO in the database
        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCEDADRECARGO() throws Exception {
        int databaseSizeBeforeUpdate = tCEDADRECARGORepository.findAll().size();
        tCEDADRECARGO.setIdEdadRecargo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCEDADRECARGOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCEDADRECARGO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCEDADRECARGO in the database
        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCEDADRECARGO() throws Exception {
        int databaseSizeBeforeUpdate = tCEDADRECARGORepository.findAll().size();
        tCEDADRECARGO.setIdEdadRecargo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCEDADRECARGOMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCEDADRECARGO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCEDADRECARGO in the database
        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCEDADRECARGOWithPatch() throws Exception {
        // Initialize the database
        tCEDADRECARGORepository.saveAndFlush(tCEDADRECARGO);

        int databaseSizeBeforeUpdate = tCEDADRECARGORepository.findAll().size();

        // Update the tCEDADRECARGO using partial update
        TCEDADRECARGO partialUpdatedTCEDADRECARGO = new TCEDADRECARGO();
        partialUpdatedTCEDADRECARGO.setIdEdadRecargo(tCEDADRECARGO.getIdEdadRecargo());

        partialUpdatedTCEDADRECARGO.edadMax(UPDATED_EDAD_MAX).recargoActual(UPDATED_RECARGO_ACTUAL);

        restTCEDADRECARGOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCEDADRECARGO.getIdEdadRecargo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCEDADRECARGO))
            )
            .andExpect(status().isOk());

        // Validate the TCEDADRECARGO in the database
        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeUpdate);
        TCEDADRECARGO testTCEDADRECARGO = tCEDADRECARGOList.get(tCEDADRECARGOList.size() - 1);
        assertThat(testTCEDADRECARGO.getEdadMin()).isEqualTo(DEFAULT_EDAD_MIN);
        assertThat(testTCEDADRECARGO.getEdadMax()).isEqualTo(UPDATED_EDAD_MAX);
        assertThat(testTCEDADRECARGO.getRecargoAnterior()).isEqualTo(DEFAULT_RECARGO_ANTERIOR);
        assertThat(testTCEDADRECARGO.getRecargoActual()).isEqualTo(UPDATED_RECARGO_ACTUAL);
    }

    @Test
    @Transactional
    void fullUpdateTCEDADRECARGOWithPatch() throws Exception {
        // Initialize the database
        tCEDADRECARGORepository.saveAndFlush(tCEDADRECARGO);

        int databaseSizeBeforeUpdate = tCEDADRECARGORepository.findAll().size();

        // Update the tCEDADRECARGO using partial update
        TCEDADRECARGO partialUpdatedTCEDADRECARGO = new TCEDADRECARGO();
        partialUpdatedTCEDADRECARGO.setIdEdadRecargo(tCEDADRECARGO.getIdEdadRecargo());

        partialUpdatedTCEDADRECARGO
            .edadMin(UPDATED_EDAD_MIN)
            .edadMax(UPDATED_EDAD_MAX)
            .recargoAnterior(UPDATED_RECARGO_ANTERIOR)
            .recargoActual(UPDATED_RECARGO_ACTUAL);

        restTCEDADRECARGOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCEDADRECARGO.getIdEdadRecargo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCEDADRECARGO))
            )
            .andExpect(status().isOk());

        // Validate the TCEDADRECARGO in the database
        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeUpdate);
        TCEDADRECARGO testTCEDADRECARGO = tCEDADRECARGOList.get(tCEDADRECARGOList.size() - 1);
        assertThat(testTCEDADRECARGO.getEdadMin()).isEqualTo(UPDATED_EDAD_MIN);
        assertThat(testTCEDADRECARGO.getEdadMax()).isEqualTo(UPDATED_EDAD_MAX);
        assertThat(testTCEDADRECARGO.getRecargoAnterior()).isEqualTo(UPDATED_RECARGO_ANTERIOR);
        assertThat(testTCEDADRECARGO.getRecargoActual()).isEqualTo(UPDATED_RECARGO_ACTUAL);
    }

    @Test
    @Transactional
    void patchNonExistingTCEDADRECARGO() throws Exception {
        int databaseSizeBeforeUpdate = tCEDADRECARGORepository.findAll().size();
        tCEDADRECARGO.setIdEdadRecargo(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCEDADRECARGOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCEDADRECARGO.getIdEdadRecargo())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCEDADRECARGO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCEDADRECARGO in the database
        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCEDADRECARGO() throws Exception {
        int databaseSizeBeforeUpdate = tCEDADRECARGORepository.findAll().size();
        tCEDADRECARGO.setIdEdadRecargo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCEDADRECARGOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCEDADRECARGO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCEDADRECARGO in the database
        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCEDADRECARGO() throws Exception {
        int databaseSizeBeforeUpdate = tCEDADRECARGORepository.findAll().size();
        tCEDADRECARGO.setIdEdadRecargo(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCEDADRECARGOMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCEDADRECARGO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCEDADRECARGO in the database
        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCEDADRECARGO() throws Exception {
        // Initialize the database
        tCEDADRECARGORepository.saveAndFlush(tCEDADRECARGO);

        int databaseSizeBeforeDelete = tCEDADRECARGORepository.findAll().size();

        // Delete the tCEDADRECARGO
        restTCEDADRECARGOMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCEDADRECARGO.getIdEdadRecargo()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCEDADRECARGO> tCEDADRECARGOList = tCEDADRECARGORepository.findAll();
        assertThat(tCEDADRECARGOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
