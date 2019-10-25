package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Sector;
import modelo.Ubigeo;
import modelo.Valle;

public class SectorImpl extends Conexion{
//
//    @Override
//    public void Registrar(Sector sectorM) throws Exception {
//        this.conectar();
//        try {
//            String sql = "INSERT INTO SECTOR (NOMSECT,AREASECT,IDVALL,CODUBI, ESTSECT) values(?, ?, ?, ?, ?)";
//            PreparedStatement ps = this.getCn().prepareStatement(sql);
//            ps.setString(1, sectorM.getNOMSECT());
//            ps.setDouble(2, sectorM.getAREASECT());
//            ps.setInt(3, 1);
//            ps.setString(4, sectorM.getCODUBI().getCODUBI());
//            ps.setString(5, "A");
//            ps.executeUpdate();
//            ps.close();
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            this.Cerrar();
//        }
//
//    }
//
//    @Override
//    public void Modificar(Sector sectorM) throws Exception {
//        this.conectar();
//        try {
//            String sql = "UPDATE SECTOR set NOMSECT=?, AREASECT=?, IDVALL=? ,CODUBI=? where IDSECT=?";
//            PreparedStatement ps = this.getCn().prepareStatement(sql);
//            ps.setString(1, sectorM.getNOMSECT());
//            ps.setDouble(2, sectorM.getAREASECT());
//            ps.setInt(3, 1);
//            ps.setString(4, sectorM.getCODUBI().getCODUBI());
//            ps.setLong(5, sectorM.getIDSECT());
//            ps.executeUpdate();
//            ps.close();
//            System.out.println("Entro dao");
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            this.Cerrar();
//        }
//    }
//
//    @Override
//    public void Eliminar(Sector sectorM) throws Exception {
//        this.conectar();
//        try {
//            String sql = "UPDATE SECTOR set ESTSECT = 'I' where IDSECT=?";
//            PreparedStatement ps = this.getCn().prepareStatement(sql);
//            ps.setLong(1, sectorM.getIDSECT());
//            ps.executeUpdate();
//
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            this.Cerrar();
//        }
//    }
//
//    public void Habilitar(Sector sectorM) throws Exception {
//        this.conectar();
//        try {
//            String sql = "UPDATE SECTOR set ESTSECT = 'A' where IDSECT=?";
//            PreparedStatement ps = this.getCn().prepareStatement(sql);
//            ps.setLong(1, sectorM.getIDSECT());
//            ps.executeUpdate();
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            this.Cerrar();
//        }
//
//    }
//
//    public List<Sector> listado(String estado) throws Exception {
//        this.conectar();
//        List<Sector> listaSector;
//        Sector sectorM;
//        Ubigeo ubigeoM;
//        try {
//            String sql = "Select * from vw_SECTOR where ESTSECT = '" + estado + "'";
//            listaSector = new ArrayList();
//            Statement st = getCn().createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                sectorM = new Sector();
//                ubigeoM = new Ubigeo();
//                sectorM.setIDSECT(rs.getLong("IDSECT"));
//                sectorM.setNOMSECT(rs.getString("NOMSECT"));
//                sectorM.setAREASECT(rs.getDouble("AREASECT"));
//                ubigeoM.setCODUBI(rs.getString("NOMDIST"));
//                sectorM.setCODUBI(ubigeoM);
//                sectorM.setESTSECT(rs.getString("ESTSECT"));
//                listaSector.add(sectorM);
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            this.Cerrar();
//        }
//        return listaSector;
//    }
//
//    @Override
//    public List<Sector> lista() throws Exception {
//        this.conectar();
//        List<Sector> listaSector;
//        Sector sectorM;
//        try {
//            String sql = "Select * from vw_SECTOR where ESTSECT = 'A'";
//            listaSector = new ArrayList();
//            Statement st = getCn().createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                sectorM = new Sector();
//                sectorM.setIDSECT(rs.getLong("IDSECT"));
//                sectorM.setNOMSECT(rs.getString("NOMSECT"));
//                sectorM.setAREASECT(rs.getDouble("AREASECT"));
//                sectorM.setCODUBI(rs.getString("NOMDIST"));
//                sectorM.setESTSECT(rs.getString("ESTSECT"));
//                listaSector.add(sectorM);
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            this.Cerrar();
//        }
//        return listaSector;
//    }

    public Sector validarExistenciaSector(String sector) throws Exception {
        this.conectar();
        try {
            String sql = "Select IDSECT, NOMSECT from SECTOR where NOMSECT = '" + sector + "'";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            Sector sectorM = new Sector();
            while (rs.next()) {
                sectorM.setIDSECT(rs.getLong("IDSECT"));
                sectorM.setNOMSECT(rs.getString("NOMSECT"));
                break;
            }
            return sectorM;
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

//    public List<Sector> listaAutoComplete(String sectorComplete) throws Exception {
//        List<Sector> listaSector;
//        Sector sectorM;
//        this.conectar();
//        try {
//            String sql = "Select * from SECTOR where NOMSECT like '%" + sectorComplete + "%'";
//            listaSector = new ArrayList();
//            Statement st = getCn().createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                sectorM = new Sector();
//                sectorM.setIDSECT(rs.getLong("NUMSECT"));
//                sectorM.setNOMSECT(rs.getString("NOMSECT"));
//                sectorM.setAREASECT(rs.getDouble("AREASECT"));
//                sectorM.setIDVALL(rs.getInt("IDVALL"));
//                sectorM.setCODUBI(rs.getString("CODUBI"));
//                listaSector.add(sectorM);
//            }
//            rs.close();
//            st.close();
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            this.Cerrar();
//        }
//        return listaSector;
//    }

    public void create(Sector selected) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO SECTOR (NOMSECT,AREASECT,IDVALL,CODUBI, ESTSECT) values(?, ?, ?, ?, ?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, selected.getNOMSECT());
            ps.setDouble(2, selected.getAREASECT());
            ps.setLong(3, selected.getIDVALL().getIdvall());
            ps.setString(4, selected.getCODUBI().getCODUBI());
            ps.setString(5, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void edit(Sector selected) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE SECTOR set NOMSECT=?, AREASECT=?, IDVALL=? ,CODUBI=? where IDSECT=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, selected.getNOMSECT());
            ps.setDouble(2, selected.getAREASECT());
            ps.setLong(3, selected.getIDVALL().getIdvall());
            ps.setString(4, selected.getCODUBI().getCODUBI());
            ps.setLong(5, selected.getIDSECT());
            ps.executeUpdate();
            ps.close();
            System.out.println("Entro dao");
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void remove(Sector selected) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE SECTOR set ESTSECT = 'I' where IDSECT=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setLong(1, selected.getIDSECT());
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Sector> findAll() throws Exception {
        this.conectar();
        List<Sector> listaSector;
        Sector sectorM;
        Ubigeo ubigeoM;
        try {
            String sql = "Select s.*, u.* from SECTOR s inner join UBIGEO u on u.CODUBI = s.CODUBI where ESTSECT = 'A'";
            listaSector = new ArrayList();
            Statement st = getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                sectorM = new Sector();
                ubigeoM = new Ubigeo();
                sectorM.setIDSECT(rs.getLong("IDSECT"));
                sectorM.setNOMSECT(rs.getString("NOMSECT"));
                sectorM.setAREASECT(rs.getDouble("AREASECT"));
                ubigeoM.setCODUBI(rs.getString("CODUBI"));
                ubigeoM.setNOMDIST(rs.getString("NOMDIST"));
                sectorM.setCODUBI(ubigeoM);
                sectorM.setESTSECT(rs.getString("ESTSECT"));
                listaSector.add(sectorM);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listaSector;
    }

    public List<Sector> completeNomsect(String nomsect) throws Exception {
        List<Sector> listaSector;
        Sector sectorM;
        Ubigeo ubigeoM;
        Valle valleM;
        this.conectar();
        try {
            String sql = "Select * from SECTOR where NOMSECT like '%" + nomsect + "%'";
            listaSector = new ArrayList();
            Statement st = getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                sectorM = new Sector();
                valleM = new Valle();
                ubigeoM = new Ubigeo();
                sectorM.setIDSECT(rs.getLong("NUMSECT"));
                sectorM.setNOMSECT(rs.getString("NOMSECT"));
                sectorM.setAREASECT(rs.getDouble("AREASECT"));
                valleM.setIdvall(rs.getLong("IDVALL"));
                valleM.setNomvall(rs.getString("NOMVALL"));
                sectorM.setIDVALL(valleM);
                ubigeoM.setCODUBI(rs.getString("CODUBI"));
                sectorM.setCODUBI(ubigeoM);
                listaSector.add(sectorM);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listaSector;
    }

    public Sector find(Long id) throws Exception {
        this.conectar();
        Sector sectorM = new Sector();
        Valle valleM;
        Ubigeo ubigeoM;
        try {
            String sql = "Select * from sector where ESTSECT = 'A' and IDSECT = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ubigeoM = new Ubigeo();
                valleM = new Valle();
                sectorM.setIDSECT(rs.getLong("IDSECT"));
                sectorM.setNOMSECT(rs.getString("NOMSECT"));
                sectorM.setAREASECT(rs.getDouble("AREASECT"));
                valleM.setIdvall(rs.getLong("IDVALL"));
                sectorM.setIDVALL(valleM);
                sectorM.setESTSECT(rs.getString("ESTSECT"));
                break;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return sectorM;
    }

    public boolean countSector(String sector) throws SQLException {
        this.conectar();
        try {
            String sql = "Select IDSECT, NOMSECT from SECTOR where NOMSECT = '" + sector + "'";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                this.Cerrar();
                return true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return false;
    }

}
