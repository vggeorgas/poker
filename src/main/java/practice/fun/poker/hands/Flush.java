package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.List;

@Getter
public class Flush extends Straight implements Hand {

    private static final int RANK = 4;

    public Flush(List<Card> cards) {
        super(cards);
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
}
