package berfin.climatecinema.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import static berfin.climatecinema.security.CustomHttpConfigurer.customDsl;


@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)

public class SecurityConfig {

    /*@Bean
    public UserDetailsService users() {
        UserDetails user1 = User.builder()
                .username("usr")
                .password(bCryptPasswordEncoder().encode("password"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("berfin")
                .password(bCryptPasswordEncoder().encode("pass"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, admin);
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /*http
                .headers(x-> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .authorizeRequests(x -> x.requestMatchers(new AntPathRequestMatcher
                        ("/api/**")).hasRole("ADMIN"))
                .authorizeRequests(x -> x.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
*/

        http.apply(customDsl());
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
