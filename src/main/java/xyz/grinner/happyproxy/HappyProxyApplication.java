package xyz.grinner.happyproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HappyProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HappyProxyApplication.class, args);
    }

}
