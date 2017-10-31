package practice.fun.poker.hands;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static practice.fun.poker.Card.DESCENDING_COMPARATOR;
import static practice.fun.poker.Card.validNamesAndValues;

@Getter
public class Straight implements Hand {

    private static final int RANK = 5;

    static Function<List<Card>, Optional<? extends Hand>> parser = (sortedCards) -> {
        if (isStraight(sortedCards)) {
            return Optional.of(new Straight(sortedCards));
        } else {
            return Optional.empty();
        }
    };

    static boolean isStraight(List<Card> sortedCards) {
        return isStraightFromAceToFive(sortedCards) || isStraightHigherThanFive(sortedCards);
    }

    private static boolean isStraightHigherThanFive(List<Card> sortedCards) {
        for (int i = 0; i < sortedCards.size() - 1; i++) {
            Card nextCard = sortedCards.get(i + 1);
            Card card = sortedCards.get(i);
            if (nextCard.getValue() != card.getValue() - 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isStraightFromAceToFive(List<Card> sortedCards) {
        if (sortedCards.get(0).getName().equals("a") && sortedCards.get(1).getValue() == 5) {
            int nextValue = 5;
            for (int i = 1; i < sortedCards.size() - 1; i++) {
                if (sortedCards.get(i).getValue() != nextValue) {
                    return false;
                }
                nextValue--;
            }
            return true;
        }
        return false;
    }

    private final List<Card> cards;

    public Straight(List<Card> cards) {
        this.cards = cards;
        Collections.sort(this.cards, DESCENDING_COMPARATOR);
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
        if (o instanceof Straight) {
            Straight anotherStraight = (Straight) o;
            int valueToCompare = isLowest(this) ? 5 : this.cards.get(0).getValue();
            int valueToCompareFormAnotherStraight = isLowest(anotherStraight) ? 5 : anotherStraight.getCards().get(0).getValue();
            return Integer.compare(valueToCompare, valueToCompareFormAnotherStraight);
        } else {
            return Integer.compare(RANK, o.getRank());
        }
    }

    @Override
    public String toString() {
        String prettyCardsFormat = "";
        if (isLowest(this)) {
            prettyCardsFormat = cards.subList(1, cards.size()).stream().map(card -> card.getName()).collect(Collectors.joining(", ")) + ", a";
        } else {
            prettyCardsFormat = cards.stream().map(card -> card.getName()).collect(Collectors.joining(", "));
        }
        return String.format("Straight [%s]", prettyCardsFormat);
    }

    private boolean isLowest(Straight straight) {
        return (straight.getCards().get(0).getValue() == validNamesAndValues.get("a")
                && straight.getCards().get(1).getValue() == validNamesAndValues.get("5")) ? true : false;
    }
}
