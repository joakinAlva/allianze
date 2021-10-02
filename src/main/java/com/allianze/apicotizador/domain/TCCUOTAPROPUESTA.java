package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCCUOTAPROPUESTA.
 */
@Entity
@Table(name = "tccuotapropuesta")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCCUOTAPROPUESTA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idCuotaPropuesta;

    @NotNull
    @Column(name = "edad", nullable = false)
    private String edad;

    @NotNull
    @Column(name = "valor_vg", nullable = false)
    private Long valorVg;

    @NotNull
    @Column(name = "valor_bip_tres", nullable = false)
    private Long valorBipTres;

    @NotNull
    @Column(name = "valor_bit", nullable = false)
    private Long valorBit;

    @NotNull
    @Column(name = "valor_di", nullable = false)
    private Long valorDi;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdCuotaPropuesta() {
        return idCuotaPropuesta;
    }

    public void setIdCuotaPropuesta(Long idCuotaPropuesta) {
        this.idCuotaPropuesta = idCuotaPropuesta;
    }

    public TCCUOTAPROPUESTA idCuotaPropuesta(Long idCuotaPropuesta) {
        this.idCuotaPropuesta = idCuotaPropuesta;
        return this;
    }

    public String getEdad() {
        return this.edad;
    }

    public TCCUOTAPROPUESTA edad(String edad) {
        this.edad = edad;
        return this;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Long getValorVg() {
        return this.valorVg;
    }

    public TCCUOTAPROPUESTA valorVg(Long valorVg) {
        this.valorVg = valorVg;
        return this;
    }

    public void setValorVg(Long valorVg) {
        this.valorVg = valorVg;
    }

    public Long getValorBipTres() {
        return this.valorBipTres;
    }

    public TCCUOTAPROPUESTA valorBipTres(Long valorBipTres) {
        this.valorBipTres = valorBipTres;
        return this;
    }

    public void setValorBipTres(Long valorBipTres) {
        this.valorBipTres = valorBipTres;
    }

    public Long getValorBit() {
        return this.valorBit;
    }

    public TCCUOTAPROPUESTA valorBit(Long valorBit) {
        this.valorBit = valorBit;
        return this;
    }

    public void setValorBit(Long valorBit) {
        this.valorBit = valorBit;
    }

    public Long getValorDi() {
        return this.valorDi;
    }

    public TCCUOTAPROPUESTA valorDi(Long valorDi) {
        this.valorDi = valorDi;
        return this;
    }

    public void setValorDi(Long valorDi) {
        this.valorDi = valorDi;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCCUOTAPROPUESTA)) {
            return false;
        }
        return idCuotaPropuesta != null && idCuotaPropuesta.equals(((TCCUOTAPROPUESTA) o).idCuotaPropuesta);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCCUOTAPROPUESTA{" +
            "idCuotaPropuesta=" + getIdCuotaPropuesta() +
            ", edad='" + getEdad() + "'" +
            ", valorVg=" + getValorVg() +
            ", valorBipTres=" + getValorBipTres() +
            ", valorBit=" + getValorBit() +
            ", valorDi=" + getValorDi() +
            "}";
    }
}
