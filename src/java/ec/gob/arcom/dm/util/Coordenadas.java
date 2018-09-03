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
public class Coordenadas {
    private String numero;
    private String utmEste;
    private String utmNorte;  
    private String zona;
    private String tipoArea;
    private String tipoCoordenada;  

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUtmEste() {
        return utmEste;
    }

    public void setUtmEste(String utmEste) {
        this.utmEste = utmEste;
    }

    public String getUtmNorte() {
        return utmNorte;
    }

    public void setUtmNorte(String utmNorte) {
        this.utmNorte = utmNorte;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getTipoArea() {
        return tipoArea;
    }

    public void setTipoArea(String tipoArea) {
        this.tipoArea = tipoArea;
    }

    public String getTipoCoordenada() {
        return tipoCoordenada;
    }

    public void setTipoCoordenada(String tipoCoordenada) {
        this.tipoCoordenada = tipoCoordenada;
    }
        
}
