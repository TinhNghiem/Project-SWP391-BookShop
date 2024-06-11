/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.OrderStatusHistory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class OrderStatusDao extends DBContext{
      PreparedStatement stm;//thực hiên câu lệnh sql
    ResultSet rs;//lưu trữ dữ liệu lấy về từ câu ljeenh select
    public ArrayList<OrderStatusHistory> getOrderStatusSuccesful(){
        ArrayList<OrderStatusHistory> list= new ArrayList<>();
        String sql="select * from OrderStatusHistory where Status= 'Completed'";
        try{
            stm=connection.prepareStatement(sql);
            rs= stm.executeQuery();
            while(rs.next()){
                OrderStatusHistory orderstatus= new OrderStatusHistory(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                list.add(orderstatus);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return list;
    }
      public ArrayList<OrderStatusHistory> getOrderStatusRequest(){
        ArrayList<OrderStatusHistory> list= new ArrayList<>();
        String sql="select * from OrderStatusHistory where Status= 'Pending'";
        try{
            stm=connection.prepareStatement(sql);
            rs= stm.executeQuery();
            while(rs.next()){
                OrderStatusHistory orderstatus= new OrderStatusHistory(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                list.add(orderstatus);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return list;
    }
    public static void main(String[] args) {
         ArrayList<OrderStatusHistory> list= new ArrayList<>();
        OrderStatusDao orderdao= new OrderStatusDao();
        list= orderdao.getOrderStatusSuccesful();
        System.out.println(list.size());
        
    }
}
