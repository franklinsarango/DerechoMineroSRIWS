/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.util;

import ec.gob.arcom.dm.entities.PersonaJuridica;
import ec.gob.arcom.dm.entities.PersonaNatural;

/**
 *
 * @author mejiaw
 */
public class Titular {
    private static final String VALOR_BLANCO= null;
    
    //Propios de persona natural
    private String cedula;
    private String apellido;
    private String nombre;
    
    //Propios de persona juridica
    private String ruc;
    private String razonSocial;
    private String apellidoRepresentanteLegal;
    private String nombreRepresentanteLegal;
    
    //Comunes
    private String email;
    private String telefonoFijo;
    private String telefonoMovil;
    private String direccion;
    private String tipoPersona;
    
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getApellidoRepresentanteLegal() {
        return apellidoRepresentanteLegal;
    }

    public void setApellidoRepresentanteLegal(String apellidoRepresentanteLegal) {
        this.apellidoRepresentanteLegal = apellidoRepresentanteLegal;
    }

    public String getNombreRepresentanteLegal() {
        return nombreRepresentanteLegal;
    }

    public void setNombreRepresentanteLegal(String nombreRepresentanteLegal) {
        this.nombreRepresentanteLegal = nombreRepresentanteLegal;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    
    public Titular(PersonaNatural pn) {
        this.cedula= pn.getId();
        this.apellido= pn.getApellido();
        this.nombre= pn.getNombre();
        this.email= pn.getEmail();
        this.telefonoFijo= pn.getTelefono();
        this.telefonoMovil= pn.getCelular();
        this.direccion= pn.getDireccion();
        this.tipoPersona= "PERNAT";
        //Dejar en blanco los campos de persona juridica
        this.ruc= VALOR_BLANCO;
        this.razonSocial= VALOR_BLANCO;
        this.apellidoRepresentanteLegal= VALOR_BLANCO;
        this.nombreRepresentanteLegal= VALOR_BLANCO;
    }
    
    public Titular(PersonaJuridica pj) {
        this.ruc= pj.getId();
        this.razonSocial= pj.getNombre_legal();
        this.apellidoRepresentanteLegal= pj.getApellido_representante_legal();
        this.nombreRepresentanteLegal= pj.getNombre_representante_legal();
        this.email= pj.getEmail();
        this.telefonoFijo= pj.getTelefono();
        this.telefonoMovil= pj.getCelular();
        this.direccion= pj.getDireccion();
        this.tipoPersona= "PERJUR";
        //Dejar en blanco los campos de persona natural
        this.cedula= VALOR_BLANCO;
        this.apellido= VALOR_BLANCO;
        this.nombre= VALOR_BLANCO;
    }
}
