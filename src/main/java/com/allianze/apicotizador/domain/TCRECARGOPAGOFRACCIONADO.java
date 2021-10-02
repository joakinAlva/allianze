package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCRECARGOPAGOFRACCIONADO.
 */
@Entity
@Table(name = "tcrecargopagofraccionado")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCRECARGOPAGOFRACCIONADO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idRecargoPagoFraccionado;

    @NotNull
    @Column(name = "periodo", nullable = false)
    private String periodo;

    @NotNull
    @Column(name = "porcentaje", nullable = false)
    private Long porcentaje;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdRecargoPagoFraccionado() {
        return idRecargoPagoFraccionado;
    }

    public void setIdRecargoPagoFraccionado(Long idRecargoPagoFraccionado) {
        this.idRecargoPagoFraccionado = idRecargoPagoFraccionado;
    }

    public TCRECARGOPAGOFRACCIONADO idRecargoPagoFraccionado(Long idRecargoPagoFraccionado) {
        this.idRecargoPagoFraccionado = idRecargoPagoFraccionado;
        return this;
    }

    public String getPeriodo() {
        return this.periodo;
    }

    public TCRECARGOPAGOFRACCIONADO periodo(String periodo) {
        this.periodo = periodo;
        return this;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Long getPorcentaje() {
        return this.porcentaje;
    }

    public TCRECARGOPAGOFRACCIONADO porcentaje(Long porcentaje) {
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
        if (!(o instanceof TCRECARGOPAGOFRACCIONADO)) {
            return false;
        }
        return idRecargoPagoFraccionado != null && idRecargoPagoFraccionado.equals(((TCRECARGOPAGOFRACCIONADO) o).idRecargoPagoFraccionado);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCRECARGOPAGOFRACCIONADO{" +
            "idRecargoPagoFraccionado=" + getIdRecargoPagoFraccionado() +
            ", periodo='" + getPeriodo() + "'" +
            ", porcentaje=" + getPorcentaje() +
            "}";
    }
}
