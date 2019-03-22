import crossover.KPointCrossover;
import fitness.PolynomialMatchFitness;
import fitness.RandomMatchFitness;
import generic.EvolutionaryAlgorithm;
import generic.IncompatibleFunctionsException;
import generic.Solution;
import mutation.BitInversionMutation;
import selection.MostFitWeightedSelection;
import selection.TournamentSelection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IncompatibleFunctionsException {

        EvolutionaryAlgorithm test = new EvolutionaryAlgorithm(new RandomMatchFitness(100), new MostFitWeightedSelection(), new BitInversionMutation(0.01), new KPointCrossover(15, 10), 100, 100, Solution.initGenes.random);

        try{

            BufferedWriter graph = new BufferedWriter(new FileWriter("data.csv"));

            for( int i = 50; i > 0; i--) {
                test.runEpoch();

                //if(i%10 == 0) {
                    System.out.println(test.getBestPopulationMember().getFitness());
                    graph.write(test.getBestPopulationMember().getFitness() + ",");
                    graph.newLine();
                //}

                for(boolean b : test.getBestPopulationMember().getGenome())System.out.print(b+" ");
                System.out.println();
            }

            graph.close();

        } catch (IOException ignored){}
    }

}
