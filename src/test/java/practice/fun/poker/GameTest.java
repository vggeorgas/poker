package practice.fun.poker;


import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GameTest {

    private Game game = new Game();

    private String highCard = "3c 5d 2s tc 8c";
    private String onePair = "3c 3s 2s tc 8c";
    private String twoPair = "4c 4s 2s 2c 8c";
    private String threeOfAKind = "3c 3s 3h tc 8c";
    private String straight = "3c 6c 4s 7c 5c";
    private String flush = "3c 3c 2c tc 8c";
    private String fullHouse = "8d 8h 4s 4c 8s";
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
    public void testOneCardResolvesWithOtherOnePairsCorrectly() {
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
    public void testTwoPairsResolvesWithOtherTwoPairsCorrectly() {
        String twoPairsWithBetterTopKicker = "4c 4s 2s 2c ac";
        String equalTwoPairs = "4c 4s 2s 2c 8c";
        String twoPairsWithHigherSmallerPair = "4c 4s 3s 3c 8c";
        String twoPairsWithHigherBigPair = "7c 7s 2s 2c 8c";

        assertThat(game.play(twoPair, twoPairsWithBetterTopKicker), equalTo(-1));
        assertThat(game.play(twoPair, twoPairsWithHigherSmallerPair), equalTo(-1));
        assertThat(game.play(twoPair, twoPairsWithHigherBigPair), equalTo(-1));
        assertThat(game.play(twoPair, equalTwoPairs), equalTo(0));
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
    public void testThreeOfAKindResolvesWithOtherThreeOfAKind() {
        String threeOfAKindWithBetterTopKicker = "3c 3s 3h jc 8c";
        String threeOfAKindWithBetterLowerKickerKicker = "3c 3s 3h tc 9c";
        String equalThreeOfAKind = "3c 3s 3h tc 8c";
        String threeOfAKindWithBetterRank = "9c 9s 9h tc 8c";

        assertThat(game.play(threeOfAKind, threeOfAKindWithBetterTopKicker), equalTo(-1));
        assertThat(game.play(threeOfAKind, threeOfAKindWithBetterLowerKickerKicker), equalTo(-1));
        assertThat(game.play(threeOfAKind, threeOfAKindWithBetterRank), equalTo(-1));
        assertThat(game.play(threeOfAKind, equalThreeOfAKind), equalTo(0));
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
    public void testStraightResolvesWithOtherStraights() {
        String higherStraight = "8c 6c 4s 7c 5c";
        String higherStraightOnAce = "ac js th kc qc";
        String equalStraight = "3c 6c 4s 7c 5c";
        String lowerStraight = "3c 6c 4s 2c 5c";
        String lowerStraightOnAce = "3c 2c 4s ac 5c";

        assertThat(game.play(straight, higherStraight), equalTo(-1));
        assertThat(game.play(straight, higherStraightOnAce), equalTo(-1));
        assertThat(game.play(straight, lowerStraight), equalTo(1));
        assertThat(game.play(straight, lowerStraightOnAce), equalTo(1));
        assertThat(game.play(straight, equalStraight), equalTo(0));
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
    public void testFlushResolvesWithOtherFlushes() {
        String higherFlush = "3c 3c 2c jc 8c";
        String higherFlushInTheMiddle = "3c 5c 2c tc 8c";
        String equalFlush = "3c 3c 2c tc 8c";
        String lowerFlush = "3c 3c 2c 9c 8c";
        String lowerFlushInTheMiddle = "3c 3c 2c tc 7c";

        assertThat(game.play(flush, higherFlush), equalTo(-1));
        assertThat(game.play(flush, higherFlushInTheMiddle), equalTo(-1));
        assertThat(game.play(flush, lowerFlush), equalTo(1));
        assertThat(game.play(flush, lowerFlushInTheMiddle), equalTo(1));
        assertThat(game.play(flush, equalFlush), equalTo(0));
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
    public void testFullHouseWithOtherFullHouses() {
        String higherFullHouseThreeOfAKind = "td th 4s 4c ts";
        String higherFullHousePair = "8d 8h 6s 6c 8s";
        String equalFullHouse = "8d 8h 4s 4c 8s";
        String lowerFullHouseThreeOfAKind = "7d 7h 4s 4c 7s";
        String lowerFullHousePair = "8d 8h 3s 3c 8s";

        assertThat(game.play(fullHouse, higherFullHouseThreeOfAKind), equalTo(-1));
        assertThat(game.play(fullHouse, higherFullHousePair), equalTo(-1));
        assertThat(game.play(fullHouse, lowerFullHouseThreeOfAKind), equalTo(1));
        assertThat(game.play(fullHouse, lowerFullHousePair), equalTo(1));
        assertThat(game.play(fullHouse, equalFullHouse), equalTo(0));
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
    public void testFourOfAKindResolvesWithOtherFourOfAKinds() {
        String higherFourOfAKind = "5c 5s 5d 5h 8c";
        String higherFourOfAKindKicker = "3c 3s 3d 3h tc";
        String equalFourOfAKind = "3c 3s 3d 3h 8c";
        String lowerFourOfAKind = "2c 2s 2d 2h 8c";
        String lowerFourOfAKindKicker = "3c 3s 3d 3h 6c";

        assertThat(game.play(fourOfAKind, higherFourOfAKind), equalTo(-1));
        assertThat(game.play(fourOfAKind, higherFourOfAKindKicker), equalTo(-1));
        assertThat(game.play(fourOfAKind, lowerFourOfAKind), equalTo(1));
        assertThat(game.play(fourOfAKind, lowerFourOfAKindKicker), equalTo(1));
        assertThat(game.play(fourOfAKind, equalFourOfAKind), equalTo(0));
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

    @Test
    public void testStraightFlushResolvesWithOtherStraightFlushes() {
        String higherStraightFlush = "8c 4c 5c 6c 7c";
        String higherStraightFlushOnAce = "tc jc qc kc ac";
        String equalStraightFlush = "3c 4c 5c 6c 7c";
        String lowerStraightFlush = "3c 4c 5c 6c 2c";
        String lowerStraightFlushOnAce = "3c 4c 5c 2c ac";

        assertThat(game.play(straightFlush, higherStraightFlush), equalTo(-1));
        assertThat(game.play(straightFlush, higherStraightFlushOnAce), equalTo(-1));
        assertThat(game.play(straightFlush, lowerStraightFlush), equalTo(1));
        assertThat(game.play(straightFlush, lowerStraightFlushOnAce), equalTo(1));
        assertThat(game.play(straightFlush, equalStraightFlush), equalTo(0));
    }
}
