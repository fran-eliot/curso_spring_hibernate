package init;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Configuration;


@Configuration
@SpringBootApplication
public class Application {
	

    @LoadBalanced // Crucial para que WebClient entienda nombres de Eureka como "servicio_productos"
    @Bean
	public RestClient.Builder getClient() {
		return RestClient.create();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
