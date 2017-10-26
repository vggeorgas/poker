package practice.fun.poker.hands;


import practice.fun.poker.Card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static practice.fun.poker.Card.DESCENDING_COMPARATOR;

public class HighCard implements Hand {

    private static final int RANK = 1;

    private final List<Card> kickers;

    public HighCard(List<Card> cards) {
        kickers = cards;
        Collections.sort(kickers, DESCENDING_COMPARATOR);
    }

    @Override
    public int getRank() {
        return RANK;
    }

    @Override
    public List<Card> getKickers() {
        return kickers;
    }

    @Override
    public int compareTo(Hand o) {
        if (o instanceof HighCard) {
            return compareKickers(o.getKickers());
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        String prettyCardsFormat = kickers.stream().map(card-> card.getName()).collect(Collectors.joining(", "));
        return String.format("High Card, [%s]", prettyCardsFormat);
    }
}
