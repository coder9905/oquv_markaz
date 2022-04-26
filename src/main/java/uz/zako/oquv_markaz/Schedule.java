package uz.zako.oquv_markaz;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

// har hafta wuni bajarsin yoki har yil vaqtini ozimiz beramiz.
// funksiya yozib qoyib wuni qaysi payt bajariliwini berib qoyamiz.
@Component
public class  Schedule {

    // fixedy = 3000L 3 sekundda hello qaytib chiqib turadi

    //fixedDelay keyingi funksiya kutib turadi
    // initialDelay - kutib turadi
//    @Scheduled(initialDelay = 1000L,fixedDelay = 3000L)
//    public void seyHelloDelay(){
//        System.out.println("Hello Delay:)");
//    }
//
//    //fixedRate - keyingi funksiyani bajarilaveradi kutmasdan
//    @Scheduled(fixedRate = 1000L)
//    public void seyHelloRate(){
//        System.out.println("Privet Rate:)");
//    }

    //Baza bilan ham iwlawimiz mumkin

//    cron ham takrorlash vaqtini korsatadi
//    0 minut soat kun oy hafta
//    @Scheduled(cron = "0 52 15 19 4 *")
//    public void cronTest(){
//        System.out.println("hello cron:)");
//    }

    // Har kuni 12:00 da iwlaydigan qilib yozamiz
    @Scheduled(cron = "0 0 0 * * *")
    public void dailyWorking(){

        System.out.println(new Date());

    }


}
