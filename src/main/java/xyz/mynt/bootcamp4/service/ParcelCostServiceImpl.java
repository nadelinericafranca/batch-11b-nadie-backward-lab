package xyz.mynt.bootcamp4.service;

import xyz.mynt.bootcamp4.remote.VoucherRemote;
import xyz.mynt.bootcamp4.remote.VoucherResponse;
import xyz.mynt.bootcamp4.util.ParcelUtil;

public class ParcelCostServiceImpl implements ParcelCostService {
    private final VoucherRemote voucherRemote;

    public ParcelCostServiceImpl(VoucherRemote voucherRemote) {
        this.voucherRemote = voucherRemote;
    }

    @Override
    public double computeCost(double length, double width, double height, String voucher) throws RuntimeException {

        // 1. First we call the computeCost that to get the current cost
        double cost = computeCost(length, width, height);

        // 2. Then call the VoucherRemote.getVoucher() to get the status
        // and the amount of the voucher
        VoucherResponse voucherResponse = voucherRemote.getVoucher(voucher);

        // 3. On this line we check if the response contains any error
        if (voucherResponse.isError()) {
            throw new RuntimeException(voucherResponse.getError());
        }

        // 4. Or if the voucher is already claimed
        if (voucherResponse.getStatus() == VoucherResponse.Status.CLAIMED) {
            throw new RuntimeException("Voucher already claimed.");
        }

        // 5. If all validations succeed, then we will subtract the discount from
        // the current cost and return the difference.
        return cost - voucherResponse.getDiscount();
    }

    @Override
    public double computeCost(double length, double width, double height) throws RuntimeException {

        ParcelUtil.validateValues(length, width, height);

        double volume = ParcelUtil.computeVolume(length, width, height);

        double multiplier = ParcelUtil.getMultiplier(volume);

        return multiplier * volume;
    }
}
