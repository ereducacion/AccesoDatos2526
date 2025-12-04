package controlador;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.oid.OIDFactory;

import modelo.Equipo;
import modelo.Jugador;

public class AsginaEquipoBDOO {

	public static void main(String[] args) {
		
		
		ODB baseDatosOO = null;
		try {
			String nombreBD = "BDDeportes.test";
			String ruta = "src\\basedatos\\";
			baseDatosOO = ODBFactory.open(ruta + nombreBD);
			
			// recuperar al equipo
			OID elIDdelEquipo = OIDFactory.buildObjectOID(4);
			Equipo elequipo = (Equipo) baseDatosOO.getObjectFromId(elIDdelEquipo);

			// recuperar al jugador
			Objects<Jugador> losjugadores = baseDatosOO.getObjects(Jugador.class);
			
			// asignar el jugador al equipo
			for (Jugador jugador : losjugadores) {
				if (jugador.getDeporte().equals(elequipo.getDeporte())) { 
					elequipo.addJugador(jugador);
				}
			}
			
			// guardar lo que he modificado
			baseDatosOO.store(elequipo);
			
			// hare un commit
			baseDatosOO.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (baseDatosOO!=null) {
				baseDatosOO.close();
			}
		}
		
		
		
		

	}

}
