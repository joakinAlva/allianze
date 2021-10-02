package com.allianze.apicotizador.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCSUBGRUPO.
 */
@Entity
@Table(name = "tcsubgrupo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCSUBGRUPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idSubGrupo;

    @NotNull
    @Column(name = "sub_grupo", nullable = false)
    private String subGrupo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdSubGrupo() {
        return idSubGrupo;
    }

    public void setIdSubGrupo(Long idSubGrupo) {
        this.idSubGrupo = idSubGrupo;
    }

    public TCSUBGRUPO idSubGrupo(Long idSubGrupo) {
        this.idSubGrupo = idSubGrupo;
        return this;
    }

    public String getSubGrupo() {
        return this.subGrupo;
    }

    public TCSUBGRUPO subGrupo(String subGrupo) {
        this.subGrupo = subGrupo;
        return this;
    }

    public void setSubGrupo(String subGrupo) {
        this.subGrupo = subGrupo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCSUBGRUPO)) {
            return false;
        }
        return idSubGrupo != null && idSubGrupo.equals(((TCSUBGRUPO) o).idSubGrupo);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCSUBGRUPO{" +
            "idSubGrupo=" + getIdSubGrupo() +
            ", subGrupo='" + getSubGrupo() + "'" +
            "}";
    }
}
