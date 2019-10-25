package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Persona;
import servicios.Encriptar;

public class PersonalImpl extends Conexion implements CRUD<Persona> {

    @Override
    public void Registrar(Persona personalM) throws Exception {
        Encriptar encriptar = new Encriptar();
        this.conectar();
        try {
            String sql = "insert into persona(NOMPER,APEPER,DNIPER,TELPER,TIPPER,ESTAPER,USERPER,PSWPER) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, personalM.getNOMPER().trim());
            ps.setString(2, personalM.getAPEPER().trim());
            ps.setString(3, personalM.getDNIPER());
            ps.setString(4, personalM.getTELPER());
            ps.setString(5, personalM.getTIPPER());
            ps.setString(6, "A");
            ps.setString(7, personalM.getUSERPER());
            ps.setString(8, encriptar.encriptar(personalM.getPSWPER()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void Modificar(Persona personalM) throws Exception {
        this.conectar();
        Encriptar encriptar = new Encriptar();
        try {
            String sql = "update persona set NOMPER=?,APEPER=?,DNIPER=?,TELPER=?,TIPPER=?,ESTAPER=? where IDPER=?";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, personalM.getNOMPER());
            ps.setString(2, personalM.getAPEPER());
            ps.setString(3, personalM.getDNIPER());
            ps.setString(4, personalM.getTELPER());
            ps.setString(5, personalM.getTIPPER());
            ps.setString(6, personalM.getESTAPER());
            ps.setLong(7, personalM.getIDPER());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void ModificarCredencial(Persona personalM) throws Exception {
        this.conectar();
        Encriptar encriptar = new Encriptar();
        try {
            String sql = "update persona set USERPER=?,PSWPER=? where IDPER=?";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, personalM.getUSERPER());
            ps.setString(2, encriptar.encriptar(personalM.getPSWPER()));
            ps.setInt(3, personalM.getIDPER());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void Eliminar(Persona personalM) throws Exception {
        this.conectar();
        try {
            String sql = "update PERSONA set ESTAPER='I' where IDPER=? ";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setInt(1, personalM.getIDPER());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public Persona validarExistenciaPersonal(String DNIPER) throws Exception {
        this.conectar();
        try {
            String sql = "Select IDPER, DNIPER from PERSONA where DNIPER = '" + DNIPER + "'";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            Persona personalM = new Persona();
            while (rs.next()) {
                personalM.setIDPER(rs.getInt("IDPER"));
                personalM.setDNIPER(rs.getString("DNIPER"));
                break;
            }
            return personalM;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void habilitarPersonal(Persona personalM) throws Exception {
        this.conectar();
        try {
            String sql = "update PERSONA set ESTAPER='A' where IDPER=? ";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setInt(1, personalM.getIDPER());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public Persona validarUsuarioExistente(String USERPER) throws Exception {
        this.conectar();
        try {
            String sql = "Select IDPER,USERPER from PERSONA where USERPER='" + USERPER + "'";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            Persona personalM = new Persona();
            while (rs.next()) {
                personalM.setIDPER(rs.getInt("IDPER"));
                personalM.setUSERPER(rs.getString("USERPER"));
                break;
            }
            return personalM;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }

    }

    public Persona validarTelefono(String TELPER) throws Exception {
        this.conectar();
        try {
            String sql = "select IDPER,TELPER from PERSONA where TELPER='" + TELPER + "'";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            Persona personalM = new Persona();
            while (rs.next()) {
                personalM.setIDPER(rs.getInt("IDPER"));
                personalM.setTELPER(rs.getString("TELPER"));
                break;
            }
            return personalM;
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Persona> listaTipo(String filtro) throws Exception {
        List<Persona> listper;
        Persona persM;
        this.conectar();
        try {
            listper = new ArrayList();
            String sql = "select*from persona where ESTAPER='" + filtro + "'";
            Statement st = getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                persM = new Persona();
                persM.setIDPER(rs.getInt("IDPER"));
                persM.setNOMPER(rs.getString("NOMPER"));
                persM.setAPEPER(rs.getString("APEPER"));
                persM.setDNIPER(rs.getString("DNIPER"));
                persM.setTELPER(rs.getString("TELPER"));
                persM.setTIPPER(rs.getString("TIPPER"));
                persM.setESTAPER(rs.getString("ESTAPER"));
                persM.setUSERPER(rs.getString("USERPER"));
                persM.setPSWPER(rs.getString("PSWPER"));
                listper.add(persM);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listper;
    }

    @Override
    public List<Persona> lista() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void create(Persona personalM) throws Exception {
        Encriptar encriptar = new Encriptar();
        this.conectar();
        try {
            String sql = "insert into persona(NOMPER,APEPER,DNIPER,TELPER,TIPPER,ESTAPER,USERPER,PSWPER) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, personalM.getNOMPER().trim());
            ps.setString(2, personalM.getAPEPER().trim());
            ps.setString(3, personalM.getDNIPER());
            ps.setString(4, personalM.getTELPER());
            ps.setString(5, personalM.getTIPPER());
            ps.setString(6, "A");
            ps.setString(7, personalM.getUSERPER());
            ps.setString(8, encriptar.encriptar(personalM.getPSWPER()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
    
    public Persona find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Persona> findAll() throws SQLException  {
        List<Persona> listper;
        Persona persM;
        this.conectar();
        try {
            listper = new ArrayList();
            String sql = "select * from persona";
            Statement st = getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                persM = new Persona();
                persM.setIDPER(rs.getInt("IDPER"));
                persM.setNOMPER(rs.getString("NOMPER"));
                persM.setAPEPER(rs.getString("APEPER"));
                persM.setDNIPER(rs.getString("DNIPER"));
                persM.setTELPER(rs.getString("TELPER"));
                persM.setTIPPER(rs.getString("TIPPER"));
                persM.setESTAPER(rs.getString("ESTAPER"));
                persM.setUSERPER(rs.getString("USERPER"));
                persM.setPSWPER(rs.getString("PSWPER"));
                listper.add(persM);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listper;
    }

    public void edit(Persona selected) throws SQLException {
        this.conectar();
        Encriptar encriptar = new Encriptar();
        try {
            String sql = "update persona set NOMPER=?,APEPER=?,DNIPER=?,TELPER=?,TIPPER=?,ESTAPER=? where IDPER=?";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, selected.getNOMPER());
            ps.setString(2, selected.getAPEPER());
            ps.setString(3, selected.getDNIPER());
            ps.setString(4, selected.getTELPER());
            ps.setString(5, selected.getTIPPER());
            ps.setString(6, selected.getESTAPER());
            ps.setLong(7, selected.getIDPER());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void remove(Persona selected) throws SQLException {
        this.conectar();
        Encriptar encriptar = new Encriptar();
        try {
            String sql = "DELETE persona where IDPER=?";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setInt(1, selected.getIDPER());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public boolean countTelefono(String telefono) throws Exception {
        this.conectar();
        try {
            String sql = "select IDPER,TELPER from PERSONA where TELPER='" + telefono + "'";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public boolean countDni(String dniper) throws SQLException {
        this.conectar();
        try {
            String sql = "Select IDPER, DNIPER from PERSONA where DNIPER = '" + dniper + "'";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public boolean validarUsarioExistente(String usuario) throws SQLException {
        this.conectar();
        try {
            String sql = "Select IDPER,USERPER from PERSONA where USERPER='" + usuario + "'";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
}
