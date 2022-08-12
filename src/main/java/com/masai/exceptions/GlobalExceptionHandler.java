package com.masai.exceptions;

import java.time.LocalDateTime;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExpHandlerMain(Exception ie, WebRequest wr) {
		System.out.println("inside myHandler method...EXP");
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), wr.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException manv,
			WebRequest wr) {
		String message = manv.getBindingResult().getFieldError().getDefaultMessage();
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), message, wr.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(RollbackException.class)
	public ResponseEntity<MyErrorDetails> handleRollbackException(Exception exp, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),
				"Improper arguments passed in jason. Validation failed", req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<MyErrorDetails> handleValidationException(Exception exp, WebRequest req) {
		System.out.println("Inside Constraint Violation Exception. Exception is being handled");
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),
				"Improper arguments passed in json. Validation failed", req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> mynotFoundHandler(NoHandlerFoundException nfe, WebRequest req) {
		System.out.println("Inside No Handler Found Exception. Exception is being handled");
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myIllegalHandler(MemberNotFoundException me, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), me.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

}
