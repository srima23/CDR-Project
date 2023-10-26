package com.example.project.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.CdrCustomersCsvProperties;
import com.example.project.business.CustomersForm;
import com.example.project.business.StatusClass;
import com.example.project.entity.Customers;
import com.example.project.repository.CustomersRepository;
import com.opencsv.CSVWriter;

@Service
public class CustomersService {

    private final CustomersRepository customersRepository;

    @Autowired
    private CdrCustomersCsvProperties cdrCustomersCsvProperties;

    @Autowired
    public CustomersService(@Autowired CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public StatusClass saveCustomersDetails(CustomersForm form) {
        StatusClass status = new StatusClass();

        Customers customer = new Customers();
        customer.setId(form.getId());
        customer.setName(form.getName());
        customer.setNumber(form.getNumber());
        customer.setLocation(form.getLocation());
        customer.setGender(form.getGender());

        customersRepository.save(customer);
        status.setStatus("added successfully");
        saveCDRtoCSV();

        return status;
    }

    public void saveCDRtoCSV() {
        List<Customers> cdrList = (List<Customers>) customersRepository.findAll();

        String filePath = cdrCustomersCsvProperties.getPath();

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

            List<Customers> customer = (List<Customers>) customersRepository.findAll();
            for (Customers customers : customer) {

                String[] rowData = {
                        String.valueOf(customers.getId()),
                        customers.getName(),
                        customers.getNumber(),
                        customers.getLocation(),
                        customers.getGender()

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

    // public void setCustomersRepository(CustomersRepository customersRepository2)
    // {
    // }

}
