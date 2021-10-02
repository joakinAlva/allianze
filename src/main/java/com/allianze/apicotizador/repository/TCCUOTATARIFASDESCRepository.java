package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCCUOTATARIFASDESC;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCCUOTATARIFASDESC entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCCUOTATARIFASDESCRepository extends JpaRepository<TCCUOTATARIFASDESC, Long> {}
