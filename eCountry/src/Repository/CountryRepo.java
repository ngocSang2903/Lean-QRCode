/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.CountryModel;
import Model.TypeCountryModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CountryRepo {

    public ArrayList<CountryModel> Combobox() {
        ArrayList<CountryModel> list = new ArrayList<>();
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "select * from CountryType";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {
                CountryModel countryModel = new CountryModel();
                countryModel.setIdnuoc(rs.getString("IdCountryType"));
                countryModel.setLoainuoc(rs.getString("NameCountryType"));
                list.add(countryModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<CountryModel> all() {
        ArrayList<CountryModel> list = new ArrayList<>();
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "select * from Country";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {
                CountryModel countryModel = new CountryModel();
                countryModel.setIdnuoc(rs.getString("IdCountry"));
                countryModel.setTennuoc(rs.getString("NameCountry"));
                countryModel.setGiatien(rs.getInt("Price"));
                countryModel.setLoainuoc(rs.getString("IdCountryType"));
                list.add(countryModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(CountryModel countryModel) {
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "insert into Country(IdCountry,NameCountry,Price,IdCountryType) values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, countryModel.getIdnuoc());
            ps.setString(2, countryModel.getTennuoc());
            ps.setDouble(3, countryModel.getGiatien());
            ps.setString(4, countryModel.getLoainuoc());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(String id, CountryModel countryModel) {
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "update Country set NameCountry=?,Price=?,IdCountryType=? where IdCountry=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(4, id);
            ps.setString(1, countryModel.getTennuoc());
            ps.setDouble(2, countryModel.getGiatien());
            ps.setString(3, countryModel.getLoainuoc());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "delete from Country where IdCountry=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
