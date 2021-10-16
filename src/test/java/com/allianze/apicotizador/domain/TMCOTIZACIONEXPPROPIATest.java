package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TMCOTIZACIONEXPPROPIATest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TMCOTIZACIONEXPPROPIA.class);
        TMCOTIZACIONEXPPROPIA tMCOTIZACIONEXPPROPIA1 = new TMCOTIZACIONEXPPROPIA();
        tMCOTIZACIONEXPPROPIA1.setIdCotizacionExpPropia(1L);
        TMCOTIZACIONEXPPROPIA tMCOTIZACIONEXPPROPIA2 = new TMCOTIZACIONEXPPROPIA();
        tMCOTIZACIONEXPPROPIA2.setIdCotizacionExpPropia(tMCOTIZACIONEXPPROPIA1.getIdCotizacionExpPropia());
        assertThat(tMCOTIZACIONEXPPROPIA1).isEqualTo(tMCOTIZACIONEXPPROPIA2);
        tMCOTIZACIONEXPPROPIA2.setIdCotizacionExpPropia(2L);
        assertThat(tMCOTIZACIONEXPPROPIA1).isNotEqualTo(tMCOTIZACIONEXPPROPIA2);
        tMCOTIZACIONEXPPROPIA1.setIdCotizacionExpPropia(null);
        assertThat(tMCOTIZACIONEXPPROPIA1).isNotEqualTo(tMCOTIZACIONEXPPROPIA2);
    }
}
