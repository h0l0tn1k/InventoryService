package cz.siemens.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@ComponentScan("cz.siemens.inventory")
@SpringBootApplication
public class InventoryService extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(InventoryService.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(InventoryService.class);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties(){
		PropertySourcesPlaceholderConfigurer pspc
				= new PropertySourcesPlaceholderConfigurer();
		Resource[] resources = new ClassPathResource[ ]
				{ new ClassPathResource( "application.properties" ) }; //todo change to security.properties
		pspc.setLocations( resources );
		pspc.setIgnoreUnresolvablePlaceholders( true );
		return pspc;
	}
}
