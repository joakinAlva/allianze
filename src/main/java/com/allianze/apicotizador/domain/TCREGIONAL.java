package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCREGIONAL.
 */
@Entity
@Table(name = "tcregional")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCREGIONAL implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idRegional;

    @NotNull
    @Column(name = "regional", nullable = false)
    private String regional;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdRegional() {
        return idRegional;
    }

    public void setIdRegional(Long idRegional) {
        this.idRegional = idRegional;
    }

    public TCREGIONAL idRegional(Long idRegional) {
        this.idRegional = idRegional;
        return this;
    }

    public String getRegional() {
        return this.regional;
    }

    public TCREGIONAL regional(String regional) {
        this.regional = regional;
        return this;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCREGIONAL)) {
            return false;
        }
        return idRegional != null && idRegional.equals(((TCREGIONAL) o).idRegional);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCREGIONAL{" +
            "idRegional=" + getIdRegional() +
            ", regional='" + getRegional() + "'" +
            "}";
    }
}
