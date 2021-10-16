package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TMCOTIZACIONEXPPROPIA.
 */
@Entity
@Table(name = "tmcotizacionexppropia")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TMCOTIZACIONEXPPROPIA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idCotizacionExpPropia;

    @NotNull
    @Column(name = "numero", nullable = false)
    private Long numero;

    @NotNull
    @Column(name = "periodo", nullable = false)
    private Long periodo;

    @NotNull
    @Column(name = "siniestro", nullable = false)
    private Long siniestro;

    @NotNull
    @Column(name = "asegurados", nullable = false)
    private Long asegurados;

    @NotNull
    @Column(name = "valor_qx", nullable = false)
    private Long valorQx;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdCotizacionExpPropia() {
        return idCotizacionExpPropia;
    }

    public void setIdCotizacionExpPropia(Long idCotizacionExpPropia) {
        this.idCotizacionExpPropia = idCotizacionExpPropia;
    }

    public TMCOTIZACIONEXPPROPIA idCotizacionExpPropia(Long idCotizacionExpPropia) {
        this.idCotizacionExpPropia = idCotizacionExpPropia;
        return this;
    }

    public Long getNumero() {
        return this.numero;
    }

    public TMCOTIZACIONEXPPROPIA numero(Long numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getPeriodo() {
        return this.periodo;
    }

    public TMCOTIZACIONEXPPROPIA periodo(Long periodo) {
        this.periodo = periodo;
        return this;
    }

    public void setPeriodo(Long periodo) {
        this.periodo = periodo;
    }

    public Long getSiniestro() {
        return this.siniestro;
    }

    public TMCOTIZACIONEXPPROPIA siniestro(Long siniestro) {
        this.siniestro = siniestro;
        return this;
    }

    public void setSiniestro(Long siniestro) {
        this.siniestro = siniestro;
    }

    public Long getAsegurados() {
        return this.asegurados;
    }

    public TMCOTIZACIONEXPPROPIA asegurados(Long asegurados) {
        this.asegurados = asegurados;
        return this;
    }

    public void setAsegurados(Long asegurados) {
        this.asegurados = asegurados;
    }

    public Long getValorQx() {
        return this.valorQx;
    }

    public TMCOTIZACIONEXPPROPIA valorQx(Long valorQx) {
        this.valorQx = valorQx;
        return this;
    }

    public void setValorQx(Long valorQx) {
        this.valorQx = valorQx;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TMCOTIZACIONEXPPROPIA)) {
            return false;
        }
        return idCotizacionExpPropia != null && idCotizacionExpPropia.equals(((TMCOTIZACIONEXPPROPIA) o).idCotizacionExpPropia);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TMCOTIZACIONEXPPROPIA{" +
            "idCotizacionExpPropia=" + getIdCotizacionExpPropia() +
            ", numero=" + getNumero() +
            ", periodo=" + getPeriodo() +
            ", siniestro=" + getSiniestro() +
            ", asegurados=" + getAsegurados() +
            ", valorQx=" + getValorQx() +
            "}";
    }
}
