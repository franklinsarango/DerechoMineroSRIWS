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
public class DerechoMineroARCHDTO {
    private List<DerechoMinero> derechoMinero;    

    public List<DerechoMinero> getDerechoMinero() {
        return derechoMinero;
    }

    public void setDerechoMinero(List<DerechoMinero> derechoMinero) {
        this.derechoMinero = derechoMinero;
    }
    
}
