package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCCUOTARIESGO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCCUOTARIESGO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCCUOTARIESGORepository extends JpaRepository<TCCUOTARIESGO, Long> {}
