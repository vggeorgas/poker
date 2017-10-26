package practice.fun.poker;

import practice.fun.poker.hands.Hand;
import practice.fun.poker.hands.HandParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter left hand");
        String firstLine = br.readLine();

        List<Card> leftHandCards = getCardsFromInput(firstLine);

        if (leftHandCards.size() != 5) {
            System.out.println("Invalid left hand");
            System.exit(0);
        }

        HandParser parserForLeftHand = new HandParser(leftHandCards);
        Hand leftHand = parserForLeftHand.parse();


        System.out.println("Enter right hand");
        String secondLine = br.readLine();

        List<Card> rightHandCards = getCardsFromInput(secondLine);

        if (rightHandCards.size() != 5) {
            System.out.println("Invalid right hand");
            System.exit(0);
        }

        HandParser parserForRightHand = new HandParser(rightHandCards);
        Hand rightHand = parserForRightHand.parse();

        System.out.println("Left hand: " + firstLine + ", right hand: " + secondLine);

        int comparison = leftHand.compareTo(rightHand);
        if (comparison > 0) {
            System.out.println("Winner is leftHand " + firstLine);
        } else if (comparison < 0) {
            System.out.println("Winner is rightHand " + secondLine);
        } else {
            System.out.println("Tie!!!!");
        }
    }

    private static List<Card> getCardsFromInput(String inputLine) {
        return Arrays.stream(inputLine.toLowerCase().split(" "))
                .filter(cardString -> cardString.length() == 2
                        && Card.isValidCardName(cardString.substring(0, 1))
                        && Card.isValidSuite(cardString.substring(1))
                )
                .map(cardString -> {
                    return Card.builder()
                            .name(cardString.substring(0, 1))
                            .suite(cardString.substring(1))
                            .value(Card.validNamesAndValues.get(cardString.substring(0, 1)))
                            .build();
                }).collect(Collectors.toList());
    }
}
