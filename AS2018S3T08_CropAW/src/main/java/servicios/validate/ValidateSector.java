package servicios.validate;

import dao.SectorImpl;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named("V_Sector")
@RequestScoped()
public class ValidateSector implements Validator {

    private SectorImpl ejbFacade = new SectorImpl();

    @Override
    public void validate(FacesContext context,
            UIComponent component, Object value)
            throws ValidatorException {

        boolean existe = false;
        String sector = value.toString().trim();

        try {

            if (getFacade().countSector(sector)) {
                existe = true;
            }

        } catch (Exception e) {

        }
        if (existe) {
            FacesMessage msg = new FacesMessage("Sector Existente");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }

    public SectorImpl getFacade() {
        return ejbFacade;
    }

}
