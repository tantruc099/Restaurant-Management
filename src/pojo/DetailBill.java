/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author Lenovo
 */
public class DetailBill {

    public DetailBill(int MaChiTietHD, int MaHoaDon, int MaThucPham, String TenDonHang, float Gia, int SoLuongDat) {
        this.MaChiTietHD = MaChiTietHD;
        this.MaHoaDon = MaHoaDon;
        this.MaThucPham = MaThucPham;
        this.TenDonHang = TenDonHang;
        this.Gia = Gia;
        this.SoLuongDat = SoLuongDat;
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

    public String TenDonHang() {
        return TenDonHang;
    }

    public void TenDonHang(String TenDonHang) {
        this.TenDonHang = TenDonHang;
    }


    public float getGia() {
        return Gia;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }

    public int getSoLuongDat() {
        return SoLuongDat;
    }

    public void setSoLuongDat(int SoLuongDat) {
        this.SoLuongDat = SoLuongDat;
    }


    public DetailBill() {
    }
    int MaChiTietHD;
    int MaHoaDon;
    int MaThucPham;
    String TenDonHang;
    float Gia;
    int SoLuongDat;
}
