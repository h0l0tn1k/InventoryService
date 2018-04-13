package cz.siemens.inventory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {

	private static EntityManagerFactory emf;

	public static void main(String[] args) {
		ApplicationContext persistenceApplicationContext = new AnnotationConfigApplicationContext(PersistenceApplicationContext.class);
		emf = Persistence.createEntityManagerFactory("default");

		emf.close();
	}
}
