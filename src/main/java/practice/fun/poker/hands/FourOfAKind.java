package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.Arrays;
import java.util.List;

@Getter
public class FourOfAKind implements Hand {

    private static final int RANK = 8;
    private final Card fourOfAKind;
    private final Card kicker;

    public FourOfAKind(Card fourOfAKind, Card kicker) {
        this.fourOfAKind = fourOfAKind;
        this.kicker = kicker;
    }

    @Override
    public int getRank() {
        return RANK;
    }

    @Override
    public List<Card> getKickers() {
        return Arrays.asList(kicker);
    }

    @Override
    public int compareTo(Hand o) {
        if (o instanceof FourOfAKind) {
            int fourOfAKindComparison = fourOfAKind.compareTo(((FourOfAKind) o).getFourOfAKind());
            return fourOfAKindComparison == 0 ? kicker.compareTo(((FourOfAKind) o).getKicker()) : fourOfAKindComparison;
        } else {
            return Integer.compare(RANK, o.getRank());
        }
    }

    @Override
    public String toString() {
        return String.format("Four of a kind of Rank %s with kicker", fourOfAKind.getName(), kicker.getName());
    }
}
