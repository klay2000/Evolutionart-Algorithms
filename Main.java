import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IncompatibleFunctionsException {

        EvolutionaryAlgorithm test = new EvolutionaryAlgorithm(new PolynomialMatchFitness(4, 0, 10), new MostFitWeightedSelection(), new BitInversionMutation(1.0/64.0), new KPointCrossover(6, 2), 20000, 32, Solution.initGenes.random);

        try{

            BufferedWriter graph = new BufferedWriter(new FileWriter("data.csv"));

            for( int i = 1000; i > 0; i--) {
                test.runEpoch();

                if(i%10 == 0) {
                    System.out.println(test.getBestPopulationMember().getFitness());
                    graph.write(test.getBestPopulationMember().getFitness() + ",");
                    graph.newLine();
                }

    //            for(boolean b : test.getBestPopulationMember().getGenome())System.out.print(b);
    //            System.out.println();
            }

            graph.close();

        } catch (IOException e){}
    }

}
