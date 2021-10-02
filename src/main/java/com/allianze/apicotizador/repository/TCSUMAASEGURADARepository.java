package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCSUMAASEGURADA;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCSUMAASEGURADA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCSUMAASEGURADARepository extends JpaRepository<TCSUMAASEGURADA, Long> {}
