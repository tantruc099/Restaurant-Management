/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author Lenovo
 */
public class BanPojo {

    public int getMaban() {
        return maban;
    }

    public void setMaban(int maban) {
        this.maban = maban;
    }

    public String getTenban() {
        return tenban;
    }

    public void setTenban(String tenban) {
        this.tenban = tenban;
    }

    public BanPojo() {
    }

    int maban;
    String tenban;


    public String toString() {
        return tenban; // Hiển thị tên trong ComboBox
    }
}
