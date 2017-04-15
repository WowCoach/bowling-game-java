package tw.game.bowling;

import static tw.game.bowling.FrameType.REWARD;
import static tw.game.bowling.FrameType.SPARE;
import static tw.game.bowling.FrameType.STRIKE;

import java.util.List;

public class DefaultScoringRule implements ScoringRule {
    @Override
    public int calculate(int framePosition, List<BowlingFrame> bowlingFrames) {
        int frameIndex = framePosition - 1;
        assertFrameIndex(frameIndex, bowlingFrames.size());

        BowlingFrame bowlingFrame = bowlingFrames.get(frameIndex);
        FrameType frameType = bowlingFrame.getFrameType();
        if (REWARD.equals(frameType)) {
            return 0;
        }

        int rewardScore = 0;
        if (STRIKE.equals(frameType)) {
            rewardScore = getNextTwoBowlingFrameTotalScore(frameIndex, bowlingFrames);
        } else if (SPARE.equals(frameType)) {
            rewardScore = getNextBowlingFrameTotalScore(frameIndex, bowlingFrames);
        }

        return bowlingFrame.getTotalScore() + rewardScore;
    }

    private void assertFrameIndex(int frameIndex, int size) {
        if (frameIndex >= size) {
            throw new InvalidPositionException();
        }
    }

    private int getNextTwoBowlingFrameTotalScore(int frameIndex, List<BowlingFrame> bowlingFrames) {
        return getNextBowlingFrameTotalScore(frameIndex, bowlingFrames) + getNextBowlingFrameTotalScore(frameIndex + 1, bowlingFrames);
    }

    private int getNextBowlingFrameTotalScore(int frameIndex, List<BowlingFrame> bowlingFrames) {
        int nextFrameIndex = frameIndex + 1;
        if (nextFrameIndex >= bowlingFrames.size()) {
            throw new LackOfFrameException();
        }
        return bowlingFrames.get(nextFrameIndex).getTotalScore();
    }
}
