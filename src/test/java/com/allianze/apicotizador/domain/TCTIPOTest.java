package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCTIPOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCTIPO.class);
        TCTIPO tCTIPO1 = new TCTIPO();
        tCTIPO1.setIdTipo(1L);
        TCTIPO tCTIPO2 = new TCTIPO();
        tCTIPO2.setIdTipo(tCTIPO1.getIdTipo());
        assertThat(tCTIPO1).isEqualTo(tCTIPO2);
        tCTIPO2.setIdTipo(2L);
        assertThat(tCTIPO1).isNotEqualTo(tCTIPO2);
        tCTIPO1.setIdTipo(null);
        assertThat(tCTIPO1).isNotEqualTo(tCTIPO2);
    }
}
