package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCTIPOPRODUCTOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCTIPOPRODUCTO.class);
        TCTIPOPRODUCTO tCTIPOPRODUCTO1 = new TCTIPOPRODUCTO();
        tCTIPOPRODUCTO1.setIdTipoProducto(1L);
        TCTIPOPRODUCTO tCTIPOPRODUCTO2 = new TCTIPOPRODUCTO();
        tCTIPOPRODUCTO2.setIdTipoProducto(tCTIPOPRODUCTO1.getIdTipoProducto());
        assertThat(tCTIPOPRODUCTO1).isEqualTo(tCTIPOPRODUCTO2);
        tCTIPOPRODUCTO2.setIdTipoProducto(2L);
        assertThat(tCTIPOPRODUCTO1).isNotEqualTo(tCTIPOPRODUCTO2);
        tCTIPOPRODUCTO1.setIdTipoProducto(null);
        assertThat(tCTIPOPRODUCTO1).isNotEqualTo(tCTIPOPRODUCTO2);
    }
}
