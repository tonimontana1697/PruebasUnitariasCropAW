package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.AsignacionCultivo;

public class AsignacionCultImpl extends Conexion implements CRUD<AsignacionCultivo> {

    @Override
    public void Registrar(AsignacionCultivo asigCM) throws Exception {
        this.conectar();
        try {
            String sql = "insert into ASIGNACION_CULTIVO (IDCUL,IDSECT, ESTAASIGCUL) values(?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, asigCM.getIDCUL());
            ps.setInt(2, asigCM.getIDSECT());
            ps.setString(3, "A");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void Modificar(AsignacionCultivo asigCM) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE ASIGNACION_CULTIVO set IDCUL=?, IDSECT=? where IDASIGCUL=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, asigCM.getIDCULI());
            ps.setInt(2, asigCM.getIDSECTI());
            ps.setInt(3, asigCM.getIDASIGCUL());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void Eliminar(AsignacionCultivo asigCM) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE ASIGNACION_CULTIVO SET ESTAASIGCUL='I' where IDASIGCUL=? ";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setInt(1, asigCM.getIDASIGCUL());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<AsignacionCultivo> listarTipo(String estado) throws Exception {
        List<AsignacionCultivo> listAC;
        AsignacionCultivo asigCM;
        this.conectar();
        try {
            listAC = new ArrayList();
            String sql = "select*from vw_AsignacionCultivo where ESTAASIGCUL='" + estado + "'";
            Statement st = getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                asigCM = new AsignacionCultivo();
                asigCM.setIDASIGCUL(rs.getInt("CodigoAsigCul"));
                asigCM.setIDCULI(rs.getInt("IDCultivo"));
                asigCM.setNombreculivo(rs.getString("NombreCultivo"));
                asigCM.setIDSECT(rs.getInt("IDSector"));
                asigCM.setNombreSector(rs.getString("NombreSector"));
                listAC.add(asigCM);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listAC;
    }

    public boolean verificarExistecia(AsignacionCultivo asignacionCul) throws Exception {
        this.conectar();
        try {
            String sql = "select * from ASIGNACION_CULTIVO where IDCUL = '" + asignacionCul.getIDCUL() + "' and IDSECT = '" + asignacionCul.getIDSECT() + "'";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return false;
    }

    public void habilitarAsignacionCultivo(AsignacionCultivo asignacionCul) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE ASIGNACION_CULTIVO SET ESTAASIGCUL='A' where IDASIGCUL=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, asignacionCul.getIDASIGCUL());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<AsignacionCultivo> lista() throws Exception {
        return null;
    }
}
