package existDB;

import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;

import java.util.Scanner;

import org.xmldb.api.*;

public class Borrar {
	private static String URI = "xmldb:exist://192.168.149.8:8080/exist/xmlrpc";

	public static void main(String[] args) {

		final String driver = "org.exist.xmldb.DatabaseImpl";
		final String nombreColeccion = "/db/pruebas";
		final String consultaXQuery = "for $b in doc(\"libros.xml\")//libro return data($b/titulo)";

		Collection col = null;
		XMLResource res = null;
		
		Scanner sc = new Scanner(System.in);

        System.out.print("Título que deseas borrar: ");
        String tituloBorrar = sc.nextLine();
        
		// Inicializar el driver
		try {
			Class cl = Class.forName(driver);
			Database database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);

			col = DatabaseManager.getCollection(URI + nombreColeccion);
			XQueryService service = (XQueryService) col.getService("XQueryService", "1.0");

			// XQuery Update
			String xquery = "for $l in doc('" + nombreColeccion + "/libros.xml')//libro "
					+ "where $l/titulo = '" + tituloBorrar + "' " + "return update delete $l";
			ResourceSet result = service.query(xquery);

			if (result == null) {
				System.out.println("null");
			} else {
				ResourceIterator i = result.getIterator();
				while(i.hasMoreResources()) {
					res = (XMLResource) i.nextResource();
					System.out.println(res.getContent());
				}
				System.out.println("Libro borrado en libros.xml correctamente.");
			}

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException e) {
			e.printStackTrace();
		} finally {
			// no olvidar liberar los recursos
			if (res != null) {
				res = null;
			}

			if (col != null) {
				try {
					col.close();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}
	}
}