package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCCOVID.
 */
@Entity
@Table(name = "tccovid")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCCOVID implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idCovid;

    @NotNull
    @Column(name = "edad", nullable = false)
    private String edad;

    @NotNull
    @Column(name = "basica", nullable = false)
    private Long basica;

    @NotNull
    @Column(name = "recargo_edad", nullable = false)
    private Long recargoEdad;

    @NotNull
    @Column(name = "recargo_giro", nullable = false)
    private Long recargoGiro;

    @NotNull
    @Column(name = "recargo_total", nullable = false)
    private Long recargoTotal;

    @NotNull
    @Column(name = "basica_recargada", nullable = false)
    private Long basicaRecargada;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdCovid() {
        return idCovid;
    }

    public void setIdCovid(Long idCovid) {
        this.idCovid = idCovid;
    }

    public TCCOVID idCovid(Long idCovid) {
        this.idCovid = idCovid;
        return this;
    }

    public String getEdad() {
        return this.edad;
    }

    public TCCOVID edad(String edad) {
        this.edad = edad;
        return this;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Long getBasica() {
        return this.basica;
    }

    public TCCOVID basica(Long basica) {
        this.basica = basica;
        return this;
    }

    public void setBasica(Long basica) {
        this.basica = basica;
    }

    public Long getRecargoEdad() {
        return this.recargoEdad;
    }

    public TCCOVID recargoEdad(Long recargoEdad) {
        this.recargoEdad = recargoEdad;
        return this;
    }

    public void setRecargoEdad(Long recargoEdad) {
        this.recargoEdad = recargoEdad;
    }

    public Long getRecargoGiro() {
        return this.recargoGiro;
    }

    public TCCOVID recargoGiro(Long recargoGiro) {
        this.recargoGiro = recargoGiro;
        return this;
    }

    public void setRecargoGiro(Long recargoGiro) {
        this.recargoGiro = recargoGiro;
    }

    public Long getRecargoTotal() {
        return this.recargoTotal;
    }

    public TCCOVID recargoTotal(Long recargoTotal) {
        this.recargoTotal = recargoTotal;
        return this;
    }

    public void setRecargoTotal(Long recargoTotal) {
        this.recargoTotal = recargoTotal;
    }

    public Long getBasicaRecargada() {
        return this.basicaRecargada;
    }

    public TCCOVID basicaRecargada(Long basicaRecargada) {
        this.basicaRecargada = basicaRecargada;
        return this;
    }

    public void setBasicaRecargada(Long basicaRecargada) {
        this.basicaRecargada = basicaRecargada;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCCOVID)) {
            return false;
        }
        return idCovid != null && idCovid.equals(((TCCOVID) o).idCovid);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCCOVID{" +
            "idCovid=" + getIdCovid() +
            ", edad='" + getEdad() + "'" +
            ", basica=" + getBasica() +
            ", recargoEdad=" + getRecargoEdad() +
            ", recargoGiro=" + getRecargoGiro() +
            ", recargoTotal=" + getRecargoTotal() +
            ", basicaRecargada=" + getBasicaRecargada() +
            "}";
    }
}
