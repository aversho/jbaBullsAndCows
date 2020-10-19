package bullscows;

import java.util.Scanner;

public class BullsAndCows {
    private final Scanner sc = new Scanner(System.in);
    Secret secret;

    public void nextGame(){
        int sizeOfSecret = askUserForSecretLength();
        int sizeOfDictionary = askUserForSecretVariety();

        secret = new Secret(sizeOfSecret, sizeOfDictionary);
        System.out.printf("The secret is prepared: %s (%s)\n",
                secret.getCipheredSecret(), secret.getLimits());

        startGameLoop();
    }

    private int askUserForSecretLength() {
        System.out.println("Please, enter the secret code's length:");
        return promptNumber();
    }

    private int askUserForSecretVariety(){
        System.out.println("Please, enter the number of possible symbols in the code:");
        return promptNumber();
    }

    private int promptNumber(){
        String userInput;
        userInput = sc.nextLine().trim();
        if (!userInput.matches("\\d+")) {
            throw new GameException(String.format("\"%s\" isn't a valid number.", userInput));
        }
        return Integer.parseInt(userInput);
    }

    private void startGameLoop() {
        System.out.println("Okay, let's start a game!");
        Grader grader = new Grader(secret.getSecret());

        int turn = 1;
        while (!grader.isGameOver()) {
            System.out.printf("Turn %d:\n", turn);
            System.out.println(grader.grade(askUserForSecretGuess()));
            turn++;
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }

    private String askUserForSecretGuess() {
        String guess = sc.nextLine();
        if(!isGuessInLimits(guess)) {
            return "";
        }
        return guess;
    }

    private boolean isGuessInLimits(String guess) {
        if(guess.length() != secret.length()) {
            System.out.println("Warning: guess length must be equal to secret length " + secret.length());
            return false;
        }
        if(!guess.matches("[" + secret.getSecret() + "]+")){
            System.out.printf("Warning: guess must contain only symbols in secret range (%s)\n",
                    secret.getLimits());
            return false;
        }
        return true;
    }
}
