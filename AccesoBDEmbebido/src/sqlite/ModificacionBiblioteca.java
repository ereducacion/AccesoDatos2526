package sqlite;


import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ModificacionBiblioteca {

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
            
            int numResultados = 
            		sentencia.executeUpdate("insert INTO Prestamo values (1,3,\"2025-10-15\",\"2025-11-03\")");
            
            // recorrer el resultado
            if (numResultados > 0)
            		System.out.println(numResultados + " modificados");
            else
            		System.out.println("No he modificado nada");
            
            //cerrar la conexion
            conexion.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
