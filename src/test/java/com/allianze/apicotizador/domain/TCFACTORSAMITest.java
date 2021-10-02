package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCFACTORSAMITest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCFACTORSAMI.class);
        TCFACTORSAMI tCFACTORSAMI1 = new TCFACTORSAMI();
        tCFACTORSAMI1.setIdFactorSami(1L);
        TCFACTORSAMI tCFACTORSAMI2 = new TCFACTORSAMI();
        tCFACTORSAMI2.setIdFactorSami(tCFACTORSAMI1.getIdFactorSami());
        assertThat(tCFACTORSAMI1).isEqualTo(tCFACTORSAMI2);
        tCFACTORSAMI2.setIdFactorSami(2L);
        assertThat(tCFACTORSAMI1).isNotEqualTo(tCFACTORSAMI2);
        tCFACTORSAMI1.setIdFactorSami(null);
        assertThat(tCFACTORSAMI1).isNotEqualTo(tCFACTORSAMI2);
    }
}
