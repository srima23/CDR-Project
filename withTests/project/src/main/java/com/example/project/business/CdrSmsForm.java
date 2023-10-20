package com.example.project.business;

import lombok.Data;

@Data

public class CdrSmsForm {
    private Long subscriberNum;
    private Long receiverNum;
    private String date;
    private String time;
    private String subscriberLoc;
    private String receiverLoc;
    private String smsType;
    private String status;

}
