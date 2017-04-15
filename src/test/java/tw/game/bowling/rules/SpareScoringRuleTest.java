package tw.game.bowling.rules;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static tw.game.bowling.model.BowlingFrame.newBowlingNormalFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingRewardFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingSpareFrame;
import static tw.game.bowling.model.FrameType.NORMAL;
import static tw.game.bowling.model.FrameType.SPARE;

import org.junit.Before;
import org.junit.Test;

import tw.game.bowling.exception.InvalidPositionException;
import tw.game.bowling.exception.LackOfFramesException;
import tw.game.bowling.exception.TypeMismatchException;

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

    @Test
    public void shouldMatchFrameTypeReturnTrueGiveParameterTypeIsSpare() throws Exception {
        assertTrue(scoringRule.matchFrameType(SPARE));
    }

    @Test
    public void shouldMatchFrameTypeReturnFalseGiveParameterTypeIsNotSpare() throws Exception {
        assertFalse(scoringRule.matchFrameType(NORMAL));
    }

    @Test(expected = TypeMismatchException.class)
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