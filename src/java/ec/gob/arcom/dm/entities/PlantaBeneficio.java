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
@Table(name= "planta_beneficio", schema= "catmin")
public class PlantaBeneficio implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_planta_beneficio", nullable = false)
    private Long id;
    
    private String codigo_arcom;
    private String numero_documento_representante_legal;
    private String nombre_planta_beneficio;
    private String nombre_representante_legal;
    private String apellido_representante_legal;
    private Long codigo_provincia;
    private Long codigo_canton;
    private Long codigo_parroquida;
    private Long estado_planta;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_inscribe;
    private Long plazo;

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

    public String getNumero_documento_representante_legal() {
        return numero_documento_representante_legal;
    }

    public void setNumero_documento_representante_legal(String numero_documento_representante_legal) {
        this.numero_documento_representante_legal = numero_documento_representante_legal;
    }

    public String getNombre_planta_beneficio() {
        return nombre_planta_beneficio;
    }

    public void setNombre_planta_beneficio(String nombre_planta_beneficio) {
        this.nombre_planta_beneficio = nombre_planta_beneficio;
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

    public Long getEstado_planta() {
        return estado_planta;
    }

    public void setEstado_planta(Long estado_planta) {
        this.estado_planta = estado_planta;
    }

    public Date getFecha_inscribe() {
        return fecha_inscribe;
    }

    public void setFecha_inscribe(Date fecha_inscribe) {
        this.fecha_inscribe = fecha_inscribe;
    }

    public Long getPlazo() {
        return plazo;
    }

    public void setPlazo(Long plazo) {
        this.plazo = plazo;
    }
}
