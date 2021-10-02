package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCCUOTARIESGOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCCUOTARIESGO.class);
        TCCUOTARIESGO tCCUOTARIESGO1 = new TCCUOTARIESGO();
        tCCUOTARIESGO1.setIdCuotaRiesgo(1L);
        TCCUOTARIESGO tCCUOTARIESGO2 = new TCCUOTARIESGO();
        tCCUOTARIESGO2.setIdCuotaRiesgo(tCCUOTARIESGO1.getIdCuotaRiesgo());
        assertThat(tCCUOTARIESGO1).isEqualTo(tCCUOTARIESGO2);
        tCCUOTARIESGO2.setIdCuotaRiesgo(2L);
        assertThat(tCCUOTARIESGO1).isNotEqualTo(tCCUOTARIESGO2);
        tCCUOTARIESGO1.setIdCuotaRiesgo(null);
        assertThat(tCCUOTARIESGO1).isNotEqualTo(tCCUOTARIESGO2);
    }
}
