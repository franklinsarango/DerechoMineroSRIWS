/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.daos;

import ec.gob.arcom.dm.dtos.TitularMineroDTO;
import ec.gob.arcom.dm.entities.ConcesionMinera;
import ec.gob.arcom.dm.entities.LicenciaComercializacion;
import ec.gob.arcom.dm.entities.Localidad;
import ec.gob.arcom.dm.entities.PersonaJuridica;
import ec.gob.arcom.dm.entities.PersonaNatural;
import ec.gob.arcom.dm.entities.PlantaBeneficio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mejiaw
 */
@Local
public interface TitularMineroSBLocal {
    
    TitularMineroDTO buscarPorDocumento(String documento);
    
    PersonaNatural obtenerPersonaNatural(String id);

    PersonaJuridica obtenerPersonaJuridica(String id);

    List<ConcesionMinera> obtenerConcesiones(String documento);
    
    List<ConcesionMinera> obtenerConcesionesCotitular(String documento);
    
    List<ConcesionMinera> obtenerConcesionesContratoOperacion(String documento);
    
    List<LicenciaComercializacion> obtenerLicencias(String documento);
    
    List<PlantaBeneficio> obtenerPlantas(String documento);
    
    String obtenerTipoMineria(Long codigo);
    
    String obtenerEstadoConcesion(Long id);

    String obtenerRegional(Long id);
    
    Localidad obtenerLocalidad(Long codigo);
    
    Double obtenerSuperficieAreaMinera(Long codigo);
    
    String obtenerFase(Long codigo);
}
