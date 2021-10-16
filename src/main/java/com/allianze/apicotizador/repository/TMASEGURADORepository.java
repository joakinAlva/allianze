package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TMASEGURADO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TMASEGURADO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TMASEGURADORepository extends JpaRepository<TMASEGURADO, Long> {}
