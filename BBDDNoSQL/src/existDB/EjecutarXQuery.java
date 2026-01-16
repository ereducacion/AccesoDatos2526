package existDB;

import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;
import javax.xml.transform.OutputKeys;

public class EjecutarXQuery {    
	private static String URI = "xmldb:exist://192.168.149.8:8080/exist/xmlrpc";

	public static void main(String[] args) {

		final String driver = "org.exist.xmldb.DatabaseImpl";
		final String nombreColeccion = "/db/pruebas";
		final String consultaXQuery = "for $b in doc(\"libros.xml\")//libro return data($b/titulo)";

		Collection col = null;
		XMLResource res = null;
		// Inicializar el driver
		try {
			Class cl = Class.forName(driver);
			Database database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);

			col = DatabaseManager.getCollection(URI + nombreColeccion);
			XPathQueryService xpqs = (XPathQueryService)col.getService("XPathQueryService", "1.0");
			xpqs.setProperty("indent", "yes");

			ResourceSet result = xpqs.query(consultaXQuery);
			ResourceIterator i = result.getIterator();
			while(i.hasMoreResources()) {
				res = (XMLResource) i.nextResource();
				System.out.println(res.getContent());
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException   e) {
			e.printStackTrace();
		} finally {
			// no olvidar liberar los recursos
			if(res != null) {
            	res = null;
            }
            
            if(col != null) {
                try { 
                	col.close(); 
                }
                catch(XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
		}
	}
}