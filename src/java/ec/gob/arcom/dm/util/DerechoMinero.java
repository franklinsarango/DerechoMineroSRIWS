/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.dm.util;


/**
 *
 * @author mejiaw
 */
public class DerechoMinero {
    private String codigoCatastral;
    private String nombreDerechoMinero;
    private String titularDocumento;
    private String titularNombre;
    private String tipoDerechoMinero;
    private String regional;
    private String estado;
    private String fechaInscripcion;
    private Long plazo;
    private Double superficie;
    private String fase;
    private String provincia;   
    private String codigoProvincia;
    private String canton;
    private String codigoCanton;
    private String parroquia;
    private String codigoParroquia;
    
    public String getNombreDerechoMinero() {
        return nombreDerechoMinero;
    }

    public void setNombreDerechoMinero(String nombreDerechoMinero) {
        this.nombreDerechoMinero = nombreDerechoMinero;
    }

    public String getCodigoCatastral() {
        return codigoCatastral;
    }

    public void setCodigoCatastral(String codigoCatastral) {
        this.codigoCatastral = codigoCatastral;
    }

    public String getTitularDocumento() {
        return titularDocumento;
    }

    public void setTitularDocumento(String titularDocumento) {
        this.titularDocumento = titularDocumento;
    }

    public String getTitularNombre() {
        return titularNombre;
    }

    public void setTitularNombre(String titularNombre) {
        this.titularNombre = titularNombre;
    }

    public String getTipoDerechoMinero() {
        return tipoDerechoMinero;
    }

    public void setTipoDerechoMinero(String tipoDerechoMinero) {
        this.tipoDerechoMinero = tipoDerechoMinero;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getPlazo() {
        return plazo;
    }

    public void setPlazo(Long plazo) {
        this.plazo = plazo;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(String codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodigoCanton() {
        return codigoCanton;
    }

    public void setCodigoCanton(String codigoCanton) {
        this.codigoCanton = codigoCanton;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getCodigoParroquia() {
        return codigoParroquia;
    }

    public void setCodigoParroquia(String codigoParroquia) {
        this.codigoParroquia = codigoParroquia;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }
}
