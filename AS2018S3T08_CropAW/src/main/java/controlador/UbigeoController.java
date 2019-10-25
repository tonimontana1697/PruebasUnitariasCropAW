package controlador;

import modelo.Ubigeo;
import dao.UbigeoImpl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("ubigeoController")
@SessionScoped
public class UbigeoController implements Serializable {
    
    private UbigeoImpl ejbFacade = new UbigeoImpl();
    private List<Ubigeo> items = null;
    private Ubigeo selected;

    public UbigeoController() {
    }

    public Ubigeo getSelected() {
        return selected;
    }

    public void setSelected(Ubigeo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UbigeoImpl getFacade() {
        return ejbFacade;
    }

//    public List<Ubigeo> getItems() {
//        if (items == null) {
//            items = getFacade().findAll();
//        }
//        return items;
//    }
    
    public List<Ubigeo>complete(String distri) throws Exception {
        items = getFacade().UbigeoDistrito(distri);
        return items;
    }

    public Ubigeo getUbigeo(java.lang.String id) throws Exception {
        return getFacade().find(id);
    }

    @FacesConverter(forClass = Ubigeo.class)
    public static class UbigeoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UbigeoController controller = (UbigeoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ubigeoController");
            try {
                return controller.getUbigeo(getKey(value));
            } catch (Exception ex) {
                Logger.getLogger(UbigeoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Ubigeo) {
                Ubigeo o = (Ubigeo) object;
                return getStringKey(o.getCODUBI());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Ubigeo.class.getName()});
                return null;
            }
        }

    }

}
