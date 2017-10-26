package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static practice.fun.poker.Card.DESCENDING_COMPARATOR;

@Getter
public class Straight implements Hand {

    private static final int RANK = 5;
    private final List<Card> cards;

    public Straight(List<Card> cards) {
        this.cards = cards;
        Collections.sort(this.cards, DESCENDING_COMPARATOR);
    }

    @Override
    public int getRank() {return RANK;}

    @Override
    public List<Card> getKickers() {return Collections.emptyList();}

    @Override
    public int compareTo(Hand o) {
        if (o instanceof Straight) {
            Straight anotherStraight = (Straight) o;
            return compareCards(this.cards, anotherStraight.getCards());
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
        return String.format("Straight [%s]", prettyCardsFormat);
    }
}
