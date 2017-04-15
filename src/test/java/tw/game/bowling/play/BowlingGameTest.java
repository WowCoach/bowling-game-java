package tw.game.bowling.play;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static tw.game.bowling.model.BowlingFrame.newBowlingNormalFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingRewardFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingSpareFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingStrikeFrame;
import static tw.game.bowling.model.FrameType.NORMAL;
import static tw.game.bowling.model.FrameType.SPARE;
import static tw.game.bowling.model.FrameType.STRIKE;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tw.game.bowling.model.BowlingFrame;
import tw.game.bowling.rules.NormalScoringRule;
import tw.game.bowling.rules.ScoringRule;
import tw.game.bowling.rules.SpareScoringRule;
import tw.game.bowling.rules.StrikeScoringRule;

public class BowlingGameTest {
    private BowlingGame bowlingGame;
    private ScoringRule normalScoringRule;
    private ScoringRule spareScoringRule;
    private ScoringRule strikeScoringRule;

    @Before
    public void setUp() throws Exception {
        normalScoringRule = mock(NormalScoringRule.class);
        spareScoringRule = mock(SpareScoringRule.class);
        strikeScoringRule = mock(StrikeScoringRule.class);
        bowlingGame = new BowlingGame(asList(normalScoringRule, spareScoringRule, strikeScoringRule));
    }

    @Test
    public void shouldCallDifferentScoringRuleBasedOnTheFrameType() throws Exception {
        List<BowlingFrame> bowlingFrames = asList(
                newBowlingNormalFrame(3, 5), newBowlingSpareFrame(5),
                newBowlingStrikeFrame(), newBowlingRewardFrame(10, 10));

        when(normalScoringRule.matchFrameType(NORMAL)).thenReturn(true);
        when(spareScoringRule.matchFrameType(SPARE)).thenReturn(true);
        when(strikeScoringRule.matchFrameType(STRIKE)).thenReturn(true);
        when(normalScoringRule.calculate(1, bowlingFrames)).thenReturn(1);
        when(spareScoringRule.calculate(2, bowlingFrames)).thenReturn(2);
        when(strikeScoringRule.calculate(3, bowlingFrames)).thenReturn(3);

        int score = bowlingGame.calculate(bowlingFrames);

        assertThat(score, is(6));
    }
}
