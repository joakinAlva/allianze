package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCPRIMANETASDESC.
 */
@Entity
@Table(name = "tcprimanetasdesc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCPRIMANETASDESC implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idPrimaNetaSdesc;

    @NotNull
    @Column(name = "prima_neta_sdesc", nullable = false)
    private String primaNetaSdesc;

    @NotNull
    @Column(name = "margen_min", nullable = false)
    private Long margenMin;

    @NotNull
    @Column(name = "margen_max", nullable = false)
    private Long margenMax;

    @NotNull
    @Column(name = "max_com_sd", nullable = false)
    private Long maxComSd;

    @NotNull
    @Column(name = "max_com_ep", nullable = false)
    private Long maxComEp;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdPrimaNetaSdesc() {
        return idPrimaNetaSdesc;
    }

    public void setIdPrimaNetaSdesc(Long idPrimaNetaSdesc) {
        this.idPrimaNetaSdesc = idPrimaNetaSdesc;
    }

    public TCPRIMANETASDESC idPrimaNetaSdesc(Long idPrimaNetaSdesc) {
        this.idPrimaNetaSdesc = idPrimaNetaSdesc;
        return this;
    }

    public String getPrimaNetaSdesc() {
        return this.primaNetaSdesc;
    }

    public TCPRIMANETASDESC primaNetaSdesc(String primaNetaSdesc) {
        this.primaNetaSdesc = primaNetaSdesc;
        return this;
    }

    public void setPrimaNetaSdesc(String primaNetaSdesc) {
        this.primaNetaSdesc = primaNetaSdesc;
    }

    public Long getMargenMin() {
        return this.margenMin;
    }

    public TCPRIMANETASDESC margenMin(Long margenMin) {
        this.margenMin = margenMin;
        return this;
    }

    public void setMargenMin(Long margenMin) {
        this.margenMin = margenMin;
    }

    public Long getMargenMax() {
        return this.margenMax;
    }

    public TCPRIMANETASDESC margenMax(Long margenMax) {
        this.margenMax = margenMax;
        return this;
    }

    public void setMargenMax(Long margenMax) {
        this.margenMax = margenMax;
    }

    public Long getMaxComSd() {
        return this.maxComSd;
    }

    public TCPRIMANETASDESC maxComSd(Long maxComSd) {
        this.maxComSd = maxComSd;
        return this;
    }

    public void setMaxComSd(Long maxComSd) {
        this.maxComSd = maxComSd;
    }

    public Long getMaxComEp() {
        return this.maxComEp;
    }

    public TCPRIMANETASDESC maxComEp(Long maxComEp) {
        this.maxComEp = maxComEp;
        return this;
    }

    public void setMaxComEp(Long maxComEp) {
        this.maxComEp = maxComEp;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCPRIMANETASDESC)) {
            return false;
        }
        return idPrimaNetaSdesc != null && idPrimaNetaSdesc.equals(((TCPRIMANETASDESC) o).idPrimaNetaSdesc);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCPRIMANETASDESC{" +
            "idPrimaNetaSdesc=" + getIdPrimaNetaSdesc() +
            ", primaNetaSdesc='" + getPrimaNetaSdesc() + "'" +
            ", margenMin=" + getMargenMin() +
            ", margenMax=" + getMargenMax() +
            ", maxComSd=" + getMaxComSd() +
            ", maxComEp=" + getMaxComEp() +
            "}";
    }
}
