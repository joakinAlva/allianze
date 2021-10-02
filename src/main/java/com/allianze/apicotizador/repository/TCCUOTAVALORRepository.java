package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCCUOTAVALOR;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCCUOTAVALOR entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCCUOTAVALORRepository extends JpaRepository<TCCUOTAVALOR, Long> {}
