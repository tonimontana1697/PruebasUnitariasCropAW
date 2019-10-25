package controlador;

import dao.AsignacionCultImpl;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.AsignacionCultivo;
import modelo.Cultivo;

@Named(value = "asignacionCultC")
@ViewScoped
public class AsignacionCultC implements Serializable {

    private AsignacionCultImpl dao;
    private AsignacionCultivo asigM;
    private AsignacionCultivo asigMEdit;
    private List<AsignacionCultivo> listAsig;
    private List<AsignacionCultivo> listAsigFilter;
    private boolean bt;

    private List<AsignacionCultivo> listAsigRegistrar;
    private String[] listAsigSelect;

    public AsignacionCultC() {
        asigM = new AsignacionCultivo();
        asigMEdit = new AsignacionCultivo();
        dao = new AsignacionCultImpl();
        listAsig = new ArrayList();
    }

    @PostConstruct
    public void inicio() {
        try {
            listarTipo("A");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void asignarcult() throws Exception {
        try {
            if (dao.verificarExistecia(asigM)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "El cultivo ya esta asignado a ese sector", null));
            } else {
                dao.Registrar(asigM);
                listarTipo("A");
                limpiar();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Se asigno correctamente.", null));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarasig() throws Exception {
        int id = asigMEdit.getIDASIGCUL();
        try {
            dao.Modificar(asigMEdit);
            if (id > 0) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificacion", "Completa"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ""));
            }
            listarTipo("A");
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(AsignacionCultivo asigM) throws Exception {
        try {
            dao.Eliminar(asigM);
            listarTipo("A");
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminacion", "Completa"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarTipo(String estado) throws Exception {
        try {
            if (estado.equals("A")) {
                bt = true;
            } else {
                bt = false;
            }
            listAsig = dao.listarTipo(estado);
        } catch (Exception e) {
            throw e;
        }
    }

    public void habilitar(AsignacionCultivo asigM) throws Exception {
        try {
            dao.habilitarAsignacionCultivo(asigM);
            listarTipo("I");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Habilitar", null));
        } catch (Exception e) {
            throw e;
        }
    }

    public void verInactivos() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/faces/Vistas/Asignacion/ListAsigCulInactivos.xhtml");
    }

    public void limpiar() {
        asigM = new AsignacionCultivo();
    }

    public AsignacionCultImpl getDao() {
        return dao;
    }

    public void setDao(AsignacionCultImpl dao) {
        this.dao = dao;
    }

    public AsignacionCultivo getAsigM() {
        return asigM;
    }

    public void setAsigM(AsignacionCultivo asigM) {
        this.asigM = asigM;
    }

    public List<AsignacionCultivo> getListAsig() {
        return listAsig;
    }

    public void setListAsig(List<AsignacionCultivo> listAsig) {
        this.listAsig = listAsig;
    }

    public AsignacionCultivo getAsigMEdit() {
        return asigMEdit;
    }

    public void setAsigMEdit(AsignacionCultivo asigMEdit) {
        this.asigMEdit = asigMEdit;
    }

    public List<AsignacionCultivo> getListAsigFilter() {
        return listAsigFilter;
    }

    public void setListAsigFilter(List<AsignacionCultivo> listAsigFilter) {
        this.listAsigFilter = listAsigFilter;
    }

    public boolean isBt() {
        return bt;
    }

    public void setBt(boolean bt) {
        this.bt = bt;
    }

    public String[] getListAsigSelect() {
        return listAsigSelect;
    }

    public void setListAsigSelect(String[] listAsigSelect) {
        this.listAsigSelect = listAsigSelect;
    }

    public List<AsignacionCultivo> getListAsigRegistrar() {
        return listAsigRegistrar;
    }

    public void setListAsigRegistrar(List<AsignacionCultivo> listAsigRegistrar) {
        this.listAsigRegistrar = listAsigRegistrar;
    }
}
