package br.com.ywassa.galaxy.exception;

import br.com.ywassa.galaxy.infra.AbstractBusinessException;

public class DataNotFoundException extends AbstractBusinessException {
	public DataNotFoundException(final String message) {
		super(message);
	}
}
