package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCCUOTATARIFASDESCTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCCUOTATARIFASDESC.class);
        TCCUOTATARIFASDESC tCCUOTATARIFASDESC1 = new TCCUOTATARIFASDESC();
        tCCUOTATARIFASDESC1.setIdCuotaTarifaSdesc(1L);
        TCCUOTATARIFASDESC tCCUOTATARIFASDESC2 = new TCCUOTATARIFASDESC();
        tCCUOTATARIFASDESC2.setIdCuotaTarifaSdesc(tCCUOTATARIFASDESC1.getIdCuotaTarifaSdesc());
        assertThat(tCCUOTATARIFASDESC1).isEqualTo(tCCUOTATARIFASDESC2);
        tCCUOTATARIFASDESC2.setIdCuotaTarifaSdesc(2L);
        assertThat(tCCUOTATARIFASDESC1).isNotEqualTo(tCCUOTATARIFASDESC2);
        tCCUOTATARIFASDESC1.setIdCuotaTarifaSdesc(null);
        assertThat(tCCUOTATARIFASDESC1).isNotEqualTo(tCCUOTATARIFASDESC2);
    }
}
