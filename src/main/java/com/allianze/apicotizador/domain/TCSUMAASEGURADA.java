package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCSUMAASEGURADA.
 */
@Entity
@Table(name = "tcsumaasegurada")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCSUMAASEGURADA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idSumaAsegurada;

    @NotNull
    @Column(name = "sagff", nullable = false)
    private Long sagff;

    @NotNull
    @Column(name = "saca", nullable = false)
    private Long saca;

    @NotNull
    @Column(name = "sage", nullable = false)
    private Long sage;

    @NotNull
    @Column(name = "saiqa", nullable = false)
    private Long saiqa;

    @NotNull
    @Column(name = "saiqv", nullable = false)
    private Long saiqv;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdSumaAsegurada() {
        return idSumaAsegurada;
    }

    public void setIdSumaAsegurada(Long idSumaAsegurada) {
        this.idSumaAsegurada = idSumaAsegurada;
    }

    public TCSUMAASEGURADA idSumaAsegurada(Long idSumaAsegurada) {
        this.idSumaAsegurada = idSumaAsegurada;
        return this;
    }

    public Long getSagff() {
        return this.sagff;
    }

    public TCSUMAASEGURADA sagff(Long sagff) {
        this.sagff = sagff;
        return this;
    }

    public void setSagff(Long sagff) {
        this.sagff = sagff;
    }

    public Long getSaca() {
        return this.saca;
    }

    public TCSUMAASEGURADA saca(Long saca) {
        this.saca = saca;
        return this;
    }

    public void setSaca(Long saca) {
        this.saca = saca;
    }

    public Long getSage() {
        return this.sage;
    }

    public TCSUMAASEGURADA sage(Long sage) {
        this.sage = sage;
        return this;
    }

    public void setSage(Long sage) {
        this.sage = sage;
    }

    public Long getSaiqa() {
        return this.saiqa;
    }

    public TCSUMAASEGURADA saiqa(Long saiqa) {
        this.saiqa = saiqa;
        return this;
    }

    public void setSaiqa(Long saiqa) {
        this.saiqa = saiqa;
    }

    public Long getSaiqv() {
        return this.saiqv;
    }

    public TCSUMAASEGURADA saiqv(Long saiqv) {
        this.saiqv = saiqv;
        return this;
    }

    public void setSaiqv(Long saiqv) {
        this.saiqv = saiqv;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCSUMAASEGURADA)) {
            return false;
        }
        return idSumaAsegurada != null && idSumaAsegurada.equals(((TCSUMAASEGURADA) o).idSumaAsegurada);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCSUMAASEGURADA{" +
            "idSumaAsegurada=" + getIdSumaAsegurada() +
            ", sagff=" + getSagff() +
            ", saca=" + getSaca() +
            ", sage=" + getSage() +
            ", saiqa=" + getSaiqa() +
            ", saiqv=" + getSaiqv() +
            "}";
    }
}
