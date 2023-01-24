package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.config.MainConfig;
import com.nhnacademy.edu.springframework.project.repository.BillsRepository;
import com.nhnacademy.edu.springframework.project.repository.WaterBill;
import com.nhnacademy.edu.springframework.project.service.CalculateWaterBillsService;
import com.nhnacademy.edu.springframework.project.service.ReportResult;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BootStrap {
    public static void main(String[] args) throws IOException {

        String filePathCsv = "data/U20221015_202249152_Tariff_20220331.csv";
        String filePatgJson = "data/U20221015_202249163_Tariff_20220331.json";
        int usage;
        Scanner scanner = new Scanner(System.in);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        // 요금표 데이터를 저장하고 조회하는 역할을 하는 스프링빈
        BillsRepository billsRepository =
                BillsRepository.class.cast(context.getBean("defaultBillsRepository"));
        // 입력받은 사용량으로 요금표에서 구간을 찾아내고 요금을 계산해 주는 스프링빈
        CalculateWaterBillsService waterBillsService =
                CalculateWaterBillsService.class.cast(context.getBean("defaultCalWaterBillsService"));
        // 결과를 화면에 표시하는 스프링빈
        ReportResult reportResultService =
                ReportResult.class.cast(context.getBean("defaultReportResultService"));

        // 확장자에 따라 자동으로 parser 를 선택
        billsRepository.load(filePatgJson);

        System.out.print("상수도 사용량을 입력하세요: ");
        usage = scanner.nextInt();
        System.out.println("\n입력받은 상수도 사용량은 " + usage + "입니다.");
        scanner.close();

        waterBillsService.calculateWaterBills(usage);
        List<WaterBill> billsOnUsageList = billsRepository.findBillsOnUsage(usage);
        reportResultService.report(billsOnUsageList);

    }
}
