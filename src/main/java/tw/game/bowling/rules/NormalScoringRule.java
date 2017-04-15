package tw.game.bowling.rules;

import static tw.game.bowling.model.FrameType.NORMAL;

import java.util.List;

import tw.game.bowling.model.BowlingFrame;
import tw.game.bowling.model.FrameType;

public class NormalScoringRule extends ScoringRule {


    @Override
    protected boolean isFrameTypeMatching(FrameType frameType) {
        return NORMAL.equals(frameType);
    }

    @Override
    protected int calculateNextFrameScore(int nextFrameIndex, List<BowlingFrame> bowlingFrames) {
        return 0;
    }
}
