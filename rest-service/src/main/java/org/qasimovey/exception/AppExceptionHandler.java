package org.qasimovey.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice //Controller -a listene edir, when exception occurs, it will trigger
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleNullPointerException(Exception ex, WebRequest req) {
        ErrorMessage em=new ErrorMessage(new Date(),(ex.getLocalizedMessage()==null)?ex.toString():ex.getLocalizedMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(em,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SpecialException.class)
    public ResponseEntity<Object> handleException(SpecialException fe){
        ErrorMessage em=new ErrorMessage(new Date(),fe.getLocalizedMessage());
        return new ResponseEntity<Object>(em,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}