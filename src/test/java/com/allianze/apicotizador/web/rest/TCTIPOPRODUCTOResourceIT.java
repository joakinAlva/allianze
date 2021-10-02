package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TCTIPOPRODUCTO;
import com.allianze.apicotizador.repository.TCTIPOPRODUCTORepository;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link TCTIPOPRODUCTOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TCTIPOPRODUCTOResourceIT {

    private static final String DEFAULT_TIPO_PRODUCTO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_PRODUCTO = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTRO = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/tctipoproductos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idTipoProducto}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TCTIPOPRODUCTORepository tCTIPOPRODUCTORepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTCTIPOPRODUCTOMockMvc;

    private TCTIPOPRODUCTO tCTIPOPRODUCTO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCTIPOPRODUCTO createEntity(EntityManager em) {
        TCTIPOPRODUCTO tCTIPOPRODUCTO = new TCTIPOPRODUCTO()
            .tipoProducto(DEFAULT_TIPO_PRODUCTO)
            .registro(DEFAULT_REGISTRO)
            .fecha(DEFAULT_FECHA);
        return tCTIPOPRODUCTO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TCTIPOPRODUCTO createUpdatedEntity(EntityManager em) {
        TCTIPOPRODUCTO tCTIPOPRODUCTO = new TCTIPOPRODUCTO()
            .tipoProducto(UPDATED_TIPO_PRODUCTO)
            .registro(UPDATED_REGISTRO)
            .fecha(UPDATED_FECHA);
        return tCTIPOPRODUCTO;
    }

    @BeforeEach
    public void initTest() {
        tCTIPOPRODUCTO = createEntity(em);
    }

    @Test
    @Transactional
    void createTCTIPOPRODUCTO() throws Exception {
        int databaseSizeBeforeCreate = tCTIPOPRODUCTORepository.findAll().size();
        // Create the TCTIPOPRODUCTO
        restTCTIPOPRODUCTOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCTIPOPRODUCTO))
            )
            .andExpect(status().isCreated());

        // Validate the TCTIPOPRODUCTO in the database
        List<TCTIPOPRODUCTO> tCTIPOPRODUCTOList = tCTIPOPRODUCTORepository.findAll();
        assertThat(tCTIPOPRODUCTOList).hasSize(databaseSizeBeforeCreate + 1);
        TCTIPOPRODUCTO testTCTIPOPRODUCTO = tCTIPOPRODUCTOList.get(tCTIPOPRODUCTOList.size() - 1);
        assertThat(testTCTIPOPRODUCTO.getTipoProducto()).isEqualTo(DEFAULT_TIPO_PRODUCTO);
        assertThat(testTCTIPOPRODUCTO.getRegistro()).isEqualTo(DEFAULT_REGISTRO);
        assertThat(testTCTIPOPRODUCTO.getFecha()).isEqualTo(DEFAULT_FECHA);
    }

    @Test
    @Transactional
    void createTCTIPOPRODUCTOWithExistingId() throws Exception {
        // Create the TCTIPOPRODUCTO with an existing ID
        tCTIPOPRODUCTO.setIdTipoProducto(1L);

        int databaseSizeBeforeCreate = tCTIPOPRODUCTORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTCTIPOPRODUCTOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCTIPOPRODUCTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCTIPOPRODUCTO in the database
        List<TCTIPOPRODUCTO> tCTIPOPRODUCTOList = tCTIPOPRODUCTORepository.findAll();
        assertThat(tCTIPOPRODUCTOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTipoProductoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCTIPOPRODUCTORepository.findAll().size();
        // set the field null
        tCTIPOPRODUCTO.setTipoProducto(null);

        // Create the TCTIPOPRODUCTO, which fails.

        restTCTIPOPRODUCTOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCTIPOPRODUCTO))
            )
            .andExpect(status().isBadRequest());

        List<TCTIPOPRODUCTO> tCTIPOPRODUCTOList = tCTIPOPRODUCTORepository.findAll();
        assertThat(tCTIPOPRODUCTOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRegistroIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCTIPOPRODUCTORepository.findAll().size();
        // set the field null
        tCTIPOPRODUCTO.setRegistro(null);

        // Create the TCTIPOPRODUCTO, which fails.

        restTCTIPOPRODUCTOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCTIPOPRODUCTO))
            )
            .andExpect(status().isBadRequest());

        List<TCTIPOPRODUCTO> tCTIPOPRODUCTOList = tCTIPOPRODUCTORepository.findAll();
        assertThat(tCTIPOPRODUCTOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFechaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tCTIPOPRODUCTORepository.findAll().size();
        // set the field null
        tCTIPOPRODUCTO.setFecha(null);

        // Create the TCTIPOPRODUCTO, which fails.

        restTCTIPOPRODUCTOMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCTIPOPRODUCTO))
            )
            .andExpect(status().isBadRequest());

        List<TCTIPOPRODUCTO> tCTIPOPRODUCTOList = tCTIPOPRODUCTORepository.findAll();
        assertThat(tCTIPOPRODUCTOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTCTIPOPRODUCTOS() throws Exception {
        // Initialize the database
        tCTIPOPRODUCTORepository.saveAndFlush(tCTIPOPRODUCTO);

        // Get all the tCTIPOPRODUCTOList
        restTCTIPOPRODUCTOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idTipoProducto,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idTipoProducto").value(hasItem(tCTIPOPRODUCTO.getIdTipoProducto().intValue())))
            .andExpect(jsonPath("$.[*].tipoProducto").value(hasItem(DEFAULT_TIPO_PRODUCTO)))
            .andExpect(jsonPath("$.[*].registro").value(hasItem(DEFAULT_REGISTRO)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())));
    }

    @Test
    @Transactional
    void getTCTIPOPRODUCTO() throws Exception {
        // Initialize the database
        tCTIPOPRODUCTORepository.saveAndFlush(tCTIPOPRODUCTO);

        // Get the tCTIPOPRODUCTO
        restTCTIPOPRODUCTOMockMvc
            .perform(get(ENTITY_API_URL_ID, tCTIPOPRODUCTO.getIdTipoProducto()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idTipoProducto").value(tCTIPOPRODUCTO.getIdTipoProducto().intValue()))
            .andExpect(jsonPath("$.tipoProducto").value(DEFAULT_TIPO_PRODUCTO))
            .andExpect(jsonPath("$.registro").value(DEFAULT_REGISTRO))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTCTIPOPRODUCTO() throws Exception {
        // Get the tCTIPOPRODUCTO
        restTCTIPOPRODUCTOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTCTIPOPRODUCTO() throws Exception {
        // Initialize the database
        tCTIPOPRODUCTORepository.saveAndFlush(tCTIPOPRODUCTO);

        int databaseSizeBeforeUpdate = tCTIPOPRODUCTORepository.findAll().size();

        // Update the tCTIPOPRODUCTO
        TCTIPOPRODUCTO updatedTCTIPOPRODUCTO = tCTIPOPRODUCTORepository.findById(tCTIPOPRODUCTO.getIdTipoProducto()).get();
        // Disconnect from session so that the updates on updatedTCTIPOPRODUCTO are not directly saved in db
        em.detach(updatedTCTIPOPRODUCTO);
        updatedTCTIPOPRODUCTO.tipoProducto(UPDATED_TIPO_PRODUCTO).registro(UPDATED_REGISTRO).fecha(UPDATED_FECHA);

        restTCTIPOPRODUCTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTCTIPOPRODUCTO.getIdTipoProducto())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTCTIPOPRODUCTO))
            )
            .andExpect(status().isOk());

        // Validate the TCTIPOPRODUCTO in the database
        List<TCTIPOPRODUCTO> tCTIPOPRODUCTOList = tCTIPOPRODUCTORepository.findAll();
        assertThat(tCTIPOPRODUCTOList).hasSize(databaseSizeBeforeUpdate);
        TCTIPOPRODUCTO testTCTIPOPRODUCTO = tCTIPOPRODUCTOList.get(tCTIPOPRODUCTOList.size() - 1);
        assertThat(testTCTIPOPRODUCTO.getTipoProducto()).isEqualTo(UPDATED_TIPO_PRODUCTO);
        assertThat(testTCTIPOPRODUCTO.getRegistro()).isEqualTo(UPDATED_REGISTRO);
        assertThat(testTCTIPOPRODUCTO.getFecha()).isEqualTo(UPDATED_FECHA);
    }

    @Test
    @Transactional
    void putNonExistingTCTIPOPRODUCTO() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPOPRODUCTORepository.findAll().size();
        tCTIPOPRODUCTO.setIdTipoProducto(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCTIPOPRODUCTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tCTIPOPRODUCTO.getIdTipoProducto())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCTIPOPRODUCTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCTIPOPRODUCTO in the database
        List<TCTIPOPRODUCTO> tCTIPOPRODUCTOList = tCTIPOPRODUCTORepository.findAll();
        assertThat(tCTIPOPRODUCTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTCTIPOPRODUCTO() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPOPRODUCTORepository.findAll().size();
        tCTIPOPRODUCTO.setIdTipoProducto(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCTIPOPRODUCTOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tCTIPOPRODUCTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCTIPOPRODUCTO in the database
        List<TCTIPOPRODUCTO> tCTIPOPRODUCTOList = tCTIPOPRODUCTORepository.findAll();
        assertThat(tCTIPOPRODUCTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTCTIPOPRODUCTO() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPOPRODUCTORepository.findAll().size();
        tCTIPOPRODUCTO.setIdTipoProducto(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCTIPOPRODUCTOMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tCTIPOPRODUCTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCTIPOPRODUCTO in the database
        List<TCTIPOPRODUCTO> tCTIPOPRODUCTOList = tCTIPOPRODUCTORepository.findAll();
        assertThat(tCTIPOPRODUCTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTCTIPOPRODUCTOWithPatch() throws Exception {
        // Initialize the database
        tCTIPOPRODUCTORepository.saveAndFlush(tCTIPOPRODUCTO);

        int databaseSizeBeforeUpdate = tCTIPOPRODUCTORepository.findAll().size();

        // Update the tCTIPOPRODUCTO using partial update
        TCTIPOPRODUCTO partialUpdatedTCTIPOPRODUCTO = new TCTIPOPRODUCTO();
        partialUpdatedTCTIPOPRODUCTO.setIdTipoProducto(tCTIPOPRODUCTO.getIdTipoProducto());

        partialUpdatedTCTIPOPRODUCTO.registro(UPDATED_REGISTRO);

        restTCTIPOPRODUCTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCTIPOPRODUCTO.getIdTipoProducto())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCTIPOPRODUCTO))
            )
            .andExpect(status().isOk());

        // Validate the TCTIPOPRODUCTO in the database
        List<TCTIPOPRODUCTO> tCTIPOPRODUCTOList = tCTIPOPRODUCTORepository.findAll();
        assertThat(tCTIPOPRODUCTOList).hasSize(databaseSizeBeforeUpdate);
        TCTIPOPRODUCTO testTCTIPOPRODUCTO = tCTIPOPRODUCTOList.get(tCTIPOPRODUCTOList.size() - 1);
        assertThat(testTCTIPOPRODUCTO.getTipoProducto()).isEqualTo(DEFAULT_TIPO_PRODUCTO);
        assertThat(testTCTIPOPRODUCTO.getRegistro()).isEqualTo(UPDATED_REGISTRO);
        assertThat(testTCTIPOPRODUCTO.getFecha()).isEqualTo(DEFAULT_FECHA);
    }

    @Test
    @Transactional
    void fullUpdateTCTIPOPRODUCTOWithPatch() throws Exception {
        // Initialize the database
        tCTIPOPRODUCTORepository.saveAndFlush(tCTIPOPRODUCTO);

        int databaseSizeBeforeUpdate = tCTIPOPRODUCTORepository.findAll().size();

        // Update the tCTIPOPRODUCTO using partial update
        TCTIPOPRODUCTO partialUpdatedTCTIPOPRODUCTO = new TCTIPOPRODUCTO();
        partialUpdatedTCTIPOPRODUCTO.setIdTipoProducto(tCTIPOPRODUCTO.getIdTipoProducto());

        partialUpdatedTCTIPOPRODUCTO.tipoProducto(UPDATED_TIPO_PRODUCTO).registro(UPDATED_REGISTRO).fecha(UPDATED_FECHA);

        restTCTIPOPRODUCTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTCTIPOPRODUCTO.getIdTipoProducto())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTCTIPOPRODUCTO))
            )
            .andExpect(status().isOk());

        // Validate the TCTIPOPRODUCTO in the database
        List<TCTIPOPRODUCTO> tCTIPOPRODUCTOList = tCTIPOPRODUCTORepository.findAll();
        assertThat(tCTIPOPRODUCTOList).hasSize(databaseSizeBeforeUpdate);
        TCTIPOPRODUCTO testTCTIPOPRODUCTO = tCTIPOPRODUCTOList.get(tCTIPOPRODUCTOList.size() - 1);
        assertThat(testTCTIPOPRODUCTO.getTipoProducto()).isEqualTo(UPDATED_TIPO_PRODUCTO);
        assertThat(testTCTIPOPRODUCTO.getRegistro()).isEqualTo(UPDATED_REGISTRO);
        assertThat(testTCTIPOPRODUCTO.getFecha()).isEqualTo(UPDATED_FECHA);
    }

    @Test
    @Transactional
    void patchNonExistingTCTIPOPRODUCTO() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPOPRODUCTORepository.findAll().size();
        tCTIPOPRODUCTO.setIdTipoProducto(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTCTIPOPRODUCTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tCTIPOPRODUCTO.getIdTipoProducto())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCTIPOPRODUCTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCTIPOPRODUCTO in the database
        List<TCTIPOPRODUCTO> tCTIPOPRODUCTOList = tCTIPOPRODUCTORepository.findAll();
        assertThat(tCTIPOPRODUCTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTCTIPOPRODUCTO() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPOPRODUCTORepository.findAll().size();
        tCTIPOPRODUCTO.setIdTipoProducto(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCTIPOPRODUCTOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tCTIPOPRODUCTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TCTIPOPRODUCTO in the database
        List<TCTIPOPRODUCTO> tCTIPOPRODUCTOList = tCTIPOPRODUCTORepository.findAll();
        assertThat(tCTIPOPRODUCTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTCTIPOPRODUCTO() throws Exception {
        int databaseSizeBeforeUpdate = tCTIPOPRODUCTORepository.findAll().size();
        tCTIPOPRODUCTO.setIdTipoProducto(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTCTIPOPRODUCTOMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tCTIPOPRODUCTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TCTIPOPRODUCTO in the database
        List<TCTIPOPRODUCTO> tCTIPOPRODUCTOList = tCTIPOPRODUCTORepository.findAll();
        assertThat(tCTIPOPRODUCTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTCTIPOPRODUCTO() throws Exception {
        // Initialize the database
        tCTIPOPRODUCTORepository.saveAndFlush(tCTIPOPRODUCTO);

        int databaseSizeBeforeDelete = tCTIPOPRODUCTORepository.findAll().size();

        // Delete the tCTIPOPRODUCTO
        restTCTIPOPRODUCTOMockMvc
            .perform(delete(ENTITY_API_URL_ID, tCTIPOPRODUCTO.getIdTipoProducto()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TCTIPOPRODUCTO> tCTIPOPRODUCTOList = tCTIPOPRODUCTORepository.findAll();
        assertThat(tCTIPOPRODUCTOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
