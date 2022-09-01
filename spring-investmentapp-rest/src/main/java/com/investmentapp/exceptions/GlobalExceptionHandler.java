package com.investmentapp.exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.investmentapp.model.ApiErrors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		headers.add("error", "method not allowed");
		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getMessage());
		errorMessage.add(request.toString());
		ApiErrors errors = new ApiErrors(LocalDate.now(), ex.getMessage(), status.value(), errorMessage);
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		headers.add("error", "Media Type is not Supported");
		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getMessage());
		errorMessage.add(request.toString());
		ApiErrors errors = new ApiErrors(LocalDate.now(), ex.getMessage(), status.value(), errorMessage);
		return ResponseEntity.status(status).body(errors);

	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return super.handleMissingPathVariable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return super.handleTypeMismatch(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return super.handleMissingServletRequestParameter(ex, headers, status, request);
	}

	@ExceptionHandler(PlanNotFoundException.class)
	public ResponseEntity<Object> handlePlanNotFoundException(PlanNotFoundException ex) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("error", "method not allowed");
		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getMessage());
		ApiErrors errors = new ApiErrors(LocalDate.now(), ex.getMessage(), HttpStatus.CONFLICT.value(), errorMessage);
		return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers).body(errors);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exception(Exception ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("error", "exception occured");
		List<String> errorMessage = new ArrayList<>();
		errorMessage.add(ex.getMessage());
		ApiErrors errors = new ApiErrors(LocalDate.now(), ex.getMessage(), HttpStatus.CONFLICT.value(), errorMessage);
		return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers).body(errors);

	}

}
