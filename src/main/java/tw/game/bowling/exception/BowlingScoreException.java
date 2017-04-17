package tw.game.bowling.exception;

public class BowlingScoreException extends RuntimeException {
    public BowlingScoreException() {
        super("Given bowling score is not match to the rule.");
    }
}
