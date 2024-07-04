package com.task1.csv.task.model.repository;

import com.task1.csv.task.model.CsvRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CsvRecordRepository extends JpaRepository<CsvRecord, Long> {

    @Query(value = "SELECT * FROM csvrecord " +
            "WHERE eng LIKE %:query% " +
            "OR ukr LIKE %:query% " +
            "OR level LIKE %:query%", nativeQuery = true)
    List<CsvRecord> findInAllFields(@Param("query") String query);

    List<CsvRecord> findByEngContaining(String query);
    List<CsvRecord> findByUkrContaining(String query);

    List<CsvRecord> findByLevelContaining(String query);
}