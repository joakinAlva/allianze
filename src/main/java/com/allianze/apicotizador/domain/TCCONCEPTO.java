package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCCONCEPTO.
 */
@Entity
@Table(name = "tcconcepto")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCCONCEPTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idConcepto;

    @NotNull
    @Column(name = "concepto", nullable = false)
    private String concepto;

    @NotNull
    @Column(name = "dato", nullable = false)
    private Long dato;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Long idConcepto) {
        this.idConcepto = idConcepto;
    }

    public TCCONCEPTO idConcepto(Long idConcepto) {
        this.idConcepto = idConcepto;
        return this;
    }

    public String getConcepto() {
        return this.concepto;
    }

    public TCCONCEPTO concepto(String concepto) {
        this.concepto = concepto;
        return this;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Long getDato() {
        return this.dato;
    }

    public TCCONCEPTO dato(Long dato) {
        this.dato = dato;
        return this;
    }

    public void setDato(Long dato) {
        this.dato = dato;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCCONCEPTO)) {
            return false;
        }
        return idConcepto != null && idConcepto.equals(((TCCONCEPTO) o).idConcepto);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCCONCEPTO{" +
            "idConcepto=" + getIdConcepto() +
            ", concepto='" + getConcepto() + "'" +
            ", dato=" + getDato() +
            "}";
    }
}
