package com.allianze.apicotizador.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TMASEGURADO.
 */
@Entity
@Table(name = "tmasegurado")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TMASEGURADO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idAsegurados;

    @NotNull
    @Column(name = "numero", nullable = false)
    private Long numero;

    @NotNull
    @Column(name = "excedente", nullable = false)
    private Long excedente;

    @NotNull
    @Column(name = "subgrupo", nullable = false)
    private Long subgrupo;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "f_nacimiento", nullable = false)
    private LocalDate fNacimiento;

    @NotNull
    @Column(name = "sueldo", nullable = false)
    private Long sueldo;

    @NotNull
    @Column(name = "regla_monto", nullable = false)
    private Long reglaMonto;

    @NotNull
    @Column(name = "edad", nullable = false)
    private Long edad;

    @NotNull
    @Column(name = "sa_total", nullable = false)
    private Long saTotal;

    @NotNull
    @Column(name = "sa_topado", nullable = false)
    private Long saTopado;

    @NotNull
    @Column(name = "basica", nullable = false)
    private Long basica;

    @NotNull
    @Column(name = "basica_covid", nullable = false)
    private Long basicaCovid;

    @NotNull
    @Column(name = "extrabas", nullable = false)
    private Long extrabas;

    @NotNull
    @Column(name = "prima_basica", nullable = false)
    private Long primaBasica;

    @NotNull
    @Column(name = "invalidez", nullable = false)
    private Long invalidez;

    @NotNull
    @Column(name = "extra_inv", nullable = false)
    private Long extraInv;

    @NotNull
    @Column(name = "exencion", nullable = false)
    private Long exencion;

    @NotNull
    @Column(name = "extra_exe", nullable = false)
    private Long extraExe;

    @NotNull
    @Column(name = "muerte_acc", nullable = false)
    private Long muerteAcc;

    @NotNull
    @Column(name = "extra_acc", nullable = false)
    private Long extraAcc;

    @NotNull
    @Column(name = "valor_gff", nullable = false)
    private Long valorGff;

    @NotNull
    @Column(name = "valor_gff_covid", nullable = false)
    private Long valorGffCovid;

    @NotNull
    @Column(name = "extra_gff", nullable = false)
    private Long extraGff;

    @NotNull
    @Column(name = "prima_gff", nullable = false)
    private Long primaGff;

    @NotNull
    @Column(name = "prima_ca", nullable = false)
    private Long primaCa;

    @NotNull
    @Column(name = "extra_ca", nullable = false)
    private Long extraCa;

    @NotNull
    @Column(name = "prima_ge", nullable = false)
    private Long primaGe;

    @NotNull
    @Column(name = "extra_ge", nullable = false)
    private Long extraGe;

    @NotNull
    @Column(name = "prima_iqs", nullable = false)
    private Long primaIqs;

    @NotNull
    @Column(name = "extra_iqa", nullable = false)
    private Long extraIqa;

    @NotNull
    @Column(name = "prima_iqv", nullable = false)
    private Long primaIqv;

    @NotNull
    @Column(name = "extra_iqv", nullable = false)
    private Long extraIqv;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdAsegurados() {
        return idAsegurados;
    }

    public void setIdAsegurados(Long idAsegurados) {
        this.idAsegurados = idAsegurados;
    }

    public TMASEGURADO idAsegurados(Long idAsegurados) {
        this.idAsegurados = idAsegurados;
        return this;
    }

    public Long getNumero() {
        return this.numero;
    }

    public TMASEGURADO numero(Long numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getExcedente() {
        return this.excedente;
    }

    public TMASEGURADO excedente(Long excedente) {
        this.excedente = excedente;
        return this;
    }

    public void setExcedente(Long excedente) {
        this.excedente = excedente;
    }

    public Long getSubgrupo() {
        return this.subgrupo;
    }

    public TMASEGURADO subgrupo(Long subgrupo) {
        this.subgrupo = subgrupo;
        return this;
    }

    public void setSubgrupo(Long subgrupo) {
        this.subgrupo = subgrupo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public TMASEGURADO nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getfNacimiento() {
        return this.fNacimiento;
    }

    public TMASEGURADO fNacimiento(LocalDate fNacimiento) {
        this.fNacimiento = fNacimiento;
        return this;
    }

    public void setfNacimiento(LocalDate fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public Long getSueldo() {
        return this.sueldo;
    }

    public TMASEGURADO sueldo(Long sueldo) {
        this.sueldo = sueldo;
        return this;
    }

    public void setSueldo(Long sueldo) {
        this.sueldo = sueldo;
    }

    public Long getReglaMonto() {
        return this.reglaMonto;
    }

    public TMASEGURADO reglaMonto(Long reglaMonto) {
        this.reglaMonto = reglaMonto;
        return this;
    }

    public void setReglaMonto(Long reglaMonto) {
        this.reglaMonto = reglaMonto;
    }

    public Long getEdad() {
        return this.edad;
    }

    public TMASEGURADO edad(Long edad) {
        this.edad = edad;
        return this;
    }

    public void setEdad(Long edad) {
        this.edad = edad;
    }

    public Long getSaTotal() {
        return this.saTotal;
    }

    public TMASEGURADO saTotal(Long saTotal) {
        this.saTotal = saTotal;
        return this;
    }

    public void setSaTotal(Long saTotal) {
        this.saTotal = saTotal;
    }

    public Long getSaTopado() {
        return this.saTopado;
    }

    public TMASEGURADO saTopado(Long saTopado) {
        this.saTopado = saTopado;
        return this;
    }

    public void setSaTopado(Long saTopado) {
        this.saTopado = saTopado;
    }

    public Long getBasica() {
        return this.basica;
    }

    public TMASEGURADO basica(Long basica) {
        this.basica = basica;
        return this;
    }

    public void setBasica(Long basica) {
        this.basica = basica;
    }

    public Long getBasicaCovid() {
        return this.basicaCovid;
    }

    public TMASEGURADO basicaCovid(Long basicaCovid) {
        this.basicaCovid = basicaCovid;
        return this;
    }

    public void setBasicaCovid(Long basicaCovid) {
        this.basicaCovid = basicaCovid;
    }

    public Long getExtrabas() {
        return this.extrabas;
    }

    public TMASEGURADO extrabas(Long extrabas) {
        this.extrabas = extrabas;
        return this;
    }

    public void setExtrabas(Long extrabas) {
        this.extrabas = extrabas;
    }

    public Long getPrimaBasica() {
        return this.primaBasica;
    }

    public TMASEGURADO primaBasica(Long primaBasica) {
        this.primaBasica = primaBasica;
        return this;
    }

    public void setPrimaBasica(Long primaBasica) {
        this.primaBasica = primaBasica;
    }

    public Long getInvalidez() {
        return this.invalidez;
    }

    public TMASEGURADO invalidez(Long invalidez) {
        this.invalidez = invalidez;
        return this;
    }

    public void setInvalidez(Long invalidez) {
        this.invalidez = invalidez;
    }

    public Long getExtraInv() {
        return this.extraInv;
    }

    public TMASEGURADO extraInv(Long extraInv) {
        this.extraInv = extraInv;
        return this;
    }

    public void setExtraInv(Long extraInv) {
        this.extraInv = extraInv;
    }

    public Long getExencion() {
        return this.exencion;
    }

    public TMASEGURADO exencion(Long exencion) {
        this.exencion = exencion;
        return this;
    }

    public void setExencion(Long exencion) {
        this.exencion = exencion;
    }

    public Long getExtraExe() {
        return this.extraExe;
    }

    public TMASEGURADO extraExe(Long extraExe) {
        this.extraExe = extraExe;
        return this;
    }

    public void setExtraExe(Long extraExe) {
        this.extraExe = extraExe;
    }

    public Long getMuerteAcc() {
        return this.muerteAcc;
    }

    public TMASEGURADO muerteAcc(Long muerteAcc) {
        this.muerteAcc = muerteAcc;
        return this;
    }

    public void setMuerteAcc(Long muerteAcc) {
        this.muerteAcc = muerteAcc;
    }

    public Long getExtraAcc() {
        return this.extraAcc;
    }

    public TMASEGURADO extraAcc(Long extraAcc) {
        this.extraAcc = extraAcc;
        return this;
    }

    public void setExtraAcc(Long extraAcc) {
        this.extraAcc = extraAcc;
    }

    public Long getValorGff() {
        return this.valorGff;
    }

    public TMASEGURADO valorGff(Long valorGff) {
        this.valorGff = valorGff;
        return this;
    }

    public void setValorGff(Long valorGff) {
        this.valorGff = valorGff;
    }

    public Long getValorGffCovid() {
        return this.valorGffCovid;
    }

    public TMASEGURADO valorGffCovid(Long valorGffCovid) {
        this.valorGffCovid = valorGffCovid;
        return this;
    }

    public void setValorGffCovid(Long valorGffCovid) {
        this.valorGffCovid = valorGffCovid;
    }

    public Long getExtraGff() {
        return this.extraGff;
    }

    public TMASEGURADO extraGff(Long extraGff) {
        this.extraGff = extraGff;
        return this;
    }

    public void setExtraGff(Long extraGff) {
        this.extraGff = extraGff;
    }

    public Long getPrimaGff() {
        return this.primaGff;
    }

    public TMASEGURADO primaGff(Long primaGff) {
        this.primaGff = primaGff;
        return this;
    }

    public void setPrimaGff(Long primaGff) {
        this.primaGff = primaGff;
    }

    public Long getPrimaCa() {
        return this.primaCa;
    }

    public TMASEGURADO primaCa(Long primaCa) {
        this.primaCa = primaCa;
        return this;
    }

    public void setPrimaCa(Long primaCa) {
        this.primaCa = primaCa;
    }

    public Long getExtraCa() {
        return this.extraCa;
    }

    public TMASEGURADO extraCa(Long extraCa) {
        this.extraCa = extraCa;
        return this;
    }

    public void setExtraCa(Long extraCa) {
        this.extraCa = extraCa;
    }

    public Long getPrimaGe() {
        return this.primaGe;
    }

    public TMASEGURADO primaGe(Long primaGe) {
        this.primaGe = primaGe;
        return this;
    }

    public void setPrimaGe(Long primaGe) {
        this.primaGe = primaGe;
    }

    public Long getExtraGe() {
        return this.extraGe;
    }

    public TMASEGURADO extraGe(Long extraGe) {
        this.extraGe = extraGe;
        return this;
    }

    public void setExtraGe(Long extraGe) {
        this.extraGe = extraGe;
    }

    public Long getPrimaIqs() {
        return this.primaIqs;
    }

    public TMASEGURADO primaIqs(Long primaIqs) {
        this.primaIqs = primaIqs;
        return this;
    }

    public void setPrimaIqs(Long primaIqs) {
        this.primaIqs = primaIqs;
    }

    public Long getExtraIqa() {
        return this.extraIqa;
    }

    public TMASEGURADO extraIqa(Long extraIqa) {
        this.extraIqa = extraIqa;
        return this;
    }

    public void setExtraIqa(Long extraIqa) {
        this.extraIqa = extraIqa;
    }

    public Long getPrimaIqv() {
        return this.primaIqv;
    }

    public TMASEGURADO primaIqv(Long primaIqv) {
        this.primaIqv = primaIqv;
        return this;
    }

    public void setPrimaIqv(Long primaIqv) {
        this.primaIqv = primaIqv;
    }

    public Long getExtraIqv() {
        return this.extraIqv;
    }

    public TMASEGURADO extraIqv(Long extraIqv) {
        this.extraIqv = extraIqv;
        return this;
    }

    public void setExtraIqv(Long extraIqv) {
        this.extraIqv = extraIqv;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TMASEGURADO)) {
            return false;
        }
        return idAsegurados != null && idAsegurados.equals(((TMASEGURADO) o).idAsegurados);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TMASEGURADO{" +
            "idAsegurados=" + getIdAsegurados() +
            ", numero=" + getNumero() +
            ", excedente=" + getExcedente() +
            ", subgrupo=" + getSubgrupo() +
            ", nombre='" + getNombre() + "'" +
            ", fNacimiento='" + getfNacimiento() + "'" +
            ", sueldo=" + getSueldo() +
            ", reglaMonto=" + getReglaMonto() +
            ", edad=" + getEdad() +
            ", saTotal=" + getSaTotal() +
            ", saTopado=" + getSaTopado() +
            ", basica=" + getBasica() +
            ", basicaCovid=" + getBasicaCovid() +
            ", extrabas=" + getExtrabas() +
            ", primaBasica=" + getPrimaBasica() +
            ", invalidez=" + getInvalidez() +
            ", extraInv=" + getExtraInv() +
            ", exencion=" + getExencion() +
            ", extraExe=" + getExtraExe() +
            ", muerteAcc=" + getMuerteAcc() +
            ", extraAcc=" + getExtraAcc() +
            ", valorGff=" + getValorGff() +
            ", valorGffCovid=" + getValorGffCovid() +
            ", extraGff=" + getExtraGff() +
            ", primaGff=" + getPrimaGff() +
            ", primaCa=" + getPrimaCa() +
            ", extraCa=" + getExtraCa() +
            ", primaGe=" + getPrimaGe() +
            ", extraGe=" + getExtraGe() +
            ", primaIqs=" + getPrimaIqs() +
            ", extraIqa=" + getExtraIqa() +
            ", primaIqv=" + getPrimaIqv() +
            ", extraIqv=" + getExtraIqv() +
            "}";
    }
}
