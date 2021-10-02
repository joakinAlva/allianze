package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCEJECUTIVO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCEJECUTIVO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCEJECUTIVORepository extends JpaRepository<TCEJECUTIVO, Long> {}
