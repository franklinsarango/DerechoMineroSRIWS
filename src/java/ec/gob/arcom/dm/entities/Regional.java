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
@Table(name= "regional", schema= "catmin")
public class Regional implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_regional", nullable = false)
    private Long id;
    
    private String descripcion_regional;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion_regional() {
        return descripcion_regional;
    }

    public void setDescripcion_regional(String descripcion_regional) {
        this.descripcion_regional = descripcion_regional;
    }
    
    @Override
    public String toString() {
        return "ec.gob.arcom.dm.entities.Regional[ id=" + id + " ]";
    }
    
}
