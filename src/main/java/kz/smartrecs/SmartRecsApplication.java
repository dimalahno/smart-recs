package kz.smartrecs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartRecsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartRecsApplication.class, args);
        System.out.println("http://localhost:8080/welcome");
    }

}
