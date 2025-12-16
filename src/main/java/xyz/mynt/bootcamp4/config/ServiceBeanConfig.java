package xyz.mynt.bootcamp4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import xyz.mynt.bootcamp4.remote.VoucherRemote;
import xyz.mynt.bootcamp4.remote.VoucherRemoteImpl;
import xyz.mynt.bootcamp4.service.ParcelCostService;
import xyz.mynt.bootcamp4.service.ParcelCostServiceImpl;

@Configuration
public class ServiceBeanConfig {

    // 1. Inject VoucherRemote instance to ParcelCostServiceImpl
    @Bean
    public ParcelCostService parcelCostService(VoucherRemote voucherRemote) {
        return new ParcelCostServiceImpl(voucherRemote);
    }

    // 2. RestTemplate instance
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // 3. VoucherRemote instance
    @Bean
    public VoucherRemote voucherRemote(RestTemplate restTemplate) {
        return new VoucherRemoteImpl(restTemplate);
    }

}
