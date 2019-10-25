/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author PC04
 */
public class Valle {

    private Long idvall;
    private String nomvall;
    private String estavall;

    public Long getIdvall() {
        return idvall;
    }

    public void setIdvall(Long idvall) {
        this.idvall = idvall;
    }

    public String getNomvall() {
        return nomvall;
    }

    public void setNomvall(String nomvall) {
        this.nomvall = nomvall;
    }

    public String getEstavall() {
        return estavall;
    }

    public void setEstavall(String estavall) {
        this.estavall = estavall;
    }

}
