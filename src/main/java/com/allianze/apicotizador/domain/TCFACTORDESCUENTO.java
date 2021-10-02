package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCFACTORDESCUENTO.
 */
@Entity
@Table(name = "tcfactordescuento")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCFACTORDESCUENTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idFactorDescuento;

    @NotNull
    @Column(name = "factor", nullable = false)
    private Long factor;

    @NotNull
    @Column(name = "porcentaje", nullable = false)
    private Long porcentaje;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdFactorDescuento() {
        return idFactorDescuento;
    }

    public void setIdFactorDescuento(Long idFactorDescuento) {
        this.idFactorDescuento = idFactorDescuento;
    }

    public TCFACTORDESCUENTO idFactorDescuento(Long idFactorDescuento) {
        this.idFactorDescuento = idFactorDescuento;
        return this;
    }

    public Long getFactor() {
        return this.factor;
    }

    public TCFACTORDESCUENTO factor(Long factor) {
        this.factor = factor;
        return this;
    }

    public void setFactor(Long factor) {
        this.factor = factor;
    }

    public Long getPorcentaje() {
        return this.porcentaje;
    }

    public TCFACTORDESCUENTO porcentaje(Long porcentaje) {
        this.porcentaje = porcentaje;
        return this;
    }

    public void setPorcentaje(Long porcentaje) {
        this.porcentaje = porcentaje;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCFACTORDESCUENTO)) {
            return false;
        }
        return idFactorDescuento != null && idFactorDescuento.equals(((TCFACTORDESCUENTO) o).idFactorDescuento);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCFACTORDESCUENTO{" +
            "idFactorDescuento=" + getIdFactorDescuento() +
            ", factor=" + getFactor() +
            ", porcentaje=" + getPorcentaje() +
            "}";
    }
}
