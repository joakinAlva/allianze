package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCPERFIL;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCPERFIL entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCPERFILRepository extends JpaRepository<TCPERFIL, Long> {}
