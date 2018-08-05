package cz.siemens.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

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
}
