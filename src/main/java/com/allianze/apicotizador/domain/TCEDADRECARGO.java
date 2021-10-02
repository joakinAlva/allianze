package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCEDADRECARGO.
 */
@Entity
@Table(name = "tcedadrecargo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCEDADRECARGO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idEdadRecargo;

    @NotNull
    @Column(name = "edad_min", nullable = false)
    private String edadMin;

    @NotNull
    @Column(name = "edad_max", nullable = false)
    private String edadMax;

    @NotNull
    @Column(name = "recargo_anterior", nullable = false)
    private Long recargoAnterior;

    @NotNull
    @Column(name = "recargo_actual", nullable = false)
    private Long recargoActual;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdEdadRecargo() {
        return idEdadRecargo;
    }

    public void setIdEdadRecargo(Long idEdadRecargo) {
        this.idEdadRecargo = idEdadRecargo;
    }

    public TCEDADRECARGO idEdadRecargo(Long idEdadRecargo) {
        this.idEdadRecargo = idEdadRecargo;
        return this;
    }

    public String getEdadMin() {
        return this.edadMin;
    }

    public TCEDADRECARGO edadMin(String edadMin) {
        this.edadMin = edadMin;
        return this;
    }

    public void setEdadMin(String edadMin) {
        this.edadMin = edadMin;
    }

    public String getEdadMax() {
        return this.edadMax;
    }

    public TCEDADRECARGO edadMax(String edadMax) {
        this.edadMax = edadMax;
        return this;
    }

    public void setEdadMax(String edadMax) {
        this.edadMax = edadMax;
    }

    public Long getRecargoAnterior() {
        return this.recargoAnterior;
    }

    public TCEDADRECARGO recargoAnterior(Long recargoAnterior) {
        this.recargoAnterior = recargoAnterior;
        return this;
    }

    public void setRecargoAnterior(Long recargoAnterior) {
        this.recargoAnterior = recargoAnterior;
    }

    public Long getRecargoActual() {
        return this.recargoActual;
    }

    public TCEDADRECARGO recargoActual(Long recargoActual) {
        this.recargoActual = recargoActual;
        return this;
    }

    public void setRecargoActual(Long recargoActual) {
        this.recargoActual = recargoActual;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCEDADRECARGO)) {
            return false;
        }
        return idEdadRecargo != null && idEdadRecargo.equals(((TCEDADRECARGO) o).idEdadRecargo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCEDADRECARGO{" +
            "idEdadRecargo=" + getIdEdadRecargo() +
            ", edadMin='" + getEdadMin() + "'" +
            ", edadMax='" + getEdadMax() + "'" +
            ", recargoAnterior=" + getRecargoAnterior() +
            ", recargoActual=" + getRecargoActual() +
            "}";
    }
}
