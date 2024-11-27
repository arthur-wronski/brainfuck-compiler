package test;

import main.Lexer;
import main.Token;
import org.junit.Before;
import org.junit.Test;

import java.util.Queue;

import static org.junit.Assert.*;

public class LexerTests {

    private Lexer lexer;

    @Before
    public void setUp() {
        lexer = new Lexer();  // Initialize lexer before each test
    }

    @Test
    public void testValidTokens() {
        String code = "++--><";
        lexer.tokenizeCode(code);
        Queue<Token> tokens = lexer.getTokenQueue();

        assertEquals(6, tokens.size());  // Expecting 6 tokens
        assertEquals(Token.INCREMENT, tokens.poll());
        assertEquals(Token.INCREMENT, tokens.poll());
        assertEquals(Token.DECREMENT, tokens.poll());
        assertEquals(Token.DECREMENT, tokens.poll());
        assertEquals(Token.MOVE_RIGHT, tokens.poll());
        assertEquals(Token.MOVE_RIGHT, tokens.poll());
    }

    @Test
    public void testIgnoreInvalidCharacters() {
        String code = "++abc--xyz><";
        lexer.tokenizeCode(code);
        Queue<Token> tokens = lexer.getTokenQueue();

        assertEquals(6, tokens.size());  // Only 6 valid tokens (+, +, -, -, >, <)
        assertEquals(Token.INCREMENT, tokens.poll());
        assertEquals(Token.INCREMENT, tokens.poll());
        assertEquals(Token.DECREMENT, tokens.poll());
        assertEquals(Token.DECREMENT, tokens.poll());
        assertEquals(Token.MOVE_RIGHT, tokens.poll());
        assertEquals(Token.MOVE_RIGHT, tokens.poll());
    }

    @Test
    public void testEmptyCode() {
        String code = "";
        lexer.tokenizeCode(code);
        Queue<Token> tokens = lexer.getTokenQueue();

        assertTrue(tokens.isEmpty());  // Should be empty since the input is empty
    }

    @Test
    public void testOnlyInvalidCharacters() {
        String code = "abcdef";
        lexer.tokenizeCode(code);
        Queue<Token> tokens = lexer.getTokenQueue();

        assertTrue(tokens.isEmpty());  // No valid tokens, queue should be empty
    }

    @Test
    public void testMixedValidAndInvalidCharacters() {
        String code = "+-abc><";
        lexer.tokenizeCode(code);
        Queue<Token> tokens = lexer.getTokenQueue();

        assertEquals(4, tokens.size());  // Only 4 valid tokens (+, -, >, <)
        assertEquals(Token.INCREMENT, tokens.poll());
        assertEquals(Token.DECREMENT, tokens.poll());
        assertEquals(Token.MOVE_RIGHT, tokens.poll());
        assertEquals(Token.MOVE_RIGHT, tokens.poll());
    }
}
