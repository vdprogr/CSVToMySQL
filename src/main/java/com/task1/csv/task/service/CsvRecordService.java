package com.task1.csv.task.service;

import com.task1.csv.task.model.CsvRecord;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CsvRecordService {

    void saveCSVFile(MultipartFile file, char delimiter);

    List<CsvRecord> findInAll(String queryText);

    List<CsvRecord> findByEngField(String query);

    List<CsvRecord> findByUkrField(String query);

    List<CsvRecord> findByLevelField(String query);
}
