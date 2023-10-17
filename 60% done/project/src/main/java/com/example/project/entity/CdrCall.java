package com.example.project.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class CdrCall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long usageId;

    private int SubscriberNum;
    private int ReceiverNum;
    private LocalDate Date;
    private LocalTime Time;
    private int duration;
    private String SubscriberLoc;
    private String ReceiverLoc;
    private String callType;
    private String callStatus;
    private boolean voicemail;

}
