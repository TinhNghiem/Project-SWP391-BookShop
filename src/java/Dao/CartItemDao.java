/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.Cart;
import Model.CartItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Aplal
 */
public class CartItemDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public boolean addNewCartItem(CartItem cartItem) {
        String sql = "INSERT INTO [dbo].[CartItems] ([CartID],[BookID],[Quantity]) VALUES (?,?,?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, cartItem.getCartID());
            stm.setInt(2, cartItem.getBookID());
            stm.setInt(3, cartItem.getQuantity());
            int result = stm.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("addNewCartItem: " + e.getMessage());
        }
        return false;
    }

    public CartItem getCartItemByBookID(int bookID, int cartID) {
        String sql = "SELECT * FROM CartItems WHERE BookID = ? AND CartID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, bookID);
            stm.setInt(2, cartID);
            rs = stm.executeQuery();
            if (rs.next()) {
                return new CartItem(rs.getInt("CartItemID"),
                        cartID,
                        bookID,
                        rs.getInt("Quantity"),
                        rs.getTimestamp("CreatedAt").toLocalDateTime(),
                        rs.getTimestamp("UpdatedAt").toLocalDateTime());
            }
        } catch (SQLException e) {
            System.out.println("getCartItemByBookID: " + e.getMessage());
        }
        return null;
    }



    public int getBookIdFromCartItem(int cartID, int bookID) {
        String sql = "SELECT * FROM CartItems WHERE BookID = ? AND CartID = ?";
        try {
            stm=connection.prepareStatement(sql);
            stm.setInt(1, bookID);
            stm.setInt(2, cartID);
            rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("BookID");
            }
        } catch (SQLException e) {
            System.out.println("getBookIdFromCartItem: " + e.getMessage());
        }
        return  0;
    }

    public int getQuantityFromCart(int cartID, int bookID) {
        String sql = "SELECT * FROM CartItems WHERE CartID = ? AND BookID = ? ";
        try {
            stm=connection.prepareStatement(sql);
            stm.setInt(1, cartID);
            stm.setInt(2, bookID);
            rs=stm.executeQuery();
            if(rs.next()) return rs.getInt("Quantity");
        } catch (SQLException e) {
            System.out.println("getQuantityFromCart: " + e.getMessage());
        }
        return 0;
    }

    public boolean updateQuantityCartItem(CartItem cartItem) {
        String sql ="UPDATE CartItems SET Quantity = ? WHERE CartID = ? AND BookID = ? ";
        try {
            stm=connection.prepareStatement(sql);
            stm.setInt(1, cartItem.getQuantity());
            stm.setInt(2, cartItem.getCartID());
            stm.setInt(3, cartItem.getBookID());
            int result = stm.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("updateQuantityCartItem: " + e.getMessage());
        }
        return false;
    }

    public ArrayList<CartItem> getCartItemsByCartID(int cartid) {
        String sql = "SELECT * FROM CartItems WHERE CartID = ?";
        ArrayList<CartItem> cartItems = new ArrayList<>();
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, cartid);
            rs = stm.executeQuery();
            while (rs.next()) {
                int cartItemID = rs.getInt("CartItemID");
                int bookID = rs.getInt("BookID");
                int quantity = rs.getInt("Quantity");
                LocalDateTime createAt = rs.getTimestamp("CreatedAt").toLocalDateTime();
                LocalDateTime updateAt = rs.getTimestamp("UpdatedAt").toLocalDateTime();
                CartItem cartItem = new CartItem(cartItemID, cartid, bookID, quantity, createAt, updateAt);
                cartItems.add(cartItem);
            }
        } catch (SQLException e) {
            System.out.println("getCartItemsByCartID: " + e.getMessage());
        }
        return cartItems;
    }

    //Xóa CartItem theo CartID và BookID
    public boolean deleteCartItem(int cartID, int bookID) {
        String sql = "DELETE FROM CartItems WHERE CartID = ? AND BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, cartID);
            stm.setInt(2, bookID);
            int result = stm.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("deleteCartItem: " + e.getMessage());
        }
        return false;
    }
}
