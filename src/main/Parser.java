package main;

import java.util.Queue;

// here's how you construct valid code in Brainfuck:
// alphabet: Σ := > | < | + | - | . | , | [ | ]
// Operator: Ω:= > | < | + | - | . | ,
// Valid Code: Code := [Code] | Ω*

public class Parser {
    public void parseTokenQueue(Queue<Token> tokenQueue) throws Exception{
        // if queue not empty -> error in parsing throw exception
        outerParser(tokenQueue);
        if (!tokenQueue.isEmpty()){
            throw new Exception("Error: found loop closure ] with no corresponding loop initiator [");
        }
//        System.out.println("Parsing successful");
    }

    void outerParser(Queue<Token> tokenQueue) throws Exception{
        while (!tokenQueue.isEmpty()) {
            Token token = tokenQueue.peek();

            if (token == Token.JUMP_LEFT){
                return;
            } else if (token == Token.JUMP_RIGHT) {
                tokenQueue.poll();
                loopParser(tokenQueue);
            }else {
                tokenQueue.poll();
            }

        }
        // queue being empty is fine
    }

    private void loopParser(Queue<Token> tokenQueue) throws Exception {
        outerParser(tokenQueue);

        if (tokenQueue.poll() != Token.JUMP_LEFT){
            throw new Exception("Error: found loop initiator with no corresponding loop closure ]");
        }
    }
}
