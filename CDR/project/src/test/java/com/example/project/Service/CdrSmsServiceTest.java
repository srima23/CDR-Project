package com.example.project.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.project.CdrSmsCsvProperties;
import com.example.project.business.CdrSmsForm;
import com.example.project.entity.CdrSms;
import com.example.project.repository.CdrSmsRepository;
import com.example.project.service.CdrSmsService;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CdrSmsServiceTest {

    @Mock
    private CdrSmsCsvProperties cdrCsvFileProperties;

    @InjectMocks
    private CdrSmsService cdrSmsService;

    @Mock
    private CdrSmsRepository cdrSmsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCDRSmsData() {
        // Create a CdrSmsForm with valid data
        CdrSmsForm form = new CdrSmsForm();
        form.setSmsType("Outgoing");
        form.setReceiverLoc("ReceiverLoc");
        form.setSubscriberLoc("SubscriberLoc");
        form.setReceiverNum("9337116656");
        form.setSubscriberNum("827116656");
        form.setDate("2023-10-15");
        form.setTime("15:30");
        form.setStatus("Delivered");

        // Create a sample CdrSms for expected interaction with the repository
        CdrSms expectedCdrSms = new CdrSms();
        expectedCdrSms.setSmsType("Outgoing");
        expectedCdrSms.setReceiverLoc("ReceiverLoc");
        expectedCdrSms.setSubscriberLoc("SubscriberLoc");
        expectedCdrSms.setReceiverNum("9337116656");
        expectedCdrSms.setSubscriberNum("8237116656");
        expectedCdrSms.setDate(LocalDate.parse("2023-10-15"));
        expectedCdrSms.setTime(LocalTime.parse("15:30"));
        expectedCdrSms.setStatus("Delivered");

        when(cdrSmsRepository.save(expectedCdrSms)).thenReturn(expectedCdrSms);
        // StatusClass status = cdrSmsService.saveCDRSmsData(form);
        // assertTrue(status.getStatus().startsWith("added successfully"));

    }

    @Test
    void testSaveCDRtoCSV() {

        List<CdrSms> cdrSmsList = new ArrayList<>();
        CdrSms cdrSms = new CdrSms();

        cdrSmsList.add(cdrSms);

        when(cdrSmsRepository.findAll()).thenReturn(cdrSmsList);

        when(cdrCsvFileProperties.getPath()).thenReturn(
                "/home/srimasarajita/Downloads/CapstoneProject/project/src/main/java/com/example/project/sms_test.csv");
        // cdrSmsService.saveCDRtoCSV();
    }

    @Test
    void testListSmsCdrs() {

        List<CdrSms> cdrSmsList = new ArrayList<>();

        when(cdrSmsRepository.findAll()).thenReturn(cdrSmsList);
        List<CdrSms> result = cdrSmsService.listSmsCdrs();

        assertEquals(cdrSmsList, result);

    }
}
