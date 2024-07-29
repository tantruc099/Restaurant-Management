/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MySQLDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.LayHoaDon;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JLabel;
import MySQLDB.ConnectDB;
import java.sql.Connection;
import java.sql.Timestamp;
import HoaDon.HoaDon;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import pojo.ChiTietHoaDon;

/**
 *
 * @author MyPC
 */




public class HoaDonDAO {

       
    
           public ArrayList<LayHoaDon> getAllLayHoaDon() {
            
            try
            {
                ArrayList<LayHoaDon> layHoaDonList = new ArrayList<>();
                String sql = "Select * from HOA_DON";
                
                ConnectDB provider = new ConnectDB();
                Connection connect = provider.open();
                ResultSet rs = provider.executeQuery(sql);
                
//                Connection connect = provider.open();
                while (rs.next()) {
                    LayHoaDon layHoaDon = new LayHoaDon();

                    
                    layHoaDon.setMaHoaDon(rs.getInt("MaHoaDon"));
                    layHoaDon.setTongTien(rs.getDouble("TongTien"));
                    layHoaDon.setThoiGianHoaDon(rs.getTimestamp("ThoiGianHoaDon"));
                    layHoaDon.setMaNhanVien(rs.getInt("MaNhanVien"));
                    layHoaDon.setMaBan(rs.getInt("MaBan"));

                    layHoaDonList.add(layHoaDon);
            }
            provider.close();
            return layHoaDonList;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
           
       public ArrayList<LayHoaDon> getLayHoaDon(int maBan) {
            
            try
            {
                ArrayList<LayHoaDon> layHoaDonList = new ArrayList<>();
                String sql = "Select * from HOA_DON where MaBan";
                
                ConnectDB provider = new ConnectDB();
                Connection connect = provider.open();
                ResultSet rs = provider.executeQuery(sql);
                
//                Connection connect = provider.open();
                while (rs.next()) {
                    LayHoaDon layHoaDon = new LayHoaDon();

                    
                    layHoaDon.setMaHoaDon(rs.getInt("MaHoaDon"));
                    layHoaDon.setTongTien(rs.getDouble("TongTien"));
                    layHoaDon.setThoiGianHoaDon(rs.getTimestamp("ThoiGianHoaDon"));
                    layHoaDon.setMaNhanVien(rs.getInt("MaNhanVien"));
                    layHoaDon.setMaBan(rs.getInt("MaBan"));

                    layHoaDonList.add(layHoaDon);
            }
            provider.close();
            return layHoaDonList;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
       public void loadChiTietHoaDonData(DefaultTableModel model) {
        try {
                String sql = "Select * from CHI_TIET_HOA_DON";
                ConnectDB provider = new ConnectDB();
                Connection connect = provider.open();
                ResultSet rs = provider.executeQuery(sql);


            model.setRowCount(0); // Xóa tất cả các dòng cũ

            while (rs.next()) {
                Object[] row = {
                    
                    rs.getInt("MaChiTietHD"),
                    rs.getInt("MaHoaDon"),
                    rs.getInt("MaThucPham"),
                    rs.getString("TenDonHang"),
                    rs.getDouble("Gia"),
                    rs.getInt("SoLuongDat")
                    
                    
                };
                model.addRow(row);
            }

            rs.close();
            provider.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
       
    public ArrayList<LayHoaDon> getHoaDonByMaBan(String maBan) {
        ArrayList<LayHoaDon> list = new ArrayList<>();
        String query = "SELECT MaBan, MaHoaDon, TongTien, ThoiGianHoaDon, MaNhanVien FROM HOA_DON WHERE MaBan = ?";
        ConnectDB provider = new ConnectDB();
        try (
                Connection connect = provider.open();
                PreparedStatement preparedStatement = connect.prepareStatement(query))
            {

            preparedStatement.setString(1, maBan);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
             //   list.add(new LayHoaDon(resultSet.getInt("MaBan"), resultSet.getInt("MaHoaDon"), resultSet.getDouble("TongTien"), resultSet.getTimestamp("ThoiGianHoaDon"), resultSet.getInt("MaNhanVien")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
            public ArrayList<ChiTietHoaDon> getCTHoaDonbyMaHoaDon(int MaHoaDon) {
            
            try
            {
                ArrayList<ChiTietHoaDon> layCTHoaDonList = new ArrayList<>();
                String sql = "Select * from CHI_TIET_HOA_DON where MaHoaDon = ?";
                
                ConnectDB provider = new ConnectDB();
                Connection connect = provider.open();
                ResultSet rs = provider.executeQuery(sql);
                
//                Connection connect = provider.open();
                while (rs.next()) {
                    ChiTietHoaDon layCTHoaDon = new ChiTietHoaDon();
                    
                    layCTHoaDon.setMaChiTietHD(rs.getInt("MaChiTietHD"));
                    layCTHoaDon.setMaHoaDon(rs.getInt("MaHoaDon"));
                    layCTHoaDon.setMaThucPham(rs.getInt("MaThucPham"));
                    layCTHoaDon.setTenDonHang(rs.getString("TenDonHang"));
                    layCTHoaDon.setGia(rs.getInt("Gia"));
                    layCTHoaDon.setSoLuongDat(rs.getInt("SoLuongDat"));

                    layCTHoaDonList.add(layCTHoaDon);
            }
            provider.close();
            return layCTHoaDonList;
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
       
//    public ArrayList<LayHoaDon> getBan() {
//        ArrayList<LayHoaDon> getBanList = new ArrayList<>();
//        try (
//                
//                String sql = "Select MaBan from HOA_DON";
//                ConnectDB provider = new ConnectDB();
//                Connection connect = provider.open();
//                ResultSet rs = provider.executeQuery(query); {
//
//            while (rs.next()) {
//                LayHoaDon layTable = new LayHoaDon();
//                
//                getBanList.add(rs.getInt("MaBan"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return getBanList;
//    }
//        public List<LayHoaDon> layMaBan() {
//            List<LayHoaDon> Tablelist = new ArrayList<>();
//            
//            try {
//
//                String sql = "Select MaBan from HOA_DON";
//
//                ConnectDB provider = new ConnectDB();
//                Connection connect = provider.open();
//                ResultSet rs = provider.executeQuery(sql);
//                while (rs.next()) {
//                    LayHoaDon maBan = new LayHoaDon();
//                    String maBan = Integer.toString(rs.getInt("MaBan"));
//                    
//                    Tablelist.add(maBan); // Thêm tên danh mục vào danh sách
//                }
//                provider.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            return Tablelist;
//        }
//       
//       
//       public List<LayHoaDon> getHoaDonByMaBan(String maBan) {
//            List<LayHoaDon> layHoaDonlist = new ArrayList<>(); 
//            
//            String sql = "SELECT HOA_DON.MaHoaDon, HOA_DON.TongTien, HOA_DON.ThoiGianHoaDon, HOA_DON.MaNhanVien"
//                + "FROM HOA_DON JOIN BAN ON HOA_DON.MaBan = BAN.MaBan "
//                + "WHERE DanhMucSach.TenDanhMuc = ?";
//           
//            try(Connection conn = open();
//                PreparedStatement pstmt = conn.prepareStatement(sql))
//
//            {
//
//            pstmt.setString(1, maBan);
//            ResultSet rs = pstmt.executeQuery();
//                while (rs.next()) {
//                    LayHoaDon layHoaDon = new LayHoaDon();
//
//                    
//                    layHoaDon.setMaHoaDon(rs.getInt("MaHoaDon"));
//                    layHoaDon.setTongTien(rs.getDouble("TongTien"));
//                    layHoaDon.setThoiGianHoaDon(rs.getTimestamp("ThoiGianHoaDon"));
//                    layHoaDon.setMaNhanVien(rs.getInt("MaNhanVien"));
//
//                    layHoaDonlist.add(layHoaDon);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Lỗi khi lọc hóa đơn theo só bàn: " + e.getMessage());
//        }
//
//        return layHoaDonlist;
//    }
}
