package javas;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * La clase Connexio proporciona una forma de obtener una conexión a una base de datos
 * a partir de un archivo de propiedades que contiene la URL, el nombre de usuario y la contraseña.
 * 
 * @author Manu
 */
public class Connexio {
    
    // Archivo de propiedades que contiene las credenciales y la URL de la base de datos
    private static final String PROPERTIES_FILE = "db.properties";

    /**
     * Obtiene una conexión a la base de datos utilizando las credenciales y la URL 
     * proporcionadas en el archivo de propiedades.
     * 
     * @return Una conexión a la base de datos.
     * @throws Exception Si ocurre un error al cargar el archivo de propiedades o al
     *         establecer la conexión.
     */
    public static Connection getConnexio() throws Exception {
        Properties properties = new Properties();
        // Cargar el controlador JDBC para MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Cargar las propiedades desde db.properties
        try (InputStream input = Connexio.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new Exception("No se encontró el archivo db.properties");
            }
            properties.load(input);
        }

        // Obtener credenciales y URL desde las propiedades
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        // Retornar la conexión a la base de datos
        return DriverManager.getConnection(url, username, password);
    }
}
