/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.controllers;

import ec.gob.arcom.dm.daos.TitularMineroSBLocal;
import ec.gob.arcom.dm.dtos.TitularMineroDTO;

/**
 *
 * @author mejiaw
 */
public class TitularMineroController {
    
    public TitularMineroDTO buscar(String documento, TitularMineroSBLocal titularMineroSB) {
        TitularMineroDTO tmDTO= titularMineroSB.buscarPorDocumento(documento);
        
        if(tmDTO!=null) {
            System.out.println("Se encontro el titular con documento: " + documento);
            return tmDTO;
        }
        System.out.println("No se encontraron titulares");
        return new TitularMineroDTO();
    }
}
