package CartController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import Dao.BookDao;
import Dao.CartDao;
import Dao.CartItemDao;
import Dao.CouponDao;
import Dao.PromotionDao;
import Model.Accounts;
import Model.Books;
import Model.Cart;
import Model.CartItem;
import Model.Coupons;
import Model.Promotions;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Aplal
 */
@WebServlet(urlPatterns = {"/cart"})
public class CartView extends HttpServlet {

    private Accounts getAccountsInfoSession(HttpServletRequest request) {

        return (Accounts) request.getSession().getAttribute("account");

    }
    private BookDao bookDao;
    private PromotionDao promotionDao;
    private CouponDao couponDao;
    private CartItemDao cartitemDao;
    private CartDao cartDao;

    @Override
    public void init() throws ServletException {
        super.init();
        bookDao = new BookDao();
        promotionDao = new PromotionDao();
        couponDao = new CouponDao();
        cartitemDao = new CartItemDao();
        cartDao = new CartDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Accounts user = getAccountsInfoSession(request);
        if (user != null) {
            int userid = user.getUserID();
            int cartid = cartDao.getCartIdByUserID(userid);
            //Lấy ra số lượng quyển sách trong CartItem theo CartID và BookID
            // Lấy ra danh sách ID của sản phẩm trong CartItem theo CartID
            ArrayList<CartItem> cartItems = cartitemDao.getCartItemsByCartID(cartid);

            //Lấy ra tất cả Sách trong bảng Books theo BookID lấy được từ phần trên
//            ArrayList<Books> books = new ArrayList<>();
//            for (CartItem cartItem : cartItems) {
//                Books book = bookDao.getBookByID(cartItem.getBookID());
//                books.add(book);
//            }
            
            ArrayList<Books> books = bookDao.getAllBooks();
            //Lấy ra chương trình khuyến mãi còn hiệu lực
            Promotions promotion = promotionDao.getPromotionValid();
            
            request.setAttribute("promotion", promotion);
            request.setAttribute("books", books);
            request.setAttribute("cartItems", cartItems);
            request.setAttribute("cartid", cartid);

        } else {
            request.getRequestDispatcher("signinView.jsp").forward(request, response);
        }
        request.getRequestDispatcher("cartView.jsp").forward(request, response);
    }

    
}
