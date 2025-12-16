package xyz.mynt.bootcamp4.service;

public interface ParcelCostService {

    double computeCost(double length, double width, double height) throws RuntimeException;

    // New Service Method that accepts voucher
    double computeCost(double length, double width, double height, String voucher) throws RuntimeException;

}
