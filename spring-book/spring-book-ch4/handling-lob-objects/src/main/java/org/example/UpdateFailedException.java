package org.example;

import org.springframework.dao.DataAccessException;

public class UpdateFailedException extends DataAccessException {
	public UpdateFailedException(String msg) {
		super(msg);
	}
}
