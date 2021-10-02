package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCSUMAASEGURADATest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCSUMAASEGURADA.class);
        TCSUMAASEGURADA tCSUMAASEGURADA1 = new TCSUMAASEGURADA();
        tCSUMAASEGURADA1.setIdSumaAsegurada(1L);
        TCSUMAASEGURADA tCSUMAASEGURADA2 = new TCSUMAASEGURADA();
        tCSUMAASEGURADA2.setIdSumaAsegurada(tCSUMAASEGURADA1.getIdSumaAsegurada());
        assertThat(tCSUMAASEGURADA1).isEqualTo(tCSUMAASEGURADA2);
        tCSUMAASEGURADA2.setIdSumaAsegurada(2L);
        assertThat(tCSUMAASEGURADA1).isNotEqualTo(tCSUMAASEGURADA2);
        tCSUMAASEGURADA1.setIdSumaAsegurada(null);
        assertThat(tCSUMAASEGURADA1).isNotEqualTo(tCSUMAASEGURADA2);
    }
}
