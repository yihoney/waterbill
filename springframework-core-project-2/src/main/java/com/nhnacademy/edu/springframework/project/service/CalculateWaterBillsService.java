package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.WaterBill;

import java.util.List;

public interface CalculateWaterBillsService {
    public void calculateWaterBills(int usage);
    public List<WaterBill> getBillsOnUsageList();

}
