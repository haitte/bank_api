package com.junying.Exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BalanceTooLowException extends WebApplicationException {
    public BalanceTooLowException(String message) {
        super(Response.status(Response.Status.CONFLICT)
                .entity(message).type(MediaType.TEXT_PLAIN).build());
    }
}
