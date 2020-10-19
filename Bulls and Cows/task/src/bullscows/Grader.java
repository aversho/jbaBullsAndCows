package bullscows;

public class Grader {
    private final String code;
    private int cowsCount;
    private int bullsCount;

    public Grader(String code) {
        this.code = code;
    }

    public String grade(String guess) {
        countCowsAndBulls(guess);
        return formatOutput();
    }

    public boolean isGameOver(){
        return bullsCount == code.length();
    }

    private void countCowsAndBulls(String guess) {
        cowsCount = 0;
        bullsCount = 0;

        for (int i = 0; i < guess.length(); i++) {
            for (int j = 0; j < code.length(); j++) {
                if (guess.charAt(i) == code.charAt(j)) {
                    if (i == j){
                        bullsCount++;
                        break;
                    }
                    cowsCount++;
                    break;
                }
            }
        }
    }

    private String formatOutput() {
        StringBuilder output = new StringBuilder("Grade: ");
        if (hasBulls()) {
            output.append(bullsCount).append(" bull(s)");
        }
        if (hasCows()) {
            if (hasBulls()) {
                output.append(" and ");
            }
            output.append(cowsCount).append(" cow(s)");
        }
        if (!hasCows() && !hasBulls()) {
            output.append("None");
        }

        return output.toString();
    }

    private boolean hasCows() {
        return cowsCount > 0;
    }

    private boolean hasBulls() {
        return bullsCount > 0;
    }
}
