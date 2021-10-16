package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TMCOTIZACIONINFO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TMCOTIZACIONINFO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TMCOTIZACIONINFORepository extends JpaRepository<TMCOTIZACIONINFO, Long> {}
