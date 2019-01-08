/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubespbo.dao;

import com.mysql.jdbc.Connection;
import tubespbo.config.Koneksi;
import tubespbo.entity.Penduduk;
import tubespbo.service.ServicePenduduk;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aduar DAO = Data Access Object
 */
public class DaoPenduduk implements ServicePenduduk {

    private Connection connection;

    public DaoPenduduk() throws SQLException {
        connection = Koneksi.getConnection();
    }

    @Override
    public void insertPenduduk(Penduduk m) throws SQLException {
        PreparedStatement st = null;
        String sql = "INSERT INTO penduduk VALUE(?,?,?,?,?,?)";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, m.getNik());
            st.setString(2, m.getNama());
            st.setString(3, m.getLahir());
            st.setString(4, m.getJk());
            st.setString(5, m.getKota());
            st.setString(6, m.getAlamat());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (st != null) {
                st.close();
            }
        }

    }

    @Override
    public void updatePenduduk(Penduduk m) throws SQLException {
        PreparedStatement st = null;
        String sql = "UPDATE penduduk SET nama=?, lahir=?, jk=?, kota=?, alamat=? WHERE nik=?";
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, m.getNama());
            st.setString(2, m.getLahir());
            st.setString(3, m.getJk());
            st.setString(4, m.getKota());
            st.setString(5, m.getAlamat());
            st.setString(6, m.getNik());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (st != null) {
                st.close();
            }
        }

    }

    @Override
    public void deletePenduduk(Penduduk m) throws SQLException {
        PreparedStatement st = null;
        String sql = "DELETE FROM penduduk WHERE nik=?";

        try {
            st = connection.prepareCall(sql);
            st.setString(1, m.getNik());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (st != null) {
                st.close();
            }
        }

    }

    @Override
    public Penduduk getPendudukByNik(String nik) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        Penduduk m = null;
        String sql = "SELECT * FROM penduduk WHERE nik=?";

        try {
            
            
            st = connection.prepareStatement(sql);
            st.setString(1, nik);
            rs = st.executeQuery();
            while (rs.next()) {
                m = new Penduduk();
                m.setNik(rs.getString("nik"));
                m.setNama(rs.getString("nama"));
                m.setLahir(rs.getString("lahir"));
                m.setJk(rs.getString("jk"));
                m.setKota(rs.getString("kota"));
                m.setAlamat(rs.getString("alamat"));
            }
            return m;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    
    
    
    @Override
    public ArrayList<Penduduk> getPenduduk() throws SQLException {
        PreparedStatement st = null;
        ArrayList<Penduduk> listPenduduk = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM penduduk";

        try {
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Penduduk m = new Penduduk();
                m.setNik(rs.getString("nik"));
                m.setNama(rs.getString("nama"));
                m.setLahir(rs.getString("lahir"));
                m.setJk(rs.getString("jk"));
                m.setKota(rs.getString("kota"));
                m.setAlamat(rs.getString("alamat"));
                listPenduduk.add(m);
            }
            return listPenduduk;
        } catch (SQLException e) {
            throw e;
        } finally {
            if(st != null){
                st.close();
            }
            if(rs != null){
                rs.close();
            }
        }
    }

}
