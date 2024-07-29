/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package HoaDon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import DatMon.DatMon;
import MySQLDB.ConnectDB;
import MySQLDB.DatMonDAO;
import MySQLDB.HoaDonDAO;
import TrangChu.TrangChu;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import pojo.LayHoaDon;
import java.io.Serializable;
import java.sql.Timestamp;
import static javax.swing.UIManager.getInt;
import static javax.swing.UIManager.getString;
import pojo.BanPojo;
import pojo.ChiTietHoaDon;
import java.awt.*;
import MySQLDB.*;

/**
 *
 * @author nguye
 */
public class HoaDon extends javax.swing.JFrame {

    /**
     * Creates new form HoaDon
     */
    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtm2 = new DefaultTableModel();

    public HoaDon() {
        String Tieude[] = {"Mã Bàn", "Mã Hóa đơn", "Tổng tiền", "Thời gian", "Nhân viên"};
        dtm.setColumnIdentifiers(Tieude);

        String Tieude2[] = {"Mã Chi tiết hóa đơn", "Mã hóa đơn", "Mã thực phẩm", "Tên đơn hàng", "Giá bán", "Số lượng đặt"};
        dtm2.setColumnIdentifiers(Tieude2);

        initComponents();

        loadHoaDonData();

        loadChiTietHoaDonData();
        loadComboBoxData();
        setLocationRelativeTo(null);

        // loadTableData();
        cbo_SoBan.addActionListener(e -> updateHoaDonTable());

        tb_hd.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateChiTietHoaDonTable();
            }
        });
    }

    private void loadHoaDonData() {
        HoaDonDAO hoaDon = new HoaDonDAO();
        List<LayHoaDon> layHoaDon = hoaDon.getAllLayHoaDon();

        dtm.setRowCount(0);
        for (LayHoaDon pb : layHoaDon) {
            Vector<Object> v = new Stack<>();

            v.add(pb.getMaBan());
            v.add(pb.getMaHoaDon());
            v.add(pb.getTongTien());
            v.add(pb.getThoiGianHoaDon());
            v.add(pb.getMaNhanVien());

            dtm.addRow(v);
        }
        tb_hd.setModel(dtm);

    }

    private void loadChiTietHoaDonData() {

        HoaDonDAO chiTietHoaDonDAO = new HoaDonDAO();
        tb_cthd.setModel(dtm2);
        chiTietHoaDonDAO.loadChiTietHoaDonData((DefaultTableModel) tb_cthd.getModel());

    }

//    private void loadComboBoxData() {
//        // Xóa các mục hiện tại trong combobox
//
//        // Mảng các mã bàn (có thể được thay thế bằng dữ liệu lấy từ cơ sở dữ liệu)
//        String[] maBanList = {"1", "2", "3", "4", "5"};
//
//        // Thêm từng mã bàn vào combobox
//        for (String maBan : maBanList) {
//            cbo_SoBan.addItem(maBan);
//        }
//    }

    private void loadComboBoxData() {
        cbo_SoBan.removeAllItems(); // Xóa các mục hiện tại trong combobox

        ConnectDB provider = new ConnectDB(); // Tạo đối tượng kết nối
        Connection conn = provider.open(); // Mở kết nối đến cơ sở dữ liệu
        if (conn != null) {
            String query = "SELECT MaBan FROM BAN"; // Câu lệnh truy vấn
            try {
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery(); // Thực hiện truy vấn
                while (rs.next()) {
                    String maBan = rs.getString("MaBan"); // Lấy giá trị cột MaBan từ kết quả truy vấn
                    cbo_SoBan.addItem(maBan); // Thêm giá trị MaBan vào combobox
                }
                conn.close(); // Đóng kết nối sau khi hoàn thành
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to the database."); // Hiển thị thông báo nếu kết nối thất bại
        }
    }
//    private void loadTableData() {
//        cbo_SoBan.removeAllItems();
//
//        ConnectDB provider = new ConnectDB();
//        Connection conn = provider.open();
//        if (conn != null) {
//            String query = "SELECT MaBan FROM BAN";
//            try {
//                ResultSet rs = conn.createStatement().executeQuery(query);
//                while (rs.next()) {
//                    cbo_SoBan.addItem(String.valueOf(rs.getInt("MaBan")));
//                }
//                conn.close(); // Đóng kết nối sau khi hoàn thành
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        } else {
//            System.out.println("Failed to connect to the database.");
//        }
//    }
    private void updateHoaDonTable() {
        HoaDonDAO hoaDon = new HoaDonDAO();
        BanPojo selectedBan = (BanPojo) cbo_SoBan.getSelectedItem();
        if (selectedBan != null) {
            ArrayList<LayHoaDon> hoaDonList = hoaDon.getHoaDonByMaBan(String.valueOf(selectedBan.getMaban()));
            dtm.setRowCount(0); // Clear existing data
            for (LayHoaDon hd : hoaDonList) {
                dtm.addRow(new Object[]{hd.getMaBan(), hd.getMaHoaDon(), hd.getTongTien(), hd.getThoiGianHoaDon(), hd.getMaNhanVien()});
            }
        }
    }

    private void updateChiTietHoaDonTable() {
        HoaDonDAO hoaDon = new HoaDonDAO();
        int selectedRow = tb_hd.getSelectedRow();
        if (selectedRow != -1) {
            int MaHoaDon = (int) tb_hd.getValueAt(selectedRow, 0);
            ArrayList<ChiTietHoaDon> chiTietList = hoaDon.getCTHoaDonbyMaHoaDon(MaHoaDon);
            dtm2.setRowCount(0); // Clear existing data
            for (ChiTietHoaDon chiTiet : chiTietList) {
                dtm2.addRow(new Object[]{
                    chiTiet.getMaChiTietHD(),
                    chiTiet.getMaHoaDon(),
                    chiTiet.getMaThucPham(),
                    chiTiet.getTenDonHang(),
                    chiTiet.getGia(),
                    chiTiet.getSoLuongDat()

                });
            }
        }
    }
//    private void loadMaBanIntoComboBox() {
//        HoaDonDAO MaBanDao = new HoaDonDAO();
//        try {
//            List<String> Tablelist = MaBanDao.layMaBan();
//            cbo_SoBan.removeAllItems(); // Xóa các mục cũ trong combobox
//            for (String maBan : Tablelist) {
//                cbo_SoBan.addItem(maBan); // Thêm tên danh mục vào combobox
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu số bàn: " + e.getMessage());
//        }
//    }

//    private void filterCTHDbyMaBan(String maBan) {
//        DefaultTableModel model = (DefaultTableModel) tb_cthd.getModel();
//        model.setRowCount(0);
//        HoaDonDAO hoaDonDAO = new HoaDonDAO();
//        try {
//            List<LayHoaDon> layHoaDonlist = hoaDonDAO.getHoaDonByMaBan(maBan);
//            for (LayHoaDon layHoaDon : layHoaDonlist) {
//                model.addRow(new Object[]{
//                    layHoaDon.getMaHoaDon(),
//                    layHoaDon.getTongTien(),
//                    layHoaDon.getThoiGianHoaDon(),
//                    layHoaDon.getMaNhanVien()
//                });
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Lỗi khi lọc hóa đơn theo số bàn: " + e.getMessage());
//        }
//    }
// 
//    public void loadCbo() {
//        HoaDonDAO xu = new HoaDonDAO();
//        ArrayList<LayHoaDon> list = new ArrayList<>();
//        list = xu.layMaBan();
//
//        for (LayHoaDon MaBan : list) {
//            cbo_SoBan.addItem(getString(MaBan));  
//        }
//    }
//     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        canvas1 = new java.awt.Canvas();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_hd = new javax.swing.JTable();
        btnDatBan = new javax.swing.JButton();
        btnTrangChu = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_cthd = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        cbo_SoBan = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("Hóa Đơn");

        tb_hd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tb_hd);

        btnDatBan.setText("Đặt Bàn");
        btnDatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatBanActionPerformed(evt);
            }
        });

        btnTrangChu.setText("Về Trang chủ");
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });

        btnInHoaDon.setText("In hóa đơn");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        tb_cthd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tb_cthd);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setText("Chi Tiết Hóa Đơn");

        cbo_SoBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_SoBanActionPerformed(evt);
            }
        });

        jLabel1.setText("Bàn số");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(349, 349, 349)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDatBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                            .addComponent(btnInHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jLabel1)
                        .addGap(43, 43, 43)
                        .addComponent(cbo_SoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(btnDatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbo_SoBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
//        // Kiểm tra nếu bảng tblHoaDon trống
//        if (tb_cthd.getRowCount() == 0) {
//            JOptionPane.showMessageDialog(this, "Bảng hóa đơn đang trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        } else {
//            // Nếu không trống, tiến hành xuất hóa đơn
//            int response = JOptionPane.showConfirmDialog(this, "Xác nhận in hóa đơn?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//            if (response == JOptionPane.YES_OPTION) {
//                // Lấy mô hình dữ liệu từ bảng tblHoaDon
//                TableModel modelHoaDon = tb_cthd.getModel();
//                // Gọi phương thức generatePDF từ gói DAO để tạo và lưu tệp PDF từ mô hình dữ liệu
//                // Khởi tạo đối tượng của class entity
//                DatMonDAO.generatePDF(modelHoaDon); // truyền vào mã hóa đơn từ nơi nào đó
//            }
//        }
    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void btnDatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatBanActionPerformed
        // TODO add your handling code here:
        // Hiển thị hộp thoại xác nhận
        this.dispose();
        DatMon datMon = new DatMon();
        datMon.setVisible(true);
    }//GEN-LAST:event_btnDatBanActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        // TODO add your handling code here:
        this.dispose();
        TrangChu trangChu = new TrangChu();
        trangChu.setVisible(true);
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void cbo_SoBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_SoBanActionPerformed
        // TODO add your handling code here:
//        String selectedmaBan = (String) cbo_SoBan.getSelectedItem();
//        if (selectedmaBan != null) {
//            filterCTHDbyMaBan(selectedmaBan);
//        }
    }//GEN-LAST:event_cbo_SoBanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatBan;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnTrangChu;
    private java.awt.Canvas canvas1;
    private javax.swing.JComboBox<String> cbo_SoBan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tb_cthd;
    private javax.swing.JTable tb_hd;
    // End of variables declaration//GEN-END:variables
}
