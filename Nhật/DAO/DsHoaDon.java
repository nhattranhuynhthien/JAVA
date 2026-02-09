/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import hoadon.CTietHD;
import hoadon.HoaDon;
import java.sql.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Nhat
 */
public class DsHoaDon {
    public ArrayList<HoaDon> getDsHoaDon(){
        ArrayList<HoaDon> list= new ArrayList<>();
        
        String sql="SELECT * FROM HoaDon";
        
        try(Connection conn=KetNoiCSDL.getConnection();
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery()){
        while(rs.next()){
            HoaDon hd=maptoHd(rs);
            list.add(hd);
        }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public DsHoaDon() {
    }
    public HoaDon timHoaDon(String mahd){
        String sql="SELECT * FROM HOADON where mahd=?";
        try(Connection conn=KetNoiCSDL.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, mahd);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return maptoHd(rs);
            }
        }catch(SQLException e){
                    e.printStackTrace();
                    }
        return null;
    }
    public HoaDon maptoHd(ResultSet rs) throws SQLException{
        String mahd=rs.getString("MaHD");
        String makhtour = rs.getString("MaKHTour");
        String makhdi = rs.getString("MaKHangDat");
        int tongtien =rs.getInt("TongTien");
        String manv=rs.getString("MaNV");
        return new HoaDon(mahd, makhtour, makhdi, manv, tongtien);
    }
    public boolean themHoaDon(HoaDon hd,ArrayList<CTietHD> listct){
        if(timHoaDon(hd.getMaHD())!=null){
            return false;
        }
        String sqlhd="Insert into hoadon(mahd,makhtour,makhangdat,tongtien,manv) values(?,?,?,?,?)";
        String sqlctiethd = "Insert into cthoadon(mahd,makhang,giave,sl) values(?,?,?,?)";
        Connection conn=null;
        
        try{
            conn=KetNoiCSDL.getConnection();
            conn.setAutoCommit(false);
            try(PreparedStatement ps=conn.prepareStatement(sqlhd)){
                ps.setString(1, hd.getMaHD());
                ps.setString(2, hd.getMaKHTour());
                ps.setString(3, hd.getMaKHDat());
                ps.setFloat(4, hd.getTongTien());
                ps.setString(5, hd.getMaNV());
                ps.executeUpdate();
            }
            try(PreparedStatement ps=conn.prepareStatement(sqlctiethd)){
                for(CTietHD cthd:listct){
                    ps.setString(1, hd.getMaHD());
                    ps.setString(2, cthd.getMaKHDi());
                    ps.setFloat(3,cthd.getGiaVe());
                    ps.setInt(4, cthd.getSoLuong());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            conn.commit();
            return true;
        }catch (SQLException ex) {
            ex.printStackTrace();
            if(conn!=null){
                try{
                    conn.rollback();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }finally{
            if(conn!=null){
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return false;
}
    
    public boolean xoaHd(HoaDon hd){
            String sqlct = "Delete from ctiethd where mahd=?";
            String sql="Delete from hoadon where mahd=?";
            
            Connection connection=null;
            try{
                connection=KetNoiCSDL.getConnection();
                connection.setAutoCommit(false);
                try(PreparedStatement ps=connection.prepareStatement(sqlct)){
                    ps.setString(1, hd.getMaHD());
                    ps.executeUpdate();
                }
                try(PreparedStatement ps=connection.prepareStatement(sql)){
                    ps.setString(1, hd.getMaHD());
                    int result=ps.executeUpdate();
                    if(result >0){
                    connection.commit();
                    return true;
                }else{
                        connection.rollback();
                        return false;
                        }
                }
            }catch (SQLException ex) {
            ex.printStackTrace();
            if(connection!=null){
                try{
                    connection.rollback();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }finally{
                if(connection!=null){
                    try{
                        connection.close();
                    }catch(SQLException e){
                        
                    }
                }
            }
          return false;
    }
    
    public boolean suaHd(HoaDon hd){
        String sqlhd ="Update hoadon set makhtour=?,makhangdi=?,tongtien=?,manv=? where mahd=?";
        String sqlcthd ="Update ctiethd set  mahd=?,makhang=?,giave=?,sl=? where mahd=?";
        
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlhd)){
            ps.setString(1, hd.getMaKHTour());
            ps.setString(2, hd.getMaKHDat());
            ps.setFloat(3, hd.getTongTien());
            ps.setString(4, hd.getMaNV());
            
            ps.setString(5, hd.getMaHD());
            return ps.executeUpdate()>0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public float laygia(String makht){
        float gia=0;
        String sql ="Select t.dongia from kehoachtour k join tour t on k.matour = t.matour where k.makhtour=?";
        try(Connection conn=KetNoiCSDL.getConnection();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, makht);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                gia=rs.getFloat("Dongia");
            }}
        catch(SQLException e){
                    e.printStackTrace();
                    }
        return gia;
        }
    
    }