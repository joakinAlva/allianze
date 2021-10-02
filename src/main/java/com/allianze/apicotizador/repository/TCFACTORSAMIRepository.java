package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCFACTORSAMI;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCFACTORSAMI entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCFACTORSAMIRepository extends JpaRepository<TCFACTORSAMI, Long> {}
