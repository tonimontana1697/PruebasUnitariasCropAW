package controlador;

import dao.ReportesDao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import modelo.Cultivo;

/**
 *
 * @author PC03
 */
@Named(value = "reportC")
@SessionScoped
public class ReportC implements Serializable {

    private Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd/mm/YYYY");
    
    Cultivo cul = new Cultivo();

    public void reportcultivo() throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "Cultivo.jasper", "Lista de Cultivo " + format.format(date) + ".pdf");
        } catch (Exception e) {
        }
    }

    public void reportpersonal() throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "Personal.jasper", "Lista del Personal " + format.format(date) + ".pdf");
        } catch (Exception e) {
        }
    }

    public void reportsector() throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "Sector.jasper", "Lista de Sectores " + format.format(date) + ".pdf");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void reportasignacionpersonal() throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "PersonaAsignacion.jasper", "Lista de Asignacion del Personal " + format.format(date) + ".pdf");
        } catch (Exception e) {
        }
    }

    public void reportasignacioncultivo() throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "AsignacionCultivo.jasper", "Lista de Asignacion de Cutlivo " + format.format(date) + ".pdf");
        } catch (Exception e) {
        }
    }

    public void reporteinformacion(int IDINFO) throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            HashMap parameters = new HashMap();
            parameters.put("IDINFO", IDINFO);
            report.exportarXLSX(parameters, "Informacion.jasper", "informacion " + format.format(date) + ".xlsx");
        } catch (Exception e) {
            throw e;
        }
    }

    public void reportpara(String TIPCUL) throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            if (TIPCUL == null) {
                TIPCUL = "";
            }
            HashMap parameters = new HashMap();
            parameters.put("TIPCUL", TIPCUL);
            report.exportarPDFGlobal(parameters, "CultivoParameters.jasper", "Lista de cultivo " + format.format(date) + ".pdf");
        } catch (Exception e) {
            throw e;
        }
    }

    public void reporteinformacions(int IDINFO) throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            HashMap parameters = new HashMap();
            parameters.put("IDINFO", IDINFO);
            //report.exportarPDFGlobal(parameters, "Informacion.jasper", "Informacion de Sector.pdf");
            report.exportarXLSX(parameters, "Informacion.jasper", "informacion " + format.format(date) + ".xlsx");
        } catch (Exception e) {
            throw e;
        }
    }

    public void reporteinformacionMes(Date primerDiaMes, Date ultimoDiaMes) throws Exception {
        ReportesDao report = new ReportesDao();
        try {
            HashMap parameters = new HashMap();
            parameters.put("PRIMERDIA", new java.sql.Date(primerDiaMes.getTime()));
            parameters.put("ULTIMODIA", new java.sql.Date(ultimoDiaMes.getTime()));
            //report.exportarPDFGlobal(parameters, "Informacion.jasper", "Informacion de Sector.pdf");
            report.exportarXLSX(parameters, "InformacionMes.jasper", "informacion " + format.format(date) + ".xlsx");
        } catch (Exception e) {
            throw e;
        }
    }

    public void reporteinfodist(int CODUBI) {
        ReportesDao report = new ReportesDao();
        try {
            HashMap parameters = new HashMap();
            parameters.put("CODUBI", CODUBI);
            report.exportarXLSX(parameters, "InformacionDistrito.jasper", "informacion " + format.format(date) + ".xlsx");
        } catch (Exception e) {

        }
    }

    public void reportgraf() {
        ReportesDao report = new ReportesDao();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "Graficossub1.jasper", "Graficos " + format.format(date) + ".pdf");
        } catch (Exception e) {
        }
    }

    public void reportgraf2() {
        ReportesDao report = new ReportesDao();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "SumCosecha.jasper", "Graficos " + format.format(date) + ".pdf");
        } catch (Exception e) {
        }
    }

    public void reportgraf3() {
        ReportesDao report = new ReportesDao();
        try {
            Map<String, Object> parameters = new HashMap();
            report.exportarPDFGlobal(parameters, "Cantcult.jasper", "Graficos " + format.format(date) + ".pdf");
        } catch (Exception e) {
        }
    }
}
