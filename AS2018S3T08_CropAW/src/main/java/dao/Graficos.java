package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.GraficosM;

public class Graficos extends Conexion {

    public List<GraficosM> listar() throws Exception {
        List<GraficosM> lista = null;
        ResultSet rs;
        this.conectar();
        try {
            String sql = "SELECT case TIPCUL when 'HT' then 'HORTALIZAS' \n"
                    + "when 'OT' THEN 'OTROS' \n"
                    + "when 'PM' THEN 'PERMANENTES' \n"
                    + "WHEN 'SP' THEN 'SEMI-PERMANENTES' \n"
                    + "ELSE 'TRANSITORIOS' END TIPCUL,\n"
                    + "COUNT (TIPCUL) AS COUNTTIPCUL  \n"
                    + "FROM CULTIVO \n"
                    + "where ESTACUL='A' \n"
                    + "GROUP BY TIPCUL\n"
                    + "ORDER BY COUNTTIPCUL DESC";
            PreparedStatement ps = getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                GraficosM graficosM = new GraficosM();
                graficosM.setTIPO(rs.getString("TIPCUL"));
                graficosM.setCOUNT(rs.getInt("COUNTTIPCUL"));
                lista.add(graficosM);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    public List<GraficosM> listarPer() throws Exception {
        List<GraficosM> lista = null;
        ResultSet rs;
        this.conectar();
        try {
            String sql = "select  TIPPER , count(TIPPER) AS COUNTTIPPER from PERSONA where ESTAPER='A' group by tipper";
            PreparedStatement ps = getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                GraficosM graficosM = new GraficosM();
                graficosM.setTIPO(rs.getString("TIPPER"));
                graficosM.setCOUNT(rs.getInt("COUNTTIPPER"));
                lista.add(graficosM);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    public List<GraficosM> listarContar() throws Exception {
        List<GraficosM> lista = null;
        ResultSet rs;
        this.conectar();
        try {
            String sql = "select * from VW_PERSONA,VW_CULTIVO,VW_SECTORCHAR";
            PreparedStatement ps = getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                GraficosM graficosM = new GraficosM();
                graficosM.setPERSONA(rs.getInt("PERSONA"));
                graficosM.setCULTIVO(rs.getInt("CULTIVO"));
                graficosM.setSECTOR(rs.getInt("SECTOR"));
                lista.add(graficosM);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    public List<GraficosM> listargraf() throws Exception {
        List<GraficosM> listado = null;
        ResultSet rs;
        this.conectar();
        try {
            String sql = "select i.FECINFO, sum(di.VERDMES) VERDMES  from detalle_informacion di\n"
                    + "inner join informacion i on i.IDINFO = di.IDINFO\n"
                    + "GROUP BY i.FECINFO\n"
                    + "ORDER BY i.FECINFO";
            PreparedStatement ps = getCn().prepareCall(sql);
            rs = ps.executeQuery();
            listado = new ArrayList();
            while (rs.next()) {
                GraficosM graficosM = new GraficosM();
                graficosM.setFECHA(rs.getDate("FECINFO"));
                graficosM.setSUM(rs.getInt("VERDMES"));
                listado.add(graficosM);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listado;
    }

    public List<GraficosM> listargrafcultasig() throws SQLException {
        List<GraficosM> listar = null;
        ResultSet rs;
        this.conectar();
        try {
            String sql = "select IDCUL,nomsect FROM(select count(IDCUL) as IDCUL ,s.nomsect from asignacion_cultivo AC\n"
                    + "inner join sector S ON s.idsect = ac.idsect\n"
                    + "GROUP BY s.nomsect\n"
                    + "ORDER BY IDCUL DESC)\n"
                    + "WHERE  rownum <= 5";
            PreparedStatement ps = getCn().prepareCall(sql);
            rs = ps.executeQuery();
            listar = new ArrayList<>();
            while (rs.next()) {
                GraficosM graficosM = new GraficosM();
                graficosM.setCOUNT(rs.getInt("IDCUL"));
                graficosM.setSector(rs.getString("nomsect"));
                listar.add(graficosM);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listar;
    }
}
