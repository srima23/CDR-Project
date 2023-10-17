package com.example.project.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.business.CustomersForm;
import com.example.project.business.Status;
import com.example.project.entity.Customers;
import com.example.project.repository.CustomersRepository;
import com.opencsv.CSVWriter;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepository customersRepository;

    // Method to parse date strings in ISO 8601 format to LocalDate
    // private LocalDate parseDate(String dateString) {
    // DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
    // return LocalDate.parse(dateString, formatter);
    // }

    // Method to parse time strings in ISO 8601 format to LocalTime
    // private LocalTime parseTime(String timeString) {
    // DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;
    // return LocalTime.parse(timeString, formatter);
    // }

    // Method to list CDRs and parse date and time strings to LocalDate and
    // LocalTime
    // public Iterable<Customers> listCustomers() {
    // var listFromDB = customersRepository.findAll();
    // var customersList = new ArrayList<Customers>();
    // return customersList;
    // }

    public Status saveCustomersData(CustomersForm form) {
        Customers customer = new Customers();
        customer.setId(form.getId());
        customer.setName(form.getName());
        customer.setNumber(form.getNumber());
        customer.setLocation(form.getLocation());
        customer.setGender(form.getGender());
        System.out.println("hello");

        customersRepository.save(customer);

        Status status = new Status();
        saveCDRtoCSV();
        System.out.println("not working");
        status.setStatus("added successfully");

        return status;
    }

    public void saveCDRtoCSV() {
        List<Customers> cdrList = (List<Customers>) customersRepository.findAll();

        String filePath = "/home/srimasarajita/Downloads/CapstoneProject/project/src/main/java/com/example/project/cdr_customers.csv";

        exportCustomersToCsv(cdrList, filePath);

    }

    public void exportCustomersToCsv(List<Customers> cdrList, String filePath) {
        try (FileWriter writer = new FileWriter(filePath);
                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END)) {
            List<String[]> data = new ArrayList<>();
            data.add(new String[] { "Id", "Name", "Number", "Location",
                    "Gender" });

            List<Customers> customers = (List<Customers>) customersRepository.findAll();
            for (Customers customer : customers) {

                String[] rowData = {
                        // String.valueOf(customers.getId()),
                        String.valueOf(((CustomersForm) customers).getId()),
                        ((CustomersForm) customers).getName(),
                        String.valueOf(((CustomersForm) customers).getNumber()),
                        ((CustomersForm) customers).getLocation(),
                        ((CustomersForm) customers).getGender(),
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
}
// listFromDB.forEach(cdr -> {
// cdr.setDate(parseDate(cdr.getDate())); // Assuming date string is stored in
// the field named date
// cdr.setTime(parseTime(cdr.getTime())); // Assuming time string is stored in
// the field named time
// cdrList.add(cdr);
// });

// return cdrList;

// }

// public CustomersRepository getCustomersRepository() {
// return customersRepository;
// }

// public void setCustomersRepository(CustomersRepository customersRepository) {
// this.customersRepository = customersRepository;

// }
