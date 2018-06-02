/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.daos;

import ec.gob.arcom.dm.dtos.DerechoMineroDTO;
import ec.gob.arcom.dm.entities.Localidad;
import ec.gob.arcom.dm.entities.PersonaJuridica;
import ec.gob.arcom.dm.entities.PersonaNatural;
import javax.ejb.Local;

/**
 *
 * @author mejiaw
 */
@Local
public interface DerechoMineroSBLocal {

    DerechoMineroDTO buscarPorCodigo(String codigo);
    
    DerechoMineroDTO buscarPorCodigoLicencia(String codigo);
    
    DerechoMineroDTO buscarPorCodigoPlanta(String codigo);

    Long comprobarExiste(String codigo);
    
    Long comprobarExisteLicencia(String codigo);
    
    Long comprobarExistePlanta(String codigo);

    Object obtenerConcesionario(String documento);

    String obtenerTipoMineria(Long codigo);

    String obtenerFase(Long codigo);

    Localidad obtenerLocalidad(Long codigo);

    //String obtenerNumeroResolucion(Long codigo);

    //List obtenerMaquinaria(Long codigo);

    //String obtenerTipoMaquinaria(Long codigo);

    //List obtenerCoordenadas(Long codigo);

    Double obtenerSuperficieAreaMinera(Long codigo);

    PersonaNatural obtenerPersonaNatural(String id);

    PersonaJuridica obtenerPersonaJuridica(String id);

    String obtenerEstadoConcesion(Long id);

    String obtenerRegional(Long id);
    
}
