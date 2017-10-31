package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static practice.fun.poker.Card.DESCENDING_COMPARATOR;

@Getter
public class OnePair implements Hand {

    private static final int RANK = 2;

    static Function<List<Card>, Optional<? extends Hand>> parser = (sortedCards) -> {
        Map<Card, Long> mapByCounting = sortedCards.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (mapByCounting.size() != 4) {
            return Optional.empty();
        }

        int pair = 0;
        List<Card> kickers = new ArrayList<>();
        for (Map.Entry<Card, Long> entry : mapByCounting.entrySet()) {
            if (entry.getValue() == 2) {
                pair = entry.getKey().getValue();
            }
            if (entry.getValue() == 1) {
                kickers.add(entry.getKey());
            }
        }

        if (pair != 0 && kickers.size() == 3) {
            return Optional.of(new OnePair(pair, kickers));
        } else {
            return Optional.empty();
        }
    };

    private final List<Card> kickers;
    private final int pair;

    public OnePair(int pair, List<Card> kickers) {
        this.pair = pair;
        this.kickers = kickers;
        Collections.sort(this.kickers, DESCENDING_COMPARATOR);
    }

    @Override
    public int getRank() {
        return RANK;
    }

    @Override
    public List<Card> getKickers() {
        return kickers;
    }

    @Override
    public int compareTo(Hand o) {
        if (o instanceof OnePair) {
            if (this.pair == ((OnePair) o).getPair()) {
                return compareKickers(o.getKickers());
            } else {
                return Integer.compare(this.pair, ((OnePair) o).getPair());
            }
        } else {
            return Integer.compare(RANK, o.getRank());
        }
    }

    @Override
    public String toString() {
        String prettyCardsFormat = kickers.stream().map(card -> card.getName()).collect(Collectors.joining(", "));
        return String.format("One pair of %s with kickers [%s]", pair, prettyCardsFormat);
    }
}
