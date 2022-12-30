package cn.edu.cc.book;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/removeFromCart")
public class RemoveFromCart extends HttpServlet {

    public final static String CART = "cart";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long bookId = Long.valueOf(request.getParameter("bookId"));
        List<Long> cart = this.getCart(request);
        System.out.println("remove id :  " + bookId);
        for (int i = 0 ; i < cart.size(); i++) {
            if (cart.get(i).equals(bookId)) {
                System.out.println("remove item " + i);
                cart.remove(i);
            }
        }

        response.setContentType("application/json; charset=UTF-8");
        try (Writer writer = response.getWriter()) {
            writer.write("{\"code\": 200, \"message\": \"success\"}");
        }
    }

    private List<Long> getCart(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        List<Long> cart = (List<Long>) session.getAttribute(CART);
        if (cart == null) {
            cart = new ArrayList<Long>();
            session.setAttribute(CART, cart);
        }
        return cart;
    }

}
