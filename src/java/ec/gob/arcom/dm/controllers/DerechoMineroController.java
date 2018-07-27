/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.controllers;

import ec.gob.arcom.dm.daos.DerechoMineroMAEDAO;
import ec.gob.arcom.dm.daos.DerechoMineroSBLocal;
import ec.gob.arcom.dm.dtos.DerechoMineroDTO;
import ec.gob.arcom.dm.dtos.DerechoMineroMAEDTO;
import ec.gob.arcom.dm.util.Coordenadas;
import ec.gob.arcom.dm.util.DerechoMinero;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author sarangof
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
    
    
    public DerechoMineroMAEDTO buscarCodigoArcomMAE(String codigoArcom, DerechoMineroMAEDAO derechoMineroMAEDAO) {
        DerechoMineroMAEDTO dmDTO= new DerechoMineroMAEDTO();
        DerechoMinero d = new DerechoMinero();
        List<Coordenadas> c = new ArrayList();
        d = derechoMineroMAEDAO.concesionPorCodigo(codigoArcom);
        c = derechoMineroMAEDAO.coordenadasPorCodigo(codigoArcom);
        
        dmDTO.setDerechoMinero(d);
        dmDTO.setCoordenadas(c);
        return dmDTO;
    }
}
