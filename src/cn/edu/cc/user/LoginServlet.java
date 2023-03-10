package cn.edu.cc.user;

import com.mysql.cj.Session;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public final static String LOGIN_TOKEN = "USER_LOGIN_TOKEN";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("user");
        String password = request.getParameter("password");

        if (userName != null && password != null) {
            this.doLogin(request, response);
        } else {
            HttpSession session = request.getSession();
            if (session == null || session.getAttribute(LoginServlet.LOGIN_TOKEN) != Boolean.TRUE) {
                response.sendRedirect("./login.html");
            } else {
                response.sendRedirect("./admin.html");
            }
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("user");
        String password = request.getParameter("password");
        String code = request.getParameter("code");

        String verifyCode = (String) request.getSession(true).getAttribute(ValidateCodeServlet.LOGIN_VERIFY_CODE);
        if (code == null || !code.equalsIgnoreCase(verifyCode)) {
            System.out.println("验证码错误");
            response.sendRedirect("./login.html");
            return;
        }

        try {
            User user = UserRepo.getInstance().auth(userName, password);
            if (user == null) {
                System.out.println("error");
            }
            if (user != null) {
                System.out.println("用户名密码正确");
                HttpSession session = request.getSession();
                session.setAttribute(LOGIN_TOKEN, Boolean.TRUE);
                response.sendRedirect("./admin.html");
            } else {
                response.sendRedirect("./login.html");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
