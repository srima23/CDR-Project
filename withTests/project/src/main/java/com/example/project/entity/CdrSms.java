package com.example.project.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class CdrSms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long usageId;

    private Long subscriberNum;
    private Long receiverNum;
    private LocalDate Date;
    private LocalTime Time;
    private String subscriberLoc;
    private String receiverLoc;
    private String smsType;
    private String status;

}
