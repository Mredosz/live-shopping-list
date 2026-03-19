package pl.mateusz.redosz.userservice.exceptions;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        super("Invalid credentials");
    }
}
