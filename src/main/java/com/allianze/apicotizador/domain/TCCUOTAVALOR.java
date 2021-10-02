package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCCUOTAVALOR.
 */
@Entity
@Table(name = "tccuotavalor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCCUOTAVALOR implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idCuotaValor;

    @NotNull
    @Column(name = "porcentaje", nullable = false)
    private Long porcentaje;

    @NotNull
    @Column(name = "valor", nullable = false)
    private String valor;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdCuotaValor() {
        return idCuotaValor;
    }

    public void setIdCuotaValor(Long idCuotaValor) {
        this.idCuotaValor = idCuotaValor;
    }

    public TCCUOTAVALOR idCuotaValor(Long idCuotaValor) {
        this.idCuotaValor = idCuotaValor;
        return this;
    }

    public Long getPorcentaje() {
        return this.porcentaje;
    }

    public TCCUOTAVALOR porcentaje(Long porcentaje) {
        this.porcentaje = porcentaje;
        return this;
    }

    public void setPorcentaje(Long porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getValor() {
        return this.valor;
    }

    public TCCUOTAVALOR valor(String valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCCUOTAVALOR)) {
            return false;
        }
        return idCuotaValor != null && idCuotaValor.equals(((TCCUOTAVALOR) o).idCuotaValor);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCCUOTAVALOR{" +
            "idCuotaValor=" + getIdCuotaValor() +
            ", porcentaje=" + getPorcentaje() +
            ", valor='" + getValor() + "'" +
            "}";
    }
}
