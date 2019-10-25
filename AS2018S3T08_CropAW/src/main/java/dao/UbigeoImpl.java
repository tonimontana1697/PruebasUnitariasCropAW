package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Ubigeo;

public class UbigeoImpl extends Conexion{
    
    public List<String> listaAutoCompleteUbigeo(String ubigeo) throws SQLException{
        this.conectar();
        List<String> lista;
        try {
            lista = new ArrayList();
            String sql = "Select CONCAT(CONCAT(NOMDEP,CONCAT(' ',NOMPROV)), CONCAT(' ',NOMDIST)) UBIGEO \n" +
                                "from UBIGEO \n" +
                                "WHERE NOMDIST like ? and NOMPROV = 'CAÑETE' and ROWNUM <= 15";      
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, "%"+ubigeo.toUpperCase()+"%");
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
       
    public String obtenerCodigoUbigeo(String Ubigeo) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT CODUBI \n" +
                     "    FROM UBIGEO \n"
                    + "WHERE CONCAT(CONCAT(NOMDEP,CONCAT(' ',NOMPROV)), CONCAT(' ',NOMDIST)) LIKE ? and NOMPROV = 'CAÑETE'";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%"+Ubigeo.toUpperCase()+"%");
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

    public List<Ubigeo> UbigeoDistrito(String distri) throws Exception {
       
        this.conectar();
        List<Ubigeo> lista;
        Ubigeo ubigeoM;
        try {
            lista = new ArrayList();
            String sql = "Select * \n" +
                                "from UBIGEO \n" +
                                "WHERE NOMDIST like ? and NOMPROV = 'CAÑETE' and ROWNUM <= 15";      
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, "%"+distri.toUpperCase()+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ubigeoM = new Ubigeo();
                ubigeoM.setCODUBI(rs.getString("CODUBI"));
                ubigeoM.setNOMDIST(rs.getString("NOMDIST"));
                ubigeoM.setNOMPROV(rs.getString("NOMPROV"));
                lista.add(ubigeoM);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            this.Cerrar();
        }
        return lista;         
    }

    public Ubigeo find(String id) throws Exception {
        this.conectar();
        Ubigeo ubigeoM = new Ubigeo();
        try {
            String sql = "Select * from UBIGEO WHERE CODUBI = ?";      
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ubigeoM = new Ubigeo();
                ubigeoM.setCODUBI(rs.getString("CODUBI"));
                ubigeoM.setNOMDIST(rs.getString("NOMDIST"));
                ubigeoM.setNOMPROV(rs.getString("NOMPROV"));
            }
        } catch (Exception e) {
            throw e;
        }finally{
            this.Cerrar();
        }
        return ubigeoM;
    }
    
}
