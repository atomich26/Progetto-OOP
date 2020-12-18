package it.michdev.restwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * Classe principale dell'applicazione Java. Contiene al suo interno il metodo
 * main() per l'avvio del software.
 * 
 * @version 1.0.0
 * @author Michele Bevilacqua
 */
@SpringBootApplication
@EnableScheduling
public class RestwebserviceApplication {

	/**
	 * Questo metodo è il punto di partenza dell'esecuzione del software.
	 * 
	 * @param args argomenti scritti a riga di comando.
	 */
	public static void main(String[] args) {
		SpringApplication.run(RestwebserviceApplication.class, args);
	}
}
