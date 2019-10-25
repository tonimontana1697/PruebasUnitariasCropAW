package modelo;

import java.util.Date;

/**
 *
 * @author PC03
 */
public class GraficosM {
    private String TIPO;
    private int COUNT;
    private int SUM;
    private int PERSONA;
    private int CULTIVO;
    private int SECTOR;
    private Date FECHA;
    private String Sector;

    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public int getCOUNT() {
        return COUNT;
    }

    public void setCOUNT(int COUNT) {
        this.COUNT = COUNT;
    }

    public int getSUM() {
        return SUM;
    }

    public void setSUM(int SUM) {
        this.SUM = SUM;
    }

    public int getPERSONA() {
        return PERSONA;
    }

    public void setPERSONA(int PERSONA) {
        this.PERSONA = PERSONA;
    }

    public int getCULTIVO() {
        return CULTIVO;
    }

    public void setCULTIVO(int CULTIVO) {
        this.CULTIVO = CULTIVO;
    }

    public int getSECTOR() {
        return SECTOR;
    }

    public void setSECTOR(int SECTOR) {
        this.SECTOR = SECTOR;
    }

    public Date getFECHA() {
        return FECHA;
    }

    public void setFECHA(Date FECHA) {
        this.FECHA = FECHA;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String Sector) {
        this.Sector = Sector;
    }
    
    
}
