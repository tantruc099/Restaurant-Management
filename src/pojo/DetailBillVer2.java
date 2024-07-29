/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author Lenovo
 */
public class DetailBillVer2 {

    public int getMamon() {
        return mamon;
    }

    public void setMamon(int mamon) {
        this.mamon = mamon;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public DetailBillVer2(int mamon, String tenmon, float gia, int soluong) {
        this.mamon = mamon;
        this.tenmon = tenmon;
        this.gia = gia;
        this.soluong = soluong;
    }


    public DetailBillVer2() {
    }
    private int mamon;
    private String tenmon;
    private float gia;
    private int soluong;
}
