/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
import hoadon.CTietHD;
import java.util.*;
/**
 *
 * @author Nhat
 */
public class DsCTietHD {
    public DsCTietHD() {
    }

    public ArrayList<CTietHD> getDs() {
        ArrayList<CTietHD> ds=new ArrayList<>();
        String sql ="Select * from CTietHD";
        
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                CTietHD ct=maptoCthd(rs);
                ds.add(ct);
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public CTietHD maptoCthd(ResultSet rs) throws SQLException{
        String MaHD =rs.getString("MaHD");
        String MaKHDi =rs.getString("MaKHDi");
        Float GiaVe =rs.getFloat("GiaVe");
        int sl=rs.getInt("Sl");
        return new CTietHD(MaHD,MaKHDi,GiaVe,sl);
    }
    
    public CTietHD TimHD(String mahd){
        String sql = "Select * from CTietHD where mahd=?";
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
                ps.setString(1, mahd);
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    return maptoCthd(rs);
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean themCtietHD(CTietHD ct){
        String sqlcheck ="Select * from ctiethd where mahd=? and makhang=?";
        String sqlinsert ="Insert into ctiethd(mahd,makhang,giave,sl) Values(?,?,?,?)";
        
        try(Connection conn=KetNoiCSDL.getConnection()){
            try(PreparedStatement ps=conn.prepareStatement(sqlcheck)){
                ps.setString(1, ct.getMaHD());
                ps.setString(2, ct.getMaKHDi());
                ResultSet rs=ps.executeQuery();;
                if(rs.next()){
                    return false;
                }
            }
            try(PreparedStatement ps=conn.prepareStatement(sqlinsert)){
                ps.setString(1, ct.getMaHD());
                ps.setString(2, ct.getMaKHDi());
                ps.setFloat(3, ct.getGiaVe());
                ps.setInt(4, ct.getSoLuong());
                return ps.executeUpdate()>0;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean xoaCtietHd(String mact,String makh){
        String sqlxoa= "Delete from CTietHD where mahd=? and makhang=?";
        try(Connection conn =KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sqlxoa)){
            ps.setString(1,mact);
            ps.setString(2, makh);
            return ps.executeUpdate()>0;
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean suaCthd(CTietHD ct){
        String sql = "Update CTietHD set giave=?,sl=? where mahd=? and makhang=?";
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setFloat(1,ct.getGiaVe() );
            ps.setInt(2, ct.getSoLuong());
            ps.setString(3, ct.getMaHD());
            ps.setString(4, ct.getMaKHDi());
            
            return ps.executeUpdate()>0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
