package tw.game.bowling.parser;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static tw.game.bowling.model.BowlingFrame.FULL_SCORE;
import static tw.game.bowling.model.BowlingFrame.ZERO_SCORE;
import static tw.game.bowling.model.BowlingFrame.newBowlingMissFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingRewardFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingSpareFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingStrikeFrame;
import static tw.game.bowling.model.FrameType.MISS;
import static tw.game.bowling.model.FrameType.SPARE;
import static tw.game.bowling.model.FrameType.STRIKE;

import java.util.List;

import tw.game.bowling.exception.InvalidFrameDataException;
import tw.game.bowling.model.BowlingFrame;

public class BowlingParser {
    public static final String FRAME_SEPARATOR = "\\|";
    public static final String REWARD_SEPARATOR = "\\|\\|";

    public static List<BowlingFrame> parse(String input) {
        String[] result = input.split(REWARD_SEPARATOR);

        List<BowlingFrame> bowlingFrames = getNormalBowlingFrames(result[0]);

        if (hasRewardStrike(result)) {
            bowlingFrames.add(getRewardBowlingFrame(result[1]));
        }

        return bowlingFrames;
    }

    private static boolean hasRewardStrike(String[] result) {
        return result.length > 1 && !result[1].isEmpty();
    }

    private static BowlingFrame getRewardBowlingFrame(String reward) {
        int firstScore = parseToScore(reward.substring(0, 1));
        int secondScore = reward.length() > 1 ? parseToScore(reward.substring(1)) : 0;
        return newBowlingRewardFrame(firstScore, secondScore);
    }

    private static List<BowlingFrame> getNormalBowlingFrames(String normalFrame) {
        String[] frames = normalFrame.split(FRAME_SEPARATOR);
        return stream(frames).filter(value -> !value.isEmpty()).map(value -> {
            if (STRIKE.getCode().equals(value)) {
                return newBowlingStrikeFrame();
            }
            if (SPARE.getCode().equals(value.substring(1))) {
                return newBowlingSpareFrame(parseToScore(value.substring(0, 1)));
            }
            if (value.length() == 2) {
                return newBowlingMissFrame(parseToScore(value.substring(0, 1)), parseToScore(value.substring(1)));
            }
            throw new InvalidFrameDataException();
        }).collect(toList());
    }

    private static int parseToScore(String value) {
        if (STRIKE.getCode().equals(value)) {
            return FULL_SCORE;
        }
        if (MISS.getCode().equals(value)) {
            return ZERO_SCORE;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidFrameDataException();
        }

    }
}
