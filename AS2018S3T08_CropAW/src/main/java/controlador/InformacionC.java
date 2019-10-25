package controlador;

import dao.DetalleInfoImpl;
import dao.InformacionImpl;
import java.io.IOException;
import java.io.OutputStream;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.AsignacionPersona;
import modelo.Informacion;
import modelo.Persona;
import org.primefaces.model.DualListModel;
import servicios.NotificationService;
import servicios.SessionUtils;

@Named(value = "informacionC")
@SessionScoped
public class InformacionC implements Serializable {

    private AsignacionC asignacionC = new AsignacionC();
    private List<AsignacionPersona> listaAsignacionM;
    private List<Informacion> listaInformacion;
    private List<Informacion> listaDistrito;
    private List<Informacion> listaInformacionFiltro;
    private Informacion informacionM = new Informacion();
    private Informacion informacionEdit;
    private Date fechaAtual = new Date();
    private Integer progress;
    private boolean existe = false;

    private final DetalleInfoImpl daoDet = new DetalleInfoImpl();

    private String mes;
    private String anio;
    private List<String> listaAnios = new ArrayList();

    private final InformacionImpl dao = new InformacionImpl();
    private DualListModel<AsignacionPersona> asignaciones;
    private NotificationService notificationService = new NotificationService();
    public InformacionC() {
        this.mes = "";
        this.anio = "";
        informacionEdit = new Informacion();
    }

    @PostConstruct
    public void init() {
        try {
            this.filtro();
            listaAño();
            listdist();
        } catch (Exception e) {
        }
    }

    public void registrar(Informacion infoM) throws Exception {
        try {
            this.dao.Registrar(infoM);
            this.filtro();
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            this.dao.Modificar(informacionEdit);
            this.filtro();
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Correcto"));
            limpiar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Informacion info) throws Exception {
        try {
            this.dao.Eliminar(info);
            this.filtro();
            limpiar();
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar", "Correcto"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void verDetalle() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/faces/Vistas/informacion/Detalle_Informacion.xhtml");
    }

    public void listaAño() throws Exception {
        try {
            this.listaAnios = this.dao.listaAnios();
        } catch (Exception e) {
            throw e;
        }
    }

    public void filtro() throws Exception {
        try {
            try {
                listaInformacion = this.dao.listaFiltroMes(this.restarMes(this.fechaAtual, 0), this.restarMesUlt(fechaAtual, 1));
            } catch (ParseException ex) {
                Logger.getLogger(InformacionC.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void listAsignaciones() throws Exception {
        List<AsignacionPersona> asignacionSource = this.dao.asigRestantes();
        List<AsignacionPersona> asignacionTarget = new ArrayList<AsignacionPersona>();
        this.asignaciones = new DualListModel<AsignacionPersona>(asignacionSource, asignacionTarget);
    }

    public void reporteMes() throws Exception {
        ReportC reporteMes;
        reporteMes = new ReportC();
        try {
            try {
                reporteMes.reporteinformacionMes(this.restarMes(this.fechaAtual, 0), this.restarMesUlt(fechaAtual, 1));
            } catch (ParseException ex) {
                Logger.getLogger(InformacionC.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Date restarMes(Date fecha, int meses) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MONTH, meses);  // numero de horas a añadir, o restar en caso de horas<0
        int dias = calendar.get(Calendar.DATE);
        calendar.add(Calendar.DAY_OF_MONTH, -(dias - 1));
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }

    public Date restarMesUlt(Date fecha, int meses) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MONTH, meses);  // numero de mes a añadir, o restar en caso de horas<0
        int dias = calendar.get(Calendar.DATE);
        calendar.add(Calendar.DAY_OF_MONTH, -(dias));
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }

    public void generar() throws Exception {
        try {
            List<AsignacionPersona> asig = (List<AsignacionPersona>) this.asignaciones.getTarget();
            System.out.println(asig.size());
            Persona per = SessionUtils.obtenerObjetoSesion();
            System.out.println(per.getIDPER());
            if (asig.size() > 0) {
                int num = 70 / asig.size();
                asig.forEach(action -> {
                    System.out.println("Resgitro");
                    Informacion inforM = new Informacion();
                    inforM.setIDASIG(action.getIDASIGPER());
                    inforM.setIDPER(per.getIDPER());
                    inforM.setTOTAREDET(0.0);
                    inforM.setESTAINFO("A");
                    try {
                        this.registrar(inforM);
                    } catch (Exception e) {
                        return;
                    }
                    this.setProgress(progress += num);
                });

                this.daoDet.Registrar(per.getIDPER());
                progress = 100;
                this.notificationService.notificacion();
                this.filtro();
            } else {
                progress = 100;
                this.existe = true;
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public Integer getProgress() {
        if (progress == null) {
            progress = 0;
        }
        if (progress > 100) {
            progress = 100;
        }
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public void onComplete() {
        System.out.println(":D");
        if (this.existe) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ya hay registros del mes", ":D"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Completado :D"));
            this.setProgress(0);
        }
    }

    public void listdist() {
        try {
            listaDistrito = dao.listar();
        } catch (Exception e) {
        }
    }

    public void limpiar() {
        informacionM = new Informacion();
    }

    public List<Informacion> getListaInformacion() {
        return listaInformacion;
    }

    public void setListaInformacion(List<Informacion> listaInformacion) {
        this.listaInformacion = listaInformacion;
    }

    public List<Informacion> getListaInformacionFiltro() {
        return listaInformacionFiltro;
    }

    public void setListaInformacionFiltro(List<Informacion> listaInformacionFiltro) {
        this.listaInformacionFiltro = listaInformacionFiltro;
    }

    public Informacion getInformacionM() {
        return informacionM;
    }

    public void setInformacionM(Informacion informacionM) {
        this.informacionM = informacionM;
    }

    public Informacion getInformacionEdit() {
        return informacionEdit;
    }

    public void setInformacionEdit(Informacion informacionEdit) {
        this.informacionEdit = informacionEdit;
    }

    public Date getFechaAtual() {
        return fechaAtual;
    }

    public void setFechaAtual(Date fechaAtual) {
        this.fechaAtual = fechaAtual;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public List<String> getListaAnios() {
        return listaAnios;
    }

    public void setListaAnios(List<String> listaAnios) {
        this.listaAnios = listaAnios;
    }

    public DualListModel<AsignacionPersona> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(DualListModel<AsignacionPersona> asignaciones) {
        this.asignaciones = asignaciones;
    }

    public List<Informacion> getListaDistrito() {
        return listaDistrito;
    }

    public void setListaDistrito(List<Informacion> listaDistrito) {
        this.listaDistrito = listaDistrito;
    }

}
