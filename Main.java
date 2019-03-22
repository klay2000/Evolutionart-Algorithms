public class Main {

    public static void main(String[] args) throws IncompatibleFunctionsException {

        EvolutionaryAlgorithm test = new EvolutionaryAlgorithm(new TestFitness(), new TournamentWeightedSelection(2), new BitInversion(0.001), new KPointCrossover(9, 2), 140, 200, Solution.initGenes.random);

        for( int i = 100; i> 0; i--) {
            test.runEpoch();

            System.out.println(test.getBestPopulationMember().getFitness());
        }
    }

}
