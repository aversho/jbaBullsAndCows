package bullscows;

import java.util.Random;

public class Secret {
    private final StringBuilder secret = new StringBuilder();
    private final int LOWER_LIMIT = 1;
    private final int UPPER_LIMIT = 36;
    private final int sizeOfDictionary;
    private final int sizeOfSecret;
    private final Random random = new Random();

    public Secret(int sizeOfSecret, int sizeOfDictionary){
        this.sizeOfDictionary = sizeOfDictionary;
        this.sizeOfSecret = sizeOfSecret;
        checkLimits();
        generate();
    }

    private void checkLimits(){
        if (sizeOfDictionary > UPPER_LIMIT) {
            throw new GameException(String.format("maximum number of possible symbols in the code is %d (%s).",
                    UPPER_LIMIT, getLimits()));
        }
        if(sizeOfSecret > sizeOfDictionary) {
            throw new GameException(String.format("it's not possible to generate a code with a length of %d " +
                            "with %d unique symbols.",
                    sizeOfSecret, sizeOfDictionary));
        }
        if(sizeOfSecret < LOWER_LIMIT) {
            throw new GameException(String.format("it's not possible to generate a code with a length of %d " +
                            "minimum length is %d.",
                    sizeOfSecret, LOWER_LIMIT));
        }
    }

    private void generate() {
        do {
            char candidate = nextChar();
            if (isValid(candidate)) {
                secret.append(candidate);
            }
        } while (secret.length() != sizeOfSecret);
    }

    public String getLimits() {
        StringBuilder range = new StringBuilder("0");
        if(sizeOfDictionary > 1){
            range.append('-');
        }
        if(sizeOfDictionary > 1 && sizeOfDictionary <= 10){
            range.append((char)('0' + sizeOfDictionary - 1));
        }
        if(sizeOfDictionary > 10){
            range.append("9, a");
        }
        if(sizeOfDictionary > 11){
            range.append('-').append((char)('a' + sizeOfDictionary - 11));
        }
        return range.toString();
    }

    private boolean isValid(char candidate){
        return !secret.toString().contains(String.valueOf(candidate));
    }

    private char nextChar() {
        int position = random.nextInt(sizeOfDictionary);
        char startCharacter = '0';
        if (position > 9) {
            position -= 10;
            startCharacter = 'a';
        }
        return (char) (startCharacter + position);
    }

    public String getCipheredSecret() {
        return "*".repeat(secret.length());
    }

    public String getSecret() {
        return secret.toString();
    }

    public int length(){
        return secret.length();
    }

}
