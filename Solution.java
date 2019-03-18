public class Solution {

    public enum initGenes{
        zeros,
        ones,
        random
    }

    private boolean[] genome;

    private double fitness;

    public Solution(int genomeSize, initGenes init){

        genome = new boolean[genomeSize];

        switch (init){

            case ones:

                for(boolean i : genome)
                    i = true;
                break;

            case zeros:
                    for(boolean i : genome)
                        i = false;
                 break;

            case random:
                    for(boolean i : genome)
                        i = Math.round(Math.random()) == 1;
                break;

        }

    }

    public boolean[] getGenome(){
        return genome;
    } //gets genome

    public void setGenome( boolean[] genome){
        this.genome = genome;
    } //sets genome

    public double getFitness() { return fitness; } //gets fitness

    public void setFitness(double fitness) { this.fitness = fitness; } //sets fitness

}
