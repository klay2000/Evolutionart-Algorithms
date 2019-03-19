public class Main {

    public static void main(String[] args) throws IncompatibleFunctionsException {

        BitInversion inversion = new BitInversion();
        inversion.setProbability(0.001);

        EvolutionaryAlgorithm test = new EvolutionaryAlgorithm(new TestFitness(), new MostFitSelection(), inversion, new EAFunction(), 100, 100, Solution.initGenes.zeros);

        for( int i = 10000; i> 0; i--) {
            test.runEpoch();

            System.out.println(test.getBestPopulationMember().getFitness());

            if(test.getBestPopulationMember().getFitness() == 100) break;
        }
    }

}
