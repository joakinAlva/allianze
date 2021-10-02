package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCOCUPACION.
 */
@Entity
@Table(name = "tcocupacion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCOCUPACION implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idOcupacion;

    @NotNull
    @Column(name = "ocupacion", nullable = false)
    private String ocupacion;

    @NotNull
    @Column(name = "factor_giro_anterior", nullable = false)
    private Long factorGiroAnterior;

    @NotNull
    @Column(name = "factor_giro_actual", nullable = false)
    private Long factorGiroActual;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdOcupacion() {
        return idOcupacion;
    }

    public void setIdOcupacion(Long idOcupacion) {
        this.idOcupacion = idOcupacion;
    }

    public TCOCUPACION idOcupacion(Long idOcupacion) {
        this.idOcupacion = idOcupacion;
        return this;
    }

    public String getOcupacion() {
        return this.ocupacion;
    }

    public TCOCUPACION ocupacion(String ocupacion) {
        this.ocupacion = ocupacion;
        return this;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public Long getFactorGiroAnterior() {
        return this.factorGiroAnterior;
    }

    public TCOCUPACION factorGiroAnterior(Long factorGiroAnterior) {
        this.factorGiroAnterior = factorGiroAnterior;
        return this;
    }

    public void setFactorGiroAnterior(Long factorGiroAnterior) {
        this.factorGiroAnterior = factorGiroAnterior;
    }

    public Long getFactorGiroActual() {
        return this.factorGiroActual;
    }

    public TCOCUPACION factorGiroActual(Long factorGiroActual) {
        this.factorGiroActual = factorGiroActual;
        return this;
    }

    public void setFactorGiroActual(Long factorGiroActual) {
        this.factorGiroActual = factorGiroActual;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCOCUPACION)) {
            return false;
        }
        return idOcupacion != null && idOcupacion.equals(((TCOCUPACION) o).idOcupacion);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCOCUPACION{" +
            "idOcupacion=" + getIdOcupacion() +
            ", ocupacion='" + getOcupacion() + "'" +
            ", factorGiroAnterior=" + getFactorGiroAnterior() +
            ", factorGiroActual=" + getFactorGiroActual() +
            "}";
    }
}
