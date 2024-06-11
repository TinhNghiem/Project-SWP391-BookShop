/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dal.DBContext;
import Model.Books;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class BookDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    //Lây tất cả Books
    public ArrayList<Books> getAllBooks() {
        ArrayList<Books> books = new ArrayList<Books>();
        String sql = "SELECT * FROM Books";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("getAllBooks" + e.getMessage());
        }
        return books;
    }

    //Lấy sách theo ID
    public Books getBookByID(int id) {
        String sql = "SELECT * FROM Books WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return book;
            }
        } catch (Exception e) {
            System.out.println("getBookByID" + e.getMessage());
        }
        return null;
    }

    //Tìm kiếm sách theo tên
    public ArrayList<Books> searchBookByName(String name) {
        ArrayList<Books> books = new ArrayList<Books>();
        String sql = "SELECT * FROM Books WHERE Title LIKE ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("searchBookByName" + e.getMessage());
        }
        return books;
    }

    // Create (Add new Book)
    public boolean addBook(Books book) {
        String sql = "INSERT INTO Books(Title, Publisher, PublicationDate, ISBN, Price, Stock, SoldQuantity, Description, CoverImage, IsAvailable, IsBanned, CreatedAt, UpdatedAt) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, book.getTitle());
            stm.setString(2, book.getPublisher());
            stm.setString(3, book.getPublicationDate());
            stm.setString(4, book.getISBN());
            stm.setString(5, book.getPrice());
            stm.setInt(6, book.getStock());
            stm.setInt(7, book.getSoldQuantity());
            stm.setString(8, book.getDescription());
            stm.setString(9, book.getCoverImage());
            stm.setBoolean(10, book.getIsAvailable());
            stm.setBoolean(11, book.getIsBanned());
            stm.setTimestamp(12, java.sql.Timestamp.valueOf(book.getCreatedAt()));
            stm.setTimestamp(13, java.sql.Timestamp.valueOf(book.getUpdatedAt()));
            int result = stm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println("addBook" + e.getMessage());
        }
        return false;
    }

    // Update (Update Book)
    public boolean updateBook(Books book) {
        String sql = "UPDATE Books SET Title = ?, Publisher = ?, PublicationDate = ?, ISBN = ?, Price = ?, Stock = ?, SoldQuantity = ?, Description = ?, CoverImage = ?, IsAvailable = ?, IsBanned = ?, UpdatedAt = ? WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, book.getTitle());
            stm.setString(2, book.getPublisher());
            stm.setString(3, book.getPublicationDate());
            stm.setString(4, book.getISBN());
            stm.setString(5, book.getPrice());
            stm.setInt(6, book.getStock());
            stm.setInt(7, book.getSoldQuantity());
            stm.setString(8, book.getDescription());
            stm.setString(9, book.getCoverImage());
            stm.setBoolean(10, book.getIsAvailable());
            stm.setBoolean(11, book.getIsBanned());
            stm.setTimestamp(12, java.sql.Timestamp.valueOf(book.getUpdatedAt()));
            stm.setInt(13, book.getBookID());
            int result = stm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println("updateBook" + e.getMessage());
        }
        return false;
    }

    // Delete (Delete Book)
    public boolean deleteBook(int id) {
        String sql = "DELETE FROM Books WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            int result = stm.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            System.out.println("deleteBook" + e.getMessage());
        }
        return false;
    }

    // Read (Get Book by ISBN)
    public Books getBookByISBN(String isbn) {
        String sql = "SELECT * FROM Books WHERE ISBN = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, isbn);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return book;
            }
        } catch (Exception e) {
            System.out.println("getBookByISBN" + e.getMessage());
        }
        return null;
    }

    public List<Books> getBooksByPage(int page, int pageSize) {
        List<Books> books = new ArrayList<>();
        String query = "SELECT * FROM Books ORDER BY BookID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {

            stm = connection.prepareStatement(query);
            stm.setInt(1, (page - 1) * pageSize);
            stm.setInt(2, pageSize);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return books;
    }
    
    //Chỉ lấy ra Publisher của Books và không trùng nhau (Distinct)
    public ArrayList<String> getDistinctPublisher() {
        ArrayList<String> publishers = new ArrayList<String>();
        String sql = "SELECT DISTINCT Publisher FROM Books";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                publishers.add(rs.getString("Publisher"));
            }
        } catch (Exception e) {
            System.out.println("getDistinctPublisher" + e.getMessage());
        }
        return publishers;
    }

    public static void main(String[] args) {
        // Khởi tạo một đối tượng BookDao
        BookDao bookDao = new BookDao();

        // Tạo một đối tượng Books mới
        Books newBook = new Books();
        newBook.setTitle("Test Book");
        newBook.setPublisher("Test Publisher");
        newBook.setPublicationDate("2024"); // Đảm bảo định dạng của ngày tháng là đúng
        newBook.setISBN("1234567890");
        newBook.setPrice("9.99");
        newBook.setStock(10);
        newBook.setSoldQuantity(0);
        newBook.setDescription("This is a test book.");
        newBook.setCoverImage("test_cover.jpg");
        newBook.setIsAvailable(true);
        newBook.setIsBanned(false);
        newBook.setCreatedAt(LocalDateTime.now());
        newBook.setUpdatedAt(LocalDateTime.now());

        // Gọi phương thức addBook để thêm sách mới vào cơ sở dữ liệu
        boolean success = bookDao.addBook(newBook);

        // Kiểm tra xem việc thêm sách mới đã thành công hay không
        if (success) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Failed to add book.");
        }
    }

}
