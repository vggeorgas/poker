package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.List;

@Getter
public class StraightFlush  extends Straight implements Hand {

    private static final int RANK = 1;

    public StraightFlush(List<Card> cards) {
        super(cards);
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
}
