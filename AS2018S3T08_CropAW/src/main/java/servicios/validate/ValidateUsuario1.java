package servicios.validate;

import dao.PersonalImpl;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

@Named("V_Usuario")
@RequestScoped
public class ValidateUsuario1 implements Validator{

    private PersonalImpl ejbFacade = new PersonalImpl();
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        boolean existe = false;
        
        String usuario = value.toString().trim();
 
        try {
            if(ejbFacade.validarUsarioExistente(usuario)){
                existe = true;
            }
        } catch (Exception e) {
        }
        if(existe){
            FacesMessage msg = new FacesMessage("Usuario existe");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
    
}
