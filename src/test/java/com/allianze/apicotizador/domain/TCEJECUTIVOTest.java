package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCEJECUTIVOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCEJECUTIVO.class);
        TCEJECUTIVO tCEJECUTIVO1 = new TCEJECUTIVO();
        tCEJECUTIVO1.setIdEjecutivo(1L);
        TCEJECUTIVO tCEJECUTIVO2 = new TCEJECUTIVO();
        tCEJECUTIVO2.setIdEjecutivo(tCEJECUTIVO1.getIdEjecutivo());
        assertThat(tCEJECUTIVO1).isEqualTo(tCEJECUTIVO2);
        tCEJECUTIVO2.setIdEjecutivo(2L);
        assertThat(tCEJECUTIVO1).isNotEqualTo(tCEJECUTIVO2);
        tCEJECUTIVO1.setIdEjecutivo(null);
        assertThat(tCEJECUTIVO1).isNotEqualTo(tCEJECUTIVO2);
    }
}
