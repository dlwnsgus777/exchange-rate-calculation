package youth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CalculationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculationApplication.class, args);
	}

}
