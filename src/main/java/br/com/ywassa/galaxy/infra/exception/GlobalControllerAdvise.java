package br.com.ywassa.galaxy.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvise {

	public static final String MESSAGE_INTERNAL_ERROR = "Internal Error";

	@ExceptionHandler({AbstractBusinessException.class})
	public ResponseEntity<ErrorResponse> handleServerError(final AbstractBusinessException be) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body(new ErrorResponse(be.getMessage()));
	}

	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<ErrorResponse> handleServerError(final IllegalArgumentException iae) {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body(new ErrorResponse(iae.getMessage()));
	}

	@ExceptionHandler({Throwable.class})
	public ResponseEntity<ErrorResponse> handleServerError(final Throwable t) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse(MESSAGE_INTERNAL_ERROR));
	}

}