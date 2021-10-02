package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCPRIMATARIFA.
 */
@Entity
@Table(name = "tcprimatarifa")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCPRIMATARIFA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idPrimaTarifa;

    @NotNull
    @Column(name = "div_prima_tarifa", nullable = false)
    private Long divPrimaTarifa;

    @NotNull
    @Column(name = "z_neta", nullable = false)
    private Long zNeta;

    @NotNull
    @Column(name = "div_prima_riesgo", nullable = false)
    private Long divPrimaRiesgo;

    @NotNull
    @Column(name = "z_riesgo", nullable = false)
    private Long zRiesgo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdPrimaTarifa() {
        return idPrimaTarifa;
    }

    public void setIdPrimaTarifa(Long idPrimaTarifa) {
        this.idPrimaTarifa = idPrimaTarifa;
    }

    public TCPRIMATARIFA idPrimaTarifa(Long idPrimaTarifa) {
        this.idPrimaTarifa = idPrimaTarifa;
        return this;
    }

    public Long getDivPrimaTarifa() {
        return this.divPrimaTarifa;
    }

    public TCPRIMATARIFA divPrimaTarifa(Long divPrimaTarifa) {
        this.divPrimaTarifa = divPrimaTarifa;
        return this;
    }

    public void setDivPrimaTarifa(Long divPrimaTarifa) {
        this.divPrimaTarifa = divPrimaTarifa;
    }

    public Long getzNeta() {
        return this.zNeta;
    }

    public TCPRIMATARIFA zNeta(Long zNeta) {
        this.zNeta = zNeta;
        return this;
    }

    public void setzNeta(Long zNeta) {
        this.zNeta = zNeta;
    }

    public Long getDivPrimaRiesgo() {
        return this.divPrimaRiesgo;
    }

    public TCPRIMATARIFA divPrimaRiesgo(Long divPrimaRiesgo) {
        this.divPrimaRiesgo = divPrimaRiesgo;
        return this;
    }

    public void setDivPrimaRiesgo(Long divPrimaRiesgo) {
        this.divPrimaRiesgo = divPrimaRiesgo;
    }

    public Long getzRiesgo() {
        return this.zRiesgo;
    }

    public TCPRIMATARIFA zRiesgo(Long zRiesgo) {
        this.zRiesgo = zRiesgo;
        return this;
    }

    public void setzRiesgo(Long zRiesgo) {
        this.zRiesgo = zRiesgo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCPRIMATARIFA)) {
            return false;
        }
        return idPrimaTarifa != null && idPrimaTarifa.equals(((TCPRIMATARIFA) o).idPrimaTarifa);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCPRIMATARIFA{" +
            "idPrimaTarifa=" + getIdPrimaTarifa() +
            ", divPrimaTarifa=" + getDivPrimaTarifa() +
            ", zNeta=" + getzNeta() +
            ", divPrimaRiesgo=" + getDivPrimaRiesgo() +
            ", zRiesgo=" + getzRiesgo() +
            "}";
    }
}
