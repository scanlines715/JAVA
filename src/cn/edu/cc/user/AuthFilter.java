package cn.edu.cc.user;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = request.getRequestURI();
        if ( uri.endsWith("login.html")
                || uri.endsWith("/api/books")
                || uri.endsWith("/api/addToCart")
                || uri.endsWith("/api/getBooksInCart")
                || uri.endsWith("/api/removeFromCart")
                || uri.endsWith("cart.html")
                || uri.endsWith("login")
                || uri.endsWith("verifyCode")
                || uri.endsWith("index.html")
                || uri.endsWith("png")
                || uri.endsWith("jpg")
                || uri.endsWith("css") ) {
            // 对于不需要进行登录认证的资源，直接放行，继续往后执行
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("./login.html");
        } else {
            Boolean toke = (Boolean) session.getAttribute(LoginServlet.LOGIN_TOKEN);
            if (toke == Boolean.TRUE) {
                System.out.println("已经是登录用户");
                chain.doFilter(request, response);
            } else {
                System.out.println("未获得登录标记");
                response.sendRedirect("./login.html");
            }
        }
    }

}
