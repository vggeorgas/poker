package practice.fun.poker;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of={"value"})
public class Card implements Comparable<Card>{

    public static Map<String, Integer> validNamesAndValues = new HashMap<>();
    static {
        validNamesAndValues.put("2", 2);
        validNamesAndValues.put("3", 3);
        validNamesAndValues.put("4", 4);
        validNamesAndValues.put("5", 5);
        validNamesAndValues.put("6", 6);
        validNamesAndValues.put("7", 7);
        validNamesAndValues.put("8", 8);
        validNamesAndValues.put("9", 9);
        validNamesAndValues.put("t", 10);
        validNamesAndValues.put("j", 11);
        validNamesAndValues.put("q", 12);
        validNamesAndValues.put("k", 13);
        validNamesAndValues.put("a", 14);
    }

    public static Map<String, String> validSuits = new HashMap<>();
    static {
        validSuits.put("d", "diamonds");
        validSuits.put("h", "hearts");
        validSuits.put("c", "clubs");
        validSuits.put("s", "spades");
    }

    public static Comparator<Card> DESCENDING_COMPARATOR = (o1, o2) -> Integer.compare(o2.getValue(), o1.getValue());

    private final int value;
    private final String name;
    private final String suite;

    @Override
    public int compareTo(Card o) {
        return Integer.compare(value, o.getValue());
    }

    static boolean isValidCardName(String nameToCheck) {
        return validNamesAndValues.containsKey(nameToCheck);
    }

    static boolean isValidSuite(String suiteToCheck) {
        return validSuits.containsKey(suiteToCheck);
    }
}
