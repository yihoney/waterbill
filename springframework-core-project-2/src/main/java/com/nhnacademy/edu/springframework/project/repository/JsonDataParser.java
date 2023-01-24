package com.nhnacademy.edu.springframework.project.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Scope("singleton")
public class JsonDataParser implements DataParser {
    String filePath;

    public JsonDataParser() {

    }

    private JsonDataParser(String filePath) {
        this.filePath = filePath;
    }

    List<WaterBill> waterBillsList = new ArrayList<>();

    @Override
    public void parse(String filePath) throws IOException{

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = getData(filePath);

            List<Map<String, Object>> jsonMap = objectMapper.readValue(jsonStr, new TypeReference<List<Map<String, Object>>>() {
            });
            for (Map<String, Object> json : jsonMap) {
                String city = json.get("지자체명").toString();
                String sector = json.get("업종").toString();
                int unitPrice = Integer.parseInt(json.get("구간금액(원)").toString());
                int billTotal = 0;
                waterBillsList.add(new WaterBill(city, sector, unitPrice, billTotal));
            }
    }

    public List<WaterBill> getWaterBillsList() {
        return waterBillsList;
    }

    public String getData(String filePath) {
        return new BufferedReader(new InputStreamReader(
                this.getClass().getClassLoader().getResourceAsStream(filePath))
        ).lines().collect(Collectors.joining());
    }

    @Override
    public void clean() {
        this.waterBillsList.clear();
    }

    public static void main(String[] args) throws IOException {
        String filePath = "data/U20221015_202249163_Tariff_20220331.json";
        JsonDataParser jsonDataParser = new JsonDataParser(filePath);
        jsonDataParser.parse(filePath);


        System.out.println("-------");
        System.out.println(jsonDataParser.getWaterBillsList());
        System.out.println("-------");
    }
}
