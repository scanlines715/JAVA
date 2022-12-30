package cn.edu.cc.book;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listBook")
public class ListBookServlet  extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Book> books = BookRepo.getInstance().getAll();
            response.setContentType("text/html; charset=UTF-8");
            try(Writer writer = response.getWriter()) {
                writer.write("<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>My Java Web APP</title>\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />\n" +
                        "    <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\" />\n" +
                        "    <title>Material Design for Bootstrap</title>\n" +
                        "    <!-- Font Awesome -->\n" +
                        "    <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.11.2/css/all.css\" />\n" +
                        "    <!-- Google Fonts Roboto -->\n" +
                        "    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap\" />\n" +
                        "    <!-- MDB -->\n" +
                        "    <link rel=\"stylesheet\" href=\"css/mdb.min.css\" />\n" +
                        "    <!-- Custom styles -->\n" +
                        "    <link rel=\"stylesheet\" href=\"css/style.css\" />\n" +
                        "\n" +
                        "    <script src=\"https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js\"></script>" +
                        "    <!-- Styles -->\n" +
                        "    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/main.css\">\n" +
                        "    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/utility.css\">\n" +
                        "    <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/demo.css\">\n" +
                        "</head><!--Main Navigation-->\n" +
                        "<header>\n" +
                        "    <!-- Intro settings -->\n" +
                        "    <style>\n" +
                        "      #intro {\n" +
                        "        /* Margin to fix overlapping fixed navbar */\n" +
                        "        margin-top: 100px;\n" +
                        "      }\n" +
                        "    </style>\n" +
                        "\n" +
                        "    <!-- Navbar -->\n" +
                        "    <nav class=\"navbar navbar-expand-lg navbar-light bg-white fixed-top\">\n" +
                        "        <div class=\"container-fluid\">\n" +
                        "            <!-- Navbar brand -->\n" +
                        "            <a class=\"navbar-brand\" target=\"_blank\" href=\"https://mdbootstrap.com/docs/standard/\">\n" +
                        "                <img src=\"https://mdbootstrap.com/img/logo/mdb-transaprent-noshadows.png\" height=\"16\" alt=\"\" loading=\"lazy\"\n" +
                        "                     style=\"margin-top: -3px;\" />\n" +
                        "            </a>\n" +
                        "            <button class=\"navbar-toggler\" type=\"button\" data-mdb-toggle=\"collapse\" data-mdb-target=\"#navbarExample01\"\n" +
                        "                    aria-controls=\"navbarExample01\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                        "                <i class=\"fas fa-bars\"></i>\n" +
                        "            </button>\n" +
                        "            <div class=\"collapse navbar-collapse\" id=\"navbarExample01\">\n" +
                        "                <ul class=\"navbar-nav me-auto mb-2 mb-lg-0\">\n" +
                        "                    <li class=\"nav-item active\">\n" +
                        "                        <a class=\"nav-link\" aria-current=\"page\" href=\"#intro\">首 页</a>\n" +
                        "                    </li>\n" +
                        "                    <li class=\"nav-item\">\n" +
                        "                        <a class=\"nav-link\" href=\"https://www.youtube.com/channel/UC5CF7mLQZhvx8O5GODZAhdA\" rel=\"nofollow\"\n" +
                        "                           target=\"_blank\">图书查询</a>\n" +
                        "                    </li>\n" +
                        "                    <li class=\"nav-item\">\n" +
                        "                        <a class=\"nav-link\" href=\"https://mdbootstrap.com/docs/standard/\" target=\"_blank\">我的收藏</a>\n" +
                        "                    </li>\n" +
                        "                </ul>\n" +
                        "\n" +
                        "                <ul class=\"navbar-nav d-flex flex-row\">\n" +
                        "                    <!-- Icons -->\n" +
                        "                    <li class=\"nav-item me-3 me-lg-0\">\n" +
                        "                        <a class=\"nav-link\" href=\"https://www.youtube.com/channel/UC5CF7mLQZhvx8O5GODZAhdA\" rel=\"nofollow\"\n" +
                        "                           target=\"_blank\">\n" +
                        "                            <i class=\"fab fa-youtube\"></i>\n" +
                        "                        </a>\n" +
                        "                    </li>\n" +
                        "                    <li class=\"nav-item me-3 me-lg-0\">\n" +
                        "                        <a class=\"nav-link\" href=\"https://www.facebook.com/mdbootstrap\" rel=\"nofollow\" target=\"_blank\">\n" +
                        "                            <i class=\"fab fa-facebook-f\"></i>\n" +
                        "                        </a>\n" +
                        "                    </li>\n" +
                        "                    <li class=\"nav-item me-3 me-lg-0\">\n" +
                        "                        <a class=\"nav-link\" href=\"https://twitter.com/MDBootstrap\" rel=\"nofollow\" target=\"_blank\">\n" +
                        "                            <i class=\"fab fa-twitter\"></i>\n" +
                        "                        </a>\n" +
                        "                    </li>\n" +
                        "                    <li class=\"nav-item me-3 me-lg-0\">\n" +
                        "                        <a class=\"nav-link\" href=\"https://github.com/mdbootstrap/mdb-ui-kit\" rel=\"nofollow\" target=\"_blank\">\n" +
                        "                            <i class=\"fab fa-github\"></i>\n" +
                        "                        </a>\n" +
                        "                    </li>\n" +
                        "                </ul>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </nav>\n" +
                        "    <!-- Navbar -->\n" +
                        "</header>\n" +
                        "<!--Main Navigation-->\n"
                );
                writer.write("<h1 class=\"ls-tight font-bolder display-6 text-white mb-5\">欢迎访问我的网上书店</h1>\n");

                writer.write("<table width='55%' border='0' cellpadding=4>");
                for(int i=0; i<books.size(); i++) {
                    Book book = books.get(i);
                    if (i % 2 == 0) {
                        writer.write("<tr style='background-color:#F5F5F5;height:2em'>");
                    } else {
                        writer.write("<tr style='background-color:#D6E6F2;height:2em'>");
                    }
                    writer.write(String.format("<td width='30px'>%s</td>", book.getId()));
                    writer.write(String.format("<td width='150px'>%s</td>", book.getName()));
                    writer.write(String.format("<td width='100px'>%s</td>", book.getAuthor()));
                    writer.write(String.format("<td width='60px'>%s</td>", book.getPrice()));
                    writer.write(String.format("<td>%s</td>", book.getDescribe()));
                    writer.write(String.format("<td><img src='./upload/%s' style='width:50px'/></td>", book.getPicture()));
                    writer.write(String.format("<td><a href='./deleteBook?id=%s'>" +
                            "<img src='./images/trash.png' width='20px'></a></td>", book.getId()));
                    writer.write(String.format("<td><a href='./updateBook?id=%s'>" +
                            "<img src='./images/edit.png' width='20px'></a></td>", book.getId()));
                    writer.write("</tr>");
                }
                writer.write("</table><br><br>\n\n");

                writer.write("<a href='./admin.html'>返 回 首 页</a>\n");
                writer.write("</center>\n");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
