package uz.zako.oquv_markaz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Lesson53JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lesson53JwtApplication.class, args);
    }

}
