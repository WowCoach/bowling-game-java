package tw.game.bowling;

import static java.util.Arrays.asList;
import static tw.game.bowling.parser.BowlingParser.parseToBowlingFrames;

import java.util.List;

import tw.game.bowling.play.BowlingGame;
import tw.game.bowling.rules.MissScoringRule;
import tw.game.bowling.rules.ScoringRule;
import tw.game.bowling.rules.SpareScoringRule;
import tw.game.bowling.rules.StrikeScoringRule;

public class BowlingMain {
    public static void main(String[] args) {
        String input = "X|X|X|X|X|X|X|X|X|X||XX";

        List<ScoringRule> scoringRule = asList(new StrikeScoringRule(), new SpareScoringRule(), new MissScoringRule());
        BowlingGame bowlingGame = new BowlingGame(scoringRule);

        int totalScore = bowlingGame.calculate(parseToBowlingFrames(input));

        System.out.println(totalScore);
    }
}
