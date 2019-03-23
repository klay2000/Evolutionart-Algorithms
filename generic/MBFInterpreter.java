package generic;

import java.util.Arrays;

public class MBFInterpreter {

    private char[] program;
    private int[] memory;
    private int programPos = 0;
    private int pointer = 0;
    private boolean done = false;

    public MBFInterpreter(int memorySize, boolean[] binaryProgram){
        memory = new int[memorySize];

        program = binaryProgToChar(binaryProgram);
    }

    public int runCycle(){
        int out = -1;

        if(!done) {

            switch (program[programPos]) {
                case '>':
                    pointer++;
                    break;

                case '<':
                    pointer--;
                    break;

                case '+':
                    memory[pointer]++;
                    break;

                case '-':
                    memory[pointer]--;
                    break;

                case '.':
                    out = memory[pointer];
                    break;

                case '[':

                    if(memory[pointer] == 0 && programPos+1 != program.length) {
                        int i = 0;
                        int pos = programPos+1;

                        while (!(i == 0 && program[pos] == ']')) {
                            if (program[pos] == '[') i++;
                            if (program[pos] == ']') i--;
                            pos++;

                            //System.out.println(program[pos] +  " " + i);

                            if(pos >= program.length) break;
                        }
                        programPos = pos;
                    }

                    break;

                case ']':

                    if(memory[pointer] != 0 && programPos+1 != program.length) {
                        int i = 0;
                        int pos = programPos+1;

                        while (!(i <= 0 && program[pos] == '[')) {
                            if (program[pos] == ']') i++;
                            if (program[pos] == '[') i--;
                            pos--;

                            if(pos <= 0) break;
                        }
                        programPos = pos-1;
                    }
                    break;
            }

            if (pointer < 0) pointer = memory.length;
            if (pointer >= memory.length) pointer = 0;

            programPos++;

            if (programPos >= program.length) done = true;
            //else System.out.println(program[programPos]);

        }

        return out;
    }

    public boolean isDone(){
        return done;
    }

    public int getCurentMemory(){
        return memory[pointer];
    }

    private char[] binaryProgToChar(boolean[] binaryProgram){

        char[] progChars = new char[binaryProgram.length/3];

        for(int i = 0; i < progChars.length; i++){
            boolean[] instruction = {binaryProgram[i*3], binaryProgram[i*3+1], binaryProgram[i*3+2]};

            if (Arrays.equals(instruction, new boolean[]{true, true, true})){ progChars[i] = '>'; }
            else if (Arrays.equals(instruction, new boolean[]{true, true, false}))progChars[i] = '<';
            else if (Arrays.equals(instruction, new boolean[]{true, false, false}))progChars[i] = '+';
            else if (Arrays.equals(instruction, new boolean[]{false, false, true}))progChars[i] = '-';
            else if (Arrays.equals(instruction, new boolean[]{false, true, true}))progChars[i] = '.';
            else if (Arrays.equals(instruction, new boolean[]{true, false, true}))progChars[i] = '[';
            else if (Arrays.equals(instruction, new boolean[]{false, true, false}))progChars[i] = ']';

           // System.out.print(progChars[i]);

        }
        //System.out.println();

        return progChars;

    }

}
