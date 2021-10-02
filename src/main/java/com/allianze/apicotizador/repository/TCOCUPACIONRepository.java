package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCOCUPACION;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCOCUPACION entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCOCUPACIONRepository extends JpaRepository<TCOCUPACION, Long> {}
