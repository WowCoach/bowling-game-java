package tw.game.bowling;

import static java.util.Arrays.asList;
import static tw.game.bowling.parser.BowlingParser.parseToBowlingFrames;

import java.util.List;
import java.util.Scanner;

import tw.game.bowling.model.BowlingFrame;
import tw.game.bowling.play.BowlingGame;
import tw.game.bowling.rules.MissScoringRule;
import tw.game.bowling.rules.ScoringRule;
import tw.game.bowling.rules.SpareScoringRule;
import tw.game.bowling.rules.StrikeScoringRule;

public class Main {
    public static void main(String[] args) {

        List<ScoringRule> scoringRule = asList(new StrikeScoringRule(), new SpareScoringRule(), new MissScoringRule());
        BowlingGame bowlingGame = new BowlingGame(scoringRule);

        output("---------Enjoy the bowling game. Input 'exit' to exit!---------");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }

            try {
                List<BowlingFrame> bowlingFrames = parseToBowlingFrames(input);
                int totalScore = bowlingGame.calculate(bowlingFrames);

                output(totalScore);
            } catch (Exception e) {
                output("Please input valid data string.----------:\n" + e.getMessage());
            }
        }
    }

    private static void output(int value) {
        System.out.println(value);
        System.out.println();
    }

    private static void output(String value) {
        System.out.println(value);
        System.out.println();
    }

}
