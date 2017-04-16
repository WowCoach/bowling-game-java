package tw.game.bowling;

import static java.util.Arrays.asList;
import static tw.game.bowling.parser.BowlingParser.parseToBowlingFrames;

import java.util.List;
import java.util.Scanner;

import tw.game.bowling.play.BowlingGame;
import tw.game.bowling.rules.MissScoringRule;
import tw.game.bowling.rules.ScoringRule;
import tw.game.bowling.rules.SpareScoringRule;
import tw.game.bowling.rules.StrikeScoringRule;

public class BowlingMain {
    public static void main(String[] args) {

        List<ScoringRule> scoringRule = asList(new StrikeScoringRule(), new SpareScoringRule(), new MissScoringRule());
        BowlingGame bowlingGame = new BowlingGame(scoringRule);

        System.out.println("Enjoy the bowling game. Input 'exit' to exit.\n");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }

            try {
                int totalScore = bowlingGame.calculate(parseToBowlingFrames(input));

                System.out.println(totalScore);
                System.out.println();
            } catch (Exception e) {
                System.out.println("Please input valid data string.----------\n");
            }
        }
    }

}
