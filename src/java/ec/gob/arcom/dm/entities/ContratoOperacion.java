/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author latinusprogramador
 */
@Entity
@Table(name = "contrato_operacion", schema = "catmin")
public class ContratoOperacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    @Column(name = "codigo_contrato_operacion")
    private Long codigoContratoOperacion;
    @Column(name = "sector")
    private String sector;
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Column(name = "campo_reservado_05")
    private String nombreContrato;
    @Column(name = "campo_reservado_04")
    private String apellidoContrato;
    @Column(name = "campo_reservado_03")
    private String campoReservado03;
    @Column(name = "campo_reservado_02")
    private String campoReservado02;
    @Column(name = "campo_reservado_01")
    private String campoReservado01;
    @Column(name = "estado_registro")
    private Boolean estadoRegistro;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = "usuario_creacion")
    private Long usuarioCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Column(name = "usuario_modificacion")
    private Long usuarioModificacion;
    @Column(name = "codigo_arcom")
    private String codigoArcom;
    @Column(name = "plazo")
    private Integer plazo;
    @JoinColumn(name = "codigo_area", referencedColumnName = "codigo_area_minera")
    @ManyToOne
    private AreaMinera codigoArea;
    @JoinColumn(name = "tipo_contrato", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle tipoContrato;
    @JoinColumn(name = "codigo_canton", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad codigoCanton;
    @JoinColumn(name = "codigo_provincia", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad codigoProvincia;
    @JoinColumn(name = "codigo_parroquia", referencedColumnName = "codigo_localidad")
    @ManyToOne
    private Localidad codigoParroquia;
    @JoinColumn(name = "codigo_concesion", referencedColumnName = "codigo_concesion")
    @ManyToOne
    private ConcesionMinera codigoConcesion;
    @JoinColumn(name = "estado_contrato", referencedColumnName = "codigo_catalogo_detalle")
    @ManyToOne
    private CatalogoDetalle estadoContrato;
    /*@JoinColumn(name = "codigo_informe", referencedColumnName = "codigo_informe")
    @ManyToOne
    private Informe codigoInforme;*/
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoContratoOperacion", fetch = FetchType.LAZY)
    private List<CoordenadaCota> coordenadaCotaList;*/
    @Column(name = "fecha_inscribe")
    @Temporal(TemporalType.DATE)
    private Date fechaInscribe;
    @Column(name = "destacar")
    private Boolean destacar;
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    @Column(name = "procurador_comun")
    private Boolean procurador_comun;

    public ContratoOperacion() {
    }

    public ContratoOperacion(Long codigoContratoOperacion) {
        this.codigoContratoOperacion = codigoContratoOperacion;
    }

    public Boolean getDestacar() {
        return destacar;
    }

    public void setDestacar(Boolean destacar) {
        this.destacar = destacar;
    }

    public Long getCodigoContratoOperacion() {
        return codigoContratoOperacion;
    }

    public void setCodigoContratoOperacion(Long codigoContratoOperacion) {
        this.codigoContratoOperacion = codigoContratoOperacion;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreContrato() {
        return nombreContrato;
    }

    public void setNombreContrato(String nombreContrato) {
        this.nombreContrato = nombreContrato;
    }

    public String getApellidoContrato() {
        return apellidoContrato;
    }

    public void setApellidoContrato(String apellidoContrato) {
        this.apellidoContrato = apellidoContrato;
    }

    public String getCampoReservado03() {
        return campoReservado03;
    }

    public void setCampoReservado03(String campoReservado03) {
        this.campoReservado03 = campoReservado03;
    }

    public String getCampoReservado02() {
        return campoReservado02;
    }

    public void setCampoReservado02(String campoReservado02) {
        this.campoReservado02 = campoReservado02;
    }

    public String getCampoReservado01() {
        return campoReservado01;
    }

    public void setCampoReservado01(String campoReservado01) {
        this.campoReservado01 = campoReservado01;
    }

    public Boolean getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(Boolean estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(Long usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Long getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(Long usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public AreaMinera getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(AreaMinera codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getCodigoArcom() {
        return codigoArcom;
    }

    public void setCodigoArcom(String codigoArcom) {
        this.codigoArcom = codigoArcom;
    }

    public CatalogoDetalle getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(CatalogoDetalle tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Localidad getCodigoCanton() {
        return codigoCanton;
    }

    public void setCodigoCanton(Localidad codigoCanton) {
        this.codigoCanton = codigoCanton;
    }

    public Localidad getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(Localidad codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public Localidad getCodigoParroquia() {
        return codigoParroquia;
    }

    public void setCodigoParroquia(Localidad codigoParroquia) {
        this.codigoParroquia = codigoParroquia;
    }

    public ConcesionMinera getCodigoConcesion() {
        return codigoConcesion;
    }

    public void setCodigoConcesion(ConcesionMinera codigoConcesion) {
        this.codigoConcesion = codigoConcesion;
    }

    public CatalogoDetalle getEstadoContrato() {
        return estadoContrato;
    }

    public void setEstadoContrato(CatalogoDetalle estadoContrato) {
        this.estadoContrato = estadoContrato;
    }

    /*public Informe getCodigoInforme() {
        return codigoInforme;
    }

    public void setCodigoInforme(Informe codigoInforme) {
        this.codigoInforme = codigoInforme;
    }*/

    public Date getFechaInscribe() {
        return fechaInscribe;
    }

    public void setFechaInscribe(Date fechaInscribe) {
        this.fechaInscribe = fechaInscribe;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }
    
    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Boolean getProcurador_comun() {
        return procurador_comun;
    }

    public void setProcurador_comun(Boolean procurador_comun) {
        this.procurador_comun = procurador_comun;
    }

    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoContratoOperacion != null ? codigoContratoOperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoOperacion)) {
            return false;
        }
        ContratoOperacion other = (ContratoOperacion) object;
        if ((this.codigoContratoOperacion == null && other.codigoContratoOperacion != null) || (this.codigoContratoOperacion != null && !this.codigoContratoOperacion.equals(other.codigoContratoOperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "ContratoOperacion{" + "codigoContratoOperacion=" + codigoContratoOperacion + ", sector=" + sector + ", numeroDocumento=" + numeroDocumento + ", nombreContrato=" + nombreContrato + ", apellidoContrato=" + apellidoContrato + ", campoReservado03=" + campoReservado03 + ", campoReservado02=" + campoReservado02 + ", campoReservado01=" + campoReservado01 + ", estadoRegistro=" + estadoRegistro + ", fechaCreacion=" + fechaCreacion + ", usuarioCreacion=" + usuarioCreacion + ", fechaModificacion=" + fechaModificacion + ", usuarioModificacion=" + usuarioModificacion + ", codigoArcom=" + codigoArcom + ", plazo=" + plazo + ", codigoArea=" + codigoArea + ", tipoContrato=" + tipoContrato + ", codigoCanton=" + codigoCanton + ", codigoProvincia=" + codigoProvincia + ", codigoParroquia=" + codigoParroquia + ", codigoConcesion=" + codigoConcesion + ", estadoContrato=" + estadoContrato + ", codigoInforme=" + codigoInforme + ", coordenadaCotaList=" + coordenadaCotaList + ", fechaInscribe=" + fechaInscribe + ", destacar=" + destacar + ", porcentaje=" + porcentaje + ", procurador_comun=" + procurador_comun + '}';
        return "ContratoOperacion{" + "codigoContratoOperacion=" + codigoContratoOperacion + ", sector=" + sector + ", numeroDocumento=" + numeroDocumento + ", nombreContrato=" + nombreContrato + ", apellidoContrato=" + apellidoContrato + ", campoReservado03=" + campoReservado03 + ", campoReservado02=" + campoReservado02 + ", campoReservado01=" + campoReservado01 + ", estadoRegistro=" + estadoRegistro + ", fechaCreacion=" + fechaCreacion + ", usuarioCreacion=" + usuarioCreacion + ", fechaModificacion=" + fechaModificacion + ", usuarioModificacion=" + usuarioModificacion + ", codigoArcom=" + codigoArcom + ", plazo=" + plazo + ", codigoArea=" + codigoArea + ", tipoContrato=" + tipoContrato + ", codigoCanton=" + codigoCanton + ", codigoProvincia=" + codigoProvincia + ", codigoParroquia=" + codigoParroquia + ", codigoConcesion=" + codigoConcesion + ", estadoContrato=" + estadoContrato + ", fechaInscribe=" + fechaInscribe + ", destacar=" + destacar + ", porcentaje=" + porcentaje + ", procurador_comun=" + procurador_comun + '}';
    } */
}
