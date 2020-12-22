package it.michdev.restwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Classe principale dell'applicazione Java. Contiene al suo interno il metodo
 * <code>main()</code> per l'avvio del software.
 * 
 * @version 1.1.2
 * @author Michele Bevilacqua
 * @see it.michdev.restwebservice.controller.RequestController
 */
@SpringBootApplication
@EnableScheduling
public class RestWebServiceApplication {

	/**
	 * Questo metodo è il punto di partenza dell'esecuzione del software.
	 * 
	 * @param args argomenti scritti a riga di comando.
	 */
	public static void main(String[] args) {
		SpringApplication.run(RestWebServiceApplication.class, args);
	}
}
