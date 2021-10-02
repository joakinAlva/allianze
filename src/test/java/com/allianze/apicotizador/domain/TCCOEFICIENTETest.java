package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCCOEFICIENTETest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCCOEFICIENTE.class);
        TCCOEFICIENTE tCCOEFICIENTE1 = new TCCOEFICIENTE();
        tCCOEFICIENTE1.setIdCoeficiente(1L);
        TCCOEFICIENTE tCCOEFICIENTE2 = new TCCOEFICIENTE();
        tCCOEFICIENTE2.setIdCoeficiente(tCCOEFICIENTE1.getIdCoeficiente());
        assertThat(tCCOEFICIENTE1).isEqualTo(tCCOEFICIENTE2);
        tCCOEFICIENTE2.setIdCoeficiente(2L);
        assertThat(tCCOEFICIENTE1).isNotEqualTo(tCCOEFICIENTE2);
        tCCOEFICIENTE1.setIdCoeficiente(null);
        assertThat(tCCOEFICIENTE1).isNotEqualTo(tCCOEFICIENTE2);
    }
}
