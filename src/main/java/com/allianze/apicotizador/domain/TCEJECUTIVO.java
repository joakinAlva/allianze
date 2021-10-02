package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCEJECUTIVO.
 */
@Entity
@Table(name = "tcejecutivo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCEJECUTIVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idEjecutivo;

    @NotNull
    @Column(name = "ejecutivo", nullable = false)
    private String ejecutivo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdEjecutivo() {
        return idEjecutivo;
    }

    public void setIdEjecutivo(Long idEjecutivo) {
        this.idEjecutivo = idEjecutivo;
    }

    public TCEJECUTIVO idEjecutivo(Long idEjecutivo) {
        this.idEjecutivo = idEjecutivo;
        return this;
    }

    public String getEjecutivo() {
        return this.ejecutivo;
    }

    public TCEJECUTIVO ejecutivo(String ejecutivo) {
        this.ejecutivo = ejecutivo;
        return this;
    }

    public void setEjecutivo(String ejecutivo) {
        this.ejecutivo = ejecutivo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCEJECUTIVO)) {
            return false;
        }
        return idEjecutivo != null && idEjecutivo.equals(((TCEJECUTIVO) o).idEjecutivo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCEJECUTIVO{" +
            "idEjecutivo=" + getIdEjecutivo() +
            ", ejecutivo='" + getEjecutivo() + "'" +
            "}";
    }
}
