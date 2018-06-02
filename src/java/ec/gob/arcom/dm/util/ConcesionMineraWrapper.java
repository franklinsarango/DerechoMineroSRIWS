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
public class ConcesionMineraWrapper {
    private String codigoCatastral;
    private String nombreDerechoMinero;
    private String tipoDerechoMinero;
    private String estado;
    private Long plazo;
    private Double superficie;
    private String fase;
    private String regionalArcom;
    private String fechaInscripcion;
    private String codigoProvincia;
    private String provincia;
    private String codigoCanton;
    private String canton;
    private String codigoParroquia;
    private String parroquia;
    
    public String getCodigoCatastral() {
        return codigoCatastral;
    }

    public void setCodigoCatastral(String codigoCatastral) {
        this.codigoCatastral = codigoCatastral;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }

    public String getNombreDerechoMinero() {
        return nombreDerechoMinero;
    }

    public void setNombreDerechoMinero(String nombreDerechoMinero) {
        this.nombreDerechoMinero = nombreDerechoMinero;
    }

    public String getTipoDerechoMinero() {
        return tipoDerechoMinero;
    }

    public void setTipoDerechoMinero(String tipoDerechoMinero) {
        this.tipoDerechoMinero = tipoDerechoMinero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getRegionalArcom() {
        return regionalArcom;
    }

    public void setRegionalArcom(String regionalArcom) {
        this.regionalArcom = regionalArcom;
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

    public Long getPlazo() {
        return plazo;
    }

    public void setPlazo(Long plazo) {
        this.plazo = plazo;
    }
}
