package com.allianze.apicotizador.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCTIPOPRODUCTO.
 */
@Entity
@Table(name = "tctipoproducto")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCTIPOPRODUCTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idTipoProducto;

    @NotNull
    @Column(name = "tipo_producto", nullable = false)
    private String tipoProducto;

    @NotNull
    @Column(name = "registro", nullable = false)
    private String registro;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Long idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public TCTIPOPRODUCTO idTipoProducto(Long idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
        return this;
    }

    public String getTipoProducto() {
        return this.tipoProducto;
    }

    public TCTIPOPRODUCTO tipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
        return this;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getRegistro() {
        return this.registro;
    }

    public TCTIPOPRODUCTO registro(String registro) {
        this.registro = registro;
        return this;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public TCTIPOPRODUCTO fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCTIPOPRODUCTO)) {
            return false;
        }
        return idTipoProducto != null && idTipoProducto.equals(((TCTIPOPRODUCTO) o).idTipoProducto);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCTIPOPRODUCTO{" +
            "idTipoProducto=" + getIdTipoProducto() +
            ", tipoProducto='" + getTipoProducto() + "'" +
            ", registro='" + getRegistro() + "'" +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
}
