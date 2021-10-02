package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCEDADRECARGOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCEDADRECARGO.class);
        TCEDADRECARGO tCEDADRECARGO1 = new TCEDADRECARGO();
        tCEDADRECARGO1.setIdEdadRecargo(1L);
        TCEDADRECARGO tCEDADRECARGO2 = new TCEDADRECARGO();
        tCEDADRECARGO2.setIdEdadRecargo(tCEDADRECARGO1.getIdEdadRecargo());
        assertThat(tCEDADRECARGO1).isEqualTo(tCEDADRECARGO2);
        tCEDADRECARGO2.setIdEdadRecargo(2L);
        assertThat(tCEDADRECARGO1).isNotEqualTo(tCEDADRECARGO2);
        tCEDADRECARGO1.setIdEdadRecargo(null);
        assertThat(tCEDADRECARGO1).isNotEqualTo(tCEDADRECARGO2);
    }
}
