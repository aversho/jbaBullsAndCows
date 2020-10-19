package bullscows;

public class Main {
    public static void main(String[] args) {
        try {
            BullsAndCows bullsAndCows = new BullsAndCows();
            bullsAndCows.nextGame();
        } catch (GameException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
