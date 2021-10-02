package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCTIPOPRODUCTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCTIPOPRODUCTO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCTIPOPRODUCTORepository extends JpaRepository<TCTIPOPRODUCTO, Long> {}
