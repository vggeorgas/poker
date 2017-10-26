package practice.fun.poker.combinations;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TwoPair implements Combination {

    private static final int RANK = 7;

    private final List<Integer> kicker;
    private final int bigPair;
    private final int smallPair;

    public TwoPair(int pair1, int pair2, Integer kicker) {
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
    public List<Integer> getKickers() {
        return this.kicker;
    }

    @Override
    public int compareTo(Combination o) {
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

            return Integer.compare(this.getKickers().get(0), anotherTwoPair.getKickers().get(0));
        } else {
            return Integer.compare(RANK, o.getRank());
        }
    }
}
