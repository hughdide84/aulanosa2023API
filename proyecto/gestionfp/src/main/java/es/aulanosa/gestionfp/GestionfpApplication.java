package es.aulanosa.gestionfp;

import es.aulanosa.gestionfp.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class GestionfpApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GestionfpApplication.class, args);
	}
}
