package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCREFENCIA;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCREFENCIA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCREFENCIARepository extends JpaRepository<TCREFENCIA, Long> {}
