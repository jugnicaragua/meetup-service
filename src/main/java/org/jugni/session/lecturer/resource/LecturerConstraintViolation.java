package org.jugni.session.lecturer.resource;

import org.jugni.session.rest.validation.HeaderViolation;

/**
 *
 * @author aalaniz
 */
public enum LecturerConstraintViolation implements HeaderViolation {

    LECTURER_ALREADY_EXISTS("JUG-LE-1", "The lecturer with the specified id [%s] already exists"),
    LECTURER_DOESNOT_EXIST("JUG-LE-2", "The lecturer with the specified id [%s] doesn't exist");

    private final String code;
    private final String message;

    private LecturerConstraintViolation(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getMessage(Object... parameters) {
        return String.format(message, parameters);
    }

}
