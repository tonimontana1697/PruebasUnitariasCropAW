package servicios.validate;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named("V_TelefonoLength")
@RequestScoped()
public class ValidateTelefonoLength implements Validator{
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        boolean exite = false;
        
        String telefono = value.toString().trim();
        
        if(telefono.length() < 9 && telefono.length() > 0 ){
            FacesMessage msg = new FacesMessage("Numero celular no valido");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }  
}
