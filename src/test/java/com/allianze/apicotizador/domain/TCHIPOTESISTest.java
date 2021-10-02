package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCHIPOTESISTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCHIPOTESIS.class);
        TCHIPOTESIS tCHIPOTESIS1 = new TCHIPOTESIS();
        tCHIPOTESIS1.setIdHipotesis(1L);
        TCHIPOTESIS tCHIPOTESIS2 = new TCHIPOTESIS();
        tCHIPOTESIS2.setIdHipotesis(tCHIPOTESIS1.getIdHipotesis());
        assertThat(tCHIPOTESIS1).isEqualTo(tCHIPOTESIS2);
        tCHIPOTESIS2.setIdHipotesis(2L);
        assertThat(tCHIPOTESIS1).isNotEqualTo(tCHIPOTESIS2);
        tCHIPOTESIS1.setIdHipotesis(null);
        assertThat(tCHIPOTESIS1).isNotEqualTo(tCHIPOTESIS2);
    }
}
