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
	

	@Bean
	@LoadBalanced
	public RestClient.Builder getBuilder() {
		return RestClient.builder();
	}
	
	@Bean
	public RestClient getClient(RestClient.Builder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
