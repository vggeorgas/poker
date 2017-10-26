package practice.fun.poker.hands;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class FourOfAKind implements Hand {

    private static final int RANK = 2;
    private final int value;

    public FourOfAKind(int value) {
        this.value = value;
    }

    @Override
    public int getRank() {
        return RANK;
    }

    @Override
    public List<Integer> getKickers() {
        return Collections.emptyList();
    }

    @Override
    public int compareTo(Hand o) {
        if (o instanceof FourOfAKind) {
            return Integer.compare(this.getValue(), ((FourOfAKind) o).getValue());
        } else {
            return Integer.compare(RANK, o.getRank());
        }
    }
}
