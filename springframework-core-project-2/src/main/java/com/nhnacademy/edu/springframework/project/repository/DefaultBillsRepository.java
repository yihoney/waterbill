package com.nhnacademy.edu.springframework.project.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.edu.springframework.project.service.CalculateWaterBillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DefaultBillsRepository implements BillsRepository {

    @Autowired
    DataParser csvDataParser;

    @Autowired
    DataParser jsonDataParser;
    @Autowired
    CalculateWaterBillsService defaultCalWaterBillsService;

    List<WaterBill> billsOnUsageList = new ArrayList<>();
    List<WaterBill> waterBillsList = new ArrayList<>();


    @Override
    public void load(String filePath) throws IOException {
        String ext = filePath.substring(filePath.lastIndexOf(".") + 1);
        // 확장자에 따라 자동으로 parser 를 선택
        if(ext.equals("csv")) {
            csvDataParser.parse(filePath);
            waterBillsList = csvDataParser.getWaterBillsList();
        } else if (ext.equals("json")) {
            jsonDataParser.parse(filePath);
            waterBillsList = jsonDataParser.getWaterBillsList();
        }


    }

    @Override
    public List<WaterBill> getWaterBillsList() {
        return this.waterBillsList;
    }

    @Override
    public List<WaterBill> findBillsOnUsage(int usage) {
        billsOnUsageList = defaultCalWaterBillsService.getBillsOnUsageList();
        return this.billsOnUsageList;
    }

    @Override
    public void clean() {
        this.waterBillsList.clear();
    }
}
