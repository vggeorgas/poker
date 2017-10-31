package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class FourOfAKind implements Hand {

    private static final int RANK = 8;

    static Function<List<Card>, Optional<? extends Hand>> parser = (sortedCards) -> {
        Map<Card, Long> mapByCounting = sortedCards.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (mapByCounting.size() != 2) {
            return Optional.empty();
        }

        Card fourOfAKind = null;
        Card kicker = null;
        for (Map.Entry<Card, Long> entry : mapByCounting.entrySet()) {
            if (entry.getValue() == 4) {
                fourOfAKind = entry.getKey();
            }
            if (entry.getValue() == 1) {
                kicker = entry.getKey();
            }
        }
        if (fourOfAKind != null && kicker != null) {
            return Optional.of(new FourOfAKind(fourOfAKind, kicker));
        } else {
            return Optional.empty();
        }
    };

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
