package test;

import main.Lexer;
import main.Parser;
import main.Token;
import org.junit.Before;
import org.junit.Test;

import java.util.Queue;

import static org.junit.Assert.*;

public class ParserTests {

    private Lexer lexer;
    private Parser parser;

    @Before
    public void setUp() {
        lexer = new Lexer();  // Initialize lexer
        parser = new Parser();  // Initialize parser
    }

    @Test
    public void testValidCode() throws Exception {
        String code = "++[--]>";  // Valid Brainfuck code
        lexer.tokenizeCode(code);
        Queue<Token> tokens = lexer.getTokenQueue();

        parser.parseTokenQueue(tokens);  // Should parse successfully without exception

        assertTrue(tokens.isEmpty());  // Ensure all tokens were parsed
    }

    @Test(expected = Exception.class)
    public void testUnmatchedOpeningBracket() throws Exception {
        String code = "++[--";  // Missing closing bracket
        lexer.tokenizeCode(code);
        Queue<Token> tokens = lexer.getTokenQueue();

        parser.parseTokenQueue(tokens);  // Should throw an exception
    }

    @Test(expected = Exception.class)
    public void testUnmatchedClosingBracket() throws Exception {
        String code = "++--]";  // Unmatched closing bracket
        lexer.tokenizeCode(code);
        Queue<Token> tokens = lexer.getTokenQueue();

        parser.parseTokenQueue(tokens);  // Should throw an exception
    }

    @Test
    public void testEmptyCode() throws Exception {
        String code = "";  // Empty code should be valid
        lexer.tokenizeCode(code);
        Queue<Token> tokens = lexer.getTokenQueue();

        parser.parseTokenQueue(tokens);  // Should parse successfully
        assertTrue(tokens.isEmpty());
    }

    @Test
    public void testNestedLoops() throws Exception {
        String code = "++[->>++[--<<]>]";  // Valid nested loops
        lexer.tokenizeCode(code);
        Queue<Token> tokens = lexer.getTokenQueue();

        parser.parseTokenQueue(tokens);  // Should parse successfully
        assertTrue(tokens.isEmpty());
    }
}
