package cz.siemens.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("cz.siemens.inventory")
@SpringBootApplication
public class SiemensInventory {

	public static void main(String[] args) {
		SpringApplication.run(SiemensInventory.class, args);
	}
}
