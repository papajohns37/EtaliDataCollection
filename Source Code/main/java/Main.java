import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Main {
    private static final String SPREADSHEET_ID = "1JYxdNA1i9XMg652Tqwb4wqFOQsmMKsBvNPzf-NqO-i8";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your discord username?");
        String username = scanner.nextLine();

        Configuration.browser = "chrome";
        Selenide.open("https://moxfield.com/decks/JhWK-MfmY0W1V5feboKLxQ/goldfish");

        while (true) {
            List<String> cardNames = getCardNames();

            //Get if hand was successful
            HandResults handResult = getWasHandSuccessfulInput(scanner);

            if (handResult == HandResults.quit)
                break;

            //Get hand size
            int handSize = 0;
            if (handResult == HandResults.wasSuccessful) {
                handSize = getHandSize(scanner);
            }

            //Organize data
            List<List<Object>> values = getSheetsData(handResult, handSize, username, cardNames);

            AppendValues.appendValues(SPREADSHEET_ID, "A:N", "USER_ENTERED", values);

            refreshHand();
        }
    }

    private static List<String> getCardNames() {
        ElementsCollection cards = $$x("//*[contains(@src, 'assets.moxfield.net/cards') and not(@alt='Etali, Primal Conqueror // Etali, Primal Sickness')]").shouldHave(size(7));
        List<String> cardNames = new ArrayList<>();
        for (SelenideElement card : cards)
            cardNames.add(card.getAttribute("alt"));
        return cardNames;
    }

    private static void refreshHand() {
        $x("//*[text()='estart']").click();
        $x("//*[text()='Restart']/..").click();
        $x("//*[text()='Restart']").should(not(exist));
    }

    private static List<List<Object>> getSheetsData(HandResults handResults, int handSize, String username, List<String> cardNames) {
        List<String> handSizesThatWorked = new ArrayList<>();
        if (handResults == HandResults.wasSuccessful) {
            for (int i = 7; i >= 3; i--) {
                handSizesThatWorked.add(handSize <= i ? "1" : "");
            }
        } else if (handResults == HandResults.wasNotSuccessful) {
            handSizesThatWorked.add("0");
            handSizesThatWorked.add("");
            handSizesThatWorked.add("");
            handSizesThatWorked.add("");
            handSizesThatWorked.add("");
        } else {
            handSizesThatWorked.add("?");
            handSizesThatWorked.add("?");
            handSizesThatWorked.add("?");
            handSizesThatWorked.add("?");
            handSizesThatWorked.add("?");
        }

        List<List<Object>> values = List.of(Arrays.asList("",
                username,
                        handSizesThatWorked.get(0),
                        handSizesThatWorked.get(1),
                        handSizesThatWorked.get(2),
                        handSizesThatWorked.get(3),
                        handSizesThatWorked.get(4),
                        cardNames.get(0),
                        cardNames.get(1),
                        cardNames.get(2),
                        cardNames.get(3),
                        cardNames.get(4),
                        cardNames.get(5),
                        cardNames.get(6)));
        return values;
    }

    private static HandResults getWasHandSuccessfulInput(Scanner scanner) {
        while (true) {
            System.out.println("Does this hand cast Etali on turn 2? (Y/N/?/Q)");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                return HandResults.wasSuccessful;
            }
            else if (input.equalsIgnoreCase("N")) {
                return HandResults.wasNotSuccessful;
            }
            else if (input.equalsIgnoreCase("?")) {
                return HandResults.unsure;
            }
            else if (input.equalsIgnoreCase("Q")){
                return HandResults.quit;
            }
            else { System.out.println("Try again (enter a Y, y, N, n or ?)"); }
        }
    }

    private static int getHandSize(Scanner scanner) {
        int handSize = -1;
        while (handSize == -1) {
            System.out.println("How many cards did you use to count to 7 / win the game?");
            String input = scanner.nextLine();
            try {
                handSize = Integer.parseUnsignedInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Try again (enter a number between 1 and 7)");
                handSize = -1;
                continue;
            }

            if (!(handSize <= 7 && handSize >= 1)) {
                System.out.println("Try again (enter a number between 1 and 7)");
                handSize = -1;
            }
        }
        return handSize;
    }

    private enum HandResults{
        wasSuccessful,
        wasNotSuccessful,
        unsure,
        quit
    }
}