package tw.game.bowling.model;

import tw.game.bowling.exception.BowlingScoreException;

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
        return new BowlingFrame(FrameType.STRIKE, FULL_SCORE, ZERO_SCORE);
    }

    public static BowlingFrame newBowlingMissFrame(int firstStrikeScore, int secondStrikeScore) {
        validateInputScores(firstStrikeScore, secondStrikeScore);
        validateInputScores(firstStrikeScore + secondStrikeScore, 1);
        return new BowlingFrame(FrameType.MISS, firstStrikeScore, secondStrikeScore);
    }

    public static BowlingFrame newBowlingSpareFrame(int firstStrikeScore) {
        validateInputScores(firstStrikeScore, 1);
        return new BowlingFrame(FrameType.SPARE, firstStrikeScore, FULL_SCORE - firstStrikeScore);
    }

    public static BowlingFrame newBowlingRewardFrame(int firstStrikeScore, int secondStrikeScore) {
        validateInputScores(firstStrikeScore, 0);
        validateInputScores(0, secondStrikeScore);
        return new BowlingFrame(FrameType.REWARD, firstStrikeScore, secondStrikeScore);
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

    private static void validateInputScores(int firstStrikeScore, int secondStrikeScore) {
        if (firstStrikeScore < ZERO_SCORE || secondStrikeScore < ZERO_SCORE || firstStrikeScore + secondStrikeScore > FULL_SCORE) {
            throw new BowlingScoreException();
        }
    }
}
