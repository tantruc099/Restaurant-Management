/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author User
 */
public class NhanVien {
    private int MaNhanVien;
    private String TenNhanVien,GioiTinh,DiaChi,SoDienThoai;
    private Timestamp NgayVaoLam;
    private double Luong; 

    public NhanVien(int MaNhanVien, String TenNhanVien, String GioiTinh, Timestamp NgayVaoLam, String DiaChi, String SoDienThoai, double Luong) {
        this.MaNhanVien = MaNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.GioiTinh = GioiTinh;
        this.NgayVaoLam = NgayVaoLam;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
        this.Luong = Luong;
        
        
    }

    public NhanVien() {
        
    }
    
    
    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(int MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public Timestamp getNgayVaoLam() {
        return NgayVaoLam;
    }

    public void setNgayVaoLam(Timestamp NgayVaoLam) {
        this.NgayVaoLam = NgayVaoLam;
    }

    public double getLuong() {
        return Luong;
    }

    public void setLuong(double Luong) {
        this.Luong = Luong;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }


    
}
