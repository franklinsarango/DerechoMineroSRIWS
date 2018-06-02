/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author mejiaw
 */
@Entity
@Table(name= "licencia_comercializacion", schema= "catmin")
public class LicenciaComercializacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_licencia_comercializacion", nullable = false)
    private Long id;
    
    private String codigo_arcom;
    private String numero_documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String casillero_judicial;
    private Long codigo_provincia;
    private Long codigo_canton;
    private Long codigo_parroquida;
    private Long estado_licencia;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_inscribe;
    private Long codigo_tipo_mineral;
    private Long codigo_mineral_interes;
    private String correo_electronico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo_arcom() {
        return codigo_arcom;
    }

    public void setCodigo_arcom(String codigo_arcom) {
        this.codigo_arcom = codigo_arcom;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCasillero_judicial() {
        return casillero_judicial;
    }

    public void setCasillero_judicial(String casillero_judicial) {
        this.casillero_judicial = casillero_judicial;
    }

    public Long getCodigo_provincia() {
        return codigo_provincia;
    }

    public void setCodigo_provincia(Long codigo_provincia) {
        this.codigo_provincia = codigo_provincia;
    }

    public Long getCodigo_canton() {
        return codigo_canton;
    }

    public void setCodigo_canton(Long codigo_canton) {
        this.codigo_canton = codigo_canton;
    }

    public Long getCodigo_parroquida() {
        return codigo_parroquida;
    }

    public void setCodigo_parroquida(Long codigo_parroquida) {
        this.codigo_parroquida = codigo_parroquida;
    }

    public Long getEstado_licencia() {
        return estado_licencia;
    }

    public void setEstado_licencia(Long estado_licencia) {
        this.estado_licencia = estado_licencia;
    }

    public Date getFecha_inscribe() {
        return fecha_inscribe;
    }

    public void setFecha_inscribe(Date fecha_inscribe) {
        this.fecha_inscribe = fecha_inscribe;
    }

    public Long getCodigo_tipo_mineral() {
        return codigo_tipo_mineral;
    }

    public void setCodigo_tipo_mineral(Long codigo_tipo_mineral) {
        this.codigo_tipo_mineral = codigo_tipo_mineral;
    }

    public Long getCodigo_mineral_interes() {
        return codigo_mineral_interes;
    }

    public void setCodigo_mineral_interes(Long codigo_mineral_interes) {
        this.codigo_mineral_interes = codigo_mineral_interes;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    
}
