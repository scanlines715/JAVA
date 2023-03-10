package cn.edu.cc.book;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/books")
public class ListBookApi extends HttpServlet  {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Book> books = BookRepo.getInstance().getAll();
            response.setContentType("application/json; charset=UTF-8");
            try (Writer writer = response.getWriter()) {
                this.writeJsonByJackson(response.getWriter(), books);
            }
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    private void writeJsonByJackson(Writer writer, List<Book > books)  throws IOException  {
        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(books);
        writer.write(json);
    }

    private void writeJson(Writer writer, List<Book > books) throws IOException {
        writer.write("{");
        writer.write("\"books\":[");
        for (int i=0; i<books.size(); i++) {
            if (i > 0) writer.write(",");
            Book book = books.get(i);
            writer.write(String.format(
                    "{\"id\":%s, \"name\":\"%s\", \"author\":\"%s\", \"price\":%s, \"describe\":\"%s\", \"picture\":\"%s\"}",
                    book.getId(), book.getName(),book.getAuthor(), book.getPrice(), book.getDescribe(), book.getPicture()
            ));
        }
        writer.write("]");
        writer.write("}");
    }

}
