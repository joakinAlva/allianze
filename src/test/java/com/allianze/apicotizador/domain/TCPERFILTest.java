package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCPERFILTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCPERFIL.class);
        TCPERFIL tCPERFIL1 = new TCPERFIL();
        tCPERFIL1.setIdPerfil(1L);
        TCPERFIL tCPERFIL2 = new TCPERFIL();
        tCPERFIL2.setIdPerfil(tCPERFIL1.getIdPerfil());
        assertThat(tCPERFIL1).isEqualTo(tCPERFIL2);
        tCPERFIL2.setIdPerfil(2L);
        assertThat(tCPERFIL1).isNotEqualTo(tCPERFIL2);
        tCPERFIL1.setIdPerfil(null);
        assertThat(tCPERFIL1).isNotEqualTo(tCPERFIL2);
    }
}
