package controlador;

import java.util.ArrayList;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import modelo.Equipo;
import modelo.Jugador;

public class GeneraBDOO {

	public static void main(String[] args) {
		
		Jugador j1 = new Jugador("María",22, "Zaragoza", "voleybol");

		ODB baseDatosOO = null;
		try {
			String nombreBD = "BDDeportes.test";
			baseDatosOO = ODBFactory.open("src\\basedatos\\" + nombreBD);
			
			baseDatosOO.store(j1);
			
			Equipo equipo = new Equipo("Estella", "voleybol");
			
			baseDatosOO.store(equipo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (baseDatosOO!=null) {
				baseDatosOO.close();
			}
		}
		
		
		
		

	}

}
