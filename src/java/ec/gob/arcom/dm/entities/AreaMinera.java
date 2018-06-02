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
@Table(name= "area_minera", schema= "catmin")
public class AreaMinera implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_area_minera", nullable = false)
    private Long id;
    private Long codigo_concesion;
    private Double superficie_area_minera;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigo_concesion() {
        return codigo_concesion;
    }

    public void setCodigo_concesion(Long codigo_concesion) {
        this.codigo_concesion = codigo_concesion;
    }

    public Double getSuperficie_area_minera() {
        return superficie_area_minera;
    }

    public void setSuperficie_area_minera(Double superficie_area_minera) {
        this.superficie_area_minera = superficie_area_minera;
    }
}
