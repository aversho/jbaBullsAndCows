package bullscows;

public class GameException extends RuntimeException{
    public GameException(String message){
        super("Error: " + message);
    }
}
