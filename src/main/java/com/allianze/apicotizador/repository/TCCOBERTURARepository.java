package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCCOBERTURA;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCCOBERTURA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCCOBERTURARepository extends JpaRepository<TCCOBERTURA, Long> {}
