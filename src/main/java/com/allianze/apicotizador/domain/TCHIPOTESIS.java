package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCHIPOTESIS.
 */
@Entity
@Table(name = "tchipotesis")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCHIPOTESIS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idHipotesis;

    @NotNull
    @Column(name = "hipotesis", nullable = false)
    private String hipotesis;

    @NotNull
    @Column(name = "valor", nullable = false)
    private Long valor;

    @NotNull
    @Column(name = "variable", nullable = false)
    private String variable;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdHipotesis() {
        return idHipotesis;
    }

    public void setIdHipotesis(Long idHipotesis) {
        this.idHipotesis = idHipotesis;
    }

    public TCHIPOTESIS idHipotesis(Long idHipotesis) {
        this.idHipotesis = idHipotesis;
        return this;
    }

    public String getHipotesis() {
        return this.hipotesis;
    }

    public TCHIPOTESIS hipotesis(String hipotesis) {
        this.hipotesis = hipotesis;
        return this;
    }

    public void setHipotesis(String hipotesis) {
        this.hipotesis = hipotesis;
    }

    public Long getValor() {
        return this.valor;
    }

    public TCHIPOTESIS valor(Long valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public String getVariable() {
        return this.variable;
    }

    public TCHIPOTESIS variable(String variable) {
        this.variable = variable;
        return this;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCHIPOTESIS)) {
            return false;
        }
        return idHipotesis != null && idHipotesis.equals(((TCHIPOTESIS) o).idHipotesis);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCHIPOTESIS{" +
            "idHipotesis=" + getIdHipotesis() +
            ", hipotesis='" + getHipotesis() + "'" +
            ", valor=" + getValor() +
            ", variable='" + getVariable() + "'" +
            "}";
    }
}
