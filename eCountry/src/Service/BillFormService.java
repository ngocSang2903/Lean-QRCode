/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.BillFormModel;
import Model.OrderDetailModel;
import Model.OrderModel;
import Repository.BillFormRepo;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BillFormService {

    private BillFormRepo billFormRepo = new BillFormRepo();

    public ArrayList<BillFormModel> all(String ten) {
        return billFormRepo.all(ten);
    }

    public void insertOrder(OrderModel orderModel) {
        billFormRepo.insertOrder(orderModel);
    }

    public void insertOrderDetail(OrderDetailModel orderDetailModel) {
        billFormRepo.insertOrderDetail(orderDetailModel);
    }
}
