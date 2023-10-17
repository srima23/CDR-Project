package com.example.project.business;

import lombok.Data;

@Data

public class CdrSmsForm {
    private int SubscriberNum;
    private int ReceiverNum;
    private String date;
    private String time;
    private String subscriberLoc;
    private String receiverLoc;
    private String smsType;
    private String status;

}
