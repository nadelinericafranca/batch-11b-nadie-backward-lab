package xyz.mynt.bootcamp4.service;

import xyz.mynt.bootcamp4.util.ParcelUtil;

public class ParcelCostServiceImpl implements ParcelCostService {


    @Override
    public double computeCost(double length, double width, double height) throws RuntimeException {

        ParcelUtil.validateValues(length, width, height);

        double volume = ParcelUtil.computeVolume(length, width, height);

        double multiplier = ParcelUtil.getMultiplier(volume);

        return multiplier * volume;
    }


}
