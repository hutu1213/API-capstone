package project.apicapstone.zuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.apicapstone.zuul.filter.MyFilter;
@Configuration
public class GatewayConfig {
    @Bean
    public MyFilter myFilter() {
        return new MyFilter();
    }
}
