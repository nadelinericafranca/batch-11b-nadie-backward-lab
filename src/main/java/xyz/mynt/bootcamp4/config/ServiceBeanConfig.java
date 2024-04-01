package xyz.mynt.bootcamp4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.mynt.bootcamp4.service.ParcelCostService;
import xyz.mynt.bootcamp4.service.ParcelCostServiceImpl;

@Configuration
public class ServiceBeanConfig {

    @Bean
    public ParcelCostService parcelCostService() {
        return new ParcelCostServiceImpl();
    }

}
