package tw.game.bowling;

import java.util.List;

public abstract class ScoringRule {
    public int calculate(int framePosition, List<BowlingFrame> bowlingFrames) {
        int frameIndex = framePosition - 1;
        assertFrameIndex(frameIndex, bowlingFrames.size());

        BowlingFrame bowlingFrame = bowlingFrames.get(frameIndex);
        checkFrameTypeMatched(bowlingFrame.getFrameType());

        return bowlingFrame.getTotalScore() + calculateNextFrameScore(frameIndex + 1, bowlingFrames);
    }

    private void assertFrameIndex(int frameIndex, int frameSize) {
        if(frameIndex >= frameSize){
            throw new InvalidPositionException();
        }
    }

    protected void assertNextFrameExist(int nextFrameIndex, int frameSize) {
        if (nextFrameIndex >= frameSize) {
            throw new LackOfFramesException();
        }
    }

    protected abstract void checkFrameTypeMatched(FrameType frameType);

    protected abstract int calculateNextFrameScore(int nextFrameIndex, List<BowlingFrame> bowlingFrames);
}
