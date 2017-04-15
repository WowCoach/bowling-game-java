package tw.game.bowling;

import static tw.game.bowling.FrameType.SPARE;

import java.util.List;

public class SpareScoringRule extends ScoringRule {

    @Override
    protected void checkFrameTypeMatched(FrameType frameType) {
        if(!SPARE.equals(frameType)) {
            throw new FrameTypeMismatchException();
        }
    }

    @Override
    protected int calculateNextFrameScore(int nextFrameIndex, List<BowlingFrame> bowlingFrames) {
        assertNextFrameExist(nextFrameIndex, bowlingFrames.size());
        return bowlingFrames.get(nextFrameIndex).getFirstStrikeScore();
    }

}
