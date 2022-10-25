/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.BillFormModel;
import Model.OrderDetailModel;
import Model.OrderModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BillFormRepo {

    public ArrayList<BillFormModel> all(String ten) {
        ArrayList<BillFormModel> list = new ArrayList<>();
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "Select IdCountry,NameCountry,Price,Country.IdCountryType "
                    + "from Country Join CountryType on Country.IdCountryType=CountryType.IdCountryType"
                    + " where Country.NameCountry=? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNuoc = rs.getString("IdCountry");
                String tenNuoc = rs.getString("NameCountry");
                String giaTien = rs.getString("Price");
                String maLoai = rs.getString("IdCountryType");
                BillFormModel billFormModel = new BillFormModel(maNuoc, tenNuoc, giaTien, maLoai, null, null);
                list.add(billFormModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    

    public void insertOrder(OrderModel orderModel) {
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "Insert into [Order] values(?,convert(varchar(20),getdate(),103),?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, orderModel.getIDorder());
            ps.setString(2, orderModel.getUserEmployee());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertOrderDetail(OrderDetailModel orderDetailModel) {
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "Insert into OrderDetail values(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, orderDetailModel.getIdOrder());
            ps.setString(2, orderDetailModel.getIdCountry());
            ps.setInt(3, orderDetailModel.getQuantity());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
