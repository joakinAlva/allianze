package com.allianze.apicotizador.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.allianze.apicotizador.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCUNIDADNEGOCIOTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCUNIDADNEGOCIO.class);
        TCUNIDADNEGOCIO tCUNIDADNEGOCIO1 = new TCUNIDADNEGOCIO();
        tCUNIDADNEGOCIO1.setIdUnidadNegocio(1L);
        TCUNIDADNEGOCIO tCUNIDADNEGOCIO2 = new TCUNIDADNEGOCIO();
        tCUNIDADNEGOCIO2.setIdUnidadNegocio(tCUNIDADNEGOCIO1.getIdUnidadNegocio());
        assertThat(tCUNIDADNEGOCIO1).isEqualTo(tCUNIDADNEGOCIO2);
        tCUNIDADNEGOCIO2.setIdUnidadNegocio(2L);
        assertThat(tCUNIDADNEGOCIO1).isNotEqualTo(tCUNIDADNEGOCIO2);
        tCUNIDADNEGOCIO1.setIdUnidadNegocio(null);
        assertThat(tCUNIDADNEGOCIO1).isNotEqualTo(tCUNIDADNEGOCIO2);
    }
}
