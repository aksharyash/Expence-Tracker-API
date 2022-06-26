package com.akshar.expensetrackerapi.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class Errorobject {

    private Integer statusCode;

    private String message;

    private Date timestamp;
}
