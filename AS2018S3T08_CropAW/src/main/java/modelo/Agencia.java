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
public class Agencia {
    
    @NotNull
    private int idagen;
    
    @NotNull
    @Size(min = 1, max = 100)
    private String nomagen;
    
    @NotNull
    @Size(min = 1, max = 1)
    private String estaage;

    public int getIdagen() {
        return idagen;
    }

    public void setIdagen(int idagen) {
        this.idagen = idagen;
    }

    public String getNomagen() {
        return nomagen;
    }

    public void setNomagen(String nomagen) {
        this.nomagen = nomagen;
    }

    public String getEstaage() {
        return estaage;
    }

    public void setEstaage(String estaage) {
        this.estaage = estaage;
    }
    
}
