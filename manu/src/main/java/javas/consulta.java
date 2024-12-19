package javas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * La clase consulta es un servlet que maneja las solicitudes HTTP GET para realizar
 * una consulta a la base de datos y devolver los resultados en formato HTML.
 * 
 * @author Manu
 */
public class consulta extends HttpServlet {

    /**
     * Método que maneja las solicitudes HTTP GET. Realiza una consulta a la base de datos
     * para obtener los libros y sus detalles, y luego genera una respuesta HTML
     * con los resultados.
     * 
     * @param request La solicitud HTTP recibida.
     * @param response La respuesta HTTP que se enviará al cliente.
     * @throws ServletException Si ocurre un error durante el procesamiento del servlet.
     * @throws IOException Si ocurre un error durante la escritura de la respuesta.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection connection = Connexio.getConnexio()) {
            // Consulta SQL (ejemplo) para obtener libros
            String query = "SELECT id, titol, isbn, any_publicacio FROM llibres";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                // Generar respuesta HTML con los resultados de la consulta
                out.println("<html><head><title>Consulta</title></head><body>");
                out.println("<h1>Resultats de la consulta</h1>");
                out.println("<table border='1'><tr><th>ID</th><th>Títol</th><th>Autor</th><th>Any</th></tr>");

                // Iterar a través de los resultados y mostrarlos en una tabla HTML
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String titol = resultSet.getString("titol");
                    String isbn = resultSet.getString("isbn");
                    int any = resultSet.getInt("any_publicacio");

                    out.println("<tr><td>" + id + "</td><td>" + titol + "</td><td>" + isbn + "</td><td>" + any + "</td></tr>");
                }

                out.println("</table></body></html>");
            }
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
