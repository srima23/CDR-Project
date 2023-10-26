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

import com.example.project.CdrCallCsvProperties;
import com.example.project.business.CdrCallForm;
import com.example.project.entity.CdrCall;
import com.example.project.repository.CdrCallRepository;
import com.example.project.service.CdrCallService;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CdrCallServiceTest {

    @Mock
    private CdrCallCsvProperties cdrCsvFileProperties;

    @InjectMocks
    private CdrCallService cdrCallService;

    @Mock
    private CdrCallRepository cdrCallRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCDRCallData() {

        CdrCallForm form = new CdrCallForm();
        form.setCallType("Incoming");
        form.setDuration(120);
        form.setReceiverLoc("ReceiverLoc");
        form.setSubscriberLoc("SubscriberLoc");
        form.setReceiverNum("9337116656");
        form.setSubscriberNum("8237116656");
        form.setDate("2023-10-15");
        form.setTime("15:30");
        form.setCallStatus("Successful");
        form.setVoicemail(false);

        CdrCall expectedCdrCall = new CdrCall();
        expectedCdrCall.setCallType("Incoming");
        expectedCdrCall.setDuration(120);
        expectedCdrCall.setReceiverLoc("ReceiverLoc");
        expectedCdrCall.setSubscriberLoc("SubscriberLoc");
        expectedCdrCall.setReceiverNum("9337116656");
        expectedCdrCall.setSubscriberNum("8237116656");
        expectedCdrCall.setDate(LocalDate.parse("2023-10-15"));
        expectedCdrCall.setTime(LocalTime.parse("15:30"));
        expectedCdrCall.setCallStatus("Successful");
        expectedCdrCall.setVoicemail(false);

        when(cdrCallRepository.save(expectedCdrCall)).thenReturn(expectedCdrCall);
        // StatusClass status = cdrCallService.saveCDRCallData(form);
        // assertTrue(status.getStatus().startsWith("added successfully"));

    }

    @Test
    void testSaveCDRtoCSV() {

        List<CdrCall> cdrCallList = new ArrayList<>();
        CdrCall cdrCall = new CdrCall();

        cdrCallList.add(cdrCall);

        when(cdrCallRepository.findAll()).thenReturn(cdrCallList);

        when(cdrCsvFileProperties.getPath()).thenReturn(
                "/home/srimasarajita/Downloads/CapstoneProject/project/src/main/java/com/example/project/testCall.csv");
        // cdrCallService.saveCDRtoCSV();
    }

    @Test
    void testListCallCdrs() {

        List<CdrCall> cdrCallList = new ArrayList<>();

        when(cdrCallRepository.findAll()).thenReturn(cdrCallList);
        List<CdrCall> result = cdrCallService.listCallCdrs();

        assertEquals(cdrCallList, result);

    }
}
