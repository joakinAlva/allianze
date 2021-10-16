package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TMCOTIZACIONEXPPROPIA;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TMCOTIZACIONEXPPROPIA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TMCOTIZACIONEXPPROPIARepository extends JpaRepository<TMCOTIZACIONEXPPROPIA, Long> {}
