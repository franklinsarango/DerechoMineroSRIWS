/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mejiaw
 */
@Entity
@Table(name = "persona_juridica", schema = "catmin")
public class PersonaJuridica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ruc", nullable = false)
    private String id;
    
    private String nombre_legal;
    private String documento_representante_legal;
    private String nombre_representante_legal;
    private String apellido_representante_legal;
    private String email;
    private String telefono;
    private String celular;
    private String direccion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre_legal() {
        return nombre_legal;
    }

    public void setNombre_legal(String nombre_legal) {
        this.nombre_legal = nombre_legal;
    }

    public String getDocumento_representante_legal() {
        return documento_representante_legal;
    }

    public void setDocumento_representante_legal(String documento_representante_legal) {
        this.documento_representante_legal = documento_representante_legal;
    }

    public String getNombre_representante_legal() {
        return nombre_representante_legal;
    }

    public void setNombre_representante_legal(String nombre_representante_legal) {
        this.nombre_representante_legal = nombre_representante_legal;
    }

    public String getApellido_representante_legal() {
        return apellido_representante_legal;
    }

    public void setApellido_representante_legal(String apellido_representante_legal) {
        this.apellido_representante_legal = apellido_representante_legal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "ec.gob.arcom.dm.entities.PersonaJuridica[ id=" + id + " ]";
    }
}
