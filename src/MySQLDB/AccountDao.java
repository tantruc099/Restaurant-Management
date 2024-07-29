/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MySQLDB;
//alo
import MySQLDB.ConnectDB;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import pojo.Login;

/**
 *
 * @author nguye
 */
public class AccountDao {

    public static boolean checkLogin(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tài tài khoản và mật khẩu", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên đăng nhập", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {

            String sql = "SELECT * FROM DANG_NHAP WHERE TenNguoiDung = ? AND MatKhau= ?";
            ConnectDB provider = new ConnectDB();
            provider.open();
            PreparedStatement statement = provider.getConnection().prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                provider.close();
                return true;
            } else {
                provider.close();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, "Error while checking login", ex);
            return false;
        }

    }

    public static int getUserRole(String username, String password) {
        int role = -1;
        try {
            String sql = "SELECT VaiTro FROM DANG_NHAP WHERE TenNguoiDung = ? AND MatKhau= ?";
            ConnectDB con = new ConnectDB();
            con.open();
            PreparedStatement statement = con.getConnection().prepareStatement(sql);

            // Thiết lập giá trị của tham số id và username trong câu lệnh SQL
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            // Xử lý kết quả trả về
            if (resultSet.next()) {
                // Lấy giá trị của cột role từ kết quả truy vấn
                role = resultSet.getInt("VaiTro");
            }

            // Đóng các tài nguyên
            resultSet.close();
            statement.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi", "LỖi", JOptionPane.WARNING_MESSAGE);
        }

        return role;
    }

//    private static String hashPassword(String password) {
//        try {
//            // Sử dụng thuật toán SHA-256 để mã hóa mật khẩu
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(password.getBytes());
//
//            // Chuyển đổi byte array thành dạng hex string
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : hash) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) {
//                    hexString.append('0');
//                }
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException ex) {
//            // Xử lý ngoại lệ nếu thuật toán không được hỗ trợ
//            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, "Error while hashing password", ex);
//            return null;
//        }
//    }
    
    
    
}

