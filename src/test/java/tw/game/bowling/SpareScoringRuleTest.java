package tw.game.bowling;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static tw.game.bowling.BowlingFrame.newBowlingNormalFrame;
import static tw.game.bowling.BowlingFrame.newBowlingRewardFrame;
import static tw.game.bowling.BowlingFrame.newBowlingSpareFrame;

import org.junit.Before;
import org.junit.Test;

public class SpareScoringRuleTest {

    private ScoringRule scoringRule;

    @Before
    public void setUp() throws Exception {
        scoringRule = new SpareScoringRule();
    }

    @Test
    public void shouldCalculateScoreWhenCurrentFrameTypeIsSpareAndNextTypeIsNormal() throws Exception {
        int score = scoringRule.calculate(1, asList(newBowlingSpareFrame(6), newBowlingNormalFrame(1, 3)));

        assertThat(score, is(11));
    }

    @Test
    public void shouldCalculateScoreWhenCurrentFrameTypeIsSpareAndNextTypeIsReward() throws Exception {
        int score = scoringRule.calculate(1, asList(newBowlingSpareFrame(6), newBowlingRewardFrame(8, 1)));

        assertThat(score, is(18));
    }

    @Test(expected = FrameTypeMismatchException.class)
    public void shouldThrowFrameTypeMismatchExceptionWhenCurrentFrameTypeIsNotSpare() throws Exception {
        scoringRule.calculate(1, asList(newBowlingNormalFrame(3, 5)));
    }

    @Test(expected = InvalidPositionException.class)
    public void shouldThrowInvalidPositionExceptionGivenWrongPositionValue() throws Exception {
        scoringRule.calculate(2, asList(newBowlingSpareFrame(3)));
    }

    @Test(expected = LackOfFramesException.class)
    public void shouldThrowLackOfFramesExceptionWhenBowlingFramesDataIsInvalid() throws Exception {
        scoringRule.calculate(1, asList(newBowlingSpareFrame(5)));
    }
}