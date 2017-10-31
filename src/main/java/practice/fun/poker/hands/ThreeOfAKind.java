package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static practice.fun.poker.Card.DESCENDING_COMPARATOR;

@Getter
public class ThreeOfAKind implements Hand {

    private static final int RANK = 4;

    static Function<List<Card>, Optional<? extends Hand>> parser = (sortedCards) -> {

        Map<Card, Long> mapByCounting = sortedCards.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (mapByCounting.size() != 3) {
            return Optional.empty();
        }

        int threeOfAKind = 0;
        List<Card> kickers = new LinkedList<>();

        for (Map.Entry<Card, Long> entry : mapByCounting.entrySet()) {
            if (entry.getValue() == 3) {
                threeOfAKind = entry.getKey().getValue();
            } else {
                kickers.add(entry.getKey());
            }
        }

        if (threeOfAKind != 0 && kickers.size() == 2) {
            Collections.sort(kickers);
            return Optional.of(new ThreeOfAKind(threeOfAKind, kickers));
        }

        return Optional.empty();
    };


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
