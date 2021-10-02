package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCTIPO.
 */
@Entity
@Table(name = "tctipo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCTIPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idTipo;

    @NotNull
    @Column(name = "tipo_regla", nullable = false)
    private String tipoRegla;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }

    public TCTIPO idTipo(Long idTipo) {
        this.idTipo = idTipo;
        return this;
    }

    public String getTipoRegla() {
        return this.tipoRegla;
    }

    public TCTIPO tipoRegla(String tipoRegla) {
        this.tipoRegla = tipoRegla;
        return this;
    }

    public void setTipoRegla(String tipoRegla) {
        this.tipoRegla = tipoRegla;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCTIPO)) {
            return false;
        }
        return idTipo != null && idTipo.equals(((TCTIPO) o).idTipo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCTIPO{" +
            "idTipo=" + getIdTipo() +
            ", tipoRegla='" + getTipoRegla() + "'" +
            "}";
    }
}
