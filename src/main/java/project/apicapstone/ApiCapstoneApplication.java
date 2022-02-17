package project.apicapstone;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import project.apicapstone.entity.Area;
import project.apicapstone.jobrunr.AppConfig;


@SpringBootApplication
@Import(AppConfig.class)
public class ApiCapstoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCapstoneApplication.class, args);

    }

}
