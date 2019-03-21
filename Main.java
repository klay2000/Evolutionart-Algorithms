public class Main {

    public static void main(String[] args) throws IncompatibleFunctionsException {

        EvolutionaryAlgorithm test = new EvolutionaryAlgorithm(new TestFitness(), new MostFitWeightedSelection(), new BitInversion(0.001), new SinglePointCrossover(4), 12, 300, Solution.initGenes.random);

        for( int i = 100000; i> 0; i--) {
            test.runEpoch();

            System.out.println(test.getBestPopulationMember().getFitness());

            if(test.getBestPopulationMember().getFitness() == 100) break;
        }
    }

}
