/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.daos;


import ec.gob.arcom.dm.dtos.DerechoMineroMAEDTO;
import ec.gob.arcom.dm.util.Coordenadas;
import ec.gob.arcom.dm.util.DerechoMinero;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mejiaw
 */
@Local
public interface DerechoMineroMAEDAO {

    DerechoMinero concesionPorCodigo(String codigoArcom);  
    
    DerechoMinero plantaBeneficioPorCodigo(String codigoArcom);  
    
    List<Coordenadas> coordenadasConcesionPorCodigo(String codigoArcom);    
    
    List<Coordenadas> coordenadasPlantaBeneficioPorCodigo(String codigoArcom);    
}
