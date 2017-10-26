package practice.fun.poker.combinations;

import lombok.Getter;
import practice.fun.poker.Card;

import java.util.List;

@Getter
public class ThreeOfAKind implements Combination {

    private static final int RANK = 6;
    private final int value;
    private final List<Integer> kickers;

    public ThreeOfAKind(int value, List<Integer> kickers) {
        this.value = value;
        this.kickers = kickers;
    }

    @Override
    public int getRank() {
        return RANK;
    }

    @Override
    public List<Integer> getKickers() {
        return this.kickers;
    }

    @Override
    public int compareTo(Combination o) {
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
}
