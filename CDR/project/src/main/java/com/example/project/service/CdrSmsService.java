package com.example.project.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.CdrSmsCsvProperties;
import com.example.project.business.CdrSmsForm;
import com.example.project.business.StatusClass;
import com.example.project.entity.CdrSms;
import com.example.project.repository.CdrSmsRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

@Service

public class CdrSmsService {

    private final CdrSmsCsvProperties cdrCsvFileProperties;

    @Autowired
    private CdrSmsRepository cdrSmsRepository;

    @Autowired
    public CdrSmsService(CdrSmsCsvProperties cdrCsvFileProperties) {
        this.cdrCsvFileProperties = cdrCsvFileProperties;
    }

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public StatusClass saveCDRSmsData(CdrSmsForm form) {

        String date = form.getDate();
        String time = form.getTime();

        StatusClass status = new StatusClass();
        if (!isValidDate(date)) {
            status.setStatus("error: Invalid date format");
            return status;
        }

        if (!isValidTime(time)) {
            status.setStatus("error: Invalid time format");
            return status;
        }

        LocalDate localDate = LocalDate.parse(date, dateFormatter);
        LocalTime localTime = LocalTime.parse(time, timeFormatter);

        CdrSms cdrSms = new CdrSms();
        cdrSms.setSmsType(form.getSmsType());
        cdrSms.setReceiverLoc(form.getReceiverLoc());
        cdrSms.setSubscriberLoc(form.getSubscriberLoc());
        cdrSms.setReceiverNum(form.getReceiverNum());
        cdrSms.setSubscriberNum(form.getSubscriberNum());
        cdrSms.setDate(localDate);
        cdrSms.setTime(localTime);
        cdrSms.setStatus(form.getStatus());

        cdrSmsRepository.save(cdrSms);
        status.setStatus("added successfully");
        saveCDRtoCSV();

        return status;

    }

    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean isValidTime(String time) {
        try {
            LocalTime.parse(time, timeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public void saveCDRtoCSV() {
        List<CdrSms> cdrList = (List<CdrSms>) cdrSmsRepository.findAll();

        String filePath = cdrCsvFileProperties.getPath();

        exportCdrSmsToCsv(cdrList, filePath);
    }

    public void exportCdrSmsToCsv(List<CdrSms> cdrList, String filePath) {
        try (FileWriter writer = new FileWriter(filePath);
                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END)) {
            List<String[]> data = new ArrayList<>();
            data.add(new String[] { "SmsType", "ReceiverLoc", "SubscriberLoc",
                    "ReceiverNum", "SubscriberNum", "Date", "Time", "Status" });

            List<CdrSms> cdrsms = (List<CdrSms>) cdrSmsRepository.findAll();
            for (CdrSms cdrSms : cdrsms) {

                String[] rowData = {

                        cdrSms.getSmsType(),
                        cdrSms.getReceiverLoc(),
                        cdrSms.getSubscriberLoc(),
                        String.valueOf(cdrSms.getReceiverNum()),
                        String.valueOf(cdrSms.getSubscriberNum()),
                        dateFormatter.format(cdrSms.getDate()),
                        timeFormatter.format(cdrSms.getTime()),
                        cdrSms.getStatus()

                };
                data.add(rowData);

            }
            csvWriter.writeAll(data);

            System.out.println("Data exported to CSV successfully using OpenCSV at: " +
                    filePath);
        } catch (IOException e) {
            System.err.println("Error exporting data to CSV: " + e.getMessage());
        }
    }

    public void processCSVFile(MultipartFile file) throws CsvValidationException {
        try (InputStream inputStream = file.getInputStream()) {
            // Parse and process the CSV data from the input stream
            List<CdrSms> cdrList = parseCSVData(inputStream);

            // Save the parsed data to the database
            cdrSmsRepository.saveAll(cdrList);
        } catch (IOException e) {
            throw new RuntimeException("Error processing CSV file: " + e.getMessage(),
                    e);
        }
    }

    private List<CdrSms> parseCSVData(InputStream inputStream) throws CsvValidationException {
        List<CdrSms> cdrList = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {
            String[] record;
            try {
                csvReader.readNext();
            } catch (CsvValidationException e) {

                e.printStackTrace();
            }

            while ((record = csvReader.readNext()) != null) {
                // Parse and create CdrCall objects from CSV data
                CdrSms cdrSms = createCdrSmsFromCSVRecord(record);
                cdrList.add(cdrSms);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error parsing CSV data: " + e.getMessage(), e);

        }
        return cdrList;
    }

    private CdrSms createCdrSmsFromCSVRecord(String[] record) {
        // Implement logic to map CSV data to CdrCall entity
        // Example:
        CdrSms cdrSms = new CdrSms();
        cdrSms.setSmsType(record[0]);
        cdrSms.setReceiverLoc(record[1]);
        cdrSms.setSubscriberLoc(record[2]);
        cdrSms.setReceiverNum((record[3]));
        cdrSms.setSubscriberNum((record[4]));
        // Parse and set date and time
        cdrSms.setDate(LocalDate.parse(record[5], dateFormatter));
        cdrSms.setTime(LocalTime.parse(record[6], timeFormatter));
        cdrSms.setStatus(record[7]);

        return cdrSms;
    }

    public List<CdrSms> listSmsCdrs() {
        return (List<CdrSms>) cdrSmsRepository.findAll();
    }

}
