package com.example.project.service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.business.CdrCallForm;
import com.example.project.business.StatusClass;
import com.example.project.entity.CdrCall;
import com.example.project.repository.CdrCallRepository;
import com.opencsv.CSVWriter;

import lombok.Data;

@Data
@Service
public class CdrCallService {

    @Autowired
    private CdrCallRepository cdrCallRepository;

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

        String filePath = "/home/srimasarajita/Downloads/CapstoneProject/project/src/main/java/com/example/project/cdr_calls.csv";

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

    public Object listCallCdrs() {
        return null;
    }

}
