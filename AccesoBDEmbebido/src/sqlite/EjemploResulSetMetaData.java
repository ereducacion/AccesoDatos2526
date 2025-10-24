package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class EjemploResulSetMetaData {

	public static void main(String[] args) {

		try {
	 		
	 		//1- Crear una instancia del JDBC driver
			Class.forName("org.sqlite.JDBC");
			//2-Especificar la url de la base de datos.
			//3-Establecer una conexión usando el driver que crea el  objeto Connection.
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:src\\sqlite\\biblioteca.db");

			//4- Crear un objeto Statement, usando Connection.
            Statement sentencia = (Statement) conexion.createStatement();
            //5- Armar el postulado SQL y enviarlo a ejecución usando  el Statement.
            //6- Recibir los resultados en el objeto ResultSet.
            ResultSet resultado = sentencia.executeQuery("select * from socio");
            
            ResultSetMetaData rsmd = resultado.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            
            System.out.println("Esta consulta tiene " + numberOfColumns + " columnas");
            
            for (int i = 1; i <= numberOfColumns; i++) {
            	System.out.println("Tipo=" + rsmd.getColumnClassName(i) + "  " + 
            					"nombreSchema=" + rsmd.getCatalogName(i) + "  " +
            					"nombreColumna=" + rsmd.getColumnName(i) + "  " + // equivalente a rsmd.getColumnLabel(i)
            					"tipoNombre=" + rsmd.getColumnTypeName(i) + "  "
            					);
			}
            
            //cerrar la conexion
            conexion.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
