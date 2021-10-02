package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCCUOTAPROPUESTATest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCCUOTAPROPUESTA.class);
        TCCUOTAPROPUESTA tCCUOTAPROPUESTA1 = new TCCUOTAPROPUESTA();
        tCCUOTAPROPUESTA1.setIdCuotaPropuesta(1L);
        TCCUOTAPROPUESTA tCCUOTAPROPUESTA2 = new TCCUOTAPROPUESTA();
        tCCUOTAPROPUESTA2.setIdCuotaPropuesta(tCCUOTAPROPUESTA1.getIdCuotaPropuesta());
        assertThat(tCCUOTAPROPUESTA1).isEqualTo(tCCUOTAPROPUESTA2);
        tCCUOTAPROPUESTA2.setIdCuotaPropuesta(2L);
        assertThat(tCCUOTAPROPUESTA1).isNotEqualTo(tCCUOTAPROPUESTA2);
        tCCUOTAPROPUESTA1.setIdCuotaPropuesta(null);
        assertThat(tCCUOTAPROPUESTA1).isNotEqualTo(tCCUOTAPROPUESTA2);
    }
}
