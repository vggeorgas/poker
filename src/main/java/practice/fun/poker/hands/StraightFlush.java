package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static practice.fun.poker.Card.DESCENDING_COMPARATOR;

@Getter
public class StraightFlush implements Hand {

    private static final int RANK = 9;
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
            return compareCards(this.cards, anotherStraightFlush.getCards());
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
}
