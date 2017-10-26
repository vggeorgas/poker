package practice.fun.poker.hands;

import lombok.Getter;

import java.util.List;

@Getter
public class OnePair implements Hand {

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
}
