package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCDESCUENTOSUMAASEGURADA.
 */
@Entity
@Table(name = "tcdescuentosumaasegurada")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCDESCUENTOSUMAASEGURADA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idDescuentoSumaAsegurada;

    @NotNull
    @Column(name = "min_suma", nullable = false)
    private String minSuma;

    @NotNull
    @Column(name = "max_suma", nullable = false)
    private String maxSuma;

    @NotNull
    @Column(name = "porcentaje", nullable = false)
    private Long porcentaje;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdDescuentoSumaAsegurada() {
        return idDescuentoSumaAsegurada;
    }

    public void setIdDescuentoSumaAsegurada(Long idDescuentoSumaAsegurada) {
        this.idDescuentoSumaAsegurada = idDescuentoSumaAsegurada;
    }

    public TCDESCUENTOSUMAASEGURADA idDescuentoSumaAsegurada(Long idDescuentoSumaAsegurada) {
        this.idDescuentoSumaAsegurada = idDescuentoSumaAsegurada;
        return this;
    }

    public String getMinSuma() {
        return this.minSuma;
    }

    public TCDESCUENTOSUMAASEGURADA minSuma(String minSuma) {
        this.minSuma = minSuma;
        return this;
    }

    public void setMinSuma(String minSuma) {
        this.minSuma = minSuma;
    }

    public String getMaxSuma() {
        return this.maxSuma;
    }

    public TCDESCUENTOSUMAASEGURADA maxSuma(String maxSuma) {
        this.maxSuma = maxSuma;
        return this;
    }

    public void setMaxSuma(String maxSuma) {
        this.maxSuma = maxSuma;
    }

    public Long getPorcentaje() {
        return this.porcentaje;
    }

    public TCDESCUENTOSUMAASEGURADA porcentaje(Long porcentaje) {
        this.porcentaje = porcentaje;
        return this;
    }

    public void setPorcentaje(Long porcentaje) {
        this.porcentaje = porcentaje;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCDESCUENTOSUMAASEGURADA)) {
            return false;
        }
        return idDescuentoSumaAsegurada != null && idDescuentoSumaAsegurada.equals(((TCDESCUENTOSUMAASEGURADA) o).idDescuentoSumaAsegurada);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCDESCUENTOSUMAASEGURADA{" +
            "idDescuentoSumaAsegurada=" + getIdDescuentoSumaAsegurada() +
            ", minSuma='" + getMinSuma() + "'" +
            ", maxSuma='" + getMaxSuma() + "'" +
            ", porcentaje=" + getPorcentaje() +
            "}";
    }
}
