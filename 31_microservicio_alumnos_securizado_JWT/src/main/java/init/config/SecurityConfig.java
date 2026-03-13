package init.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import init.filters.JwtAuthorizationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Value("${db.security.driver}")
	private String driver;
	@Value("${db.security.url}")
	private String url;
	@Value("${db.security.user}")
	private String user;
	@Value("${db.security.pass}")
	private String pass;
	@Value("${jwt.properties.key}")
	private String key;
	
	private AuthenticationManager auth;
	
	@Bean
	public AuthenticationManager getManager(AuthenticationConfiguration conf) {
		auth = conf.getAuthenticationManager();
		return auth;
	}
	
	@Bean
	public JdbcUserDetailsManager users() {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername("root");
		ds.setPassword("root");
		JdbcUserDetailsManager jdbc=new JdbcUserDetailsManager(ds);
		jdbc.setUsersByUsernameQuery("select user,pwd,enabled from users where user=?");
		jdbc.setAuthoritiesByUsernameQuery("select user,rol from roles where user=?");
		return jdbc;
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception{
		http.csrf(c->c.disable())
		.authorizeHttpRequests(
				aut->aut.requestMatchers(HttpMethod.GET, "/alumnos","/alumnos/**").authenticated()
				.requestMatchers(HttpMethod.POST, "/alumnos").hasRole("ADMINS")
				.requestMatchers(HttpMethod.DELETE, "/alumnos").hasAnyRole("ADMINS","OPERATORS")
				.anyRequest().permitAll()
				)
		.addFilter(new JwtAuthorizationFilter(auth,key));
		return http.build();
		
	}

}
