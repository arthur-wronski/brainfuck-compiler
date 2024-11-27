package main;

import java.util.Scanner;

public class TuringMachine {
    private final byte[] tape;
    private int headPointer;

    public TuringMachine(){
        // tape length should be 30,000
        this.tape = new byte[30000];

        // head starts at beginning of tape
        this.headPointer = 0;
    }

    public byte getValueAtPointer(){
        return tape[headPointer];
    }

    public void moveHeadRight(){
        headPointer += 1;
        if (headPointer >= tape.length){
            throw new ArrayIndexOutOfBoundsException("Error: head pointer tried to move right beyond end of tape");
        }
    }

    public void moveHeadLeft(){
        headPointer -= 1;

        if (headPointer < 0){
            throw new ArrayIndexOutOfBoundsException("Error: head pointer tried to move left on index 0");
        }
    }

    public void incrementByte(){
        tape[headPointer] += 1;
    }

    public void decrementByte(){
        tape[headPointer] -= 1;
    }

    public void outputChar(){
        // casting byte to char to get ASCII representation
        char characterToBePrinted = (char) getValueAtPointer();

        System.out.print(characterToBePrinted);
    }

    public void storeInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter character to be stored: ");

        char inputtedCharacter = scanner.next().charAt(0);

        // typecast  character to ASCII value in byte
        tape[headPointer] = (byte) inputtedCharacter;
    }
}
