package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCDESCUENTOTIPORIESGO.
 */
@Entity
@Table(name = "tcdescuentotiporiesgo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCDESCUENTOTIPORIESGO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idDescuentoTipoRiesgo;

    @NotNull
    @Column(name = "tipo", nullable = false)
    private String tipo;

    @NotNull
    @Column(name = "descuento", nullable = false)
    private Long descuento;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdDescuentoTipoRiesgo() {
        return idDescuentoTipoRiesgo;
    }

    public void setIdDescuentoTipoRiesgo(Long idDescuentoTipoRiesgo) {
        this.idDescuentoTipoRiesgo = idDescuentoTipoRiesgo;
    }

    public TCDESCUENTOTIPORIESGO idDescuentoTipoRiesgo(Long idDescuentoTipoRiesgo) {
        this.idDescuentoTipoRiesgo = idDescuentoTipoRiesgo;
        return this;
    }

    public String getTipo() {
        return this.tipo;
    }

    public TCDESCUENTOTIPORIESGO tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getDescuento() {
        return this.descuento;
    }

    public TCDESCUENTOTIPORIESGO descuento(Long descuento) {
        this.descuento = descuento;
        return this;
    }

    public void setDescuento(Long descuento) {
        this.descuento = descuento;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCDESCUENTOTIPORIESGO)) {
            return false;
        }
        return idDescuentoTipoRiesgo != null && idDescuentoTipoRiesgo.equals(((TCDESCUENTOTIPORIESGO) o).idDescuentoTipoRiesgo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCDESCUENTOTIPORIESGO{" +
            "idDescuentoTipoRiesgo=" + getIdDescuentoTipoRiesgo() +
            ", tipo='" + getTipo() + "'" +
            ", descuento=" + getDescuento() +
            "}";
    }
}
