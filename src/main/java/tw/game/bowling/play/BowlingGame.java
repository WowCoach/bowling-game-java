package tw.game.bowling.play;

import static java.util.stream.IntStream.range;

import java.util.ArrayList;
import java.util.List;

import tw.game.bowling.model.BowlingFrame;
import tw.game.bowling.model.FrameType;
import tw.game.bowling.rules.ScoringRule;

public class BowlingGame {
    private List<ScoringRule> scoringRules = new ArrayList<>();

    public BowlingGame(List<ScoringRule> scoringRules) {
        this.scoringRules.addAll(scoringRules);
    }

    public int calculate(List<BowlingFrame> bowlingFrames) {
        return range(0, bowlingFrames.size())
                .map(index -> calculateEachBowlingFrame(index, bowlingFrames))
                .sum();
    }

    private int calculateEachBowlingFrame(int index, List<BowlingFrame> bowlingFrames) {
        FrameType frameType = bowlingFrames.get(index).getFrameType();
        return scoringRules.stream()
                .filter(it -> it.matchFrameType(frameType))
                .findFirst()
                .map(rule -> rule.calculate(index + 1, bowlingFrames))
                .orElse(0);
    }

}
