package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCESTATUSCOTIZACION;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCESTATUSCOTIZACION entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCESTATUSCOTIZACIONRepository extends JpaRepository<TCESTATUSCOTIZACION, Long> {}
