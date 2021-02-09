package com.java.exam.component;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends RuntimeException{

	private static final long serialVersionUID = 1L;

    private String message;
    private int code;

    public CustomException() {

    }
    public CustomException(String message, int code) {
        super();
        this.message = message;
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
}
