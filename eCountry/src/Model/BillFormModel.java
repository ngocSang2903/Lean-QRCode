/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class BillFormModel {

    private String MaNuoc, TenNuoc, GiaTien, MaLoai, soLuong, thanhTien;

    public BillFormModel() {
    }

    public BillFormModel(String MaNuoc, String TenNuoc, String GiaTien, String MaLoai, String soLuong, String thanhTien) {
        this.MaNuoc = MaNuoc;
        this.TenNuoc = TenNuoc;
        this.GiaTien = GiaTien;
        this.MaLoai = MaLoai;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    public String getMaNuoc() {
        return MaNuoc;
    }

    public void setMaNuoc(String MaNuoc) {
        this.MaNuoc = MaNuoc;
    }

    public String getTenNuoc() {
        return TenNuoc;
    }

    public void setTenNuoc(String TenNuoc) {
        this.TenNuoc = TenNuoc;
    }

    public String getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(String GiaTien) {
        this.GiaTien = GiaTien;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }

}
