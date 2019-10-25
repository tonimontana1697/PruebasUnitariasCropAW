/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.validate;

import dao.PersonalImpl;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

/**
 *
 * @author jq_00
 */
@Named("validateDni")
@RequestScoped
public class ValidateDni implements Validator {

    private PersonalImpl ejbFacade = new PersonalImpl();
    
    public ValidateDni() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        try {
            String dniper = (String) value;
            boolean a = getFacade().countDni(dniper);
            String msg = "Dni Existente";
            if (a) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
            }
        } catch (SQLException ex) {
            String msg = "Error al validar";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg)); 
        }
    }

    public PersonalImpl getFacade() {
        return ejbFacade;
    }
       
}
