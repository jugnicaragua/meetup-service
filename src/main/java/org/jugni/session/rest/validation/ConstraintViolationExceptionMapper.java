package org.jugni.session.rest.validation;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author aalaniz
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<ValidationError> errors = exception.getConstraintViolations().stream()
                .map(this::mapToValidationError)
                .collect(Collectors.toList());

        return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
    }

    private ValidationError mapToValidationError(ConstraintViolation<?> constraintViolation) {
        return new ValidationError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"path", "message"})
    public static class ValidationError {

        private String path;
        private String message;

        public ValidationError() {
        }

        public ValidationError(String path, String message) {
            this.path = path;
            this.message = message;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
