package org.example.dao;

import org.example.dto._CTietKHTourDTO;

import java.sql.*;
import java.util.ArrayList;

public class _CTietKHTourDAO {
    Connection c = _MyConnection.getConnection();
    Statement st = null;

    public _CTietKHTourDAO(){
        ArrayList<_CTietKHTourDAO> lsCTietKHTours = new ArrayList<>();
    }

    //get all tours
    public ArrayList<_CTietKHTourDTO> getAllCTietKHTours(){
        ArrayList<_CTietKHTourDTO> lsCTietKHTours = new ArrayList<>();
        try {
            String sql = "select * from ctietkhtour";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                _CTietKHTourDTO t = new _CTietKHTourDTO(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getLong(4),
                        rs.getLong(5),
                        rs.getLong(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)
                );
                lsCTietKHTours.add(t);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lsCTietKHTours;
    }

    //add
    public boolean addCTietKHTour(_CTietKHTourDTO t){
        try{
            String sql = "Insert into ctietkhtour values(";
            sql += "'" +  t.getMaCTietKHTour() + "'";
            sql += ","  + "'" +  t.getNgayThucHien() + "'";
            sql += ","  + "'" +  t.getTongChi() + "'";
            sql += ","  + "'" +  t.getTienO() + "'";
            sql += ","  + "'" +  t.getTienAn() + "'";
            sql += ","  + "'" +  t.getTienDiLai() + "'";
            sql += ","  + "'" +  t.getDiemDi() + "'";
            sql += ","  + "'" +  t.getDiemDen() + "'";
            sql += ","  + "'" +  t.getMaKHTour() + "'";
            sql += ")";
            st = c.createStatement();
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean removeCTietKHTour(String maCTietKHTour){
        try{
            String sql = "delete from ctietkhtour where mactietkhtour = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, maCTietKHTour);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //edit
    public boolean editCTietKHTour(_CTietKHTourDTO t){
        String id = t.getMaCTietKHTour();

        try{
            String qry = "update ctietkhtour set ";
            qry += "ngaythuchien = " + "'" + t.getNgayThucHien() + "'";
            qry += ",tongchi = " + "'" + t.getTongChi() + "'";
            qry += ",tieno = " + "'" + t.getTienO() + "'";
            qry += ",tienan = " + "'" + t.getTienAn() + "'";
            qry += ",tiendilai = " + "'" + t.getTienDiLai() + "'";
            qry += ",diemdi = " + "'" + t.getDiemDi() + "'";
            qry += ",diemden = " + "'" + t.getDiemDen() + "'";
            qry += ",makhtour = " + "'" + t.getMaKHTour() + "'";
            qry += "where mactietkhtour = '" + id + "';";
            st = c.createStatement();
            st.executeUpdate(qry);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
