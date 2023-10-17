package com.example.project.business;

import lombok.Data;

@Data

public class CdrCallForm {
    private int SubscriberNum;
    private int ReceiverNum;
    private String date;
    private String time;
    private int duration;
    private String subscriberLoc;
    private String receiverLoc;
    private String callType;
    private String callStatus;
    private boolean voicemail;

}
