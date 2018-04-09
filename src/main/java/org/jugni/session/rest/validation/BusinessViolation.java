package org.jugni.session.rest.validation;

/**
 *
 * @author aalaniz
 */
public interface BusinessViolation {

    String getCode();

    String getMessage();

    String getMessage(Object... parameters);

}
