package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.MainConfig;
import com.nhnacademy.edu.springframework.project.repository.BillsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = MainConfig.class)
public class CalWaterBillsServiceTest {

    @Autowired
    BillsRepository defaultBillsRepository;
    @Autowired
    CalculateWaterBillsService defaultCalWaterBillsService;

    int usage = 1000;

    @BeforeEach
    void init() throws IOException {
        String filePath = "data/U20221015_202249152_Tariff_20220331.csv";
        defaultBillsRepository.load(filePath);
        defaultCalWaterBillsService.calculateWaterBills(usage);
    }

    @AfterEach
    void clean() {
        defaultBillsRepository.clean();
    }

    @Test
    void testCalculateWaterBills() {
        assertAll(
                () -> assertDoesNotThrow(() -> defaultCalWaterBillsService.calculateWaterBills(usage)),
                () -> assertNotNull(defaultCalWaterBillsService.getBillsOnUsageList())
        );
    }

    @Test
    void testGetBillsOnUsageList() {
        int expectedTotalBill = 690000;
        assertEquals(expectedTotalBill, defaultCalWaterBillsService.getBillsOnUsageList().get(0).getBillTotal());
    }
}
