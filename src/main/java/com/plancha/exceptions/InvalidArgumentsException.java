package com.plancha.exceptions;

public class InvalidArgumentsException extends Exception {

	public InvalidArgumentsException(String operation) {
        super("Operation " + operation + " doesn't have the right number of arguments");
    }
}
