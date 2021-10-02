package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCPRIMANETASDESCTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCPRIMANETASDESC.class);
        TCPRIMANETASDESC tCPRIMANETASDESC1 = new TCPRIMANETASDESC();
        tCPRIMANETASDESC1.setIdPrimaNetaSdesc(1L);
        TCPRIMANETASDESC tCPRIMANETASDESC2 = new TCPRIMANETASDESC();
        tCPRIMANETASDESC2.setIdPrimaNetaSdesc(tCPRIMANETASDESC1.getIdPrimaNetaSdesc());
        assertThat(tCPRIMANETASDESC1).isEqualTo(tCPRIMANETASDESC2);
        tCPRIMANETASDESC2.setIdPrimaNetaSdesc(2L);
        assertThat(tCPRIMANETASDESC1).isNotEqualTo(tCPRIMANETASDESC2);
        tCPRIMANETASDESC1.setIdPrimaNetaSdesc(null);
        assertThat(tCPRIMANETASDESC1).isNotEqualTo(tCPRIMANETASDESC2);
    }
}
