package MySQLDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.datban;

public class datBan {
    
    public static void addQuantity(ArrayList<datban> danhSach, String Tenmon, String SoLuong) {
        boolean found = false;
        for (datban ban : danhSach) {
            if (ban.getTenmon().equals(Tenmon)) {
                ban.increaseQuantity(SoLuong);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không có món nào trùng khớp");
        }
    }

    public static ArrayList<datban> laydulieuMon() {
        ArrayList<datban> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM CHI_TIET_HOA_DON";
            ConnectDB provider = new ConnectDB();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                datban dat = new datban();
                dat.setTenmon(rs.getString("TenThucPham"));
                dat.setSoluong(rs.getString("SoLuongDat"));
                dat.setTien(rs.getFloat("Gia"));

                list.add(dat);
            }
            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(datBan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static int GetId() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS Count FROM CHI_TIET_HOA_DON";
            ConnectDB provider = new ConnectDB();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("Count");
            }
            rs.close();
            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(datBan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public static boolean insertMenu(int MaThucPham, String TenThucPham, float Gia, String SoLuongDat) {
        try {
            String sqlInsert = "INSERT INTO CHI_TIET_HOA_DON (TenThucPham, Gia, SoLuongDat) VALUES (?, ?, ?)";
            ConnectDB provider = new ConnectDB();
            Connection connection = provider.open();
            PreparedStatement statementInsert = connection.prepareStatement(sqlInsert);
            statementInsert.setString(1, TenThucPham);
            statementInsert.setFloat(2, Gia);
            statementInsert.setString(3, SoLuongDat);
            int rowsAffected = statementInsert.executeUpdate();
            provider.close();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(datBan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean xoaMon(int tenmon) {
        boolean result;
        String sql = "DELETE FROM CHI_TIET_HOA_DON WHERE MaThucPham = ?";
        try (Connection connection = new ConnectDB().open(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, tenmon);
            result = (preparedStatement.executeUpdate() == 1);
        } catch (SQLException ex) {
            Logger.getLogger(datBan.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        return result;
    }

    public static boolean capNhatMenu(int MaThucPham , String TenThucPham) {
        String sql2 = "UPDATE CHI_TIET_HOA_DON SET TenThucPham = ? WHERE MaThucPham = ?";
        try (Connection connection = new ConnectDB().open(); PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
            preparedStatement.setString(1, TenThucPham);
            preparedStatement.setInt(2, MaThucPham);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(datBan.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
