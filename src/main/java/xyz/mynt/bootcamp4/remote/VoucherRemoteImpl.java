package xyz.mynt.bootcamp4.remote;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class VoucherRemoteImpl implements VoucherRemote {

    // 1. RestTemplate variable
    private final RestTemplate restTemplate;

    // 2. Inject the VOUCHER_API url that we will use
    @Value("${voucher.api}")
    private String VOUCHER_API;

    // 3. Inject the restTemplate
    public VoucherRemoteImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 4. Actual implementation of getVoucher method
    @Override
    public VoucherResponse getVoucher(String voucher) throws RuntimeException {

        ResponseEntity<VoucherResponse> voucherResponse = null;
        try {
            // 5. Call the voucher API with the given voucher
            voucherResponse = restTemplate.getForEntity(VOUCHER_API + voucher.trim(), VoucherResponse.class);
        } catch (HttpClientErrorException e) {
            return e.getResponseBodyAs(VoucherResponse.class);
        }
        // 6. Return the response
        return voucherResponse.getBody();
    }
}