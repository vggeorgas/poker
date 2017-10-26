package practice.fun.poker.combinations;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.List;

@Getter
public class OnePair implements Combination {

    private static final int RANK = 8;

    private final List<Integer> kickers;
    private final int pair;

    public OnePair(int pair, List<Integer> kickers) {
        this.pair = pair;
        this.kickers = kickers;
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
}
