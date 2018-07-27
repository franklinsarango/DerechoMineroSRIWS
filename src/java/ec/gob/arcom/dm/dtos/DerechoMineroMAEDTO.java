/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.dtos;

import ec.gob.arcom.dm.util.Coordenadas;
import ec.gob.arcom.dm.util.DerechoMinero;
import java.util.List;


/**
 *
 * @author mejiaw
 */
public class DerechoMineroMAEDTO {
    private DerechoMinero derechoMinero;
    private List<Coordenadas> coordenadas;

    public DerechoMinero getDerechoMinero() {
        return derechoMinero;
    }

    public void setDerechoMinero(DerechoMinero derechoMinero) {
        this.derechoMinero = derechoMinero;
    }

    public List<Coordenadas> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(List<Coordenadas> coordenadas) {
        this.coordenadas = coordenadas;
    }
    
    
}
