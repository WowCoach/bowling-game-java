package tw.game.bowling.rules;

import static tw.game.bowling.model.FrameType.STRIKE;

import java.util.List;

import tw.game.bowling.model.BowlingFrame;
import tw.game.bowling.model.FrameType;

public class StrikeScoringRule extends ScoringRule {
    @Override
    public boolean matchFrameType(FrameType frameType) {
        return STRIKE.equals(frameType);
    }

    @Override
    protected int calculateNextFrameScore(int nextFrameIndex, List<BowlingFrame> bowlingFrames) {
        assertNextFrameExist(nextFrameIndex, bowlingFrames.size());
        BowlingFrame bowlingFrame = bowlingFrames.get(nextFrameIndex);
        int totalScore = bowlingFrame.getTotalScore();
        if (matchFrameType(bowlingFrame.getFrameType())) {
            int nextNextFrameIndex = nextFrameIndex + 1;
            assertNextFrameExist(nextNextFrameIndex, bowlingFrames.size());
            totalScore += bowlingFrames.get(nextNextFrameIndex).getFirstStrikeScore();
        }
        return totalScore;
    }
}
