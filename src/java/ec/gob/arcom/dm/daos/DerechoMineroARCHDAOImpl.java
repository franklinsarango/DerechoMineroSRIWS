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
public class DerechoMineroARCHDAOImpl implements DerechoMineroARCHDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<DerechoMinero> concesionPorDocumento(String documento) {
        try {
            String sql = "select\n"
                    + "cm.codigo_arcom,\n"
                    + "catmin.format_gmadigital(cm.nombre_concesion) as nombre_concesion,\n"
                    + "cm.documento_concesionario_principal AS titular_documento,\n"
                    + "case when p.apellido is null then catmin.format_gmadigital(p.nombre) else catmin.format_gmadigital(p.apellido || ' ' || p.nombre) end as titular_nombre,\n"
                    + "p.documento_representante_legal as rep_legal_documento,\n"
                    + "case when p.apellido_representante_legal is null then catmin.format_gmadigital(p.nombre_representante_legal) else catmin.format_gmadigital(p.apellido_representante_legal || ' ' || p.nombre_representante_legal) end as rep_legal_nombre,\n"
                    + "tm.nombre_tipo_mineria as tipo_solicitud,\n"
                    + "(select ciudad_regional from catmin.regional r, catmin.localidad_regional l where cm.codigo_provincia = l.codigo_localidad and r.codigo_regional = l.codigo_regional) as nombre_regional,\n"
                    + "(select case when est_.nombre in ('SUSPENDIDO','NO OTORGADO','EXTINGUIDO','CADUCADO','SOLICITUD EXPIRADA') then 'ARCHIVADA'\n"
                    + "else est_.nombre end) as estado_concesion,\n"
                    + "cm.fecha_inscribe as fecha_inscripcion,\n"
                    + "cm.plazo_concesion,\n"
                    + "(select catmin.format_gmadigital(nombre) from catmin.catalogo_detalle where codigo_catalogo_detalle = cm.codigo_material_interes) as material_interes,        \n"
                    + "(select catmin.format_gmadigital(nombre) from catmin.catalogo where codigo_catalogo = cm.codigo_tipo_material) as mineral,\n"
                    + "cm.numero_hectareas_concesion as superficie,\n"
                    + "(select upper(nombre) from catmin.regimen where codigo_regimen = cm.codigo_regimen) as regimen,\n"
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
                    + "and cm.estado_concesion = 243\n"
                    + "and cm.documento_concesionario_principal = '" + documento + "' \n"
                    + "and cm.estado_registro = true";

            System.out.println("sql concesion: " + sql);

            Query query = em.createNativeQuery(sql);

            List<Object[]> listaTmp = query.getResultList();
            
            List<DerechoMinero> lista = new ArrayList();

            for (Object[] fila : listaTmp) {
                DerechoMinero mae = new DerechoMinero();
                mae.setCodigoCatastral(fila[0] != null ? fila[0].toString() : null);
                mae.setNombreDerechoMinero(fila[1] != null ? fila[1].toString() : null);
                mae.setTitularDocumento(fila[2] != null ? fila[2].toString() : null);
                mae.setTitularNombre(fila[3] != null ? fila[3].toString() : null);
                mae.setRepresentanteLegalDocumento(fila[4] != null ? fila[4].toString() : null);
                mae.setRepresentanteLegalNombre(fila[5] != null ? fila[5].toString() : null);
                mae.setTipoDerechoMinero(fila[6] != null ? fila[6].toString() : null);
                //mae.setRegional(fila[7] != null ? fila[7].toString() : null);
                mae.setEstado(fila[8] != null ? fila[8].toString() : null);
                mae.setFechaInscripcion(fila[9] != null ? (fila[9].toString()) : null);
                mae.setPlazo(fila[10] != null ? Long.valueOf(fila[10].toString()) : null);
                //mae.setMaterialInteres(fila[11] != null ? fila[11].toString() : null);
                //mae.setMineral(fila[12] != null ? fila[12].toString() : null);
                //mae.setSuperficie(fila[13] != null ? Double.valueOf(fila[13].toString()) : null);
                mae.setRegimen(fila[14] != null ? fila[14].toString() : null);
                mae.setFase(fila[15] != null ? fila[15].toString() : null);
                mae.setProvincia(fila[16] != null ? fila[16].toString() : null);
                //mae.setCodigoProvincia(fila[17] != null ? fila[17].toString() : null);
                mae.setCanton(fila[18] != null ? fila[18].toString() : null);
                //mae.setCodigoCanton(fila[19] != null ? fila[19].toString() : null);
                mae.setParroquia(fila[20] != null ? fila[20].toString() : null);
                //mae.setCodigoParroquia(fila[21] != null ? fila[21].toString() : null);
                //mae.setMaterialRecuperado(null);    //SOLO APLICA PARA PLANTAS DE BENEFICIO 
                lista.add(mae);
            }

            return lista;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    @Override
    public List<DerechoMinero> plantaBeneficioPorDocumento(String documento) {
        try {
            String sql = "select \n"
                    + "pb.codigo_arcom,\n"
                    + "pb.nombre_planta_beneficio,\n"
                    + "pb.numero_documento_representante_legal as titular_documento,\n"
                    + "case when p.apellido is null then catmin.format_gmadigital(p.nombre) else catmin.format_gmadigital(p.apellido || ' ' || p.nombre) end as titular_nombre,\n"
                    + "p.documento_representante_legal,\n"
                    + "case when p.apellido_representante_legal is null then catmin.format_gmadigital(p.nombre_representante_legal) \n"
                    + "        else catmin.format_gmadigital(p.apellido_representante_legal || ' ' || p.nombre_representante_legal) end as rep_legal_nombre,\n"
                    + "(select nombre_tipo_mineria from catmin.tipo_mineria where nemonico_tipo_mineria = 'PLANBEN') as tipo_derecho_minero, \n"
                    + "(select nombre_regional from catmin.regional r, catmin.localidad_regional l where pb.codigo_provincia = l.codigo_localidad and r.codigo_regional = l.codigo_regional) as nombre_regional,\n"
                    + "(select cd.nombre from catmin.catalogo_detalle cd where cd.codigo_catalogo_detalle = pb.estado_planta) as estado,\n"
                    + "pb.fecha_inscribe,\n"
                    + "pb.plazo,\n"
                    + "(select codigo_internacional from catmin.localidad where pb.codigo_provincia = codigo_localidad) as codigo_provincia,\n"
                    + "(select l.nombre from catmin.localidad l where l.codigo_localidad = pb.codigo_provincia) as provincia,\n"
                    + "(select codigo_internacional from catmin.localidad where pb.codigo_canton = codigo_localidad) as codigo_canton,\n"
                    + "(select l.nombre from catmin.localidad l where l.codigo_localidad = pb.codigo_canton) as canton,\n"
                    + "(select codigo_internacional from catmin.localidad where pb.codigo_parroquia = codigo_localidad) as codigo_parroquia,\n"
                    + "(select l.nombre from catmin.localidad l where l.codigo_localidad = pb.codigo_parroquia) as parroquia\n"
                    + "\n"
                    + "from catmin.planta_beneficio pb, catmin.personas p\n"
                    + "where pb.numero_documento_representante_legal = p.numero_documento\n"
                    + "and pb.estado_planta = 243\n"
                    + "and pb.codigo_arcom not like '%PB%'\n"
                    + "and pb.numero_documento_representante_legal = '" + documento + "' \n"
                    + "and pb.estado_registro = true";                    

            System.out.println("sql concesion: " + sql);

            Query query = em.createNativeQuery(sql);

            List<Object[]> listaTmp = query.getResultList();
            
            List<DerechoMinero> lista = new ArrayList();

            for (Object[] fila : listaTmp) {
                DerechoMinero mae = new DerechoMinero();
                mae.setCodigoCatastral(fila[0] != null ? fila[0].toString() : null);
                mae.setNombreDerechoMinero(fila[1] != null ? fila[1].toString() : null);
                mae.setTitularDocumento(fila[2] != null ? fila[2].toString() : null);
                mae.setTitularNombre(fila[3] != null ? fila[3].toString() : null);
                mae.setRepresentanteLegalDocumento(fila[4] != null ? fila[4].toString() : null);
                mae.setRepresentanteLegalNombre(fila[5] != null ? fila[5].toString() : null);
                mae.setTipoDerechoMinero(fila[6] != null ? fila[6].toString() : null);
                //mae.setRegional(fila[7] != null ? fila[7].toString() : null);
                mae.setEstado(fila[8] != null ? fila[8].toString() : null);
                mae.setFechaInscripcion(fila[9] != null ? (fila[9].toString()) : null);
                mae.setPlazo(fila[10] != null ? Long.valueOf(fila[10].toString()) : null);                
                mae.setProvincia(fila[11] != null ? fila[11].toString() : null);
                //mae.setCodigoProvincia(fila[12] != null ? fila[12].toString() : null);
                mae.setCanton(fila[13] != null ? fila[13].toString() : null);
                //mae.setCodigoCanton(fila[14] != null ? fila[14].toString() : null);
                mae.setParroquia(fila[15] != null ? fila[15].toString() : null);
                //mae.setCodigoParroquia(fila[16] != null ? fila[16].toString() : null);
                //mae.setSuperficie(Double.valueOf("-1"));    //SE ENVIA -1 HASTA TENER EL CAMPO EN EL SGM                
                //mae.setMaterialRecuperado("-1");            //SE ENVIA -1 HASTA TENER EL CAMPO EN EL SGM               
                //mae.setMaterialInteres(null);   //NO EXISTE EN PLANTA DE BENEFICIO
                //mae.setMineral(null);           //NO EXISTE EN PLANTA DE BENEFICIO
                mae.setRegimen(null);           //NO EXISTE EN PLANTA DE BENEFICIO
                mae.setFase(null);              //NO EXISTE EN PLANTA DE BENEFICIO
                lista.add(mae);
            }

            return lista;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    @Override
    public List<DerechoMinero> concesionesCotitular(String documento) {
        try {
            String sql = "select\n"
                    + "cm.codigo_arcom,\n"
                    + "catmin.format_gmadigital(cm.nombre_concesion) as nombre_concesion,\n"
                    + "cm.documento_concesionario_principal AS titular_documento,\n"
                    + "case when p.apellido is null then catmin.format_gmadigital(p.nombre) else catmin.format_gmadigital(p.apellido || ' ' || p.nombre) end as titular_nombre,\n"
                    + "p.documento_representante_legal as rep_legal_documento,\n"
                    + "case when p.apellido_representante_legal is null then catmin.format_gmadigital(p.nombre_representante_legal) else catmin.format_gmadigital(p.apellido_representante_legal || ' ' || p.nombre_representante_legal) end as rep_legal_nombre,\n"
                    + "tm.nombre_tipo_mineria  || ' | COTITULAR' as tipo_solicitud,\n"
                    + "(select ciudad_regional from catmin.regional r, catmin.localidad_regional l where cm.codigo_provincia = l.codigo_localidad and r.codigo_regional = l.codigo_regional) as nombre_regional,\n"
                    + "(select case when est_.nombre in ('SUSPENDIDO','NO OTORGADO','EXTINGUIDO','CADUCADO','SOLICITUD EXPIRADA') then 'ARCHIVADA'\n"
                    + "else est_.nombre end) as estado_concesion,\n"
                    + "cm.fecha_inscribe as fecha_inscripcion,\n"
                    + "cm.plazo_concesion,\n"
                    + "(select catmin.format_gmadigital(nombre) from catmin.catalogo_detalle where codigo_catalogo_detalle = cm.codigo_material_interes) as material_interes,        \n"
                    + "(select catmin.format_gmadigital(nombre) from catmin.catalogo where codigo_catalogo = cm.codigo_tipo_material) as mineral,\n"
                    + "cm.numero_hectareas_concesion as superficie,\n"
                    + "(select upper(nombre) from catmin.regimen where codigo_regimen = cm.codigo_regimen) as regimen,\n"
                    + "COALESCE((select nombre_fase from catmin.fase where codigo_fase = cm.codigo_fase),'') as fase,\n"
                    + "catmin.format_gmadigital(prov.nombre) as provincia,\n"
                    + "(select codigo_internacional from catmin.localidad where cm.codigo_provincia = codigo_localidad) as codigo_provincia,\n"
                    + "(select catmin.format_gmadigital(nombre) from catmin.localidad where cm.codigo_canton = codigo_localidad) as canton,\n"
                    + "(select codigo_internacional from catmin.localidad where cm.codigo_canton = codigo_localidad) as codigo_canton,\n"
                    + "(select catmin.format_gmadigital(nombre) from catmin.localidad where cm.codigo_parroquia = codigo_localidad) as parroquia,\n"
                    + "(select codigo_internacional from catmin.localidad where cm.codigo_parroquia = codigo_localidad) as codigo_parroquia\n"                    
                    + "\n"
                    + "FROM catmin.concesion_minera cm, catmin.personas p, catmin.tipo_mineria tm,catmin.localidad prov, catmin.catalogo_detalle est_, catmin.contrato_operacion co    \n"
                    + "where prov.codigo_localidad = cm.codigo_provincia\n"
                    + "and est_.codigo_catalogo_detalle = cm.estado_concesion\n"
                    + "and cm.codigo_tipo_mineria = tm.codigo_tipo_mineria\n"
                    + "and cm.documento_concesionario_principal = p.numero_documento\n"
                    + "and cm.estado_registro = true\n"
                    + "and cm.codigo_concesion = co.codigo_concesion\n"
                    + "and co.estado_registro = true\n"
                    + "and co.numero_documento = '" + documento + "' \n"
                    + "and co.codigo_arcom like '%CD%' \n"
                    + "and co.estado_contrato = 243";

            System.out.println("sql concesion: " + sql);

            Query query = em.createNativeQuery(sql);

            List<Object[]> listaTmp = query.getResultList();
            
            List<DerechoMinero> lista = new ArrayList();

            for (Object[] fila : listaTmp) {
                DerechoMinero mae = new DerechoMinero();
                mae.setCodigoCatastral(fila[0] != null ? fila[0].toString() : null);
                mae.setNombreDerechoMinero(fila[1] != null ? fila[1].toString() : null);
                mae.setTitularDocumento(fila[2] != null ? fila[2].toString() : null);
                mae.setTitularNombre(fila[3] != null ? fila[3].toString() : null);
                mae.setRepresentanteLegalDocumento(fila[4] != null ? fila[4].toString() : null);
                mae.setRepresentanteLegalNombre(fila[5] != null ? fila[5].toString() : null);
                mae.setTipoDerechoMinero(fila[6] != null ? fila[6].toString() : null);
                //mae.setRegional(fila[7] != null ? fila[7].toString() : null);
                mae.setEstado(fila[8] != null ? fila[8].toString() : null);
                mae.setFechaInscripcion(fila[9] != null ? (fila[9].toString()) : null);
                mae.setPlazo(fila[10] != null ? Long.valueOf(fila[10].toString()) : null);
                //mae.setMaterialInteres(fila[11] != null ? fila[11].toString() : null);
                //mae.setMineral(fila[12] != null ? fila[12].toString() : null);
                //mae.setSuperficie(fila[13] != null ? Double.valueOf(fila[13].toString()) : null);
                mae.setRegimen(fila[14] != null ? fila[14].toString() : null);
                mae.setFase(fila[15] != null ? fila[15].toString() : null);
                mae.setProvincia(fila[16] != null ? fila[16].toString() : null);
                //mae.setCodigoProvincia(fila[17] != null ? fila[17].toString() : null);
                mae.setCanton(fila[18] != null ? fila[18].toString() : null);
                //mae.setCodigoCanton(fila[19] != null ? fila[19].toString() : null);
                mae.setParroquia(fila[20] != null ? fila[20].toString() : null);
                //mae.setCodigoParroquia(fila[21] != null ? fila[21].toString() : null);
                //mae.setMaterialRecuperado(null);    //SOLO APLICA PARA PLANTAS DE BENEFICIO  
                
                lista.add(mae);
            }

            return lista;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }
    
    @Override
    public List<DerechoMinero> concesionesContratoOperacion(String documento) {
        try {
            String sql = "select\n"
                    + "cm.codigo_arcom,\n"
                    + "catmin.format_gmadigital(cm.nombre_concesion) as nombre_concesion,\n"
                    + "cm.documento_concesionario_principal AS titular_documento,\n"
                    + "case when p.apellido is null then catmin.format_gmadigital(p.nombre) else catmin.format_gmadigital(p.apellido || ' ' || p.nombre) end as titular_nombre,\n"
                    + "p.documento_representante_legal as rep_legal_documento,\n"
                    + "case when p.apellido_representante_legal is null then catmin.format_gmadigital(p.nombre_representante_legal) else catmin.format_gmadigital(p.apellido_representante_legal || ' ' || p.nombre_representante_legal) end as rep_legal_nombre,\n"
                    + "tm.nombre_tipo_mineria || ' | CONTRATO OPERACION' as tipo_solicitud,\n"
                    + "(select ciudad_regional from catmin.regional r, catmin.localidad_regional l where cm.codigo_provincia = l.codigo_localidad and r.codigo_regional = l.codigo_regional) as nombre_regional,\n"
                    + "(select case when est_.nombre in ('SUSPENDIDO','NO OTORGADO','EXTINGUIDO','CADUCADO','SOLICITUD EXPIRADA') then 'ARCHIVADA'\n"
                    + "else est_.nombre end) as estado_concesion,\n"
                    + "cm.fecha_inscribe as fecha_inscripcion,\n"
                    + "cm.plazo_concesion,\n"
                    + "(select catmin.format_gmadigital(nombre) from catmin.catalogo_detalle where codigo_catalogo_detalle = cm.codigo_material_interes) as material_interes,        \n"
                    + "(select catmin.format_gmadigital(nombre) from catmin.catalogo where codigo_catalogo = cm.codigo_tipo_material) as mineral,\n"
                    + "cm.numero_hectareas_concesion as superficie,\n"
                    + "(select upper(nombre) from catmin.regimen where codigo_regimen = cm.codigo_regimen) as regimen,\n"
                    + "COALESCE((select nombre_fase from catmin.fase where codigo_fase = cm.codigo_fase),'') as fase,\n"
                    + "catmin.format_gmadigital(prov.nombre) as provincia,\n"
                    + "(select codigo_internacional from catmin.localidad where cm.codigo_provincia = codigo_localidad) as codigo_provincia,\n"
                    + "(select catmin.format_gmadigital(nombre) from catmin.localidad where cm.codigo_canton = codigo_localidad) as canton,\n"
                    + "(select codigo_internacional from catmin.localidad where cm.codigo_canton = codigo_localidad) as codigo_canton,\n"
                    + "(select catmin.format_gmadigital(nombre) from catmin.localidad where cm.codigo_parroquia = codigo_localidad) as parroquia,\n"
                    + "(select codigo_internacional from catmin.localidad where cm.codigo_parroquia = codigo_localidad) as codigo_parroquia\n"                    
                    + "\n"
                    + "FROM catmin.concesion_minera cm, catmin.personas p, catmin.tipo_mineria tm,catmin.localidad prov, catmin.catalogo_detalle est_, catmin.contrato_operacion co    \n"
                    + "where prov.codigo_localidad = cm.codigo_provincia\n"
                    + "and est_.codigo_catalogo_detalle = cm.estado_concesion\n"
                    + "and cm.codigo_tipo_mineria = tm.codigo_tipo_mineria\n"
                    + "and cm.documento_concesionario_principal = p.numero_documento\n"
                    + "and cm.estado_registro = true\n"
                    + "and cm.codigo_concesion = co.codigo_concesion\n"
                    + "and co.estado_registro = true\n"
                    + "and co.numero_documento = '" + documento + "' \n"
                    + "and co.codigo_arcom like '%CO%' \n"
                    + "and co.estado_contrato = 243";

            System.out.println("sql concesion: " + sql);

            Query query = em.createNativeQuery(sql);

            List<Object[]> listaTmp = query.getResultList();
            
            List<DerechoMinero> lista = new ArrayList();

            for (Object[] fila : listaTmp) {
                DerechoMinero mae = new DerechoMinero();
                mae.setCodigoCatastral(fila[0] != null ? fila[0].toString() : null);
                mae.setNombreDerechoMinero(fila[1] != null ? fila[1].toString() : null);
                mae.setTitularDocumento(fila[2] != null ? fila[2].toString() : null);
                mae.setTitularNombre(fila[3] != null ? fila[3].toString() : null);
                mae.setRepresentanteLegalDocumento(fila[4] != null ? fila[4].toString() : null);
                mae.setRepresentanteLegalNombre(fila[5] != null ? fila[5].toString() : null);
                mae.setTipoDerechoMinero(fila[6] != null ? fila[6].toString() : null);
                //mae.setRegional(fila[7] != null ? fila[7].toString() : null);
                mae.setEstado(fila[8] != null ? fila[8].toString() : null);
                mae.setFechaInscripcion(fila[9] != null ? (fila[9].toString()) : null);
                mae.setPlazo(fila[10] != null ? Long.valueOf(fila[10].toString()) : null);
                //mae.setMaterialInteres(fila[11] != null ? fila[11].toString() : null);
                //mae.setMineral(fila[12] != null ? fila[12].toString() : null);
                //mae.setSuperficie(fila[13] != null ? Double.valueOf(fila[13].toString()) : null);
                mae.setRegimen(fila[14] != null ? fila[14].toString() : null);
                mae.setFase(fila[15] != null ? fila[15].toString() : null);
                mae.setProvincia(fila[16] != null ? fila[16].toString() : null);
                //mae.setCodigoProvincia(fila[17] != null ? fila[17].toString() : null);
                mae.setCanton(fila[18] != null ? fila[18].toString() : null);
                //mae.setCodigoCanton(fila[19] != null ? fila[19].toString() : null);
                mae.setParroquia(fila[20] != null ? fila[20].toString() : null);
                //mae.setCodigoParroquia(fila[21] != null ? fila[21].toString() : null);
                //mae.setMaterialRecuperado(null);    //SOLO APLICA PARA PLANTAS DE BENEFICIO    
                lista.add(mae);
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
