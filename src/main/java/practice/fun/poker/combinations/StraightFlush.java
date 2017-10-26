package practice.fun.poker.combinations;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.Collections;
import java.util.List;

@Getter
public class StraightFlush  extends Straight implements Combination {

    private static final int RANK = 1;

    public StraightFlush(List<Card> cards) {
        super(cards);
    }

    @Override
    public int compareTo(Combination o) {
        if (o instanceof StraightFlush) {
            StraightFlush anotherStraight = (StraightFlush) o;
            for (int i = 0; i < this.cards.size(); i++) {
                if (this.cards.get(i).getValue() > anotherStraight.getCards().get(i).getValue()) {
                    return 1;
                }
                if (this.cards.get(i).getValue() < anotherStraight.getCards().get(i).getValue()) {
                    return -1;
                }
            }
            return 0;
        } else {
            return Integer.compare(RANK, o.getRank());
        }
    }
}
