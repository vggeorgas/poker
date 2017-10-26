package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static practice.fun.poker.Card.DESCENDING_COMPARATOR;

@Getter
public class ThreeOfAKind implements Hand {

    private static final int RANK = 4;
    private final int value;
    private final List<Card> kickers;

    public ThreeOfAKind(int value, List<Card> kickers) {
        this.value = value;
        this.kickers = kickers;
        Collections.sort(kickers, DESCENDING_COMPARATOR);
    }

    @Override
    public int getRank() {
        return RANK;
    }

    @Override
    public List<Card> getKickers() {
        return this.kickers;
    }

    @Override
    public int compareTo(Hand o) {
        if (o instanceof ThreeOfAKind) {
            ThreeOfAKind anotherThreeOfAKind = (ThreeOfAKind) o;
            if (this.getValue() != anotherThreeOfAKind.getValue()) {
                return Integer.compare(this.getValue(), ((ThreeOfAKind) o).getValue());
            }

            return compareKickers(anotherThreeOfAKind.getKickers());
        } else {
            return Integer.compare(RANK, o.getRank());
        }
    }

    @Override
    public String toString() {
        String prettyCardsFormat = kickers.stream().map(card -> card.getName()).collect(Collectors.joining(", "));
        return String.format("Three of a kind of %s with kickers [%s]", value, prettyCardsFormat);
    }
}
