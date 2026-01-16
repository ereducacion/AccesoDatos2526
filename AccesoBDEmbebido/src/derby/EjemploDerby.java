package derby;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class EjemploDerby {
    public static void main(String[] args) {
        try {
	 		//1- Crear una instancia del JDBC driver
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			//2-Especificar la url de la base de datos.
			//3-Establecer una conexión usando el driver que crea el  objeto Connection.
			Connection conexion = DriverManager.getConnection("jdbc:derby:derby\\bibliotecaCompleta.db", "miusuario", "Pass!123456");

			//4- Crear un objeto Statement, usando Connection.
            Statement sentencia = (Statement) conexion.createStatement();
            //5- Armar el postulado SQL y enviarlo a ejecución usando  el Statement.
            //6- Recibir los resultados en el objeto ResultSet.
            ResultSet resultado = sentencia.executeQuery("select * from socio");
            
            // recorro el resultado
            while (resultado.next()) {
            	System.out.println(resultado.getString(2));
            }
            
            // LIBRERAR LOS RECURSOS
            resultado.close();
            sentencia.close();
            conexion.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error en la conexion de la base de datos");
            ex.printStackTrace();
        }
    }
}