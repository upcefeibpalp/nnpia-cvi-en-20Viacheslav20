package cz.upce.fei.nnpiacv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "cz.upce.fei.nnpiacv.repository")
@EntityScan(basePackages = "domain")
public class NnpiacvApplication {

	public static void main(String[] args) {
		SpringApplication.run(NnpiacvApplication.class, args);
	}

}
