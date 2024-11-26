import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Lexer {
    private final Queue<Token> tokenQueue;
    private final Map<Character, Token> charToTokenMap;

    public Lexer(){
        this.tokenQueue = new ArrayDeque<Token>();
        this.charToTokenMap = new HashMap<Character, Token>();
        charToTokenMap.put('>', Token.MOVE_RIGHT);
        charToTokenMap.put('<', Token.MOVE_RIGHT);
        charToTokenMap.put('+', Token.INCREMENT);
        charToTokenMap.put('-', Token.DECREMENT);
        charToTokenMap.put('.', Token.OUTPUT_CHAR);
        charToTokenMap.put(',', Token.INPUT_CHAR);
        charToTokenMap.put('[', Token.JUMP_RIGHT);
        charToTokenMap.put(']', Token.JUMP_LEFT);
    }

    void tokenizeCode(String code){
        for (char character : code.toCharArray()){
            Token token = charToTokenMap.get(character);

            if (token != null){
                tokenQueue.add(token);
            }
        }
    }

    public Queue<Token> getTokenQueue(){
        return tokenQueue;
    }
}
