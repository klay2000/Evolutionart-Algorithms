package mutation;

import generic.EAFunction;
import generic.Solution;

public class SwapMutation extends EAFunction {

    private double probability;

    /*
    *Sets probability.
     */
    void setProbability(double probability){ this.probability = probability; }

    /*
    *Gets probability.
     */
    double getProbability(){ return probability; }

    public SwapMutation(double probability){

        this.probability = probability;

    }

    @Override
    public Solution[] execute(Solution[] population) {

        for(Solution i : population){

            boolean[] genes = i.getGenome();

            for(int n = 0; n < genes.length; n++){
                if(Math.random() <= probability){
                    boolean temp = genes[n];
                    int swapLocation = (int)Math.floor(Math.random()*genes.length);
                    genes[n] = genes[swapLocation];
                    genes[swapLocation] = temp;
                }
            }
        }

        return population;
    }
}
