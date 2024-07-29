package MySQLDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.food;
import java.sql.Connection;
import java.sql.PreparedStatement;
import pojo.datban;
// import pojo.food; // Xóa dòng này vì đã import ở trên
/**
 *
 * @author MyPC
 */
public class MenuFood_1 {

    public static ArrayList<food> layDmenumon() {
        try {
            ArrayList<food> list = new ArrayList<>();
            String sql = "Select * from THUC_PHAM";
            ConnectDB provider = new ConnectDB();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                food food = new food();
                food.setMaThucPham(rs.getInt(1));
                food.setTenThucPham(rs.getString(2));
                food.setGhiChu(rs.getString(3));
                food.setGia(rs.getFloat(4));
                list.add(food);
            }
            provider.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(MenuFood_1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ArrayList<food> timKiemmenu(String tenmon) {
        try {
            ArrayList<food> list = new ArrayList<>();
            String sql = "Select * from THUC_PHAM where TenThucPham Like '%" + tenmon + "%'";
            ConnectDB provider = new ConnectDB();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                food food = new food();
                food.setMaThucPham(rs.getInt(1));
                food.setTenThucPham(rs.getString(2));
                food.setGhiChu(rs.getString(3));
                food.setGia(rs.getFloat(4));
                list.add(food);
            }
            provider.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(food.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static boolean insertFood(food food) {

        try {
            String sqlInsert = "insert into THUC_PHAM (TenThucPham,GhiChu,Gia) values(?,?,?)";

            ConnectDB provider = new ConnectDB();
            Connection connection = provider.open();

            // Thực hiện câu lệnh INSERT
            PreparedStatement statementInsert = connection.prepareStatement(sqlInsert);
            statementInsert.setString(1, food.getTenThucPham());
            statementInsert.setString(2, food.getGhiChu());
            statementInsert.setFloat(3, food.getGia());

            int rowsAffected = statementInsert.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            Logger.getLogger(MenuFood_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean xoaPmon(int mamonan) {
        boolean result;
        String sql = String.format("Delete from THUC_PHAM where MaThucPham = %d", mamonan);
        ConnectDB provider = new ConnectDB();
        provider.open();
        result = (provider.executeUpdate(sql) == 1);
        provider.close();
        return result;
    }

    public static boolean capNhatmonan(String TenThucPham, String MaThucPham, String Gia, String GhiChu) {
    try {
        int maThucPham = Integer.parseInt(MaThucPham);
        float gia = Float.parseFloat(Gia);

        String sql = "UPDATE THUC_PHAM SET TenThucPham = N'" + TenThucPham + "', Gia = " + gia + ", GhiChu = N'" + GhiChu + "' WHERE MaThucPham = " + maThucPham;

        ConnectDB provider = new ConnectDB();
        provider.open();

        int rowsAffected = provider.executeUpdate(sql);

        provider.close();

        return rowsAffected > 0;
    } catch (NumberFormatException ex) {
        // Xử lý ngoại lệ nếu không thể chuyển đổi thành công từ String sang số nguyên hoặc số thực
        ex.printStackTrace();
        return false;
    }
}
}
