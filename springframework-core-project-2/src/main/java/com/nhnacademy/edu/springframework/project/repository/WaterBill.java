package com.nhnacademy.edu.springframework.project.repository;

public class WaterBill implements Comparable<WaterBill>{
    private final String city;
    private final String sector;
    private final int unitPrice;
    private int billTotal;

    public WaterBill(String city, String sector, int unitPrice, int billTotal) {
        this.city = city;
        this.sector = sector;
        this.unitPrice = unitPrice;
        this.billTotal = billTotal;
    }

    public String getCity() {
        return city;
    }

    public String getSector() {
        return sector;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getBillTotal() {
        return billTotal;
    }

    @Override
    public String toString() {
        return "WaterBill{" +
                "city=" + city +
                ", sector=" + sector +
                ", unitPrice=" + unitPrice +
                ", billTotal=" + billTotal +
                '}';
    }

    @Override
    public int compareTo(WaterBill o) {
        return (this.getBillTotal() - o.getBillTotal());
    }
}
