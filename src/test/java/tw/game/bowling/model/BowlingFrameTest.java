package tw.game.bowling.model;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static tw.game.bowling.model.BowlingFrame.newBowlingNormalFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingRewardFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingSpareFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingStrikeFrame;
import static tw.game.bowling.model.FrameType.NORMAL;
import static tw.game.bowling.model.FrameType.REWARD;
import static tw.game.bowling.model.FrameType.SPARE;
import static tw.game.bowling.model.FrameType.STRIKE;

import org.junit.Test;

import tw.game.bowling.exception.BowlingScoreException;

public class BowlingFrameTest {

    public static final int FULL_SCORE = 10;

    @Test
    public void shouldNewStrikeBowlingFrame() throws Exception {
        BowlingFrame bowlingFrame = newBowlingStrikeFrame();

        assertThat(bowlingFrame.getFrameType(), is(STRIKE));
        assertThat(bowlingFrame.getFirstStrikeScore(), is(FULL_SCORE));
        assertThat(bowlingFrame.getSecondStrikeScore(), is(0));
        assertThat(bowlingFrame.getTotalScore(), is(FULL_SCORE));
    }

    @Test
    public void shouldNewNormalBowlingFrame() throws Exception {
        BowlingFrame bowlingFrame = newBowlingNormalFrame(8, 1);

        assertThat(bowlingFrame.getFrameType(), is(NORMAL));
        assertThat(bowlingFrame.getFirstStrikeScore(), is(8));
        assertThat(bowlingFrame.getSecondStrikeScore(), is(1));
        assertThat(bowlingFrame.getTotalScore(), is(9));
    }

    @Test
    public void shouldNewSpareBowlingFrame() throws Exception {
        BowlingFrame bowlingFrame = newBowlingSpareFrame(6);

        assertThat(bowlingFrame.getFrameType(), is(SPARE));
        assertThat(bowlingFrame.getFirstStrikeScore(), is(6));
        assertThat(bowlingFrame.getSecondStrikeScore(), is(4));
        assertThat(bowlingFrame.getTotalScore(), is(FULL_SCORE));
    }

    @Test
    public void shouldNewRewardBowlingFrame() throws Exception {
        BowlingFrame bowlingFrame = newBowlingRewardFrame(5, 3);

        assertThat(bowlingFrame.getFrameType(), is(REWARD));
        assertThat(bowlingFrame.getFirstStrikeScore(), is(5));
        assertThat(bowlingFrame.getSecondStrikeScore(), is(3));
        assertThat(bowlingFrame.getTotalScore(), is(8));
    }

    @Test
    public void shouldNewRewardBowlingFrameWithBothZeroValues() throws Exception {
        BowlingFrame bowlingFrame = newBowlingRewardFrame(0, 0);

        assertThat(bowlingFrame.getFrameType(), is(REWARD));
        assertThat(bowlingFrame.getFirstStrikeScore(), is(0));
        assertThat(bowlingFrame.getSecondStrikeScore(), is(0));
        assertThat(bowlingFrame.getTotalScore(), is(0));
    }

    @Test
    public void shouldNewRewardBowlingFrameWithBothFullScoreValues() throws Exception {
        BowlingFrame bowlingFrame = newBowlingRewardFrame(FULL_SCORE, FULL_SCORE);

        assertThat(bowlingFrame.getFrameType(), is(REWARD));
        assertThat(bowlingFrame.getFirstStrikeScore(), is(FULL_SCORE));
        assertThat(bowlingFrame.getSecondStrikeScore(), is(FULL_SCORE));
        assertThat(bowlingFrame.getTotalScore(), is(FULL_SCORE + FULL_SCORE));
    }

    @Test(expected = BowlingScoreException.class)
    public void shouldNewNormalFrameThrowExceptionWhenTotalScoreIsEqualToFullScore() throws Exception {
        newBowlingNormalFrame(5, 5);
    }

    @Test(expected = BowlingScoreException.class)
    public void shouldNewNormalFrameThrowExceptionWhenTotalScoreIsGreaterThanFullScore() throws Exception {
        newBowlingNormalFrame(5, 6);
    }

    @Test(expected = BowlingScoreException.class)
    public void shouldNewNormalFrameThrowExceptionWhenFirstScoreLessThanZero() throws Exception {
        newBowlingNormalFrame(-1, 5);
    }

    @Test(expected = BowlingScoreException.class)
    public void shouldNewNormalFrameThrowExceptionWhenSecondScoreLessThanZero() throws Exception {
        newBowlingNormalFrame(5, -1);
    }

    @Test(expected = BowlingScoreException.class)
    public void shouldSpareFrameThrowExceptionWhenFirstScoreLessThanZero() throws Exception {
        newBowlingSpareFrame(-1);
    }

    @Test(expected = BowlingScoreException.class)
    public void shouldSpareFrameThrowExceptionWhenFirstScoreEqualToFullScore() throws Exception {
        newBowlingSpareFrame(FULL_SCORE);
    }

    @Test(expected = BowlingScoreException.class)
    public void ShouldSpareFrameThrowExceptionWhenFirstScoreIsGreaterThanFullScore() throws Exception {
        newBowlingSpareFrame(FULL_SCORE + 1);
    }

    @Test(expected = BowlingScoreException.class)
    public void shouldRewardFrameThrowExceptionWhenFirstScoreIsLessThanZero() throws Exception {
        newBowlingRewardFrame(-1, 5);
    }

    @Test(expected = BowlingScoreException.class)
    public void shouldRewardFrameThrowExceptionWhenSecondScoreIsLessThanZero() throws Exception {
        newBowlingRewardFrame(5, -1);
    }

    @Test(expected = BowlingScoreException.class)
    public void shouldRewardFrameThrowExceptionWhenFirstScoreIsGreaterThanFullScore() throws Exception {
        newBowlingRewardFrame(11, 5);
    }

    @Test(expected = BowlingScoreException.class)
    public void shouldRewardFrameThrowExceptionWhenSecondScoreIsGreaterThanFullScore() throws Exception {
        newBowlingRewardFrame(5, 11);
    }
}