package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCFACTORDESCUENTOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCFACTORDESCUENTO.class);
        TCFACTORDESCUENTO tCFACTORDESCUENTO1 = new TCFACTORDESCUENTO();
        tCFACTORDESCUENTO1.setIdFactorDescuento(1L);
        TCFACTORDESCUENTO tCFACTORDESCUENTO2 = new TCFACTORDESCUENTO();
        tCFACTORDESCUENTO2.setIdFactorDescuento(tCFACTORDESCUENTO1.getIdFactorDescuento());
        assertThat(tCFACTORDESCUENTO1).isEqualTo(tCFACTORDESCUENTO2);
        tCFACTORDESCUENTO2.setIdFactorDescuento(2L);
        assertThat(tCFACTORDESCUENTO1).isNotEqualTo(tCFACTORDESCUENTO2);
        tCFACTORDESCUENTO1.setIdFactorDescuento(null);
        assertThat(tCFACTORDESCUENTO1).isNotEqualTo(tCFACTORDESCUENTO2);
    }
}
