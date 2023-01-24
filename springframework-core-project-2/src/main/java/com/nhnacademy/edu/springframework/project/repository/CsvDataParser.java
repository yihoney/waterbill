package com.nhnacademy.edu.springframework.project.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope ("singleton")
public class CsvDataParser implements DataParser{
    String filePath;

    public CsvDataParser() {

    }

    private CsvDataParser(String filePath) {
        this.filePath = filePath;
    }

    List<WaterBill> waterBillsList = new ArrayList<>();
    BufferedReader bufferedReader = null;

    @Override
    public void parse(String filePath) {
        try {
            InputStream inputStream = new ClassPathResource(filePath).getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            bufferedReader.readLine(); // 첫번째 행 Pass
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String stringArray[] = line.split(",");
                String city = stringArray[1];
                String sector = stringArray[2];
                int unitPrice = Integer.parseInt(stringArray[6]);
                int billTotal = 0;
                waterBillsList.add(new WaterBill(city, sector, unitPrice, billTotal));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert bufferedReader != null;
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<WaterBill> getWaterBillsList() {
        return waterBillsList;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "data/U20221015_202249152_Tariff_20220331.csv";
        CsvDataParser csvDataParser = new CsvDataParser(filePath);
        csvDataParser.parse(filePath);


        System.out.println("-------");
        System.out.println(csvDataParser.getWaterBillsList());
        System.out.println("-------");
    }

    @Override
    public void clean() {
        this.waterBillsList.clear();
    }

}
