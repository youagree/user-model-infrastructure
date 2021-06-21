
package ru.unit_techno.user.model.impl.exception;

public class ActivationCodeNotValid extends IllegalArgumentException {
    public ActivationCodeNotValid(String s) {
        super(s);
    }
}