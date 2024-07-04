package com.task1.csv.task.model;

import javax.persistence.*;

@Entity(name = "csvrecord")
public class CsvRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eng;

    private String ukr;

    private String level;

    public CsvRecord() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getEng() {
        return eng;
    }

    public String getUkr() {
        return ukr;
    }

    public String getLevel() {
        return level;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEng(String engl) {
        this.eng = engl;
    }

    public void setUkr(String ukr) {
        this.ukr = ukr;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
