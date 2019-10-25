package servicios;

import javax.faces.context.FacesContext;
import modelo.Persona;

public class SessionUtils {
    
    public static Persona obtenerObjetoSesion() {
        return (Persona) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
    }

    public static String ObtenerNombreSesion() {
        Persona us = (Persona) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        if (us != null) {
            return us.getNOMPER();
        } else {
            return null;
        }
    }

    public static int ObtenerCodigoSesion() {
        Persona us = (Persona) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        if (us != null) {
            return us.getIDPER();
        } else {
            return 0;
        }
    }
    
}
