package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.WaterBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultReportResultService implements ReportResult {

    @Override
    public void report(List<WaterBill> data) {
        List<WaterBill> sortedBillsList = getOrderByTotalBills(data);
        System.out.println("\n다음은 가장 저렴한 가격( billTotal)을 가진 지자체 순위 5위까지의 목록입니다.");
        if (getOrderByTotalBills(data).size() <= 5)
            throw new IndexOutOfBoundsException();
        for(int i=0; i<5; i++) {
            System.out.println((i+1) + ". " +sortedBillsList.get(i));
        }
    }

    @Override
    public List<WaterBill> getOrderByTotalBills(List<WaterBill> data) {

        List<WaterBill> sortedBillsList = data
                .stream()
                .sorted()
                .collect(Collectors.toList());
        return sortedBillsList;
    }
}
