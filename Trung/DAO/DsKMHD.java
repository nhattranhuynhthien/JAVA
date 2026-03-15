package DAO;

import DTO.KMHD;

import java.sql.*;

import java.util.*;

public class DsKMHD {
    public ArrayList<KMHD> getDsKMHD() {
        ArrayList<KMHD> list = new ArrayList<>();
        try (Connection conn = KetNoiCSDL.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM KMHD")) {
            while (rs.next()) {
                KMHD kmhd = new KMHD(
                        rs.getString("maKM"),
                        rs.getString("tenKM"),
                        rs.getString("ngayBD"),
                        rs.getString("ngayKT"),
                        rs.getBoolean("hinhThucKM"),
                        rs.getFloat("chietKhau"),
                        rs.getString("ghiChu"),
                        rs.getString("maHD"),
                        rs.getFloat("tongTienApDung")

                );
                list.add(kmhd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public DsKMHD() {
    }

    public KMHD timKMHD(String maKM) {
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM KMHD WHERE maKM = ?")) {
            pstmt.setString(1, maKM);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new KMHD(
                            rs.getString("maKM"),
                            rs.getString("tenKM"),
                            rs.getString("ngayBD"), 
                            rs.getString("ngayKT"),
                            rs.getBoolean("hinhThucKM"),
                            rs.getFloat("chietKhau"),
                            rs.getString("ghiChu"),
                            rs.getString("maHD"),
                            rs.getFloat("tongTienApDung")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();    
        }
        return null;
    }

    public KMHD maptoKMHD(ResultSet rs) throws SQLException {
        return new KMHD(
                rs.getString("maKM"),
                rs.getString("tenKM"),
                rs.getString("ngayBD"),
                rs.getString("ngayKT"),
                rs.getBoolean("hinhThucKM"),
                rs.getFloat("chietKhau"),
                rs.getString("ghiChu"),
                rs.getString("maHD"),
                rs.getFloat("tongTienApDung")
        );
    }

    public ArrayList<KMHD> getDsKMHDTheoNgay(String ngay) {
        ArrayList<KMHD> list = new ArrayList<>();
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM KMHD WHERE ngayBD <= ? AND ngayKT >= ?")) {
            pstmt.setString(1, ngay);
            pstmt.setString(2, ngay);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    KMHD kmhd = new KMHD(
                            rs.getString("maKM"),
                            rs.getString("tenKM"),
                            rs.getString("ngayBD"),
                            rs.getString("ngayKT"),
                            rs.getBoolean("hinhThucKM"),
                            rs.getFloat("chietKhau"),
                            rs.getString("ghiChu"),
                            rs.getString("maHD"),
                            rs.getFloat("tongTienApDung")
                    );
                    list.add(kmhd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public ArrayList<KMHD> getDsKMHDTheoMaHD(String maHD) {
        ArrayList<KMHD> list = new ArrayList<>();
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM KMHD WHERE maHD = ?")) {
            pstmt.setString(1, maHD);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    KMHD kmhd = new KMHD(
                            rs.getString("maKM"),
                            rs.getString("tenKM"),
                            rs.getString("ngayBD"),
                            rs.getString("ngayKT"),
                            rs.getBoolean("hinhThucKM"),
                            rs.getFloat("chietKhau"),
                            rs.getString("ghiChu"),
                            rs.getString("maHD"),
                            rs.getFloat("tongTienApDung")
                    );
                    list.add(kmhd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public boolean themKMHD(KMHD kmhd) {
        String sql = "INSERT INTO KMHD (maKM, tenKM, ngayBD, ngayKT, hinhThucKM, ghiChu, maHD, chietKhau, tongTienApDung) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = KetNoiCSDL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, kmhd.getMaKM());
            pstmt.setString(2, kmhd.getTenKM());
            pstmt.setString(3, kmhd.getNgayBD());
            pstmt.setString(4, kmhd.getNgayKT());
            pstmt.setBoolean(5, kmhd.getHinhThucKM());
            pstmt.setString(6, kmhd.getGhiChu());
            
            // Xử lý maHD có thể null
            if (kmhd.getMaHD() == null || kmhd.getMaHD().isEmpty()) {
                pstmt.setNull(7, Types.VARCHAR);
            } else {
                pstmt.setString(7, kmhd.getMaHD());
            }
            
            pstmt.setFloat(8, kmhd.getChietKhau());
            pstmt.setFloat(9, kmhd.getTongTienApDung());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
}

    public boolean xoaKMHD(String maKM) {
        String sql = "DELETE FROM KMHD WHERE maKM = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maKM);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaKMHD(KMHD kmhd) {
    String sql = "UPDATE KMHD SET tenKM=?, ngayBD=?, ngayKT=?, hinhThucKM=?, ghiChu=?, maHD=?, chietKhau=?, tongTienApDung=? WHERE maKM=?";
    try (Connection conn = KetNoiCSDL.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, kmhd.getTenKM());
        pstmt.setString(2, kmhd.getNgayBD());
        pstmt.setString(3, kmhd.getNgayKT());
        pstmt.setBoolean(4, kmhd.getHinhThucKM());
        pstmt.setString(5, kmhd.getGhiChu());
        pstmt.setString(6, kmhd.getMaHD());
        pstmt.setFloat(7, kmhd.getChietKhau());
        pstmt.setFloat(8, kmhd.getTongTienApDung());
        pstmt.setString(9, kmhd.getMaKM());
        return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
