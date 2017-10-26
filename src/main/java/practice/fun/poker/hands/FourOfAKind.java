package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FourOfAKind implements Hand {

    private static final int RANK = 8;
    private final int value;

    public FourOfAKind(int value) {
        this.value = value;
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
        if (o instanceof FourOfAKind) {
            return Integer.compare(this.getValue(), ((FourOfAKind) o).getValue());
        } else {
            return Integer.compare(RANK, o.getRank());
        }
    }

    @Override
    public String toString() {
        return String.format("Four of a kind of Rank %s", value);
    }
}
