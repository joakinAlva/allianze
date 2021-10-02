package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCTIPO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCTIPO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCTIPORepository extends JpaRepository<TCTIPO, Long> {}
