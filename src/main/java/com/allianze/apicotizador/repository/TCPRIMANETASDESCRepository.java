package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCPRIMANETASDESC;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCPRIMANETASDESC entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCPRIMANETASDESCRepository extends JpaRepository<TCPRIMANETASDESC, Long> {}
