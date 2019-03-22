package fitness;

import generic.EAFunction;
import generic.Solution;

public class RandomMatchFitness extends EAFunction {

    boolean [] targetSequence;

    public RandomMatchFitness(int length){
        targetSequence = new boolean [length];

        for(int i = 0; i < targetSequence.length; i++){
            targetSequence[i] = Math.random() <= 0.5;
            System.out.print(targetSequence[i]+" ");
        }
        System.out.println();
    }

    @Override
    public Solution[] execute(Solution[] population) {

        for (Solution aPopulation : population) {
            boolean[] genes = aPopulation.getGenome();

            int fitness = 0;

            for (int i = 0; i < genes.length; i++) {
                if (genes[i] == targetSequence[i]) fitness++;
            }

            aPopulation.setFitness(fitness);
        }

        return population;
    }
}
