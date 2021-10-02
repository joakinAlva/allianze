package com.allianze.apicotizador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.allianze.apicotizador.IntegrationTest;
import com.allianze.apicotizador.domain.TMUSUARIO;
import com.allianze.apicotizador.repository.TMUSUARIORepository;
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
 * Integration tests for the {@link TMUSUARIOResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TMUSUARIOResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDOS = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDOS = "BBBBBBBBBB";

    private static final String DEFAULT_CORREO_ELECTRONICO = "AAAAAAAAAA";
    private static final String UPDATED_CORREO_ELECTRONICO = "BBBBBBBBBB";

    private static final String DEFAULT_USUARIO = "AAAAAAAAAA";
    private static final String UPDATED_USUARIO = "BBBBBBBBBB";

    private static final String DEFAULT_CLAVE = "AAAAAAAAAA";
    private static final String UPDATED_CLAVE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_REGISTRO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_REGISTRO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

    private static final Long DEFAULT_ESTATUS = 1L;
    private static final Long UPDATED_ESTATUS = 0L;

    private static final String ENTITY_API_URL = "/api/tmusuarios";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{idUsuario}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TMUSUARIORepository tMUSUARIORepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTMUSUARIOMockMvc;

    private TMUSUARIO tMUSUARIO;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TMUSUARIO createEntity(EntityManager em) {
        TMUSUARIO tMUSUARIO = new TMUSUARIO()
            .nombre(DEFAULT_NOMBRE)
            .apellidos(DEFAULT_APELLIDOS)
            .correoElectronico(DEFAULT_CORREO_ELECTRONICO)
            .usuario(DEFAULT_USUARIO)
            .clave(DEFAULT_CLAVE)
            .fechaRegistro(DEFAULT_FECHA_REGISTRO)
            .token(DEFAULT_TOKEN)
            .estatus(DEFAULT_ESTATUS);
        return tMUSUARIO;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TMUSUARIO createUpdatedEntity(EntityManager em) {
        TMUSUARIO tMUSUARIO = new TMUSUARIO()
            .nombre(UPDATED_NOMBRE)
            .apellidos(UPDATED_APELLIDOS)
            .correoElectronico(UPDATED_CORREO_ELECTRONICO)
            .usuario(UPDATED_USUARIO)
            .clave(UPDATED_CLAVE)
            .fechaRegistro(UPDATED_FECHA_REGISTRO)
            .token(UPDATED_TOKEN)
            .estatus(UPDATED_ESTATUS);
        return tMUSUARIO;
    }

    @BeforeEach
    public void initTest() {
        tMUSUARIO = createEntity(em);
    }

    @Test
    @Transactional
    void createTMUSUARIO() throws Exception {
        int databaseSizeBeforeCreate = tMUSUARIORepository.findAll().size();
        // Create the TMUSUARIO
        restTMUSUARIOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMUSUARIO)))
            .andExpect(status().isCreated());

        // Validate the TMUSUARIO in the database
        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeCreate + 1);
        TMUSUARIO testTMUSUARIO = tMUSUARIOList.get(tMUSUARIOList.size() - 1);
        assertThat(testTMUSUARIO.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testTMUSUARIO.getApellidos()).isEqualTo(DEFAULT_APELLIDOS);
        assertThat(testTMUSUARIO.getCorreoElectronico()).isEqualTo(DEFAULT_CORREO_ELECTRONICO);
        assertThat(testTMUSUARIO.getUsuario()).isEqualTo(DEFAULT_USUARIO);
        assertThat(testTMUSUARIO.getClave()).isEqualTo(DEFAULT_CLAVE);
        assertThat(testTMUSUARIO.getFechaRegistro()).isEqualTo(DEFAULT_FECHA_REGISTRO);
        assertThat(testTMUSUARIO.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testTMUSUARIO.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
    }

    @Test
    @Transactional
    void createTMUSUARIOWithExistingId() throws Exception {
        // Create the TMUSUARIO with an existing ID
        tMUSUARIO.setIdUsuario(1L);

        int databaseSizeBeforeCreate = tMUSUARIORepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTMUSUARIOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMUSUARIO)))
            .andExpect(status().isBadRequest());

        // Validate the TMUSUARIO in the database
        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMUSUARIORepository.findAll().size();
        // set the field null
        tMUSUARIO.setNombre(null);

        // Create the TMUSUARIO, which fails.

        restTMUSUARIOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMUSUARIO)))
            .andExpect(status().isBadRequest());

        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkApellidosIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMUSUARIORepository.findAll().size();
        // set the field null
        tMUSUARIO.setApellidos(null);

        // Create the TMUSUARIO, which fails.

        restTMUSUARIOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMUSUARIO)))
            .andExpect(status().isBadRequest());

        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCorreoElectronicoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMUSUARIORepository.findAll().size();
        // set the field null
        tMUSUARIO.setCorreoElectronico(null);

        // Create the TMUSUARIO, which fails.

        restTMUSUARIOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMUSUARIO)))
            .andExpect(status().isBadRequest());

        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUsuarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMUSUARIORepository.findAll().size();
        // set the field null
        tMUSUARIO.setUsuario(null);

        // Create the TMUSUARIO, which fails.

        restTMUSUARIOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMUSUARIO)))
            .andExpect(status().isBadRequest());

        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkClaveIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMUSUARIORepository.findAll().size();
        // set the field null
        tMUSUARIO.setClave(null);

        // Create the TMUSUARIO, which fails.

        restTMUSUARIOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMUSUARIO)))
            .andExpect(status().isBadRequest());

        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFechaRegistroIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMUSUARIORepository.findAll().size();
        // set the field null
        tMUSUARIO.setFechaRegistro(null);

        // Create the TMUSUARIO, which fails.

        restTMUSUARIOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMUSUARIO)))
            .andExpect(status().isBadRequest());

        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTokenIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMUSUARIORepository.findAll().size();
        // set the field null
        tMUSUARIO.setToken(null);

        // Create the TMUSUARIO, which fails.

        restTMUSUARIOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMUSUARIO)))
            .andExpect(status().isBadRequest());

        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = tMUSUARIORepository.findAll().size();
        // set the field null
        tMUSUARIO.setEstatus(null);

        // Create the TMUSUARIO, which fails.

        restTMUSUARIOMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMUSUARIO)))
            .andExpect(status().isBadRequest());

        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTMUSUARIOS() throws Exception {
        // Initialize the database
        tMUSUARIORepository.saveAndFlush(tMUSUARIO);

        // Get all the tMUSUARIOList
        restTMUSUARIOMockMvc
            .perform(get(ENTITY_API_URL + "?sort=idUsuario,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(tMUSUARIO.getIdUsuario().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellidos").value(hasItem(DEFAULT_APELLIDOS)))
            .andExpect(jsonPath("$.[*].correoElectronico").value(hasItem(DEFAULT_CORREO_ELECTRONICO)))
            .andExpect(jsonPath("$.[*].usuario").value(hasItem(DEFAULT_USUARIO)))
            .andExpect(jsonPath("$.[*].clave").value(hasItem(DEFAULT_CLAVE)))
            .andExpect(jsonPath("$.[*].fechaRegistro").value(hasItem(DEFAULT_FECHA_REGISTRO.toString())))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN)))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS.intValue())));
    }

    @Test
    @Transactional
    void getTMUSUARIO() throws Exception {
        // Initialize the database
        tMUSUARIORepository.saveAndFlush(tMUSUARIO);

        // Get the tMUSUARIO
        restTMUSUARIOMockMvc
            .perform(get(ENTITY_API_URL_ID, tMUSUARIO.getIdUsuario()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.idUsuario").value(tMUSUARIO.getIdUsuario().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.apellidos").value(DEFAULT_APELLIDOS))
            .andExpect(jsonPath("$.correoElectronico").value(DEFAULT_CORREO_ELECTRONICO))
            .andExpect(jsonPath("$.usuario").value(DEFAULT_USUARIO))
            .andExpect(jsonPath("$.clave").value(DEFAULT_CLAVE))
            .andExpect(jsonPath("$.fechaRegistro").value(DEFAULT_FECHA_REGISTRO.toString()))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN))
            .andExpect(jsonPath("$.estatus").value(DEFAULT_ESTATUS.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTMUSUARIO() throws Exception {
        // Get the tMUSUARIO
        restTMUSUARIOMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTMUSUARIO() throws Exception {
        // Initialize the database
        tMUSUARIORepository.saveAndFlush(tMUSUARIO);

        int databaseSizeBeforeUpdate = tMUSUARIORepository.findAll().size();

        // Update the tMUSUARIO
        TMUSUARIO updatedTMUSUARIO = tMUSUARIORepository.findById(tMUSUARIO.getIdUsuario()).get();
        // Disconnect from session so that the updates on updatedTMUSUARIO are not directly saved in db
        em.detach(updatedTMUSUARIO);
        updatedTMUSUARIO
            .nombre(UPDATED_NOMBRE)
            .apellidos(UPDATED_APELLIDOS)
            .correoElectronico(UPDATED_CORREO_ELECTRONICO)
            .usuario(UPDATED_USUARIO)
            .clave(UPDATED_CLAVE)
            .fechaRegistro(UPDATED_FECHA_REGISTRO)
            .token(UPDATED_TOKEN)
            .estatus(UPDATED_ESTATUS);

        restTMUSUARIOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTMUSUARIO.getIdUsuario())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTMUSUARIO))
            )
            .andExpect(status().isOk());

        // Validate the TMUSUARIO in the database
        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeUpdate);
        TMUSUARIO testTMUSUARIO = tMUSUARIOList.get(tMUSUARIOList.size() - 1);
        assertThat(testTMUSUARIO.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testTMUSUARIO.getApellidos()).isEqualTo(UPDATED_APELLIDOS);
        assertThat(testTMUSUARIO.getCorreoElectronico()).isEqualTo(UPDATED_CORREO_ELECTRONICO);
        assertThat(testTMUSUARIO.getUsuario()).isEqualTo(UPDATED_USUARIO);
        assertThat(testTMUSUARIO.getClave()).isEqualTo(UPDATED_CLAVE);
        assertThat(testTMUSUARIO.getFechaRegistro()).isEqualTo(UPDATED_FECHA_REGISTRO);
        assertThat(testTMUSUARIO.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testTMUSUARIO.getEstatus()).isEqualTo(UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    void putNonExistingTMUSUARIO() throws Exception {
        int databaseSizeBeforeUpdate = tMUSUARIORepository.findAll().size();
        tMUSUARIO.setIdUsuario(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTMUSUARIOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tMUSUARIO.getIdUsuario())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMUSUARIO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMUSUARIO in the database
        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTMUSUARIO() throws Exception {
        int databaseSizeBeforeUpdate = tMUSUARIORepository.findAll().size();
        tMUSUARIO.setIdUsuario(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMUSUARIOMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tMUSUARIO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMUSUARIO in the database
        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTMUSUARIO() throws Exception {
        int databaseSizeBeforeUpdate = tMUSUARIORepository.findAll().size();
        tMUSUARIO.setIdUsuario(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMUSUARIOMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tMUSUARIO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TMUSUARIO in the database
        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTMUSUARIOWithPatch() throws Exception {
        // Initialize the database
        tMUSUARIORepository.saveAndFlush(tMUSUARIO);

        int databaseSizeBeforeUpdate = tMUSUARIORepository.findAll().size();

        // Update the tMUSUARIO using partial update
        TMUSUARIO partialUpdatedTMUSUARIO = new TMUSUARIO();
        partialUpdatedTMUSUARIO.setIdUsuario(tMUSUARIO.getIdUsuario());

        partialUpdatedTMUSUARIO.usuario(UPDATED_USUARIO).token(UPDATED_TOKEN).estatus(UPDATED_ESTATUS);

        restTMUSUARIOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTMUSUARIO.getIdUsuario())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTMUSUARIO))
            )
            .andExpect(status().isOk());

        // Validate the TMUSUARIO in the database
        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeUpdate);
        TMUSUARIO testTMUSUARIO = tMUSUARIOList.get(tMUSUARIOList.size() - 1);
        assertThat(testTMUSUARIO.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testTMUSUARIO.getApellidos()).isEqualTo(DEFAULT_APELLIDOS);
        assertThat(testTMUSUARIO.getCorreoElectronico()).isEqualTo(DEFAULT_CORREO_ELECTRONICO);
        assertThat(testTMUSUARIO.getUsuario()).isEqualTo(UPDATED_USUARIO);
        assertThat(testTMUSUARIO.getClave()).isEqualTo(DEFAULT_CLAVE);
        assertThat(testTMUSUARIO.getFechaRegistro()).isEqualTo(DEFAULT_FECHA_REGISTRO);
        assertThat(testTMUSUARIO.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testTMUSUARIO.getEstatus()).isEqualTo(UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    void fullUpdateTMUSUARIOWithPatch() throws Exception {
        // Initialize the database
        tMUSUARIORepository.saveAndFlush(tMUSUARIO);

        int databaseSizeBeforeUpdate = tMUSUARIORepository.findAll().size();

        // Update the tMUSUARIO using partial update
        TMUSUARIO partialUpdatedTMUSUARIO = new TMUSUARIO();
        partialUpdatedTMUSUARIO.setIdUsuario(tMUSUARIO.getIdUsuario());

        partialUpdatedTMUSUARIO
            .nombre(UPDATED_NOMBRE)
            .apellidos(UPDATED_APELLIDOS)
            .correoElectronico(UPDATED_CORREO_ELECTRONICO)
            .usuario(UPDATED_USUARIO)
            .clave(UPDATED_CLAVE)
            .fechaRegistro(UPDATED_FECHA_REGISTRO)
            .token(UPDATED_TOKEN)
            .estatus(UPDATED_ESTATUS);

        restTMUSUARIOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTMUSUARIO.getIdUsuario())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTMUSUARIO))
            )
            .andExpect(status().isOk());

        // Validate the TMUSUARIO in the database
        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeUpdate);
        TMUSUARIO testTMUSUARIO = tMUSUARIOList.get(tMUSUARIOList.size() - 1);
        assertThat(testTMUSUARIO.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testTMUSUARIO.getApellidos()).isEqualTo(UPDATED_APELLIDOS);
        assertThat(testTMUSUARIO.getCorreoElectronico()).isEqualTo(UPDATED_CORREO_ELECTRONICO);
        assertThat(testTMUSUARIO.getUsuario()).isEqualTo(UPDATED_USUARIO);
        assertThat(testTMUSUARIO.getClave()).isEqualTo(UPDATED_CLAVE);
        assertThat(testTMUSUARIO.getFechaRegistro()).isEqualTo(UPDATED_FECHA_REGISTRO);
        assertThat(testTMUSUARIO.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testTMUSUARIO.getEstatus()).isEqualTo(UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    void patchNonExistingTMUSUARIO() throws Exception {
        int databaseSizeBeforeUpdate = tMUSUARIORepository.findAll().size();
        tMUSUARIO.setIdUsuario(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTMUSUARIOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tMUSUARIO.getIdUsuario())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tMUSUARIO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMUSUARIO in the database
        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTMUSUARIO() throws Exception {
        int databaseSizeBeforeUpdate = tMUSUARIORepository.findAll().size();
        tMUSUARIO.setIdUsuario(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMUSUARIOMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tMUSUARIO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TMUSUARIO in the database
        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTMUSUARIO() throws Exception {
        int databaseSizeBeforeUpdate = tMUSUARIORepository.findAll().size();
        tMUSUARIO.setIdUsuario(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTMUSUARIOMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tMUSUARIO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TMUSUARIO in the database
        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTMUSUARIO() throws Exception {
        // Initialize the database
        tMUSUARIORepository.saveAndFlush(tMUSUARIO);

        int databaseSizeBeforeDelete = tMUSUARIORepository.findAll().size();

        // Delete the tMUSUARIO
        restTMUSUARIOMockMvc
            .perform(delete(ENTITY_API_URL_ID, tMUSUARIO.getIdUsuario()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TMUSUARIO> tMUSUARIOList = tMUSUARIORepository.findAll();
        assertThat(tMUSUARIOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
