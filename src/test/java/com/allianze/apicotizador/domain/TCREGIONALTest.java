package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCREGIONALTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCREGIONAL.class);
        TCREGIONAL tCREGIONAL1 = new TCREGIONAL();
        tCREGIONAL1.setIdRegional(1L);
        TCREGIONAL tCREGIONAL2 = new TCREGIONAL();
        tCREGIONAL2.setIdRegional(tCREGIONAL1.getIdRegional());
        assertThat(tCREGIONAL1).isEqualTo(tCREGIONAL2);
        tCREGIONAL2.setIdRegional(2L);
        assertThat(tCREGIONAL1).isNotEqualTo(tCREGIONAL2);
        tCREGIONAL1.setIdRegional(null);
        assertThat(tCREGIONAL1).isNotEqualTo(tCREGIONAL2);
    }
}
