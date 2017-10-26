package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Flush implements Hand {

    private static final int RANK = 6;
    private final List<Card> cards;

    public Flush(List<Card> cards) {
        this.cards = cards;
        Collections.sort(cards);
    }

    @Override
    public int compareTo(Hand o) {
        if (o instanceof Flush) {
            Flush anotherFlush = (Flush) o;
            return compareCards(this.cards, anotherFlush.getCards());
        } else {
            return Integer.compare(RANK, o.getRank());
        }
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
    public String toString() {
        String prettyCardsFormat = cards.stream().map(card-> card.getName()).collect(Collectors.joining(", "));
        String suit = Card.validSuits.get(cards.get(0).getSuite());
        return String.format("Flush of %s, [%s]", suit, prettyCardsFormat);
    }
}
