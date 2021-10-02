package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TMUSUARIO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TMUSUARIO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TMUSUARIORepository extends JpaRepository<TMUSUARIO, Long> {}
