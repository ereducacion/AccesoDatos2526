package aleatorio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EscribeLeeFichAleatorio {

	public static void main(String[] args) {
		
		try {
			RandomAccessFile ficheroAleatorio = new RandomAccessFile("src//aleatorio//Alumnos.dat", "rw");
			
			int tamagnoNombreMax = 10;
			int tamagnoRegistro = 4 + (tamagnoNombreMax*2) ;
			Persona p1 = new Persona(21, "Juana");
			Persona p2 = new Persona(23, "Pedro");
			
/*			ficheroAleatorio.writeChars("uno");
			ficheroAleatorio.writeChars("dos");
			ficheroAleatorio.writeChars("tres");
*/			
			
			long pos = 1;
			ficheroAleatorio.seek(pos); // primera posicion
			ficheroAleatorio.writeInt(p1.getEdad()); //primero escribo la edad
			ficheroAleatorio.writeChars(p1.getNombre()); // después escribo el nombre
			
			
			pos = pos + tamagnoRegistro;
			ficheroAleatorio.seek(pos);
			ficheroAleatorio.writeInt(p2.getEdad()); //primero escribo la edad
			ficheroAleatorio.writeChars(p2.getNombre()); // después escribo el nombre
			
			System.out.println("He escrito");
			
			// leer...
			pos = 1; // vuelvo al inicio
			ficheroAleatorio.seek(pos);
			System.out.println(ficheroAleatorio.readInt());
			char nombre[] = new char[tamagnoNombreMax];
			for (int i = 0; i < tamagnoNombreMax; i++) {
				nombre[i] = ficheroAleatorio.readChar();
			}
			System.out.println(nombre);
			
			System.out.println("El curso se ha quedado en la posicion " + ficheroAleatorio.getFilePointer());
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
