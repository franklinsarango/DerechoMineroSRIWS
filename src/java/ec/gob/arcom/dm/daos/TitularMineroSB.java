/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.daos;

import ec.gob.arcom.dm.dtos.TitularMineroDTO;
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
import ec.gob.arcom.dm.util.ConcesionMineraWrapper;
import ec.gob.arcom.dm.util.Titular;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class TitularMineroSB implements TitularMineroSBLocal {
    @PersistenceContext
    private EntityManager em;
    
    private static final String VALOR_VACIO= " ";
    
    @Override
    public TitularMineroDTO buscarPorDocumento(String documento) {
        //buscar en tabla persona natural
        PersonaNatural pn= obtenerPersonaNatural(documento);
        if(pn!=null) {
            return generarDTO(pn);
        }
        
        //buscar en tabla persona juridica
        PersonaJuridica pj= obtenerPersonaJuridica(documento);
        if(pj!=null) {
            return generarDTO(pj);
        }
        
        return null;
    }
    
    private TitularMineroDTO generarDTO(PersonaNatural pn) {
        TitularMineroDTO dto= new TitularMineroDTO();
        dto.setDerechosMineros(new ArrayList<ConcesionMineraWrapper>());
        dto.setTitular(new Titular(pn));
        dto.setDerechosMineros(listarConcesiones(pn.getId()));
        //Buscar y cargar licencias de comercializacion
        List<ConcesionMineraWrapper> licencias= listarLicencias(pn.getId());
        for (ConcesionMineraWrapper licencia : licencias) {
            dto.getDerechosMineros().add(licencia);
        }
        //Buscar y cargar plantas de beneficio
        List<ConcesionMineraWrapper> plantas= listarPlantas(pn.getId());
        for (ConcesionMineraWrapper planta : plantas) {
            dto.getDerechosMineros().add(planta);
        }
        
        return dto;
    }
    
    private TitularMineroDTO generarDTO(PersonaJuridica pj) {
        TitularMineroDTO dto= new TitularMineroDTO();
        dto.setDerechosMineros(new ArrayList<ConcesionMineraWrapper>());
        dto.setTitular(new Titular(pj));
        dto.setDerechosMineros(listarConcesiones(pj.getId()));
        //Buscar y cargar licencias de comercializacion
        List<ConcesionMineraWrapper> licencias= listarLicencias(pj.getId());
        for (ConcesionMineraWrapper licencia : licencias) {
            dto.getDerechosMineros().add(licencia);
        }
        //Buscar y cargar plantas de beneficio
        List<ConcesionMineraWrapper> plantas= listarPlantas(pj.getId());
        for (ConcesionMineraWrapper planta : plantas) {
            dto.getDerechosMineros().add(planta);
        }
        
        return dto;
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
    public List<ConcesionMinera> obtenerConcesiones(String documento) {
        try {
            Query query= em.createQuery("Select c from ConcesionMinera c where c.documento_concesionario_principal= :documento");
            query.setParameter("documento", documento);
            return query.getResultList();
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    @Override
    public List<ConcesionMinera> obtenerConcesionesCotitular(String documento) {
        try {
            String tipoContrato = "CD";
            Long estadoContrato = 243L;
            Query query= em.createQuery("Select c.codigoConcesion from ContratoOperacion c where c.numeroDocumento= :documento and c.codigoArcom like :tipoContrato and "
                    + " c.estadoContrato.id= :estadoContrato and c.estadoRegistro = true");
            query.setParameter("documento", documento);
            query.setParameter("tipoContrato", "%" + tipoContrato + "%");
            query.setParameter("estadoContrato", estadoContrato);
            return query.getResultList();
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    @Override
    public List<ConcesionMinera> obtenerConcesionesContratoOperacion(String documento) {
        try {
            String tipoContrato = "CO";
            Long estadoContrato = 243L;
            Query query= em.createQuery("Select c.codigoConcesion from ContratoOperacion c where c.numeroDocumento= :documento and c.codigoArcom like :tipoContrato and "
                    + " c.estadoContrato.id= :estadoContrato and c.estadoRegistro = true");
            query.setParameter("documento", documento);
            query.setParameter("tipoContrato", "%" + tipoContrato + "%");
            query.setParameter("estadoContrato", estadoContrato);
            return query.getResultList();
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    private List<ConcesionMineraWrapper> listarConcesiones(String documento) {
        //LISTAR CONCESIONES POR TITULAR
        List<ConcesionMineraWrapper> wrappers= new ArrayList<ConcesionMineraWrapper>();
        List<ConcesionMinera> concesiones= obtenerConcesiones(documento);
        if(concesiones != null) {
            for (ConcesionMinera concesion : concesiones) {
                ConcesionMineraWrapper cmw = generarCMWrapper(concesion);
                wrappers.add(cmw);
            }
        }        
        
        //LISTAR CONCESIONES POR COTITULAR
        List<ConcesionMinera> concesionesCotitular= obtenerConcesionesCotitular(documento);
        if (concesionesCotitular != null) {
            for (ConcesionMinera concesion : concesionesCotitular) {
                ConcesionMineraWrapper cmw = generarCMWrapper(concesion);
                cmw.setTipoDerechoMinero(cmw.getTipoDerechoMinero() + " | COTITULAR");
                wrappers.add(cmw);
            }
        }
        
        //LISTAR CONCESIONES POR CONTRATO DE OPERACION
        List<ConcesionMinera> concesionesContratoOperacion= obtenerConcesionesContratoOperacion(documento);
        if (concesionesContratoOperacion != null) {
            for (ConcesionMinera concesion : concesionesContratoOperacion) {
                ConcesionMineraWrapper cmw = generarCMWrapper(concesion);
                cmw.setTipoDerechoMinero(cmw.getTipoDerechoMinero() + " | CONTRATO OPERACION");
                wrappers.add(cmw);
            }
        }
        return wrappers;
    }
    
    @Override
    public List<LicenciaComercializacion> obtenerLicencias(String documento) {
        try {
            Query query= em.createQuery("Select l from LicenciaComercializacion l where l.numero_documento= :documento");
            query.setParameter("documento", documento);
            return query.getResultList();
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    private List<ConcesionMineraWrapper> listarLicencias(String documento) {
        List<ConcesionMineraWrapper> wrappers= new ArrayList<ConcesionMineraWrapper>();
        List<LicenciaComercializacion> licencias= obtenerLicencias(documento);
        for (LicenciaComercializacion licencia : licencias) {
            ConcesionMineraWrapper cmw= generarCMWrapper(licencia);
            wrappers.add(cmw);
        }
        return wrappers;
    }
    
    @Override
    public List<PlantaBeneficio> obtenerPlantas(String documento) {
        try {
            Query query= em.createQuery("Select p from PlantaBeneficio p where p.numero_documento_representante_legal= :documento");
            query.setParameter("documento", documento);
            return query.getResultList();
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    private List<ConcesionMineraWrapper> listarPlantas(String documento) {
        List<ConcesionMineraWrapper> wrappers= new ArrayList<ConcesionMineraWrapper>();
        List<PlantaBeneficio> plantas= obtenerPlantas(documento);
        for (PlantaBeneficio planta : plantas) {
            ConcesionMineraWrapper cmw= generarCMWrapper(planta);
            wrappers.add(cmw);
        }
        return wrappers;
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
    
    public ConcesionMineraWrapper generarCMWrapper(ConcesionMinera concesion) {
        ConcesionMineraWrapper cmw= new ConcesionMineraWrapper();
        cmw.setCodigoCatastral(concesion.getCodigo_arcom());
        cmw.setNombreDerechoMinero(concesion.getNombre_concesion());
        cmw.setTipoDerechoMinero(obtenerTipoMineria(concesion.getCodigo_tipo_mineria()));
        cmw.setEstado(obtenerEstadoConcesion(concesion.getEstado_concesion()));
        cmw.setRegionalArcom(obtenerRegional(concesion.getCodigo_regional()));
        cmw.setFechaInscripcion(obtenerFecha(concesion.getFecha_inscribe()));
        cmw.setCodigoProvincia(obtenerLocalidad(concesion.getCodigo_provincia()).getCodigo_internacional());
        cmw.setProvincia(obtenerLocalidad(concesion.getCodigo_provincia()).getNombre());
        cmw.setCodigoCanton(obtenerLocalidad(concesion.getCodigo_canton()).getCodigo_internacional());
        cmw.setCanton(obtenerLocalidad(concesion.getCodigo_canton()).getNombre());
        cmw.setCodigoParroquia(obtenerLocalidad(concesion.getCodigo_parroquia()).getCodigo_internacional());
        cmw.setParroquia(obtenerLocalidad(concesion.getCodigo_parroquia()).getNombre());
        cmw.setPlazo(concesion.getPlazo_concesion());
        cmw.setSuperficie(obtenerSuperficieAreaMinera(concesion.getId()));
        cmw.setFase(obtenerFase(concesion.getCodigo_fase()));
        return cmw;
    }
    
    public ConcesionMineraWrapper generarCMWrapper(LicenciaComercializacion licencia) {
        ConcesionMineraWrapper cmw= new ConcesionMineraWrapper();
        cmw.setCodigoCatastral(licencia.getCodigo_arcom());
        cmw.setNombreDerechoMinero(licencia.getNombre() + " " + licencia.getApellido());
        cmw.setTipoDerechoMinero("LICENCIA DE COMERCIALIZACION");
        cmw.setEstado(obtenerEstadoConcesion(licencia.getEstado_licencia()));
        cmw.setRegionalArcom(VALOR_VACIO);
        cmw.setFechaInscripcion(obtenerFecha(licencia.getFecha_inscribe()));
        
        try {
            cmw.setCodigoProvincia(obtenerLocalidad(licencia.getCodigo_provincia()).getCodigo_internacional());
        } catch (Exception e) {
            cmw.setCodigoProvincia(VALOR_VACIO);
        }
        
        try {
            cmw.setProvincia(obtenerLocalidad(licencia.getCodigo_provincia()).getNombre());
        } catch (Exception e) {
            cmw.setProvincia(VALOR_VACIO);
        }
        
        try {
            cmw.setCodigoCanton(obtenerLocalidad(licencia.getCodigo_canton()).getCodigo_internacional());
        } catch (Exception e) {
            cmw.setCodigoCanton(VALOR_VACIO);
        }
        
        try {
            cmw.setCanton(obtenerLocalidad(licencia.getCodigo_canton()).getNombre());
        } catch (Exception e) {
            cmw.setCanton(VALOR_VACIO);
        }
        
        try {
            cmw.setCodigoParroquia(obtenerLocalidad(licencia.getCodigo_parroquida()).getCodigo_internacional());
        } catch (Exception e) {
            cmw.setCodigoParroquia(VALOR_VACIO);
        }
        
        try {
            cmw.setParroquia(obtenerLocalidad(licencia.getCodigo_parroquida()).getNombre());
        } catch (Exception e) {
            cmw.setParroquia(VALOR_VACIO);
        }
        
        cmw.setPlazo(new Long(36));
        cmw.setSuperficie(new Double(0));
        cmw.setFase(VALOR_VACIO);
        return cmw;
    }
    
    public ConcesionMineraWrapper generarCMWrapper(PlantaBeneficio planta) {
        ConcesionMineraWrapper cmw= new ConcesionMineraWrapper();
        cmw.setCodigoCatastral(planta.getCodigo_arcom());
        cmw.setNombreDerechoMinero(planta.getNombre_planta_beneficio());
        cmw.setTipoDerechoMinero("PLANTA DE BENEFICIO");
        cmw.setEstado(obtenerEstadoConcesion(planta.getEstado_planta()));
        cmw.setRegionalArcom(VALOR_VACIO);
        cmw.setFechaInscripcion(obtenerFecha(planta.getFecha_inscribe()));
        
        try {
            cmw.setCodigoProvincia(obtenerLocalidad(planta.getCodigo_provincia()).getCodigo_internacional());
        } catch(Exception ex) {
            cmw.setCodigoProvincia(VALOR_VACIO);
        }
        
        try {
            cmw.setProvincia(obtenerLocalidad(planta.getCodigo_provincia()).getNombre());
        } catch (Exception e) {
            cmw.setProvincia(VALOR_VACIO);
        }
        
        try {
            cmw.setCodigoCanton(obtenerLocalidad(planta.getCodigo_canton()).getCodigo_internacional());
        } catch (Exception e) {
            cmw.setCodigoCanton(VALOR_VACIO);
        }
        
        try {
            cmw.setCanton(obtenerLocalidad(planta.getCodigo_canton()).getNombre());
        } catch (Exception e) {
            cmw.setCanton(VALOR_VACIO);
        }
        
        try {
            cmw.setCodigoParroquia(obtenerLocalidad(planta.getCodigo_parroquida()).getCodigo_internacional());
        } catch (Exception e) {
            cmw.setCodigoParroquia(VALOR_VACIO);
        }
        
        try {
            cmw.setParroquia(obtenerLocalidad(planta.getCodigo_parroquida()).getNombre());
        } catch (Exception e) {
            cmw.setParroquia(VALOR_VACIO);
        }
        
        cmw.setPlazo(planta.getPlazo());
        cmw.setSuperficie(new Double(0));
        cmw.setFase(VALOR_VACIO);
        return cmw;
    }
}
