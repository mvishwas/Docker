package com.dellemc.devops.meta;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class metadataNotFoundException extends RuntimeException {

	public metadataNotFoundException(String error) {
		super(error);
	}

	private static final long serialVersionUID = 1L;

}
