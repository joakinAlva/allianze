package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCCOBERTURATest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCCOBERTURA.class);
        TCCOBERTURA tCCOBERTURA1 = new TCCOBERTURA();
        tCCOBERTURA1.setIdCobertura(1L);
        TCCOBERTURA tCCOBERTURA2 = new TCCOBERTURA();
        tCCOBERTURA2.setIdCobertura(tCCOBERTURA1.getIdCobertura());
        assertThat(tCCOBERTURA1).isEqualTo(tCCOBERTURA2);
        tCCOBERTURA2.setIdCobertura(2L);
        assertThat(tCCOBERTURA1).isNotEqualTo(tCCOBERTURA2);
        tCCOBERTURA1.setIdCobertura(null);
        assertThat(tCCOBERTURA1).isNotEqualTo(tCCOBERTURA2);
    }
}
