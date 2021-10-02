package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCFACTORDESCUENTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCFACTORDESCUENTO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCFACTORDESCUENTORepository extends JpaRepository<TCFACTORDESCUENTO, Long> {}
