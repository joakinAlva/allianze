package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCRANGOPRIMA;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCRANGOPRIMA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCRANGOPRIMARepository extends JpaRepository<TCRANGOPRIMA, Long> {}
