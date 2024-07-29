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
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Nguyenlieu;
import pojo.NhapKhoModel;

/**
 *
 * @author MyPC
 */
public class NhapkhoDAO {
    
    public static void addQuantity(ArrayList<NhapKhoModel> danhSach, int maNguyenLieu, int soLuongNhap,Date NgayNhap) {
        boolean found = false;
        for (NhapKhoModel nl : danhSach) {
            if (nl.getMaNguyenLieu() == maNguyenLieu) {
                nl.increaseQuantity(soLuongNhap);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không có nguyên liệu nào trùng khớp");
        }
    }
    
    public static ArrayList<NhapKhoModel> laydulieuNhapKho() {
        try {
            ArrayList<NhapKhoModel> list = new ArrayList<>();
            String sql = "Select * from Nhap_Kho";
            ConnectDB provider = new ConnectDB();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                NhapKhoModel Nguyenlieu = new NhapKhoModel();
                Nguyenlieu.setMaNhapKho(rs.getInt(1));
                Nguyenlieu.setMaNguyenLieu(rs.getInt(2));
                Nguyenlieu.setSoLuongNhap(rs.getInt(3));
                Nguyenlieu.setNgayNhap(rs.getDate(4));
                list.add(Nguyenlieu);
            }
            provider.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(MenuFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
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
    
    public static boolean insertKho(NhapKhoModel nhapkho) {
        try {
            String sqlInsert = "INSERT INTO NHAP_KHO ( MaNguyenLieu, SoLuongNhap,NgayNhap) VALUES (?, ?, ?)";
            
            ConnectDB provider = new ConnectDB();
            Connection connection = provider.open();

            // Thực hiện câu lệnh INSERT
            PreparedStatement statementInsert = connection.prepareStatement(sqlInsert);
            
            statementInsert.setInt(1, nhapkho.getMaNguyenLieu());
            statementInsert.setInt(2, nhapkho.getSoLuongNhap());
            statementInsert.setDate(3, (java.sql.Date) nhapkho.getNgayNhap());
            int rowsAffected = statementInsert.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(NhapkhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean xoanguyenlieu(int manglieu) {
        boolean result;
        String sql = String.format("Delete from NHAP_KHO where MaNhapKho = %d", manglieu);
        ConnectDB provider = new ConnectDB();
        provider.open();
        result = (provider.executeUpdate(sql) == 1);
        provider.close();
        return result;
    }

//    
//    public static boolean capNhatnguyenlieu(String IdWarehouseMaterial,String NameWarehouseMaterial, String QuantityToEnter) {
//        String sql2 = "Update WarehouseMaterial set QuantityToEnter ="+QuantityToEnter+"where IdWarehouseMaterial="+IdWarehouseMaterial;
//        boolean result;
//        System.out.println(sql2);        ConnectDB provider = new ConnectDB();
//        provider.open();
//        result = (provider.executeUpdate(sql2) == 1);
//        provider.close();
//        return result;
//    }
//    public static boolean capNhatNhapKho(int maNhapKho, int maNguyenLieu, int soLuongNhap) {
//        String sqlUpdate = "UPDATE Nhap_Kho SET MaNguyenLieu = ?, SoLuongNhap = ? WHERE MaNhapKho = ?";
//        ConnectDB provider = new ConnectDB();
//        
//        try (Connection connection = provider.open()) {
//            PreparedStatement statementUpdate = connection.prepareStatement(sqlUpdate);
//            statementUpdate.setInt(1, maNguyenLieu);
//            statementUpdate.setInt(2, soLuongNhap);
//            statementUpdate.setInt(3, maNhapKho);
//            
//            int rowsAffected = statementUpdate.executeUpdate();
//            return rowsAffected > 0; // Update successful if at least one row is affected
//
//        } catch (SQLException ex) {
//            Logger.getLogger(NhapkhoDAO.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//    }
//    
    public static boolean capNhatNhapKho(int maNhapKho, int maNguyenLieu, int soLuongNhap) {
    String sqlUpdate = "UPDATE Nhap_Kho SET SoLuongNhap = ? WHERE MaNhapKho = ? AND MaNguyenLieu = ?";
    ConnectDB provider = new ConnectDB();

    try (Connection connection = provider.open()) {
        PreparedStatement statementUpdate = connection.prepareStatement(sqlUpdate);
        statementUpdate.setInt(1, soLuongNhap);
        statementUpdate.setInt(2, maNhapKho);
        statementUpdate.setInt(3, maNguyenLieu);

        int rowsAffected = statementUpdate.executeUpdate();
        return rowsAffected > 0; // Update successful if at least one row is affected

    } catch (SQLException ex) {
        Logger.getLogger(NhapkhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}
    
    public static ArrayList<NhapKhoModel> timKiemNhapKho(String keyword) {
        try {
            ArrayList<NhapKhoModel> list = new ArrayList<>();
            // Corrected SQL query
            String sql = "SELECT * FROM Nhap_Kho WHERE MaNguyenLieu LIKE ? OR MaNhapKho LIKE ?";
            ConnectDB provider = new ConnectDB();
            provider.open();
            PreparedStatement statement = provider.getConnection().prepareStatement(sql);
            // Setting the parameters for the LIKE query
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                NhapKhoModel nhapKho = new NhapKhoModel();
                nhapKho.setMaNhapKho(rs.getInt("MaNhapKho"));
                nhapKho.setMaNguyenLieu(rs.getInt("MaNguyenLieu"));
                nhapKho.setSoLuongNhap(rs.getInt("SoLuongNhap"));
                list.add(nhapKho);
            }
            provider.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(NhapkhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
