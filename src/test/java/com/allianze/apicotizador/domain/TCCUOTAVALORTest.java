package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCCUOTAVALORTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCCUOTAVALOR.class);
        TCCUOTAVALOR tCCUOTAVALOR1 = new TCCUOTAVALOR();
        tCCUOTAVALOR1.setIdCuotaValor(1L);
        TCCUOTAVALOR tCCUOTAVALOR2 = new TCCUOTAVALOR();
        tCCUOTAVALOR2.setIdCuotaValor(tCCUOTAVALOR1.getIdCuotaValor());
        assertThat(tCCUOTAVALOR1).isEqualTo(tCCUOTAVALOR2);
        tCCUOTAVALOR2.setIdCuotaValor(2L);
        assertThat(tCCUOTAVALOR1).isNotEqualTo(tCCUOTAVALOR2);
        tCCUOTAVALOR1.setIdCuotaValor(null);
        assertThat(tCCUOTAVALOR1).isNotEqualTo(tCCUOTAVALOR2);
    }
}
