package tw.game.bowling.exception;

public class InvalidFrameDataException extends RuntimeException {
    public InvalidFrameDataException() {
        super("Given frame data is invalid.");
    }
}
