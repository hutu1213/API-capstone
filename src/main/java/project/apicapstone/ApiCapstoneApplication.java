package project.apicapstone;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
//import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import org.springframework.context.annotation.Import;

import project.apicapstone.jobrunr.AppConfig;


@SpringBootApplication
//@EnableZuulProxy
//@EnableDiscoveryClient
//@EnableEurekaClient
//@EnableEurekaServer
@Import(AppConfig.class)
public class ApiCapstoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCapstoneApplication.class, args);

    }

}
