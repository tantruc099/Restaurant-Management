package pojo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MyPC
 */
public class HoaDonModel {
 private int IdDetailBill;
 private int IdBill;
 private int IdOrder;
 private String NameOrder;
 private int CountOrder;
 private Float Price;

    public int getIdDetailBill() {
        return IdDetailBill;
    }

    public void setIdDetailBill(int IdDetailBill) {
        this.IdDetailBill = IdDetailBill;
    }

    public int getIdBill() {
        return IdBill;
    }

    public void setIdBill(int IdBill) {
        this.IdBill = IdBill;
    }

    public int getIdOrder() {
        return IdOrder;
    }

    public void setIdOrder(int IdOrder) {
        this.IdOrder = IdOrder;
    }

    public String getNameOrder() {
        return NameOrder;
    }

    public void setNameOrder(String NameOrder) {
        this.NameOrder = NameOrder;
    }

    public int getCountOrder() {
        return CountOrder;
    }

    public void setCountOrder(int CountOrder) {
        this.CountOrder = CountOrder;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float Price) {
        this.Price = Price;
    }

    public HoaDonModel(int IdDetailBill, int IdBill, int IdOrder, String NameOrder, int CountOrder, Float Price) {
        this.IdDetailBill = IdDetailBill;
        this.IdBill = IdBill;
        this.IdOrder = IdOrder;
        this.NameOrder = NameOrder;
        this.CountOrder = CountOrder;
        this.Price = Price;
    }

    public HoaDonModel() {
    }
}
