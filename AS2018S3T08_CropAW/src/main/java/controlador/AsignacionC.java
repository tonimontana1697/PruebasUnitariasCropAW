package controlador;

import dao.AsignacionImpl;
import dao.ReportesDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.AsignacionPersona;

@Named(value = "asignacionC")
@ViewScoped
public class AsignacionC implements Serializable {

    private AsignacionPersona asigM;
    private AsignacionPersona asigMEdit;
    private AsignacionImpl dao;
    private List<AsignacionPersona> listAsig;
    private List<AsignacionPersona> listAsigFilter;
    private Date fechaAtual = new Date();
    private boolean bt;

    public AsignacionC() {
        asigM = new AsignacionPersona();
        dao = new AsignacionImpl();
        listAsig = new ArrayList();
        asigMEdit = new AsignacionPersona();
    }

    @PostConstruct
    public void inicio() {
        try {
            listarEstado("A");
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public void asignar() throws Exception {
        try {
            listAsig = new ArrayList();
            if (dao.verificarExistecia(asigM)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "El usuario ya esta asignado", null));
            } else {
                dao.Registrar(asigM);
                listarEstado("A");
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Se asigno correctamente.", null));
            }
            listarEstado("A");
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao.Modificar(asigMEdit);
            listarEstado("A");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modifico correctamente", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarEstado(String estado) throws Exception {
        try {
            AsignacionImpl dao;
            dao = new AsignacionImpl();
            if (estado.equals("A")) {
                bt = true;
            } else {
                bt = false;
            }
            listAsig = dao.listaEstado(estado);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(AsignacionPersona asignacionM) throws Exception {
        try {
            dao.desactivarUsuario(asignacionM);
            limpiar();
            listarEstado("A");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminacion", ""));
        } catch (Exception e) {
            throw e;
        }
    }

    public void hablitiar(AsignacionPersona asignacionM) throws Exception {
        try {
            dao.activarUsuario(asignacionM);
            limpiar();
            listarEstado("I");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Habilitado", ""));
        } catch (Exception e) {
            throw e;
        }
    }


    public List<AsignacionPersona> getListarEstado(String estado) throws Exception {
        try {
            AsignacionImpl dao;
            dao = new AsignacionImpl();
            return listAsig = dao.listaEstado(estado);
        } catch (Exception e) {
            System.out.println("Ya es toda we");
            throw e;
        }
    }

    public void limpiar() {
        asigM = new AsignacionPersona();
    }

    public AsignacionPersona getAsigM() {
        return asigM;
    }

    public void setAsigM(AsignacionPersona asigM) {
        this.asigM = asigM;
    }

    public AsignacionImpl getDao() {
        return dao;
    }

    public void setDao(AsignacionImpl dao) {
        this.dao = dao;
    }

    public List<AsignacionPersona> getListAsig() {
        return listAsig;
    }

    public void setListAsig(List<AsignacionPersona> listAsig) {
        this.listAsig = listAsig;
    }

    public AsignacionPersona getAsigMEdit() {
        return asigMEdit;
    }

    public void setAsigMEdit(AsignacionPersona asigMEdit) {
        this.asigMEdit = asigMEdit;
    }

    public Date getFechaAtual() {
        return fechaAtual;
    }

    public void setFechaAtual(Date fechaAtual) {
        this.fechaAtual = fechaAtual;
    }

    public boolean isBt() {
        return bt;
    }

    public void setBt(boolean bt) {
        this.bt = bt;
    }

    public List<AsignacionPersona> getListAsigFilter() {
        return listAsigFilter;
    }

    public void setListAsigFilter(List<AsignacionPersona> listAsigFilter) {
        this.listAsigFilter = listAsigFilter;
    }
}
