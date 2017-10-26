package practice.fun.poker;

import lombok.Getter;
import lombok.Setter;
import practice.fun.poker.combinations.Combination;
import practice.fun.poker.combinations.CombinationFactory;

import java.util.List;

@Getter
@Setter
public class Hand implements Comparable<Hand> {
    private final Combination combination;

    public Hand(List<Card> cards) {
        combination = CombinationFactory.createCombination(cards);
    }


    @Override
    public int compareTo(Hand o) {
        return combination.compareTo(o.combination);
    }
}
