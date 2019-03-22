package selection;

import generic.EAFunction;
import generic.Solution;

public class MostFitWeightedSelection extends EAFunction {

    final Boolean isWeighted = true;

    @Override
    public Solution[] execute(Solution[] population) {

        for(int i = 0; i < population.length; i++){
            population[i].setSelectionWeight(population[i].getFitness());
        }

        return population;
    }
}
