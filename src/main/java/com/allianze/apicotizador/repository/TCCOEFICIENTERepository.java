package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCCOEFICIENTE;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCCOEFICIENTE entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCCOEFICIENTERepository extends JpaRepository<TCCOEFICIENTE, Long> {}
