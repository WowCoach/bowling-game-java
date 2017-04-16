package tw.game.bowling.parser;

import static java.util.stream.IntStream.range;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static tw.game.bowling.model.FrameType.MISS;
import static tw.game.bowling.model.FrameType.REWARD;
import static tw.game.bowling.model.FrameType.SPARE;
import static tw.game.bowling.model.FrameType.STRIKE;

import java.util.List;

import org.junit.Test;

import tw.game.bowling.model.BowlingFrame;
import tw.game.bowling.model.FrameType;

public class BowlingParserTest {

    public static final int FULL_SCORE = 10;
    public static final int ZERO_SCORE = 0;

    @Test
    public void shouldParseToBowlingFramesGivenBowlingStrikeString() throws Exception {
        String input = "X|X|X|X|X|X|X|X|X|X||XX";

        List<BowlingFrame> bowlingFrames = BowlingParser.parse(input);

        assertThat(bowlingFrames.size(), is(11));
        range(0, bowlingFrames.size() - 1).forEach(index ->
                assertBowlingFrame(bowlingFrames.get(index), STRIKE, FULL_SCORE, ZERO_SCORE)
        );
        assertBowlingFrame(bowlingFrames.get(10), REWARD, FULL_SCORE, FULL_SCORE);
    }

    @Test
    public void shouldParseToBowlingFrameGivenBowlingSpareString() throws Exception {
        String input = "5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5";

        List<BowlingFrame> bowlingFrames = BowlingParser.parse(input);

        assertThat(bowlingFrames.size(), is(11));
        range(0, bowlingFrames.size() - 1).forEach(index ->
                assertBowlingFrame(bowlingFrames.get(index), SPARE, 5, 5)
        );
        assertBowlingFrame(bowlingFrames.get(10), REWARD, 5, 0);
    }

    @Test
    public void shouldParseToNormalFrameGivenBowlingNormalStringWithMissCode() throws Exception {
        String input = "9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||";

        List<BowlingFrame> bowlingFrames = BowlingParser.parse(input);

        assertThat(bowlingFrames.size(), is(10));
        range(0, bowlingFrames.size() - 1).forEach(index ->
                assertBowlingFrame(bowlingFrames.get(index), MISS, 9, 0)
        );
    }

    private void assertBowlingFrame(BowlingFrame bowlingFrame, FrameType frameType, int firstScore, int secondScore) {
        assertThat(bowlingFrame.getFrameType(), is(frameType));
        assertThat(bowlingFrame.getFirstStrikeScore(), is(firstScore));
        assertThat(bowlingFrame.getSecondStrikeScore(), is(secondScore));
        assertThat(bowlingFrame.getTotalScore(), is(firstScore + secondScore));
    }
}
