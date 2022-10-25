/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class CountryModel {
    private String Idnuoc;
    private String tennuoc;
    private String loainuoc;
    private double giatien;

    public CountryModel() {
    }

    public CountryModel(String Idnuoc, String tennuoc, String loainuoc, double giatien) {
        this.Idnuoc = Idnuoc;
        this.tennuoc = tennuoc;
        this.loainuoc = loainuoc;
        this.giatien = giatien;
    }

    public String getIdnuoc() {
        return Idnuoc;
    }

    public void setIdnuoc(String Idnuoc) {
        this.Idnuoc = Idnuoc;
    }

    public String getTennuoc() {
        return tennuoc;
    }

    public void setTennuoc(String tennuoc) {
        this.tennuoc = tennuoc;
    }

    public String getLoainuoc() {
        return loainuoc;
    }

    public void setLoainuoc(String loainuoc) {
        this.loainuoc = loainuoc;
    }

    public double getGiatien() {
        return giatien;
    }

    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }
        public String toString(){
        return loainuoc;
    }
}
