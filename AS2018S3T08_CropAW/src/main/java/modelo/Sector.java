package modelo;

import java.io.Serializable;

public class Sector implements Serializable{
    private Long IDSECT;
    private String NOMSECT;
    private double AREASECT;
    private Valle IDVALL;
    private Ubigeo CODUBI;
    private String ESTSECT;

    public Long getIDSECT() {
        return IDSECT;
    }

    public void setIDSECT(Long IDSECT) {
        this.IDSECT = IDSECT;
    }

    public String getNOMSECT() {
        return NOMSECT;
    }

    public void setNOMSECT(String NOMSECT) {
        this.NOMSECT = NOMSECT;
    }

    public double getAREASECT() {
        return AREASECT;
    }

    public void setAREASECT(double AREASECT) {
        this.AREASECT = AREASECT;
    }

    public Valle getIDVALL() {
        return IDVALL;
    }

    public void setIDVALL(Valle IDVALL) {
        this.IDVALL = IDVALL;
    }

    public Ubigeo getCODUBI() {
        return CODUBI;
    }

    public void setCODUBI(Ubigeo CODUBI) {
        this.CODUBI = CODUBI;
    }

    public String getESTSECT() {
        return ESTSECT;
    }

    public void setESTSECT(String ESTSECT) {
        this.ESTSECT = ESTSECT;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (IDSECT != null ? IDSECT.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sector)) {
            return false;
        }
        Sector other = (Sector) object;
        if ((this.IDSECT == null && other.IDSECT != null) || (this.IDSECT != null && !this.IDSECT.equals(other.IDSECT))) {
            return false;
        }
        return true;
    }
}