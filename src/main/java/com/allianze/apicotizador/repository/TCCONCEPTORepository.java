package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCCONCEPTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCCONCEPTO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCCONCEPTORepository extends JpaRepository<TCCONCEPTO, Long> {}
