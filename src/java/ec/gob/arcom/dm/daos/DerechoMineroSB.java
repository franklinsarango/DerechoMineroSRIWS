/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.daos;

import ec.gob.arcom.dm.dtos.DerechoMineroDTO;
import ec.gob.arcom.dm.entities.AreaMinera;
import ec.gob.arcom.dm.entities.CatalogoDetalle;
import ec.gob.arcom.dm.entities.ConcesionMinera;
import ec.gob.arcom.dm.entities.Fase;
import ec.gob.arcom.dm.entities.LicenciaComercializacion;
import ec.gob.arcom.dm.entities.Localidad;
import ec.gob.arcom.dm.entities.PersonaJuridica;
import ec.gob.arcom.dm.entities.PersonaNatural;
import ec.gob.arcom.dm.entities.PlantaBeneficio;
import ec.gob.arcom.dm.entities.Regional;
import ec.gob.arcom.dm.entities.TipoMineria;
import ec.gob.arcom.dm.util.Titular;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mejiaw
 */
@Stateless
public class DerechoMineroSB implements DerechoMineroSBLocal {
    @PersistenceContext
    private EntityManager em;
    
    private static final int PROVINCIA= 2;
    private static final int CANTON= 3;
    private static final int PARROQUIA= 5;
    private static final String VALOR_VACIO= " ";
    private static final String VALOR_NO_EXISTENTE= "OTROS";
    
    @Override
    public Long comprobarExiste(String codigo) {
        try {
            Query query= em.createQuery("Select count(c) from ConcesionMinera c where c.codigo_arcom= :codigo");
            query.setParameter("codigo", codigo);
            return (Long) query.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Error al contar las concesiones: " + ex.toString());
        }
        return null;
    }
    
    @Override
    public Long comprobarExisteLicencia(String codigo) {
        try {
            Query query= em.createQuery("Select count(l) from LicenciaComercializacion l where l.codigo_arcom= :codigo");
            query.setParameter("codigo", codigo);
            return (Long) query.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Error al contar las licencias: " + ex.toString());
        }
        return null;
    }
    
    @Override
    public Long comprobarExistePlanta(String codigo) {
        try {
            Query query= em.createQuery("Select count(p) from PlantaBeneficio p where p.codigo_arcom= :codigo");
            query.setParameter("codigo", codigo);
            return (Long) query.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Error al contar las plantas: " + ex.toString());
        }
        return null;
    }
    
    @Override
    public DerechoMineroDTO buscarPorCodigo(String codigo) {
        try {
            //Query query= em.createQuery("Select c.nombre_concesion, c.documento_concesionario_principal, c.codigo_tipo_mineria, c.codigo_fase, c.plazo_concesion, c.codigo_provincia, c.codigo_canton, c.codigo_parroquia from ConcesionMinera c where c.codigo_arcom= :codigo");
            Query query= em.createQuery("Select c from ConcesionMinera c where c.codigo_arcom= :codigo");
            query.setParameter("codigo", codigo);
            
            return generarDTO(query.getResultList());
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    @Override
    public DerechoMineroDTO buscarPorCodigoLicencia(String codigo) {
        try {
            Query query= em.createQuery("Select l from LicenciaComercializacion l where l.codigo_arcom= :codigo");
            query.setParameter("codigo", codigo);
            
            return generarDTO(query.getResultList(), 0);
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    @Override
    public DerechoMineroDTO buscarPorCodigoPlanta(String codigo) {
        try {
            Query query= em.createQuery("Select p from PlantaBeneficio p where p.codigo_arcom= :codigo");
            query.setParameter("codigo", codigo);
            
            return generarDTO(query.getResultList(), 1);
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    @Override
    public Titular obtenerConcesionario(String documento) {
        
        PersonaNatural perNat= obtenerPersonaNatural(documento);
        if(perNat != null) {
            Titular tpn= new Titular(perNat);
            return tpn;
        }
        
        PersonaJuridica perJur= obtenerPersonaJuridica(documento);
        if(perJur != null) {
            Titular tpj= new Titular(perJur);
            return tpj;
        }
        
        return null;
    }
    
    @Override
    public String obtenerTipoMineria(Long codigo) {
        TipoMineria tm= null;
        String tipoMineria= VALOR_VACIO;
        try {
            Query query= em.createQuery("Select tipo from TipoMineria tipo where tipo.id= :codigo");
            query.setParameter("codigo", codigo);
            List tipos= query.getResultList();
            if(tipos.size()>0) {
                tm= (TipoMineria) tipos.get(0);
            }
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        
        if(tm!=null) {
            tipoMineria= tm.getNombre_tipo_mineria();
        }
        
        return tipoMineria;
    }
    
    @Override
    public String obtenerFase(Long codigo) {
        Fase f= null;
        String fase= VALOR_VACIO;
        try {
            Query query= em.createQuery("Select f from Fase f where f.id= :codigo");
            query.setParameter("codigo", codigo);
            List fases= query.getResultList();
            if(fases.size()>0) {
                f= (Fase) fases.get(0);
            }
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        
        if(f!=null) {
            fase= f.getNombre_fase();
        }
        
        return fase;
    }
    
    @Override
    public Localidad obtenerLocalidad(Long codigo) {
        Localidad localidad= null;
        try {
            Query query= em.createQuery("Select l from Localidad l where l.id= :codigo");
            query.setParameter("codigo", codigo);
            List localidades= query.getResultList();
            if(localidades.size()>0) {
                localidad= (Localidad) localidades.get(0);
            }
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        
        return localidad;
    }
    
    private DerechoMineroDTO generarDTO(List derechos) {
        DerechoMineroDTO dto= new DerechoMineroDTO();
        ConcesionMinera c= (ConcesionMinera) derechos.get(0);
        
        dto.setCodigoCatastral(c.getCodigo_arcom());
        dto.setNombreDerechoMinero(c.getNombre_concesion().toUpperCase());
        
        dto.setTitular(obtenerConcesionario(c.getDocumento_concesionario_principal()));
        if(dto.getTitular()==null) {
            System.out.println("No hay titular");
        }
        
        dto.setTipoDerechoMinero(obtenerTipoMineria(c.getCodigo_tipo_mineria()).toUpperCase());
        dto.setEstado(obtenerEstadoConcesion(c.getEstado_concesion()));
        dto.setFase(obtenerFase(c.getCodigo_fase()).toUpperCase());
        dto.setRegionalArcom(obtenerRegional(c.getCodigo_regional()));
        dto.setPlazo(c.getPlazo_concesion());
        dto.setSuperficie(obtenerSuperficieAreaMinera(c.getId()));
        dto.setFechaInscripcion(obtenerFecha(c.getFecha_inscribe()));
        
        Localidad provincia= obtenerLocalidad(c.getCodigo_provincia());
        if (provincia != null) {
            dto.setCodigoProvincia(provincia.getCodigo_internacional().toUpperCase());
            dto.setProvincia(provincia.getNombre().toUpperCase());
        } else {
            dto.setCodigoProvincia(VALOR_VACIO);
            dto.setProvincia(VALOR_VACIO);
        }
        
        Localidad canton= obtenerLocalidad(c.getCodigo_canton());
        if (canton != null) {
            dto.setCodigoCanton(canton.getCodigo_internacional().toUpperCase());
            dto.setCanton(canton.getNombre().toUpperCase());
        } else {
            dto.setCodigoCanton(VALOR_VACIO);
            dto.setCanton(VALOR_VACIO);
        }
        
        Localidad parroquia= obtenerLocalidad(c.getCodigo_parroquia());
        if (parroquia != null) {
            dto.setCodigoParroquia(parroquia.getCodigo_internacional().toUpperCase());
            dto.setParroquia(parroquia.getNombre().toUpperCase());
        } else {
            dto.setCodigoParroquia(VALOR_VACIO);
            dto.setParroquia(VALOR_VACIO);
        }
        
        return dto;
    }
    
    public String obtenerFecha(Date fecha) {
        if(fecha!=null) {
            return obtenerFechaConFormato("dd-MM-yyyy", fecha);
        }
        return VALOR_VACIO;
    }
    
    public String obtenerFechaConFormato(String formato, Date fecha) {
        SimpleDateFormat sdf= new SimpleDateFormat(formato);
        return sdf.format(fecha);
    }

    @Override
    public Double obtenerSuperficieAreaMinera(Long codigo) {
        AreaMinera am= null;
        Double superficie= (double) 0;
        try {
            Query query= em.createQuery("Select am from AreaMinera am where am.codigo_concesion= :codigo");
            query.setParameter("codigo", codigo);
            List areas= query.getResultList();
            if(areas.size()>0) {
                am= (AreaMinera) areas.get(0);
            }
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        
        if(am!=null) {
            superficie= am.getSuperficie_area_minera();
        }
        
        return superficie;
    }

    @Override
    public PersonaNatural obtenerPersonaNatural(String id) {
        return em.find(PersonaNatural.class, id);
    }

    @Override
    public PersonaJuridica obtenerPersonaJuridica(String id) {
        return em.find(PersonaJuridica.class, id);
    }

    @Override
    public String obtenerEstadoConcesion(Long id) {
        return em.find(CatalogoDetalle.class, id).getNombre();
    }

    @Override
    public String obtenerRegional(Long id) {
        if(id != null) {
            return em.find(Regional.class, id).getDescripcion_regional();
        }
        return VALOR_VACIO;
    }
    
    private DerechoMineroDTO generarDTO(List derechos, int tipo) {
        if(tipo==0) {
            return generarDTOLicencia((LicenciaComercializacion) derechos.get(0));
        }
        return generarDTOPlanta((PlantaBeneficio) derechos.get(0));
    }
    
    private DerechoMineroDTO generarDTOLicencia(LicenciaComercializacion licencia) {
        DerechoMineroDTO dto= new DerechoMineroDTO();
        dto.setCodigoCatastral(licencia.getCodigo_arcom());
        dto.setNombreDerechoMinero(licencia.getNombre().toUpperCase() + " " + licencia.getApellido().toUpperCase());
        
        dto.setTitular(obtenerConcesionario(licencia.getNumero_documento()));
        dto.setTipoDerechoMinero("LICENCIA DE COMERCIALIZACION");
        dto.setEstado(obtenerEstadoConcesion(licencia.getEstado_licencia()));
        dto.setFase(VALOR_VACIO);
        dto.setRegionalArcom(VALOR_VACIO);
        dto.setPlazo(new Long(36));
        dto.setSuperficie(new Double(0));
        dto.setFechaInscripcion(obtenerFecha(licencia.getFecha_inscribe()));
        
        Localidad provincia= obtenerLocalidad(licencia.getCodigo_provincia());
        if (provincia != null) {
            dto.setCodigoProvincia(provincia.getCodigo_internacional().toUpperCase());
            dto.setProvincia(provincia.getNombre().toUpperCase());
        } else {
            dto.setCodigoProvincia(VALOR_VACIO);
            dto.setProvincia(VALOR_VACIO);
        }
        
        Localidad canton= obtenerLocalidad(licencia.getCodigo_canton());
        if (canton != null) {
            dto.setCodigoCanton(canton.getCodigo_internacional().toUpperCase());
            dto.setCanton(canton.getNombre().toUpperCase());
        } else {
            dto.setCodigoCanton(VALOR_VACIO);
            dto.setCanton(VALOR_VACIO);
        }
        
        Localidad parroquia= obtenerLocalidad(licencia.getCodigo_parroquida());
        if (parroquia != null) {
            dto.setCodigoParroquia(parroquia.getCodigo_internacional().toUpperCase());
            dto.setParroquia(parroquia.getNombre().toUpperCase());
        } else {
            dto.setCodigoParroquia(VALOR_VACIO);
            dto.setParroquia(VALOR_VACIO);
        }
        
        return dto;
    }
    
    private DerechoMineroDTO generarDTOPlanta(PlantaBeneficio planta) {
        DerechoMineroDTO dto= new DerechoMineroDTO();
        dto.setCodigoCatastral(planta.getCodigo_arcom());
        dto.setNombreDerechoMinero(planta.getNombre_planta_beneficio().toUpperCase());
        
        dto.setTitular(obtenerConcesionario(planta.getNumero_documento_representante_legal()));
        dto.setTipoDerechoMinero("PLANTA DE BENEFICIO");
        dto.setEstado(obtenerEstadoConcesion(planta.getEstado_planta()));
        dto.setFase(VALOR_VACIO);
        dto.setRegionalArcom(VALOR_VACIO);
        dto.setPlazo(planta.getPlazo());
        dto.setSuperficie(new Double(0));
        dto.setFechaInscripcion(obtenerFecha(planta.getFecha_inscribe()));
        
        Localidad provincia= obtenerLocalidad(planta.getCodigo_provincia());
        if (provincia != null) {
            dto.setCodigoProvincia(provincia.getCodigo_internacional().toUpperCase());
            dto.setProvincia(provincia.getNombre().toUpperCase());
        } else {
            dto.setCodigoProvincia(VALOR_VACIO);
            dto.setProvincia(VALOR_VACIO);
        }
        
        Localidad canton= obtenerLocalidad(planta.getCodigo_canton());
        if (canton != null) {
            dto.setCodigoCanton(canton.getCodigo_internacional().toUpperCase());
            dto.setCanton(canton.getNombre().toUpperCase());
        } else {
            dto.setCodigoCanton(VALOR_VACIO);
            dto.setCanton(VALOR_VACIO);
        }
        
        Localidad parroquia= obtenerLocalidad(planta.getCodigo_parroquida());
        if (parroquia != null) {
            dto.setCodigoParroquia(parroquia.getCodigo_internacional().toUpperCase());
            dto.setParroquia(parroquia.getNombre().toUpperCase());
        } else {
            dto.setCodigoParroquia(VALOR_VACIO);
            dto.setParroquia(VALOR_VACIO);
        }
        
        return dto;
    }
}
