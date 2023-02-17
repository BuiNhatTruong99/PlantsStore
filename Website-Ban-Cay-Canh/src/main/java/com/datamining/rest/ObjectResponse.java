package com.datamining.rest;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class ObjectResponse {
    //time, message, data, status
    private Timestamp time = new Timestamp(System.currentTimeMillis());
    private String message;
    private Object data;
    private int status;


    public ObjectResponse(String message, Object data, int status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }
}


