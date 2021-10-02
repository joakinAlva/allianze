package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCDESCUENTOTIPORIESGO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCDESCUENTOTIPORIESGO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCDESCUENTOTIPORIESGORepository extends JpaRepository<TCDESCUENTOTIPORIESGO, Long> {}
