package pojo;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MyPC
 */
public class LayHoaDon {

private int MaBan;    
private int MaHoaDon;
private double TongTien;
private Timestamp ThoiGianHoaDon; 
private int MaNhanVien;


    public LayHoaDon(int MaHoaDon, double TongTien, Timestamp ThoiGianHoaDon, int MaNhanVien, int MaBan) {

        this.MaHoaDon = MaHoaDon;
        this.TongTien = TongTien;
        this.ThoiGianHoaDon = ThoiGianHoaDon;
        this.MaNhanVien = MaNhanVien;
        this.MaBan = MaBan;
    }
    
    public LayHoaDon() {
    }

    
    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int MaBan) {
        this.MaBan = MaBan;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public Timestamp getThoiGianHoaDon() {
        return ThoiGianHoaDon;
    }

    public void setThoiGianHoaDon(Timestamp ThoiGianHoaDon) {
        this.ThoiGianHoaDon = ThoiGianHoaDon;
    }

    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(int MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }




}
