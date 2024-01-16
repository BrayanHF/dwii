package unicordoba.dwii.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM usuario WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, role FROM usuario WHERE username = ?");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(requests -> requests
                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v2/api-docs", "/swagger-resources/**",
                        "/webjars/**", "/usuario-agregar")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/SoftwareSA/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/SoftwareSA/usuario-agregar").permitAll()
                .anyRequest().hasRole("ADMIN"))
                .csrf(csrf -> csrf.disable())
                .formLogin(login -> login.disable())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    /*
     * @Bean
     * public UserDetailsService users() {
     * UserDetails user = User.builder()
     * .username("user")
     * .password("{noop}1234")
     * .roles("USER")
     * .build();
     * UserDetails admin = User.builder()
     * .username("admin")
     * .password("{noop}1234")
     * .roles("USER", "ADMIN")
     * .build();
     * return new InMemoryUserDetailsManager(user, admin);
     * }
     */

}