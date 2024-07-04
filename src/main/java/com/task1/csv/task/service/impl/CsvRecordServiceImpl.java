package com.task1.csv.task.service.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.task1.csv.task.model.CsvRecord;
import com.task1.csv.task.model.repository.CsvRecordRepository;
import com.task1.csv.task.service.CsvRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CsvRecordServiceImpl implements CsvRecordService {

    private final Logger log = LoggerFactory.getLogger(CsvRecordServiceImpl.class);
    private final CsvRecordRepository csvRecordRepository;

    public CsvRecordServiceImpl(CsvRecordRepository csvRecordRepository) {
        this.csvRecordRepository = csvRecordRepository;
    }

    @Override
    public void saveCSVFile(MultipartFile file, char delimiter) {

        log.info("Start parsing CSV file");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(delimiter)
                    .withQuoteChar('"')
                    .build();

            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(0)
                    .withCSVParser(parser)
                    .build();

            List<CsvRecord> records = new ArrayList<>();
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null)  {

                CsvRecord record = new CsvRecord();
                record.setEng(nextRecord[0]);
                record.setUkr(nextRecord[1]);
                record.setLevel(nextRecord[2]);
                records.add(record);

            }

            log.info("parsed {} lines and prepared for saving to database", records.size());

            csvRecordRepository.saveAll(records);

        } catch (Exception e) {
            throw new RuntimeException("Error processing the file.");
        }
    }

    @Override
    public List<CsvRecord> findInAll(String queryText) {
        log.info("searching by all fields");
        return csvRecordRepository.findInAllFields(queryText);
    }

    @Override
    public List<CsvRecord> findByEngField(String query) {
        log.info("searching by all Eng");
        return csvRecordRepository.findByEngContaining(query);
    }

    @Override
    public List<CsvRecord> findByUkrField(String query) {
        log.info("searching by all Ukr");
        return csvRecordRepository.findByUkrContaining(query);
    }

    @Override
    public List<CsvRecord> findByLevelField(String query) {
        log.info("searching by all Level");
        return csvRecordRepository.findByLevelContaining(query);
    }
}
