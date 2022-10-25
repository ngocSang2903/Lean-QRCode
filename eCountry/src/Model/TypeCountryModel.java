/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class TypeCountryModel {
    private String idLoaiNuoc,TenLoaiNuoc;

    public TypeCountryModel() {
    }

    public TypeCountryModel(String idLoaiNuoc, String TenLoaiNuoc) {
        this.idLoaiNuoc = idLoaiNuoc;
        this.TenLoaiNuoc = TenLoaiNuoc;
    }

    public String getIdLoaiNuoc() {
        return idLoaiNuoc;
    }

    public void setIdLoaiNuoc(String idLoaiNuoc) {
        this.idLoaiNuoc = idLoaiNuoc;
    }

    public String getTenLoaiNuoc() {
        return TenLoaiNuoc;
    }

    public void setTenLoaiNuoc(String TenLoaiNuoc) {
        this.TenLoaiNuoc = TenLoaiNuoc;
    }
    
}
