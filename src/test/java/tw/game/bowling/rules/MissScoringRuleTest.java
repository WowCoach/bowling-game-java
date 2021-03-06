package tw.game.bowling.rules;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static tw.game.bowling.model.BowlingFrame.newBowlingMissFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingStrikeFrame;
import static tw.game.bowling.model.FrameType.MISS;
import static tw.game.bowling.model.FrameType.SPARE;

import org.junit.Before;
import org.junit.Test;

import tw.game.bowling.exception.InvalidPositionException;
import tw.game.bowling.exception.TypeMismatchException;

public class MissScoringRuleTest {
    private ScoringRule scoringRule;

    @Before
    public void setUp() throws Exception {
        scoringRule = new MissScoringRule();
    }

    @Test
    public void shouldCalculateScoreWhenCurrentFrameTypeIsMiss() throws Exception {
        int score = scoringRule.calculate(1, asList(newBowlingMissFrame(3, 5)));

        assertThat(score, is(8));
    }

    @Test
    public void shouldMatchFrameTypeReturnTrueGiveParameterTypeIsMiss() throws Exception {
        assertTrue(scoringRule.matchFrameType(MISS));
    }

    @Test
    public void shouldMatchFrameTypeReturnFalseGiveParameterTypeIsNotMiss() throws Exception {
        assertFalse(scoringRule.matchFrameType(SPARE));
    }

    @Test(expected = InvalidPositionException.class)
    public void shouldThrowInvalidPositionExceptionGivenWrongPositionValue() throws Exception {
        scoringRule.calculate(2, asList(newBowlingMissFrame(3, 5)));
    }

    @Test(expected = TypeMismatchException.class)
    public void shouldThrowTypeMismatchExceptionGivenCurrentFrameTypeIsNotMiss() throws Exception {
        scoringRule.calculate(1, asList(newBowlingStrikeFrame()));
    }
}
