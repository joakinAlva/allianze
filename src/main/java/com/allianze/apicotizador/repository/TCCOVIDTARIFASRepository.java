package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCCOVIDTARIFAS;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCCOVIDTARIFAS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCCOVIDTARIFASRepository extends JpaRepository<TCCOVIDTARIFAS, Long> {}
