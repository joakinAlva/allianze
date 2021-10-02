package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCEDADRECARGO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCEDADRECARGO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCEDADRECARGORepository extends JpaRepository<TCEDADRECARGO, Long> {}
