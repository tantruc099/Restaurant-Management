/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author MyPC
 */
public class Nguyenlieu {
    private int MaNguyenLieu;

    public int getMaNguyenLieu() {
        return MaNguyenLieu;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    public Nguyenlieu(int soluongton) {
        this.soluongton = soluongton;
    }

    public void setMaNguyenLieu(int MaNguyenLieu) {
        this.MaNguyenLieu = MaNguyenLieu;
    }

    public String getTenNguyenLieu() {
        return TenNguyenLieu;
    }

    public void setTenNguyenLieu(String TenNguyenLieu) {
        this.TenNguyenLieu = TenNguyenLieu;
    }

   

    public Nguyenlieu(int MaNguyenLieu, String TenNguyenLieu, int SoLuong) {
        this.MaNguyenLieu = MaNguyenLieu;
        this.TenNguyenLieu = TenNguyenLieu;
       
    }

    public Nguyenlieu() {
    }
    private String TenNguyenLieu;
    private int soluongton;
}
