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

    @Bean
    public AuthenticationManager getManager(AuthenticationConfiguration conf) throws Exception {
        return conf.getAuthenticationManager();
    }

    @Bean
    public JdbcUserDetailsManager users() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        // Se usan las variables de @Value en lugar de "root" fijo
        ds.setUsername(this.user);
        ds.setPassword(this.pass);
        
        JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager(ds);
        jdbc.setUsersByUsernameQuery("select user,pwd,enabled from users where user=?");
        jdbc.setAuthoritiesByUsernameQuery("select user,rol from roles where user=?");
        return jdbc;
    }

    @Bean
    public SecurityFilterChain filter(HttpSecurity http, AuthenticationManager auth) throws Exception {
        http.csrf(c -> c.disable())
            .authorizeHttpRequests(aut -> aut
                .requestMatchers(HttpMethod.GET, "/productos", "/productos/**").authenticated()
                // hasAuthority es más seguro si en tu DB el rol es solo "ADMINS" sin prefijo ROLE_
                .requestMatchers(HttpMethod.POST, "/productos").hasAuthority("ROLE_ADMINS")
                .requestMatchers(HttpMethod.PUT, "/productos/**").hasAuthority("ROLE_ADMINS")
                .anyRequest().permitAll()
            )
            // Se pasa el AuthenticationManager gestionado por Spring
            .addFilter(new JwtAuthorizationFilter(auth, key));
        return http.build();
    }
    
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
