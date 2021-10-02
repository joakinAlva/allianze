package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCDESCUENTOSUMAASEGURADA;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCDESCUENTOSUMAASEGURADA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCDESCUENTOSUMAASEGURADARepository extends JpaRepository<TCDESCUENTOSUMAASEGURADA, Long> {}
