package com.ecom.domain;

public class Error {
int code;
String message;
String cause;

public int getCode() {
    return code;
}

public void setCode(int code) {
    this.code = code;
}

public String getMessage() {
    return message;
}

public void setMessage(String message) {
    this.message = message;
}

public String getCause() {
    return cause;
}

public void setCause(String cause) {
    this.cause = cause;
}

}