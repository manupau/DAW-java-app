<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javas.Connexio" %>
<!DOCTYPE html>
<html>
<head>
    <title>Llibreria</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container my-5">
        <h1 class="text-center mb-4">Llibreria - Resultats de la consulta</h1>

        <!-- Botón para añadir un nuevo libro -->
        <div class="mb-4 text-center">
            <a href="insertar.html" class="btn btn-success">Añadir Nuevo Libro</a>
        </div>

        <div class="row row-cols-1 row-cols-sm-2 row-cols-lg-4 g-4">
            <%
                Connection connection = null;
                PreparedStatement statement = null;
                ResultSet resultSet = null;
                try {
                    connection = Connexio.getConnexio();

                    String query = "SELECT id, titol, isbn, any_publicacio FROM llibres";
                    statement = connection.prepareStatement(query);
                    resultSet = statement.executeQuery();

                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String titol = resultSet.getString("titol");
                        String isbn = resultSet.getString("isbn");
                        int any = resultSet.getInt("any_publicacio");
            %>
            <div class="col">
                <div class="card shadow-sm h-100">
                    <div class="card-body">
                        <h5 class="card-title">Títol: <%= titol %></h5>
                        <p class="card-text">
                            <strong>ID:</strong> <%= id %><br>
                            <strong>ISBN:</strong> <%= isbn %><br>
                            <strong>Any Publicació:</strong> <%= any %>
                        </p>
                        <div class="d-flex justify-content-between">
                            <a href="modificar.html?id=<%= id %>" class="btn btn-warning btn-sm">Modificar</a>
                            <a href="eliminar.jsp?id=<%= id %>" class="btn btn-danger btn-sm">Eliminar</a>
                        </div>
                    </div>
                </div>
            </div>
            <%
                    }
                } catch (Exception e) {
                    out.println("<div class='col-12 text-danger text-center'>Error: " + e.getMessage() + "</div>");
                    e.printStackTrace();
                } finally {
                    if (resultSet != null) resultSet.close();
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                }
            %>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>