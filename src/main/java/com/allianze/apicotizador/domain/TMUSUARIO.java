package com.allianze.apicotizador.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TMUSUARIO.
 */
@Entity
@Table(name = "tmusuario")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TMUSUARIO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long idUsuario;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @NotNull
    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;

    @NotNull
    @Column(name = "usuario", nullable = false)
    private String usuario;

    @NotNull
    @Column(name = "clave", nullable = false)
    private String clave;

    @NotNull
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @NotNull
    @Column(name = "token", nullable = false)
    private String token;

    @NotNull
    @Max(value = 1L)
    @Column(name = "estatus", nullable = false)
    private Long estatus;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tMUSUARIOS" }, allowSetters = true)
    private TCPERFIL employee;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TMUSUARIO idUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    public String getNombre() {
        return this.nombre;
    }

    public TMUSUARIO nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public TMUSUARIO apellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public TMUSUARIO correoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
        return this;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public TMUSUARIO usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return this.clave;
    }

    public TMUSUARIO clave(String clave) {
        this.clave = clave;
        return this;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public LocalDate getFechaRegistro() {
        return this.fechaRegistro;
    }

    public TMUSUARIO fechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
        return this;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getToken() {
        return this.token;
    }

    public TMUSUARIO token(String token) {
        this.token = token;
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getEstatus() {
        return this.estatus;
    }

    public TMUSUARIO estatus(Long estatus) {
        this.estatus = estatus;
        return this;
    }

    public void setEstatus(Long estatus) {
        this.estatus = estatus;
    }

    public TCPERFIL getEmployee() {
        return this.employee;
    }

    public TMUSUARIO employee(TCPERFIL tCPERFIL) {
        this.setEmployee(tCPERFIL);
        return this;
    }

    public void setEmployee(TCPERFIL tCPERFIL) {
        this.employee = tCPERFIL;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TMUSUARIO)) {
            return false;
        }
        return idUsuario != null && idUsuario.equals(((TMUSUARIO) o).idUsuario);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TMUSUARIO{" +
            "idUsuario=" + getIdUsuario() +
            ", nombre='" + getNombre() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            ", correoElectronico='" + getCorreoElectronico() + "'" +
            ", usuario='" + getUsuario() + "'" +
            ", clave='" + getClave() + "'" +
            ", fechaRegistro='" + getFechaRegistro() + "'" +
            ", token='" + getToken() + "'" +
            ", estatus=" + getEstatus() +
            "}";
    }
}
