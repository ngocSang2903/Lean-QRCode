/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.TypeCountryModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class TypeCountryRepo {

    public ArrayList<TypeCountryModel> all() {
        ArrayList<TypeCountryModel> list = new ArrayList<>();
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "select * from CountryType";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next() == true) {
                String idLoai = rs.getString("IdCountryType");
                String tenLoai = rs.getString("NameCountryType");
                TypeCountryModel typeCountryModel = new TypeCountryModel(idLoai, tenLoai);
                list.add(typeCountryModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(TypeCountryModel typeCountryModel) {
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "insert into CountryType(IdCountryType,NameCountryType) values (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, typeCountryModel.getIdLoaiNuoc());
            ps.setString(2, typeCountryModel.getTenLoaiNuoc());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(String id, TypeCountryModel typeCountryModel) {
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "update CountryType set NameCountryType=? where IdCountryType=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, typeCountryModel.getTenLoaiNuoc());
            ps.setString(2, id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "delete from CountryType where IdCountryType=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
