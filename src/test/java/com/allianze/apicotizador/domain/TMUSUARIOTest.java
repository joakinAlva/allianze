package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TMUSUARIOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TMUSUARIO.class);
        TMUSUARIO tMUSUARIO1 = new TMUSUARIO();
        tMUSUARIO1.setIdUsuario(1L);
        TMUSUARIO tMUSUARIO2 = new TMUSUARIO();
        tMUSUARIO2.setIdUsuario(tMUSUARIO1.getIdUsuario());
        assertThat(tMUSUARIO1).isEqualTo(tMUSUARIO2);
        tMUSUARIO2.setIdUsuario(2L);
        assertThat(tMUSUARIO1).isNotEqualTo(tMUSUARIO2);
        tMUSUARIO1.setIdUsuario(null);
        assertThat(tMUSUARIO1).isNotEqualTo(tMUSUARIO2);
    }
}
