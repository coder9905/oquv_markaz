package uz.zako.oquv_markaz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class OquvMarkazApplication {

    public static void main(String[] args) {
        SpringApplication.run(OquvMarkazApplication.class, args);
    }

}
