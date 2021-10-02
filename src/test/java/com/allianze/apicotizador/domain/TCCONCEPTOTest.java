package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCCONCEPTOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCCONCEPTO.class);
        TCCONCEPTO tCCONCEPTO1 = new TCCONCEPTO();
        tCCONCEPTO1.setIdConcepto(1L);
        TCCONCEPTO tCCONCEPTO2 = new TCCONCEPTO();
        tCCONCEPTO2.setIdConcepto(tCCONCEPTO1.getIdConcepto());
        assertThat(tCCONCEPTO1).isEqualTo(tCCONCEPTO2);
        tCCONCEPTO2.setIdConcepto(2L);
        assertThat(tCCONCEPTO1).isNotEqualTo(tCCONCEPTO2);
        tCCONCEPTO1.setIdConcepto(null);
        assertThat(tCCONCEPTO1).isNotEqualTo(tCCONCEPTO2);
    }
}
