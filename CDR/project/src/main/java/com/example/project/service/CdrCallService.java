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

import com.example.project.CdrCallCsvProperties;
import com.example.project.business.CdrCallForm;
import com.example.project.business.StatusClass;
import com.example.project.entity.CdrCall;
import com.example.project.repository.CdrCallRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class CdrCallService {

    private final CdrCallCsvProperties cdrCsvFileProperties;

    @Autowired
    private CdrCallRepository cdrCallRepository;

    @Autowired
    public CdrCallService(CdrCallCsvProperties cdrCsvFileProperties) {
        this.cdrCsvFileProperties = cdrCsvFileProperties;
    }

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public StatusClass saveCDRCallData(CdrCallForm form) {
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

        CdrCall cdrCall = new CdrCall();
        cdrCall.setCallType(form.getCallType());
        cdrCall.setDuration(form.getDuration());
        cdrCall.setReceiverLoc(form.getReceiverLoc());
        cdrCall.setSubscriberLoc(form.getSubscriberLoc());
        cdrCall.setReceiverNum(form.getReceiverNum());
        cdrCall.setSubscriberNum(form.getSubscriberNum());
        cdrCall.setDate(localDate);
        cdrCall.setTime(localTime);
        cdrCall.setCallStatus(form.getCallStatus());
        cdrCall.setVoicemail(form.isVoicemail());

        cdrCallRepository.save(cdrCall);

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
        List<CdrCall> cdrList = (List<CdrCall>) cdrCallRepository.findAll();

        String filePath = cdrCsvFileProperties.getPath();

        exportCdrCallsToCsv(cdrList, filePath);

    }

    public void exportCdrCallsToCsv(List<CdrCall> cdrList, String filePath) {
        try (FileWriter writer = new FileWriter(filePath);
                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END)) {
            List<String[]> data = new ArrayList<>();
            data.add(new String[] { "CallType", "Duration", "ReceiverLoc", "SubscriberLoc",
                    "ReceiverNum", "SubscriberNum", "Date", "Time", "CallStatus", "Voicemail" });

            List<CdrCall> cdrCalls = (List<CdrCall>) cdrCallRepository.findAll();
            for (CdrCall cdrCall : cdrCalls) {

                String[] rowData = {
                        cdrCall.getCallType(),
                        String.valueOf(cdrCall.getDuration()),
                        cdrCall.getReceiverLoc(),
                        cdrCall.getSubscriberLoc(),
                        String.valueOf(cdrCall.getReceiverNum()),
                        String.valueOf(cdrCall.getSubscriberNum()),
                        dateFormatter.format(cdrCall.getDate()),
                        timeFormatter.format(cdrCall.getTime()),
                        cdrCall.getCallStatus(),
                        String.valueOf(cdrCall.isVoicemail())
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
            List<CdrCall> cdrList = parseCSVData(inputStream);

            // Save the parsed data to the database
            cdrCallRepository.saveAll(cdrList);
        } catch (IOException e) {
            throw new RuntimeException("Error processing CSV file: " + e.getMessage(),
                    e);
        }
    }

    private List<CdrCall> parseCSVData(InputStream inputStream) throws CsvValidationException {
        List<CdrCall> cdrList = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {
            String[] record;
            try {
                csvReader.readNext();
            } catch (CsvValidationException e) {

                e.printStackTrace();
            }

            while ((record = csvReader.readNext()) != null) {
                // Parse and create CdrCall objects from CSV data
                CdrCall cdrCall = createCdrCallFromCSVRecord(record);
                cdrList.add(cdrCall);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error parsing CSV data: " + e.getMessage(), e);

        }
        return cdrList;
    }

    private CdrCall createCdrCallFromCSVRecord(String[] record) {
        // Implement logic to map CSV data to CdrCall entity
        // Example:
        CdrCall cdrCall = new CdrCall();
        cdrCall.setCallType(record[0]);
        cdrCall.setDuration((int) Long.parseLong(record[1]));
        cdrCall.setReceiverLoc(record[2]);
        cdrCall.setSubscriberLoc(record[3]);
        cdrCall.setReceiverNum((record[4]));
        cdrCall.setSubscriberNum((record[5]));
        // Parse and set date and time
        cdrCall.setDate(LocalDate.parse(record[6], dateFormatter));
        cdrCall.setTime(LocalTime.parse(record[7], timeFormatter));
        cdrCall.setCallStatus(record[8]);
        cdrCall.setVoicemail(Boolean.parseBoolean(record[9]));
        return cdrCall;
    }

    public List<CdrCall> listCallCdrs() {
        return (List<CdrCall>) cdrCallRepository.findAll();
    }

}
