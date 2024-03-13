package berfin.climatecinema;

import java.util.HashSet;


import berfin.climatecinema.entities.concretes.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import berfin.climatecinema.business.concretes.UserService;

@SpringBootApplication
public class ClimateCinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClimateCinemaApplication.class, args);
    }

    /*@Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.save(User.builder().username("a").password("1").roles(new HashSet<>()).build());

            userService.addRoleToUser("a", "ROLE_ADMIN");
            userService.addRoleToUser("a", "ROLE_USER");

        };
    }*/
}