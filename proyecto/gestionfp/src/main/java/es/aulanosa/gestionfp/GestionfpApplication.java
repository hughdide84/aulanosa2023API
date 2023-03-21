package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class GestionfpApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionfpApplication.class, args);
	}
}
