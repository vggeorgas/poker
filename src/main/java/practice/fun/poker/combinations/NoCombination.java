package practice.fun.poker.combinations;


import practice.fun.poker.Card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NoCombination implements Combination {

    private static final int RANK = 9;

    private final List<Integer> kickers;

    public NoCombination(List<Card> cards) {
        kickers = cards.stream().map(card -> card.getValue()).collect(Collectors.toList());
        Collections.sort(kickers);
    }

    @Override
    public int getRank() {
        return RANK;
    }

    @Override
    public List<Integer> getKickers() {
        return kickers;
    }

    @Override
    public int compareTo(Combination o) {
        if (o instanceof NoCombination) {
            return compareKickers(o.getKickers());
        } else {
            return -1;
        }
    }
}
