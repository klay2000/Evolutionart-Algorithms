package selection;

import generic.EAFunction;
import generic.Solution;

public class MostFitSelection extends EAFunction {

    @Override
    public Solution[] execute(Solution[] population) {
        Solution best = population[0];

        for(Solution i : population){
            if(i.getFitness() > best.getFitness())best = i;
        }

        for(int i = 0; i < population.length; i++){
            population[i] = best;
        }

        return population;
    }
}
