package xyz.mynt.bootcamp4.remote;

public interface VoucherRemote {

    VoucherResponse getVoucher(String voucher) throws RuntimeException;

}
