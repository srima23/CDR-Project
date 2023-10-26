package com.example.project.business;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CdrSmsForm {
    private String subscriberNum;
    private String receiverNum;
    private String date;
    private String time;
    private String subscriberLoc;
    private String receiverLoc;
    private String smsType;
    private String status;

}
