package tw.game.bowling.parser;

import static java.util.stream.IntStream.range;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static tw.game.bowling.model.FrameType.MISS;
import static tw.game.bowling.model.FrameType.REWARD;
import static tw.game.bowling.model.FrameType.SPARE;
import static tw.game.bowling.model.FrameType.STRIKE;
import static tw.game.bowling.parser.BowlingParser.parseToBowlingFrames;

import java.util.List;

import org.junit.Test;

import tw.game.bowling.exception.InvalidFrameDataException;
import tw.game.bowling.model.BowlingFrame;
import tw.game.bowling.model.FrameType;

public class BowlingParserTest {

    public static final int FULL_SCORE = 10;
    public static final int ZERO_SCORE = 0;

    @Test
    public void shouldParseToBowlingFramesGivenBowlingStrikeString() throws Exception {
        String input = "X|X|X|X|X|X|X|X|X|X||XX";

        List<BowlingFrame> bowlingFrames = parseToBowlingFrames(input);

        assertThat(bowlingFrames.size(), is(11));
        range(0, bowlingFrames.size() - 1).forEach(index ->
                assertBowlingFrame(bowlingFrames.get(index), STRIKE, FULL_SCORE, ZERO_SCORE)
        );
        assertBowlingFrame(bowlingFrames.get(10), REWARD, FULL_SCORE, FULL_SCORE);
    }

    @Test
    public void shouldParseToBowlingFrameGivenBowlingSpareString() throws Exception {
        String input = "5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5";

        List<BowlingFrame> bowlingFrames = parseToBowlingFrames(input);

        assertThat(bowlingFrames.size(), is(11));
        range(0, bowlingFrames.size() - 1).forEach(index ->
                assertBowlingFrame(bowlingFrames.get(index), SPARE, 5, 5)
        );
        assertBowlingFrame(bowlingFrames.get(10), REWARD, 5, 0);
    }

    @Test
    public void shouldParseToMissFrameGivenBowlingStringWithMissCode() throws Exception {
        String input = "9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||";

        List<BowlingFrame> bowlingFrames = parseToBowlingFrames(input);

        assertThat(bowlingFrames.size(), is(10));
        range(0, bowlingFrames.size() - 1).forEach(index ->
                assertBowlingFrame(bowlingFrames.get(index), MISS, 9, 0)
        );
    }

    @Test
    public void shouldParseToBowlingFramesGivenBowingStringWithMixedCode() throws Exception {
        String input = "53|7/|9-|-8|X||81";

        List<BowlingFrame> bowlingFrames = parseToBowlingFrames(input);

        assertThat(bowlingFrames.size(), is(6));
        assertBowlingFrame(bowlingFrames.get(0), MISS, 5, 3);
        assertBowlingFrame(bowlingFrames.get(1), SPARE, 7, 3);
        assertBowlingFrame(bowlingFrames.get(2), MISS, 9, 0);
        assertBowlingFrame(bowlingFrames.get(3), MISS, 0, 8);
        assertBowlingFrame(bowlingFrames.get(4), STRIKE, FULL_SCORE, 0);
        assertBowlingFrame(bowlingFrames.get(5), REWARD, 8, 1);
    }

    @Test(expected = InvalidFrameDataException.class)
    public void shouldThrowOutInvalidFrameDataExceptionGivenInvalidBowlingString() throws Exception {
        String input = "5|/|333||";

        parseToBowlingFrames(input);
    }

    @Test
    public void shouldReturnEmptyBowlingFramesGivenEmptyBowlingString() throws Exception {
        List<BowlingFrame> bowlingFrames = parseToBowlingFrames("");

        assertTrue(bowlingFrames.isEmpty());
    }

    private void assertBowlingFrame(BowlingFrame bowlingFrame, FrameType frameType, int firstScore, int secondScore) {
        assertThat(bowlingFrame.getFrameType(), is(frameType));
        assertThat(bowlingFrame.getFirstStrikeScore(), is(firstScore));
        assertThat(bowlingFrame.getSecondStrikeScore(), is(secondScore));
        assertThat(bowlingFrame.getTotalScore(), is(firstScore + secondScore));
    }
}
