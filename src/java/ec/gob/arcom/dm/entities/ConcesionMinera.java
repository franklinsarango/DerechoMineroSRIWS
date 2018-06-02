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
@Table(name= "concesion_minera", schema= "catmin")
public class ConcesionMinera implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_concesion", nullable = false)
    private Long id;
    
    private String codigo_arcom;
    private Long codigo_fase;
    private Long estado_concesion;
    private Long codigo_regional;
    private String nombre_concesion;
    private Long codigo_tipo_mineria;
    private Long plazo_concesion;
    private Long codigo_provincia;
    private Long codigo_canton;
    private Long codigo_parroquia;
    private String documento_concesionario_principal;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_inscribe;
    
    private String material_interes; //mineral
    
    
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

    public Long getCodigo_fase() {
        return codigo_fase;
    }

    public void setCodigo_fase(Long codigo_fase) {
        this.codigo_fase = codigo_fase;
    }

    public String getNombre_concesion() {
        return nombre_concesion;
    }

    public void setNombre_concesion(String nombre_concesion) {
        this.nombre_concesion = nombre_concesion;
    }

    public Long getCodigo_tipo_mineria() {
        return codigo_tipo_mineria;
    }

    public void setCodigo_tipo_mineria(Long codigo_tipo_mineria) {
        this.codigo_tipo_mineria = codigo_tipo_mineria;
    }

    public Long getPlazo_concesion() {
        return plazo_concesion;
    }

    public void setPlazo_concesion(Long plazo_concesion) {
        this.plazo_concesion = plazo_concesion;
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

    public Long getCodigo_parroquia() {
        return codigo_parroquia;
    }

    public void setCodigo_parroquia(Long codigo_parroquia) {
        this.codigo_parroquia = codigo_parroquia;
    }

    public String getDocumento_concesionario_principal() {
        return documento_concesionario_principal;
    }

    public void setDocumento_concesionario_principal(String documento_concesionario_principal) {
        this.documento_concesionario_principal = documento_concesionario_principal;
    }

    public Date getFecha_inscribe() {
        return fecha_inscribe;
    }

    public void setFecha_inscribe(Date fecha_inscribe) {
        this.fecha_inscribe = fecha_inscribe;
    }

    public Long getEstado_concesion() {
        return estado_concesion;
    }

    public void setEstado_concesion(Long estado_concesion) {
        this.estado_concesion = estado_concesion;
    }

    public Long getCodigo_regional() {
        return codigo_regional;
    }

    public void setCodigo_regional(Long codigo_regional) {
        this.codigo_regional = codigo_regional;
    }

    public String getMaterial_interes() {
        return material_interes;
    }

    public void setMaterial_interes(String material_interes) {
        this.material_interes = material_interes;
    }
}
