package tw.game.bowling.rules;

import static tw.game.bowling.model.FrameType.MISS;

import java.util.List;

import tw.game.bowling.model.BowlingFrame;
import tw.game.bowling.model.FrameType;

public class MissScoringRule extends ScoringRule {

    @Override
    public boolean matchFrameType(FrameType frameType) {
        return MISS.equals(frameType);
    }

    @Override
    protected int calculateNextFrameScore(int nextFrameIndex, List<BowlingFrame> bowlingFrames) {
        return 0;
    }
}
