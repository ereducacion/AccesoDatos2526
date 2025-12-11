package controlador;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.oid.OIDFactory;

import modelo.Equipo;
import modelo.Jugador;

public class RecogeInformacionPorIdBDOO {

	public static void main(String[] args) {
		
		
		ODB baseDatosOO = null;
		try {
			String nombreBD = "deportes.odb";
			String ruta = "src\\basedatos\\";
			baseDatosOO = ODBFactory.open(ruta + nombreBD);
			
			// recuperar al equipo
			OID elIDdelEquipo = OIDFactory.buildObjectOID(2);
			Object elobjeto = (Object) baseDatosOO.getObjectFromId(elIDdelEquipo);

			if (elobjeto.getClass().equals(Equipo.class)) {
				System.out.println("El deporte del equipo es " + ((Equipo)elobjeto).getDeporte());
			} else if (elobjeto.getClass().equals(Jugador.class)) {
				System.out.println("El jugador se llama "+ ((Jugador)elobjeto).getNombre());
			}	
			
		} catch (org.neodatis.odb.ODBRuntimeException e) {
			System.out.println("No existe ese id");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (baseDatosOO!=null) {
				baseDatosOO.close();
			}
		}
		
		
		
		

	}

}
