package com.company.client.Error;

import com.company.client.Entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class RestResponseExceptionEntityHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CampaignNotFoundException.class)
    public ResponseEntity<ErrorMessage> campaignNotFoundException(CampaignNotFoundException exception, WebRequest request){
        ErrorMessage message =new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
