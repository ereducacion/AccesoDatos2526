package app;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;
import datos.*;

import jakarta.persistence.EntityExistsException;

public class CRUD {

	public static void main(String[] args) {

		// ------------------UTILIZAMOS LO DEFINIDO ANTES-------------
		//obtener la fábrica de la conexión actual para crear una sesión
		SessionFactory fabrica = HibernateUtil.getSessionFactory();
		//------------------------------------------------------------
		// creamos la sesión
		Session sesion = fabrica.openSession();	
		// creamos la transacción de la sesió
		Transaction tx = sesion.beginTransaction();
		
		// CREACIÓN
		Libro l1 = new Libro(21, "El alquimista", "Paulo Cohelo", "La Alhambra", 2002, "CO-1123", 3, 233, null);
		try {
			sesion.persist(l1);
			tx.commit();
			
		} catch (EntityExistsException e1) {
			System.err.println("El libro ya existe");
			System.err.println(e1.getMessage());
			tx.rollback();
			
		} catch (IllegalArgumentException e2) {
			System.err.println("Error en el libro que se desea guardar");
			System.err.println(e2.getMessage());
			tx.rollback();
		}
		
		// LECTURA (READ)
		Libro l2 = sesion.get(Libro.class, 2);
		if (l2 != null) {
			System.out.println(l2);
			l2.setEditorial("Nueva editorial");
		}
		else {
			System.out.println("El libro con id=2 no existe");
		}
		
		// ACTUALIZACIÓN (UPDATE)
		Libro l3 = sesion.get(Libro.class, 3);
		if (l3 != null) {
			System.out.println("Libro antes:\n" + l3);
			l3.setEditorial("Nueva editorial");
			try {
				tx = sesion.beginTransaction();  // OJO, DESPUÉS DE HABER HECHO UN COMMIT O ROLLBACK INICIAMOS TRANSACCIÓN DE NUEVO
				sesion.merge(l3);
				tx.commit();
				System.out.println("Libro después:\n" + l3);
			} catch (IllegalArgumentException e3) {
				System.err.println("Error en el libro que se desea modificar");
				System.err.println(e3.getMessage());
				tx.rollback();
			}
		}
		else {
			System.out.println("El libro con id=3 no existe");
		}
		
		// BORRADO (DELETE)
		Libro l21 = sesion.get(Libro.class, 21);
		if (l21 != null) {
			System.out.println("Voy a borrar:\n" + l21);
			try {
				tx = sesion.beginTransaction();  // OJO, DESPUÉS DE HABER HECHO UN COMMIT O ROLLBACK INICIAMOS TRANSACCIÓN DE NUEVO
				sesion.remove(l21);
				tx.commit();
			} catch (IllegalArgumentException e4) {
				System.err.println("Error en el libro que se desea borrar");
				System.err.println(e4.getMessage());
				tx.rollback();
			}
		}
		else {
			System.out.println("El libro con id=21 no existe");
		}
		
		System.out.println("FUNCIONO!!");
		
		sesion.close();
		fabrica.close();
		
	}

}
