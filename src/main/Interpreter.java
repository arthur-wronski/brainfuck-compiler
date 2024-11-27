package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

public class Interpreter {
    private final TuringMachine turingMachine;
    private List<Token> instructionList;
    private int instructionPointer;
    private final Map<Integer,Integer> loopIndexes;

    public Interpreter(){
        this.turingMachine = new TuringMachine();
        this.instructionList = new ArrayList<>();
        this.instructionPointer = 0;
        this.loopIndexes = new HashMap<Integer, Integer>();
    }

    public TuringMachine getTuringMachine() {
        return turingMachine;
    }

    private void setInstructionList(List<Token> instructionList){
        this.instructionList = instructionList;
    }

    private void setLoopIndexes(){
        // keep indexes in stack
        Stack<Integer> loopStartIndexes = new Stack<>();

        for (int idx = 0; idx< instructionList.size(); idx++){
            if (instructionList.get(idx) == Token.JUMP_RIGHT){
                loopStartIndexes.push(idx);
            } else if (instructionList.get(idx) == Token.JUMP_LEFT) {
                int start = loopStartIndexes.pop();
                loopIndexes.put(start, idx);
                loopIndexes.put(idx, start);
            }
        }
    }

    public void run(String code) throws Exception {
        Lexer lexer = new Lexer();
        lexer.tokenizeCode(code);

        List<Token> instructionList = new ArrayList<>(lexer.getTokenQueue());

        Parser parser = new Parser();
        parser.parseTokenQueue(lexer.getTokenQueue());

        setInstructionList(instructionList);
        setLoopIndexes();
        executeCode();
    }

    private void executeCode(){
        while (instructionPointer < instructionList.size()){
            Token instruction = instructionList.get(instructionPointer);
            executeInstruction(instruction);
            instructionPointer++;
        }
    }

    private void executeInstruction(Token instruction){
        switch (instruction) {
            case Token.MOVE_RIGHT:
                turingMachine.moveHeadRight();
                break;
            case Token.MOVE_LEFT:
                turingMachine.moveHeadLeft();
                break;
            case Token.INCREMENT:
                turingMachine.incrementByte();
                break;
            case Token.DECREMENT:
                turingMachine.decrementByte();
                break;
            case Token.OUTPUT_CHAR:
                turingMachine.outputChar();
                break;
            case Token.INPUT_CHAR:
                turingMachine.storeInput();
                break;
            case Token.JUMP_RIGHT:
                // if value at pointer is 0 -> move forward to instruction after corresponding ]
                if (turingMachine.getValueAtPointer() == 0) {
                    instructionPointer = loopIndexes.get(instructionPointer);
                }
                break;
            case Token.JUMP_LEFT:
                // if value at pointer is not 0 -> move back to instruction after corresponding [
                if (turingMachine.getValueAtPointer() != 0) {
                    instructionPointer = loopIndexes.get(instructionPointer);
                }
                break;
        }
    }
}
