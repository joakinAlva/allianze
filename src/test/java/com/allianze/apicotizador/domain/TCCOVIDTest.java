package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCCOVIDTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCCOVID.class);
        TCCOVID tCCOVID1 = new TCCOVID();
        tCCOVID1.setIdCovid(1L);
        TCCOVID tCCOVID2 = new TCCOVID();
        tCCOVID2.setIdCovid(tCCOVID1.getIdCovid());
        assertThat(tCCOVID1).isEqualTo(tCCOVID2);
        tCCOVID2.setIdCovid(2L);
        assertThat(tCCOVID1).isNotEqualTo(tCCOVID2);
        tCCOVID1.setIdCovid(null);
        assertThat(tCCOVID1).isNotEqualTo(tCCOVID2);
    }
}
