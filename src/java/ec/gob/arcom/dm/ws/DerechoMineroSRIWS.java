/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.ws;

import ec.gob.arcom.dm.controllers.DerechoMineroController;
import ec.gob.arcom.dm.controllers.TitularMineroController;
import ec.gob.arcom.dm.daos.DerechoMineroSBLocal;
import ec.gob.arcom.dm.daos.TitularMineroSBLocal;
import ec.gob.arcom.dm.dtos.DerechoMineroDTO;
import ec.gob.arcom.dm.dtos.TitularMineroDTO;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author mejiaw
 */
@WebService(serviceName = "DerechoMineroWS")
public class DerechoMineroSRIWS {
    @EJB
    private DerechoMineroSBLocal derechoMineroSB;
    @EJB
    private TitularMineroSBLocal titularMineroSB;
    
    /**
     * Web service operation
     * @param codigoCatastral
     * @return 
     */
    @WebMethod(operationName = "consultarPorCodigo")
    public DerechoMineroDTO consultarPorCodigo(@WebParam(name = "codigoCatastral") String codigoCatastral) {
        DerechoMineroController dmc= new DerechoMineroController();
        return dmc.buscar(codigoCatastral, derechoMineroSB);
    }

    /**
     * Web service operation
     * @param documento
     * @return 
     */
    @WebMethod(operationName = "consultarPorDocumento")
    public TitularMineroDTO consultarPorDocumento(@WebParam(name = "documento") String documento) {
        TitularMineroController tmc= new TitularMineroController();
        return tmc.buscar(documento, titularMineroSB);
    }
}
