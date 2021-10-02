package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCREFENCIA.
 */
@Entity
@Table(name = "tcrefencia")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCREFENCIA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idReferencia;

    @NotNull
    @Column(name = "periodo", nullable = false)
    private String periodo;

    @NotNull
    @Column(name = "referencia", nullable = false)
    private String referencia;

    @NotNull
    @Column(name = "edadpromedio", nullable = false)
    private String edadpromedio;

    @NotNull
    @Column(name = "cuota", nullable = false)
    private String cuota;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(Long idReferencia) {
        this.idReferencia = idReferencia;
    }

    public TCREFENCIA idReferencia(Long idReferencia) {
        this.idReferencia = idReferencia;
        return this;
    }

    public String getPeriodo() {
        return this.periodo;
    }

    public TCREFENCIA periodo(String periodo) {
        this.periodo = periodo;
        return this;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getReferencia() {
        return this.referencia;
    }

    public TCREFENCIA referencia(String referencia) {
        this.referencia = referencia;
        return this;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getEdadpromedio() {
        return this.edadpromedio;
    }

    public TCREFENCIA edadpromedio(String edadpromedio) {
        this.edadpromedio = edadpromedio;
        return this;
    }

    public void setEdadpromedio(String edadpromedio) {
        this.edadpromedio = edadpromedio;
    }

    public String getCuota() {
        return this.cuota;
    }

    public TCREFENCIA cuota(String cuota) {
        this.cuota = cuota;
        return this;
    }

    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCREFENCIA)) {
            return false;
        }
        return idReferencia != null && idReferencia.equals(((TCREFENCIA) o).idReferencia);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCREFENCIA{" +
            "idReferencia=" + getIdReferencia() +
            ", periodo='" + getPeriodo() + "'" +
            ", referencia='" + getReferencia() + "'" +
            ", edadpromedio='" + getEdadpromedio() + "'" +
            ", cuota='" + getCuota() + "'" +
            "}";
    }
}
