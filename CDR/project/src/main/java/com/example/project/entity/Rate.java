package com.example.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode

public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String callType;
    private String smsType;
    private int rate;

}
