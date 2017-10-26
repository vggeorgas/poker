package practice.fun.poker.hands;


import lombok.Getter;
import practice.fun.poker.Card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FullHouse implements Hand {

    private static final int RANK = 7;
    private final int threeOfAKind;
    private final int pair;

    public FullHouse(int threeOfAKind, int pair) {
        this.threeOfAKind = threeOfAKind;
        this.pair = pair;
    }

    @Override
    public int getRank() {
        return RANK;
    }

    @Override
    public List<Card> getKickers() {
        return Collections.emptyList();
    }

    @Override
    public int compareTo(Hand o) {
        if (o instanceof FullHouse) {
            FullHouse anotherFullHouse = (FullHouse) o;
            if (this.threeOfAKind > anotherFullHouse.getThreeOfAKind()) {
                return 1;
            }

            if (this.threeOfAKind < anotherFullHouse.getThreeOfAKind()) {
                return -1;
            }

            if (this.pair > anotherFullHouse.getPair()) {
                return 1;
            }

            if (this.pair < anotherFullHouse.getPair()) {
                return -1;
            }

            return 0;
        } else {
            return Integer.compare(RANK, o.getRank());
        }
    }

    @Override
    public String toString() {
        return String.format("Full house of %s with a pair of %s", threeOfAKind, pair);
    }
}
