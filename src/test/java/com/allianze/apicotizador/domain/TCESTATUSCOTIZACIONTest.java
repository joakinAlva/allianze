package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCESTATUSCOTIZACIONTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCESTATUSCOTIZACION.class);
        TCESTATUSCOTIZACION tCESTATUSCOTIZACION1 = new TCESTATUSCOTIZACION();
        tCESTATUSCOTIZACION1.setIdEstatusCotizacion(1L);
        TCESTATUSCOTIZACION tCESTATUSCOTIZACION2 = new TCESTATUSCOTIZACION();
        tCESTATUSCOTIZACION2.setIdEstatusCotizacion(tCESTATUSCOTIZACION1.getIdEstatusCotizacion());
        assertThat(tCESTATUSCOTIZACION1).isEqualTo(tCESTATUSCOTIZACION2);
        tCESTATUSCOTIZACION2.setIdEstatusCotizacion(2L);
        assertThat(tCESTATUSCOTIZACION1).isNotEqualTo(tCESTATUSCOTIZACION2);
        tCESTATUSCOTIZACION1.setIdEstatusCotizacion(null);
        assertThat(tCESTATUSCOTIZACION1).isNotEqualTo(tCESTATUSCOTIZACION2);
    }
}
