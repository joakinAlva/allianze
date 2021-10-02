package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCTIPOREGLA.
 */
@Entity
@Table(name = "tctiporegla")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCTIPOREGLA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idTipoRegla;

    @NotNull
    @Column(name = "tipo", nullable = false)
    private String tipo;

    @NotNull
    @Column(name = "segmento", nullable = false)
    private Long segmento;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdTipoRegla() {
        return idTipoRegla;
    }

    public void setIdTipoRegla(Long idTipoRegla) {
        this.idTipoRegla = idTipoRegla;
    }

    public TCTIPOREGLA idTipoRegla(Long idTipoRegla) {
        this.idTipoRegla = idTipoRegla;
        return this;
    }

    public String getTipo() {
        return this.tipo;
    }

    public TCTIPOREGLA tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getSegmento() {
        return this.segmento;
    }

    public TCTIPOREGLA segmento(Long segmento) {
        this.segmento = segmento;
        return this;
    }

    public void setSegmento(Long segmento) {
        this.segmento = segmento;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCTIPOREGLA)) {
            return false;
        }
        return idTipoRegla != null && idTipoRegla.equals(((TCTIPOREGLA) o).idTipoRegla);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCTIPOREGLA{" +
            "idTipoRegla=" + getIdTipoRegla() +
            ", tipo='" + getTipo() + "'" +
            ", segmento=" + getSegmento() +
            "}";
    }
}
