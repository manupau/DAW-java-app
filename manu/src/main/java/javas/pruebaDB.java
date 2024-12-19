package javas;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * La clase pruebaDB se utiliza para probar la conexión a la base de datos utilizando
 * las propiedades definidas en un archivo `db.properties`. La clase contiene métodos 
 * para obtener la conexión y un método `main` para ejecutar la prueba.
 * 
 * @author Manu
 */
public class pruebaDB {

    /**
     * Obtiene una conexión a la base de datos utilizando las credenciales y la URL
     * proporcionadas en el archivo de propiedades `db.properties`.
     * 
     * @return La conexión a la base de datos.
     * @throws IOException Si ocurre un error al leer el archivo de propiedades.
     * @throws SQLException Si ocurre un error al establecer la conexión con la base de datos.
     */
    public static Connection getConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        // Cargar las propiedades desde db.properties
        InputStream input = pruebaDB.class.getClassLoader().getResourceAsStream("db.properties");
        properties.load(input);

        // Obtener la URL, nombre de usuario y contraseña
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        // Retornar la conexión a la base de datos
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Método principal que prueba la conexión a la base de datos y muestra un mensaje
     * de éxito si la conexión es exitosa, o imprime el error si ocurre alguna excepción.
     * 
     * @param args Argumentos de la línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        try {
            // Intentar obtener la conexión
            getConnection();
            System.out.println("La prueba ha sido un éxito");
        } catch (IOException e) {
            // Manejar el error de entrada/salida
            e.printStackTrace();
        } catch (SQLException e) {
            // Manejar el error de conexión con la base de datos
            e.printStackTrace();
        }
    }
}
