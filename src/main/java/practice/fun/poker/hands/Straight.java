package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.Collections;
import java.util.List;

@Getter
public class Straight implements Hand {

    private static final int RANK = 5;
    protected final List<Card> cards;

    public Straight(List<Card> cards) {
        this.cards = cards;
        Collections.sort(cards);
    }

    @Override
    public int getRank() {return RANK;}

    @Override
    public List<Integer> getKickers() {return Collections.emptyList();}

    @Override
    public int compareTo(Hand o) {
        if (o instanceof Straight) {
            Straight anotherStraight = (Straight) o;
            return compareCards(this.cards, anotherStraight.getCards());
        } else {
            return Integer.compare(RANK, o.getRank());
        }
    }
}
