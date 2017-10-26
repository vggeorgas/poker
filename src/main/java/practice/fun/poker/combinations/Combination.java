package practice.fun.poker.combinations;


import java.util.List;

public interface Combination extends Comparable<Combination> {
    int getRank();

    List<Integer> getKickers();

    default int compareKickers(List<Integer> anotherCombinationKicker) {
        for (int i = 0; i < this.getKickers().size(); i++) {
            if (this.getKickers().get(i) > anotherCombinationKicker.get(i)) {
                return 1;
            }
            if (this.getKickers().get(i) < anotherCombinationKicker.get(i)) {
                return -1;
            }
        }
        return 0;
    }
}
