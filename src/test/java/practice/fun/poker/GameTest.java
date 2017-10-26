package practice.fun.poker;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GameTest {

    private Game game = new Game();

    private String highCard = "3c 5d 2s tc 8c";
    private String onePair = "3c 3s 2s tc 8c";
    private String twoPair = "3c 3s 2s 2c 8c";
    private String threeOfAKind = "3c 3s 3h tc 8c";
    private String straight = "3c 6c 4s 7c 5c";
    private String flush = "3c 3c 2c tc 8c";
    private String fullHouse = "3d 3h 2s 2c 3s";
    private String fourOfAKind = "3c 3s 3d 3h 8c";
    private String straightFlush = "3c 4c 5c 6c 7c";

    @Test
    public void testHighCardLosesToAllOtherCombinations() {

        assertThat(game.play(highCard, onePair), equalTo(-1));
        assertThat(game.play(highCard, twoPair), equalTo(-1));
        assertThat(game.play(highCard, threeOfAKind), equalTo(-1));
        assertThat(game.play(highCard, straight), equalTo(-1));
        assertThat(game.play(highCard, flush), equalTo(-1));
        assertThat(game.play(highCard, fullHouse), equalTo(-1));
        assertThat(game.play(highCard, fourOfAKind), equalTo(-1));
        assertThat(game.play(highCard, straightFlush), equalTo(-1));
    }

    @Test
    public void testHighCardResolvesWithOtherHighCardsCorrectly() {
        String highCardWithBetterTopKicker = "3c 5d 2s kc 8c";
        String highCardWithBetterMiddleKicker = "3c 5d 4s tc 8c";
        String highCardTie = highCard;

        assertThat(game.play(highCard, highCardWithBetterTopKicker), equalTo(-1));
        assertThat(game.play(highCard, highCardWithBetterMiddleKicker), equalTo(-1));
        assertThat(game.play(highCard, highCardTie), equalTo(0));
    }

    @Test
    public void testOnePairWinsHichCardAndLosesToAllOtherCombinations() {

        assertThat(game.play(onePair, highCard), equalTo(1));
        assertThat(game.play(onePair, twoPair), equalTo(-1));
        assertThat(game.play(onePair, threeOfAKind), equalTo(-1));
        assertThat(game.play(onePair, straight), equalTo(-1));
        assertThat(game.play(onePair, flush), equalTo(-1));
        assertThat(game.play(onePair, fullHouse), equalTo(-1));
        assertThat(game.play(onePair, fourOfAKind), equalTo(-1));
        assertThat(game.play(onePair, straightFlush), equalTo(-1));
    }

    @Test
    public void testHighCardResolvesWithOtherOnePairsCorrectly() {
        String onePairWithBetterTopKicker = "3c 3s 2s ac 8c";
        String onePairWithWorstTopKicker = "3c 3s 2s 9c 8c";
        String onePairWithBetterMiddleKicker = "3c 3s 2s tc 9c";
        String oneEqualPair = "3c 3s 2s tc 8c";

        assertThat(game.play(onePair, onePairWithBetterTopKicker), equalTo(-1));
        assertThat(game.play(onePair, onePairWithWorstTopKicker), equalTo(1));
        assertThat(game.play(onePair, onePairWithBetterMiddleKicker), equalTo(-1));
        assertThat(game.play(onePair, oneEqualPair), equalTo(0));
    }

    @Test
    public void testTwoPairsWinsLowerCombinationsAndLosesToAllOtherCombinations() {

        assertThat(game.play(twoPair, highCard), equalTo(1));
        assertThat(game.play(twoPair, onePair), equalTo(1));
        assertThat(game.play(twoPair, threeOfAKind), equalTo(-1));
        assertThat(game.play(twoPair, straight), equalTo(-1));
        assertThat(game.play(twoPair, flush), equalTo(-1));
        assertThat(game.play(twoPair, fullHouse), equalTo(-1));
        assertThat(game.play(twoPair, fourOfAKind), equalTo(-1));
        assertThat(game.play(twoPair, straightFlush), equalTo(-1));
    }

    @Test
    public void testThreeOfAKindWinsLowerCombinationsAndLosesToAllOtherCombinations() {

        assertThat(game.play(threeOfAKind, highCard), equalTo(1));
        assertThat(game.play(threeOfAKind, onePair), equalTo(1));
        assertThat(game.play(threeOfAKind, twoPair), equalTo(1));
        assertThat(game.play(threeOfAKind, straight), equalTo(-1));
        assertThat(game.play(threeOfAKind, flush), equalTo(-1));
        assertThat(game.play(threeOfAKind, fullHouse), equalTo(-1));
        assertThat(game.play(threeOfAKind, fourOfAKind), equalTo(-1));
        assertThat(game.play(threeOfAKind, straightFlush), equalTo(-1));
    }

    @Test
    public void testStraightWinsLowerCombinationsAndLosesToAllOtherCombinations() {

        assertThat(game.play(straight, highCard), equalTo(1));
        assertThat(game.play(straight, onePair), equalTo(1));
        assertThat(game.play(straight, twoPair), equalTo(1));
        assertThat(game.play(straight, threeOfAKind), equalTo(1));
        assertThat(game.play(straight, flush), equalTo(-1));
        assertThat(game.play(straight, fullHouse), equalTo(-1));
        assertThat(game.play(straight, fourOfAKind), equalTo(-1));
        assertThat(game.play(straight, straightFlush), equalTo(-1));
    }

    @Test
    public void testFlushWinsLowerCombinationsAndLosesToAllOtherCombinations() {

        assertThat(game.play(flush, highCard), equalTo(1));
        assertThat(game.play(flush, onePair), equalTo(1));
        assertThat(game.play(flush, twoPair), equalTo(1));
        assertThat(game.play(flush, threeOfAKind), equalTo(1));
        assertThat(game.play(flush, straight), equalTo(1));
        assertThat(game.play(flush, fullHouse), equalTo(-1));
        assertThat(game.play(flush, fourOfAKind), equalTo(-1));
        assertThat(game.play(flush, straightFlush), equalTo(-1));
    }

    @Test
    public void testFullHouseWinsLowerCombinationsAndLosesToAllOtherCombinations() {

        assertThat(game.play(fullHouse, highCard), equalTo(1));
        assertThat(game.play(fullHouse, onePair), equalTo(1));
        assertThat(game.play(fullHouse, twoPair), equalTo(1));
        assertThat(game.play(fullHouse, threeOfAKind), equalTo(1));
        assertThat(game.play(fullHouse, straight), equalTo(1));
        assertThat(game.play(fullHouse, flush), equalTo(1));
        assertThat(game.play(fullHouse, fourOfAKind), equalTo(-1));
        assertThat(game.play(fullHouse, straightFlush), equalTo(-1));
    }

    @Test
    public void testFourOfAKindWinsLowerCombinationsAndLosesToAllOtherCombinations() {

        assertThat(game.play(fourOfAKind, highCard), equalTo(1));
        assertThat(game.play(fourOfAKind, onePair), equalTo(1));
        assertThat(game.play(fourOfAKind, twoPair), equalTo(1));
        assertThat(game.play(fourOfAKind, threeOfAKind), equalTo(1));
        assertThat(game.play(fourOfAKind, straight), equalTo(1));
        assertThat(game.play(fourOfAKind, flush), equalTo(1));
        assertThat(game.play(fourOfAKind, fullHouse), equalTo(1));
        assertThat(game.play(fourOfAKind, straightFlush), equalTo(-1));
    }

    @Test
    public void testStraightFlushWinsEverything() {

        assertThat(game.play(straightFlush, highCard), equalTo(1));
        assertThat(game.play(straightFlush, onePair), equalTo(1));
        assertThat(game.play(straightFlush, twoPair), equalTo(1));
        assertThat(game.play(straightFlush, threeOfAKind), equalTo(1));
        assertThat(game.play(straightFlush, straight), equalTo(1));
        assertThat(game.play(straightFlush, flush), equalTo(1));
        assertThat(game.play(straightFlush, fullHouse), equalTo(1));
        assertThat(game.play(straightFlush, fourOfAKind), equalTo(1));
    }
}
