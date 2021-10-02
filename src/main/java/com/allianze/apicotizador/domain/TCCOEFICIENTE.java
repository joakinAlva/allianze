package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCCOEFICIENTE.
 */
@Entity
@Table(name = "tccoeficiente")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCCOEFICIENTE implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idCoeficiente;

    @NotNull
    @Column(name = "coeficiente", nullable = false)
    private Long coeficiente;

    @NotNull
    @Column(name = "intervalo_min", nullable = false)
    private Long intervaloMin;

    @NotNull
    @Column(name = "intervalo_max", nullable = false)
    private Long intervaloMax;

    @NotNull
    @Column(name = "descuento_extra", nullable = false)
    private Long descuentoExtra;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdCoeficiente() {
        return idCoeficiente;
    }

    public void setIdCoeficiente(Long idCoeficiente) {
        this.idCoeficiente = idCoeficiente;
    }

    public TCCOEFICIENTE idCoeficiente(Long idCoeficiente) {
        this.idCoeficiente = idCoeficiente;
        return this;
    }

    public Long getCoeficiente() {
        return this.coeficiente;
    }

    public TCCOEFICIENTE coeficiente(Long coeficiente) {
        this.coeficiente = coeficiente;
        return this;
    }

    public void setCoeficiente(Long coeficiente) {
        this.coeficiente = coeficiente;
    }

    public Long getIntervaloMin() {
        return this.intervaloMin;
    }

    public TCCOEFICIENTE intervaloMin(Long intervaloMin) {
        this.intervaloMin = intervaloMin;
        return this;
    }

    public void setIntervaloMin(Long intervaloMin) {
        this.intervaloMin = intervaloMin;
    }

    public Long getIntervaloMax() {
        return this.intervaloMax;
    }

    public TCCOEFICIENTE intervaloMax(Long intervaloMax) {
        this.intervaloMax = intervaloMax;
        return this;
    }

    public void setIntervaloMax(Long intervaloMax) {
        this.intervaloMax = intervaloMax;
    }

    public Long getDescuentoExtra() {
        return this.descuentoExtra;
    }

    public TCCOEFICIENTE descuentoExtra(Long descuentoExtra) {
        this.descuentoExtra = descuentoExtra;
        return this;
    }

    public void setDescuentoExtra(Long descuentoExtra) {
        this.descuentoExtra = descuentoExtra;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCCOEFICIENTE)) {
            return false;
        }
        return idCoeficiente != null && idCoeficiente.equals(((TCCOEFICIENTE) o).idCoeficiente);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCCOEFICIENTE{" +
            "idCoeficiente=" + getIdCoeficiente() +
            ", coeficiente=" + getCoeficiente() +
            ", intervaloMin=" + getIntervaloMin() +
            ", intervaloMax=" + getIntervaloMax() +
            ", descuentoExtra=" + getDescuentoExtra() +
            "}";
    }
}
