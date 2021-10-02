package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCREFENCIATest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCREFENCIA.class);
        TCREFENCIA tCREFENCIA1 = new TCREFENCIA();
        tCREFENCIA1.setIdReferencia(1L);
        TCREFENCIA tCREFENCIA2 = new TCREFENCIA();
        tCREFENCIA2.setIdReferencia(tCREFENCIA1.getIdReferencia());
        assertThat(tCREFENCIA1).isEqualTo(tCREFENCIA2);
        tCREFENCIA2.setIdReferencia(2L);
        assertThat(tCREFENCIA1).isNotEqualTo(tCREFENCIA2);
        tCREFENCIA1.setIdReferencia(null);
        assertThat(tCREFENCIA1).isNotEqualTo(tCREFENCIA2);
    }
}
