package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCRECARGOPAGOFRACCIONADOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCRECARGOPAGOFRACCIONADO.class);
        TCRECARGOPAGOFRACCIONADO tCRECARGOPAGOFRACCIONADO1 = new TCRECARGOPAGOFRACCIONADO();
        tCRECARGOPAGOFRACCIONADO1.setIdRecargoPagoFraccionado(1L);
        TCRECARGOPAGOFRACCIONADO tCRECARGOPAGOFRACCIONADO2 = new TCRECARGOPAGOFRACCIONADO();
        tCRECARGOPAGOFRACCIONADO2.setIdRecargoPagoFraccionado(tCRECARGOPAGOFRACCIONADO1.getIdRecargoPagoFraccionado());
        assertThat(tCRECARGOPAGOFRACCIONADO1).isEqualTo(tCRECARGOPAGOFRACCIONADO2);
        tCRECARGOPAGOFRACCIONADO2.setIdRecargoPagoFraccionado(2L);
        assertThat(tCRECARGOPAGOFRACCIONADO1).isNotEqualTo(tCRECARGOPAGOFRACCIONADO2);
        tCRECARGOPAGOFRACCIONADO1.setIdRecargoPagoFraccionado(null);
        assertThat(tCRECARGOPAGOFRACCIONADO1).isNotEqualTo(tCRECARGOPAGOFRACCIONADO2);
    }
}
