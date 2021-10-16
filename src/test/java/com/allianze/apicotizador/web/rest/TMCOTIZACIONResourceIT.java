package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TMCOTIZACION;
import com.allianze.apicotizador.repository.TMCOTIZACIONRepository;
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
 * Integration tests for the {@link TMCOTIZACIONResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TMCOTIZACIONResourceIT {

    private static final Long DEFAULT_USUARIO = 1L;
    private static final Long UPDATED_USUARIO = 2L;

    private static final LocalDate DEFAULT_FECHA_REGISTRO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_REGISTRO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ESTATUS = 1L;
    private static final Long UPDATED_ESTATUS = 2L;

    private static final Long DEFAULT_ELIMINADA = 1L;
    private static final Long UPDATED_ELIMINADA = 2L;

    private static final String ENTITY_API_URL = "/api/tmcotizacions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idCotizacion}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TMCOTIZACIONRepository tMCOTIZACIONRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTMCOTIZACIONMockMvc;

    private TMCOTIZACION tMCOTIZACION;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TMCOTIZACION createEntity(EntityManager em) {
        TMCOTIZACION tMCOTIZACION = new TMCOTIZACION()
            .usuario(DEFAULT_USUARIO)
            .fechaRegistro(DEFAULT_FECHA_REGISTRO)
            .estatus(DEFAULT_ESTATUS)
            .eliminada(DEFAULT_ELIMINADA);
        return tMCOTIZACION;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TMCOTIZACION createUpdatedEntity(EntityManager em) {
        TMCOTIZACION tMCOTIZACION = new TMCOTIZACION()
            .usuario(UPDATED_USUARIO)
            .fechaRegistro(UPDATED_FECHA_REGISTRO)
            .estatus(UPDATED_ESTATUS)
            .eliminada(UPDATED_ELIMINADA);
        return tMCOTIZACION;
    }

    @BeforeEach
    public void initTest() {
        tMCOTIZACION = createEntity(em);
    }

    @Test
    @Transactional
    void createTMCOTIZACION() throws Exception {
        int databaseSizeBeforeCreate = tMCOTIZACIONRepository.findAll().size();
        // Create the TMCOTIZACION
        restTMCOTIZACIONMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACION)))
            .andExpect(status().isCreated());

        // Validate the TMCOTIZACION in the database
        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeCreate + 1);
        TMCOTIZACION testTMCOTIZACION = tMCOTIZACIONList.get(tMCOTIZACIONList.size() - 1);
        assertThat(testTMCOTIZACION.getUsuario()).isEqualTo(DEFAULT_USUARIO);
        assertThat(testTMCOTIZACION.getFechaRegistro()).isEqualTo(DEFAULT_FECHA_REGISTRO);
        assertThat(testTMCOTIZACION.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testTMCOTIZACION.getEliminada()).isEqualTo(DEFAULT_ELIMINADA);
    }

    @Test
    @Transactional
    void createTMCOTIZACIONWithExistingId() throws Exception {
        // Create the TMCOTIZACION with an existing ID
        tMCOTIZACION.setIdCotizacion(1L);

        int databaseSizeBeforeCreate = tMCOTIZACIONRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTMCOTIZACIONMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACION)))
            .andExpect(status().isBadRequest());

        // Validate the TMCOTIZACION in the database
        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUsuarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONRepository.findAll().size();
        // set the field null
        tMCOTIZACION.setUsuario(null);

        // Create the TMCOTIZACION, which fails.

        restTMCOTIZACIONMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACION)))
            .andExpect(status().isBadRequest());

        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFechaRegistroIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONRepository.findAll().size();
        // set the field null
        tMCOTIZACION.setFechaRegistro(null);

        // Create the TMCOTIZACION, which fails.

        restTMCOTIZACIONMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACION)))
            .andExpect(status().isBadRequest());

        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONRepository.findAll().size();
        // set the field null
        tMCOTIZACION.setEstatus(null);

        // Create the TMCOTIZACION, which fails.

        restTMCOTIZACIONMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACION)))
            .andExpect(status().isBadRequest());

        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEliminadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMCOTIZACIONRepository.findAll().size();
        // set the field null
        tMCOTIZACION.setEliminada(null);

        // Create the TMCOTIZACION, which fails.

        restTMCOTIZACIONMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACION)))
            .andExpect(status().isBadRequest());

        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTMCOTIZACIONS() throws Exception {
        // Initialize the database
        tMCOTIZACIONRepository.saveAndFlush(tMCOTIZACION);

        // Get all the tMCOTIZACIONList
        restTMCOTIZACIONMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idCotizacion,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idCotizacion").value(hasItem(tMCOTIZACION.getIdCotizacion().intValue())))
            .andExpect(jsonPath("$.[*].usuario").value(hasItem(DEFAULT_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].fechaRegistro").value(hasItem(DEFAULT_FECHA_REGISTRO.toString())))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS.intValue())))
            .andExpect(jsonPath("$.[*].eliminada").value(hasItem(DEFAULT_ELIMINADA.intValue())));
    }

    @Test
    @Transactional
    void getTMCOTIZACION() throws Exception {
        // Initialize the database
        tMCOTIZACIONRepository.saveAndFlush(tMCOTIZACION);

        // Get the tMCOTIZACION
        restTMCOTIZACIONMockMvc
            .perform(get(ENTITY_API_URL_ID, tMCOTIZACION.getIdCotizacion()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idCotizacion").value(tMCOTIZACION.getIdCotizacion().intValue()))
            .andExpect(jsonPath("$.usuario").value(DEFAULT_USUARIO.intValue()))
            .andExpect(jsonPath("$.fechaRegistro").value(DEFAULT_FECHA_REGISTRO.toString()))
            .andExpect(jsonPath("$.estatus").value(DEFAULT_ESTATUS.intValue()))
            .andExpect(jsonPath("$.eliminada").value(DEFAULT_ELIMINADA.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTMCOTIZACION() throws Exception {
        // Get the tMCOTIZACION
        restTMCOTIZACIONMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTMCOTIZACION() throws Exception {
        // Initialize the database
        tMCOTIZACIONRepository.saveAndFlush(tMCOTIZACION);

        int databaseSizeBeforeUpdate = tMCOTIZACIONRepository.findAll().size();

        // Update the tMCOTIZACION
        TMCOTIZACION updatedTMCOTIZACION = tMCOTIZACIONRepository.findById(tMCOTIZACION.getIdCotizacion()).get();
        // Disconnect from session so that the updates on updatedTMCOTIZACION are not directly saved in db
        em.detach(updatedTMCOTIZACION);
        updatedTMCOTIZACION
            .usuario(UPDATED_USUARIO)
            .fechaRegistro(UPDATED_FECHA_REGISTRO)
            .estatus(UPDATED_ESTATUS)
            .eliminada(UPDATED_ELIMINADA);

        restTMCOTIZACIONMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTMCOTIZACION.getIdCotizacion())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTMCOTIZACION))
            )
            .andExpect(status().isOk());

        // Validate the TMCOTIZACION in the database
        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
        TMCOTIZACION testTMCOTIZACION = tMCOTIZACIONList.get(tMCOTIZACIONList.size() - 1);
        assertThat(testTMCOTIZACION.getUsuario()).isEqualTo(UPDATED_USUARIO);
        assertThat(testTMCOTIZACION.getFechaRegistro()).isEqualTo(UPDATED_FECHA_REGISTRO);
        assertThat(testTMCOTIZACION.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testTMCOTIZACION.getEliminada()).isEqualTo(UPDATED_ELIMINADA);
    }

    @Test
    @Transactional
    void putNonExistingTMCOTIZACION() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONRepository.findAll().size();
        tMCOTIZACION.setIdCotizacion(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTMCOTIZACIONMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tMCOTIZACION.getIdCotizacion())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACION))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMCOTIZACION in the database
        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTMCOTIZACION() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONRepository.findAll().size();
        tMCOTIZACION.setIdCotizacion(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMCOTIZACIONMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACION))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMCOTIZACION in the database
        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTMCOTIZACION() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONRepository.findAll().size();
        tMCOTIZACION.setIdCotizacion(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMCOTIZACIONMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMCOTIZACION)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TMCOTIZACION in the database
        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTMCOTIZACIONWithPatch() throws Exception {
        // Initialize the database
        tMCOTIZACIONRepository.saveAndFlush(tMCOTIZACION);

        int databaseSizeBeforeUpdate = tMCOTIZACIONRepository.findAll().size();

        // Update the tMCOTIZACION using partial update
        TMCOTIZACION partialUpdatedTMCOTIZACION = new TMCOTIZACION();
        partialUpdatedTMCOTIZACION.setIdCotizacion(tMCOTIZACION.getIdCotizacion());

        partialUpdatedTMCOTIZACION.fechaRegistro(UPDATED_FECHA_REGISTRO).eliminada(UPDATED_ELIMINADA);

        restTMCOTIZACIONMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTMCOTIZACION.getIdCotizacion())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTMCOTIZACION))
            )
            .andExpect(status().isOk());

        // Validate the TMCOTIZACION in the database
        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
        TMCOTIZACION testTMCOTIZACION = tMCOTIZACIONList.get(tMCOTIZACIONList.size() - 1);
        assertThat(testTMCOTIZACION.getUsuario()).isEqualTo(DEFAULT_USUARIO);
        assertThat(testTMCOTIZACION.getFechaRegistro()).isEqualTo(UPDATED_FECHA_REGISTRO);
        assertThat(testTMCOTIZACION.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testTMCOTIZACION.getEliminada()).isEqualTo(UPDATED_ELIMINADA);
    }

    @Test
    @Transactional
    void fullUpdateTMCOTIZACIONWithPatch() throws Exception {
        // Initialize the database
        tMCOTIZACIONRepository.saveAndFlush(tMCOTIZACION);

        int databaseSizeBeforeUpdate = tMCOTIZACIONRepository.findAll().size();

        // Update the tMCOTIZACION using partial update
        TMCOTIZACION partialUpdatedTMCOTIZACION = new TMCOTIZACION();
        partialUpdatedTMCOTIZACION.setIdCotizacion(tMCOTIZACION.getIdCotizacion());

        partialUpdatedTMCOTIZACION
            .usuario(UPDATED_USUARIO)
            .fechaRegistro(UPDATED_FECHA_REGISTRO)
            .estatus(UPDATED_ESTATUS)
            .eliminada(UPDATED_ELIMINADA);

        restTMCOTIZACIONMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTMCOTIZACION.getIdCotizacion())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTMCOTIZACION))
            )
            .andExpect(status().isOk());

        // Validate the TMCOTIZACION in the database
        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
        TMCOTIZACION testTMCOTIZACION = tMCOTIZACIONList.get(tMCOTIZACIONList.size() - 1);
        assertThat(testTMCOTIZACION.getUsuario()).isEqualTo(UPDATED_USUARIO);
        assertThat(testTMCOTIZACION.getFechaRegistro()).isEqualTo(UPDATED_FECHA_REGISTRO);
        assertThat(testTMCOTIZACION.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testTMCOTIZACION.getEliminada()).isEqualTo(UPDATED_ELIMINADA);
    }

    @Test
    @Transactional
    void patchNonExistingTMCOTIZACION() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONRepository.findAll().size();
        tMCOTIZACION.setIdCotizacion(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTMCOTIZACIONMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tMCOTIZACION.getIdCotizacion())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACION))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMCOTIZACION in the database
        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTMCOTIZACION() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONRepository.findAll().size();
        tMCOTIZACION.setIdCotizacion(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMCOTIZACIONMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tMCOTIZACION))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMCOTIZACION in the database
        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTMCOTIZACION() throws Exception {
        int databaseSizeBeforeUpdate = tMCOTIZACIONRepository.findAll().size();
        tMCOTIZACION.setIdCotizacion(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMCOTIZACIONMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tMCOTIZACION))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TMCOTIZACION in the database
        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTMCOTIZACION() throws Exception {
        // Initialize the database
        tMCOTIZACIONRepository.saveAndFlush(tMCOTIZACION);

        int databaseSizeBeforeDelete = tMCOTIZACIONRepository.findAll().size();

        // Delete the tMCOTIZACION
        restTMCOTIZACIONMockMvc
            .perform(delete(ENTITY_API_URL_ID, tMCOTIZACION.getIdCotizacion()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TMCOTIZACION> tMCOTIZACIONList = tMCOTIZACIONRepository.findAll();
        assertThat(tMCOTIZACIONList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
