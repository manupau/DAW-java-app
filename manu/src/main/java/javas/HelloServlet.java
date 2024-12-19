package javas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * El servlet HelloServlet maneja las solicitudes HTTP GET enviadas a la URL "/hello".
 * Este servlet responde con un mensaje simple en formato HTML.
 * 
 * @author Manu
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    /**
     * Método que maneja las solicitudes HTTP GET. Responde con un mensaje HTML simple
     * que dice "Hello, Servlets!".
     * 
     * @param req La solicitud HTTP recibida.
     * @param resp La respuesta HTTP que se enviará al cliente.
     * @throws ServletException Si ocurre un error durante el procesamiento del servlet.
     * @throws IOException Si ocurre un error durante la escritura de la respuesta.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Hello, Servlets!</h1>");
    }
}
