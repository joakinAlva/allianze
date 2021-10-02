package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCRANGOPRIMATest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCRANGOPRIMA.class);
        TCRANGOPRIMA tCRANGOPRIMA1 = new TCRANGOPRIMA();
        tCRANGOPRIMA1.setIdRangoPrima(1L);
        TCRANGOPRIMA tCRANGOPRIMA2 = new TCRANGOPRIMA();
        tCRANGOPRIMA2.setIdRangoPrima(tCRANGOPRIMA1.getIdRangoPrima());
        assertThat(tCRANGOPRIMA1).isEqualTo(tCRANGOPRIMA2);
        tCRANGOPRIMA2.setIdRangoPrima(2L);
        assertThat(tCRANGOPRIMA1).isNotEqualTo(tCRANGOPRIMA2);
        tCRANGOPRIMA1.setIdRangoPrima(null);
        assertThat(tCRANGOPRIMA1).isNotEqualTo(tCRANGOPRIMA2);
    }
}
