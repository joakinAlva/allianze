package com.allianze.apicotizador.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TCPERFIL.
 */
@Entity
@Table(name = "tcperfil")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TCPERFIL implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idPerfil;

    @NotNull
    @Column(name = "perfil", nullable = false)
    private String perfil;

    @OneToMany(mappedBy = "employee")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "employee" }, allowSetters = true)
    private Set<TMUSUARIO> tMUSUARIOS = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public TCPERFIL idPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
        return this;
    }

    public String getPerfil() {
        return this.perfil;
    }

    public TCPERFIL perfil(String perfil) {
        this.perfil = perfil;
        return this;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Set<TMUSUARIO> getTMUSUARIOS() {
        return this.tMUSUARIOS;
    }

    public TCPERFIL tMUSUARIOS(Set<TMUSUARIO> tMUSUARIOS) {
        this.setTMUSUARIOS(tMUSUARIOS);
        return this;
    }

    public TCPERFIL addTMUSUARIO(TMUSUARIO tMUSUARIO) {
        this.tMUSUARIOS.add(tMUSUARIO);
        tMUSUARIO.setEmployee(this);
        return this;
    }

    public TCPERFIL removeTMUSUARIO(TMUSUARIO tMUSUARIO) {
        this.tMUSUARIOS.remove(tMUSUARIO);
        tMUSUARIO.setEmployee(null);
        return this;
    }

    public void setTMUSUARIOS(Set<TMUSUARIO> tMUSUARIOS) {
        if (this.tMUSUARIOS != null) {
            this.tMUSUARIOS.forEach(i -> i.setEmployee(null));
        }
        if (tMUSUARIOS != null) {
            tMUSUARIOS.forEach(i -> i.setEmployee(this));
        }
        this.tMUSUARIOS = tMUSUARIOS;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TCPERFIL)) {
            return false;
        }
        return idPerfil != null && idPerfil.equals(((TCPERFIL) o).idPerfil);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TCPERFIL{" +
            "idPerfil=" + getIdPerfil() +
            ", perfil='" + getPerfil() + "'" +
            "}";
    }
}
