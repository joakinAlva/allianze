package com.allianze.apicotizador.repository;

import com.allianze.apicotizador.domain.TCRECARGOPAGOFRACCIONADO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TCRECARGOPAGOFRACCIONADO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCRECARGOPAGOFRACCIONADORepository extends JpaRepository<TCRECARGOPAGOFRACCIONADO, Long> {}
