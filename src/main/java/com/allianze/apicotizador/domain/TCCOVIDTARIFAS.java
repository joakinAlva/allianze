package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCCOVIDTARIFAS.
 */
@Entity
@Table(name = "tccovidtarifas")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCCOVIDTARIFAS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idCovidTarifas;

    @NotNull
    @Column(name = "edad", nullable = false)
    private String edad;

    @NotNull
    @Column(name = "qxcnsfg", nullable = false)
    private Long qxcnsfg;

    @NotNull
    @Column(name = "titular", nullable = false)
    private String titular;

    @NotNull
    @Column(name = "conyuge", nullable = false)
    private String conyuge;

    @NotNull
    @Column(name = "hijo_mayor", nullable = false)
    private String hijoMayor;

    @NotNull
    @Column(name = "hijo_menor", nullable = false)
    private String hijoMenor;

    @NotNull
    @Column(name = "qx_titular", nullable = false)
    private Long qxTitular;

    @NotNull
    @Column(name = "qx_conyuge", nullable = false)
    private Long qxConyuge;

    @NotNull
    @Column(name = "qx_hijo_mayor", nullable = false)
    private Long qxHijoMayor;

    @NotNull
    @Column(name = "qx_hijo_menor", nullable = false)
    private Long qxHijoMenor;

    @NotNull
    @Column(name = "qx_titular_recargada", nullable = false)
    private Long qxTitularRecargada;

    @NotNull
    @Column(name = "qx_conyuge_recargada", nullable = false)
    private Long qxConyugeRecargada;

    @NotNull
    @Column(name = "qx_hijo_mayor_recargada", nullable = false)
    private Long qxHijoMayorRecargada;

    @NotNull
    @Column(name = "qx_hijo_menor_recargada", nullable = false)
    private Long qxHijoMenorRecargada;

    @NotNull
    @Column(name = "valor_gff", nullable = false)
    private Long valorGff;

    @NotNull
    @Column(name = "valor_gff_covid", nullable = false)
    private Long valorGffCovid;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdCovidTarifas() {
        return idCovidTarifas;
    }

    public void setIdCovidTarifas(Long idCovidTarifas) {
        this.idCovidTarifas = idCovidTarifas;
    }

    public TCCOVIDTARIFAS idCovidTarifas(Long idCovidTarifas) {
        this.idCovidTarifas = idCovidTarifas;
        return this;
    }

    public String getEdad() {
        return this.edad;
    }

    public TCCOVIDTARIFAS edad(String edad) {
        this.edad = edad;
        return this;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Long getQxcnsfg() {
        return this.qxcnsfg;
    }

    public TCCOVIDTARIFAS qxcnsfg(Long qxcnsfg) {
        this.qxcnsfg = qxcnsfg;
        return this;
    }

    public void setQxcnsfg(Long qxcnsfg) {
        this.qxcnsfg = qxcnsfg;
    }

    public String getTitular() {
        return this.titular;
    }

    public TCCOVIDTARIFAS titular(String titular) {
        this.titular = titular;
        return this;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getConyuge() {
        return this.conyuge;
    }

    public TCCOVIDTARIFAS conyuge(String conyuge) {
        this.conyuge = conyuge;
        return this;
    }

    public void setConyuge(String conyuge) {
        this.conyuge = conyuge;
    }

    public String getHijoMayor() {
        return this.hijoMayor;
    }

    public TCCOVIDTARIFAS hijoMayor(String hijoMayor) {
        this.hijoMayor = hijoMayor;
        return this;
    }

    public void setHijoMayor(String hijoMayor) {
        this.hijoMayor = hijoMayor;
    }

    public String getHijoMenor() {
        return this.hijoMenor;
    }

    public TCCOVIDTARIFAS hijoMenor(String hijoMenor) {
        this.hijoMenor = hijoMenor;
        return this;
    }

    public void setHijoMenor(String hijoMenor) {
        this.hijoMenor = hijoMenor;
    }

    public Long getQxTitular() {
        return this.qxTitular;
    }

    public TCCOVIDTARIFAS qxTitular(Long qxTitular) {
        this.qxTitular = qxTitular;
        return this;
    }

    public void setQxTitular(Long qxTitular) {
        this.qxTitular = qxTitular;
    }

    public Long getQxConyuge() {
        return this.qxConyuge;
    }

    public TCCOVIDTARIFAS qxConyuge(Long qxConyuge) {
        this.qxConyuge = qxConyuge;
        return this;
    }

    public void setQxConyuge(Long qxConyuge) {
        this.qxConyuge = qxConyuge;
    }

    public Long getQxHijoMayor() {
        return this.qxHijoMayor;
    }

    public TCCOVIDTARIFAS qxHijoMayor(Long qxHijoMayor) {
        this.qxHijoMayor = qxHijoMayor;
        return this;
    }

    public void setQxHijoMayor(Long qxHijoMayor) {
        this.qxHijoMayor = qxHijoMayor;
    }

    public Long getQxHijoMenor() {
        return this.qxHijoMenor;
    }

    public TCCOVIDTARIFAS qxHijoMenor(Long qxHijoMenor) {
        this.qxHijoMenor = qxHijoMenor;
        return this;
    }

    public void setQxHijoMenor(Long qxHijoMenor) {
        this.qxHijoMenor = qxHijoMenor;
    }

    public Long getQxTitularRecargada() {
        return this.qxTitularRecargada;
    }

    public TCCOVIDTARIFAS qxTitularRecargada(Long qxTitularRecargada) {
        this.qxTitularRecargada = qxTitularRecargada;
        return this;
    }

    public void setQxTitularRecargada(Long qxTitularRecargada) {
        this.qxTitularRecargada = qxTitularRecargada;
    }

    public Long getQxConyugeRecargada() {
        return this.qxConyugeRecargada;
    }

    public TCCOVIDTARIFAS qxConyugeRecargada(Long qxConyugeRecargada) {
        this.qxConyugeRecargada = qxConyugeRecargada;
        return this;
    }

    public void setQxConyugeRecargada(Long qxConyugeRecargada) {
        this.qxConyugeRecargada = qxConyugeRecargada;
    }

    public Long getQxHijoMayorRecargada() {
        return this.qxHijoMayorRecargada;
    }

    public TCCOVIDTARIFAS qxHijoMayorRecargada(Long qxHijoMayorRecargada) {
        this.qxHijoMayorRecargada = qxHijoMayorRecargada;
        return this;
    }

    public void setQxHijoMayorRecargada(Long qxHijoMayorRecargada) {
        this.qxHijoMayorRecargada = qxHijoMayorRecargada;
    }

    public Long getQxHijoMenorRecargada() {
        return this.qxHijoMenorRecargada;
    }

    public TCCOVIDTARIFAS qxHijoMenorRecargada(Long qxHijoMenorRecargada) {
        this.qxHijoMenorRecargada = qxHijoMenorRecargada;
        return this;
    }

    public void setQxHijoMenorRecargada(Long qxHijoMenorRecargada) {
        this.qxHijoMenorRecargada = qxHijoMenorRecargada;
    }

    public Long getValorGff() {
        return this.valorGff;
    }

    public TCCOVIDTARIFAS valorGff(Long valorGff) {
        this.valorGff = valorGff;
        return this;
    }

    public void setValorGff(Long valorGff) {
        this.valorGff = valorGff;
    }

    public Long getValorGffCovid() {
        return this.valorGffCovid;
    }

    public TCCOVIDTARIFAS valorGffCovid(Long valorGffCovid) {
        this.valorGffCovid = valorGffCovid;
        return this;
    }

    public void setValorGffCovid(Long valorGffCovid) {
        this.valorGffCovid = valorGffCovid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCCOVIDTARIFAS)) {
            return false;
        }
        return idCovidTarifas != null && idCovidTarifas.equals(((TCCOVIDTARIFAS) o).idCovidTarifas);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCCOVIDTARIFAS{" +
            "idCovidTarifas=" + getIdCovidTarifas() +
            ", edad='" + getEdad() + "'" +
            ", qxcnsfg=" + getQxcnsfg() +
            ", titular='" + getTitular() + "'" +
            ", conyuge='" + getConyuge() + "'" +
            ", hijoMayor='" + getHijoMayor() + "'" +
            ", hijoMenor='" + getHijoMenor() + "'" +
            ", qxTitular=" + getQxTitular() +
            ", qxConyuge=" + getQxConyuge() +
            ", qxHijoMayor=" + getQxHijoMayor() +
            ", qxHijoMenor=" + getQxHijoMenor() +
            ", qxTitularRecargada=" + getQxTitularRecargada() +
            ", qxConyugeRecargada=" + getQxConyugeRecargada() +
            ", qxHijoMayorRecargada=" + getQxHijoMayorRecargada() +
            ", qxHijoMenorRecargada=" + getQxHijoMenorRecargada() +
            ", valorGff=" + getValorGff() +
            ", valorGffCovid=" + getValorGffCovid() +
            "}";
    }
}
