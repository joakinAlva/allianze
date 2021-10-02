package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCDESCUENTOTIPORIESGOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCDESCUENTOTIPORIESGO.class);
        TCDESCUENTOTIPORIESGO tCDESCUENTOTIPORIESGO1 = new TCDESCUENTOTIPORIESGO();
        tCDESCUENTOTIPORIESGO1.setIdDescuentoTipoRiesgo(1L);
        TCDESCUENTOTIPORIESGO tCDESCUENTOTIPORIESGO2 = new TCDESCUENTOTIPORIESGO();
        tCDESCUENTOTIPORIESGO2.setIdDescuentoTipoRiesgo(tCDESCUENTOTIPORIESGO1.getIdDescuentoTipoRiesgo());
        assertThat(tCDESCUENTOTIPORIESGO1).isEqualTo(tCDESCUENTOTIPORIESGO2);
        tCDESCUENTOTIPORIESGO2.setIdDescuentoTipoRiesgo(2L);
        assertThat(tCDESCUENTOTIPORIESGO1).isNotEqualTo(tCDESCUENTOTIPORIESGO2);
        tCDESCUENTOTIPORIESGO1.setIdDescuentoTipoRiesgo(null);
        assertThat(tCDESCUENTOTIPORIESGO1).isNotEqualTo(tCDESCUENTOTIPORIESGO2);
    }
}
