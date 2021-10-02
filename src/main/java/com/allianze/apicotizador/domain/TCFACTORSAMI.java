package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCFACTORSAMI.
 */
@Entity
@Table(name = "tcfactorsami")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCFACTORSAMI implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idFactorSami;

    @NotNull
    @Column(name = "min_asegurados", nullable = false)
    private String minAsegurados;

    @NotNull
    @Column(name = "max_asegurados", nullable = false)
    private String maxAsegurados;

    @NotNull
    @Column(name = "factor", nullable = false)
    private String factor;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdFactorSami() {
        return idFactorSami;
    }

    public void setIdFactorSami(Long idFactorSami) {
        this.idFactorSami = idFactorSami;
    }

    public TCFACTORSAMI idFactorSami(Long idFactorSami) {
        this.idFactorSami = idFactorSami;
        return this;
    }

    public String getMinAsegurados() {
        return this.minAsegurados;
    }

    public TCFACTORSAMI minAsegurados(String minAsegurados) {
        this.minAsegurados = minAsegurados;
        return this;
    }

    public void setMinAsegurados(String minAsegurados) {
        this.minAsegurados = minAsegurados;
    }

    public String getMaxAsegurados() {
        return this.maxAsegurados;
    }

    public TCFACTORSAMI maxAsegurados(String maxAsegurados) {
        this.maxAsegurados = maxAsegurados;
        return this;
    }

    public void setMaxAsegurados(String maxAsegurados) {
        this.maxAsegurados = maxAsegurados;
    }

    public String getFactor() {
        return this.factor;
    }

    public TCFACTORSAMI factor(String factor) {
        this.factor = factor;
        return this;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCFACTORSAMI)) {
            return false;
        }
        return idFactorSami != null && idFactorSami.equals(((TCFACTORSAMI) o).idFactorSami);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCFACTORSAMI{" +
            "idFactorSami=" + getIdFactorSami() +
            ", minAsegurados='" + getMinAsegurados() + "'" +
            ", maxAsegurados='" + getMaxAsegurados() + "'" +
            ", factor='" + getFactor() + "'" +
            "}";
    }
}
