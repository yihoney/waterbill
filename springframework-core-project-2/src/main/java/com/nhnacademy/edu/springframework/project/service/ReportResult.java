package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.WaterBill;

import java.util.Collection;
import java.util.List;

public interface ReportResult {
    void report(List<WaterBill> data);
    List<WaterBill> getOrderByTotalBills(List<WaterBill> data);
}
