package com.blackcofferData.controller;


import com.blackcofferData.Repository.CustomDataRepository;
import com.blackcofferData.entity.DataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    private CustomDataRepository customDataRepository;


    @PostMapping("/upload")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a CSV file to upload.";
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                // Read the CSV file
                List<DataEntity> dataList = new ArrayList<>();
                String line;
                int rowNumber = 0; // Variable to track the row number
                boolean skipHeader = true;
                while ((line = ((BufferedReader) reader).readLine()) != null) {
                    rowNumber++; // Increment row number for each iteration
                    if (skipHeader) {
                        skipHeader = false;
                        continue; // Skip the header line
                    }
                    String[] parts = line.split(",");
                    if (parts.length == 20) { // Assuming CSV file has 20 columns
                        DataEntity data = new DataEntity();
                        // Set entity properties from CSV parts
                        // Ensure correct mapping of properties to CSV columns
                        data.setEndYear(Integer.parseInt(parts[0]));
                        data.setCityLng(parts[1]);
                        data.setCityLat(parts[2]);
                        // Set other properties similarly

                        dataList.add(data);
                    } else {
                        // Log or handle invalid rows with incorrect column count
                        System.out.println("Invalid data in row " + rowNumber + ": " + line);
                    }
                }
                // Save data to database
                customDataRepository.saveAllEntities(dataList);
                return "CSV file uploaded successfully.";
            } catch (Exception e) {
                return "Failed to upload CSV file: " + e.getMessage();
            }
        }
    }

    @GetMapping("/data")
    public List<DataEntity> getAllData() {
        return customDataRepository.findAll();
    }

    @GetMapping("/entities/filter")
    public ResponseEntity<List<DataEntity>> getFilteredEntities(
            @RequestParam(value = "param1", required = false) String param1,
            @RequestParam(value = "param2", required = false) Integer param2
    ) {
        List<DataEntity> filteredEntities = null;
        if (param1 != null && param2 != null) {
            // Implement filtering logic based on both parameters
            // Example: filteredEntities = customDataRepository.findByParam1AndParam2(param1, param2);
        } else if (param1 != null) {
            // Implement filtering logic based on param1
            // Example: filteredEntities = customDataRepository.findByParam1(param1);
        } else if (param2 != null) {
            // Implement filtering logic based on param2
            // Example: filteredEntities = customDataRepository.findByParam2(param2);
        } else {
            // No filtering, return all entities
            filteredEntities = customDataRepository.findAll();
        }
        return ResponseEntity.ok().body(filteredEntities);
    }


   
}