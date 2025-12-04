package modelo;

import java.util.HashSet;
import java.util.Set;

public class Equipo {

	private String nombre;
	private String deporte;
	private Set<Jugador> jugadores;
	
	public Equipo() {
		
	}
	
	/**
	 * @param nombre
	 * @param deporte
	 */
	public Equipo(String nombre, String deporte) {
		super();
		this.nombre = nombre;
		this.deporte = deporte;
		this.jugadores = new HashSet<Jugador>();
	}

	/**
	 * @param nombre
	 * @param deporte
	 * @param jugadores
	 */
	public Equipo(String nombre, String deporte, Set<Jugador> jugadores) {
		super();
		this.nombre = nombre;
		this.deporte = deporte;
		this.jugadores = jugadores;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public Set<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	/**
	 * 
	 * @param jug
	 */
	public void addJugador(Jugador jug) {
		if (this.jugadores != null) {
			this.jugadores.add(jug);
		}
	}
	
}
