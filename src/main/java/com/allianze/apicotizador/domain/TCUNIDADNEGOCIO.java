package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCUNIDADNEGOCIO.
 */
@Entity
@Table(name = "tcunidadnegocio")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCUNIDADNEGOCIO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idUnidadNegocio;

    @NotNull
    @Column(name = "unidad_negocio", nullable = false)
    private String unidadNegocio;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdUnidadNegocio() {
        return idUnidadNegocio;
    }

    public void setIdUnidadNegocio(Long idUnidadNegocio) {
        this.idUnidadNegocio = idUnidadNegocio;
    }

    public TCUNIDADNEGOCIO idUnidadNegocio(Long idUnidadNegocio) {
        this.idUnidadNegocio = idUnidadNegocio;
        return this;
    }

    public String getUnidadNegocio() {
        return this.unidadNegocio;
    }

    public TCUNIDADNEGOCIO unidadNegocio(String unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
        return this;
    }

    public void setUnidadNegocio(String unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCUNIDADNEGOCIO)) {
            return false;
        }
        return idUnidadNegocio != null && idUnidadNegocio.equals(((TCUNIDADNEGOCIO) o).idUnidadNegocio);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCUNIDADNEGOCIO{" +
            "idUnidadNegocio=" + getIdUnidadNegocio() +
            ", unidadNegocio='" + getUnidadNegocio() + "'" +
            "}";
    }
}
