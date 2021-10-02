package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCTIPOREGLA;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCTIPOREGLA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCTIPOREGLARepository extends JpaRepository<TCTIPOREGLA, Long> {}
