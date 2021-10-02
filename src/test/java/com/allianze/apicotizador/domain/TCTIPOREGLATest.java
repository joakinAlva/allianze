package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCTIPOREGLATest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCTIPOREGLA.class);
        TCTIPOREGLA tCTIPOREGLA1 = new TCTIPOREGLA();
        tCTIPOREGLA1.setIdTipoRegla(1L);
        TCTIPOREGLA tCTIPOREGLA2 = new TCTIPOREGLA();
        tCTIPOREGLA2.setIdTipoRegla(tCTIPOREGLA1.getIdTipoRegla());
        assertThat(tCTIPOREGLA1).isEqualTo(tCTIPOREGLA2);
        tCTIPOREGLA2.setIdTipoRegla(2L);
        assertThat(tCTIPOREGLA1).isNotEqualTo(tCTIPOREGLA2);
        tCTIPOREGLA1.setIdTipoRegla(null);
        assertThat(tCTIPOREGLA1).isNotEqualTo(tCTIPOREGLA2);
    }
}
