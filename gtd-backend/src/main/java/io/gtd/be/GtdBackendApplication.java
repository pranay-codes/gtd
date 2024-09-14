package io.gtd.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"io.gtd.be"})
public class GtdBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GtdBackendApplication.class, args);
	}

}
