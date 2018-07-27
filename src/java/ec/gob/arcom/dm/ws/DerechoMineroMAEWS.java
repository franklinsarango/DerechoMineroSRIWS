/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.ws;

import ec.gob.arcom.dm.controllers.DerechoMineroController;
import ec.gob.arcom.dm.daos.DerechoMineroMAEDAO;
import ec.gob.arcom.dm.dtos.DerechoMineroMAEDTO;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author SarangoF
 */
@WebService(serviceName = "DerechoMineroMAEWS")
public class DerechoMineroMAEWS {

    @EJB
    private DerechoMineroMAEDAO derechoMineroMAEDAO;
    
    /**
     * Web service operation
     * @param codigoCatastral
     * @return 
     */
    @WebMethod(operationName = "consultarPorCodigo")
    public DerechoMineroMAEDTO consultarPorCodigo(@WebParam(name = "codigoCatastral") String codigoCatastral) {
        DerechoMineroController dmc= new DerechoMineroController();
        return dmc.buscarCodigoArcomMAE(codigoCatastral, derechoMineroMAEDAO);
    }
}
