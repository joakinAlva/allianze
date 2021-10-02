package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCRANGOPRIMA.
 */
@Entity
@Table(name = "tcrangoprima")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCRANGOPRIMA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idRangoPrima;

    @NotNull
    @Column(name = "valor_min", nullable = false)
    private Long valorMin;

    @NotNull
    @Column(name = "valor_max", nullable = false)
    private Long valorMax;

    @NotNull
    @Column(name = "dividendos", nullable = false)
    private Long dividendos;

    @NotNull
    @Column(name = "comision", nullable = false)
    private Long comision;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdRangoPrima() {
        return idRangoPrima;
    }

    public void setIdRangoPrima(Long idRangoPrima) {
        this.idRangoPrima = idRangoPrima;
    }

    public TCRANGOPRIMA idRangoPrima(Long idRangoPrima) {
        this.idRangoPrima = idRangoPrima;
        return this;
    }

    public Long getValorMin() {
        return this.valorMin;
    }

    public TCRANGOPRIMA valorMin(Long valorMin) {
        this.valorMin = valorMin;
        return this;
    }

    public void setValorMin(Long valorMin) {
        this.valorMin = valorMin;
    }

    public Long getValorMax() {
        return this.valorMax;
    }

    public TCRANGOPRIMA valorMax(Long valorMax) {
        this.valorMax = valorMax;
        return this;
    }

    public void setValorMax(Long valorMax) {
        this.valorMax = valorMax;
    }

    public Long getDividendos() {
        return this.dividendos;
    }

    public TCRANGOPRIMA dividendos(Long dividendos) {
        this.dividendos = dividendos;
        return this;
    }

    public void setDividendos(Long dividendos) {
        this.dividendos = dividendos;
    }

    public Long getComision() {
        return this.comision;
    }

    public TCRANGOPRIMA comision(Long comision) {
        this.comision = comision;
        return this;
    }

    public void setComision(Long comision) {
        this.comision = comision;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCRANGOPRIMA)) {
            return false;
        }
        return idRangoPrima != null && idRangoPrima.equals(((TCRANGOPRIMA) o).idRangoPrima);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCRANGOPRIMA{" +
            "idRangoPrima=" + getIdRangoPrima() +
            ", valorMin=" + getValorMin() +
            ", valorMax=" + getValorMax() +
            ", dividendos=" + getDividendos() +
            ", comision=" + getComision() +
            "}";
    }
}
