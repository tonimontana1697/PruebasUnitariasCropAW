/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author PC31
 */
@FacesConverter("C_Estado")
public class ConverterEstado implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String Estado = "";
        Estado = (String) value;
        
        if(value != null){  
            switch(Estado){
                case "A":
                    Estado = "Activo";
                    break;
                case "I":
                    Estado = "Innactivo";
                    break;         
            }            
        }      
        return Estado;
    }
    
}
