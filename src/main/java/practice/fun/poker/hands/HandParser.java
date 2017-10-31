package practice.fun.poker.hands;

import practice.fun.poker.Card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static practice.fun.poker.Card.DESCENDING_COMPARATOR;

public class HandParser {

    public HandParser(List<Card> cards) {
        Collections.sort(cards, DESCENDING_COMPARATOR);
    }

    private static Function<List<Card>, Optional<? extends Hand>> noCombinationParser = (sortedCards) -> Optional.of(new HighCard(sortedCards));

    private static List<Function<List<Card>, Optional<? extends Hand>>> handParsers = Arrays.asList(StraightFlush.parser,
            FourOfAKind.parser,
            FullHouse.parser,
            Flush.parser,
            Straight.parser,
            ThreeOfAKind.parser,
            TwoPair.parser,
            OnePair.parser,
            noCombinationParser);

    public static Hand parse(List<Card> cards) {
        Collections.sort(cards, DESCENDING_COMPARATOR);

        Optional<? extends Hand> hand = Optional.empty();

        for (Function<List<Card>, Optional<? extends Hand>> parser : handParsers) {
            hand = parser.apply(cards);
            if (hand.isPresent()) {
                break;
            }
        }

        return hand.get();
    }
}
