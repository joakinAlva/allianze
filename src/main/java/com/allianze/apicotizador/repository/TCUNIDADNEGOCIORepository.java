package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCUNIDADNEGOCIO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCUNIDADNEGOCIO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCUNIDADNEGOCIORepository extends JpaRepository<TCUNIDADNEGOCIO, Long> {}
