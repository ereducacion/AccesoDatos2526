package existDB;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XQueryService;

import java.util.Scanner;

public class Insertar {

    private static final String URI = "xmldb:exist://192.168.149.8:8080/exist/xmlrpc";
    private static final String USER = "miusuario";
    private static final String PASSWORD = "Pass!123456";
    private static final String driver = "org.exist.xmldb.DatabaseImpl";
    private static final String nombreColeccion = "/db/pruebas";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Año (0 si no tiene año): ");
        int agno = sc.nextInt(); sc.nextLine();

        System.out.print("Editorial: ");
        String editorial = sc.nextLine();
        
        System.out.print("Número de autores: ");
        int numAutores = Integer.parseInt(sc.nextLine());

        StringBuilder autoresXML = new StringBuilder();

        for (int i = 1; i <= numAutores; i++) {
            System.out.print("Nombre del autor " + i + ": ");
            String nombre = sc.nextLine();

            System.out.print("Apellidos del autor " + i + ": ");
            String apellido = sc.nextLine();

            autoresXML.append("<autor>")
                      .append("<apellido>").append(apellido).append("</apellido>")
                      .append("<nombre>").append(nombre).append("</nombre>")
                      .append("</autor>");
        }

        System.out.print("Precio: ");
        int precio = Integer.parseInt(sc.nextLine());

        // Nodo libro a insertar
        String libroXML = "<libro>";
        if (agno > 0) libroXML = "<libro año=\"" + agno + "\">";
        libroXML = libroXML +
                "<titulo>" + titulo + "</titulo>" +
                    autoresXML +
                    "<editorial>" + editorial + "</editorial>" +
                    "<precio>" + precio + "</precio>" +
                "</libro>";


      
        // Registrar BD
        Class cl;
        Collection col = null;
		try {
			cl = Class.forName(driver);
            Database database = (Database) cl.newInstance();
            DatabaseManager.registerDatabase(database);

            // Obtener colección
            col = DatabaseManager.getCollection(URI + nombreColeccion, USER, PASSWORD);

            if (col == null) {
                System.out.println("No se pudo acceder a la colección.");
                return;
            }

            // Servicio XQuery
            XQueryService service = (XQueryService) col.getService("XQueryService", "1.0");
          
            // XQuery Update
            String xquery = "update insert " + libroXML + " into doc('"+ nombreColeccion +"/libros.xml')/bib";
            service.query(xquery);

            System.out.println("Libro insertado en libros.xml correctamente.");
           
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException e) {
			e.printStackTrace();
		} finally {
			// no olvidar liberar los recursos
			if(col != null) {
				try { col.close(); } catch(XMLDBException xe) {xe.printStackTrace();}
			}
		}

        
    }
}
