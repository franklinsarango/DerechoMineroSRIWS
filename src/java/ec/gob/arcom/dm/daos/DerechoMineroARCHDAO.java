/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.daos;


import ec.gob.arcom.dm.dtos.DerechoMineroMAEDTO;
import ec.gob.arcom.dm.entities.ConcesionMinera;
import ec.gob.arcom.dm.util.Coordenadas;
import ec.gob.arcom.dm.util.DerechoMinero;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mejiaw
 */
@Local
public interface DerechoMineroARCHDAO {

    List<DerechoMinero> concesionPorDocumento(String documento);  
    
    List<DerechoMinero> plantaBeneficioPorDocumento(String documento);  
    
    List<DerechoMinero> concesionesCotitular(String documento);
    
    List<DerechoMinero> concesionesContratoOperacion(String documento);
    
}
