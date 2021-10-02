package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCCUOTATARIFASDESC.
 */
@Entity
@Table(name = "tccuotatarifasdesc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCCUOTATARIFASDESC implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idCuotaTarifaSdesc;

    @NotNull
    @Column(name = "edad", nullable = false)
    private String edad;

    @NotNull
    @Column(name = "valor_ba", nullable = false)
    private Long valorBa;

    @NotNull
    @Column(name = "valor_ba_covid", nullable = false)
    private Long valorBaCovid;

    @NotNull
    @Column(name = "valor_bip_tres", nullable = false)
    private Long valorBipTres;

    @NotNull
    @Column(name = "valor_bip_seis", nullable = false)
    private Long valorBipSeis;

    @NotNull
    @Column(name = "valor_bit", nullable = false)
    private Long valorBit;

    @NotNull
    @Column(name = "valor_ma", nullable = false)
    private Long valorMa;

    @NotNull
    @Column(name = "valor_di", nullable = false)
    private Long valorDi;

    @NotNull
    @Column(name = "valor_ti", nullable = false)
    private Long valorTi;

    @NotNull
    @Column(name = "valor_gff", nullable = false)
    private Long valorGff;

    @NotNull
    @Column(name = "valor_gff_covid", nullable = false)
    private Long valorGffCovid;

    @NotNull
    @Column(name = "valor_ca", nullable = false)
    private Long valorCa;

    @NotNull
    @Column(name = "valor_ge", nullable = false)
    private Long valorGe;

    @NotNull
    @Column(name = "valor_iq_uno", nullable = false)
    private Long valorIqUno;

    @NotNull
    @Column(name = "valor_iq_dos", nullable = false)
    private Long valorIqDos;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdCuotaTarifaSdesc() {
        return idCuotaTarifaSdesc;
    }

    public void setIdCuotaTarifaSdesc(Long idCuotaTarifaSdesc) {
        this.idCuotaTarifaSdesc = idCuotaTarifaSdesc;
    }

    public TCCUOTATARIFASDESC idCuotaTarifaSdesc(Long idCuotaTarifaSdesc) {
        this.idCuotaTarifaSdesc = idCuotaTarifaSdesc;
        return this;
    }

    public String getEdad() {
        return this.edad;
    }

    public TCCUOTATARIFASDESC edad(String edad) {
        this.edad = edad;
        return this;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Long getValorBa() {
        return this.valorBa;
    }

    public TCCUOTATARIFASDESC valorBa(Long valorBa) {
        this.valorBa = valorBa;
        return this;
    }

    public void setValorBa(Long valorBa) {
        this.valorBa = valorBa;
    }

    public Long getValorBaCovid() {
        return this.valorBaCovid;
    }

    public TCCUOTATARIFASDESC valorBaCovid(Long valorBaCovid) {
        this.valorBaCovid = valorBaCovid;
        return this;
    }

    public void setValorBaCovid(Long valorBaCovid) {
        this.valorBaCovid = valorBaCovid;
    }

    public Long getValorBipTres() {
        return this.valorBipTres;
    }

    public TCCUOTATARIFASDESC valorBipTres(Long valorBipTres) {
        this.valorBipTres = valorBipTres;
        return this;
    }

    public void setValorBipTres(Long valorBipTres) {
        this.valorBipTres = valorBipTres;
    }

    public Long getValorBipSeis() {
        return this.valorBipSeis;
    }

    public TCCUOTATARIFASDESC valorBipSeis(Long valorBipSeis) {
        this.valorBipSeis = valorBipSeis;
        return this;
    }

    public void setValorBipSeis(Long valorBipSeis) {
        this.valorBipSeis = valorBipSeis;
    }

    public Long getValorBit() {
        return this.valorBit;
    }

    public TCCUOTATARIFASDESC valorBit(Long valorBit) {
        this.valorBit = valorBit;
        return this;
    }

    public void setValorBit(Long valorBit) {
        this.valorBit = valorBit;
    }

    public Long getValorMa() {
        return this.valorMa;
    }

    public TCCUOTATARIFASDESC valorMa(Long valorMa) {
        this.valorMa = valorMa;
        return this;
    }

    public void setValorMa(Long valorMa) {
        this.valorMa = valorMa;
    }

    public Long getValorDi() {
        return this.valorDi;
    }

    public TCCUOTATARIFASDESC valorDi(Long valorDi) {
        this.valorDi = valorDi;
        return this;
    }

    public void setValorDi(Long valorDi) {
        this.valorDi = valorDi;
    }

    public Long getValorTi() {
        return this.valorTi;
    }

    public TCCUOTATARIFASDESC valorTi(Long valorTi) {
        this.valorTi = valorTi;
        return this;
    }

    public void setValorTi(Long valorTi) {
        this.valorTi = valorTi;
    }

    public Long getValorGff() {
        return this.valorGff;
    }

    public TCCUOTATARIFASDESC valorGff(Long valorGff) {
        this.valorGff = valorGff;
        return this;
    }

    public void setValorGff(Long valorGff) {
        this.valorGff = valorGff;
    }

    public Long getValorGffCovid() {
        return this.valorGffCovid;
    }

    public TCCUOTATARIFASDESC valorGffCovid(Long valorGffCovid) {
        this.valorGffCovid = valorGffCovid;
        return this;
    }

    public void setValorGffCovid(Long valorGffCovid) {
        this.valorGffCovid = valorGffCovid;
    }

    public Long getValorCa() {
        return this.valorCa;
    }

    public TCCUOTATARIFASDESC valorCa(Long valorCa) {
        this.valorCa = valorCa;
        return this;
    }

    public void setValorCa(Long valorCa) {
        this.valorCa = valorCa;
    }

    public Long getValorGe() {
        return this.valorGe;
    }

    public TCCUOTATARIFASDESC valorGe(Long valorGe) {
        this.valorGe = valorGe;
        return this;
    }

    public void setValorGe(Long valorGe) {
        this.valorGe = valorGe;
    }

    public Long getValorIqUno() {
        return this.valorIqUno;
    }

    public TCCUOTATARIFASDESC valorIqUno(Long valorIqUno) {
        this.valorIqUno = valorIqUno;
        return this;
    }

    public void setValorIqUno(Long valorIqUno) {
        this.valorIqUno = valorIqUno;
    }

    public Long getValorIqDos() {
        return this.valorIqDos;
    }

    public TCCUOTATARIFASDESC valorIqDos(Long valorIqDos) {
        this.valorIqDos = valorIqDos;
        return this;
    }

    public void setValorIqDos(Long valorIqDos) {
        this.valorIqDos = valorIqDos;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCCUOTATARIFASDESC)) {
            return false;
        }
        return idCuotaTarifaSdesc != null && idCuotaTarifaSdesc.equals(((TCCUOTATARIFASDESC) o).idCuotaTarifaSdesc);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCCUOTATARIFASDESC{" +
            "idCuotaTarifaSdesc=" + getIdCuotaTarifaSdesc() +
            ", edad='" + getEdad() + "'" +
            ", valorBa=" + getValorBa() +
            ", valorBaCovid=" + getValorBaCovid() +
            ", valorBipTres=" + getValorBipTres() +
            ", valorBipSeis=" + getValorBipSeis() +
            ", valorBit=" + getValorBit() +
            ", valorMa=" + getValorMa() +
            ", valorDi=" + getValorDi() +
            ", valorTi=" + getValorTi() +
            ", valorGff=" + getValorGff() +
            ", valorGffCovid=" + getValorGffCovid() +
            ", valorCa=" + getValorCa() +
            ", valorGe=" + getValorGe() +
            ", valorIqUno=" + getValorIqUno() +
            ", valorIqDos=" + getValorIqDos() +
            "}";
    }
}
