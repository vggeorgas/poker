package practice.fun.poker.hands;

import practice.fun.poker.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static practice.fun.poker.Card.DESCENDING_COMPARATOR;

public class HandParser {

    private List<Card> sortedCards;

    public HandParser(List<Card> cards) {
        Collections.sort(cards, DESCENDING_COMPARATOR);
        this.sortedCards = cards;
    }

    public Hand parse() {
        return straightFlushParser
                .andThen(fourOfAKindParser)
                .andThen(fullHouseParser)
                .andThen(flushParser)
                .andThen(straightParser)
                .andThen(threeOfAKindParser)
                .andThen(twoPairParser)
                .andThen(onePairParser)
                .andThen(noCombinationParser)
                .apply(Optional.empty())
                .get();
    }

    private Function<Optional<? extends Hand>, Optional<? extends Hand>> straightFlushParser = (currentOptionalHand) -> {
        if (currentOptionalHand.isPresent()) {
            return currentOptionalHand;
        }
        if (isFlush(sortedCards) && isStraight(sortedCards)) {
            return Optional.of(new StraightFlush(sortedCards));
        } else {
            return Optional.empty();
        }
    };

    private Function<Optional<? extends Hand>, Optional<? extends Hand>> fourOfAKindParser = (currentOptionalHand) -> {
        if (currentOptionalHand.isPresent()) {
            return currentOptionalHand;
        }
        Map<Card, Long> mapByCounting = sortedCards.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (mapByCounting.size() != 2) {
            return Optional.empty();
        }
        for (Map.Entry<Card, Long> entry : mapByCounting.entrySet()) {
            if (entry.getValue() == 4) {
                return Optional.of(new FourOfAKind(entry.getKey().getValue()));
            }
        }
        return Optional.empty();
    };

    private static boolean isFlush(List<Card> sortedCards) {
        for (int i = 0; i < sortedCards.size() - 1; i++) {
            Card nextCard = sortedCards.get(i + 1);
            Card card = sortedCards.get(i);
            if (!nextCard.getSuite().equals(card.getSuite())) {
                return false;
            }
        }
        return true;
    }

    private static boolean isStraight(List<Card> sortedCards) {

        if (sortedCards.get(0).getName().equals("a")) {
            int nextValue = 2;
            for (int i = 1; i < sortedCards.size() - 1; i++) {
                if (sortedCards.get(i).getValue() != nextValue) {
                    return false;
                }
                nextValue++;
            }
            return true;
        }

        for (int i = 0; i < sortedCards.size() - 1; i++) {
            Card nextCard = sortedCards.get(i + 1);
            Card card = sortedCards.get(i);
            if (nextCard.getValue() != card.getValue() - 1) {
                return false;
            }
        }
        return true;
    }

    private Function<Optional<? extends Hand>, Optional<? extends Hand>> fullHouseParser = (currentOptionalHand) -> {
        if (currentOptionalHand.isPresent()) {
            return currentOptionalHand;
        }
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

    private Function<Optional<? extends Hand>, Optional<? extends Hand>> flushParser = (currentOptionalHand) -> {
        if (currentOptionalHand.isPresent()) {
            return currentOptionalHand;
        }
        if (isFlush(sortedCards)) {
            return Optional.of(new Flush(sortedCards));
        } else {
            return Optional.empty();
        }
    };

    private Function<Optional<? extends Hand>, Optional<? extends Hand>> straightParser = (currentOptionalHand) -> {
        if (currentOptionalHand.isPresent()) {
            return currentOptionalHand;
        }
        if (isStraight(sortedCards)) {
            return Optional.of(new Straight(sortedCards));
        } else {
            return Optional.empty();
        }
    };

    private Function<Optional<? extends Hand>, Optional<? extends Hand>> threeOfAKindParser = (currentOptionalHand) -> {
        if (currentOptionalHand.isPresent()) {
            return currentOptionalHand;
        }
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

    private Function<Optional<? extends Hand>, Optional<? extends Hand>> twoPairParser = (currentOptionalHand) -> {
        if (currentOptionalHand.isPresent()) {
            return currentOptionalHand;
        }
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

    private Function<Optional<? extends Hand>, Optional<? extends Hand>> onePairParser = (currentOptionalHand) -> {
        if (currentOptionalHand.isPresent()) {
            return currentOptionalHand;
        }
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

    private Function<Optional<? extends Hand>, Optional<? extends Hand>> noCombinationParser = (currentOptionalHand) -> {
        if (currentOptionalHand.isPresent()) {
            return currentOptionalHand;
        } else {
            return Optional.of(new HighCard(sortedCards));
        }
    };
}
