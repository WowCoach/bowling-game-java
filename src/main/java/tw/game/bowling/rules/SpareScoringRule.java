package tw.game.bowling.rules;

import static tw.game.bowling.model.FrameType.SPARE;

import java.util.List;

import tw.game.bowling.exception.TypeMismatchException;
import tw.game.bowling.model.BowlingFrame;
import tw.game.bowling.model.FrameType;

public class SpareScoringRule extends ScoringRule {

    @Override
    protected void checkFrameTypeMatched(FrameType frameType) {
        if (!SPARE.equals(frameType)) {
            throw new TypeMismatchException();
        }
    }

    @Override
    protected int calculateNextFrameScore(int nextFrameIndex, List<BowlingFrame> bowlingFrames) {
        assertNextFrameExist(nextFrameIndex, bowlingFrames.size());
        return bowlingFrames.get(nextFrameIndex).getFirstStrikeScore();
    }

}
