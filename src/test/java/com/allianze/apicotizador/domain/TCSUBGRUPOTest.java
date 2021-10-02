package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCSUBGRUPOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCSUBGRUPO.class);
        TCSUBGRUPO tCSUBGRUPO1 = new TCSUBGRUPO();
        tCSUBGRUPO1.setIdSubGrupo(1L);
        TCSUBGRUPO tCSUBGRUPO2 = new TCSUBGRUPO();
        tCSUBGRUPO2.setIdSubGrupo(tCSUBGRUPO1.getIdSubGrupo());
        assertThat(tCSUBGRUPO1).isEqualTo(tCSUBGRUPO2);
        tCSUBGRUPO2.setIdSubGrupo(2L);
        assertThat(tCSUBGRUPO1).isNotEqualTo(tCSUBGRUPO2);
        tCSUBGRUPO1.setIdSubGrupo(null);
        assertThat(tCSUBGRUPO1).isNotEqualTo(tCSUBGRUPO2);
    }
}
