package br.com.ywassa.galaxy.infra.exception;

public class AbstractBusinessException extends RuntimeException {
	public AbstractBusinessException(final String message) {
		super(message);
	}
}
