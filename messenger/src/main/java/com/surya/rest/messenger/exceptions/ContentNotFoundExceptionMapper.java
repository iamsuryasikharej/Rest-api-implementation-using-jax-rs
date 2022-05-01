package com.surya.rest.messenger.exceptions;

import com.surya.rest.messenger.model.ErrorMessage;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

public class ContentNotFoundExceptionMapper implements ExceptionMapper<ContentNotFoundException> {
	@Override
	public Response toResponse(ContentNotFoundException exception) {
		System.out.println(exception);
		ErrorMessage errorMessage=new ErrorMessage("Content not found yaar", 404, "https://www.github.com");
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}


}

