package tw.game.bowling.exception;

public class InvalidPositionException extends RuntimeException {
    public InvalidPositionException() {
        super("Given bowling frame position is invalid.");
    }
}
