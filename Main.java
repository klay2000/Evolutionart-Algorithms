public class Main {

    public static void main(String[] args){

        BitInversion inversion = new BitInversion();
        inversion.setProbability(0.25);

        EvolutionaryAlgorithm test = new EvolutionaryAlgorithm(new TestFitness(), inversion, new EAFunction(), 10, 10, Solution.initGenes.zeros);

        for(boolean i : test.getBestPopulationMember().getGenome()){
            System.out.print(i + " ");
        }
        System.out.println(test.getBestPopulationMember().getFitness());

        test.runEpoch();

        for(boolean i : test.getBestPopulationMember().getGenome()){
            System.out.print(i + " ");
        }
        System.out.println(test.getBestPopulationMember().getFitness());

    }

}
