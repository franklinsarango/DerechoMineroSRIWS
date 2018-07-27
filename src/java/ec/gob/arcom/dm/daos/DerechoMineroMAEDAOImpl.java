/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.daos;

import ec.gob.arcom.dm.dtos.DerechoMineroDTO;
import ec.gob.arcom.dm.dtos.DerechoMineroMAEDTO;
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
import ec.gob.arcom.dm.util.Coordenadas;
import ec.gob.arcom.dm.util.DerechoMinero;
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
public class DerechoMineroMAEDAOImpl implements DerechoMineroMAEDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public DerechoMinero concesionPorCodigo(String codigoArcom) {
        try {
            String sql = "select\n"
                    + "cm.codigo_arcom,\n"
                    + "catmin.format_gmadigital(cm.nombre_concesion) as nombre_concesion,\n"
                    + "cm.documento_concesionario_principal AS titular_documento,\n"
                    + "case when p.apellido is null then catmin.format_gmadigital(p.nombre) else catmin.format_gmadigital(p.apellido || ' ' || p.nombre) end as titular_nombre,\n"
                    + "tm.nombre_tipo_mineria as tipo_solicitud,\n"
                    + "(select ciudad_regional from catmin.regional r, catmin.localidad_regional l where cm.codigo_provincia = l.codigo_localidad and r.codigo_regional = l.codigo_regional) as nombre_regional,\n"
                    + "(select case when est_.nombre in ('SUSPENDIDO','NO OTORGADO','EXTINGUIDO','CADUCADO','SOLICITUD EXPIRADA') then 'ARCHIVADA'\n"
                    + "                else est_.nombre end) as estado_concesion,\n"
                    + "cm.fecha_inscribe as fecha_inscripcion,\n"
                    + "cm.plazo_concesion,\n"
                    + "cm.numero_hectareas_concesion as superficie,\n"
                    + "COALESCE((select nombre_fase from catmin.fase where codigo_fase = cm.codigo_fase),'') as fase,\n"
                    + "catmin.format_gmadigital(prov.nombre) as provincia,\n"
                    + "(select codigo_internacional from catmin.localidad where cm.codigo_provincia = codigo_localidad) as codigo_provincia,\n"
                    + "(select catmin.format_gmadigital(nombre) from catmin.localidad where cm.codigo_canton = codigo_localidad) as canton,\n"
                    + "(select codigo_internacional from catmin.localidad where cm.codigo_canton = codigo_localidad) as codigo_canton,\n"
                    + "(select catmin.format_gmadigital(nombre) from catmin.localidad where cm.codigo_parroquia = codigo_localidad) as parroquia,\n"
                    + "(select codigo_internacional from catmin.localidad where cm.codigo_parroquia = codigo_localidad) as codigo_parroquia\n"
                    + "\n"
                    + "FROM catmin.concesion_minera cm, catmin.personas p, catmin.tipo_mineria tm,catmin.localidad prov, catmin.catalogo_detalle est_    \n"
                    + "where prov.codigo_localidad = cm.codigo_provincia\n"
                    + "and est_.codigo_catalogo_detalle = cm.estado_concesion\n"
                    + "and cm.codigo_tipo_mineria = tm.codigo_tipo_mineria\n"
                    + "and cm.documento_concesionario_principal = p.numero_documento\n"
                    + "and cm.codigo_arcom = '" + codigoArcom + "' \n"
                    + "and cm.estado_registro = true";

            System.out.println("sql concesion: " + sql);

            Query query = em.createNativeQuery(sql);

            List<Object[]> listaTmp = query.getResultList();
            
            DerechoMinero mae = new DerechoMinero();

            for (Object[] fila : listaTmp) {
                mae.setCodigoCatastral(fila[0] != null ? fila[0].toString() : null);
                mae.setNombreDerechoMinero(fila[1] != null ? fila[1].toString() : null);
                mae.setTitularDocumento(fila[2] != null ? fila[2].toString() : null);
                mae.setTitularNombre(fila[3] != null ? fila[3].toString() : null);
                mae.setTipoDerechoMinero(fila[4] != null ? fila[4].toString() : null);
                mae.setRegional(fila[5] != null ? fila[5].toString() : null);
                mae.setEstado(fila[6] != null ? fila[6].toString() : null);
                mae.setFechaInscripcion(fila[7] != null ? (fila[7].toString()) : null);
                mae.setPlazo(fila[8] != null ? Long.valueOf(fila[8].toString()) : null);
                mae.setSuperficie(fila[9] != null ? Double.valueOf(fila[9].toString()) : null);
                mae.setFase(fila[10] != null ? fila[10].toString() : null);
                mae.setProvincia(fila[11] != null ? fila[11].toString() : null);
                mae.setCodigoProvincia(fila[12] != null ? fila[12].toString() : null);
                mae.setCanton(fila[13] != null ? fila[13].toString() : null);
                mae.setCodigoCanton(fila[14] != null ? fila[14].toString() : null);
                mae.setParroquia(fila[15] != null ? fila[15].toString() : null);
                mae.setCodigoParroquia(fila[16] != null ? fila[16].toString() : null);
                
                
            }

            return mae;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    
    @Override
    public List<Coordenadas> coordenadasPorCodigo(String codigoArcom) {
        try {
            String sql = "select ca.numero_coordenada, ca.utm_este, ca.utm_norte \n"
                    + "from catmin.concesion_minera cm, catmin.area_minera a, catmin.coordenada_area ca\n"
                    + "where cm.codigo_arcom = '" + codigoArcom + "'\n"
                    + "and cm.codigo_concesion = a.codigo_concesion \n"
                    + "and cm.estado_registro = true\n"
                    + "and a.estado_registro = true\n"
                    + "and a.codigo_area_minera = ca.codigo_area\n"
                    + "and ca.estado_registro = true";

            System.out.println("sql concesion: " + sql);

            Query query = em.createNativeQuery(sql);

            List<Object[]> listaTmp = query.getResultList();
            
            List<Coordenadas> lista = new ArrayList();

            for (Object[] fila : listaTmp) {
                Coordenadas c = new Coordenadas();
                c.setNumero(fila[0] != null ? fila[0].toString() : null);
                c.setUtmEste(fila[1] != null ? fila[1].toString() : null);
                c.setUtmNorte(fila[2] != null ? fila[2].toString() : null);
                
                lista.add(c);
            }

            return lista;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    public String obtenerFecha(Date fecha) {
        if (fecha != null) {
            return obtenerFechaConFormato("dd-MM-yyyy", fecha);
        }
        return null;
    }

    public String obtenerFechaConFormato(String formato, Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(fecha);
    }

}
