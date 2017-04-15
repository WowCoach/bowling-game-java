package tw.game.bowling;

import java.util.List;

public interface ScoringRule {
    int calculate(int framePosition, List<BowlingFrame> bowlingFrames);
}
