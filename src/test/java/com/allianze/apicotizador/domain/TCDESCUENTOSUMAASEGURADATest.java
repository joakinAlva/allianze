package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCDESCUENTOSUMAASEGURADATest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCDESCUENTOSUMAASEGURADA.class);
        TCDESCUENTOSUMAASEGURADA tCDESCUENTOSUMAASEGURADA1 = new TCDESCUENTOSUMAASEGURADA();
        tCDESCUENTOSUMAASEGURADA1.setIdDescuentoSumaAsegurada(1L);
        TCDESCUENTOSUMAASEGURADA tCDESCUENTOSUMAASEGURADA2 = new TCDESCUENTOSUMAASEGURADA();
        tCDESCUENTOSUMAASEGURADA2.setIdDescuentoSumaAsegurada(tCDESCUENTOSUMAASEGURADA1.getIdDescuentoSumaAsegurada());
        assertThat(tCDESCUENTOSUMAASEGURADA1).isEqualTo(tCDESCUENTOSUMAASEGURADA2);
        tCDESCUENTOSUMAASEGURADA2.setIdDescuentoSumaAsegurada(2L);
        assertThat(tCDESCUENTOSUMAASEGURADA1).isNotEqualTo(tCDESCUENTOSUMAASEGURADA2);
        tCDESCUENTOSUMAASEGURADA1.setIdDescuentoSumaAsegurada(null);
        assertThat(tCDESCUENTOSUMAASEGURADA1).isNotEqualTo(tCDESCUENTOSUMAASEGURADA2);
    }
}
