package practice.fun.poker.hands;


import lombok.Getter;
import practice.fun.poker.Card;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class FullHouse implements Hand {

    private static final int RANK = 7;

    static Function<List<Card>, Optional<? extends Hand>> parser = (sortedCards) -> {
        Map<Card, Long> mapByCounting = sortedCards.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (mapByCounting.size() != 2) {
            return Optional.empty();
        }
        int threeOfAKind = 0;
        int pair = 0;
        for (Map.Entry<Card, Long> entry : mapByCounting.entrySet()) {
            if (entry.getValue() == 3) {
                threeOfAKind = entry.getKey().getValue();
            }
            if (entry.getValue() == 2) {
                pair = entry.getKey().getValue();
            }
        }
        if (threeOfAKind != 0 && pair != 0) {
            return Optional.of(new FullHouse(threeOfAKind, pair));
        } else {
            return Optional.empty();
        }
    };

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
