package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCESTATUSCOTIZACION.
 */
@Entity
@Table(name = "tcestatuscotizacion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCESTATUSCOTIZACION implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idEstatusCotizacion;

    @NotNull
    @Column(name = "orden", nullable = false)
    private Long orden;

    @NotNull
    @Column(name = "estatus_cotizacion", nullable = false)
    private String estatusCotizacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdEstatusCotizacion() {
        return idEstatusCotizacion;
    }

    public void setIdEstatusCotizacion(Long idEstatusCotizacion) {
        this.idEstatusCotizacion = idEstatusCotizacion;
    }

    public TCESTATUSCOTIZACION idEstatusCotizacion(Long idEstatusCotizacion) {
        this.idEstatusCotizacion = idEstatusCotizacion;
        return this;
    }

    public Long getOrden() {
        return this.orden;
    }

    public TCESTATUSCOTIZACION orden(Long orden) {
        this.orden = orden;
        return this;
    }

    public void setOrden(Long orden) {
        this.orden = orden;
    }

    public String getEstatusCotizacion() {
        return this.estatusCotizacion;
    }

    public TCESTATUSCOTIZACION estatusCotizacion(String estatusCotizacion) {
        this.estatusCotizacion = estatusCotizacion;
        return this;
    }

    public void setEstatusCotizacion(String estatusCotizacion) {
        this.estatusCotizacion = estatusCotizacion;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCESTATUSCOTIZACION)) {
            return false;
        }
        return idEstatusCotizacion != null && idEstatusCotizacion.equals(((TCESTATUSCOTIZACION) o).idEstatusCotizacion);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCESTATUSCOTIZACION{" +
            "idEstatusCotizacion=" + getIdEstatusCotizacion() +
            ", orden=" + getOrden() +
            ", estatusCotizacion='" + getEstatusCotizacion() + "'" +
            "}";
    }
}
