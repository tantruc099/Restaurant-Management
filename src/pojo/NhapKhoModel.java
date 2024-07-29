package pojo;

import java.util.Date;

public class NhapKhoModel {
    private int MaNhapKho;
    private int MaNguyenLieu;
    private int SoLuongNhap;
    private Date ngayNhap; // Thêm thuộc tính ngayNhap

    public NhapKhoModel() {
    }

    public NhapKhoModel(int MaNhapKho, int MaNguyenLieu, int SoLuongNhap, Date ngayNhap) {
        this.MaNhapKho = MaNhapKho;
        this.MaNguyenLieu = MaNguyenLieu;
        this.SoLuongNhap = SoLuongNhap;
        this.ngayNhap = ngayNhap;
    }

    public int getMaNhapKho() {
        return MaNhapKho;
    }

    public void setMaNhapKho(int MaNhapKho) {
        this.MaNhapKho = MaNhapKho;
    }

    public int getMaNguyenLieu() {
        return MaNguyenLieu;
    }

    public void setMaNguyenLieu(int MaNguyenLieu) {
        this.MaNguyenLieu = MaNguyenLieu;
    }

    public int getSoLuongNhap() {
        return SoLuongNhap;
    }

    public void setSoLuongNhap(int SoLuongNhap) {
        this.SoLuongNhap = SoLuongNhap;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public void increaseQuantity(int additionalQuantity) {
        this.SoLuongNhap += additionalQuantity;
    }
}
