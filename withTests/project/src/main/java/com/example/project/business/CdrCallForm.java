package com.example.project.business;

import lombok.Data;

@Data

public class CdrCallForm {
    private Long subscriberNum;
    private Long receiverNum;
    private String date;
    private String time;
    private int duration;
    private String subscriberLoc;
    private String receiverLoc;
    private String callType;
    private String callStatus;
    private boolean voicemail;

}
