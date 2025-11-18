package app;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.Libro;
import util.HibernateUtil;

public class Consultar {

	public static void main(String[] args) {
				// ------------------UTILIZAMOS LO DEFINIDO ANTES-------------
				//obtener la fábrica de la conexión actual para crear una sesión
				SessionFactory fabrica = HibernateUtil.getSessionFactory();
				//------------------------------------------------------------
				// creamos la sesión
				Session sesion = fabrica.openSession();	
				// creamos la transacción de la sesión
				Transaction tx = sesion.beginTransaction();
				System.out.println("Leo los libros");	

				/*
				 * A CONTINUACIÓN COMENTADA LA FORMA TRADICIONAL
				  
				String pregunta = "from Libro where agno < 2000";
				Query<Libro> laQuery = sesion.createQuery(pregunta, Libro.class);
				
				List<Libro> libros = laQuery.list();
				for (Iterator<Libro> iterator = libros.iterator(); iterator.hasNext();) {
					Libro libro = (Libro) iterator.next();
					System.out.println(libro);
				}
				
				*/
				
				// Ejemplo de consulta que parametrizada
				String pregunta = "from Libro where agno < :elagno";
				Query<Libro> laQuery = sesion.createQuery(pregunta, Libro.class);
				
				laQuery.setParameter("elagno", 2000);
							
				List<Libro> libros = laQuery.getResultList();
				for (Iterator<Libro> iterator = libros.iterator(); iterator.hasNext();) {
					Libro libro = (Libro) iterator.next();
					System.out.println(libro);
				}
				
				// Ejemplo de consulta que genera un único resultado
				String pregunta2 = "from Libro where isbn = :isbn";
				Query<Libro> laQuery2 = sesion.createQuery(pregunta2, Libro.class);
				
				laQuery2.setParameter("isbn", "232323");
							
				Libro ellibro= laQuery2.uniqueResult();
				if (ellibro!= null) System.out.println(ellibro);
				else System.out.println("no hay libros así");
				
				tx.commit();
				
				
		        sesion.close();
	}
}
