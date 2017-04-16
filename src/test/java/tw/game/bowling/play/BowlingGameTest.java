package tw.game.bowling.play;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tw.game.bowling.model.BowlingFrame.newBowlingMissFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingRewardFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingSpareFrame;
import static tw.game.bowling.model.BowlingFrame.newBowlingStrikeFrame;
import static tw.game.bowling.model.FrameType.MISS;
import static tw.game.bowling.model.FrameType.SPARE;
import static tw.game.bowling.model.FrameType.STRIKE;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tw.game.bowling.model.BowlingFrame;
import tw.game.bowling.rules.ScoringRule;

@RunWith(MockitoJUnitRunner.class)
public class BowlingGameTest {
    private BowlingGame bowlingGame;
    @Mock
    private ScoringRule normalScoringRule;
    @Mock
    private ScoringRule spareScoringRule;
    @Mock
    private ScoringRule strikeScoringRule;

    @Before
    public void setUp() throws Exception {
        when(normalScoringRule.matchFrameType(MISS)).thenReturn(true);
        when(spareScoringRule.matchFrameType(SPARE)).thenReturn(true);
        when(strikeScoringRule.matchFrameType(STRIKE)).thenReturn(true);
        bowlingGame = new BowlingGame(asList(normalScoringRule, spareScoringRule, strikeScoringRule));
    }

    @Test
    public void shouldCallDifferentScoringRuleBasedOnTheFrameType() throws Exception {
        List<BowlingFrame> bowlingFrames = asList(
                newBowlingMissFrame(3, 5), newBowlingSpareFrame(5),
                newBowlingStrikeFrame(), newBowlingRewardFrame(10, 10));

        when(normalScoringRule.calculate(1, bowlingFrames)).thenReturn(1);
        when(spareScoringRule.calculate(2, bowlingFrames)).thenReturn(2);
        when(strikeScoringRule.calculate(3, bowlingFrames)).thenReturn(3);

        int score = bowlingGame.calculate(bowlingFrames);
        verify(normalScoringRule).calculate(1, bowlingFrames);
        verify(spareScoringRule).calculate(2, bowlingFrames);
        verify(strikeScoringRule).calculate(3, bowlingFrames);

        assertThat(score, is(6));
    }

    @Test
    public void shouldReturnZeroWhenNoScoringRulesProvided() throws Exception {
        BowlingGame bowlingGame = new BowlingGame(new ArrayList<>());

        int score = bowlingGame.calculate(asList(newBowlingMissFrame(3, 5)));

        assertThat(score, is(0));
    }
}
