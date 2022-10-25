/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.EmployeeModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class EmployeeRepo {

    public ArrayList<EmployeeModel> all() {
        ArrayList<EmployeeModel> list = new ArrayList<>();
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "select * from Employee";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String taiKhoan = rs.getString("UsernameEmp");
                String matKhau = rs.getString("Password");
                String tenNhanVien = rs.getString("EmployeeName");
                String gioiTinh = rs.getString("Gender");
                String ngaySinh = rs.getString("Birthday");
                String soDienThoai = rs.getString("Phone");
                String email = rs.getString("Email");
                String diaChi = rs.getString("Address");
                String hinh = rs.getString("Hinh");
                EmployeeModel employeeModel = new EmployeeModel(taiKhoan, matKhau, tenNhanVien, gioiTinh, ngaySinh, soDienThoai, email, diaChi, hinh);
                list.add(employeeModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(EmployeeModel employeeModel) {
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "insert into Employee(UsernameEmp,[Password],EmployeeName,Gender,Birthday,Phone,Email,[Address],Hinh) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employeeModel.getTaiKhoan());
            ps.setString(2, employeeModel.getMatKhau());
            ps.setString(3, employeeModel.getHoTen());
            ps.setString(4, employeeModel.getGioiTinh());
            ps.setString(5, employeeModel.getNgaySinh());
            ps.setString(6, employeeModel.getSoDienThoai());
            ps.setString(7, employeeModel.getEmail());
            ps.setString(8, employeeModel.getDiaChi());
            ps.setString(9, employeeModel.getHinh());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(String taiKhoan, EmployeeModel employeeModel) {
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "update Employee set [Password]=?,EmployeeName=?,Gender=?,Birthday=?,Phone=?,Email=?,[Address]=?,Hinh=? where UsernameEmp=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(9, taiKhoan);
            ps.setString(1, employeeModel.getMatKhau());
            ps.setString(2, employeeModel.getHoTen());
            ps.setString(3, employeeModel.getGioiTinh());
            ps.setString(4, employeeModel.getNgaySinh());
            ps.setString(5, employeeModel.getSoDienThoai());
            ps.setString(6, employeeModel.getEmail());
            ps.setString(7, employeeModel.getDiaChi());
            ps.setString(8, employeeModel.getHinh());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public void delete(String taiKhoan) {
        try {
            Connection conn = ConnecJDBC.Connections.getConnection();
            String sql = "delete from Employee where UsernameEmp=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, taiKhoan);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
