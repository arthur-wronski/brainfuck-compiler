package test;

import main.Interpreter;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class InterpreterTests {

    private Interpreter interpreter;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() {
        interpreter = new Interpreter();  // Initialize interpreter
        outputStream = new ByteArrayOutputStream();  // Capture standard output
        System.setOut(new PrintStream(outputStream));  // Redirect System.out to our outputStream
    }

    @Test
    public void testHelloWorldProgram() throws Exception {
        String code = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>."
                + ">---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";  // Brainfuck program for "Hello World!"

        interpreter.run(code);

        String output = outputStream.toString().trim();  // Get output from captured stream
        assertEquals("Hello World!", output);  // Assert that the output matches "Hello World!"
    }

    @Test
    public void testEmptyCodeExecution() throws Exception {
        String code = "";  // Empty Brainfuck code
        interpreter.run(code);

        String output = outputStream.toString().trim();
        assertTrue(output.isEmpty());  // No output expected for empty code
    }

    @Test
    public void testSimpleIncrementAndMove() throws Exception {
        String code = "++++";  // Increment four times
        interpreter.run(code);

        String output = outputStream.toString().trim();
        assertEquals(4, interpreter.getTuringMachine().getValueAtPointer());
    }

    @Test(expected = Exception.class)
    public void testUnmatchedBracketsInCode() throws Exception {
        String code = "++[->>++[--<<>";  // Invalid Brainfuck code with unmatched bracket
        interpreter.run(code);  // This should throw an exception
    }

    @Test
    public void testSimpleOutput() throws Exception {
        int targetValue = 65;

        interpreter.run("+".repeat(targetValue) + ".");

        String output = outputStream.toString().trim();
        assertEquals("A", output);  // Check if the output is 'A'
    }
}
