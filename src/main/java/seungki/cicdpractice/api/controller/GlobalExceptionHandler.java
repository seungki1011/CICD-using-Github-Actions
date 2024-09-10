package seungki.cicdpractice.api.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ProblemDetail> handleValidationException(MethodArgumentNotValidException exception) {

		log.error("Validation Failure occurred: {}", exception.getMessage());

		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setTitle("Validation failed");
		problemDetail.setDetail("One or more fields have validation errors.");

		Map<String, List<String>> validationErrors = exception.getBindingResult().getFieldErrors().stream()
			.collect(Collectors.groupingBy(
				FieldError::getField,
				Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
			));

		problemDetail.setProperty("validation", validationErrors);

		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(problemDetail);
	}
}
