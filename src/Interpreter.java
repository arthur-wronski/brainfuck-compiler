import java.util.Queue;

public class Interpreter {
    private final TuringMachine turingMachine;

    public Interpreter(){
        this.turingMachine = new TuringMachine();
    }

    public void executeCode(Queue<Token> instructionQueue){
        while (!instructionQueue.isEmpty()){
            executeInstruction(instructionQueue.poll());
        }
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
        }
    }
}
