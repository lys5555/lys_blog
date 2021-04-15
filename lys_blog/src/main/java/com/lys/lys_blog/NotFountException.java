package com.lys.lys_blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//没用找到状态码异常
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFountException extends RuntimeException {
    public NotFountException() {
        super();
    }
    public NotFountException(String message) {
        super(message);
    }
    public NotFountException(String message,Throwable cause) {
        super(message,cause);
    }
}
