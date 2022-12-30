package cn.edu.cc.book;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Book book = null;
        try {
            book = BookRepo.getInstance().getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>My Book Store</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <center>\n" +
                "    <div style=\"margin-top:5em; padding: 2em;text-align:center; width:60%; background-color:#EEEEEE\">\n" +
                "      <h2>编辑图书信息</h2>\n" +
                "      <form action=\"./saveBook\" method=\"post\">\n" +
                "        <input type=\"hidden\" name=\"id\" value=\"" + book.getId() + "\"><br><br>\n" +
                "        书 名： <input type=\"text\" name=\"name\" value=\"" + book.getName() + "\"><br><br>\n" +
                "        作 者： <input type=\"text\" name=\"author\" value=\"" + book.getAuthor() + "\"><br><br>\n" +
                "        价 格： <input type=\"text\" name=\"price\" value=\"" + book.getPrice() + "\"><br><br>\n" +
                "        简 介： <textarea name=\"describe\" rows=\"4\" cols=\"22\">" + book.getDescribe() + "</textarea> <br><br>\n" +
                "        <input type=\"submit\" value=\" 提 交 信 息\">\n" +
                "      </form>\n" +
                "    </div>\n" +
                "  </center>\n" +
                "</body>\n" +
                "</html>";

        response.setContentType("text/html; Charset=utf8");
        try(Writer writer = response.getWriter()) {
            writer.write(html);
        }
    }

}
