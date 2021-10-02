package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCCUOTAPROPUESTA;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCCUOTAPROPUESTA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCCUOTAPROPUESTARepository extends JpaRepository<TCCUOTAPROPUESTA, Long> {}
