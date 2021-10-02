package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCSUBGRUPO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCSUBGRUPO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCSUBGRUPORepository extends JpaRepository<TCSUBGRUPO, Long> {}
