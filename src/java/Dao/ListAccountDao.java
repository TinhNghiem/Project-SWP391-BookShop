/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.Accounts;
import Model.Books;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ListAccountDao extends DBContext {

    public Accounts getLogin(String email, String pass) {
        String sql = "select * from Users where Email = ? and Password = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, email);
            pr.setString(2, pass);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                java.sql.Timestamp timestamp1 = rs.getTimestamp(9);
                LocalDateTime localdate1 = timestamp1.toLocalDateTime();

                java.sql.Timestamp timestamp2 = rs.getTimestamp(10);
                LocalDateTime localdate2 = timestamp2.toLocalDateTime();

                Accounts a = new Accounts(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        localdate1,
                        localdate2);
                return a;
            }
        } catch (SQLException e) {
            System.out.println("getLogin" + e.getMessage());
        }
        return null;
    }

    public void signupAccount(String userName, String pass, String email, String fullname, String address, String phoneNumber, int roleId) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([Username]\n"
                + "           ,[Password]\n"
                + "           ,[Email]\n"
                + "           ,[FullName]\n"
                + "           ,[Address]\n"
                + "           ,[Phone]\n"
                + "           ,[RoleID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, userName);
            pr.setString(2, pass);
            pr.setString(3, email);
            pr.setString(4, fullname);
            pr.setString(5, address);
            pr.setString(6, phoneNumber);
            pr.setInt(7, roleId);
            pr.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Accounts> getAll() {
        List<Accounts> list = new ArrayList<>();
        String sql = "select * from Users";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                java.sql.Timestamp timestamp1 = rs.getTimestamp("CreatedAt");
                LocalDateTime localdate1 = timestamp1.toLocalDateTime();

                java.sql.Timestamp timestamp2 = rs.getTimestamp("UpdatedAt");
                LocalDateTime localdate2 = timestamp2.toLocalDateTime();

                Accounts a = new Accounts(rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("FullName"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getInt("RoleID"),
                        localdate1,
                        localdate2);

                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void addAccount(String userName, String pass, String email, String fullname, String address, String Phone, int role) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([Username]\n"
                + "           ,[Password]\n"
                + "           ,[Email]\n"
                + "           ,[FullName]\n"
                + "           ,[Address]\n"
                + "           ,[Phone]\n"
                + "           ,[RoleID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, userName);
            pr.setString(2, pass);
            pr.setString(3, email);
            pr.setString(4, fullname);
            pr.setString(5, address);
            pr.setString(6, Phone);
            pr.setInt(7, role);
            pr.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Accounts checkAccountExistByEmail(String email) {
        String sql = "select * from Users where Email = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, email);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                java.sql.Timestamp timestamp1 = rs.getTimestamp("CreatedAt");
                LocalDateTime localdate1 = timestamp1.toLocalDateTime();

                java.sql.Timestamp timestamp2 = rs.getTimestamp("UpdatedAt");
                LocalDateTime localdate2 = timestamp2.toLocalDateTime();

                return new Accounts(rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("FullName"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getInt("RoleID"),
                        localdate1,
                        localdate2);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Accounts getAccountByID(int id) {
        String sql = "select * from Users where UserID = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                java.sql.Timestamp timestamp1 = rs.getTimestamp("CreatedAt");
                LocalDateTime localdate1 = timestamp1.toLocalDateTime();

                java.sql.Timestamp timestamp2 = rs.getTimestamp("UpdatedAt");
                LocalDateTime localdate2 = timestamp2.toLocalDateTime();

                return new Accounts(rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("FullName"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getInt("RoleID"),
                        localdate1,
                        localdate2);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateAccount(String username, String pass, String email, String fullname, String address, String phone, int roleID, int userID) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [Username] = ?\n"
                + "      ,[Password] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[FullName] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[RoleID] = ?\n"
                + " WHERE UserID = ?";

        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, username);
            pr.setString(2, pass);
            pr.setString(3, email);
            pr.setString(4, fullname);
            pr.setString(5, address);
            pr.setString(6, phone);
            pr.setInt(7, roleID);
            pr.setInt(8, userID);
            pr.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteAccount(int id) {
        String sql = "DELETE FROM [dbo].[Users]\n"
                + "      WHERE UserID = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, id);
            pr.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public List<Books> getBookByName(String txtSearch) {
        List<Books> list = new ArrayList<>();
        String sql = "select * from Books\n"
                + "where Title like ?";

        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, "%" + txtSearch + "%");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                java.sql.Timestamp timestamp1 = rs.getTimestamp(13);
                LocalDateTime localdate1 = timestamp1.toLocalDateTime();

                java.sql.Timestamp timestamp2 = rs.getTimestamp(14);
                LocalDateTime localdate2 = timestamp2.toLocalDateTime();

                list.add(new Books(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getBoolean(11),
                        rs.getBoolean(12),
                        localdate1,
                        localdate2));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public static void main(String[] args) {
        ListAccountDao dal = new ListAccountDao();
dal.deleteAccount(3);

        System.out.println(dal);
    }
}
