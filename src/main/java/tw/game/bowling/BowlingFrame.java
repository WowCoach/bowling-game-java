package tw.game.bowling;

import static tw.game.bowling.FrameType.NORMAL;
import static tw.game.bowling.FrameType.REWARD;
import static tw.game.bowling.FrameType.SPARE;
import static tw.game.bowling.FrameType.STRIKE;

public class BowlingFrame {
    public static final int FULL_SCORE = 10;
    public static final int ZERO_SCORE = 0;
    private int firstStrikeScore;
    private int secondStrikeScore;
    private FrameType frameType;

    private BowlingFrame(FrameType frameType, int firstStrikeScore, int secondStrikeScore) {
        this.frameType = frameType;
        this.firstStrikeScore = firstStrikeScore;
        this.secondStrikeScore = secondStrikeScore;
    }

    public static BowlingFrame newBowlingStrikeFrame() {
        return new BowlingFrame(STRIKE, FULL_SCORE, ZERO_SCORE);
    }

    public static BowlingFrame newBowlingNormalFrame(int firstStrikeScore, int secondStrikeScore) {
        return new BowlingFrame(NORMAL, firstStrikeScore, secondStrikeScore);
    }

    public static BowlingFrame newBowlingSpareFrame(int firstStrikeScore) {
        return new BowlingFrame(SPARE, firstStrikeScore, FULL_SCORE - firstStrikeScore);
    }

    public static BowlingFrame newBowlingRewardFrame(int firstStrikeScore, int secondStrikeScore) {
        return new BowlingFrame(REWARD, firstStrikeScore, secondStrikeScore);
    }

    public int getTotalScore() {
        return firstStrikeScore + secondStrikeScore;
    }

    public FrameType getFrameType() {
        return frameType;
    }

    public int getFirstStrikeScore() {
        return firstStrikeScore;
    }

    public int getSecondStrikeScore() {
        return secondStrikeScore;
    }
}
