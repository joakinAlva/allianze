package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCCOVIDTARIFASTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCCOVIDTARIFAS.class);
        TCCOVIDTARIFAS tCCOVIDTARIFAS1 = new TCCOVIDTARIFAS();
        tCCOVIDTARIFAS1.setIdCovidTarifas(1L);
        TCCOVIDTARIFAS tCCOVIDTARIFAS2 = new TCCOVIDTARIFAS();
        tCCOVIDTARIFAS2.setIdCovidTarifas(tCCOVIDTARIFAS1.getIdCovidTarifas());
        assertThat(tCCOVIDTARIFAS1).isEqualTo(tCCOVIDTARIFAS2);
        tCCOVIDTARIFAS2.setIdCovidTarifas(2L);
        assertThat(tCCOVIDTARIFAS1).isNotEqualTo(tCCOVIDTARIFAS2);
        tCCOVIDTARIFAS1.setIdCovidTarifas(null);
        assertThat(tCCOVIDTARIFAS1).isNotEqualTo(tCCOVIDTARIFAS2);
    }
}
