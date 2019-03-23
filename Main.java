import crossover.KPointCrossover;
import fitness.BFGenProgFitness;
import fitness.PolynomialMatchFitness;
import fitness.RandomMatchFitness;
import generic.EvolutionaryAlgorithm;
import generic.IncompatibleFunctionsException;
import generic.MBFInterpreter;
import generic.Solution;
import mutation.BitInversionMutation;
import selection.MostFitWeightedSelection;
import selection.TournamentSelection;
import selection.TournamentWeightedSelection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IncompatibleFunctionsException {

        EvolutionaryAlgorithm test = new EvolutionaryAlgorithm(new BFGenProgFitness(2500, new int[]{1, 3, 5, 7, 9}), new TournamentWeightedSelection(2), new BitInversionMutation(1.0/15.0), new KPointCrossover(25, 10), 500, 15, Solution.initGenes.zeros);

        try{

            BufferedWriter graph = new BufferedWriter(new FileWriter("data.csv"));

            for( int i = 50000; i > 0; i--) {
                test.runEpoch();

                System.out.println(test.getBestPopulationMember().getFitness());
                graph.write(test.getBestPopulationMember().getFitness() + ",");
                graph.newLine();

                for(boolean b : test.getBestPopulationMember().getGenome())System.out.print(b+" ");
                System.out.println();
            }

            graph.close();

        } catch (IOException ignored){}

    }

}
