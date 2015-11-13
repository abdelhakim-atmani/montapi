package edu.spring.restapi.montapi;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class to start the application in standalone mode. Due to this class the
 * application can started as a standalone JAVA application.
 * 
 * @author abdelhakim
 *
 */
// To enable spring boot application.
@SpringBootApplication

// With this annontation the JSONDoc application will scan the code source with
// the properties defined in the file application.properties and generated the
// json ai documentation. Then this json generated can be used to get a html documentation.
@EnableJSONDoc
public class Application {

	/**
	 * Function executed to start the application.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		// Run the application.
		SpringApplication.run(Application.class, args);
	}
}
