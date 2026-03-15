package DAO;
import DTO.CTrinhKM;
import java.sql.*;
import java.util.*;
public class DsCTrinhKM {
    public ArrayList<CTrinhKM> getDsCTrinhKM() {
        ArrayList<CTrinhKM> list = new ArrayList<>();
        try (Connection conn = KetNoiCSDL.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM CTrinhKM")) {
            while (rs.next()) {
                CTrinhKM ct = new CTrinhKM(
                        rs.getString("maKM"),
                        rs.getString("tenKM"),
                        rs.getString("ngayBD"),
                        rs.getString("ngayKT"),
                        rs.getBoolean("hinhThucKM"),
                        rs.getFloat("chietKhau"),
                        rs.getString("ghiChu")
                );
                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public DsCTrinhKM() {
    }

    public CTrinhKM timCTrinhKM(String maKM) {
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM CTrinhKM WHERE maKM = ?")) {
            pstmt.setString(1, maKM);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new CTrinhKM(
                            rs.getString("maKM"),
                            rs.getString("tenKM"),
                            rs.getString("ngayBD"), 
                            rs.getString("ngayKT"),
                            rs.getBoolean("hinhThucKM"),
                            rs.getFloat("chietKhau"),
                            rs.getString("ghiChu")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CTrinhKM maptoCTrinhKM(ResultSet rs) throws SQLException {
        return new CTrinhKM(
                rs.getString("maKM"),
                rs.getString("tenKM"),
                rs.getString("ngayBD"),
                rs.getString("ngayKT"),
                rs.getBoolean ("hinhThucKM"),
                rs.getFloat("chietKhau"),
                rs.getString("ghiChu")
        );
    }
    public boolean themCTrinhKM(CTrinhKM ct) {
        String sql = "INSERT INTO CTrinhKM (maKM, tenKM, ngayBD, ngayKT, hinhThucKM,chietKhau, ghiChu) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ct.getMaKM());
            pstmt.setString(2, ct.getTenKM());
            pstmt.setString(3, ct.getNgayBD());
            pstmt.setString(4, ct.getNgayKT());
            pstmt.setBoolean(5,ct.getHinhThucKM() );
            pstmt.setFloat(6, ct.getChietKhau());
            pstmt.setString(7, ct.getGhiChu());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean xoaCTrinhKM(String maKM) {
        String sql = "DELETE FROM CTrinhKM WHERE maKM = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maKM);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean suaCTrinhKM(CTrinhKM ct) {
        String sql = "UPDATE CTrinhKM SET tenKM = ?, ngayBD = ?, ngayKT = ?, hinhThucKM = ?,chietKhau = ?, ghiChu = ? WHERE maKM = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ct.getTenKM());
            pstmt.setString(2, ct.getNgayBD());
            pstmt.setString(3, ct.getNgayKT());
            pstmt.setBoolean(4, ct.getHinhThucKM());
            pstmt.setFloat(5, ct.getChietKhau());
            pstmt.setString(6, ct.getGhiChu());
            pstmt.setString(7, ct.getMaKM());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<CTrinhKM> searchCTrinhKM(String loai, String keyword) {
        ArrayList<CTrinhKM> result = new ArrayList<>();
        String sql = "SELECT * FROM CTrinhKM WHERE 1=1";
        switch (loai) {
            case "Tất cả":
                sql += " AND (maKM LIKE ? OR tenKM LIKE ?)";
                break;
            case "Mã KM":
                sql += " AND maKM LIKE ?";
                break;
            case "Tên KM":
                sql += " AND tenKM LIKE ?";
                break;
            // Thêm các trường khác nếu cần
        }
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (loai.equals("Tất cả")) {
                pstmt.setString(1, "%" + keyword + "%");
                pstmt.setString(2, "%" + keyword + "%");
            } else {
                pstmt.setString(1, "%" + keyword + "%");
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.add(maptoCTrinhKM(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
