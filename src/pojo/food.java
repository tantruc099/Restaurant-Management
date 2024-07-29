/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author MyPC
 */
public class food {

    public int getMaThucPham() {
        return MaThucPham;
    }

    public void setMaThucPham(int MaThucPham) {
        this.MaThucPham = MaThucPham;
    }

    public String getTenThucPham() {
        return TenThucPham;
    }

    public void setTenThucPham(String TenThucPham) {
        this.TenThucPham = TenThucPham;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }

    public food() {
    }
    private int MaThucPham;
   private String TenThucPham;
   private String GhiChu;
  private float Gia;   
}
