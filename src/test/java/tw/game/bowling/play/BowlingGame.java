package tw.game.bowling.play;

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
        int totalScore = 0;
        for (int index = 0; index < bowlingFrames.size(); ++index) {
            ScoringRule scoringRule = getMatchScoringRule(bowlingFrames.get(index).getFrameType());
            if (scoringRule != null) {
                totalScore += scoringRule.calculate(index + 1, bowlingFrames);
            }
        }
        return totalScore;
    }

    private ScoringRule getMatchScoringRule(FrameType frameType) {
        return scoringRules.stream().filter(it -> it != null && it.matchFrameType(frameType)).findFirst().orElseGet(null);
    }
}
