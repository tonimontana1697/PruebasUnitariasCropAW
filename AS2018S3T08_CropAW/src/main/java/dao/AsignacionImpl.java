package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.AsignacionPersona;

public class AsignacionImpl extends Conexion implements CRUD<AsignacionPersona> {

    @Override
    public void Registrar(AsignacionPersona asigM) throws Exception {
        this.conectar();
        try {
            String sql = "insert into ASIGNACION_PERSONA (FECASIG,IDSECT,IDPER, ESTASIGPER) values (?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(asigM.getFECASIGD().getTime()));
            ps.setInt(2, asigM.getIDSECT());
            ps.setInt(3, asigM.getIDPER());
            ps.setString(4, "A");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void Modificar(AsignacionPersona asigM) throws Exception {
        this.conectar();
        try {
            String sql = "update ASIGNACION_PERSONA set FECASIG =?, IDSECT =?, IDPER=? where IDASIGPER =?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(asigM.getFECASIGD().getTime()));
            ps.setInt(2, asigM.getIDSECT());
            ps.setInt(3, asigM.getIDPER());
            ps.setInt(4, asigM.getIDASIGPER());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void Eliminar(AsignacionPersona asignacionPer) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE ASIGNACION_PERSONA SET ESTASIGPER='I' where IDASIGPER=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, asignacionPer.getIDASIGPER());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<AsignacionPersona> listaEstado(String estado) throws Exception {
        List<AsignacionPersona> listasig;
        AsignacionPersona asigM;
        this.conectar();
        try {
            listasig = new ArrayList();
            String sql = "select * from vw_Asignacion where ESTASIGPER='" + estado + "' and rownum <= 5 order by UBICACION asc";
            Statement st = getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                asigM = new AsignacionPersona();
                asigM.setIDASIGPER(rs.getInt("ID"));
                asigM.setFECASIGD(rs.getDate("Fecha"));
                asigM.setIDSECT(rs.getInt("IDSECT"));
                asigM.setSECTOR(rs.getString("Ubicacion"));
                asigM.setIDPER(rs.getInt("IDPER"));
                asigM.setPERSONA(rs.getString("Nombre"));
                asigM.setESTAPER(rs.getString("ESTASIGPER"));
                listasig.add(asigM);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listasig;
    }

    public boolean verificarExistecia(AsignacionPersona asignacionPer) throws Exception {
        this.conectar();
        try {
            String sql = "select * from ASIGNACION_PERSONA where IDPER = '" + asignacionPer.getIDPER() + "' and IDSECT = '" + asignacionPer.getIDSECT() + "'";
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

    public void activarUsuario(AsignacionPersona asignacionPer) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE ASIGNACION_PERSONA SET ESTASIGPER='A' where IDASIGPER=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, asignacionPer.getIDASIGPER());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void desactivarUsuario(AsignacionPersona asignacionPer) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE ASIGNACION_PERSONA SET ESTASIGPER='I' where IDASIGPER=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, asignacionPer.getIDASIGPER());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<AsignacionPersona> lista() throws Exception {
        return null;
    }
}
