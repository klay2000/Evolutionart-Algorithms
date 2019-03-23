package fitness;

import generic.EAFunction;
import generic.MBFInterpreter;
import generic.Solution;

public class BFGenProgFitness extends EAFunction {

    private int [] targetOutput;
    private int maxCycles;

     public BFGenProgFitness(int maxCycles, int[]targetOutput){
         this.maxCycles = maxCycles;

         this.targetOutput = targetOutput;
     }

    @Override
    public Solution[] execute(Solution[] population) {

        for(int i = 0; i < population.length; i++){

            boolean [] genes = population[i].getGenome();

            MBFInterpreter interpreter = new MBFInterpreter(1000, genes);

            int fitness = 0;

            int targetInd = 0;

            for(int n = 0; n < maxCycles; n++){
                int out = interpreter.runCycle();

                if(out!=-1) {
                    if (out == targetOutput[targetInd]) fitness+=5;
                    else fitness-=2;
//                    System.out.println(out);
                    targetInd++;

                }//else if(interpreter.getCurentMemory() == targetInd) fitness++;


                if(targetInd >= targetOutput.length || interpreter.isDone()) break;
            }

            population[i].setFitness(fitness);
        }

        return population;
    }
}
