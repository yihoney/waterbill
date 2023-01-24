package com.nhnacademy.edu.springframework.project.repository;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

public interface BillsRepository {
    void load(String filePath) throws IOException;
    List<WaterBill> getWaterBillsList();

    List<WaterBill> findBillsOnUsage(int usage);


    void clean();
}
