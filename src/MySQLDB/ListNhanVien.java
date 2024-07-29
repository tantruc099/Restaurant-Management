/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MySQLDB;

/**
 *
 * @author User
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.NhanVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

public class ListNhanVien {
    
    public static ArrayList<NhanVien> layDSNV() {
        try {
            ArrayList<NhanVien> list = new ArrayList<>();
            String sql = "Select * from NHAN_VIEN";
            ConnectDB provider = new ConnectDB();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                NhanVien nv= new NhanVien();
                
                nv.setMaNhanVien(rs.getInt(1));
                nv.setTenNhanVien(rs.getString(2));
                nv.setGioiTinh(rs.getString(3));
                nv.setNgayVaoLam(rs.getTimestamp(4));
                nv.setDiaChi(rs.getString(5));
                nv.setSoDienThoai(rs.getString(6));
                nv.setLuong(rs.getDouble(7));
                
                list.add(nv);
            }
            provider.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ListNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public boolean kiemTraDuLieu(String MaNhanVien, String TenNhanVien, String GioiTinh, String NgayVaoLam, String DiaChi, String SoDienThoai, String Luong) {
        // Phương thức kiểm tra dữ liệu nhập vào
        if (MaNhanVien.isEmpty() || TenNhanVien.isEmpty() || GioiTinh.isEmpty() || NgayVaoLam.isEmpty() || DiaChi.isEmpty() || SoDienThoai.isEmpty() || Luong.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
        
    public boolean kiemTraTrungMaNV(String MaNhanVien) {
        // Phương thức kiểm tra trùng lặp mã sách
        String query = "SELECT MaNhanVien FROM NHAN_VIEN WHERE MaNhanVien = ?";
        
        ConnectDB provider = new ConnectDB();
        Connection conn = provider.open();
        try (
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, MaNhanVien);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Nếu có kết quả trả về, mã sách đã tồn tại
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi kiểm tra trùng mã nhân viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
        
    public boolean insertNV(String MaNhanVien, String TenNhanVien, String GioiTinh, String NgayVaoLam, String DiaChi, String SoDienThoai, String Luong) {

        if(!kiemTraDuLieu(MaNhanVien, TenNhanVien, GioiTinh, NgayVaoLam, DiaChi, SoDienThoai, Luong))
        {
            return false;
        }
        if(kiemTraTrungMaNV(MaNhanVien))
        {
            JOptionPane.showMessageDialog(null, "Mã nhân viên đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int luongInt = Integer.parseInt(Luong);
            if (luongInt <= 0) {
                JOptionPane.showMessageDialog(null, "Hãy kiểm tra lại lương nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Hãy kiểm tra lại lương nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String sqlInsert = "insert into NHAN_VIEN (MaNhanVien,TenNhanVien,GioiTinh,NgayVaoLam,DiaChi,SoDienThoai,Luong) values(?,?,?,?,?,?,?)";
        ConnectDB provider = new ConnectDB();
        try (Connection connection = provider.open();
            PreparedStatement statementInsert = connection.prepareStatement(sqlInsert)){
            


            statementInsert.setString(1, MaNhanVien);    
            statementInsert.setString(2, TenNhanVien);
            statementInsert.setString(3, GioiTinh);
            statementInsert.setString(4, NgayVaoLam);
            statementInsert.setString(5, DiaChi);
            statementInsert.setString(6, SoDienThoai);
            statementInsert.setString(7, Luong);
                

            int rowsAffected = statementInsert.executeUpdate();
            if(rowsAffected > 0)
            {
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm nhân viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean deleteNV(String MaNhanVien) {
        try {
            String sql = "DELETE FROM NHAN_VIEN WHERE MaNhanVien = ?";
            ConnectDB provider = new ConnectDB();
            Connection conn = provider.open();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, MaNhanVien);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

//    
//    public static boolean capNhatNV(int maNV, String IdFood, String Price, String Note) {
//        String sql2 = "Update Staff set NameFood = N'" + NameFood +"' where IdFood = " + IdFood;
//        boolean result;
//        System.out.println(sql2);
//        ConnectDB provider = new ConnectDB();
//        provider.open();
//        result = (provider.executeUpdate(sql2) == 1);
//        provider.close();
//        return result;
//    }
public boolean updateNV(String MaNhanVien, String TenNhanVien, String GioiTinh, String NgayVaoLam, String DiaChi, String SoDienThoai, String Luong) {
    if(!kiemTraDuLieu(MaNhanVien, TenNhanVien, GioiTinh, NgayVaoLam, DiaChi, SoDienThoai, Luong)) {
        return false;
    }

    if(!kiemTraTrungMaNV(MaNhanVien)) {
        JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    try {
        int luongInt = Integer.parseInt(Luong);
        if (luongInt <= 0) {
            JOptionPane.showMessageDialog(null, "Hãy kiểm tra lại lương nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Hãy kiểm tra lại lương nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    String sqlUpdate = "UPDATE NHAN_VIEN SET TenNhanVien = ?, GioiTinh = ?, NgayVaoLam = ?, DiaChi = ?, SoDienThoai = ?, Luong = ? WHERE MaNhanVien = ?";
    ConnectDB provider = new ConnectDB();
    try (Connection connection = provider.open();
         PreparedStatement statementUpdate = connection.prepareStatement(sqlUpdate)) {

        statementUpdate.setString(1, TenNhanVien);
        statementUpdate.setString(2, GioiTinh);
        statementUpdate.setString(3, NgayVaoLam);
        statementUpdate.setString(4, DiaChi);
        statementUpdate.setString(5, SoDienThoai);
        statementUpdate.setString(6, Luong);

        int rowsAffected = statementUpdate.executeUpdate();
        if (rowsAffected > 0) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật nhân viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    return false;
}

    
}
