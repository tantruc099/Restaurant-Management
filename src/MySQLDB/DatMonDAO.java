package MySQLDB;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import pojo.BanPojo;

public class DatMonDAO {

    private static final Logger LOGGER = Logger.getLogger(DatMonDAO.class.getName());

    public static int GetIdHoaDon() {
        String query = "SELECT MAX(MaHoaDon) as MaxCount FROM HOA_DON";
        ConnectDB provider = new ConnectDB();
        Connection connect = null;
        ResultSet rs = null;
        try {
            connect = provider.open();
            PreparedStatement statement = connect.prepareStatement(query);
            rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("MaxCount");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public static int GetIdChiTietHoaDon() {
        String query = "SELECT MAX(MaChiTietHD) as MaxCount FROM CHI_TIET_HOA_DON";
        ConnectDB provider = new ConnectDB();
        Connection connect = null;
        ResultSet rs = null;
        try {
            connect = provider.open();
            PreparedStatement statement = connect.prepareStatement(query);
            rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("MaxCount");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public static ArrayList<BanPojo> getBan() {
        String query = "SELECT * FROM Ban";
        ArrayList<BanPojo> list = new ArrayList<>();
        ConnectDB provider = new ConnectDB();
        Connection connect = null;
        ResultSet rs = null;
        try {
            connect = provider.open();
            PreparedStatement statement = connect.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                BanPojo ban = new BanPojo();
                ban.setMaban(rs.getInt("Maban"));
                ban.setTenban(rs.getString("TenBan"));
                list.add(ban);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public static boolean insertChiTietHoaDon( int maHoaDon, int maThucPham, String tenDonHang, float gia, int soLuongDat) {
        try {
            String query = "INSERT INTO CHI_TIET_HOA_DON( MaHoaDon, MaThucPham, TenDonHang, Gia, SoLuongDat) VALUES ( ?, ?,?, ?, ?)";
            ConnectDB provider = new ConnectDB();
            Connection connection = provider.open();
            PreparedStatement statementInsert = connection.prepareStatement(query);
            statementInsert.setInt(1, maHoaDon);
            statementInsert.setInt(2, maThucPham);
            statementInsert.setString(3, tenDonHang);
            statementInsert.setFloat(4, gia);
            statementInsert.setInt(5, soLuongDat);
            int rowsAffected = statementInsert.executeUpdate();
            provider.close();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DatMonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean insertHoaDon(int maBan, float tongTien, String thoiGianHoaDon) {
        String query = "INSERT INTO HOA_DON (MaBan, TongTien, ThoiGianHoaDon) VALUES (?, ?, ?)";
        ConnectDB provider = new ConnectDB();
        Connection connect = null;
        try {
            connect = provider.open();
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setInt(1, maBan);
            statement.setFloat(2, tongTien);
            statement.setString(3, thoiGianHoaDon);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
