/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.ws;

import ec.gob.arcom.dm.controllers.DerechoMineroController;
import ec.gob.arcom.dm.daos.DerechoMineroARCHDAO;
import ec.gob.arcom.dm.dtos.DerechoMineroARCHDTO;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author SarangoF
 */
@WebService(serviceName = "DerechoMineroARCHWS", targetNamespace="http://ws.dm.arcom.gob.ec/DerechoMinero/")
public class DerechoMineroARCHWS {

    @EJB
    private DerechoMineroARCHDAO derechoMineroARCHDAO;
    
    /**
     * Web service operation
     * @param numeroDocumento
     * @return 
     */
    @WebMethod(operationName = "consultarPorDocumento")
    public DerechoMineroARCHDTO consultarPorDocumento(@WebParam(name = "numeroDocumento") String numeroDocumento) {
        DerechoMineroController dmc= new DerechoMineroController();
        return dmc.buscarPorDocumentoARCH(numeroDocumento, derechoMineroARCHDAO);
    }
}
