package pl.mateusz.redosz.userservice.exceptions;

public class UsernameTakenException extends Exception {

    public UsernameTakenException() {
        super("Username taken");
    }
}
