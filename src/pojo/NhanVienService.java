/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import MySQLDB.ListNhanVien;

/**
 *
 * @author User
 */
public class NhanVienService {
    private ListNhanVien nvDAO;

    public NhanVienService() {
        this.nvDAO = new ListNhanVien();
    }

    public boolean deleteNV(String MaNhanVien) {
        return nvDAO.deleteNV(MaNhanVien);
    }
}
