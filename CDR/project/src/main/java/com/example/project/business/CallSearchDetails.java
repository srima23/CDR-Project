package com.example.project.business;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CallSearchDetails {
    String subscriberNum;
    String receiverNum;
    String date;
    String time;
    int duration;
    String subscriberLoc;
    String receiverLoc;
    String callType;
    String callStatus;
    boolean voicemail;

}
