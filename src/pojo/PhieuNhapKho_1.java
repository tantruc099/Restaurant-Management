/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

import java.util.Date;

/**
 *
 * @author nguye
 */
public class PhieuNhapKho_1 {

    public int getManguyenlieu() {
        return manguyenlieu;
    }

    public void setManguyenlieu(int manguyenlieu) {
        this.manguyenlieu = manguyenlieu;
    }

    public String getTennguyenlieu() {
        return tennguyenlieu;
    }

    public void setTennguyenlieu(String tennguyenlieu) {
        this.tennguyenlieu = tennguyenlieu;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public PhieuNhapKho_1() {
    }

    public PhieuNhapKho_1(int manguyenlieu, String tennguyenlieu, int soluong, Date NgayNhap) {
        this.manguyenlieu = manguyenlieu;
        this.tennguyenlieu = tennguyenlieu;
        this.soluong = soluong;
        this.NgayNhap = NgayNhap;
    }
   private int manguyenlieu;
   private String tennguyenlieu;
   private int soluong;
   private Date NgayNhap;
   private String nhacc;

    public String getNhacc() {
        return nhacc;
    }

    public void setNhacc(String nhacc) {
        this.nhacc = nhacc;
    }

    public PhieuNhapKho_1(String nhacc) {
        this.nhacc = nhacc;
    }
}
