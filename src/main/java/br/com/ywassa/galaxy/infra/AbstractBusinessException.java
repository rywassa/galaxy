package br.com.ywassa.galaxy.infra;

public class AbstractBusinessException extends RuntimeException {
	public AbstractBusinessException(final String message) {
		super(message);
	}
}
