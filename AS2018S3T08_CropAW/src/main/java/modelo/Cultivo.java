package modelo;

public class Cultivo {
    private Long IDCUL;
    private String NOMCUL;
    private String TIPCUL;
    private String ESTACUL;

    public Long getIDCUL() {
        return IDCUL;
    }

    public void setIDCUL(Long IDCUL) {
        this.IDCUL = IDCUL;
    }

    public String getNOMCUL() {
        return NOMCUL;
    }

    public void setNOMCUL(String NOMCUL) {
        this.NOMCUL = NOMCUL;
    }

    public String getTIPCUL() {
        return TIPCUL;
    }

    public void setTIPCUL(String TIPCUL) {
        this.TIPCUL = TIPCUL;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (IDCUL != null ? IDCUL.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cultivo)) {
            return false;
        }
        Cultivo other = (Cultivo) object;
        if ((this.IDCUL == null && other.IDCUL != null) || (this.IDCUL != null && !this.IDCUL.equals(other.IDCUL))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cultivo[ idcul=" + IDCUL + " ]";
    }

    public String getESTACUL() {
        return ESTACUL;
    }

    public void setESTACUL(String ESTACUL) {
        this.ESTACUL = ESTACUL;
    }
}
