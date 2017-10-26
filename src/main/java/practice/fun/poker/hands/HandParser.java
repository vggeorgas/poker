package practice.fun.poker.hands;

import practice.fun.poker.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HandParser {

    private List<Card> sortedCards;

    public HandParser(List<Card> cards) {
        Collections.sort(cards);
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
        Map<Integer, Long> mapByCounting = sortedCards.stream().collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        if (mapByCounting.size() != 2) {
            return Optional.empty();
        }
        for (Map.Entry<Integer, Long> entry : mapByCounting.entrySet()) {
            if (entry.getValue() == 4) {
                return Optional.of(new FourOfAKind(entry.getKey()));
            }
        }
        return Optional.empty();
    };

    private static boolean isFlush(List<Card> sortedCards) {
        for (int i = 0; i < sortedCards.size() - 1; i++) {
            Card nextCard = sortedCards.get(i + 1);
            Card card = sortedCards.get(i);
            if (nextCard.getSuite() != card.getSuite()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isStraight(List<Card> sortedCards) {

        boolean checkStraightFromOne = false;
        if (sortedCards.get(sortedCards.size() - 1).getName().equals("a")) {
            checkStraightFromOne = true;
            sortedCards.remove(sortedCards.size() - 1);
        }

        for (int i = 0; i < sortedCards.size() - 1; i++) {
            Card nextCard = sortedCards.get(i + 1);
            Card card = sortedCards.get(i);
            if (i == 0 && checkStraightFromOne && card.getValue() != 2) {
                return false;
            }
            if (nextCard.getSuite() != card.getSuite()) {
                return false;
            }
        }
        return true;
    }

    private Function<Optional<? extends Hand>, Optional<? extends Hand>> fullHouseParser = (currentOptionalHand) -> {
        if (currentOptionalHand.isPresent()) {
            return currentOptionalHand;
        }
        Map<Integer, Long> mapByCounting = sortedCards.stream().collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        if (mapByCounting.size() != 2) {
            return Optional.empty();
        }
        int threeOfAKind = 0;
        int pair = 0;
        for (Map.Entry<Integer, Long> entry : mapByCounting.entrySet()) {
            if (entry.getValue() == 3) {
                threeOfAKind = entry.getKey();
            }
            if (entry.getValue() == 2) {
                pair = entry.getKey();
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
        Map<Integer, Long> mapByCounting = sortedCards.stream().collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        if (mapByCounting.size() != 3) {
            return Optional.empty();
        }

        int threeOfAKind = 0;
        List<Integer> kickers = new LinkedList<>();

        for (Map.Entry<Integer, Long> entry : mapByCounting.entrySet()) {
            if (entry.getValue() == 3) {
                threeOfAKind = entry.getKey();
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
        Map<Integer, Long> mapByCounting = sortedCards.stream().collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        if (mapByCounting.size() != 3) {
            return Optional.empty();
        }

        int pair1 = 0;
        int pair2 = 0;
        int kicker = 0;

        for (Map.Entry<Integer, Long> entry : mapByCounting.entrySet()) {
            if (entry.getValue() == 2) {
                if (pair1 == 0) {
                    pair1 = entry.getKey();
                } else {
                    pair2 = entry.getKey();
                }
            }
            if (entry.getValue() == 1) {
                kicker = entry.getKey();
            }
        }

        if (pair1 != 0 && pair2 != 0 && kicker != 0) {
            return Optional.of(new TwoPair(pair1, pair2, kicker));
        } else {
            return Optional.empty();
        }
    };

    private Function<Optional<? extends Hand>, Optional<? extends Hand>> onePairParser = (currentOptionalHand) -> {
        if (currentOptionalHand.isPresent()) {
            return currentOptionalHand;
        }
        Map<Integer, Long> mapByCounting = sortedCards.stream().collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        if (mapByCounting.size() != 4) {
            return Optional.empty();
        }

        int pair = 0;
        List<Integer> kickers = new ArrayList<>();
        for (Map.Entry<Integer, Long> entry : mapByCounting.entrySet()) {
            if (entry.getValue() == 2) {
                pair = entry.getKey();
            }
            if (entry.getValue() == 1) {
                kickers.add(entry.getKey());
            }
        }

        if (pair != 0 && kickers.size() == 3) {
            Collections.sort(kickers);
            return Optional.of(new OnePair(pair, kickers));
        } else {
            return Optional.empty();
        }
    };

    private Function<Optional<? extends Hand>, Optional<? extends Hand>> noCombinationParser = (currentOptionalHand) -> {
        if (currentOptionalHand.isPresent()) {
            return currentOptionalHand;
        } else {
            return Optional.of(new NoCombination(sortedCards));
        }
    };
}
