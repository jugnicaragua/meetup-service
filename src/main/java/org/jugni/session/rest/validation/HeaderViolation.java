package org.jugni.session.rest.validation;

import javax.ws.rs.core.Response;

/**
 *
 * @author aalaniz
 */
public interface HeaderViolation extends BusinessViolation {

    String HEADER_ERROR_CODE = "x-error-code";
    String HEADER_ERROR_DESCRIPTION = "x-error-description";

    default String codeHeaderName() {
        return HEADER_ERROR_CODE;
    }

    default String messageHeaderName() {
        return HEADER_ERROR_DESCRIPTION;
    }

    static Response badRequest(HeaderViolation headerViolation) {
        return Response.status(Response.Status.BAD_REQUEST)
                .header(headerViolation.codeHeaderName(), headerViolation.getCode())
                .header(headerViolation.messageHeaderName(), headerViolation.getMessage())
                .build();
    }

    static Response badRequest(HeaderViolation headerViolation, Object... parameters) {
        return Response.status(Response.Status.BAD_REQUEST)
                .header(headerViolation.codeHeaderName(), headerViolation.getCode())
                .header(headerViolation.messageHeaderName(), headerViolation.getMessage(parameters))
                .build();
    }
}
