package tw.game.bowling;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static tw.game.bowling.BowlingFrame.newBowlingNormalFrame;
import static tw.game.bowling.BowlingFrame.newBowlingRewardFrame;
import static tw.game.bowling.BowlingFrame.newBowlingSpareFrame;
import static tw.game.bowling.BowlingFrame.newBowlingStrikeFrame;
import static tw.game.bowling.FrameType.NORMAL;
import static tw.game.bowling.FrameType.REWARD;
import static tw.game.bowling.FrameType.SPARE;
import static tw.game.bowling.FrameType.STRIKE;

import org.junit.Test;

public class BowlingFrameTest {
    @Test
    public void shouldNewStrikeBowlingFrame() throws Exception {
        BowlingFrame bowlingFrame = newBowlingStrikeFrame();

        assertThat(bowlingFrame.getFrameType(), is(STRIKE));
        assertThat(bowlingFrame.getFirstStrikeScore(), is(10));
        assertThat(bowlingFrame.getSecondStrikeScore(), is(0));
        assertThat(bowlingFrame.getTotalScore(), is(10));
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
        assertThat(bowlingFrame.getTotalScore(), is(10));
    }

    @Test
    public void shouldNewRewardBowlingFrame() throws Exception {
        BowlingFrame bowlingFrame = newBowlingRewardFrame(5, 3);

        assertThat(bowlingFrame.getFrameType(), is(REWARD));
        assertThat(bowlingFrame.getFirstStrikeScore(), is(5));
        assertThat(bowlingFrame.getSecondStrikeScore(), is(3));
        assertThat(bowlingFrame.getTotalScore(), is(8));
    }


}