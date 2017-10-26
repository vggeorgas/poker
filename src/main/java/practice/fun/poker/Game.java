package practice.fun.poker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter left hand");
        String firstLine = br.readLine();
        String[] cardsArray = firstLine.split(" ");
        if (cardsArray.length != 5) {
            System.out.println("Invalid left hand");
            System.exit(0);
        }

        List<Card> leftHandCards = Arrays.stream(cardsArray)
                .map(cardString -> {
                    cardString.toCharArray();
                    char[] charArr = cardString.toCharArray();
                    if (charArr.length == 2 && Card.validNamesAndValues.get(String.valueOf(charArr[0])) != null
                            && Card.validSuites.contains(String.valueOf(charArr[1]))) {
                        return Card.builder()
                                .name(String.valueOf(charArr[0]))
                                .suite(String.valueOf(charArr[1]))
                                .value(Card.validNamesAndValues.get(String.valueOf(charArr[0])))
                                .build();
                    } else {
                        System.out.println("Invalid left hand");
                        throw new RuntimeException("Invalid hand");
                    }
                }).collect(Collectors.toList());

        Hand leftHand = new Hand(leftHandCards);


        System.out.println("Enter right hand");
        String secondLine = br.readLine();


        cardsArray = secondLine.split(" ");
        if (cardsArray.length != 5) {
            System.out.println("Invalid left hand");
            System.exit(0);
        }

        List<Card> rightHandCards = Arrays.stream(cardsArray)
                .map(cardString -> {
                    cardString.toCharArray();
                    char[] charArr = cardString.toCharArray();
                    if (charArr.length == 2 && Card.validNamesAndValues.get(String.valueOf(charArr[0])) != null
                            && Card.validSuites.contains(String.valueOf(charArr[1]))) {
                        return Card.builder()
                                .name(String.valueOf(charArr[0]))
                                .suite(String.valueOf(charArr[1]))
                                .value(Card.validNamesAndValues.get(String.valueOf(charArr[0])))
                                .build();
                    } else {
                        System.out.println("Invalid left hand");
                        throw new RuntimeException("Invalid hand");
                    }
                }).collect(Collectors.toList());

        Hand rightHand = new Hand(rightHandCards);

        System.out.println("Left hand: " + firstLine + ", right hand: " + secondLine);

        int comparison = leftHand.compareTo(rightHand);
        if (comparison > 0) {
            System.out.println("Winner is leftHand " +firstLine);
        } else if (comparison < 0) {
            System.out.println("Winner is rightHand " +secondLine);
        } else {
            System.out.println("Tie!!!!");
        }
    }
}
