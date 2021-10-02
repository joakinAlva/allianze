package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCPRIMATARIFA;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCPRIMATARIFA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCPRIMATARIFARepository extends JpaRepository<TCPRIMATARIFA, Long> {}
