package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TwoPair implements Hand {

    private static final int RANK = 3;

    private final List<Card> kicker;
    private final int bigPair;
    private final int smallPair;

    public TwoPair(int pair1, int pair2, Card kicker) {
        if (pair1 > pair2) {
            bigPair = pair1;
            smallPair = pair2;
        } else {
            bigPair = pair2;
            smallPair = pair1;
        }
        this.kicker = new ArrayList<>();
        this.kicker.add(kicker);
    }

    @Override
    public int getRank() {
        return RANK;
    }

    @Override
    public List<Card> getKickers() {
        return this.kicker;
    }

    @Override
    public int compareTo(Hand o) {
        if (o instanceof TwoPair) {
            TwoPair anotherTwoPair = (TwoPair) o;
            if (this.bigPair > anotherTwoPair.getBigPair()) {
                return 1;
            }
            if (this.bigPair < anotherTwoPair.getBigPair()) {
                return -1;
            }

            if (this.smallPair > anotherTwoPair.getSmallPair()) {
                return 1;
            }
            if (this.smallPair < anotherTwoPair.getSmallPair()) {
                return -1;
            }

            return Integer.compare(this.getKickers().get(0).getValue(), anotherTwoPair.getKickers().get(0).getValue());
        } else {
            return Integer.compare(RANK, o.getRank());
        }
    }

    @Override
    public String toString() {
        return String.format("Two pairs of %s and %s with kickers %s", bigPair, smallPair,  kicker.get(0).getValue());
    }
}
