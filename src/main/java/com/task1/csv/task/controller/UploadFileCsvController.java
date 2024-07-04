package com.task1.csv.task.controller;

import com.task1.csv.task.model.CsvRecord;
import com.task1.csv.task.service.impl.CsvRecordServiceImpl;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/")
@Api(tags = "CSV File Upload API")
public class UploadFileCsvController {
    private final Logger log = LoggerFactory.getLogger(UploadFileCsvController.class);
    private final Counter fileUploadCounter;
    private final CsvRecordServiceImpl csvRecordService;
    @Autowired
    public UploadFileCsvController(MeterRegistry meterRegistry,
                                   CsvRecordServiceImpl csvRecordService) {
        this.fileUploadCounter = meterRegistry.counter("csv.uploads");
        this.csvRecordService = csvRecordService;
    }
    @GetMapping("/health")
    @ApiOperation(value = "health check endpoint", notes="check process status")
    public ResponseEntity<String> checkStatus(){

        return ResponseEntity.ok().body("Process running... " + LocalDateTime.now());
    }
    @PostMapping("/upload")
    @ApiOperation(value = "Upload a CSV file", notes = "Upload a CSV file and save its data into the database.")
    public ResponseEntity<String> UploadCSV(
            @ApiParam(name = "file", value = "CSV file to upload", required = true)
            @RequestParam("file") MultipartFile file,

            @ApiParam(name = "delimiter",
                    value = "char used to separate fields in CSV file", required = false, defaultValue = ",")
            @RequestParam(value = "delimiter", required = false, defaultValue = ","
            ) Character delimiter ) {

        if (file.isEmpty()) {
            log.info("Please upload a CSV file.");
            return ResponseEntity.badRequest().body("Please upload a CSV file.");
        }

        try {
            log.info("delimiter = " + delimiter);
            csvRecordService.saveCSVFile(file, delimiter);
        } catch (Exception e) {
            log.debug("Failed to upload CSV file: " + e.getMessage());
            return ResponseEntity.status(500).body("Failed to upload CSV file: " + e.getMessage());
        }

        log.info("CSV file uploaded successfully.");
        fileUploadCounter.increment();
        return ResponseEntity.ok().body("CSV file uploaded successfully.");
    }
    @GetMapping("/search")
    @ApiOperation(value = "Search within all fields",
            notes = "send query text for searching within all fields")
    public List<CsvRecord> searchData(
            @ApiParam(name = "query", value = "text for search", required = true)
            @RequestParam("query") String queryText) {

        log.info(queryText);
        return csvRecordService.findInAll(queryText);
    }
    @GetMapping("eng/search")
    @ApiOperation(value = "Search by fields Eng",
            notes = "send query text for searching in Eng field")
    public List<CsvRecord> searchDataInEngField(
            @ApiParam(name = "query", value = "text for search", required = true)
            @RequestParam("query") String queryText) {

        log.info(queryText);
        return csvRecordService.findByEngField(queryText);
    }

    @GetMapping("ukr/search")
    @ApiOperation(value = "Search by fields Ukr",
            notes = "send query text for searching in Ukr field")
    public List<CsvRecord> searchDataInUkrField(
            @ApiParam(name = "query", value = "text for search", required = true)
            @RequestParam("query") String queryText) {

        log.info(queryText);
        return csvRecordService.findByUkrField(queryText);
    }

    @GetMapping("level/search")
    @ApiOperation(value = "Search by fields level",
            notes = "send query text for searching in level field")
    public List<CsvRecord> searchDataInLevelField(
            @ApiParam(name = "query", value = "text for search", required = true)
            @RequestParam("query") String queryText) {

        log.info(queryText);
        return csvRecordService.findByLevelField(queryText);
    }
}


