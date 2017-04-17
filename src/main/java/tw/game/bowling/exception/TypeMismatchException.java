package tw.game.bowling.exception;

public class TypeMismatchException extends RuntimeException {
    public TypeMismatchException() {
        super("Frame type is not matched.");
    }
}
