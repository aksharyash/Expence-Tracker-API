package com.akshar.expensetrackerapi.Exceptions;

import com.akshar.expensetrackerapi.Entity.Errorobject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Errorobject> handleGenralException(Exception ex, WebRequest request){
        Errorobject errorobject = new Errorobject();
        errorobject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorobject.setMessage(ex.getMessage());
        errorobject.setTimestamp(new Date());

        return new ResponseEntity<Errorobject>(errorobject,HttpStatus.INTERNAL_SERVER_ERROR);
    }
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<Errorobject> handleExpenseNotFoundException(ResourceNotFoundException ex, WebRequest request){
//        Errorobject errorobject = new Errorobject();
//        errorobject.setStatusCode(HttpStatus.NOT_FOUND.value());
//        errorobject.setMessage(ex.getMessage());
//        errorobject.setTimestamp(new Date());
//
//        return new ResponseEntity<Errorobject>(errorobject,HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<Errorobject> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request){
//        Errorobject errorobject = new Errorobject();
//        errorobject.setStatusCode(HttpStatus.BAD_REQUEST.value());
//        errorobject.setMessage(ex.getMessage());
//        errorobject.setTimestamp(new Date());
//
//        return new ResponseEntity<Errorobject>(errorobject,HttpStatus.BAD_REQUEST);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp",new Date());
        map.put("statusCode",HttpStatus.BAD_REQUEST.value());
        List<String> errors = ex
                .getBindingResult()
                .getAllErrors()
                .stream().
                map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        map.put("messages",errors);
        return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
    }
}
