package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.MainConfig;
import com.nhnacademy.edu.springframework.project.service.CalculateWaterBillsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = MainConfig.class)
public class BillsRepositoryTest {

    @Autowired
    BillsRepository defaultBillsRepository;

    @Autowired
    CalculateWaterBillsService defaultCalWaterBillsService;
    String filePath1 = "data/U20221015_202249152_Tariff_20220331.csv";
    String filePath2 = "data/U20221015_202249163_Tariff_20220331.json";

    @AfterEach
    void clean() {
        defaultBillsRepository.clean();
    }

    @Test
    void testLoadCsv() {
        assertAll(
                () -> assertDoesNotThrow(() -> defaultBillsRepository.load(filePath1)),
                () -> assertNotNull(defaultBillsRepository.getWaterBillsList())
        );
    }

    @Test
    void testLoadJson() {
        assertAll(
                () -> assertDoesNotThrow(() -> defaultBillsRepository.load(filePath2)),
                () -> assertNotNull(defaultBillsRepository.getWaterBillsList())
        );
    }

    @Test
    void testGetWaterBillsListCsv() throws IOException {
        defaultBillsRepository.load(filePath1);
        assertAll(
                () -> assertTrue(defaultBillsRepository.getWaterBillsList().size() > 0),
                () -> assertEquals(690, defaultBillsRepository.getWaterBillsList().get(0).getUnitPrice())
        );
    }

    @Test
    void testGetWaterBillsListJson() throws IOException {
        defaultBillsRepository.load(filePath2);
        assertAll(
                () -> assertTrue(defaultBillsRepository.getWaterBillsList().size() > 0),
                () -> assertEquals(690, defaultBillsRepository.getWaterBillsList().get(0).getUnitPrice())
        );
    }


}
