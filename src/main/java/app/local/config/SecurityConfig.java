package app.local.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityConfig {
//    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(
                                        "/audio/**",
                                        "/fonts/**",
                                        "/js/**",
                                        "/css/**",
                                        "/icon/**",
                                        "/imgs/**",
                                        "/scss/**",
                                        "/**", //?
                                        "/login",
                                        "/music-app",
                                        "/music-app/logout",
                                        "/music-app/register",
                                        "/music-app/process-register",
                                        "/music-app/artists",
                                        "/music-app/artists/**",
                                        "/music-app/playlists",
                                        "/music-app/playlists/**",
                                        "/music-app/songs",
                                        "/music-app/songs/**",
                                        "/music-app/contact"
                                ).permitAll()
                                .requestMatchers(
                                        "/music-app/me"
                                ).hasAnyRole("USER", "ADMIN")
                                .requestMatchers(
                                        "/admin.music-app/**"
                                ).hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .usernameParameter("email")
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/music-app", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/music-app/logout")
                        .logoutSuccessUrl("/music-app")
                        .permitAll()
                )
                .exceptionHandling(eh -> eh
                        .accessDeniedPage("/music-app/login")
                )
        ;
        return http.build();
    }
}
