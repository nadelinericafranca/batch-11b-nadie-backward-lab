package xyz.mynt.bootcamp4.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import xyz.mynt.bootcamp4.remote.VoucherRemote;
import xyz.mynt.bootcamp4.remote.VoucherResponse;

public class ParcelCostServiceV2Test {

    // 1. Class interface that will be tested
    ParcelCostService parcelCostService;

    // 2. Mock the voucher remote so that we run the test without being dependent
    // on the actual live API
    @Mock
    VoucherRemote voucherRemote;

    // 3. Initialize the mocked objects, and create the instance of ParcelCostService.
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        parcelCostService = new ParcelCostServiceImpl(voucherRemote);
    }

    // 4. Test case TC-180 "Small Parcel w/ Voucher"
    @Test
    @DisplayName("TC-180 Small parcel with voucher")
    public void testCase_TC180() {
        // Given
        double l = 10;
        double w = 10;
        double h = 10;
        String v = "G10VOUCHER";

        // Prepare mock response
        VoucherResponse voucherResponse = new VoucherResponse();
        voucherResponse.setDiscount(10);
        voucherResponse.setStatus(VoucherResponse.Status.AVAILABLE);
        voucherResponse.setExpiry("2025-01-01 23:59:59");

        // Mock
        Mockito.when(voucherRemote.getVoucher(ArgumentMatchers.anyString()))
                .thenReturn(voucherResponse);

        // Execution
        double cost = parcelCostService.computeCost(l, w, h, v);

        // Assertion
        Assertions.assertEquals(20, cost);
    }

    // 5. Test case TC-190 "Medium Parcel w/ Voucher"
    @Test
    @DisplayName("TC-190 Medium parcel with voucher")
    public void testCase_TC190() {
        // Given
        double l = 20;
        double w = 10;
        double h = 10;
        String v = "G10VOUCHER";

        // Prepare mock response
        VoucherResponse voucherResponse = new VoucherResponse();
        voucherResponse.setDiscount(10);
        voucherResponse.setStatus(VoucherResponse.Status.AVAILABLE);
        voucherResponse.setExpiry("2025-01-01 23:59:59");

        // Mock
        Mockito.when(voucherRemote.getVoucher(ArgumentMatchers.anyString()))
                .thenReturn(voucherResponse);

        // Execution
        double cost = parcelCostService.computeCost(l, w, h, v);

        // Assertion
        Assertions.assertEquals(70, cost);
    }

    // 6. Test case TC-180 "Large Parcel w/ Voucher"
    @Test
    @DisplayName("TC-200 Large parcel with voucher")
    public void testCase_TC200() {
        // Given
        double l = 30;
        double w = 10;
        double h = 10;
        String v = "G10VOUCHER";

        // Prepare mock response
        VoucherResponse voucherResponse = new VoucherResponse();
        voucherResponse.setDiscount(10);
        voucherResponse.setStatus(VoucherResponse.Status.AVAILABLE);
        voucherResponse.setExpiry("2025-01-01 23:59:59");

        // Mock
        Mockito.when(voucherRemote.getVoucher(ArgumentMatchers.anyString()))
                .thenReturn(voucherResponse);

        // Execution
        double cost = parcelCostService.computeCost(l, w, h, v);

        // Assertion
        Assertions.assertEquals(140, cost);
    }

}