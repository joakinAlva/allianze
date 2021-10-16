package com.allianze.apicotizador.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TMCOTIZACION.
 */
@Entity
@Table(name = "tmcotizacion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TMCOTIZACION implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idCotizacion;

    @NotNull
    @Column(name = "usuario", nullable = false)
    private Long usuario;

    @NotNull
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @NotNull
    @Column(name = "estatus", nullable = false)
    private Long estatus;

    @NotNull
    @Column(name = "eliminada", nullable = false)
    private Long eliminada;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(Long idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public TMCOTIZACION idCotizacion(Long idCotizacion) {
        this.idCotizacion = idCotizacion;
        return this;
    }

    public Long getUsuario() {
        return this.usuario;
    }

    public TMCOTIZACION usuario(Long usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaRegistro() {
        return this.fechaRegistro;
    }

    public TMCOTIZACION fechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
        return this;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getEstatus() {
        return this.estatus;
    }

    public TMCOTIZACION estatus(Long estatus) {
        this.estatus = estatus;
        return this;
    }

    public void setEstatus(Long estatus) {
        this.estatus = estatus;
    }

    public Long getEliminada() {
        return this.eliminada;
    }

    public TMCOTIZACION eliminada(Long eliminada) {
        this.eliminada = eliminada;
        return this;
    }

    public void setEliminada(Long eliminada) {
        this.eliminada = eliminada;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TMCOTIZACION)) {
            return false;
        }
        return idCotizacion != null && idCotizacion.equals(((TMCOTIZACION) o).idCotizacion);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TMCOTIZACION{" +
            "idCotizacion=" + getIdCotizacion() +
            ", usuario=" + getUsuario() +
            ", fechaRegistro='" + getFechaRegistro() + "'" +
            ", estatus=" + getEstatus() +
            ", eliminada=" + getEliminada() +
            "}";
    }
}
