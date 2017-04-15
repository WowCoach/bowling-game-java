package tw.game.bowling.rules;

import java.util.List;

import tw.game.bowling.exception.InvalidPositionException;
import tw.game.bowling.exception.LackOfFramesException;
import tw.game.bowling.exception.TypeMismatchException;
import tw.game.bowling.model.BowlingFrame;
import tw.game.bowling.model.FrameType;

public abstract class ScoringRule {
    public int calculate(int framePosition, List<BowlingFrame> bowlingFrames) {
        int frameIndex = framePosition - 1;
        assertFrameIndex(frameIndex, bowlingFrames.size());

        BowlingFrame bowlingFrame = bowlingFrames.get(frameIndex);
        if (!isFrameTypeMatching(bowlingFrame.getFrameType())) {
            throw new TypeMismatchException();
        }

        return bowlingFrame.getTotalScore() + calculateNextFrameScore(frameIndex + 1, bowlingFrames);
    }

    private void assertFrameIndex(int frameIndex, int frameSize) {
        if (frameIndex >= frameSize) {
            throw new InvalidPositionException();
        }
    }

    protected void assertNextFrameExist(int nextFrameIndex, int frameSize) {
        if (nextFrameIndex >= frameSize) {
            throw new LackOfFramesException();
        }
    }

    protected abstract boolean isFrameTypeMatching(FrameType frameType);

    protected abstract int calculateNextFrameScore(int nextFrameIndex, List<BowlingFrame> bowlingFrames);
}
