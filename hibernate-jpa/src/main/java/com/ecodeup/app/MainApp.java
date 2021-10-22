package com.ecodeup.app;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.ecodeup.model.Estudiantes;

public class MainApp {

	public static void main(String[] args) {
		
		int opcion = 0;
		Scanner scanner = new Scanner(System.in);
		Estudiantes estudiante;

		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
		while (opcion!=5) {
			System.out.println("1. Crear Registro");
			System.out.println("2. Buscar Registro");
			System.out.println("3. Actualizar Registro");
			System.out.println("4. Eliminar Registro");
			System.out.println("5. Salir");
			System.out.println("Elija una opción:");

			opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Digite Nombres:");
				estudiante = new Estudiantes();
				estudiante.setId(null);
				scanner.nextLine();
				estudiante.setNombre(scanner.nextLine());
				
				System.out.println("Digite Apellidos:");
				estudiante.setApellido(scanner.nextLine());
				System.out.println(estudiante);
				
				System.out.println("Digite Edad:");
				estudiante.setEdad(scanner.nextInt());
				System.out.println(estudiante);
				
				System.out.println("Digite su Direccion de residencia");
				estudiante.setDireccion(scanner.nextLine());
				System.out.println(estudiante);
				
				System.out.println("Digite la carrera universitaria seleccionada");
				estudiante.setCarrera(scanner.nextLine());
				System.out.println(estudiante);
				
				System.out.println("Digite el año de ingreso");
				estudiante.setAño(scanner.nextLine());
				System.out.println(estudiante);
				entity.getTransaction().begin();
				entity.persist(estudiante);
				entity.getTransaction().commit();
				System.out.println("Registro Creado..");
				System.out.println();
				break;
				
			case 2:
				System.out.println("Digite el id del registro para buscarla:");
				estudiante = new Estudiantes();
				estudiante = entity.find(Estudiantes.class, scanner.nextLong());
				if (estudiante != null) {
					System.out.println(estudiante);
					System.out.println();
				} else {
					System.out.println();
					System.out.println("Registro no encontrado... Lista de registros completa");
					List<Estudiantes> listaEstudiantes= new ArrayList<>();
					Query query=entity.createQuery("SELECT p FROM Producto p");
					listaEstudiantes=query.getResultList();
					for (Estudiantes p : listaEstudiantes) {
						System.out.println(p);
					}
					
					System.out.println();
				}

				break;
			case 3:
				System.out.println("Digite el id del registro a actualizar:");
				estudiante = new Estudiantes();

				estudiante = entity.find(Estudiantes.class, scanner.nextLong());
				if (estudiante != null) {
					System.out.println(estudiante);
					System.out.println("Digite los Nombres :");
					scanner.nextLine();
					estudiante.setNombre(scanner.nextLine());
					System.out.println("Digite los Apellidos:");
					estudiante.setApellido(scanner.nextLine());
					System.out.println("Digite su edad:");
					estudiante.setEdad(scanner.nextInt());
					System.out.println("Digite su direccion de residencia:");
					estudiante.setDireccion(scanner.nextLine());
					System.out.println("Digite sus carrera universitaria:");
					estudiante.setCarrera(scanner.nextLine());
					System.out.println("Digite año de ingreso:");
					estudiante.setAño(scanner.nextLine());
					
					entity.getTransaction().begin();
					entity.merge(estudiante);
					entity.getTransaction().commit();
					System.out.println("Registro actualizado..");
					System.out.println();
				} else {
					System.out.println("Registro no encontrado....");
					System.out.println();
				}
				break;
			case 4:
				System.out.println("Digite el id del registro a eliminar:");
				estudiante = new Estudiantes();

				estudiante = entity.find(Estudiantes.class, scanner.nextLong());
				if (estudiante != null) {
					System.out.println(estudiante);
					entity.getTransaction().begin();
					entity.remove(estudiante);
					entity.getTransaction().commit();
					System.out.println("Registro eliminado...");
				} else {
					System.out.println("Registro no encontrado...");
				}
				break;
			case 5:entity.close();JPAUtil.shutdown();
			break;

			default:
				System.out.println("Opción no válida\n");
				break;



			}
			
		}
		

	}

}
