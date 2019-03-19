public class EvolutionaryAlgorithm {

    private Solution[] population;

    final private EAFunction fitness;
    final private EAFunction selection;
    final private EAFunction mutation;
    final private EAFunction crossover;

    public EvolutionaryAlgorithm(EAFunction fitness, EAFunction selection, EAFunction mutation, EAFunction crossover, int populationSize, int genomeSize, Solution.initGenes init)throws IncompatibleFunctionsException{

        this.fitness = fitness;
        this.selection = selection;
        this.mutation = mutation;
        this.crossover = crossover;

        if(!(selection.isWeighted==mutation.isWeighted && mutation.isWeighted==crossover.isWeighted)) throw new IncompatibleFunctionsException("");

        population = new Solution[populationSize];

        for(int i = 0; i < populationSize; i++){
            population[i] = new Solution(genomeSize, init);
        }

    }

    /*
     *Runs an epoch.
     */
    void runEpoch(){

        fitness.execute(population);
        selection.execute(population);
        mutation.execute(population);
        crossover.execute(population);

    }

    /*
     *Gets population.
     */
    Solution [] getPopulation(){ return population;}

    /*
     *Returns best solution in population.
     */
    Solution getBestPopulationMember(){

        fitness.execute(population);

        Solution best = population[0];

        for (Solution i : population) {
            if (i.getFitness() > best.getFitness()) best = i;
        }

        return best;
    }

}
