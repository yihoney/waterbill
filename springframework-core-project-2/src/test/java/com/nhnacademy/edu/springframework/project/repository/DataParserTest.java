package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.MainConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = MainConfig.class)
public class DataParserTest {
    @Autowired
    DataParser csvDataParser;
    @Autowired
    DataParser jsonDataParser;
    String filePath1 = "data/U20221015_202249152_Tariff_20220331.csv";
    String filePath2 = "data/U20221015_202249163_Tariff_20220331.json";

    @AfterEach
    void clean() {
        csvDataParser.clean();
        jsonDataParser.clean();
    }

    @Test
    void testParse() {
        assertAll(
                () -> assertDoesNotThrow(() -> csvDataParser.parse(filePath1)),
                () -> assertNotNull(csvDataParser.getWaterBillsList()),
                () -> assertDoesNotThrow(() -> jsonDataParser.parse(filePath2)),
                () -> assertNotNull(jsonDataParser.getWaterBillsList())
        );
    }

    @Test
    void getWaterBillList() throws IOException {
        csvDataParser.parse(filePath1);
        jsonDataParser.parse(filePath2);
        assertAll(
                () -> assertTrue(csvDataParser.getWaterBillsList().size() > 0),
                () -> assertEquals(690, csvDataParser.getWaterBillsList().get(0).getUnitPrice()),
                () -> assertTrue(jsonDataParser.getWaterBillsList().size() > 0),
                () -> assertEquals(690, jsonDataParser.getWaterBillsList().get(0).getUnitPrice())
        );
    }
}
