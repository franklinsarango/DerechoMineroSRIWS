/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.dtos;

import ec.gob.arcom.dm.util.ConcesionMineraWrapper;
import ec.gob.arcom.dm.util.Titular;
import java.util.List;

/**
 *
 * @author mejiaw
 */
public class TitularMineroDTO {
    
    private Titular titular;
    private List<ConcesionMineraWrapper> derechosMineros;

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public List<ConcesionMineraWrapper> getDerechosMineros() {
        return derechosMineros;
    }

    public void setDerechosMineros(List<ConcesionMineraWrapper> derechosMineros) {
        this.derechosMineros = derechosMineros;
    }
}
