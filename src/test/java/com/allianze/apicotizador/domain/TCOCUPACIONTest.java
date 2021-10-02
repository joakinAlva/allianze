package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCOCUPACIONTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCOCUPACION.class);
        TCOCUPACION tCOCUPACION1 = new TCOCUPACION();
        tCOCUPACION1.setIdOcupacion(1L);
        TCOCUPACION tCOCUPACION2 = new TCOCUPACION();
        tCOCUPACION2.setIdOcupacion(tCOCUPACION1.getIdOcupacion());
        assertThat(tCOCUPACION1).isEqualTo(tCOCUPACION2);
        tCOCUPACION2.setIdOcupacion(2L);
        assertThat(tCOCUPACION1).isNotEqualTo(tCOCUPACION2);
        tCOCUPACION1.setIdOcupacion(null);
        assertThat(tCOCUPACION1).isNotEqualTo(tCOCUPACION2);
    }
}
