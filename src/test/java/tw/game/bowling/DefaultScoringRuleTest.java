package tw.game.bowling;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static tw.game.bowling.BowlingFrame.newBowlingNormalFrame;
import static tw.game.bowling.BowlingFrame.newBowlingRewardFrame;
import static tw.game.bowling.BowlingFrame.newBowlingSpareFrame;
import static tw.game.bowling.BowlingFrame.newBowlingStrikeFrame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DefaultScoringRuleTest {
    private ScoringRule scoringRule;

    @Before
    public void setUp() throws Exception {
        scoringRule = new DefaultScoringRule();
    }

    @Test
    public void shouldCalculateFrameScoreWhenFrameTypeIsNormal() throws Exception {
        List<BowlingFrame> bowlingFrames = asList(newBowlingNormalFrame(8, 1));

        int score = scoringRule.calculate(1, bowlingFrames);

        assertThat(score, is(9));
    }

    @Test
    public void shouldCalculateFrameScoreWhenFrameTypeIsSpare() throws Exception {
        List<BowlingFrame> bowlingFrames = asList(newBowlingSpareFrame(5), newBowlingNormalFrame(6, 3));

        int score = scoringRule.calculate(1, bowlingFrames);

        assertThat(score, is(19));
    }

    @Test
    public void shouldCalculateFrameScoreAsZeroWhenFrameTypeIsReward() throws Exception {
        List<BowlingFrame> bowlingFrames = asList(newBowlingRewardFrame(10, 10));

        int score = scoringRule.calculate(1, bowlingFrames);

        assertThat(score, is(0));
    }

    @Test
    public void shouldCalculateFrameScoreWhenFrameTypeIsStrike() throws Exception {
        List<BowlingFrame> bowlingFrames = asList(newBowlingStrikeFrame(), newBowlingNormalFrame(8, 1), newBowlingNormalFrame(5, 1));

        int score = scoringRule.calculate(1, bowlingFrames);

        assertThat(score, is(25));
    }

    @Test(expected = LackOfFrameException.class)
    public void shouldThrowExceptionWhenBowlingFrameDataIsInvalidForSpareFrame() throws Exception {
        List<BowlingFrame> bowlingFrames = asList(newBowlingSpareFrame(6));

        scoringRule.calculate(1, bowlingFrames);
    }

    @Test(expected = LackOfFrameException.class)
    public void shouldThrowExceptionWhenBowlingFrameDataIsInvalidForStrikeFrame() throws Exception {
        List<BowlingFrame> bowlingFrames = asList(newBowlingStrikeFrame(), newBowlingNormalFrame(8, 1));

        scoringRule.calculate(1, bowlingFrames);
    }

    @Test(expected = InvalidPositionException.class)
    public void shouldThrowInvalidPositionExceptionWhenBowlingFramePositionIsInvalid() throws Exception {
        List<BowlingFrame> bowlingFrames = asList(newBowlingNormalFrame(5, 3));

        scoringRule.calculate(2, bowlingFrames);
    }

}
