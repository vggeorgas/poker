package practice.fun.poker.combinations;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.Collections;
import java.util.List;

@Getter
public class Straight implements Combination {

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
    public int compareTo(Combination o) {
        if (o instanceof Straight) {
            Straight anotherStraight = (Straight) o;
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
