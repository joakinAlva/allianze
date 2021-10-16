package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TMASEGURADOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TMASEGURADO.class);
        TMASEGURADO tMASEGURADO1 = new TMASEGURADO();
        tMASEGURADO1.setIdAsegurados(1L);
        TMASEGURADO tMASEGURADO2 = new TMASEGURADO();
        tMASEGURADO2.setIdAsegurados(tMASEGURADO1.getIdAsegurados());
        assertThat(tMASEGURADO1).isEqualTo(tMASEGURADO2);
        tMASEGURADO2.setIdAsegurados(2L);
        assertThat(tMASEGURADO1).isNotEqualTo(tMASEGURADO2);
        tMASEGURADO1.setIdAsegurados(null);
        assertThat(tMASEGURADO1).isNotEqualTo(tMASEGURADO2);
    }
}
