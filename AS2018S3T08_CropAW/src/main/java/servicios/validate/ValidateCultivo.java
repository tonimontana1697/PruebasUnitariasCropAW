package servicios.validate;

import dao.CultivoImpl;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named("V_Cultivo")
@RequestScoped()
public class ValidateCultivo implements Validator {
    

    private CultivoImpl ejbFacade = new CultivoImpl();
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        boolean existe = false;
        String cultivo = value.toString().trim();

        try {

            if (getFacade().countNOMCULT(cultivo)) {
                existe = true;
            }

        } catch (Exception e) {
        }
        if (existe) {
            FacesMessage msg = new FacesMessage("Cultivo Existente");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }

    public CultivoImpl getFacade() {
        return ejbFacade;
    }

}
