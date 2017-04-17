package tw.game.bowling.exception;

public class LackOfFramesException extends RuntimeException {
    public LackOfFramesException() {
        super("Lack of frames error. Please provide enough bowling frame data");
    }
}
