package com.nhnacademy.edu.springframework.project.repository;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface DataParser {
    void parse(String filePath) throws IOException;
    List<WaterBill> getWaterBillsList();
    void clean();
}
