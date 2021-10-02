package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCCOBERTURA.
 */
@Entity
@Table(name = "tccobertura")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCCOBERTURA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idCobertura;

    @NotNull
    @Column(name = "cobertura", nullable = false)
    private String cobertura;

    @NotNull
    @Column(name = "posicion", nullable = false)
    private Long posicion;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdCobertura() {
        return idCobertura;
    }

    public void setIdCobertura(Long idCobertura) {
        this.idCobertura = idCobertura;
    }

    public TCCOBERTURA idCobertura(Long idCobertura) {
        this.idCobertura = idCobertura;
        return this;
    }

    public String getCobertura() {
        return this.cobertura;
    }

    public TCCOBERTURA cobertura(String cobertura) {
        this.cobertura = cobertura;
        return this;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public Long getPosicion() {
        return this.posicion;
    }

    public TCCOBERTURA posicion(Long posicion) {
        this.posicion = posicion;
        return this;
    }

    public void setPosicion(Long posicion) {
        this.posicion = posicion;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCCOBERTURA)) {
            return false;
        }
        return idCobertura != null && idCobertura.equals(((TCCOBERTURA) o).idCobertura);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCCOBERTURA{" +
            "idCobertura=" + getIdCobertura() +
            ", cobertura='" + getCobertura() + "'" +
            ", posicion=" + getPosicion() +
            "}";
    }
}
