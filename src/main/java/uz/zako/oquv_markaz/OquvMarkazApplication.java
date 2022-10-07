package uz.zako.oquv_markaz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

@SpringBootApplication
@EnableWebMvc
@EnableScheduling
public class OquvMarkazApplication {

    public static void main(String[] args) {
        SpringApplication.run(OquvMarkazApplication.class, args);

    }

    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:3001","http://localhost:3002","http://localhost:3003");
            }
        };
    }

    //Har sekund da chiqarib turadi
//    @Scheduled(fixedRate = 1000L)
//    public void getfixedRate(){
//        System.out.println("getfixedRate"+new Date());
//    }
//
//    //Bajarilib bolgandan kn 1 sekund dan kn chiqaradi
//    @Scheduled(fixedDelay = 1000L)
//    public void getfixedDelay(){
//        System.out.println("getfixedDelay"+new Date());
//    }

    // second minute hour day month weekday
    @Scheduled(cron = "0 14 18 4 10 *")
    public void startCron(){
        System.out.println("startCron"+new Date());
    }



}
