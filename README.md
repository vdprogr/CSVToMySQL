# CSVToMySQL

## How to start project

In order to start this project you need:
1. In the local MySQL database create database dbcsv

2. Parameters to connect database
   username=root
   password=root

3.  you can use file to test
 - english_ukrainian_texts.csv - fields separated by comma
 - english_ukrainian_texts-2 - fields separated by semicolon

4. use link http://localhost:8080/swagger-ui/index.html for SWAGGER
5. Use link http://localhost:8080/actuator/prometheus for metrics

6. CSV file three fields
  - eng (english phrase)
  - urk (ukrainian phrase)
  - level (level grammar)

# Files to test

7. Source files with different delimiters
 - english_ukrainian_texts-comma.csv
 - english_ukrainian_texts-semicolon.csv
