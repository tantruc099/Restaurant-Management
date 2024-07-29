/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MySQLDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Nguyenlieu;

/**
 *
 * @author MyPC
 */
public class NguyenlieuDAO {

    public static ArrayList<Nguyenlieu> laydulieunguyenlieu() {
        try {
            ArrayList<Nguyenlieu> list = new ArrayList<>();
            String sql = "Select * from NGUYEN_LIEU";
            ConnectDB provider = new ConnectDB();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                Nguyenlieu Nguyenlieu = new Nguyenlieu();
                Nguyenlieu.setMaNguyenLieu(rs.getInt(1));
                Nguyenlieu.setTenNguyenLieu(rs.getString(2));
                Nguyenlieu.setSoluongton(rs.getInt(3));

                list.add(Nguyenlieu);
            }
            provider.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(MenuFood.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static boolean insertNguyenlieu(Nguyenlieu nguyenlieu) {

        String sqlInsert = "INSERT INTO NGUYEN_LIEU (TenNguyenLieu, SoLuongTon) VALUES (?, ?)";
        ConnectDB provider = new ConnectDB();
        Connection connection = null;
        PreparedStatement statementInsert = null;
        boolean result = false;

        try {

            connection = provider.open();

            // Prepare and execute the INSERT statement
            statementInsert = connection.prepareStatement(sqlInsert);
            statementInsert.setString(1, nguyenlieu.getTenNguyenLieu());
            statementInsert.setInt(2, nguyenlieu.getSoluongton());
            int rowsAffected = statementInsert.executeUpdate();
            if (rowsAffected > 0) {
                NguyenlieuDAO.resetIdentity();
            }
            result = rowsAffected > 0;
        } catch (SQLException ex) {
           // Logger.getLogger(NguyenlieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (statementInsert != null) {
                    statementInsert.close();
                }
                if (connection != null) {
                    provider.close();
                }
            } catch (SQLException ex) {
                //Logger.getLogger(NguyenlieuDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;
    }
//    public static boolean insertNguyenlieu(Nguyenlieu nguyenlieu) {
//    String sqlInsert = "INSERT INTO NGUYEN_LIEU (TenNguyenLieu, SoLuongTon) VALUES (?, ?)";
//    ConnectDB provider = new ConnectDB();
//    Connection connection = null;
//    PreparedStatement statementInsert = null;
//    boolean result = false;
//
//    try {
//        connection = provider.open();
//
//         Prepare and execute the INSERT statement
//        statementInsert = connection.prepareStatement(sqlInsert);
//        statementInsert.setString(1, nguyenlieu.getTenNguyenLieu());
//        statementInsert.setInt(2, nguyenlieu.getSoluongton());
//
//        int rowsAffected = statementInsert.executeUpdate();
//        result = rowsAffected > 0;
//    } catch (SQLException ex) {
//        Logger.getLogger(NguyenlieuDAO.class.getName()).log(Level.SEVERE, null, ex);
//    } finally {
//        try {
//            if (statementInsert != null) {
//                statementInsert.close();
//            }
//            if (connection != null) {
//                provider.close();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(NguyenlieuDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    return result;
//}
//    public static boolean insertNguyenlieu(Nguyenlieu nguyenlieu) {
//        String sqlInsert = "INSERT INTO NGUYEN_LIEU (TenNguyenLieu, SoLuongTon) VALUES (?, ?)";
//        ConnectDB provider = new ConnectDB();
//        Connection connection = null;
//        PreparedStatement statementInsert = null;
//        boolean result = false;
//
//        try {
//            connection = provider.open();
//
//            // Prepare and execute the INSERT statement
//            statementInsert = connection.prepareStatement(sqlInsert);
//            statementInsert.setString(1, nguyenlieu.getTenNguyenLieu());
//            statementInsert.setInt(2, nguyenlieu.getSoluongton());
//
//            int rowsAffected = statementInsert.executeUpdate();
//            if (rowsAffected > 0) {
//                result = true;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(NguyenlieuDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (statementInsert != null) {
//                    statementInsert.close();
//                }
//                if (connection != null) {
//                    provider.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(NguyenlieuDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        // Reset identity after closing the connection
//        if (result) {
//            provider.resetIdentity("NGUYEN_LIEU");
//        }
//
//        return result;
//    }

    public static boolean xoanguyenlieu(int manglieu) {
        boolean result;
        String sql = String.format("Delete from NGUYEN_LIEU where MaNguyenLieu = %d", manglieu);
        ConnectDB provider = new ConnectDB();
        provider.open();
        result = (provider.executeUpdate(sql) == 1);
        provider.close();
        return result;
    }
////    public static boolean xoanguyenlieu(String tenNguyenLieu) {
////        boolean result = false;
////        String sql = "DELETE FROM NGUYEN_LIEU WHERE TenNguyenLieu = ?";
////
////        ConnectDB provider = new ConnectDB();
////        Connection connection = null;
////        PreparedStatement statement = null;
////
////        try {
////            connection = provider.open();
////            statement = connection.prepareStatement(sql);
////            statement.setString(1, tenNguyenLieu);
////
////            result = (statement.executeUpdate() == 1);
////        } catch (SQLException ex) {
////            Logger.getLogger(MenuFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
////        } finally {
////            try {
////                if (statement != null) {
////                    statement.close();
////                }
////                if (connection != null) {
////                    provider.close();
////                }
////            } catch (SQLException ex) {
////                Logger.getLogger(MenuFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
////            }
////        }
////        return result;
////    }

//    
    public static boolean capNhatnguyenlieu(int maNguyenLieu, String nameIngredient, int quantity) {
        String sql2 = "UPDATE NGUYEN_LIEU SET TenNguyenLieu = ?, SoLuongTon = ? WHERE MaNguyenLieu = ?";
        boolean result = false;

        ConnectDB provider = new ConnectDB();

        try {
            provider.open();
            Connection conn = provider.getConnection();

            try (PreparedStatement pstmt = conn.prepareStatement(sql2)) {
                pstmt.setString(1, nameIngredient);
                pstmt.setInt(2, quantity);
                pstmt.setInt(3, maNguyenLieu);
                result = (pstmt.executeUpdate() == 1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception properly in real applications
        } finally {
            provider.close();
        }

        return result;
    }
//      public static boolean capNhatnguyenlieu(int maNguyenLieu, String nameIngredient, int quantity) {
//        String sqlUpdate = "UPDATE NGUYEN_LIEU SET TenNguyenLieu = ?, SoLuongTon = ? WHERE MaNguyenLieu = ?";
//        try (ConnectDB provider = new ConnectDB();
//             Connection connection = provider.open();
//             PreparedStatement pstmt = connection.prepareStatement(sqlUpdate)) {
//            pstmt.setString(1, nameIngredient);
//            pstmt.setInt(2, quantity);
//            pstmt.setInt(3, maNguyenLieu);
//            return pstmt.executeUpdate() > 0;
//        } catch (SQLException ex) {
//            Logger.getLogger(NguyenlieuDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }

    public static int GetId() {

        try {
            String sql = "SELECT COUNT(*) AS Count FROM NGUYEN_LIEU";
            int count = 0;
            ConnectDB provider = new ConnectDB();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);

            // Lấy kết quả từ ResultSet
            if (rs.next()) {
                count = rs.getInt("Count");
            }

            // Đóng ResultSet, Statement và kết nối
            rs.close();
            provider.close();
            return count;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static void resetIdentity() {
        ConnectDB provider = new ConnectDB();
        provider.open();
        boolean success = provider.resetIdentity("NGUYEN_LIEU"); // Thay "NGUYEN_LIEU" bằng tên bảng bạn muốn reset identity
        provider.close();
        if (success) {
            System.out.println("Reset identity thành công!");
        } else {
            System.out.println("Reset identity thất bại!");
        }
    }

    public static ArrayList<Nguyenlieu> timkiemNguyenlieu(Integer maNguyenLieu, String tenNguyenLieu) {
        ArrayList<Nguyenlieu> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM NGUYEN_LIEU WHERE 1=1");

        if (maNguyenLieu != null) {
            sql.append(" AND MaNguyenLieu = ?");
        }
        if (tenNguyenLieu != null && !tenNguyenLieu.isEmpty()) {
            sql.append(" AND TenNguyenLieu LIKE ?");
        }

        ConnectDB provider = new ConnectDB();
        try {
            provider.open();
            Connection connection = provider.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (maNguyenLieu != null) {
                statement.setInt(paramIndex++, maNguyenLieu);
            }
            if (tenNguyenLieu != null && !tenNguyenLieu.isEmpty()) {
                statement.setString(paramIndex++, "%" + tenNguyenLieu + "%");
            }

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Nguyenlieu nguyenlieu = new Nguyenlieu();
                nguyenlieu.setMaNguyenLieu(rs.getInt("MaNguyenLieu"));
                nguyenlieu.setTenNguyenLieu(rs.getString("TenNguyenLieu"));
                nguyenlieu.setSoluongton(rs.getInt("SoLuongTon"));
                list.add(nguyenlieu);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(NguyenlieuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            provider.close();
        }

        return list;
    }
    
}
