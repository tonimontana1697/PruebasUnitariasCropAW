package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Valle;


public class ValleImpl extends Conexion{
  
    public List<String> listaAutoCompleteValle(String ubigeo) throws SQLException{
        this.conectar();
        List<String> lista;
        try {
            lista = new ArrayList();
            String sql = "Select \n" +
                      "    CONCAT(NOMDEP,CONCAT(CONCAT(' ',NOMPROV),CONCAT(' ',NOMDIST))) UBIGEO \n"
                    + "from UBIGEO WHERE NOMDIST like ?";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, "%"+ubigeo+"%"); 
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                lista.add(rs.getString("UBIGEO"));
            }
            
        } catch (Exception e) {
            throw e;
        }finally{
            this.Cerrar();
        }
        return lista;
    }
       
    public String obtenerCodigoValle(String Ubigeo) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODUBI FROM UBIGEO WHERE CONCAT(NOMDEP,',',NOMPROV,',',NOMDIST) LIKE ? and NOMPROV = 'Cañete';";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, Ubigeo);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("CODUBI");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }finally{
            this.Cerrar();
        }
    }

    public List<Valle> findAll() throws Exception {
        this.conectar();
        List<Valle> lista;
        Valle valleM;
        try {
            lista = new ArrayList();
            String sql = "Select * from VALLE";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                valleM = new Valle();
                valleM.setIdvall(rs.getLong("IDVALL"));
                valleM.setNomvall(rs.getString("NOMVALL"));
                valleM.setEstavall(rs.getString("ESTAVALL"));
                lista.add(valleM);
            }
            
        } catch (Exception e) {
            throw e;
        }finally{
            this.Cerrar();
        }
        return lista;
    }

    public void create(Valle selected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void edit(Valle selected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void remove(Valle selected) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Valle find(Long id) throws SQLException {
        this.conectar();
        Valle valleM = new Valle();
        try {
            String sql = "Select * from VALLE where IDVALL = ?";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                valleM.setIdvall(rs.getLong("IDVALL"));
                valleM.setNomvall(rs.getString("NOMVALL"));
                valleM.setEstavall(rs.getString("ESTAVALL"));
                break;
            }
            
        } catch (Exception e) {
            throw e;
        }finally{
            this.Cerrar();
        }
        return valleM;
    }
    
}

