package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.BillsRepository;
import com.nhnacademy.edu.springframework.project.repository.WaterBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultCalWaterBillsService implements CalculateWaterBillsService {

    @Autowired
    BillsRepository defaultBillsRepository;
    List<WaterBill> waterBillsList;
    List<WaterBill> billsOnUsageList = new ArrayList<>();

    @Override
    public void calculateWaterBills(int usage) {
        waterBillsList = defaultBillsRepository.getWaterBillsList();
        try {
        for( int i=0; i<waterBillsList.size(); i++) {
            WaterBill currentLine = waterBillsList.get(i);
            String city = currentLine.getCity();
            String sector = currentLine.getSector();
            int unitPrice = currentLine.getUnitPrice();
            int totalPrice = currentLine.getUnitPrice() * usage;
            this.billsOnUsageList.add(new WaterBill(city, sector, unitPrice, totalPrice));
        }}
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<WaterBill> getBillsOnUsageList() {
        return this.billsOnUsageList;
    }
}
