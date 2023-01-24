package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.MainConfig;
import com.nhnacademy.edu.springframework.project.repository.BillsRepository;
import com.nhnacademy.edu.springframework.project.repository.WaterBill;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = MainConfig.class)
public class ReportResultServiceTest {

    @Autowired
    BillsRepository defaultBillsRepository;
    @Autowired
    CalculateWaterBillsService defaultCalWaterBillsService;

    @Autowired
    ReportResult defaultReportResultService;

    int usage = 1000;
    List<WaterBill> billsOnUsageList;

    @BeforeEach
    void init() throws IOException {
        String filePath = "data/U20221015_202249152_Tariff_20220331.csv";
        defaultBillsRepository.load(filePath);
        defaultCalWaterBillsService.calculateWaterBills(usage);
        billsOnUsageList = defaultBillsRepository.findBillsOnUsage(usage);
    }

    @AfterEach
    void clean() {
        defaultBillsRepository.clean();
    }

    @Test
    void testReport() {
        defaultReportResultService.report(billsOnUsageList);
        assertAll(
                () -> assertNotNull(defaultReportResultService.getOrderByTotalBills(billsOnUsageList))
                );
    }

    @Test
    void testGetOrderByTotalBills() {
        assertAll(
                () -> assertNotNull(defaultReportResultService.getOrderByTotalBills(billsOnUsageList)),
                () -> assertEquals(220, defaultReportResultService.getOrderByTotalBills(billsOnUsageList).get(0).getUnitPrice())
        );
    }
}
