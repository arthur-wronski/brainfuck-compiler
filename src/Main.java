import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception {
        Lexer lexer = new Lexer();
        lexer.tokenizeCode("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.");

        Queue<Token> instructionQueue = new ArrayDeque<Token>(lexer.getTokenQueue());

        Parser parser = new Parser();
        parser.parseTokenQueue(lexer.getTokenQueue());

        Interpreter interpreter = new Interpreter();
        interpreter.executeCode(instructionQueue);
    }
}