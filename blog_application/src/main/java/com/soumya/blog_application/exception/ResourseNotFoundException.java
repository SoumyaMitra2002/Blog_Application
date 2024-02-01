package com.soumya.blog_application.exception;

import lombok.Data;

@Data
public class ResourseNotFoundException extends RuntimeException {
    String resourseName;
    String fieldName;
    Integer fieldValue;
    public ResourseNotFoundException(String resourseName, String fieldName, Integer fieldValue) {
        super(String.format("%s not found with %s : %d",resourseName,fieldName,fieldValue));
        this.resourseName = resourseName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    
}
