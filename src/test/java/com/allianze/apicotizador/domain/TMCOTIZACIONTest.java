package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TMCOTIZACIONTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TMCOTIZACION.class);
        TMCOTIZACION tMCOTIZACION1 = new TMCOTIZACION();
        tMCOTIZACION1.setIdCotizacion(1L);
        TMCOTIZACION tMCOTIZACION2 = new TMCOTIZACION();
        tMCOTIZACION2.setIdCotizacion(tMCOTIZACION1.getIdCotizacion());
        assertThat(tMCOTIZACION1).isEqualTo(tMCOTIZACION2);
        tMCOTIZACION2.setIdCotizacion(2L);
        assertThat(tMCOTIZACION1).isNotEqualTo(tMCOTIZACION2);
        tMCOTIZACION1.setIdCotizacion(null);
        assertThat(tMCOTIZACION1).isNotEqualTo(tMCOTIZACION2);
    }
}
