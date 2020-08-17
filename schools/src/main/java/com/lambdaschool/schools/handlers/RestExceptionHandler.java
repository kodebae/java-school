package com.lambdaschool.schools.handlers;

//import com.lambdaschool.models.ErrorDetail;
import com.lambdaschool.schools.models.ErrorDetail;
import com.lambdaschool.schools.services.HelperFunctions;
//import com.lambdaschool.services.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;


    @Order(Ordered.HIGHEST_PRECEDENCE)
    @RestControllerAdvice
    public class RestExceptionHandler extends ResponseEntityExceptionHandler
    {
        @Autowired
        HelperFunctions helperFunctions;

        public RestExceptionHandler()
        {
            super();
        }
        @Override
        protected ResponseEntity<Object> handleExceptionInternal(
                Exception ex,
                Object body,
                HttpHeaders headers,
                HttpStatus status,
                WebRequest request)
        {
            ErrorDetail errorDetail = new ErrorDetail();
            errorDetail.setTimestamp(new Date());
            errorDetail.setStatus(status.value());
            errorDetail.setTitle("Rest Internal Exception");
            errorDetail.setDetail(ex.getMessage());
            errorDetail.setDeveloperMessage(ex.getClass().getName());

            errorDetail.setErrors(helperFunctions.getConstraintViolation(ex));
            return new ResponseEntity<>(errorDetail, null, status);
        }

    }
//If we run into a situation where we have more than one HIGHEST PRECEDENCE will allow an exception
// handler to take the highest priority over the others.  This is going to be similar to a response,
// we will send out a different response. This is more backwards compatible with Java. We will be sending
// back an error detail. The value, message, and the list of errors will be the most helpful elements to
// return.