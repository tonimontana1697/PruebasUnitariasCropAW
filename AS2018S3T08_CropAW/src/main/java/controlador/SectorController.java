package controlador;

import modelo.Sector;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import dao.SectorImpl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("sectorController")
@SessionScoped
public class SectorController implements Serializable {

    private SectorImpl ejbFacade = new SectorImpl();
    private List<Sector> items = null;
    private Sector selected;

    public SectorController() {
    }

    public Sector getSelected() {
        return selected;
    }

    public void setSelected(Sector selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SectorImpl getFacade() {
        return ejbFacade;
    }

    public Sector prepareCreate() {
        selected = new Sector();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Vistas").getString("Sector_1Created"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Vistas").getString("Sector_1Updated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Vistas").getString("Sector_1Deleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Sector> getItems() throws Exception {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Sector> complete(String nomsect) throws Exception {
        items = getFacade().completeNomsect(nomsect);
        return items;
    }
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                switch (persistAction) {
                    case CREATE:
                        getFacade().create(selected);
                        break;
                    case UPDATE:
                        getFacade().edit(selected);
                        break;
                    case DELETE:
                        getFacade().remove(selected);
                        break;
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (SQLException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (ex.getErrorCode() == 2292) {
                    msg = selected.getNOMSECT() + " se encuentra asignado a un sector.";
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Vistas").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Vistas").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Sector getSector_1(java.lang.Long id) throws Exception {
        return getFacade().find(id);
    }

    public List<Sector> getItemsAvailableSelectMany() throws Exception {
        return getFacade().findAll();
    }

    public List<Sector> getItemsAvailableSelectOne() throws Exception {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Sector.class)
    public static class Sector_1ControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SectorController controller = (SectorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sectorController");
            try {
                return controller.getSector_1(getKey(value));
            } catch (Exception ex) {
                Logger.getLogger(SectorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Sector) {
                Sector o = (Sector) object;
                return getStringKey(o.getIDSECT());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Sector.class.getName()});
                return null;
            }
        }

    }

}
