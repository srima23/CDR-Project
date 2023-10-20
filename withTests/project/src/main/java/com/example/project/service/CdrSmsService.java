package com.example.project.service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.business.CdrSmsForm;
import com.example.project.business.StatusClass;
import com.example.project.entity.CdrSms;
import com.example.project.repository.CdrSmsRepository;
import com.opencsv.CSVWriter;

import lombok.Data;

@Service
@Data
public class CdrSmsService {

    @Autowired
    private CdrSmsRepository cdrSmsRepository;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public StatusClass saveCDRSmsData(CdrSmsForm form) {

        String date = form.getDate();
        String time = form.getTime();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
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

            StatusClass status = new StatusClass();
            saveCDRtoCSV(form);
            status.setStatus("added successfully");

            return status;

        } catch (Exception e) {
            StatusClass status = new StatusClass();
            System.out.println("An error occurred: " + e.getMessage());
            status.setStatus("error: " + e.getMessage());
            return status;
        }

    }

    public void saveCDRtoCSV(CdrSmsForm cdrSmsForm) {
        List<CdrSms> cdrList = (List<CdrSms>) cdrSmsRepository.findAll();

        String filePath = "/home/srimasarajita/Downloads/CapstoneProject/project/src/main/java/com/example/project/cdr_sms.csv";

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

    public Object listSmsCdrs() {
        return null;
    }

}
