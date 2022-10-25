/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class OrderModel {

    private String IDorder, dateOrder, UserEmployee;

    public OrderModel() {
    }

    public OrderModel(String IDorder, String dateOrder, String UserEmployee) {
        this.IDorder = IDorder;
        this.dateOrder = dateOrder;
        this.UserEmployee = UserEmployee;
    }

    public String getIDorder() {
        return IDorder;
    }

    public void setIDorder(String IDorder) {
        this.IDorder = IDorder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getUserEmployee() {
        return UserEmployee;
    }

    public void setUserEmployee(String UserEmployee) {
        this.UserEmployee = UserEmployee;
    }

}
