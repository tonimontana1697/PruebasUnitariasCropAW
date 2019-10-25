package servicios;

import controlador.CultivoC;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ValidarCultivo")
public class ValidarCultivo implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        boolean existe = false;
        String cultivo = value.toString().trim();

        CultivoC cultivoC = new CultivoC();

        try {

            if (cultivoC.validarExistenciaCultivo(cultivo)) {
                existe = true;
            }

        } catch (Exception e) {
            throw e;
        }
        if (existe) {
            FacesMessage msg = new FacesMessage("Cultivo Existente");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }

}
