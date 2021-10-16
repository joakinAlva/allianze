package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TMCOTIZACIONINFOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TMCOTIZACIONINFO.class);
        TMCOTIZACIONINFO tMCOTIZACIONINFO1 = new TMCOTIZACIONINFO();
        tMCOTIZACIONINFO1.setIdCotizacionInfo(1L);
        TMCOTIZACIONINFO tMCOTIZACIONINFO2 = new TMCOTIZACIONINFO();
        tMCOTIZACIONINFO2.setIdCotizacionInfo(tMCOTIZACIONINFO1.getIdCotizacionInfo());
        assertThat(tMCOTIZACIONINFO1).isEqualTo(tMCOTIZACIONINFO2);
        tMCOTIZACIONINFO2.setIdCotizacionInfo(2L);
        assertThat(tMCOTIZACIONINFO1).isNotEqualTo(tMCOTIZACIONINFO2);
        tMCOTIZACIONINFO1.setIdCotizacionInfo(null);
        assertThat(tMCOTIZACIONINFO1).isNotEqualTo(tMCOTIZACIONINFO2);
    }
}
