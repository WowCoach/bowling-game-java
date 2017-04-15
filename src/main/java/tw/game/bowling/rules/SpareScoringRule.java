package tw.game.bowling.rules;

import static tw.game.bowling.model.FrameType.SPARE;

import java.util.List;

import tw.game.bowling.model.BowlingFrame;
import tw.game.bowling.model.FrameType;

public class SpareScoringRule extends ScoringRule {

    @Override
    public boolean matchFrameType(FrameType frameType) {
        return SPARE.equals(frameType);
    }

    @Override
    protected int calculateNextFrameScore(int nextFrameIndex, List<BowlingFrame> bowlingFrames) {
        assertNextFrameExist(nextFrameIndex, bowlingFrames.size());
        return bowlingFrames.get(nextFrameIndex).getFirstStrikeScore();
    }

}
