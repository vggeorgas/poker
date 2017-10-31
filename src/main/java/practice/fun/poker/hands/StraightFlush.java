package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static practice.fun.poker.Card.DESCENDING_COMPARATOR;
import static practice.fun.poker.Card.validNamesAndValues;
import static practice.fun.poker.hands.Flush.isFlush;
import static practice.fun.poker.hands.Straight.isStraight;

@Getter
public class StraightFlush implements Hand {

    private static final int RANK = 9;

    static Function<List<Card>, Optional<? extends Hand>> parser = (sortedCards) -> {
        if (isFlush(sortedCards) && isStraight(sortedCards)) {
            return Optional.of(new StraightFlush(sortedCards));
        } else {
            return Optional.empty();
        }
    };

    private final List<Card> cards;

    public StraightFlush(List<Card> cards) {
        this.cards = cards;
        Collections.sort(this.cards, DESCENDING_COMPARATOR);
    }

    @Override
    public int getRank() {
        return RANK;
    }

    @Override
    public List<Card> getKickers() {
        return Collections.emptyList();
    }

    @Override
    public int compareTo(Hand o) {
        if (o instanceof StraightFlush) {
            StraightFlush anotherStraightFlush = (StraightFlush) o;
            int valueToCompare = isLowest(this) ? 5 : this.cards.get(0).getValue();
            int valueToCompareFormAnotherStraight = isLowest(anotherStraightFlush) ? 5 : anotherStraightFlush.getCards().get(0).getValue();
            return Integer.compare(valueToCompare, valueToCompareFormAnotherStraight);
        } else {
            return Integer.compare(RANK, o.getRank());
        }
    }

    @Override
    public String toString() {
        String prettyCardsFormat = "";
        if (cards.get(0).getName().equals("a")) {
            prettyCardsFormat = cards.subList(1, cards.size()).stream().map(card -> card.getName()).collect(Collectors.joining(", ")) + ", a";
        } else {
            prettyCardsFormat = cards.stream().map(card -> card.getName()).collect(Collectors.joining(", "));
        }
        String suit = Card.validSuits.get(cards.get(0).getSuite());
        return String.format("Straight Flush of %s: [%s]", suit, prettyCardsFormat);
    }

    private boolean isLowest(StraightFlush straightFlush) {
        return (straightFlush.getCards().get(0).getValue() == validNamesAndValues.get("a")
                && straightFlush.getCards().get(1).getValue() == validNamesAndValues.get("5")) ? true : false;
    }
}
