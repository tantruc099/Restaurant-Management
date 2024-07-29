/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author User
 */
public class ChiTietHoaDon {
    
    private int MaChiTietHD;
    private int MaHoaDon;
    private int MaThucPham;
    private String TenDonHang;
    private double Gia;
    private int SoLuongDat;
    

    public ChiTietHoaDon(int MaChiTietHD, int MaHoaDon,int MaThucPham, String TenDonHang, double Gia, int SoLuongDat) {
        this.MaChiTietHD = MaChiTietHD;
        this.MaHoaDon = MaHoaDon;
        this.MaThucPham = MaThucPham;
        this.TenDonHang = TenDonHang;
        this.Gia = Gia;
        this.SoLuongDat = SoLuongDat;
        
    }
    
    public ChiTietHoaDon()
    {
        
    }

    public int getMaChiTietHD() {
        return MaChiTietHD;
    }

    public void setMaChiTietHD(int MaChiTietHD) {
        this.MaChiTietHD = MaChiTietHD;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public int getMaThucPham() {
        return MaThucPham;
    }

    public void setMaThucPham(int MaThucPham) {
        this.MaThucPham = MaThucPham;
    }

    public String getTenDonHang() {
        return TenDonHang;
    }

    public void setTenDonHang(String TenDonHang) {
        this.TenDonHang = TenDonHang;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public int getSoLuongDat() {
        return SoLuongDat;
    }

    public void setSoLuongDat(int SoLuongDat) {
        this.SoLuongDat = SoLuongDat;
    }
    
    
 
    
    
    
}
