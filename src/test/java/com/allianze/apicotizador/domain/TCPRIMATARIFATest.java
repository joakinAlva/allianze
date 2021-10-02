package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCPRIMATARIFATest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCPRIMATARIFA.class);
        TCPRIMATARIFA tCPRIMATARIFA1 = new TCPRIMATARIFA();
        tCPRIMATARIFA1.setIdPrimaTarifa(1L);
        TCPRIMATARIFA tCPRIMATARIFA2 = new TCPRIMATARIFA();
        tCPRIMATARIFA2.setIdPrimaTarifa(tCPRIMATARIFA1.getIdPrimaTarifa());
        assertThat(tCPRIMATARIFA1).isEqualTo(tCPRIMATARIFA2);
        tCPRIMATARIFA2.setIdPrimaTarifa(2L);
        assertThat(tCPRIMATARIFA1).isNotEqualTo(tCPRIMATARIFA2);
        tCPRIMATARIFA1.setIdPrimaTarifa(null);
        assertThat(tCPRIMATARIFA1).isNotEqualTo(tCPRIMATARIFA2);
    }
}
