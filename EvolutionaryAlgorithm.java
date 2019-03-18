public class EvolutionaryAlgorithm {

    private Solution[] population;

    private EAFunction fitness;
    private EAFunction mutation;
    private EAFunction crossover;

    public EvolutionaryAlgorithm(EAFunction fitness, EAFunction mutation, EAFunction crossover, int populationSize, int genomeSize, Solution.initGenes init){

        this.fitness = fitness;
        this.mutation = mutation;
        this.crossover = crossover;

        population = new Solution[populationSize];

        for(Solution i : population){
            i = new Solution(genomeSize, init);
        }

    }

    void runEpoch(){

        fitness.execute(population);
        mutation.execute(population);
        crossover.execute(population);

    }

    Solution [] getPopulation(){ return population;} //returns population

    Solution getBestPopulationMember(){ //returns best solution in population

        Solution best = population[0];

        for (Solution i : population) {
            if (i.getFitness() > best.getFitness()) best = i;
        }

        return best;
    }

}
