package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCCOVID;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCCOVID entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCCOVIDRepository extends JpaRepository<TCCOVID, Long> {}
