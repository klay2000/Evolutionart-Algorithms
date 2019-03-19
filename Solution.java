public class Solution {

    public enum initGenes{
        zeros,
        ones,
        random
    }

    private boolean[] genome;

    private double fitness = 0;

    private double selectionWeight = 0;

    public Solution(int genomeSize, initGenes init){

        genome = new boolean[genomeSize];

        switch (init){

            case ones:

                for(int i = 0; i < genomeSize; i ++)
                    genome[i] = true;

                break;

            case zeros:

                for(int i = 0; i < genomeSize; i ++)
                        genome[i] = false;

                 break;

            case random:

                for(int i = 0; i < genomeSize; i ++)
                        genome[i] = Math.round(Math.random()) == 1;

                break;

        }

    }

    /*
     *Gets genome.
     */
    public boolean[] getGenome(){
        return genome;
    } //gets genome

    /*
     *Sets genome.
     * @Param genome Value to set.
     */
    public void setGenome( boolean[] genome){
        this.genome = genome;
    } //sets genome

    /*
     *Gets fitness.
     */
    public double getFitness() { return fitness; } //gets fitness

    /*
     *Sets fitness.
     * @Param fitness Value to set.
     */
    public void setFitness(double fitness) { this.fitness = fitness; } //sets fitness

    /*
    *Sets weight from selection.
    *@Param selectionWeight Value to set.
     */
    public void setSelectionWeight(double selectionWeight) { this.selectionWeight = selectionWeight; }

    /*
     *Gets weight from selection.
     */
    public double getSelectionWeight(){ return  selectionWeight; }
}
