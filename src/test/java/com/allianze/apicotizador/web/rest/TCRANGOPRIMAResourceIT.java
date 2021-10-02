package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCRANGOPRIMA;
import com.allianze.apicotizador.repository.TCRANGOPRIMARepository;
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
 * Integration tests for the {@link TCRANGOPRIMAResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCRANGOPRIMAResourceIT {

    private static final Long DEFAULT_VALOR_MIN = 1L;
    private static final Long UPDATED_VALOR_MIN = 2L;

    private static final Long DEFAULT_VALOR_MAX = 1L;
    private static final Long UPDATED_VALOR_MAX = 2L;

    private static final Long DEFAULT_DIVIDENDOS = 1L;
    private static final Long UPDATED_DIVIDENDOS = 2L;

    private static final Long DEFAULT_COMISION = 1L;
    private static final Long UPDATED_COMISION = 2L;

    private static final String ENTITY_API_URL = "/api/tcrangoprimas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idRangoPrima}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCRANGOPRIMARepository tCRANGOPRIMARepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCRANGOPRIMAMockMvc;

    private TCRANGOPRIMA tCRANGOPRIMA;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCRANGOPRIMA createEntity(EntityManager em) {
        TCRANGOPRIMA tCRANGOPRIMA = new TCRANGOPRIMA()
            .valorMin(DEFAULT_VALOR_MIN)
            .valorMax(DEFAULT_VALOR_MAX)
            .dividendos(DEFAULT_DIVIDENDOS)
            .comision(DEFAULT_COMISION);
        return tCRANGOPRIMA;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCRANGOPRIMA createUpdatedEntity(EntityManager em) {
        TCRANGOPRIMA tCRANGOPRIMA = new TCRANGOPRIMA()
            .valorMin(UPDATED_VALOR_MIN)
            .valorMax(UPDATED_VALOR_MAX)
            .dividendos(UPDATED_DIVIDENDOS)
            .comision(UPDATED_COMISION);
        return tCRANGOPRIMA;
    }

    @BeforeEach
    public void initTest() {
        tCRANGOPRIMA = createEntity(em);
    }

    @Test
    @Transactional
    void createTCRANGOPRIMA() throws Exception {
        int databaseSizeBeforeCreate = tCRANGOPRIMARepository.findAll().size();
        // Create the TCRANGOPRIMA
        restTCRANGOPRIMAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCRANGOPRIMA)))
            .andExpect(status().isCreated());

        // Validate the TCRANGOPRIMA in the database
        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeCreate + 1);
        TCRANGOPRIMA testTCRANGOPRIMA = tCRANGOPRIMAList.get(tCRANGOPRIMAList.size() - 1);
        assertThat(testTCRANGOPRIMA.getValorMin()).isEqualTo(DEFAULT_VALOR_MIN);
        assertThat(testTCRANGOPRIMA.getValorMax()).isEqualTo(DEFAULT_VALOR_MAX);
        assertThat(testTCRANGOPRIMA.getDividendos()).isEqualTo(DEFAULT_DIVIDENDOS);
        assertThat(testTCRANGOPRIMA.getComision()).isEqualTo(DEFAULT_COMISION);
    }

    @Test
    @Transactional
    void createTCRANGOPRIMAWithExistingId() throws Exception {
        // Create the TCRANGOPRIMA with an existing ID
        tCRANGOPRIMA.setIdRangoPrima(1L);

        int databaseSizeBeforeCreate = tCRANGOPRIMARepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCRANGOPRIMAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCRANGOPRIMA)))
            .andExpect(status().isBadRequest());

        // Validate the TCRANGOPRIMA in the database
        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkValorMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCRANGOPRIMARepository.findAll().size();
        // set the field null
        tCRANGOPRIMA.setValorMin(null);

        // Create the TCRANGOPRIMA, which fails.

        restTCRANGOPRIMAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCRANGOPRIMA)))
            .andExpect(status().isBadRequest());

        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkValorMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCRANGOPRIMARepository.findAll().size();
        // set the field null
        tCRANGOPRIMA.setValorMax(null);

        // Create the TCRANGOPRIMA, which fails.

        restTCRANGOPRIMAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCRANGOPRIMA)))
            .andExpect(status().isBadRequest());

        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDividendosIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCRANGOPRIMARepository.findAll().size();
        // set the field null
        tCRANGOPRIMA.setDividendos(null);

        // Create the TCRANGOPRIMA, which fails.

        restTCRANGOPRIMAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCRANGOPRIMA)))
            .andExpect(status().isBadRequest());

        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkComisionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCRANGOPRIMARepository.findAll().size();
        // set the field null
        tCRANGOPRIMA.setComision(null);

        // Create the TCRANGOPRIMA, which fails.

        restTCRANGOPRIMAMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCRANGOPRIMA)))
            .andExpect(status().isBadRequest());

        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCRANGOPRIMAS() throws Exception {
        // Initialize the database
        tCRANGOPRIMARepository.saveAndFlush(tCRANGOPRIMA);

        // Get all the tCRANGOPRIMAList
        restTCRANGOPRIMAMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idRangoPrima,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idRangoPrima").value(hasItem(tCRANGOPRIMA.getIdRangoPrima().intValue())))
            .andExpect(jsonPath("$.[*].valorMin").value(hasItem(DEFAULT_VALOR_MIN.intValue())))
            .andExpect(jsonPath("$.[*].valorMax").value(hasItem(DEFAULT_VALOR_MAX.intValue())))
            .andExpect(jsonPath("$.[*].dividendos").value(hasItem(DEFAULT_DIVIDENDOS.intValue())))
            .andExpect(jsonPath("$.[*].comision").value(hasItem(DEFAULT_COMISION.intValue())));
    }

    @Test
    @Transactional
    void getTCRANGOPRIMA() throws Exception {
        // Initialize the database
        tCRANGOPRIMARepository.saveAndFlush(tCRANGOPRIMA);

        // Get the tCRANGOPRIMA
        restTCRANGOPRIMAMockMvc
            .perform(get(ENTITY_API_URL_ID, tCRANGOPRIMA.getIdRangoPrima()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idRangoPrima").value(tCRANGOPRIMA.getIdRangoPrima().intValue()))
            .andExpect(jsonPath("$.valorMin").value(DEFAULT_VALOR_MIN.intValue()))
            .andExpect(jsonPath("$.valorMax").value(DEFAULT_VALOR_MAX.intValue()))
            .andExpect(jsonPath("$.dividendos").value(DEFAULT_DIVIDENDOS.intValue()))
            .andExpect(jsonPath("$.comision").value(DEFAULT_COMISION.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTCRANGOPRIMA() throws Exception {
        // Get the tCRANGOPRIMA
        restTCRANGOPRIMAMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCRANGOPRIMA() throws Exception {
        // Initialize the database
        tCRANGOPRIMARepository.saveAndFlush(tCRANGOPRIMA);

        int databaseSizeBeforeUpdate = tCRANGOPRIMARepository.findAll().size();

        // Update the tCRANGOPRIMA
        TCRANGOPRIMA updatedTCRANGOPRIMA = tCRANGOPRIMARepository.findById(tCRANGOPRIMA.getIdRangoPrima()).get();
        // Disconnect from session so that the updates on updatedTCRANGOPRIMA are not directly saved in db
        em.detach(updatedTCRANGOPRIMA);
        updatedTCRANGOPRIMA
            .valorMin(UPDATED_VALOR_MIN)
            .valorMax(UPDATED_VALOR_MAX)
            .dividendos(UPDATED_DIVIDENDOS)
            .comision(UPDATED_COMISION);

        restTCRANGOPRIMAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCRANGOPRIMA.getIdRangoPrima())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCRANGOPRIMA))
            )
            .andExpect(status().isOk());

        // Validate the TCRANGOPRIMA in the database
        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeUpdate);
        TCRANGOPRIMA testTCRANGOPRIMA = tCRANGOPRIMAList.get(tCRANGOPRIMAList.size() - 1);
        assertThat(testTCRANGOPRIMA.getValorMin()).isEqualTo(UPDATED_VALOR_MIN);
        assertThat(testTCRANGOPRIMA.getValorMax()).isEqualTo(UPDATED_VALOR_MAX);
        assertThat(testTCRANGOPRIMA.getDividendos()).isEqualTo(UPDATED_DIVIDENDOS);
        assertThat(testTCRANGOPRIMA.getComision()).isEqualTo(UPDATED_COMISION);
    }

    @Test
    @Transactional
    void putNonExistingTCRANGOPRIMA() throws Exception {
        int databaseSizeBeforeUpdate = tCRANGOPRIMARepository.findAll().size();
        tCRANGOPRIMA.setIdRangoPrima(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCRANGOPRIMAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCRANGOPRIMA.getIdRangoPrima())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCRANGOPRIMA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCRANGOPRIMA in the database
        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCRANGOPRIMA() throws Exception {
        int databaseSizeBeforeUpdate = tCRANGOPRIMARepository.findAll().size();
        tCRANGOPRIMA.setIdRangoPrima(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCRANGOPRIMAMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCRANGOPRIMA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCRANGOPRIMA in the database
        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCRANGOPRIMA() throws Exception {
        int databaseSizeBeforeUpdate = tCRANGOPRIMARepository.findAll().size();
        tCRANGOPRIMA.setIdRangoPrima(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCRANGOPRIMAMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCRANGOPRIMA)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCRANGOPRIMA in the database
        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCRANGOPRIMAWithPatch() throws Exception {
        // Initialize the database
        tCRANGOPRIMARepository.saveAndFlush(tCRANGOPRIMA);

        int databaseSizeBeforeUpdate = tCRANGOPRIMARepository.findAll().size();

        // Update the tCRANGOPRIMA using partial update
        TCRANGOPRIMA partialUpdatedTCRANGOPRIMA = new TCRANGOPRIMA();
        partialUpdatedTCRANGOPRIMA.setIdRangoPrima(tCRANGOPRIMA.getIdRangoPrima());

        partialUpdatedTCRANGOPRIMA.valorMin(UPDATED_VALOR_MIN).dividendos(UPDATED_DIVIDENDOS);

        restTCRANGOPRIMAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCRANGOPRIMA.getIdRangoPrima())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCRANGOPRIMA))
            )
            .andExpect(status().isOk());

        // Validate the TCRANGOPRIMA in the database
        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeUpdate);
        TCRANGOPRIMA testTCRANGOPRIMA = tCRANGOPRIMAList.get(tCRANGOPRIMAList.size() - 1);
        assertThat(testTCRANGOPRIMA.getValorMin()).isEqualTo(UPDATED_VALOR_MIN);
        assertThat(testTCRANGOPRIMA.getValorMax()).isEqualTo(DEFAULT_VALOR_MAX);
        assertThat(testTCRANGOPRIMA.getDividendos()).isEqualTo(UPDATED_DIVIDENDOS);
        assertThat(testTCRANGOPRIMA.getComision()).isEqualTo(DEFAULT_COMISION);
    }

    @Test
    @Transactional
    void fullUpdateTCRANGOPRIMAWithPatch() throws Exception {
        // Initialize the database
        tCRANGOPRIMARepository.saveAndFlush(tCRANGOPRIMA);

        int databaseSizeBeforeUpdate = tCRANGOPRIMARepository.findAll().size();

        // Update the tCRANGOPRIMA using partial update
        TCRANGOPRIMA partialUpdatedTCRANGOPRIMA = new TCRANGOPRIMA();
        partialUpdatedTCRANGOPRIMA.setIdRangoPrima(tCRANGOPRIMA.getIdRangoPrima());

        partialUpdatedTCRANGOPRIMA
            .valorMin(UPDATED_VALOR_MIN)
            .valorMax(UPDATED_VALOR_MAX)
            .dividendos(UPDATED_DIVIDENDOS)
            .comision(UPDATED_COMISION);

        restTCRANGOPRIMAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCRANGOPRIMA.getIdRangoPrima())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCRANGOPRIMA))
            )
            .andExpect(status().isOk());

        // Validate the TCRANGOPRIMA in the database
        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeUpdate);
        TCRANGOPRIMA testTCRANGOPRIMA = tCRANGOPRIMAList.get(tCRANGOPRIMAList.size() - 1);
        assertThat(testTCRANGOPRIMA.getValorMin()).isEqualTo(UPDATED_VALOR_MIN);
        assertThat(testTCRANGOPRIMA.getValorMax()).isEqualTo(UPDATED_VALOR_MAX);
        assertThat(testTCRANGOPRIMA.getDividendos()).isEqualTo(UPDATED_DIVIDENDOS);
        assertThat(testTCRANGOPRIMA.getComision()).isEqualTo(UPDATED_COMISION);
    }

    @Test
    @Transactional
    void patchNonExistingTCRANGOPRIMA() throws Exception {
        int databaseSizeBeforeUpdate = tCRANGOPRIMARepository.findAll().size();
        tCRANGOPRIMA.setIdRangoPrima(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCRANGOPRIMAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCRANGOPRIMA.getIdRangoPrima())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCRANGOPRIMA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCRANGOPRIMA in the database
        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCRANGOPRIMA() throws Exception {
        int databaseSizeBeforeUpdate = tCRANGOPRIMARepository.findAll().size();
        tCRANGOPRIMA.setIdRangoPrima(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCRANGOPRIMAMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCRANGOPRIMA))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCRANGOPRIMA in the database
        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCRANGOPRIMA() throws Exception {
        int databaseSizeBeforeUpdate = tCRANGOPRIMARepository.findAll().size();
        tCRANGOPRIMA.setIdRangoPrima(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCRANGOPRIMAMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCRANGOPRIMA))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCRANGOPRIMA in the database
        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCRANGOPRIMA() throws Exception {
        // Initialize the database
        tCRANGOPRIMARepository.saveAndFlush(tCRANGOPRIMA);

        int databaseSizeBeforeDelete = tCRANGOPRIMARepository.findAll().size();

        // Delete the tCRANGOPRIMA
        restTCRANGOPRIMAMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCRANGOPRIMA.getIdRangoPrima()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCRANGOPRIMA> tCRANGOPRIMAList = tCRANGOPRIMARepository.findAll();
        assertThat(tCRANGOPRIMAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
