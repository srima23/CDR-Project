package com.example.project.business;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CdrCallForm {
    private String subscriberNum;
    private String receiverNum;
    private String date;
    private String time;
    private int duration;
    private String subscriberLoc;
    private String receiverLoc;
    private String callType;
    private String callStatus;
    private boolean voicemail;

}
