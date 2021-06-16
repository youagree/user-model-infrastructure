
package ru.unit_techno.user.model.impl.exception;

import javax.persistence.EntityExistsException;

public class LoginAlreadyExistException extends EntityExistsException {
    public LoginAlreadyExistException(String message) {
        super(message);
    }
}