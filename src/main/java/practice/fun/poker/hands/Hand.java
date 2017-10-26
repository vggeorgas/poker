package practice.fun.poker.hands;


import practice.fun.poker.Card;

import java.util.List;

public interface Hand extends Comparable<Hand> {
    int getRank();

    List<Card> getKickers();

    default int compareKickers(List<Card> anotherCombinationKicker) {
        for (int i = 0; i < this.getKickers().size(); i++) {
            if (this.getKickers().get(i).getValue() > anotherCombinationKicker.get(i).getValue()) {
                return 1;
            }
            if (this.getKickers().get(i).getValue() < anotherCombinationKicker.get(i).getValue()) {
                return -1;
            }
        }
        return 0;
    }

    default int compareCards(List<Card> firstSortedCards, List<Card> secondSortedCards) {
        if (firstSortedCards.size() != secondSortedCards.size()) {
            throw new IllegalArgumentException("You should compare the same number of cards");
        }
        for (int i = 0; i < firstSortedCards.size(); i++) {
            if (firstSortedCards.get(i).getValue() > secondSortedCards.get(i).getValue()) {
                return 1;
            }
            if (firstSortedCards.get(i).getValue() < secondSortedCards.get(i).getValue()) {
                return -1;
            }
        }
        return 0;
    }
}
