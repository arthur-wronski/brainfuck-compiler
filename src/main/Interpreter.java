package main;

import java.util.Queue;

public class Interpreter {
    private final TuringMachine turingMachine;

    public Interpreter(){
        this.turingMachine = new TuringMachine();
    }

    public void executeCode(Queue<Token> instructionQueue){
        while (!instructionQueue.isEmpty()){
            Token instruction = instructionQueue.peek();
        }
    }

    private void executeLoop(Queue<Token> instructionQueue){

    }

    private void executeInstruction(Token instruction){
        switch (instruction){
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
                // otherwise nothing
            case Token.JUMP_LEFT:
                // if value at pointer is 0 -> move back to instruction after corresponding [
                // otherwise nothing
        }
    }
}
