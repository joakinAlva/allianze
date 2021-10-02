package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCREGIONAL;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCREGIONAL entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCREGIONALRepository extends JpaRepository<TCREGIONAL, Long> {}
