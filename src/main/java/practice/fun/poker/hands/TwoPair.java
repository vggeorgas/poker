package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class TwoPair implements Hand {

    private static final int RANK = 3;

    static Function<List<Card>, Optional<? extends Hand>> parser = (sortedCards) -> {
        Map<Card, Long> mapByCounting = sortedCards.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (mapByCounting.size() != 3) {
            return Optional.empty();
        }

        int pair1 = 0;
        int pair2 = 0;
        Card kicker = null;

        for (Map.Entry<Card, Long> entry : mapByCounting.entrySet()) {
            if (entry.getValue() == 2) {
                if (pair1 == 0) {
                    pair1 = entry.getKey().getValue();
                } else {
                    pair2 = entry.getKey().getValue();
                }
            }
            if (entry.getValue() == 1) {
                kicker = entry.getKey();
            }
        }

        if (pair1 != 0 && pair2 != 0 && kicker != null) {
            return Optional.of(new TwoPair(pair1, pair2, kicker));
        } else {
            return Optional.empty();
        }
    };

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
