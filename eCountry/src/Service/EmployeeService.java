/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.EmployeeModel;
import Repository.EmployeeRepo;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class EmployeeService {

    private EmployeeRepo employeeRepo = new EmployeeRepo();

    public ArrayList<EmployeeModel> all() {
        return employeeRepo.all();
    }

    public void insert(EmployeeModel employeeModel) {
        employeeRepo.insert(employeeModel);
    }

    public void update(String TaiKhoan, EmployeeModel employeeModel) {
        employeeRepo.update(TaiKhoan, employeeModel);
    }

    public void delete(String TaiKhoan) {
        employeeRepo.delete(TaiKhoan);
    }
}
