package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TMCOTIZACION;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TMCOTIZACION entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TMCOTIZACIONRepository extends JpaRepository<TMCOTIZACION, Long> {}
