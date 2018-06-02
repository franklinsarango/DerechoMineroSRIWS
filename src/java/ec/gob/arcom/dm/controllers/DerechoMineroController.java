/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.controllers;

import ec.gob.arcom.dm.daos.DerechoMineroSBLocal;
import ec.gob.arcom.dm.dtos.DerechoMineroDTO;

/**
 *
 * @author mejiaw
 */
public class DerechoMineroController {
    
    public DerechoMineroDTO buscar(String codigo_arcom, DerechoMineroSBLocal derechoMineroSB) {
        DerechoMineroDTO dmDTO= new DerechoMineroDTO();
        
        Long result= derechoMineroSB.comprobarExiste(codigo_arcom);
        if(result!=null && result>0) {
            System.out.println("Se encontraron: " + result + " derechos mineros con código: " + codigo_arcom);
            return derechoMineroSB.buscarPorCodigo(codigo_arcom);
            
        } else {
            result= derechoMineroSB.comprobarExisteLicencia(codigo_arcom);
            if(result!=null && result>0) {
                System.out.println("Se encontraron: " + result + " derechos mineros con código: " + codigo_arcom);
                return derechoMineroSB.buscarPorCodigoLicencia(codigo_arcom);
            } else {
                result= derechoMineroSB.comprobarExistePlanta(codigo_arcom);
                if(result!=null && result>0) {
                    System.out.println("Se encontraron: " + result + " derechos mineros con código: " + codigo_arcom);
                    return derechoMineroSB.buscarPorCodigoPlanta(codigo_arcom);
                } else {
                    System.out.println("No se encontraron derechos mineros con código: " + codigo_arcom);
                }
            }
        }
        
        return dmDTO;
    }
}
