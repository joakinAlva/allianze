package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCHIPOTESIS;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCHIPOTESIS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCHIPOTESISRepository extends JpaRepository<TCHIPOTESIS, Long> {}
