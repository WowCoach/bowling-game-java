package tw.game.bowling.rules;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static tw.game.bowling.model.BowlingFrame.newBowlingNormalFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingRewardFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingSpareFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingStrikeFrame;

import org.junit.Before;
import org.junit.Test;

import tw.game.bowling.exception.InvalidPositionException;
import tw.game.bowling.exception.LackOfFramesException;
import tw.game.bowling.exception.TypeMismatchException;

public class StrikeScoringRuleTest {
    private ScoringRule scoringRule;

    @Before
    public void setUp() throws Exception {
        scoringRule = new StrikeScoringRule();
    }

    @Test
    public void shouldCalculateScoreWhenCurrentFrameTypeIsStrikeAndNextIsNormal() throws Exception {
        int score = scoringRule.calculate(1, asList(newBowlingStrikeFrame(), newBowlingNormalFrame(3, 5)));

        assertThat(score, is(18));
    }

    @Test
    public void shouldCalculateScoreWhenCurrentFrameTypeIsStrikeAndNextIsReward() throws Exception {
        int score = scoringRule.calculate(1, asList(newBowlingStrikeFrame(), newBowlingRewardFrame(10, 5)));

        assertThat(score, is(25));
    }

    @Test
    public void shouldCalculateScoreWhenCurrentFrameTypeIsStrikeAndNextTwoAreStrike() throws Exception {
        int score = scoringRule.calculate(1, asList(newBowlingStrikeFrame(), newBowlingStrikeFrame(), newBowlingStrikeFrame()));

        assertThat(score, is(30));
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionWhenCurrentFrameTypeIsNotStrike() throws Exception {
        scoringRule.calculate(1, asList(newBowlingSpareFrame(3)));
    }

    @Test(expected = InvalidPositionException.class)
    public void shouldThrowInvalidPositionExceptionWhenPositionIsInvalid() throws Exception {
        scoringRule.calculate(2, asList(newBowlingStrikeFrame()));
    }

    @Test(expected = LackOfFramesException.class)
    public void shouldThrowLackOfFramesExceptionGivenCurrentFrameTypeIsStrikeWithoutAnyNextFrames() throws Exception {
        scoringRule.calculate(1, asList(newBowlingStrikeFrame()));
    }

    @Test(expected = LackOfFramesException.class)
    public void shouldThrowLackOfFramesExceptionGivenCurrentAndNextFrameTypAreStrikeWitoutTheThirdFrame() throws Exception {
        scoringRule.calculate(1, asList(newBowlingStrikeFrame(), newBowlingStrikeFrame()));
    }
}